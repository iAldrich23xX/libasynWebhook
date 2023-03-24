<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body\embed\components;

use ialdrich23xx\libasynwebhook\discord\body\embed\base\IconURL;
use ialdrich23xx\libasynwebhook\discord\body\embed\base\Name;
use ialdrich23xx\libasynwebhook\discord\body\embed\base\Structure;
use ialdrich23xx\libasynwebhook\discord\body\embed\base\URL;
use function array_merge;
use function strlen;

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
        if (strlen($this->getName()) === 0) return false;

        if (strlen($this->getIcon()) !== 0 && !$this->iconBuild()) return false;
        if (strlen($this->getUrl()) !== 0 && !$this->urlBuild()) return false;

        return true;
    }

    public function toArray(): array
    {
        $result = $this->nameToArray();

        if (strlen($this->getIcon()) !== 0) $result = array_merge($result, $this->iconToArray());
        if (strlen($this->getUrl()) !== 0) $result = array_merge($result, $this->urlToArray());

        return $result;
    }

    public function toString(): string
    {
        return "Author(" . $this->nameToString() . "," . $this->urlToString() . "," . $this->iconToString() . ")";
    }
}