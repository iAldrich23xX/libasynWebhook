<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook;

use ialdrich23xx\libasynwebhook\thread\Pool;
use pocketmine\plugin\PluginBase;
use pocketmine\scheduler\AsyncPool;
use pocketmine\scheduler\ClosureTask;
use pocketmine\Server;
use pocketmine\utils\SingletonTrait;
use function is_null;

class Loader
{
    use SingletonTrait;

    private AsyncPool|Pool $threadPool;

    public function __construct(
        private PluginBase $pluginBase,
        ?AsyncPool $pool = null
    ) {
        self::$instance = $this;

        $server = Server::getInstance();

        if (!is_null($pool)) {
            $this->threadPool = $pool;
            return;
        }

        $this->threadPool = new Pool(
            Pool::POOL_SIZE,
            Pool::MEMORY_LIMIT,
            $server->getLoader(),
            $server->getLogger(),
            $server->getTickSleeper()
        );

        $pluginBase->getScheduler()->scheduleRepeatingTask(new ClosureTask(function (): void {
            $this->threadPool->collectTasks();
        }), Pool::COLLECT_INTERVAL);

        $pluginBase->getScheduler()->scheduleRepeatingTask(new ClosureTask(function (): void {
            $this->threadPool->triggerGarbageCollector();
        }), Pool::GARBAGE_COLLECT_INTERVAL);
    }

    public static function make(PluginBase $pluginBase): self
    {
        return new self($pluginBase);
    }

    public function getPlugin(): PluginBase
    {
        return $this->pluginBase;
    }

    public function getThread(): AsyncPool|Pool
    {
        return $this->threadPool;
    }

    public function isValidUrl(string $url): bool
    {
        return filter_var($url, FILTER_VALIDATE_URL) !== false;
    }
}