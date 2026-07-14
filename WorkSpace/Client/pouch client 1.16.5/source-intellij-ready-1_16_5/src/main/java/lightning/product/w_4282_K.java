/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  javax.annotation.Nullable
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.IntSupplier;
import javax.annotation.Nullable;
import lightning.product.A_1471_w;
import lightning.product.Resource;
import lightning.product.O_3339_a;
import lightning.product.ResourceManager;
import lightning.product.U_45_E;
import lightning.product.X_933_l;
import lightning.product.Z_4614_k;
import lightning.product.c_4037_x;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.v_1007_D;
import lightning.product.y_3193_B;
import lightning.product.z_3307_s;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class w_4282_K
implements AutoCloseable,
y_3193_B {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Z_4614_k J_1907_R = new Z_4614_k();
    private static w_4282_K R_4764_Y;
    private static int G_564_y;
    private final Map<String, IntSupplier> P_1922_E = Maps.newHashMap();
    private final List<String> u_1723_Y = Lists.newArrayList();
    private final List<Integer> v_4262_N = Lists.newArrayList();
    private final List<z_3307_s> w_1484_f = Lists.newArrayList();
    private final List<Integer> t_148_a = Lists.newArrayList();
    private final Map<String, z_3307_s> s_956_w = Maps.newHashMap();
    private final int u_2550_I;
    private final String M_588_G;
    private boolean P_4830_p;
    private final A_1471_w h_1847_R;
    private final List<Integer> Q_4569_t;
    private final List<String> M_182_A;
    private final v_1007_D t_1786_h;
    private final v_1007_D multiplayerClientSuggestionProvider;

    public w_4282_K(ResourceManager p_i50988_1_, String p_i50988_2_) throws IOException {
        g_2336_b resourcelocation = new g_2336_b("shaders/program/" + p_i50988_2_ + ".json");
        this.M_588_G = p_i50988_2_;
        Resource iresource = null;
        try {
            JsonArray jsonarray2;
            JsonArray jsonarray1;
            iresource = p_i50988_1_.n_1700_B(resourcelocation);
            JsonObject jsonobject = i_4431_W.n_1700_B(new InputStreamReader(iresource.J_1907_R(), StandardCharsets.UTF_8));
            String s = i_4431_W.u_1723_Y(jsonobject, "vertex");
            String s2 = i_4431_W.u_1723_Y(jsonobject, "fragment");
            JsonArray jsonarray = i_4431_W.n_1700_B(jsonobject, "samplers", (JsonArray)null);
            if (jsonarray != null) {
                int i = 0;
                for (Object jsonelement : jsonarray) {
                    try {
                        this.n_1700_B((JsonElement)jsonelement);
                    }
                    catch (Exception exception2) {
                        U_45_E jsonexception1 = U_45_E.n_1700_B(exception2);
                        jsonexception1.n_1700_B("samplers[" + i + "]");
                        throw jsonexception1;
                    }
                    ++i;
                }
            }
            if ((jsonarray1 = i_4431_W.n_1700_B(jsonobject, "attributes", (JsonArray)null)) != null) {
                int j = 0;
                this.Q_4569_t = Lists.newArrayListWithCapacity((int)jsonarray1.size());
                this.M_182_A = Lists.newArrayListWithCapacity((int)jsonarray1.size());
                for (Iterator jsonelement1 : jsonarray1) {
                    try {
                        this.M_182_A.add(i_4431_W.n_1700_B((JsonElement)jsonelement1, "attribute"));
                    }
                    catch (Exception exception1) {
                        U_45_E jsonexception2 = U_45_E.n_1700_B(exception1);
                        jsonexception2.n_1700_B("attributes[" + j + "]");
                        throw jsonexception2;
                    }
                    ++j;
                }
            } else {
                this.Q_4569_t = null;
                this.M_182_A = null;
            }
            if ((jsonarray2 = i_4431_W.n_1700_B(jsonobject, "uniforms", (JsonArray)null)) != null) {
                int k = 0;
                for (JsonElement jsonelement2 : jsonarray2) {
                    try {
                        this.J_1907_R(jsonelement2);
                    }
                    catch (Exception exception) {
                        U_45_E jsonexception3 = U_45_E.n_1700_B(exception);
                        jsonexception3.n_1700_B("uniforms[" + k + "]");
                        throw jsonexception3;
                    }
                    ++k;
                }
            }
            this.h_1847_R = w_4282_K.n_1700_B(i_4431_W.n_1700_B(jsonobject, "blend", (JsonObject)null));
            this.t_1786_h = w_4282_K.n_1700_B(p_i50988_1_, v_1007_D.n_1700_B.n_1700_B, s);
            this.multiplayerClientSuggestionProvider = w_4282_K.n_1700_B(p_i50988_1_, v_1007_D.n_1700_B.J_1907_R, s2);
            this.u_2550_I = O_3339_a.n_1700_B();
            O_3339_a.J_1907_R(this);
            this.v_4262_N();
            if (this.M_182_A != null) {
                for (String s3 : this.M_182_A) {
                    int l = z_3307_s.J_1907_R(this.u_2550_I, s3);
                    this.Q_4569_t.add(l);
                }
            }
        }
        catch (Exception exception3) {
            Object s1 = iresource != null ? " (" + iresource.R_4764_Y() + ")" : "";
            U_45_E jsonexception = U_45_E.n_1700_B(exception3);
            jsonexception.J_1907_R(resourcelocation.J_1907_R() + (String)s1);
            throw jsonexception;
        }
        finally {
            IOUtils.closeQuietly((Closeable)iresource);
        }
        this.J_1907_R();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static v_1007_D n_1700_B(ResourceManager p_216542_0_, v_1007_D.n_1700_B p_216542_1_, String p_216542_2_) throws IOException {
        v_1007_D shaderloader = p_216542_1_.R_4764_Y().get(p_216542_2_);
        if (shaderloader == null) {
            g_2336_b resourcelocation = new g_2336_b("shaders/program/" + p_216542_2_ + p_216542_1_.J_1907_R());
            Resource iresource = p_216542_0_.n_1700_B(resourcelocation);
            try {
                shaderloader = v_1007_D.n_1700_B(p_216542_1_, p_216542_2_, iresource.J_1907_R(), iresource.R_4764_Y());
            }
            finally {
                IOUtils.closeQuietly((Closeable)iresource);
            }
        }
        return shaderloader;
    }

    public static A_1471_w n_1700_B(JsonObject p_216543_0_) {
        if (p_216543_0_ == null) {
            return new A_1471_w();
        }
        int i = 32774;
        int j = 1;
        int k = 0;
        int l = 1;
        int i1 = 0;
        boolean flag = true;
        boolean flag1 = false;
        if (i_4431_W.n_1700_B(p_216543_0_, "func") && (i = A_1471_w.n_1700_B(p_216543_0_.get("func").getAsString())) != 32774) {
            flag = false;
        }
        if (i_4431_W.n_1700_B(p_216543_0_, "srcrgb") && (j = A_1471_w.J_1907_R(p_216543_0_.get("srcrgb").getAsString())) != 1) {
            flag = false;
        }
        if (i_4431_W.n_1700_B(p_216543_0_, "dstrgb") && (k = A_1471_w.J_1907_R(p_216543_0_.get("dstrgb").getAsString())) != 0) {
            flag = false;
        }
        if (i_4431_W.n_1700_B(p_216543_0_, "srcalpha")) {
            l = A_1471_w.J_1907_R(p_216543_0_.get("srcalpha").getAsString());
            if (l != 1) {
                flag = false;
            }
            flag1 = true;
        }
        if (i_4431_W.n_1700_B(p_216543_0_, "dstalpha")) {
            i1 = A_1471_w.J_1907_R(p_216543_0_.get("dstalpha").getAsString());
            if (i1 != 0) {
                flag = false;
            }
            flag1 = true;
        }
        if (flag) {
            return new A_1471_w();
        }
        return flag1 ? new A_1471_w(j, k, l, i1, i) : new A_1471_w(j, k, i);
    }

    @Override
    public void close() {
        for (z_3307_s shaderuniform : this.w_1484_f) {
            shaderuniform.close();
        }
        O_3339_a.n_1700_B(this);
    }

    public void P_1922_E() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        O_3339_a.n_1700_B(0);
        G_564_y = -1;
        R_4764_Y = null;
        for (int i = 0; i < this.v_4262_N.size(); ++i) {
            if (this.P_1922_E.get(this.u_1723_Y.get(i)) == null) continue;
            X_933_l.t_1786_h(33984 + i);
            X_933_l.d_2461_k();
            X_933_l.w_1457_N(0);
        }
    }

    public void u_1723_Y() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        this.P_4830_p = false;
        R_4764_Y = this;
        this.h_1847_R.n_1700_B();
        if (this.u_2550_I != G_564_y) {
            O_3339_a.n_1700_B(this.u_2550_I);
            G_564_y = this.u_2550_I;
        }
        for (int i = 0; i < this.v_4262_N.size(); ++i) {
            String s = this.u_1723_Y.get(i);
            IntSupplier intsupplier = this.P_1922_E.get(s);
            if (intsupplier == null) continue;
            c_4037_x.P_1922_E(33984 + i);
            c_4037_x.x_607_J();
            int j = intsupplier.getAsInt();
            if (j == -1) continue;
            c_4037_x.v_4262_N(j);
            z_3307_s.n_1700_B((int)this.v_4262_N.get(i), i);
        }
        for (z_3307_s shaderuniform : this.w_1484_f) {
            shaderuniform.J_1907_R();
        }
    }

    @Override
    public void J_1907_R() {
        this.P_4830_p = true;
    }

    @Nullable
    public z_3307_s n_1700_B(String p_216539_1_) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return this.s_956_w.get(p_216539_1_);
    }

    public Z_4614_k J_1907_R(String p_216538_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        z_3307_s shaderuniform = this.n_1700_B(p_216538_1_);
        return shaderuniform == null ? J_1907_R : shaderuniform;
    }

    private void v_4262_N() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        IntArrayList intlist = new IntArrayList();
        for (int i = 0; i < this.u_1723_Y.size(); ++i) {
            String s = this.u_1723_Y.get(i);
            int j = z_3307_s.n_1700_B(this.u_2550_I, s);
            if (j == -1) {
                n_1700_B.warn("Shader {} could not find sampler named {} in the specified shader program.", (Object)this.M_588_G, (Object)s);
                this.P_1922_E.remove(s);
                intlist.add(i);
                continue;
            }
            this.v_4262_N.add(j);
        }
        for (int l = intlist.size() - 1; l >= 0; --l) {
            this.u_1723_Y.remove(intlist.getInt(l));
        }
        for (z_3307_s shaderuniform : this.w_1484_f) {
            String s1 = shaderuniform.n_1700_B();
            int k = z_3307_s.n_1700_B(this.u_2550_I, s1);
            if (k == -1) {
                n_1700_B.warn("Could not find uniform named {} in the specified shader program.", (Object)s1);
                continue;
            }
            this.t_148_a.add(k);
            shaderuniform.n_1700_B(k);
            this.s_956_w.put(s1, shaderuniform);
        }
    }

    private void n_1700_B(JsonElement p_216541_1_) {
        JsonObject jsonobject = i_4431_W.w_1484_f(p_216541_1_, "sampler");
        String s = i_4431_W.u_1723_Y(jsonobject, "name");
        if (!i_4431_W.n_1700_B(jsonobject, "file")) {
            this.P_1922_E.put(s, null);
            this.u_1723_Y.add(s);
        } else {
            this.u_1723_Y.add(s);
        }
    }

    public void n_1700_B(String p_216537_1_, IntSupplier p_216537_2_) {
        if (this.P_1922_E.containsKey(p_216537_1_)) {
            this.P_1922_E.remove(p_216537_1_);
        }
        this.P_1922_E.put(p_216537_1_, p_216537_2_);
        this.J_1907_R();
    }

    private void J_1907_R(JsonElement p_216540_1_) throws U_45_E {
        JsonObject jsonobject = i_4431_W.w_1484_f(p_216540_1_, "uniform");
        String s = i_4431_W.u_1723_Y(jsonobject, "name");
        int i = z_3307_s.n_1700_B(i_4431_W.u_1723_Y(jsonobject, "type"));
        int j = i_4431_W.u_2550_I(jsonobject, "count");
        float[] afloat = new float[Math.max(j, 16)];
        JsonArray jsonarray = i_4431_W.P_4830_p(jsonobject, "values");
        if (jsonarray.size() != j && jsonarray.size() > 1) {
            throw new U_45_E("Invalid amount of values specified (expected " + j + ", found " + jsonarray.size() + ")");
        }
        int k = 0;
        for (JsonElement jsonelement : jsonarray) {
            try {
                afloat[k] = i_4431_W.G_564_y(jsonelement, "value");
            }
            catch (Exception exception) {
                U_45_E jsonexception = U_45_E.n_1700_B(exception);
                jsonexception.n_1700_B("values[" + k + "]");
                throw jsonexception;
            }
            ++k;
        }
        if (j > 1 && jsonarray.size() == 1) {
            while (k < j) {
                afloat[k] = afloat[0];
                ++k;
            }
        }
        int l = j > 1 && j <= 4 && i < 8 ? j - 1 : 0;
        z_3307_s shaderuniform = new z_3307_s(s, i + l, j, this);
        if (i <= 3) {
            shaderuniform.n_1700_B((int)afloat[0], (int)afloat[1], (int)afloat[2], (int)afloat[3]);
        } else if (i <= 7) {
            shaderuniform.J_1907_R(afloat[0], afloat[1], afloat[2], afloat[3]);
        } else {
            shaderuniform.n_1700_B(afloat);
        }
        this.w_1484_f.add(shaderuniform);
    }

    @Override
    public v_1007_D R_4764_Y() {
        return this.t_1786_h;
    }

    @Override
    public v_1007_D G_564_y() {
        return this.multiplayerClientSuggestionProvider;
    }

    @Override
    public int n_1700_B() {
        return this.u_2550_I;
    }

    static {
        G_564_y = -1;
    }
}


