package com.ialdrich23xx.libasynwebhook.api.discord.body;

import com.ialdrich23xx.libasynwebhook.api.Loader;
import com.ialdrich23xx.libasynwebhook.api.discord.body.embed.EmbedManager;
import com.ialdrich23xx.libasynwebhook.api.discord.body.embed.base.Structure;

import java.lang.reflect.Array;
import java.util.*;

public class Base extends Structure {

    private String content = null;
    private String username = null;
    private String avatar = null;
    private Boolean textToSpeech = false;

    private List<EmbedManager> embeds = new ArrayList<>();

    public Base() {}

    public static Base make() {
        return new Base();
    }

    public Base setContent(String content) {
        this.content = content;

        return this;
    }

    public String getContent() {
        return this.content;
    }

    public Base setUsername(String username) {
        this.username = username;

        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public Base setAvatar(String avatar) {
        this.avatar = avatar;

        return this;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public Base setTextToSpeech(Boolean textToSpeech) {
        this.textToSpeech = textToSpeech;

        return this;
    }

    public Boolean isTextToSpeech() {
        return this.textToSpeech;
    }

    public Base addEmbed(EmbedManager embed) {
        this.embeds.add(embed);

        return this;
    }

    public Base resetEmbeds() {
        this.embeds = new ArrayList<>();

        return this;
    }

    public List<EmbedManager> getEmbeds() {
        return this.embeds;
    }

    @Override
    public Boolean build() {
        if (this.getAvatar() != null && !Loader.getInstance().isValidUrl(this.getAvatar())) return false;
        if (this.getContent() == null && this.getEmbeds().isEmpty()) return false;
        if (this.getContent() != null && this.getContent().length() == 0) return false;

        return true;
    }

    @Override
    public Map<String, Object> toArray() {
        Map<String, Object> result = new HashMap<>();

        result.put("tts", this.isTextToSpeech());

        if (this.getContent() != null) result.put("content", this.getContent());
        if (this.getUsername() != null) result.put("username", this.getUsername());
        if (this.getAvatar() != null) result.put("avatar_url", this.getAvatar());

        List<Object> embedList = new ArrayList<>();

        this.getEmbeds().forEach(embed -> embedList.add(Loader.getInstance().formatToJson(embed.toArray().entrySet())));

        if (!embedList.isEmpty()) {
            result.put("embeds", embedList.toArray());
        }

        return result;
    }

    @Override
    public String toString() {
        return "Base(content=" + this.getContent() + ",username=" + this.getUsername() + ",avatar=" + this.getAvatar() +
        ";embeds=Array(" + this.getEmbeds().size() + ")";
    }

    public String toJson() {
        return Loader.getInstance().formatToJson(this.toArray().entrySet());
    }

    private String quote(String string) {
        return "\"" + string + "\"";
    }
}
