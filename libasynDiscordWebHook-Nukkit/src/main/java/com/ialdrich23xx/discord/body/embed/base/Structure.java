package com.ialdrich23xx.discord.body.embed.base;

import com.ialdrich23xx.Loader;

import java.util.HashMap;
import java.util.Map;

public abstract class Structure {

    private String name = "";
    private String url = "";
    private String icon = "";

    abstract public Boolean build();
    abstract public Map<String, Object> toArray();
    abstract public String toString();

    public Structure setName(String newName) {
        if (!(this instanceof Name)) {
            throw new BodyException("Error this class not implements name");
        } else {
            this.name = newName;
        }

        return this;
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

    public Structure setUrl(String newUrl) {
        if (!(this instanceof URL)) {
            throw new BodyException("Error this class not implements URL");
        } else {
            this.url = newUrl;
        }

        return this;
    }

    public String getUrl() {
        if (!(this instanceof URL)) throw new BodyException("Error this class not implements URL");

        return this.url;
    }

    public Boolean urlBuild() {
        if (this.getUrl().isEmpty()) return false;

        return Loader.getInstance().isValidUrl(this.getUrl());
    }

    public Map<String, Object> urlToArray() {
        if (!(this instanceof URL)) throw new BodyException("Error this class not implements URL");

        Map<String, Object> result = new HashMap<>();

        result.put("url", this.getUrl());

        return result;
    }

    public String urlToString() {
        if (!(this instanceof URL)) throw new BodyException("Error this class not implements URL");

        return "url=" + this.getUrl();
    }

    public Structure setIcon(String newIcon) {
        if (!(this instanceof IconURL)) {
            throw new BodyException("Error this class not implements IconURL");
        } else {
            this.icon = newIcon;
        }

        return this;
    }

    public Boolean iconBuild() {
        if (this.getIcon().isEmpty()) return false;

        return Loader.getInstance().isValidUrl(this.getIcon());
    }

    public String getIcon() {
        if (!(this instanceof IconURL)) throw new BodyException("Error this class not implements IconURL");

        return this.icon;
    }

    public Map<String, Object> iconToArray() {
        if (!(this instanceof IconURL)) throw new BodyException("Error this class not implements IconURL");

        Map<String, Object> result = new HashMap<>();

        result.put("icon_url", this.getIcon());

        return result;
    }

    public String iconToString() {
        if (!(this instanceof IconURL)) throw new BodyException("Error this class not implements IconURL");

        return "icon=" + this.getIcon();
    }
}
