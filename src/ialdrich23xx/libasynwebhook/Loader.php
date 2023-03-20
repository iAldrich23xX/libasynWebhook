<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook;

use ialdrich23xx\libasynwebhook\thread\Pool;
use pocketmine\plugin\PluginBase;
use pocketmine\scheduler\ClosureTask;
use pocketmine\Server;
use pocketmine\utils\SingletonTrait;

class Loader
{
    use SingletonTrait;

    private Pool $threadPool;

    public function __construct(
        private PluginBase $pluginBase
    ) {
        self::$instance = $this;

        $server = Server::getInstance();

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

    public function getThread(): Pool
    {
        return $this->threadPool;
    }
}