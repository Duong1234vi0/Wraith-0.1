/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 *  com.google.common.collect.Maps
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  com.mojang.authlib.minecraft.MinecraftSessionService
 *  com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
 *  com.mojang.util.UUIDTypeAdapter
 */
package lightning.product;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.util.UUIDTypeAdapter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lightning.product.MinecraftClient;

public class H_1883_T {
    private static final YggdrasilAuthenticationService J_1907_R = new YggdrasilAuthenticationService(MinecraftClient.A_4115_X().d_2461_k());
    private static final MinecraftSessionService R_4764_Y = J_1907_R.createMinecraftSessionService();
    public static LoadingCache<String, GameProfile> n_1700_B = CacheBuilder.newBuilder().expireAfterWrite(60L, TimeUnit.MINUTES).build((CacheLoader)new CacheLoader<String, GameProfile>(){

        public GameProfile n_1700_B(String p_load_1_) throws Exception {
            GameProfile gameprofile = R_4764_Y.fillProfileProperties(new GameProfile(UUIDTypeAdapter.fromString((String)p_load_1_), (String)null), false);
            if (gameprofile == null) {
                throw new Exception("Couldn't get profile");
            }
            return gameprofile;
        }

        public /* synthetic */ Object load(Object object) throws Exception {
            return this.n_1700_B((String)object);
        }
    });

    public static String n_1700_B(String p_225193_0_) throws Exception {
        GameProfile gameprofile = (GameProfile)n_1700_B.get((Object)p_225193_0_);
        return gameprofile.getName();
    }

    public static Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> J_1907_R(String p_225191_0_) {
        try {
            GameProfile gameprofile = (GameProfile)n_1700_B.get((Object)p_225191_0_);
            return R_4764_Y.getTextures(gameprofile, false);
        }
        catch (Exception exception) {
            return Maps.newHashMap();
        }
    }

    public static String n_1700_B(long p_225192_0_) {
        if (p_225192_0_ < 0L) {
            return "right now";
        }
        long i = p_225192_0_ / 1000L;
        if (i < 60L) {
            return (String)(i == 1L ? "1 second" : i + " seconds") + " ago";
        }
        if (i < 3600L) {
            long l = i / 60L;
            return (String)(l == 1L ? "1 minute" : l + " minutes") + " ago";
        }
        if (i < 86400L) {
            long k = i / 3600L;
            return (String)(k == 1L ? "1 hour" : k + " hours") + " ago";
        }
        long j = i / 86400L;
        return (String)(j == 1L ? "1 day" : j + " days") + " ago";
    }

    public static String n_1700_B(Date p_238105_0_) {
        return H_1883_T.n_1700_B(System.currentTimeMillis() - p_238105_0_.getTime());
    }
}


