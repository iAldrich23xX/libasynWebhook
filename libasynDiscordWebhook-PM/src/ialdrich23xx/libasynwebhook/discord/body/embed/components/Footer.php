<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body\embed\components;

use ialdrich23xx\libasynwebhook\discord\body\embed\base\IconURL;
use ialdrich23xx\libasynwebhook\discord\body\embed\base\Structure;
use function array_merge;
use function strlen;

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
        if (strlen($this->getText()) === 0) return false;
        if (strlen($this->getIcon()) !== 0 && !$this->iconBuild()) return false;

        return true;
    }

    public function toArray(): array
    {
        $result = [];

        $result["text"] = $this->getText();

        if (strlen($this->getIcon()) !== 0) $result = array_merge($result, $this->iconToArray());

        return $result;
    }

    public function toString(): string
    {
        return "Footer(text=" . $this->getText() . "," . $this->iconToString() . ")";
    }
}