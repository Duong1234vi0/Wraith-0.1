/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import lightning.product.h_1915_h;
import lightning.product.n_3236_c;

public class CrashReportCategory {
    private final n_3236_c n_1700_B;
    private final String J_1907_R;
    private final List<n_1700_B> R_4764_Y = Lists.newArrayList();
    private StackTraceElement[] G_564_y = new StackTraceElement[0];

    public CrashReportCategory(n_3236_c report, String name) {
        this.n_1700_B = report;
        this.J_1907_R = name;
    }

    public static String n_1700_B(double x, double y, double z) {
        return String.format(Locale.ROOT, "%.2f,%.2f,%.2f - %s", x, y, z, CrashReportCategory.n_1700_B(new c_1514_x(x, y, z)));
    }

    public static String n_1700_B(c_1514_x pos) {
        return CrashReportCategory.n_1700_B(pos.getX(), pos.getY(), pos.getZ());
    }

    public static String n_1700_B(int x, int y, int z) {
        StringBuilder stringbuilder = new StringBuilder();
        try {
            stringbuilder.append(String.format("World: (%d,%d,%d)", x, y, z));
        }
        catch (Throwable throwable2) {
            stringbuilder.append("(Error finding world loc)");
        }
        stringbuilder.append(", ");
        try {
            int i = x >> 4;
            int j = z >> 4;
            int k = x & 0xF;
            int l = y >> 4;
            int i1 = z & 0xF;
            int j1 = i << 4;
            int k1 = j << 4;
            int l1 = (i + 1 << 4) - 1;
            int i2 = (j + 1 << 4) - 1;
            stringbuilder.append(String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", k, l, i1, i, j, j1, k1, l1, i2));
        }
        catch (Throwable throwable1) {
            stringbuilder.append("(Error finding chunk loc)");
        }
        stringbuilder.append(", ");
        try {
            int k2 = x >> 9;
            int l2 = z >> 9;
            int i3 = k2 << 5;
            int j3 = l2 << 5;
            int k3 = (k2 + 1 << 5) - 1;
            int l3 = (l2 + 1 << 5) - 1;
            int i4 = k2 << 9;
            int j4 = l2 << 9;
            int k4 = (k2 + 1 << 9) - 1;
            int j2 = (l2 + 1 << 9) - 1;
            stringbuilder.append(String.format("Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)", k2, l2, i3, j3, k3, l3, i4, j4, k4, j2));
        }
        catch (Throwable throwable) {
            stringbuilder.append("(Error finding world loc)");
        }
        return stringbuilder.toString();
    }

    public CrashReportCategory n_1700_B(String nameIn, h_1915_h<String> detail) {
        try {
            this.n_1700_B(nameIn, detail.call());
        }
        catch (Throwable throwable) {
            this.n_1700_B(nameIn, throwable);
        }
        return this;
    }

    public CrashReportCategory n_1700_B(String sectionName, Object value) {
        this.R_4764_Y.add(new n_1700_B(sectionName, value));
        return this;
    }

    public void n_1700_B(String sectionName, Throwable throwable) {
        this.n_1700_B(sectionName, (Object)throwable);
    }

    public int n_1700_B(int size) {
        StackTraceElement[] astacktraceelement = Thread.currentThread().getStackTrace();
        if (astacktraceelement.length <= 0) {
            return 0;
        }
        this.G_564_y = new StackTraceElement[astacktraceelement.length - 3 - size];
        System.arraycopy(astacktraceelement, 3 + size, this.G_564_y, 0, this.G_564_y.length);
        return this.G_564_y.length;
    }

    public boolean n_1700_B(StackTraceElement s1, StackTraceElement s2) {
        if (this.G_564_y.length != 0 && s1 != null) {
            StackTraceElement stacktraceelement = this.G_564_y[0];
            if (stacktraceelement.isNativeMethod() == s1.isNativeMethod() && stacktraceelement.getClassName().equals(s1.getClassName()) && stacktraceelement.getFileName().equals(s1.getFileName()) && stacktraceelement.getMethodName().equals(s1.getMethodName())) {
                if (s2 != null != this.G_564_y.length > 1) {
                    return false;
                }
                if (s2 != null && !this.G_564_y[1].equals(s2)) {
                    return false;
                }
                this.G_564_y[0] = s1;
                return true;
            }
            return false;
        }
        return false;
    }

    public void J_1907_R(int amount) {
        StackTraceElement[] astacktraceelement = new StackTraceElement[this.G_564_y.length - amount];
        System.arraycopy(this.G_564_y, 0, astacktraceelement, 0, astacktraceelement.length);
        this.G_564_y = astacktraceelement;
    }

    public void n_1700_B(StringBuilder builder) {
        builder.append("-- ").append(this.J_1907_R).append(" --\n");
        builder.append("Details:");
        for (n_1700_B crashreportcategory$entry : this.R_4764_Y) {
            builder.append("\n\t");
            builder.append(crashreportcategory$entry.n_1700_B());
            builder.append(": ");
            builder.append(crashreportcategory$entry.J_1907_R());
        }
        if (this.G_564_y != null && this.G_564_y.length > 0) {
            builder.append("\nStacktrace:");
            for (StackTraceElement stacktraceelement : this.G_564_y) {
                builder.append("\n\tat ");
                builder.append(stacktraceelement);
            }
        }
    }

    public StackTraceElement[] n_1700_B() {
        return this.G_564_y;
    }

    public static void n_1700_B(CrashReportCategory category, c_1514_x pos, @Nullable K_4074_S state) {
        if (state != null) {
            category.n_1700_B("Block", state::toString);
        }
        category.n_1700_B("Block location", () -> CrashReportCategory.n_1700_B(pos));
    }

    static class n_1700_B {
        private final String n_1700_B;
        private final String J_1907_R;

        public n_1700_B(String key, @Nullable Object value) {
            this.n_1700_B = key;
            if (value == null) {
                this.J_1907_R = "~~NULL~~";
            } else if (value instanceof Throwable) {
                Throwable throwable = (Throwable)value;
                this.J_1907_R = "~~ERROR~~ " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
            } else {
                this.J_1907_R = value.toString();
            }
        }

        public String n_1700_B() {
            return this.n_1700_B;
        }

        public String J_1907_R() {
            return this.J_1907_R;
        }
    }
}


