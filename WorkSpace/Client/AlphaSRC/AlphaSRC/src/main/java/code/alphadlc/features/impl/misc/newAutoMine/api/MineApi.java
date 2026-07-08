/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 */
package code.alphadlc.features.impl.misc.newAutoMine.api;

import com.google.gson.Gson;
import java.net.http.HttpClient;
import java.time.Duration;

public interface MineApi {
    public static final String URL = "http://85.208.139.128:8000/api/mines";
    public static final Gson GSON = new Gson();
    public static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(2L)).build();
}
