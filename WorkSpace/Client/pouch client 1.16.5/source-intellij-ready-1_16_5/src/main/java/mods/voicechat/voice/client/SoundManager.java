/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.lwjgl.openal.AL11
 *  org.lwjgl.openal.ALC11
 *  org.lwjgl.openal.ALUtil
 *  org.lwjgl.openal.EXTThreadLocalContext
 */
package mods.voicechat.voice.client;

import java.nio.IntBuffer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import mods.voicechat.Voicechat;
import mods.voicechat.plugins.ClientPluginManager;
import mods.voicechat.voice.client.speaker.SpeakerException;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.ALC11;
import org.lwjgl.openal.ALUtil;
import org.lwjgl.openal.EXTThreadLocalContext;

public class SoundManager {
    private static final long ERROR_LOG_COOLDOWN_MS = 10000L;
    private static long lastAlErrorLogMs = 0L;
    private static long lastAlcErrorLogMs = 0L;
    private static int suppressedAlErrors = 0;
    private static int suppressedAlcErrors = 0;
    @Nullable
    private final String deviceName;
    private long device;
    private long context;
    private static final Pattern DEVICE_NAME = Pattern.compile("^(?:OpenAL.+?on )?(.*)$");

    public SoundManager(@Nullable String deviceName) throws SpeakerException {
        this.deviceName = deviceName;
        this.device = this.openSpeaker(deviceName);
        this.context = ALC11.alcCreateContext((long)this.device, (IntBuffer)null);
        ClientPluginManager.instance().onCreateALContext(this.context, this.device);
    }

    public void close() {
        ClientPluginManager.instance().onDestroyALContext(this.context, this.device);
        if (this.context != 0L) {
            ALC11.alcDestroyContext((long)this.context);
            SoundManager.checkAlcError(this.device);
        }
        if (this.device != 0L) {
            ALC11.alcCloseDevice((long)this.device);
            SoundManager.checkAlcError(this.device);
        }
        this.context = 0L;
        this.device = 0L;
    }

    public boolean isClosed() {
        return this.context == 0L || this.device == 0L;
    }

    private long openSpeaker(@Nullable String name) throws SpeakerException {
        try {
            return this.tryOpenSpeaker(name);
        }
        catch (SpeakerException e) {
            if (name != null) {
                Voicechat.LOGGER.warn("Failed to open audio channel '{}', falling back to default", name);
            }
            try {
                return this.tryOpenSpeaker(SoundManager.getDefaultSpeaker());
            }
            catch (SpeakerException ex) {
                return this.tryOpenSpeaker(null);
            }
        }
    }

    private long tryOpenSpeaker(@Nullable String string) throws SpeakerException {
        long l = ALC11.alcOpenDevice((CharSequence)string);
        if (l == 0L) {
            throw new SpeakerException("Failed to open audio device: Audio device not found");
        }
        SoundManager.checkAlcError(this.device);
        return l;
    }

    @Nullable
    public static String getDefaultSpeaker() {
        if (!SoundManager.canEnumerate()) {
            return null;
        }
        String defaultSpeaker = ALC11.alcGetString((long)0L, (int)4115);
        SoundManager.checkAlcError(0L);
        return defaultSpeaker;
    }

    public static List<String> getAllSpeakers() {
        if (!SoundManager.canEnumerate()) {
            return Collections.emptyList();
        }
        List devices = ALUtil.getStringList((long)0L, (int)4115);
        SoundManager.checkAlcError(0L);
        return devices == null ? Collections.emptyList() : devices;
    }

    public void runInContext(Executor executor, Runnable runnable) {
        long time = System.currentTimeMillis();
        executor.execute(() -> {
            long diff = System.currentTimeMillis() - time;
            if (diff > 20L || diff >= 5L && Voicechat.debugMode()) {
                Voicechat.LOGGER.warn("Sound executor delay: {} ms!", diff);
            }
            if (this.openContext()) {
                runnable.run();
                this.closeContext();
            }
        });
    }

    public boolean openContext() {
        if (this.context == 0L) {
            return false;
        }
        boolean success = EXTThreadLocalContext.alcSetThreadContext((long)this.context);
        SoundManager.checkAlcError(this.device);
        return success;
    }

    public void closeContext() {
        EXTThreadLocalContext.alcSetThreadContext((long)0L);
        SoundManager.checkAlcError(this.device);
    }

    public static boolean checkAlError() {
        int error = AL11.alGetError();
        if (error == 0) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (now - lastAlErrorLogMs >= 10000L) {
            if (suppressedAlErrors > 0) {
                Voicechat.LOGGER.warn("Voicechat sound manager AL error: suppressed {} similar errors in last {} ms", suppressedAlErrors, 10000L);
                suppressedAlErrors = 0;
            }
            StackTraceElement stack = Thread.currentThread().getStackTrace()[2];
            Voicechat.LOGGER.error("Voicechat sound manager AL error: {}.{}[{}] {}", stack.getClassName(), stack.getMethodName(), stack.getLineNumber(), SoundManager.getAlError(error));
            lastAlErrorLogMs = now;
        } else {
            ++suppressedAlErrors;
        }
        return true;
    }

    public static boolean checkAlcError(long device) {
        int error = ALC11.alcGetError((long)device);
        if (error == 0) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (now - lastAlcErrorLogMs >= 10000L) {
            if (suppressedAlcErrors > 0) {
                Voicechat.LOGGER.warn("Voicechat sound manager ALC error: suppressed {} similar errors in last {} ms", suppressedAlcErrors, 10000L);
                suppressedAlcErrors = 0;
            }
            StackTraceElement stack = Thread.currentThread().getStackTrace()[2];
            Voicechat.LOGGER.error("Voicechat sound manager ALC error: {}.{}[{}] {}", stack.getClassName(), stack.getMethodName(), stack.getLineNumber(), SoundManager.getAlcError(error));
            lastAlcErrorLogMs = now;
        } else {
            ++suppressedAlcErrors;
        }
        return true;
    }

    private static String getAlError(int i) {
        switch (i) {
            case 40961: {
                return "Invalid name";
            }
            case 40962: {
                return "Invalid enum ";
            }
            case 40963: {
                return "Invalid value";
            }
            case 40964: {
                return "Invalid operation";
            }
            case 40965: {
                return "Out of memory";
            }
        }
        return "Unknown error";
    }

    public static String getAlcError(int i) {
        switch (i) {
            case 40961: {
                return "Invalid device";
            }
            case 40962: {
                return "Invalid context";
            }
            case 40963: {
                return "Invalid enum";
            }
            case 40964: {
                return "Invalid value";
            }
            case 40965: {
                return "Out of memory";
            }
        }
        return "Unknown error";
    }

    public static String cleanDeviceName(String name) {
        Matcher matcher = DEVICE_NAME.matcher(name);
        if (!matcher.matches()) {
            return name;
        }
        return matcher.group(1);
    }

    public static boolean canEnumerate() {
        boolean present = ALC11.alcIsExtensionPresent((long)0L, (CharSequence)"ALC_ENUMERATE_ALL_EXT");
        SoundManager.checkAlcError(0L);
        return present;
    }
}

