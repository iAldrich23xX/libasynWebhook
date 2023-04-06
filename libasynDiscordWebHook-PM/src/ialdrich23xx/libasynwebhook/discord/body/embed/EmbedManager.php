<?php

declare(strict_types=1);

namespace ialdrich23xx\libasynwebhook\discord\body\embed;

use ialdrich23xx\libasynwebhook\discord\body\embed\base\Structure;
use ialdrich23xx\libasynwebhook\discord\body\embed\components\Author;
use ialdrich23xx\libasynwebhook\discord\body\embed\components\Field;
use ialdrich23xx\libasynwebhook\discord\body\embed\components\Footer;
use ialdrich23xx\libasynwebhook\discord\body\embed\components\Image;
use ialdrich23xx\libasynwebhook\discord\body\embed\components\Thumbnail;
use ialdrich23xx\libasynwebhook\discord\body\embed\components\Timestamp;
use ialdrich23xx\libasynwebhook\Loader;
use function is_null;
use function strlen;

class EmbedManager extends Structure
{
    private ?Author $author = null;

    /** @var Field[] $fields */
    private array $fields = [];

    private ?Footer $footer = null;

    private ?Thumbnail $thumbnail = null;

    private ?Image $image = null;

    private ?Timestamp $timestamp = null;

    public function __construct(
        private string $title,
        private string $description,
        private int $color = EmbedColors::Default
    ){}

    public static function make(string $title, string $description, int $color = EmbedColors::Default): self
    {
        return new self($title, $description, $color);
    }

    public function setTitle(string $title): self
    {
        $this->title = $title;

        return $this;
    }

    public function getTitle(): string
    {
        return $this->title;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getDescription(): string
    {
        return $this->description;
    }

    public function setColor(int $color): self
    {
        $this->color = $color;

        return $this;
    }

    public function getColor(): int
    {
        return $this->color;
    }

    public function setAuthor(Author $author): self
    {
        if (!$author->build()) {
            Loader::getInstance()->getPlugin()->getLogger()->error("Author is invalid: " . $author->toString());
        } else $this->author = $author;

        return $this;
    }

    public function removeAuthor(): self
    {
        $this->author = null;

        return $this;
    }

    public function getAuthor(): ?Author
    {
        return $this->author;
    }

    public function addField(Field $field): self
    {
        if (!$field->build()) {
            Loader::getInstance()->getPlugin()->getLogger()->error("Field is invalid: " . $field->toString());
        } else $this->fields[] = $field;

        return $this;
    }

    public function resetFields(): self
    {
        $this->fields = [];

        return $this;
    }

    /**
     * @return Field[]
     */
    public function getFields(): array
    {
        return $this->fields;
    }

    public function setFooter(Footer $footer): self
    {
        if (!$footer->build()) {
            Loader::getInstance()->getPlugin()->getLogger()->error("Footer is invalid: " . $footer->toString());
        } else $this->footer = $footer;

        return $this;
    }

    public function removeFooter(): self
    {
        $this->footer = null;

        return $this;
    }

    public function getFooter(): ?Footer
    {
        return $this->footer;
    }

    public function setThumbnail(Thumbnail $thumbnail): self
    {
        if (!$thumbnail->build()) {
            Loader::getInstance()->getPlugin()->getLogger()->error("Thumbnail is invalid: " . $thumbnail->toString());
        } else $this->thumbnail = $thumbnail;

        return $this;
    }

    public function removeThumbnail(): self
    {
        $this->thumbnail = null;

        return $this;
    }

    public function getThumbnail(): ?Thumbnail
    {
        return $this->thumbnail;
    }

    public function setImage(Image $image): self
    {
        if (!$image->build()) {
            Loader::getInstance()->getPlugin()->getLogger()->error("Image is invalid: " . $image->toString());
        } else $this->image = $image;

        return $this;
    }

    public function removeImage(): self
    {
        $this->image = null;

        return $this;
    }

    public function getImage(): ?Image
    {
        return $this->image;
    }

    public function setTimestamp(Timestamp $timestamp): self
    {
        if (!$timestamp->build()) {
            Loader::getInstance()->getPlugin()->getLogger()->error("Timestamp is invalid: " . $timestamp->toString());
        } else $this->timestamp = $timestamp;

        return $this;
    }

    public function removeTimestamp(): self
    {
        $this->timestamp = null;

        return $this;
    }

    public function getTimestamp(): ?Timestamp
    {
        return $this->timestamp;
    }

    public function build(): bool
    {
        return strlen($this->getTitle()) !== 0 && strlen($this->getDescription()) !== 0 || !empty($this->getFields());
    }

    public function toArray(): array
    {
        $result = [
            "title" => $this->getTitle(),
            "description" => $this->getDescription(),
            "color" => $this->getColor()
        ];

        if (!is_null($this->getAuthor())) $result["author"] = $this->getAuthor()->toArray();
        if (!is_null($this->getFooter())) $result["footer"] = $this->getFooter()->toArray();
        if (!is_null($this->getThumbnail())) $result["thumbnail"] = $this->getThumbnail()->toArray();
        if (!is_null($this->getImage())) $result["image"] = $this->getImage()->toArray();
        if (!is_null($this->getTimestamp())) $result["timestamp"] = $this->getTimestamp()->getData()->format(Timestamp::FORMAT);

        if (!empty($this->getFields())) {
            foreach ($this->getFields() as $field) {
                $result["fields"][] = $field->toArray();
            }
        }

        return $result;
    }

    public function toString(): string
    {
        return "EmbedManager(title=" . $this->getTitle() . ",description=" . $this->getDescription() . ",color=" . $this->getColor() .
            ",author=" . $this->getAuthor()?->toString() . ",footer=" . $this->getFooter()?->toString() . ",thumbnail=" . $this->getThumbnail()?->toString() .
            ",image=" . $this->getImage()?->toString() . ",timestamp=" . $this->getTimestamp()?->toString() . ")";
    }
}