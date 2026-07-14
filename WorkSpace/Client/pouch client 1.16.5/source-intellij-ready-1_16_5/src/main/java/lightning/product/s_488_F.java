/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.I_408_V;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.a_3913_L;
import lightning.product.TargetingConditions;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;

public interface s_488_F {
    public List<N_4263_v> J_1907_R(@Nullable N_4263_v var1, I_4817_s var2, @Nullable Predicate<? super N_4263_v> var3);

    public <T extends N_4263_v> List<T> n_1700_B(Class<? extends T> var1, I_4817_s var2, @Nullable Predicate<? super T> var3);

    default public <T extends N_4263_v> List<T> J_1907_R(Class<? extends T> p_225316_1_, I_4817_s p_225316_2_, @Nullable Predicate<? super T> p_225316_3_) {
        return this.n_1700_B(p_225316_1_, p_225316_2_, p_225316_3_);
    }

    public List<? extends a_3913_L> multiplayerClientSuggestionProvider();

    default public List<N_4263_v> n_1700_B(@Nullable N_4263_v entityIn, I_4817_s bb) {
        return this.J_1907_R(entityIn, bb, I_408_V.v_4262_N);
    }

    default public boolean n_1700_B(@Nullable N_4263_v entityIn, s_1395_c shape) {
        if (shape.J_1907_R()) {
            return true;
        }
        for (N_4263_v entity : this.n_1700_B(entityIn, shape.n_1700_B())) {
            if (entity.t_4219_U || !entity.s_2632_s || entityIn != null && entity.Y_259_p(entityIn) || !x_268_Y.R_4764_Y(shape, x_268_Y.n_1700_B(entity.i_601_W()), BooleanOp.t_148_a)) continue;
            return false;
        }
        return true;
    }

    default public <T extends N_4263_v> List<T> n_1700_B(Class<? extends T> p_217357_1_, I_4817_s p_217357_2_) {
        return this.n_1700_B(p_217357_1_, p_217357_2_, I_408_V.v_4262_N);
    }

    default public <T extends N_4263_v> List<T> J_1907_R(Class<? extends T> p_225317_1_, I_4817_s p_225317_2_) {
        return this.J_1907_R(p_225317_1_, p_225317_2_, I_408_V.v_4262_N);
    }

    default public Stream<s_1395_c> n_1700_B(@Nullable N_4263_v p_230318_1_, I_4817_s p_230318_2_, Predicate<N_4263_v> p_230318_3_) {
        if (p_230318_2_.getAverageEdgeLength() < 1.0E-7) {
            return Stream.empty();
        }
        I_4817_s axisalignedbb = p_230318_2_.grow(1.0E-7);
        return this.J_1907_R(p_230318_1_, axisalignedbb, p_230318_3_.and(p_234892_2_ -> p_234892_2_.i_601_W().intersects(axisalignedbb) && (p_230318_1_ == null ? p_234892_2_.RealmsParentalConsentScreen() : p_230318_1_.u_1723_Y((N_4263_v)p_234892_2_)))).stream().map(N_4263_v::i_601_W).map(x_268_Y::n_1700_B);
    }

    @Nullable
    default public a_3913_L n_1700_B(double x, double y, double z, double distance, @Nullable Predicate<N_4263_v> predicate) {
        double d0 = -1.0;
        a_3913_L playerentity = null;
        for (a_3913_L a_3913_L2 : this.multiplayerClientSuggestionProvider()) {
            if (predicate != null && !predicate.test(a_3913_L2)) continue;
            double d1 = a_3913_L2.v_4262_N(x, y, z);
            if (!(distance < 0.0) && !(d1 < distance * distance) || d0 != -1.0 && !(d1 < d0)) continue;
            d0 = d1;
            playerentity = a_3913_L2;
        }
        return playerentity;
    }

    @Nullable
    default public a_3913_L n_1700_B(N_4263_v entityIn, double distance) {
        return this.n_1700_B(entityIn.O_3598_v(), entityIn.X_2960_b(), entityIn.l_2647_k(), distance, false);
    }

    @Nullable
    default public a_3913_L n_1700_B(double x, double y, double z, double distance, boolean creativePlayers) {
        Predicate<N_4263_v> predicate = creativePlayers ? I_408_V.P_1922_E : I_408_V.v_4262_N;
        return this.n_1700_B(x, y, z, distance, predicate);
    }

