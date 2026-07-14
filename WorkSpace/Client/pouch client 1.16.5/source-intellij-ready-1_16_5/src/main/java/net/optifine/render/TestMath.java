/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import java.util.Random;
import lightning.product.D_1098_v;
import lightning.product.M_1336_P;
import lightning.product.Z_2491_A;
import lightning.product.o_1290_k;
import lightning.product.w_3785_E;

public class TestMath {
    static Random random = new Random();

    public static void main(String[] args) {
        int i = 1000000;
        TestMath.dbg("Test math: " + i);
        for (int j = 0; j < 1000000; ++j) {
            TestMath.testMatrix4f_mulTranslate();
            TestMath.testMatrix4f_mulScale();
            TestMath.testMatrix4f_mulQuaternion();
            TestMath.testMatrix3f_mulQuaternion();
            TestMath.testVector4f_transform();
            TestMath.testVector3f_transform();
        }
        TestMath.dbg("Done");
    }

    private static void testMatrix4f_mulTranslate() {
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B(random);
        D_1098_v matrix4f1 = matrix4f.u_1723_Y();
        float f = random.nextFloat();
        float f1 = random.nextFloat();
        float f2 = random.nextFloat();
        matrix4f.n_1700_B(D_1098_v.J_1907_R(f, f1, f2));
        matrix4f1.R_4764_Y(f, f1, f2);
        if (!matrix4f1.equals(matrix4f)) {
            TestMath.dbg("*** DIFFERENT ***");
            TestMath.dbg(matrix4f.toString());
            TestMath.dbg(matrix4f1.toString());
        }
    }

    private static void testMatrix4f_mulScale() {
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B(random);
        D_1098_v matrix4f1 = matrix4f.u_1723_Y();
        float f = random.nextFloat();
        float f1 = random.nextFloat();
        float f2 = random.nextFloat();
        matrix4f.n_1700_B(D_1098_v.n_1700_B(f, f1, f2));
        matrix4f1.G_564_y(f, f1, f2);
        if (!matrix4f1.equals(matrix4f)) {
            TestMath.dbg("*** DIFFERENT ***");
            TestMath.dbg(matrix4f.toString());
            TestMath.dbg(matrix4f1.toString());
        }
    }

    private static void testMatrix4f_mulQuaternion() {
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B(random);
        D_1098_v matrix4f1 = matrix4f.u_1723_Y();
        w_3785_E quaternion = new w_3785_E(random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat());
        matrix4f.n_1700_B(new D_1098_v(quaternion));
        matrix4f1.n_1700_B(quaternion);
        if (!matrix4f1.equals(matrix4f)) {
            TestMath.dbg("*** DIFFERENT ***");
            TestMath.dbg(matrix4f.toString());
            TestMath.dbg(matrix4f1.toString());
        }
    }

    private static void testMatrix3f_mulQuaternion() {
        o_1290_k matrix3f = new o_1290_k();
        matrix3f.n_1700_B(random);
        o_1290_k matrix3f1 = matrix3f.u_1723_Y();
        w_3785_E quaternion = new w_3785_E(random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat());
        matrix3f.J_1907_R(new o_1290_k(quaternion));
        matrix3f1.n_1700_B(quaternion);
        if (!matrix3f1.equals(matrix3f)) {
            TestMath.dbg("*** DIFFERENT ***");
            TestMath.dbg(matrix3f.toString());
            TestMath.dbg(matrix3f1.toString());
        }
    }

    private static void testVector3f_transform() {
        M_1336_P vector3f = new M_1336_P(random.nextFloat(), random.nextFloat(), random.nextFloat());
        M_1336_P vector3f1 = vector3f.P_1922_E();
        o_1290_k matrix3f = new o_1290_k();
        matrix3f.n_1700_B(random);
        vector3f.n_1700_B(matrix3f);
        float f = matrix3f.J_1907_R(vector3f1.n_1700_B(), vector3f1.J_1907_R(), vector3f1.R_4764_Y());
        float f1 = matrix3f.R_4764_Y(vector3f1.n_1700_B(), vector3f1.J_1907_R(), vector3f1.R_4764_Y());
        float f2 = matrix3f.G_564_y(vector3f1.n_1700_B(), vector3f1.J_1907_R(), vector3f1.R_4764_Y());
        vector3f1 = new M_1336_P(f, f1, f2);
        if (!vector3f1.equals(vector3f)) {
            TestMath.dbg("*** DIFFERENT ***");
            TestMath.dbg(vector3f.toString());
            TestMath.dbg(vector3f1.toString());
        }
    }

    private static void testVector4f_transform() {
        Z_2491_A vector4f = new Z_2491_A(random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat());
        Z_2491_A vector4f1 = new Z_2491_A(vector4f.n_1700_B(), vector4f.J_1907_R(), vector4f.R_4764_Y(), vector4f.G_564_y());
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B(random);
        vector4f.n_1700_B(matrix4f);
        float f = matrix4f.J_1907_R(vector4f1.n_1700_B(), vector4f1.J_1907_R(), vector4f1.R_4764_Y(), vector4f1.G_564_y());
        float f1 = matrix4f.R_4764_Y(vector4f1.n_1700_B(), vector4f1.J_1907_R(), vector4f1.R_4764_Y(), vector4f1.G_564_y());
        float f2 = matrix4f.G_564_y(vector4f1.n_1700_B(), vector4f1.J_1907_R(), vector4f1.R_4764_Y(), vector4f1.G_564_y());
        float f3 = matrix4f.P_1922_E(vector4f1.n_1700_B(), vector4f1.J_1907_R(), vector4f1.R_4764_Y(), vector4f1.G_564_y());
        vector4f1 = new Z_2491_A(f, f1, f2, f3);
        if (!vector4f1.equals(vector4f)) {
            TestMath.dbg("*** DIFFERENT ***");
            TestMath.dbg(vector4f.toString());
            TestMath.dbg(vector4f1.toString());
        }
    }

    private static void dbg(String str) {
        System.out.println(str);
    }
}

