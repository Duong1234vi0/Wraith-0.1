/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;

public class UploadTokenCache {
    private static final Long2ObjectMap<String> n_1700_B = new Long2ObjectOpenHashMap();

    public static String n_1700_B(long p_225235_0_) {
        return (String)n_1700_B.get(p_225235_0_);
    }

    public static void J_1907_R(long p_225233_0_) {
        n_1700_B.remove(p_225233_0_);
    }

    public static void n_1700_B(long p_225234_0_, String p_225234_2_) {
        n_1700_B.put(p_225234_0_, (Object)p_225234_2_);
    }
}


