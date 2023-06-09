package com.ialdrich23xx.libasynwebhook.api.discord.body.embed.base;

import java.util.Map;

public interface IconURL {
    Structure setIcon(String icon);
    String getIcon();
    Boolean iconBuild();
    Map<String, Object> iconToArray();
    String iconToString();
}
