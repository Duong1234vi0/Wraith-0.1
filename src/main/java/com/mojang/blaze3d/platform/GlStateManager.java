package com.mojang.blaze3d.platform;

import org.lwjgl.opengl.GL20;

public final class GlStateManager {
    private static int activeTexture;

    private GlStateManager() {
    }

    public enum SrcFactor {
        SRC_ALPHA,
        ONE,
        ZERO,
        DST_COLOR
    }

    public enum DstFactor {
        ONE_MINUS_SRC_ALPHA,
        ONE,
        ZERO,
        SRC_COLOR
    }

    public static void _activeTexture(int texture) {
        activeTexture = texture;
    }

    public static int _getActiveTexture() {
        return activeTexture;
    }

    public static void _bindTexture(int texture) {
    }

    public static void _clearColor(float red, float green, float blue, float alpha) {
    }

    public static int glCreateProgram() {
        return GL20.glCreateProgram();
    }

    public static void glAttachShader(int program, int shader) {
        GL20.glAttachShader(program, shader);
    }

    public static void glLinkProgram(int program) {
        GL20.glLinkProgram(program);
    }

    public static int glGetProgrami(int program, int pname) {
        return GL20.glGetProgrami(program, pname);
    }

    public static String glGetProgramInfoLog(int program, int maxLength) {
        return GL20.glGetProgramInfoLog(program, maxLength);
    }

    public static void glDeleteShader(int shader) {
        GL20.glDeleteShader(shader);
    }

    public static int glCreateShader(int type) {
        return GL20.glCreateShader(type);
    }

    public static void glShaderSource(int shader, CharSequence source) {
        GL20.glShaderSource(shader, source);
    }

    public static void glCompileShader(int shader) {
        GL20.glCompileShader(shader);
    }

    public static int glGetShaderi(int shader, int pname) {
        return GL20.glGetShaderi(shader, pname);
    }

    public static String glGetShaderInfoLog(int shader, int maxLength) {
        return GL20.glGetShaderInfoLog(shader, maxLength);
    }

    public static void _glUseProgram(int program) {
        GL20.glUseProgram(program);
    }

    public static void glDeleteProgram(int program) {
        GL20.glDeleteProgram(program);
    }
}
