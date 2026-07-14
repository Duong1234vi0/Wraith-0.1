package ua.babka.render.shader;

import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.ByteBuffer;
import net.minecraft.client.gl.Framebuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import ua.babka.util.IMinecraft;

public class StencilUtil
implements IMinecraft {
    public static void checkSetupFBO(Framebuffer fbo) {
        if (fbo == null || !fbo.useDepthAttachment) {
            return;
        }
        int depthId = fbo.getDepthAttachment();
        GL11.glBindTexture((int)3553, (int)depthId);
        int format = GL11.glGetTexLevelParameteri((int)3553, (int)0, (int)4099);
        if (format != 35056) {
            GL11.glTexImage2D((int)3553, (int)0, (int)35056, (int)fbo.textureWidth, (int)fbo.textureHeight, (int)0, (int)34041, (int)34042, (ByteBuffer)null);
            GL30.glBindFramebuffer((int)36160, (int)fbo.fbo);
            GL30.glFramebufferTexture2D((int)36160, (int)33306, (int)3553, (int)depthId, (int)0);
            fbo.beginWrite(false);
        }
    }

    public static void push() {
        StencilUtil.checkSetupFBO(mc.getFramebuffer());
        GL11.glEnable((int)2960);
        GL11.glClearStencil((int)0);
        GL11.glClear((int)1024);
        GL11.glStencilFunc((int)519, (int)1, (int)255);
        GL11.glStencilOp((int)7681, (int)7681, (int)7681);
        RenderSystem.colorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
    }

    public static void read(int ref) {
        RenderSystem.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        GL11.glStencilFunc((int)514, (int)ref, (int)255);
        GL11.glStencilOp((int)7680, (int)7680, (int)7680);
    }

    public static void pop() {
        GL11.glDisable((int)2960);
    }
}

