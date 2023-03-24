package com.ialdrich23xx.libasynwebhook.nukkit.example;

import cn.nukkit.plugin.PluginBase;
import com.ialdrich23xx.libasynwebhook.nukkit.Loader;
import com.ialdrich23xx.libasynwebhook.nukkit.discord.WebHook;
import com.ialdrich23xx.libasynwebhook.nukkit.discord.body.Base;
import com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed.EmbedColors;
import com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed.EmbedManager;
import com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed.components.Author;

public class Main extends PluginBase {

    @Override
    public void onLoad() {
        Loader.make(this);
    }

    @Override
    public void onEnable() {
        WebHook.make("your_url", Base.make()
                .setContent("content")
                .setTextToSpeech(false)
                .addEmbed(EmbedManager.make("server", "enable", EmbedColors.Green)
                        .setAuthor((Author) Author.make("ialdrich23xx")
                                .setUrl("https://github.com/iAldrich23xX")
                                .setIcon("https://pbs.twimg.com/profile_images/1517343098237599746/WLUKxFIw_400x400.jpg")
                        )
                )
        ).send();
    }

    @Override
    public void onDisable() {
        WebHook.make("your_url", Base.make()
                .setContent("content")
                .setTextToSpeech(false)
                .addEmbed(EmbedManager.make("server", "disbale", EmbedColors.Red)
                        .setAuthor((Author) Author.make("ialdrich23xx")
                                .setUrl("https://github.com/iAldrich23xX")
                                .setIcon("https://pbs.twimg.com/profile_images/1517343098237599746/WLUKxFIw_400x400.jpg")
                        )
                )
        ).send();
    }
}