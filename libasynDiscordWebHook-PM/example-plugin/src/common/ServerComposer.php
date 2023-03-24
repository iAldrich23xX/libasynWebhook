<?php

declare(strict_types=1);

namespace common;

define('VENDOR_ROOT_PATH', str_replace('src', '', dirname(__DIR__)).'vendor'.DIRECTORY_SEPARATOR);

use pocketmine\plugin\PluginBase;
use pocketmine\Server;
use pocketmine\utils\TextFormat;
use function dirname;
use function is_file;
use function str_replace;
use const DIRECTORY_SEPARATOR;

class ServerComposer
{
    private PluginBase $root;

    public function __construct(PluginBase $root)
    {
        $this->root = $root;
    }

    public static function make(PluginBase $root): self
    {
        return new self($root);
    }

    public function autoload(): void
    {
        $server = $this->root->getServer();
        $logger = $this->root->getLogger();
        $vendor = VENDOR_ROOT_PATH.'autoload.php';

        if (! is_file($vendor)) {
            $logger->error(TextFormat::colorize('&cCould not find vendor autoload file to load libraries!'));
            $server->shutdown();

            return;
        }

        require_once $vendor;

        $this->registerExternalComposerLibToPMMP('Paragi\\PhpWebsocket\\', 'paragi', 'php-websocket-client');
        $this->registerExternalComposerLibToPMMP('ialdrich23xx\\libasynwebhook\\', 'ialdrich23xx', 'libasynwebhook', "libasynDiscordWebHook-PM");

        $logger->info(TextFormat::colorize('&aComposer autoloader loaded!'));
    }

    private function registerExternalComposerLibToPMMP(string $namespaces, string $author, string $libName, string $srcDir = 'src'): void
    {
        $loader = Server::getInstance()->getLoader();
        $loader->addPath(
            $namespaces,
            VENDOR_ROOT_PATH.$author.DIRECTORY_SEPARATOR.$libName.DIRECTORY_SEPARATOR.$srcDir.DIRECTORY_SEPARATOR
        );
    }
}