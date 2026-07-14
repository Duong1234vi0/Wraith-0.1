/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.k_594_Q;
import net.optifine.Config;
import net.optifine.IRandomEntity;
import net.optifine.util.TileEntityUtils;

public class RandomTileEntity
implements IRandomEntity {
    private i_2154_H tileEntity;

    @Override
    public int getId() {
        return Config.getRandom(this.tileEntity.x_607_J(), 0);
    }

    @Override
    public c_1514_x getSpawnPosition() {
        return this.tileEntity.x_607_J();
    }

    @Override
    public String getName() {
        return TileEntityUtils.getTileEntityName(this.tileEntity);
    }

    @Override
    public k_594_Q getSpawnBiome() {
        return this.tileEntity.c_3005_b().P_1922_E(this.tileEntity.x_607_J());
    }

    @Override
    public int getHealth() {
        return -1;
    }

    @Override
    public int getMaxHealth() {
        return -1;
    }

    public i_2154_H getTileEntity() {
        return this.tileEntity;
    }

    public void setTileEntity(i_2154_H tileEntity) {
        this.tileEntity = tileEntity;
    }
}

