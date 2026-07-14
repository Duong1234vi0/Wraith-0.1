/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.J_2538_C;
import lightning.product.K_4074_S;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.b_1320_N;
import lightning.product.e_933_M;
import lightning.product.Nameable;
import lightning.product.i_2154_H;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.q_2896_o;
import lightning.product.BlockEntityType;
import lightning.product.AbstractBannerBlock;
import lightning.product.x_282_a;

public class r_4889_F
extends i_2154_H
implements Nameable {
    @Nullable
    private x_282_a n_1700_B;
    @Nullable
    private e_933_M J_1907_R = e_933_M.n_1700_B;
    @Nullable
    private q_2896_o R_4764_Y;
    private boolean G_564_y;
    @Nullable
    private List<Pair<J_2538_C, e_933_M>> P_1922_E;

    public r_4889_F() {
        super(BlockEntityType.w_1457_N);
    }

    public r_4889_F(e_933_M baseColor) {
        this();
        this.J_1907_R = baseColor;
    }

    @Nullable
    public static q_2896_o n_1700_B(Z_1993_T stack) {
        q_2896_o listnbt = null;
        U_2912_j compoundnbt = stack.J_1907_R("BlockEntityTag");
        if (compoundnbt != null && compoundnbt.R_4764_Y("Patterns", 9)) {
            listnbt = compoundnbt.G_564_y("Patterns", 10).G_564_y();
        }
        return listnbt;
    }

    public void n_1700_B(Z_1993_T stack, e_933_M color) {
        this.R_4764_Y = r_4889_F.n_1700_B(stack);
        this.J_1907_R = color;
        this.P_1922_E = null;
        this.G_564_y = true;
        this.n_1700_B = stack.Y_601_j() ? stack.multiplayerClientSuggestionProvider() : null;
    }

    @Override
    public x_282_a O_1309_Q() {
        return this.n_1700_B != null ? this.n_1700_B : new F_2904_S("block.minecraft.banner");
    }

    @Override
    @Nullable
    public x_282_a k_2302_P() {
        return this.n_1700_B;
    }

    public void n_1700_B(x_282_a name) {
        this.n_1700_B = name;
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.R_4764_Y != null) {
            compound.n_1700_B("Patterns", this.R_4764_Y);
        }
        if (this.n_1700_B != null) {
            compound.n_1700_B("CustomName", x_282_a.n_1700_B.n_1700_B(this.n_1700_B));
        }
        return compound;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        if (nbt.R_4764_Y("CustomName", 8)) {
            this.n_1700_B = x_282_a.n_1700_B.n_1700_B(nbt.M_588_G("CustomName"));
        }
        this.J_1907_R = this.t_4043_B() ? ((AbstractBannerBlock)this.e_4240_b().J_1907_R()).J_1907_R() : null;
        this.R_4764_Y = nbt.G_564_y("Patterns", 10);
        this.P_1922_E = null;
        this.G_564_y = true;
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket G_() {
        return new ClientboundBlockEntityDataPacket(this.M_588_G, 6, this.H_());
    }

    @Override
    public U_2912_j H_() {
        return this.n_1700_B(new U_2912_j());
    }

    public static int J_1907_R(Z_1993_T stack) {
        U_2912_j compoundnbt = stack.J_1907_R("BlockEntityTag");
        return compoundnbt != null && compoundnbt.P_1922_E("Patterns") ? compoundnbt.G_564_y("Patterns", 10).size() : 0;
    }

    public List<Pair<J_2538_C, e_933_M>> P_1922_E() {
        if (this.P_1922_E == null && this.G_564_y) {
            this.P_1922_E = r_4889_F.n_1700_B(this.n_1700_B(this::e_4240_b), this.R_4764_Y);
        }
        return this.P_1922_E;
    }

    public static List<Pair<J_2538_C, e_933_M>> n_1700_B(e_933_M color, @Nullable q_2896_o nbtList) {
        ArrayList list = Lists.newArrayList();
        list.add(Pair.of((Object)((Object)J_2538_C.n_1700_B), (Object)color));
        if (nbtList != null) {
            for (int i = 0; i < nbtList.size(); ++i) {
                U_2912_j compoundnbt = nbtList.n_1700_B(i);
                J_2538_C bannerpattern = J_2538_C.n_1700_B(compoundnbt.M_588_G("Pattern"));
                if (bannerpattern == null) continue;
                int j = compoundnbt.w_1484_f("Color");
                list.add(Pair.of((Object)((Object)bannerpattern), (Object)e_933_M.n_1700_B(j)));
            }
        }
        return list;
    }

    public static void R_4764_Y(Z_1993_T stack) {
        q_2896_o listnbt;
        U_2912_j compoundnbt = stack.J_1907_R("BlockEntityTag");
        if (compoundnbt != null && compoundnbt.R_4764_Y("Patterns", 9) && !(listnbt = compoundnbt.G_564_y("Patterns", 10)).isEmpty()) {
            listnbt.R_4764_Y(listnbt.size() - 1);
            if (listnbt.isEmpty()) {
                stack.R_4764_Y("BlockEntityTag");
            }
        }
    }

    public Z_1993_T n_1700_B(K_4074_S state) {
        Z_1993_T itemstack = new Z_1993_T(b_1320_N.n_1700_B(this.n_1700_B(() -> state)));
        if (this.R_4764_Y != null && !this.R_4764_Y.isEmpty()) {
            itemstack.n_1700_B("BlockEntityTag").n_1700_B("Patterns", this.R_4764_Y.G_564_y());
        }
        if (this.n_1700_B != null) {
            itemstack.n_1700_B(this.n_1700_B);
        }
        return itemstack;
    }

    public e_933_M n_1700_B(Supplier<K_4074_S> bannerBlockStateSupplier) {
        if (this.J_1907_R == null) {
            this.J_1907_R = ((AbstractBannerBlock)bannerBlockStateSupplier.get().J_1907_R()).J_1907_R();
        }
        return this.J_1907_R;
    }
}


