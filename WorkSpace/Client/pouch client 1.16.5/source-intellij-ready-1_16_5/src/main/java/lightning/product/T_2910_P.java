/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Objects;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.D_4792_h;
import lightning.product.H_3330_w;
import lightning.product.MinecraftClient;
import lightning.product.g_2336_b;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import net.optifine.EmissiveTextures;
import net.optifine.render.RenderUtils;

public class T_2910_P {
    private final g_2336_b n_1700_B;
    private final g_2336_b J_1907_R;
    @Nullable
    private o_2576_A R_4764_Y;

    public T_2910_P(g_2336_b atlasLocationIn, g_2336_b textureLocationIn) {
        this.n_1700_B = atlasLocationIn;
        this.J_1907_R = textureLocationIn;
    }

    public g_2336_b n_1700_B() {
        return this.n_1700_B;
    }

    public g_2336_b J_1907_R() {
        return this.J_1907_R;
    }

    public B_3871_I R_4764_Y() {
        B_3871_I textureatlassprite = MinecraftClient.A_4115_X().n_1700_B(this.n_1700_B()).apply(this.J_1907_R());
        if (EmissiveTextures.isActive()) {
            textureatlassprite = EmissiveTextures.getEmissiveSprite(textureatlassprite);
        }
        return textureatlassprite;
    }

    public o_2576_A n_1700_B(Function<g_2336_b, o_2576_A> renderTypeGetter) {
        if (this.R_4764_Y == null) {
            this.R_4764_Y = renderTypeGetter.apply(this.n_1700_B);
        }
        return this.R_4764_Y;
    }

    public D_4792_h n_1700_B(o_3091_w bufferIn, Function<g_2336_b, o_2576_A> renderTypeGetter) {
        B_3871_I textureatlassprite = this.R_4764_Y();
        o_2576_A rendertype = this.n_1700_B(renderTypeGetter);
        if (textureatlassprite.Q_4569_t && rendertype.x_607_J()) {
            RenderUtils.flushRenderBuffers();
            rendertype = o_2576_A.R_4764_Y(this.n_1700_B);
        }
        return textureatlassprite.n_1700_B(bufferIn.getBuffer(rendertype));
    }

    public D_4792_h n_1700_B(o_3091_w buffer, Function<g_2336_b, o_2576_A> renderTypeGetter, boolean withGlint) {
        return this.R_4764_Y().n_1700_B(H_3330_w.R_4764_Y(buffer, this.n_1700_B(renderTypeGetter), true, withGlint));
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            T_2910_P rendermaterial = (T_2910_P)p_equals_1_;
            return this.n_1700_B.equals(rendermaterial.n_1700_B) && this.J_1907_R.equals(rendermaterial.J_1907_R);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.n_1700_B, this.J_1907_R);
    }

    public String toString() {
        return "Material{atlasLocation=" + String.valueOf(this.n_1700_B) + ", texture=" + String.valueOf(this.J_1907_R) + "}";
    }
}


