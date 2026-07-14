package ai.onnxruntime;

public class OrtEnvironment implements AutoCloseable {
    public static OrtEnvironment getEnvironment(OrtLoggingLevel level, String name) throws OrtException {
        throw new OrtException("ONNX Runtime is not bundled in this recovered source project");
    }

    public OrtSession createSession(String modelPath, OrtSession.SessionOptions options) throws OrtException {
        throw new OrtException("ONNX Runtime is not available");
    }

    public OrtSession createSession(byte[] modelBytes, OrtSession.SessionOptions options) throws OrtException {
        throw new OrtException("ONNX Runtime is not available");
    }

    @Override
    public void close() {
    }
}
