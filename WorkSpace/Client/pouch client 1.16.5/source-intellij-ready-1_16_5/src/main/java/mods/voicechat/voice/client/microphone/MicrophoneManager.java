/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.client.microphone;

import java.util.List;
import mods.voicechat.Voicechat;
import mods.voicechat.VoicechatClient;
import mods.voicechat.voice.client.MicrophoneException;
import mods.voicechat.voice.client.microphone.ALMicrophone;
import mods.voicechat.voice.client.microphone.JavaxMicrophone;
import mods.voicechat.voice.client.microphone.Microphone;

public class MicrophoneManager {
    private static boolean fallback;
    private static boolean openAlFallbackLogged;

    public static Microphone createMicrophone() throws MicrophoneException {
        Microphone mic;
        if (fallback || ((Boolean)VoicechatClient.CLIENT_CONFIG.javaMicrophoneImplementation.get()).booleanValue()) {
            mic = MicrophoneManager.createJavaMicrophone();
        } else {
            try {
                mic = MicrophoneManager.createALMicrophone();
            }
            catch (MicrophoneException e) {
                if (!openAlFallbackLogged) {
                    Voicechat.LOGGER.warn("Failed to use OpenAL microphone implementation, falling back to Java microphone implementation", new Object[0]);
                    openAlFallbackLogged = true;
                } else {
                    Voicechat.LOGGER.debug("OpenAL microphone failed again, keeping Java fallback", new Object[0]);
                }
                mic = MicrophoneManager.createJavaMicrophone();
                fallback = true;
            }
        }
        return mic;
    }

    private static Microphone createJavaMicrophone() throws MicrophoneException {
        JavaxMicrophone mic = new JavaxMicrophone(48000, 960, (String)VoicechatClient.CLIENT_CONFIG.microphone.get());
        mic.open();
        return mic;
    }

    private static Microphone createALMicrophone() throws MicrophoneException {
        ALMicrophone mic = new ALMicrophone(48000, 960, (String)VoicechatClient.CLIENT_CONFIG.microphone.get());
        mic.open();
        return mic;
    }

    public static List<String> deviceNames() {
        if (fallback || ((Boolean)VoicechatClient.CLIENT_CONFIG.javaMicrophoneImplementation.get()).booleanValue()) {
            return JavaxMicrophone.getAllMicrophones();
        }
        return ALMicrophone.getAllMicrophones();
    }
}

