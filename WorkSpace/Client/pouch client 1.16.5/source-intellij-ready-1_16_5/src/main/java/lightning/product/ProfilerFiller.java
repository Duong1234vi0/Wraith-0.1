/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Supplier;
import lightning.product.InactiveProfiler;

public interface ProfilerFiller {
    public void n_1700_B();

    public void J_1907_R();

    public void n_1700_B(String var1);

    public void n_1700_B(Supplier<String> var1);

    public void R_4764_Y();

    public void J_1907_R(String var1);

    public void J_1907_R(Supplier<String> var1);

    public void R_4764_Y(String var1);

    public void R_4764_Y(Supplier<String> var1);

    public static ProfilerFiller n_1700_B(final ProfilerFiller p_233513_0_, final ProfilerFiller p_233513_1_) {
        if (p_233513_0_ == InactiveProfiler.n_1700_B) {
            return p_233513_1_;
        }
        return p_233513_1_ == InactiveProfiler.n_1700_B ? p_233513_0_ : new ProfilerFiller(){

            @Override
            public void n_1700_B() {
                p_233513_0_.n_1700_B();
                p_233513_1_.n_1700_B();
            }

            @Override
            public void J_1907_R() {
                p_233513_0_.J_1907_R();
                p_233513_1_.J_1907_R();
            }

            @Override
            public void n_1700_B(String name) {
                p_233513_0_.n_1700_B(name);
                p_233513_1_.n_1700_B(name);
            }

            @Override
            public void n_1700_B(Supplier<String> nameSupplier) {
                p_233513_0_.n_1700_B(nameSupplier);
                p_233513_1_.n_1700_B(nameSupplier);
            }

            @Override
            public void R_4764_Y() {
                p_233513_0_.R_4764_Y();
                p_233513_1_.R_4764_Y();
            }

            @Override
            public void J_1907_R(String name) {
                p_233513_0_.J_1907_R(name);
                p_233513_1_.J_1907_R(name);
            }

            @Override
            public void J_1907_R(Supplier<String> nameSupplier) {
                p_233513_0_.J_1907_R(nameSupplier);
                p_233513_1_.J_1907_R(nameSupplier);
            }

            @Override
            public void R_4764_Y(String p_230035_1_) {
                p_233513_0_.R_4764_Y(p_230035_1_);
                p_233513_1_.R_4764_Y(p_230035_1_);
            }

            @Override
            public void R_4764_Y(Supplier<String> p_230036_1_) {
                p_233513_0_.R_4764_Y(p_230036_1_);
                p_233513_1_.R_4764_Y(p_230036_1_);
            }
        };
    }
}


