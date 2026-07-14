/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.A_4115_X;
import lightning.product.F_489_x;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.Q_4113_P;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_2491_A;
import lightning.product.Z_3822_q;
import lightning.product.MinecraftAccess;
import lightning.product.b_3528_u;
import lightning.product.MinecraftClient;
import lightning.product.ModuleManager;
import lightning.product.d_2169_p;
import lightning.product.d_560_A;
import lightning.product.g_221_o;
import lightning.product.h_1015_G;
import lightning.product.l_3370_o;
import lightning.product.ClientBootstrap;
import lightning.product.u_530_F;
import lightning.product.v_1900_v;
import lightning.product.v_2826_q;
import org.joml.Vector2f;

public class k_2273_q
implements d_560_A {
    private final List<Runnable> n_1700_B = new CopyOnWriteArrayList<Runnable>();
    private final List<Runnable> J_1907_R = new CopyOnWriteArrayList<Runnable>();
    private g_221_o R_4764_Y = null;

    public k_2273_q() {
        A_4115_X.n_1700_B(new n_1700_B());
    }

    @Override
    public void n_1700_B(String message) {
        v_1900_v.n_1700_B(message, new Object[0]);
    }

    @Override
    public void n_1700_B(String format, Object ... args) {
        v_1900_v.n_1700_B(String.format(format, args), new Object[0]);
    }

    @Override
    public float[] v_4262_N() {
        if (!this.w_1484_f()) {
            return null;
        }
        return new float[]{MinecraftAccess.c_3005_b.Y_259_p.p_178_J, MinecraftAccess.c_3005_b.Y_259_p.f_4016_n};
    }

    @Override
    public void J_1907_R(float yaw, float pitch) {
        if (!this.w_1484_f()) {
            return;
        }
        yaw = this.R_4764_Y(yaw);
        pitch = this.G_564_y(pitch);
        MinecraftAccess.c_3005_b.Y_259_p.p_178_J = yaw;
        MinecraftAccess.c_3005_b.Y_259_p.f_4016_n = pitch;
        d_2169_p.n_1700_B(yaw);
        d_2169_p.J_1907_R(pitch);
    }

    @Override
    public float G_564_y() {
        if (!this.w_1484_f()) {
            return 0.0f;
        }
        return MinecraftAccess.c_3005_b.Y_259_p.p_178_J;
    }

    @Override
    public float P_1922_E() {
        if (!this.w_1484_f()) {
            return 0.0f;
        }
        return MinecraftAccess.c_3005_b.Y_259_p.f_4016_n;
    }

    @Override
    public void n_1700_B(float yaw) {
        if (!this.w_1484_f()) {
            return;
        }
        MinecraftAccess.c_3005_b.Y_259_p.p_178_J = yaw = this.R_4764_Y(yaw);
        d_2169_p.n_1700_B(yaw);
    }

    @Override
    public void J_1907_R(float pitch) {
        if (!this.w_1484_f()) {
            return;
        }
        MinecraftAccess.c_3005_b.Y_259_p.f_4016_n = pitch = this.G_564_y(pitch);
        d_2169_p.J_1907_R(pitch);
    }

    @Override
    public boolean w_1484_f() {
        return MinecraftAccess.c_3005_b.Y_259_p != null;
    }

    @Override
    public double[] n_1700_B() {
        if (!this.w_1484_f()) {
            return null;
        }
        return new double[]{MinecraftAccess.c_3005_b.Y_259_p.O_3598_v(), MinecraftAccess.c_3005_b.Y_259_p.X_2960_b(), MinecraftAccess.c_3005_b.Y_259_p.l_2647_k()};
    }

    @Override
    public boolean t_148_a() {
        if (!this.w_1484_f()) {
            return false;
        }
        return MinecraftAccess.c_3005_b.Y_259_p.M_1641_O();
    }

    @Override
    public void s_956_w() {
        if (!this.w_1484_f()) {
            return;
        }
        MinecraftAccess.c_3005_b.Y_259_p.e_837_t();
    }

    @Override
    public void n_1700_B(boolean jumping) {
        if (!this.w_1484_f()) {
            return;
        }
        MinecraftAccess.c_3005_b.Y_259_p.t_1786_h(jumping);
    }

    @Override
    public boolean J_1907_R(String moduleName) {
        Module module = this.Q_4569_t(moduleName);
        return module != null && module.w_1484_f();
    }

    @Override
    public boolean R_4764_Y(String moduleName) {
        Module module = this.Q_4569_t(moduleName);
        if (module != null && !module.w_1484_f()) {
            module.R_4764_Y();
            return true;
        }
        return false;
    }

    @Override
    public boolean G_564_y(String moduleName) {
        Module module = this.Q_4569_t(moduleName);
        if (module != null && module.w_1484_f()) {
            module.R_4764_Y();
            return true;
        }
        return false;
    }

    @Override
    public boolean P_1922_E(String moduleName) {
        Module module = this.Q_4569_t(moduleName);
        if (module != null) {
            module.R_4764_Y();
            return true;
        }
        return false;
    }

    private Module Q_4569_t(String moduleName) {
        ModuleManager moduleManager = ClientBootstrap.Y_601_j().J_1907_R();
        if (moduleManager == null) {
            return null;
        }
        return moduleManager.n_1700_B(moduleName).orElse(null);
    }

    @Override
    public void n_1700_B(Runnable handler) {
        if (handler != null) {
            this.n_1700_B.add(handler);
        }
    }

    @Override
    public void J_1907_R(Runnable handler) {
        if (handler != null) {
            this.J_1907_R.add(handler);
        }
    }

    @Override
    public void G_564_y(Runnable handler) {
        this.n_1700_B.remove(handler);
    }

    @Override
    public void P_1922_E(Runnable handler) {
        this.J_1907_R.remove(handler);
    }

    @Override
    public long C_2741_M() {
        return System.currentTimeMillis();
    }

    @Override
    public float R_4764_Y(float angle) {
        return u_530_F.v_4262_N(angle);
    }

    @Override
    public float G_564_y(float pitch) {
        return u_530_F.n_1700_B(pitch, -90.0f, 90.0f);
    }

    @Override
    public void n_1700_B(String text, String color) {
        String colorCode = "\u00a7f";
        switch (color.toLowerCase()) {
            case "red": {
                colorCode = "\u00a7c";
                break;
            }
            case "green": {
                colorCode = "\u00a7a";
                break;
            }
            case "blue": {
                colorCode = "\u00a79";
                break;
            }
            case "yellow": {
                colorCode = "\u00a7e";
                break;
            }
            case "purple": {
                colorCode = "\u00a75";
                break;
            }
            case "cyan": {
                colorCode = "\u00a7b";
                break;
            }
            case "white": {
                colorCode = "\u00a7f";
                break;
            }
            case "black": {
                colorCode = "\u00a70";
            }
        }
        v_1900_v.n_1700_B(colorCode + text, new Object[0]);
    }

    @Override
    public void n_1700_B(double x, double y, double z) {
        if (!this.w_1484_f()) {
            return;
        }
        MinecraftAccess.c_3005_b.Y_259_p.J_1907_R(x, y, z);
    }

    @Override
    public double[] J_1907_R() {
        if (!this.w_1484_f()) {
            return null;
        }
        return new double[]{MinecraftAccess.c_3005_b.Y_259_p.I_4348_c().J_1907_R, MinecraftAccess.c_3005_b.Y_259_p.I_4348_c().R_4764_Y, MinecraftAccess.c_3005_b.Y_259_p.I_4348_c().G_564_y};
    }

    @Override
    public void J_1907_R(double x, double y, double z) {
        if (!this.w_1484_f()) {
            return;
        }
        MinecraftAccess.c_3005_b.Y_259_p.h_1847_R(x, y, z);
    }

    @Override
    public void n_1700_B(double speed) {
        if (!this.w_1484_f()) {
            return;
        }
        double[] motion = this.J_1907_R();
        if (motion == null) {
            return;
        }
        double currentSpeed = Math.sqrt(motion[0] * motion[0] + motion[2] * motion[2]);
        if (currentSpeed > 0.0) {
            double factor = speed / currentSpeed;
            this.J_1907_R(motion[0] * factor, motion[1], motion[2] * factor);
        }
    }

    @Override
    public float[] R_4764_Y() {
        return this.v_4262_N();
    }

    @Override
    public void n_1700_B(float yaw, float pitch) {
        this.J_1907_R(yaw, pitch);
    }

    @Override
    public double[] u_1723_Y() {
        if (!this.w_1484_f()) {
            return null;
        }
        return new double[]{MinecraftAccess.c_3005_b.Y_259_p.O_3598_v(), MinecraftAccess.c_3005_b.Y_259_p.X_2960_b() + (double)MinecraftAccess.c_3005_b.Y_259_p.X_1313_W(), MinecraftAccess.c_3005_b.Y_259_p.l_2647_k()};
    }

    @Override
    public void R_4764_Y(double x, double y, double z) {
        this.n_1700_B(x, y - (double)MinecraftAccess.c_3005_b.Y_259_p.X_1313_W(), z);
    }

    @Override
    public boolean J_1907_R(String name, String category) {
        return false;
    }

    @Override
    public boolean u_1723_Y(String name) {
        return false;
    }

    @Override
    public List<String> v_4262_N(String moduleName) {
        return new ArrayList<String>();
    }

    @Override
    public Object R_4764_Y(String moduleName, String settingName) {
        return null;
    }

    @Override
    public void n_1700_B(String moduleName, String settingName, Object value) {
    }

    @Override
    public String G_564_y(String moduleName, String settingName) {
        return "";
    }

    @Override
    public double[] P_1922_E(String moduleName, String settingName) {
        return new double[0];
    }

    @Override
    public List<String> u_1723_Y(String moduleName, String settingName) {
        return new ArrayList<String>();
    }

    @Override
    public void n_1700_B(String moduleName, String settingName, String settingType, Object defaultValue) {
    }

    @Override
    public List<String> u_2550_I() {
        return new ArrayList<String>();
    }

    @Override
    public int w_1484_f(String moduleName) {
        return -100;
    }

    @Override
    public Object n_1700_B(int slot) {
        return null;
    }

    @Override
    public String J_1907_R(int slot) {
        return "";
    }

    @Override
    public int R_4764_Y(int slot) {
        return 0;
    }

    @Override
    public boolean G_564_y(int slot) {
        return true;
    }

    @Override
    public int t_148_a(String itemName) {
        return -1;
    }

    @Override
    public int M_588_G() {
        return 0;
    }

    @Override
    public void P_1922_E(int slot) {
    }

    @Override
    public Object P_4830_p() {
        return null;
    }

    @Override
    public Object h_1847_R() {
        return null;
    }

    @Override
    public void n_1700_B(int slot1, int slot2) {
    }

    @Override
    public void u_1723_Y(int slot) {
    }

    @Override
    public void v_4262_N(int slot) {
    }

    @Override
    public Object Q_4569_t() {
        return null;
    }

    @Override
    public String M_182_A() {
        return "";
    }

    @Override
    public boolean t_1786_h() {
        return false;
    }

    @Override
    public int multiplayerClientSuggestionProvider() {
        return -1;
    }

    @Override
    public int w_1457_N() {
        return -1;
    }

    @Override
    public int n_1700_B(Object item) {
        return -1;
    }

    @Override
    public void w_1484_f(int slot) {
    }

    @Override
    public void t_148_a(int slot) {
    }

    @Override
    public void s_956_w(int slot) {
    }

    @Override
    public void u_2550_I(int slot) {
    }

    @Override
    public int s_956_w(String itemName) {
        return 0;
    }

    @Override
    public List<Integer> u_2550_I(String itemName) {
        return new ArrayList<Integer>();
    }

    @Override
    public List<Object> Y_601_j() {
        return new ArrayList<Object>();
    }

    @Override
    public List<Object> M_588_G(String typeName) {
        return new ArrayList<Object>();
    }

    @Override
    public List<Object> n_1700_B(double x, double y, double z, double radius) {
        return new ArrayList<Object>();
    }

    @Override
    public Object J_1907_R(double x, double y, double z, double maxDistance) {
        return null;
    }

    @Override
    public double[] J_1907_R(Object entity) {
        return null;
    }

    @Override
    public double R_4764_Y(Object entity) {
        return 0.0;
    }

    @Override
    public Object Y_259_p() {
        return null;
    }

    @Override
    public String G_564_y(Object entity) {
        return "";
    }

    @Override
    public Object G_564_y(double x, double y, double z) {
        return null;
    }

    @Override
    public boolean P_1922_E(double x, double y, double z) {
        return true;
    }

    @Override
    public String u_1723_Y(double x, double y, double z) {
        return "";
    }

    @Override
    public int n_1700_B(int r, int g, int b, int a) {
        return a << 24 | r << 16 | g << 8 | b;
    }

    @Override
    public int n_1700_B(int r, int g, int b) {
        return this.n_1700_B(r, g, b, 255);
    }

    @Override
    public int[] M_588_G(int color) {
        return new int[]{color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, color >> 24 & 0xFF};
    }

    @Override
    public void P_1922_E(Object packet) {
    }

    @Override
    public void n_1700_B(double x, double y, double z, boolean onGround) {
    }

    @Override
    public void n_1700_B(float yaw, float pitch, boolean onGround) {
    }

    @Override
    public void n_1700_B(double x, double y, double z, float yaw, float pitch, boolean onGround) {
    }

    @Override
    public Object J_1907_R(double x, double y, double z, boolean onGround) {
        return null;
    }

    @Override
    public Object J_1907_R(float yaw, float pitch, boolean onGround) {
        return null;
    }

    @Override
    public Object J_1907_R(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        return null;
    }

    @Override
    public void n_1700_B(String action, double x, double y, double z, String facing) {
    }

    @Override
    public void P_4830_p(String hand) {
    }

    @Override
    public void n_1700_B(double x, double y, double z, String facing, String hand, double hitX, double hitY, double hitZ) {
    }

    @Override
    public void P_4830_p(int slot) {
    }

    @Override
    public boolean u_1723_Y(Object packet) {
        return false;
    }

    @Override
    public void v_4262_N(Object packet) {
    }

    @Override
    public double[] w_1484_f(Object packet) {
        return null;
    }

    @Override
    public Object h_1847_R(String hand) {
        return null;
    }

    @Override
    public Object J_1907_R(double x, double y, double z, String facing, String hand, double hitX, double hitY, double hitZ) {
        return null;
    }

    @Override
    public Object h_1847_R(int slot) {
        return null;
    }

    @Override
    public void n_1700_B(float x, float y, float width, float height, int color) {
        if (this.R_4764_Y != null) {
            F_489_x.n_1700_B(this.R_4764_Y, x, y, width, height, color);
        } else {
            F_489_x.J_1907_R(x, y, width, height, color);
        }
    }

    @Override
    public void n_1700_B(float x, float y, float width, float height, int leftColor, int rightColor) {
        F_489_x.n_1700_B(x, y, width, height, leftColor, rightColor);
    }

    @Override
    public void n_1700_B(float x, float y, float width, float height, float radius, int color) {
        if (this.R_4764_Y == null) {
            return;
        }
        F_489_x.n_1700_B(x, y, width, height, new Z_2491_A(radius, radius, radius, radius), color);
    }

    @Override
    public void n_1700_B(float centerX, float centerY, float radius, int color) {
        F_489_x.n_1700_B(centerX, centerY, radius, color);
    }

    @Override
    public void n_1700_B(float centerX, float centerY, float radius, int color, float lineWidth) {
        F_489_x.n_1700_B(centerX, centerY, radius, color, lineWidth);
    }

    @Override
    public void n_1700_B(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, int color, boolean fill) {
        if (MinecraftAccess.c_3005_b.Y_601_j == null) {
            return;
        }
        I_4817_s bb = new I_4817_s(minX, minY, minZ, maxX, maxY, maxZ);
        F_489_x.n_1700_B(bb, color, fill);
    }

    @Override
    public void n_1700_B(double centerX, double centerY, double centerZ, double radius, int color) {
        this.n_1700_B(centerX - radius, centerY - 0.1, centerZ - radius, centerX + radius, centerY + 0.1, centerZ + radius, color, true);
    }

    @Override
    public float[] v_4262_N(double x, double y, double z) {
        if (MinecraftAccess.c_3005_b.Y_601_j == null) {
            return null;
        }
        Vector2f result = v_2826_q.n_1700_B(x, y, z);
        if (result.x == Float.MAX_VALUE) {
            return null;
        }
        return new float[]{result.x, result.y};
    }

    @Override
    public void n_1700_B(String text, float x, float y, int color, float fontSize, String fontType) {
        if (this.R_4764_Y == null) {
            return;
        }
        int size = Math.max(1, Math.min((int)fontSize, 35));
        Z_3822_q font = null;
        switch (fontType != null ? fontType.toLowerCase() : "medium") {
            case "bold": {
                if (size >= l_3370_o.P_1922_E.length) break;
                font = l_3370_o.P_1922_E[size];
                break;
            }
            case "regular": {
                if (size >= l_3370_o.R_4764_Y.length) break;
                font = l_3370_o.R_4764_Y[size];
                break;
            }
            case "semibold": {
                if (size >= l_3370_o.G_564_y.length) break;
                font = l_3370_o.G_564_y[size];
                break;
            }
            default: {
                if (size >= l_3370_o.J_1907_R.length) break;
                font = l_3370_o.J_1907_R[size];
            }
        }
        if (font == null && size < l_3370_o.J_1907_R.length) {
            font = l_3370_o.J_1907_R[size];
        }
        if (font != null) {
            font.n_1700_B(this.R_4764_Y, text, (double)x, (double)y, color);
        }
    }

    @Override
    public void n_1700_B(boolean withColor, boolean textured) {
        F_489_x.n_1700_B(withColor, textured);
    }

    @Override
    public void Q_2552_b() {
        F_489_x.n_1700_B();
    }

    @Override
    public boolean t_148_a(Object entity) {
        if (entity instanceof N_4263_v) {
            return v_2826_q.n_1700_B((N_4263_v)entity);
        }
        return false;
    }

    @Override
    public void R_4764_Y(Runnable handler) {
    }

    @Override
    public void n_1700_B(d_560_A.J_1907_R handler) {
    }

    @Override
    public void u_1723_Y(Runnable handler) {
    }

    @Override
    public void J_1907_R(d_560_A.J_1907_R handler) {
    }

    @Override
    public void J_1907_R(boolean cancel) {
    }

    @Override
    public void n_1700_B(int key, d_560_A.n_1700_B handler) {
    }

    @Override
    public int k_2293_S() {
        return MinecraftClient.x_607_J;
    }

    @Override
    public int[] q_2307_F() {
        return new int[]{MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t(), MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A()};
    }

    @Override
    public long Z_875_P() {
        return MinecraftAccess.c_3005_b.Y_601_j != null ? MinecraftAccess.c_3005_b.Y_601_j.X_933_l() : 0L;
    }

    @Override
    public double n_1700_B(double x1, double y1, double z1, double x2, double y2, double z2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    @Override
    public double w_1484_f(double x, double y, double z) {
        if (!this.w_1484_f()) {
            return 0.0;
        }
        double[] pos = this.n_1700_B();
        if (pos == null) {
            return 0.0;
        }
        return this.n_1700_B(pos[0], pos[1], pos[2], x, y, z);
    }

    @Override
    public boolean Q_4569_t(int keyCode) {
        return Q_4113_P.n_1700_B(MinecraftAccess.c_3005_b.RealmsServerPing().t_148_a(), keyCode);
    }

    public void c_3005_b() {
        this.n_1700_B.clear();
        this.J_1907_R.clear();
    }

    private class n_1700_B {
        private n_1700_B() {
        }

        @Y_1740_V
        public void n_1700_B(h_1015_G event) {
            for (Runnable handler : k_2273_q.this.n_1700_B) {
                try {
                    handler.run();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Y_1740_V
        public void n_1700_B(b_3528_u event) {
            k_2273_q.this.R_4764_Y = event.J_1907_R();
            for (Runnable handler : k_2273_q.this.J_1907_R) {
                try {
                    handler.run();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            k_2273_q.this.R_4764_Y = null;
        }
    }
}



