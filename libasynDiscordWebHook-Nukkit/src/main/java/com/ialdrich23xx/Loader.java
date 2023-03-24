package com.ialdrich23xx;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginLogger;
import com.ialdrich23xx.thread.Pool;
import lombok.Getter;
import org.apache.commons.validator.UrlValidator;

public class Loader {

    @Getter
    private static Loader instance;
    private final PluginBase plugin;
    private final Pool threadPool;

    public Loader(PluginBase plugin) {
        instance = this;

        this.plugin = plugin;

        this.threadPool = new Pool(Server.getInstance(), Pool.POOL_SIZE);
    }

    public PluginLogger getLogger() {
        return this.plugin.getLogger();
    }

    public Pool getThreadPool() {
        return this.threadPool;
    }

    public Boolean isValidUrl(String url)
    {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}
