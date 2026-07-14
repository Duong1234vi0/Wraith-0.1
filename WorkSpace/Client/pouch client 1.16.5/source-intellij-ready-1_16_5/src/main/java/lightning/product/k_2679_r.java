/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.EnumMap;
import java.util.function.Supplier;
import lightning.product.D_1098_v;
import lightning.product.M_1336_P;
import lightning.product.b_257_Y;
import lightning.product.j_3341_s;
import lightning.product.Transformation;
import lightning.product.w_3785_E;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class k_2679_r {
    private static final Logger R_4764_Y = LogManager.getLogger();
    public static final EnumMap<b_257_Y, Transformation> n_1700_B = j_3341_s.n_1700_B(Maps.newEnumMap(b_257_Y.class), localToGlobalMap -> {
        localToGlobalMap.put(b_257_Y.G_564_y, Transformation.n_1700_B());
        localToGlobalMap.put(b_257_Y.u_1723_Y, new Transformation(null, new w_3785_E(new M_1336_P(0.0f, 1.0f, 0.0f), 90.0f, true), null, null));
        localToGlobalMap.put(b_257_Y.P_1922_E, new Transformation(null, new w_3785_E(new M_1336_P(0.0f, 1.0f, 0.0f), -90.0f, true), null, null));
        localToGlobalMap.put(b_257_Y.R_4764_Y, new Transformation(null, new w_3785_E(new M_1336_P(0.0f, 1.0f, 0.0f), 180.0f, true), null, null));
        localToGlobalMap.put(b_257_Y.J_1907_R, new Transformation(null, new w_3785_E(new M_1336_P(1.0f, 0.0f, 0.0f), -90.0f, true), null, null));
        localToGlobalMap.put(b_257_Y.n_1700_B, new Transformation(null, new w_3785_E(new M_1336_P(1.0f, 0.0f, 0.0f), 90.0f, true), null, null));
    });
    public static final EnumMap<b_257_Y, Transformation> J_1907_R = j_3341_s.n_1700_B(Maps.newEnumMap(b_257_Y.class), globalToLocalMap -> {
        for (b_257_Y direction : b_257_Y.values()) {
            globalToLocalMap.put(direction, n_1700_B.get(direction).J_1907_R());
        }
    });

    public static Transformation n_1700_B(Transformation matrixIn) {
        D_1098_v matrix4f = D_1098_v.J_1907_R(0.5f, 0.5f, 0.5f);
        matrix4f.n_1700_B(matrixIn.R_4764_Y());
        matrix4f.n_1700_B(D_1098_v.J_1907_R(-0.5f, -0.5f, -0.5f));
        return new Transformation(matrix4f);
    }

    public static Transformation n_1700_B(Transformation matrixIn, b_257_Y directionIn, Supplier<String> warningIn) {
        b_257_Y direction = b_257_Y.n_1700_B(matrixIn.R_4764_Y(), directionIn);
        Transformation transformationmatrix = matrixIn.J_1907_R();
        if (transformationmatrix == null) {
            R_4764_Y.warn(warningIn.get());
            return new Transformation(null, null, new M_1336_P(0.0f, 0.0f, 0.0f), null);
        }
        Transformation transformationmatrix1 = J_1907_R.get(directionIn).n_1700_B(transformationmatrix).n_1700_B(n_1700_B.get(direction));
        return k_2679_r.n_1700_B(transformationmatrix1);
    }
}


