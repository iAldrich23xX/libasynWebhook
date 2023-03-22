<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body\embed\base;

use ialdrich23xx\libasynwebhook\Loader;
use function strlen;

trait URL
{
    private string $url = "";

    public function setUrl(string $url): self
    {
        $this->url = $url;

        return $this;
    }

    public function getUrl(): string
    {
        return $this->url;
    }

    public function urlBuild(): bool
    {
        if (strlen($this->getUrl()) === 0) return false;

        return Loader::getInstance()->isValidUrl($this->getUrl());
    }

    public function urlToArray(): array
    {
        return ["url" => $this->getUrl()];
    }

    public function urlToString(): string
    {
        return "url=" . $this->getUrl();
    }
}