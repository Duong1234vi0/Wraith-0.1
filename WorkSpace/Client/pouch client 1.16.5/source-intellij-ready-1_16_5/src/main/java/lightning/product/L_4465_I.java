/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.glfw.GLFWDropCallback
 */
package lightning.product;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import lightning.product.A_4115_X;
import lightning.product.D_590_W;
import lightning.product.J_1907_R;
import lightning.product.K_3372_t;
import lightning.product.Q_4113_P;
import lightning.product.MinecraftClient;
import lightning.product.f_1574_f;
import lightning.product.i_4434_b;
import lightning.product.k_2603_m;
import lightning.product.n_1700_B;
import lightning.product.SmoothDouble;
import lightning.product.r_1166_b;
import lightning.product.u_530_F;
import mods.voicechat.eventforge.InputEvent;
import org.lwjgl.glfw.GLFWDropCallback;

public class L_4465_I {
    private final MinecraftClient n_1700_B;
    private boolean J_1907_R;
    private boolean R_4764_Y;
    private boolean G_564_y;
    private double P_1922_E;
    private double u_1723_Y;
    private int v_4262_N;
    private int w_1484_f = -1;
    private boolean t_148_a = true;
    private int s_956_w;
    private double u_2550_I;
    private final SmoothDouble M_588_G = new SmoothDouble();
    private final SmoothDouble P_4830_p = new SmoothDouble();
    private double h_1847_R;
    private double Q_4569_t;
    private double M_182_A;
    private double t_1786_h = Double.MIN_VALUE;
    private boolean multiplayerClientSuggestionProvider;

    public L_4465_I(MinecraftClient minecraftIn) {
        this.n_1700_B = minecraftIn;
    }

    private void n_1700_B(long handle, int button, int action, int mods) {
        if (handle == this.n_1700_B.RealmsServerPing().t_148_a()) {
            boolean flag;
            boolean bl = flag = action == 1;
            if (MinecraftClient.n_1700_B && button == 0) {
                if (flag) {
                    if ((mods & 2) == 2) {
                        button = 1;
                        ++this.v_4262_N;
                    }
                } else if (this.v_4262_N > 0) {
                    button = 1;
                    --this.v_4262_N;
                }
            }
            int i = button;
            if (flag) {
                if (this.n_1700_B.P_4830_p.c_4037_x && this.s_956_w++ > 0) {
                    return;
                }
                this.w_1484_f = i;
                this.u_2550_I = r_1166_b.J_1907_R();
            } else if (this.w_1484_f != -1) {
                if (this.n_1700_B.P_4830_p.c_4037_x && --this.s_956_w > 0) {
                    return;
                }
                this.w_1484_f = -1;
            }
            A_4115_X.n_1700_B(new InputEvent.RawMouseEvent(button, action, mods));
            boolean[] aboolean = new boolean[]{false};
            if (this.n_1700_B.t_4043_B == null) {
                if (this.n_1700_B.Y_1740_V == null) {
                    if (!this.multiplayerClientSuggestionProvider && flag) {
                        this.w_1484_f();
                    }
                } else {
                    double d0 = this.P_1922_E * (double)this.n_1700_B.RealmsServerPing().Q_4569_t() / (double)this.n_1700_B.RealmsServerPing().P_4830_p();
                    double d1 = this.u_1723_Y * (double)this.n_1700_B.RealmsServerPing().M_182_A() / (double)this.n_1700_B.RealmsServerPing().h_1847_R();
                    if (flag) {
                        k_2603_m.wrapScreenError(() -> {
                            aboolean[0] = this.n_1700_B.Y_1740_V.mouseClicked(d0, d1, i);
                        }, "mouseClicked event handler", this.n_1700_B.Y_1740_V.getClass().getCanonicalName());
                    } else {
                        k_2603_m.wrapScreenError(() -> {
                            aboolean[0] = this.n_1700_B.Y_1740_V.mouseReleased(d0, d1, i);
                        }, "mouseReleased event handler", this.n_1700_B.Y_1740_V.getClass().getCanonicalName());
                    }
                }
            }
            if (!aboolean[0] && (this.n_1700_B.Y_1740_V == null || this.n_1700_B.Y_1740_V.passEvents) && this.n_1700_B.t_4043_B == null) {
                if (i == 0) {
                    this.J_1907_R = flag;
                } else if (i == 2) {
                    this.R_4764_Y = flag;
                } else if (i == 1) {
                    this.G_564_y = flag;
                }
                D_590_W.n_1700_B(Q_4113_P.J_1907_R.R_4764_Y.n_1700_B(i), flag);
                n_1700_B bot1 = null;
                for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                    if (this.n_1700_B.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                    bot1 = bot;
                    break;
                }
                if (flag) {
                    if (bot1 != null) {
                        if (bot1.P_1922_E.Q_2552_b.d_2461_k() && i == 2) {
                            this.n_1700_B.M_588_G.u_1723_Y().J_1907_R();
                            return;
                        }
                    } else if (this.n_1700_B.Y_259_p != null && this.n_1700_B.Y_259_p.d_2461_k() && i == 2) {
                        this.n_1700_B.M_588_G.u_1723_Y().J_1907_R();
                        return;
                    }
                    A_4115_X.n_1700_B(new i_4434_b(i, true));
                    if (this.n_1700_B.Y_259_p != null && this.n_1700_B.Y_259_p.d_2461_k() && i == 2) {
                        this.n_1700_B.M_588_G.u_1723_Y().J_1907_R();
                    } else {
                        D_590_W.n_1700_B(Q_4113_P.J_1907_R.R_4764_Y.n_1700_B(i));
                    }
                } else {
                    A_4115_X.n_1700_B(new i_4434_b(i, false));
                }
            }
        }
    }

