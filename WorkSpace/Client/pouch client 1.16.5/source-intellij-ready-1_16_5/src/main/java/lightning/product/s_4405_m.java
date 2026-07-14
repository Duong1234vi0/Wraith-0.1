/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.ARBShaderObjects
 *  org.lwjgl.opengl.GL20
 */
package lightning.product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lightning.product.MinecraftAccess;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL20;

public class s_4405_m
implements MinecraftAccess {
    private final int e_2887_G;
    private final boolean B_1668_F;
    private final Map<String, Integer> g_164_R = new HashMap<String, Integer>();
    public static s_4405_m n_1700_B = new s_4405_m("rounded_rectangle");
    public static s_4405_m J_1907_R = new s_4405_m("outline");
    public static s_4405_m R_4764_Y = new s_4405_m("rounded_rectangle_gradient");
    public static s_4405_m G_564_y = new s_4405_m("rounded_rectangle_gradient_glowed");
    public static s_4405_m P_1922_E = new s_4405_m("rounded_rectangle_inner_glow");
    public static s_4405_m u_1723_Y = new s_4405_m("light_kawase_down");
    public static s_4405_m v_4262_N = new s_4405_m("light_kawase_up");
    public static s_4405_m w_1484_f = new s_4405_m("blurred_round_rectangle");
    public static s_4405_m t_148_a = new s_4405_m("blurred_round_rectangle_inner_glow");
    public static s_4405_m s_956_w = new s_4405_m("rounded_head_texture");
    public static s_4405_m u_2550_I = new s_4405_m("rounded_image");
    public static s_4405_m M_588_G = new s_4405_m("substring");
    public static s_4405_m P_4830_p = new s_4405_m("outline_glow_esp");
    public static s_4405_m h_1847_R = new s_4405_m("glow_esp");
    public static s_4405_m Q_4569_t = new s_4405_m("outline_esp");
    public static s_4405_m M_182_A = new s_4405_m("hands");
    public static s_4405_m t_1786_h = new s_4405_m("hands_uv");
    public static s_4405_m multiplayerClientSuggestionProvider = new s_4405_m("saturation");
    public static s_4405_m w_1457_N = new s_4405_m("gradient_fill_chams");
    public static s_4405_m Y_601_j = new s_4405_m("glint_wave");
    public static s_4405_m Y_259_p = new s_4405_m("totem_pop");
    public static s_4405_m Q_2552_b = new s_4405_m("kill_effect");
    public static s_4405_m C_2741_M = new s_4405_m("ring_segment");
    public static s_4405_m k_2293_S = new s_4405_m("pulse_effect");
    public static s_4405_m q_2307_F = new s_4405_m("hitmarker");
    public static s_4405_m Z_875_P = new s_4405_m("pentagram");
    public static s_4405_m t_4043_B = new s_4405_m("nova_effect");
    public static s_4405_m x_607_J = new s_4405_m("target_triangle_vertex", "target_triangle");
    public static s_4405_m e_4240_b = new s_4405_m("motion_blur");
    public static s_4405_m n_3318_d = new s_4405_m("space_sky_vertex", "space_sky");
    public static s_4405_m d_2427_y = new s_4405_m("space_sky_vertex", "sky_plasma");
    public static s_4405_m z_1737_N = new s_4405_m("space_sky_vertex", "sky_balatro");
    public static s_4405_m v_4276_D = new s_4405_m("space_sky_vertex", "sky_summer");
    public static s_4405_m d_2461_k = new s_4405_m("space_sky_vertex", "sky_sakura");
    public static s_4405_m G_624_v = new s_4405_m("space_sky_vertex", "sky_ethereal");
    public static s_4405_m T_2506_i = new s_4405_m("space_sky_vertex", "sky_blizzard");
    public static s_4405_m q_4610_l = new s_4405_m("space_sky_vertex", "sky_aurora");
    public static s_4405_m z_4693_k = new s_4405_m("space_sky_vertex", "block_overlay_sky_plasma");
    public static s_4405_m g_221_o = new s_4405_m("space_sky_vertex", "block_overlay_sky_balatro");

    public s_4405_m(String fragmentShaderLoc) {
        this("vertex", fragmentShaderLoc);
    }

    public s_4405_m(String vertexShaderLoc, String fragmentShaderLoc) {
        int tempProgramID = ARBShaderObjects.glCreateProgramObjectARB();
        boolean tempValid = false;
        try {
            int fragmentShaderID = this.n_1700_B(fragmentShaderLoc, 35632);
            ARBShaderObjects.glAttachObjectARB((int)tempProgramID, (int)fragmentShaderID);
            int vertexShaderID = this.n_1700_B(vertexShaderLoc, 35633);
            ARBShaderObjects.glAttachObjectARB((int)tempProgramID, (int)vertexShaderID);
            ARBShaderObjects.glLinkProgramARB((int)tempProgramID);
            if (ARBShaderObjects.glGetObjectParameteriARB((int)tempProgramID, (int)35714) == 0) {
                throw new IllegalStateException("Shader program failed to link: " + ARBShaderObjects.glGetInfoLogARB((int)tempProgramID, (int)4096));
            }
            tempValid = true;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0435 \u0448\u0435\u0439\u0434\u0435\u0440\u0430: " + vertexShaderLoc + " / " + fragmentShaderLoc);
        }
        this.e_2887_G = tempProgramID;
        this.B_1668_F = tempValid;
    }

    public boolean n_1700_B() {
        return this.B_1668_F;
    }

    public void J_1907_R() {
        if (!this.B_1668_F) {
            return;
        }
        ARBShaderObjects.glUseProgramObjectARB((int)this.e_2887_G);
    }

    public void R_4764_Y() {
        GL20.glUseProgram((int)0);
    }

    public void n_1700_B(String name, float ... args) {
        int loc = this.R_4764_Y(name);
        if (loc < 0) {
            return;
        }
        switch (args.length) {
            case 1: {
                ARBShaderObjects.glUniform1fARB((int)loc, (float)args[0]);
                break;
            }
            case 2: {
                ARBShaderObjects.glUniform2fARB((int)loc, (float)args[0], (float)args[1]);
                break;
            }
            case 3: {
                ARBShaderObjects.glUniform3fARB((int)loc, (float)args[0], (float)args[1], (float)args[2]);
                break;
            }
            case 4: {
                ARBShaderObjects.glUniform4fARB((int)loc, (float)args[0], (float)args[1], (float)args[2], (float)args[3]);
                break;
            }
            default: {
                throw new IllegalArgumentException("\u041d\u0435\u0434\u043e\u043f\u0443\u0441\u0442\u0438\u043c\u043e\u0435 \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u0434\u043b\u044f uniform '" + name + "'");
            }
        }
    }

    public void n_1700_B(String name, int ... args) {
        int loc = this.R_4764_Y(name);
        if (loc < 0) {
            return;
        }
        switch (args.length) {
            case 1: {
                ARBShaderObjects.glUniform1iARB((int)loc, (int)args[0]);
                break;
            }
            case 2: {
                ARBShaderObjects.glUniform2iARB((int)loc, (int)args[0], (int)args[1]);
                break;
            }
            case 3: {
                ARBShaderObjects.glUniform3iARB((int)loc, (int)args[0], (int)args[1], (int)args[2]);
                break;
            }
            case 4: {
                ARBShaderObjects.glUniform4iARB((int)loc, (int)args[0], (int)args[1], (int)args[2], (int)args[3]);
                break;
            }
            default: {
                throw new IllegalArgumentException("\u041d\u0435\u0434\u043e\u043f\u0443\u0441\u0442\u0438\u043c\u043e\u0435 \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u0434\u043b\u044f uniform '" + name + "'");
            }
        }
    }

    public void J_1907_R(String name, float ... args) {
        int loc = this.R_4764_Y(name);
        if (loc < 0) {
            return;
        }
        switch (args.length) {
            case 1: {
                ARBShaderObjects.glUniform1fARB((int)loc, (float)args[0]);
                break;
            }
            case 2: {
                ARBShaderObjects.glUniform2fARB((int)loc, (float)args[0], (float)args[1]);
                break;
            }
            case 3: {
                ARBShaderObjects.glUniform3fARB((int)loc, (float)args[0], (float)args[1], (float)args[2]);
                break;
            }
            case 4: {
                ARBShaderObjects.glUniform4fARB((int)loc, (float)args[0], (float)args[1], (float)args[2], (float)args[3]);
            }
        }
    }

    private int n_1700_B(String shaderName, int shaderType) throws IOException {
        int shader = ARBShaderObjects.glCreateShaderObjectARB((int)shaderType);
        String shaderSource = this.J_1907_R(shaderName);
        ARBShaderObjects.glShaderSourceARB((int)shader, (CharSequence)shaderSource);
        ARBShaderObjects.glCompileShaderARB((int)shader);
        if (GL20.glGetShaderi((int)shader, (int)35713) == 0) {
            String errorLog = GL20.glGetShaderInfoLog((int)shader, (int)4096);
            throw new IllegalStateException("Shader (" + shaderName + ") failed to compile: " + errorLog);
        }
        return shader;
    }

    private String J_1907_R(String shaderName) throws IOException {
        String path = "assets/minecraft/Pouch/shaders/" + shaderName + ".glsl";
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);){
            if (inputStream == null) {
                throw new FileNotFoundException("Shader file not found: " + path);
            }
            String string = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
            return string;
        }
    }

    public int n_1700_B(String name) {
        return this.R_4764_Y(name);
    }

    public void n_1700_B(String name, Matrix4f matrix) {
        if (!this.B_1668_F) {
            return;
        }
        int loc = this.R_4764_Y(name);
        if (loc < 0) {
            return;
        }
        FloatBuffer buf = BufferUtils.createFloatBuffer((int)16);
        matrix.get(buf);
        buf.flip();
        ARBShaderObjects.glUniformMatrix4fvARB((int)loc, (boolean)false, (FloatBuffer)buf);
    }

    private int R_4764_Y(String name) {
        Integer cached = this.g_164_R.get(name);
        if (cached != null) {
            return cached;
        }
        int loc = ARBShaderObjects.glGetUniformLocationARB((int)this.e_2887_G, (CharSequence)name);
        this.g_164_R.put(name, loc);
        return loc;
    }
}



