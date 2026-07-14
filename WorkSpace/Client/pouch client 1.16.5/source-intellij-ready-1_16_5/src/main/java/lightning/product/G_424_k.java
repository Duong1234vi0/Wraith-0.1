/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Either
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Either;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.D_4024_W;
import lightning.product.TextRenderingUtils;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.H_3272_P;
import lightning.product.K_1289_S;
import lightning.product.ObjectSelectionList;
import lightning.product.S_4022_R;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.RealmsScreen;
import lightning.product.j_3341_s;
import lightning.product.l_4537_E;
import lightning.product.o_2488_o;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.RealmsScreenWithCallback;
import lightning.product.NarrationHelper;
import lightning.product.u_744_e;
import lightning.product.x_282_a;
import lightning.product.y_2772_m;
import lightning.product.z_3470_q;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class G_424_k
extends RealmsScreen {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final g_2336_b J_1907_R = new g_2336_b("realms", "textures/gui/realms/link_icons.png");
    private static final g_2336_b R_4764_Y = new g_2336_b("realms", "textures/gui/realms/trailer_icons.png");
    private static final g_2336_b G_564_y = new g_2336_b("realms", "textures/gui/realms/slot_frame.png");
    private static final x_282_a P_1922_E = new F_2904_S("mco.template.info.tooltip");
    private static final x_282_a u_1723_Y = new F_2904_S("mco.template.trailer.tooltip");
    private final RealmsScreenWithCallback v_4262_N;
    private J_1907_R w_1484_f;
    private int t_148_a = -1;
    private x_282_a s_956_w;
    private Button u_2550_I;
    private Button M_588_G;
    private Button P_4830_p;
    @Nullable
    private x_282_a h_1847_R;
    private String Q_4569_t;
    private final q_1982_R.J_1907_R M_182_A;
    private int t_1786_h;
    @Nullable
    private x_282_a[] multiplayerClientSuggestionProvider;
    private String w_1457_N;
    private boolean Y_601_j;
    private boolean Y_259_p;
    @Nullable
    private List<TextRenderingUtils.n_1700_B> Q_2552_b;

    public G_424_k(RealmsScreenWithCallback p_i51752_1_, q_1982_R.J_1907_R p_i51752_2_) {
        this(p_i51752_1_, p_i51752_2_, null);
    }

    public G_424_k(RealmsScreenWithCallback p_i51753_1_, q_1982_R.J_1907_R p_i51753_2_, @Nullable l_4537_E p_i51753_3_) {
        this.v_4262_N = p_i51753_1_;
        this.M_182_A = p_i51753_2_;
        if (p_i51753_3_ == null) {
            this.w_1484_f = new J_1907_R();
            this.n_1700_B(new l_4537_E(10));
        } else {
            this.w_1484_f = new J_1907_R(Lists.newArrayList(p_i51753_3_.n_1700_B));
            this.n_1700_B(p_i51753_3_);
        }
        this.s_956_w = new F_2904_S("mco.template.title");
    }

    public void n_1700_B(x_282_a p_238001_1_) {
        this.s_956_w = p_238001_1_;
    }

    public void n_1700_B(x_282_a ... p_238002_1_) {
        this.multiplayerClientSuggestionProvider = p_238002_1_;
        this.Y_601_j = true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.Y_259_p && this.w_1457_N != null) {
            j_3341_s.t_148_a().n_1700_B("https://www.minecraft.net/realms/adventure-maps-in-1-9");
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.w_1484_f = new J_1907_R(this.w_1484_f.R_4764_Y());
        this.M_588_G = this.addButton(new Button(this.width / 2 - 206, this.height - 32, 100, 20, new F_2904_S("mco.template.button.trailer"), p_238011_1_ -> this.s_956_w()));
        this.u_2550_I = this.addButton(new Button(this.width / 2 - 100, this.height - 32, 100, 20, new F_2904_S("mco.template.button.select"), p_238008_1_ -> this.w_1484_f()));
        x_282_a itextcomponent = this.M_182_A == q_1982_R.J_1907_R.J_1907_R ? CommonComponents.G_564_y : CommonComponents.w_1484_f;
        Button button = new Button(this.width / 2 + 6, this.height - 32, 100, 20, itextcomponent, p_238006_1_ -> this.v_4262_N());
        this.addButton(button);
        this.P_4830_p = this.addButton(new Button(this.width / 2 + 112, this.height - 32, 100, 20, new F_2904_S("mco.template.button.publisher"), p_238000_1_ -> this.u_2550_I()));
        this.u_2550_I.active = false;
        this.M_588_G.visible = false;
        this.P_4830_p.visible = false;
        this.addListener(this.w_1484_f);
        this.J_1907_R(this.w_1484_f);
        Stream<x_282_a> stream = Stream.of(this.s_956_w);
        if (this.multiplayerClientSuggestionProvider != null) {
            stream = Stream.concat(Stream.of(this.multiplayerClientSuggestionProvider), stream);
        }
        NarrationHelper.n_1700_B(stream.filter(Objects::nonNull).map(x_282_a::getString).collect(Collectors.toList()));
    }

    private void n_1700_B() {
        this.P_4830_p.visible = this.R_4764_Y();
        this.M_588_G.visible = this.u_1723_Y();
        this.u_2550_I.active = this.J_1907_R();
    }

    private boolean J_1907_R() {
        return this.t_148_a != -1;
    }

    private boolean R_4764_Y() {
        return this.t_148_a != -1 && !this.G_564_y().P_1922_E.isEmpty();
    }

    private S_4022_R G_564_y() {
        return this.w_1484_f.J_1907_R(this.t_148_a);
    }

    private boolean u_1723_Y() {
        return this.t_148_a != -1 && !this.G_564_y().v_4262_N.isEmpty();
    }

    @Override
    public void tick() {
        super.tick();
        --this.t_1786_h;
        if (this.t_1786_h < 0) {
            this.t_1786_h = 0;
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.v_4262_N();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void v_4262_N() {
        this.v_4262_N.n_1700_B((S_4022_R)null);
        this.minecraft.n_1700_B(this.v_4262_N);
    }

    private void w_1484_f() {
        if (this.t_148_a()) {
            this.v_4262_N.n_1700_B(this.G_564_y());
        }
    }

    private boolean t_148_a() {
        return this.t_148_a >= 0 && this.t_148_a < this.w_1484_f.getItemCount();
    }

    private void s_956_w() {
        if (this.t_148_a()) {
            S_4022_R worldtemplate = this.G_564_y();
            if (!"".equals(worldtemplate.v_4262_N)) {
                j_3341_s.t_148_a().n_1700_B(worldtemplate.v_4262_N);
            }
        }
    }

    private void u_2550_I() {
        if (this.t_148_a()) {
            S_4022_R worldtemplate = this.G_564_y();
            if (!"".equals(worldtemplate.P_1922_E)) {
                j_3341_s.t_148_a().n_1700_B(worldtemplate.P_1922_E);
            }
        }
    }

    private void n_1700_B(final l_4537_E p_224497_1_) {
        new Thread("realms-template-fetcher"){

            @Override
            public void run() {
                l_4537_E worldtemplatepaginatedlist = p_224497_1_;
                p_178_J realmsclient = p_178_J.n_1700_B();
                while (worldtemplatepaginatedlist != null) {
                    Either<l_4537_E, String> either = G_424_k.this.n_1700_B(worldtemplatepaginatedlist, realmsclient);
                    worldtemplatepaginatedlist = ((H_3272_P)G_424_k.this.minecraft).n_1700_B(() -> {
                        if (either.right().isPresent()) {
                            n_1700_B.error("Couldn't fetch templates: {}", either.right().get());
                            if (G_424_k.this.w_1484_f.J_1907_R()) {
                                G_424_k.this.Q_2552_b = TextRenderingUtils.n_1700_B(K_1289_S.n_1700_B("mco.template.select.failure", new Object[0]), new TextRenderingUtils.J_1907_R[0]);
                            }
                            return null;
                        }
                        l_4537_E worldtemplatepaginatedlist1 = (l_4537_E)either.left().get();
                        for (S_4022_R worldtemplate : worldtemplatepaginatedlist1.n_1700_B) {
                            G_424_k.this.w_1484_f.n_1700_B(worldtemplate);
                        }
                        if (worldtemplatepaginatedlist1.n_1700_B.isEmpty()) {
                            if (G_424_k.this.w_1484_f.J_1907_R()) {
                                String s = K_1289_S.n_1700_B("mco.template.select.none", "%link");
                                TextRenderingUtils.J_1907_R textrenderingutils$linesegment = TextRenderingUtils.J_1907_R.n_1700_B(K_1289_S.n_1700_B("mco.template.select.none.linkTitle", new Object[0]), "https://aka.ms/MinecraftRealmsContentCreator");
                                G_424_k.this.Q_2552_b = TextRenderingUtils.n_1700_B(s, textrenderingutils$linesegment);
                            }
                            return null;
                        }
                        return worldtemplatepaginatedlist1;
                    }).join();
                }
            }
        }.start();
    }

    private Either<l_4537_E, String> n_1700_B(l_4537_E p_224509_1_, p_178_J p_224509_2_) {
        try {
            return Either.left((Object)p_224509_2_.n_1700_B(p_224509_1_.J_1907_R + 1, p_224509_1_.R_4764_Y, this.M_182_A));
        }
        catch (u_744_e realmsserviceexception) {
            return Either.right((Object)realmsserviceexception.getMessage());
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.h_1847_R = null;
        this.Q_4569_t = null;
        this.Y_259_p = false;
        this.renderBackground(matrixStack);
        this.w_1484_f.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.Q_2552_b != null) {
            this.n_1700_B(matrixStack, mouseX, mouseY, this.Q_2552_b);
        }
        G_424_k.drawCenteredString(matrixStack, this.font, this.s_956_w, this.width / 2, 13, 0xFFFFFF);
        if (this.Y_601_j) {
            x_282_a[] aitextcomponent = this.multiplayerClientSuggestionProvider;
            for (int i = 0; i < aitextcomponent.length; ++i) {
                int j = this.font.n_1700_B((FormattedText)aitextcomponent[i]);
                int k = this.width / 2 - j / 2;
                int l = G_424_k.G_564_y(-1 + i);
                if (mouseX < k || mouseX > k + j || mouseY < l || mouseY > l + 9) continue;
                this.Y_259_p = true;
            }
            for (int i1 = 0; i1 < aitextcomponent.length; ++i1) {
                x_282_a itextcomponent = aitextcomponent[i1];
                int j1 = 0xA0A0A0;
                if (this.w_1457_N != null) {
                    if (this.Y_259_p) {
                        j1 = 7107012;
                        itextcomponent = itextcomponent.P_1922_E().n_1700_B(D_4024_W.w_1457_N);
                    } else {
                        j1 = 0x3366BB;
                    }
                }
                G_424_k.drawCenteredString(matrixStack, this.font, itextcomponent, this.width / 2, G_424_k.G_564_y(-1 + i1), j1);
            }
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.n_1700_B(matrixStack, this.h_1847_R, mouseX, mouseY);
    }

    private void n_1700_B(g_221_o p_237992_1_, int p_237992_2_, int p_237992_3_, List<TextRenderingUtils.n_1700_B> p_237992_4_) {
        for (int i = 0; i < p_237992_4_.size(); ++i) {
            TextRenderingUtils.n_1700_B textrenderingutils$line = p_237992_4_.get(i);
            int j = G_424_k.G_564_y(4 + i);
            int k = textrenderingutils$line.n_1700_B.stream().mapToInt(p_237999_1_ -> this.font.J_1907_R(p_237999_1_.n_1700_B())).sum();
            int l = this.width / 2 - k / 2;
            for (TextRenderingUtils.J_1907_R textrenderingutils$linesegment : textrenderingutils$line.n_1700_B) {
                int i1 = textrenderingutils$linesegment.J_1907_R() ? 0x3366BB : 0xFFFFFF;
                int j1 = this.font.n_1700_B(p_237992_1_, textrenderingutils$linesegment.n_1700_B(), (float)l, (float)j, i1);
                if (textrenderingutils$linesegment.J_1907_R() && p_237992_2_ > l && p_237992_2_ < j1 && p_237992_3_ > j - 3 && p_237992_3_ < j + 8) {
                    this.h_1847_R = new U_2871_b(textrenderingutils$linesegment.R_4764_Y());
                    this.Q_4569_t = textrenderingutils$linesegment.R_4764_Y();
                }
                l = j1;
            }
        }
    }

    protected void n_1700_B(g_221_o p_237993_1_, @Nullable x_282_a p_237993_2_, int p_237993_3_, int p_237993_4_) {
        if (p_237993_2_ != null) {
            int i = p_237993_3_ + 12;
            int j = p_237993_4_ - 12;
            int k = this.font.n_1700_B((FormattedText)p_237993_2_);
            G_424_k.fillGradient(p_237993_1_, i - 3, j - 3, i + k + 3, j + 8 + 3, -1073741824, -1073741824);
            this.font.n_1700_B(p_237993_1_, p_237993_2_, (float)i, (float)j, 0xFFFFFF);
        }
    }

    class J_1907_R
    extends z_3470_q<n_1700_B> {
        public J_1907_R() {
            this(Collections.emptyList());
        }

        public J_1907_R(Iterable<S_4022_R> p_i51726_2_) {
            super(G_424_k.this.width, G_424_k.this.height, G_424_k.this.Y_601_j ? G_424_k.G_564_y(1) : 32, G_424_k.this.height - 40, 46);
            p_i51726_2_.forEach(this::n_1700_B);
        }

        public void n_1700_B(S_4022_R p_223876_1_) {
            G_424_k g_424_k = G_424_k.this;
            Objects.requireNonNull(g_424_k);
            this.n_1700_B(g_424_k.new n_1700_B(p_223876_1_));
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if (button == 0 && mouseY >= (double)this.y0 && mouseY <= (double)this.y1) {
                int i = this.width / 2 - 150;
                if (G_424_k.this.Q_4569_t != null) {
                    j_3341_s.t_148_a().n_1700_B(G_424_k.this.Q_4569_t);
                }
                int j = (int)Math.floor(mouseY - (double)this.y0) - this.headerHeight + (int)this.getScrollAmount() - 4;
                int k = j / this.itemHeight;
                if (mouseX >= (double)i && mouseX < (double)this.getScrollbarPosition() && k >= 0 && j >= 0 && k < this.getItemCount()) {
                    this.n_1700_B(k);
                    this.n_1700_B(j, k, mouseX, mouseY, this.width);
                    if (k >= G_424_k.this.w_1484_f.getItemCount()) {
                        return super.mouseClicked(mouseX, mouseY, button);
                    }
                    G_424_k.this.t_1786_h += 7;
                    if (G_424_k.this.t_1786_h >= 10) {
                        G_424_k.this.w_1484_f();
                    }
                    return true;
                }
            }
            return super.mouseClicked(mouseX, mouseY, button);
        }

        @Override
        public void n_1700_B(int p_231400_1_) {
            this.G_564_y(p_231400_1_);
            if (p_231400_1_ != -1) {
                S_4022_R worldtemplate = G_424_k.this.w_1484_f.J_1907_R(p_231400_1_);
                String s = K_1289_S.n_1700_B("narrator.select.list.position", p_231400_1_ + 1, G_424_k.this.w_1484_f.getItemCount());
                String s1 = K_1289_S.n_1700_B("mco.template.select.narrate.version", worldtemplate.R_4764_Y);
                String s2 = K_1289_S.n_1700_B("mco.template.select.narrate.authors", worldtemplate.G_564_y);
                String s3 = NarrationHelper.J_1907_R(Arrays.asList(worldtemplate.J_1907_R, s2, worldtemplate.w_1484_f, s1, s));
                NarrationHelper.n_1700_B(K_1289_S.n_1700_B("narrator.select", s3));
            }
        }

        public void n_1700_B(@Nullable n_1700_B entry) {
            super.setSelected(entry);
            G_424_k.this.t_148_a = this.getEventListeners().indexOf(entry);
            G_424_k.this.n_1700_B();
        }

        @Override
        public int getMaxPosition() {
            return this.getItemCount() * 46;
        }

        @Override
        public int getRowWidth() {
            return 300;
        }

        @Override
        public void renderBackground(g_221_o p_230433_1_) {
            G_424_k.this.renderBackground(p_230433_1_);
        }

        @Override
        public boolean isFocused() {
            return G_424_k.this.getListener() == this;
        }

        public boolean J_1907_R() {
            return this.getItemCount() == 0;
        }

        public S_4022_R J_1907_R(int p_223877_1_) {
            return ((n_1700_B)this.getEventListeners().get((int)p_223877_1_)).J_1907_R;
        }

        public List<S_4022_R> R_4764_Y() {
            return this.getEventListeners().stream().map(p_223875_0_ -> p_223875_0_.J_1907_R).collect(Collectors.toList());
        }

        @Override
        public /* synthetic */ void setSelected(@Nullable o_2488_o.n_1700_B n_1700_B2) {
            this.n_1700_B((n_1700_B)n_1700_B2);
        }
    }

    class n_1700_B
    extends ObjectSelectionList.n_1700_B<n_1700_B> {
        private final S_4022_R J_1907_R;

        public n_1700_B(S_4022_R p_i51724_2_) {
            this.J_1907_R = p_i51724_2_;
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            this.n_1700_B(p_230432_1_, this.J_1907_R, p_230432_4_, p_230432_3_, p_230432_7_, p_230432_8_);
        }

        private void n_1700_B(g_221_o p_238029_1_, S_4022_R p_238029_2_, int p_238029_3_, int p_238029_4_, int p_238029_5_, int p_238029_6_) {
            int i = p_238029_3_ + 45 + 20;
            G_424_k.this.font.J_1907_R(p_238029_1_, p_238029_2_.J_1907_R, (float)i, (float)(p_238029_4_ + 2), 0xFFFFFF);
            G_424_k.this.font.J_1907_R(p_238029_1_, p_238029_2_.G_564_y, (float)i, (float)(p_238029_4_ + 15), 0x6C6C6C);
            G_424_k.this.font.J_1907_R(p_238029_1_, p_238029_2_.R_4764_Y, (float)(i + 227 - G_424_k.this.font.J_1907_R(p_238029_2_.R_4764_Y)), (float)(p_238029_4_ + 1), 0x6C6C6C);
            if (!("".equals(p_238029_2_.P_1922_E) && "".equals(p_238029_2_.v_4262_N) && "".equals(p_238029_2_.w_1484_f))) {
                this.n_1700_B(p_238029_1_, i - 1, p_238029_4_ + 25, p_238029_5_, p_238029_6_, p_238029_2_.P_1922_E, p_238029_2_.v_4262_N, p_238029_2_.w_1484_f);
            }
            this.n_1700_B(p_238029_1_, p_238029_3_, p_238029_4_ + 1, p_238029_5_, p_238029_6_, p_238029_2_);
        }

        private void n_1700_B(g_221_o p_238027_1_, int p_238027_2_, int p_238027_3_, int p_238027_4_, int p_238027_5_, S_4022_R p_238027_6_) {
            y_2772_m.n_1700_B(p_238027_6_.n_1700_B, p_238027_6_.u_1723_Y);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            C_2701_A.blit(p_238027_1_, p_238027_2_ + 1, p_238027_3_ + 1, 0.0f, 0.0f, 38, 38, 38, 38);
            G_424_k.this.minecraft.G_624_v().n_1700_B(G_564_y);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            C_2701_A.blit(p_238027_1_, p_238027_2_, p_238027_3_, 0.0f, 0.0f, 40, 40, 40, 40);
        }

        private void n_1700_B(g_221_o p_238028_1_, int p_238028_2_, int p_238028_3_, int p_238028_4_, int p_238028_5_, String p_238028_6_, String p_238028_7_, String p_238028_8_) {
            if (!"".equals(p_238028_8_)) {
                G_424_k.this.font.J_1907_R(p_238028_1_, p_238028_8_, (float)p_238028_2_, (float)(p_238028_3_ + 4), 0x4C4C4C);
            }
            int i = "".equals(p_238028_8_) ? 0 : G_424_k.this.font.J_1907_R(p_238028_8_) + 2;
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = "".equals(p_238028_6_);
            if (p_238028_4_ >= p_238028_2_ + i && p_238028_4_ <= p_238028_2_ + i + 32 && p_238028_5_ >= p_238028_3_ && p_238028_5_ <= p_238028_3_ + 15 && p_238028_5_ < G_424_k.this.height - 15 && p_238028_5_ > 32) {
                if (p_238028_4_ <= p_238028_2_ + 15 + i && p_238028_4_ > i) {
                    if (flag2) {
                        flag1 = true;
                    } else {
                        flag = true;
                    }
                } else if (!flag2) {
                    flag1 = true;
                }
            }
            if (!flag2) {
                G_424_k.this.minecraft.G_624_v().n_1700_B(J_1907_R);
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                c_4037_x.v_4276_D();
                c_4037_x.J_1907_R(1.0f, 1.0f, 1.0f);
                float f = flag ? 15.0f : 0.0f;
                C_2701_A.blit(p_238028_1_, p_238028_2_ + i, p_238028_3_, f, 0.0f, 15, 15, 30, 15);
                c_4037_x.d_2461_k();
            }
            if (!"".equals(p_238028_7_)) {
                G_424_k.this.minecraft.G_624_v().n_1700_B(R_4764_Y);
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                c_4037_x.v_4276_D();
                c_4037_x.J_1907_R(1.0f, 1.0f, 1.0f);
                int j = p_238028_2_ + i + (flag2 ? 0 : 17);
                float f1 = flag1 ? 15.0f : 0.0f;
                C_2701_A.blit(p_238028_1_, j, p_238028_3_, f1, 0.0f, 15, 15, 30, 15);
                c_4037_x.d_2461_k();
            }
            if (flag) {
                G_424_k.this.h_1847_R = P_1922_E;
                G_424_k.this.Q_4569_t = p_238028_6_;
            } else if (flag1 && !"".equals(p_238028_7_)) {
                G_424_k.this.h_1847_R = u_1723_Y;
                G_424_k.this.Q_4569_t = p_238028_7_;
            }
        }
    }
}


