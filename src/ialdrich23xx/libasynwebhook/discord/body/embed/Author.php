<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body\embed;

use ialdrich23xx\libasynwebhook\discord\body\embed\base\IconURL;
use ialdrich23xx\libasynwebhook\discord\body\embed\base\Name;
use ialdrich23xx\libasynwebhook\discord\body\embed\base\Structure;
use ialdrich23xx\libasynwebhook\discord\body\embed\base\URL;
use function array_merge;
use function is_null;

class Author extends Structure
{
    use URL;
    use IconURL;
    use Name;

    public function __construct(string $name) {
        $this->setName($name);
    }

    public static function make(string $name): self
    {
        return new self($name);
    }

    public function build(): bool
    {
        if (is_null($this->getName())) return false;

        if (!is_null($this->getIcon()) && !$this->iconBuild()) return false;
        if (!is_null($this->getUrl()) && !$this->urlBuild()) return false;

        return true;
    }

    public function toArray(): array
    {
        $result = $this->nameToArray();

        if (!is_null($this->getIcon())) $result = array_merge($result, $this->iconToArray());
        if (!is_null($this->getUrl())) $result = array_merge($result, $this->urlToArray());

        return $result;
    }

    public function toString(): string
    {
        return "Field(" . $this->nameToString() . "," . $this->urlToString() . "," . $this->iconToString() . ")";
    }
}