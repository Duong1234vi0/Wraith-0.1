/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.io.Files
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.mojang.authlib.Agent
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.GameProfileRepository
 *  com.mojang.authlib.ProfileLookupCallback
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.a_3913_L;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class W_1689_V {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static boolean J_1907_R;
    private final Map<String, n_1700_B> R_4764_Y = Maps.newConcurrentMap();
    private final Map<UUID, n_1700_B> G_564_y = Maps.newConcurrentMap();
    private final GameProfileRepository P_1922_E;
    private final Gson u_1723_Y = new GsonBuilder().create();
    private final File v_4262_N;
    private final AtomicLong w_1484_f = new AtomicLong();

    public W_1689_V(GameProfileRepository profileRepoIn, File usercacheFileIn) {
        this.P_1922_E = profileRepoIn;
        this.v_4262_N = usercacheFileIn;
        Lists.reverse(this.n_1700_B()).forEach(this::n_1700_B);
    }

    private void n_1700_B(n_1700_B p_242118_1_) {
        UUID uuid;
        GameProfile gameprofile = p_242118_1_.n_1700_B();
        p_242118_1_.n_1700_B(this.G_564_y());
        String s = gameprofile.getName();
        if (s != null) {
            this.R_4764_Y.put(s.toLowerCase(Locale.ROOT), p_242118_1_);
        }
        if ((uuid = gameprofile.getId()) != null) {
            this.G_564_y.put(uuid, p_242118_1_);
        }
    }

    @Nullable
    private static GameProfile n_1700_B(GameProfileRepository profileRepoIn, String name) {
        final AtomicReference atomicreference = new AtomicReference();
        ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback(){

            public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
                atomicreference.set(p_onProfileLookupSucceeded_1_);
            }

            public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
                atomicreference.set(null);
            }
        };
        profileRepoIn.findProfilesByNames(new String[]{name}, Agent.MINECRAFT, profilelookupcallback);
        GameProfile gameprofile = (GameProfile)atomicreference.get();
        if (!W_1689_V.R_4764_Y() && gameprofile == null) {
            UUID uuid = a_3913_L.n_1700_B(new GameProfile((UUID)null, name));
            gameprofile = new GameProfile(uuid, name);
        }
        return gameprofile;
    }

    public static void n_1700_B(boolean onlineModeIn) {
        J_1907_R = onlineModeIn;
    }

    private static boolean R_4764_Y() {
        return J_1907_R;
    }

    public void n_1700_B(GameProfile gameProfile) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(2, 1);
        Date date = calendar.getTime();
        n_1700_B playerprofilecache$profileentry = new n_1700_B(gameProfile, date);
        this.n_1700_B(playerprofilecache$profileentry);
        this.J_1907_R();
    }

    private long G_564_y() {
        return this.w_1484_f.incrementAndGet();
    }

    @Nullable
    public GameProfile n_1700_B(String username) {
        GameProfile gameprofile;
        String s = username.toLowerCase(Locale.ROOT);
        n_1700_B playerprofilecache$profileentry = this.R_4764_Y.get(s);
        boolean flag = false;
        if (playerprofilecache$profileentry != null && new Date().getTime() >= playerprofilecache$profileentry.J_1907_R.getTime()) {
            this.G_564_y.remove(playerprofilecache$profileentry.n_1700_B().getId());
            this.R_4764_Y.remove(playerprofilecache$profileentry.n_1700_B().getName().toLowerCase(Locale.ROOT));
            flag = true;
            playerprofilecache$profileentry = null;
        }
        if (playerprofilecache$profileentry != null) {
            playerprofilecache$profileentry.n_1700_B(this.G_564_y());
            gameprofile = playerprofilecache$profileentry.n_1700_B();
        } else {
            gameprofile = W_1689_V.n_1700_B(this.P_1922_E, s);
            if (gameprofile != null) {
                this.n_1700_B(gameprofile);
                flag = false;
            }
        }
        if (flag) {
            this.J_1907_R();
        }
        return gameprofile;
    }

    @Nullable
    public GameProfile n_1700_B(UUID uuid) {
        n_1700_B playerprofilecache$profileentry = this.G_564_y.get(uuid);
        if (playerprofilecache$profileentry == null) {
            return null;
        }
        playerprofilecache$profileentry.n_1700_B(this.G_564_y());
        return playerprofilecache$profileentry.n_1700_B();
    }

    private static DateFormat P_1922_E() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public List<n_1700_B> n_1700_B() {
        ArrayList list = Lists.newArrayList();
        try (BufferedReader reader2222 = Files.newReader((File)this.v_4262_N, (Charset)StandardCharsets.UTF_8);){
            JsonArray jsonarray = (JsonArray)this.u_1723_Y.fromJson((Reader)reader2222, JsonArray.class);
            if (jsonarray == null) {
                ArrayList arrayList = list;
                return arrayList;
            }
            DateFormat dateformat = W_1689_V.P_1922_E();
            jsonarray.forEach(p_242122_2_ -> {
                n_1700_B playerprofilecache$profileentry = W_1689_V.n_1700_B(p_242122_2_, dateformat);
                if (playerprofilecache$profileentry != null) {
                    list.add(playerprofilecache$profileentry);
                }
            });
            return list;
        }
        catch (FileNotFoundException reader2222) {
            return list;
        }
        catch (JsonParseException | IOException ioexception) {
            n_1700_B.warn("Failed to load profile cache {}", (Object)this.v_4262_N, (Object)ioexception);
        }
        return list;
    }

    public void J_1907_R() {
        JsonArray jsonarray = new JsonArray();
        DateFormat dateformat = W_1689_V.P_1922_E();
        this.n_1700_B(1000).forEach(p_242120_2_ -> jsonarray.add(W_1689_V.n_1700_B(p_242120_2_, dateformat)));
        String s = this.u_1723_Y.toJson((JsonElement)jsonarray);
        try (BufferedWriter writer = Files.newWriter((File)this.v_4262_N, (Charset)StandardCharsets.UTF_8);){
            writer.write(s);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private Stream<n_1700_B> n_1700_B(int p_242117_1_) {
        return ImmutableList.copyOf(this.G_564_y.values()).stream().sorted(Comparator.comparing(n_1700_B::R_4764_Y).reversed()).limit(p_242117_1_);
    }

    private static JsonElement n_1700_B(n_1700_B p_242119_0_, DateFormat p_242119_1_) {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("name", p_242119_0_.n_1700_B().getName());
        UUID uuid = p_242119_0_.n_1700_B().getId();
        jsonobject.addProperty("uuid", uuid == null ? "" : uuid.toString());
        jsonobject.addProperty("expiresOn", p_242119_1_.format(p_242119_0_.J_1907_R()));
        return jsonobject;
    }

    @Nullable
    private static n_1700_B n_1700_B(JsonElement p_242121_0_, DateFormat p_242121_1_) {
        if (p_242121_0_.isJsonObject()) {
            JsonObject jsonobject = p_242121_0_.getAsJsonObject();
            JsonElement jsonelement = jsonobject.get("name");
            JsonElement jsonelement1 = jsonobject.get("uuid");
            JsonElement jsonelement2 = jsonobject.get("expiresOn");
            if (jsonelement != null && jsonelement1 != null) {
                String s = jsonelement1.getAsString();
                String s1 = jsonelement.getAsString();
                Date date = null;
                if (jsonelement2 != null) {
                    try {
                        date = p_242121_1_.parse(jsonelement2.getAsString());
                    }
                    catch (ParseException parseException) {
                        // empty catch block
                    }
                }
                if (s1 != null && s != null && date != null) {
                    UUID uuid;
                    try {
                        uuid = UUID.fromString(s);
                    }
                    catch (Throwable throwable) {
                        return null;
                    }
                    return new n_1700_B(new GameProfile(uuid, s1), date);
                }
                return null;
            }
            return null;
        }
        return null;
    }

    static class n_1700_B {
        private final GameProfile n_1700_B;
        private final Date J_1907_R;
        private volatile long R_4764_Y;

        private n_1700_B(GameProfile p_i241888_1_, Date p_i241888_2_) {
            this.n_1700_B = p_i241888_1_;
            this.J_1907_R = p_i241888_2_;
        }

        public GameProfile n_1700_B() {
            return this.n_1700_B;
        }

        public Date J_1907_R() {
            return this.J_1907_R;
        }

        public void n_1700_B(long p_242126_1_) {
            this.R_4764_Y = p_242126_1_;
        }

        public long R_4764_Y() {
            return this.R_4764_Y;
        }
    }
}

