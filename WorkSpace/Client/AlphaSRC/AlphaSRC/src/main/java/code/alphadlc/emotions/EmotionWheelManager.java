/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_10055
 *  net.minecraft.class_1132
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_3532
 *  net.minecraft.class_3675$class_307
 *  net.minecraft.class_437
 *  net.minecraft.class_5498
 *  net.minecraft.class_630
 */
package code.alphadlc.emotions;

import code.alphadlc.display.screens.EmotionWheelScreen;
import code.alphadlc.emotions.EmotionSyncWebSocketClient;
import code.alphadlc.emotions.EmotionType;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.misc.Globals;
import code.alphadlc.main.listener.Listener;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.interfaces.QuickImports;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import net.minecraft.class_10055;
import net.minecraft.class_1132;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_3532;
import net.minecraft.class_3675;
import net.minecraft.class_437;
import net.minecraft.class_5498;
import net.minecraft.class_630;

public class EmotionWheelManager
implements Listener,
QuickImports {
    private static final String SYNC_URL = "wss://AlphaDLC-emotions.onrender.com";
    private static final String PRESENCE_EMOTION = "__presence__";
    private static final long PRESENCE_INTERVAL_MS = 20000L;
    private static final long PRESENCE_TIMEOUT_MS = 35000L;
    private static EmotionWheelManager instance;
    private final Map<UUID, ActiveEmotion> activeEmotions = new ConcurrentHashMap<UUID, ActiveEmotion>();
    private final Map<UUID, PendingEmotion> pendingEmotions = new ConcurrentHashMap<UUID, PendingEmotion>();
    private final Map<UUID, Long> knownClientPlayers = new ConcurrentHashMap<UUID, Long>();
    private final CopyOnWriteArrayList<Consumer<JsonObject>> relayListeners = new CopyOnWriteArrayList();
    private EmotionWheelScreen wheelScreen;
    private EmotionSyncWebSocketClient syncClient;
    private long nextReconnectAttemptMs;
    private long nextPresenceBroadcastMs;
    private EmotionType previewEmotion = EmotionType.HELLO;
    private long previewStartedAtMs;

    public static EmotionWheelManager create() {
        instance = new EmotionWheelManager();
        return instance;
    }

    public static EmotionWheelManager getInstance() {
        return instance;
    }

    @EventHandler
    public void onKey(KeyEvent event) {
        class_437 class_4372;
        Globals globalsModule = Instance.get(Globals.class);
        if (globalsModule == null || !globalsModule.isState()) {
            return;
        }
        int openKey = globalsModule.getEmotionWheelBind().getKey();
        if (openKey == -1 || event.key() != openKey || event.type() != class_3675.class_307.field_1668) {
            return;
        }
        if (event.action() == 1) {
            if (EmotionWheelManager.mc.field_1724 != null && EmotionWheelManager.mc.field_1687 != null && EmotionWheelManager.mc.field_1755 == null) {
                this.openWheel();
            }
        } else if (event.action() == 0 && (class_4372 = EmotionWheelManager.mc.field_1755) instanceof EmotionWheelScreen) {
            EmotionWheelScreen screen = (EmotionWheelScreen)class_4372;
            EmotionType emotion = screen.getHoveredEmotion();
            this.closeWheel(false);
            if (emotion != null) {
                this.playEmotion(emotion);
            }
        }
    }

    @EventHandler
    public void onTick(TickEvent event) {
        Globals globalsModule = Instance.get(Globals.class);
        if (globalsModule == null || !globalsModule.isState()) {
            this.closeWheel(false);
            this.stopEmotion();
            this.disconnectSync();
            this.knownClientPlayers.clear();
            this.pendingEmotions.clear();
            return;
        }
        this.ensureSyncConnection();
        if (EmotionWheelManager.mc.field_1724 == null || EmotionWheelManager.mc.field_1687 == null) {
            this.closeWheel(false);
            this.stopEmotion();
            this.knownClientPlayers.clear();
            this.pendingEmotions.clear();
            return;
        }
        this.sendPresenceIfNeeded();
        this.pruneKnownClients();
        this.applyPendingEmotions();
        Iterator<Map.Entry<UUID, ActiveEmotion>> iterator = this.activeEmotions.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, ActiveEmotion> entry = iterator.next();
            if (!entry.getValue().isExpired()) continue;
            if (entry.getValue().local && EmotionWheelManager.mc.field_1724 != null && EmotionWheelManager.mc.field_1724.method_5667().equals(entry.getKey())) {
                EmotionWheelManager.mc.field_1690.method_31043(entry.getValue().previousPerspective);
            }
            iterator.remove();
        }
    }

    public void closeWheel(boolean forceGame) {
        if (EmotionWheelManager.mc.field_1755 instanceof EmotionWheelScreen) {
            this.wheelScreen = null;
            mc.method_1507(null);
        }
    }

    public void applyToModel(class_10055 state, class_630 head, class_630 hat, class_630 body, class_630 rightArm, class_630 leftArm, class_630 rightLeg, class_630 leftLeg) {
        float alpha;
        if (EmotionWheelManager.mc.field_1687 == null) {
            return;
        }
        class_1297 class_12972 = EmotionWheelManager.mc.field_1687.method_8469(state.field_53528);
        if (!(class_12972 instanceof class_1657)) {
            return;
        }
        class_1657 player = (class_1657)class_12972;
        ActiveEmotion activeEmotion = this.activeEmotions.get(player.method_5667());
        EmotionType renderEmotion = activeEmotion != null ? activeEmotion.type : null;
        long startedAt = activeEmotion != null ? activeEmotion.startedAt : 0L;
        float f = alpha = activeEmotion != null ? activeEmotion.getBlend() : 0.0f;
        if (renderEmotion == null && EmotionWheelManager.mc.field_1755 instanceof EmotionWheelScreen && EmotionWheelManager.mc.field_1724 != null && player == EmotionWheelManager.mc.field_1724 && this.previewEmotion != null) {
            renderEmotion = this.previewEmotion;
            startedAt = this.previewStartedAtMs;
            alpha = 1.0f;
        }
        if (renderEmotion == null) {
            return;
        }
        this.resetModelPose(head, hat, body, rightArm, leftArm, rightLeg, leftLeg);
        long elapsed = Math.max(0L, System.currentTimeMillis() - startedAt);
        boolean animateHands = this.canAnimateHands(player);
        this.applyEmotionPose(renderEmotion, alpha, elapsed, animateHands, head, hat, body, rightArm, leftArm, rightLeg, leftLeg);
    }

    private void applyEmotionPose(EmotionType renderEmotion, float alpha, long elapsed, boolean animateHands, class_630 head, class_630 hat, class_630 body, class_630 rightArm, class_630 leftArm, class_630 rightLeg, class_630 leftLeg) {
        if (renderEmotion == EmotionType.DEB) {
            this.applyDeb(alpha, animateHands, head, hat, rightArm, leftArm);
        } else if (renderEmotion == EmotionType.FLOSS) {
            this.applyFloss(alpha, elapsed, animateHands, body, rightArm, leftArm, rightLeg, leftLeg);
        } else if (renderEmotion == EmotionType.GET_GRIDDY) {
            this.applyGetGriddy(alpha, elapsed, animateHands, head, hat, body, rightArm, leftArm, rightLeg, leftLeg);
        } else if (renderEmotion == EmotionType.HELLO) {
            this.applyHello(alpha, elapsed, animateHands, rightArm);
        } else if (renderEmotion == EmotionType.HAPPY) {
            this.applyHappy(alpha, animateHands, rightArm, leftArm);
        } else if (renderEmotion == EmotionType.CLAP) {
            this.applyClap(alpha, elapsed, animateHands, rightArm, leftArm);
        } else if (renderEmotion == EmotionType.CRY) {
            this.applyCry(alpha, elapsed, animateHands, head, hat, rightArm, leftArm);
        } else if (renderEmotion == EmotionType.POINT) {
            this.applyPoint(alpha, animateHands, head, hat, body, rightArm, leftArm);
        } else if (renderEmotion == EmotionType.SALUTE) {
            this.applySalute(alpha, animateHands, head, hat, rightArm, leftArm);
        } else if (renderEmotion == EmotionType.THINK) {
            this.applyThink(alpha, animateHands, head, hat, rightArm, leftArm);
        } else if (renderEmotion == EmotionType.STRETCH) {
            this.applyStretch(alpha, animateHands, body, rightArm, leftArm);
        } else if (renderEmotion == EmotionType.DANCE) {
            this.applyDance(alpha, elapsed, head, hat, body, rightArm, leftArm, rightLeg, leftLeg);
        } else if (renderEmotion == EmotionType.FACEPALM) {
            this.applyFacepalm(alpha, animateHands, head, hat, rightArm, leftArm);
        } else if (renderEmotion == EmotionType.SHRUG) {
            this.applyShrug(alpha, animateHands, head, hat, rightArm, leftArm);
        }
    }

    private void openWheel() {
        this.wheelScreen = new EmotionWheelScreen(this);
        mc.method_1507((class_437)this.wheelScreen);
    }

    public void openFromGlobals() {
        Globals globalsModule = Instance.get(Globals.class);
        if (globalsModule == null || !globalsModule.isState()) {
            return;
        }
        if (EmotionWheelManager.mc.field_1724 == null || EmotionWheelManager.mc.field_1687 == null || EmotionWheelManager.mc.field_1755 != null) {
            return;
        }
        this.openWheel();
    }

    public void setPreviewEmotion(EmotionType emotionType) {
        if (emotionType == null) {
            return;
        }
        if (this.previewEmotion != emotionType) {
            this.previewEmotion = emotionType;
            this.previewStartedAtMs = System.currentTimeMillis();
        }
    }

    public EmotionType getPreviewEmotion() {
        return this.previewEmotion;
    }

    private void playEmotion(EmotionType emotionType) {
        if (EmotionWheelManager.mc.field_1724 == null) {
            return;
        }
        long startedAt = System.currentTimeMillis();
        this.startEmotion((class_1657)EmotionWheelManager.mc.field_1724, emotionType, true, startedAt);
        this.sendEmotionPacket(emotionType, startedAt);
    }

    private void stopEmotion() {
        if (EmotionWheelManager.mc.field_1724 == null) {
            this.activeEmotions.clear();
            return;
        }
        ActiveEmotion activeEmotion = this.activeEmotions.remove(EmotionWheelManager.mc.field_1724.method_5667());
        if (activeEmotion != null && activeEmotion.local) {
            EmotionWheelManager.mc.field_1690.method_31043(activeEmotion.previousPerspective);
        }
    }

    private void applyDeb(float alpha, boolean animateHands, class_630 head, class_630 hat, class_630 rightArm, class_630 leftArm) {
        if (animateHands) {
            rightArm.field_3654 = this.move(rightArm.field_3654, -0.7f, alpha);
            rightArm.field_3674 = this.move(rightArm.field_3674, -2.0f, alpha);
            leftArm.field_3654 = this.move(leftArm.field_3654, -0.7f, alpha);
            leftArm.field_3674 = this.move(leftArm.field_3674, -2.0f, alpha);
        }
        head.field_3654 = this.move(head.field_3654, 0.7f, alpha);
        head.field_3675 = this.move(head.field_3675, 0.5f, alpha);
        hat.field_3654 = this.move(hat.field_3654, 0.7f, alpha);
        hat.field_3675 = this.move(hat.field_3675, 0.5f, alpha);
    }

    private void applyHello(float alpha, long elapsed, boolean animateHands, class_630 rightArm) {
        if (!animateHands) {
            return;
        }
        float wave = this.pingPong(elapsed, 250L);
        rightArm.field_3654 = this.move(rightArm.field_3654, -0.2f, alpha);
        rightArm.field_3674 = this.move(rightArm.field_3674, 2.75f - 0.25f * wave, alpha);
    }

    private void applyHappy(float alpha, boolean animateHands, class_630 rightArm, class_630 leftArm) {
        if (!animateHands) {
            return;
        }
        float pulse = this.pingPong(System.currentTimeMillis(), 260L);
        rightArm.field_3654 = this.move(rightArm.field_3654, -0.35f - 0.2f * pulse, alpha);
        rightArm.field_3674 = this.move(rightArm.field_3674, 2.1f + 0.3f * pulse, alpha);
        leftArm.field_3654 = this.move(leftArm.field_3654, -0.35f - 0.2f * pulse, alpha);
        leftArm.field_3674 = this.move(leftArm.field_3674, -2.1f - 0.3f * pulse, alpha);
    }

    private void applyClap(float alpha, long elapsed, boolean animateHands, class_630 rightArm, class_630 leftArm) {
        if (!animateHands) {
            return;
        }
        float t = this.pingPong(elapsed, 220L);
        float xBend = -1.1f * t;
        float yInward = 0.95f * t;
        float zFlat = 0.55f * t;
        rightArm.field_3654 = this.move(rightArm.field_3654, xBend, alpha);
        rightArm.field_3675 = this.move(rightArm.field_3675, -yInward, alpha);
        rightArm.field_3674 = this.move(rightArm.field_3674, zFlat, alpha);
        leftArm.field_3654 = this.move(leftArm.field_3654, xBend, alpha);
        leftArm.field_3675 = this.move(leftArm.field_3675, yInward, alpha);
        leftArm.field_3674 = this.move(leftArm.field_3674, -zFlat, alpha);
    }

    private void applyCry(float alpha, long elapsed, boolean animateHands, class_630 head, class_630 hat, class_630 rightArm, class_630 leftArm) {
        if (animateHands) {
            float hand = this.pingPong(elapsed, 320L);
            float xBend = -1.9f * hand - 0.25f;
            float yInward = 0.62f * hand;
            float zAcross = 1.05f * hand;
            rightArm.field_3654 = this.move(rightArm.field_3654, xBend, alpha);
            rightArm.field_3675 = this.move(rightArm.field_3675, -yInward, alpha);
            rightArm.field_3674 = this.move(rightArm.field_3674, zAcross, alpha);
            leftArm.field_3654 = this.move(leftArm.field_3654, xBend, alpha);
            leftArm.field_3675 = this.move(leftArm.field_3675, yInward, alpha);
            leftArm.field_3674 = this.move(leftArm.field_3674, -zAcross, alpha);
        }
        float headBob = this.pingPong(elapsed, 220L);
        head.field_3654 = this.move(head.field_3654, 0.62f + 0.25f * headBob, alpha);
        head.field_3674 = this.move(head.field_3674, -0.1f + 0.2f * headBob, alpha);
        hat.field_3654 = this.move(hat.field_3654, head.field_3654, alpha);
        hat.field_3674 = this.move(hat.field_3674, head.field_3674, alpha);
    }

    private void applyFloss(float alpha, long elapsed, boolean animateHands, class_630 body, class_630 rightArm, class_630 leftArm, class_630 rightLeg, class_630 leftLeg) {
        float action = this.pingPong(elapsed, 250L);
        float rightSide = this.pingPong(elapsed, 500L);
        float leftSide = this.pingPong(elapsed + 250L, 500L);
        if (animateHands) {
            rightArm.field_3654 = this.move(rightArm.field_3654, 0.5f - rightSide, alpha);
            rightArm.field_3674 = this.move(rightArm.field_3674, 1.0f - 2.0f * action, alpha);
            leftArm.field_3654 = this.move(leftArm.field_3654, 0.5f - leftSide, alpha);
            leftArm.field_3674 = this.move(leftArm.field_3674, 1.5f - 2.0f * action, alpha);
        }
        body.field_3674 = this.move(body.field_3674, 0.3f * action - 0.15f, alpha);
        rightLeg.field_3657 = this.move(rightLeg.field_3657, -0.5f - 3.0f * action, alpha);
        leftLeg.field_3657 = this.move(leftLeg.field_3657, 3.0f - 2.5f * action, alpha);
    }

    private void applyGetGriddy(float alpha, long elapsed, boolean animateHands, class_630 head, class_630 hat, class_630 body, class_630 rightArm, class_630 leftArm, class_630 rightLeg, class_630 leftLeg) {
        float action = this.pingPong(elapsed, 350L);
        float leg = this.pingPong(elapsed, 300L);
        float eye = this.griddyEye(elapsed);
        if (animateHands) {
            rightArm.field_3654 = this.move(rightArm.field_3654, 1.0f - 1.5f * action - 3.5f * eye, alpha);
            rightArm.field_3674 = this.move(rightArm.field_3674, 0.5f - 0.75f * action, alpha);
            leftArm.field_3654 = this.move(leftArm.field_3654, 1.0f - 1.5f * action - 3.5f * eye, alpha);
            leftArm.field_3674 = this.move(leftArm.field_3674, -0.5f + 0.75f * action, alpha);
        }
        body.field_3654 = this.move(body.field_3654, 0.2f - 0.05f * action, alpha);
        rightLeg.field_3655 = this.move(rightLeg.field_3655, 2.0f - 0.5f * action, alpha);
        rightLeg.field_3654 = this.move(rightLeg.field_3654, 0.3f * leg - 0.3f, alpha);
        leftLeg.field_3655 = this.move(leftLeg.field_3655, 2.0f - 0.5f * action, alpha);
        leftLeg.field_3654 = this.move(leftLeg.field_3654, -0.3f * leg - 0.1f, alpha);
        head.field_3675 = this.move(head.field_3675, -0.4f + 0.4f * eye, alpha);
        head.field_3654 = this.move(head.field_3654, -0.3f * eye, alpha);
        head.field_3674 = this.move(head.field_3674, 0.0f, alpha);
        hat.field_3675 = this.move(hat.field_3675, -0.4f + 0.4f * eye, alpha);
        hat.field_3654 = this.move(hat.field_3654, -0.3f * eye, alpha);
        hat.field_3674 = this.move(hat.field_3674, 0.0f, alpha);
    }

    private void applyPoint(float alpha, boolean animateHands, class_630 head, class_630 hat, class_630 body, class_630 rightArm, class_630 leftArm) {
        body.field_3675 = this.move(body.field_3675, -0.18f, alpha);
        body.field_3654 = this.move(body.field_3654, -0.04f, alpha);
        head.field_3675 = this.move(head.field_3675, -0.3f, alpha);
        head.field_3654 = this.move(head.field_3654, -0.1f, alpha);
        hat.field_3675 = this.move(hat.field_3675, head.field_3675, alpha);
        hat.field_3654 = this.move(hat.field_3654, head.field_3654, alpha);
        if (!animateHands) {
            return;
        }
        rightArm.field_3654 = this.move(rightArm.field_3654, -1.52f, alpha);
        rightArm.field_3675 = this.move(rightArm.field_3675, -0.22f, alpha);
        rightArm.field_3674 = this.move(rightArm.field_3674, 0.09f, alpha);
        leftArm.field_3654 = this.move(leftArm.field_3654, 0.1f, alpha);
        leftArm.field_3674 = this.move(leftArm.field_3674, -0.2f, alpha);
    }

    private void applySalute(float alpha, boolean animateHands, class_630 head, class_630 hat, class_630 rightArm, class_630 leftArm) {
        head.field_3675 = this.move(head.field_3675, -0.12f, alpha);
        head.field_3654 = this.move(head.field_3654, -0.08f, alpha);
        hat.field_3675 = this.move(hat.field_3675, head.field_3675, alpha);
        hat.field_3654 = this.move(hat.field_3654, head.field_3654, alpha);
        if (!animateHands) {
            return;
        }
        rightArm.field_3654 = this.move(rightArm.field_3654, -1.26f, alpha);
        rightArm.field_3675 = this.move(rightArm.field_3675, -0.38f, alpha);
        rightArm.field_3674 = this.move(rightArm.field_3674, 0.7f, alpha);
        leftArm.field_3654 = this.move(leftArm.field_3654, 0.04f, alpha);
        leftArm.field_3674 = this.move(leftArm.field_3674, -0.1f, alpha);
    }

    private void applyThink(float alpha, boolean animateHands, class_630 head, class_630 hat, class_630 rightArm, class_630 leftArm) {
        head.field_3675 = this.move(head.field_3675, 0.36f, alpha);
        head.field_3654 = this.move(head.field_3654, 0.22f, alpha);
        head.field_3674 = this.move(head.field_3674, 0.05f, alpha);
        hat.field_3675 = this.move(hat.field_3675, head.field_3675, alpha);
        hat.field_3654 = this.move(hat.field_3654, head.field_3654, alpha);
        hat.field_3674 = this.move(hat.field_3674, head.field_3674, alpha);
        if (!animateHands) {
            return;
        }
        rightArm.field_3654 = this.move(rightArm.field_3654, -1.04f, alpha);
        rightArm.field_3675 = this.move(rightArm.field_3675, -0.28f, alpha);
        rightArm.field_3674 = this.move(rightArm.field_3674, 0.48f, alpha);
        leftArm.field_3654 = this.move(leftArm.field_3654, 0.1f, alpha);
        leftArm.field_3674 = this.move(leftArm.field_3674, -0.16f, alpha);
    }

    private void applyStretch(float alpha, boolean animateHands, class_630 body, class_630 rightArm, class_630 leftArm) {
        float stretchWave = this.pingPong(System.currentTimeMillis(), 320L);
        body.field_3654 = this.move(body.field_3654, -0.12f - 0.06f * stretchWave, alpha);
        body.field_3656 = this.move(body.field_3656, -0.8f - 0.3f * stretchWave, alpha);
        if (!animateHands) {
            return;
        }
        rightArm.field_3654 = this.move(rightArm.field_3654, -2.8f, alpha);
        leftArm.field_3654 = this.move(leftArm.field_3654, -2.8f, alpha);
        rightArm.field_3674 = this.move(rightArm.field_3674, 0.22f, alpha);
        leftArm.field_3674 = this.move(leftArm.field_3674, -0.22f, alpha);
        rightArm.field_3675 = this.move(rightArm.field_3675, 0.05f, alpha);
        leftArm.field_3675 = this.move(leftArm.field_3675, -0.05f, alpha);
    }

    private void applyDance(float alpha, long elapsed, class_630 head, class_630 hat, class_630 body, class_630 rightArm, class_630 leftArm, class_630 rightLeg, class_630 leftLeg) {
        float sway = this.pingPong(elapsed, 220L) * 2.0f - 1.0f;
        float bounce = this.pingPong(elapsed + 90L, 150L);
        body.field_3674 = this.move(body.field_3674, 0.28f * sway, alpha);
        body.field_3675 = this.move(body.field_3675, 0.25f * sway, alpha);
        body.field_3654 = this.move(body.field_3654, -0.03f + 0.06f * bounce, alpha);
        head.field_3674 = this.move(head.field_3674, -0.18f * sway, alpha);
        head.field_3675 = this.move(head.field_3675, 0.22f * sway, alpha);
        hat.field_3674 = this.move(hat.field_3674, head.field_3674, alpha);
        hat.field_3675 = this.move(hat.field_3675, head.field_3675, alpha);
        rightArm.field_3654 = this.move(rightArm.field_3654, -0.8f + 0.5f * sway, alpha);
        leftArm.field_3654 = this.move(leftArm.field_3654, -0.8f - 0.5f * sway, alpha);
        rightArm.field_3674 = this.move(rightArm.field_3674, 0.55f + 0.3f * bounce, alpha);
        leftArm.field_3674 = this.move(leftArm.field_3674, -0.55f - 0.3f * bounce, alpha);
        rightLeg.field_3654 = this.move(rightLeg.field_3654, 0.18f + 0.38f * sway, alpha);
        leftLeg.field_3654 = this.move(leftLeg.field_3654, 0.18f - 0.38f * sway, alpha);
    }

    private void applyFacepalm(float alpha, boolean animateHands, class_630 head, class_630 hat, class_630 rightArm, class_630 leftArm) {
        if (animateHands) {
            rightArm.field_3654 = this.move(rightArm.field_3654, -1.62f, alpha);
            rightArm.field_3675 = this.move(rightArm.field_3675, -0.3f, alpha);
            rightArm.field_3674 = this.move(rightArm.field_3674, 0.48f, alpha);
            leftArm.field_3654 = this.move(leftArm.field_3654, -0.06f, alpha);
            leftArm.field_3674 = this.move(leftArm.field_3674, -0.05f, alpha);
        }
        head.field_3654 = this.move(head.field_3654, 0.48f, alpha);
        head.field_3674 = this.move(head.field_3674, -0.08f, alpha);
        hat.field_3654 = this.move(hat.field_3654, head.field_3654, alpha);
        hat.field_3674 = this.move(hat.field_3674, head.field_3674, alpha);
    }

    private void applyShrug(float alpha, boolean animateHands, class_630 head, class_630 hat, class_630 rightArm, class_630 leftArm) {
        if (animateHands) {
            float shrugPulse = this.pingPong(System.currentTimeMillis(), 260L);
            rightArm.field_3654 = this.move(rightArm.field_3654, -0.42f - 0.12f * shrugPulse, alpha);
            leftArm.field_3654 = this.move(leftArm.field_3654, -0.42f - 0.12f * shrugPulse, alpha);
            rightArm.field_3674 = this.move(rightArm.field_3674, 1.05f + 0.15f * shrugPulse, alpha);
            leftArm.field_3674 = this.move(leftArm.field_3674, -1.05f - 0.15f * shrugPulse, alpha);
        }
        head.field_3654 = this.move(head.field_3654, 0.1f, alpha);
        head.field_3675 = this.move(head.field_3675, 0.08f, alpha);
        hat.field_3654 = this.move(hat.field_3654, head.field_3654, alpha);
        hat.field_3675 = this.move(hat.field_3675, head.field_3675, alpha);
    }

    private boolean canAnimateHands(class_1657 targetPlayer) {
        return EmotionWheelManager.mc.field_1724 == null || targetPlayer != EmotionWheelManager.mc.field_1724 || !EmotionWheelManager.mc.field_1690.method_31044().method_31034();
    }

    private void resetModelPose(class_630 head, class_630 hat, class_630 body, class_630 rightArm, class_630 leftArm, class_630 rightLeg, class_630 leftLeg) {
        head.field_3657 = 0.0f;
        head.field_3656 = 0.0f;
        head.field_3655 = 0.0f;
        hat.field_3657 = 0.0f;
        hat.field_3656 = 0.0f;
        hat.field_3655 = 0.0f;
        body.field_3657 = 0.0f;
        body.field_3656 = 0.0f;
        body.field_3655 = 0.0f;
        rightArm.field_3657 = -5.0f;
        rightArm.field_3656 = 2.0f;
        rightArm.field_3655 = 0.0f;
        leftArm.field_3657 = 5.0f;
        leftArm.field_3656 = 2.0f;
        leftArm.field_3655 = 0.0f;
        rightLeg.field_3657 = -1.9f;
        rightLeg.field_3656 = 12.0f;
        rightLeg.field_3655 = 0.0f;
        leftLeg.field_3657 = 1.9f;
        leftLeg.field_3656 = 12.0f;
        leftLeg.field_3655 = 0.0f;
    }

    private float move(float current, float target, float alpha) {
        return class_3532.method_16439((float)alpha, (float)current, (float)target);
    }

    private float pingPong(long elapsed, long halfCycleMs) {
        if (halfCycleMs <= 0L) {
            return 0.0f;
        }
        float cycle = (float)elapsed % ((float)halfCycleMs * 2.0f) / (float)halfCycleMs;
        return cycle <= 1.0f ? cycle : 2.0f - cycle;
    }

    private float griddyEye(long elapsed) {
        long cycle = elapsed % 3100L;
        if (cycle < 2100L) {
            return 0.0f;
        }
        return this.pingPong(cycle - 2100L, 500L);
    }

    private void ensureSyncConnection() {
        if (this.syncClient != null && (this.syncClient.isOpen() || this.syncClient.isConnectingState())) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now < this.nextReconnectAttemptMs) {
            return;
        }
        this.nextReconnectAttemptMs = now + 10000L;
        try {
            this.syncClient = new EmotionSyncWebSocketClient(URI.create(SYNC_URL), this::handleIncomingMessage);
            this.syncClient.connect();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void disconnectSync() {
        if (this.syncClient != null) {
            try {
                this.syncClient.close();
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.syncClient = null;
        }
    }

    public boolean isSyncConnected() {
        return this.syncClient != null && this.syncClient.isOpen();
    }

    public boolean isSyncConnecting() {
        return this.syncClient != null && this.syncClient.isConnectingState();
    }

    public String getSyncUrl() {
        return SYNC_URL;
    }

    public String getDebugServerId() {
        return this.getCurrentServerId();
    }

    public boolean hasCompatibleClient(UUID uuid) {
        return this.knownClientPlayers.containsKey(uuid);
    }

    public void addRelayListener(Consumer<JsonObject> listener) {
        if (listener != null && !this.relayListeners.contains(listener)) {
            this.relayListeners.add(listener);
        }
    }

    public void removeRelayListener(Consumer<JsonObject> listener) {
        this.relayListeners.remove(listener);
    }

    public int getKnownClientCount() {
        return this.knownClientPlayers.size();
    }

    private void sendEmotionPacket(EmotionType emotionType, long startedAt) {
        this.sendRelayPacket(emotionType.name(), startedAt, null);
    }

    private void sendPresenceIfNeeded() {
        long now = System.currentTimeMillis();
        if (now < this.nextPresenceBroadcastMs) {
            return;
        }
        this.nextPresenceBroadcastMs = now + 20000L;
        this.sendSpecialPacket(PRESENCE_EMOTION, now);
    }

    private void sendSpecialPacket(String emotionValue, long startedAt) {
        this.sendRelayPacket(emotionValue, startedAt, null);
    }

    public boolean sendRelayPacket(String emotionValue, long startedAt, Consumer<JsonObject> packetCustomizer) {
        if (this.syncClient == null || !this.syncClient.isOpen() || EmotionWheelManager.mc.field_1724 == null) {
            return false;
        }
        JsonObject json = new JsonObject();
        json.addProperty("type", "play_emotion");
        json.addProperty("playerUuid", EmotionWheelManager.mc.field_1724.method_5845());
        json.addProperty("playerName", EmotionWheelManager.mc.field_1724.method_5477().getString());
        json.addProperty("emotion", emotionValue);
        json.addProperty("startedAt", (Number)startedAt);
        json.addProperty("serverId", this.getCurrentServerId());
        if (packetCustomizer != null) {
            packetCustomizer.accept(json);
        }
        this.syncClient.send(gson.toJson((JsonElement)json));
        return true;
    }

    private void pruneKnownClients() {
        long cutoff = System.currentTimeMillis() - 35000L;
        this.knownClientPlayers.entrySet().removeIf(entry -> (Long)entry.getValue() < cutoff);
    }

    private void handleIncomingMessage(String message) {
        try {
            long startedAt;
            JsonObject json = JsonParser.parseString((String)message).getAsJsonObject();
            if (!json.has("type") || !"play_emotion".equals(json.get("type").getAsString())) {
                return;
            }
            if (!json.has("serverId") || !this.getCurrentServerId().equals(json.get("serverId").getAsString())) {
                return;
            }
            if (EmotionWheelManager.mc.field_1687 == null || !json.has("playerUuid") || !json.has("emotion")) {
                return;
            }
            UUID uuid = UUID.fromString(json.get("playerUuid").getAsString());
            if (EmotionWheelManager.mc.field_1724 != null && EmotionWheelManager.mc.field_1724.method_5667().equals(uuid)) {
                return;
            }
            this.knownClientPlayers.put(uuid, System.currentTimeMillis());
            for (Consumer<JsonObject> relayListener : this.relayListeners) {
                relayListener.accept(json);
            }
            String emotionValue = json.get("emotion").getAsString();
            if (PRESENCE_EMOTION.equals(emotionValue)) {
                return;
            }
            EmotionType type = EmotionType.valueOf(emotionValue);
            long l = startedAt = json.has("startedAt") ? json.get("startedAt").getAsLong() : System.currentTimeMillis();
            if (this.applyEmotionToPlayer(uuid, type, startedAt)) {
                return;
            }
            this.pendingEmotions.put(uuid, new PendingEmotion(type, startedAt));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void applyPendingEmotions() {
        if (this.pendingEmotions.isEmpty() || EmotionWheelManager.mc.field_1687 == null) {
            return;
        }
        Iterator<Map.Entry<UUID, PendingEmotion>> iterator = this.pendingEmotions.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, PendingEmotion> entry = iterator.next();
            PendingEmotion pending = entry.getValue();
            if (pending.isExpired()) {
                iterator.remove();
                continue;
            }
            if (!this.applyEmotionToPlayer(entry.getKey(), pending.type(), pending.startedAt())) continue;
            iterator.remove();
        }
    }

    private boolean applyEmotionToPlayer(UUID uuid, EmotionType type, long startedAt) {
        for (class_1657 player : EmotionWheelManager.mc.field_1687.method_18456()) {
            if (!player.method_5667().equals(uuid)) continue;
            this.startEmotion(player, type, false, startedAt);
            return true;
        }
        return false;
    }

    private void startEmotion(class_1657 player, EmotionType emotionType, boolean local, long startedAt) {
        class_5498 previous = class_5498.field_26664;
        if (local) {
            ActiveEmotion previousLocal = this.activeEmotions.get(player.method_5667());
            previous = previousLocal != null && previousLocal.local ? previousLocal.previousPerspective : EmotionWheelManager.mc.field_1690.method_31044();
            EmotionWheelManager.mc.field_1690.method_31043(class_5498.field_26666);
        }
        this.activeEmotions.put(player.method_5667(), new ActiveEmotion(emotionType, startedAt, previous, local));
    }

    private String getCurrentServerId() {
        if (mc.method_1542()) {
            class_1132 server = mc.method_1576();
            return server != null ? "singleplayer:" + server.method_27728().method_150() : "singleplayer";
        }
        return mc.method_1558() != null ? EmotionWheelManager.mc.method_1558().field_3761 : "unknown";
    }

    private static final class ActiveEmotion {
        private final EmotionType type;
        private final long startedAt;
        private final class_5498 previousPerspective;
        private final boolean local;

        private ActiveEmotion(EmotionType type, long startedAt, class_5498 previousPerspective, boolean local) {
            this.type = type;
            this.startedAt = startedAt;
            this.previousPerspective = previousPerspective;
            this.local = local;
        }

        private long getElapsedMs() {
            return System.currentTimeMillis() - this.startedAt;
        }

        private boolean isExpired() {
            return this.getElapsedMs() >= this.type.getDurationMs();
        }

        private float getBlend() {
            long elapsed = this.getElapsedMs();
            long remaining = Math.max(0L, this.type.getDurationMs() - elapsed);
            float fadeIn = class_3532.method_15363((float)((float)elapsed / 300.0f), (float)0.0f, (float)1.0f);
            float fadeOut = class_3532.method_15363((float)((float)remaining / 300.0f), (float)0.0f, (float)1.0f);
            float blend = Math.min(fadeIn, fadeOut);
            return blend * blend * (3.0f - 2.0f * blend);
        }
    }

    private static final class PendingEmotion {
        private final EmotionType type;
        private final long startedAt;

        private PendingEmotion(EmotionType type, long startedAt) {
            this.type = type;
            this.startedAt = startedAt;
        }

        private EmotionType type() {
            return this.type;
        }

        private long startedAt() {
            return this.startedAt;
        }

        private boolean isExpired() {
            return System.currentTimeMillis() - this.startedAt >= this.type.getDurationMs();
        }
    }
}
