/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  javax.annotation.Nullable
 *  org.lwjgl.glfw.GLFW
 *  org.lwjgl.glfw.GLFWCharModsCallbackI
 *  org.lwjgl.glfw.GLFWCursorPosCallbackI
 *  org.lwjgl.glfw.GLFWDropCallbackI
 *  org.lwjgl.glfw.GLFWKeyCallbackI
 *  org.lwjgl.glfw.GLFWMouseButtonCallbackI
 *  org.lwjgl.glfw.GLFWScrollCallbackI
 */
package lightning.product;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.function.BiFunction;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.U_2871_b;
import lightning.product.l_4033_W;
import lightning.product.l_52_h;
import lightning.product.x_282_a;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCharModsCallbackI;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWDropCallbackI;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.glfw.GLFWScrollCallbackI;

public class Q_4113_P {
    @Nullable
    private static final MethodHandle J_1907_R;
    private static final int R_4764_Y;
    public static final n_1700_B n_1700_B;

    public static n_1700_B n_1700_B(int keyCode, int scanCode) {
        return keyCode == -1 ? lightning.product.Q_4113_P$J_1907_R.J_1907_R.n_1700_B(scanCode) : lightning.product.Q_4113_P$J_1907_R.n_1700_B.n_1700_B(keyCode);
    }

    public static n_1700_B n_1700_B(String name) {
        if (lightning.product.Q_4113_P$n_1700_B.P_1922_E.containsKey(name)) {
            return lightning.product.Q_4113_P$n_1700_B.P_1922_E.get(name);
        }
        for (J_1907_R inputmappings$type : lightning.product.Q_4113_P$J_1907_R.values()) {
            if (!name.startsWith(inputmappings$type.P_1922_E)) continue;
            String s = name.substring(inputmappings$type.P_1922_E.length() + 1);
            return inputmappings$type.n_1700_B(Integer.parseInt(s));
        }
        throw new IllegalArgumentException("Unknown key name: " + name);
    }

    public static boolean n_1700_B(long p_216506_0_, int p_216506_2_) {
        return GLFW.glfwGetKey((long)p_216506_0_, (int)p_216506_2_) == 1;
    }

    public static void n_1700_B(long p_216505_0_, GLFWKeyCallbackI p_216505_2_, GLFWCharModsCallbackI p_216505_3_) {
        GLFW.glfwSetKeyCallback((long)p_216505_0_, (GLFWKeyCallbackI)p_216505_2_);
        GLFW.glfwSetCharModsCallback((long)p_216505_0_, (GLFWCharModsCallbackI)p_216505_3_);
    }

    public static void n_1700_B(long p_216503_0_, GLFWCursorPosCallbackI p_216503_2_, GLFWMouseButtonCallbackI p_216503_3_, GLFWScrollCallbackI p_216503_4_, GLFWDropCallbackI p_216503_5_) {
        GLFW.glfwSetCursorPosCallback((long)p_216503_0_, (GLFWCursorPosCallbackI)p_216503_2_);
        GLFW.glfwSetMouseButtonCallback((long)p_216503_0_, (GLFWMouseButtonCallbackI)p_216503_3_);
        GLFW.glfwSetScrollCallback((long)p_216503_0_, (GLFWScrollCallbackI)p_216503_4_);
        GLFW.glfwSetDropCallback((long)p_216503_0_, (GLFWDropCallbackI)p_216503_5_);
    }

    public static void n_1700_B(long p_216504_0_, int p_216504_2_, double p_216504_3_, double p_216504_5_) {
        GLFW.glfwSetCursorPos((long)p_216504_0_, (double)p_216504_3_, (double)p_216504_5_);
        GLFW.glfwSetInputMode((long)p_216504_0_, (int)208897, (int)p_216504_2_);
    }

