/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.File;
import java.io.FileFilter;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lightning.product.A_885_K;
import lightning.product.D_2103_L;
import lightning.product.H_3999_U;
import lightning.product.I_2946_k;
import lightning.product.PackResources;
import lightning.product.PackSource;

public class e_4716_U
implements I_2946_k {
    private static final FileFilter n_1700_B = p_195731_0_ -> {
        boolean flag = p_195731_0_.isFile() && p_195731_0_.getName().endsWith(".zip");
        boolean flag1 = p_195731_0_.isDirectory() && new File(p_195731_0_, "pack.mcmeta").isFile();
        return flag || flag1;
    };
    private final File J_1907_R;
    private final PackSource R_4764_Y;

    public e_4716_U(File p_i231420_1_, PackSource p_i231420_2_) {
        this.J_1907_R = p_i231420_1_;
        this.R_4764_Y = p_i231420_2_;
    }

    @Override
    public void n_1700_B(Consumer<D_2103_L> infoConsumer, D_2103_L.n_1700_B infoFactory) {
        File[] afile;
        if (!this.J_1907_R.isDirectory()) {
            this.J_1907_R.mkdirs();
        }
        if ((afile = this.J_1907_R.listFiles(n_1700_B)) != null) {
            for (File file1 : afile) {
                String s = "file/" + file1.getName();
                D_2103_L resourcepackinfo = D_2103_L.n_1700_B(s, false, this.n_1700_B(file1), infoFactory, D_2103_L.J_1907_R.n_1700_B, this.R_4764_Y);
                if (resourcepackinfo == null) continue;
                infoConsumer.accept(resourcepackinfo);
            }
        }
    }

    private Supplier<PackResources> n_1700_B(File fileIn) {
        return fileIn.isDirectory() ? () -> new A_885_K(fileIn) : () -> new H_3999_U(fileIn);
    }
}