    private void n_1700_B(long handle, double xoffset, double yoffset) {
        if (handle == MinecraftClient.A_4115_X().RealmsServerPing().t_148_a()) {
            double d0 = (this.n_1700_B.P_4830_p.B_1668_F ? Math.signum(yoffset) : yoffset) * this.n_1700_B.P_4830_p.e_4240_b;
            n_1700_B bot1 = null;
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                if (this.n_1700_B.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                bot1 = bot;
                break;
            }
            if (this.n_1700_B.t_4043_B == null) {
                if (this.n_1700_B.Y_1740_V != null) {
                    double d1 = this.P_1922_E * (double)this.n_1700_B.RealmsServerPing().Q_4569_t() / (double)this.n_1700_B.RealmsServerPing().P_4830_p();
                    double d2 = this.u_1723_Y * (double)this.n_1700_B.RealmsServerPing().M_182_A() / (double)this.n_1700_B.RealmsServerPing().h_1847_R();
                    this.n_1700_B.Y_1740_V.mouseScrolled(d1, d2, d0);
                } else if (bot1 != null || this.n_1700_B.Y_259_p != null) {
                    boolean isSpectator;
                    if (this.M_182_A != 0.0 && Math.signum(d0) != Math.signum(this.M_182_A)) {
                        this.M_182_A = 0.0;
                    }
                    this.M_182_A += d0;
                    float f1 = (int)this.M_182_A;
                    if (f1 == 0.0f) {
                        return;
                    }
                    this.M_182_A -= (double)f1;
                    boolean bl = isSpectator = bot1 != null ? bot1.P_1922_E.Q_2552_b.d_2461_k() : this.n_1700_B.Y_259_p.d_2461_k();
                    if (isSpectator) {
                        if (this.n_1700_B.M_588_G.u_1723_Y().n_1700_B()) {
                            this.n_1700_B.M_588_G.u_1723_Y().n_1700_B(-f1);
                        } else {
                            float f = u_530_F.n_1700_B(this.n_1700_B.Y_259_p.C_415_h.n_1700_B() + f1 * 0.005f, 0.0f, 0.2f);
                            this.n_1700_B.Y_259_p.C_415_h.n_1700_B(f);
                        }
                    } else {
                        f_1574_f eventCancelHotbar = new f_1574_f();
                        A_4115_X.n_1700_B(eventCancelHotbar);
                        if (!eventCancelHotbar.n_1700_B()) {
                            if (bot1 != null) {
                                bot1.P_1922_E.Q_2552_b.l_1268_F.n_1700_B(f1);
                            } else {
                                this.n_1700_B.Y_259_p.l_1268_F.n_1700_B(f1);
                            }
                        }
                    }
                }
            }
        }
    }

