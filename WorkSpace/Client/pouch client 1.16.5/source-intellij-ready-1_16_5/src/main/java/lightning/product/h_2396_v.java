/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSyntaxException
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import javax.annotation.Nullable;
import lightning.product.N_4263_v;
import lightning.product.U_2912_j;
import lightning.product.Tag;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.i_4431_W;
import lightning.product.n_3832_I;
import lightning.product.r_4318_c;

public class h_2396_v {
    public static final h_2396_v n_1700_B = new h_2396_v(null);
    @Nullable
    private final U_2912_j J_1907_R;

    public h_2396_v(@Nullable U_2912_j tag) {
        this.J_1907_R = tag;
    }

    public boolean n_1700_B(Z_1993_T item) {
        return this == n_1700_B ? true : this.n_1700_B(item.Q_4569_t());
    }

    public boolean n_1700_B(N_4263_v entityIn) {
        return this == n_1700_B ? true : this.n_1700_B(h_2396_v.J_1907_R(entityIn));
    }

    public boolean n_1700_B(@Nullable Tag nbt) {
        if (nbt == null) {
            return this == n_1700_B;
        }
        return this.J_1907_R == null || n_3832_I.n_1700_B(this.J_1907_R, nbt, true);
    }

    public JsonElement n_1700_B() {
        return this != n_1700_B && this.J_1907_R != null ? new JsonPrimitive(this.J_1907_R.toString()) : JsonNull.INSTANCE;
    }

    public static h_2396_v n_1700_B(@Nullable JsonElement json) {
        if (json != null && !json.isJsonNull()) {
            U_2912_j compoundnbt;
            try {
                compoundnbt = r_4318_c.n_1700_B(i_4431_W.n_1700_B(json, "nbt"));
            }
            catch (CommandSyntaxException commandsyntaxexception) {
                throw new JsonSyntaxException("Invalid nbt tag: " + commandsyntaxexception.getMessage());
            }
            return new h_2396_v(compoundnbt);
        }
        return n_1700_B;
    }

    public static U_2912_j J_1907_R(N_4263_v entityIn) {
        Z_1993_T itemstack;
        U_2912_j compoundnbt = entityIn.P_1922_E(new U_2912_j());
        if (entityIn instanceof a_3913_L && !(itemstack = ((a_3913_L)entityIn).l_1268_F.R_4764_Y()).n_1700_B()) {
            compoundnbt.n_1700_B("SelectedItem", itemstack.J_1907_R(new U_2912_j()));
        }
        return compoundnbt;
    }
}


