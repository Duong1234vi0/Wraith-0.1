/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lightning.product.TagContainer;
import lightning.product.G_3474_H;
import lightning.product.Q_2241_p;
import lightning.product.ReloadableResourceManager;
import lightning.product.ResourceManager;
import lightning.product.V_3553_K;
import lightning.product.X_1446_C;
import lightning.product.PackResources;
import lightning.product.b_653_U;
import lightning.product.f_4186_T;
import lightning.product.i_4221_J;
import lightning.product.k_1471_n;
import lightning.product.ServerAdvancementManager;
import lightning.product.n_916_l;

public class ServerResources
implements AutoCloseable {
    private static final CompletableFuture<X_1446_C> n_1700_B = CompletableFuture.completedFuture(X_1446_C.n_1700_B);
    private final ReloadableResourceManager J_1907_R = new b_653_U(i_4221_J.J_1907_R);
    private final Q_2241_p R_4764_Y;
    private final G_3474_H G_564_y = new G_3474_H();
    private final V_3553_K P_1922_E = new V_3553_K();
    private final k_1471_n u_1723_Y = new k_1471_n();
    private final f_4186_T v_4262_N = new f_4186_T(this.u_1723_Y);
    private final ServerAdvancementManager w_1484_f = new ServerAdvancementManager(this.u_1723_Y);
    private final n_916_l t_148_a;

    public ServerResources(Q_2241_p.n_1700_B envType, int permissionsLevel) {
        this.R_4764_Y = new Q_2241_p(envType);
        this.t_148_a = new n_916_l(permissionsLevel, this.R_4764_Y.n_1700_B());
        this.J_1907_R.n_1700_B(this.P_1922_E);
        this.J_1907_R.n_1700_B(this.u_1723_Y);
        this.J_1907_R.n_1700_B(this.G_564_y);
        this.J_1907_R.n_1700_B(this.v_4262_N);
        this.J_1907_R.n_1700_B(this.t_148_a);
        this.J_1907_R.n_1700_B(this.w_1484_f);
    }

    public n_916_l n_1700_B() {
        return this.t_148_a;
    }

    public k_1471_n J_1907_R() {
        return this.u_1723_Y;
    }

    public f_4186_T R_4764_Y() {
        return this.v_4262_N;
    }

    public TagContainer G_564_y() {
        return this.P_1922_E.J_1907_R();
    }

    public G_3474_H P_1922_E() {
        return this.G_564_y;
    }

    public Q_2241_p u_1723_Y() {
        return this.R_4764_Y;
    }

    public ServerAdvancementManager v_4262_N() {
        return this.w_1484_f;
    }

    public ResourceManager w_1484_f() {
        return this.J_1907_R;
    }

    public static CompletableFuture<ServerResources> n_1700_B(List<PackResources> p_240961_0_, Q_2241_p.n_1700_B p_240961_1_, int p_240961_2_, Executor p_240961_3_, Executor p_240961_4_) {
        ServerResources datapackregistries = new ServerResources(p_240961_1_, p_240961_2_);
        CompletableFuture<X_1446_C> completablefuture = datapackregistries.J_1907_R.n_1700_B(p_240961_3_, p_240961_4_, p_240961_0_, n_1700_B);
        return ((CompletableFuture)completablefuture.whenComplete((p_240963_1_, p_240963_2_) -> {
            if (p_240963_2_ != null) {
                datapackregistries.close();
            }
        })).thenApply(p_240962_1_ -> datapackregistries);
    }

    public void t_148_a() {
        this.P_1922_E.J_1907_R().P_1922_E();
    }

    @Override
    public void close() {
        this.J_1907_R.close();
    }
}


