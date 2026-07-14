package ua.babka.render.model;

import org.joml.Matrix4f;

public interface IRenderer {
    public static final Matrix4f DEFAULT_MATRIX = new Matrix4f();

    default public void render(double x2, double y2) {
        this.render((float)x2, (float)y2);
    }

    default public void render(float x2, float y2) {
        this.render(DEFAULT_MATRIX, x2, y2);
    }

    default public void render(Matrix4f matrix, double x2, double y2) {
        this.render(matrix, (float)x2, (float)y2);
    }

    default public void render(Matrix4f matrix, float x2, float y2) {
        this.render(matrix, x2, y2, 0.0f);
    }

    default public void render(double x2, double y2, double z2) {
        this.render((float)x2, (float)y2, (float)z2);
    }

    default public void render(float x2, float y2, float z2) {
        this.render(DEFAULT_MATRIX, x2, y2, z2);
    }

    default public void render(Matrix4f matrix, double x2, double y2, double z2) {
        this.render(matrix, (float)x2, (float)y2, (float)z2);
    }

    public void render(Matrix4f var1, float var2, float var3, float var4);
}

