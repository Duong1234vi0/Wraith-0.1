/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.ProfilerFiller;
import lightning.product.Z_976_R;
import lightning.product.g_2336_b;
import lightning.product.SimplePreparableReloadListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class K_2357_w
extends SimplePreparableReloadListener<n_1700_B> {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final g_2336_b J_1907_R = new g_2336_b("gpu_warnlist.json");
    private ImmutableMap<String, String> R_4764_Y = ImmutableMap.of();
    private boolean G_564_y;
    private boolean P_1922_E;
    private boolean u_1723_Y;

    public boolean J_1907_R() {
        return !this.R_4764_Y.isEmpty();
    }

    public boolean R_4764_Y() {
        return this.J_1907_R() && !this.P_1922_E;
    }

    public void G_564_y() {
        this.G_564_y = true;
    }

    public void P_1922_E() {
        this.P_1922_E = true;
    }

    public void u_1723_Y() {
        this.P_1922_E = true;
        this.u_1723_Y = true;
    }

    public boolean v_4262_N() {
        return this.G_564_y && !this.P_1922_E;
    }

    public boolean w_1484_f() {
        return this.u_1723_Y;
    }

    public void t_148_a() {
        this.G_564_y = false;
        this.P_1922_E = false;
        this.u_1723_Y = false;
    }

    @Nullable
    public String s_956_w() {
        return (String)this.R_4764_Y.get((Object)"renderer");
    }

    @Nullable
    public String u_2550_I() {
        return (String)this.R_4764_Y.get((Object)"version");
    }

    @Nullable
    public String M_588_G() {
        return (String)this.R_4764_Y.get((Object)"vendor");
    }

    @Nullable
    public String P_4830_p() {
        StringBuilder stringbuilder = new StringBuilder();
        this.R_4764_Y.forEach((p_243498_1_, p_243498_2_) -> stringbuilder.append((String)p_243498_1_).append(": ").append((String)p_243498_2_));
        return stringbuilder.length() == 0 ? null : stringbuilder.toString();
    }

    protected n_1700_B n_1700_B(ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        ArrayList list = Lists.newArrayList();
        ArrayList list1 = Lists.newArrayList();
        ArrayList list2 = Lists.newArrayList();
        profilerIn.n_1700_B();
        JsonObject jsonobject = K_2357_w.J_1907_R(resourceManagerIn, profilerIn);
        if (jsonobject != null) {
            profilerIn.n_1700_B("compile_regex");
            K_2357_w.n_1700_B(jsonobject.getAsJsonArray("renderer"), list);
            K_2357_w.n_1700_B(jsonobject.getAsJsonArray("version"), list1);
            K_2357_w.n_1700_B(jsonobject.getAsJsonArray("vendor"), list2);
            profilerIn.R_4764_Y();
        }
        profilerIn.J_1907_R();
        return new n_1700_B(list, list1, list2);
    }

    protected void n_1700_B(n_1700_B objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        this.R_4764_Y = objectIn.n_1700_B();
    }

    private static void n_1700_B(JsonArray p_241693_0_, List<Pattern> p_241693_1_) {
        p_241693_0_.forEach(p_241694_1_ -> p_241693_1_.add(Pattern.compile(p_241694_1_.getAsString(), 2)));
    }

    @Nullable
    private static JsonObject J_1907_R(ResourceManager p_241696_0_, ProfilerFiller p_241696_1_) {
        p_241696_1_.n_1700_B("parse_json");
        JsonObject jsonobject = null;
        try (Resource iresource = p_241696_0_.n_1700_B(J_1907_R);
             BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(iresource.J_1907_R(), StandardCharsets.UTF_8));){
            jsonobject = new JsonParser().parse((Reader)bufferedreader).getAsJsonObject();
        }
        catch (JsonSyntaxException | IOException ioexception) {
            n_1700_B.warn("Failed to load GPU warnlist");
        }
        p_241696_1_.R_4764_Y();
        return jsonobject;
    }

    @Override
    protected /* synthetic */ void apply(Object object, ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        this.n_1700_B((n_1700_B)object, s_2107_a, x_2951_U);
    }

    @Override
    protected /* synthetic */ Object prepare(ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        return this.n_1700_B(s_2107_a, x_2951_U);
    }

    public static final class n_1700_B {
        private final List<Pattern> n_1700_B;
        private final List<Pattern> J_1907_R;
        private final List<Pattern> R_4764_Y;

        private n_1700_B(List<Pattern> p_i241261_1_, List<Pattern> p_i241261_2_, List<Pattern> p_i241261_3_) {
            this.n_1700_B = p_i241261_1_;
            this.J_1907_R = p_i241261_2_;
            this.R_4764_Y = p_i241261_3_;
        }

        private static String n_1700_B(List<Pattern> p_241711_0_, String p_241711_1_) {
            ArrayList list = Lists.newArrayList();
            for (Pattern pattern : p_241711_0_) {
                Matcher matcher = pattern.matcher(p_241711_1_);
                while (matcher.find()) {
                    list.add(matcher.group());
                }
            }
            return String.join((CharSequence)", ", list);
        }

        private ImmutableMap<String, String> n_1700_B() {
            String s2;
            String s1;
            ImmutableMap.Builder builder = new ImmutableMap.Builder();
            String s = lightning.product.K_2357_w$n_1700_B.n_1700_B(this.n_1700_B, Z_976_R.R_4764_Y());
            if (!s.isEmpty()) {
                builder.put((Object)"renderer", (Object)s);
            }
            if (!(s1 = lightning.product.K_2357_w$n_1700_B.n_1700_B(this.J_1907_R, Z_976_R.G_564_y())).isEmpty()) {
                builder.put((Object)"version", (Object)s1);
            }
            if (!(s2 = lightning.product.K_2357_w$n_1700_B.n_1700_B(this.R_4764_Y, Z_976_R.n_1700_B())).isEmpty()) {
                builder.put((Object)"vendor", (Object)s2);
            }
            return builder.build();
        }
    }
}


