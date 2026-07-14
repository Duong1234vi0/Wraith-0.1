/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ComparisonChain
 *  com.google.common.collect.Ordering
 *  com.mojang.authlib.GameProfile
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lightning.product.A_2226_Q;
import lightning.product.C_2701_A;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.D_4024_W;
import lightning.product.E_4346_v;
import lightning.product.E_688_b;
import lightning.product.FormattedText;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.I_14_v;
import lightning.product.M_1462_J;
import lightning.product.P_4249_L;
import lightning.product.U_2871_b;
import lightning.product.Objective;
import lightning.product.a_3913_L;
import lightning.product.BetterMinecraft;
import lightning.product.MinecraftClient;
import lightning.product.PlayerTeam;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.g_164_R;
import lightning.product.g_221_o;
import lightning.product.Easing;
import lightning.product.i_4895_l;
import lightning.product.j_3341_s;
import lightning.product.Animation;
import lightning.product.l_3747_P;
import lightning.product.ClientBootstrap;
import lightning.product.ExtendedTab;
import lightning.product.u_1406_j;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class W_226_N
extends C_2701_A {
    private static final Ordering<A_2226_Q> n_1700_B = Ordering.from((Comparator)new n_1700_B());
    private final MinecraftClient J_1907_R;
    private final u_1406_j R_4764_Y;
    private x_282_a G_564_y;
    private x_282_a P_1922_E;
    private long u_1723_Y;
    private boolean v_4262_N;
    private final Animation w_1484_f = new Animation(0.0f, 12.0f, Easing.u_2550_I);
    @Nullable
    private P_4249_L t_148_a;
    private int s_956_w;
    private int u_2550_I;
    private int M_588_G;
    private int P_4830_p;
    private int h_1847_R;
    private int Q_4569_t;
    private int M_182_A;
    private boolean t_1786_h;
    private int multiplayerClientSuggestionProvider;
    private int w_1457_N;
    private int Y_601_j;
    private final Pattern Y_259_p = Pattern.compile("^\\w{3,16}$");

    private boolean P_1922_E() {
        BetterMinecraft betterMinecraft = (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
        if (betterMinecraft == null || !betterMinecraft.w_1484_f()) {
            return false;
        }
        Boolean listEnabled = betterMinecraft.Z_875_P().J_1907_R("\u0421\u043f\u0438\u0441\u043e\u043a \u0438\u0433\u0440\u043e\u043a\u043e\u0432");
        return Boolean.TRUE.equals(listEnabled);
    }

    private boolean u_1723_Y() {
        BetterMinecraft betterMinecraft = (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
        return betterMinecraft != null && betterMinecraft.M_182_A();
    }

    public W_226_N(MinecraftClient mcIn, u_1406_j guiIngameIn) {
        this.J_1907_R = mcIn;
        this.R_4764_Y = guiIngameIn;
    }

    public x_282_a n_1700_B(A_2226_Q p_200262_1_) {
        return p_200262_1_.u_2550_I() != null ? this.n_1700_B(p_200262_1_, p_200262_1_.u_2550_I().P_1922_E()) : this.n_1700_B(p_200262_1_, PlayerTeam.n_1700_B(p_200262_1_.t_148_a(), new U_2871_b(p_200262_1_.n_1700_B().getName())));
    }

    private x_282_a n_1700_B(A_2226_Q p_238524_1_, MutableComponent p_238524_2_) {
        return p_238524_1_.J_1907_R() == I_14_v.P_1922_E ? p_238524_2_.n_1700_B(D_4024_W.Y_259_p) : p_238524_2_;
    }

    public void n_1700_B(boolean visible) {
        boolean wasVisible = this.v_4262_N;
        if (visible && !wasVisible) {
            this.u_1723_Y = j_3341_s.J_1907_R();
        }
        this.v_4262_N = visible;
        if (wasVisible != visible) {
            if (visible) {
                this.t_1786_h = true;
            }
            if (this.P_1922_E()) {
                this.w_1484_f.n_1700_B(visible ? 1.0f : 0.0f);
            } else {
                this.w_1484_f.J_1907_R(visible ? 1.0f : 0.0f);
            }
        }
    }

    public boolean n_1700_B() {
        return this.v_4262_N || this.P_1922_E() && this.w_1484_f.G_564_y();
    }

    public void n_1700_B(g_221_o p_238523_1_, int p_238523_2_, i_4895_l p_238523_3_, @Nullable Objective p_238523_4_) {
        int n;
        int i4;
        int j;
        int i;
        boolean reuseTabLayoutMetrics;
        boolean useBufferedTab;
        int totalPlayerLimit;
        int maxColumns;
        int maxPlayersPerColumn;
        lightning.product.n_1700_B bot1 = null;
        if (this.J_1907_R.k_2293_S != null && this.J_1907_R.C_2741_M == this.J_1907_R.k_2293_S.P_1922_E.Q_2552_b) {
            bot1 = this.J_1907_R.k_2293_S;
        }
        Collection<A_2226_Q> playerInfoCollection = bot1 != null ? bot1.P_1922_E.P_1922_E() : this.J_1907_R.Y_259_p.n_1700_B.P_1922_E();
        List<A_2226_Q> list = new ArrayList<A_2226_Q>();
        HashSet<String> onlineNames = new HashSet<String>();
        for (A_2226_Q info : playerInfoCollection) {
            onlineNames.add(info.n_1700_B().getName());
        }
        for (A_2226_Q info : n_1700_B.sortedCopy(playerInfoCollection)) {
            String trimmed;
            String disp;
            String string = disp = info.u_2550_I() != null ? info.u_2550_I().getString() : info.n_1700_B().getName();
            if (disp != null && ((trimmed = disp.trim()).toLowerCase(Locale.ROOT).startsWith("npc") || trimmed.startsWith("FS_"))) continue;
            list.add(info);
        }
        boolean isExtendedTabEnabled = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(ExtendedTab.class).w_1484_f();
        if (isExtendedTabEnabled) {
            maxPlayersPerColumn = ((Float)ExtendedTab.v_4262_N.J_1907_R()).intValue();
            maxColumns = ((Float)ExtendedTab.w_1484_f.J_1907_R()).intValue();
            totalPlayerLimit = maxPlayersPerColumn * maxColumns;
        } else {
            maxPlayersPerColumn = 20;
            maxColumns = Integer.MAX_VALUE;
            totalPlayerLimit = 80;
        }
        int scaledHeight = this.J_1907_R.RealmsServerPing().M_182_A();
        boolean bl = useBufferedTab = g_164_R.v_4262_N() && this.u_1723_Y();
        boolean flag = this.J_1907_R.x_607_J() || (bot1 != null ? bot1.P_1922_E.getBotNetwork().u_1723_Y() : this.J_1907_R.k_2293_S().getNetworkManager().P_1922_E());
        int dataHashPreMetrics = this.n_1700_B(list, p_238523_2_, p_238523_3_, p_238523_4_, flag, maxPlayersPerColumn, maxColumns, totalPlayerLimit, bot1 != null, isExtendedTabEnabled);
        boolean sizeMismatch = this.t_148_a == null || this.s_956_w != p_238523_2_ || this.u_2550_I != scaledHeight;
        boolean bl2 = reuseTabLayoutMetrics = useBufferedTab && this.v_4262_N && this.t_148_a != null && !sizeMismatch && !this.t_1786_h && dataHashPreMetrics == this.multiplayerClientSuggestionProvider;
        if (reuseTabLayoutMetrics) {
            i = this.w_1457_N;
            j = this.Y_601_j;
        } else {
            i = 0;
            j = 0;
            for (A_2226_Q networkplayerinfo : list) {
                int k = this.J_1907_R.t_148_a.n_1700_B((FormattedText)this.n_1700_B(networkplayerinfo));
                i = Math.max(i, k);
                if (p_238523_4_ == null || p_238523_4_.u_1723_Y() == M_1462_J.n_1700_B.J_1907_R) continue;
                k = this.J_1907_R.t_148_a.J_1907_R(" " + p_238523_3_.J_1907_R(networkplayerinfo.n_1700_B().getName(), p_238523_4_).J_1907_R());
                j = Math.max(j, k);
            }
            this.multiplayerClientSuggestionProvider = dataHashPreMetrics;
            this.w_1457_N = i;
            this.Y_601_j = j;
        }
        list = list.subList(0, Math.min(list.size(), totalPlayerLimit));
        int j4 = i4 = list.size();
        int k4 = 1;
        while (j4 > maxPlayersPerColumn) {
            if (isExtendedTabEnabled && ++k4 > maxColumns) {
                k4 = maxColumns;
                break;
            }
            j4 = (i4 + k4 - 1) / k4;
        }
        int l = p_238523_4_ != null ? (p_238523_4_.u_1723_Y() == M_1462_J.n_1700_B.J_1907_R ? 90 : j) : 0;
        int i1 = Math.min(k4 * ((flag ? 9 : 0) + i + l + 13), p_238523_2_ - 50) / k4;
        int j1 = p_238523_2_ / 2 - (i1 * k4 + (k4 - 1) * 5) / 2;
        int l1 = i1 * k4 + (k4 - 1) * 5;
        List<FormattedCharSequence> list1 = null;
        if (this.P_1922_E != null) {
            list1 = this.J_1907_R.t_148_a.J_1907_R(this.P_1922_E, p_238523_2_ - 50);
            for (FormattedCharSequence d_3332_t2 : list1) {
                l1 = Math.max(l1, this.J_1907_R.t_148_a.n_1700_B(d_3332_t2));
            }
        }
        List<FormattedCharSequence> list2 = null;
        if (this.G_564_y != null) {
            list2 = this.J_1907_R.t_148_a.J_1907_R(this.G_564_y, p_238523_2_ - 50);
            for (FormattedCharSequence ireorderingprocessor1 : list2) {
                l1 = Math.max(l1, this.J_1907_R.t_148_a.n_1700_B(ireorderingprocessor1));
            }
        }
        int n2 = 10;
        int boundsMinY = Integer.MAX_VALUE;
        int boundsMaxY = Integer.MIN_VALUE;
        if (list1 != null) {
            boundsMinY = Math.min(boundsMinY, n2 - 1);
            boundsMaxY = Math.max(boundsMaxY, n2 + list1.size() * 9);
            n = n2 + (list1.size() * 9 + 1);
        }
        boundsMinY = Math.min(boundsMinY, (int)(n - true));
        boundsMaxY = Math.max(boundsMaxY, (int)(n + j4 * 9));
        if (list2 != null) {
            void var30_41 = n + (j4 * 9 + 1);
            boundsMinY = Math.min(boundsMinY, (int)(var30_41 - true));
            boundsMaxY = Math.max(boundsMaxY, (int)(var30_41 + list2.size() * 9));
        }
        int boundsMinX = p_238523_2_ / 2 - l1 / 2 - 1;
        int boundsMaxX = p_238523_2_ / 2 + l1 / 2 + 1;
        if (useBufferedTab) {
            boolean useAnim;
            boolean needsRedraw;
            int signature = this.n_1700_B(list, p_238523_2_, p_238523_4_, flag, maxPlayersPerColumn, maxColumns, totalPlayerLimit, bot1 != null, i, j);
            boolean bl3 = needsRedraw = this.t_1786_h || sizeMismatch || signature != this.M_182_A;
            if (needsRedraw) {
                this.n_1700_B(p_238523_2_, scaledHeight);
                this.t_148_a.J_1907_R(true);
                this.t_148_a.n_1700_B(0.0f, 0.0f, 0.0f, 0.0f);
                this.t_148_a.R_4764_Y(MinecraftClient.n_1700_B);
                c_4037_x.x_607_J();
                c_4037_x.Y_601_j();
                c_4037_x.s_2632_s();
                c_4037_x.M_588_G();
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                g_221_o bufStack = new g_221_o();
                this.n_1700_B(bufStack, p_238523_2_, p_238523_3_, p_238523_4_, list, i, j1, 10, l1, j4, i4, l, flag, list1, list2, bot1, i1);
                this.J_1907_R.G_564_y().J_1907_R(true);
                this.M_588_G = boundsMinX;
                this.P_4830_p = boundsMinY;
                this.h_1847_R = boundsMaxX;
                this.Q_4569_t = boundsMaxY;
                this.M_182_A = signature;
                this.t_1786_h = false;
            }
            if (useAnim = this.P_1922_E()) {
                if (this.w_1484_f.G_564_y()) {
                    this.w_1484_f.n_1700_B(this.v_4262_N ? 1.0f : 0.0f);
                }
                float slideProgress = this.w_1484_f.n_1700_B();
                int headerHeight = list1 != null ? list1.size() * 9 : 0;
                int footerHeight = list2 != null ? list2.size() * 9 : 0;
                int contentHeight = j4 * 9;
                int slideDistance = headerHeight + contentHeight + footerHeight + 10;
                p_238523_1_.n_1700_B();
                p_238523_1_.n_1700_B(0.0, -(1.0 - (double)slideProgress) * (double)slideDistance, 0.0);
            }
            if (this.t_148_a != null && boundsMaxY >= boundsMinY && boundsMaxX > boundsMinX) {
                this.n_1700_B(p_238523_1_, this.t_148_a, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t);
            }
            if (useAnim) {
                p_238523_1_.J_1907_R();
            }
            return;
        }
        boolean useAnim = this.P_1922_E();
        if (useAnim) {
            if (this.w_1484_f.G_564_y()) {
                this.w_1484_f.n_1700_B(this.v_4262_N ? 1.0f : 0.0f);
            }
            float slideProgress = this.w_1484_f.n_1700_B();
            int headerHeight = list1 != null ? list1.size() * 9 : 0;
            int footerHeight = list2 != null ? list2.size() * 9 : 0;
            int contentHeight = j4 * 9;
            int slideDistance = headerHeight + contentHeight + footerHeight + 10;
            p_238523_1_.n_1700_B();
            p_238523_1_.n_1700_B(0.0, -(1.0 - (double)slideProgress) * (double)slideDistance, 0.0);
        }
        this.n_1700_B(p_238523_1_, p_238523_2_, p_238523_3_, p_238523_4_, list, i, j1, 10, l1, j4, i4, l, flag, list1, list2, bot1, i1);
        if (useAnim) {
            p_238523_1_.J_1907_R();
        }
    }

    private void n_1700_B(int width, int height) {
        if (this.t_148_a == null || this.s_956_w != width || this.u_2550_I != height) {
            if (this.t_148_a != null) {
                this.t_148_a.P_1922_E();
            }
            this.t_148_a = new P_4249_L(width, height, false, MinecraftClient.n_1700_B);
            this.s_956_w = width;
            this.u_2550_I = height;
            this.multiplayerClientSuggestionProvider = 0;
        }
    }

    private int n_1700_B(List<A_2226_Q> listPreLimit, int scaledWidth, i_4895_l scoreboard, @Nullable Objective objective, boolean encryptedIcons, int maxPlayersPerColumn, int maxColumns, int totalPlayerLimit, boolean viewingBot, boolean extendedTabEnabled) {
        int h = 1;
        h = 31 * h + scaledWidth;
        h = 31 * h + totalPlayerLimit;
        h = 31 * h + maxPlayersPerColumn;
        h = 31 * h + maxColumns;
        h = 31 * h + (extendedTabEnabled ? 1 : 0);
        h = 31 * h + (this.P_1922_E != null ? this.P_1922_E.getString().hashCode() : 0);
        h = 31 * h + (this.G_564_y != null ? this.G_564_y.getString().hashCode() : 0);
        h = 31 * h + (objective != null ? objective.J_1907_R().hashCode() : 0);
        h = 31 * h + (objective != null ? objective.u_1723_Y().ordinal() : -1);
        h = 31 * h + (encryptedIcons ? 1 : 0);
        h = 31 * h + (viewingBot ? 1 : 0);
        h = 31 * h + listPreLimit.size();
        for (A_2226_Q info : listPreLimit) {
            h = 31 * h + info.n_1700_B().getId().hashCode();
            h = 31 * h + (info.u_2550_I() != null ? info.u_2550_I().getString().hashCode() : 0);
            h = 31 * h + info.J_1907_R().ordinal();
            if (objective == null || objective.u_1723_Y() == M_1462_J.n_1700_B.J_1907_R) continue;
            h = 31 * h + scoreboard.J_1907_R(info.n_1700_B().getName(), objective).J_1907_R();
        }
        return h;
    }

    private int n_1700_B(List<A_2226_Q> list, int scaledWidth, @Nullable Objective objective, boolean encryptedIcons, int maxPlayersPerColumn, int maxColumns, int totalPlayerLimit, boolean viewingBot, int maxNamePixelWidth, int maxScorePixelWidth) {
        int h = 1;
        h = 31 * h + scaledWidth;
        h = 31 * h + maxNamePixelWidth;
        h = 31 * h + maxScorePixelWidth;
        h = 31 * h + (this.P_1922_E != null ? this.P_1922_E.getString().hashCode() : 0);
        h = 31 * h + (this.G_564_y != null ? this.G_564_y.getString().hashCode() : 0);
        h = 31 * h + (objective != null ? objective.J_1907_R().hashCode() : 0);
        h = 31 * h + (objective != null ? objective.u_1723_Y().ordinal() : -1);
        h = 31 * h + (encryptedIcons ? 1 : 0);
        h = 31 * h + maxPlayersPerColumn;
        h = 31 * h + maxColumns;
        h = 31 * h + totalPlayerLimit;
        h = 31 * h + (viewingBot ? 1 : 0);
        h = 31 * h + list.size();
        for (A_2226_Q info : list) {
            h = 31 * h + info.n_1700_B().getId().hashCode();
            h = 31 * h + (info.u_2550_I() != null ? info.u_2550_I().getString().hashCode() : 0);
            h = 31 * h + info.J_1907_R().ordinal();
        }
        return h;
    }

    private void n_1700_B(g_221_o matrices, P_4249_L buffer, int minX, int minY, int maxX, int maxY) {
        float texW = buffer.n_1700_B;
        float texH = buffer.J_1907_R;
        if (texW <= 0.0f || texH <= 0.0f || maxX <= minX || maxY < minY) {
            return;
        }
        float u0 = (float)minX / texW;
        float u1 = (float)maxX / texW;
        float vTop = 1.0f - (float)minY / texH;
        float vBottom = 1.0f - (float)maxY / texH;
        float z = this.R_4764_Y.getBlitOffset();
        D_1098_v matrix = matrices.R_4764_Y().n_1700_B();
        c_4037_x.x_607_J();
        c_4037_x.u_2550_I();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.t_1786_h();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        buffer.v_4262_N();
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
        bufferbuilder.n_1700_B(matrix, (float)minX, (float)maxY, z).tex(u0, vBottom).color(255, 255, 255, 255).endVertex();
        bufferbuilder.n_1700_B(matrix, (float)maxX, (float)maxY, z).tex(u1, vBottom).color(255, 255, 255, 255).endVertex();
        bufferbuilder.n_1700_B(matrix, (float)maxX, (float)minY, z).tex(u1, vTop).color(255, 255, 255, 255).endVertex();
        bufferbuilder.n_1700_B(matrix, (float)minX, (float)minY, z).tex(u0, vTop).color(255, 255, 255, 255).endVertex();
        tessellator.J_1907_R();
        buffer.w_1484_f();
        c_4037_x.u_2550_I();
    }

    private void n_1700_B(g_221_o p_238523_1_, int p_238523_2_, i_4895_l p_238523_3_, @Nullable Objective p_238523_4_, List<A_2226_Q> list, int i, int j1, int k1Start, int l1, int j4, int i4, int l, boolean flag, @Nullable List<FormattedCharSequence> list1, @Nullable List<FormattedCharSequence> list2, @Nullable lightning.product.n_1700_B bot1, int i1) {
        int k1 = k1Start;
        if (list1 != null) {
            W_226_N.fill(p_238523_1_, p_238523_2_ / 2 - l1 / 2 - 1, k1 - 1, p_238523_2_ / 2 + l1 / 2 + 1, k1 + list1.size() * 9, Integer.MIN_VALUE);
            for (FormattedCharSequence ireorderingprocessor2 : list1) {
                int i2 = this.J_1907_R.t_148_a.n_1700_B(ireorderingprocessor2);
                this.J_1907_R.t_148_a.n_1700_B(p_238523_1_, ireorderingprocessor2, (float)(p_238523_2_ / 2 - i2 / 2), (float)k1, -1);
                k1 += 9;
            }
            ++k1;
        }
        W_226_N.fill(p_238523_1_, p_238523_2_ / 2 - l1 / 2 - 1, k1 - 1, p_238523_2_ / 2 + l1 / 2 + 1, k1 + j4 * 9, Integer.MIN_VALUE);
        int l4 = this.J_1907_R.P_4830_p.n_1700_B(0x20FFFFFF);
        for (int i5 = 0; i5 < i4; ++i5) {
            int l5;
            int i6;
            int j5 = i5 / j4;
            int j2 = i5 % j4;
            int k2 = j1 + j5 * i1 + j5 * 5;
            int l2 = k1 + j2 * 9;
            W_226_N.fill(p_238523_1_, k2, l2, k2 + i1, l2 + 8, l4);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            c_4037_x.M_588_G();
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            if (i5 >= list.size()) continue;
            A_2226_Q networkplayerinfo1 = list.get(i5);
            GameProfile gameprofile = networkplayerinfo1.n_1700_B();
            if (flag) {
                a_3913_L playerentity = bot1 != null ? bot1.P_1922_E.G_564_y().n_1700_B(gameprofile.getId()) : this.J_1907_R.Y_601_j.n_1700_B(gameprofile.getId());
                boolean flag1 = playerentity != null && playerentity.n_1700_B(E_4346_v.n_1700_B) && ("Dinnerbone".equals(gameprofile.getName()) || "Grumm".equals(gameprofile.getName()));
                this.J_1907_R.G_624_v().n_1700_B(networkplayerinfo1.u_1723_Y());
                int i3 = 8 + (flag1 ? 8 : 0);
                int j3 = 8 * (flag1 ? -1 : 1);
                C_2701_A.blit(p_238523_1_, k2, l2, 8, 8, 8.0f, i3, 8, j3, 64, 64);
                if (playerentity != null && playerentity.n_1700_B(E_4346_v.v_4262_N)) {
                    int k3 = 8 + (flag1 ? 8 : 0);
                    int l3 = 8 * (flag1 ? -1 : 1);
                    C_2701_A.blit(p_238523_1_, k2, l2, 8, 8, 40.0f, k3, 8, l3, 64, 64);
                }
                k2 += 9;
            }
            this.J_1907_R.t_148_a.n_1700_B(p_238523_1_, this.n_1700_B(networkplayerinfo1), (float)k2, (float)l2, networkplayerinfo1.J_1907_R() == I_14_v.P_1922_E ? -1862270977 : -1);
            if (p_238523_4_ != null && networkplayerinfo1.J_1907_R() != I_14_v.P_1922_E && (i6 = (l5 = k2 + i + 1) + l) - l5 > 5) {
                this.n_1700_B(p_238523_4_, l2, gameprofile.getName(), l5, i6, networkplayerinfo1, p_238523_1_);
            }
            this.n_1700_B(p_238523_1_, i1, k2 - (flag ? 9 : 0), l2, networkplayerinfo1);
        }
        if (list2 != null) {
            k1 = k1 + j4 * 9 + 1;
            W_226_N.fill(p_238523_1_, p_238523_2_ / 2 - l1 / 2 - 1, k1 - 1, p_238523_2_ / 2 + l1 / 2 + 1, k1 + list2.size() * 9, Integer.MIN_VALUE);
            for (FormattedCharSequence ireorderingprocessor3 : list2) {
                int k5 = this.J_1907_R.t_148_a.n_1700_B(ireorderingprocessor3);
                this.J_1907_R.t_148_a.n_1700_B(p_238523_1_, ireorderingprocessor3, (float)(p_238523_2_ / 2 - k5 / 2), (float)k1, -1);
                k1 += 9;
            }
        }
    }

    protected void n_1700_B(g_221_o p_238522_1_, int p_238522_2_, int p_238522_3_, int p_238522_4_, A_2226_Q p_238522_5_) {
        int textColor;
        String pingText;
        int responseTime = p_238522_5_.R_4764_Y();
        if (responseTime < 0) {
            pingText = "N/A";
            textColor = 0xFF5555;
        } else {
            pingText = String.valueOf(responseTime);
            int[] thresholds = new int[]{50, 100, 150, 200};
            int[] colors = new int[]{65280, 0x55FF55, 0xFFFF00, 0xFFAA00, 0xFF0000};
            textColor = colors[colors.length - 1];
            if (responseTime < thresholds[0]) {
                textColor = colors[0];
            } else if (responseTime < thresholds[thresholds.length - 1]) {
                for (int i = 0; i < thresholds.length - 1; ++i) {
                    if (responseTime < thresholds[i] || responseTime >= thresholds[i + 1]) continue;
                    float t = (float)(responseTime - thresholds[i]) / (float)(thresholds[i + 1] - thresholds[i]);
                    textColor = H_2506_c.n_1700_B(colors[i + 1], colors[i], t);
                    break;
                }
            }
        }
        p_238522_1_.n_1700_B();
        p_238522_1_.n_1700_B(0.5f, 0.5f, 1.0f);
        float x = ((float)(p_238522_3_ + p_238522_2_) - (float)this.J_1907_R.t_148_a.J_1907_R(pingText) * 0.5f - 1.0f) / 0.5f;
        float y = ((float)p_238522_4_ + 2.5f) / 0.5f;
        this.J_1907_R.t_148_a.n_1700_B(p_238522_1_, new U_2871_b(pingText), x, y, textColor);
        p_238522_1_.J_1907_R();
    }

    private void n_1700_B(Objective objective, int p_175247_2_, String name, int p_175247_4_, int p_175247_5_, A_2226_Q info, g_221_o p_175247_7_) {
        int i = objective.n_1700_B().J_1907_R(name, objective).J_1907_R();
        if (objective.u_1723_Y() == M_1462_J.n_1700_B.J_1907_R) {
            boolean flag;
            this.J_1907_R.G_624_v().n_1700_B(GUI_ICONS_LOCATION);
            long j = j_3341_s.J_1907_R();
            if (this.u_1723_Y == info.M_182_A()) {
                if (i < info.M_588_G()) {
                    info.n_1700_B(j);
                    info.J_1907_R((long)(this.R_4764_Y.G_564_y() + 20));
                } else if (i > info.M_588_G()) {
                    info.n_1700_B(j);
                    info.J_1907_R((long)(this.R_4764_Y.G_564_y() + 10));
                }
            }
            if (j - info.h_1847_R() > 1000L || this.u_1723_Y != info.M_182_A()) {
                info.J_1907_R(i);
                info.R_4764_Y(i);
                info.n_1700_B(j);
            }
            info.R_4764_Y(this.u_1723_Y);
            info.J_1907_R(i);
            int k = u_530_F.u_1723_Y((float)Math.max(i, info.P_4830_p()) / 2.0f);
            int l = Math.max(u_530_F.u_1723_Y((float)(i / 2)), Math.max(u_530_F.u_1723_Y((float)(info.P_4830_p() / 2)), 10));
            boolean bl = flag = info.Q_4569_t() > (long)this.R_4764_Y.G_564_y() && (info.Q_4569_t() - (long)this.R_4764_Y.G_564_y()) / 3L % 2L == 1L;
            if (k > 0) {
                int i1 = u_530_F.G_564_y(Math.min((float)(p_175247_5_ - p_175247_4_ - 4) / (float)l, 9.0f));
                if (i1 > 3) {
                    for (int j1 = k; j1 < l; ++j1) {
                        this.blit(p_175247_7_, p_175247_4_ + j1 * i1, p_175247_2_, flag ? 25 : 16, 0, 9, 9);
                    }
                    for (int l1 = 0; l1 < k; ++l1) {
                        this.blit(p_175247_7_, p_175247_4_ + l1 * i1, p_175247_2_, flag ? 25 : 16, 0, 9, 9);
                        if (flag) {
                            if (l1 * 2 + 1 < info.P_4830_p()) {
                                this.blit(p_175247_7_, p_175247_4_ + l1 * i1, p_175247_2_, 70, 0, 9, 9);
                            }
                            if (l1 * 2 + 1 == info.P_4830_p()) {
                                this.blit(p_175247_7_, p_175247_4_ + l1 * i1, p_175247_2_, 79, 0, 9, 9);
                            }
                        }
                        if (l1 * 2 + 1 < i) {
                            this.blit(p_175247_7_, p_175247_4_ + l1 * i1, p_175247_2_, l1 >= 10 ? 160 : 52, 0, 9, 9);
                        }
                        if (l1 * 2 + 1 != i) continue;
                        this.blit(p_175247_7_, p_175247_4_ + l1 * i1, p_175247_2_, l1 >= 10 ? 169 : 61, 0, 9, 9);
                    }
                } else {
                    float f = u_530_F.n_1700_B((float)i / 20.0f, 0.0f, 1.0f);
                    int k1 = (int)((1.0f - f) * 255.0f) << 16 | (int)(f * 255.0f) << 8;
                    String s = "" + (float)i / 2.0f;
                    if (p_175247_5_ - this.J_1907_R.t_148_a.J_1907_R(s + "hp") >= p_175247_4_) {
                        s = s + "hp";
                    }
                    this.J_1907_R.t_148_a.n_1700_B(p_175247_7_, s, (float)((p_175247_5_ + p_175247_4_) / 2 - this.J_1907_R.t_148_a.J_1907_R(s) / 2), (float)p_175247_2_, k1);
                }
            }
        } else {
            String s1 = String.valueOf((Object)D_4024_W.Q_4569_t) + i;
            this.J_1907_R.t_148_a.n_1700_B(p_175247_7_, s1, (float)(p_175247_5_ - this.J_1907_R.t_148_a.J_1907_R(s1)), (float)p_175247_2_, 0xFFFFFF);
        }
    }

    public void n_1700_B(@Nullable x_282_a footerIn) {
        this.G_564_y = footerIn;
        this.t_1786_h = true;
        this.multiplayerClientSuggestionProvider = 0;
    }

    public void J_1907_R(@Nullable x_282_a headerIn) {
        this.P_1922_E = headerIn;
        this.t_1786_h = true;
        this.multiplayerClientSuggestionProvider = 0;
    }

    @Nullable
    public x_282_a J_1907_R() {
        return this.P_1922_E;
    }

    @Nullable
    public x_282_a R_4764_Y() {
        return this.G_564_y;
    }

    public void G_564_y() {
        this.P_1922_E = null;
        this.G_564_y = null;
        this.t_1786_h = true;
        this.multiplayerClientSuggestionProvider = 0;
    }

    static class n_1700_B
    implements Comparator<A_2226_Q> {
        private n_1700_B() {
        }

        public int n_1700_B(A_2226_Q p_compare_1_, A_2226_Q p_compare_2_) {
            PlayerTeam scoreplayerteam = p_compare_1_.t_148_a();
            PlayerTeam scoreplayerteam1 = p_compare_2_.t_148_a();
            return ComparisonChain.start().compareTrueFirst(p_compare_1_.J_1907_R() != I_14_v.P_1922_E, p_compare_2_.J_1907_R() != I_14_v.P_1922_E).compare((Comparable)((Object)(scoreplayerteam != null ? scoreplayerteam.n_1700_B() : "")), (Comparable)((Object)(scoreplayerteam1 != null ? scoreplayerteam1.n_1700_B() : ""))).compare((Object)p_compare_1_.n_1700_B().getName(), (Object)p_compare_2_.n_1700_B().getName(), String::compareToIgnoreCase).result();
        }

        @Override
        public /* synthetic */ int compare(Object object, Object object2) {
            return this.n_1700_B((A_2226_Q)object, (A_2226_Q)object2);
        }
    }
}



