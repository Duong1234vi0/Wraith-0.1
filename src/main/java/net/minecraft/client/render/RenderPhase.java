package net.minecraft.client.render;

public class RenderPhase {
    public void startDrawing() {
    }

    public void endDrawing() {
    }

    public static class Target extends RenderPhase {
        private final Runnable beginAction;
        private final Runnable endAction;

        public Target(String name, Runnable beginAction, Runnable endAction) {
            this.beginAction = beginAction;
            this.endAction = endAction;
        }

        @Override
        public void startDrawing() {
            beginAction.run();
        }

        @Override
        public void endDrawing() {
            endAction.run();
        }
    }

    public static class ShaderProgram extends RenderPhase {
    }
}
