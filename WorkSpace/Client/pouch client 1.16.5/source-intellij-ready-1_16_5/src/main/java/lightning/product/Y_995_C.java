/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.MutableComponent;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.i_4556_r;
import lightning.product.MinMaxBounds;
import lightning.product.t_5_h;
import lightning.product.ComponentUtils;
import lightning.product.y_2498_m;

public class Y_995_C {
    private final int n_1700_B;
    private final boolean J_1907_R;
    private final boolean R_4764_Y;
    private final Predicate<N_4263_v> G_564_y;
    private final MinMaxBounds.n_1700_B P_1922_E;
    private final Function<e_2866_D, e_2866_D> u_1723_Y;
    @Nullable
    private final I_4817_s v_4262_N;
    private final BiConsumer<e_2866_D, List<? extends N_4263_v>> w_1484_f;
    private final boolean t_148_a;
    @Nullable
    private final String s_956_w;
    @Nullable
    private final UUID u_2550_I;
    @Nullable
    private final t_5_h<?> M_588_G;
    private final boolean P_4830_p;

    public Y_995_C(int p_i50800_1_, boolean p_i50800_2_, boolean p_i50800_3_, Predicate<N_4263_v> p_i50800_4_, MinMaxBounds.n_1700_B p_i50800_5_, Function<e_2866_D, e_2866_D> p_i50800_6_, @Nullable I_4817_s p_i50800_7_, BiConsumer<e_2866_D, List<? extends N_4263_v>> p_i50800_8_, boolean p_i50800_9_, @Nullable String p_i50800_10_, @Nullable UUID p_i50800_11_, @Nullable t_5_h<?> p_i50800_12_, boolean p_i50800_13_) {
        this.n_1700_B = p_i50800_1_;
        this.J_1907_R = p_i50800_2_;
        this.R_4764_Y = p_i50800_3_;
        this.G_564_y = p_i50800_4_;
        this.P_1922_E = p_i50800_5_;
        this.u_1723_Y = p_i50800_6_;
        this.v_4262_N = p_i50800_7_;
        this.w_1484_f = p_i50800_8_;
        this.t_148_a = p_i50800_9_;
        this.s_956_w = p_i50800_10_;
        this.u_2550_I = p_i50800_11_;
        this.M_588_G = p_i50800_12_;
        this.P_4830_p = p_i50800_13_;
    }

    public int n_1700_B() {
        return this.n_1700_B;
    }

    public boolean J_1907_R() {
        return this.J_1907_R;
    }

    public boolean R_4764_Y() {
        return this.t_148_a;
    }

    public boolean G_564_y() {
        return this.R_4764_Y;
    }

    private void P_1922_E(y_2498_m source) throws CommandSyntaxException {
        if (this.P_4830_p && !source.n_1700_B(2)) {
            throw i_4556_r.u_1723_Y.create();
        }
    }

    public N_4263_v n_1700_B(y_2498_m source) throws CommandSyntaxException {
        this.P_1922_E(source);
        List<? extends N_4263_v> list = this.J_1907_R(source);
        if (list.isEmpty()) {
            throw i_4556_r.G_564_y.create();
        }
        if (list.size() > 1) {
            throw i_4556_r.n_1700_B.create();
        }
        return list.get(0);
    }

    public List<? extends N_4263_v> J_1907_R(y_2498_m source) throws CommandSyntaxException {
        this.P_1922_E(source);
        if (!this.J_1907_R) {
            return this.G_564_y(source);
        }
        if (this.s_956_w != null) {
            B_4088_l serverplayerentity = source.w_1457_N().p_178_J().n_1700_B(this.s_956_w);
            return serverplayerentity == null ? Collections.emptyList() : Lists.newArrayList((Object[])new B_4088_l[]{serverplayerentity});
        }
        if (this.u_2550_I != null) {
            for (e_3591_l serverworld1 : source.w_1457_N().n_3318_d()) {
                N_4263_v entity = serverworld1.J_1907_R(this.u_2550_I);
                if (entity == null) continue;
                return Lists.newArrayList((Object[])new N_4263_v[]{entity});
            }
            return Collections.emptyList();
        }
        e_2866_D vector3d = this.u_1723_Y.apply(source.P_4830_p());
        Predicate<N_4263_v> predicate = this.n_1700_B(vector3d);
        if (this.t_148_a) {
            return source.Q_4569_t() != null && predicate.test(source.Q_4569_t()) ? Lists.newArrayList((Object[])new N_4263_v[]{source.Q_4569_t()}) : Collections.emptyList();
        }
        ArrayList list = Lists.newArrayList();
        if (this.G_564_y()) {
            this.n_1700_B(list, source.h_1847_R(), vector3d, predicate);
        } else {
            for (e_3591_l serverworld : source.w_1457_N().n_3318_d()) {
                this.n_1700_B(list, serverworld, vector3d, predicate);
            }
        }
        return this.n_1700_B(vector3d, list);
    }

