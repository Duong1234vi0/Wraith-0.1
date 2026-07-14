/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ai.onnxruntime.OrtEnvironment
 *  ai.onnxruntime.OrtException
 *  ai.onnxruntime.OrtLoggingLevel
 */
package lightning.product;

import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtLoggingLevel;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class t_4864_b {
    private static final Logger n_1700_B = Logger.getLogger("Pouch/AI/ONNX");
    private static volatile OrtEnvironment J_1907_R;
    private static volatile boolean R_4764_Y;

    private t_4864_b() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static OrtEnvironment n_1700_B() {
        OrtEnvironment local = J_1907_R;
        if (local != null || R_4764_Y) {
            return local;
        }
        Class<t_4864_b> clazz = t_4864_b.class;
        synchronized (t_4864_b.class) {
            if (J_1907_R != null) {
                // ** MonitorExit[var1_1] (shouldn't be in output)
                return J_1907_R;
            }
            if (R_4764_Y) {
                // ** MonitorExit[var1_1] (shouldn't be in output)
                return null;
            }
            try {
                J_1907_R = OrtEnvironment.getEnvironment((OrtLoggingLevel)OrtLoggingLevel.ORT_LOGGING_LEVEL_ERROR, (String)"Pouch-Neuro");
                // ** MonitorExit[var1_1] (shouldn't be in output)
                return J_1907_R;
            }
            catch (Throwable t) {
                R_4764_Y = true;
                n_1700_B.log(Level.SEVERE, "Failed to initialize ONNX Runtime environment", t);
                // ** MonitorExit[var1_1] (shouldn't be in output)
                return null;
            }
        }
    }

    public static boolean J_1907_R() {
        return t_4864_b.n_1700_B() != null;
    }

    public static OrtEnvironment R_4764_Y() throws OrtException {
        OrtEnvironment local = t_4864_b.n_1700_B();
        if (local == null) {
            throw new OrtException("ONNX Runtime environment is not available");
        }
        return local;
    }
}

