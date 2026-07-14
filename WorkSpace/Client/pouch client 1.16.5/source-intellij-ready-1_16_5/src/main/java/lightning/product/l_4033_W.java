/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;
import lightning.product.StringDecomposer;
import lightning.product.FormattedText;
import lightning.product.Z_1567_W;
import lightning.product.FormattedCharSequence;
import lightning.product.i_4431_W;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class l_4033_W {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Gson J_1907_R = new Gson();
    private static final Pattern R_4764_Y = Pattern.compile("%(\\d+\\$)?[\\d.]*[df]");
    private static volatile l_4033_W G_564_y = l_4033_W.J_1907_R();

    private static l_4033_W J_1907_R() {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        BiConsumer<String, String> biconsumer = (arg_0, arg_1) -> ((ImmutableMap.Builder)builder).put(arg_0, arg_1);
        try (InputStream inputstream = l_4033_W.class.getResourceAsStream("/assets/minecraft/lang/en_us.json");){
            l_4033_W.n_1700_B(inputstream, biconsumer);
        }
        catch (JsonParseException | IOException ioexception) {
            n_1700_B.error("Couldn't read strings from /assets/minecraft/lang/en_us.json", ioexception);
        }
        ImmutableMap map = builder.build();
        return new l_4033_W((Map)map){
            final /* synthetic */ Map n_1700_B;
            {
                this.n_1700_B = map;
            }

            @Override
            public String n_1700_B(String p_230503_1_) {
                return this.n_1700_B.getOrDefault(p_230503_1_, p_230503_1_);
            }

            @Override
            public boolean J_1907_R(String p_230506_1_) {
                return this.n_1700_B.containsKey(p_230506_1_);
            }

            @Override
            public boolean n_1700_B() {
                return false;
            }

            @Override
            public FormattedCharSequence n_1700_B(FormattedText p_241870_1_) {
                return p_244262_1_ -> p_241870_1_.n_1700_B((p_244261_1_, p_244261_2_) -> StringDecomposer.R_4764_Y(p_244261_2_, p_244261_1_, p_244262_1_) ? Optional.empty() : FormattedText.n_1700_B, Z_1567_W.n_1700_B).isPresent();
            }
        };
    }

    public static void n_1700_B(InputStream p_240593_0_, BiConsumer<String, String> p_240593_1_) {
        JsonObject jsonobject = (JsonObject)J_1907_R.fromJson((Reader)new InputStreamReader(p_240593_0_, StandardCharsets.UTF_8), JsonObject.class);
        for (Map.Entry entry : jsonobject.entrySet()) {
            String s = R_4764_Y.matcher(i_4431_W.n_1700_B((JsonElement)entry.getValue(), (String)entry.getKey())).replaceAll("%$1s");
            p_240593_1_.accept((String)entry.getKey(), s);
        }
    }

    public static l_4033_W R_4764_Y() {
        return G_564_y;
    }

    public static void n_1700_B(l_4033_W p_240594_0_) {
        G_564_y = p_240594_0_;
    }

    public abstract String n_1700_B(String var1);

    public abstract boolean J_1907_R(String var1);

    public abstract boolean n_1700_B();

    public abstract FormattedCharSequence n_1700_B(FormattedText var1);

    public List<FormattedCharSequence> n_1700_B(List<FormattedText> p_244260_1_) {
        return (List)p_244260_1_.stream().map(l_4033_W.R_4764_Y()::n_1700_B).collect(ImmutableList.toImmutableList());
    }
}


