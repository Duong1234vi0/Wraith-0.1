/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.system.MemoryUtil
 */
package lightning.product;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.ThreadLocalRandom;
import lightning.product.SharedConstants;
import lightning.product.X_933_l;
import lightning.product.c_4037_x;
import lightning.product.i_2518_W;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public class N_1972_P {
    private static final Logger n_1700_B = LogManager.getLogger();

    public static int n_1700_B() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        if (SharedConstants.G_564_y) {
            int[] aint = new int[ThreadLocalRandom.current().nextInt(15) + 1];
            X_933_l.n_1700_B(aint);
            int i = X_933_l.G_624_v();
            X_933_l.J_1907_R(aint);
            return i;
        }
        return X_933_l.G_624_v();
    }

    public static void n_1700_B(int textureId) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        X_933_l.multiplayerClientSuggestionProvider(textureId);
    }

    public static void n_1700_B(int textureId, int width, int height) {
        N_1972_P.n_1700_B(i_2518_W.J_1907_R.n_1700_B, textureId, 0, width, height);
    }

    public static void n_1700_B(i_2518_W.J_1907_R pixelFormat, int textureId, int width, int height) {
        N_1972_P.n_1700_B(pixelFormat, textureId, 0, width, height);
    }

    public static void n_1700_B(int textureId, int mipmapLevel, int width, int height) {
        N_1972_P.n_1700_B(i_2518_W.J_1907_R.n_1700_B, textureId, mipmapLevel, width, height);
    }

    public static void n_1700_B(i_2518_W.J_1907_R pixelFormat, int textureId, int mipmapLevel, int width, int height) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        N_1972_P.J_1907_R(textureId);
        if (mipmapLevel >= 0) {
            X_933_l.J_1907_R(3553, 33085, mipmapLevel);
            X_933_l.J_1907_R(3553, 33082, 0);
            X_933_l.J_1907_R(3553, 33083, mipmapLevel);
            X_933_l.n_1700_B(3553, 34049, 0.0f);
        }
        for (int i = 0; i <= mipmapLevel; ++i) {
            X_933_l.n_1700_B(3553, i, pixelFormat.n_1700_B(), width >> i, height >> i, 0, 6408, 5121, null);
        }
    }

    private static void J_1907_R(int textureId) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        X_933_l.w_1457_N(textureId);
    }

    public static ByteBuffer n_1700_B(InputStream inputStreamIn) throws IOException {
        ByteBuffer bytebuffer;
        if (inputStreamIn instanceof FileInputStream) {
            FileInputStream fileinputstream = (FileInputStream)inputStreamIn;
            FileChannel filechannel = fileinputstream.getChannel();
            bytebuffer = MemoryUtil.memAlloc((int)((int)filechannel.size() + 1));
            while (filechannel.read(bytebuffer) != -1) {
            }
        } else {
            bytebuffer = MemoryUtil.memAlloc((int)8192);
            ReadableByteChannel readablebytechannel = Channels.newChannel(inputStreamIn);
            while (readablebytechannel.read(bytebuffer) != -1) {
                if (bytebuffer.remaining() != 0) continue;
                bytebuffer = MemoryUtil.memRealloc((ByteBuffer)bytebuffer, (int)(bytebuffer.capacity() * 2));
            }
        }
        return bytebuffer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String J_1907_R(InputStream inputStreamIn) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        ByteBuffer bytebuffer = null;
        try {
            bytebuffer = N_1972_P.n_1700_B(inputStreamIn);
            int i = bytebuffer.position();
            ((Buffer)bytebuffer).rewind();
            String string = MemoryUtil.memASCII((ByteBuffer)bytebuffer, (int)i);
            return string;
        }
        catch (IOException iOException) {
        }
        finally {
            if (bytebuffer != null) {
                MemoryUtil.memFree((Buffer)bytebuffer);
            }
        }
        return null;
    }

    public static void n_1700_B(IntBuffer bufferIn, int width, int height) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glPixelStorei((int)3312, (int)0);
        GL11.glPixelStorei((int)3313, (int)0);
        GL11.glPixelStorei((int)3314, (int)0);
        GL11.glPixelStorei((int)3315, (int)0);
        GL11.glPixelStorei((int)3316, (int)0);
        GL11.glPixelStorei((int)3317, (int)4);
        GL11.glTexImage2D((int)3553, (int)0, (int)6408, (int)width, (int)height, (int)0, (int)32993, (int)33639, (IntBuffer)bufferIn);
        GL11.glTexParameteri((int)3553, (int)10242, (int)10497);
        GL11.glTexParameteri((int)3553, (int)10243, (int)10497);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
    }
}


