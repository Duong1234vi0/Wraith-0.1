/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.common;

import java.util.concurrent.ThreadFactory;
import mods.voicechat.debug.VoicechatUncaughtExceptionHandler;

public class NamedThreadPoolFactory
implements ThreadFactory {
    private final String name;

    public NamedThreadPoolFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, this.name);
        thread.setUncaughtExceptionHandler(new VoicechatUncaughtExceptionHandler());
        thread.setDaemon(true);
        return thread;
    }

    public static NamedThreadPoolFactory create(String name) {
        return new NamedThreadPoolFactory(name);
    }
}

