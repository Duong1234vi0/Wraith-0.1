/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.DataLayer;
import lightning.product.LightEventListener;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;

public interface LayerLightEventListener
extends LightEventListener {
    @Nullable
    public DataLayer n_1700_B(SectionPos var1);

    public int n_1700_B(c_1514_x var1);

    public static final class n_1700_B
    extends Enum<n_1700_B>
    implements LayerLightEventListener {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] J_1907_R;

        public static n_1700_B[] values() {
            return (n_1700_B[])J_1907_R.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        @Override
        @Nullable
        public DataLayer n_1700_B(SectionPos p_215612_1_) {
            return null;
        }

        @Override
        public int n_1700_B(c_1514_x worldPos) {
            return 0;
        }

        @Override
        public void n_1700_B(SectionPos pos, boolean isEmpty) {
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B};
        }

        static {
            J_1907_R = lightning.product.LayerLightEventListener$n_1700_B.n_1700_B();
        }
    }
}


