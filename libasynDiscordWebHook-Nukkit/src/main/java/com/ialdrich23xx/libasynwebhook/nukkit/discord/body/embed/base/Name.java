package com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed.base;

import java.util.Map;

public interface Name {
    String getName();
    Structure setName(String newName);
    Map<String, Object> nameToArray();
    String nameToString();
}
