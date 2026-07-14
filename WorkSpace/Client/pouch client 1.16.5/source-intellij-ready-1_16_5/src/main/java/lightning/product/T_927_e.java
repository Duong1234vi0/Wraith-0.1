/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BigContext;
import lightning.product.AreaTransformer1;
import lightning.product.t_4013_W;

public sealed class T_927_e
extends Enum<T_927_e>
implements AreaTransformer1 {
    public static final /* enum */ T_927_e n_1700_B = new T_927_e();
    public static final /* enum */ T_927_e J_1907_R = new T_927_e(){

        @Override
        protected int n_1700_B(BigContext<?> context, int first, int second, int third, int fourth) {
            return context.n_1700_B(first, second, third, fourth);
        }
    };
    private static final /* synthetic */ T_927_e[] R_4764_Y;

    public static T_927_e[] values() {
        return (T_927_e[])R_4764_Y.clone();
    }

    public static T_927_e valueOf(String name) {
        return Enum.valueOf(T_927_e.class, name);
    }

    @Override
    public int n_1700_B(int x) {
        return x >> 1;
    }

    @Override
    public int J_1907_R(int z) {
        return z >> 1;
    }

    @Override
    public int n_1700_B(BigContext<?> context, t_4013_W area, int x, int z) {
        int i = area.n_1700_B(this.n_1700_B(x), this.J_1907_R(z));
        context.n_1700_B((long)(x >> 1 << 1), (long)(z >> 1 << 1));
        int j = x & 1;
        int k = z & 1;
        if (j == 0 && k == 0) {
            return i;
        }
        int l = area.n_1700_B(this.n_1700_B(x), this.J_1907_R(z + 1));
        int i1 = context.n_1700_B(i, l);
        if (j == 0 && k == 1) {
            return i1;
        }
        int j1 = area.n_1700_B(this.n_1700_B(x + 1), this.J_1907_R(z));
        int k1 = context.n_1700_B(i, j1);
        if (j == 1 && k == 0) {
            return k1;
        }
        int l1 = area.n_1700_B(this.n_1700_B(x + 1), this.J_1907_R(z + 1));
        return this.n_1700_B(context, i, j1, l, l1);
    }

    protected int n_1700_B(BigContext<?> context, int first, int second, int third, int fourth) {
        if (second == third && third == fourth) {
            return second;
        }
        if (first == second && first == third) {
            return first;
        }
        if (first == second && first == fourth) {
            return first;
        }
        if (first == third && first == fourth) {
            return first;
        }
        if (first == second && third != fourth) {
            return first;
        }
        if (first == third && second != fourth) {
            return first;
        }
        if (first == fourth && second != third) {
            return first;
        }
        if (second == third && first != fourth) {
            return second;
        }
        if (second == fourth && first != third) {
            return second;
        }
        return third == fourth && first != second ? third : context.n_1700_B(first, second, third, fourth);
    }

    private static /* synthetic */ T_927_e[] n_1700_B() {
        return new T_927_e[]{n_1700_B, J_1907_R};
    }

    static {
        R_4764_Y = T_927_e.n_1700_B();
    }
}


