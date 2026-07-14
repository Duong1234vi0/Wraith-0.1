/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import lightning.product.H_1748_a;
import lightning.product.N_4263_v;
import lightning.product.P_3550_Z;
import lightning.product.Y_1387_d;
import lightning.product.b_4507_u;
import lightning.product.c_1108_W;
import net.optifine.ChunkDataOF;
import net.optifine.ChunkSectionDataOF;

public class ChunkOF
extends H_1748_a {
    private ChunkDataOF chunkDataOF;
    private boolean hasEntitiesOF;
    private boolean loadedOF;

    public ChunkOF(b_4507_u worldIn, Y_1387_d chunkPosIn, c_1108_W biomeContainerIn) {
        super(worldIn, chunkPosIn, biomeContainerIn);
    }

    public ChunkDataOF getChunkDataOF() {
        return this.chunkDataOF;
    }

    public void setChunkDataOF(ChunkDataOF chunkDataOF) {
        this.chunkDataOF = chunkDataOF;
    }

    public static ChunkDataOF makeChunkDataOF(H_1748_a chunkIn) {
        ChunkSectionDataOF[] achunksectiondataof = null;
        P_3550_Z chunksection = chunkIn.t_148_a();
        if (chunksection != null) {
            int i = (chunksection.v_4262_N() >> 4) + 1;
            achunksectiondataof = new ChunkSectionDataOF[i];
            P_3550_Z[] achunksection = chunkIn.getSections();
            for (int j = 0; j < i; ++j) {
                P_3550_Z chunksection1 = achunksection[j];
                if (chunksection1 == null) continue;
                short short1 = chunksection1.u_2550_I();
                short short2 = chunksection1.M_588_G();
                short short3 = chunksection1.P_4830_p();
                achunksectiondataof[j] = new ChunkSectionDataOF(short1, short2, short3);
            }
        }
        return new ChunkDataOF(achunksectiondataof);
    }

    @Override
    public void addEntity(N_4263_v entityIn) {
        this.hasEntitiesOF = true;
        super.addEntity(entityIn);
    }

    @Override
    public void setHasEntities(boolean hasEntitiesIn) {
        this.hasEntitiesOF = hasEntitiesIn;
        super.setHasEntities(hasEntitiesIn);
    }

    public boolean hasEntities() {
        return this.hasEntitiesOF;
    }

    @Override
    public void setLoaded(boolean loaded) {
        this.loadedOF = loaded;
        super.setLoaded(loaded);
    }

    public boolean isLoaded() {
        return this.loadedOF;
    }
}