    private void n_1700_B(long window, List<Path> paths) {
        if (this.n_1700_B.Y_1740_V != null) {
            this.n_1700_B.Y_1740_V.addPacks(paths);
        }
    }

    public void n_1700_B(long handle) {
        Q_4113_P.n_1700_B(handle, (handle1, xPos, yPos) -> this.n_1700_B.execute(() -> this.J_1907_R(handle1, xPos, yPos)), (handle1, button, action, modifiers) -> this.n_1700_B.execute(() -> this.n_1700_B(handle1, button, action, modifiers)), (handle1, xOffset, yOffset) -> this.n_1700_B.execute(() -> this.n_1700_B(handle1, xOffset, yOffset)), (window, callbackCount, names) -> {
            Path[] apath = new Path[callbackCount];
            for (int i = 0; i < callbackCount; ++i) {
                apath[i] = Paths.get(GLFWDropCallback.getName((long)names, (int)i), new String[0]);
            }
            this.n_1700_B.execute(() -> this.n_1700_B(window, Arrays.asList(apath)));
        });
    }

    private void J_1907_R(long handle, double xpos, double ypos) {
        if (handle == MinecraftClient.A_4115_X().RealmsServerPing().t_148_a()) {
            k_2603_m iguieventlistener;
            if (this.t_148_a) {
                this.P_1922_E = xpos;
                this.u_1723_Y = ypos;
                this.t_148_a = false;
            }
            if ((iguieventlistener = this.n_1700_B.Y_1740_V) != null && this.n_1700_B.t_4043_B == null) {
                double d0 = xpos * (double)this.n_1700_B.RealmsServerPing().Q_4569_t() / (double)this.n_1700_B.RealmsServerPing().P_4830_p();
                double d1 = ypos * (double)this.n_1700_B.RealmsServerPing().M_182_A() / (double)this.n_1700_B.RealmsServerPing().h_1847_R();
                k_2603_m.wrapScreenError(() -> iguieventlistener.n_1700_B(d0, d1), "mouseMoved event handler", iguieventlistener.getClass().getCanonicalName());
                if (this.w_1484_f != -1 && this.u_2550_I > 0.0) {
                    double d2 = (xpos - this.P_1922_E) * (double)this.n_1700_B.RealmsServerPing().Q_4569_t() / (double)this.n_1700_B.RealmsServerPing().P_4830_p();
                    double d3 = (ypos - this.u_1723_Y) * (double)this.n_1700_B.RealmsServerPing().M_182_A() / (double)this.n_1700_B.RealmsServerPing().h_1847_R();
                    k_2603_m.wrapScreenError(() -> iguieventlistener.mouseDragged(d0, d1, this.w_1484_f, d2, d3), "mouseDragged event handler", iguieventlistener.getClass().getCanonicalName());
                }
            }
            this.n_1700_B.PlayerInfo().n_1700_B("mouse");
            if (this.v_4262_N() && this.n_1700_B.k_3961_g()) {
                this.h_1847_R += xpos - this.P_1922_E;
                this.Q_4569_t += ypos - this.u_1723_Y;
            }
            this.n_1700_B();
            this.P_1922_E = xpos;
            this.u_1723_Y = ypos;
            this.n_1700_B.PlayerInfo().R_4764_Y();
        }
    }

