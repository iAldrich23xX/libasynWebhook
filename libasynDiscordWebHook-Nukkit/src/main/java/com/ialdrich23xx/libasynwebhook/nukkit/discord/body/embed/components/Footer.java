package com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed.components;

import com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed.base.IconURL;
import com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed.base.Structure;

import java.util.HashMap;
import java.util.Map;

public class Footer extends Structure implements IconURL {

    private String text;

    public Footer(String text) {
        this.text = text;
    }

    public static Footer make(String text) {
        return new Footer(text);
    }

    public Footer setText(String text)
    {
        this.text = text;

        return this;
    }

    public String getText()
    {
        return this.text;
    }

    @Override
    public Boolean build() {
        if (this.getText().isEmpty()) return false;
        if (!this.getIcon().isEmpty() && !this.iconBuild()) return false;

        return true;
    }

    @Override
    public Map<String, Object> toArray() {
        Map<String, Object> result = new HashMap<>();

        result.put("text", this.getText());

        if (!this.getIcon().isEmpty()) result.putAll(this.iconToArray());

        return result;
    }

    @Override
    public String toString() {
        return "Footer(text=" + this.getText() + "," + this.iconToString() + ")";
    }
}
