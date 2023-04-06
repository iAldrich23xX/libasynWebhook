<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord;

use JsonException;
use ialdrich23xx\libasynwebhook\discord\body\Base;
use ialdrich23xx\libasynwebhook\Loader;
use ialdrich23xx\libasynwebhook\thread\SendWebHookTask;
use function is_null;

class WebHook
{
    public function __construct(
        private string $url,
        private ?Base $body = null
    ) {}

    public static function make(string $url, ?Base $body = null): self
    {
        return new self($url, $body);
    }

    public function getUrl(): string
    {
        return $this->url;
    }

    public function getBody(): ?Base
    {
        return $this->body;
    }

    public function setBody(Base $body): self
    {
        $this->body = $body;

        return $this;
    }

    /**
     * @throws JsonException
     */
    public function send(): void
    {
        if (is_null($this->getBody())) {
            Loader::getInstance()->getPlugin()->getLogger()->error("Body of webhook is null");
            return;
        }

        if (Loader::getInstance()->isValidUrl($this->getUrl())) {
            if ($this->getBody()->build()) {
                Loader::getInstance()->getThread()->submitTask(new SendWebHookTask($this));
            } else Loader::getInstance()->getPlugin()->getLogger()->error("Webhook build failed");
        } else Loader::getInstance()->getPlugin()->getLogger()->error("Url not valid: " . $this->getUrl());
    }
}