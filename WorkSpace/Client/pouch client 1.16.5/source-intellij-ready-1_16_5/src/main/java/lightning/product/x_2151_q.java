/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  org.apache.commons.io.IOUtils
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import lightning.product.C_3240_x;
import lightning.product.D_1098_v;
import lightning.product.N_4471_B;
import lightning.product.Resource;
import lightning.product.P_4249_L;
import lightning.product.ResourceManager;
import lightning.product.U_45_E;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.c_4477_a;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.z_3307_s;
import org.apache.commons.io.IOUtils;

public class x_2151_q
implements AutoCloseable {
    private final P_4249_L n_1700_B;
    private final ResourceManager J_1907_R;
    private final String R_4764_Y;
    private final List<N_4471_B> G_564_y = Lists.newArrayList();
    private final Map<String, P_4249_L> P_1922_E = Maps.newHashMap();
    private final List<P_4249_L> u_1723_Y = Lists.newArrayList();
    private D_1098_v v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private float s_956_w;
    private float u_2550_I;

    public x_2151_q(C_3240_x p_i1050_1_, ResourceManager resourceManagerIn, P_4249_L mainFramebufferIn, g_2336_b p_i1050_4_) throws IOException, JsonSyntaxException {
        this.J_1907_R = resourceManagerIn;
        this.n_1700_B = mainFramebufferIn;
        this.s_956_w = 0.0f;
        this.u_2550_I = 0.0f;
        this.w_1484_f = mainFramebufferIn.R_4764_Y;
        this.t_148_a = mainFramebufferIn.G_564_y;
        this.R_4764_Y = p_i1050_4_.toString();
        this.J_1907_R();
        this.n_1700_B(p_i1050_1_, p_i1050_4_);
    }

    private void n_1700_B(C_3240_x p_152765_1_, g_2336_b p_152765_2_) throws IOException, JsonSyntaxException {
        Resource iresource;
        block11: {
            iresource = null;
            try {
                iresource = this.J_1907_R.n_1700_B(p_152765_2_);
                JsonObject jsonobject = i_4431_W.n_1700_B(new InputStreamReader(iresource.J_1907_R(), StandardCharsets.UTF_8));
                if (i_4431_W.R_4764_Y(jsonobject, "targets")) {
                    JsonArray jsonarray = jsonobject.getAsJsonArray("targets");
                    int i = 0;
                    for (JsonElement jsonelement : jsonarray) {
                        try {
                            this.n_1700_B(jsonelement);
                        }
                        catch (Exception exception1) {
                            U_45_E jsonexception1 = U_45_E.n_1700_B(exception1);
                            jsonexception1.n_1700_B("targets[" + i + "]");
                            throw jsonexception1;
                        }
                        ++i;
                    }
                }
                if (!i_4431_W.R_4764_Y(jsonobject, "passes")) break block11;
                JsonArray jsonarray1 = jsonobject.getAsJsonArray("passes");
                int j = 0;
                for (JsonElement jsonelement1 : jsonarray1) {
                    try {
                        this.n_1700_B(p_152765_1_, jsonelement1);
                    }
                    catch (Exception exception) {
                        U_45_E jsonexception2 = U_45_E.n_1700_B(exception);
                        jsonexception2.n_1700_B("passes[" + j + "]");
                        throw jsonexception2;
                    }
                    ++j;
                }
            }
            catch (Exception exception2) {
                try {
                    Object s = iresource != null ? " (" + iresource.R_4764_Y() + ")" : "";
                    U_45_E jsonexception = U_45_E.n_1700_B(exception2);
                    jsonexception.J_1907_R(p_152765_2_.J_1907_R() + (String)s);
                    throw jsonexception;
                }
                catch (Throwable throwable) {
                    IOUtils.closeQuietly(iresource);
                    throw throwable;
                }
            }
        }
        IOUtils.closeQuietly((Closeable)iresource);
    }

    private void n_1700_B(JsonElement p_148027_1_) throws U_45_E {
        if (i_4431_W.n_1700_B(p_148027_1_)) {
            this.n_1700_B(p_148027_1_.getAsString(), this.w_1484_f, this.t_148_a);
        } else {
            JsonObject jsonobject = i_4431_W.w_1484_f(p_148027_1_, "target");
            String s = i_4431_W.u_1723_Y(jsonobject, "name");
            int i = i_4431_W.n_1700_B(jsonobject, "width", this.w_1484_f);
            int j = i_4431_W.n_1700_B(jsonobject, "height", this.t_148_a);
            if (this.P_1922_E.containsKey(s)) {
                throw new U_45_E(s + " is already defined");
            }
            this.n_1700_B(s, i, j);
        }
    }

    private void n_1700_B(C_3240_x p_152764_1_, JsonElement json) throws IOException {
        JsonArray jsonarray1;
        JsonObject jsonobject;
        block21: {
            jsonobject = i_4431_W.w_1484_f(json, "pass");
            String s = i_4431_W.u_1723_Y(jsonobject, "name");
            String s1 = i_4431_W.u_1723_Y(jsonobject, "intarget");
            String s2 = i_4431_W.u_1723_Y(jsonobject, "outtarget");
            P_4249_L framebuffer = this.J_1907_R(s1);
            P_4249_L framebuffer1 = this.J_1907_R(s2);
            if (framebuffer == null) {
                throw new U_45_E("Input target '" + s1 + "' does not exist");
            }
            if (framebuffer1 == null) {
                throw new U_45_E("Output target '" + s2 + "' does not exist");
            }
            N_4471_B shader = this.n_1700_B(s, framebuffer, framebuffer1);
            JsonArray jsonarray = i_4431_W.n_1700_B(jsonobject, "auxtargets", (JsonArray)null);
            if (jsonarray == null) break block21;
            int i = 0;
            for (JsonElement jsonelement : jsonarray) {
                block20: {
                    try {
                        P_4249_L framebuffer2;
                        boolean flag;
                        String s5;
                        block22: {
                            String s4;
                            JsonObject jsonobject1 = i_4431_W.w_1484_f(jsonelement, "auxtarget");
                            s5 = i_4431_W.u_1723_Y(jsonobject1, "name");
                            String s3 = i_4431_W.u_1723_Y(jsonobject1, "id");
                            if (s3.endsWith(":depth")) {
                                flag = true;
                                s4 = s3.substring(0, s3.lastIndexOf(58));
                            } else {
                                flag = false;
                                s4 = s3;
                            }
                            framebuffer2 = this.J_1907_R(s4);
                            if (framebuffer2 != null) break block22;
                            if (flag) {
                                throw new U_45_E("Render target '" + s4 + "' can't be used as depth buffer");
                            }
                            g_2336_b resourcelocation = new g_2336_b("textures/effect/" + s4 + ".png");
                            Resource iresource = null;
                            try {
                                iresource = this.J_1907_R.n_1700_B(resourcelocation);
                            }
                            catch (FileNotFoundException filenotfoundexception) {
                                try {
                                    throw new U_45_E("Render target or texture '" + s4 + "' does not exist");
                                }
                                catch (Throwable throwable) {
                                    IOUtils.closeQuietly(iresource);
                                    throw throwable;
                                }
                            }
                            IOUtils.closeQuietly((Closeable)iresource);
                            p_152764_1_.n_1700_B(resourcelocation);
                            c_4477_a lvt_22_2_ = p_152764_1_.J_1907_R(resourcelocation);
                            int lvt_23_1_ = i_4431_W.u_2550_I(jsonobject1, "width");
                            int lvt_24_1_ = i_4431_W.u_2550_I(jsonobject1, "height");
                            boolean flag1 = i_4431_W.w_1484_f(jsonobject1, "bilinear");
                            if (flag1) {
                                c_4037_x.n_1700_B(3553, 10241, 9729);
                                c_4037_x.n_1700_B(3553, 10240, 9729);
                            } else {
                                c_4037_x.n_1700_B(3553, 10241, 9728);
                                c_4037_x.n_1700_B(3553, 10240, 9728);
                            }
                            shader.n_1700_B(s5, lvt_22_2_::getGlTextureId, lvt_23_1_, lvt_24_1_);
                            break block20;
                        }
                        if (flag) {
                            shader.n_1700_B(s5, framebuffer2::M_588_G, framebuffer2.n_1700_B, framebuffer2.J_1907_R);
                        } else {
                            shader.n_1700_B(s5, framebuffer2::u_2550_I, framebuffer2.n_1700_B, framebuffer2.J_1907_R);
                        }
                    }
                    catch (Exception exception1) {
                        U_45_E jsonexception = U_45_E.n_1700_B(exception1);
                        jsonexception.n_1700_B("auxtargets[" + i + "]");
                        throw jsonexception;
                    }
                }
                ++i;
            }
        }
        if ((jsonarray1 = i_4431_W.n_1700_B(jsonobject, "uniforms", (JsonArray)null)) != null) {
            int l = 0;
            for (JsonElement jsonelement1 : jsonarray1) {
                try {
                    this.J_1907_R(jsonelement1);
                }
                catch (Exception exception) {
                    U_45_E jsonexception1 = U_45_E.n_1700_B(exception);
                    jsonexception1.n_1700_B("uniforms[" + l + "]");
                    throw jsonexception1;
                }
                ++l;
            }
        }
    }

    private void J_1907_R(JsonElement json) throws U_45_E {
        JsonObject jsonobject = i_4431_W.w_1484_f(json, "uniform");
        String s = i_4431_W.u_1723_Y(jsonobject, "name");
        z_3307_s shaderuniform = this.G_564_y.get(this.G_564_y.size() - 1).n_1700_B().n_1700_B(s);
        if (shaderuniform == null) {
            throw new U_45_E("Uniform '" + s + "' does not exist");
        }
        float[] afloat = new float[4];
        int i = 0;
        for (JsonElement jsonelement : i_4431_W.P_4830_p(jsonobject, "values")) {
            try {
                afloat[i] = i_4431_W.G_564_y(jsonelement, "value");
            }
            catch (Exception exception) {
                U_45_E jsonexception = U_45_E.n_1700_B(exception);
                jsonexception.n_1700_B("values[" + i + "]");
                throw jsonexception;
            }
            ++i;
        }
        switch (i) {
            default: {
                break;
            }
            case 1: {
                shaderuniform.n_1700_B(afloat[0]);
                break;
            }
            case 2: {
                shaderuniform.n_1700_B(afloat[0], afloat[1]);
                break;
            }
            case 3: {
                shaderuniform.n_1700_B(afloat[0], afloat[1], afloat[2]);
                break;
            }
            case 4: {
                shaderuniform.n_1700_B(afloat[0], afloat[1], afloat[2], afloat[3]);
            }
        }
    }

    public P_4249_L n_1700_B(String attributeName) {
        return this.P_1922_E.get(attributeName);
    }

    public void n_1700_B(String name, int width, int height) {
        P_4249_L framebuffer = new P_4249_L(width, height, true, MinecraftClient.n_1700_B);
        framebuffer.n_1700_B(0.0f, 0.0f, 0.0f, 0.0f);
        this.P_1922_E.put(name, framebuffer);
        if (width == this.w_1484_f && height == this.t_148_a) {
            this.u_1723_Y.add(framebuffer);
        }
    }

    @Override
    public void close() {
        for (P_4249_L framebuffer : this.P_1922_E.values()) {
            framebuffer.P_1922_E();
        }
        for (N_4471_B shader : this.G_564_y) {
            shader.close();
        }
        this.G_564_y.clear();
    }

    public N_4471_B n_1700_B(String programName, P_4249_L framebufferIn, P_4249_L framebufferOut) throws IOException {
        N_4471_B shader = new N_4471_B(this.J_1907_R, programName, framebufferIn, framebufferOut);
        this.G_564_y.add(this.G_564_y.size(), shader);
        return shader;
    }

    private void J_1907_R() {
        this.v_4262_N = D_1098_v.n_1700_B(this.n_1700_B.n_1700_B, (float)this.n_1700_B.J_1907_R, 0.1f, 1000.0f);
    }

    public void n_1700_B(int width, int height) {
        this.w_1484_f = this.n_1700_B.n_1700_B;
        this.t_148_a = this.n_1700_B.J_1907_R;
        this.J_1907_R();
        for (N_4471_B shader : this.G_564_y) {
            shader.n_1700_B(this.v_4262_N);
        }
        for (P_4249_L framebuffer : this.u_1723_Y) {
            framebuffer.n_1700_B(width, height, MinecraftClient.n_1700_B);
        }
    }

    public void n_1700_B(float partialTicks) {
        if (partialTicks < this.u_2550_I) {
            this.s_956_w += 1.0f - this.u_2550_I;
            this.s_956_w += partialTicks;
        } else {
            this.s_956_w += partialTicks - this.u_2550_I;
        }
        this.u_2550_I = partialTicks;
        while (this.s_956_w > 20.0f) {
            this.s_956_w -= 20.0f;
        }
        for (N_4471_B shader : this.G_564_y) {
            shader.n_1700_B(this.s_956_w / 20.0f);
        }
    }

    public final String n_1700_B() {
        return this.R_4764_Y;
    }

    private P_4249_L J_1907_R(String p_148017_1_) {
        if (p_148017_1_ == null) {
            return null;
        }
        return p_148017_1_.equals("minecraft:main") ? this.n_1700_B : this.P_1922_E.get(p_148017_1_);
    }
}



