/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.File;
import java.util.Collections;
import java.util.List;
import lightning.product.ProfileResults;
import lightning.product.ResultField;

public class EmptyProfileResults
implements ProfileResults {
    public static final EmptyProfileResults n_1700_B = new EmptyProfileResults();

    private EmptyProfileResults() {
    }

    @Override
    public List<ResultField> n_1700_B(String sectionPath) {
        return Collections.emptyList();
    }

    @Override
    public boolean n_1700_B(File p_219919_1_) {
        return false;
    }

    @Override
    public long n_1700_B() {
        return 0L;
    }

    @Override
    public int J_1907_R() {
        return 0;
    }

    @Override
    public long R_4764_Y() {
        return 0L;
    }

    @Override
    public int G_564_y() {
        return 0;
    }
}


