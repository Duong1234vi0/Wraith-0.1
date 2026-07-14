/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.Rotations;
import lightning.product.I_1170_F;
import lightning.product.VillagerData;
import lightning.product.K_4074_S;
import lightning.product.ParticleOptions;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.t_252_P;
import lightning.product.ParticleType;
import lightning.product.x_282_a;
import lightning.product.EntityDataSerializer;

public class EntityDataSerializers {
    private static final t_252_P<EntityDataSerializer<?>> Y_601_j = new t_252_P(16);
    public static final EntityDataSerializer<Byte> n_1700_B = new EntityDataSerializer<Byte>(){

        @Override
        public void n_1700_B(b_2585_i buf, Byte value) {
            buf.writeByte(value.byteValue());
        }

        @Override
        public Byte n_1700_B(b_2585_i buf) {
            return buf.readByte();
        }

        @Override
        public Byte n_1700_B(Byte value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<Integer> J_1907_R = new EntityDataSerializer<Integer>(){

        @Override
        public void n_1700_B(b_2585_i buf, Integer value) {
            buf.G_564_y(value);
        }

        @Override
        public Integer n_1700_B(b_2585_i buf) {
            return buf.u_1723_Y();
        }

        @Override
        public Integer n_1700_B(Integer value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<Float> R_4764_Y = new EntityDataSerializer<Float>(){

        @Override
        public void n_1700_B(b_2585_i buf, Float value) {
            buf.writeFloat(value.floatValue());
        }

        @Override
        public Float n_1700_B(b_2585_i buf) {
            return Float.valueOf(buf.readFloat());
        }

        @Override
        public Float n_1700_B(Float value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<String> G_564_y = new EntityDataSerializer<String>(){

        @Override
        public void n_1700_B(b_2585_i buf, String value) {
            buf.n_1700_B(value);
        }

        @Override
        public String n_1700_B(b_2585_i buf) {
            return buf.P_1922_E(Short.MAX_VALUE);
        }

        @Override
        public String n_1700_B(String value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<x_282_a> P_1922_E = new EntityDataSerializer<x_282_a>(){

        @Override
        public void n_1700_B(b_2585_i buf, x_282_a value) {
            buf.n_1700_B(value);
        }

        @Override
        public x_282_a n_1700_B(b_2585_i buf) {
            return buf.P_1922_E();
        }

        @Override
        public x_282_a n_1700_B(x_282_a value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<Optional<x_282_a>> u_1723_Y = new EntityDataSerializer<Optional<x_282_a>>(){

        @Override
        public void n_1700_B(b_2585_i buf, Optional<x_282_a> value) {
            if (value.isPresent()) {
                buf.writeBoolean(true);
                buf.n_1700_B(value.get());
            } else {
                buf.writeBoolean(false);
            }
        }

        @Override
        public Optional<x_282_a> n_1700_B(b_2585_i buf) {
            return buf.readBoolean() ? Optional.of(buf.P_1922_E()) : Optional.empty();
        }

        @Override
        public Optional<x_282_a> n_1700_B(Optional<x_282_a> value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<Z_1993_T> v_4262_N = new EntityDataSerializer<Z_1993_T>(){

        @Override
        public void n_1700_B(b_2585_i buf, Z_1993_T value) {
            buf.n_1700_B(value);
        }

        @Override
        public Z_1993_T n_1700_B(b_2585_i buf) {
            return buf.u_2550_I();
        }

        @Override
        public Z_1993_T n_1700_B(Z_1993_T value) {
            return value.t_148_a();
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<Optional<K_4074_S>> w_1484_f = new EntityDataSerializer<Optional<K_4074_S>>(){

        @Override
        public void n_1700_B(b_2585_i buf, Optional<K_4074_S> value) {
            if (value.isPresent()) {
                buf.G_564_y(T_2915_h.s_956_w(value.get()));
            } else {
                buf.G_564_y(0);
            }
        }

        @Override
        public Optional<K_4074_S> n_1700_B(b_2585_i buf) {
            int i = buf.u_1723_Y();
            return i == 0 ? Optional.empty() : Optional.of(T_2915_h.n_1700_B(i));
        }

        @Override
        public Optional<K_4074_S> n_1700_B(Optional<K_4074_S> value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<Boolean> t_148_a = new EntityDataSerializer<Boolean>(){

        @Override
        public void n_1700_B(b_2585_i buf, Boolean value) {
            buf.writeBoolean(value);
        }

        @Override
        public Boolean n_1700_B(b_2585_i buf) {
            return buf.readBoolean();
        }

        @Override
        public Boolean n_1700_B(Boolean value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<ParticleOptions> s_956_w = new EntityDataSerializer<ParticleOptions>(){

        @Override
        public void n_1700_B(b_2585_i buf, ParticleOptions value) {
            buf.G_564_y(V_3137_a.g_164_R.n_1700_B(value.G_564_y()));
            value.n_1700_B(buf);
        }

        @Override
        public ParticleOptions n_1700_B(b_2585_i buf) {
            return this.n_1700_B(buf, (ParticleType)V_3137_a.g_164_R.n_1700_B(buf.u_1723_Y()));
        }

        private <T extends ParticleOptions> T n_1700_B(b_2585_i p_200543_1_, ParticleType<T> p_200543_2_) {
            return p_200543_2_.u_1723_Y().J_1907_R(p_200543_2_, p_200543_1_);
        }

        @Override
        public ParticleOptions n_1700_B(ParticleOptions value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<Rotations> u_2550_I = new EntityDataSerializer<Rotations>(){

        @Override
        public void n_1700_B(b_2585_i buf, Rotations value) {
            buf.writeFloat(value.J_1907_R());
            buf.writeFloat(value.R_4764_Y());
            buf.writeFloat(value.G_564_y());
        }

        @Override
        public Rotations n_1700_B(b_2585_i buf) {
            return new Rotations(buf.readFloat(), buf.readFloat(), buf.readFloat());
        }

        @Override
        public Rotations n_1700_B(Rotations value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<c_1514_x> M_588_G = new EntityDataSerializer<c_1514_x>(){

        @Override
        public void n_1700_B(b_2585_i buf, c_1514_x value) {
            buf.n_1700_B(value);
        }

        @Override
        public c_1514_x n_1700_B(b_2585_i buf) {
            return buf.R_4764_Y();
        }

        @Override
        public c_1514_x n_1700_B(c_1514_x value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<Optional<c_1514_x>> P_4830_p = new EntityDataSerializer<Optional<c_1514_x>>(){

        @Override
        public void n_1700_B(b_2585_i buf, Optional<c_1514_x> value) {
            buf.writeBoolean(value.isPresent());
            if (value.isPresent()) {
                buf.n_1700_B(value.get());
            }
        }

        @Override
        public Optional<c_1514_x> n_1700_B(b_2585_i buf) {
            return !buf.readBoolean() ? Optional.empty() : Optional.of(buf.R_4764_Y());
        }

        @Override
        public Optional<c_1514_x> n_1700_B(Optional<c_1514_x> value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<b_257_Y> h_1847_R = new EntityDataSerializer<b_257_Y>(){

        @Override
        public void n_1700_B(b_2585_i buf, b_257_Y value) {
            buf.n_1700_B(value);
        }

        @Override
        public b_257_Y n_1700_B(b_2585_i buf) {
            return buf.n_1700_B(b_257_Y.class);
        }

        @Override
        public b_257_Y n_1700_B(b_257_Y value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<Optional<UUID>> Q_4569_t = new EntityDataSerializer<Optional<UUID>>(){

        @Override
        public void n_1700_B(b_2585_i buf, Optional<UUID> value) {
            buf.writeBoolean(value.isPresent());
            if (value.isPresent()) {
                buf.n_1700_B(value.get());
            }
        }

        @Override
        public Optional<UUID> n_1700_B(b_2585_i buf) {
            return !buf.readBoolean() ? Optional.empty() : Optional.of(buf.w_1484_f());
        }

        @Override
        public Optional<UUID> n_1700_B(Optional<UUID> value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<U_2912_j> M_182_A = new EntityDataSerializer<U_2912_j>(){

        @Override
        public void n_1700_B(b_2585_i buf, U_2912_j value) {
            buf.n_1700_B(value);
        }

        @Override
        public U_2912_j n_1700_B(b_2585_i buf) {
            return buf.t_148_a();
        }

        @Override
        public U_2912_j n_1700_B(U_2912_j value) {
            return value.v_4262_N();
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<VillagerData> t_1786_h = new EntityDataSerializer<VillagerData>(){

        @Override
        public void n_1700_B(b_2585_i buf, VillagerData value) {
            buf.G_564_y(V_3137_a.O_508_d.n_1700_B(value.n_1700_B()));
            buf.G_564_y(V_3137_a.r_715_M.n_1700_B(value.J_1907_R()));
            buf.G_564_y(value.R_4764_Y());
        }

        @Override
        public VillagerData n_1700_B(b_2585_i buf) {
            return new VillagerData(V_3137_a.O_508_d.n_1700_B(buf.u_1723_Y()), V_3137_a.r_715_M.n_1700_B(buf.u_1723_Y()), buf.u_1723_Y());
        }

        @Override
        public VillagerData n_1700_B(VillagerData value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<OptionalInt> multiplayerClientSuggestionProvider = new EntityDataSerializer<OptionalInt>(){

        @Override
        public void n_1700_B(b_2585_i buf, OptionalInt value) {
            buf.G_564_y(value.orElse(-1) + 1);
        }

        @Override
        public OptionalInt n_1700_B(b_2585_i buf) {
            int i = buf.u_1723_Y();
            return i == 0 ? OptionalInt.empty() : OptionalInt.of(i - 1);
        }

        @Override
        public OptionalInt n_1700_B(OptionalInt value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };
    public static final EntityDataSerializer<I_1170_F> w_1457_N = new EntityDataSerializer<I_1170_F>(){

        @Override
        public void n_1700_B(b_2585_i buf, I_1170_F value) {
            buf.n_1700_B(value);
        }

        @Override
        public I_1170_F n_1700_B(b_2585_i buf) {
            return buf.n_1700_B(I_1170_F.class);
        }

        @Override
        public I_1170_F n_1700_B(I_1170_F value) {
            return value;
        }

        @Override
        public /* synthetic */ Object J_1907_R(b_2585_i b_2585_i2) {
            return this.n_1700_B(b_2585_i2);
        }
    };

    public static void n_1700_B(EntityDataSerializer<?> serializer) {
        Y_601_j.J_1907_R(serializer);
    }

    @Nullable
    public static EntityDataSerializer<?> n_1700_B(int id) {
        return Y_601_j.n_1700_B(id);
    }

    public static int J_1907_R(EntityDataSerializer<?> serializer) {
        return Y_601_j.n_1700_B(serializer);
    }

    static {
        EntityDataSerializers.n_1700_B(n_1700_B);
        EntityDataSerializers.n_1700_B(J_1907_R);
        EntityDataSerializers.n_1700_B(R_4764_Y);
        EntityDataSerializers.n_1700_B(G_564_y);
        EntityDataSerializers.n_1700_B(P_1922_E);
        EntityDataSerializers.n_1700_B(u_1723_Y);
        EntityDataSerializers.n_1700_B(v_4262_N);
        EntityDataSerializers.n_1700_B(t_148_a);
        EntityDataSerializers.n_1700_B(u_2550_I);
        EntityDataSerializers.n_1700_B(M_588_G);
        EntityDataSerializers.n_1700_B(P_4830_p);
        EntityDataSerializers.n_1700_B(h_1847_R);
        EntityDataSerializers.n_1700_B(Q_4569_t);
        EntityDataSerializers.n_1700_B(w_1484_f);
        EntityDataSerializers.n_1700_B(M_182_A);
        EntityDataSerializers.n_1700_B(s_956_w);
        EntityDataSerializers.n_1700_B(t_1786_h);
        EntityDataSerializers.n_1700_B(multiplayerClientSuggestionProvider);
        EntityDataSerializers.n_1700_B(w_1457_N);
    }
}


