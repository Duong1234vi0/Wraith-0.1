/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.MinecraftSessionService
 *  com.mojang.authlib.properties.Property
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.H_1468_N;
import lightning.product.K_4074_S;
import lightning.product.U_2912_j;
import lightning.product.W_1689_V;
import lightning.product.X_1924_A;
import lightning.product.a_3742_W;
import lightning.product.i_2154_H;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.n_3832_I;
import lightning.product.BlockEntityType;

public class O_2639_P
extends i_2154_H
implements X_1924_A {
    @Nullable
    private static W_1689_V n_1700_B;
    @Nullable
    private static MinecraftSessionService J_1907_R;
    @Nullable
    private GameProfile R_4764_Y;
    private int G_564_y;
    private boolean P_1922_E;

    public O_2639_P() {
        super(BlockEntityType.Q_4569_t);
    }

    public static void n_1700_B(W_1689_V profileCacheIn) {
        n_1700_B = profileCacheIn;
    }

    public static void n_1700_B(MinecraftSessionService sessionServiceIn) {
        J_1907_R = sessionServiceIn;
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.R_4764_Y != null) {
            U_2912_j compoundnbt = new U_2912_j();
            n_3832_I.n_1700_B(compoundnbt, this.R_4764_Y);
            compound.n_1700_B("SkullOwner", compoundnbt);
        }
        return compound;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        String s;
        super.n_1700_B(state, nbt);
        if (nbt.R_4764_Y("SkullOwner", 10)) {
            this.n_1700_B(n_3832_I.n_1700_B(nbt.M_182_A("SkullOwner")));
        } else if (nbt.R_4764_Y("ExtraType", 8) && !H_1468_N.J_1907_R(s = nbt.M_588_G("ExtraType"))) {
            this.n_1700_B(new GameProfile((UUID)null, s));
        }
    }

    @Override
    public void P_1922_E() {
        K_4074_S blockstate = this.e_4240_b();
        if (blockstate.n_1700_B(a_3742_W.H_1491_c) || blockstate.n_1700_B(a_3742_W.h_2367_h)) {
            if (this.u_2550_I.Y_601_j(this.M_588_G)) {
                this.P_1922_E = true;
                ++this.G_564_y;
            } else {
                this.P_1922_E = false;
            }
        }
    }

    public float n_1700_B(float p_184295_1_) {
        return this.P_1922_E ? (float)this.G_564_y + p_184295_1_ : (float)this.G_564_y;
    }

    @Nullable
    public GameProfile v_4262_N() {
        return this.R_4764_Y;
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket G_() {
        return new ClientboundBlockEntityDataPacket(this.M_588_G, 4, this.H_());
    }

    @Override
    public U_2912_j H_() {
        return this.n_1700_B(new U_2912_j());
    }

    public void n_1700_B(@Nullable GameProfile p_195485_1_) {
        this.R_4764_Y = p_195485_1_;
        this.w_1484_f();
    }

    private void w_1484_f() {
        this.R_4764_Y = O_2639_P.J_1907_R(this.R_4764_Y);
        this.J_1907_R();
    }

    @Nullable
    public static GameProfile J_1907_R(@Nullable GameProfile input) {
        if (input != null && !H_1468_N.J_1907_R(input.getName())) {
            if (input.isComplete() && input.getProperties().containsKey((Object)"textures")) {
                return input;
            }
            if (n_1700_B != null && J_1907_R != null) {
                GameProfile gameprofile = n_1700_B.n_1700_B(input.getName());
                if (gameprofile == null) {
                    return input;
                }
                Property property = (Property)Iterables.getFirst((Iterable)gameprofile.getProperties().get((Object)"textures"), (Object)null);
                if (property == null) {
                    gameprofile = J_1907_R.fillProfileProperties(gameprofile, true);
                }
                return gameprofile;
            }
            return input;
        }
        return input;
    }
}


