<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body\embed\components;

use ialdrich23xx\libasynwebhook\discord\body\embed\base\Structure;
use ialdrich23xx\libasynwebhook\discord\body\embed\base\URL;

class Thumbnail extends Structure
{
    use URL;

    public function __construct(
        string $url
    ){
        $this->setUrl($url);
    }

    public static function make(string $url): self
    {
        return new self($url);
    }

    public function build(): bool
    {
        return $this->urlBuild();
    }

    public function toArray(): array
    {
        return $this->urlToArray();
    }

    public function toString(): string
    {
        return "Thumbnail(" . $this->urlToString() .  ")";
    }
}