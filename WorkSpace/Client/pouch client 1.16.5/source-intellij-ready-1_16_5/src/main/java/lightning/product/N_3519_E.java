/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.openal.AL10
 *  org.lwjgl.openal.ALC10
 */
package lightning.product;

import javax.sound.sampled.AudioFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC10;

public class N_3519_E {
    private static final Logger n_1700_B = LogManager.getLogger();

    private static String n_1700_B(int rawValue) {
        switch (rawValue) {
            case 40961: {
                return "Invalid name parameter.";
            }
            case 40962: {
                return "Invalid enumerated parameter value.";
            }
            case 40963: {
                return "Invalid parameter parameter value.";
            }
            case 40964: {
                return "Invalid operation.";
            }
            case 40965: {
                return "Unable to allocate memory.";
            }
        }
        return "An unrecognized error occurred.";
    }

    static boolean n_1700_B(String where) {
        int i = AL10.alGetError();
        if (i != 0) {
            n_1700_B.error("{}: {}", (Object)where, (Object)N_3519_E.n_1700_B(i));
            return true;
        }
        return false;
    }

    private static String J_1907_R(int errorCode) {
        switch (errorCode) {
            case 40961: {
                return "Invalid device.";
            }
            case 40962: {
                return "Invalid context.";
            }
            case 40963: {
                return "Illegal enum.";
            }
            case 40964: {
                return "Invalid value.";
            }
            case 40965: {
                return "Unable to allocate memory.";
            }
        }
        return "An unrecognized error occurred.";
    }

    static boolean n_1700_B(long deviceHandle, String where) {
        int i = ALC10.alcGetError((long)deviceHandle);
        if (i != 0) {
            n_1700_B.error("{}{}: {}", (Object)where, (Object)deviceHandle, (Object)N_3519_E.J_1907_R(i));
            return true;
        }
        return false;
    }

    static int n_1700_B(AudioFormat audioFormat) {
        AudioFormat.Encoding encoding = audioFormat.getEncoding();
        int i = audioFormat.getChannels();
        int j = audioFormat.getSampleSizeInBits();
        if (encoding.equals(AudioFormat.Encoding.PCM_UNSIGNED) || encoding.equals(AudioFormat.Encoding.PCM_SIGNED)) {
            if (i == 1) {
                if (j == 8) {
                    return 4352;
                }
                if (j == 16) {
                    return 4353;
                }
            } else if (i == 2) {
                if (j == 8) {
                    return 4354;
                }
                if (j == 16) {
                    return 4355;
                }
            }
        }
        throw new IllegalArgumentException("Invalid audio format: " + String.valueOf(audioFormat));
    }
}

