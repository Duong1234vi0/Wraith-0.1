/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.util.UUIDTypeAdapter
 *  javax.annotation.Nullable
 *  lombok.Generated
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import com.mojang.util.UUIDTypeAdapter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lombok.Generated;

public class u_3100_Q {
    private String n_1700_B;
    private final String J_1907_R;
    private final String R_4764_Y;
    private final n_1700_B G_564_y;

    public u_3100_Q(String usernameIn, String playerIDIn, String tokenIn, String sessionTypeIn) {
        this.n_1700_B = usernameIn;
        this.J_1907_R = playerIDIn;
        this.R_4764_Y = tokenIn;
        this.G_564_y = lightning.product.u_3100_Q$n_1700_B.n_1700_B(sessionTypeIn);
    }

    public String n_1700_B() {
        return "token:" + this.R_4764_Y + ":" + this.J_1907_R;
    }

    public String J_1907_R() {
        return this.J_1907_R;
    }

    public String R_4764_Y() {
        return this.n_1700_B;
    }

    public String G_564_y() {
        return this.R_4764_Y;
    }

    public GameProfile P_1922_E() {
        try {
            UUID uuid = UUIDTypeAdapter.fromString((String)this.J_1907_R());
            return new GameProfile(uuid, this.R_4764_Y());
        }
        catch (IllegalArgumentException illegalargumentexception) {
            return new GameProfile((UUID)null, this.R_4764_Y());
        }
    }

    @Generated
    public void n_1700_B(String username) {
        this.n_1700_B = username;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("legacy");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("mojang");
        private static final Map<String, n_1700_B> R_4764_Y;
        private final String G_564_y;
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String sessionTypeIn) {
            this.G_564_y = sessionTypeIn;
        }

        @Nullable
        public static n_1700_B n_1700_B(String sessionTypeIn) {
            return R_4764_Y.get(sessionTypeIn.toLowerCase(Locale.ROOT));
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            P_1922_E = lightning.product.u_3100_Q$n_1700_B.n_1700_B();
            R_4764_Y = Arrays.stream(lightning.product.u_3100_Q$n_1700_B.values()).collect(Collectors.toMap(type -> type.G_564_y, Function.identity()));
        }
    }
}

