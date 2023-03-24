package com.ialdrich23xx.discord;

import com.ialdrich23xx.Loader;
import com.ialdrich23xx.discord.body.Base;
import com.ialdrich23xx.thread.SendWebHookTask;

public class WebHook {

    private String url;
    private Base body;

    public WebHook(String url, Base body) {
        this.url = url;
        this.body = body;
    }

    public static WebHook make(String url, Base body) {
        return new WebHook(url, body);
    }

    public String getUrl() {
        return this.url;
    }

    public Base getBody() {
        return this.body;
    }

    public WebHook setBody(Base body) {
        this.body = body;

        return this;
    }

    public void send() {
        if (this.getBody() == null) {
            Loader.getInstance().getLogger().error("Body of webhook is null");
            return;
        }

        if (Loader.getInstance().isValidUrl(this.getUrl())) {
            Loader.getInstance().getThreadPool().submit(new SendWebHookTask(this));
        } else Loader.getInstance().getLogger().error("Url not valid: " + this.getUrl());
    }
}
