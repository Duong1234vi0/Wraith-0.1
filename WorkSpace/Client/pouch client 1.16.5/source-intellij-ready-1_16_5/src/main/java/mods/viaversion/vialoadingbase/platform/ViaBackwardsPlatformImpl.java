/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viabackwards.api.ViaBackwardsPlatform
 */
package mods.viaversion.vialoadingbase.platform;

import com.viaversion.viabackwards.api.ViaBackwardsPlatform;
import java.io.File;
import java.util.logging.Logger;
import mods.viaversion.vialoadingbase.ViaLoadingBase;

public class ViaBackwardsPlatformImpl
implements ViaBackwardsPlatform {
    private final File directory;

    public ViaBackwardsPlatformImpl(File directory) {
        this.directory = directory;
        this.init(new File(this.directory, "viabackwards.yml"));
    }

    public Logger getLogger() {
        return ViaLoadingBase.LOGGER;
    }

    public boolean isOutdated() {
        return false;
    }

    public void disable() {
    }

    public File getDataFolder() {
        return this.directory;
    }
}

