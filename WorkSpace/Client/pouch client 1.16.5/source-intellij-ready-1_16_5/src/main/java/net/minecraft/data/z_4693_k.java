/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.r_109_r;
import lightning.product.SetTag;
import net.minecraft.data.M_182_A;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.Y_259_p;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class z_4693_k<T>
implements Y_259_p {
    private static final Logger G_564_y = LogManager.getLogger();
    private static final Gson P_1922_E = new GsonBuilder().setPrettyPrinting().create();
    protected final Q_4569_t J_1907_R;
    protected final V_3137_a<T> R_4764_Y;
    private final Map<g_2336_b, r_109_r.n_1700_B> u_1723_Y = Maps.newLinkedHashMap();

    protected z_4693_k(Q_4569_t generatorIn, V_3137_a<T> registryIn) {
        this.J_1907_R = generatorIn;
        this.R_4764_Y = registryIn;
    }

    protected abstract void J_1907_R();

    @Override
    public void n_1700_B(M_182_A cache) {
        this.u_1723_Y.clear();
        this.J_1907_R();
        SetTag itag = SetTag.J_1907_R();
        Function<g_2336_b, r_109_r> function = key -> this.u_1723_Y.containsKey(key) ? itag : null;
        Function<g_2336_b, Object> function1 = key -> this.R_4764_Y.J_1907_R((g_2336_b)key).orElse(null);
        this.u_1723_Y.forEach((tagName, builder) -> {
            List list = builder.J_1907_R(function, function1).collect(Collectors.toList());
            if (!list.isEmpty()) {
                throw new IllegalArgumentException(String.format("Couldn't define tag %s as it is missing following references: %s", tagName, list.stream().map(Objects::toString).collect(Collectors.joining(","))));
            }
            JsonObject jsonobject = builder.R_4764_Y();
            Path path = this.n_1700_B((g_2336_b)tagName);
            try {
                String s = P_1922_E.toJson((JsonElement)jsonobject);
                String s1 = n_1700_B.hashUnencodedChars((CharSequence)s).toString();
                if (!Objects.equals(cache.n_1700_B(path), s1) || !Files.exists(path, new LinkOption[0])) {
                    Files.createDirectories(path.getParent(), new FileAttribute[0]);
                    try (BufferedWriter bufferedwriter = Files.newBufferedWriter(path, new OpenOption[0]);){
                        bufferedwriter.write(s);
                    }
                }
                cache.n_1700_B(path, s1);
            }
            catch (IOException ioexception) {
                G_564_y.error("Couldn't save tags to {}", (Object)path, (Object)ioexception);
            }
        });
    }

    protected abstract Path n_1700_B(g_2336_b var1);

    protected n_1700_B<T> n_1700_B(r_109_r.J_1907_R<T> tag) {
        r_109_r.n_1700_B itag$builder = this.J_1907_R(tag);
        return new n_1700_B<T>(itag$builder, this.R_4764_Y, "vanilla");
    }

    protected r_109_r.n_1700_B J_1907_R(r_109_r.J_1907_R<T> tag) {
        return this.u_1723_Y.computeIfAbsent(tag.J_1907_R(), key -> new r_109_r.n_1700_B());
    }

    public static class n_1700_B<T> {
        private final r_109_r.n_1700_B n_1700_B;
        private final V_3137_a<T> J_1907_R;
        private final String R_4764_Y;

        private n_1700_B(r_109_r.n_1700_B builder, V_3137_a<T> registry, String id) {
            this.n_1700_B = builder;
            this.J_1907_R = registry;
            this.R_4764_Y = id;
        }

        public n_1700_B<T> n_1700_B(T item) {
            this.n_1700_B.n_1700_B(this.J_1907_R.J_1907_R(item), this.R_4764_Y);
            return this;
        }

        public n_1700_B<T> n_1700_B(r_109_r.J_1907_R<T> tag) {
            this.n_1700_B.J_1907_R(tag.J_1907_R(), this.R_4764_Y);
            return this;
        }

        @SafeVarargs
        public final n_1700_B<T> n_1700_B(T ... toAdd) {
            Stream.of(toAdd).map(this.J_1907_R::J_1907_R).forEach(key -> this.n_1700_B.n_1700_B((g_2336_b)key, this.R_4764_Y));
            return this;
        }
    }
}


