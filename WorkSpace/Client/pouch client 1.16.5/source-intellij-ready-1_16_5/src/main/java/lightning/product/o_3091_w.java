/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lightning.product.D_3318_r;
import lightning.product.D_4792_h;
import lightning.product.g_2336_b;
import lightning.product.o_2576_A;
import net.optifine.render.VertexBuilderDummy;
import net.optifine.util.TextureUtils;

public interface o_3091_w {
    public static n_1700_B n_1700_B(D_3318_r builderIn) {
        return o_3091_w.n_1700_B((Map<o_2576_A, D_3318_r>)ImmutableMap.of(), builderIn);
    }

    public static n_1700_B n_1700_B(Map<o_2576_A, D_3318_r> mapBuildersIn, D_3318_r builderIn) {
        return new n_1700_B(builderIn, mapBuildersIn);
    }

    public D_4792_h getBuffer(o_2576_A var1);

    default public void n_1700_B() {
    }

    public static class n_1700_B
    implements o_3091_w {
        protected final D_3318_r n_1700_B;
        protected final Map<o_2576_A, D_3318_r> J_1907_R;
        protected o_2576_A R_4764_Y = null;
        protected final Set<D_3318_r> G_564_y = Sets.newIdentityHashSet();
        private final D_4792_h P_1922_E = new VertexBuilderDummy(this);

        protected n_1700_B(D_3318_r bufferIn, Map<o_2576_A, D_3318_r> fixedBuffersIn) {
            this.n_1700_B = bufferIn;
            this.J_1907_R = fixedBuffersIn;
            this.n_1700_B.n_1700_B(this);
            for (D_3318_r bufferbuilder : fixedBuffersIn.values()) {
                bufferbuilder.n_1700_B(this);
            }
        }

        @Override
        public D_4792_h getBuffer(o_2576_A p_getBuffer_1_) {
            D_3318_r bufferbuilder = this.J_1907_R(p_getBuffer_1_);
            if (!Objects.equals(this.R_4764_Y, p_getBuffer_1_)) {
                o_2576_A rendertype;
                if (this.R_4764_Y != null && !this.J_1907_R.containsKey(rendertype = this.R_4764_Y)) {
                    this.n_1700_B(rendertype);
                }
                if (this.G_564_y.add(bufferbuilder)) {
                    bufferbuilder.setRenderType(p_getBuffer_1_);
                    bufferbuilder.n_1700_B(p_getBuffer_1_.c_3005_b(), p_getBuffer_1_.Z_875_P());
                }
                this.R_4764_Y = p_getBuffer_1_;
            }
            return p_getBuffer_1_.n_3318_d() == TextureUtils.LOCATION_TEXTURE_EMPTY ? this.P_1922_E : bufferbuilder;
        }

        private D_3318_r J_1907_R(o_2576_A renderTypeIn) {
            return this.J_1907_R.getOrDefault(renderTypeIn, this.n_1700_B);
        }

        public void J_1907_R() {
            if (!this.G_564_y.isEmpty()) {
                D_4792_h ivertexbuilder;
                if (this.R_4764_Y != null && (ivertexbuilder = this.getBuffer(this.R_4764_Y)) == this.n_1700_B) {
                    this.n_1700_B(this.R_4764_Y);
                }
                if (!this.G_564_y.isEmpty()) {
                    for (o_2576_A rendertype : this.J_1907_R.keySet()) {
                        this.n_1700_B(rendertype);
                        if (!this.G_564_y.isEmpty()) continue;
                        break;
                    }
                }
            }
        }

        public void n_1700_B(o_2576_A renderTypeIn) {
            D_3318_r bufferbuilder = this.J_1907_R(renderTypeIn);
            boolean flag = Objects.equals(this.R_4764_Y, renderTypeIn);
            if ((flag || bufferbuilder != this.n_1700_B) && this.G_564_y.remove(bufferbuilder)) {
                renderTypeIn.n_1700_B(bufferbuilder, 0, 0, 0);
                if (flag) {
                    this.R_4764_Y = null;
                }
            }
        }

        public D_4792_h n_1700_B(g_2336_b p_getBuffer_1_, D_4792_h p_getBuffer_2_) {
            if (!(this.R_4764_Y instanceof o_2576_A.R_4764_Y)) {
                return p_getBuffer_2_;
            }
            p_getBuffer_1_ = o_2576_A.multiplayerClientSuggestionProvider(p_getBuffer_1_);
            o_2576_A.R_4764_Y rendertype$type = (o_2576_A.R_4764_Y)this.R_4764_Y;
            o_2576_A.R_4764_Y rendertype$type1 = rendertype$type.w_1457_N(p_getBuffer_1_);
            return this.getBuffer(rendertype$type1);
        }

        public o_2576_A R_4764_Y() {
            return this.R_4764_Y;
        }

        @Override
        public void n_1700_B() {
            o_2576_A rendertype = this.R_4764_Y;
            this.J_1907_R();
            if (rendertype != null) {
                this.getBuffer(rendertype);
            }
        }

        public D_4792_h G_564_y() {
            return this.P_1922_E;
        }
    }
}


