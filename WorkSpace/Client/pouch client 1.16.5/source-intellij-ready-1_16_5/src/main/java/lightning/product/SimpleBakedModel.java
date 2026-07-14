/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.ItemTransforms;
import lightning.product.K_4074_S;
import lightning.product.L_4237_Q;
import lightning.product.S_3826_o;
import lightning.product.b_257_Y;
import lightning.product.c_932_S;
import lightning.product.o_3047_I;

public class SimpleBakedModel
implements S_3826_o {
    protected final List<c_932_S> n_1700_B;
    protected final Map<b_257_Y, List<c_932_S>> J_1907_R;
    protected final boolean R_4764_Y;
    protected final boolean G_564_y;
    protected final boolean P_1922_E;
    protected final B_3871_I u_1723_Y;
    protected final ItemTransforms v_4262_N;
    protected final L_4237_Q w_1484_f;

    public SimpleBakedModel(List<c_932_S> generalQuad, Map<b_257_Y, List<c_932_S>> faceQuads, boolean ambientOcclusion, boolean isSideLit, boolean gui3d, B_3871_I texture, ItemTransforms cameraTransforms, L_4237_Q itemOverrideList) {
        this.n_1700_B = generalQuad;
        this.J_1907_R = faceQuads;
        this.R_4764_Y = ambientOcclusion;
        this.G_564_y = gui3d;
        this.P_1922_E = isSideLit;
        this.u_1723_Y = texture;
        this.v_4262_N = cameraTransforms;
        this.w_1484_f = itemOverrideList;
    }

    @Override
    public List<c_932_S> n_1700_B(@Nullable K_4074_S state, @Nullable b_257_Y side, Random rand) {
        return side == null ? this.n_1700_B : this.J_1907_R.get(side);
    }

    @Override
    public boolean n_1700_B() {
        return this.R_4764_Y;
    }

    @Override
    public boolean J_1907_R() {
        return this.G_564_y;
    }

    @Override
    public boolean R_4764_Y() {
        return this.P_1922_E;
    }

    @Override
    public boolean G_564_y() {
        return false;
    }

    @Override
    public B_3871_I P_1922_E() {
        return this.u_1723_Y;
    }

    @Override
    public ItemTransforms u_1723_Y() {
        return this.v_4262_N;
    }

    @Override
    public L_4237_Q v_4262_N() {
        return this.w_1484_f;
    }

    public static class n_1700_B {
        private final List<c_932_S> n_1700_B = Lists.newArrayList();
        private final Map<b_257_Y, List<c_932_S>> J_1907_R = Maps.newEnumMap(b_257_Y.class);
        private final L_4237_Q R_4764_Y;
        private final boolean G_564_y;
        private B_3871_I P_1922_E;
        private final boolean u_1723_Y;
        private final boolean v_4262_N;
        private final ItemTransforms w_1484_f;

        public n_1700_B(o_3047_I model, L_4237_Q itemOverrideList, boolean gui3d) {
            this(model.J_1907_R(), model.R_4764_Y().n_1700_B(), gui3d, model.v_4262_N(), itemOverrideList);
        }

        private n_1700_B(boolean ambientOcclusion, boolean isSideLit, boolean isSideLit2, ItemTransforms cameraTransforms, L_4237_Q itemOverrideList) {
            for (b_257_Y direction : b_257_Y.values()) {
                this.J_1907_R.put(direction, Lists.newArrayList());
            }
            this.R_4764_Y = itemOverrideList;
            this.G_564_y = ambientOcclusion;
            this.u_1723_Y = isSideLit;
            this.v_4262_N = isSideLit2;
            this.w_1484_f = cameraTransforms;
        }

        public n_1700_B n_1700_B(b_257_Y facing, c_932_S quad) {
            this.J_1907_R.get(facing).add(quad);
            return this;
        }

        public n_1700_B n_1700_B(c_932_S quad) {
            this.n_1700_B.add(quad);
            return this;
        }

        public n_1700_B n_1700_B(B_3871_I texture) {
            this.P_1922_E = texture;
            return this;
        }

        public S_3826_o n_1700_B() {
            if (this.P_1922_E == null) {
                throw new RuntimeException("Missing particle!");
            }
            return new SimpleBakedModel(this.n_1700_B, this.J_1907_R, this.G_564_y, this.u_1723_Y, this.v_4262_N, this.P_1922_E, this.w_1484_f, this.R_4764_Y);
        }
    }
}


