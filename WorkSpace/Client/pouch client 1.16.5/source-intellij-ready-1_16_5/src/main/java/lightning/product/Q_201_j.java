/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import lightning.product.F_2904_S;
import lightning.product.RealmsLongRunningMcoTaskScreen;
import lightning.product.J_739_q;
import lightning.product.ConnectTask;
import lightning.product.LongRunningTask;
import lightning.product.U_2871_b;
import lightning.product.MinecraftClient;
import lightning.product.RealmsLongConfirmationScreen;
import lightning.product.RetryCallException;
import lightning.product.g_4106_L;
import lightning.product.k_2603_m;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import lightning.product.r_715_M;
import lightning.product.u_744_e;
import lightning.product.dtoRealmsServerAddress;
import lightning.product.w_728_N;

public class Q_201_j
extends LongRunningTask {
    private final q_1982_R R_4764_Y;
    private final k_2603_m G_564_y;
    private final r_715_M P_1922_E;
    private final ReentrantLock u_1723_Y;

    public Q_201_j(r_715_M p_i232231_1_, k_2603_m p_i232231_2_, q_1982_R p_i232231_3_, ReentrantLock p_i232231_4_) {
        this.G_564_y = p_i232231_2_;
        this.P_1922_E = p_i232231_1_;
        this.R_4764_Y = p_i232231_3_;
        this.u_1723_Y = p_i232231_4_;
    }

    @Override
    public void run() {
        this.J_1907_R(new F_2904_S("mco.connect.connecting"));
        p_178_J realmsclient = p_178_J.n_1700_B();
        boolean flag = false;
        boolean flag1 = false;
        int i = 5;
        dtoRealmsServerAddress realmsserveraddress = null;
        boolean flag2 = false;
        boolean flag3 = false;
        for (int j = 0; j < 40 && !this.n_1700_B(); ++j) {
            try {
                realmsserveraddress = realmsclient.J_1907_R(this.R_4764_Y.n_1700_B);
                flag = true;
            }
            catch (RetryCallException retrycallexception) {
                i = retrycallexception.P_1922_E;
            }
            catch (u_744_e realmsserviceexception) {
                if (realmsserviceexception.R_4764_Y == 6002) {
                    flag2 = true;
                    break;
                }
                if (realmsserviceexception.R_4764_Y == 6006) {
                    flag3 = true;
                    break;
                }
                flag1 = true;
                this.n_1700_B(realmsserviceexception.toString());
                n_1700_B.error("Couldn't connect to world", (Throwable)realmsserviceexception);
                break;
            }
            catch (Exception exception) {
                flag1 = true;
                n_1700_B.error("Couldn't connect to world", (Throwable)exception);
                this.n_1700_B(exception.getLocalizedMessage());
                break;
            }
            if (flag) break;
            this.J_1907_R(i);
        }
        if (flag2) {
            Q_201_j.n_1700_B(new J_739_q(this.G_564_y, this.P_1922_E, this.R_4764_Y));
        } else if (flag3) {
            if (this.R_4764_Y.v_4262_N.equals(MinecraftClient.A_4115_X().z_1737_N().J_1907_R())) {
                Q_201_j.n_1700_B(new g_4106_L(this.G_564_y, this.P_1922_E, this.R_4764_Y.n_1700_B, this.R_4764_Y.P_4830_p == q_1982_R.J_1907_R.J_1907_R));
            } else {
                Q_201_j.n_1700_B(new w_728_N(new F_2904_S("mco.brokenworld.nonowner.title"), new F_2904_S("mco.brokenworld.nonowner.error"), this.G_564_y));
            }
        } else if (!this.n_1700_B() && !flag1) {
            if (flag) {
                dtoRealmsServerAddress realmsserveraddress1 = realmsserveraddress;
                if (realmsserveraddress1.J_1907_R != null && realmsserveraddress1.R_4764_Y != null) {
                    F_2904_S itextcomponent = new F_2904_S("mco.configure.world.resourcepack.question.line1");
                    F_2904_S itextcomponent1 = new F_2904_S("mco.configure.world.resourcepack.question.line2");
                    Q_201_j.n_1700_B(new RealmsLongConfirmationScreen(p_238121_2_ -> {
                        try {
                            if (p_238121_2_) {
                                Function<Throwable, Void> function = p_238122_1_ -> {
                                    MinecraftClient.A_4115_X().z_4693_k().J_1907_R();
                                    n_1700_B.error(p_238122_1_);
                                    Q_201_j.n_1700_B(new w_728_N(new U_2871_b("Failed to download resource pack!"), this.G_564_y));
                                    return null;
                                };
                                try {
                                    ((CompletableFuture)MinecraftClient.A_4115_X().z_4693_k().n_1700_B(realmsserveraddress1.J_1907_R, realmsserveraddress1.R_4764_Y).thenRun(() -> this.n_1700_B(new RealmsLongRunningMcoTaskScreen(this.G_564_y, new ConnectTask(this.G_564_y, this.R_4764_Y, realmsserveraddress1))))).exceptionally(function);
                                }
                                catch (Exception exception1) {
                                    function.apply(exception1);
                                }
                            } else {
                                Q_201_j.n_1700_B(this.G_564_y);
                            }
                        }
                        finally {
                            if (this.u_1723_Y != null && this.u_1723_Y.isHeldByCurrentThread()) {
                                this.u_1723_Y.unlock();
                            }
                        }
                    }, RealmsLongConfirmationScreen.n_1700_B.J_1907_R, itextcomponent, itextcomponent1, true));
                } else {
                    this.n_1700_B(new RealmsLongRunningMcoTaskScreen(this.G_564_y, new ConnectTask(this.G_564_y, this.R_4764_Y, realmsserveraddress1)));
                }
            } else {
                this.n_1700_B(new F_2904_S("mco.errorMessage.connectionFailure"));
            }
        }
    }

    private void J_1907_R(int p_238123_1_) {
        try {
            Thread.sleep(p_238123_1_ * 1000);
        }
        catch (InterruptedException interruptedexception) {
            n_1700_B.warn(interruptedexception.getLocalizedMessage());
        }
    }
}



