/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import lightning.product.BlockGetter;
import lightning.product.U_2871_b;
import lightning.product.c_1514_x;
import lightning.product.c_1869_W;
import lightning.product.Nameable;
import lightning.product.i_2154_H;
import lightning.product.BaseContainerBlockEntity;
import lightning.product.r_4889_F;
import lightning.product.x_282_a;
import lightning.product.x_3974_Q;
import net.optifine.reflect.Reflector;
import net.optifine.util.IntegratedServerUtils;

public class TileEntityUtils {
    public static String getTileEntityName(BlockGetter blockAccess, c_1514_x blockPos) {
        i_2154_H tileentity = blockAccess.getTileEntity(blockPos);
        return TileEntityUtils.getTileEntityName(tileentity);
    }

    public static String getTileEntityName(i_2154_H te) {
        if (!(te instanceof Nameable)) {
            return null;
        }
        Nameable inameable = (Nameable)((Object)te);
        TileEntityUtils.updateTileEntityName(te);
        return !inameable.t_3452_g() ? null : inameable.k_2302_P().J_1907_R();
    }

    public static void updateTileEntityName(i_2154_H te) {
        c_1514_x blockpos = te.x_607_J();
        x_282_a itextcomponent = TileEntityUtils.getTileEntityRawName(te);
        if (itextcomponent == null) {
            x_282_a itextcomponent1 = TileEntityUtils.getServerTileEntityRawName(blockpos);
            if (itextcomponent1 == null) {
                itextcomponent1 = new U_2871_b("");
            }
            TileEntityUtils.setTileEntityRawName(te, itextcomponent1);
        }
    }

    public static x_282_a getServerTileEntityRawName(c_1514_x blockPos) {
        i_2154_H tileentity = IntegratedServerUtils.getTileEntity(blockPos);
        return tileentity == null ? null : TileEntityUtils.getTileEntityRawName(tileentity);
    }

    public static x_282_a getTileEntityRawName(i_2154_H te) {
        if (te instanceof Nameable) {
            return ((Nameable)((Object)te)).k_2302_P();
        }
        return te instanceof x_3974_Q ? (x_282_a)Reflector.getFieldValue(te, Reflector.TileEntityBeacon_customName) : null;
    }

    public static boolean setTileEntityRawName(i_2154_H te, x_282_a name) {
        if (te instanceof BaseContainerBlockEntity) {
            ((BaseContainerBlockEntity)te).n_1700_B(name);
            return true;
        }
        if (te instanceof r_4889_F) {
            ((r_4889_F)te).n_1700_B(name);
            return true;
        }
        if (te instanceof c_1869_W) {
            ((c_1869_W)te).n_1700_B(name);
            return true;
        }
        if (te instanceof x_3974_Q) {
            ((x_3974_Q)te).n_1700_B(name);
            return true;
        }
        return false;
    }
}


