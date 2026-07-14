/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  com.viaversion.viaversion.api.protocol.version.ProtocolVersion
 *  com.viaversion.viaversion.protocol.version.BaseVersionProvider
 */
package mods.viaversion.vialoadingbase.provider;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.protocol.version.BaseVersionProvider;
import mods.viaversion.vialoadingbase.ViaLoadingBase;

public class VLBBaseVersionProvider
extends BaseVersionProvider {
    public ProtocolVersion getClosestServerProtocol(UserConnection connection) throws Exception {
        if (connection.isClientSide()) {
            return ViaLoadingBase.getInstance().getTargetVersion();
        }
        return super.getClosestServerProtocol(connection);
    }
}

