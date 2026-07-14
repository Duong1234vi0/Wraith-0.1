/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import java.util.Map;
import lightning.product.C_2701_A;
import lightning.product.ServerboundTeleportToEntityPacket;
import lightning.product.SpectatorMenuItem;
import lightning.product.U_2871_b;
import lightning.product.X_2140_T;
import lightning.product.a_3913_L;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.s_2614_w;
import lightning.product.x_282_a;

public class PlayerMenuItem
implements SpectatorMenuItem {
    private final GameProfile n_1700_B;
    private final g_2336_b J_1907_R;
    private final U_2871_b R_4764_Y;

    public PlayerMenuItem(GameProfile profileIn) {
        this.n_1700_B = profileIn;
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.c_4037_x().n_1700_B(profileIn);
        this.J_1907_R = map.containsKey(MinecraftProfileTexture.Type.SKIN) ? minecraft.c_4037_x().n_1700_B(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN) : s_2614_w.n_1700_B(a_3913_L.n_1700_B(profileIn));
        this.R_4764_Y = new U_2871_b(profileIn.getName());
    }

    @Override
    public void n_1700_B(X_2140_T menu) {
        MinecraftClient.A_4115_X().k_2293_S().n_1700_B(new ServerboundTeleportToEntityPacket(this.n_1700_B.getId()));
    }

    @Override
    public x_282_a R_4764_Y() {
        return this.R_4764_Y;
    }

    @Override
    public void n_1700_B(g_221_o p_230485_1_, float p_230485_2_, int p_230485_3_) {
        MinecraftClient.A_4115_X().G_624_v().n_1700_B(this.J_1907_R);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, (float)p_230485_3_ / 255.0f);
        C_2701_A.blit(p_230485_1_, 2, 2, 12, 12, 8.0f, 8.0f, 8, 8, 64, 64);
        C_2701_A.blit(p_230485_1_, 2, 2, 12, 12, 40.0f, 8.0f, 8, 8, 64, 64);
    }

    @Override
    public boolean G_564_y() {
        return true;
    }
}



