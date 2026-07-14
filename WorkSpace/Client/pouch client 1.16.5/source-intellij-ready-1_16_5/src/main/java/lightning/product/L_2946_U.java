/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import lightning.product.K_2588_D;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class L_2946_U<E extends r_4811_B>
extends Behavior<E> {
    private final Set<MemoryModuleType<?>> n_1700_B;
    private final n_1700_B R_4764_Y;
    private final J_1907_R G_564_y;
    private final K_2588_D<Behavior<? super E>> P_1922_E = new K_2588_D();

    public L_2946_U(Map<MemoryModuleType<?>, S_50_d> p_i51503_1_, Set<MemoryModuleType<?>> p_i51503_2_, n_1700_B p_i51503_3_, J_1907_R p_i51503_4_, List<Pair<Behavior<? super E>, Integer>> p_i51503_5_) {
        super(p_i51503_1_);
        this.n_1700_B = p_i51503_2_;
        this.R_4764_Y = p_i51503_3_;
        this.G_564_y = p_i51503_4_;
        p_i51503_5_.forEach(p_220411_1_ -> this.P_1922_E.n_1700_B((Behavior)p_220411_1_.getFirst(), (Integer)p_220411_1_.getSecond()));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        return this.P_1922_E.R_4764_Y().filter(p_220414_0_ -> p_220414_0_.n_1700_B() == Behavior.n_1700_B.J_1907_R).anyMatch(p_220413_4_ -> p_220413_4_.n_1700_B(worldIn, entityIn, gameTimeIn));
    }

    @Override
    protected boolean n_1700_B(long gameTime) {
        return false;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        this.R_4764_Y.n_1700_B(this.P_1922_E);
        this.G_564_y.n_1700_B(this.P_1922_E, worldIn, entityIn, gameTimeIn);
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, E owner, long gameTime) {
        this.P_1922_E.R_4764_Y().filter(p_220408_0_ -> p_220408_0_.n_1700_B() == Behavior.n_1700_B.J_1907_R).forEach(p_220409_4_ -> p_220409_4_.u_1723_Y(worldIn, owner, gameTime));
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        this.P_1922_E.R_4764_Y().filter(p_220407_0_ -> p_220407_0_.n_1700_B() == Behavior.n_1700_B.J_1907_R).forEach(p_220412_4_ -> p_220412_4_.v_4262_N(worldIn, entityIn, gameTimeIn));
        this.n_1700_B.forEach(((r_4811_B)entityIn).y_1945_D()::J_1907_R);
    }

    @Override
    public String toString() {
        Set set = this.P_1922_E.R_4764_Y().filter(p_220410_0_ -> p_220410_0_.n_1700_B() == Behavior.n_1700_B.J_1907_R).collect(Collectors.toSet());
        return "(" + this.getClass().getSimpleName() + "): " + String.valueOf(set);
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(p_220627_0_ -> {});
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(K_2588_D::n_1700_B);
        private final Consumer<K_2588_D<?>> R_4764_Y;
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(Consumer<K_2588_D<?>> p_i50849_3_) {
            this.R_4764_Y = p_i50849_3_;
        }

        public void n_1700_B(K_2588_D<?> p_220628_1_) {
            this.R_4764_Y.accept(p_220628_1_);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            G_564_y = lightning.product.L_2946_U$n_1700_B.n_1700_B();
        }
    }

    static abstract sealed class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(){

            @Override
            public <E extends r_4811_B> void n_1700_B(K_2588_D<Behavior<? super E>> p_220630_1_, e_3591_l p_220630_2_, E p_220630_3_, long p_220630_4_) {
                p_220630_1_.R_4764_Y().filter(p_220634_0_ -> p_220634_0_.n_1700_B() == Behavior.n_1700_B.n_1700_B).filter(p_220633_4_ -> p_220633_4_.P_1922_E(p_220630_2_, p_220630_3_, p_220630_4_)).findFirst();
            }
        };
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(){

            @Override
            public <E extends r_4811_B> void n_1700_B(K_2588_D<Behavior<? super E>> p_220630_1_, e_3591_l p_220630_2_, E p_220630_3_, long p_220630_4_) {
                p_220630_1_.R_4764_Y().filter(p_220632_0_ -> p_220632_0_.n_1700_B() == Behavior.n_1700_B.n_1700_B).forEach(p_220631_4_ -> p_220631_4_.P_1922_E(p_220630_2_, p_220630_3_, p_220630_4_));
            }
        };
        private static final /* synthetic */ J_1907_R[] R_4764_Y;

        public static J_1907_R[] values() {
            return (J_1907_R[])R_4764_Y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        public abstract <E extends r_4811_B> void n_1700_B(K_2588_D<Behavior<? super E>> var1, e_3591_l var2, E var3, long var4);

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.L_2946_U$J_1907_R.n_1700_B();
        }
    }
}


