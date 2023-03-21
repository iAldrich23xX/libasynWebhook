<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body\embed\base;

abstract class Structure
{
    abstract public function build(): bool;

    abstract public function toArray(): array;

    abstract public function toString(): string;
}