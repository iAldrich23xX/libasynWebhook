package com.ialdrich23xx.discord.body.embed.base;

import java.util.Map;

public interface IconURL {
    void setIcon(String icon);
    String getIcon();
    Map<String, Object> iconToArray();
    String iconToString();
}
