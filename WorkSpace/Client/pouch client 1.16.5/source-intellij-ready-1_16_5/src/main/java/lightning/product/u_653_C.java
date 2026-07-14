/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.stb.STBTTFontinfo
 *  org.lwjgl.stb.STBTruetype
 *  org.lwjgl.system.MemoryUtil
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import lightning.product.N_1972_P;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.e_3495_r;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.j_938_M;
import lightning.product.n_4006_Y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryUtil;

public class u_653_C
implements n_4006_Y {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final g_2336_b J_1907_R;
    private final float R_4764_Y;
    private final float G_564_y;
    private final float P_1922_E;
    private final float u_1723_Y;
    private final String v_4262_N;

    public u_653_C(g_2336_b p_i49753_1_, float p_i49753_2_, float p_i49753_3_, float p_i49753_4_, float p_i49753_5_, String p_i49753_6_) {
        this.J_1907_R = p_i49753_1_;
        this.R_4764_Y = p_i49753_2_;
        this.G_564_y = p_i49753_3_;
        this.P_1922_E = p_i49753_4_;
        this.u_1723_Y = p_i49753_5_;
        this.v_4262_N = p_i49753_6_;
    }

    public static n_4006_Y n_1700_B(JsonObject p_211624_0_) {
        float f = 0.0f;
        float f1 = 0.0f;
        if (p_211624_0_.has("shift")) {
            JsonArray jsonarray = p_211624_0_.getAsJsonArray("shift");
            if (jsonarray.size() != 2) {
                throw new JsonParseException("Expected 2 elements in 'shift', found " + jsonarray.size());
            }
            f = i_4431_W.G_564_y(jsonarray.get(0), "shift[0]");
            f1 = i_4431_W.G_564_y(jsonarray.get(1), "shift[1]");
        }
        StringBuilder stringbuilder = new StringBuilder();
        if (p_211624_0_.has("skip")) {
            JsonElement jsonelement = p_211624_0_.get("skip");
            if (jsonelement.isJsonArray()) {
                JsonArray jsonarray1 = i_4431_W.t_148_a(jsonelement, "skip");
                for (int i = 0; i < jsonarray1.size(); ++i) {
                    stringbuilder.append(i_4431_W.n_1700_B(jsonarray1.get(i), "skip[" + i + "]"));
                }
            } else {
                stringbuilder.append(i_4431_W.n_1700_B(jsonelement, "skip"));
            }
        }
        return new u_653_C(new g_2336_b(i_4431_W.u_1723_Y(p_211624_0_, "file")), i_4431_W.n_1700_B(p_211624_0_, "size", 11.0f), i_4431_W.n_1700_B(p_211624_0_, "oversample", 1.0f), f, f1, stringbuilder.toString());
    }

    @Override
    @Nullable
    public e_3495_r n_1700_B(ResourceManager resourceManagerIn) {
        j_938_M j_938_M2;
        block10: {
            STBTTFontinfo stbttfontinfo = null;
            ByteBuffer bytebuffer = null;
            Resource iresource = resourceManagerIn.n_1700_B(new g_2336_b(this.J_1907_R.R_4764_Y(), "font/" + this.J_1907_R.J_1907_R()));
            try {
                n_1700_B.debug("Loading font {}", (Object)this.J_1907_R);
                stbttfontinfo = STBTTFontinfo.malloc();
                bytebuffer = N_1972_P.n_1700_B(iresource.J_1907_R());
                ((Buffer)bytebuffer).flip();
                n_1700_B.debug("Reading font {}", (Object)this.J_1907_R);
                if (!STBTruetype.stbtt_InitFont((STBTTFontinfo)stbttfontinfo, (ByteBuffer)bytebuffer)) {
                    throw new IOException("Invalid ttf");
                }
                j_938_M2 = new j_938_M(bytebuffer, stbttfontinfo, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N);
                if (iresource == null) break block10;
            }
            catch (Throwable throwable) {
                try {
                    if (iresource != null) {
                        try {
                            iresource.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (Exception exception) {
                    n_1700_B.error("Couldn't load truetype font {}", (Object)this.J_1907_R, (Object)exception);
                    if (stbttfontinfo != null) {
                        stbttfontinfo.free();
                    }
                    MemoryUtil.memFree(bytebuffer);
                    return null;
                }
            }
            iresource.close();
        }
        return j_938_M2;
    }
}


