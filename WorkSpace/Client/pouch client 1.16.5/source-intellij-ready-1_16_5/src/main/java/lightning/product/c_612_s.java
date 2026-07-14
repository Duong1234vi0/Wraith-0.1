/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import javax.annotation.Nullable;
import lightning.product.r_4097_j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class c_612_s<T extends c_612_s<T>> {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Properties J_1907_R;

    public c_612_s(Properties propertiesIn) {
        this.J_1907_R = propertiesIn;
    }

    public static Properties n_1700_B(Path pathIn) {
        Properties properties = new Properties();
        try (InputStream inputstream = Files.newInputStream(pathIn, new OpenOption[0]);){
            properties.load(inputstream);
        }
        catch (IOException ioexception) {
            n_1700_B.error("Failed to load properties from file: " + String.valueOf(pathIn));
        }
        return properties;
    }

    public void J_1907_R(Path pathIn) {
        try (OutputStream outputstream = Files.newOutputStream(pathIn, new OpenOption[0]);){
            this.J_1907_R.store(outputstream, "Minecraft server properties");
        }
        catch (IOException ioexception) {
            n_1700_B.error("Failed to store properties to file: " + String.valueOf(pathIn));
        }
    }

    private static <V extends Number> Function<String, V> n_1700_B(Function<String, V> parseFunc) {
        return p_218975_1_ -> {
            try {
                return (Number)parseFunc.apply((String)p_218975_1_);
            }
            catch (NumberFormatException numberformatexception) {
                return null;
            }
        };
    }

    protected static <V> Function<String, V> n_1700_B(IntFunction<V> byId, Function<String, V> byName) {
        return p_218971_2_ -> {
            try {
                return byId.apply(Integer.parseInt(p_218971_2_));
            }
            catch (NumberFormatException numberformatexception) {
                return byName.apply((String)p_218971_2_);
            }
        };
    }

    @Nullable
    private String R_4764_Y(String key) {
        return (String)this.J_1907_R.get(key);
    }

    @Nullable
    protected <V> V n_1700_B(String key, Function<String, V> p_218984_2_) {
        String s = this.R_4764_Y(key);
        if (s == null) {
            return null;
        }
        this.J_1907_R.remove(key);
        return p_218984_2_.apply(s);
    }

    protected <V> V n_1700_B(String key, Function<String, V> p_218983_2_, Function<V, String> p_218983_3_, V p_218983_4_) {
        String s = this.R_4764_Y(key);
        Object v = MoreObjects.firstNonNull(s != null ? p_218983_2_.apply(s) : null, p_218983_4_);
        this.J_1907_R.put(key, p_218983_3_.apply(v));
        return (V)v;
    }

    protected <V> n_1700_B<V> J_1907_R(String key, Function<String, V> p_218981_2_, Function<V, String> p_218981_3_, V p_218981_4_) {
        String s = this.R_4764_Y(key);
        Object v = MoreObjects.firstNonNull(s != null ? p_218981_2_.apply(s) : null, p_218981_4_);
        this.J_1907_R.put(key, p_218981_3_.apply(v));
        return new n_1700_B<Object>(key, v, p_218981_3_);
    }

    protected <V> V n_1700_B(String key, Function<String, V> p_218977_2_, UnaryOperator<V> p_218977_3_, Function<V, String> p_218977_4_, V p_218977_5_) {
        return (V)this.n_1700_B(key, p_218972_2_ -> {
            Object v = p_218977_2_.apply((String)p_218972_2_);
            return v != null ? p_218977_3_.apply(v) : null;
        }, p_218977_4_, p_218977_5_);
    }

    protected <V> V n_1700_B(String key, Function<String, V> p_218979_2_, V p_218979_3_) {
        return (V)this.n_1700_B(key, p_218979_2_, Objects::toString, p_218979_3_);
    }

    protected <V> n_1700_B<V> J_1907_R(String key, Function<String, V> p_218965_2_, V p_218965_3_) {
        return this.J_1907_R(key, p_218965_2_, Objects::toString, p_218965_3_);
    }

    protected String n_1700_B(String key, String p_218973_2_) {
        return this.n_1700_B(key, Function.identity(), Function.identity(), p_218973_2_);
    }

    @Nullable
    protected String n_1700_B(String key) {
        return (String)this.n_1700_B(key, Function.identity());
    }

    protected int n_1700_B(String key, int p_218968_2_) {
        return this.n_1700_B(key, c_612_s.n_1700_B(Integer::parseInt), Integer.valueOf(p_218968_2_));
    }

    protected n_1700_B<Integer> J_1907_R(String key, int p_218974_2_) {
        return this.J_1907_R(key, c_612_s.n_1700_B(Integer::parseInt), p_218974_2_);
    }

    protected int n_1700_B(String key, UnaryOperator<Integer> p_218962_2_, int p_218962_3_) {
        return this.n_1700_B(key, c_612_s.n_1700_B(Integer::parseInt), p_218962_2_, Objects::toString, p_218962_3_);
    }

    protected long n_1700_B(String key, long p_218967_2_) {
        return this.n_1700_B(key, c_612_s.n_1700_B(Long::parseLong), p_218967_2_);
    }

    protected boolean n_1700_B(String key, boolean p_218982_2_) {
        return this.n_1700_B(key, Boolean::valueOf, p_218982_2_);
    }

    protected n_1700_B<Boolean> J_1907_R(String key, boolean p_218961_2_) {
        return this.J_1907_R(key, Boolean::valueOf, p_218961_2_);
    }

    @Nullable
    protected Boolean J_1907_R(String key) {
        return this.n_1700_B(key, Boolean::valueOf);
    }

    protected Properties n_1700_B() {
        Properties properties = new Properties();
        properties.putAll((Map<?, ?>)this.J_1907_R);
        return properties;
    }

    protected abstract T n_1700_B(r_4097_j var1, Properties var2);

    public class n_1700_B<V>
    implements Supplier<V> {
        private final String J_1907_R;
        private final V R_4764_Y;
        private final Function<V, String> G_564_y;

        private n_1700_B(String p_i50880_2_, V p_i50880_3_, Function<V, String> p_i50880_4_) {
            this.J_1907_R = p_i50880_2_;
            this.R_4764_Y = p_i50880_3_;
            this.G_564_y = p_i50880_4_;
        }

        @Override
        public V get() {
            return this.R_4764_Y;
        }

        public T n_1700_B(r_4097_j p_244381_1_, V p_244381_2_) {
            Properties properties = c_612_s.this.n_1700_B();
            properties.put(this.J_1907_R, this.G_564_y.apply(p_244381_2_));
            return c_612_s.this.n_1700_B(p_244381_1_, properties);
        }
    }
}

