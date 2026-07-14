/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lightning.product.N_4263_v;
import lightning.product.Y_1387_d;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Cursor3D;
import lightning.product.u_530_F;
import lightning.product.z_3539_x;

public class SectionPos
extends z_3539_x {
    private SectionPos(int p_i50794_1_, int p_i50794_2_, int p_i50794_3_) {
        super(p_i50794_1_, p_i50794_2_, p_i50794_3_);
    }

    public static SectionPos n_1700_B(int chunkX, int chunkY, int chunkZ) {
        return new SectionPos(chunkX, chunkY, chunkZ);
    }

    public static SectionPos n_1700_B(c_1514_x worldPos) {
        return new SectionPos(SectionPos.n_1700_B(worldPos.getX()), SectionPos.n_1700_B(worldPos.getY()), SectionPos.n_1700_B(worldPos.getZ()));
    }

    public static SectionPos n_1700_B(Y_1387_d xz, int y) {
        return new SectionPos(xz.J_1907_R, y, xz.R_4764_Y);
    }

    public static SectionPos n_1700_B(N_4263_v p_218157_0_) {
        return new SectionPos(SectionPos.n_1700_B(u_530_F.R_4764_Y(p_218157_0_.O_3598_v())), SectionPos.n_1700_B(u_530_F.R_4764_Y(p_218157_0_.X_2960_b())), SectionPos.n_1700_B(u_530_F.R_4764_Y(p_218157_0_.l_2647_k())));
    }

    public static SectionPos n_1700_B(long p_218170_0_) {
        return new SectionPos(SectionPos.J_1907_R(p_218170_0_), SectionPos.R_4764_Y(p_218170_0_), SectionPos.G_564_y(p_218170_0_));
    }

    public static long n_1700_B(long p_218172_0_, b_257_Y p_218172_2_) {
        return SectionPos.n_1700_B(p_218172_0_, p_218172_2_.t_148_a(), p_218172_2_.s_956_w(), p_218172_2_.u_2550_I());
    }

    public static long n_1700_B(long p_218174_0_, int dx, int dy, int dz) {
        return SectionPos.J_1907_R(SectionPos.J_1907_R(p_218174_0_) + dx, SectionPos.R_4764_Y(p_218174_0_) + dy, SectionPos.G_564_y(p_218174_0_) + dz);
    }

    public static int n_1700_B(int worldCoord) {
        return worldCoord >> 4;
    }

    public static int J_1907_R(int p_218171_0_) {
        return p_218171_0_ & 0xF;
    }

    public static short J_1907_R(c_1514_x p_218150_0_) {
        int i = SectionPos.J_1907_R(p_218150_0_.getX());
        int j = SectionPos.J_1907_R(p_218150_0_.getY());
        int k = SectionPos.J_1907_R(p_218150_0_.getZ());
        return (short)(i << 8 | k << 4 | j << 0);
    }

    public static int n_1700_B(short p_243641_0_) {
        return p_243641_0_ >>> 8 & 0xF;
    }

    public static int J_1907_R(short p_243642_0_) {
        return p_243642_0_ >>> 0 & 0xF;
    }

    public static int R_4764_Y(short p_243643_0_) {
        return p_243643_0_ >>> 4 & 0xF;
    }

    public int G_564_y(short p_243644_1_) {
        return this.G_564_y() + SectionPos.n_1700_B(p_243644_1_);
    }

    public int P_1922_E(short p_243645_1_) {
        return this.P_1922_E() + SectionPos.J_1907_R(p_243645_1_);
    }

    public int u_1723_Y(short p_243646_1_) {
        return this.u_1723_Y() + SectionPos.R_4764_Y(p_243646_1_);
    }

    public c_1514_x v_4262_N(short p_243647_1_) {
        return new c_1514_x(this.G_564_y(p_243647_1_), this.P_1922_E(p_243647_1_), this.u_1723_Y(p_243647_1_));
    }

    public static int R_4764_Y(int chunkCoord) {
        return chunkCoord << 4;
    }

    public static int J_1907_R(long packed) {
        return (int)(packed << 0 >> 42);
    }

    public static int R_4764_Y(long packed) {
        return (int)(packed << 44 >> 44);
    }

    public static int G_564_y(long packed) {
        return (int)(packed << 22 >> 42);
    }

    public int n_1700_B() {
        return this.getX();
    }

    public int J_1907_R() {
        return this.getY();
    }

    public int R_4764_Y() {
        return this.getZ();
    }

    public int G_564_y() {
        return this.n_1700_B() << 4;
    }

    public int P_1922_E() {
        return this.J_1907_R() << 4;
    }

    public int u_1723_Y() {
        return this.R_4764_Y() << 4;
    }

    public int v_4262_N() {
        return (this.n_1700_B() << 4) + 15;
    }

    public int w_1484_f() {
        return (this.J_1907_R() << 4) + 15;
    }

    public int t_148_a() {
        return (this.R_4764_Y() << 4) + 15;
    }

    public static long P_1922_E(long worldPos) {
        return SectionPos.J_1907_R(SectionPos.n_1700_B(c_1514_x.unpackX(worldPos)), SectionPos.n_1700_B(c_1514_x.unpackY(worldPos)), SectionPos.n_1700_B(c_1514_x.unpackZ(worldPos)));
    }

    public static long u_1723_Y(long p_218169_0_) {
        return p_218169_0_ & 0xFFFFFFFFFFF00000L;
    }

    public c_1514_x s_956_w() {
        return new c_1514_x(SectionPos.R_4764_Y(this.n_1700_B()), SectionPos.R_4764_Y(this.J_1907_R()), SectionPos.R_4764_Y(this.R_4764_Y()));
    }

    public c_1514_x u_2550_I() {
        int i = 8;
        return this.s_956_w().add(8, 8, 8);
    }

    public Y_1387_d M_588_G() {
        return new Y_1387_d(this.n_1700_B(), this.R_4764_Y());
    }

    public static long J_1907_R(int p_218166_0_, int p_218166_1_, int p_218166_2_) {
        long i = 0L;
        i |= ((long)p_218166_0_ & 0x3FFFFFL) << 42;
        return (i |= ((long)p_218166_1_ & 0xFFFFFL) << 0) | ((long)p_218166_2_ & 0x3FFFFFL) << 20;
    }

    public long P_4830_p() {
        return SectionPos.J_1907_R(this.n_1700_B(), this.J_1907_R(), this.R_4764_Y());
    }

    public Stream<c_1514_x> h_1847_R() {
        return c_1514_x.getAllInBox(this.G_564_y(), this.P_1922_E(), this.u_1723_Y(), this.v_4262_N(), this.w_1484_f(), this.t_148_a());
    }

    public static Stream<SectionPos> n_1700_B(SectionPos center, int radius) {
        int i = center.n_1700_B();
        int j = center.J_1907_R();
        int k = center.R_4764_Y();
        return SectionPos.n_1700_B(i - radius, j - radius, k - radius, i + radius, j + radius, k + radius);
    }

    public static Stream<SectionPos> J_1907_R(Y_1387_d p_229421_0_, int p_229421_1_) {
        int i = p_229421_0_.J_1907_R;
        int j = p_229421_0_.R_4764_Y;
        return SectionPos.n_1700_B(i - p_229421_1_, 0, j - p_229421_1_, i + p_229421_1_, 15, j + p_229421_1_);
    }

    public static Stream<SectionPos> n_1700_B(final int p_218168_0_, final int p_218168_1_, final int p_218168_2_, final int p_218168_3_, final int p_218168_4_, final int p_218168_5_) {
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<SectionPos>((long)((p_218168_3_ - p_218168_0_ + 1) * (p_218168_4_ - p_218168_1_ + 1) * (p_218168_5_ - p_218168_2_ + 1)), 64){
            final Cursor3D n_1700_B;
            {
                super(est, additionalCharacteristics);
                this.n_1700_B = new Cursor3D(p_218168_0_, p_218168_1_, p_218168_2_, p_218168_3_, p_218168_4_, p_218168_5_);
            }

            @Override
            public boolean tryAdvance(Consumer<? super SectionPos> p_tryAdvance_1_) {
                if (this.n_1700_B.n_1700_B()) {
                    p_tryAdvance_1_.accept(new SectionPos(this.n_1700_B.J_1907_R(), this.n_1700_B.R_4764_Y(), this.n_1700_B.G_564_y()));
                    return true;
                }
                return false;
            }
        }, false);
    }
}


