/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.util.UUID;
import lightning.product.ChunkStatus;
import lightning.product.N_4263_v;
import lightning.product.R_3197_Z;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.i_2154_H;
import lightning.product.k_4690_i;
import net.optifine.Config;

public class IntegratedServerUtils {
    public static e_3591_l getWorldServer() {
        MinecraftClient minecraft = Config.getMinecraft();
        k_4690_i world = minecraft.Y_601_j;
        if (world == null) {
            return null;
        }
        if (!minecraft.x_607_J()) {
            return null;
        }
        R_3197_Z integratedserver = minecraft.n_3318_d();
        if (integratedserver == null) {
            return null;
        }
        f_2392_k<b_4507_u> registrykey = world.g_2268_R();
        if (registrykey == null) {
            return null;
        }
        try {
            return integratedserver.n_1700_B(registrykey);
        }
        catch (NullPointerException nullpointerexception) {
            return null;
        }
    }

    public static N_4263_v getEntity(UUID uuid) {
        e_3591_l serverworld = IntegratedServerUtils.getWorldServer();
        return serverworld == null ? null : serverworld.J_1907_R(uuid);
    }

    public static i_2154_H getTileEntity(c_1514_x pos) {
        e_3591_l serverworld = IntegratedServerUtils.getWorldServer();
        if (serverworld == null) {
            return null;
        }
        ChunkAccess ichunk = serverworld.Y_259_p().J_1907_R(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.P_4830_p, false);
        return ichunk == null ? null : ichunk.getTileEntity(pos);
    }
}



