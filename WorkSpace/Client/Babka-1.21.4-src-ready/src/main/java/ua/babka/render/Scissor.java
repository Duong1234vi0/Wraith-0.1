package ua.babka.render;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Rectangle;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;

public class Scissor {
    private static State state = new State();
    private static final List<State> stateStack = Lists.newArrayList();

    public static void push() {
        stateStack.add(state.clone());
    }

    public static void pop() {
        state = stateStack.remove(stateStack.size() - 1);
        if (Scissor.state.enabled) {
            RenderSystem.enableScissor((int)Scissor.state.x, (int)Scissor.state.y, (int)Scissor.state.width, (int)Scissor.state.height);
        } else {
            RenderSystem.disableScissor();
        }
    }

    public static void unset() {
        RenderSystem.disableScissor();
        Scissor.state.enabled = false;
    }

    public static void setFromComponentCoordinates(int x2, int y2, int width, int height) {
        Window window = MinecraftClient.getInstance().getWindow();
        int scaleFactor = (int)window.getScaleFactor();
        int screenX = x2 * scaleFactor;
        int screenY = y2 * scaleFactor;
        int screenWidth = width * scaleFactor;
        int screenHeight = height * scaleFactor;
        screenY = window.getHeight() - screenY - screenHeight;
        Scissor.set(screenX, screenY, screenWidth, screenHeight);
    }

    public static void setFromComponentCoordinates(double x2, double y2, double width, double height) {
        Window window = MinecraftClient.getInstance().getWindow();
        int scaleFactor = (int)window.getScaleFactor();
        int screenX = (int)(x2 * (double)scaleFactor);
        int screenY = (int)(y2 * (double)scaleFactor);
        int screenWidth = (int)(width * (double)scaleFactor);
        int screenHeight = (int)(height * (double)scaleFactor);
        screenY = window.getHeight() - screenY - screenHeight;
        Scissor.set(screenX, screenY, screenWidth, screenHeight);
    }

    public static void setFromComponentCoordinates(double x2, double y2, double width, double height, float scale) {
        Window window = MinecraftClient.getInstance().getWindow();
        float animationValue = scale;
        float halfRest = (1.0f - animationValue) / 2.0f;
        double testX = x2 + width * (double)halfRest;
        double testY = y2 + height * (double)halfRest;
        double testW = width * (double)animationValue;
        double testH = height * (double)animationValue;
        testX = testX * (double)animationValue + ((double)window.getScaledWidth() - testW) * (double)halfRest;
        int scaleFactor = (int)window.getScaleFactor();
        int screenX = (int)(testX * (double)scaleFactor);
        int screenY = (int)(testY * (double)scaleFactor);
        int screenWidth = (int)(testW * (double)scaleFactor);
        int screenHeight = (int)(testH * (double)scaleFactor);
        screenY = window.getHeight() - screenY - screenHeight;
        Scissor.set(screenX, screenY, screenWidth, screenHeight);
    }

    public static void scissor(Window window, double x2, double y2, double width, double height) {
        if (x2 + width == x2 || y2 + height == y2 || x2 < 0.0 || y2 + height < 0.0) {
            return;
        }
        double scaleFactor = window.getScaleFactor();
        int sx = (int)Math.round(x2 * scaleFactor);
        int sy = (int)Math.round(((double)window.getScaledHeight() - (y2 + height)) * scaleFactor);
        int sw = (int)Math.round(width * scaleFactor);
        int sh = (int)Math.round(height * scaleFactor);
        RenderSystem.enableScissor((int)sx, (int)sy, (int)sw, (int)sh);
    }

    public static void set(int x2, int y2, int width, int height) {
        Window window = MinecraftClient.getInstance().getWindow();
        Rectangle screen = new Rectangle(0, 0, window.getWidth(), window.getHeight());
        Rectangle current = Scissor.state.enabled ? new Rectangle(Scissor.state.x, Scissor.state.y, Scissor.state.width, Scissor.state.height) : screen;
        Rectangle target = new Rectangle(x2 + Scissor.state.transX, y2 + Scissor.state.transY, width, height);
        Rectangle result = current.intersection(target).intersection(screen);
        if (result.width < 0) {
            result.width = 0;
        }
        if (result.height < 0) {
            result.height = 0;
        }
        Scissor.state.enabled = true;
        Scissor.state.x = result.x;
        Scissor.state.y = result.y;
        Scissor.state.width = result.width;
        Scissor.state.height = result.height;
        RenderSystem.enableScissor((int)result.x, (int)result.y, (int)result.width, (int)result.height);
    }

    public static void translate(int x2, int y2) {
        Scissor.state.transX = x2;
        Scissor.state.transY = y2;
    }

    public static void translateFromComponentCoordinates(int x2, int y2) {
        Window window = MinecraftClient.getInstance().getWindow();
        int scaleFactor = (int)window.getScaleFactor();
        int screenX = x2 * scaleFactor;
        int screenY = y2 * scaleFactor;
        screenY = window.getScaledHeight() * scaleFactor - screenY;
        Scissor.translate(screenX, screenY);
    }

    private static class State
    implements Cloneable {
        public boolean enabled;
        public int transX;
        public int transY;
        public int x;
        public int y;
        public int width;
        public int height;

        private State() {
        }

        public State clone() {
            try {
                return (State)super.clone();
            }
            catch (CloneNotSupportedException e2) {
                throw new AssertionError((Object)e2);
            }
        }
    }
}

