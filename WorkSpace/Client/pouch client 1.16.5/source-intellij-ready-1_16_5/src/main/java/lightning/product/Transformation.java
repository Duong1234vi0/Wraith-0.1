/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.tuple.Triple
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import java.util.Objects;
import javax.annotation.Nullable;
import lightning.product.D_1098_v;
import lightning.product.M_1336_P;
import lightning.product.j_3341_s;
import lightning.product.o_1290_k;
import lightning.product.w_3785_E;
import org.apache.commons.lang3.tuple.Triple;

public final class Transformation {
    private final D_1098_v n_1700_B;
    private boolean J_1907_R;
    @Nullable
    private M_1336_P R_4764_Y;
    @Nullable
    private w_3785_E G_564_y;
    @Nullable
    private M_1336_P P_1922_E;
    @Nullable
    private w_3785_E u_1723_Y;
    private static final Transformation v_4262_N = j_3341_s.n_1700_B(() -> {
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B();
        Transformation transformationmatrix = new Transformation(matrix4f);
        transformationmatrix.G_564_y();
        return transformationmatrix;
    });

    public Transformation(@Nullable D_1098_v matrixIn) {
        this.n_1700_B = matrixIn == null ? Transformation.v_4262_N.n_1700_B : matrixIn;
    }

    public Transformation(@Nullable M_1336_P translationIn, @Nullable w_3785_E rotationLeftIn, @Nullable M_1336_P scaleIn, @Nullable w_3785_E rotationRightIn) {
        this.n_1700_B = Transformation.n_1700_B(translationIn, rotationLeftIn, scaleIn, rotationRightIn);
        this.R_4764_Y = translationIn != null ? translationIn : new M_1336_P();
        this.G_564_y = rotationLeftIn != null ? rotationLeftIn : w_3785_E.n_1700_B.v_4262_N();
        this.P_1922_E = scaleIn != null ? scaleIn : new M_1336_P(1.0f, 1.0f, 1.0f);
        this.u_1723_Y = rotationRightIn != null ? rotationRightIn : w_3785_E.n_1700_B.v_4262_N();
        this.J_1907_R = true;
    }

    public static Transformation n_1700_B() {
        return v_4262_N;
    }

    public Transformation n_1700_B(Transformation matrixIn) {
        D_1098_v matrix4f = this.R_4764_Y();
        matrix4f.n_1700_B(matrixIn.R_4764_Y());
        return new Transformation(matrix4f);
    }

    @Nullable
    public Transformation J_1907_R() {
        if (this == v_4262_N) {
            return this;
        }
        D_1098_v matrix4f = this.R_4764_Y();
        return matrix4f.P_1922_E() ? new Transformation(matrix4f) : null;
    }

    private void P_1922_E() {
        if (!this.J_1907_R) {
            Pair<o_1290_k, M_1336_P> pair = Transformation.n_1700_B(this.n_1700_B);
            Triple<w_3785_E, M_1336_P, w_3785_E> triple = ((o_1290_k)pair.getFirst()).J_1907_R();
            this.R_4764_Y = (M_1336_P)pair.getSecond();
            this.G_564_y = (w_3785_E)triple.getLeft();
            this.P_1922_E = (M_1336_P)triple.getMiddle();
            this.u_1723_Y = (w_3785_E)triple.getRight();
            this.J_1907_R = true;
        }
    }

    private static D_1098_v n_1700_B(@Nullable M_1336_P translation, @Nullable w_3785_E rotationLeft, @Nullable M_1336_P scale, @Nullable w_3785_E rotationRight) {
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B();
        if (rotationLeft != null) {
            matrix4f.n_1700_B(new D_1098_v(rotationLeft));
        }
        if (scale != null) {
            matrix4f.n_1700_B(D_1098_v.n_1700_B(scale.n_1700_B(), scale.J_1907_R(), scale.R_4764_Y()));
        }
        if (rotationRight != null) {
            matrix4f.n_1700_B(new D_1098_v(rotationRight));
        }
        if (translation != null) {
            matrix4f.G_564_y = translation.n_1700_B();
            matrix4f.w_1484_f = translation.J_1907_R();
            matrix4f.M_588_G = translation.R_4764_Y();
        }
        return matrix4f;
    }

    public static Pair<o_1290_k, M_1336_P> n_1700_B(D_1098_v matrixIn) {
        matrixIn.n_1700_B(1.0f / matrixIn.M_182_A);
        M_1336_P vector3f = new M_1336_P(matrixIn.G_564_y, matrixIn.w_1484_f, matrixIn.M_588_G);
        o_1290_k matrix3f = new o_1290_k(matrixIn);
        return Pair.of((Object)matrix3f, (Object)vector3f);
    }

    public D_1098_v R_4764_Y() {
        return this.n_1700_B.u_1723_Y();
    }

    public w_3785_E G_564_y() {
        this.P_1922_E();
        return this.G_564_y.v_4262_N();
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            Transformation transformationmatrix = (Transformation)p_equals_1_;
            return Objects.equals(this.n_1700_B, transformationmatrix.n_1700_B);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.n_1700_B);
    }
}


