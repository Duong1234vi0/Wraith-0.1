/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import javax.annotation.Nullable;
import lightning.product.C_4114_x;
import lightning.product.U_2912_j;
import lightning.product.AbstractIllager;
import lightning.product.SoundEvent;
import lightning.product.b_4507_u;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;

public abstract class SpellcasterIllager
extends AbstractIllager {
    private static final h_256_u<Byte> h_1847_R = C_4114_x.n_1700_B(SpellcasterIllager.class, EntityDataSerializers.n_1700_B);
    protected int R_4764_Y;
    private J_1907_R Q_4569_t = lightning.product.SpellcasterIllager$J_1907_R.n_1700_B;

    protected SpellcasterIllager(t_5_h<? extends SpellcasterIllager> type, b_4507_u p_i48551_2_) {
        super((t_5_h<? extends AbstractIllager>)type, p_i48551_2_);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(h_1847_R, (byte)0);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.R_4764_Y = compound.w_1484_f("SpellTicks");
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("SpellTicks", this.R_4764_Y);
    }

    @Override
    public AbstractIllager.n_1700_B u_1723_Y() {
        if (this.ModuleCategory()) {
            return AbstractIllager.n_1700_B.R_4764_Y;
        }
        return this.h_973_D() ? AbstractIllager.n_1700_B.v_4262_N : AbstractIllager.n_1700_B.n_1700_B;
    }

    public boolean ModuleCategory() {
        if (this.O_508_d.Y_259_p) {
            return this.l_4537_E.n_1700_B(h_1847_R) > 0;
        }
        return this.R_4764_Y > 0;
    }

    public void n_1700_B(J_1907_R spellType) {
        this.Q_4569_t = spellType;
        this.l_4537_E.J_1907_R(h_1847_R, (byte)spellType.v_4262_N);
    }

    protected J_1907_R p_1458_L() {
        return !this.O_508_d.Y_259_p ? this.Q_4569_t : lightning.product.SpellcasterIllager$J_1907_R.n_1700_B(this.l_4537_E.n_1700_B(h_1847_R).byteValue());
    }

    @Override
    protected void X_933_l() {
        super.X_933_l();
        if (this.R_4764_Y > 0) {
            --this.R_4764_Y;
        }
    }

    @Override
    public void v_() {
        super.v_();
        if (this.O_508_d.Y_259_p && this.ModuleCategory()) {
            J_1907_R spellcastingillagerentity$spelltype = this.p_1458_L();
            double d0 = spellcastingillagerentity$spelltype.w_1484_f[0];
            double d1 = spellcastingillagerentity$spelltype.w_1484_f[1];
            double d2 = spellcastingillagerentity$spelltype.w_1484_f[2];
            float f = this.C_1162_e * ((float)Math.PI / 180) + u_530_F.J_1907_R((float)this.RealmsWorldResetDto * 0.6662f) * 0.25f;
            float f1 = u_530_F.J_1907_R(f);
            float f2 = u_530_F.n_1700_B(f);
            this.O_508_d.n_1700_B(ParticleTypes.Y_259_p, this.O_3598_v() + (double)f1 * 0.6, this.X_2960_b() + 1.8, this.l_2647_k() + (double)f2 * 0.6, d0, d1, d2);
            this.O_508_d.n_1700_B(ParticleTypes.Y_259_p, this.O_3598_v() - (double)f1 * 0.6, this.X_2960_b() + 1.8, this.l_2647_k() - (double)f2 * 0.6, d0, d1, d2);
        }
    }

    protected int Module() {
        return this.R_4764_Y;
    }

    protected abstract SoundEvent V_537_k();

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(0, 0.0, 0.0, 0.0);
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(1, 0.7, 0.7, 0.8);
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R(2, 0.4, 0.3, 0.35);
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R(3, 0.7, 0.5, 0.2);
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R(4, 0.3, 0.3, 0.8);
        public static final /* enum */ J_1907_R u_1723_Y = new J_1907_R(5, 0.1, 0.1, 0.2);
        private final int v_4262_N;
        private final double[] w_1484_f;
        private static final /* synthetic */ J_1907_R[] t_148_a;

        public static J_1907_R[] values() {
            return (J_1907_R[])t_148_a.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(int idIn, double xParticleSpeed, double yParticleSpeed, double zParticleSpeed) {
            this.v_4262_N = idIn;
            this.w_1484_f = new double[]{xParticleSpeed, yParticleSpeed, zParticleSpeed};
        }

        public static J_1907_R n_1700_B(int idIn) {
            for (J_1907_R spellcastingillagerentity$spelltype : lightning.product.SpellcasterIllager$J_1907_R.values()) {
                if (idIn != spellcastingillagerentity$spelltype.v_4262_N) continue;
                return spellcastingillagerentity$spelltype;
            }
            return n_1700_B;
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            t_148_a = lightning.product.SpellcasterIllager$J_1907_R.n_1700_B();
        }
    }

    public abstract class R_4764_Y
    extends Goal {
        protected int J_1907_R;
        protected int R_4764_Y;

        protected R_4764_Y() {
        }

        @Override
        public boolean n_1700_B() {
            r_4811_B livingentity = SpellcasterIllager.this.t_148_a();
            if (livingentity != null && livingentity.RealmsLongRunningMcoTaskScreen()) {
                if (SpellcasterIllager.this.ModuleCategory()) {
                    return false;
                }
                return SpellcasterIllager.this.RealmsWorldResetDto >= this.R_4764_Y;
            }
            return false;
        }

        @Override
        public boolean J_1907_R() {
            r_4811_B livingentity = SpellcasterIllager.this.t_148_a();
            return livingentity != null && livingentity.RealmsLongRunningMcoTaskScreen() && this.J_1907_R > 0;
        }

        @Override
        public void R_4764_Y() {
            this.J_1907_R = this.P_4830_p();
            SpellcasterIllager.this.R_4764_Y = this.v_4262_N();
            this.R_4764_Y = SpellcasterIllager.this.RealmsWorldResetDto + this.w_1484_f();
            SoundEvent soundevent = this.u_2550_I();
            if (soundevent != null) {
                SpellcasterIllager.this.n_1700_B(soundevent, 1.0f, 1.0f);
            }
            SpellcasterIllager.this.n_1700_B(this.M_588_G());
        }

        @Override
        public void P_1922_E() {
            --this.J_1907_R;
            if (this.J_1907_R == 0) {
                this.s_956_w();
                SpellcasterIllager.this.n_1700_B(SpellcasterIllager.this.V_537_k(), 1.0f, 1.0f);
            }
        }

        protected abstract void s_956_w();

        protected int P_4830_p() {
            return 20;
        }

        protected abstract int v_4262_N();

        protected abstract int w_1484_f();

        @Nullable
        protected abstract SoundEvent u_2550_I();

        protected abstract J_1907_R M_588_G();
    }

    public class n_1700_B
    extends Goal {
        public n_1700_B() {
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            return SpellcasterIllager.this.Module() > 0;
        }

        @Override
        public void R_4764_Y() {
            super.R_4764_Y();
            SpellcasterIllager.this.t_148_a.h_1847_R();
        }

        @Override
        public void G_564_y() {
            super.G_564_y();
            SpellcasterIllager.this.n_1700_B(lightning.product.SpellcasterIllager$J_1907_R.n_1700_B);
        }

        @Override
        public void P_1922_E() {
            if (SpellcasterIllager.this.t_148_a() != null) {
                SpellcasterIllager.this.c_3005_b().n_1700_B(SpellcasterIllager.this.t_148_a(), (float)SpellcasterIllager.this.H_1990_U(), (float)SpellcasterIllager.this.Z_976_R());
            }
        }
    }
}



