/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.E_3771_B;
import lightning.product.BoundingBox;
import lightning.product.U_2912_j;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.LevelAccessor;
import lightning.product.z_2963_s;

public abstract class ScatteredFeaturePiece
extends E_3771_B {
    protected final int n_1700_B;
    protected final int J_1907_R;
    protected final int R_4764_Y;
    protected int G_564_y = -1;

    protected ScatteredFeaturePiece(StructurePieceType structurePieceTypeIn, Random rand, int xIn, int yIn, int zIn, int widthIn, int heightIn, int depthIn) {
        super(structurePieceTypeIn, 0);
        this.n_1700_B = widthIn;
        this.J_1907_R = heightIn;
        this.R_4764_Y = depthIn;
        this.n_1700_B(b_257_Y.R_4764_Y.n_1700_B.n_1700_B(rand));
        this.h_1847_R = this.t_148_a().h_1847_R() == b_257_Y.n_1700_B.R_4764_Y ? new BoundingBox(xIn, yIn, zIn, xIn + widthIn - 1, yIn + heightIn - 1, zIn + depthIn - 1) : new BoundingBox(xIn, yIn, zIn, xIn + depthIn - 1, yIn + heightIn - 1, zIn + widthIn - 1);
    }

    protected ScatteredFeaturePiece(StructurePieceType structurePieceTypeIn, U_2912_j nbt) {
        super(structurePieceTypeIn, nbt);
        this.n_1700_B = nbt.w_1484_f("Width");
        this.J_1907_R = nbt.w_1484_f("Height");
        this.R_4764_Y = nbt.w_1484_f("Depth");
        this.G_564_y = nbt.w_1484_f("HPos");
    }

    @Override
    protected void n_1700_B(U_2912_j tagCompound) {
        tagCompound.J_1907_R("Width", this.n_1700_B);
        tagCompound.J_1907_R("Height", this.J_1907_R);
        tagCompound.J_1907_R("Depth", this.R_4764_Y);
        tagCompound.J_1907_R("HPos", this.G_564_y);
    }

    protected boolean n_1700_B(LevelAccessor worldIn, BoundingBox boundsIn, int heightIn) {
        if (this.G_564_y >= 0) {
            return true;
        }
        int i = 0;
        int j = 0;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int k = this.h_1847_R.R_4764_Y; k <= this.h_1847_R.u_1723_Y; ++k) {
            for (int l = this.h_1847_R.n_1700_B; l <= this.h_1847_R.G_564_y; ++l) {
                blockpos$mutable.n_1700_B(l, 64, k);
                if (!boundsIn.J_1907_R(blockpos$mutable)) continue;
                i += worldIn.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, (c_1514_x)blockpos$mutable).getY();
                ++j;
            }
        }
        if (j == 0) {
            return false;
        }
        this.G_564_y = i / j;
        this.h_1847_R.n_1700_B(0, this.G_564_y - this.h_1847_R.J_1907_R + heightIn, 0);
        return true;
    }
}


