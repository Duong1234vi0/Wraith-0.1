/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Consumer;
import java.util.function.Function;
import lightning.product.D_4792_h;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_2576_A;
import net.optifine.EmissiveTextures;

public abstract class v_3569_v
implements Consumer<e_4189_z> {
    protected final Function<g_2336_b, o_2576_A> renderType;
    public int textureWidth = 64;
    public int textureHeight = 32;

    public v_3569_v(Function<g_2336_b, o_2576_A> renderTypeIn) {
        this.renderType = renderTypeIn;
    }

    @Override
    public void accept(e_4189_z p_accept_1_) {
    }

    public final o_2576_A getRenderType(g_2336_b locationIn) {
        o_2576_A rendertype = this.renderType.apply(locationIn);
        if (EmissiveTextures.isRenderEmissive() && rendertype.x_607_J()) {
            rendertype = o_2576_A.R_4764_Y(locationIn);
        }
        return rendertype;
    }

    public abstract void render(g_221_o var1, D_4792_h var2, int var3, int var4, float var5, float var6, float var7, float var8);
}

