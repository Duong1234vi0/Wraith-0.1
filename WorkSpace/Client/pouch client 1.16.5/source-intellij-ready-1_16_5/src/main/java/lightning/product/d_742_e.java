/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.ResultConsumer
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.brigadier.ResultConsumer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.CommandSource;
import lightning.product.H_1468_N;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.m_3054_I;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.CrashReportCategory;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import net.minecraft.server.G_564_y;

public abstract class d_742_e
implements CommandSource {
    private static final SimpleDateFormat n_1700_B = new SimpleDateFormat("HH:mm:ss");
    private static final x_282_a J_1907_R = new U_2871_b("@");
    private long R_4764_Y = -1L;
    private boolean G_564_y = true;
    private int P_1922_E;
    private boolean u_1723_Y = true;
    @Nullable
    private x_282_a v_4262_N;
    private String w_1484_f = "";
    private x_282_a t_148_a = J_1907_R;

    public int u_1723_Y() {
        return this.P_1922_E;
    }

    public void n_1700_B(int successCountIn) {
        this.P_1922_E = successCountIn;
    }

    public x_282_a v_4262_N() {
        return this.v_4262_N == null ? U_2871_b.R_4764_Y : this.v_4262_N;
    }

    public U_2912_j n_1700_B(U_2912_j compound) {
        compound.n_1700_B("Command", this.w_1484_f);
        compound.J_1907_R("SuccessCount", this.P_1922_E);
        compound.n_1700_B("CustomName", x_282_a.n_1700_B.n_1700_B(this.t_148_a));
        compound.n_1700_B("TrackOutput", this.u_1723_Y);
        if (this.v_4262_N != null && this.u_1723_Y) {
            compound.n_1700_B("LastOutput", x_282_a.n_1700_B.n_1700_B(this.v_4262_N));
        }
        compound.n_1700_B("UpdateLastExecution", this.G_564_y);
        if (this.G_564_y && this.R_4764_Y > 0L) {
            compound.n_1700_B("LastExecution", this.R_4764_Y);
        }
        return compound;
    }

    public void J_1907_R(U_2912_j nbt) {
        this.w_1484_f = nbt.M_588_G("Command");
        this.P_1922_E = nbt.w_1484_f("SuccessCount");
        if (nbt.R_4764_Y("CustomName", 8)) {
            this.n_1700_B(x_282_a.n_1700_B.n_1700_B(nbt.M_588_G("CustomName")));
        }
        if (nbt.R_4764_Y("TrackOutput", 1)) {
            this.u_1723_Y = nbt.t_1786_h("TrackOutput");
        }
        if (nbt.R_4764_Y("LastOutput", 8) && this.u_1723_Y) {
            try {
                this.v_4262_N = x_282_a.n_1700_B.n_1700_B(nbt.M_588_G("LastOutput"));
            }
            catch (Throwable throwable) {
                this.v_4262_N = new U_2871_b(throwable.getMessage());
            }
        } else {
            this.v_4262_N = null;
        }
        if (nbt.P_1922_E("UpdateLastExecution")) {
            this.G_564_y = nbt.t_1786_h("UpdateLastExecution");
        }
        this.R_4764_Y = this.G_564_y && nbt.P_1922_E("LastExecution") ? nbt.t_148_a("LastExecution") : -1L;
    }

    public void n_1700_B(String command) {
        this.w_1484_f = command;
        this.P_1922_E = 0;
    }

    public String w_1484_f() {
        return this.w_1484_f;
    }

    public boolean n_1700_B(b_4507_u worldIn) {
        if (!worldIn.Y_259_p && worldIn.X_933_l() != this.R_4764_Y) {
            if ("Searge".equalsIgnoreCase(this.w_1484_f)) {
                this.v_4262_N = new U_2871_b("#itzlipofutzli");
                this.P_1922_E = 1;
                return true;
            }
            this.P_1922_E = 0;
            G_564_y minecraftserver = this.n_1700_B().T_2506_i();
            if (minecraftserver.s_2632_s() && !H_1468_N.J_1907_R(this.w_1484_f)) {
                try {
                    this.v_4262_N = null;
                    y_2498_m commandsource = this.P_1922_E().n_1700_B((ResultConsumer<y_2498_m>)((ResultConsumer)(p_209527_1_, p_209527_2_, p_209527_3_) -> {
                        if (p_209527_2_) {
                            ++this.P_1922_E;
                        }
                    }));
                    minecraftserver.H_1083_k().n_1700_B(commandsource, this.w_1484_f);
                }
                catch (Throwable throwable) {
                    n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Executing command block");
                    CrashReportCategory crashreportcategory = crashreport.n_1700_B("Command to be executed");
                    crashreportcategory.n_1700_B("Command", this::w_1484_f);
                    crashreportcategory.n_1700_B("Name", () -> this.t_148_a().getString());
                    throw new ReportedException(crashreport);
                }
            }
            this.R_4764_Y = this.G_564_y ? worldIn.X_933_l() : -1L;
            return true;
        }
        return false;
    }

    public x_282_a t_148_a() {
        return this.t_148_a;
    }

    public void n_1700_B(@Nullable x_282_a nameIn) {
        this.t_148_a = nameIn != null ? nameIn : J_1907_R;
    }

    @Override
    public void n_1700_B(x_282_a component, UUID senderUUID) {
        if (this.u_1723_Y) {
            this.v_4262_N = new U_2871_b("[" + n_1700_B.format(new Date()) + "] ").n_1700_B(component);
            this.J_1907_R();
        }
    }

    public abstract e_3591_l n_1700_B();

    public abstract void J_1907_R();

    public void J_1907_R(@Nullable x_282_a lastOutputMessage) {
        this.v_4262_N = lastOutputMessage;
    }

    public void n_1700_B(boolean shouldTrackOutput) {
        this.u_1723_Y = shouldTrackOutput;
    }

    public boolean s_956_w() {
        return this.u_1723_Y;
    }

    public m_3054_I n_1700_B(a_3913_L playerIn) {
        if (!playerIn.ModuleManager()) {
            return m_3054_I.R_4764_Y;
        }
        if (playerIn.Z_759_W().Y_259_p) {
            playerIn.n_1700_B(this);
        }
        return m_3054_I.n_1700_B(playerIn.O_508_d.Y_259_p);
    }

    public abstract e_2866_D R_4764_Y();

    public abstract y_2498_m P_1922_E();

    @Override
    public boolean O_508_d() {
        return this.n_1700_B().H_1990_U().J_1907_R(A_2352_Z.h_1847_R) && this.u_1723_Y;
    }

    @Override
    public boolean r_715_M() {
        return this.u_1723_Y;
    }

    @Override
    public boolean A_1038_p() {
        return this.n_1700_B().H_1990_U().J_1907_R(A_2352_Z.w_1484_f);
    }
}



