/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Random;
import lightning.product.E_3771_B;
import lightning.product.J_3017_d;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.M_3212_T;
import lightning.product.WorldGenLevel;
import lightning.product.U_2912_j;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.ServerLevelAccessor;
import lightning.product.f_71_T;
import lightning.product.w_1748_S;
import lightning.product.z_1753_f;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class q_1616_l
extends E_3771_B {
    private static final Logger G_564_y = LogManager.getLogger();
    protected a_2886_t n_1700_B;
    protected w_1748_S J_1907_R;
    protected c_1514_x R_4764_Y;

    public q_1616_l(StructurePieceType structurePieceTypeIn, int componentTypeIn) {
        super(structurePieceTypeIn, componentTypeIn);
    }

    public q_1616_l(StructurePieceType structurePieceTypeIn, U_2912_j nbt) {
        super(structurePieceTypeIn, nbt);
        this.R_4764_Y = new c_1514_x(nbt.w_1484_f("TPX"), nbt.w_1484_f("TPY"), nbt.w_1484_f("TPZ"));
    }

    protected void n_1700_B(a_2886_t templateIn, c_1514_x pos, w_1748_S settings) {
        this.n_1700_B = templateIn;
        this.n_1700_B(b_257_Y.R_4764_Y);
        this.R_4764_Y = pos;
        this.J_1907_R = settings;
        this.h_1847_R = templateIn.J_1907_R(settings, pos);
    }

    @Override
    protected void n_1700_B(U_2912_j tagCompound) {
        tagCompound.J_1907_R("TPX", this.R_4764_Y.getX());
        tagCompound.J_1907_R("TPY", this.R_4764_Y.getY());
        tagCompound.J_1907_R("TPZ", this.R_4764_Y.getZ());
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
        this.J_1907_R.n_1700_B(p_230383_5_);
        this.h_1847_R = this.n_1700_B.J_1907_R(this.J_1907_R, this.R_4764_Y);
        if (this.n_1700_B.n_1700_B(p_230383_1_, this.R_4764_Y, p_230383_7_, this.J_1907_R, p_230383_4_, 2)) {
            for (a_2886_t.J_1907_R template$blockinfo : this.n_1700_B.n_1700_B(this.R_4764_Y, this.J_1907_R, a_3742_W.l_14_c)) {
                M_3212_T structuremode;
                if (template$blockinfo.R_4764_Y == null || (structuremode = M_3212_T.valueOf(template$blockinfo.R_4764_Y.M_588_G("mode"))) != M_3212_T.G_564_y) continue;
                this.n_1700_B(template$blockinfo.R_4764_Y.M_588_G("metadata"), template$blockinfo.n_1700_B, p_230383_1_, p_230383_4_, p_230383_5_);
            }
            for (a_2886_t.J_1907_R template$blockinfo1 : this.n_1700_B.n_1700_B(this.R_4764_Y, this.J_1907_R, a_3742_W.q_2034_t)) {
                if (template$blockinfo1.R_4764_Y == null) continue;
                String s = template$blockinfo1.R_4764_Y.M_588_G("final_state");
                f_71_T blockstateparser = new f_71_T(new StringReader(s), false);
                K_4074_S blockstate = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
                try {
                    blockstateparser.n_1700_B(true);
                    K_4074_S blockstate1 = blockstateparser.J_1907_R();
                    if (blockstate1 != null) {
                        blockstate = blockstate1;
                    } else {
                        G_564_y.error("Error while parsing blockstate {} in jigsaw block @ {}", (Object)s, (Object)template$blockinfo1.n_1700_B);
                    }
                }
                catch (CommandSyntaxException commandsyntaxexception) {
                    G_564_y.error("Error while parsing blockstate {} in jigsaw block @ {}", (Object)s, (Object)template$blockinfo1.n_1700_B);
                }
                p_230383_1_.n_1700_B(template$blockinfo1.n_1700_B, blockstate, 3);
            }
        }
        return true;
    }

    protected abstract void n_1700_B(String var1, c_1514_x var2, ServerLevelAccessor var3, Random var4, BoundingBox var5);

    @Override
    public void n_1700_B(int x, int y, int z) {
        super.n_1700_B(x, y, z);
        this.R_4764_Y = this.R_4764_Y.add(x, y, z);
    }

    @Override
    public W_2163_m n_1700_B() {
        return this.J_1907_R.G_564_y();
    }
}


