<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body;

use JsonSerializable;

class Base implements JsonSerializable
{
    protected array $data = ["content" => "test"];

    public function jsonSerialize(): array
    {
        return $this->data;
    }
}