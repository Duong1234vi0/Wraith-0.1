/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TextRenderingUtils {
    @VisibleForTesting
    protected static List<String> n_1700_B(String p_225223_0_) {
        return Arrays.asList(p_225223_0_.split("\\n"));
    }

    public static List<n_1700_B> n_1700_B(String p_225224_0_, J_1907_R ... p_225224_1_) {
        return TextRenderingUtils.n_1700_B(p_225224_0_, Arrays.asList(p_225224_1_));
    }

    private static List<n_1700_B> n_1700_B(String p_225225_0_, List<J_1907_R> p_225225_1_) {
        List<String> list = TextRenderingUtils.n_1700_B(p_225225_0_);
        return TextRenderingUtils.n_1700_B(list, p_225225_1_);
    }

    private static List<n_1700_B> n_1700_B(List<String> p_225222_0_, List<J_1907_R> p_225222_1_) {
        int i = 0;
        ArrayList list = Lists.newArrayList();
        for (String s : p_225222_0_) {
            ArrayList list1 = Lists.newArrayList();
            for (String s1 : TextRenderingUtils.n_1700_B(s, "%link")) {
                if ("%link".equals(s1)) {
                    list1.add(p_225222_1_.get(i++));
                    continue;
                }
                list1.add(J_1907_R.n_1700_B(s1));
            }
            list.add(new n_1700_B(list1));
        }
        return list;
    }

    public static List<String> n_1700_B(String p_225226_0_, String p_225226_1_) {
        int j;
        if (p_225226_1_.isEmpty()) {
            throw new IllegalArgumentException("Delimiter cannot be the empty string");
        }
        ArrayList list = Lists.newArrayList();
        int i = 0;
        while ((j = p_225226_0_.indexOf(p_225226_1_, i)) != -1) {
            if (j > i) {
                list.add(p_225226_0_.substring(i, j));
            }
            list.add(p_225226_1_);
            i = j + p_225226_1_.length();
        }
        if (i < p_225226_0_.length()) {
            list.add(p_225226_0_.substring(i));
        }
        return list;
    }

    public static class J_1907_R {
        private final String n_1700_B;
        private final String J_1907_R;
        private final String R_4764_Y;

        private J_1907_R(String p_i51642_1_) {
            this.n_1700_B = p_i51642_1_;
            this.J_1907_R = null;
            this.R_4764_Y = null;
        }

        private J_1907_R(String p_i51643_1_, String p_i51643_2_, String p_i51643_3_) {
            this.n_1700_B = p_i51643_1_;
            this.J_1907_R = p_i51643_2_;
            this.R_4764_Y = p_i51643_3_;
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                J_1907_R textrenderingutils$linesegment = (J_1907_R)p_equals_1_;
                return Objects.equals(this.n_1700_B, textrenderingutils$linesegment.n_1700_B) && Objects.equals(this.J_1907_R, textrenderingutils$linesegment.J_1907_R) && Objects.equals(this.R_4764_Y, textrenderingutils$linesegment.R_4764_Y);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(this.n_1700_B, this.J_1907_R, this.R_4764_Y);
        }

        public String toString() {
            return "Segment{fullText='" + this.n_1700_B + "', linkTitle='" + this.J_1907_R + "', linkUrl='" + this.R_4764_Y + "'}";
        }

        public String n_1700_B() {
            return this.J_1907_R() ? this.J_1907_R : this.n_1700_B;
        }

        public boolean J_1907_R() {
            return this.J_1907_R != null;
        }

        public String R_4764_Y() {
            if (!this.J_1907_R()) {
                throw new IllegalStateException("Not a link: " + String.valueOf(this));
            }
            return this.R_4764_Y;
        }

        public static J_1907_R n_1700_B(String p_225214_0_, String p_225214_1_) {
            return new J_1907_R(null, p_225214_0_, p_225214_1_);
        }

        @VisibleForTesting
        protected static J_1907_R n_1700_B(String p_225218_0_) {
            return new J_1907_R(p_225218_0_);
        }
    }

    public static class n_1700_B {
        public final List<J_1907_R> n_1700_B;

        n_1700_B(List<J_1907_R> p_i51644_1_) {
            this.n_1700_B = p_i51644_1_;
        }

        public String toString() {
            return "Line{segments=" + String.valueOf(this.n_1700_B) + "}";
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                n_1700_B textrenderingutils$line = (n_1700_B)p_equals_1_;
                return Objects.equals(this.n_1700_B, textrenderingutils$line.n_1700_B);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(this.n_1700_B);
        }
    }
}


