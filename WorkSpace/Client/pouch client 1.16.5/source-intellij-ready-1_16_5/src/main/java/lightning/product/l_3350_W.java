/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.H_2543_D;
import lightning.product.K_1289_S;
import lightning.product.ObjectSelectionList;
import lightning.product.StatsUpdateListener;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.SimpleSoundInstance;
import lightning.product.V_3137_a;
import lightning.product.Button;
import lightning.product.SoundEvents;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.StatsCounter;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.l_3747_P;
import lightning.product.o_98_P;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.CommonComponents;
import lightning.product.q_3277_O;
import lightning.product.Items;
import lightning.product.t_5_h;
import lightning.product.v_1669_V;
import lightning.product.x_282_a;

public class l_3350_W
extends k_2603_m
implements StatsUpdateListener {
    private static final x_282_a R_4764_Y = new F_2904_S("multiplayer.downloadingStats");
    protected final k_2603_m J_1907_R;
    private n_1700_B G_564_y;
    private R_4764_Y P_1922_E;
    private J_1907_R u_1723_Y;
    private final StatsCounter v_4262_N;
    @Nullable
    private ObjectSelectionList<?> w_1484_f;
    private boolean t_148_a = true;

    public l_3350_W(k_2603_m parent, StatsCounter manager) {
        super(new F_2904_S("gui.stats"));
        this.J_1907_R = parent;
        this.v_4262_N = manager;
    }

    @Override
    protected void init() {
        this.t_148_a = true;
        this.minecraft.k_2293_S().n_1700_B(new H_2543_D(H_2543_D.n_1700_B.J_1907_R));
    }

    public void J_1907_R() {
        this.G_564_y = new n_1700_B(this.minecraft);
        this.P_1922_E = new R_4764_Y(this.minecraft);
        this.u_1723_Y = new J_1907_R(this.minecraft);
    }

    public void R_4764_Y() {
        this.addButton(new Button(this.width / 2 - 120, this.height - 52, 80, 20, new F_2904_S("stat.generalButton"), p_213109_1_ -> this.n_1700_B(this.G_564_y)));
        Button button = this.addButton(new Button(this.width / 2 - 40, this.height - 52, 80, 20, new F_2904_S("stat.itemsButton"), p_213115_1_ -> this.n_1700_B(this.P_1922_E)));
        Button button1 = this.addButton(new Button(this.width / 2 + 40, this.height - 52, 80, 20, new F_2904_S("stat.mobsButton"), p_213114_1_ -> this.n_1700_B(this.u_1723_Y)));
        this.addButton(new Button(this.width / 2 - 100, this.height - 28, 200, 20, CommonComponents.R_4764_Y, p_213113_1_ -> this.minecraft.n_1700_B(this.J_1907_R)));
        if (this.P_1922_E.getEventListeners().isEmpty()) {
            button.active = false;
        }
        if (this.u_1723_Y.getEventListeners().isEmpty()) {
            button1.active = false;
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.t_148_a) {
            this.renderBackground(matrixStack);
            l_3350_W.drawCenteredString(matrixStack, this.font, R_4764_Y, this.width / 2, this.height / 2, 0xFFFFFF);
            l_3350_W.drawCenteredString(matrixStack, this.font, n_1700_B[(int)(j_3341_s.J_1907_R() / 150L % (long)n_1700_B.length)], this.width / 2, this.height / 2 + 18, 0xFFFFFF);
        } else {
            this.G_564_y().render(matrixStack, mouseX, mouseY, partialTicks);
            l_3350_W.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 20, 0xFFFFFF);
            super.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void n_1700_B() {
        if (this.t_148_a) {
            this.J_1907_R();
            this.R_4764_Y();
            this.n_1700_B(this.G_564_y);
            this.t_148_a = false;
        }
    }

    @Override
    public boolean isPauseScreen() {
        return !this.t_148_a;
    }

    @Nullable
    public ObjectSelectionList<?> G_564_y() {
        return this.w_1484_f;
    }

    public void n_1700_B(@Nullable ObjectSelectionList<?> p_213110_1_) {
        this.children.remove(this.G_564_y);
        this.children.remove(this.P_1922_E);
        this.children.remove(this.u_1723_Y);
        if (p_213110_1_ != null) {
            this.children.add(0, p_213110_1_);
            this.w_1484_f = p_213110_1_;
        }
    }

    private static String n_1700_B(o_98_P<g_2336_b> p_238672_0_) {
        return "stat." + p_238672_0_.P_1922_E().toString().replace(':', '.');
    }

    private int n_1700_B(int p_195224_1_) {
        return 115 + 40 * p_195224_1_;
    }

    private void n_1700_B(g_221_o p_238667_1_, int p_238667_2_, int p_238667_3_, q_1613_l p_238667_4_) {
        this.n_1700_B(p_238667_1_, p_238667_2_ + 1, p_238667_3_ + 1, 0, 0);
        c_4037_x.n_3318_d();
        this.itemRenderer.n_1700_B(p_238667_4_.Y_601_j(), p_238667_2_ + 2, p_238667_3_ + 2);
        c_4037_x.d_2427_y();
    }

    private void n_1700_B(g_221_o p_238674_1_, int p_238674_2_, int p_238674_3_, int p_238674_4_, int p_238674_5_) {
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(STATS_ICON_LOCATION);
        l_3350_W.blit(p_238674_1_, p_238674_2_, p_238674_3_, this.getBlitOffset(), p_238674_4_, p_238674_5_, 18, 18, 128, 128);
    }

    class lightning.product.l_3350_W$n_1700_B
    extends ObjectSelectionList<n_1700_B> {
        public lightning.product.l_3350_W$n_1700_B(MinecraftClient mcIn) {
            super(mcIn, l_3350_W.this.width, l_3350_W.this.height, 32, l_3350_W.this.height - 64, 10);
            ObjectArrayList objectarraylist = new ObjectArrayList(Stats.t_148_a.iterator());
            objectarraylist.sort(Comparator.comparing(p_238679_0_ -> K_1289_S.n_1700_B(l_3350_W.n_1700_B(p_238679_0_), new Object[0])));
            for (o_98_P stat : objectarraylist) {
                this.addEntry(new n_1700_B(stat));
            }
        }

        @Override
        protected void renderBackground(g_221_o p_230433_1_) {
            l_3350_W.this.renderBackground(p_230433_1_);
        }

        class n_1700_B
        extends ObjectSelectionList.n_1700_B<n_1700_B> {
            private final o_98_P<g_2336_b> J_1907_R;
            private final x_282_a R_4764_Y;

            private n_1700_B(o_98_P<g_2336_b> p_i50466_2_) {
                this.J_1907_R = p_i50466_2_;
                this.R_4764_Y = new F_2904_S(l_3350_W.n_1700_B(p_i50466_2_));
            }

            @Override
            public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
                C_2701_A.drawString(p_230432_1_, l_3350_W.this.font, this.R_4764_Y, p_230432_4_ + 2, p_230432_3_ + 1, p_230432_2_ % 2 == 0 ? 0xFFFFFF : 0x909090);
                String s = this.J_1907_R.n_1700_B(l_3350_W.this.v_4262_N.n_1700_B(this.J_1907_R));
                C_2701_A.drawString(p_230432_1_, l_3350_W.this.font, s, p_230432_4_ + 2 + 213 - l_3350_W.this.font.J_1907_R(s), p_230432_3_ + 1, p_230432_2_ % 2 == 0 ? 0xFFFFFF : 0x909090);
            }
        }
    }

    class R_4764_Y
    extends ObjectSelectionList<J_1907_R> {
        protected final List<q_3277_O<T_2915_h>> n_1700_B;
        protected final List<q_3277_O<q_1613_l>> J_1907_R;
        private final int[] t_148_a;
        protected int R_4764_Y;
        protected final List<q_1613_l> G_564_y;
        protected final Comparator<q_1613_l> P_1922_E;
        @Nullable
        protected q_3277_O<?> u_1723_Y;
        protected int v_4262_N;

        public R_4764_Y(MinecraftClient mcIn) {
            super(mcIn, l_3350_W.this.width, l_3350_W.this.height, 32, l_3350_W.this.height - 64, 20);
            this.t_148_a = new int[]{3, 4, 1, 2, 5, 6};
            this.R_4764_Y = -1;
            this.P_1922_E = new n_1700_B();
            this.n_1700_B = Lists.newArrayList();
            this.n_1700_B.add(Stats.n_1700_B);
            this.J_1907_R = Lists.newArrayList((Object[])new q_3277_O[]{Stats.G_564_y, Stats.J_1907_R, Stats.R_4764_Y, Stats.P_1922_E, Stats.u_1723_Y});
            this.setRenderHeader(true, 20);
            Set set = Sets.newIdentityHashSet();
            for (q_1613_l item : V_3137_a.e_2887_G) {
                boolean flag = false;
                for (q_3277_O<q_1613_l> q_3277_O2 : this.J_1907_R) {
                    if (!q_3277_O2.n_1700_B(item) || l_3350_W.this.v_4262_N.n_1700_B(q_3277_O2.J_1907_R(item)) <= 0) continue;
                    flag = true;
                }
                if (!flag) continue;
                set.add(item);
            }
            for (T_2915_h block : V_3137_a.q_4610_l) {
                boolean flag1 = false;
                for (q_3277_O<q_1803_e> q_3277_O3 : this.n_1700_B) {
                    if (!q_3277_O3.n_1700_B(block) || l_3350_W.this.v_4262_N.n_1700_B(q_3277_O3.J_1907_R(block)) <= 0) continue;
                    flag1 = true;
                }
                if (!flag1) continue;
                set.add(block.u_1723_Y());
            }
            set.remove(Items.n_1700_B);
            this.G_564_y = Lists.newArrayList((Iterable)set);
            for (int i = 0; i < this.G_564_y.size(); ++i) {
                this.addEntry(new J_1907_R());
            }
        }

        @Override
        protected void renderHeader(g_221_o p_230448_1_, int p_230448_2_, int p_230448_3_, l_3747_P p_230448_4_) {
            if (!this.minecraft.h_1847_R.J_1907_R()) {
                this.R_4764_Y = -1;
            }
            for (int i = 0; i < this.t_148_a.length; ++i) {
                l_3350_W.this.n_1700_B(p_230448_1_, p_230448_2_ + l_3350_W.this.n_1700_B(i) - 18, p_230448_3_ + 1, 0, this.R_4764_Y == i ? 0 : 18);
            }
            if (this.u_1723_Y != null) {
                int k = l_3350_W.this.n_1700_B(this.J_1907_R(this.u_1723_Y)) - 36;
                int j = this.v_4262_N == 1 ? 2 : 1;
                l_3350_W.this.n_1700_B(p_230448_1_, p_230448_2_ + k, p_230448_3_ + 1, 18 * j, 0);
            }
            for (int l = 0; l < this.t_148_a.length; ++l) {
                int i1 = this.R_4764_Y == l ? 1 : 0;
                l_3350_W.this.n_1700_B(p_230448_1_, p_230448_2_ + l_3350_W.this.n_1700_B(l) - 18 + i1, p_230448_3_ + 1 + i1, 18 * this.t_148_a[l], 18);
            }
        }

        @Override
        public int getRowWidth() {
            return 375;
        }

        @Override
        protected int getScrollbarPosition() {
            return this.width / 2 + 140;
        }

        @Override
        protected void renderBackground(g_221_o p_230433_1_) {
            l_3350_W.this.renderBackground(p_230433_1_);
        }

        @Override
        protected void clickedHeader(int p_230938_1_, int p_230938_2_) {
            this.R_4764_Y = -1;
            for (int i = 0; i < this.t_148_a.length; ++i) {
                int j = p_230938_1_ - l_3350_W.this.n_1700_B(i);
                if (j < -36 || j > 0) continue;
                this.R_4764_Y = i;
                break;
            }
            if (this.R_4764_Y >= 0) {
                this.n_1700_B(this.n_1700_B(this.R_4764_Y));
                this.minecraft.Z_976_R().n_1700_B(SimpleSoundInstance.n_1700_B(SoundEvents.HayBlock, 1.0f));
            }
        }

        private q_3277_O<?> n_1700_B(int p_195108_1_) {
            return p_195108_1_ < this.n_1700_B.size() ? this.n_1700_B.get(p_195108_1_) : this.J_1907_R.get(p_195108_1_ - this.n_1700_B.size());
        }

        private int J_1907_R(q_3277_O<?> p_195105_1_) {
            int i = this.n_1700_B.indexOf(p_195105_1_);
            if (i >= 0) {
                return i;
            }
            int j = this.J_1907_R.indexOf(p_195105_1_);
            return j >= 0 ? j + this.n_1700_B.size() : -1;
        }

        @Override
        protected void renderDecorations(g_221_o p_230447_1_, int p_230447_2_, int p_230447_3_) {
            if (p_230447_3_ >= this.y0 && p_230447_3_ <= this.y1) {
                J_1907_R statsscreen$statslist$entry = (J_1907_R)this.getEntryAtPosition(p_230447_2_, p_230447_3_);
                int i = (this.width - this.getRowWidth()) / 2;
                if (statsscreen$statslist$entry != null) {
                    if (p_230447_2_ < i + 40 || p_230447_2_ > i + 40 + 20) {
                        return;
                    }
                    q_1613_l item = this.G_564_y.get(this.getEventListeners().indexOf(statsscreen$statslist$entry));
                    this.n_1700_B(p_230447_1_, this.n_1700_B(item), p_230447_2_, p_230447_3_);
                } else {
                    x_282_a itextcomponent = null;
                    int j = p_230447_2_ - i;
                    for (int k = 0; k < this.t_148_a.length; ++k) {
                        int l = l_3350_W.this.n_1700_B(k);
                        if (j < l - 18 || j > l) continue;
                        itextcomponent = this.n_1700_B(k).R_4764_Y();
                        break;
                    }
                    this.n_1700_B(p_230447_1_, itextcomponent, p_230447_2_, p_230447_3_);
                }
            }
        }

        protected void n_1700_B(g_221_o p_238680_1_, @Nullable x_282_a p_238680_2_, int p_238680_3_, int p_238680_4_) {
            if (p_238680_2_ != null) {
                int i = p_238680_3_ + 12;
                int j = p_238680_4_ - 12;
                int k = l_3350_W.this.font.n_1700_B((FormattedText)p_238680_2_);
                lightning.product.l_3350_W$R_4764_Y.fillGradient(p_238680_1_, i - 3, j - 3, i + k + 3, j + 8 + 3, -1073741824, -1073741824);
                c_4037_x.v_4276_D();
                c_4037_x.R_4764_Y(0.0f, 0.0f, 400.0f);
                l_3350_W.this.font.n_1700_B(p_238680_1_, p_238680_2_, (float)i, (float)j, -1);
                c_4037_x.d_2461_k();
            }
        }

        protected x_282_a n_1700_B(q_1613_l p_200208_1_) {
            return p_200208_1_.h_1847_R();
        }

        protected void n_1700_B(q_3277_O<?> p_195107_1_) {
            if (p_195107_1_ != this.u_1723_Y) {
                this.u_1723_Y = p_195107_1_;
                this.v_4262_N = -1;
            } else if (this.v_4262_N == -1) {
                this.v_4262_N = 1;
            } else {
                this.u_1723_Y = null;
                this.v_4262_N = 0;
            }
            this.G_564_y.sort(this.P_1922_E);
        }

        class n_1700_B
        implements Comparator<q_1613_l> {
            private n_1700_B() {
            }

            public int n_1700_B(q_1613_l p_compare_1_, q_1613_l p_compare_2_) {
                int j;
                int i;
                if (R_4764_Y.this.u_1723_Y == null) {
                    i = 0;
                    j = 0;
                } else if (R_4764_Y.this.n_1700_B.contains(R_4764_Y.this.u_1723_Y)) {
                    q_3277_O<?> stattype = R_4764_Y.this.u_1723_Y;
                    i = p_compare_1_ instanceof v_1669_V ? l_3350_W.this.v_4262_N.n_1700_B(stattype, ((v_1669_V)p_compare_1_).v_4262_N()) : -1;
                    j = p_compare_2_ instanceof v_1669_V ? l_3350_W.this.v_4262_N.n_1700_B(stattype, ((v_1669_V)p_compare_2_).v_4262_N()) : -1;
                } else {
                    q_3277_O<?> stattype1 = R_4764_Y.this.u_1723_Y;
                    i = l_3350_W.this.v_4262_N.n_1700_B(stattype1, p_compare_1_);
                    j = l_3350_W.this.v_4262_N.n_1700_B(stattype1, p_compare_2_);
                }
                return i == j ? R_4764_Y.this.v_4262_N * Integer.compare(q_1613_l.n_1700_B(p_compare_1_), q_1613_l.n_1700_B(p_compare_2_)) : R_4764_Y.this.v_4262_N * Integer.compare(i, j);
            }

            @Override
            public /* synthetic */ int compare(Object object, Object object2) {
                return this.n_1700_B((q_1613_l)object, (q_1613_l)object2);
            }
        }

        class J_1907_R
        extends ObjectSelectionList.n_1700_B<J_1907_R> {
            private J_1907_R() {
            }

            @Override
            public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
                q_1613_l item = l_3350_W.this.P_1922_E.G_564_y.get(p_230432_2_);
                l_3350_W.this.n_1700_B(p_230432_1_, p_230432_4_ + 40, p_230432_3_, item);
                for (int i = 0; i < l_3350_W.this.P_1922_E.n_1700_B.size(); ++i) {
                    o_98_P<T_2915_h> stat = item instanceof v_1669_V ? l_3350_W.this.P_1922_E.n_1700_B.get(i).J_1907_R(((v_1669_V)item).v_4262_N()) : null;
                    this.n_1700_B(p_230432_1_, stat, p_230432_4_ + l_3350_W.this.n_1700_B(i), p_230432_3_, p_230432_2_ % 2 == 0);
                }
                for (int j = 0; j < l_3350_W.this.P_1922_E.J_1907_R.size(); ++j) {
                    this.n_1700_B(p_230432_1_, l_3350_W.this.P_1922_E.J_1907_R.get(j).J_1907_R(item), p_230432_4_ + l_3350_W.this.n_1700_B(j + l_3350_W.this.P_1922_E.n_1700_B.size()), p_230432_3_, p_230432_2_ % 2 == 0);
                }
            }

            protected void n_1700_B(g_221_o p_238681_1_, @Nullable o_98_P<?> p_238681_2_, int p_238681_3_, int p_238681_4_, boolean p_238681_5_) {
                String s = p_238681_2_ == null ? "-" : p_238681_2_.n_1700_B(l_3350_W.this.v_4262_N.n_1700_B(p_238681_2_));
                C_2701_A.drawString(p_238681_1_, l_3350_W.this.font, s, p_238681_3_ - l_3350_W.this.font.J_1907_R(s), p_238681_4_ + 5, p_238681_5_ ? 0xFFFFFF : 0x909090);
            }
        }
    }

    class J_1907_R
    extends ObjectSelectionList<n_1700_B> {
        public J_1907_R(MinecraftClient mcIn) {
            super(mcIn, l_3350_W.this.width, l_3350_W.this.height, 32, l_3350_W.this.height - 64, 36);
            for (t_5_h t_5_h2 : V_3137_a.g_221_o) {
                if (l_3350_W.this.v_4262_N.n_1700_B(Stats.v_4262_N.J_1907_R(t_5_h2)) <= 0 && l_3350_W.this.v_4262_N.n_1700_B(Stats.w_1484_f.J_1907_R(t_5_h2)) <= 0) continue;
                this.addEntry(new n_1700_B(t_5_h2));
            }
        }

        @Override
        protected void renderBackground(g_221_o p_230433_1_) {
            l_3350_W.this.renderBackground(p_230433_1_);
        }

        class n_1700_B
        extends ObjectSelectionList.n_1700_B<n_1700_B> {
            private final t_5_h<?> J_1907_R;
            private final x_282_a R_4764_Y;
            private final x_282_a G_564_y;
            private final boolean P_1922_E;
            private final x_282_a u_1723_Y;
            private final boolean v_4262_N;

            public n_1700_B(t_5_h<?> p_i50018_2_) {
                this.J_1907_R = p_i50018_2_;
                this.R_4764_Y = p_i50018_2_.v_4262_N();
                int i = l_3350_W.this.v_4262_N.n_1700_B(Stats.v_4262_N.J_1907_R(p_i50018_2_));
                if (i == 0) {
                    this.G_564_y = new F_2904_S("stat_type.minecraft.killed.none", this.R_4764_Y);
                    this.P_1922_E = false;
                } else {
                    this.G_564_y = new F_2904_S("stat_type.minecraft.killed", i, this.R_4764_Y);
                    this.P_1922_E = true;
                }
                int j = l_3350_W.this.v_4262_N.n_1700_B(Stats.w_1484_f.J_1907_R(p_i50018_2_));
                if (j == 0) {
                    this.u_1723_Y = new F_2904_S("stat_type.minecraft.killed_by.none", this.R_4764_Y);
                    this.v_4262_N = false;
                } else {
                    this.u_1723_Y = new F_2904_S("stat_type.minecraft.killed_by", this.R_4764_Y, j);
                    this.v_4262_N = true;
                }
            }

            @Override
            public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
                C_2701_A.drawString(p_230432_1_, l_3350_W.this.font, this.R_4764_Y, p_230432_4_ + 2, p_230432_3_ + 1, 0xFFFFFF);
                C_2701_A.drawString(p_230432_1_, l_3350_W.this.font, this.G_564_y, p_230432_4_ + 2 + 10, p_230432_3_ + 1 + 9, this.P_1922_E ? 0x909090 : 0x606060);
                C_2701_A.drawString(p_230432_1_, l_3350_W.this.font, this.u_1723_Y, p_230432_4_ + 2 + 10, p_230432_3_ + 1 + 18, this.v_4262_N ? 0x909090 : 0x606060);
            }
        }
    }
}



