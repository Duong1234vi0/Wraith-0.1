/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import lightning.product.DedicatedServerProperties;
import lightning.product.V_4604_M;
import lightning.product.Z_4308_L;
import lightning.product.g_1995_W;
import lightning.product.r_4097_j;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DedicatedPlayerList
extends g_1995_W {
    private static final Logger u_1723_Y = LogManager.getLogger();

    public DedicatedPlayerList(V_4604_M p_i232600_1_, r_4097_j.J_1907_R p_i232600_2_, Z_4308_L p_i232600_3_) {
        super(p_i232600_1_, p_i232600_2_, p_i232600_3_, p_i232600_1_.n_1700_B().v_4276_D);
        DedicatedServerProperties serverproperties = p_i232600_1_.n_1700_B();
        this.n_1700_B(serverproperties.z_1737_N);
        super.n_1700_B(serverproperties.Z_976_R.get());
        this.q_2307_F();
        this.C_2741_M();
        this.k_2293_S();
        this.Q_2552_b();
        this.Z_875_P();
        this.H_2857_Y();
        this.c_3005_b();
        if (!this.s_956_w().J_1907_R().exists()) {
            this.A_4115_X();
        }
    }

    @Override
    public void n_1700_B(boolean whitelistEnabled) {
        super.n_1700_B(whitelistEnabled);
        this.J_1907_R().s_956_w(whitelistEnabled);
    }

    @Override
    public void n_1700_B(GameProfile profile) {
        super.n_1700_B(profile);
        this.c_3005_b();
    }

    @Override
    public void J_1907_R(GameProfile profile) {
        super.J_1907_R(profile);
        this.c_3005_b();
    }

    @Override
    public void n_1700_B() {
        this.H_2857_Y();
    }

    private void Q_2552_b() {
        try {
            this.w_1484_f().P_1922_E();
        }
        catch (IOException ioexception) {
            u_1723_Y.warn("Failed to save ip banlist: ", (Throwable)ioexception);
        }
    }

    private void C_2741_M() {
        try {
            this.v_4262_N().P_1922_E();
        }
        catch (IOException ioexception) {
            u_1723_Y.warn("Failed to save user banlist: ", (Throwable)ioexception);
        }
    }

    private void k_2293_S() {
        try {
            this.w_1484_f().u_1723_Y();
        }
        catch (IOException ioexception) {
            u_1723_Y.warn("Failed to load ip banlist: ", (Throwable)ioexception);
        }
    }

    private void q_2307_F() {
        try {
            this.v_4262_N().u_1723_Y();
        }
        catch (IOException ioexception) {
            u_1723_Y.warn("Failed to load user banlist: ", (Throwable)ioexception);
        }
    }

    private void Z_875_P() {
        try {
            this.M_588_G().u_1723_Y();
        }
        catch (Exception exception) {
            u_1723_Y.warn("Failed to load operators list: ", (Throwable)exception);
        }
    }

    private void c_3005_b() {
        try {
            this.M_588_G().P_1922_E();
        }
        catch (Exception exception) {
            u_1723_Y.warn("Failed to save operators list: ", (Throwable)exception);
        }
    }

    private void H_2857_Y() {
        try {
            this.s_956_w().u_1723_Y();
        }
        catch (Exception exception) {
            u_1723_Y.warn("Failed to load white-list: ", (Throwable)exception);
        }
    }

    private void A_4115_X() {
        try {
            this.s_956_w().P_1922_E();
        }
        catch (Exception exception) {
            u_1723_Y.warn("Failed to save white-list: ", (Throwable)exception);
        }
    }

    @Override
    public boolean R_4764_Y(GameProfile profile) {
        return !this.M_182_A() || this.u_1723_Y(profile) || this.s_956_w().n_1700_B(profile);
    }

    public V_4604_M J_1907_R() {
        return (V_4604_M)super.R_4764_Y();
    }

    @Override
    public boolean G_564_y(GameProfile profile) {
        return this.M_588_G().n_1700_B(profile);
    }

    @Override
    public /* synthetic */ G_564_y R_4764_Y() {
        return this.J_1907_R();
    }
}


