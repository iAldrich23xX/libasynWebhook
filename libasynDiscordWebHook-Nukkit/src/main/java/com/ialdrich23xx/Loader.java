package com.ialdrich23xx;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginLogger;
import lombok.Getter;

public class Loader {

    @Getter
    private static Loader instance;
    private final PluginBase plugin;

    public Loader(PluginBase plugin) {
        instance = this;

        this.plugin = plugin;
    }

    public PluginLogger getLogger() {
        return this.plugin.getLogger();
    }
}
