<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body\embed;

use ialdrich23xx\libasynwebhook\discord\body\embed\base\Name;
use ialdrich23xx\libasynwebhook\discord\body\embed\base\Structure;
use function is_null;

class Field extends Structure
{
    use Name;

    public function __construct(
        string $name,
        private string $value,
        private bool $inline = false
    ){
        $this->setName($name);
    }

    public static function make(string $name, string $value, bool $inline = false): self
    {
        return new self($name, $value, $inline);
    }

    public function getValue(): string
    {
        return $this->value;
    }

    public function setInline(bool $inLine): self
    {
        $this->inline = $inLine;

        return $this;
    }

    public function getInline(): bool
    {
        return $this->inline;
    }

    public function build(): bool
    {
        if (is_null($this->getName()) || is_null($this->getValue())) return false;

        return true;
    }

    public function toArray(): array
    {
        $result = $this->nameToArray();

        $result["value"] = $this->getValue() ?? "null";
        $result["inline"] = $this->getInline() ?? false;

        return $result;
    }

    public function toString(): string
    {
        return "Field(" . $this->nameToString() . ",value=" . $this->getValue() ?? "null" . ",inline=" . $this->getInLine() ?? "null" . ")";
    }
}