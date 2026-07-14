/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  org.lwjgl.system.MemoryUtil
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import lightning.product.D_3318_r;
import lightning.product.X_933_l;
import lightning.product.b_1213_w;
import lightning.product.c_4037_x;
import net.optifine.Config;
import net.optifine.render.MultiTextureData;
import net.optifine.render.MultiTextureRenderer;
import net.optifine.shaders.SVertexBuilder;
import org.lwjgl.system.MemoryUtil;

public class o_2840_r {
    public static void n_1700_B(D_3318_r bufferBuilderIn) {
        if (!c_4037_x.J_1907_R()) {
            c_4037_x.n_1700_B(() -> {
                Pair<D_3318_r.n_1700_B, ByteBuffer> pair1 = bufferBuilderIn.v_4262_N();
                D_3318_r.n_1700_B bufferbuilder$drawstate1 = (D_3318_r.n_1700_B)pair1.getFirst();
                o_2840_r.n_1700_B((ByteBuffer)pair1.getSecond(), bufferbuilder$drawstate1.G_564_y(), bufferbuilder$drawstate1.J_1907_R(), bufferbuilder$drawstate1.R_4764_Y(), bufferbuilder$drawstate1.n_1700_B());
            });
        } else {
            Pair<D_3318_r.n_1700_B, ByteBuffer> pair = bufferBuilderIn.v_4262_N();
            D_3318_r.n_1700_B bufferbuilder$drawstate = (D_3318_r.n_1700_B)pair.getFirst();
            o_2840_r.n_1700_B((ByteBuffer)pair.getSecond(), bufferbuilder$drawstate.G_564_y(), bufferbuilder$drawstate.J_1907_R(), bufferbuilder$drawstate.R_4764_Y(), bufferbuilder$drawstate.n_1700_B());
        }
    }

    private static void n_1700_B(ByteBuffer bufferIn, int modeIn, b_1213_w vertexFormatIn, int countIn) {
        o_2840_r.n_1700_B(bufferIn, modeIn, vertexFormatIn, countIn, null);
    }

    private static void n_1700_B(ByteBuffer p_draw_0_, int p_draw_1_, b_1213_w p_draw_2_, int p_draw_3_, MultiTextureData p_draw_4_) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        ((Buffer)p_draw_0_).clear();
        if (p_draw_3_ > 0) {
            boolean flag;
            p_draw_2_.n_1700_B(MemoryUtil.memAddress((ByteBuffer)p_draw_0_));
            boolean bl = flag = Config.isShaders() && SVertexBuilder.preDrawArrays(p_draw_2_, p_draw_0_);
            if (p_draw_4_ != null) {
                MultiTextureRenderer.draw(p_draw_1_, p_draw_4_);
            } else {
                X_933_l.u_1723_Y(p_draw_1_, 0, p_draw_3_);
            }
            if (flag) {
                SVertexBuilder.postDrawArrays();
            }
            p_draw_2_.G_564_y();
        }
    }
}

