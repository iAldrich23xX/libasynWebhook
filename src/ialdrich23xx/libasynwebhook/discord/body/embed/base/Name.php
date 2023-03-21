<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body\embed\base;

trait Name
{
    private string $name = "";

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    public function getName(): string
    {
        return $this->name;
    }

    public function nameToArray(): array
    {
        return ["name" => $this->getName()];
    }

    public function nameToString(): string
    {
        return "name=" . $this->getName();
    }
}