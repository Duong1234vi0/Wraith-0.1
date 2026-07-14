/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 *  org.luaj.vm2.LuaValue
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lightning.product.A_4115_X;
import lightning.product.D_3318_r;
import lightning.product.D_4024_W;
import lightning.product.E_688_b;
import lightning.product.F_1464_b;
import lightning.product.F_489_x;
import lightning.product.BlockHitResult;
import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.K_4074_S;
import lightning.product.N_3268_u;
import lightning.product.N_4263_v;
import lightning.product.O_3016_i;
import lightning.product.P_4526_H;
import lightning.product.Q_4113_P;
import lightning.product.R_1796_s;
import lightning.product.R_2515_i;
import lightning.product.U_2871_b;
import lightning.product.V_3137_a;
import lightning.product.Module;
import lightning.product.Z_1567_W;
import lightning.product.Z_1993_T;
import lightning.product.Z_3504_M;
import lightning.product.Z_3822_q;
import lightning.product.a_408_T;
import lightning.product.b_257_Y;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_2367_h;
import lightning.product.Setting;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.l_3370_o;
import lightning.product.l_3747_P;
import lightning.product.ClientBootstrap;
import lightning.product.p_1183_T;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.KeyBindSetting;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.AttackAura;
import lightning.product.Packet;
import lightning.product.u_1934_K;
import lightning.product.v_1900_v;
import lightning.product.v_2826_q;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;
import org.joml.Vector2f;
import org.luaj.vm2.LuaValue;
import org.lwjgl.opengl.GL11;

public class E_738_L {
    private static final MinecraftClient n_1700_B = MinecraftClient.A_4115_X();
    private static final Map<String, R_1796_s> J_1907_R = new HashMap<String, R_1796_s>();
    private static final Map<String, Set<String>> R_4764_Y = new HashMap<String, Set<String>>();
    private static final Map<String, String> G_564_y = new HashMap<String, String>();
    private static g_221_o P_1922_E = null;

