<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body\embed;

use ialdrich23xx\libasynwebhook\discord\body\embed\base\IconURL;
use ialdrich23xx\libasynwebhook\discord\body\embed\base\Structure;
use ialdrich23xx\libasynwebhook\Loader;
use function array_merge;
use function is_null;

class Footer extends Structure
{
    use IconURL;

    public function __construct(
        private string $text
    ){}

    public static function make(string $text): self
    {
        return new self($text);
    }

    public function setText(string $text): self
    {
        $this->text = $text;

        return $this;
    }

    public function getText(): string
    {
        return $this->text;
    }

    public function build(): bool
    {
        if (is_null($this->getText())) return false;
        if (!is_null($this->getIcon()) && !Loader::getInstance()->isValidUrl($this->getIcon())) return false;

        return true;
    }

    public function toArray(): array
    {
        $result = [];

        $result["text"] = $this->getText() ?? "null";

        if (!is_null($this->getIcon())) $result = array_merge($result, $this->iconToArray());

        return $result;
    }

    public function toString(): string
    {
        return "Footer(text=" . $this->getText() ?? "null" . "," . $this->iconToString() . ")";
    }
}