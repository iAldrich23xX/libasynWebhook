package com.ialdrich23xx.libasynwebhook.api.discord.body.embed.components;

import com.ialdrich23xx.libasynwebhook.api.discord.body.embed.base.Structure;
import com.ialdrich23xx.libasynwebhook.api.discord.body.embed.base.URL;

import java.util.Map;

public class Image extends Structure implements URL {

    public Image(String url) {
        this.setUrl(url);
    }

    public static Image make(String url) {
        return new Image(url);
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
        return "Image(" + this.urlToString() +  ")";
    }
}
