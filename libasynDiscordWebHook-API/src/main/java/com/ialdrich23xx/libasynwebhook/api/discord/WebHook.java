package com.ialdrich23xx.libasynwebhook.api.discord;

import com.ialdrich23xx.libasynwebhook.api.Loader;
import com.ialdrich23xx.libasynwebhook.api.discord.body.Base;
import com.ialdrich23xx.libasynwebhook.api.discord.body.embed.base.BodyException;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class WebHook {

    private final String url;
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
        if (this.getBody() == null) throw new BodyException("Body of webhook is null");

        if (Loader.getInstance().isValidUrl(this.getUrl()) && this.getBody().build()) {
            CompletableFuture.runAsync(() -> {
                try {
                    URL url = new URL(this.url);
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

                    connection.addRequestProperty("Content-Type", "application/json");
                    connection.addRequestProperty("User-Agent", "CloverCube");
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");

                    OutputStream stream = connection.getOutputStream();
                    stream.write(this.getBody().toJson().getBytes());
                    stream.flush();
                    stream.close();

                    connection.getInputStream().close(); //I'm not sure why, but it doesn't work without getting the InputStream

                    connection.disconnect();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        } else throw new BodyException("Url not valid: " + this.getUrl());
    }
}
