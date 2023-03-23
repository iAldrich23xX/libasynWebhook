package com.ialdrich23xx.discord.body.embed.base;

import java.util.Map;

public interface URL {
    void setUrl(String url);
    String getUrl();
    Map<String, Object> urlToArray();
    String urlToString();
}
