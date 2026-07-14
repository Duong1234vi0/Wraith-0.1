/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.mojang.datafixers.DataFixer
 *  com.mojang.datafixers.util.Function4
 *  it.unimi.dsi.fastutil.booleans.BooleanConsumer
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenCustomHashMap
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Function4;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenCustomHashMap;
import javax.annotation.Nullable;
import lightning.product.B_4315_y;
import lightning.product.F_2904_S;
import lightning.product.ResourceManager;
import lightning.product.U_3339_M;
import lightning.product.Button;
import lightning.product.b_2971_z;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.f_2392_k;
import lightning.product.g_221_o;
import lightning.product.DataPackConfig;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.WorldData;
import lightning.product.CommonComponents;
import lightning.product.r_4097_j;
import lightning.product.u_530_F;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class G_3105_A
extends k_2603_m {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Object2IntMap<f_2392_k<b_4507_u>> J_1907_R = (Object2IntMap)j_3341_s.n_1700_B(new Object2IntOpenCustomHashMap(j_3341_s.u_2550_I()), p_212346_0_ -> {
        p_212346_0_.put(b_4507_u.u_1723_Y, -13408734);
        p_212346_0_.put(b_4507_u.v_4262_N, -10075085);
        p_212346_0_.put(b_4507_u.w_1484_f, -8943531);
        p_212346_0_.defaultReturnValue(-2236963);
    });
    private final BooleanConsumer R_4764_Y;
    private final U_3339_M G_564_y;

    @Nullable
    public static G_3105_A n_1700_B(MinecraftClient p_239025_0_, BooleanConsumer p_239025_1_, DataFixer p_239025_2_, b_2971_z.n_1700_B p_239025_3_, boolean p_239025_4_) {
        r_4097_j.J_1907_R dynamicregistries$impl = r_4097_j.J_1907_R();
        MinecraftClient.n_1700_B minecraft$packmanager = p_239025_0_.n_1700_B(dynamicregistries$impl, MinecraftClient::n_1700_B, (Function4<b_2971_z.n_1700_B, r_4097_j.J_1907_R, ResourceManager, DataPackConfig, WorldData>)((Function4)MinecraftClient::n_1700_B), false, p_239025_3_);
        try {
            WorldData iserverconfiguration = minecraft$packmanager.R_4764_Y();
            p_239025_3_.n_1700_B(dynamicregistries$impl, iserverconfiguration);
            ImmutableSet<f_2392_k<b_4507_u>> immutableset = iserverconfiguration.e_4240_b().u_1723_Y();
            G_3105_A g_3105_A = new G_3105_A(p_239025_1_, p_239025_2_, p_239025_3_, iserverconfiguration.A_4115_X(), p_239025_4_, immutableset);
            if (minecraft$packmanager != null) {
                minecraft$packmanager.close();
            }
            return g_3105_A;
        }
        catch (Throwable throwable) {
            try {
                if (minecraft$packmanager != null) {
                    try {
                        minecraft$packmanager.close();
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                }
                throw throwable;
            }
            catch (Exception exception) {
                n_1700_B.warn("Failed to load datapacks, can't optimize world", (Throwable)exception);
                return null;
            }
        }
    }

    private G_3105_A(BooleanConsumer p_i232319_1_, DataFixer p_i232319_2_, b_2971_z.n_1700_B p_i232319_3_, B_4315_y p_i232319_4_, boolean p_i232319_5_, ImmutableSet<f_2392_k<b_4507_u>> p_i232319_6_) {
        super(new F_2904_S("optimizeWorld.title", p_i232319_4_.n_1700_B()));
        this.R_4764_Y = p_i232319_1_;
        this.G_564_y = new U_3339_M(p_i232319_3_, p_i232319_2_, p_i232319_6_, p_i232319_5_);
    }

    @Override
    protected void init() {
        super.init();
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 150, 200, 20, CommonComponents.G_564_y, p_214331_1_ -> {
            this.G_564_y.n_1700_B();
            this.R_4764_Y.accept(false);
        }));
    }

    @Override
    public void tick() {
        if (this.G_564_y.J_1907_R()) {
            this.R_4764_Y.accept(true);
        }
    }

    @Override
    public void closeScreen() {
        this.R_4764_Y.accept(false);
    }

    @Override
    public void onClose() {
        this.G_564_y.n_1700_B();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        G_3105_A.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        int i = this.width / 2 - 150;
        int j = this.width / 2 + 150;
        int k = this.height / 4 + 100;
        int l = k + 10;
        G_3105_A.drawCenteredString(matrixStack, this.font, this.G_564_y.w_1484_f(), this.width / 2, k - 9 - 2, 0xA0A0A0);
        if (this.G_564_y.P_1922_E() > 0) {
            G_3105_A.fill(matrixStack, i - 1, k - 1, j + 1, l + 1, -16777216);
            G_3105_A.drawString(matrixStack, this.font, new F_2904_S("optimizeWorld.info.converted", this.G_564_y.u_1723_Y()), i, 40, 0xA0A0A0);
            G_3105_A.drawString(matrixStack, this.font, new F_2904_S("optimizeWorld.info.skipped", this.G_564_y.v_4262_N()), i, 52, 0xA0A0A0);
            G_3105_A.drawString(matrixStack, this.font, new F_2904_S("optimizeWorld.info.total", this.G_564_y.P_1922_E()), i, 64, 0xA0A0A0);
            int i1 = 0;
            for (f_2392_k registrykey : this.G_564_y.R_4764_Y()) {
                int j1 = u_530_F.G_564_y(this.G_564_y.n_1700_B(registrykey) * (float)(j - i));
                G_3105_A.fill(matrixStack, i + i1, k, i + i1 + j1, l, J_1907_R.getInt((Object)registrykey));
                i1 += j1;
            }
            int k1 = this.G_564_y.u_1723_Y() + this.G_564_y.v_4262_N();
            G_3105_A.drawCenteredString(matrixStack, this.font, k1 + " / " + this.G_564_y.P_1922_E(), this.width / 2, k + 18 + 2, 0xA0A0A0);
            G_3105_A.drawCenteredString(matrixStack, this.font, u_530_F.G_564_y(this.G_564_y.G_564_y() * 100.0f) + "%", this.width / 2, k + (l - k) / 2 - 4, 0xA0A0A0);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}



