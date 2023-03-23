package com.ialdrich23xx.discord.body.embed.base;

import java.util.HashMap;
import java.util.Map;

public abstract class Structure {

    private String name = "";
    private String url = "";
    private String icon = "";

    abstract public Boolean build();
    abstract public Object toArray();
    abstract public String toString();

    public void setName(String newName) {
        if (!(this instanceof Name)) {
            throw new BodyException("Error this class not implements name");
        } else {
            this.name = newName;
        }
    }

    public String getName() {
        if (!(this instanceof Name)) throw new BodyException("Error this class not implements name");

        return this.name;
    }

    public Map<String, Object> nameToArray() {
        if (!(this instanceof Name)) throw new BodyException("Error this class not implements name");

        Map<String, Object> result = new HashMap<>();

        result.put("name", this.getName());

        return result;
    }

    public String nameToString() {
        if (!(this instanceof Name)) throw new BodyException("Error this class not implements name");

        return "name=" + this.getName();
    }

    public void setUrl(String newUrl) {
        if (!(this instanceof URL)) {
            throw new BodyException("Error this class not implements URL");
        } else {
            this.url = newUrl;
        }
    }

    public String getUrl() {
        if (!(this instanceof URL)) throw new BodyException("Error this class not implements URL");

        return this.url;
    }

    public Map<String, Object> urlToArray() {
        if (!(this instanceof Name)) throw new BodyException("Error this class not implements URL");

        Map<String, Object> result = new HashMap<>();

        result.put("url", this.getUrl());

        return result;
    }

    public String urlToString() {
        if (!(this instanceof Name)) throw new BodyException("Error this class not implements Icon");

        return "url=" + this.getUrl();
    }

    public void setIcon(String newIcon) {
        if (!(this instanceof URL)) {
            throw new BodyException("Error this class not implements Icon");
        } else {
            this.icon = newIcon;
        }
    }

    public String getIcon() {
        if (!(this instanceof URL)) throw new BodyException("Error this class not implements Icon");

        return this.icon;
    }

    public Map<String, Object> iconToArray() {
        if (!(this instanceof Name)) throw new BodyException("Error this class not implements Icon");

        Map<String, Object> result = new HashMap<>();

        result.put("icon_url", this.getIcon());

        return result;
    }

    public String iconToString() {
        if (!(this instanceof Name)) throw new BodyException("Error this class not implements Icon");

        return "icon=" + this.getIcon();
    }
}
