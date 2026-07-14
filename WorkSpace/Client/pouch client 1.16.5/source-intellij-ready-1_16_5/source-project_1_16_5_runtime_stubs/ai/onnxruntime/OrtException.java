package ai.onnxruntime;

public class OrtException extends Exception {
    public OrtException(String message) {
        super(message);
    }

    public OrtException(String message, Throwable cause) {
        super(message, cause);
    }
}
