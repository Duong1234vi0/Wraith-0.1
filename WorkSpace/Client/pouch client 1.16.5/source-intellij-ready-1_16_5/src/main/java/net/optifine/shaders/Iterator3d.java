/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders;

import java.util.Iterator;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import net.optifine.BlockPosM;
import net.optifine.shaders.IteratorAxis;

public class Iterator3d
implements Iterator<c_1514_x> {
    private IteratorAxis iteratorAxis;
    private BlockPosM blockPos = new BlockPosM(0, 0, 0);
    private int axis = 0;
    private int kX;
    private int kY;
    private int kZ;
    private static final int AXIS_X = 0;
    private static final int AXIS_Y = 1;
    private static final int AXIS_Z = 2;

    public Iterator3d(c_1514_x posStart, c_1514_x posEnd, int width, int height) {
        boolean flag = posStart.getX() > posEnd.getX();
        boolean flag1 = posStart.getY() > posEnd.getY();
        boolean flag2 = posStart.getZ() > posEnd.getZ();
        posStart = this.reverseCoord(posStart, flag, flag1, flag2);
        posEnd = this.reverseCoord(posEnd, flag, flag1, flag2);
        this.kX = flag ? -1 : 1;
        this.kY = flag1 ? -1 : 1;
        this.kZ = flag2 ? -1 : 1;
        e_2866_D vector3d = new e_2866_D(posEnd.getX() - posStart.getX(), posEnd.getY() - posStart.getY(), posEnd.getZ() - posStart.getZ());
        e_2866_D vector3d1 = vector3d.G_564_y();
        e_2866_D vector3d2 = new e_2866_D(1.0, 0.0, 0.0);
        double d0 = vector3d1.J_1907_R(vector3d2);
        double d1 = Math.abs(d0);
        e_2866_D vector3d3 = new e_2866_D(0.0, 1.0, 0.0);
        double d2 = vector3d1.J_1907_R(vector3d3);
        double d3 = Math.abs(d2);
        e_2866_D vector3d4 = new e_2866_D(0.0, 0.0, 1.0);
        double d4 = vector3d1.J_1907_R(vector3d4);
        double d5 = Math.abs(d4);
        if (d5 >= d3 && d5 >= d1) {
            this.axis = 2;
            c_1514_x blockpos3 = new c_1514_x(posStart.getZ(), posStart.getY() - width, posStart.getX() - height);
            c_1514_x blockpos5 = new c_1514_x(posEnd.getZ(), posStart.getY() + width + 1, posStart.getX() + height + 1);
            int k = posEnd.getZ() - posStart.getZ();
            double d9 = (double)(posEnd.getY() - posStart.getY()) / (1.0 * (double)k);
            double d11 = (double)(posEnd.getX() - posStart.getX()) / (1.0 * (double)k);
            this.iteratorAxis = new IteratorAxis(blockpos3, blockpos5, d9, d11);
        } else if (d3 >= d1 && d3 >= d5) {
            this.axis = 1;
            c_1514_x blockpos2 = new c_1514_x(posStart.getY(), posStart.getX() - width, posStart.getZ() - height);
            c_1514_x blockpos4 = new c_1514_x(posEnd.getY(), posStart.getX() + width + 1, posStart.getZ() + height + 1);
            int j = posEnd.getY() - posStart.getY();
            double d8 = (double)(posEnd.getX() - posStart.getX()) / (1.0 * (double)j);
            double d10 = (double)(posEnd.getZ() - posStart.getZ()) / (1.0 * (double)j);
            this.iteratorAxis = new IteratorAxis(blockpos2, blockpos4, d8, d10);
        } else {
            this.axis = 0;
            c_1514_x blockpos = new c_1514_x(posStart.getX(), posStart.getY() - width, posStart.getZ() - height);
            c_1514_x blockpos1 = new c_1514_x(posEnd.getX(), posStart.getY() + width + 1, posStart.getZ() + height + 1);
            int i = posEnd.getX() - posStart.getX();
            double d6 = (double)(posEnd.getY() - posStart.getY()) / (1.0 * (double)i);
            double d7 = (double)(posEnd.getZ() - posStart.getZ()) / (1.0 * (double)i);
            this.iteratorAxis = new IteratorAxis(blockpos, blockpos1, d6, d7);
        }
    }

    private c_1514_x reverseCoord(c_1514_x pos, boolean revX, boolean revY, boolean revZ) {
        if (revX) {
            pos = new c_1514_x(-pos.getX(), pos.getY(), pos.getZ());
        }
        if (revY) {
            pos = new c_1514_x(pos.getX(), -pos.getY(), pos.getZ());
        }
        if (revZ) {
            pos = new c_1514_x(pos.getX(), pos.getY(), -pos.getZ());
        }
        return pos;
    }

    @Override
    public boolean hasNext() {
        return this.iteratorAxis.hasNext();
    }

    @Override
    public c_1514_x next() {
        c_1514_x blockpos = this.iteratorAxis.next();
        switch (this.axis) {
            case 0: {
                this.blockPos.setXyz(blockpos.getX() * this.kX, blockpos.getY() * this.kY, blockpos.getZ() * this.kZ);
                return this.blockPos;
            }
            case 1: {
                this.blockPos.setXyz(blockpos.getY() * this.kX, blockpos.getX() * this.kY, blockpos.getZ() * this.kZ);
                return this.blockPos;
            }
            case 2: {
                this.blockPos.setXyz(blockpos.getZ() * this.kX, blockpos.getY() * this.kY, blockpos.getX() * this.kZ);
                return this.blockPos;
            }
        }
        this.blockPos.setXyz(blockpos.getX() * this.kX, blockpos.getY() * this.kY, blockpos.getZ() * this.kZ);
        return this.blockPos;
    }

    @Override
    public void remove() {
        throw new RuntimeException("Not supported");
    }

    public static void main(String[] args) {
        c_1514_x blockpos = new c_1514_x(10, 20, 30);
        c_1514_x blockpos1 = new c_1514_x(30, 40, 20);
        Iterator3d iterator3d = new Iterator3d(blockpos, blockpos1, 1, 1);
        while (iterator3d.hasNext()) {
            c_1514_x blockpos2 = iterator3d.next();
            System.out.println(String.valueOf(blockpos2));
        }
    }
}

