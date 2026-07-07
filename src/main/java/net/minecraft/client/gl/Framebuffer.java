package net.minecraft.client.gl;

public class Framebuffer {
    public int textureWidth;
    public int textureHeight;
    public int viewportWidth;
    public int viewportHeight;
    public int colorAttachment;

    public Framebuffer(int width, int height, boolean useDepth) {
        resize(width, height);
    }

    public void setTexFilter(int filter) {
    }

    public void setClearColor(float red, float green, float blue, float alpha) {
    }

    public void clear() {
    }

    public void beginWrite(boolean setViewport) {
    }

    public void endWrite() {
    }

    public void beginRead() {
    }

    public void resize(int width, int height) {
        this.textureWidth = width;
        this.textureHeight = height;
        this.viewportWidth = width;
        this.viewportHeight = height;
    }

    public void drawInternal(int width, int height) {
    }

    public int getColorAttachment() {
        return colorAttachment;
    }

    public void delete() {
    }
}