    default public boolean n_1700_B(double x, double y, double z, double distance) {
        for (a_3913_L a_3913_L2 : this.multiplayerClientSuggestionProvider()) {
            if (!I_408_V.v_4262_N.test(a_3913_L2) || !I_408_V.J_1907_R.test(a_3913_L2)) continue;
            double d0 = a_3913_L2.v_4262_N(x, y, z);
            if (!(distance < 0.0) && !(d0 < distance * distance)) continue;
            return true;
        }
        return false;
    }

    @Nullable
    default public a_3913_L n_1700_B(TargetingConditions predicate, r_4811_B target) {
        return this.n_1700_B(this.multiplayerClientSuggestionProvider(), predicate, target, target.O_3598_v(), target.X_2960_b(), target.l_2647_k());
    }

    @Nullable
    default public a_3913_L n_1700_B(TargetingConditions predicate, r_4811_B target, double p_217372_3_, double p_217372_5_, double p_217372_7_) {
        return this.n_1700_B(this.multiplayerClientSuggestionProvider(), predicate, target, p_217372_3_, p_217372_5_, p_217372_7_);
    }

    @Nullable
    default public a_3913_L n_1700_B(TargetingConditions predicate, double x, double y, double z) {
        return this.n_1700_B(this.multiplayerClientSuggestionProvider(), predicate, null, x, y, z);
    }

    @Nullable
    default public <T extends r_4811_B> T n_1700_B(Class<? extends T> entityClazz, TargetingConditions p_217360_2_, @Nullable r_4811_B target, double x, double y, double z, I_4817_s boundingBox) {
        return this.n_1700_B(this.n_1700_B(entityClazz, boundingBox, (Predicate)null), p_217360_2_, target, x, y, z);
    }

    @Nullable
    default public <T extends r_4811_B> T J_1907_R(Class<? extends T> p_225318_1_, TargetingConditions p_225318_2_, @Nullable r_4811_B p_225318_3_, double p_225318_4_, double p_225318_6_, double p_225318_8_, I_4817_s p_225318_10_) {
        return this.n_1700_B(this.J_1907_R(p_225318_1_, p_225318_10_, (Predicate)null), p_225318_2_, p_225318_3_, p_225318_4_, p_225318_6_, p_225318_8_);
    }

    @Nullable
    default public <T extends r_4811_B> T n_1700_B(List<? extends T> entities, TargetingConditions predicate, @Nullable r_4811_B target, double x, double y, double z) {
        double d0 = -1.0;
        r_4811_B t = null;
        for (r_4811_B t1 : entities) {
            if (!predicate.n_1700_B(target, t1)) continue;
            double d1 = t1.v_4262_N(x, y, z);
            if (d0 != -1.0 && !(d1 < d0)) continue;
            d0 = d1;
            t = t1;
        }
        return (T)t;
    }

    default public List<a_3913_L> n_1700_B(TargetingConditions predicate, r_4811_B target, I_4817_s box) {
        ArrayList list = Lists.newArrayList();
        for (a_3913_L a_3913_L2 : this.multiplayerClientSuggestionProvider()) {
            if (!box.contains(a_3913_L2.O_3598_v(), a_3913_L2.X_2960_b(), a_3913_L2.l_2647_k()) || !predicate.n_1700_B(target, a_3913_L2)) continue;
            list.add(a_3913_L2);
        }
        return list;
    }

    default public <T extends r_4811_B> List<T> n_1700_B(Class<? extends T> p_217374_1_, TargetingConditions p_217374_2_, r_4811_B p_217374_3_, I_4817_s p_217374_4_) {
        List<T> list = this.n_1700_B(p_217374_1_, p_217374_4_, (Predicate)null);
        ArrayList list1 = Lists.newArrayList();
        for (r_4811_B t : list) {
            if (!p_217374_2_.n_1700_B(p_217374_3_, t)) continue;
            list1.add(t);
        }
        return list1;
    }

    @Nullable
    default public a_3913_L n_1700_B(UUID uniqueIdIn) {
        for (int i = 0; i < this.multiplayerClientSuggestionProvider().size(); ++i) {
            a_3913_L playerentity = this.multiplayerClientSuggestionProvider().get(i);
            if (!uniqueIdIn.equals(playerentity.w_2705_t())) continue;
            return playerentity;
        }
        return null;
    }
}


