/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.BiPredicate;
import java.util.function.Function;
import lightning.product.K_4074_S;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.DirectionProperty;
import lightning.product.i_2154_H;
import lightning.product.BlockEntityType;
import lightning.product.LevelAccessor;

public class DoubleBlockCombiner {
    public static <S extends i_2154_H> J_1907_R<S> n_1700_B(BlockEntityType<S> p_226924_0_, Function<K_4074_S, R_4764_Y> p_226924_1_, Function<K_4074_S, b_257_Y> p_226924_2_, DirectionProperty p_226924_3_, K_4074_S p_226924_4_, LevelAccessor p_226924_5_, c_1514_x p_226924_6_, BiPredicate<LevelAccessor, c_1514_x> p_226924_7_) {
        R_4764_Y tileentitymerger$type1;
        boolean flag1;
        S s = p_226924_0_.n_1700_B(p_226924_5_, p_226924_6_);
        if (s == null) {
            return n_1700_B::J_1907_R;
        }
        if (p_226924_7_.test(p_226924_5_, p_226924_6_)) {
            return n_1700_B::J_1907_R;
        }
        R_4764_Y tileentitymerger$type = p_226924_1_.apply(p_226924_4_);
        boolean flag = tileentitymerger$type == R_4764_Y.n_1700_B;
        boolean bl = flag1 = tileentitymerger$type == R_4764_Y.J_1907_R;
        if (flag) {
            return new J_1907_R.J_1907_R<S>(s);
        }
        c_1514_x blockpos = p_226924_6_.offset(p_226924_2_.apply(p_226924_4_));
        K_4074_S blockstate = p_226924_5_.getBlockState(blockpos);
        if (blockstate.n_1700_B(p_226924_4_.J_1907_R()) && (tileentitymerger$type1 = p_226924_1_.apply(blockstate)) != R_4764_Y.n_1700_B && tileentitymerger$type != tileentitymerger$type1 && blockstate.R_4764_Y(p_226924_3_) == p_226924_4_.R_4764_Y(p_226924_3_)) {
            if (p_226924_7_.test(p_226924_5_, blockpos)) {
                return n_1700_B::J_1907_R;
            }
            S s1 = p_226924_0_.n_1700_B(p_226924_5_, blockpos);
            if (s1 != null) {
                S s2 = flag1 ? s : s1;
                S s3 = flag1 ? s1 : s;
                return new J_1907_R.n_1700_B<S>(s2, s3);
            }
        }
        return new J_1907_R.J_1907_R<S>(s);
    }

    public static interface lightning.product.DoubleBlockCombiner$J_1907_R<S> {
        public <T> T apply(lightning.product.DoubleBlockCombiner$n_1700_B<? super S, T> var1);

        public static final class J_1907_R<S>
        implements lightning.product.DoubleBlockCombiner$J_1907_R<S> {
            private final S n_1700_B;

            public J_1907_R(S p_i225761_1_) {
                this.n_1700_B = p_i225761_1_;
            }

            @Override
            public <T> T apply(lightning.product.DoubleBlockCombiner$n_1700_B<? super S, T> p_apply_1_) {
                return p_apply_1_.n_1700_B(this.n_1700_B);
            }
        }

        public static final class n_1700_B<S>
        implements lightning.product.DoubleBlockCombiner$J_1907_R<S> {
            private final S n_1700_B;
            private final S J_1907_R;

            public n_1700_B(S p_i225760_1_, S p_i225760_2_) {
                this.n_1700_B = p_i225760_1_;
                this.J_1907_R = p_i225760_2_;
            }

            @Override
            public <T> T apply(lightning.product.DoubleBlockCombiner$n_1700_B<? super S, T> p_apply_1_) {
                return p_apply_1_.n_1700_B(this.n_1700_B, this.J_1907_R);
            }
        }
    }

    public static final class R_4764_Y
    extends Enum<R_4764_Y> {
        public static final /* enum */ R_4764_Y n_1700_B = new R_4764_Y();
        public static final /* enum */ R_4764_Y J_1907_R = new R_4764_Y();
        public static final /* enum */ R_4764_Y R_4764_Y = new R_4764_Y();
        private static final /* synthetic */ R_4764_Y[] G_564_y;

        public static R_4764_Y[] values() {
            return (R_4764_Y[])G_564_y.clone();
        }

        public static R_4764_Y valueOf(String name) {
            return Enum.valueOf(R_4764_Y.class, name);
        }

        private static /* synthetic */ R_4764_Y[] n_1700_B() {
            return new R_4764_Y[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.DoubleBlockCombiner$R_4764_Y.n_1700_B();
        }
    }

    public static interface n_1700_B<S, T> {
        public T n_1700_B(S var1, S var2);

        public T n_1700_B(S var1);

        public T J_1907_R();
    }
}


