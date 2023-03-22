---
<img align="left" width="140px" height="140px" src="icon.png" alt="icon">
<img align="left" width="0" height="140px" hspace="10"/>

# libasynWebHook
[![PHPStan](https://github.com/ialdrich23xx/libasynWeebhook-PM/actions/workflows/phpstan.yml/badge.svg)](https://github.com/ialdrich23xx/libasynWeebhook-PM/actions/workflows/phpstan.yml)
[![License](https://img.shields.io/github/license/iAldrich23xX/libasynWeebhook-PM)](https://github.com/iAldrich23xX/libasynWeebhook-PM/blob/pmmp4/LICENSE)

<a>PMMP4</a> <a>PMMP5</a>

---

## Usage

---

<p>Add Add this line in the onLoad() function in main class, <a href="example-plugin">Plugin Example</a>.</p>

```php
<?php

use src\ialdrich23xx\libasynwebhook\Loader;

class Main extends \pocketmine\plugin\PluginBase
{
    public function onLoad(): void
    {
        Loader::make($this);
    }
}
```

### Build simple message:

---

#### Import the classes

```php


```

#### Suggested method

```php
WebHook::make("your_url", Base::make()
            ->setAvatar("url_avatar")
            ->setContent("text content")
            ->setUsername("username")
            ->setTextToSpeech(false) //enable tts
        )->send(); //function for send message to channel
```

#### OR

```php
$body = Base::make()
    ->setAvatar("url_avatar")
    ->setContent("text content")
    ->setUsername("username")
    ->setTextToSpeech(false);

$webHook = WebHook::make("your_url");
 
$webHook->setBody($body);
 
$webHook->send(); //function for send message to channel
```

### Build embed Message:

---

```php
//Import the classes
use src\ialdrich23xx\libasynwebhook\discord\body\Base;use src\ialdrich23xx\libasynwebhook\discord\body\embed\Author;use src\ialdrich23xx\libasynwebhook\discord\body\embed\EmbedColors;use src\ialdrich23xx\libasynwebhook\discord\body\embed\EmbedManager;use src\ialdrich23xx\libasynwebhook\discord\body\embed\Field;use src\ialdrich23xx\libasynwebhook\discord\body\embed\Footer;use src\ialdrich23xx\libasynwebhook\discord\body\embed\Thumbnail;use src\ialdrich23xx\libasynwebhook\discord\body\embed\Timestamp;use src\ialdrich23xx\libasynwebhook\discord\WebHook;

WebHook::make("your_url", Base::make()
    ->addEmbed(EmbedManager::make("title", "description", EmbedColors::Green)
        ->setFooter(Footer::make("text footer")
            ->setIcon("icon")) //optional
        ->setAuthor(Author::make("name")
            ->setIcon("icon") //optional
            ->setUrl("url")) //optional
        ->setTimestamp(Timestamp::make(new DateTime("now"))
            ->setTimeZone("UTC")) //Optional
        ->addField(Field::make("name", "value")
            ->setInline(false)) //optional
        ->setThumbnail(Thumbnail::make("url"))
    )
)->send();
```

---
## Installation

---

<p>Integrate the virion itself into your plugin, or you could also use it as a composer library by running the command below:</p>

    composer require ialdrich23xx/libbedrock dev-pmmp4

### COMPOSER

add in your "composer.json" and use `composer install`

```
"repositories": [
    {
      "type": "vcs",
      "url": "git@github.com:iAldrich23xX/libasynWeebhook.git"
    }
],
"require": {
    "php": "^8.0",
    "ialdrich23xx/libasynwebhook": "dev-pmmp4"
  },
```

### VIRION

<p>This library supports being included as a <a href="https://github.com/poggit/support/blob/master/virion.md">virion</a>.</p>

<p>If you use Poggit to build your plugin, you can add it to your .poggit.yml like so:</p>

```yml
--- # Poggit-CI Manifest. Open the CI at https://poggit.pmmp.io/ci/YourGithubUserName/YourPluginName
build-by-default: true
branches:
- pmmp4
projects:
  YourPluginName:
    path: ""
    libs:
      - src: ialdrich23xx/libasynwebhook/Loader
        version: 1.0.0
...
```

## Credits

* use post in repository <a href="https://github.com/NetherGamesMC/libasynCurl">libasynCurl</a>
* use color discord <a href="https://gist.github.com/thomasbnt/b6f455e2c7d743b796917fa3c205f812">code_colors_discordjs.md</a>

