/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders;

import java.util.Iterator;
import lightning.product.B_1647_r;
import lightning.product.c_1514_x;
import lightning.product.z_4547_I;
import net.optifine.BlockPosM;
import net.optifine.shaders.Iterator3d;

public class IteratorRenderChunks
implements Iterator<z_4547_I.n_1700_B> {
    private B_1647_r viewFrustum;
    private Iterator3d Iterator3d;
    private BlockPosM posBlock = new BlockPosM(0, 0, 0);

    public IteratorRenderChunks(B_1647_r viewFrustum, c_1514_x posStart, c_1514_x posEnd, int width, int height) {
        this.viewFrustum = viewFrustum;
        this.Iterator3d = new Iterator3d(posStart, posEnd, width, height);
    }

    @Override
    public boolean hasNext() {
        return this.Iterator3d.hasNext();
    }

    @Override
    public z_4547_I.n_1700_B next() {
        c_1514_x blockpos = this.Iterator3d.next();
        this.posBlock.setXyz(blockpos.getX() << 4, blockpos.getY() << 4, blockpos.getZ() << 4);
        return this.viewFrustum.n_1700_B(this.posBlock);
    }

    @Override
    public void remove() {
        throw new RuntimeException("Not implemented");
    }
}

