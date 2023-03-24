package com.ialdrich23xx.libasynwebhook.nukkit.thread;

import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncPool;

public class Pool extends AsyncPool {

    public static final int POOL_SIZE = 271;

    public Pool(Server server, int size) {
        super(server, size);
    }
}
