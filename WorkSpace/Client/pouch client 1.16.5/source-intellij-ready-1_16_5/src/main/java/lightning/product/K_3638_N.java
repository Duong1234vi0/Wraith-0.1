/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.glfw.GLFW
 *  org.lwjgl.glfw.GLFWErrorCallback
 *  org.lwjgl.glfw.GLFWErrorCallbackI
 *  org.lwjgl.system.MemoryUtil
 */
package lightning.product;

import com.google.common.base.Charsets;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import lightning.product.StringDecomposer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.system.MemoryUtil;

public class K_3638_N {
    private final ByteBuffer n_1700_B = BufferUtils.createByteBuffer((int)8192);

    public String n_1700_B(long window, GLFWErrorCallbackI errorCallback) {
        GLFWErrorCallback glfwerrorcallback = GLFW.glfwSetErrorCallback((GLFWErrorCallbackI)errorCallback);
        String s = GLFW.glfwGetClipboardString((long)window);
        s = s != null ? StringDecomposer.n_1700_B(s) : "";
        GLFWErrorCallback glfwerrorcallback1 = GLFW.glfwSetErrorCallback((GLFWErrorCallbackI)glfwerrorcallback);
        if (glfwerrorcallback1 != null) {
            glfwerrorcallback1.free();
        }
        return s;
    }

    private static void n_1700_B(long window, ByteBuffer clipboardBuffer, byte[] clipboardContent) {
        ((Buffer)clipboardBuffer).clear();
        clipboardBuffer.put(clipboardContent);
        clipboardBuffer.put((byte)0);
        ((Buffer)clipboardBuffer).flip();
        GLFW.glfwSetClipboardString((long)window, (ByteBuffer)clipboardBuffer);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B(long window, String string) {
        byte[] abyte = string.getBytes(Charsets.UTF_8);
        int i = abyte.length + 1;
        if (i < this.n_1700_B.capacity()) {
            K_3638_N.n_1700_B(window, this.n_1700_B, abyte);
        } else {
            ByteBuffer bytebuffer = MemoryUtil.memAlloc((int)i);
            try {
                K_3638_N.n_1700_B(window, bytebuffer, abyte);
            }
            finally {
                MemoryUtil.memFree((Buffer)bytebuffer);
            }
        }
    }
}


