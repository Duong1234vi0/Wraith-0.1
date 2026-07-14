/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.util.stream.Stream;
import lightning.product.B_4088_l;

public final class PlayerMap {
    private final Object2BooleanMap<B_4088_l> n_1700_B = new Object2BooleanOpenHashMap();

    public Stream<B_4088_l> n_1700_B(long chunkPosIn) {
        return this.n_1700_B.keySet().stream();
    }

    public void n_1700_B(long chunkPosIn, B_4088_l player, boolean canGenerateChunks) {
        this.n_1700_B.put((Object)player, canGenerateChunks);
    }

    public void n_1700_B(long chunkPosIn, B_4088_l player) {
        this.n_1700_B.removeBoolean((Object)player);
    }

    public void n_1700_B(B_4088_l player) {
        this.n_1700_B.replace((Object)player, true);
    }

    public void J_1907_R(B_4088_l player) {
        this.n_1700_B.replace((Object)player, false);
    }

    public boolean R_4764_Y(B_4088_l player) {
        return this.n_1700_B.getOrDefault((Object)player, true);
    }

    public boolean G_564_y(B_4088_l player) {
        return this.n_1700_B.getBoolean((Object)player);
    }

    public void n_1700_B(long oldChunkPos, long newChunkPos, B_4088_l player) {
    }
}


