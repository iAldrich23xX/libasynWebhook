package com.ialdrich23xx.libasynwebhook.api.discord.body.embed.components;

import com.ialdrich23xx.libasynwebhook.api.discord.body.embed.base.Structure;
import com.ialdrich23xx.libasynwebhook.api.discord.body.embed.base.URL;

import java.util.Map;

public class Thumbnail extends Structure implements URL {

    public Thumbnail(String url) {
        this.setUrl(url);
    }

    public static Thumbnail make(String url) {
        return new Thumbnail(url);
    }

    @Override
    public Boolean build() {
        return this.urlBuild();
    }

    @Override
    public Map<String, Object> toArray() {
        return this.urlToArray();
    }

    @Override
    public String toString() {
        return "Thumbnail(" + this.urlToString() +  ")";
    }
}
