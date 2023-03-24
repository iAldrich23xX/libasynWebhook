<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body\embed\components;

use DateTime;
use DateTimeZone;
use ialdrich23xx\libasynwebhook\discord\body\embed\base\Structure;
use ialdrich23xx\libasynwebhook\Loader;
use function date_default_timezone_get;
use function in_array;
use function timezone_identifiers_list;

class Timestamp extends Structure
{
    const FORMAT = "Y-m-d\TH:i:s.v\Z";

    private string $timezone;

    public function __construct(
        private DateTime $data
    ) {
        $this->timezone = date_default_timezone_get();
    }

    public static function make(DateTime $data): self
    {
        return new self($data);
    }

    public function getData(): DateTime
    {
        return $this->data;
    }

    public function setTimeZone(string $timeZone): self
    {
        $this->timezone = $timeZone;

        if (!$this->isValidTimeZone()) {
            Loader::getInstance()->getPlugin()->getLogger()->error($timeZone . "not is timezone");

            $this->timezone = date_default_timezone_get();
        }

        return $this;
    }

    public function isValidTimeZone(): bool
    {
        return in_array($this->timezone, timezone_identifiers_list());
    }

    public function build(): bool
    {
        if (!$this->isValidTimeZone()) return false;

        $this->data->setTimezone(new DateTimeZone($this->timezone));

        return true;
    }

    public function toString(): string
    {
        return "Timestamp(data=" . $this->getData()->format(Timestamp::FORMAT) . ",timezone=" . $this->timezone . ")";
    }

    /**
     * @no-require
     */
    public function toArray(): array
    {
        return [];
    }
}