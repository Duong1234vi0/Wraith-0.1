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
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.E_2181_N;
import lightning.product.E_4700_p;
import lightning.product.PoolElementStructurePiece;
import lightning.product.J_3017_d;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.U_2912_j;
import lightning.product.W_2163_m;
import lightning.product.a_2886_t;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.e_3109_Q;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.JigsawBlock;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.BlockEntityType;
import lightning.product.z_1753_f;

public class JigsawBlockEntity
extends i_2154_H {
    private g_2336_b n_1700_B = new g_2336_b("empty");
    private g_2336_b J_1907_R = new g_2336_b("empty");
    private g_2336_b R_4764_Y = new g_2336_b("empty");
    private n_1700_B G_564_y = lightning.product.JigsawBlockEntity$n_1700_B.n_1700_B;
    private String P_1922_E = "minecraft:air";

    public JigsawBlockEntity(BlockEntityType<?> type) {
        super(type);
    }

    public JigsawBlockEntity() {
        this(BlockEntityType.t_4043_B);
    }

    public g_2336_b P_1922_E() {
        return this.n_1700_B;
    }

    public g_2336_b v_4262_N() {
        return this.J_1907_R;
    }

    public g_2336_b w_1484_f() {
        return this.R_4764_Y;
    }

    public String s_956_w() {
        return this.P_1922_E;
    }

    public n_1700_B u_2550_I() {
        return this.G_564_y;
    }

    public void n_1700_B(g_2336_b p_235664_1_) {
        this.n_1700_B = p_235664_1_;
    }

    public void J_1907_R(g_2336_b p_235666_1_) {
        this.J_1907_R = p_235666_1_;
    }

    public void R_4764_Y(g_2336_b p_235667_1_) {
        this.R_4764_Y = p_235667_1_;
    }

    public void n_1700_B(String blockName) {
        this.P_1922_E = blockName;
    }

    public void n_1700_B(n_1700_B p_235662_1_) {
        this.G_564_y = p_235662_1_;
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("name", this.n_1700_B.toString());
        compound.n_1700_B("target", this.J_1907_R.toString());
        compound.n_1700_B("pool", this.R_4764_Y.toString());
        compound.n_1700_B("final_state", this.P_1922_E);
        compound.n_1700_B("joint", this.G_564_y.n_1700_B());
        return compound;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.n_1700_B = new g_2336_b(nbt.M_588_G("name"));
        this.J_1907_R = new g_2336_b(nbt.M_588_G("target"));
        this.R_4764_Y = new g_2336_b(nbt.M_588_G("pool"));
        this.P_1922_E = nbt.M_588_G("final_state");
        this.G_564_y = lightning.product.JigsawBlockEntity$n_1700_B.n_1700_B(nbt.M_588_G("joint")).orElseGet(() -> JigsawBlock.w_1484_f(state).h_1847_R().G_564_y() ? lightning.product.JigsawBlockEntity$n_1700_B.J_1907_R : lightning.product.JigsawBlockEntity$n_1700_B.n_1700_B);
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket G_() {
        return new ClientboundBlockEntityDataPacket(this.M_588_G, 12, this.H_());
    }

    @Override
    public U_2912_j H_() {
        return this.n_1700_B(new U_2912_j());
    }

    public void n_1700_B(e_3591_l p_235665_1_, int p_235665_2_, boolean p_235665_3_) {
        z_1753_f chunkgenerator = p_235665_1_.Y_259_p().t_148_a();
        b_2085_h templatemanager = p_235665_1_.O_508_d();
        J_3017_d structuremanager = p_235665_1_.R_4764_Y();
        Random random = p_235665_1_.e_4240_b();
        c_1514_x blockpos = this.x_607_J();
        ArrayList list = Lists.newArrayList();
        a_2886_t template = new a_2886_t();
        template.n_1700_B(p_235665_1_, blockpos, new c_1514_x(1, 1, 1), false, null);
        e_3109_Q jigsawpiece = new e_3109_Q(template);
        PoolElementStructurePiece abstractvillagepiece = new PoolElementStructurePiece(templatemanager, jigsawpiece, blockpos, 1, W_2163_m.n_1700_B, new BoundingBox(blockpos, blockpos));
        E_2181_N.n_1700_B(p_235665_1_.t_1786_h(), abstractvillagepiece, p_235665_2_, PoolElementStructurePiece::new, chunkgenerator, templatemanager, list, random);
        for (PoolElementStructurePiece abstractvillagepiece1 : list) {
            abstractvillagepiece1.n_1700_B((WorldGenLevel)p_235665_1_, structuremanager, chunkgenerator, random, BoundingBox.J_1907_R(), blockpos, p_235665_3_);
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B>
    implements E_4700_p {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("rollable");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("aligned");
        private final String R_4764_Y;
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String p_i231862_3_) {
            this.R_4764_Y = p_i231862_3_;
        }

        @Override
        public String n_1700_B() {
            return this.R_4764_Y;
        }

        public static Optional<n_1700_B> n_1700_B(String p_235673_0_) {
            return Arrays.stream(lightning.product.JigsawBlockEntity$n_1700_B.values()).filter(p_235674_1_ -> p_235674_1_.n_1700_B().equals(p_235673_0_)).findFirst();
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            G_564_y = lightning.product.JigsawBlockEntity$n_1700_B.J_1907_R();
        }
    }
}


