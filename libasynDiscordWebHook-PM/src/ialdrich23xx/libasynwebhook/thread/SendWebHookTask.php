<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\thread;

use JsonException;
use pocketmine\scheduler\AsyncTask;
use pocketmine\thread\NonThreadSafeValue;
use pocketmine\utils\Internet;
use ialdrich23xx\libasynwebhook\discord\WebHook;
use function is_null;
use function json_encode;
use const JSON_THROW_ON_ERROR;

class SendWebHookTask extends AsyncTask
{
    private string $page;
    private int $timeout;
    private string $args;
    /** @phpstan-var NonThreadSafeValue<array> */
    protected NonThreadSafeValue $headers;

    /**
     * @throws JsonException
     */
    public function __construct(WebHook $webHook) {
        if (is_null($webHook->getBody())) {
            $this->cancelRun();

            return;
        }

        $this->args = json_encode($webHook->getBody()->jsonSerialize(), JSON_THROW_ON_ERROR);

        $this->page = $webHook->getUrl();
        $this->timeout = 10;

        /** @phpstan-ignore-next-line */
        $this->headers = new NonThreadSafeValue(["Content-Type: application/json"]);
    }

    /**
     * @return string[]
     */
    public function getHeaders(): array
    {
        return $this->headers->deserialize();
    }

    public function onRun(): void
    {
        $this->setResult(Internet::postURL(
            $this->page, $this->args, $this->timeout, $this->getHeaders()
        ));
    }

    public function onCompletion(): void {}
}