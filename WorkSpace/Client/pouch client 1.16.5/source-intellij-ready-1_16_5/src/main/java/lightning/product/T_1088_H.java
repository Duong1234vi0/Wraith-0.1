/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.ints.IntOpenHashSet
 *  it.unimi.dsi.fastutil.ints.IntSet
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import lightning.product.C_2701_A;
import lightning.product.D_3318_r;
import lightning.product.D_4024_W;
import lightning.product.E_688_b;
import lightning.product.I_1084_e;
import lightning.product.Resource;
import lightning.product.U_2871_b;
import lightning.product.X_933_l;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.l_3747_P;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class T_1088_H
extends k_2603_m {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final g_2336_b J_1907_R = new g_2336_b("textures/gui/title/minecraft.png");
    private static final g_2336_b R_4764_Y = new g_2336_b("textures/gui/title/edition.png");
    private static final g_2336_b G_564_y = new g_2336_b("textures/misc/vignette.png");
    private static final String P_1922_E = String.valueOf((Object)D_4024_W.M_182_A) + String.valueOf((Object)D_4024_W.t_1786_h) + String.valueOf((Object)D_4024_W.u_2550_I) + String.valueOf((Object)D_4024_W.M_588_G);
    private final boolean u_1723_Y;
    private final Runnable v_4262_N;
    private float w_1484_f;
    private List<FormattedCharSequence> t_148_a;
    private IntSet s_956_w;
    private int u_2550_I;
    private float M_588_G = 0.5f;

    public T_1088_H(boolean poemIn, Runnable onFinishedIn) {
        super(I_1084_e.n_1700_B);
        this.u_1723_Y = poemIn;
        this.v_4262_N = onFinishedIn;
        if (!poemIn) {
            this.M_588_G = 0.75f;
        }
    }

    @Override
    public void tick() {
        this.minecraft.multiplayerClientSuggestionProvider().n_1700_B();
        this.minecraft.Z_976_R().n_1700_B(false);
        float f = (float)(this.u_2550_I + this.height + this.height + 24) / this.M_588_G;
        if (this.w_1484_f > f) {
            this.n_1700_B();
        }
    }

    @Override
    public void closeScreen() {
        this.n_1700_B();
    }

    private void n_1700_B() {
        this.v_4262_N.run();
        this.minecraft.n_1700_B((k_2603_m)null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected void init() {
        if (this.t_148_a == null) {
            this.t_148_a = Lists.newArrayList();
            this.s_956_w = new IntOpenHashSet();
            Resource iresource = null;
            try {
                String s3;
                int i = 274;
                if (this.u_1723_Y) {
                    Object s;
                    iresource = this.minecraft.T_2506_i().n_1700_B(new g_2336_b("texts/end.txt"));
                    InputStream inputstream = iresource.J_1907_R();
                    BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));
                    Random random = new Random(8124371L);
                    while ((s = bufferedreader.readLine()) != null) {
                        int j;
                        s = ((String)s).replaceAll("PLAYERNAME", this.minecraft.z_1737_N().R_4764_Y());
                        while ((j = ((String)s).indexOf(P_1922_E)) != -1) {
                            String s1 = ((String)s).substring(0, j);
                            String s2 = ((String)s).substring(j + P_1922_E.length());
                            s = s1 + String.valueOf((Object)D_4024_W.M_182_A) + String.valueOf((Object)D_4024_W.t_1786_h) + "XXXXXXXX".substring(0, random.nextInt(4) + 3) + s2;
                        }
                        this.t_148_a.addAll(this.minecraft.t_148_a.J_1907_R(new U_2871_b((String)s), 274));
                        this.t_148_a.add(FormattedCharSequence.n_1700_B);
                    }
                    inputstream.close();
                    for (int k = 0; k < 8; ++k) {
                        this.t_148_a.add(FormattedCharSequence.n_1700_B);
                    }
                }
                InputStream inputstream1 = this.minecraft.T_2506_i().n_1700_B(new g_2336_b("texts/credits.txt")).J_1907_R();
                BufferedReader bufferedreader1 = new BufferedReader(new InputStreamReader(inputstream1, StandardCharsets.UTF_8));
                while ((s3 = bufferedreader1.readLine()) != null) {
                    boolean flag;
                    s3 = s3.replaceAll("PLAYERNAME", this.minecraft.z_1737_N().R_4764_Y());
                    if ((s3 = s3.replaceAll("\t", "    ")).startsWith("[C]")) {
                        s3 = s3.substring(3);
                        flag = true;
                    } else {
                        flag = false;
                    }
                    for (FormattedCharSequence ireorderingprocessor : this.minecraft.t_148_a.J_1907_R(new U_2871_b(s3), 274)) {
                        if (flag) {
                            this.s_956_w.add(this.t_148_a.size());
                        }
                        this.t_148_a.add(ireorderingprocessor);
                    }
                    this.t_148_a.add(FormattedCharSequence.n_1700_B);
                }
                inputstream1.close();
                this.u_2550_I = this.t_148_a.size() * 12;
                IOUtils.closeQuietly((Closeable)iresource);
            }
            catch (Exception exception) {
                n_1700_B.error("Couldn't load credits", (Throwable)exception);
            }
            finally {
                IOUtils.closeQuietly(iresource);
            }
        }
    }

    private void n_1700_B(int mouseX, int mouseY, float partialTicks) {
        this.minecraft.G_624_v().n_1700_B(C_2701_A.BACKGROUND_LOCATION);
        int i = this.width;
        float f = -this.w_1484_f * 0.5f * this.M_588_G;
        float f1 = (float)this.height - this.w_1484_f * 0.5f * this.M_588_G;
        float f2 = 0.015625f;
        float f3 = this.w_1484_f * 0.02f;
        float f4 = (float)(this.u_2550_I + this.height + this.height + 24) / this.M_588_G;
        float f5 = (f4 - 20.0f - this.w_1484_f) * 0.005f;
        if (f5 < f3) {
            f3 = f5;
        }
        if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        f3 *= f3;
        f3 = f3 * 96.0f / 255.0f;
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
        bufferbuilder.pos(0.0, this.height, this.getBlitOffset()).tex(0.0f, f * 0.015625f).n_1700_B(f3, f3, f3, 1.0f).endVertex();
        bufferbuilder.pos(i, this.height, this.getBlitOffset()).tex((float)i * 0.015625f, f * 0.015625f).n_1700_B(f3, f3, f3, 1.0f).endVertex();
        bufferbuilder.pos(i, 0.0, this.getBlitOffset()).tex((float)i * 0.015625f, f1 * 0.015625f).n_1700_B(f3, f3, f3, 1.0f).endVertex();
        bufferbuilder.pos(0.0, 0.0, this.getBlitOffset()).tex(0.0f, f1 * 0.015625f).n_1700_B(f3, f3, f3, 1.0f).endVertex();
        tessellator.J_1907_R();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.n_1700_B(mouseX, mouseY, partialTicks);
        int i = 274;
        int j = this.width / 2 - 137;
        int k = this.height + 50;
        this.w_1484_f += partialTicks;
        float f = -this.w_1484_f * this.M_588_G;
        c_4037_x.v_4276_D();
        c_4037_x.R_4764_Y(0.0f, f, 0.0f);
        this.minecraft.G_624_v().n_1700_B(J_1907_R);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_4037_x.M_588_G();
        c_4037_x.Y_601_j();
        this.blitBlackOutline(j, k, (p_238665_2_, p_238665_3_) -> {
            this.blit(matrixStack, p_238665_2_ + 0, (int)p_238665_3_, 0, 0, 155, 44);
            this.blit(matrixStack, p_238665_2_ + 155, (int)p_238665_3_, 0, 45, 155, 44);
        });
        c_4037_x.Y_259_p();
        this.minecraft.G_624_v().n_1700_B(R_4764_Y);
        T_1088_H.blit(matrixStack, j + 88, k + 37, 0.0f, 0.0f, 98, 14, 128, 16);
        c_4037_x.u_2550_I();
        int l = k + 100;
        for (int i1 = 0; i1 < this.t_148_a.size(); ++i1) {
            float f1;
            if (i1 == this.t_148_a.size() - 1 && (f1 = (float)l + f - (float)(this.height / 2 - 6)) < 0.0f) {
                c_4037_x.R_4764_Y(0.0f, -f1, 0.0f);
            }
            if ((float)l + f + 12.0f + 8.0f > 0.0f && (float)l + f < (float)this.height) {
                FormattedCharSequence ireorderingprocessor = this.t_148_a.get(i1);
                if (this.s_956_w.contains(i1)) {
                    this.font.n_1700_B(matrixStack, ireorderingprocessor, (float)(j + (274 - this.font.n_1700_B(ireorderingprocessor)) / 2), (float)l, 0xFFFFFF);
                } else {
                    this.font.J_1907_R.setSeed((long)((float)((long)i1 * 4238972211L) + this.w_1484_f / 4.0f));
                    this.font.n_1700_B(matrixStack, ireorderingprocessor, (float)j, (float)l, 0xFFFFFF);
                }
            }
            l += 12;
        }
        c_4037_x.d_2461_k();
        this.minecraft.G_624_v().n_1700_B(G_564_y);
        c_4037_x.Y_601_j();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.Q_4569_t, X_933_l.s_956_w.u_2550_I);
        int j1 = this.width;
        int k1 = this.height;
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
        bufferbuilder.pos(0.0, k1, this.getBlitOffset()).tex(0.0f, 1.0f).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        bufferbuilder.pos(j1, k1, this.getBlitOffset()).tex(1.0f, 1.0f).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        bufferbuilder.pos(j1, 0.0, this.getBlitOffset()).tex(1.0f, 0.0f).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        bufferbuilder.pos(0.0, 0.0, this.getBlitOffset()).tex(0.0f, 0.0f).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        tessellator.J_1907_R();
        c_4037_x.Y_259_p();
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


