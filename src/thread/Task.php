<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\thread;

use ialdrich23xx\libasynwebhook\discord\WebHook;
use pocketmine\scheduler\AsyncTask;
use pocketmine\utils\Internet;
use function var_dump;

class Task extends AsyncTask
{
    public function __construct(
        private WebHook $webHook
    ) {}

    public function onRun(): void
    {
        $this->setResult(Internet::postURL(
            $this->webHook->getUrl(), $this->webHook->getBody()->jsonSerialize(), 10, ["Content-Type: application/json"]
        ));
    }

    public function onCompletion(): void
    {
        $response = $this->getResult();

        var_dump($response);
    }
}