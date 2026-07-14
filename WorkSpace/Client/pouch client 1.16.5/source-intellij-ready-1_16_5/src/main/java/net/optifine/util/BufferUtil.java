/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import lightning.product.A_1726_L;
import lightning.product.D_3318_r;
import lightning.product.b_1213_w;

public class BufferUtil {
    public static String getBufferHex(D_3318_r bb) {
        int i = bb.u_2550_I();
        String s = "";
        int j = -1;
        if (i == 7) {
            s = "quad";
            j = 4;
        } else {
            if (i != 4) {
                return "Invalid draw mode: " + i;
            }
            s = "triangle";
            j = 3;
        }
        StringBuffer stringbuffer = new StringBuffer();
        int k = bb.M_588_G();
        for (int l = 0; l < k; ++l) {
            if (l % j == 0) {
                stringbuffer.append(s + " " + l / j + "\n");
            }
            String s1 = BufferUtil.getVertexHex(l, bb);
            stringbuffer.append(s1);
            stringbuffer.append("\n");
        }
        return stringbuffer.toString();
    }

    private static String getVertexHex(int vertex, D_3318_r bb) {
        StringBuffer stringbuffer = new StringBuffer();
        ByteBuffer bytebuffer = bb.P_4830_p();
        b_1213_w vertexformat = bb.w_1457_N();
        int i = bb.Y_601_j() + vertex * vertexformat.J_1907_R();
        for (A_1726_L vertexformatelement : vertexformat.R_4764_Y()) {
            if (vertexformatelement.u_1723_Y() > 0) {
                stringbuffer.append("(");
            }
            for (int j = 0; j < vertexformatelement.u_1723_Y(); ++j) {
                if (j > 0) {
                    stringbuffer.append(" ");
                }
                switch (vertexformatelement.n_1700_B()) {
                    case n_1700_B: {
                        stringbuffer.append(bytebuffer.getFloat(i));
                        break;
                    }
                    case J_1907_R: 
                    case R_4764_Y: {
                        stringbuffer.append(bytebuffer.get(i));
                        break;
                    }
                    case G_564_y: 
                    case P_1922_E: {
                        stringbuffer.append(bytebuffer.getShort(i));
                        break;
                    }
                    case u_1723_Y: 
                    case v_4262_N: {
                        stringbuffer.append(bytebuffer.getShort(i));
                        break;
                    }
                    default: {
                        stringbuffer.append("??");
                    }
                }
                i += vertexformatelement.n_1700_B().n_1700_B();
            }
            if (vertexformatelement.u_1723_Y() <= 0) continue;
            stringbuffer.append(")");
        }
        return stringbuffer.toString();
    }

    public static String getBufferString(IntBuffer buf) {
        if (buf == null) {
            return "null";
        }
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("(pos=" + buf.position() + " lim=" + buf.limit() + " cap=" + buf.capacity() + ")");
        stringbuffer.append("[");
        int i = Math.min(buf.limit(), 1024);
        for (int j = 0; j < i; ++j) {
            if (j > 0) {
                stringbuffer.append(", ");
            }
            stringbuffer.append(buf.get(j));
        }
        stringbuffer.append("]");
        return stringbuffer.toString();
    }

    public static int[] toArray(IntBuffer buf) {
        int[] aint = new int[buf.limit()];
        for (int i = 0; i < aint.length; ++i) {
            aint[i] = buf.get(i);
        }
        return aint;
    }
}

