/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.Via
 *  com.viaversion.viaversion.api.platform.ViaPlatformLoader
 *  com.viaversion.viaversion.api.platform.providers.Provider
 *  com.viaversion.viaversion.api.platform.providers.ViaProviders
 *  com.viaversion.viaversion.api.protocol.version.VersionProvider
 */
package mods.viaversion.vialoadingbase.platform.viaversion;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.platform.ViaPlatformLoader;
import com.viaversion.viaversion.api.platform.providers.Provider;
import com.viaversion.viaversion.api.platform.providers.ViaProviders;
import com.viaversion.viaversion.api.protocol.version.VersionProvider;
import mods.viaversion.vialoadingbase.ViaLoadingBase;
import mods.viaversion.vialoadingbase.provider.VLBBaseVersionProvider;

public class VLBViaProviders
implements ViaPlatformLoader {
    public void load() {
        ViaProviders providers = Via.getManager().getProviders();
        providers.use(VersionProvider.class, (Provider)new VLBBaseVersionProvider());
        if (ViaLoadingBase.getInstance().getProviders() != null) {
            ViaLoadingBase.getInstance().getProviders().accept(providers);
        }
    }

    public void unload() {
    }
}

