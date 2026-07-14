/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.luaj.vm2.LuaValue
 */
package lightning.product;

import java.util.HashMap;
import java.util.Map;
import lightning.product.A_4115_X;
import lightning.product.E_738_L;
import lightning.product.I_4477_R;
import lightning.product.Q_2753_H;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.b_3528_u;
import lightning.product.h_1015_G;
import lightning.product.q_817_e;
import lightning.product.ModuleCategory;
import org.luaj.vm2.LuaValue;

public class R_1796_s
extends Module
implements MinecraftAccess {
    private final Map<String, LuaValue> v_4262_N = new HashMap<String, LuaValue>();

    public R_1796_s(String name, ModuleCategory category) {
        super(name, category);
        A_4115_X.n_1700_B(this);
    }

    public void n_1700_B(String eventName, LuaValue handler) {
        if (handler == null || handler.isnil()) {
            this.v_4262_N.remove(eventName);
        } else {
            this.v_4262_N.put(eventName, handler);
        }
    }

    public LuaValue R_4764_Y(String eventName) {
        return this.v_4262_N.getOrDefault(eventName, LuaValue.NIL);
    }

    @Y_1740_V
    public void n_1700_B(q_817_e event) {
        if (!this.w_1484_f() || R_1796_s.c_3005_b.Y_259_p == null || R_1796_s.c_3005_b.Y_259_p.k_578_l()) {
            return;
        }
        if (!R_1796_s.c_3005_b.Y_259_p.Y_601_j()) {
            return;
        }
        LuaValue handler = this.R_4764_Y("eatslow");
        if (handler != null && !handler.isnil()) {
            try {
                if (handler.isfunction()) {
                    LuaValue result = handler.call();
                    if (result.isboolean() && !result.toboolean()) {
                        event.n_1700_B(true);
                    } else if (result.isnumber() && result.todouble() == 0.0) {
                        event.n_1700_B(true);
                    }
                } else if (handler.isboolean() && !handler.toboolean()) {
                    event.n_1700_B(true);
                } else if (handler.isnumber() && handler.todouble() == 0.0) {
                    event.n_1700_B(true);
                }
            }
            catch (Exception e) {
                System.err.println("Error in eatslow event handler: " + e.getMessage());
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (!this.w_1484_f()) {
            return;
        }
        LuaValue handler = this.R_4764_Y("update");
        if (handler != null && !handler.isnil() && handler.isfunction()) {
            try {
                handler.call();
            }
            catch (Exception e) {
                System.err.println("Error in update event handler: " + e.getMessage());
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u.R_4764_Y event) {
        if (!this.w_1484_f()) {
            return;
        }
        E_738_L.n_1700_B(event.J_1907_R());
        LuaValue handler = this.R_4764_Y("render2d");
        if (handler != null && !handler.isnil() && handler.isfunction()) {
            try {
                handler.call();
            }
            catch (Exception e) {
                System.err.println("Error in render2d event handler: " + e.getMessage());
            }
        }
        E_738_L.M_588_G();
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (!this.w_1484_f()) {
            return;
        }
        LuaValue handler = this.R_4764_Y("render3d");
        if (handler != null && !handler.isnil() && handler.isfunction()) {
            try {
                handler.call();
            }
            catch (Exception e) {
                System.err.println("Error in render3d event handler: " + e.getMessage());
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        if (!this.w_1484_f()) {
            return;
        }
        LuaValue handler = this.R_4764_Y("packet");
        if (handler != null && !handler.isnil() && handler.isfunction()) {
            try {
                LuaValue result = handler.call((LuaValue)LuaValue.valueOf((boolean)event.R_4764_Y()), (LuaValue)(event.G_564_y() != null ? LuaValue.userdataOf(event.G_564_y()) : LuaValue.NIL));
                if (result.isboolean() && !result.toboolean()) {
                    event.n_1700_B(true);
                }
            }
            catch (Exception e) {
                System.err.println("Error in packet event handler: " + e.getMessage());
            }
        }
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
    }

    public void h_1847_R() {
        this.v_4262_N.clear();
    }
}


