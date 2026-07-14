/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.luaj.vm2.Globals
 *  org.luaj.vm2.LuaTable
 *  org.luaj.vm2.LuaValue
 *  org.luaj.vm2.Varargs
 *  org.luaj.vm2.lib.OneArgFunction
 *  org.luaj.vm2.lib.ThreeArgFunction
 *  org.luaj.vm2.lib.TwoArgFunction
 *  org.luaj.vm2.lib.VarArgFunction
 *  org.luaj.vm2.lib.ZeroArgFunction
 *  org.luaj.vm2.lib.jme.JmePlatform
 */
package lightning.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.E_738_L;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.Module;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftClient;
import lightning.product.Packet;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jme.JmePlatform;

public class g_24_p {
    private static final MinecraftClient n_1700_B = MinecraftClient.A_4115_X();
    private final String J_1907_R;
    private final String R_4764_Y;
    private Globals G_564_y;
    private LuaValue P_1922_E;
    private boolean u_1723_Y = false;
    private final Map<Integer, LuaValue> v_4262_N = new HashMap<Integer, LuaValue>();

    public g_24_p(String name, String content) {
        this.J_1907_R = name;
        this.R_4764_Y = content;
    }

    public void n_1700_B() {
        try {
            this.G_564_y = JmePlatform.standardGlobals();
            this.u_1723_Y();
            this.P_1922_E = this.G_564_y.load(this.R_4764_Y);
            this.P_1922_E.call();
            LuaValue onLoad = this.G_564_y.get("onLoad");
            if (onLoad.isfunction()) {
                onLoad.call();
            }
            this.u_1723_Y = true;
        }
        catch (NoClassDefFoundError e) {
            throw new RuntimeException("LuaJ library not found. Please add luaj-jme-3.0.2.jar to libraries folder.", e);
        }
        catch (Exception e) {
            throw new RuntimeException("Error loading script " + this.J_1907_R + ": " + e.getMessage(), e);
        }
    }

    public void J_1907_R() {
        if (!this.u_1723_Y) {
            return;
        }
        try {
            LuaValue onUnload;
            if (this.G_564_y != null && (onUnload = this.G_564_y.get("onUnload")).isfunction()) {
                onUnload.call();
            }
        }
        catch (Exception e) {
            System.err.println("Error in onUnload for script " + this.J_1907_R + ": " + e.getMessage());
        }
        E_738_L.w_1484_f(this.J_1907_R);
        this.v_4262_N.clear();
        this.G_564_y = null;
        this.P_1922_E = null;
        this.u_1723_Y = false;
    }

