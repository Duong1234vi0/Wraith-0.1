/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.platform.ViaInjector
 *  com.viaversion.viaversion.api.protocol.version.ProtocolVersion
 *  com.viaversion.viaversion.libs.fastutil.objects.ObjectLinkedOpenHashSet
 *  com.viaversion.viaversion.libs.gson.JsonObject
 */
package mods.viaversion.vialoadingbase.platform.viaversion;

import com.viaversion.viaversion.api.platform.ViaInjector;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.libs.fastutil.objects.ObjectLinkedOpenHashSet;
import com.viaversion.viaversion.libs.gson.JsonObject;
import java.util.SortedSet;
import mods.viaversion.vialoadingbase.ViaLoadingBase;

public class VLBViaInjector
implements ViaInjector {
    public void inject() {
    }

    public void uninject() {
    }

    public String getDecoderName() {
        return "via-decoder";
    }

    public String getEncoderName() {
        return "via-encoder";
    }

    public SortedSet<ProtocolVersion> getServerProtocolVersions() {
        ObjectLinkedOpenHashSet versions = new ObjectLinkedOpenHashSet();
        versions.addAll(ProtocolVersion.getProtocols());
        return versions;
    }

    public ProtocolVersion getServerProtocolVersion() {
        ViaLoadingBase base = ViaLoadingBase.getInstance();
        if (base != null) {
            return ProtocolVersion.getProtocol((int)base.getNativeVersion());
        }
        return ProtocolVersion.getProtocol((int)754);
    }

    public JsonObject getDump() {
        return new JsonObject();
    }
}

