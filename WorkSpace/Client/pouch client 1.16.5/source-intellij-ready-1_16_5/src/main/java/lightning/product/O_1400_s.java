/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.StringEscapeUtils
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringEscapeUtils;

public class O_1400_s {
    private final Writer n_1700_B;
    private final int J_1907_R;

    private O_1400_s(Writer p_i51695_1_, List<String> p_i51695_2_) throws IOException {
        this.n_1700_B = p_i51695_1_;
        this.J_1907_R = p_i51695_2_.size();
        this.n_1700_B(p_i51695_2_.stream());
    }

    public static n_1700_B n_1700_B() {
        return new n_1700_B();
    }

    public void n_1700_B(Object ... p_225426_1_) throws IOException {
        if (p_225426_1_.length != this.J_1907_R) {
            throw new IllegalArgumentException("Invalid number of columns, expected " + this.J_1907_R + ", but got " + p_225426_1_.length);
        }
        this.n_1700_B(Stream.of(p_225426_1_));
    }

    private void n_1700_B(Stream<?> p_225427_1_) throws IOException {
        this.n_1700_B.write(p_225427_1_.map(O_1400_s::n_1700_B).collect(Collectors.joining(",")) + "\r\n");
    }

    private static String n_1700_B(@Nullable Object p_225425_0_) {
        return StringEscapeUtils.escapeCsv((String)(p_225425_0_ != null ? p_225425_0_.toString() : "[null]"));
    }

    public static class n_1700_B {
        private final List<String> n_1700_B = Lists.newArrayList();

        public n_1700_B n_1700_B(String p_225423_1_) {
            this.n_1700_B.add(p_225423_1_);
            return this;
        }

        public O_1400_s n_1700_B(Writer p_225422_1_) throws IOException {
            return new O_1400_s(p_225422_1_, this.n_1700_B);
        }
    }
}

