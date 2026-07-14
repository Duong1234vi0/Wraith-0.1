/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.H_1468_N;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.M_3212_T;
import lightning.product.StructureBlock;
import lightning.product.U_2912_j;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.d_862_x;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.j_3341_s;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.q_4099_E;
import lightning.product.BlockEntityType;
import lightning.product.r_4811_B;
import lightning.product.s_3109_F;
import lightning.product.u_530_F;
import lightning.product.w_1748_S;

public class j_2644_e
extends i_2154_H {
    private g_2336_b n_1700_B;
    private String J_1907_R = "";
    private String R_4764_Y = "";
    private c_1514_x G_564_y = new c_1514_x(0, 1, 0);
    private c_1514_x P_1922_E = c_1514_x.ZERO;
    private q_4099_E u_1723_Y = q_4099_E.n_1700_B;
    private W_2163_m v_4262_N = W_2163_m.n_1700_B;
    private M_3212_T w_1484_f = M_3212_T.G_564_y;
    private boolean t_148_a = true;
    private boolean s_956_w;
    private boolean h_1847_R;
    private boolean Q_4569_t = true;
    private float M_182_A = 1.0f;
    private long t_1786_h;

    public j_2644_e() {
        super(BlockEntityType.Y_601_j);
    }

    @Override
    public double t_148_a() {
        return 96.0;
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("name", this.P_1922_E());
        compound.n_1700_B("author", this.J_1907_R);
        compound.n_1700_B("metadata", this.R_4764_Y);
        compound.J_1907_R("posX", this.G_564_y.getX());
        compound.J_1907_R("posY", this.G_564_y.getY());
        compound.J_1907_R("posZ", this.G_564_y.getZ());
        compound.J_1907_R("sizeX", this.P_1922_E.getX());
        compound.J_1907_R("sizeY", this.P_1922_E.getY());
        compound.J_1907_R("sizeZ", this.P_1922_E.getZ());
        compound.n_1700_B("rotation", this.v_4262_N.toString());
        compound.n_1700_B("mirror", this.u_1723_Y.toString());
        compound.n_1700_B("mode", this.w_1484_f.toString());
        compound.n_1700_B("ignoreEntities", this.t_148_a);
        compound.n_1700_B("powered", this.s_956_w);
        compound.n_1700_B("showair", this.h_1847_R);
        compound.n_1700_B("showboundingbox", this.Q_4569_t);
        compound.n_1700_B("integrity", this.M_182_A);
        compound.n_1700_B("seed", this.t_1786_h);
        return compound;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.n_1700_B(nbt.M_588_G("name"));
        this.J_1907_R = nbt.M_588_G("author");
        this.R_4764_Y = nbt.M_588_G("metadata");
        int i = u_530_F.n_1700_B(nbt.w_1484_f("posX"), -48, 48);
        int j = u_530_F.n_1700_B(nbt.w_1484_f("posY"), -48, 48);
        int k = u_530_F.n_1700_B(nbt.w_1484_f("posZ"), -48, 48);
        this.G_564_y = new c_1514_x(i, j, k);
        int l = u_530_F.n_1700_B(nbt.w_1484_f("sizeX"), 0, 48);
        int i1 = u_530_F.n_1700_B(nbt.w_1484_f("sizeY"), 0, 48);
        int j1 = u_530_F.n_1700_B(nbt.w_1484_f("sizeZ"), 0, 48);
        this.P_1922_E = new c_1514_x(l, i1, j1);
        try {
            this.v_4262_N = W_2163_m.valueOf(nbt.M_588_G("rotation"));
        }
        catch (IllegalArgumentException illegalargumentexception2) {
            this.v_4262_N = W_2163_m.n_1700_B;
        }
        try {
            this.u_1723_Y = q_4099_E.valueOf(nbt.M_588_G("mirror"));
        }
        catch (IllegalArgumentException illegalargumentexception1) {
            this.u_1723_Y = q_4099_E.n_1700_B;
        }
        try {
            this.w_1484_f = M_3212_T.valueOf(nbt.M_588_G("mode"));
        }
        catch (IllegalArgumentException illegalargumentexception) {
            this.w_1484_f = M_3212_T.G_564_y;
        }
        this.t_148_a = nbt.t_1786_h("ignoreEntities");
        this.s_956_w = nbt.t_1786_h("powered");
        this.h_1847_R = nbt.t_1786_h("showair");
        this.Q_4569_t = nbt.t_1786_h("showboundingbox");
        this.M_182_A = nbt.P_1922_E("integrity") ? nbt.s_956_w("integrity") : 1.0f;
        this.t_1786_h = nbt.t_148_a("seed");
        this.A_4115_X();
    }

    private void A_4115_X() {
        c_1514_x blockpos;
        K_4074_S blockstate;
        if (this.u_2550_I != null && (blockstate = this.u_2550_I.getBlockState(blockpos = this.x_607_J())).n_1700_B(a_3742_W.l_14_c)) {
            this.u_2550_I.n_1700_B(blockpos, (K_4074_S)blockstate.n_1700_B(StructureBlock.P_4830_p, this.w_1484_f), 2);
        }
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket G_() {
        return new ClientboundBlockEntityDataPacket(this.M_588_G, 7, this.H_());
    }

    @Override
    public U_2912_j H_() {
        return this.n_1700_B(new U_2912_j());
    }

    public boolean n_1700_B(a_3913_L player) {
        if (!player.ModuleManager()) {
            return false;
        }
        if (player.Z_759_W().Y_259_p) {
            player.n_1700_B(this);
        }
        return true;
    }

    public String P_1922_E() {
        return this.n_1700_B == null ? "" : this.n_1700_B.toString();
    }

    public String v_4262_N() {
        return this.n_1700_B == null ? "" : this.n_1700_B.J_1907_R();
    }

    public boolean w_1484_f() {
        return this.n_1700_B != null;
    }

    public void n_1700_B(@Nullable String nameIn) {
        this.n_1700_B(H_1468_N.J_1907_R(nameIn) ? null : g_2336_b.J_1907_R(nameIn));
    }

    public void n_1700_B(@Nullable g_2336_b p_210163_1_) {
        this.n_1700_B = p_210163_1_;
    }

    public void n_1700_B(r_4811_B p_189720_1_) {
        this.J_1907_R = p_189720_1_.O_1309_Q().getString();
    }

    public c_1514_x s_956_w() {
        return this.G_564_y;
    }

    public void n_1700_B(c_1514_x posIn) {
        this.G_564_y = posIn;
    }

    public c_1514_x u_2550_I() {
        return this.P_1922_E;
    }

    public void J_1907_R(c_1514_x sizeIn) {
        this.P_1922_E = sizeIn;
    }

    public q_4099_E M_588_G() {
        return this.u_1723_Y;
    }

    public void n_1700_B(q_4099_E mirrorIn) {
        this.u_1723_Y = mirrorIn;
    }

    public W_2163_m P_4830_p() {
        return this.v_4262_N;
    }

    public void n_1700_B(W_2163_m rotationIn) {
        this.v_4262_N = rotationIn;
    }

    public String h_1847_R() {
        return this.R_4764_Y;
    }

    public void J_1907_R(String metadataIn) {
        this.R_4764_Y = metadataIn;
    }

    public M_3212_T Q_4569_t() {
        return this.w_1484_f;
    }

    public void n_1700_B(M_3212_T modeIn) {
        this.w_1484_f = modeIn;
        K_4074_S blockstate = this.u_2550_I.getBlockState(this.x_607_J());
        if (blockstate.n_1700_B(a_3742_W.l_14_c)) {
            this.u_2550_I.n_1700_B(this.x_607_J(), (K_4074_S)blockstate.n_1700_B(StructureBlock.P_4830_p, modeIn), 2);
        }
    }

    public void t_1786_h() {
        switch (this.Q_4569_t()) {
            case n_1700_B: {
                this.n_1700_B(M_3212_T.J_1907_R);
                break;
            }
            case J_1907_R: {
                this.n_1700_B(M_3212_T.R_4764_Y);
                break;
            }
            case R_4764_Y: {
                this.n_1700_B(M_3212_T.G_564_y);
                break;
            }
            case G_564_y: {
                this.n_1700_B(M_3212_T.n_1700_B);
            }
        }
    }

    public boolean multiplayerClientSuggestionProvider() {
        return this.t_148_a;
    }

    public void n_1700_B(boolean ignoreEntitiesIn) {
        this.t_148_a = ignoreEntitiesIn;
    }

    public float w_1457_N() {
        return this.M_182_A;
    }

    public void n_1700_B(float integrityIn) {
        this.M_182_A = integrityIn;
    }

    public long Y_601_j() {
        return this.t_1786_h;
    }

    public void n_1700_B(long seedIn) {
        this.t_1786_h = seedIn;
    }

    public boolean Y_259_p() {
        c_1514_x blockpos2;
        if (this.w_1484_f != M_3212_T.n_1700_B) {
            return false;
        }
        c_1514_x blockpos = this.x_607_J();
        int i = 80;
        c_1514_x blockpos1 = new c_1514_x(blockpos.getX() - 80, 0, blockpos.getZ() - 80);
        List<j_2644_e> list = this.n_1700_B(blockpos1, blockpos2 = new c_1514_x(blockpos.getX() + 80, 255, blockpos.getZ() + 80));
        List<j_2644_e> list1 = this.n_1700_B(list);
        if (list1.size() < 1) {
            return false;
        }
        BoundingBox mutableboundingbox = this.n_1700_B(blockpos, list1);
        if (mutableboundingbox.G_564_y - mutableboundingbox.n_1700_B > 1 && mutableboundingbox.P_1922_E - mutableboundingbox.J_1907_R > 1 && mutableboundingbox.u_1723_Y - mutableboundingbox.R_4764_Y > 1) {
            this.G_564_y = new c_1514_x(mutableboundingbox.n_1700_B - blockpos.getX() + 1, mutableboundingbox.J_1907_R - blockpos.getY() + 1, mutableboundingbox.R_4764_Y - blockpos.getZ() + 1);
            this.P_1922_E = new c_1514_x(mutableboundingbox.G_564_y - mutableboundingbox.n_1700_B - 1, mutableboundingbox.P_1922_E - mutableboundingbox.J_1907_R - 1, mutableboundingbox.u_1723_Y - mutableboundingbox.R_4764_Y - 1);
            this.J_1907_R();
            K_4074_S blockstate = this.u_2550_I.getBlockState(blockpos);
            this.u_2550_I.n_1700_B(blockpos, blockstate, blockstate, 3);
            return true;
        }
        return false;
    }

    private List<j_2644_e> n_1700_B(List<j_2644_e> p_184415_1_) {
        Predicate<j_2644_e> predicate = p_200665_1_ -> p_200665_1_.w_1484_f == M_3212_T.R_4764_Y && Objects.equals(this.n_1700_B, p_200665_1_.n_1700_B);
        return p_184415_1_.stream().filter(predicate).collect(Collectors.toList());
    }

    private List<j_2644_e> n_1700_B(c_1514_x p_184418_1_, c_1514_x p_184418_2_) {
        ArrayList list = Lists.newArrayList();
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(p_184418_1_, p_184418_2_)) {
            i_2154_H tileentity;
            K_4074_S blockstate = this.u_2550_I.getBlockState(blockpos);
            if (!blockstate.n_1700_B(a_3742_W.l_14_c) || (tileentity = this.u_2550_I.getTileEntity(blockpos)) == null || !(tileentity instanceof j_2644_e)) continue;
            list.add((j_2644_e)tileentity);
        }
        return list;
    }

    private BoundingBox n_1700_B(c_1514_x p_184416_1_, List<j_2644_e> p_184416_2_) {
        BoundingBox mutableboundingbox;
        if (p_184416_2_.size() > 1) {
            c_1514_x blockpos = p_184416_2_.get(0).x_607_J();
            mutableboundingbox = new BoundingBox(blockpos, blockpos);
        } else {
            mutableboundingbox = new BoundingBox(p_184416_1_, p_184416_1_);
        }
        for (j_2644_e structureblocktileentity : p_184416_2_) {
            c_1514_x blockpos1 = structureblocktileentity.x_607_J();
            if (blockpos1.getX() < mutableboundingbox.n_1700_B) {
                mutableboundingbox.n_1700_B = blockpos1.getX();
            } else if (blockpos1.getX() > mutableboundingbox.G_564_y) {
                mutableboundingbox.G_564_y = blockpos1.getX();
            }
            if (blockpos1.getY() < mutableboundingbox.J_1907_R) {
                mutableboundingbox.J_1907_R = blockpos1.getY();
            } else if (blockpos1.getY() > mutableboundingbox.P_1922_E) {
                mutableboundingbox.P_1922_E = blockpos1.getY();
            }
            if (blockpos1.getZ() < mutableboundingbox.R_4764_Y) {
                mutableboundingbox.R_4764_Y = blockpos1.getZ();
                continue;
            }
            if (blockpos1.getZ() <= mutableboundingbox.u_1723_Y) continue;
            mutableboundingbox.u_1723_Y = blockpos1.getZ();
        }
        return mutableboundingbox;
    }

    public boolean Q_2552_b() {
        return this.J_1907_R(true);
    }

    public boolean J_1907_R(boolean writeToDisk) {
        if (this.w_1484_f == M_3212_T.n_1700_B && !this.u_2550_I.Y_259_p && this.n_1700_B != null) {
            a_2886_t template;
            c_1514_x blockpos = this.x_607_J().add(this.G_564_y);
            e_3591_l serverworld = (e_3591_l)this.u_2550_I;
            b_2085_h templatemanager = serverworld.O_508_d();
            try {
                template = templatemanager.n_1700_B(this.n_1700_B);
            }
            catch (s_3109_F resourcelocationexception1) {
                return false;
            }
            template.n_1700_B(this.u_2550_I, blockpos, this.P_1922_E, !this.t_148_a, a_3742_W.PearlLogger);
            template.n_1700_B(this.J_1907_R);
            if (writeToDisk) {
                try {
                    return templatemanager.R_4764_Y(this.n_1700_B);
                }
                catch (s_3109_F resourcelocationexception) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean n_1700_B(e_3591_l p_242687_1_) {
        return this.n_1700_B(p_242687_1_, true);
    }

    private static Random J_1907_R(long p_214074_0_) {
        return p_214074_0_ == 0L ? new Random(j_3341_s.J_1907_R()) : new Random(p_214074_0_);
    }

    public boolean n_1700_B(e_3591_l p_242688_1_, boolean p_242688_2_) {
        if (this.w_1484_f == M_3212_T.J_1907_R && this.n_1700_B != null) {
            a_2886_t template;
            b_2085_h templatemanager = p_242688_1_.O_508_d();
            try {
                template = templatemanager.J_1907_R(this.n_1700_B);
            }
            catch (s_3109_F resourcelocationexception) {
                return false;
            }
            return template == null ? false : this.n_1700_B(p_242688_1_, p_242688_2_, template);
        }
        return false;
    }

    public boolean n_1700_B(e_3591_l p_242689_1_, boolean p_242689_2_, a_2886_t p_242689_3_) {
        c_1514_x blockpos1;
        boolean flag;
        c_1514_x blockpos = this.x_607_J();
        if (!H_1468_N.J_1907_R(p_242689_3_.J_1907_R())) {
            this.J_1907_R = p_242689_3_.J_1907_R();
        }
        if (!(flag = this.P_1922_E.equals(blockpos1 = p_242689_3_.n_1700_B()))) {
            this.P_1922_E = blockpos1;
            this.J_1907_R();
            K_4074_S blockstate = p_242689_1_.getBlockState(blockpos);
            p_242689_1_.n_1700_B(blockpos, blockstate, blockstate, 3);
        }
        if (p_242689_2_ && !flag) {
            return false;
        }
        w_1748_S placementsettings = new w_1748_S().n_1700_B(this.u_1723_Y).n_1700_B(this.v_4262_N).n_1700_B(this.t_148_a).n_1700_B((Y_1387_d)null);
        if (this.M_182_A < 1.0f) {
            placementsettings.J_1907_R().n_1700_B(new d_862_x(u_530_F.n_1700_B(this.M_182_A, 0.0f, 1.0f))).n_1700_B(j_2644_e.J_1907_R(this.t_1786_h));
        }
        c_1514_x blockpos2 = blockpos.add(this.G_564_y);
        p_242689_3_.n_1700_B(p_242689_1_, blockpos2, placementsettings, j_2644_e.J_1907_R(this.t_1786_h));
        return true;
    }

    public void C_2741_M() {
        if (this.n_1700_B != null) {
            e_3591_l serverworld = (e_3591_l)this.u_2550_I;
            b_2085_h templatemanager = serverworld.O_508_d();
            templatemanager.G_564_y(this.n_1700_B);
        }
    }

    public boolean k_2293_S() {
        if (this.w_1484_f == M_3212_T.J_1907_R && !this.u_2550_I.Y_259_p && this.n_1700_B != null) {
            e_3591_l serverworld = (e_3591_l)this.u_2550_I;
            b_2085_h templatemanager = serverworld.O_508_d();
            try {
                return templatemanager.J_1907_R(this.n_1700_B) != null;
            }
            catch (s_3109_F resourcelocationexception) {
                return false;
            }
        }
        return false;
    }

    public boolean q_2307_F() {
        return this.s_956_w;
    }

    public void R_4764_Y(boolean poweredIn) {
        this.s_956_w = poweredIn;
    }

    public boolean Z_875_P() {
        return this.h_1847_R;
    }

    public void G_564_y(boolean showAirIn) {
        this.h_1847_R = showAirIn;
    }

    public boolean H_2857_Y() {
        return this.Q_4569_t;
    }

    public void P_1922_E(boolean showBoundingBoxIn) {
        this.Q_4569_t = showBoundingBoxIn;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.j_2644_e$n_1700_B.n_1700_B();
        }
    }
}



