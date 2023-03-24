package com.ialdrich23xx.discord.body.embed.components;

import com.ialdrich23xx.discord.body.embed.base.IconURL;
import com.ialdrich23xx.discord.body.embed.base.Name;
import com.ialdrich23xx.discord.body.embed.base.Structure;
import com.ialdrich23xx.discord.body.embed.base.URL;

import java.util.Map;

public class Author extends Structure implements Name, URL, IconURL {

    public Author(String name) {
        this.setName(name);
    }

    public static Author make(String name) {
        return new Author(name);
    }

    @Override
    public Boolean build() {
        if (this.getName().isEmpty()) return false;

        if (!this.getUrl().isEmpty() && !this.urlBuild()) return false;
        if (!this.getIcon().isEmpty() && !this.iconBuild()) return false;

        return true;
    }

    @Override
    public Map<String, Object> toArray() {
        Map<String, Object> result = this.nameToArray();

        if (!this.getUrl().isEmpty()) result.putAll(this.urlToArray());
        if (!this.getIcon().isEmpty()) result.putAll(this.iconToArray());

        return result;
    }

    @Override
    public String toString() {
        return "Author(" + this.nameToString() + "," + this.urlToString() + "," + this.iconToString() + ")";
    }
}
