package ai.onnxruntime;

import java.nio.FloatBuffer;

public class OnnxTensor implements AutoCloseable {
    public static OnnxTensor createTensor(OrtEnvironment env, Object value) throws OrtException {
        throw new OrtException("ONNX Runtime is not available");
    }

    public static OnnxTensor createTensor(OrtEnvironment env, FloatBuffer buffer, long[] shape) throws OrtException {
        throw new OrtException("ONNX Runtime is not available");
    }

    public Object getValue() throws OrtException {
        throw new OrtException("ONNX Runtime is not available");
    }

    @Override
    public void close() {
    }
}
