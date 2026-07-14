/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

public class U_45_E
extends IOException {
    private final List<n_1700_B> n_1700_B = Lists.newArrayList();
    private final String J_1907_R;

    public U_45_E(String messageIn) {
        this.n_1700_B.add(new n_1700_B());
        this.J_1907_R = messageIn;
    }

    public U_45_E(String messageIn, Throwable cause) {
        super(cause);
        this.n_1700_B.add(new n_1700_B());
        this.J_1907_R = messageIn;
    }

    public void n_1700_B(String key) {
        this.n_1700_B.get(0).n_1700_B(key);
    }

    public void J_1907_R(String filenameIn) {
        this.n_1700_B.get((int)0).n_1700_B = filenameIn;
        this.n_1700_B.add(0, new n_1700_B());
    }

    @Override
    public String getMessage() {
        return "Invalid " + String.valueOf(this.n_1700_B.get(this.n_1700_B.size() - 1)) + ": " + this.J_1907_R;
    }

    public static U_45_E n_1700_B(Exception exception) {
        if (exception instanceof U_45_E) {
            return (U_45_E)exception;
        }
        String s = exception.getMessage();
        if (exception instanceof FileNotFoundException) {
            s = "File not found";
        }
        return new U_45_E(s, exception);
    }

    public static class n_1700_B {
        @Nullable
        private String n_1700_B;
        private final List<String> J_1907_R = Lists.newArrayList();

        private n_1700_B() {
        }

        private void n_1700_B(String key) {
            this.J_1907_R.add(0, key);
        }

        public String n_1700_B() {
            return StringUtils.join(this.J_1907_R, (String)"->");
        }

        public String toString() {
            if (this.n_1700_B != null) {
                return this.J_1907_R.isEmpty() ? this.n_1700_B : this.n_1700_B + " " + this.n_1700_B();
            }
            return this.J_1907_R.isEmpty() ? "(Unknown file)" : "(Unknown file) " + this.n_1700_B();
        }
    }
}

