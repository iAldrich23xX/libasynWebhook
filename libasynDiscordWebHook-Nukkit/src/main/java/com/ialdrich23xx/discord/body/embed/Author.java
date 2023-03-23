package com.ialdrich23xx.discord.body.embed;

import com.ialdrich23xx.discord.body.embed.base.IconURL;
import com.ialdrich23xx.discord.body.embed.base.Name;
import com.ialdrich23xx.discord.body.embed.base.Structure;
import com.ialdrich23xx.discord.body.embed.base.URL;

import java.util.ArrayList;

public class Author extends Structure implements Name, URL, IconURL {

    @Override
    public Boolean build() {
        return true;
    }

    @Override
    public Object toArray() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "";
    }
}
