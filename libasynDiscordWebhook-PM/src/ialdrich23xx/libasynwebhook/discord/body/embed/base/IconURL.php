<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body\embed\base;

use ialdrich23xx\libasynwebhook\Loader;
use function strlen;

trait IconURL
{
    private string $icon = "";

    public function setIcon(string $icon): self
    {
        $this->icon = $icon;

        return $this;
    }

    public function getIcon(): string
    {
        return $this->icon;
    }

    public function iconBuild(): bool
    {
        if (strlen($this->getIcon()) === 0) return false;

        return Loader::getInstance()->isValidUrl($this->getIcon());
    }

    public function iconToArray(): array
    {
        return ["icon_url" => $this->getIcon()];
    }

    public function iconToString(): string
    {
        return "icon=" . $this->getIcon();
    }
}