package com.ialdrich23xx.thread;

import cn.nukkit.scheduler.AsyncTask;
import com.ialdrich23xx.Loader;
import com.ialdrich23xx.discord.WebHook;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;
import java.time.Duration;

public class SendWebHookTask extends AsyncTask {

    private String url;
    private String args;

    public SendWebHookTask(WebHook webhook) {
        this.url = webhook.getUrl();

        this.args = webhook.getBody().toJson();
    }

    @Override
    public void onRun() {
        try {
            URL url = new URL(this.url);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("User-Agent", "CloverCube");
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            OutputStream stream = connection.getOutputStream();
            stream.write(this.args.getBytes());
            stream.flush();
            stream.close();

            connection.getInputStream().close(); //??? test

            connection.disconnect();
        } catch (Exception e) {
            Loader.getInstance().getLogger().error("Error SendWebhookTask: " + e.getMessage());
        }
    }
}
