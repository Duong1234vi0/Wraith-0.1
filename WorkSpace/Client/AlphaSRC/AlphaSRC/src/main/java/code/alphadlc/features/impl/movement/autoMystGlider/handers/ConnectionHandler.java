/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1703
 *  net.minecraft.class_1707
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 *  net.minecraft.class_3965
 */
package code.alphadlc.features.impl.movement.autoMystGlider.handers;

import code.alphadlc.features.impl.movement.autoMystGlider.handers.StealHandler;
import code.alphadlc.features.impl.movement.autoMystGlider.parsers.SpookyParser;
import code.alphadlc.features.impl.movement.autoMystGlider.receivers.AutoMystGliderModule;
import code.alphadlc.features.impl.movement.autoMystGlider.states.ConnectionState;
import code.alphadlc.features.impl.movement.autoMystGlider.states.GlidePhase;
import code.alphadlc.features.impl.movement.autoMystGlider.states.GlideState;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import net.minecraft.class_1268;
import net.minecraft.class_1703;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3965;

public class ConnectionHandler
implements QuickImports {
    private final GlideState glideState;
    private final ConnectionState connection;
    private final AutoMystGliderModule module;
    private long startTime = -1L;
    private long elytraChangeTime;
    private boolean elytraSwaped = false;
    private boolean homeTeleported = false;
    private long homeTpTime = -1L;
    private long lastDropTime = -1L;
    private boolean eventDelaySent = false;
    private long eventDelayTime = 0L;
    private boolean apiFetched = false;
    private boolean apiReady = false;
    private int nextAnarchy = -1;
    private long apiFetchTime = 0L;
    private long lastPhaseLogTime = 0L;
    private boolean rtpSent = false;
    private boolean slotClicked = false;

    public ConnectionHandler(GlideState glideState, StealHandler stealHandler, AutoMystGliderModule module) {
        this.glideState = glideState;
        this.module = module;
        this.connection = new ConnectionState(glideState, stealHandler);
    }

    public void onTick() {
        GlidePhase phase = this.connection.getPhase();
        if (System.currentTimeMillis() - this.lastPhaseLogTime > 5000L) {
            long elapsed = System.currentTimeMillis() - this.connection.getPhaseStartTime();
            ChatMessage.brandmessage("[AutoMyst] \u0424\u0430\u0437\u0430: " + String.valueOf((Object)phase) + " | connected=" + this.connection.isConnected() + " | elapsed=" + elapsed / 1000L + "s");
            this.lastPhaseLogTime = System.currentTimeMillis();
        }
        switch (phase) {
            case FLYING: {
                this.handleFlying();
                break;
            }
            case WAITING_REOPEN: {
                this.handleWaitingReopen();
                break;
            }
            case LOOTING: {
                break;
            }
            case HUB_TO_HOME: {
                this.handleHubToHome();
                break;
            }
            case AT_HOME: {
                this.handleAtHome();
                break;
            }
            case HUB_TO_NEXT: {
                this.handleHubToNext();
                break;
            }
            case WAITING_COORDS: {
                this.handleWaitingCoords();
            }
        }
    }

    private void handleFlying() {
        if (this.connection.successfully() && this.isPlayerInBlocks() && this.isPlayer4BlocksBelowTarget() && this.connection.isConnected()) {
            if (this.startTime == -1L) {
                this.startTime = System.currentTimeMillis();
            }
            if (System.currentTimeMillis() - this.startTime >= 700L) {
                this.connection.setAnarchy(SpookyParser.parseAnarchy());
                this.connection.setRemainingTime(SpookyParser.parseRemainingTime());
                this.connection.getStealHandler().onDisconnect();
                this.connection.hub(GlidePhase.WAITING_REOPEN);
                ChatMessage.brandmessage("\u0424\u0430\u0437\u0430: \u043e\u0436\u0438\u0434\u0430\u043d\u0438\u0435 \u0440\u0435\u043e\u043f\u0435\u043d\u0430 (\u0430\u043d " + this.connection.getAnarchy() + ", ~" + this.connection.getRemainingTime() / 1000L + " \u0441\u0435\u043a)");
                this.startTime = -1L;
            }
        } else {
            this.startTime = -1L;
        }
    }

    private void handleWaitingReopen() {
        if (System.currentTimeMillis() - this.connection.getLeaveTime() > this.connection.getRemainingTime() && this.connection.getAnarchy() != -1) {
            this.connection.getStealHandler().onConnect();
            this.connection.joinAnarchy(this.connection.getAnarchy(), GlidePhase.LOOTING);
            ChatMessage.brandmessage("\u0424\u0430\u0437\u0430: \u0437\u0430\u0445\u043e\u0434\u0438\u043c \u043d\u0430 \u0430\u043d" + this.connection.getAnarchy() + " \u0434\u043b\u044f \u043b\u0443\u0442\u0430");
        }
    }

    private void handleHubToHome() {
        int homeAnarchy;
        long elapsed = System.currentTimeMillis() - this.connection.getPhaseStartTime();
        if (elapsed > 3000L && (homeAnarchy = this.getHomeAnarchy()) != -1) {
            this.connection.joinAnarchy(homeAnarchy, GlidePhase.AT_HOME);
            this.homeTeleported = false;
            this.homeTpTime = -1L;
            this.lastDropTime = -1L;
            ChatMessage.brandmessage("\u0424\u0430\u0437\u0430: \u0437\u0430\u0445\u043e\u0434\u0438\u043c \u043d\u0430 \u0434\u043e\u043c\u0430\u0448\u043d\u044e\u044e \u0430\u043d" + homeAnarchy);
        }
    }

    private void handleAtHome() {
        long elapsed = System.currentTimeMillis() - this.connection.getPhaseStartTime();
        if (elapsed < 3000L) {
            return;
        }
        if (!this.homeTeleported) {
            String home = this.module.getHomeName().getText();
            mc.method_1562().method_45731("home " + home);
            this.homeTeleported = true;
            this.homeTpTime = System.currentTimeMillis();
            ChatMessage.brandmessage("\u0424\u0430\u0437\u0430: \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u0443\u0435\u043c\u0441\u044f /home " + home);
            return;
        }
        if (System.currentTimeMillis() - this.homeTpTime < 3000L) {
            return;
        }
        class_1703 class_17032 = ConnectionHandler.mc.field_1724.field_7512;
        if (class_17032 instanceof class_1707) {
            boolean allEmpty;
            class_1707 handler = (class_1707)class_17032;
            for (class_1735 slot : handler.field_7761) {
                if (slot.field_7871 != ConnectionHandler.mc.field_1724.method_31548() || !slot.method_7681() || System.currentTimeMillis() - this.lastDropTime <= 150L) continue;
                InventoryTask.clickSlot(slot, 0, class_1713.field_7794, true);
                this.lastDropTime = System.currentTimeMillis();
                break;
            }
            if (allEmpty = handler.field_7761.stream().filter(s -> s.field_7871 == ConnectionHandler.mc.field_1724.method_31548()).noneMatch(class_1735::method_7681)) {
                ConnectionHandler.mc.field_1755.method_25419();
                this.goToNextPhase();
            }
        } else if (ConnectionHandler.mc.field_1755 == null) {
            class_2338 chest = this.findNearestChest();
            if (chest != null && System.currentTimeMillis() - this.lastDropTime > 800L) {
                class_243 center = class_243.method_24953((class_2382)chest).method_1031(0.2, 0.375, 0.2);
                class_2350 side = class_2350.method_10142((double)(center.field_1352 - ConnectionHandler.mc.field_1724.method_23317()), (double)(center.field_1351 - ConnectionHandler.mc.field_1724.method_23318()), (double)(center.field_1350 - ConnectionHandler.mc.field_1724.method_23321())).method_10153();
                ConnectionHandler.mc.field_1761.method_2896(ConnectionHandler.mc.field_1724, class_1268.field_5808, new class_3965(center, side, chest, false));
                ConnectionHandler.mc.field_1724.method_6104(class_1268.field_5808);
                this.lastDropTime = System.currentTimeMillis();
            } else if (chest == null) {
                this.goToNextPhase();
            }
        }
        if (System.currentTimeMillis() - this.homeTpTime > 30000L) {
            if (ConnectionHandler.mc.field_1755 != null) {
                ConnectionHandler.mc.field_1755.method_25419();
            }
            this.goToNextPhase();
        }
    }

    private void goToNextPhase() {
        this.apiFetched = false;
        this.apiReady = false;
        this.nextAnarchy = -1;
        this.connection.hub(GlidePhase.HUB_TO_NEXT);
        ChatMessage.brandmessage("\u0424\u0430\u0437\u0430: \u0440\u0435\u0441\u044b \u0441\u043a\u0438\u043d\u0443\u0442\u044b, \u0438\u0449\u0435\u043c \u0441\u043b\u0435\u0434\u0443\u044e\u0449\u0438\u0439 \u0438\u0432\u0435\u043d\u0442");
    }

    private void handleWaitingCoords() {
        long elapsed = System.currentTimeMillis() - this.connection.getPhaseStartTime();
        if (elapsed < 3000L) {
            return;
        }
        if (!this.eventDelaySent) {
            mc.method_1562().method_45731("event delay");
            mc.method_1562().method_45731("rtp");
            this.eventDelaySent = true;
            this.eventDelayTime = System.currentTimeMillis();
            this.rtpSent = false;
            this.slotClicked = false;
            ChatMessage.brandmessage("[AutoMyst] \u041e\u0442\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u043e /event delay \u0438 /rtp, \u0436\u0434\u0451\u043c \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b...");
            return;
        }
        if (!this.slotClicked && System.currentTimeMillis() - this.eventDelayTime > 100L) {
            InventoryTask.clickSlot(13, 0, class_1713.field_7794, false);
            this.slotClicked = true;
            ChatMessage.brandmessage("[AutoMyst] \u041a\u043b\u0438\u043a\u043d\u0443\u043b\u0438 \u043f\u043e \u0441\u043b\u043e\u0442\u0443 15");
        }
    }

    private void handleHubToNext() {
        long sinceApi;
        long elapsed = System.currentTimeMillis() - this.connection.getPhaseStartTime();
        if (elapsed > 2000L && !this.apiFetched) {
            this.apiFetched = true;
            this.apiFetchTime = System.currentTimeMillis();
            ChatMessage.brandmessage("[AutoMyst] \u0417\u0430\u043f\u0440\u0430\u0448\u0438\u0432\u0430\u0435\u043c API...");
            new Thread(() -> {
                SpookyParser.fetchFromApi();
                this.nextAnarchy = SpookyParser.getCachedAnarchy();
                this.apiReady = true;
                ChatMessage.brandmessage("[AutoMyst] API \u043e\u0442\u0432\u0435\u0442\u0438\u043b: \u0430\u043d\u0430\u0440\u0445\u0438\u044f=" + this.nextAnarchy);
            }, "SpookyAPI-Fetch").start();
        }
        if (this.apiReady && this.nextAnarchy != -1) {
            ChatMessage.brandmessage("[AutoMyst] \u0417\u0430\u0445\u043e\u0434\u0438\u043c \u043d\u0430 \u0430\u043d" + this.nextAnarchy + ", \u0436\u0434\u0451\u043c \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b \u0438\u0437 \u0447\u0430\u0442\u0430");
            this.eventDelaySent = false;
            this.rtpSent = false;
            this.slotClicked = false;
            this.connection.joinAnarchy(this.nextAnarchy, GlidePhase.WAITING_COORDS);
            this.apiFetched = false;
            this.apiReady = false;
        }
        if (this.apiReady && this.nextAnarchy == -1 && (sinceApi = System.currentTimeMillis() - this.apiFetchTime) > 30000L) {
            this.apiFetched = false;
            this.apiReady = false;
            ChatMessage.brandmessage("[AutoMyst] \u041d\u0435\u0442 \u0438\u0432\u0435\u043d\u0442\u043e\u0432, \u043f\u043e\u0432\u0442\u043e\u0440\u043d\u044b\u0439 \u0437\u0430\u043f\u0440\u043e\u0441 API...");
        }
    }

    private int getHomeAnarchy() {
        try {
            return Integer.parseInt(this.module.getAnarchyText().getText());
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean isPlayerInBlocks() {
        class_2338 playerPos = ConnectionHandler.mc.field_1724.method_24515();
        return ConnectionHandler.mc.field_1724.method_37908().method_8320(playerPos).method_51367();
    }

    private boolean isPlayer4BlocksBelowTarget() {
        double targetY = this.glideState.vec3d().method_10214();
        double playerY = ConnectionHandler.mc.field_1724.method_23318();
        return playerY <= targetY - 3.0;
    }

    private class_2338 findNearestChest() {
        class_2338 playerPos = ConnectionHandler.mc.field_1724.method_24515();
        int range = 5;
        class_2338.class_2339 nearest = new class_2338.class_2339(0, 0, 0);
        double nearestDistSq = Double.MAX_VALUE;
        for (class_2338 pos : class_2338.method_10097((class_2338)playerPos.method_10069(-range, -range, -range), (class_2338)playerPos.method_10069(range, range, range))) {
            double distSq;
            class_2680 state = ConnectionHandler.mc.field_1687.method_8320(pos);
            if (!state.method_27852(class_2246.field_10034) && !state.method_27852(class_2246.field_10380) && !state.method_27852(class_2246.field_10443) || !((distSq = ConnectionHandler.mc.field_1724.method_5707(class_243.method_24953((class_2382)pos))) < nearestDistSq) || !(distSq <= 20.25)) continue;
            nearestDistSq = distSq;
            nearest.method_10101((class_2382)pos);
        }
        return nearestDistSq < Double.MAX_VALUE ? nearest.method_10062() : null;
    }

    public void reload() {
        this.connection.reload();
        this.elytraSwaped = false;
        this.elytraChangeTime = -1L;
        this.homeTeleported = false;
        this.homeTpTime = -1L;
        this.lastDropTime = -1L;
        this.apiFetched = false;
        this.apiReady = false;
        this.nextAnarchy = -1;
        this.eventDelaySent = false;
        this.rtpSent = false;
        this.slotClicked = false;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setElytraChangeTime(long elytraChangeTime) {
        this.elytraChangeTime = elytraChangeTime;
    }

    public void setElytraSwaped(boolean elytraSwaped) {
        this.elytraSwaped = elytraSwaped;
    }

    public void setHomeTeleported(boolean homeTeleported) {
        this.homeTeleported = homeTeleported;
    }

    public void setHomeTpTime(long homeTpTime) {
        this.homeTpTime = homeTpTime;
    }

    public void setLastDropTime(long lastDropTime) {
        this.lastDropTime = lastDropTime;
    }

    public void setEventDelaySent(boolean eventDelaySent) {
        this.eventDelaySent = eventDelaySent;
    }

    public void setEventDelayTime(long eventDelayTime) {
        this.eventDelayTime = eventDelayTime;
    }

    public void setApiFetched(boolean apiFetched) {
        this.apiFetched = apiFetched;
    }

    public void setApiReady(boolean apiReady) {
        this.apiReady = apiReady;
    }

    public void setNextAnarchy(int nextAnarchy) {
        this.nextAnarchy = nextAnarchy;
    }

    public void setApiFetchTime(long apiFetchTime) {
        this.apiFetchTime = apiFetchTime;
    }

    public void setLastPhaseLogTime(long lastPhaseLogTime) {
        this.lastPhaseLogTime = lastPhaseLogTime;
    }

    public void setRtpSent(boolean rtpSent) {
        this.rtpSent = rtpSent;
    }

    public void setSlotClicked(boolean slotClicked) {
        this.slotClicked = slotClicked;
    }

    public GlideState getGlideState() {
        return this.glideState;
    }

    public ConnectionState getConnection() {
        return this.connection;
    }

    public AutoMystGliderModule getModule() {
        return this.module;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getElytraChangeTime() {
        return this.elytraChangeTime;
    }

    public boolean isElytraSwaped() {
        return this.elytraSwaped;
    }

    public boolean isHomeTeleported() {
        return this.homeTeleported;
    }

    public long getHomeTpTime() {
        return this.homeTpTime;
    }

    public long getLastDropTime() {
        return this.lastDropTime;
    }

    public boolean isEventDelaySent() {
        return this.eventDelaySent;
    }

    public long getEventDelayTime() {
        return this.eventDelayTime;
    }

    public boolean isApiFetched() {
        return this.apiFetched;
    }

    public boolean isApiReady() {
        return this.apiReady;
    }

    public int getNextAnarchy() {
        return this.nextAnarchy;
    }

    public long getApiFetchTime() {
        return this.apiFetchTime;
    }

    public long getLastPhaseLogTime() {
        return this.lastPhaseLogTime;
    }

    public boolean isRtpSent() {
        return this.rtpSent;
    }

    public boolean isSlotClicked() {
        return this.slotClicked;
    }
}
