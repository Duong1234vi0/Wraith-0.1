package com.mojang.blaze3d.vertex;

import org.joml.Matrix4f;

public class VertexBuffer implements AutoCloseable {
    public VertexBuffer(Object usage) {
    }

    public void bind() {
    }

    public void upload(MeshData meshData) {
        if (meshData != null) {
            meshData.close();
        }
    }

    public void draw() {
    }

    public void draw(Matrix4f viewMatrix, Matrix4f projectionMatrix, Object shaderProgram) {
    }

    public static void unbind() {
    }

    @Override
    public void close() {
    }
}
