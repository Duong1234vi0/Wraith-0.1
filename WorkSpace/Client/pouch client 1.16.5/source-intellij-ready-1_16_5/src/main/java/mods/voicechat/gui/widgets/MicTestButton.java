/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.widgets;

import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.Voicechat;
import mods.voicechat.VoicechatClient;
import mods.voicechat.debug.VoicechatUncaughtExceptionHandler;
import mods.voicechat.gui.widgets.ImageButton;
import mods.voicechat.gui.widgets.ToggleImageButton;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.client.MicActivator;
import mods.voicechat.voice.client.MicThread;
import mods.voicechat.voice.client.MicrophoneActivationType;
import mods.voicechat.voice.client.MicrophoneException;
import mods.voicechat.voice.client.SoundManager;
import mods.voicechat.voice.client.speaker.Speaker;
import mods.voicechat.voice.client.speaker.SpeakerException;
import mods.voicechat.voice.client.speaker.SpeakerManager;
import mods.voicechat.voice.common.Utils;

public class MicTestButton
extends ToggleImageButton
implements ImageButton.TooltipSupplier {
    private static final g_2336_b MICROPHONE = new g_2336_b("voicechat/textures/icons/microphone_button.png");
    private static final x_282_a TEST_DISABLED = new F_2904_S("message.voicechat.mic_test.disabled");
    private static final x_282_a TEST_ENABLED = new F_2904_S("message.voicechat.mic_test.enabled");
    private static final x_282_a TEST_UNAVAILABLE = new F_2904_S("message.voicechat.mic_test_unavailable").n_1700_B(D_4024_W.P_4830_p);
    private boolean micActive;
    @Nullable
    private VoiceThread voiceThread;
    private final MicListener micListener;
    @Nullable
    private final ClientVoicechat client;

    public MicTestButton(int xIn, int yIn, MicListener micListener) {
        super(xIn, yIn, MICROPHONE, null, null, null);
        this.micListener = micListener;
        this.client = ClientManager.getClient();
        this.active = this.client == null || this.client.getSoundManager() != null;
        this.stateSupplier = () -> !this.micActive;
        this.tooltipSupplier = this;
    }

    @Override
    public void render(g_221_o matrixStack, int x, int y, float partialTicks) {
        super.render(matrixStack, x, y, partialTicks);
        if (this.visible && this.voiceThread != null) {
            this.voiceThread.updateLastRender();
        }
    }

    @Override
    protected boolean shouldRenderTooltip() {
        return false;
    }

    public void setMicActive(boolean micActive) {
        this.micActive = micActive;
    }

    @Override
    public boolean isHovered() {
        return this.isHovered;
    }

    @Override
    public void onPress() {
        this.setMicActive(!this.micActive);
        if (this.micActive) {
            this.close();
            try {
                this.voiceThread = new VoiceThread();
                this.voiceThread.start();
            }
            catch (Exception e) {
                this.setMicActive(false);
                this.active = false;
                Voicechat.LOGGER.error("Microphone error", e);
            }
        } else {
            this.close();
        }
    }

    private void close() {
        if (this.voiceThread != null) {
            this.voiceThread.close();
            this.voiceThread = null;
        }
    }

    public void stop() {
        this.close();
        this.setMicActive(false);
    }

    @Override
    public void onTooltip(ImageButton button, g_221_o matrices, int mouseX, int mouseY) {
        k_2603_m screen = this.mc.Y_1740_V;
        if (screen == null) {
            return;
        }
        if (!this.active) {
            screen.renderTooltip(matrices, TEST_UNAVAILABLE, mouseX, mouseY);
            return;
        }
        if (this.micActive) {
            screen.renderTooltip(matrices, TEST_ENABLED, mouseX, mouseY);
        } else {
            screen.renderTooltip(matrices, TEST_DISABLED, mouseX, mouseY);
        }
    }

    public static interface MicListener {
        public void onMicValue(double var1);
    }

    private class VoiceThread
    extends Thread {
        private final MicActivator micActivator;
        private final Speaker speaker;
        private boolean running = true;
        private long lastRender;
        private MicThread micThread;
        private boolean usesOwnMicThread;
        @Nullable
        private SoundManager ownSoundManager;

        public VoiceThread() throws SpeakerException, MicrophoneException {
            SoundManager soundManager;
            this.setDaemon(true);
            this.setName("VoiceTestingThread");
            this.setUncaughtExceptionHandler(new VoicechatUncaughtExceptionHandler());
            this.micActivator = new MicActivator();
            MicThread micThread = this.micThread = MicTestButton.this.client != null ? MicTestButton.this.client.getMicThread() : null;
            if (this.micThread == null) {
                this.micThread = new MicThread(MicTestButton.this.client, null);
                this.usesOwnMicThread = true;
            }
            if (MicTestButton.this.client == null) {
                this.ownSoundManager = soundManager = new SoundManager((String)VoicechatClient.CLIENT_CONFIG.speaker.get());
            } else {
                soundManager = MicTestButton.this.client.getSoundManager();
            }
            if (soundManager == null) {
                throw new SpeakerException("No sound manager");
            }
            this.speaker = SpeakerManager.createSpeaker(soundManager, null);
            this.updateLastRender();
            this.setMicLocked(true);
        }

        @Override
        public void run() {
            while (this.running && System.currentTimeMillis() - this.lastRender <= 500L) {
                short[] buff = this.micThread.pollMic();
                if (buff == null) continue;
                MicTestButton.this.micListener.onMicValue(Utils.dbToPerc(Utils.getHighestAudioLevel(buff)));
                if (((MicrophoneActivationType)((Object)VoicechatClient.CLIENT_CONFIG.microphoneActivationType.get())).equals((Object)MicrophoneActivationType.VOICE)) {
                    if (!this.micActivator.push(buff, a -> {})) continue;
                    this.play(buff);
                    continue;
                }
                this.micActivator.stopActivating();
                this.play(buff);
            }
            this.speaker.close();
            this.setMicLocked(false);
            MicTestButton.this.micListener.onMicValue(0.0);
            if (this.usesOwnMicThread) {
                this.micThread.close();
            }
            if (this.ownSoundManager != null) {
                this.ownSoundManager.close();
            }
            MicTestButton.this.setMicActive(false);
            Voicechat.LOGGER.info("Mic test audio channel closed", new Object[0]);
        }

        private void play(short[] buff) {
            this.speaker.play(buff, ((Double)VoicechatClient.CLIENT_CONFIG.voiceChatVolume.get()).floatValue(), null);
        }

        public void updateLastRender() {
            this.lastRender = System.currentTimeMillis();
        }

        private void setMicLocked(boolean locked) {
            this.micThread.setMicrophoneLocked(locked);
        }

        public void close() {
            if (!this.running) {
                return;
            }
            Voicechat.LOGGER.info("Stopping mic test audio channel", new Object[0]);
            this.running = false;
            try {
                this.join();
            }
            catch (InterruptedException e) {
                Voicechat.LOGGER.warn("Failed to close microphone", e);
            }
        }
    }
}