    private void u_1723_Y() {
        this.G_564_y.set("print", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue arg) {
                E_738_L.n_1700_B(arg.tojstring());
                return LuaValue.NIL;
            }
        });
        this.G_564_y.set("printcolored", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue message, LuaValue color) {
                E_738_L.n_1700_B(message.tojstring(), color.tojstring());
                return LuaValue.NIL;
            }
        });
        this.G_564_y.set("getmodule", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue name) {
                Module module = E_738_L.J_1907_R(name.tojstring());
                return module != null ? LuaValue.userdataOf((Object)module) : LuaValue.NIL;
            }
        });
        this.G_564_y.set("enablemodule", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue name) {
                return LuaValue.valueOf((boolean)E_738_L.R_4764_Y(name.tojstring()));
            }
        });
        this.G_564_y.set("disablemodule", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue name) {
                return LuaValue.valueOf((boolean)E_738_L.G_564_y(name.tojstring()));
            }
        });
        this.G_564_y.set("ismoduleenabled", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue name) {
                return LuaValue.valueOf((boolean)E_738_L.P_1922_E(name.tojstring()));
            }
        });
        this.G_564_y.set("getplayer", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                Object player = E_738_L.n_1700_B();
                return player != null ? LuaValue.userdataOf((Object)player) : LuaValue.NIL;
            }
        });
        this.G_564_y.set("getworld", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                Object world = E_738_L.J_1907_R();
                return world != null ? LuaValue.userdataOf((Object)world) : LuaValue.NIL;
            }
        });
        this.G_564_y.set("getmc", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                MinecraftClient mc = E_738_L.R_4764_Y();
                return mc != null ? LuaValue.userdataOf((Object)mc) : LuaValue.NIL;
            }
        });
        LuaTable playerTable = LuaValue.tableOf();
        LuaTable playerMeta = LuaValue.tableOf();
        playerMeta.set("__index", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue table, LuaValue key) {
                String keyStr = key.toString();
                if (g_24_p.n_1700_B.Y_259_p == null) {
                    return LuaValue.NIL;
                }
                switch (keyStr) {
                    case "x": {
                        return LuaValue.valueOf((double)g_24_p.n_1700_B.Y_259_p.O_3598_v());
                    }
                    case "y": {
                        return LuaValue.valueOf((double)g_24_p.n_1700_B.Y_259_p.X_2960_b());
                    }
                    case "z": {
                        return LuaValue.valueOf((double)g_24_p.n_1700_B.Y_259_p.l_2647_k());
                    }
                    case "yaw": {
                        float yaw = g_24_p.n_1700_B.Y_259_p.p_178_J % 360.0f;
                        if (yaw < 0.0f) {
                            yaw += 360.0f;
                        }
                        return LuaValue.valueOf((double)yaw);
                    }
                    case "pitch": {
                        return LuaValue.valueOf((double)g_24_p.n_1700_B.Y_259_p.f_4016_n);
                    }
                    case "motionX": {
                        return LuaValue.valueOf((double)g_24_p.n_1700_B.Y_259_p.I_4348_c().J_1907_R);
                    }
                    case "motionY": {
                        return LuaValue.valueOf((double)g_24_p.n_1700_B.Y_259_p.I_4348_c().R_4764_Y);
                    }
                    case "motionZ": {
                        return LuaValue.valueOf((double)g_24_p.n_1700_B.Y_259_p.I_4348_c().G_564_y);
                    }
                    case "onGround": {
                        return LuaValue.valueOf((boolean)g_24_p.n_1700_B.Y_259_p.M_1641_O());
                    }
                    case "cameraX": {
                        if (g_24_p.n_1700_B.s_956_w != null) {
                            return LuaValue.valueOf((double)g_24_p.n_1700_B.s_956_w.M_588_G().J_1907_R().J_1907_R);
                        }
                        return LuaValue.valueOf((double)g_24_p.n_1700_B.Y_259_p.O_3598_v());
                    }
                    case "cameraY": {
                        if (g_24_p.n_1700_B.s_956_w != null) {
                            return LuaValue.valueOf((double)g_24_p.n_1700_B.s_956_w.M_588_G().J_1907_R().R_4764_Y);
                        }
                        return LuaValue.valueOf((double)g_24_p.n_1700_B.Y_259_p.X_2960_b());
                    }
                    case "cameraZ": {
                        if (g_24_p.n_1700_B.s_956_w != null) {
                            return LuaValue.valueOf((double)g_24_p.n_1700_B.s_956_w.M_588_G().J_1907_R().G_564_y);
                        }
                        return LuaValue.valueOf((double)g_24_p.n_1700_B.Y_259_p.l_2647_k());
                    }
                    case "cameraYaw": {
                        if (g_24_p.n_1700_B.s_956_w != null) {
                            float camYaw = g_24_p.n_1700_B.s_956_w.M_588_G().P_1922_E() % 360.0f;
                            if (camYaw < 0.0f) {
                                camYaw += 360.0f;
                            }
                            return LuaValue.valueOf((double)camYaw);
                        }
                        float yaw2 = g_24_p.n_1700_B.Y_259_p.p_178_J % 360.0f;
                        if (yaw2 < 0.0f) {
                            yaw2 += 360.0f;
                        }
                        return LuaValue.valueOf((double)yaw2);
                    }
                    case "cameraPitch": {
                        if (g_24_p.n_1700_B.s_956_w != null) {
                            return LuaValue.valueOf((double)g_24_p.n_1700_B.s_956_w.M_588_G().G_564_y());
                        }
                        return LuaValue.valueOf((double)g_24_p.n_1700_B.Y_259_p.f_4016_n);
                    }
                }
                return LuaValue.NIL;
            }
        });
        playerMeta.set("__newindex", (LuaValue)new ThreeArgFunction(this){

            public LuaValue call(LuaValue table, LuaValue key, LuaValue value) {
                String keyStr = key.toString();
                if (g_24_p.n_1700_B.Y_259_p == null) {
                    return LuaValue.NIL;
                }
                switch (keyStr) {
                    case "x": {
                        g_24_p.n_1700_B.Y_259_p.J_1907_R(value.todouble(), g_24_p.n_1700_B.Y_259_p.X_2960_b(), g_24_p.n_1700_B.Y_259_p.l_2647_k());
                        break;
                    }
                    case "y": {
                        g_24_p.n_1700_B.Y_259_p.J_1907_R(g_24_p.n_1700_B.Y_259_p.O_3598_v(), value.todouble(), g_24_p.n_1700_B.Y_259_p.l_2647_k());
                        break;
                    }
                    case "z": {
                        g_24_p.n_1700_B.Y_259_p.J_1907_R(g_24_p.n_1700_B.Y_259_p.O_3598_v(), g_24_p.n_1700_B.Y_259_p.X_2960_b(), value.todouble());
                        break;
                    }
                    case "yaw": {
                        float yaw = (float)value.todouble();
                        yaw %= 360.0f;
                        if (yaw < 0.0f) {
                            yaw += 360.0f;
                        }
                        g_24_p.n_1700_B.Y_259_p.p_178_J = yaw;
                        break;
                    }
                    case "pitch": {
                        float pitch = (float)value.todouble();
                        g_24_p.n_1700_B.Y_259_p.f_4016_n = pitch = Math.max(-90.0f, Math.min(90.0f, pitch));
                        break;
                    }
                    case "motionX": {
                        g_24_p.n_1700_B.Y_259_p.h_1847_R(value.todouble(), g_24_p.n_1700_B.Y_259_p.I_4348_c().R_4764_Y, g_24_p.n_1700_B.Y_259_p.I_4348_c().G_564_y);
                        break;
                    }
                    case "motionY": {
                        g_24_p.n_1700_B.Y_259_p.h_1847_R(g_24_p.n_1700_B.Y_259_p.I_4348_c().J_1907_R, value.todouble(), g_24_p.n_1700_B.Y_259_p.I_4348_c().G_564_y);
                        break;
                    }
                    case "motionZ": {
                        g_24_p.n_1700_B.Y_259_p.h_1847_R(g_24_p.n_1700_B.Y_259_p.I_4348_c().J_1907_R, g_24_p.n_1700_B.Y_259_p.I_4348_c().R_4764_Y, value.todouble());
                        break;
                    }
                    case "cameraX": 
                    case "cameraY": 
                    case "cameraZ": 
                    case "cameraYaw": 
                    case "cameraPitch": {
                        if (keyStr.equals("cameraX")) {
                            g_24_p.n_1700_B.Y_259_p.J_1907_R(value.todouble(), g_24_p.n_1700_B.Y_259_p.X_2960_b(), g_24_p.n_1700_B.Y_259_p.l_2647_k());
                            break;
                        }
                        if (keyStr.equals("cameraY")) {
                            g_24_p.n_1700_B.Y_259_p.J_1907_R(g_24_p.n_1700_B.Y_259_p.O_3598_v(), value.todouble(), g_24_p.n_1700_B.Y_259_p.l_2647_k());
                            break;
                        }
                        if (keyStr.equals("cameraZ")) {
                            g_24_p.n_1700_B.Y_259_p.J_1907_R(g_24_p.n_1700_B.Y_259_p.O_3598_v(), g_24_p.n_1700_B.Y_259_p.X_2960_b(), value.todouble());
                            break;
                        }
                        if (keyStr.equals("cameraYaw")) {
                            float camYaw = (float)value.todouble();
                            if ((camYaw %= 360.0f) < 0.0f) {
                                camYaw += 360.0f;
                            }
                            g_24_p.n_1700_B.Y_259_p.p_178_J = camYaw;
                            break;
                        }
                        if (!keyStr.equals("cameraPitch")) break;
                        float camPitch = (float)value.todouble();
                        g_24_p.n_1700_B.Y_259_p.f_4016_n = camPitch = Math.max(-90.0f, Math.min(90.0f, camPitch));
                    }
                }
                return LuaValue.NIL;
            }
        });
        playerTable.setmetatable((LuaValue)playerMeta);
        this.G_564_y.set("player", (LuaValue)playerTable);
        LuaTable positionTable = LuaValue.tableOf();
        LuaTable positionMeta = LuaValue.tableOf();
        positionMeta.set("__index", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue table, LuaValue key) {
                String keyStr = key.toString();
                double[] pos = E_738_L.P_1922_E();
                switch (keyStr) {
                    case "X": 
                    case "x": {
                        return LuaValue.valueOf((double)pos[0]);
                    }
                    case "Y": 
                    case "y": {
                        return LuaValue.valueOf((double)pos[1]);
                    }
                    case "Z": 
                    case "z": {
                        return LuaValue.valueOf((double)pos[2]);
                    }
                }
                return LuaValue.NIL;
            }
        });
        positionMeta.set("__newindex", (LuaValue)new ThreeArgFunction(this){

            public LuaValue call(LuaValue table, LuaValue key, LuaValue value) {
                String keyStr = key.toString();
                double[] pos = E_738_L.P_1922_E();
                if (keyStr.equals("X") || keyStr.equals("x")) {
                    E_738_L.n_1700_B(value.todouble(), pos[1], pos[2]);
                } else if (keyStr.equals("Y") || keyStr.equals("y")) {
                    E_738_L.n_1700_B(pos[0], value.todouble(), pos[2]);
                } else if (keyStr.equals("Z") || keyStr.equals("z")) {
                    E_738_L.n_1700_B(pos[0], pos[1], value.todouble());
                }
                return LuaValue.NIL;
            }
        });
        positionTable.setmetatable((LuaValue)positionMeta);
        this.G_564_y.set("position", (LuaValue)positionTable);
        LuaTable motionTable = LuaValue.tableOf();
        LuaTable motionMeta = LuaValue.tableOf();
        motionMeta.set("__index", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue table, LuaValue key) {
                String keyStr = key.toString();
                double[] motion = E_738_L.t_148_a();
                switch (keyStr) {
                    case "X": 
                    case "x": {
                        return LuaValue.valueOf((double)motion[0]);
                    }
                    case "Y": 
                    case "y": {
                        return LuaValue.valueOf((double)motion[1]);
                    }
                    case "Z": 
                    case "z": {
                        return LuaValue.valueOf((double)motion[2]);
                    }
                }
                return LuaValue.NIL;
            }
        });
        motionMeta.set("__newindex", (LuaValue)new ThreeArgFunction(this){

            public LuaValue call(LuaValue table, LuaValue key, LuaValue value) {
                String keyStr = key.toString();
                double[] motion = E_738_L.t_148_a();
                if (keyStr.equals("X") || keyStr.equals("x")) {
                    E_738_L.J_1907_R(value.todouble(), motion[1], motion[2]);
                } else if (keyStr.equals("Y") || keyStr.equals("y")) {
                    E_738_L.J_1907_R(motion[0], value.todouble(), motion[2]);
                } else if (keyStr.equals("Z") || keyStr.equals("z")) {
                    E_738_L.J_1907_R(motion[0], motion[1], value.todouble());
                }
                return LuaValue.NIL;
            }
        });
        motionTable.setmetatable((LuaValue)motionMeta);
        this.G_564_y.set("motion", (LuaValue)motionTable);
        LuaTable cameraTable = LuaValue.tableOf();
        LuaTable cameraMeta = LuaValue.tableOf();
        cameraMeta.set("__index", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue table, LuaValue key) {
                String keyStr = key.toString();
                double[] pos = E_738_L.v_4262_N();
                float[] rot = E_738_L.w_1484_f();
                switch (keyStr) {
                    case "X": 
                    case "x": {
                        return LuaValue.valueOf((double)pos[0]);
                    }
                    case "Y": 
                    case "y": {
                        return LuaValue.valueOf((double)pos[1]);
                    }
                    case "Z": 
                    case "z": {
                        return LuaValue.valueOf((double)pos[2]);
                    }
                    case "Yaw": 
                    case "yaw": {
                        return LuaValue.valueOf((double)rot[0]);
                    }
                    case "Pitch": 
                    case "pitch": {
                        return LuaValue.valueOf((double)rot[1]);
                    }
                }
                return LuaValue.NIL;
            }
        });
        cameraMeta.set("__newindex", (LuaValue)new ThreeArgFunction(this){

            public LuaValue call(LuaValue table, LuaValue key, LuaValue value) {
                String keyStr = key.toString();
                double[] pos = E_738_L.v_4262_N();
                float[] rot = E_738_L.w_1484_f();
                if (keyStr.equals("X") || keyStr.equals("x")) {
                    E_738_L.R_4764_Y(value.todouble(), pos[1], pos[2]);
                } else if (keyStr.equals("Y") || keyStr.equals("y")) {
                    E_738_L.R_4764_Y(pos[0], value.todouble(), pos[2]);
                } else if (keyStr.equals("Z") || keyStr.equals("z")) {
                    E_738_L.R_4764_Y(pos[0], pos[1], value.todouble());
                } else if (keyStr.equals("Yaw") || keyStr.equals("yaw")) {
                    E_738_L.J_1907_R((float)value.todouble(), rot[1]);
                } else if (keyStr.equals("Pitch") || keyStr.equals("pitch")) {
                    E_738_L.J_1907_R(rot[0], (float)value.todouble());
                }
                return LuaValue.NIL;
            }
        });
        cameraTable.setmetatable((LuaValue)cameraMeta);
        this.G_564_y.set("camera", (LuaValue)cameraTable);
        this.G_564_y.set("setposition", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 3) {
                    double x = args.arg(1).todouble();
                    double y = args.arg(2).todouble();
                    double z = args.arg(3).todouble();
                    E_738_L.n_1700_B(x, y, z);
                    return LuaValue.TRUE;
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.set("getposition", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                double[] pos = E_738_L.P_1922_E();
                LuaTable table = LuaValue.tableOf();
                table.set(1, (LuaValue)LuaValue.valueOf((double)pos[0]));
                table.set(2, (LuaValue)LuaValue.valueOf((double)pos[1]));
                table.set(3, (LuaValue)LuaValue.valueOf((double)pos[2]));
                return table;
            }
        });
        this.G_564_y.set("setrotation", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue yaw, LuaValue pitch) {
                E_738_L.n_1700_B((float)yaw.todouble(), (float)pitch.todouble());
                return LuaValue.TRUE;
            }
        });
        this.G_564_y.set("getrotation", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                float[] rot = E_738_L.u_1723_Y();
                LuaTable table = LuaValue.tableOf();
                table.set(1, (LuaValue)LuaValue.valueOf((double)rot[0]));
                table.set(2, (LuaValue)LuaValue.valueOf((double)rot[1]));
                return table;
            }
        });
        this.G_564_y.set("getcameraposition", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                double[] pos = E_738_L.v_4262_N();
                LuaTable table = LuaValue.tableOf();
                table.set(1, (LuaValue)LuaValue.valueOf((double)pos[0]));
                table.set(2, (LuaValue)LuaValue.valueOf((double)pos[1]));
                table.set(3, (LuaValue)LuaValue.valueOf((double)pos[2]));
                return table;
            }
        });
        this.G_564_y.set("getcamerarotation", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                float[] rot = E_738_L.w_1484_f();
                LuaTable table = LuaValue.tableOf();
                table.set(1, (LuaValue)LuaValue.valueOf((double)rot[0]));
                table.set(2, (LuaValue)LuaValue.valueOf((double)rot[1]));
                return table;
            }
        });
        this.G_564_y.set("setmotion", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 3) {
                    double x = args.arg(1).todouble();
                    double y = args.arg(2).todouble();
                    double z = args.arg(3).todouble();
                    E_738_L.J_1907_R(x, y, z);
                    return LuaValue.TRUE;
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.set("getmotion", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                double[] motion = E_738_L.t_148_a();
                LuaTable table = LuaValue.tableOf();
                table.set(1, (LuaValue)LuaValue.valueOf((double)motion[0]));
                table.set(2, (LuaValue)LuaValue.valueOf((double)motion[1]));
                table.set(3, (LuaValue)LuaValue.valueOf((double)motion[2]));
                return table;
            }
        });
        this.G_564_y.set("setspeed", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue speed) {
                E_738_L.n_1700_B(speed.todouble());
                return LuaValue.TRUE;
            }
        });
        this.G_564_y.set("setcameraposition", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 3) {
                    double x = args.arg(1).todouble();
                    double y = args.arg(2).todouble();
                    double z = args.arg(3).todouble();
                    E_738_L.R_4764_Y(x, y, z);
                    return LuaValue.TRUE;
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.set("setcamerarotation", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue yaw, LuaValue pitch) {
                E_738_L.J_1907_R((float)yaw.todouble(), (float)pitch.todouble());
                return LuaValue.TRUE;
            }
        });
        this.G_564_y.set("sendpacket", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue packet) {
                Object packetObj;
                if (packet.isuserdata() && (packetObj = packet.touserdata()) instanceof Packet) {
                    return LuaValue.valueOf((boolean)E_738_L.n_1700_B((Packet)packetObj));
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.set("sendpacketwithoutevent", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue packet) {
                Object packetObj;
                if (packet.isuserdata() && (packetObj = packet.touserdata()) instanceof Packet) {
                    return LuaValue.valueOf((boolean)E_738_L.J_1907_R((Packet)packetObj));
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.set("isplayerpacket", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue packet) {
                Object packetObj;
                if (packet.isuserdata() && (packetObj = packet.touserdata()) instanceof Packet) {
                    return LuaValue.valueOf((boolean)E_738_L.R_4764_Y((Packet)packetObj));
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.set("getpacketposition", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue packet) {
                double[] pos;
                Object packetObj;
                if (packet.isuserdata() && (packetObj = packet.touserdata()) instanceof Packet && (pos = E_738_L.G_564_y((Packet)packetObj)) != null) {
                    LuaTable table = LuaValue.tableOf();
                    table.set(1, (LuaValue)LuaValue.valueOf((double)pos[0]));
                    table.set(2, (LuaValue)LuaValue.valueOf((double)pos[1]));
                    table.set(3, (LuaValue)LuaValue.valueOf((double)pos[2]));
                    return table;
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.set("sendpositionpacket", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 4) {
                    double x = args.arg(1).todouble();
                    double y = args.arg(2).todouble();
                    double z = args.arg(3).todouble();
                    boolean onGround = args.arg(4).toboolean();
                    return LuaValue.valueOf((boolean)E_738_L.J_1907_R(x, y, z, onGround));
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.set("sendrotationpacket", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 3) {
                    float yaw = (float)args.arg(1).todouble();
                    float pitch = (float)args.arg(2).todouble();
                    boolean onGround = args.arg(3).toboolean();
                    return LuaValue.valueOf((boolean)E_738_L.J_1907_R(yaw, pitch, onGround));
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.set("sendpositionrotationpacket", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 6) {
                    double x = args.arg(1).todouble();
                    double y = args.arg(2).todouble();
                    double z = args.arg(3).todouble();
                    float yaw = (float)args.arg(4).todouble();
                    float pitch = (float)args.arg(5).todouble();
                    boolean onGround = args.arg(6).toboolean();
                    return LuaValue.valueOf((boolean)E_738_L.J_1907_R(x, y, z, yaw, pitch, onGround));
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.set("createpositionpacket", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 4) {
                    double x = args.arg(1).todouble();
                    double y = args.arg(2).todouble();
                    double z = args.arg(3).todouble();
                    boolean onGround = args.arg(4).toboolean();
                    Packet<?> packet = E_738_L.n_1700_B(x, y, z, onGround);
                    return LuaValue.userdataOf(packet);
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.set("createrotationpacket", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 3) {
                    float yaw = (float)args.arg(1).todouble();
                    float pitch = (float)args.arg(2).todouble();
                    boolean onGround = args.arg(3).toboolean();
                    Packet<?> packet = E_738_L.n_1700_B(yaw, pitch, onGround);
                    return LuaValue.userdataOf(packet);
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.set("createpositionrotationpacket", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 6) {
                    double x = args.arg(1).todouble();
                    double y = args.arg(2).todouble();
                    double z = args.arg(3).todouble();
                    float yaw = (float)args.arg(4).todouble();
                    float pitch = (float)args.arg(5).todouble();
                    boolean onGround = args.arg(6).toboolean();
                    Packet<?> packet = E_738_L.n_1700_B(x, y, z, yaw, pitch, onGround);
                    return LuaValue.userdataOf(packet);
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.set("registerkeybind", (LuaValue)new TwoArgFunction(){

            public LuaValue call(LuaValue key, LuaValue handler) {
                if (handler.isfunction()) {
                    int keyCode = key.toint();
                    g_24_p.this.v_4262_N.put(keyCode, handler);
                    return LuaValue.TRUE;
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.set("getmodulesettings", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue moduleName) {
                List<String> settings = E_738_L.u_1723_Y(moduleName.tojstring());
                LuaValue[] values = new LuaValue[settings.size()];
                for (int i = 0; i < settings.size(); ++i) {
                    values[i] = LuaValue.valueOf((String)settings.get(i));
                }
                return LuaValue.listOf((LuaValue[])values);
            }
        });
        this.G_564_y.set("getsettingvalue", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue moduleName, LuaValue settingName) {
                Object value = E_738_L.J_1907_R(moduleName.tojstring(), settingName.tojstring());
                if (value == null) {
                    return LuaValue.NIL;
                }
                if (value instanceof Boolean) {
                    return LuaValue.valueOf((boolean)((Boolean)value));
                }
                if (value instanceof Number) {
                    return LuaValue.valueOf((double)((Number)value).doubleValue());
                }
                if (value instanceof String) {
                    return LuaValue.valueOf((String)((String)value));
                }
                return LuaValue.valueOf((String)value.toString());
            }
        });
        this.G_564_y.set("setsettingvalue", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() < 3) {
                    return LuaValue.FALSE;
                }
                String moduleName = args.arg(1).tojstring();
                String settingName = args.arg(2).tojstring();
                LuaValue valueArg = args.arg(3);
                Object value = valueArg.isboolean() ? Boolean.valueOf(valueArg.toboolean()) : (valueArg.isnumber() ? Double.valueOf(valueArg.todouble()) : valueArg.tojstring());
                return LuaValue.valueOf((boolean)E_738_L.n_1700_B(moduleName, settingName, value));
            }
        });
        this.G_564_y.set("getsettingtype", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue moduleName, LuaValue settingName) {
                String type = E_738_L.R_4764_Y(moduleName.tojstring(), settingName.tojstring());
                return type != null ? LuaValue.valueOf((String)type) : LuaValue.NIL;
            }
        });
        this.G_564_y.set("getsliderinfo", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue moduleName, LuaValue settingName) {
                String info = E_738_L.G_564_y(moduleName.tojstring(), settingName.tojstring());
                return info != null ? LuaValue.valueOf((String)info) : LuaValue.NIL;
            }
        });
        this.G_564_y.set("getmodeoptions", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue moduleName, LuaValue settingName) {
                String[] options = E_738_L.P_1922_E(moduleName.tojstring(), settingName.tojstring());
                LuaValue[] values = new LuaValue[options.length];
                for (int i = 0; i < options.length; ++i) {
                    values[i] = LuaValue.valueOf((String)options[i]);
                }
                return LuaValue.listOf((LuaValue[])values);
            }
        });
        this.G_564_y.set("createmodule", (LuaValue)new TwoArgFunction(){

            public LuaValue call(LuaValue name, LuaValue category) {
                return LuaValue.valueOf((boolean)E_738_L.n_1700_B(name.tojstring(), category.tojstring(), g_24_p.this.J_1907_R));
            }
        });
        this.G_564_y.set("removemodule", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue name) {
                return LuaValue.valueOf((boolean)E_738_L.v_4262_N(name.tojstring()));
            }
        });
        this.G_564_y.set("addsettingtomodule", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() < 4) {
                    return LuaValue.FALSE;
                }
                String moduleName = args.arg(1).tojstring();
                String settingName = args.arg(2).tojstring();
                String settingType = args.arg(3).tojstring();
                LuaValue defaultValueArg = args.arg(4);
                Object defaultValue = defaultValueArg.isboolean() ? Boolean.valueOf(defaultValueArg.toboolean()) : (defaultValueArg.isnumber() ? Double.valueOf(defaultValueArg.todouble()) : defaultValueArg.tojstring());
                return LuaValue.valueOf((boolean)E_738_L.n_1700_B(moduleName, settingName, settingType, defaultValue));
            }
        });
        this.G_564_y.set("getcustommodules", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                List<String> modules = E_738_L.s_956_w();
                LuaValue[] values = new LuaValue[modules.size()];
                for (int i = 0; i < modules.size(); ++i) {
                    values[i] = LuaValue.valueOf((String)modules.get(i));
                }
                return LuaValue.listOf((LuaValue[])values);
            }
        });
        LuaTable eventTable = LuaValue.tableOf();
        LuaTable eventMeta = LuaValue.tableOf();
        eventMeta.set("__newindex", (LuaValue)new VarArgFunction(){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() < 3) {
                    return LuaValue.NIL;
                }
                String eventName = args.arg(2).tojstring();
                LuaValue handler = args.arg(3);
                E_738_L.n_1700_B(null, eventName, handler, g_24_p.this.J_1907_R);
                return LuaValue.NIL;
            }
        });
        eventMeta.set("__index", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue table, LuaValue key) {
                return LuaValue.NIL;
            }
        });
        eventTable.setmetatable((LuaValue)eventMeta);
        this.G_564_y.set("event", (LuaValue)eventTable);
        this.G_564_y.set("seteventhandler", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() < 3) {
                    return LuaValue.FALSE;
                }
                String moduleName = args.arg(1).tojstring();
                String eventName = args.arg(2).tojstring();
                LuaValue handler = args.arg(3);
                return LuaValue.valueOf((boolean)E_738_L.n_1700_B(moduleName, eventName, handler));
            }
        });
        this.G_564_y.set("render", (LuaValue)LuaValue.tableOf());
        this.G_564_y.get("render").set("drawRect", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 5) {
                    float x = (float)args.arg(1).todouble();
                    float y = (float)args.arg(2).todouble();
                    float width = (float)args.arg(3).todouble();
                    float height = (float)args.arg(4).todouble();
                    int color = args.arg(5).toint();
                    E_738_L.n_1700_B(x, y, width, height, color);
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.get("render").set("drawRoundedRect", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 6) {
                    float x = (float)args.arg(1).todouble();
                    float y = (float)args.arg(2).todouble();
                    float width = (float)args.arg(3).todouble();
                    float height = (float)args.arg(4).todouble();
                    float radius = (float)args.arg(5).todouble();
                    int color = args.arg(6).toint();
                    E_738_L.n_1700_B(x, y, width, height, radius, color);
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.get("render").set("drawCircle", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 4) {
                    float centerX = (float)args.arg(1).todouble();
                    float centerY = (float)args.arg(2).todouble();
                    float radius = (float)args.arg(3).todouble();
                    int color = args.arg(4).toint();
                    E_738_L.n_1700_B(centerX, centerY, radius, color);
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.get("render").set("drawCircleOutline", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 5) {
                    float centerX = (float)args.arg(1).todouble();
                    float centerY = (float)args.arg(2).todouble();
                    float radius = (float)args.arg(3).todouble();
                    int color = args.arg(4).toint();
                    float lineWidth = (float)args.arg(5).todouble();
                    E_738_L.n_1700_B(centerX, centerY, radius, color, lineWidth);
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.get("render").set("draw3DBox", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 8) {
                    double minX = args.arg(1).todouble();
                    double minY = args.arg(2).todouble();
                    double minZ = args.arg(3).todouble();
                    double maxX = args.arg(4).todouble();
                    double maxY = args.arg(5).todouble();
                    double maxZ = args.arg(6).todouble();
                    int color = args.arg(7).toint();
                    boolean fill = args.arg(8).toboolean();
                    E_738_L.n_1700_B(minX, minY, minZ, maxX, maxY, maxZ, color, fill);
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.get("render").set("draw3DCircle", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 5) {
                    double centerX = args.arg(1).todouble();
                    double centerY = args.arg(2).todouble();
                    double centerZ = args.arg(3).todouble();
                    double radius = args.arg(4).todouble();
                    int color = args.arg(5).toint();
                    E_738_L.n_1700_B(centerX, centerY, centerZ, radius, color);
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.get("render").set("beginBatch", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue withColor, LuaValue textured) {
                E_738_L.n_1700_B(withColor.toboolean(), textured.toboolean());
                return LuaValue.NIL;
            }
        });
        this.G_564_y.get("render").set("endBatch", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                E_738_L.u_2550_I();
                return LuaValue.NIL;
            }
        });
        this.G_564_y.get("render").set("isInView", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue entity) {
                Object entityObj;
                if (entity.isuserdata() && (entityObj = entity.touserdata()) instanceof N_4263_v) {
                    return LuaValue.valueOf((boolean)E_738_L.n_1700_B((N_4263_v)entityObj));
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.get("render").set("projectToScreen", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                double z;
                double y;
                double x;
                double[] screenPos;
                if (args.narg() >= 3 && (screenPos = E_738_L.G_564_y(x = args.arg(1).todouble(), y = args.arg(2).todouble(), z = args.arg(3).todouble())) != null) {
                    LuaTable table = LuaValue.tableOf();
                    table.set(1, (LuaValue)LuaValue.valueOf((double)screenPos[0]));
                    table.set(2, (LuaValue)LuaValue.valueOf((double)screenPos[1]));
                    return table;
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.get("render").set("drawText", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 4) {
                    String text = args.arg(1).tojstring();
                    float x = (float)args.arg(2).todouble();
                    float y = (float)args.arg(3).todouble();
                    int color = args.arg(4).toint();
                    int fontSize = 14;
                    if (args.narg() >= 5) {
                        fontSize = args.arg(5).toint();
                    }
                    String fontType = "medium";
                    if (args.narg() >= 6) {
                        fontType = args.arg(6).tojstring();
                    }
                    float width = E_738_L.n_1700_B(text, x, y, color, fontSize, fontType);
                    return LuaValue.valueOf((double)width);
                }
                return LuaValue.valueOf((int)0);
            }
        });
        this.G_564_y.set("getentities", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                List<N_4263_v> entities = E_738_L.P_4830_p();
                LuaValue[] values = new LuaValue[entities.size()];
                for (int i = 0; i < entities.size(); ++i) {
                    values[i] = LuaValue.userdataOf((Object)entities.get(i));
                }
                return LuaValue.listOf((LuaValue[])values);
            }
        });
        this.G_564_y.set("getentitiesbytype", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue typeName) {
                List<N_4263_v> entities = E_738_L.t_148_a(typeName.tojstring());
                LuaValue[] values = new LuaValue[entities.size()];
                for (int i = 0; i < entities.size(); ++i) {
                    values[i] = LuaValue.userdataOf((Object)entities.get(i));
                }
                return LuaValue.listOf((LuaValue[])values);
            }
        });
        this.G_564_y.set("getentitiesinradius", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 4) {
                    double x = args.arg(1).todouble();
                    double y = args.arg(2).todouble();
                    double z = args.arg(3).todouble();
                    double radius = args.arg(4).todouble();
                    List<N_4263_v> entities = E_738_L.n_1700_B(x, y, z, radius);
                    LuaValue[] values = new LuaValue[entities.size()];
                    for (int i = 0; i < entities.size(); ++i) {
                        values[i] = LuaValue.userdataOf((Object)entities.get(i));
                    }
                    return LuaValue.listOf((LuaValue[])values);
                }
                return LuaValue.listOf((LuaValue[])new LuaValue[0]);
            }
        });
        this.G_564_y.set("getclosestentity", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 4) {
                    double maxDistance;
                    double z;
                    double y;
                    double x = args.arg(1).todouble();
                    N_4263_v entity = E_738_L.J_1907_R(x, y = args.arg(2).todouble(), z = args.arg(3).todouble(), maxDistance = args.arg(4).todouble());
                    return entity != null ? LuaValue.userdataOf((Object)entity) : LuaValue.NIL;
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.set("getentityposition", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue entity) {
                Object entityObj;
                if (entity.isuserdata() && (entityObj = entity.touserdata()) instanceof N_4263_v) {
                    double[] pos = E_738_L.J_1907_R((N_4263_v)entityObj);
                    LuaTable table = LuaValue.tableOf();
                    table.set(1, (LuaValue)LuaValue.valueOf((double)pos[0]));
                    table.set(2, (LuaValue)LuaValue.valueOf((double)pos[1]));
                    table.set(3, (LuaValue)LuaValue.valueOf((double)pos[2]));
                    return table;
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.set("getdistancetoentity", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue entity) {
                Object entityObj;
                if (entity.isuserdata() && (entityObj = entity.touserdata()) instanceof N_4263_v) {
                    return LuaValue.valueOf((double)E_738_L.R_4764_Y((N_4263_v)entityObj));
                }
                return LuaValue.valueOf((int)0);
            }
        });
        this.G_564_y.set("getkillauratarget", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                N_4263_v target = E_738_L.Y_601_j();
                return target != null ? LuaValue.userdataOf((Object)target) : LuaValue.NIL;
            }
        });
        this.G_564_y.set("getentityname", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue entity) {
                Object entityObj;
                if (entity.isuserdata() && (entityObj = entity.touserdata()) instanceof N_4263_v) {
                    return LuaValue.valueOf((String)E_738_L.G_564_y((N_4263_v)entityObj));
                }
                return LuaValue.valueOf((String)"");
            }
        });
        this.G_564_y.set("getblockat", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 3) {
                    int z;
                    int y;
                    int x = args.arg(1).toint();
                    K_4074_S state = E_738_L.n_1700_B(x, y = args.arg(2).toint(), z = args.arg(3).toint());
                    return state != null ? LuaValue.userdataOf((Object)state) : LuaValue.NIL;
                }
                return LuaValue.NIL;
            }
        });
        this.G_564_y.set("isblockair", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 3) {
                    int x = args.arg(1).toint();
                    int y = args.arg(2).toint();
                    int z = args.arg(3).toint();
                    return LuaValue.valueOf((boolean)E_738_L.J_1907_R(x, y, z));
                }
                return LuaValue.TRUE;
            }
        });
        this.G_564_y.set("getblockname", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 3) {
                    int x = args.arg(1).toint();
                    int y = args.arg(2).toint();
                    int z = args.arg(3).toint();
                    return LuaValue.valueOf((String)E_738_L.R_4764_Y(x, y, z));
                }
                return LuaValue.valueOf((String)"air");
            }
        });
        this.G_564_y.set("color", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 4) {
                    int r = args.arg(1).toint();
                    int g = args.arg(2).toint();
                    int b = args.arg(3).toint();
                    int a = args.arg(4).toint();
                    return LuaValue.valueOf((int)E_738_L.n_1700_B(r, g, b, a));
                }
                return LuaValue.valueOf((int)0);
            }
        });
        this.G_564_y.set("colorRGB", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 3) {
                    int r = args.arg(1).toint();
                    int g = args.arg(2).toint();
                    int b = args.arg(3).toint();
                    return LuaValue.valueOf((int)E_738_L.G_564_y(r, g, b));
                }
                return LuaValue.valueOf((int)0);
            }
        });
        this.G_564_y.set("getcolorcomponents", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue color) {
                int[] components = E_738_L.n_1700_B(color.toint());
                LuaTable table = LuaValue.tableOf();
                table.set(1, (LuaValue)LuaValue.valueOf((int)components[0]));
                table.set(2, (LuaValue)LuaValue.valueOf((int)components[1]));
                table.set(3, (LuaValue)LuaValue.valueOf((int)components[2]));
                table.set(4, (LuaValue)LuaValue.valueOf((int)components[3]));
                return table;
            }
        });
        this.G_564_y.set("getfps", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                return LuaValue.valueOf((int)E_738_L.h_1847_R());
            }
        });
        this.G_564_y.set("getwindowsize", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                int[] size = E_738_L.Q_4569_t();
                LuaTable table = LuaValue.tableOf();
                table.set(1, (LuaValue)LuaValue.valueOf((int)size[0]));
                table.set(2, (LuaValue)LuaValue.valueOf((int)size[1]));
                return table;
            }
        });
        this.G_564_y.set("getworldtime", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                return LuaValue.valueOf((double)E_738_L.M_182_A());
            }
        });
        this.G_564_y.set("getdistance", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 6) {
                    double x1 = args.arg(1).todouble();
                    double y1 = args.arg(2).todouble();
                    double z1 = args.arg(3).todouble();
                    double x2 = args.arg(4).todouble();
                    double y2 = args.arg(5).todouble();
                    double z2 = args.arg(6).todouble();
                    return LuaValue.valueOf((double)E_738_L.n_1700_B(x1, y1, z1, x2, y2, z2));
                }
                return LuaValue.valueOf((int)0);
            }
        });
        this.G_564_y.set("getdistanceto", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 3) {
                    double x = args.arg(1).todouble();
                    double y = args.arg(2).todouble();
                    double z = args.arg(3).todouble();
                    return LuaValue.valueOf((double)E_738_L.P_1922_E(x, y, z));
                }
                return LuaValue.valueOf((int)0);
            }
        });
        this.G_564_y.set("senddiggingpacket", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 5) {
                    String action = args.arg(1).tojstring();
                    int x = args.arg(2).toint();
                    int y = args.arg(3).toint();
                    int z = args.arg(4).toint();
                    String facing = args.arg(5).tojstring();
                    return LuaValue.valueOf((boolean)E_738_L.J_1907_R(action, x, y, z, facing));
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.set("senduseitempacket", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue hand) {
                return LuaValue.valueOf((boolean)E_738_L.u_2550_I(hand.tojstring()));
            }
        });
        this.G_564_y.set("senduseitemonblockpacket", (LuaValue)new VarArgFunction(this){

            public Varargs onInvoke(Varargs args) {
                if (args.narg() >= 8) {
                    int x = args.arg(1).toint();
                    int y = args.arg(2).toint();
                    int z = args.arg(3).toint();
                    String facing = args.arg(4).tojstring();
                    String hand = args.arg(5).tojstring();
                    float hitX = (float)args.arg(6).todouble();
                    float hitY = (float)args.arg(7).todouble();
                    float hitZ = (float)args.arg(8).todouble();
                    return LuaValue.valueOf((boolean)E_738_L.J_1907_R(x, y, z, facing, hand, hitX, hitY, hitZ));
                }
                return LuaValue.FALSE;
            }
        });
        this.G_564_y.set("sendhelditemchangepacket", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue slot) {
                return LuaValue.valueOf((boolean)E_738_L.R_4764_Y(slot.toint()));
            }
        });
        LuaTable inventoryTable = LuaValue.tableOf();
        LuaTable inventoryMeta = LuaValue.tableOf();
        inventoryMeta.set("__index", (LuaValue)new TwoArgFunction(this){

            public LuaValue call(LuaValue table, LuaValue key) {
                String keyStr = key.toString();
                if (g_24_p.n_1700_B.Y_259_p == null) {
                    return LuaValue.NIL;
                }
                if (keyStr.equals("getSlot")) {
                    return new OneArgFunction(this){

                        public LuaValue call(LuaValue slot) {
                            Z_1993_T stack = E_738_L.G_564_y(slot.toint());
                            if (stack.n_1700_B()) {
                                return LuaValue.NIL;
                            }
                            return LuaValue.userdataOf((Object)stack);
                        }
                    };
                }
                if (keyStr.equals("getSlotName")) {
                    return new OneArgFunction(this){

                        public LuaValue call(LuaValue slot) {
                            return LuaValue.valueOf((String)E_738_L.P_1922_E(slot.toint()));
                        }
                    };
                }
                if (keyStr.equals("getSlotCount")) {
                    return new OneArgFunction(this){

                        public LuaValue call(LuaValue slot) {
                            return LuaValue.valueOf((int)E_738_L.u_1723_Y(slot.toint()));
                        }
                    };
                }
                if (keyStr.equals("isSlotEmpty")) {
                    return new OneArgFunction(this){

                        public LuaValue call(LuaValue slot) {
                            return LuaValue.valueOf((boolean)E_738_L.v_4262_N(slot.toint()));
                        }
                    };
                }
                if (keyStr.equals("findSlot")) {
                    return new OneArgFunction(this){

                        public LuaValue call(LuaValue itemName) {
                            int slot = E_738_L.M_588_G(itemName.tojstring());
                            return slot >= 0 ? LuaValue.valueOf((int)slot) : LuaValue.NIL;
                        }
                    };
                }
                if (keyStr.equals("currentSlot")) {
                    return LuaValue.valueOf((int)E_738_L.t_1786_h());
                }
                if (keyStr.equals("getItemCount")) {
                    return new OneArgFunction(this){

                        public LuaValue call(LuaValue itemName) {
                            return LuaValue.valueOf((int)E_738_L.P_4830_p(itemName.tojstring()));
                        }
                    };
                }
                if (keyStr.equals("getSlotsWithItem")) {
                    return new OneArgFunction(this){

                        public LuaValue call(LuaValue itemName) {
                            List<Integer> slots = E_738_L.h_1847_R(itemName.tojstring());
                            LuaValue[] values = new LuaValue[slots.size()];
                            for (int i = 0; i < slots.size(); ++i) {
                                values[i] = LuaValue.valueOf((int)slots.get(i));
                            }
                            return LuaValue.listOf((LuaValue[])values);
                        }
                    };
                }
                if (keyStr.equals("mainHand")) {
                    Z_1993_T stack = E_738_L.multiplayerClientSuggestionProvider();
                    return stack.n_1700_B() ? LuaValue.NIL : LuaValue.userdataOf((Object)stack);
                }
                if (keyStr.equals("offHand")) {
                    Z_1993_T stack = E_738_L.w_1457_N();
                    return stack.n_1700_B() ? LuaValue.NIL : LuaValue.userdataOf((Object)stack);
                }
                return LuaValue.NIL;
            }
        });
        inventoryMeta.set("__newindex", (LuaValue)new ThreeArgFunction(this){

            public LuaValue call(LuaValue table, LuaValue key, LuaValue value) {
                String keyStr = key.toString();
                if (g_24_p.n_1700_B.Y_259_p == null) {
                    return LuaValue.NIL;
                }
                if (keyStr.equals("currentSlot")) {
                    E_738_L.w_1484_f(value.toint());
                }
                return LuaValue.NIL;
            }
        });
        inventoryTable.setmetatable((LuaValue)inventoryMeta);
        this.G_564_y.set("inventory", (LuaValue)inventoryTable);
        this.G_564_y.set("getchestslotitemname", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                return LuaValue.valueOf((String)E_738_L.Q_2552_b());
            }
        });
        this.G_564_y.set("ischestslotelytra", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                return LuaValue.valueOf((boolean)E_738_L.C_2741_M());
            }
        });
        this.G_564_y.set("findchestplateslot", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                int slot = E_738_L.k_2293_S();
                return slot >= 0 ? LuaValue.valueOf((int)slot) : LuaValue.NIL;
            }
        });
        this.G_564_y.set("findelytraslot", (LuaValue)new ZeroArgFunction(this){

            public LuaValue call() {
                int slot = E_738_L.q_2307_F();
                return slot >= 0 ? LuaValue.valueOf((int)slot) : LuaValue.NIL;
            }
        });
        this.G_564_y.set("swaptochestslot", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue slot) {
                return LuaValue.valueOf((boolean)E_738_L.u_2550_I(slot.toint()));
            }
        });
        this.G_564_y.set("swaptohelmetslot", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue slot) {
                return LuaValue.valueOf((boolean)E_738_L.M_588_G(slot.toint()));
            }
        });
        this.G_564_y.set("swaptoleggingsslot", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue slot) {
                return LuaValue.valueOf((boolean)E_738_L.P_4830_p(slot.toint()));
            }
        });
        this.G_564_y.set("swaptobootsslot", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue slot) {
                return LuaValue.valueOf((boolean)E_738_L.h_1847_R(slot.toint()));
            }
        });
        this.G_564_y.set("getmodulebind", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue moduleName) {
                int bind = E_738_L.Q_4569_t(moduleName.tojstring());
                return bind >= 0 ? LuaValue.valueOf((int)bind) : LuaValue.NIL;
            }
        });
        this.G_564_y.set("iskeydown", (LuaValue)new OneArgFunction(this){

            public LuaValue call(LuaValue keyCode) {
                return LuaValue.valueOf((boolean)E_738_L.Q_4569_t(keyCode.toint()));
            }
        });
    }

    public void n_1700_B(int keyCode, boolean hold) {
        if (!this.u_1723_Y || this.G_564_y == null) {
            return;
        }
        LuaValue handler = this.v_4262_N.get(keyCode);
        if (handler != null && handler.isfunction()) {
            try {
                handler.call((LuaValue)LuaValue.valueOf((boolean)hold));
            }
            catch (Exception e) {
                System.err.println("Error in key handler for key " + keyCode + " in script " + this.J_1907_R + ": " + e.getMessage());
            }
        }
    }

    public boolean R_4764_Y() {
        return this.u_1723_Y;
    }

    public String G_564_y() {
        return this.J_1907_R;
    }

    public Globals P_1922_E() {
        return this.G_564_y;
    }
}



