<?php

declare(strict_types=1);

namespace ialdrich23xx\example;

use JsonException;
use common\ServerComposer;
use ialdrich23xx\libasynwebhook\discord\body\Base;
use ialdrich23xx\libasynwebhook\discord\body\embed\Author;
use ialdrich23xx\libasynwebhook\discord\body\embed\EmbedColors;
use ialdrich23xx\libasynwebhook\discord\body\embed\EmbedManager;
use ialdrich23xx\libasynwebhook\discord\body\embed\Footer;
use ialdrich23xx\libasynwebhook\discord\WebHook;
use ialdrich23xx\libasynwebhook\Loader;

class Main extends \pocketmine\plugin\PluginBase
{
    public function onLoad(): void
    {
        ServerComposer::make($this)->autoload();

        Loader::make($this);
    }

    /**
     * @throws JsonException
     */
    public function onEnable(): void
    {
        WebHook::make("your_url", Base::make()
            ->addEmbed(EmbedManager::make("Server", "enable", EmbedColors::Green)
                ->setFooter(Footer::make("is Footer")
                    ->setIcon("url_icon"))
                ->setAuthor(Author::make("ialdrich23xx")
                    ->setUrl("https://github.com/iAldrich23xX")
                    ->setIcon("https://pbs.twimg.com/profile_images/1517343098237599746/WLUKxFIw_400x400.jpg"))
            )
        )->send();
    }

    /**
     * @throws JsonException
     */
    public function onDisable(): void
    {
        WebHook::make("your_url", Base::make()
            ->addEmbed(EmbedManager::make("Server", "disable", EmbedColors::Red)
                ->setFooter(Footer::make("is Footer")
                    ->setIcon("url_icon"))
                ->setAuthor(Author::make("ialdrich23xx")
                    ->setUrl("https://github.com/iAldrich23xX")
                    ->setIcon("https://pbs.twimg.com/profile_images/1517343098237599746/WLUKxFIw_400x400.jpg"))
            )
        )->send();
    }
}