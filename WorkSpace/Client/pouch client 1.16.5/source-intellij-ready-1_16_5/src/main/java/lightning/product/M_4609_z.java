/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ai.onnxruntime.OnnxTensor
 *  ai.onnxruntime.OrtEnvironment
 *  ai.onnxruntime.OrtException
 *  ai.onnxruntime.OrtSession
 *  ai.onnxruntime.OrtSession$Result
 *  ai.onnxruntime.OrtSession$SessionOptions
 *  ai.onnxruntime.OrtSession$SessionOptions$OptLevel
 */
package lightning.product;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import java.io.Closeable;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Path;
import java.util.Collections;
import lightning.product.t_4864_b;

public final class M_4609_z
implements Closeable {
    private final String n_1700_B;
    private OrtSession J_1907_R;
    private String R_4764_Y;

    public M_4609_z(String name) {
        this.n_1700_B = name;
    }

    public String n_1700_B() {
        return this.n_1700_B;
    }

    public synchronized void n_1700_B(Path onnxFile) throws IOException {
        OrtEnvironment env;
        this.J_1907_R();
        try {
            env = t_4864_b.R_4764_Y();
        }
        catch (OrtException e) {
            throw new IOException("ONNX Runtime not available", e);
        }
        try {
            OrtSession.SessionOptions opts = new OrtSession.SessionOptions();
            opts.setIntraOpNumThreads(1);
            opts.setOptimizationLevel(OrtSession.SessionOptions.OptLevel.ALL_OPT);
            this.J_1907_R = env.createSession(onnxFile.toString(), opts);
            this.R_4764_Y = (String)this.J_1907_R.getInputNames().iterator().next();
        }
        catch (OrtException e) {
            throw new IOException("Failed to load ONNX model from " + String.valueOf(onnxFile), e);
        }
    }

    public synchronized float[] n_1700_B(float[] input) throws OrtException {
        if (this.J_1907_R == null) {
            throw new OrtException("Model not loaded");
        }
        OrtEnvironment env = t_4864_b.n_1700_B();
        if (env == null) {
            throw new OrtException("ONNX Runtime not available");
        }
        long[] shape = new long[]{1L, input.length};
        try (OnnxTensor tensor = OnnxTensor.createTensor((OrtEnvironment)env, (FloatBuffer)FloatBuffer.wrap(input), (long[])shape);){
            float[] fArray;
            block22: {
                OrtSession.Result result;
                block20: {
                    float[] fArray2;
                    block21: {
                        Object raw;
                        block18: {
                            float[] fArray3;
                            block19: {
                                result = this.J_1907_R.run(Collections.singletonMap(this.R_4764_Y, tensor));
                                try {
                                    raw = result.get(0).getValue();
                                    if (!(raw instanceof float[][])) break block18;
                                    float[][] arr = (float[][])raw;
                                    float[] fArray4 = fArray3 = arr.length > 0 ? arr[0] : new float[]{};
                                    if (result == null) break block19;
                                }
                                catch (Throwable throwable) {
                                    if (result != null) {
                                        try {
                                            result.close();
                                        }
                                        catch (Throwable throwable2) {
                                            throwable.addSuppressed(throwable2);
                                        }
                                    }
                                    throw throwable;
                                }
                                result.close();
                            }
                            return fArray3;
                        }
                        if (!(raw instanceof float[])) break block20;
                        fArray2 = (float[])raw;
                        if (result == null) break block21;
                        result.close();
                    }
                    return fArray2;
                }
                fArray = new float[]{};
                if (result == null) break block22;
                result.close();
            }
            return fArray;
        }
    }

    @Override
    public synchronized void close() throws IOException {
        this.J_1907_R();
    }

    private void J_1907_R() {
        if (this.J_1907_R != null) {
            try {
                this.J_1907_R.close();
            }
            catch (OrtException ortException) {
                // empty catch block
            }
            this.J_1907_R = null;
        }
    }
}

