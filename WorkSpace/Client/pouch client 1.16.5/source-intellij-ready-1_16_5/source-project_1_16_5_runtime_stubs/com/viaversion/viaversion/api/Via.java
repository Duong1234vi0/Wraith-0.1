package com.viaversion.viaversion.api;

public final class Via {
    private static final ViaManager MANAGER = new ViaManager();

    private Via() {
    }

    public static ViaManager getManager() {
        return MANAGER;
    }
}
