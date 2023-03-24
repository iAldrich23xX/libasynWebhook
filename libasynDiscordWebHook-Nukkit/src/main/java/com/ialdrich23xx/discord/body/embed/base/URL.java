package com.ialdrich23xx.discord.body.embed.base;

import java.util.Map;

public interface URL {
    Structure setUrl(String url);
    String getUrl();
    Boolean urlBuild();
    Map<String, Object> urlToArray();
    String urlToString();
}
