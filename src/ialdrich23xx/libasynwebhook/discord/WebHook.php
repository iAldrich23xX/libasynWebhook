<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord;

use ialdrich23xx\libasynwebhook\discord\body\Base;
use ialdrich23xx\libasynwebhook\Loader;
use ialdrich23xx\libasynwebhook\thread\SendWebHookTask;
use JsonException;

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

    public function setBody(Base $body): void
    {
        $this->body = $body;
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
            Loader::getInstance()->getThread()->submitTask(new SendWebHookTask($this));
        } else Loader::getInstance()->getPlugin()->getLogger()->error("Url not valid: " . $this->getUrl());
    }
}