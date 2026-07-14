/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.ExceptionCollector;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.i_3066_Y;
import lightning.product.r_1827_u;

public final class P_2395_j
implements AutoCloseable {
    private final Long2ObjectLinkedOpenHashMap<i_3066_Y> n_1700_B = new Long2ObjectLinkedOpenHashMap();
    private final File J_1907_R;
    private final boolean R_4764_Y;

    P_2395_j(File p_i231895_1_, boolean p_i231895_2_) {
        this.J_1907_R = p_i231895_1_;
        this.R_4764_Y = p_i231895_2_;
    }

    private i_3066_Y J_1907_R(Y_1387_d pos) throws IOException {
        long i = Y_1387_d.n_1700_B(pos.u_1723_Y(), pos.v_4262_N());
        i_3066_Y regionfile = (i_3066_Y)this.n_1700_B.getAndMoveToFirst(i);
        if (regionfile != null) {
            return regionfile;
        }
        if (this.n_1700_B.size() >= 256) {
            ((i_3066_Y)this.n_1700_B.removeLast()).close();
        }
        if (!this.J_1907_R.exists()) {
            this.J_1907_R.mkdirs();
        }
        File file1 = new File(this.J_1907_R, "r." + pos.u_1723_Y() + "." + pos.v_4262_N() + ".mca");
        i_3066_Y regionfile1 = new i_3066_Y(file1, this.J_1907_R, this.R_4764_Y);
        this.n_1700_B.putAndMoveToFirst(i, (Object)regionfile1);
        return regionfile1;
    }

    @Nullable
    public U_2912_j n_1700_B(Y_1387_d pos) throws IOException {
        Object object;
        i_3066_Y regionfile = this.J_1907_R(pos);
        try (DataInputStream datainputstream = regionfile.n_1700_B(pos);){
            if (datainputstream != null) {
                U_2912_j u_2912_j = r_1827_u.n_1700_B(datainputstream);
                return u_2912_j;
            }
            object = null;
        }
        return object;
    }

    protected void n_1700_B(Y_1387_d pos, U_2912_j compound) throws IOException {
        i_3066_Y regionfile = this.J_1907_R(pos);
        try (DataOutputStream dataoutputstream = regionfile.R_4764_Y(pos);){
            r_1827_u.n_1700_B(compound, (DataOutput)dataoutputstream);
        }
    }

    @Override
    public void close() throws IOException {
        ExceptionCollector<IOException> suppressedexceptions = new ExceptionCollector<IOException>();
        for (i_3066_Y regionfile : this.n_1700_B.values()) {
            try {
                regionfile.close();
            }
            catch (IOException ioexception) {
                suppressedexceptions.n_1700_B(ioexception);
            }
        }
        suppressedexceptions.n_1700_B();
    }

    public void n_1700_B() throws IOException {
        for (i_3066_Y regionfile : this.n_1700_B.values()) {
            regionfile.n_1700_B();
        }
    }
}