    public void n_1700_B() {
        double d0 = r_1166_b.J_1907_R();
        double d1 = d0 - this.t_1786_h;
        this.t_1786_h = d0;
        if (this.v_4262_N() && this.n_1700_B.k_3961_g()) {
            double d3;
            double d2;
            double d4 = this.n_1700_B.P_4830_p.n_1700_B * (double)0.6f + (double)0.2f;
            double d5 = d4 * d4 * d4 * 8.0;
            if (this.n_1700_B.P_4830_p.S_980_j) {
                double d6 = this.M_588_G.n_1700_B(this.h_1847_R * d5, d1 * d5);
                double d7 = this.P_4830_p.n_1700_B(this.Q_4569_t * d5, d1 * d5);
                d2 = d6;
                d3 = d7;
            } else {
                this.M_588_G.n_1700_B();
                this.P_4830_p.n_1700_B();
                d2 = this.h_1847_R * d5;
                d3 = this.Q_4569_t * d5;
            }
            this.h_1847_R = 0.0;
            this.Q_4569_t = 0.0;
            int i = 1;
            if (this.n_1700_B.P_4830_p.e_2887_G) {
                i = -1;
            }
            this.n_1700_B.D_60_a().n_1700_B(d2, d3);
            n_1700_B bot1 = null;
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                if (this.n_1700_B.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                bot1 = bot;
                bot.P_1922_E.Q_2552_b.n_1700_B(d2, d3 * (double)i);
                break;
            }
            if (bot1 == null && this.n_1700_B.Y_259_p != null) {
                K_3372_t event = new K_3372_t(d2, d3 * (double)i);
                A_4115_X.n_1700_B(event);
                if (!event.n_1700_B()) {
                    this.n_1700_B.Y_259_p.n_1700_B(d2, d3 * (double)i);
                }
            }
        } else {
            this.h_1847_R = 0.0;
            this.Q_4569_t = 0.0;
        }
    }

    public boolean J_1907_R() {
        return this.J_1907_R;
    }

    public boolean R_4764_Y() {
        return this.G_564_y;
    }

    public double G_564_y() {
        return this.P_1922_E;
    }

    public double P_1922_E() {
        return this.u_1723_Y;
    }

    public void u_1723_Y() {
        this.t_148_a = true;
    }

    public boolean v_4262_N() {
        return this.multiplayerClientSuggestionProvider;
    }

    public void w_1484_f() {
        if (this.n_1700_B.k_3961_g() && !this.multiplayerClientSuggestionProvider) {
            if (!MinecraftClient.n_1700_B) {
                D_590_W.n_1700_B();
            }
            this.multiplayerClientSuggestionProvider = true;
            this.P_1922_E = this.n_1700_B.RealmsServerPing().P_4830_p() / 2;
            this.u_1723_Y = this.n_1700_B.RealmsServerPing().h_1847_R() / 2;
            Q_4113_P.n_1700_B(this.n_1700_B.RealmsServerPing().t_148_a(), 212995, this.P_1922_E, this.u_1723_Y);
            this.n_1700_B.n_1700_B((k_2603_m)null);
            this.n_1700_B.H_2857_Y = 10000;
            this.t_148_a = true;
        }
    }

    public void t_148_a() {
        if (this.multiplayerClientSuggestionProvider) {
            this.multiplayerClientSuggestionProvider = false;
            this.P_1922_E = this.n_1700_B.RealmsServerPing().P_4830_p() / 2;
            this.u_1723_Y = this.n_1700_B.RealmsServerPing().h_1847_R() / 2;
            Q_4113_P.n_1700_B(this.n_1700_B.RealmsServerPing().t_148_a(), 212993, this.P_1922_E, this.u_1723_Y);
        }
    }

    public void s_956_w() {
        this.t_148_a = true;
    }
}