    public static boolean n_1700_B() {
        try {
            return J_1907_R != null && J_1907_R.invokeExact();
        }
        catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public static void n_1700_B(long p_224791_0_, boolean p_224791_2_) {
        if (Q_4113_P.n_1700_B()) {
            GLFW.glfwSetInputMode((long)p_224791_0_, (int)R_4764_Y, (int)(p_224791_2_ ? 1 : 0));
        }
    }

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodtype = MethodType.methodType(Boolean.TYPE);
        MethodHandle methodhandle = null;
        int i = 0;
        try {
            methodhandle = lookup.findStatic(GLFW.class, "glfwRawMouseMotionSupported", methodtype);
            MethodHandle methodhandle1 = lookup.findStaticGetter(GLFW.class, "GLFW_RAW_MOUSE_MOTION", Integer.TYPE);
            i = methodhandle1.invokeExact();
        }
        catch (NoSuchFieldException | NoSuchMethodException methodhandle1) {
        }
        catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
        J_1907_R = methodhandle;
        R_4764_Y = i;
        n_1700_B = lightning.product.Q_4113_P$J_1907_R.n_1700_B.n_1700_B(-1);
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("key.keyboard", (p_237528_0_, p_237528_1_) -> {
            String s = GLFW.glfwGetKeyName((int)p_237528_0_, (int)-1);
            return s != null ? new U_2871_b(s) : new F_2904_S((String)p_237528_1_);
        });
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("scancode", (p_237527_0_, p_237527_1_) -> {
            String s = GLFW.glfwGetKeyName((int)-1, (int)p_237527_0_);
            return s != null ? new U_2871_b(s) : new F_2904_S((String)p_237527_1_);
        });
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R("key.mouse", (p_237524_0_, p_237524_1_) -> l_4033_W.R_4764_Y().J_1907_R((String)p_237524_1_) ? new F_2904_S((String)p_237524_1_) : new F_2904_S("key.mouse", p_237524_0_ + 1));
        private final Int2ObjectMap<n_1700_B> G_564_y = new Int2ObjectOpenHashMap();
        private final String P_1922_E;
        private final BiFunction<Integer, String, x_282_a> u_1723_Y;
        private static final /* synthetic */ J_1907_R[] v_4262_N;

        public static J_1907_R[] values() {
            return (J_1907_R[])v_4262_N.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static void n_1700_B(J_1907_R type, String nameIn, int keyCode) {
            n_1700_B inputmappings$input = new n_1700_B(nameIn, type, keyCode);
            type.G_564_y.put(keyCode, (Object)inputmappings$input);
        }

        private J_1907_R(String p_i232180_3_, BiFunction<Integer, String, x_282_a> p_i232180_4_) {
            this.P_1922_E = p_i232180_3_;
            this.u_1723_Y = p_i232180_4_;
        }

        public n_1700_B n_1700_B(int keyCode) {
            return (n_1700_B)this.G_564_y.computeIfAbsent(keyCode, p_237525_1_ -> {
                int i = p_237525_1_;
                if (this == R_4764_Y) {
                    i = p_237525_1_ + 1;
                }
                String s = this.P_1922_E + "." + i;
                return new n_1700_B(s, this, p_237525_1_);
            });
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            v_4262_N = lightning.product.Q_4113_P$J_1907_R.n_1700_B();
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.unknown", -1);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(R_4764_Y, "key.mouse.left", 0);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(R_4764_Y, "key.mouse.right", 1);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(R_4764_Y, "key.mouse.middle", 2);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(R_4764_Y, "key.mouse.4", 3);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(R_4764_Y, "key.mouse.5", 4);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(R_4764_Y, "key.mouse.6", 5);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(R_4764_Y, "key.mouse.7", 6);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(R_4764_Y, "key.mouse.8", 7);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.0", 48);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.1", 49);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.2", 50);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.3", 51);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.4", 52);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.5", 53);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.6", 54);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.7", 55);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.8", 56);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.9", 57);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.a", 65);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.b", 66);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.c", 67);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.d", 68);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.e", 69);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f", 70);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.g", 71);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.h", 72);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.i", 73);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.j", 74);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.k", 75);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.l", 76);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.m", 77);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.n", 78);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.o", 79);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.p", 80);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.q", 81);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.r", 82);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.s", 83);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.t", 84);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.u", 85);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.v", 86);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.w", 87);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.x", 88);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.y", 89);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.z", 90);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f1", 290);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f2", 291);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f3", 292);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f4", 293);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f5", 294);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f6", 295);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f7", 296);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f8", 297);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f9", 298);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f10", 299);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f11", 300);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f12", 301);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f13", 302);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f14", 303);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f15", 304);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f16", 305);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f17", 306);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f18", 307);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f19", 308);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f20", 309);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f21", 310);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f22", 311);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f23", 312);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f24", 313);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.f25", 314);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.num.lock", 282);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.0", 320);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.1", 321);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.2", 322);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.3", 323);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.4", 324);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.5", 325);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.6", 326);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.7", 327);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.8", 328);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.9", 329);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.add", 334);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.decimal", 330);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.enter", 335);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.equal", 336);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.multiply", 332);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.divide", 331);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.keypad.subtract", 333);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.down", 264);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.left", 263);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.right", 262);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.up", 265);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.apostrophe", 39);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.backslash", 92);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.comma", 44);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.equal", 61);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.grave.accent", 96);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.left.bracket", 91);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.minus", 45);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.period", 46);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.right.bracket", 93);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.semicolon", 59);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.slash", 47);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.space", 32);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.tab", 258);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.left.alt", 342);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.left.control", 341);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.left.shift", 340);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.left.win", 343);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.right.alt", 346);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.right.control", 345);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.right.shift", 344);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.right.win", 347);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.enter", 257);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.escape", 256);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.backspace", 259);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.delete", 261);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.end", 269);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.home", 268);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.insert", 260);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.page.down", 267);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.page.up", 266);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.caps.lock", 280);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.pause", 284);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.scroll.lock", 281);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.menu", 348);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.print.screen", 283);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.world.1", 161);
            lightning.product.Q_4113_P$J_1907_R.n_1700_B(n_1700_B, "key.keyboard.world.2", 162);
        }
    }

    public static final class n_1700_B {
        private final String n_1700_B;
        private final J_1907_R J_1907_R;
        private final int R_4764_Y;
        private final l_52_h<x_282_a> G_564_y;
        private static final Map<String, n_1700_B> P_1922_E = Maps.newHashMap();

        private n_1700_B(String nameIn, J_1907_R typeIn, int keyCodeIn) {
            this.n_1700_B = nameIn;
            this.J_1907_R = typeIn;
            this.R_4764_Y = keyCodeIn;
            this.G_564_y = new l_52_h<x_282_a>(() -> typeIn.u_1723_Y.apply(keyCodeIn, nameIn));
            P_1922_E.put(nameIn, this);
        }

        public J_1907_R n_1700_B() {
            return this.J_1907_R;
        }

        public int J_1907_R() {
            return this.R_4764_Y;
        }

        public String R_4764_Y() {
            return this.n_1700_B;
        }

        public x_282_a G_564_y() {
            return this.G_564_y.n_1700_B();
        }

        public OptionalInt P_1922_E() {
            if (this.R_4764_Y >= 48 && this.R_4764_Y <= 57) {
                return OptionalInt.of(this.R_4764_Y - 48);
            }
            return this.R_4764_Y >= 320 && this.R_4764_Y <= 329 ? OptionalInt.of(this.R_4764_Y - 320) : OptionalInt.empty();
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                n_1700_B inputmappings$input = (n_1700_B)p_equals_1_;
                return this.R_4764_Y == inputmappings$input.R_4764_Y && this.J_1907_R == inputmappings$input.J_1907_R;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.J_1907_R, this.R_4764_Y});
        }

        public String toString() {
            return this.n_1700_B;
        }
    }
}

