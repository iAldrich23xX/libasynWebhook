<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\thread;

use pocketmine\scheduler\AsyncPool;
use pocketmine\scheduler\DumpWorkerMemoryTask;
use pocketmine\scheduler\GarbageCollectionTask;
use function gc_collect_cycles;

class Pool extends AsyncPool
{
    public const MEMORY_LIMIT = 256; // 256MB Limit
    public const POOL_SIZE = 2; // 2 workers
    public const COLLECT_INTERVAL = 1; // 1 tick
    public const GARBAGE_COLLECT_INTERVAL = 15 * 60 * 20; // 15 minutes

    public function dumpMemory(string $outputFolder, int $maxNesting, int $maxStringSize): void
    {
        foreach ($this->getRunningWorkers() as $i) {
            $this->submitTaskToWorker(new DumpWorkerMemoryTask($outputFolder, $maxNesting, $maxStringSize), $i);
        }
    }

    public function triggerGarbageCollector(): int
    {
        $this->shutdownUnusedWorkers();

        foreach ($this->getRunningWorkers() as $i) {
            $this->submitTaskToWorker(new GarbageCollectionTask(), $i);
        }

        return gc_collect_cycles();
    }
}