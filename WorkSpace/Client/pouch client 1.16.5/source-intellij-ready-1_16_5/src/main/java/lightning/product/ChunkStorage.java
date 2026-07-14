/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFixer
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.datafixers.DataFixer;
import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.A_1763_n;
import lightning.product.SharedConstants;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.b_4507_u;
import lightning.product.d_1030_n;
import lightning.product.f_2392_k;
import lightning.product.n_3832_I;
import lightning.product.o_1967_f;
import lightning.product.s_4380_l;

public class ChunkStorage
implements AutoCloseable {
    private final d_1030_n J_1907_R;
    protected final DataFixer n_1700_B;
    @Nullable
    private A_1763_n R_4764_Y;

    public ChunkStorage(File p_i231889_1_, DataFixer p_i231889_2_, boolean p_i231889_3_) {
        this.n_1700_B = p_i231889_2_;
        this.J_1907_R = new d_1030_n(p_i231889_1_, p_i231889_3_, "chunk");
    }

    public U_2912_j n_1700_B(f_2392_k<b_4507_u> p_235968_1_, Supplier<s_4380_l> p_235968_2_, U_2912_j p_235968_3_) {
        int i = ChunkStorage.n_1700_B(p_235968_3_);
        int j = 1493;
        if (i < 1493 && (p_235968_3_ = n_3832_I.n_1700_B(this.n_1700_B, o_1967_f.R_4764_Y, p_235968_3_, i, 1493)).M_182_A("Level").t_1786_h("hasLegacyStructureData")) {
            if (this.R_4764_Y == null) {
                this.R_4764_Y = A_1763_n.n_1700_B(p_235968_1_, p_235968_2_.get());
            }
            p_235968_3_ = this.R_4764_Y.n_1700_B(p_235968_3_);
        }
        p_235968_3_ = n_3832_I.n_1700_B(this.n_1700_B, o_1967_f.R_4764_Y, p_235968_3_, Math.max(1493, i));
        if (i < SharedConstants.n_1700_B().getWorldVersion()) {
            p_235968_3_.J_1907_R("DataVersion", SharedConstants.n_1700_B().getWorldVersion());
        }
        return p_235968_3_;
    }

    public static int n_1700_B(U_2912_j compound) {
        return compound.R_4764_Y("DataVersion", 99) ? compound.w_1484_f("DataVersion") : -1;
    }

    @Nullable
    public U_2912_j n_1700_B(Y_1387_d p_227078_1_) throws IOException {
        return this.J_1907_R.n_1700_B(p_227078_1_);
    }

    public void n_1700_B(Y_1387_d pos, U_2912_j compound) {
        this.J_1907_R.n_1700_B(pos, compound);
        if (this.R_4764_Y != null) {
            this.R_4764_Y.n_1700_B(pos.n_1700_B());
        }
    }

    public void n_1700_B() {
        this.J_1907_R.n_1700_B().join();
    }

    @Override
    public void close() throws IOException {
        this.J_1907_R.close();
    }
}


