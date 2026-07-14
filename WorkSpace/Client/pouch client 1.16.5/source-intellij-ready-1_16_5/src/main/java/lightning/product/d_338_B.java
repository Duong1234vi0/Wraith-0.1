/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.collect.Lists
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.properties.Property
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.I_14_v;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.x_282_a;

public class d_338_B
implements Packet<ClientGamePacketListener> {
    private n_1700_B n_1700_B;
    private final List<J_1907_R> J_1907_R = Lists.newArrayList();

    public d_338_B() {
    }

    public d_338_B(n_1700_B actionIn, B_4088_l ... playersIn) {
        this.n_1700_B = actionIn;
        for (B_4088_l serverplayerentity : playersIn) {
            this.J_1907_R.add(new J_1907_R(serverplayerentity.y_4642_Y(), serverplayerentity.P_1922_E, serverplayerentity.R_4764_Y.J_1907_R(), serverplayerentity.z_4693_k()));
        }
    }

    public d_338_B(n_1700_B actionIn, Iterable<B_4088_l> playersIn) {
        this.n_1700_B = actionIn;
        for (B_4088_l serverplayerentity : playersIn) {
            this.J_1907_R.add(new J_1907_R(serverplayerentity.y_4642_Y(), serverplayerentity.P_1922_E, serverplayerentity.R_4764_Y.J_1907_R(), serverplayerentity.z_4693_k()));
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.n_1700_B(n_1700_B.class);
        int i = buf.u_1723_Y();
        for (int j = 0; j < i; ++j) {
            GameProfile gameprofile = null;
            int k = 0;
            I_14_v gametype = null;
            x_282_a itextcomponent = null;
            switch (this.n_1700_B.ordinal()) {
                case 0: {
                    gameprofile = new GameProfile(buf.w_1484_f(), buf.P_1922_E(16));
                    int l = buf.u_1723_Y();
                    for (int i1 = 0; i1 < l; ++i1) {
                        String s = buf.P_1922_E(Short.MAX_VALUE);
                        String s1 = buf.P_1922_E(Short.MAX_VALUE);
                        if (buf.readBoolean()) {
                            gameprofile.getProperties().put((Object)s, (Object)new Property(s, s1, buf.P_1922_E(Short.MAX_VALUE)));
                            continue;
                        }
                        gameprofile.getProperties().put((Object)s, (Object)new Property(s, s1));
                    }
                    gametype = I_14_v.n_1700_B(buf.u_1723_Y());
                    k = buf.u_1723_Y();
                    if (!buf.readBoolean()) break;
                    itextcomponent = buf.P_1922_E();
                    break;
                }
                case 1: {
                    gameprofile = new GameProfile(buf.w_1484_f(), (String)null);
                    gametype = I_14_v.n_1700_B(buf.u_1723_Y());
                    break;
                }
                case 2: {
                    gameprofile = new GameProfile(buf.w_1484_f(), (String)null);
                    k = buf.u_1723_Y();
                    break;
                }
                case 3: {
                    gameprofile = new GameProfile(buf.w_1484_f(), (String)null);
                    if (!buf.readBoolean()) break;
                    itextcomponent = buf.P_1922_E();
                    break;
                }
                case 4: {
                    gameprofile = new GameProfile(buf.w_1484_f(), (String)null);
                }
            }
            this.J_1907_R.add(new J_1907_R(gameprofile, k, gametype, itextcomponent));
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.G_564_y(this.J_1907_R.size());
        for (J_1907_R splayerlistitempacket$addplayerdata : this.J_1907_R) {
            switch (this.n_1700_B.ordinal()) {
                case 0: {
                    buf.n_1700_B(splayerlistitempacket$addplayerdata.n_1700_B().getId());
                    buf.n_1700_B(splayerlistitempacket$addplayerdata.n_1700_B().getName());
                    buf.G_564_y(splayerlistitempacket$addplayerdata.n_1700_B().getProperties().size());
                    for (Property property : splayerlistitempacket$addplayerdata.n_1700_B().getProperties().values()) {
                        buf.n_1700_B(property.getName());
                        buf.n_1700_B(property.getValue());
                        if (property.hasSignature()) {
                            buf.writeBoolean(true);
                            buf.n_1700_B(property.getSignature());
                            continue;
                        }
                        buf.writeBoolean(false);
                    }
                    buf.G_564_y(splayerlistitempacket$addplayerdata.R_4764_Y().n_1700_B());
                    buf.G_564_y(splayerlistitempacket$addplayerdata.J_1907_R());
                    if (splayerlistitempacket$addplayerdata.G_564_y() == null) {
                        buf.writeBoolean(false);
                        break;
                    }
                    buf.writeBoolean(true);
                    buf.n_1700_B(splayerlistitempacket$addplayerdata.G_564_y());
                    break;
                }
                case 1: {
                    buf.n_1700_B(splayerlistitempacket$addplayerdata.n_1700_B().getId());
                    buf.G_564_y(splayerlistitempacket$addplayerdata.R_4764_Y().n_1700_B());
                    break;
                }
                case 2: {
                    buf.n_1700_B(splayerlistitempacket$addplayerdata.n_1700_B().getId());
                    buf.G_564_y(splayerlistitempacket$addplayerdata.J_1907_R());
                    break;
                }
                case 3: {
                    buf.n_1700_B(splayerlistitempacket$addplayerdata.n_1700_B().getId());
                    if (splayerlistitempacket$addplayerdata.G_564_y() == null) {
                        buf.writeBoolean(false);
                        break;
                    }
                    buf.writeBoolean(true);
                    buf.n_1700_B(splayerlistitempacket$addplayerdata.G_564_y());
                    break;
                }
                case 4: {
                    buf.n_1700_B(splayerlistitempacket$addplayerdata.n_1700_B().getId());
                }
            }
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public List<J_1907_R> J_1907_R() {
        return this.J_1907_R;
    }

    public n_1700_B R_4764_Y() {
        return this.n_1700_B;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("action", (Object)this.n_1700_B).add("entries", this.J_1907_R).toString();
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            u_1723_Y = lightning.product.d_338_B$n_1700_B.n_1700_B();
        }
    }

    public static class J_1907_R {
        private final int n_1700_B;
        private final I_14_v J_1907_R;
        private final GameProfile R_4764_Y;
        private final x_282_a G_564_y;

        public J_1907_R(GameProfile profileIn, int latencyIn, @Nullable I_14_v gameModeIn, @Nullable x_282_a displayNameIn) {
            this.R_4764_Y = profileIn;
            this.n_1700_B = latencyIn;
            this.J_1907_R = gameModeIn;
            this.G_564_y = displayNameIn;
        }

        public GameProfile n_1700_B() {
            return this.R_4764_Y;
        }

        public int J_1907_R() {
            return this.n_1700_B;
        }

        public I_14_v R_4764_Y() {
            return this.J_1907_R;
        }

        @Nullable
        public x_282_a G_564_y() {
            return this.G_564_y;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object)this).add("latency", this.n_1700_B).add("gameMode", (Object)this.J_1907_R).add("profile", (Object)this.R_4764_Y).add("displayName", this.G_564_y == null ? null : x_282_a.n_1700_B.n_1700_B(this.G_564_y)).toString();
        }
    }
}


