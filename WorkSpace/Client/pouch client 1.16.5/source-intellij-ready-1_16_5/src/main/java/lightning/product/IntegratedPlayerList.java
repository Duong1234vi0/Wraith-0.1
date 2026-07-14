/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import java.net.SocketAddress;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.R_3197_Z;
import lightning.product.U_2912_j;
import lightning.product.Z_4308_L;
import lightning.product.g_1995_W;
import lightning.product.r_4097_j;
import lightning.product.x_282_a;
import net.minecraft.server.G_564_y;

public class IntegratedPlayerList
extends g_1995_W {
    private U_2912_j u_1723_Y;

    public IntegratedPlayerList(R_3197_Z p_i232493_1_, r_4097_j.J_1907_R p_i232493_2_, Z_4308_L p_i232493_3_) {
        super(p_i232493_1_, p_i232493_2_, p_i232493_3_, 8);
        this.n_1700_B(10);
    }

    @Override
    protected void n_1700_B(B_4088_l playerIn) {
        if (playerIn.O_1309_Q().getString().equals(this.J_1907_R().G_624_v())) {
            this.u_1723_Y = playerIn.P_1922_E(new U_2912_j());
        }
        super.n_1700_B(playerIn);
    }

    @Override
    public x_282_a n_1700_B(SocketAddress p_206258_1_, GameProfile p_206258_2_) {
        return p_206258_2_.getName().equalsIgnoreCase(this.J_1907_R().G_624_v()) && this.n_1700_B(p_206258_2_.getName()) != null ? new F_2904_S("multiplayer.disconnect.name_taken") : super.n_1700_B(p_206258_1_, p_206258_2_);
    }

    public R_3197_Z J_1907_R() {
        return (R_3197_Z)super.R_4764_Y();
    }

    @Override
    public U_2912_j G_564_y() {
        return this.u_1723_Y;
    }

    @Override
    public /* synthetic */ G_564_y R_4764_Y() {
        return this.J_1907_R();
    }
}


