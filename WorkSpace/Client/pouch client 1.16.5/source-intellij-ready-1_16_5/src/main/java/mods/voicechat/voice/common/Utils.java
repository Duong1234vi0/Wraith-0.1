/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.common;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.P_3504_Q;
import lightning.product.u_530_F;
import mods.voicechat.Voicechat;

public class Utils {
    public static final int SAMPLE_RATE = 48000;
    public static final int FRAME_SIZE = 960;
    public static final int DEFAULT_MAX_PAYLOAD_SIZE = 1024;
    public static final double LOWEST_DB = -127.0;
    private static final float FLOAT_SHORT_SCALE = 32767.0f;
    private static final float FLOAT_CLIP = 32766.0f;
    private static final float FLOAT_SHORT_SCALING_FACTOR = 3.051851E-5f;

    public static double dbToLinear(double db) {
        return Math.pow(10.0, db / 20.0);
    }

    public static double linearToDb(double multiplier) {
        if (multiplier <= 0.0) {
            return -127.0;
        }
        return 20.0 * Math.log10(multiplier);
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    public static short[] bytesToShorts(byte[] bytes) {
        if (bytes.length % 2 != 0) {
            throw new IllegalArgumentException("Input bytes need to be divisible by 2");
        }
        short[] data = new short[bytes.length / 2];
        for (int i = 0; i < bytes.length; i += 2) {
            data[i / 2] = Utils.bytesToShort(bytes[i], bytes[i + 1]);
        }
        return data;
    }

    public static byte[] shortsToBytes(short[] shorts) {
        byte[] data = new byte[shorts.length * 2];
        for (int i = 0; i < shorts.length; ++i) {
            byte[] split = Utils.shortToBytes(shorts[i]);
            data[i * 2] = split[0];
            data[i * 2 + 1] = split[1];
        }
        return data;
    }

    public static float percentageToDB(float percentage) {
        return (float)(10.0 * Math.log(percentage));
    }

    public static short bytesToShort(byte b1, byte b2) {
        return (short)((b2 & 0xFF) << 8 | b1 & 0xFF);
    }

    public static byte[] shortToBytes(short s) {
        return new byte[]{(byte)(s & 0xFF), (byte)(s >> 8 & 0xFF)};
    }

    public static short[] floatsToShortsNormalized(float[] audioData) {
        short[] shortAudioData = new short[audioData.length];
        for (int i = 0; i < audioData.length; ++i) {
            shortAudioData[i] = (short)Math.max(Math.min(audioData[i] * 32767.0f, 32766.0f), -32767.0f);
        }
        return shortAudioData;
    }

    public static float[] shortsToFloatsNormalized(short[] audioData) {
        float[] floatAudioData = new float[audioData.length];
        for (int i = 0; i < audioData.length; ++i) {
            floatAudioData[i] = (float)audioData[i] * 3.051851E-5f;
        }
        return floatAudioData;
    }

    public static short[] floatsToShorts(float[] floats) {
        float max = -32768.0f;
        float min = 32767.0f;
        for (int i = 0; i < floats.length; ++i) {
            if (floats[i] > max) {
                max = floats[i];
            }
            if (!(floats[i] < min)) continue;
            min = floats[i];
        }
        float scale = Math.min(1.0f, 32766.0f / Math.max(Math.abs(max), Math.abs(min)));
        short[] shorts = new short[floats.length];
        for (int i = 0; i < floats.length; ++i) {
            shorts[i] = Float.valueOf(floats[i] * scale).shortValue();
        }
        return shorts;
    }

    public static float[] shortsToFloats(short[] shorts) {
        float[] floats = new float[shorts.length];
        for (int i = 0; i < shorts.length; ++i) {
            floats[i] = Short.valueOf(shorts[i]).floatValue();
        }
        return floats;
    }

    public static byte[] floatsToBytes(float[] floats) {
        byte[] bytes = new byte[floats.length * 2];
        for (int i = 0; i < floats.length; ++i) {
            short x = Float.valueOf(floats[i]).shortValue();
            bytes[i * 2] = (byte)(x & 0xFF);
            bytes[i * 2 + 1] = (byte)((x & 0xFF00) >> 8);
        }
        return bytes;
    }

    public static float[] bytesToFloats(byte[] bytes) {
        float[] floats = new float[bytes.length / 2];
        for (int i = 0; i < bytes.length / 2; ++i) {
            floats[i] = (bytes[i * 2 + 1] & 0x80) != 0 ? (float)(Short.MIN_VALUE + ((bytes[i * 2 + 1] & 0x7F) << 8) | bytes[i * 2] & 0xFF) : (float)(bytes[i * 2 + 1] << 8 & 0xFF00 | bytes[i * 2] & 0xFF);
        }
        return floats;
    }

    public static float normalizeAngle(float angle) {
        if ((angle %= 360.0f) <= -180.0f) {
            angle += 360.0f;
        } else if (angle > 180.0f) {
            angle -= 360.0f;
        }
        return angle;
    }

    public static float angle(P_3504_Q vec1, P_3504_Q vec2) {
        return (float)Math.toDegrees(Math.atan2(vec1.t_148_a * vec2.t_148_a + vec1.s_956_w * vec2.s_956_w, vec1.t_148_a * vec2.s_956_w - vec1.s_956_w * vec2.t_148_a));
    }

    private static double magnitude(P_3504_Q vec1) {
        return Math.sqrt(Math.pow(vec1.t_148_a, 2.0) + Math.pow(vec1.s_956_w, 2.0));
    }

    private static float multiply(P_3504_Q vec1, P_3504_Q vec2) {
        return vec1.t_148_a * vec2.t_148_a + vec1.s_956_w * vec2.s_956_w;
    }

    private static P_3504_Q rotate(P_3504_Q vec, float angle) {
        return new P_3504_Q(vec.t_148_a * u_530_F.J_1907_R(angle) - vec.s_956_w * u_530_F.n_1700_B(angle), vec.t_148_a * u_530_F.n_1700_B(angle) + vec.s_956_w * u_530_F.J_1907_R(angle));
    }

    public static double calculateAudioLevel(short[] samples, int offset, int length) {
        double rms = 0.0;
        for (int i = offset; i < length; ++i) {
            double sample = (double)samples[i] / 32767.0;
            rms += sample * sample;
        }
        int sampleCount = length / 2;
        rms = sampleCount == 0 ? 0.0 : Math.sqrt(rms / (double)sampleCount);
        double db = rms > 0.0 ? Math.min(Math.max(20.0 * Math.log10(rms), -127.0), 0.0) : -127.0;
        return db;
    }

    public static double getHighestAudioLevel(short[] samples) {
        double highest = -127.0;
        for (int i = 0; i < samples.length; i += 100) {
            double level = Utils.calculateAudioLevel(samples, i, Math.min(i + 100, samples.length));
            if (!(level > highest)) continue;
            highest = level;
        }
        return highest;
    }

    public static int getActivationOffset(short[] samples, double activationLevel) {
        int highestPos = -1;
        for (int i = 0; i < samples.length; i += 100) {
            double level = Utils.calculateAudioLevel(samples, i, Math.min(i + 100, samples.length));
            if (!(level >= activationLevel)) continue;
            highestPos = i;
        }
        return highestPos;
    }

    public static boolean isAboveThreshold(short[] samples, double threshold) {
        for (int i = 0; i < samples.length; i += 100) {
            double level = Utils.calculateAudioLevel(samples, i, Math.min(i + 100, samples.length));
            if (!(level >= threshold)) continue;
            return true;
        }
        return false;
    }

    public static double dbToPerc(double db) {
        return (db + 127.0) / 127.0;
    }

    public static double percToDb(double perc) {
        return perc * 127.0 - 127.0;
    }

    public static short[] combineAudio(Iterable<short[]> audioParts) {
        short[] result = new short[960];
        for (int i = 0; i < result.length; ++i) {
            int sample = 0;
            for (short[] audio : audioParts) {
                if (audio == null) {
                    sample += 0;
                    continue;
                }
                sample += audio[i];
            }
            result[i] = sample > Short.MAX_VALUE ? Short.MAX_VALUE : (sample < Short.MIN_VALUE ? Short.MIN_VALUE : (short)sample);
        }
        return result;
    }

    @Nullable
    public static <T> T createSafe(SafeSupplier<T> supplier, @Nullable Consumer<Throwable> onError, long waitTime) {
        AtomicReference exception = new AtomicReference();
        AtomicReference obj = new AtomicReference();
        Thread t = new Thread(() -> {
            if (onError != null) {
                Thread.setDefaultUncaughtExceptionHandler((t1, e) -> exception.set(e));
            }
            try {
                obj.set(supplier.get());
            }
            catch (Throwable e2) {
                exception.set(e2);
            }
        }, "NativeInitializationThread");
        t.start();
        try {
            t.join(waitTime);
        }
        catch (InterruptedException e) {
            return null;
        }
        Throwable ex = (Throwable)exception.get();
        if (onError != null && ex != null) {
            onError.accept(ex);
        }
        return (T)obj.get();
    }

    @Nullable
    public static <T> T createSafe(SafeSupplier<T> supplier, @Nullable Consumer<Throwable> onError) {
        return Utils.createSafe(supplier, onError, 5000L);
    }

    @Nullable
    public static <T> T createSafe(SafeSupplier<T> supplier) {
        return Utils.createSafe(supplier, null);
    }

    public static float getDefaultDistanceServer() {
        return ((Double)Voicechat.SERVER_CONFIG.voiceChatDistance.get()).floatValue();
    }

    @FunctionalInterface
    public static interface SafeSupplier<T> {
        public T get() throws Throwable;
    }
}