    public static void n_1700_B(String message) {
        v_1900_v.n_1700_B(new U_2871_b(message).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)), new Object[0]);
    }

    public static void n_1700_B(String message, String color) {
        D_4024_W formatting = E_738_L.M_182_A(color);
        v_1900_v.n_1700_B(new U_2871_b(message).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(formatting)), new Object[0]);
    }

    public static Module J_1907_R(String name) {
        if (ClientBootstrap.Y_601_j() == null || ClientBootstrap.Y_601_j().J_1907_R() == null) {
            return null;
        }
        return ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(name).orElse(null);
    }

    public static boolean R_4764_Y(String name) {
        Module module = E_738_L.J_1907_R(name);
        if (module != null && !module.w_1484_f()) {
            module.R_4764_Y();
            return true;
        }
        return false;
    }

    public static boolean G_564_y(String name) {
        Module module = E_738_L.J_1907_R(name);
        if (module != null && module.w_1484_f()) {
            module.R_4764_Y();
            return true;
        }
        return false;
    }

    public static boolean P_1922_E(String name) {
        Module module = E_738_L.J_1907_R(name);
        return module != null && module.w_1484_f();
    }

    public static Object n_1700_B() {
        return E_738_L.n_1700_B.Y_259_p;
    }

    public static Object J_1907_R() {
        return E_738_L.n_1700_B.Y_601_j;
    }

    public static MinecraftClient R_4764_Y() {
        return n_1700_B;
    }

    public static ClientBootstrap G_564_y() {
        return ClientBootstrap.Y_601_j();
    }

    public static void n_1700_B(double x, double y, double z) {
        if (E_738_L.n_1700_B.Y_259_p != null) {
            E_738_L.n_1700_B.Y_259_p.J_1907_R(x, y, z);
        }
    }

    public static double[] P_1922_E() {
        if (E_738_L.n_1700_B.Y_259_p != null) {
            return new double[]{E_738_L.n_1700_B.Y_259_p.O_3598_v(), E_738_L.n_1700_B.Y_259_p.X_2960_b(), E_738_L.n_1700_B.Y_259_p.l_2647_k()};
        }
        return new double[]{0.0, 0.0, 0.0};
    }

    public static void n_1700_B(float yaw, float pitch) {
        if (E_738_L.n_1700_B.Y_259_p != null) {
            if ((yaw %= 360.0f) < 0.0f) {
                yaw += 360.0f;
            }
            pitch = Math.max(-90.0f, Math.min(90.0f, pitch));
            E_738_L.n_1700_B.Y_259_p.p_178_J = yaw;
            E_738_L.n_1700_B.Y_259_p.f_4016_n = pitch;
        }
    }

    public static float[] u_1723_Y() {
        if (E_738_L.n_1700_B.Y_259_p != null) {
            float yaw = E_738_L.n_1700_B.Y_259_p.p_178_J % 360.0f;
            if (yaw < 0.0f) {
                yaw += 360.0f;
            }
            return new float[]{yaw, E_738_L.n_1700_B.Y_259_p.f_4016_n};
        }
        return new float[]{0.0f, 0.0f};
    }

    public static double[] v_4262_N() {
        if (E_738_L.n_1700_B.Y_259_p != null && E_738_L.n_1700_B.s_956_w != null) {
            e_2866_D pos = E_738_L.n_1700_B.s_956_w.M_588_G().J_1907_R();
            return new double[]{pos.J_1907_R, pos.R_4764_Y, pos.G_564_y};
        }
        return E_738_L.P_1922_E();
    }

    public static float[] w_1484_f() {
        if (E_738_L.n_1700_B.Y_259_p != null && E_738_L.n_1700_B.s_956_w != null) {
            float yaw = E_738_L.n_1700_B.s_956_w.M_588_G().P_1922_E() % 360.0f;
            if (yaw < 0.0f) {
                yaw += 360.0f;
            }
            return new float[]{yaw, E_738_L.n_1700_B.s_956_w.M_588_G().G_564_y()};
        }
        return E_738_L.u_1723_Y();
    }

    public static void J_1907_R(double x, double y, double z) {
        if (E_738_L.n_1700_B.Y_259_p != null) {
            E_738_L.n_1700_B.Y_259_p.h_1847_R(x, y, z);
        }
    }

    public static double[] t_148_a() {
        if (E_738_L.n_1700_B.Y_259_p != null) {
            e_2866_D motion = E_738_L.n_1700_B.Y_259_p.I_4348_c();
            return new double[]{motion.J_1907_R, motion.R_4764_Y, motion.G_564_y};
        }
        return new double[]{0.0, 0.0, 0.0};
    }

    public static void n_1700_B(double speed) {
        if (E_738_L.n_1700_B.Y_259_p != null) {
            double yaw;
            if (E_738_L.n_1700_B.Y_259_p.G_564_y.moveForward != 0.0f || E_738_L.n_1700_B.Y_259_p.G_564_y.moveStrafe != 0.0f) {
                yaw = Math.toRadians(E_738_L.n_1700_B.Y_259_p.p_178_J);
                if (E_738_L.n_1700_B.Y_259_p.G_564_y.moveForward < 0.0f) {
                    yaw += Math.PI;
                }
                if (E_738_L.n_1700_B.Y_259_p.G_564_y.moveStrafe > 0.0f) {
                    yaw -= 1.5707963267948966;
                } else if (E_738_L.n_1700_B.Y_259_p.G_564_y.moveStrafe < 0.0f) {
                    yaw += 1.5707963267948966;
                }
            } else {
                yaw = Math.toRadians(E_738_L.n_1700_B.Y_259_p.p_178_J);
            }
            double motionX = -Math.sin(yaw) * speed;
            double motionZ = Math.cos(yaw) * speed;
            E_738_L.n_1700_B.Y_259_p.h_1847_R(motionX, E_738_L.n_1700_B.Y_259_p.I_4348_c().R_4764_Y, motionZ);
        }
    }

    public static void R_4764_Y(double x, double y, double z) {
        E_738_L.n_1700_B(x, y, z);
    }

    public static void J_1907_R(float yaw, float pitch) {
        if (E_738_L.n_1700_B.Y_259_p != null) {
            E_738_L.n_1700_B(yaw, pitch);
        }
    }

    public static boolean n_1700_B(Packet<?> packet) {
        if (E_738_L.n_1700_B.Y_259_p != null && E_738_L.n_1700_B.Y_259_p.n_1700_B != null && packet != null) {
            try {
                E_738_L.n_1700_B.Y_259_p.n_1700_B.n_1700_B(packet);
                return true;
            }
            catch (Exception e) {
                System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u043e\u0442\u043f\u0440\u0430\u0432\u043a\u0435 \u043f\u0430\u043a\u0435\u0442\u0430: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public static boolean J_1907_R(Packet<?> packet) {
        if (E_738_L.n_1700_B.Y_259_p != null && E_738_L.n_1700_B.Y_259_p.n_1700_B != null && packet != null) {
            try {
                E_738_L.n_1700_B.Y_259_p.n_1700_B.J_1907_R(packet);
                return true;
            }
            catch (Exception e) {
                System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u043e\u0442\u043f\u0440\u0430\u0432\u043a\u0435 \u043f\u0430\u043a\u0435\u0442\u0430 \u0431\u0435\u0437 \u0441\u043e\u0431\u044b\u0442\u0438\u044f: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public static boolean R_4764_Y(Packet<?> packet) {
        return packet instanceof N_3268_u;
    }

    public static double[] G_564_y(Packet<?> packet) {
        if (packet instanceof N_3268_u) {
            N_3268_u playerPacket = (N_3268_u)packet;
            if (packet instanceof N_3268_u.n_1700_B || packet instanceof N_3268_u.J_1907_R) {
                double x = playerPacket.n_1700_B(0.0);
                double y = playerPacket.J_1907_R(0.0);
                double z = playerPacket.R_4764_Y(0.0);
                return new double[]{x, y, z};
            }
        }
        return null;
    }

    public static Packet<?> n_1700_B(double x, double y, double z, boolean onGround) {
        return new N_3268_u.n_1700_B(x, y, z, onGround);
    }

    public static Packet<?> n_1700_B(float yaw, float pitch, boolean onGround) {
        if ((yaw %= 360.0f) < 0.0f) {
            yaw += 360.0f;
        }
        pitch = Math.max(-90.0f, Math.min(90.0f, pitch));
        return new N_3268_u.R_4764_Y(yaw, pitch, onGround);
    }

    public static Packet<?> n_1700_B(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        if ((yaw %= 360.0f) < 0.0f) {
            yaw += 360.0f;
        }
        pitch = Math.max(-90.0f, Math.min(90.0f, pitch));
        return new N_3268_u.J_1907_R(x, y, z, yaw, pitch, onGround);
    }

    public static boolean J_1907_R(double x, double y, double z, boolean onGround) {
        return E_738_L.n_1700_B(E_738_L.n_1700_B(x, y, z, onGround));
    }

    public static boolean J_1907_R(float yaw, float pitch, boolean onGround) {
        return E_738_L.n_1700_B(E_738_L.n_1700_B(yaw, pitch, onGround));
    }

    public static boolean J_1907_R(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        return E_738_L.n_1700_B(E_738_L.n_1700_B(x, y, z, yaw, pitch, onGround));
    }

    public static List<String> u_1723_Y(String moduleName) {
        Module module = E_738_L.J_1907_R(moduleName);
        if (module == null) {
            return new ArrayList<String>();
        }
        ArrayList<String> settingNames = new ArrayList<String>();
        for (Setting<?> setting : module.u_2550_I()) {
            settingNames.add(setting.n_1700_B());
        }
        return settingNames;
    }

    public static Object J_1907_R(String moduleName, String settingName) {
        Module module = E_738_L.J_1907_R(moduleName);
        if (module == null) {
            return null;
        }
        for (Setting<?> setting : module.u_2550_I()) {
            if (!setting.n_1700_B().equalsIgnoreCase(settingName)) continue;
            return setting.J_1907_R();
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean n_1700_B(String moduleName, String settingName, Object value) {
        Module module = E_738_L.J_1907_R(moduleName);
        if (module == null) {
            return false;
        }
        for (Setting<?> setting : module.u_2550_I()) {
            if (!setting.n_1700_B().equalsIgnoreCase(settingName)) continue;
            try {
                if (setting instanceof BooleanSetting) {
                    ((BooleanSetting)setting).n_1700_B((Boolean)value);
                    return true;
                } else if (setting instanceof NumberSetting) {
                    if (!(value instanceof Number)) return false;
                    ((NumberSetting)setting).n_1700_B(Float.valueOf(((Number)value).floatValue()));
                    return true;
                } else if (setting instanceof ModeSetting) {
                    ((ModeSetting)setting).n_1700_B((String)value);
                    return true;
                } else if (setting instanceof O_3016_i) {
                    ((O_3016_i)setting).n_1700_B((String)value);
                    return true;
                } else if (setting instanceof h_2367_h) {
                    if (!(value instanceof Number)) return false;
                    ((h_2367_h)setting).n_1700_B(((Number)value).intValue());
                    return true;
                } else if (setting instanceof KeyBindSetting) {
                    if (!(value instanceof Number)) return false;
                    ((KeyBindSetting)setting).n_1700_B(((Number)value).intValue());
                    return true;
                } else {
                    System.err.println("Cannot set value for unknown setting type");
                    return false;
                }
            }
            catch (Exception e) {
                System.err.println("Error setting setting value: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public static String R_4764_Y(String moduleName, String settingName) {
        Module module = E_738_L.J_1907_R(moduleName);
        if (module == null) {
            return null;
        }
        for (Setting<?> setting : module.u_2550_I()) {
            if (!setting.n_1700_B().equalsIgnoreCase(settingName)) continue;
            if (setting instanceof BooleanSetting) {
                return "boolean";
            }
            if (setting instanceof NumberSetting) {
                return "float";
            }
            if (setting instanceof ModeSetting) {
                return "mode";
            }
            if (setting instanceof O_3016_i) {
                return "string";
            }
            if (setting instanceof h_2367_h) {
                return "color";
            }
            if (setting instanceof KeyBindSetting) {
                return "bind";
            }
            return "unknown";
        }
        return null;
    }

    public static String G_564_y(String moduleName, String settingName) {
        Module module = E_738_L.J_1907_R(moduleName);
        if (module == null) {
            return null;
        }
        for (Setting<?> setting : module.u_2550_I()) {
            if (!setting.n_1700_B().equalsIgnoreCase(settingName) || !(setting instanceof NumberSetting)) continue;
            NumberSetting slider = (NumberSetting)setting;
            return String.format("min=%.2f,max=%.2f,increment=%.2f", Float.valueOf(slider.G_564_y), Float.valueOf(slider.P_1922_E), Float.valueOf(slider.u_1723_Y));
        }
        return null;
    }

    public static String[] P_1922_E(String moduleName, String settingName) {
        Module module = E_738_L.J_1907_R(moduleName);
        if (module == null) {
            return new String[0];
        }
        for (Setting<?> setting : module.u_2550_I()) {
            if (!setting.n_1700_B().equalsIgnoreCase(settingName) || !(setting instanceof ModeSetting)) continue;
            ModeSetting mode = (ModeSetting)setting;
            return mode.G_564_y;
        }
        return new String[0];
    }

    public static boolean n_1700_B(String name, String category, String scriptName) {
        if (ClientBootstrap.Y_601_j() == null || ClientBootstrap.Y_601_j().J_1907_R() == null) {
            return false;
        }
        if (E_738_L.J_1907_R(name) != null) {
            return false;
        }
        try {
            ModuleCategory cat = ModuleCategory.valueOf(category);
            R_1796_s module = new R_1796_s(name, cat);
            ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y().add(module);
            J_1907_R.put(name, module);
            if (scriptName != null && !scriptName.isEmpty()) {
                R_4764_Y.computeIfAbsent(scriptName, k -> new HashSet()).add(name);
                G_564_y.put(scriptName, name);
            }
            if (ClientBootstrap.Y_601_j().multiplayerClientSuggestionProvider() != null) {
                ClientBootstrap.Y_601_j().multiplayerClientSuggestionProvider().n_1700_B();
            }
            return true;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean u_1723_Y(String name, String category) {
        return E_738_L.n_1700_B(name, category, null);
    }

    public static boolean v_4262_N(String name) {
        if (ClientBootstrap.Y_601_j() == null || ClientBootstrap.Y_601_j().J_1907_R() == null) {
            return false;
        }
        R_1796_s module = J_1907_R.remove(name);
        if (module != null) {
            if (module.w_1484_f()) {
                module.R_4764_Y();
            }
            A_4115_X.J_1907_R(module);
            ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y().remove(module);
            for (Map.Entry<String, Set<String>> entry : R_4764_Y.entrySet()) {
                entry.getValue().remove(name);
                if (G_564_y.get(entry.getKey()) == null || !G_564_y.get(entry.getKey()).equals(name)) continue;
                G_564_y.remove(entry.getKey());
            }
            if (ClientBootstrap.Y_601_j().multiplayerClientSuggestionProvider() != null) {
                ClientBootstrap.Y_601_j().multiplayerClientSuggestionProvider().n_1700_B();
            }
            return true;
        }
        return false;
    }

    public static void w_1484_f(String scriptName) {
        if (scriptName == null || scriptName.isEmpty()) {
            return;
        }
        Set<String> moduleNames = R_4764_Y.remove(scriptName);
        if (moduleNames != null && !moduleNames.isEmpty()) {
            ArrayList<String> modulesToRemove = new ArrayList<String>(moduleNames);
            for (String moduleName : modulesToRemove) {
                E_738_L.v_4262_N(moduleName);
            }
        }
    }

    public static boolean n_1700_B(String moduleName, String settingName, String settingType, Object defaultValue) {
        Module module = E_738_L.J_1907_R(moduleName);
        if (module == null) {
            return false;
        }
        for (Setting<?> setting : module.u_2550_I()) {
            if (!setting.n_1700_B().equalsIgnoreCase(settingName)) continue;
            return false;
        }
        try {
            Setting setting = null;
            switch (settingType.toLowerCase()) {
                case "boolean": {
                    setting = new BooleanSetting(settingName, (Boolean)defaultValue);
                    break;
                }
                case "float": 
                case "slider": {
                    if (!(defaultValue instanceof Number)) break;
                    float defVal = ((Number)defaultValue).floatValue();
                    NumberSetting sliderSetting = new NumberSetting(settingName, defVal, 0.0f, 100.0f, 0.1f);
                    sliderSetting.n_1700_B(Float.valueOf(defVal));
                    setting = sliderSetting;
                    break;
                }
                case "mode": {
                    if (!(defaultValue instanceof String)) break;
                    setting = new ModeSetting(settingName, (String)defaultValue, (String)defaultValue);
                    break;
                }
                case "string": {
                    if (!(defaultValue instanceof String)) break;
                    O_3016_i stringSetting = new O_3016_i(settingName);
                    stringSetting.J_1907_R((String)defaultValue);
                    setting = stringSetting;
                    break;
                }
                case "color": {
                    if (!(defaultValue instanceof Number)) break;
                    int colorValue = ((Number)defaultValue).intValue();
                    setting = new h_2367_h(settingName, true, colorValue);
                    break;
                }
                case "bind": {
                    if (defaultValue instanceof Number) {
                        int bindValue = ((Number)defaultValue).intValue();
                        setting = new KeyBindSetting(settingName);
                        if (!(setting instanceof KeyBindSetting)) break;
                        ((KeyBindSetting)setting).n_1700_B(bindValue);
                        break;
                    }
                    setting = new KeyBindSetting(settingName);
                }
            }
            if (setting != null) {
                module.n_1700_B(setting);
                return true;
            }
        }
        catch (Exception e) {
            System.err.println("Error adding setting: " + e.getMessage());
        }
        return false;
    }

    public static List<String> s_956_w() {
        return new ArrayList<String>(J_1907_R.keySet());
    }

    public static boolean n_1700_B(String moduleName, String eventName, LuaValue handler, String scriptName) {
        if ((moduleName == null || moduleName.isEmpty()) && scriptName != null && !scriptName.isEmpty()) {
            moduleName = G_564_y.get(scriptName);
        }
        if (moduleName == null || moduleName.isEmpty()) {
            return false;
        }
        Module module = E_738_L.J_1907_R(moduleName);
        if (module instanceof R_1796_s) {
            ((R_1796_s)module).n_1700_B(eventName, handler);
            return true;
        }
        return false;
    }

    public static boolean n_1700_B(String moduleName, String eventName, LuaValue handler) {
        return E_738_L.n_1700_B(moduleName, eventName, handler, null);
    }

    private static D_4024_W M_182_A(String color) {
        if (color == null) {
            return D_4024_W.M_182_A;
        }
        switch (color.toLowerCase()) {
            case "black": {
                return D_4024_W.n_1700_B;
            }
            case "dark_blue": {
                return D_4024_W.J_1907_R;
            }
            case "dark_green": {
                return D_4024_W.R_4764_Y;
            }
            case "dark_aqua": {
                return D_4024_W.G_564_y;
            }
            case "dark_red": {
                return D_4024_W.P_1922_E;
            }
            case "dark_purple": {
                return D_4024_W.u_1723_Y;
            }
            case "gold": {
                return D_4024_W.v_4262_N;
            }
            case "gray": {
                return D_4024_W.w_1484_f;
            }
            case "dark_gray": {
                return D_4024_W.t_148_a;
            }
            case "blue": {
                return D_4024_W.s_956_w;
            }
            case "green": {
                return D_4024_W.u_2550_I;
            }
            case "aqua": {
                return D_4024_W.M_588_G;
            }
            case "red": {
                return D_4024_W.P_4830_p;
            }
            case "light_purple": {
                return D_4024_W.h_1847_R;
            }
            case "yellow": {
                return D_4024_W.Q_4569_t;
            }
            case "white": {
                return D_4024_W.M_182_A;
            }
        }
        return D_4024_W.M_182_A;
    }

    public static void n_1700_B(float x, float y, float width, float height, int color) {
        F_489_x.n_1700_B(true, true);
        F_489_x.J_1907_R(x, y, width, height, color);
        F_489_x.n_1700_B();
    }

    public static void n_1700_B(float x, float y, float width, float height, float radius, int color) {
        F_489_x.n_1700_B(x, y, width, height, radius, color);
    }

    public static void n_1700_B(float centerX, float centerY, float radius, int color) {
        F_489_x.n_1700_B(centerX, centerY, radius, color);
    }

    public static void n_1700_B(float centerX, float centerY, float radius, int color, float lineWidth) {
        F_489_x.n_1700_B(centerX, centerY, radius, color, lineWidth);
    }

    public static void n_1700_B(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, int color, boolean fill) {
        if (E_738_L.n_1700_B.Y_601_j == null || E_738_L.n_1700_B.Y_259_p == null) {
            return;
        }
        e_2866_D cameraPos = E_738_L.n_1700_B.O_508_d().J_1907_R.J_1907_R();
        I_4817_s bb = new I_4817_s(minX - cameraPos.J_1907_R, minY - cameraPos.R_4764_Y, minZ - cameraPos.G_564_y, maxX - cameraPos.J_1907_R, maxY - cameraPos.R_4764_Y, maxZ - cameraPos.G_564_y);
        F_489_x.n_1700_B(bb, color, fill);
    }

    public static void n_1700_B(double centerX, double centerY, double centerZ, double radius, int color) {
        if (E_738_L.n_1700_B.Y_601_j == null || E_738_L.n_1700_B.Y_259_p == null) {
            return;
        }
        e_2866_D cameraPos = E_738_L.n_1700_B.O_508_d().J_1907_R.J_1907_R();
        c_4037_x.v_4276_D();
        c_4037_x.e_4240_b();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.t_1786_h();
        c_4037_x.q_2307_F();
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        c_4037_x.G_564_y(2.0f);
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r buffer = tessellator.R_4764_Y();
        buffer.n_1700_B(1, E_688_b.Y_601_j);
        int segments = 64;
        for (int i = 0; i < segments; ++i) {
            double angle1 = Math.PI * 2 * (double)i / (double)segments;
            double angle2 = Math.PI * 2 * (double)(i + 1) / (double)segments;
            double x1 = centerX + Math.cos(angle1) * radius;
            double z1 = centerZ + Math.sin(angle1) * radius;
            double x2 = centerX + Math.cos(angle2) * radius;
            double z2 = centerZ + Math.sin(angle2) * radius;
            double y = centerY + 0.01;
            buffer.pos(x1 - cameraPos.J_1907_R, y - cameraPos.R_4764_Y, z1 - cameraPos.G_564_y).n_1700_B(r, g, b, a).endVertex();
            buffer.pos(x2 - cameraPos.J_1907_R, y - cameraPos.R_4764_Y, z2 - cameraPos.G_564_y).n_1700_B(r, g, b, a).endVertex();
        }
        tessellator.J_1907_R();
        GL11.glDisable((int)2848);
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    public static void n_1700_B(boolean withColor, boolean textured) {
        F_489_x.n_1700_B(withColor, textured);
    }

    public static void u_2550_I() {
        F_489_x.n_1700_B();
    }

    public static boolean n_1700_B(N_4263_v entity) {
        return F_489_x.n_1700_B(entity);
    }

    public static double[] G_564_y(double x, double y, double z) {
        Vector2f screenPos = v_2826_q.n_1700_B(x, y, z);
        if (screenPos.x == Float.MAX_VALUE) {
            return null;
        }
        return new double[]{screenPos.x, screenPos.y};
    }

    public static void n_1700_B(g_221_o stack) {
        P_1922_E = stack;
    }

    public static void M_588_G() {
        P_1922_E = null;
    }

    public static float n_1700_B(String text, float x, float y, int color, int fontSize, String fontType) {
        if (P_1922_E == null) {
            return 0.0f;
        }
        Z_3822_q font = null;
        try {
            switch (fontType.toLowerCase()) {
                case "medium": {
                    if (fontSize < 1 || fontSize >= l_3370_o.J_1907_R.length) break;
                    font = l_3370_o.J_1907_R[fontSize];
                    break;
                }
                case "bold": {
                    if (fontSize < 1 || fontSize >= l_3370_o.P_1922_E.length) break;
                    font = l_3370_o.P_1922_E[fontSize];
                    break;
                }
                case "regular": {
                    if (fontSize < 1 || fontSize >= l_3370_o.R_4764_Y.length) break;
                    font = l_3370_o.R_4764_Y[fontSize];
                    break;
                }
                case "semibold": {
                    if (fontSize < 1 || fontSize >= l_3370_o.G_564_y.length) break;
                    font = l_3370_o.G_564_y[fontSize];
                }
            }
            if (font == null) {
                int defaultSize = Math.min(14, l_3370_o.J_1907_R.length - 1);
                font = l_3370_o.J_1907_R[defaultSize];
            }
            return font.n_1700_B(P_1922_E, text, (double)x, (double)y, color);
        }
        catch (Exception e) {
            System.err.println("Error rendering text: " + e.getMessage());
            return 0.0f;
        }
    }

    public static List<N_4263_v> P_4830_p() {
        ArrayList<N_4263_v> result = new ArrayList<N_4263_v>();
        if (E_738_L.n_1700_B.Y_601_j != null) {
            for (N_4263_v entity : E_738_L.n_1700_B.Y_601_j.J_1907_R()) {
                result.add(entity);
            }
        }
        return result;
    }

    public static List<N_4263_v> t_148_a(String typeName) {
        ArrayList<N_4263_v> result = new ArrayList<N_4263_v>();
        if (E_738_L.n_1700_B.Y_601_j == null) {
            return result;
        }
        for (N_4263_v entity : E_738_L.n_1700_B.Y_601_j.J_1907_R()) {
            g_2336_b key = V_3137_a.g_221_o.J_1907_R(entity.f_4016_n());
            if (key == null || !key.toString().contains(typeName.toLowerCase())) continue;
            result.add(entity);
        }
        return result;
    }

    public static List<N_4263_v> n_1700_B(double x, double y, double z, double radius) {
        ArrayList<N_4263_v> result = new ArrayList<N_4263_v>();
        if (E_738_L.n_1700_B.Y_601_j == null) {
            return result;
        }
        double radiusSq = radius * radius;
        for (N_4263_v entity : E_738_L.n_1700_B.Y_601_j.J_1907_R()) {
            double dz;
            double dy;
            double dx = entity.O_3598_v() - x;
            if (!(dx * dx + (dy = entity.X_2960_b() - y) * dy + (dz = entity.l_2647_k() - z) * dz <= radiusSq)) continue;
            result.add(entity);
        }
        return result;
    }

    public static N_4263_v J_1907_R(double x, double y, double z, double maxDistance) {
        if (E_738_L.n_1700_B.Y_601_j == null) {
            return null;
        }
        N_4263_v closest = null;
        double closestDist = maxDistance * maxDistance;
        for (N_4263_v entity : E_738_L.n_1700_B.Y_601_j.J_1907_R()) {
            double dz;
            double dy;
            double dx = entity.O_3598_v() - x;
            double dist = dx * dx + (dy = entity.X_2960_b() - y) * dy + (dz = entity.l_2647_k() - z) * dz;
            if (!(dist < closestDist)) continue;
            closestDist = dist;
            closest = entity;
        }
        return closest;
    }

    public static double[] J_1907_R(N_4263_v entity) {
        if (entity != null) {
            return new double[]{entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k()};
        }
        return new double[]{0.0, 0.0, 0.0};
    }

    public static double R_4764_Y(N_4263_v entity) {
        if (E_738_L.n_1700_B.Y_259_p != null && entity != null) {
            return E_738_L.n_1700_B.Y_259_p.R_4764_Y(entity);
        }
        return 0.0;
    }

    public static K_4074_S n_1700_B(int x, int y, int z) {
        if (E_738_L.n_1700_B.Y_601_j != null) {
            return E_738_L.n_1700_B.Y_601_j.getBlockState(new c_1514_x(x, y, z));
        }
        return null;
    }

    public static boolean J_1907_R(int x, int y, int z) {
        if (E_738_L.n_1700_B.Y_601_j != null) {
            return E_738_L.n_1700_B.Y_601_j.u_1723_Y(new c_1514_x(x, y, z));
        }
        return true;
    }

    public static String R_4764_Y(int x, int y, int z) {
        K_4074_S state = E_738_L.n_1700_B(x, y, z);
        if (state != null) {
            return state.J_1907_R().P_4830_p();
        }
        return "air";
    }

    public static int n_1700_B(int r, int g, int b, int a) {
        return a << 24 | r << 16 | g << 8 | b;
    }

    public static int G_564_y(int r, int g, int b) {
        return E_738_L.n_1700_B(r, g, b, 255);
    }

    public static int[] n_1700_B(int color) {
        int a = color >> 24 & 0xFF;
        int r = color >> 16 & 0xFF;
        int g = color >> 8 & 0xFF;
        int b = color & 0xFF;
        return new int[]{r, g, b, a};
    }

    public static int h_1847_R() {
        return MinecraftClient.x_607_J;
    }

    public static int[] Q_4569_t() {
        return new int[]{n_1700_B.RealmsServerPing().Q_4569_t(), n_1700_B.RealmsServerPing().M_182_A()};
    }

    public static long M_182_A() {
        if (E_738_L.n_1700_B.Y_601_j != null) {
            return E_738_L.n_1700_B.Y_601_j.X_933_l();
        }
        return 0L;
    }

    public static double n_1700_B(double x1, double y1, double z1, double x2, double y2, double z2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static double P_1922_E(double x, double y, double z) {
        if (E_738_L.n_1700_B.Y_259_p != null) {
            return E_738_L.n_1700_B(E_738_L.n_1700_B.Y_259_p.O_3598_v(), E_738_L.n_1700_B.Y_259_p.X_2960_b(), E_738_L.n_1700_B.Y_259_p.l_2647_k(), x, y, z);
        }
        return 0.0;
    }

    public static Packet<?> n_1700_B(String action, int x, int y, int z, String facing) {
        c_1514_x pos = new c_1514_x(x, y, z);
        b_257_Y direction = E_738_L.t_1786_h(facing);
        ServerboundPlayerActionPacket.n_1700_B packetAction = E_738_L.multiplayerClientSuggestionProvider(action);
        return new ServerboundPlayerActionPacket(packetAction, pos, direction);
    }

    public static boolean J_1907_R(String action, int x, int y, int z, String facing) {
        return E_738_L.n_1700_B(E_738_L.n_1700_B(action, x, y, z, facing));
    }

    private static b_257_Y t_1786_h(String facing) {
        if (facing == null) {
            return b_257_Y.J_1907_R;
        }
        switch (facing.toLowerCase()) {
            case "up": {
                return b_257_Y.J_1907_R;
            }
            case "down": {
                return b_257_Y.n_1700_B;
            }
            case "north": {
                return b_257_Y.R_4764_Y;
            }
            case "south": {
                return b_257_Y.G_564_y;
            }
            case "east": {
                return b_257_Y.u_1723_Y;
            }
            case "west": {
                return b_257_Y.P_1922_E;
            }
        }
        return b_257_Y.J_1907_R;
    }

    private static ServerboundPlayerActionPacket.n_1700_B multiplayerClientSuggestionProvider(String action) {
        if (action == null) {
            return ServerboundPlayerActionPacket.n_1700_B.n_1700_B;
        }
        switch (action.toUpperCase()) {
            case "START_DESTROY_BLOCK": {
                return ServerboundPlayerActionPacket.n_1700_B.n_1700_B;
            }
            case "ABORT_DESTROY_BLOCK": {
                return ServerboundPlayerActionPacket.n_1700_B.J_1907_R;
            }
            case "STOP_DESTROY_BLOCK": {
                return ServerboundPlayerActionPacket.n_1700_B.R_4764_Y;
            }
            case "DROP_ALL_ITEMS": {
                return ServerboundPlayerActionPacket.n_1700_B.G_564_y;
            }
            case "DROP_ITEM": {
                return ServerboundPlayerActionPacket.n_1700_B.P_1922_E;
            }
            case "RELEASE_USE_ITEM": {
                return ServerboundPlayerActionPacket.n_1700_B.u_1723_Y;
            }
            case "SWAP_ITEM_WITH_OFFHAND": {
                return ServerboundPlayerActionPacket.n_1700_B.v_4262_N;
            }
        }
        return ServerboundPlayerActionPacket.n_1700_B.n_1700_B;
    }

    public static Packet<?> s_956_w(String hand) {
        x_1688_C handEnum = "offhand".equalsIgnoreCase(hand) ? x_1688_C.J_1907_R : x_1688_C.n_1700_B;
        return new Z_3504_M(handEnum);
    }

    public static boolean u_2550_I(String hand) {
        return E_738_L.n_1700_B(E_738_L.s_956_w(hand));
    }

    public static Packet<?> n_1700_B(int x, int y, int z, String facing, String hand, float hitX, float hitY, float hitZ) {
        c_1514_x pos = new c_1514_x(x, y, z);
        b_257_Y direction = E_738_L.t_1786_h(facing);
        x_1688_C handEnum = "offhand".equalsIgnoreCase(hand) ? x_1688_C.J_1907_R : x_1688_C.n_1700_B;
        e_2866_D hitVec = new e_2866_D(hitX, hitY, hitZ);
        return new F_1464_b(handEnum, new BlockHitResult(hitVec, direction, pos, false));
    }

    public static boolean J_1907_R(int x, int y, int z, String facing, String hand, float hitX, float hitY, float hitZ) {
        return E_738_L.n_1700_B(E_738_L.n_1700_B(x, y, z, facing, hand, hitX, hitY, hitZ));
    }

    public static Packet<?> J_1907_R(int slot) {
        return new p_1183_T(slot);
    }

    public static boolean R_4764_Y(int slot) {
        return E_738_L.n_1700_B(E_738_L.J_1907_R(slot));
    }

    public static Z_1993_T G_564_y(int slot) {
        if (E_738_L.n_1700_B.Y_259_p != null && slot >= 0 && slot < 45) {
            return E_738_L.n_1700_B.Y_259_p.l_1268_F.s_956_w(slot);
        }
        return Z_1993_T.J_1907_R;
    }

    public static String P_1922_E(int slot) {
        Z_1993_T stack = E_738_L.G_564_y(slot);
        if (!stack.n_1700_B()) {
            return stack.multiplayerClientSuggestionProvider().getString();
        }
        return "empty";
    }

    public static int u_1723_Y(int slot) {
        Z_1993_T stack = E_738_L.G_564_y(slot);
        if (!stack.n_1700_B()) {
            return stack.t_4043_B();
        }
        return 0;
    }

    public static boolean v_4262_N(int slot) {
        return E_738_L.G_564_y(slot).n_1700_B();
    }

    public static int M_588_G(String itemName) {
        if (E_738_L.n_1700_B.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < 45; ++i) {
            Z_1993_T stack = E_738_L.n_1700_B.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || !stack.multiplayerClientSuggestionProvider().getString().toLowerCase().contains(itemName.toLowerCase())) continue;
            return i;
        }
        return -1;
    }

    public static int t_1786_h() {
        if (E_738_L.n_1700_B.Y_259_p != null) {
            return E_738_L.n_1700_B.Y_259_p.l_1268_F.G_564_y;
        }
        return 0;
    }

    public static boolean w_1484_f(int slot) {
        if (E_738_L.n_1700_B.Y_259_p != null && slot >= 0 && slot < 9) {
            E_738_L.n_1700_B.Y_259_p.l_1268_F.G_564_y = slot;
            return E_738_L.R_4764_Y(slot);
        }
        return false;
    }

    public static Z_1993_T multiplayerClientSuggestionProvider() {
        if (E_738_L.n_1700_B.Y_259_p != null) {
            return E_738_L.n_1700_B.Y_259_p.A_2714_y();
        }
        return Z_1993_T.J_1907_R;
    }

    public static Z_1993_T w_1457_N() {
        if (E_738_L.n_1700_B.Y_259_p != null) {
            return E_738_L.n_1700_B.Y_259_p.S_4035_N();
        }
        return Z_1993_T.J_1907_R;
    }

    public static int P_4830_p(String itemName) {
        if (E_738_L.n_1700_B.Y_259_p == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < 45; ++i) {
            Z_1993_T stack = E_738_L.n_1700_B.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || !stack.multiplayerClientSuggestionProvider().getString().toLowerCase().contains(itemName.toLowerCase())) continue;
            count += stack.t_4043_B();
        }
        return count;
    }

    public static List<Integer> h_1847_R(String itemName) {
        ArrayList<Integer> slots = new ArrayList<Integer>();
        if (E_738_L.n_1700_B.Y_259_p == null) {
            return slots;
        }
        for (int i = 0; i < 45; ++i) {
            Z_1993_T stack = E_738_L.n_1700_B.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || !stack.multiplayerClientSuggestionProvider().getString().toLowerCase().contains(itemName.toLowerCase())) continue;
            slots.add(i);
        }
        return slots;
    }

    public static N_4263_v Y_601_j() {
        if (ClientBootstrap.Y_601_j() == null || ClientBootstrap.Y_601_j().J_1907_R() == null) {
            return null;
        }
        AttackAura attackAura = ClientBootstrap.Y_601_j().J_1907_R().J_1907_R();
        if (attackAura != null) {
            return attackAura.h_1847_R();
        }
        return null;
    }

    public static String G_564_y(N_4263_v entity) {
        if (entity != null) {
            return entity.c_().getString();
        }
        return "";
    }

    public static boolean n_1700_B(int slot1, int slot2) {
        if (E_738_L.n_1700_B.Y_259_p == null || E_738_L.n_1700_B.w_1457_N == null) {
            return false;
        }
        if (slot1 < 0 || slot1 >= 45 || slot2 < 0 || slot2 >= 45) {
            return false;
        }
        if (slot1 == slot2) {
            return true;
        }
        try {
            u_1934_K.n_1700_B(slot1, slot2);
            return true;
        }
        catch (Exception e) {
            System.err.println("Error swapping items: " + e.getMessage());
            return false;
        }
    }

    public static boolean t_148_a(int slot) {
        if (E_738_L.n_1700_B.Y_259_p == null || E_738_L.n_1700_B.w_1457_N == null) {
            return false;
        }
        if (slot < 0 || slot >= 45) {
            return false;
        }
        try {
            int invFrom = slot < 9 ? slot + 36 : slot;
            E_738_L.n_1700_B.w_1457_N.windowClick(E_738_L.n_1700_B.Y_259_p.H_1873_g.u_1723_Y, invFrom, 40, a_408_T.R_4764_Y, E_738_L.n_1700_B.Y_259_p);
            E_738_L.n_1700_B.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(E_738_L.n_1700_B.Y_259_p.H_1873_g.u_1723_Y));
            return true;
        }
        catch (Exception e) {
            System.err.println("Error swapping to offhand: " + e.getMessage());
            return false;
        }
    }

    public static boolean s_956_w(int slot) {
        if (E_738_L.n_1700_B.Y_259_p == null || E_738_L.n_1700_B.w_1457_N == null) {
            return false;
        }
        if (slot < 0 || slot >= 45) {
            return false;
        }
        try {
            int invTo = slot < 9 ? slot + 36 : slot;
            E_738_L.n_1700_B.w_1457_N.windowClick(E_738_L.n_1700_B.Y_259_p.H_1873_g.u_1723_Y, 40, invTo, a_408_T.R_4764_Y, E_738_L.n_1700_B.Y_259_p);
            E_738_L.n_1700_B.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(E_738_L.n_1700_B.Y_259_p.H_1873_g.u_1723_Y));
            return true;
        }
        catch (Exception e) {
            System.err.println("Error swapping from offhand: " + e.getMessage());
            return false;
        }
    }

    public static Z_1993_T Y_259_p() {
        if (E_738_L.n_1700_B.Y_259_p == null) {
            return Z_1993_T.J_1907_R;
        }
        return E_738_L.n_1700_B.Y_259_p.J_1907_R(e_1174_E.P_1922_E);
    }

    public static String Q_2552_b() {
        Z_1993_T stack = E_738_L.Y_259_p();
        if (!stack.n_1700_B()) {
            return stack.multiplayerClientSuggestionProvider().getString();
        }
        return "empty";
    }

    public static boolean C_2741_M() {
        Z_1993_T stack = E_738_L.Y_259_p();
        return !stack.n_1700_B() && stack.J_1907_R() == Items.NyliumBlock;
    }

    public static int k_2293_S() {
        if (E_738_L.n_1700_B.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < E_738_L.n_1700_B.Y_259_p.l_1268_F.n_1700_B.size(); ++i) {
            Z_1993_T itemStack = E_738_L.n_1700_B.Y_259_p.l_1268_F.n_1700_B.get(i);
            if (itemStack.n_1700_B() || !(itemStack.J_1907_R() instanceof R_2515_i) || ((R_2515_i)itemStack.J_1907_R()).R_4764_Y() != e_1174_E.P_1922_E) continue;
            return i;
        }
        return -1;
    }

    public static int q_2307_F() {
        return E_738_L.n_1700_B(Items.NyliumBlock);
    }

    public static int n_1700_B(q_1613_l item) {
        if (E_738_L.n_1700_B.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < 45; ++i) {
            Z_1993_T stack = E_738_L.n_1700_B.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || stack.J_1907_R() != item) continue;
            return i;
        }
        return -1;
    }

    public static boolean u_2550_I(int slot) {
        if (E_738_L.n_1700_B.Y_259_p == null || E_738_L.n_1700_B.w_1457_N == null) {
            return false;
        }
        if (slot < 0 || slot >= 45) {
            return false;
        }
        try {
            u_1934_K.G_564_y(slot, 6);
            return true;
        }
        catch (Exception e) {
            System.err.println("Error swapping to chest slot: " + e.getMessage());
            return false;
        }
    }

    public static boolean M_588_G(int slot) {
        if (E_738_L.n_1700_B.Y_259_p == null || E_738_L.n_1700_B.w_1457_N == null) {
            return false;
        }
        if (slot < 0 || slot >= 45) {
            return false;
        }
        try {
            u_1934_K.G_564_y(slot, 5);
            return true;
        }
        catch (Exception e) {
            System.err.println("Error swapping to helmet slot: " + e.getMessage());
            return false;
        }
    }

    public static boolean P_4830_p(int slot) {
        if (E_738_L.n_1700_B.Y_259_p == null || E_738_L.n_1700_B.w_1457_N == null) {
            return false;
        }
        if (slot < 0 || slot >= 45) {
            return false;
        }
        try {
            u_1934_K.G_564_y(slot, 7);
            return true;
        }
        catch (Exception e) {
            System.err.println("Error swapping to leggings slot: " + e.getMessage());
            return false;
        }
    }

    public static boolean h_1847_R(int slot) {
        if (E_738_L.n_1700_B.Y_259_p == null || E_738_L.n_1700_B.w_1457_N == null) {
            return false;
        }
        if (slot < 0 || slot >= 45) {
            return false;
        }
        try {
            u_1934_K.G_564_y(slot, 8);
            return true;
        }
        catch (Exception e) {
            System.err.println("Error swapping to boots slot: " + e.getMessage());
            return false;
        }
    }

    public static int Q_4569_t(String moduleName) {
        Module module = E_738_L.J_1907_R(moduleName);
        if (module != null) {
            return module.v_4262_N();
        }
        return -100;
    }

    public static boolean Q_4569_t(int keyCode) {
        if (keyCode < 0) {
            return false;
        }
        if (n_1700_B == null || n_1700_B.RealmsServerPing() == null) {
            return false;
        }
        return Q_4113_P.n_1700_B(n_1700_B.RealmsServerPing().t_148_a(), keyCode);
    }
}



