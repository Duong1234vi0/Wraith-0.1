package ai.onnxruntime;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class OrtSession implements AutoCloseable {
    public Result run(Map<String, ? extends Object> inputs) throws OrtException {
        throw new OrtException("ONNX Runtime is not available");
    }

    public Set<String> getInputNames() {
        return Collections.emptySet();
    }

    @Override
    public void close() {
    }

    public static class SessionOptions implements AutoCloseable {
        public enum OptLevel {
            NO_OPT,
            BASIC_OPT,
            EXTENDED_OPT,
            ALL_OPT
        }

        public void setOptimizationLevel(OptLevel level) {
        }

        @Override
        public void close() {
        }
    }

    public static class Result implements AutoCloseable {
        public Object get(int index) {
            return null;
        }

        @Override
        public void close() {
        }
    }
}
