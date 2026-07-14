/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.HashSet;
import java.util.Set;
import lightning.product.N_4263_v;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.u_530_F;
import lightning.product.z_4547_I;
import lightning.product.z_883_p;
import net.optifine.Config;
import net.optifine.DynamicLights;

public class DynamicLight {
    private N_4263_v entity = null;
    private double offsetY = 0.0;
    private double lastPosX = -2.147483648E9;
    private double lastPosY = -2.147483648E9;
    private double lastPosZ = -2.147483648E9;
    private int lastLightLevel = 0;
    private long timeCheckMs = 0L;
    private Set<c_1514_x> setLitChunkPos = new HashSet<c_1514_x>();
    private c_1514_x.n_1700_B blockPosMutable = new c_1514_x.n_1700_B();

    public DynamicLight(N_4263_v entity) {
        this.entity = entity;
        this.offsetY = entity.X_1313_W();
    }

    public void update(z_883_p renderGlobal) {
        if (Config.isDynamicLightsFast()) {
            long i = System.currentTimeMillis();
            if (i < this.timeCheckMs + 500L) {
                return;
            }
            this.timeCheckMs = i;
        }
        double d6 = this.entity.O_3598_v() - 0.5;
        double d0 = this.entity.X_2960_b() - 0.5 + this.offsetY;
        double d1 = this.entity.l_2647_k() - 0.5;
        int j = DynamicLights.getLightLevel(this.entity);
        double d2 = d6 - this.lastPosX;
        double d3 = d0 - this.lastPosY;
        double d4 = d1 - this.lastPosZ;
        double d5 = 0.1;
        if (!(Math.abs(d2) <= d5 && Math.abs(d3) <= d5 && Math.abs(d4) <= d5 && this.lastLightLevel == j)) {
            this.lastPosX = d6;
            this.lastPosY = d0;
            this.lastPosZ = d1;
            this.lastLightLevel = j;
            HashSet<c_1514_x> set = new HashSet<c_1514_x>();
            if (j > 0) {
                b_257_Y direction = (u_530_F.R_4764_Y(d6) & 0xF) >= 8 ? b_257_Y.u_1723_Y : b_257_Y.P_1922_E;
                b_257_Y direction1 = (u_530_F.R_4764_Y(d0) & 0xF) >= 8 ? b_257_Y.J_1907_R : b_257_Y.n_1700_B;
                b_257_Y direction2 = (u_530_F.R_4764_Y(d1) & 0xF) >= 8 ? b_257_Y.G_564_y : b_257_Y.R_4764_Y;
                c_1514_x blockpos = new c_1514_x(d6, d0, d1);
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender = renderGlobal.n_1700_B(blockpos);
                c_1514_x blockpos1 = this.getChunkPos(chunkrenderdispatcher$chunkrender, blockpos, direction);
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender1 = renderGlobal.n_1700_B(blockpos1);
                c_1514_x blockpos2 = this.getChunkPos(chunkrenderdispatcher$chunkrender, blockpos, direction2);
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender2 = renderGlobal.n_1700_B(blockpos2);
                c_1514_x blockpos3 = this.getChunkPos(chunkrenderdispatcher$chunkrender1, blockpos1, direction2);
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender3 = renderGlobal.n_1700_B(blockpos3);
                c_1514_x blockpos4 = this.getChunkPos(chunkrenderdispatcher$chunkrender, blockpos, direction1);
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender4 = renderGlobal.n_1700_B(blockpos4);
                c_1514_x blockpos5 = this.getChunkPos(chunkrenderdispatcher$chunkrender4, blockpos4, direction);
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender5 = renderGlobal.n_1700_B(blockpos5);
                c_1514_x blockpos6 = this.getChunkPos(chunkrenderdispatcher$chunkrender4, blockpos4, direction2);
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender6 = renderGlobal.n_1700_B(blockpos6);
                c_1514_x blockpos7 = this.getChunkPos(chunkrenderdispatcher$chunkrender5, blockpos5, direction2);
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender7 = renderGlobal.n_1700_B(blockpos7);
                this.updateChunkLight(chunkrenderdispatcher$chunkrender, this.setLitChunkPos, set);
                this.updateChunkLight(chunkrenderdispatcher$chunkrender1, this.setLitChunkPos, set);
                this.updateChunkLight(chunkrenderdispatcher$chunkrender2, this.setLitChunkPos, set);
                this.updateChunkLight(chunkrenderdispatcher$chunkrender3, this.setLitChunkPos, set);
                this.updateChunkLight(chunkrenderdispatcher$chunkrender4, this.setLitChunkPos, set);
                this.updateChunkLight(chunkrenderdispatcher$chunkrender5, this.setLitChunkPos, set);
                this.updateChunkLight(chunkrenderdispatcher$chunkrender6, this.setLitChunkPos, set);
                this.updateChunkLight(chunkrenderdispatcher$chunkrender7, this.setLitChunkPos, set);
            }
            this.updateLitChunks(renderGlobal);
            this.setLitChunkPos = set;
        }
    }

    private c_1514_x getChunkPos(z_4547_I.n_1700_B renderChunk, c_1514_x pos, b_257_Y facing) {
        return renderChunk != null ? renderChunk.n_1700_B(facing) : pos.offset(facing, 16);
    }

    private void updateChunkLight(z_4547_I.n_1700_B renderChunk, Set<c_1514_x> setPrevPos, Set<c_1514_x> setNewPos) {
        if (renderChunk != null) {
            z_4547_I.R_4764_Y chunkrenderdispatcher$compiledchunk = renderChunk.R_4764_Y();
            if (chunkrenderdispatcher$compiledchunk != null && !chunkrenderdispatcher$compiledchunk.n_1700_B()) {
                renderChunk.n_1700_B(false);
            }
            c_1514_x blockpos = renderChunk.P_1922_E().toImmutable();
            if (setPrevPos != null) {
                setPrevPos.remove(blockpos);
            }
            if (setNewPos != null) {
                setNewPos.add(blockpos);
            }
        }
    }

    public void updateLitChunks(z_883_p renderGlobal) {
        for (c_1514_x blockpos : this.setLitChunkPos) {
            z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender = renderGlobal.n_1700_B(blockpos);
            this.updateChunkLight(chunkrenderdispatcher$chunkrender, null, null);
        }
    }

    public N_4263_v getEntity() {
        return this.entity;
    }

    public double getLastPosX() {
        return this.lastPosX;
    }

    public double getLastPosY() {
        return this.lastPosY;
    }

    public double getLastPosZ() {
        return this.lastPosZ;
    }

    public int getLastLightLevel() {
        return this.lastLightLevel;
    }

    public double getOffsetY() {
        return this.offsetY;
    }

    public String toString() {
        return "Entity: " + String.valueOf(this.entity) + ", offsetY: " + this.offsetY;
    }
}

