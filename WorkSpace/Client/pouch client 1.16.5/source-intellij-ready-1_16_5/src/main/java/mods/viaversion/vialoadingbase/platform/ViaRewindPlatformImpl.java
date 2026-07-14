/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viarewind.api.ViaRewindPlatform
 */
package mods.viaversion.vialoadingbase.platform;

import com.viaversion.viarewind.api.ViaRewindPlatform;
import java.io.File;
import java.util.logging.Logger;
import mods.viaversion.vialoadingbase.ViaLoadingBase;

public class ViaRewindPlatformImpl
implements ViaRewindPlatform {
    private final File directory;

    public ViaRewindPlatformImpl(File directory) {
        this.directory = directory;
        this.init(new File(this.directory, "viarewind.yml"));
    }

    public Logger getLogger() {
        return ViaLoadingBase.LOGGER;
    }

    public File getDataFolder() {
        return this.directory;
    }
}

