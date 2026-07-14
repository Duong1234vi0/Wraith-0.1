package com.viaversion.viaversion.api;

import com.viaversion.viaversion.api.connection.ConnectionManager;

public class ViaManager {
    private final ConnectionManager connectionManager = new ConnectionManager();

    public ConnectionManager getConnectionManager() {
        return this.connectionManager;
    }
}
