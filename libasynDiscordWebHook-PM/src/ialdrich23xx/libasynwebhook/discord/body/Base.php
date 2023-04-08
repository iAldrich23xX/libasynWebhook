<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body;

use JsonSerializable;
use ialdrich23xx\libasynwebhook\discord\body\embed\base\Structure;
use ialdrich23xx\libasynwebhook\discord\body\embed\EmbedManager;
use ialdrich23xx\libasynwebhook\Loader;
use function count;
use function is_null;
use function strlen;

class Base extends Structure implements JsonSerializable
{
    private ?string $content = null;
    private ?string $username = null;
    private ?string $avatar = null;
    private bool $textToSpeech = false;
    private ?string $threadName = null;

    /** @var EmbedManager[] */
    private array $embeds = [];

    public static function make(): self
    {
        return new self();
    }

    public function setContent(string $content): self
    {
        $this->content = $content;

        return $this;
    }

    public function getContent(): ?string
    {
        return $this->content;
    }

    public function setUsername(string $username): self
    {
        $this->username = $username;

        return $this;
    }

    public function getUsername(): ?string
    {
        return $this->username;
    }

    public function setAvatar(string $avatar): self
    {
        $this->avatar = $avatar;

        return $this;
    }

    public function getAvatar(): ?string
    {
        return $this->avatar;
    }

    public function setTextToSpeech(bool $textToSpeech): self
    {
        $this->textToSpeech = $textToSpeech;

        return $this;
    }

    public function isTextToSpeech(): bool
    {
        return $this->textToSpeech;
    }

    public function addEmbed(EmbedManager $embed): self
    {
        $this->embeds[] = $embed;

        return $this;
    }

    public function resetEmbeds(): self
    {
        $this->embeds = [];

        return $this;
    }

    /**
     * @return EmbedManager[]
     */
    public function getEmbeds(): array
    {
        return $this->embeds;
    }

    public function setForumTitle(string $title): self
    {
        $this->threadName = $title;

        return $this;
    }

    public function getForumTitle(): ?string
    {
        return $this->threadName;
    }

    public function isForum(): bool
    {
        return !is_null($this->threadName);
    }

    public function build(): bool
    {
        if (!is_null($this->getAvatar()) && !Loader::getInstance()->isValidUrl($this->getAvatar())) return false;
        if (is_null($this->getContent()) && empty($this->getEmbeds())) return false;
        if (!is_null($this->getContent()) && strlen($this->getContent()) === 0) return false;

        return true;
    }

    public function jsonSerialize(): array
    {
        return $this->toArray();
    }

    public function toArray(): array
    {
        $result = ["tts" => $this->isTextToSpeech()];

        if (!is_null($this->getContent())) $result["content"] = $this->getContent();
        if (!is_null($this->getUsername())) $result["username"] = $this->getUsername();
        if (!is_null($this->getAvatar())) $result["avatar_url"] = $this->getAvatar();

        if (!empty($this->getEmbeds())) {
            foreach ($this->getEmbeds() as $embed) {
                $result["embeds"][] = $embed->toArray();
            }
        }

        if ($this->isForum()) $result["thread_name"] = $this->getForumTitle();

        return $result;
    }

    public function toString(): string
    {
        return "Base(content=" . $this->getContent() . ",username=" . $this->getUsername() . ",avatar=" . $this->getAvatar() .
            ";embeds=Array(" . count($this->getEmbeds()) . ")";
    }
}