/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.UUID;
import lightning.product.A_4115_X;
import lightning.product.C_2701_A;
import lightning.product.FormattedText;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.BossEvent;
import lightning.product.h_3270_j;
import lightning.product.m_1761_s;
import lightning.product.LerpingBossEvent;
import lightning.product.x_282_a;
import net.optifine.Config;
import net.optifine.CustomColors;
import net.optifine.reflect.Reflector;

public class Y_3413_I
extends C_2701_A {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/bars.png");
    private final MinecraftClient J_1907_R;
    private final Map<UUID, LerpingBossEvent> R_4764_Y = Maps.newLinkedHashMap();

    public Y_3413_I(MinecraftClient clientIn) {
        this.J_1907_R = clientIn;
    }

    public boolean n_1700_B() {
        return !this.R_4764_Y.isEmpty();
    }

    public int J_1907_R() {
        return this.R_4764_Y.size();
    }

    public void n_1700_B(g_221_o p_238484_1_) {
        h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.J_1907_R);
        A_4115_X.n_1700_B(event);
        if (event.n_1700_B()) {
            return;
        }
        if (!this.R_4764_Y.isEmpty()) {
            int i = this.J_1907_R.RealmsServerPing().Q_4569_t();
            int j = 12;
            for (LerpingBossEvent clientbossinfo : this.R_4764_Y.values()) {
                int k = i / 2 - 91;
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                boolean flag = true;
                int l = 19;
                if (Reflector.ForgeHooksClient_bossBarRenderPre.exists()) {
                    Object object = Reflector.ForgeHooksClient_bossBarRenderPre.call(p_238484_1_, this.J_1907_R.RealmsServerPing(), clientbossinfo, k, j, 19);
                    flag = !Reflector.callBoolean(object, Reflector.Event_isCanceled, new Object[0]);
                    l = Reflector.callInt(object, Reflector.RenderGameOverlayEvent_BossInfo_getIncrement, new Object[0]);
                }
                if (flag) {
                    this.J_1907_R.G_624_v().n_1700_B(n_1700_B);
                    this.n_1700_B(p_238484_1_, k, j, clientbossinfo);
                    x_282_a itextcomponent = clientbossinfo.t_148_a();
                    int i1 = this.J_1907_R.t_148_a.n_1700_B((FormattedText)itextcomponent);
                    int j1 = i / 2 - i1 / 2;
                    int k1 = j - 9;
                    int l1 = 0xFFFFFF;
                    if (Config.isCustomColors()) {
                        l1 = CustomColors.getBossTextColor(l1);
                    }
                    this.J_1907_R.t_148_a.n_1700_B(p_238484_1_, itextcomponent, (float)j1, (float)k1, l1);
                }
                Reflector.ForgeHooksClient_bossBarRenderPost.callVoid(p_238484_1_, this.J_1907_R.RealmsServerPing());
                if ((j += l) < this.J_1907_R.RealmsServerPing().M_182_A() / 3) continue;
                break;
            }
        }
    }

    private void n_1700_B(g_221_o p_238485_1_, int p_238485_2_, int p_238485_3_, BossEvent p_238485_4_) {
        int i;
        this.blit(p_238485_1_, p_238485_2_, p_238485_3_, 0, p_238485_4_.s_956_w().ordinal() * 5 * 2, 182, 5);
        if (p_238485_4_.u_2550_I() != BossEvent.J_1907_R.n_1700_B) {
            this.blit(p_238485_1_, p_238485_2_, p_238485_3_, 0, 80 + (p_238485_4_.u_2550_I().ordinal() - 1) * 5 * 2, 182, 5);
        }
        if ((i = (int)(p_238485_4_.n_1700_B() * 183.0f)) > 0) {
            this.blit(p_238485_1_, p_238485_2_, p_238485_3_, 0, p_238485_4_.s_956_w().ordinal() * 5 * 2 + 5, i, 5);
            if (p_238485_4_.u_2550_I() != BossEvent.J_1907_R.n_1700_B) {
                this.blit(p_238485_1_, p_238485_2_, p_238485_3_, 0, 80 + (p_238485_4_.u_2550_I().ordinal() - 1) * 5 * 2 + 5, i, 5);
            }
        }
    }

    public void n_1700_B(m_1761_s packetIn) {
        if (packetIn.R_4764_Y() == m_1761_s.n_1700_B.n_1700_B) {
            this.R_4764_Y.put(packetIn.J_1907_R(), new LerpingBossEvent(packetIn));
        } else if (packetIn.R_4764_Y() == m_1761_s.n_1700_B.J_1907_R) {
            this.R_4764_Y.remove(packetIn.J_1907_R());
        } else {
            this.R_4764_Y.get(packetIn.J_1907_R()).n_1700_B(packetIn);
        }
    }

    public void R_4764_Y() {
        this.R_4764_Y.clear();
    }

    public boolean G_564_y() {
        if (!this.R_4764_Y.isEmpty()) {
            for (BossEvent h_2277_j2 : this.R_4764_Y.values()) {
                if (!h_2277_j2.P_4830_p()) continue;
                return true;
            }
        }
        return false;
    }

    public boolean P_1922_E() {
        if (!this.R_4764_Y.isEmpty()) {
            for (BossEvent h_2277_j2 : this.R_4764_Y.values()) {
                if (!h_2277_j2.M_588_G()) continue;
                return true;
            }
        }
        return false;
    }

    public boolean u_1723_Y() {
        if (!this.R_4764_Y.isEmpty()) {
            for (BossEvent h_2277_j2 : this.R_4764_Y.values()) {
                if (!h_2277_j2.h_1847_R()) continue;
                return true;
            }
        }
        return false;
    }
}



