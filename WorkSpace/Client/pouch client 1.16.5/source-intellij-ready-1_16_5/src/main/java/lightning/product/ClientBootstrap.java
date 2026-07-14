/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import lightning.product.A_4115_X;
import lightning.product.C_1269_X;
import lightning.product.G_624_v;
import lightning.product.L_3537_K;
import lightning.product.P_2272_O;
import lightning.product.R_2822_N;
import lightning.product.S_2828_i;
import lightning.product.U_2871_b;
import lightning.product.V_1176_p;
import lightning.product.ModuleManager;
import lightning.product.e_1231_S;
import lightning.product.h_1640_b;
import lightning.product.n_473_l;
import lightning.product.n_4915_F;
import lightning.product.o_82_k;
import lightning.product.r_4414_L;
import lightning.product.u_4724_w;
import lightning.product.w_2223_C;
import lightning.product.y_2447_C;
import lightning.product.y_2622_c;
import lightning.product.z_3000_g;
import lombok.Generated;
import mods.cape.Cape;
import mods.proxy.Config;
import mods.proxy.ProxyServer;
import mods.viaversion.viamcp.ViaMCP;
import mods.voicechat.ForgeVoicechatClientMod;
import mods.voicechat.ForgeVoicechatMod;

public class ClientBootstrap {
    private static ClientBootstrap J_1907_R;
    private ModuleManager R_4764_Y;
    private y_2622_c G_564_y;
    private V_1176_p P_1922_E;
    private L_3537_K u_1723_Y;
    private n_4915_F v_4262_N;
    private n_473_l w_1484_f;
    private R_2822_N t_148_a;
    private r_4414_L s_956_w;
    private y_2447_C u_2550_I;
    private z_3000_g M_588_G;
    private S_2828_i P_4830_p;
    private P_2272_O h_1847_R;
    private h_1640_b Q_4569_t;
    private C_1269_X M_182_A;
    private o_82_k t_1786_h;
    private e_1231_S multiplayerClientSuggestionProvider;
    public u_4724_w n_1700_B;
    private Cape w_1457_N;

    @w_2223_C
    public ClientBootstrap() {
        J_1907_R = this;
        this.Y_259_p();
    }

    private void Y_259_p() {
        if (G_624_v.t_148_a.J_1907_R()) {
            this.Q_2552_b();
            this.k_2293_S();
            this.C_2741_M();
            A_4115_X.n_1700_B(this);
        }
    }

    private void Q_2552_b() {
        String[] gameFolders;
        for (String folderName : gameFolders = new String[]{"baritone", "mediaplayerinfo", "out", "ViaMCP"}) {
            this.n_1700_B(new File(folderName));
        }
        String[] pouchFolders = new String[]{"logout_spots", "parse"};
        String pouchPath = System.getenv("SystemDrive") + "\\Pouch\\";
        for (String folderName : pouchFolders) {
            this.n_1700_B(new File(pouchPath + folderName));
        }
    }

    private void n_1700_B(File folder) {
        try {
            Path path;
            DosFileAttributeView attributes;
            if (folder.exists() && folder.isDirectory() && (attributes = Files.getFileAttributeView(path = folder.toPath(), DosFileAttributeView.class, new LinkOption[0])) != null) {
                attributes.setHidden(true);
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private void C_2741_M() {
        if (G_624_v.t_148_a.J_1907_R()) {
            this.t_1786_h = new o_82_k();
            this.t_1786_h.n_1700_B();
            this.u_1723_Y = new L_3537_K();
            this.u_1723_Y.n_1700_B();
            this.P_1922_E = new V_1176_p();
            this.P_1922_E.n_1700_B();
            this.G_564_y = new y_2622_c();
            this.G_564_y.n_1700_B();
            this.R_4764_Y = new ModuleManager();
            this.R_4764_Y.n_1700_B();
            this.w_1484_f = new n_473_l();
            this.w_1484_f.n_1700_B();
            this.t_148_a = new R_2822_N();
            this.t_148_a.n_1700_B();
            this.s_956_w = new r_4414_L();
            this.s_956_w.n_1700_B();
            this.u_2550_I = new y_2447_C();
            this.u_2550_I.n_1700_B();
            this.M_588_G = new z_3000_g();
            this.M_588_G.n_1700_B();
            this.P_4830_p = new S_2828_i();
            this.P_4830_p.n_1700_B();
            this.h_1847_R = new P_2272_O();
            this.Q_4569_t = new h_1640_b();
            this.v_4262_N = new n_4915_F();
            this.v_4262_N.n_1700_B();
            this.multiplayerClientSuggestionProvider = new e_1231_S();
            this.multiplayerClientSuggestionProvider.n_1700_B();
            this.v_4262_N.P_4830_p();
            this.n_1700_B = new u_4724_w(new U_2871_b(""));
            this.v_4262_N.w_1457_N();
            this.M_182_A = new C_1269_X();
        }
    }

    @w_2223_C
    private void k_2293_S() {
        if (G_624_v.t_148_a.J_1907_R()) {
            new ForgeVoicechatMod();
            new ForgeVoicechatClientMod();
            new ProxyServer();
            Config.loadConfig();
            this.w_1457_N = new Cape();
            this.w_1457_N.init();
            try {
                ViaMCP.create();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void n_1700_B() {
        this.t_1786_h.G_564_y();
        if (this.v_4262_N != null && n_4915_F.Y_601_j() != null) {
            this.v_4262_N.n_1700_B(n_4915_F.Y_601_j());
        }
        this.v_4262_N.M_588_G();
        if (this.Q_4569_t != null) {
            this.Q_4569_t.R_4764_Y();
        }
    }

    @Generated
    public ModuleManager J_1907_R() {
        return this.R_4764_Y;
    }

    @Generated
    public y_2622_c R_4764_Y() {
        return this.G_564_y;
    }

    @Generated
    public V_1176_p G_564_y() {
        return this.P_1922_E;
    }

    @Generated
    public L_3537_K P_1922_E() {
        return this.u_1723_Y;
    }

    @Generated
    public n_4915_F u_1723_Y() {
        return this.v_4262_N;
    }

    @Generated
    public n_473_l v_4262_N() {
        return this.w_1484_f;
    }

    @Generated
    public R_2822_N w_1484_f() {
        return this.t_148_a;
    }

    @Generated
    public r_4414_L t_148_a() {
        return this.s_956_w;
    }

    @Generated
    public y_2447_C s_956_w() {
        return this.u_2550_I;
    }

    @Generated
    public z_3000_g u_2550_I() {
        return this.M_588_G;
    }

    @Generated
    public S_2828_i M_588_G() {
        return this.P_4830_p;
    }

    @Generated
    public P_2272_O P_4830_p() {
        return this.h_1847_R;
    }

    @Generated
    public h_1640_b h_1847_R() {
        return this.Q_4569_t;
    }

    @Generated
    public C_1269_X Q_4569_t() {
        return this.M_182_A;
    }

    @Generated
    public o_82_k M_182_A() {
        return this.t_1786_h;
    }

    @Generated
    public e_1231_S t_1786_h() {
        return this.multiplayerClientSuggestionProvider;
    }

    @Generated
    public u_4724_w multiplayerClientSuggestionProvider() {
        return this.n_1700_B;
    }

    @Generated
    public Cape w_1457_N() {
        return this.w_1457_N;
    }

    @Generated
    public static ClientBootstrap Y_601_j() {
        return J_1907_R;
    }
}



