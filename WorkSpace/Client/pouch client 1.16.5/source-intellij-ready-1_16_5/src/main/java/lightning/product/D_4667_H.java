/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Multimap
 *  com.google.common.hash.Hashing
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.InsecureTextureException
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  com.mojang.authlib.minecraft.MinecraftSessionService
 *  com.mojang.authlib.properties.Property
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.hash.Hashing;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.InsecureTextureException;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import lightning.product.C_3240_x;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.c_4477_a;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.r_1020_F;
import lightning.product.s_2614_w;

public class D_4667_H {
    private final C_3240_x n_1700_B;
    private final File J_1907_R;
    private final MinecraftSessionService R_4764_Y;
    private final LoadingCache<String, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture>> G_564_y;

    public D_4667_H(C_3240_x textureManagerInstance, File skinCacheDirectory, final MinecraftSessionService sessionService) {
        this.n_1700_B = textureManagerInstance;
        this.J_1907_R = skinCacheDirectory;
        this.R_4764_Y = sessionService;
        this.G_564_y = CacheBuilder.newBuilder().expireAfterAccess(15L, TimeUnit.SECONDS).build((CacheLoader)new CacheLoader<String, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture>>(this){

            public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> n_1700_B(String p_load_1_) {
                GameProfile gameprofile = new GameProfile((UUID)null, "dummy_mcdummyface");
                gameprofile.getProperties().put((Object)"textures", (Object)new Property("textures", p_load_1_, ""));
                try {
                    return sessionService.getTextures(gameprofile, false);
                }
                catch (Throwable throwable) {
                    return ImmutableMap.of();
                }
            }

            public /* synthetic */ Object load(Object object) throws Exception {
                return this.n_1700_B((String)object);
            }
        });
    }

    public g_2336_b n_1700_B(MinecraftProfileTexture profileTexture, MinecraftProfileTexture.Type textureType) {
        return this.n_1700_B(profileTexture, textureType, (n_1700_B)null);
    }

    private g_2336_b n_1700_B(MinecraftProfileTexture profileTexture, MinecraftProfileTexture.Type textureType, @Nullable n_1700_B skinAvailableCallback) {
        String s = Hashing.sha1().hashUnencodedChars((CharSequence)profileTexture.getHash()).toString();
        g_2336_b resourcelocation = new g_2336_b("skins/" + s);
        c_4477_a texture = this.n_1700_B.J_1907_R(resourcelocation);
        if (texture != null) {
            if (skinAvailableCallback != null) {
                skinAvailableCallback.onSkinTextureAvailable(textureType, resourcelocation, profileTexture);
            }
        } else {
            File file1 = new File(this.J_1907_R, s.length() > 2 ? s.substring(0, 2) : "xx");
            File file2 = new File(file1, s);
            r_1020_F downloadingtexture = new r_1020_F(file2, profileTexture.getUrl(), s_2614_w.n_1700_B(), textureType == MinecraftProfileTexture.Type.SKIN, () -> {
                if (skinAvailableCallback != null) {
                    skinAvailableCallback.onSkinTextureAvailable(textureType, resourcelocation, profileTexture);
                }
            });
            this.n_1700_B.n_1700_B(resourcelocation, downloadingtexture);
        }
        return resourcelocation;
    }

    public void n_1700_B(GameProfile profile, n_1700_B skinAvailableCallback, boolean requireSecure) {
        Runnable runnable = () -> {
            HashMap map = Maps.newHashMap();
            try {
                map.putAll(this.R_4764_Y.getTextures(profile, requireSecure));
            }
            catch (InsecureTextureException insecureTextureException) {
                // empty catch block
            }
            if (map.isEmpty()) {
                profile.getProperties().clear();
                if (profile.getId().equals(MinecraftClient.A_4115_X().z_1737_N().P_1922_E().getId())) {
                    profile.getProperties().putAll((Multimap)MinecraftClient.A_4115_X().v_4276_D());
                    map.putAll(this.R_4764_Y.getTextures(profile, false));
                } else {
                    this.R_4764_Y.fillProfileProperties(profile, requireSecure);
                    try {
                        map.putAll(this.R_4764_Y.getTextures(profile, requireSecure));
                    }
                    catch (InsecureTextureException insecureTextureException) {
                        // empty catch block
                    }
                }
            }
            MinecraftClient.A_4115_X().execute(() -> c_4037_x.n_1700_B(() -> ImmutableList.of((Object)MinecraftProfileTexture.Type.SKIN, (Object)MinecraftProfileTexture.Type.CAPE).forEach(p_229296_3_ -> {
                if (map.containsKey(p_229296_3_)) {
                    this.n_1700_B((MinecraftProfileTexture)map.get(p_229296_3_), (MinecraftProfileTexture.Type)p_229296_3_, skinAvailableCallback);
                }
            })));
        };
        j_3341_s.u_1723_Y().execute(runnable);
    }

    public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> n_1700_B(GameProfile profile) {
        Property property = (Property)Iterables.getFirst((Iterable)profile.getProperties().get((Object)"textures"), (Object)null);
        return property == null ? ImmutableMap.of() : (Map)this.G_564_y.getUnchecked((Object)property.getValue());
    }

    public static interface n_1700_B {
        public void onSkinTextureAvailable(MinecraftProfileTexture.Type var1, g_2336_b var2, MinecraftProfileTexture var3);
    }
}


