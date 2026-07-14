/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lightning.product.ChunkStatus;
import lightning.product.F_2904_S;
import lightning.product.I_1084_e;
import lightning.product.g_221_o;
import lightning.product.StoringChunkProgressListener;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.u_530_F;

public class O_1280_J
extends k_2603_m {
    private final StoringChunkProgressListener n_1700_B;
    private long J_1907_R = -1L;
    private static final Object2IntMap<ChunkStatus> R_4764_Y = (Object2IntMap)j_3341_s.n_1700_B(new Object2IntOpenHashMap(), p_213039_0_ -> {
        p_213039_0_.defaultReturnValue(0);
        p_213039_0_.put((Object)ChunkStatus.n_1700_B, 0x545454);
        p_213039_0_.put((Object)ChunkStatus.J_1907_R, 0x999999);
        p_213039_0_.put((Object)ChunkStatus.R_4764_Y, 6250897);
        p_213039_0_.put((Object)ChunkStatus.G_564_y, 8434258);
        p_213039_0_.put((Object)ChunkStatus.P_1922_E, 0xD1D1D1);
        p_213039_0_.put((Object)ChunkStatus.u_1723_Y, 7497737);
        p_213039_0_.put((Object)ChunkStatus.v_4262_N, 7169628);
        p_213039_0_.put((Object)ChunkStatus.w_1484_f, 3159410);
        p_213039_0_.put((Object)ChunkStatus.t_148_a, 2213376);
        p_213039_0_.put((Object)ChunkStatus.s_956_w, 0xCCCCCC);
        p_213039_0_.put((Object)ChunkStatus.u_2550_I, 15884384);
        p_213039_0_.put((Object)ChunkStatus.M_588_G, 0xEEEEEE);
        p_213039_0_.put((Object)ChunkStatus.P_4830_p, 0xFFFFFF);
    });

    public O_1280_J(StoringChunkProgressListener p_i51113_1_) {
        super(I_1084_e.n_1700_B);
        this.n_1700_B = p_i51113_1_;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void onClose() {
        I_1084_e.J_1907_R.n_1700_B(new F_2904_S("narrator.loading.done").getString());
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        String s = u_530_F.n_1700_B(this.n_1700_B.P_1922_E(), 0, 100) + "%";
        long i = j_3341_s.J_1907_R();
        if (i - this.J_1907_R > 2000L) {
            this.J_1907_R = i;
            I_1084_e.J_1907_R.n_1700_B(new F_2904_S("narrator.loading", s).getString());
        }
        int j = this.width / 2;
        int k = this.height / 2;
        int l = 30;
        O_1280_J.n_1700_B(matrixStack, this.n_1700_B, j, k + 30, 2, 0);
        O_1280_J.drawCenteredString(matrixStack, this.font, s, j, k - 4 - 30, 0xFFFFFF);
    }

    public static void n_1700_B(g_221_o p_238625_0_, StoringChunkProgressListener p_238625_1_, int p_238625_2_, int p_238625_3_, int p_238625_4_, int p_238625_5_) {
        int i = p_238625_4_ + p_238625_5_;
        int j = p_238625_1_.R_4764_Y();
        int k = j * i - p_238625_5_;
        int l = p_238625_1_.G_564_y();
        int i1 = l * i - p_238625_5_;
        int j1 = p_238625_2_ - i1 / 2;
        int k1 = p_238625_3_ - i1 / 2;
        int l1 = k / 2 + 1;
        int i2 = -16772609;
        if (p_238625_5_ != 0) {
            O_1280_J.fill(p_238625_0_, p_238625_2_ - l1, p_238625_3_ - l1, p_238625_2_ - l1 + 1, p_238625_3_ + l1, -16772609);
            O_1280_J.fill(p_238625_0_, p_238625_2_ + l1 - 1, p_238625_3_ - l1, p_238625_2_ + l1, p_238625_3_ + l1, -16772609);
            O_1280_J.fill(p_238625_0_, p_238625_2_ - l1, p_238625_3_ - l1, p_238625_2_ + l1, p_238625_3_ - l1 + 1, -16772609);
            O_1280_J.fill(p_238625_0_, p_238625_2_ - l1, p_238625_3_ + l1 - 1, p_238625_2_ + l1, p_238625_3_ + l1, -16772609);
        }
        for (int j2 = 0; j2 < l; ++j2) {
            for (int k2 = 0; k2 < l; ++k2) {
                ChunkStatus chunkstatus = p_238625_1_.n_1700_B(j2, k2);
                int l2 = j1 + j2 * i;
                int i3 = k1 + k2 * i;
                O_1280_J.fill(p_238625_0_, l2, i3, l2 + p_238625_4_, i3 + p_238625_4_, R_4764_Y.getInt((Object)chunkstatus) | 0xFF000000);
            }
        }
    }
}


