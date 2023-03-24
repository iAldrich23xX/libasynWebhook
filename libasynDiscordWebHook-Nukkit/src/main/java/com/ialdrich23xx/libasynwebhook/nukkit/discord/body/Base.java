package com.ialdrich23xx.libasynwebhook.nukkit.discord.body;

import com.ialdrich23xx.libasynwebhook.nukkit.Loader;
import com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed.EmbedManager;
import com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed.base.Structure;

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

        return (this.getContent() != null || !this.getContent().isEmpty()) || !this.getEmbeds().isEmpty();
    }

    @Override
    public Map<String, Object> toArray() {
        Map<String, Object> result = new HashMap<>();

        result.put("tts", this.isTextToSpeech());

        if (this.getContent() != null) result.put("content", this.getContent());
        if (this.getUsername() != null) result.put("username", this.getUsername());
        if (this.getAvatar() != null) result.put("avatar_url", this.getAvatar());

        List<Map<String, Object>> embedList = new ArrayList<>();

        this.getEmbeds().forEach(embed -> {
            embedList.add(embed.toArray());
        });

        if (!embedList.isEmpty()) {
            result.put("embeds", embedList);
        }

        return result;
    }

    @Override
    public String toString() {
        return "Base(content=" + this.getContent() + ",username=" + this.getUsername() + ",avatar=" + this.getAvatar() +
        ";embeds=Array(" + this.getEmbeds().size() + ")";
    }

    public String toJson() {
        StringBuilder builder = new StringBuilder();

        Set<Map.Entry<String, Object>> entrySet = this.toArray().entrySet();
        builder.append("{");

        int i = 0;

        for (Map.Entry<String, Object> entry : entrySet) {
            Object value = entry.getValue();
            builder.append(quote(entry.getKey())).append(":");

            if (value instanceof String) {
                builder.append(quote(String.valueOf(value)));
            } else if (value instanceof Integer) {
                builder.append(Integer.valueOf(String.valueOf(value)));
            } else if (value instanceof Boolean) {
                builder.append(value);
            } else if (value.getClass().isArray()) {
                builder.append("[");

                int len = Array.getLength(value);

                for (int j = 0; j < len; j++) {
                    builder.append(Array.get(value, j).toString()).append(j != len - 1 ? "," : "");
                }

                builder.append("]");
            }

            builder.append(++i == entrySet.size() ? "}" : ",");
        }

        return builder.toString();
    }

    private String quote(String string) {
        return "\"" + string + "\"";
    }
}
