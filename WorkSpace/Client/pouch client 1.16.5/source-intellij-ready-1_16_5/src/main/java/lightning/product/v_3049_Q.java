/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.hash.Hashing
 *  com.google.common.util.concurrent.ThreadFactoryBuilder
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.Validate
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.I_1084_e;
import lightning.product.SharedConstants;
import lightning.product.ObjectSelectionList;
import lightning.product.T_1114_L;
import lightning.product.U_2871_b;
import lightning.product.ServerData;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.c_4477_a;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.m_3545_A;
import lightning.product.o_2488_o;
import lightning.product.q_3131_N;
import lightning.product.DefaultUncaughtExceptionHandler;
import lightning.product.x_282_a;
import lightning.product.LanServer;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class v_3049_Q
extends ObjectSelectionList<n_1700_B> {
    private static final String n_1700_B = "mc.bravohvh.su";
    private static final Logger J_1907_R = LogManager.getLogger();
    private static final ThreadPoolExecutor R_4764_Y = new ScheduledThreadPoolExecutor(5, new ThreadFactoryBuilder().setNameFormat("Server Pinger #%d").setDaemon(true).setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new DefaultUncaughtExceptionHandler(J_1907_R)).build());
    private static final g_2336_b G_564_y = new g_2336_b("textures/misc/unknown_server.png");
    private static final g_2336_b P_1922_E = new g_2336_b("textures/gui/server_selection.png");
    private static final x_282_a u_1723_Y = new F_2904_S("lanServer.scanning");
    private static final x_282_a v_4262_N = new F_2904_S("multiplayer.status.cannot_resolve").n_1700_B(D_4024_W.P_1922_E);
    private static final x_282_a w_1484_f = new F_2904_S("multiplayer.status.cannot_connect").n_1700_B(D_4024_W.P_1922_E);
    private static final x_282_a t_148_a = new F_2904_S("multiplayer.status.incompatible");
    private static final x_282_a s_956_w = new F_2904_S("multiplayer.status.no_connection");
    private static final x_282_a u_2550_I = new F_2904_S("multiplayer.status.pinging");
    private final q_3131_N M_588_G;
    private final List<G_564_y> P_4830_p = Lists.newArrayList();
    private final n_1700_B h_1847_R = new R_4764_Y();
    private final List<J_1907_R> Q_4569_t = Lists.newArrayList();

    public v_3049_Q(q_3131_N ownerIn, MinecraftClient mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
        super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
        this.M_588_G = ownerIn;
    }

    private void n_1700_B() {
        this.clearEntries();
        this.P_4830_p.forEach(x$0 -> this.addEntry(x$0));
        this.addEntry(this.h_1847_R);
        this.Q_4569_t.forEach(x$0 -> this.addEntry(x$0));
    }

    public void n_1700_B(@Nullable n_1700_B entry) {
        super.setSelected(entry);
        if (this.getSelected() instanceof G_564_y) {
            I_1084_e.J_1907_R.n_1700_B(new F_2904_S("narrator.select", ((G_564_y)this.getSelected()).G_564_y.n_1700_B).getString());
        }
        this.M_588_G.J_1907_R();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        n_1700_B serverselectionlist$entry = (n_1700_B)this.getSelected();
        return serverselectionlist$entry != null && serverselectionlist$entry.keyPressed(keyCode, scanCode, modifiers) || super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    protected void moveSelection(o_2488_o.J_1907_R p_241219_1_) {
        this.func_241572_a_(p_241219_1_, p_241612_0_ -> !(p_241612_0_ instanceof R_4764_Y));
    }

    public void n_1700_B(m_3545_A p_148195_1_) {
        this.P_4830_p.clear();
        ServerData pinnedBravohvh = new ServerData("bravohvh.su", n_1700_B, false);
        this.P_4830_p.add(new G_564_y(this.M_588_G, pinnedBravohvh));
        for (int i = 0; i < p_148195_1_.R_4764_Y(); ++i) {
            this.P_4830_p.add(new G_564_y(this.M_588_G, p_148195_1_.n_1700_B(i)));
        }
        this.n_1700_B();
    }

    public void n_1700_B(List<LanServer> p_148194_1_) {
        this.Q_4569_t.clear();
        for (LanServer lanserverinfo : p_148194_1_) {
            this.Q_4569_t.add(new J_1907_R(this.M_588_G, lanserverinfo));
        }
        this.n_1700_B();
    }

    @Override
    protected int getScrollbarPosition() {
        return super.getScrollbarPosition() + 30;
    }

    @Override
    public int getRowWidth() {
        return super.getRowWidth() + 85;
    }

    @Override
    protected boolean isFocused() {
        return this.M_588_G.getListener() == this;
    }

    @Override
    public /* synthetic */ void setSelected(@Nullable o_2488_o.n_1700_B n_1700_B2) {
        this.n_1700_B((n_1700_B)n_1700_B2);
    }

    public static class R_4764_Y
    extends n_1700_B {
        private final MinecraftClient n_1700_B = MinecraftClient.A_4115_X();

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            int i = p_230432_3_ + p_230432_6_ / 2 - 4;
            this.n_1700_B.t_148_a.J_1907_R(p_230432_1_, u_1723_Y, (float)(this.n_1700_B.Y_1740_V.width / 2 - this.n_1700_B.t_148_a.n_1700_B((FormattedText)u_1723_Y) / 2), (float)i, 0xFFFFFF);
            String s = switch ((int)(j_3341_s.J_1907_R() / 300L % 4L)) {
                default -> "O o o";
                case 1, 3 -> "o O o";
                case 2 -> "o o O";
            };
            this.n_1700_B.t_148_a.J_1907_R(p_230432_1_, s, (float)(this.n_1700_B.Y_1740_V.width / 2 - this.n_1700_B.t_148_a.J_1907_R(s) / 2), (float)(i + 9), 0x808080);
        }
    }

    public static abstract class n_1700_B
    extends ObjectSelectionList.n_1700_B<n_1700_B> {
    }

    public class G_564_y
    extends n_1700_B {
        private final q_3131_N J_1907_R;
        private final MinecraftClient R_4764_Y;
        private final ServerData G_564_y;
        private final g_2336_b P_1922_E;
        private String u_1723_Y;
        private T_1114_L v_4262_N;
        private long w_1484_f;

        protected G_564_y(q_3131_N p_i50669_2_, ServerData p_i50669_3_) {
            this.J_1907_R = p_i50669_2_;
            this.G_564_y = p_i50669_3_;
            this.R_4764_Y = MinecraftClient.A_4115_X();
            this.P_1922_E = new g_2336_b("servers/" + String.valueOf(Hashing.sha1().hashUnencodedChars((CharSequence)p_i50669_3_.J_1907_R)) + "/icon");
            this.v_4262_N = (T_1114_L)this.R_4764_Y.G_624_v().J_1907_R(this.P_1922_E);
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            List<x_282_a> list1;
            x_282_a itextcomponent;
            int l;
            if (!this.G_564_y.w_1484_f) {
                this.G_564_y.w_1484_f = true;
                this.G_564_y.P_1922_E = -2L;
                this.G_564_y.G_564_y = U_2871_b.R_4764_Y;
                this.G_564_y.R_4764_Y = U_2871_b.R_4764_Y;
                R_4764_Y.submit(() -> {
                    try {
                        this.J_1907_R.R_4764_Y().n_1700_B(this.G_564_y, () -> this.R_4764_Y.execute(this::n_1700_B));
                    }
                    catch (UnknownHostException unknownhostexception) {
                        this.G_564_y.P_1922_E = -1L;
                        this.G_564_y.G_564_y = v_4262_N;
                    }
                    catch (Exception exception) {
                        this.G_564_y.P_1922_E = -1L;
                        this.G_564_y.G_564_y = w_1484_f;
                    }
                });
            }
            boolean flag = this.G_564_y.u_1723_Y != SharedConstants.n_1700_B().getProtocolVersion();
            boolean pinnedServer = this.G_564_y.J_1907_R != null && this.G_564_y.J_1907_R.equalsIgnoreCase(v_3049_Q.n_1700_B);
            int titleColor = pinnedServer ? 0xFF3A3A : 0xFFFFFF;
            this.R_4764_Y.t_148_a.J_1907_R(p_230432_1_, this.G_564_y.n_1700_B, (float)(p_230432_4_ + 32 + 3), (float)(p_230432_3_ + 1), titleColor);
            if (pinnedServer) {
                C_2701_A.fill(p_230432_1_, p_230432_4_ - 2, p_230432_3_, p_230432_4_, p_230432_3_ + p_230432_6_, -53714);
            }
            List<FormattedCharSequence> list = this.R_4764_Y.t_148_a.J_1907_R(this.G_564_y.G_564_y, p_230432_5_ - 32 - 2);
            for (int i = 0; i < Math.min(list.size(), 2); ++i) {
                this.R_4764_Y.t_148_a.J_1907_R(p_230432_1_, list.get(i), (float)(p_230432_4_ + 32 + 3), (float)(p_230432_3_ + 12 + 9 * i), 0x808080);
            }
            x_282_a itextcomponent1 = flag ? this.G_564_y.v_4262_N.P_1922_E().n_1700_B(D_4024_W.P_4830_p) : this.G_564_y.R_4764_Y;
            int j = this.R_4764_Y.t_148_a.n_1700_B((FormattedText)itextcomponent1);
            this.R_4764_Y.t_148_a.J_1907_R(p_230432_1_, itextcomponent1, (float)(p_230432_4_ + p_230432_5_ - j - 15 - 2), (float)(p_230432_3_ + 1), 0x808080);
            int k = 0;
            if (flag) {
                l = 5;
                itextcomponent = t_148_a;
                list1 = this.G_564_y.t_148_a;
            } else if (this.G_564_y.w_1484_f && this.G_564_y.P_1922_E != -2L) {
                l = this.G_564_y.P_1922_E < 0L ? 5 : (this.G_564_y.P_1922_E < 150L ? 0 : (this.G_564_y.P_1922_E < 300L ? 1 : (this.G_564_y.P_1922_E < 600L ? 2 : (this.G_564_y.P_1922_E < 1000L ? 3 : 4))));
                if (this.G_564_y.P_1922_E < 0L) {
                    itextcomponent = s_956_w;
                    list1 = Collections.emptyList();
                } else {
                    itextcomponent = new F_2904_S("multiplayer.status.ping", this.G_564_y.P_1922_E);
                    list1 = this.G_564_y.t_148_a;
                }
            } else {
                k = 1;
                l = (int)(j_3341_s.J_1907_R() / 100L + (long)(p_230432_2_ * 2) & 7L);
                if (l > 4) {
                    l = 8 - l;
                }
                itextcomponent = u_2550_I;
                list1 = Collections.emptyList();
            }
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            this.R_4764_Y.G_624_v().n_1700_B(C_2701_A.GUI_ICONS_LOCATION);
            C_2701_A.blit(p_230432_1_, p_230432_4_ + p_230432_5_ - 15, p_230432_3_, k * 10, 176 + l * 8, 10, 8, 256, 256);
            String s = this.G_564_y.R_4764_Y();
            if (!Objects.equals(s, this.u_1723_Y)) {
                if (this.n_1700_B(s)) {
                    this.u_1723_Y = s;
                } else {
                    this.G_564_y.n_1700_B((String)null);
                    this.n_1700_B();
                }
            }
            if (this.v_4262_N != null) {
                this.n_1700_B(p_230432_1_, p_230432_4_, p_230432_3_, this.P_1922_E);
            } else {
                this.n_1700_B(p_230432_1_, p_230432_4_, p_230432_3_, G_564_y);
            }
            int i1 = p_230432_7_ - p_230432_4_;
            int j1 = p_230432_8_ - p_230432_3_;
            if (i1 >= p_230432_5_ - 15 && i1 <= p_230432_5_ - 5 && j1 >= 0 && j1 <= 8) {
                this.J_1907_R.n_1700_B(Collections.singletonList(itextcomponent));
            } else if (i1 >= p_230432_5_ - j - 15 - 2 && i1 <= p_230432_5_ - 15 - 2 && j1 >= 0 && j1 <= 8) {
                this.J_1907_R.n_1700_B(list1);
            }
            if (this.R_4764_Y.P_4830_p.c_4037_x || p_230432_9_) {
                this.R_4764_Y.G_624_v().n_1700_B(P_1922_E);
                C_2701_A.fill(p_230432_1_, p_230432_4_, p_230432_3_, p_230432_4_ + 32, p_230432_3_ + 32, -1601138544);
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                int k1 = p_230432_7_ - p_230432_4_;
                int l1 = p_230432_8_ - p_230432_3_;
                if (this.R_4764_Y()) {
                    if (k1 < 32 && k1 > 16) {
                        C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 0.0f, 32.0f, 32, 32, 256, 256);
                    } else {
                        C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 0.0f, 0.0f, 32, 32, 256, 256);
                    }
                }
                if (p_230432_2_ > 0) {
                    if (k1 < 16 && l1 < 16) {
                        C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 96.0f, 32.0f, 32, 32, 256, 256);
                    } else {
                        C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 96.0f, 0.0f, 32, 32, 256, 256);
                    }
                }
                if (p_230432_2_ < this.J_1907_R.G_564_y().R_4764_Y() - 1) {
                    if (k1 < 16 && l1 > 16) {
                        C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 64.0f, 32.0f, 32, 32, 256, 256);
                    } else {
                        C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 64.0f, 0.0f, 32, 32, 256, 256);
                    }
                }
            }
        }

        public void n_1700_B() {
            this.J_1907_R.G_564_y().J_1907_R();
        }

        protected void n_1700_B(g_221_o p_238859_1_, int p_238859_2_, int p_238859_3_, g_2336_b p_238859_4_) {
            this.R_4764_Y.G_624_v().n_1700_B(p_238859_4_);
            c_4037_x.Y_601_j();
            C_2701_A.blit(p_238859_1_, p_238859_2_, p_238859_3_, 0.0f, 0.0f, 32, 32, 32, 32);
            c_4037_x.Y_259_p();
        }

        private boolean R_4764_Y() {
            return true;
        }

        private boolean n_1700_B(@Nullable String p_241614_1_) {
            if (p_241614_1_ == null) {
                this.R_4764_Y.G_624_v().R_4764_Y(this.P_1922_E);
                if (this.v_4262_N != null && this.v_4262_N.J_1907_R() != null) {
                    this.v_4262_N.J_1907_R().close();
                }
                this.v_4262_N = null;
            } else {
                try {
                    i_2518_W nativeimage = i_2518_W.n_1700_B(p_241614_1_);
                    Validate.validState((nativeimage.n_1700_B() == 64 ? 1 : 0) != 0, (String)"Must be 64 pixels wide", (Object[])new Object[0]);
                    Validate.validState((nativeimage.J_1907_R() == 64 ? 1 : 0) != 0, (String)"Must be 64 pixels high", (Object[])new Object[0]);
                    if (this.v_4262_N == null) {
                        this.v_4262_N = new T_1114_L(nativeimage);
                    } else {
                        this.v_4262_N.n_1700_B(nativeimage);
                        this.v_4262_N.n_1700_B();
                    }
                    this.R_4764_Y.G_624_v().n_1700_B(this.P_1922_E, (c_4477_a)this.v_4262_N);
                }
                catch (Throwable throwable) {
                    J_1907_R.error("Invalid icon for server {} ({})", (Object)this.G_564_y.n_1700_B, (Object)this.G_564_y.J_1907_R, (Object)throwable);
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            if (k_2603_m.hasShiftDown()) {
                v_3049_Q serverselectionlist = this.J_1907_R.n_1700_B;
                int i = serverselectionlist.getEventListeners().indexOf(this);
                if (keyCode == 264 && i < this.J_1907_R.G_564_y().R_4764_Y() - 1 || keyCode == 265 && i > 0) {
                    this.n_1700_B(i, keyCode == 264 ? i + 1 : i - 1);
                    return true;
                }
            }
            return super.keyPressed(keyCode, scanCode, modifiers);
        }

        private void n_1700_B(int p_228196_1_, int p_228196_2_) {
            this.J_1907_R.G_564_y().n_1700_B(p_228196_1_, p_228196_2_);
            this.J_1907_R.n_1700_B.n_1700_B(this.J_1907_R.G_564_y());
            n_1700_B serverselectionlist$entry = (n_1700_B)this.J_1907_R.n_1700_B.getEventListeners().get(p_228196_2_);
            this.J_1907_R.n_1700_B.n_1700_B(serverselectionlist$entry);
            v_3049_Q.this.ensureVisible(serverselectionlist$entry);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            double d0 = mouseX - (double)v_3049_Q.this.getRowLeft();
            double d1 = mouseY - (double)v_3049_Q.this.getRowTop(v_3049_Q.this.getEventListeners().indexOf(this));
            if (d0 <= 32.0) {
                if (d0 < 32.0 && d0 > 16.0 && this.R_4764_Y()) {
                    this.J_1907_R.n_1700_B(this);
                    this.J_1907_R.n_1700_B();
                    return true;
                }
                int i = this.J_1907_R.n_1700_B.getEventListeners().indexOf(this);
                if (d0 < 16.0 && d1 < 16.0 && i > 0) {
                    this.n_1700_B(i, i - 1);
                    return true;
                }
                if (d0 < 16.0 && d1 > 16.0 && i < this.J_1907_R.G_564_y().R_4764_Y() - 1) {
                    this.n_1700_B(i, i + 1);
                    return true;
                }
            }
            this.J_1907_R.n_1700_B(this);
            if (j_3341_s.J_1907_R() - this.w_1484_f < 250L) {
                this.J_1907_R.n_1700_B();
            }
            this.w_1484_f = j_3341_s.J_1907_R();
            return false;
        }

        public ServerData J_1907_R() {
            return this.G_564_y;
        }
    }

    public static class J_1907_R
    extends n_1700_B {
        private static final x_282_a R_4764_Y = new F_2904_S("lanServer.title");
        private static final x_282_a G_564_y = new F_2904_S("selectServer.hiddenAddress");
        private final q_3131_N P_1922_E;
        protected final MinecraftClient n_1700_B;
        protected final LanServer J_1907_R;
        private long u_1723_Y;

        protected J_1907_R(q_3131_N p_i47141_1_, LanServer p_i47141_2_) {
            this.P_1922_E = p_i47141_1_;
            this.J_1907_R = p_i47141_2_;
            this.n_1700_B = MinecraftClient.A_4115_X();
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            this.n_1700_B.t_148_a.J_1907_R(p_230432_1_, R_4764_Y, (float)(p_230432_4_ + 32 + 3), (float)(p_230432_3_ + 1), 0xFFFFFF);
            this.n_1700_B.t_148_a.J_1907_R(p_230432_1_, this.J_1907_R.n_1700_B(), (float)(p_230432_4_ + 32 + 3), (float)(p_230432_3_ + 12), 0x808080);
            if (this.n_1700_B.P_4830_p.Q_4569_t) {
                this.n_1700_B.t_148_a.J_1907_R(p_230432_1_, G_564_y, (float)(p_230432_4_ + 32 + 3), (float)(p_230432_3_ + 12 + 11), 0x303030);
            } else {
                this.n_1700_B.t_148_a.J_1907_R(p_230432_1_, this.J_1907_R.J_1907_R(), (float)(p_230432_4_ + 32 + 3), (float)(p_230432_3_ + 12 + 11), 0x303030);
            }
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            this.P_1922_E.n_1700_B(this);
            if (j_3341_s.J_1907_R() - this.u_1723_Y < 250L) {
                this.P_1922_E.n_1700_B();
            }
            this.u_1723_Y = j_3341_s.J_1907_R();
            return false;
        }

        public LanServer n_1700_B() {
            return this.J_1907_R;
        }
    }
}



