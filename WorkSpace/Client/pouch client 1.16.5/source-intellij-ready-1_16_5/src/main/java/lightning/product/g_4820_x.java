/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.internal.Streams
 *  com.google.gson.stream.JsonReader
 *  com.google.gson.stream.JsonWriter
 *  com.mojang.authlib.GameProfile
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import lightning.product.SharedConstants;
import lightning.product.U_3758_m;
import lightning.product.TextFilter;
import lightning.product.i_4431_W;
import lightning.product.j_3341_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class g_4820_x
implements AutoCloseable {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final AtomicInteger J_1907_R = new AtomicInteger(1);
    private static final ThreadFactory R_4764_Y = p_244570_0_ -> {
        Thread thread = new Thread(p_244570_0_);
        thread.setName("Chat-Filter-Worker-" + J_1907_R.getAndIncrement());
        return thread;
    };
    private final URL G_564_y = null;
    private final URL P_1922_E = null;
    private final URL u_1723_Y = null;
    private final String v_4262_N;
    private final int w_1484_f = 0;
    private final String t_148_a;
    private final J_1907_R s_956_w = null;
    private final ExecutorService u_2550_I = null;

    private void n_1700_B(GameProfile p_244568_1_, URL p_244568_2_, Executor p_244568_3_) {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("server", this.t_148_a);
        jsonobject.addProperty("room", "Chat");
        jsonobject.addProperty("user_id", p_244568_1_.getId().toString());
        jsonobject.addProperty("user_display_name", p_244568_1_.getName());
        p_244568_3_.execute(() -> {
            try {
                this.J_1907_R(jsonobject, p_244568_2_);
            }
            catch (Exception exception) {
                n_1700_B.warn("Failed to send join/leave packet to {} for player {}", (Object)p_244568_2_, (Object)p_244568_1_, (Object)exception);
            }
        });
    }

    private CompletableFuture<Optional<String>> n_1700_B(GameProfile p_244567_1_, String p_244567_2_, J_1907_R p_244567_3_, Executor p_244567_4_) {
        if (p_244567_2_.isEmpty()) {
            return CompletableFuture.completedFuture(Optional.of(""));
        }
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("rule", (Number)this.w_1484_f);
        jsonobject.addProperty("server", this.t_148_a);
        jsonobject.addProperty("room", "Chat");
        jsonobject.addProperty("player", p_244567_1_.getId().toString());
        jsonobject.addProperty("player_display_name", p_244567_1_.getName());
        jsonobject.addProperty("text", p_244567_2_);
        return CompletableFuture.supplyAsync(() -> {
            try {
                JsonObject jsonobject1 = this.n_1700_B(jsonobject, this.G_564_y);
                boolean flag = i_4431_W.n_1700_B(jsonobject1, "response", false);
                if (flag) {
                    return Optional.of(p_244567_2_);
                }
                String s = i_4431_W.n_1700_B(jsonobject1, "hashed", (String)null);
                if (s == null) {
                    return Optional.empty();
                }
                int i = i_4431_W.P_4830_p(jsonobject1, "hashes").size();
                return p_244567_3_.shouldIgnore(s, i) ? Optional.empty() : Optional.of(s);
            }
            catch (Exception exception) {
                n_1700_B.warn("Failed to validate message '{}'", (Object)p_244567_2_, (Object)exception);
                return Optional.empty();
            }
        }, p_244567_4_);
    }

    @Override
    public void close() {
        this.u_2550_I.shutdownNow();
    }

    private void n_1700_B(InputStream p_244569_1_) throws IOException {
        byte[] abyte = new byte[1024];
        while (p_244569_1_.read(abyte) != -1) {
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private JsonObject n_1700_B(JsonObject p_244564_1_, URL p_244564_2_) throws IOException {
        JsonObject jsonobject;
        HttpURLConnection httpurlconnection = this.R_4764_Y(p_244564_1_, p_244564_2_);
        try (InputStream inputstream = httpurlconnection.getInputStream();){
            if (httpurlconnection.getResponseCode() != 204) {
                try {
                    JsonObject jsonObject = Streams.parse((JsonReader)new JsonReader((Reader)new InputStreamReader(inputstream))).getAsJsonObject();
                    return jsonObject;
                }
                finally {
                    this.n_1700_B(inputstream);
                }
            }
            jsonobject = new JsonObject();
        }
        return jsonobject;
    }

    private void J_1907_R(JsonObject p_244573_1_, URL p_244573_2_) throws IOException {
        HttpURLConnection httpurlconnection = this.R_4764_Y(p_244573_1_, p_244573_2_);
        try (InputStream inputstream = httpurlconnection.getInputStream();){
            this.n_1700_B(inputstream);
        }
    }

    private HttpURLConnection R_4764_Y(JsonObject p_244575_1_, URL p_244575_2_) throws IOException {
        HttpURLConnection httpurlconnection = (HttpURLConnection)p_244575_2_.openConnection();
        httpurlconnection.setConnectTimeout(15000);
        httpurlconnection.setReadTimeout(2000);
        httpurlconnection.setUseCaches(false);
        httpurlconnection.setDoOutput(true);
        httpurlconnection.setDoInput(true);
        httpurlconnection.setRequestMethod("POST");
        httpurlconnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpurlconnection.setRequestProperty("Accept", "application/json");
        httpurlconnection.setRequestProperty("Authorization", "Basic " + this.v_4262_N);
        httpurlconnection.setRequestProperty("User-Agent", "Minecraft server" + SharedConstants.n_1700_B().getName());
        try (OutputStreamWriter outputstreamwriter = new OutputStreamWriter(httpurlconnection.getOutputStream(), StandardCharsets.UTF_8);
             JsonWriter jsonwriter = new JsonWriter((Writer)outputstreamwriter);){
            Streams.write((JsonElement)p_244575_1_, (JsonWriter)jsonwriter);
        }
        int i = httpurlconnection.getResponseCode();
        if (i >= 200 && i < 300) {
            return httpurlconnection;
        }
        throw new n_1700_B(i + " " + httpurlconnection.getResponseMessage());
    }

    public TextFilter n_1700_B(GameProfile p_244566_1_) {
        return new R_4764_Y(p_244566_1_);
    }

    private g_4820_x() {
        this.v_4262_N = null;
        this.t_148_a = null;
        throw new RuntimeException("Synthetic constructor added by MCP, do not call");
    }

    @FunctionalInterface
    public static interface J_1907_R {
        public static final J_1907_R n_1700_B = (p_244583_0_, p_244583_1_) -> false;
        public static final J_1907_R J_1907_R = (p_244581_0_, p_244581_1_) -> p_244581_0_.length() == p_244581_1_;

        public boolean shouldIgnore(String var1, int var2);
    }

    public static class n_1700_B
    extends RuntimeException {
        private n_1700_B(String p_i242138_1_) {
            super(p_i242138_1_);
        }
    }

    class R_4764_Y
    implements TextFilter {
        private final GameProfile J_1907_R;
        private final Executor R_4764_Y;

        private R_4764_Y(GameProfile p_i242144_2_) {
            this.J_1907_R = p_i242144_2_;
            U_3758_m<Runnable> delegatedtaskexecutor = U_3758_m.n_1700_B(g_4820_x.this.u_2550_I, "chat stream for " + p_i242144_2_.getName());
            this.R_4764_Y = delegatedtaskexecutor::n_1700_B;
        }

        @Override
        public void n_1700_B() {
            g_4820_x.this.n_1700_B(this.J_1907_R, g_4820_x.this.P_1922_E, this.R_4764_Y);
        }

        @Override
        public void J_1907_R() {
            g_4820_x.this.n_1700_B(this.J_1907_R, g_4820_x.this.u_1723_Y, this.R_4764_Y);
        }

        @Override
        public CompletableFuture<Optional<List<String>>> n_1700_B(List<String> p_244433_1_) {
            List list = (List)p_244433_1_.stream().map(p_244589_1_ -> g_4820_x.this.n_1700_B(this.J_1907_R, (String)p_244589_1_, g_4820_x.this.s_956_w, this.R_4764_Y)).collect(ImmutableList.toImmutableList());
            return ((CompletableFuture)j_3341_s.J_1907_R(list).thenApply(p_244590_0_ -> Optional.of((List)p_244590_0_.stream().map(p_244588_0_ -> p_244588_0_.orElse("")).collect(ImmutableList.toImmutableList())))).exceptionally(p_244587_0_ -> Optional.empty());
        }

        @Override
        public CompletableFuture<Optional<String>> n_1700_B(String p_244432_1_) {
            return g_4820_x.this.n_1700_B(this.J_1907_R, p_244432_1_, g_4820_x.this.s_956_w, this.R_4764_Y);
        }
    }
}