    private void n_1700_B(List<N_4263_v> result, e_3591_l worldIn, e_2866_D pos, Predicate<N_4263_v> predicate) {
        if (this.v_4262_N != null) {
            result.addAll(worldIn.n_1700_B(this.M_588_G, this.v_4262_N.offset(pos), predicate));
        } else {
            result.addAll(worldIn.n_1700_B(this.M_588_G, predicate));
        }
    }

    public B_4088_l R_4764_Y(y_2498_m source) throws CommandSyntaxException {
        this.P_1922_E(source);
        List<B_4088_l> list = this.G_564_y(source);
        if (list.size() != 1) {
            throw i_4556_r.P_1922_E.create();
        }
        return list.get(0);
    }

    public List<B_4088_l> G_564_y(y_2498_m source) throws CommandSyntaxException {
        List<Object> list;
        this.P_1922_E(source);
        if (this.s_956_w != null) {
            B_4088_l serverplayerentity2 = source.w_1457_N().p_178_J().n_1700_B(this.s_956_w);
            return serverplayerentity2 == null ? Collections.emptyList() : Lists.newArrayList((Object[])new B_4088_l[]{serverplayerentity2});
        }
        if (this.u_2550_I != null) {
            B_4088_l serverplayerentity1 = source.w_1457_N().p_178_J().n_1700_B(this.u_2550_I);
            return serverplayerentity1 == null ? Collections.emptyList() : Lists.newArrayList((Object[])new B_4088_l[]{serverplayerentity1});
        }
        e_2866_D vector3d = this.u_1723_Y.apply(source.P_4830_p());
        Predicate<N_4263_v> predicate = this.n_1700_B(vector3d);
        if (this.t_148_a) {
            B_4088_l serverplayerentity3;
            if (source.Q_4569_t() instanceof B_4088_l && predicate.test(serverplayerentity3 = (B_4088_l)source.Q_4569_t())) {
                return Lists.newArrayList((Object[])new B_4088_l[]{serverplayerentity3});
            }
            return Collections.emptyList();
        }
        if (this.G_564_y()) {
            list = source.h_1847_R().n_1700_B(predicate::test);
        } else {
            list = Lists.newArrayList();
            for (B_4088_l serverplayerentity : source.w_1457_N().p_178_J().w_1457_N()) {
                if (!predicate.test(serverplayerentity)) continue;
                list.add(serverplayerentity);
            }
        }
        return this.n_1700_B(vector3d, list);
    }

    private Predicate<N_4263_v> n_1700_B(e_2866_D pos) {
        Predicate<N_4263_v> predicate = this.G_564_y;
        if (this.v_4262_N != null) {
            I_4817_s axisalignedbb = this.v_4262_N.offset(pos);
            predicate = predicate.and(p_197344_1_ -> axisalignedbb.intersects(p_197344_1_.i_601_W()));
        }
        if (!this.P_1922_E.R_4764_Y()) {
            predicate = predicate.and(p_211376_2_ -> this.P_1922_E.n_1700_B(p_211376_2_.u_1723_Y(pos)));
        }
        return predicate;
    }

    private <T extends N_4263_v> List<T> n_1700_B(e_2866_D pos, List<T> entities) {
        if (entities.size() > 1) {
            this.w_1484_f.accept(pos, entities);
        }
        return entities.subList(0, Math.min(this.n_1700_B, entities.size()));
    }

    public static MutableComponent n_1700_B(List<? extends N_4263_v> entities) {
        return ComponentUtils.J_1907_R(entities, N_4263_v::c_);
    }
}


