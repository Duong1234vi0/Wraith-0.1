/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.config;

import lightning.product.K_4074_S;
import net.optifine.Config;
import net.optifine.config.Matches;

public class MatchBlock {
    private int blockId = -1;
    private int[] metadatas = null;

    public MatchBlock(int blockId) {
        this.blockId = blockId;
    }

    public MatchBlock(int blockId, int metadata) {
        this.blockId = blockId;
        if (metadata >= 0) {
            this.metadatas = new int[]{metadata};
        }
    }

    public MatchBlock(int blockId, int[] metadatas) {
        this.blockId = blockId;
        this.metadatas = metadatas;
    }

    public int getBlockId() {
        return this.blockId;
    }

    public int[] getMetadatas() {
        return this.metadatas;
    }

    public boolean matches(K_4074_S blockState) {
        if (blockState.multiplayerClientSuggestionProvider() != this.blockId) {
            return false;
        }
        return Matches.metadata(blockState.w_1457_N(), this.metadatas);
    }

    public boolean matches(int id, int metadata) {
        if (id != this.blockId) {
            return false;
        }
        return Matches.metadata(metadata, this.metadatas);
    }

    public void addMetadata(int metadata) {
        if (this.metadatas != null && metadata >= 0) {
            for (int i = 0; i < this.metadatas.length; ++i) {
                if (this.metadatas[i] != metadata) continue;
                return;
            }
            this.metadatas = Config.addIntToArray(this.metadatas, metadata);
        }
    }

    public void addMetadatas(int[] mds) {
        for (int i = 0; i < mds.length; ++i) {
            int j = mds[i];
            this.addMetadata(j);
        }
    }

    public String toString() {
        return this.blockId + ":" + Config.arrayToString(this.metadatas);
    }
}


