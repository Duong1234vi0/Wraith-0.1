/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.n_3832_I;
import lightning.product.q_1613_l;

public class ItemInput
implements Predicate<Z_1993_T> {
    private static final Dynamic2CommandExceptionType n_1700_B = new Dynamic2CommandExceptionType((item, maxStackSize) -> new F_2904_S("arguments.item.overstacked", item, maxStackSize));
    private final q_1613_l J_1907_R;
    @Nullable
    private final U_2912_j R_4764_Y;

    public ItemInput(q_1613_l itemIn, @Nullable U_2912_j tagIn) {
        this.J_1907_R = itemIn;
        this.R_4764_Y = tagIn;
    }

    public q_1613_l n_1700_B() {
        return this.J_1907_R;
    }

    public boolean n_1700_B(Z_1993_T p_test_1_) {
        return p_test_1_.J_1907_R() == this.J_1907_R && n_3832_I.n_1700_B(this.R_4764_Y, p_test_1_.Q_4569_t(), true);
    }

    public Z_1993_T n_1700_B(int count, boolean allowOversizedStacks) throws CommandSyntaxException {
        Z_1993_T itemstack = new Z_1993_T(this.J_1907_R, count);
        if (this.R_4764_Y != null) {
            itemstack.R_4764_Y(this.R_4764_Y);
        }
        if (allowOversizedStacks && count > itemstack.R_4764_Y()) {
            throw n_1700_B.create((Object)V_3137_a.e_2887_G.J_1907_R(this.J_1907_R), (Object)itemstack.R_4764_Y());
        }
        return itemstack;
    }

    public String J_1907_R() {
        StringBuilder stringbuilder = new StringBuilder(V_3137_a.e_2887_G.n_1700_B(this.J_1907_R));
        if (this.R_4764_Y != null) {
            stringbuilder.append(this.R_4764_Y);
        }
        return stringbuilder.toString();
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((Z_1993_T)object);
    }
}


