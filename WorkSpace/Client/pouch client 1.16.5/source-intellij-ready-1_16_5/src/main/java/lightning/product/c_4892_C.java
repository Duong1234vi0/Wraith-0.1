/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Splitter
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.F_2904_S;
import lightning.product.StructureFeature;
import lightning.product.G_212_i;
import lightning.product.I_1084_e;
import lightning.product.ObjectSelectionList;
import lightning.product.O_694_j;
import lightning.product.StructureSettings;
import lightning.product.T_2915_h;
import lightning.product.T_3851_R;
import lightning.product.biomeBiomes;
import lightning.product.V_3137_a;
import lightning.product.Button;
import lightning.product.V_4739_Y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.f_2392_k;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.k_594_Q;
import lightning.product.WritableRegistry;
import lightning.product.o_2488_o;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.CommonComponents;
import lightning.product.Items;
import lightning.product.x_282_a;
import lightning.product.z_2376_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class c_4892_C
extends k_2603_m {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final List<n_1700_B> J_1907_R = Lists.newArrayList();
    private final G_212_i R_4764_Y;
    private x_282_a G_564_y;
    private x_282_a P_1922_E;
    private J_1907_R u_1723_Y;
    private Button v_4262_N;
    private O_694_j w_1484_f;
    private z_2376_a t_148_a;

    public c_4892_C(G_212_i parent) {
        super(new F_2904_S("createWorld.customize.presets.title"));
        this.R_4764_Y = parent;
    }

    @Nullable
    private static T_3851_R n_1700_B(String p_238638_0_, int p_238638_1_) {
        T_2915_h block;
        int i;
        String[] astring = p_238638_0_.split("\\*", 2);
        if (astring.length == 2) {
            try {
                i = Math.max(Integer.parseInt(astring[0]), 0);
            }
            catch (NumberFormatException numberformatexception) {
                n_1700_B.error("Error while parsing flat world string => {}", (Object)numberformatexception.getMessage());
                return null;
            }
        } else {
            i = 1;
        }
        int j = Math.min(p_238638_1_ + i, 256);
        int k = j - p_238638_1_;
        String s = astring[astring.length - 1];
        try {
            block = V_3137_a.q_4610_l.J_1907_R(new g_2336_b(s)).orElse(null);
        }
        catch (Exception exception) {
            n_1700_B.error("Error while parsing flat world string => {}", (Object)exception.getMessage());
            return null;
        }
        if (block == null) {
            n_1700_B.error("Error while parsing flat world string => Unknown block, {}", (Object)s);
            return null;
        }
        T_3851_R flatlayerinfo = new T_3851_R(k, block);
        flatlayerinfo.n_1700_B(p_238638_1_);
        return flatlayerinfo;
    }

    private static List<T_3851_R> n_1700_B(String p_238637_0_) {
        ArrayList list = Lists.newArrayList();
        String[] astring = p_238637_0_.split(",");
        int i = 0;
        for (String s : astring) {
            T_3851_R flatlayerinfo = c_4892_C.n_1700_B(s, i);
            if (flatlayerinfo == null) {
                return Collections.emptyList();
            }
            list.add(flatlayerinfo);
            i += flatlayerinfo.n_1700_B();
        }
        return list;
    }

    public static z_2376_a n_1700_B(V_3137_a<k_594_Q> p_243299_0_, String p_243299_1_, z_2376_a p_243299_2_) {
        Iterator iterator = Splitter.on((char)';').split((CharSequence)p_243299_1_).iterator();
        if (!iterator.hasNext()) {
            return z_2376_a.n_1700_B(p_243299_0_);
        }
        List<T_3851_R> list = c_4892_C.n_1700_B((String)iterator.next());
        if (list.isEmpty()) {
            return z_2376_a.n_1700_B(p_243299_0_);
        }
        z_2376_a flatgenerationsettings = p_243299_2_.n_1700_B(list, p_243299_2_.G_564_y());
        f_2392_k<k_594_Q> registrykey = biomeBiomes.J_1907_R;
        if (iterator.hasNext()) {
            try {
                g_2336_b resourcelocation = new g_2336_b((String)iterator.next());
                registrykey = f_2392_k.n_1700_B(V_3137_a.PlayerInfo, resourcelocation);
                p_243299_0_.J_1907_R(registrykey).orElseThrow(() -> new IllegalArgumentException("Invalid Biome: " + String.valueOf(resourcelocation)));
            }
            catch (Exception exception) {
                n_1700_B.error("Error while parsing flat world string => {}", (Object)exception.getMessage());
            }
        }
        f_2392_k<k_594_Q> registrykey1 = registrykey;
        flatgenerationsettings.n_1700_B(() -> (k_594_Q)p_243299_0_.R_4764_Y(registrykey1));
        return flatgenerationsettings;
    }

    private static String n_1700_B(V_3137_a<k_594_Q> p_243303_0_, z_2376_a p_243303_1_) {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < p_243303_1_.u_1723_Y().size(); ++i) {
            if (i > 0) {
                stringbuilder.append(",");
            }
            stringbuilder.append(p_243303_1_.u_1723_Y().get(i));
        }
        stringbuilder.append(";");
        stringbuilder.append(p_243303_0_.J_1907_R(p_243303_1_.P_1922_E()));
        return stringbuilder.toString();
    }

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.G_564_y = new F_2904_S("createWorld.customize.presets.share");
        this.P_1922_E = new F_2904_S("createWorld.customize.presets.list");
        this.w_1484_f = new O_694_j(this.font, 50, 40, this.width - 100, 20, this.G_564_y);
        this.w_1484_f.setMaxStringLength(1230);
        WritableRegistry<k_594_Q> registry = this.R_4764_Y.n_1700_B.R_4764_Y.J_1907_R().J_1907_R(V_3137_a.PlayerInfo);
        this.w_1484_f.setText(c_4892_C.n_1700_B(registry, this.R_4764_Y.n_1700_B()));
        this.t_148_a = this.R_4764_Y.n_1700_B();
        this.children.add(this.w_1484_f);
        this.u_1723_Y = new J_1907_R();
        this.children.add(this.u_1723_Y);
        this.v_4262_N = this.addButton(new Button(this.width / 2 - 155, this.height - 28, 150, 20, new F_2904_S("createWorld.customize.presets.select"), p_243298_2_ -> {
            z_2376_a flatgenerationsettings = c_4892_C.n_1700_B(registry, this.w_1484_f.getText(), this.t_148_a);
            this.R_4764_Y.n_1700_B(flatgenerationsettings);
            this.minecraft.n_1700_B(this.R_4764_Y);
        }));
        this.addButton(new Button(this.width / 2 + 5, this.height - 28, 150, 20, CommonComponents.G_564_y, p_243294_1_ -> this.minecraft.n_1700_B(this.R_4764_Y)));
        this.n_1700_B(this.u_1723_Y.getSelected() != null);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        return this.u_1723_Y.mouseScrolled(mouseX, mouseY, delta);
    }

    @Override
    public void resize(MinecraftClient minecraft, int width, int height) {
        String s = this.w_1484_f.getText();
        this.init(minecraft, width, height);
        this.w_1484_f.setText(s);
    }

    @Override
    public void closeScreen() {
        this.minecraft.n_1700_B(this.R_4764_Y);
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.u_1723_Y.render(matrixStack, mouseX, mouseY, partialTicks);
        c_4037_x.v_4276_D();
        c_4037_x.R_4764_Y(0.0f, 0.0f, 400.0f);
        c_4892_C.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 8, 0xFFFFFF);
        c_4892_C.drawString(matrixStack, this.font, this.G_564_y, 50, 30, 0xA0A0A0);
        c_4892_C.drawString(matrixStack, this.font, this.P_1922_E, 50, 70, 0xA0A0A0);
        c_4037_x.d_2461_k();
        this.w_1484_f.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void tick() {
        this.w_1484_f.tick();
        super.tick();
    }

    public void n_1700_B(boolean p_213074_1_) {
        this.v_4262_N.active = p_213074_1_ || this.w_1484_f.getText().length() > 1;
    }

    private static void n_1700_B(x_282_a p_238640_0_, q_1803_e p_238640_1_, f_2392_k<k_594_Q> p_238640_2_, List<StructureFeature<?>> p_238640_3_, boolean p_238640_4_, boolean p_238640_5_, boolean p_238640_6_, T_3851_R ... p_238640_7_) {
        J_1907_R.add(new n_1700_B(p_238640_1_.u_1723_Y(), p_238640_0_, p_243301_6_ -> {
            HashMap map = Maps.newHashMap();
            for (StructureFeature structure : p_238640_3_) {
                map.put(structure, (V_4739_Y)StructureSettings.J_1907_R.get((Object)structure));
            }
            StructureSettings dimensionstructuressettings = new StructureSettings(p_238640_4_ ? Optional.of(StructureSettings.R_4764_Y) : Optional.empty(), map);
            z_2376_a flatgenerationsettings = new z_2376_a(dimensionstructuressettings, (V_3137_a<k_594_Q>)p_243301_6_);
            if (p_238640_5_) {
                flatgenerationsettings.n_1700_B();
            }
            if (p_238640_6_) {
                flatgenerationsettings.J_1907_R();
            }
            for (int i = p_238640_7_.length - 1; i >= 0; --i) {
                flatgenerationsettings.u_1723_Y().add(p_238640_7_[i]);
            }
            flatgenerationsettings.n_1700_B(() -> (k_594_Q)p_243301_6_.R_4764_Y(p_238640_2_));
            flatgenerationsettings.w_1484_f();
            return flatgenerationsettings.n_1700_B(dimensionstructuressettings);
        }));
    }

    static {
        c_4892_C.n_1700_B(new F_2904_S("createWorld.customize.preset.classic_flat"), a_3742_W.t_148_a, biomeBiomes.J_1907_R, Arrays.asList(StructureFeature.t_1786_h), false, false, false, new T_3851_R(1, a_3742_W.t_148_a), new T_3851_R(2, a_3742_W.s_956_w), new T_3851_R(1, a_3742_W.Z_875_P));
        c_4892_C.n_1700_B(new F_2904_S("createWorld.customize.preset.tunnelers_dream"), a_3742_W.J_1907_R, biomeBiomes.G_564_y, Arrays.asList(StructureFeature.R_4764_Y), true, true, false, new T_3851_R(1, a_3742_W.t_148_a), new T_3851_R(5, a_3742_W.s_956_w), new T_3851_R(230, a_3742_W.J_1907_R), new T_3851_R(1, a_3742_W.Z_875_P));
        c_4892_C.n_1700_B(new F_2904_S("createWorld.customize.preset.water_world"), Items.W_2770_z, biomeBiomes.q_2307_F, Arrays.asList(StructureFeature.P_4830_p, StructureFeature.t_148_a, StructureFeature.M_588_G), false, false, false, new T_3851_R(90, a_3742_W.c_3005_b), new T_3851_R(5, a_3742_W.A_4115_X), new T_3851_R(5, a_3742_W.s_956_w), new T_3851_R(5, a_3742_W.J_1907_R), new T_3851_R(1, a_3742_W.Z_875_P));
        c_4892_C.n_1700_B(new F_2904_S("createWorld.customize.preset.overworld"), a_3742_W.u_744_e, biomeBiomes.J_1907_R, Arrays.asList(StructureFeature.t_1786_h, StructureFeature.R_4764_Y, StructureFeature.J_1907_R, StructureFeature.w_1484_f), true, true, true, new T_3851_R(1, a_3742_W.t_148_a), new T_3851_R(3, a_3742_W.s_956_w), new T_3851_R(59, a_3742_W.J_1907_R), new T_3851_R(1, a_3742_W.Z_875_P));
        c_4892_C.n_1700_B(new F_2904_S("createWorld.customize.preset.snowy_kingdom"), a_3742_W.X_290_I, biomeBiomes.P_4830_p, Arrays.asList(StructureFeature.t_1786_h, StructureFeature.v_4262_N), false, false, false, new T_3851_R(1, a_3742_W.X_290_I), new T_3851_R(1, a_3742_W.t_148_a), new T_3851_R(3, a_3742_W.s_956_w), new T_3851_R(59, a_3742_W.J_1907_R), new T_3851_R(1, a_3742_W.Z_875_P));
        c_4892_C.n_1700_B(new F_2904_S("createWorld.customize.preset.bottomless_pit"), Items.H_274_C, biomeBiomes.J_1907_R, Arrays.asList(StructureFeature.t_1786_h), false, false, false, new T_3851_R(1, a_3742_W.t_148_a), new T_3851_R(3, a_3742_W.s_956_w), new T_3851_R(2, a_3742_W.P_4830_p));
        c_4892_C.n_1700_B(new F_2904_S("createWorld.customize.preset.desert"), a_3742_W.A_4115_X, biomeBiomes.R_4764_Y, Arrays.asList(StructureFeature.t_1786_h, StructureFeature.u_1723_Y, StructureFeature.R_4764_Y), true, true, false, new T_3851_R(8, a_3742_W.A_4115_X), new T_3851_R(52, a_3742_W.h_4320_q), new T_3851_R(3, a_3742_W.J_1907_R), new T_3851_R(1, a_3742_W.Z_875_P));
        c_4892_C.n_1700_B(new F_2904_S("createWorld.customize.preset.redstone_ready"), Items.v_570_f, biomeBiomes.R_4764_Y, Collections.emptyList(), false, false, false, new T_3851_R(52, a_3742_W.h_4320_q), new T_3851_R(3, a_3742_W.J_1907_R), new T_3851_R(1, a_3742_W.Z_875_P));
        c_4892_C.n_1700_B(new F_2904_S("createWorld.customize.preset.the_void"), a_3742_W.N_4890_q, biomeBiomes.g_2268_R, Collections.emptyList(), false, true, false, new T_3851_R(1, a_3742_W.n_1700_B));
    }

    class J_1907_R
    extends ObjectSelectionList<n_1700_B> {
        public J_1907_R() {
            super(c_4892_C.this.minecraft, c_4892_C.this.width, c_4892_C.this.height, 80, c_4892_C.this.height - 37, 24);
            for (int i = 0; i < J_1907_R.size(); ++i) {
                this.addEntry(new n_1700_B());
            }
        }

        public void n_1700_B(@Nullable n_1700_B entry) {
            super.setSelected(entry);
            if (entry != null) {
                I_1084_e.J_1907_R.n_1700_B(new F_2904_S("narrator.select", J_1907_R.get(this.getEventListeners().indexOf(entry)).n_1700_B()).getString());
            }
            c_4892_C.this.n_1700_B(entry != null);
        }

        @Override
        protected boolean isFocused() {
            return c_4892_C.this.getListener() == this;
        }

        @Override
        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            if (super.keyPressed(keyCode, scanCode, modifiers)) {
                return true;
            }
            if ((keyCode == 257 || keyCode == 335) && this.getSelected() != null) {
                ((n_1700_B)this.getSelected()).n_1700_B();
            }
            return false;
        }

        @Override
        public /* synthetic */ void setSelected(@Nullable o_2488_o.n_1700_B n_1700_B2) {
            this.n_1700_B((n_1700_B)n_1700_B2);
        }

        public class n_1700_B
        extends ObjectSelectionList.n_1700_B<n_1700_B> {
            @Override
            public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
                lightning.product.c_4892_C$n_1700_B flatpresetsscreen$layeritem = J_1907_R.get(p_230432_2_);
                this.n_1700_B(p_230432_1_, p_230432_4_, p_230432_3_, flatpresetsscreen$layeritem.n_1700_B);
                c_4892_C.this.font.J_1907_R(p_230432_1_, flatpresetsscreen$layeritem.J_1907_R, (float)(p_230432_4_ + 18 + 5), (float)(p_230432_3_ + 6), 0xFFFFFF);
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                if (button == 0) {
                    this.n_1700_B();
                }
                return false;
            }

            private void n_1700_B() {
                J_1907_R.this.n_1700_B(this);
                lightning.product.c_4892_C$n_1700_B flatpresetsscreen$layeritem = J_1907_R.get(J_1907_R.this.getEventListeners().indexOf(this));
                WritableRegistry<k_594_Q> registry = c_4892_C.this.R_4764_Y.n_1700_B.R_4764_Y.J_1907_R().J_1907_R(V_3137_a.PlayerInfo);
                c_4892_C.this.t_148_a = flatpresetsscreen$layeritem.R_4764_Y.apply(registry);
                c_4892_C.this.w_1484_f.setText(c_4892_C.n_1700_B(registry, c_4892_C.this.t_148_a));
                c_4892_C.this.w_1484_f.setCursorPositionZero();
            }

            private void n_1700_B(g_221_o p_238647_1_, int p_238647_2_, int p_238647_3_, q_1613_l p_238647_4_) {
                this.n_1700_B(p_238647_1_, p_238647_2_ + 1, p_238647_3_ + 1);
                c_4037_x.n_3318_d();
                c_4892_C.this.itemRenderer.n_1700_B(new Z_1993_T(p_238647_4_), p_238647_2_ + 2, p_238647_3_ + 2);
                c_4037_x.d_2427_y();
            }

            private void n_1700_B(g_221_o p_238646_1_, int p_238646_2_, int p_238646_3_) {
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                J_1907_R.this.minecraft.G_624_v().n_1700_B(C_2701_A.STATS_ICON_LOCATION);
                C_2701_A.blit(p_238646_1_, p_238646_2_, p_238646_3_, c_4892_C.this.getBlitOffset(), 0.0f, 0.0f, 18, 18, 128, 128);
            }
        }
    }

    static class n_1700_B {
        public final q_1613_l n_1700_B;
        public final x_282_a J_1907_R;
        public final Function<V_3137_a<k_594_Q>, z_2376_a> R_4764_Y;

        public n_1700_B(q_1613_l p_i242057_1_, x_282_a p_i242057_2_, Function<V_3137_a<k_594_Q>, z_2376_a> p_i242057_3_) {
            this.n_1700_B = p_i242057_1_;
            this.J_1907_R = p_i242057_2_;
            this.R_4764_Y = p_i242057_3_;
        }

        public x_282_a n_1700_B() {
            return this.J_1907_R;
        }
    }
}



