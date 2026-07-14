/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.AbstractIterator
 */
package net.optifine;

import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.u_530_F;

public class BlockPosM
extends c_1514_x {
    private int mx;
    private int my;
    private int mz;
    private int level;
    private BlockPosM[] facings;
    private boolean needsUpdate;

    public BlockPosM() {
        this(0, 0, 0, 0);
    }

    public BlockPosM(int x, int y, int z) {
        this(x, y, z, 0);
    }

    public BlockPosM(double xIn, double yIn, double zIn) {
        this(u_530_F.R_4764_Y(xIn), u_530_F.R_4764_Y(yIn), u_530_F.R_4764_Y(zIn));
    }

    public BlockPosM(int x, int y, int z, int level) {
        super(0, 0, 0);
        this.mx = x;
        this.my = y;
        this.mz = z;
        this.level = level;
    }

    @Override
    public int getX() {
        return this.mx;
    }

    @Override
    public int getY() {
        return this.my;
    }

    @Override
    public int getZ() {
        return this.mz;
    }

    public void setXyz(int x, int y, int z) {
        this.mx = x;
        this.my = y;
        this.mz = z;
        this.needsUpdate = true;
    }

    public void setXyz(double xIn, double yIn, double zIn) {
        this.setXyz(u_530_F.R_4764_Y(xIn), u_530_F.R_4764_Y(yIn), u_530_F.R_4764_Y(zIn));
    }

    @Override
    public c_1514_x offset(b_257_Y facing) {
        int i;
        BlockPosM blockposm;
        if (this.level <= 0) {
            return super.offset(facing, 1).toImmutable();
        }
        if (this.facings == null) {
            this.facings = new BlockPosM[b_257_Y.v_4262_N.length];
        }
        if (this.needsUpdate) {
            this.update();
        }
        if ((blockposm = this.facings[i = facing.R_4764_Y()]) == null) {
            int j = this.mx + facing.t_148_a();
            int k = this.my + facing.s_956_w();
            int l = this.mz + facing.u_2550_I();
            this.facings[i] = blockposm = new BlockPosM(j, k, l, this.level - 1);
        }
        return blockposm;
    }

    @Override
    public c_1514_x offset(b_257_Y facing, int n) {
        return n == 1 ? this.offset(facing) : super.offset(facing, n).toImmutable();
    }

    public void setPosOffset(c_1514_x pos, b_257_Y facing) {
        this.mx = pos.getX() + facing.t_148_a();
        this.my = pos.getY() + facing.s_956_w();
        this.mz = pos.getZ() + facing.u_2550_I();
    }

    public void setPosOffset(c_1514_x pos, b_257_Y facing, b_257_Y facing2) {
        this.mx = pos.getX() + facing.t_148_a() + facing2.t_148_a();
        this.my = pos.getY() + facing.s_956_w() + facing2.s_956_w();
        this.mz = pos.getZ() + facing.u_2550_I() + facing2.u_2550_I();
    }

    private void update() {
        for (int i = 0; i < 6; ++i) {
            BlockPosM blockposm = this.facings[i];
            if (blockposm == null) continue;
            b_257_Y direction = b_257_Y.v_4262_N[i];
            int j = this.mx + direction.t_148_a();
            int k = this.my + direction.s_956_w();
            int l = this.mz + direction.u_2550_I();
            blockposm.setXyz(j, k, l);
        }
        this.needsUpdate = false;
    }

    @Override
    public c_1514_x toImmutable() {
        return new c_1514_x(this.mx, this.my, this.mz);
    }

    public static Iterable getAllInBoxMutable(c_1514_x from, c_1514_x to) {
        final c_1514_x blockpos = new c_1514_x(Math.min(from.getX(), to.getX()), Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()));
        final c_1514_x blockpos1 = new c_1514_x(Math.max(from.getX(), to.getX()), Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));
        return new Iterable(){

            public Iterator iterator() {
                return new AbstractIterator(){
                    private BlockPosM posM = null;

                    protected Object computeNext() {
                        if (this.posM == null) {
                            this.posM = new BlockPosM(blockpos.getX(), blockpos.getY(), blockpos.getZ(), 3);
                            return this.posM;
                        }
                        if (this.posM.equals(blockpos1)) {
                            return (BlockPosM)this.endOfData();
                        }
                        int i = this.posM.getX();
                        int j = this.posM.getY();
                        int k = this.posM.getZ();
                        if (i < blockpos1.getX()) {
                            ++i;
                        } else if (k < blockpos1.getZ()) {
                            i = blockpos.getX();
                            ++k;
                        } else if (j < blockpos1.getY()) {
                            i = blockpos.getX();
                            k = blockpos.getZ();
                            ++j;
                        }
                        this.posM.setXyz(i, j, k);
                        return this.posM;
                    }
                };
            }
        };
    }
}

