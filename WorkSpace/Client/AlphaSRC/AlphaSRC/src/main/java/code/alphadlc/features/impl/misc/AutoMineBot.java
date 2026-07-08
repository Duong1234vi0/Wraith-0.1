/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_2382
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_3532
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_7439
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.display.hud.Notifications;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.math.time.StopWatch;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_7439;

public class AutoMineBot
extends Module {
    private static final String API_URL = "http://85.208.139.128:8000/api/mines/nearest";
    private static final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5L)).build();
    private final StopWatch commandDelay = new StopWatch();
    private final StopWatch warpDelay = new StopWatch();
    private final StopWatch replaceCommandDelay = new StopWatch();
    private final StopWatch updateTimer = new StopWatch();
    private final StopWatch doneBuildingTimer = new StopWatch();
    private final StopWatch baritoneWorkTimer = new StopWatch();
    private final StopWatch anarchyTimer = new StopWatch();
    private static final long COMMAND_DELAY_MS = 500L;
    private static final long WARP_DELAY_MS = 7100L;
    private static final long REPLACE_COMMAND_INTERVAL_MS = 1000L;
    private static final long UPDATE_INTERVAL_MS = 2000L;
    private static final long DONE_BUILDING_TIMEOUT_MS = 5000L;
    private static final long BARITONE_WORK_TIMEOUT_MS = 2000L;
    private static final long ANARCHY_TIMEOUT_MS = 40000L;
    private static final double NEARBY_RADIUS = 30.0;
    private static final class_2338 SEL_1 = new class_2338(-96, 84, 38);
    private static final class_2338 SEL_2 = new class_2338(-74, 77, 16);
    private static final class_2350[] HORIZONTAL_FACES = new class_2350[]{class_2350.field_11043, class_2350.field_11035, class_2350.field_11039, class_2350.field_11034};
    private State currentState = State.IDLE;
    private String currentAnarchy = "";
    private boolean sel1Set = false;
    private boolean sel2Set = false;
    private boolean baritoneWorking = false;
    private boolean doneBuildingReceived = false;
    private long lastDoneBuildingTime = 0L;
    private class_243 goalMovementVec = null;
    private class_243 calculatedGoalPartH = null;
    private final List<class_243> oldPositions = new ArrayList<class_243>();
    private boolean holdForward = false;
    private boolean holdJump = false;

    public AutoMineBot() {
        super("AutoMineBot", "AutoMineBot", ModuleCategory.MISC);
        this.commandDelay.reset();
        this.warpDelay.reset();
        this.replaceCommandDelay.reset();
        this.updateTimer.reset();
    }

    @Override
    public void activate() {
        super.activate();
        ChatMessage.brandmessage("AutoMineBot \u0432\u043a\u043b\u044e\u0447\u0435\u043d. \u041d\u0430\u0447\u0438\u043d\u0430\u044e \u0440\u0430\u0431\u043e\u0442\u0443...");
        this.currentState = State.SETTING_SEL;
        this.currentAnarchy = "";
        this.sel1Set = false;
        this.sel2Set = false;
        this.baritoneWorking = false;
        this.doneBuildingReceived = false;
        this.lastDoneBuildingTime = 0L;
        this.commandDelay.reset();
        this.warpDelay.reset();
        this.replaceCommandDelay.reset();
        this.updateTimer.reset();
        this.doneBuildingTimer.reset();
        this.baritoneWorkTimer.reset();
        this.anarchyTimer.reset();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        ChatMessage.brandmessage("AutoMineBot \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d.");
        this.currentState = State.IDLE;
    }

    @EventHandler
    public void onInput(InputEvent e) {
        if (this.holdForward) {
            e.setForward(1.0f);
            e.setStrafe(0.0f);
        }
        if (this.holdJump) {
            e.setJump(true);
        }
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (AutoMineBot.mc.field_1724 == null || AutoMineBot.mc.field_1687 == null) {
            return;
        }
        this.holdForward = false;
        this.holdJump = false;
        if (this.currentState == State.WARPING || this.currentState == State.WAITING_WARP) {
            class_243 selCenter;
            this.goalMovementVec = selCenter = class_243.method_24953((class_2382)SEL_1).method_1019(class_243.method_24953((class_2382)SEL_2)).method_1021(0.5);
            this.moveToGoal(this.goalMovementVec);
        }
        switch (this.currentState.ordinal()) {
            case 0: {
                break;
            }
            case 1: {
                if (!this.sel1Set) {
                    this.sendCommand("#sel 1 -96 84 38");
                    this.sendCommand("#set smoothLook true");
                    this.sendCommand("#set smoothLookTicks 20");
                    this.sel1Set = true;
                    this.updateTimer.reset();
                    break;
                }
                if (!this.sel2Set && this.updateTimer.finished(500.0)) {
                    this.sendCommand("#sel 2 -74 77 16");
                    this.sendCommand("#set blocksToAvoidBreaking andesite,stone_bricks");
                    this.sel2Set = true;
                    this.updateTimer.reset();
                    break;
                }
                if (!this.sel1Set || !this.sel2Set || !this.updateTimer.finished(500.0)) break;
                this.currentState = State.GETTING_ANARCHY;
                this.updateTimer.reset();
                break;
            }
            case 2: {
                if (!this.updateTimer.finished(2000.0)) break;
                this.updateTimer.reset();
                this.fetchNearestAnarchy();
                break;
            }
            case 3: {
                if (!this.commandDelay.finished(500.0)) break;
                this.commandDelay.reset();
                this.currentState = State.WAITING_WARP;
                this.sendCommand("#set allowPlace false");
                this.sendCommand("/warp mine");
                this.warpDelay.reset();
                this.anarchyTimer.reset();
                break;
            }
            case 4: {
                if (this.isNearbySel()) {
                    this.currentState = State.REPLACING;
                    this.replaceCommandDelay.reset();
                    break;
                }
                this.currentState = State.WARPING;
                break;
            }
            case 5: {
                if (!this.warpDelay.finished(7100.0)) break;
                this.warpDelay.reset();
                this.currentState = State.REPLACING;
                this.replaceCommandDelay.reset();
                break;
            }
            case 7: {
                if (this.anarchyTimer.finished(40000.0)) {
                    this.currentState = State.SWITCHING;
                    this.anarchyTimer.reset();
                    break;
                }
                if (this.baritoneWorking && this.baritoneWorkTimer.finished(2000.0)) {
                    this.baritoneWorking = false;
                    this.baritoneWorkTimer.reset();
                }
                if (!this.replaceCommandDelay.finished(1000.0)) break;
                this.replaceCommandDelay.reset();
                if (this.baritoneWorking) break;
                this.sendCommand("#sel replace minecraft:diamond_ore minecraft:air");
                this.baritoneWorking = true;
                this.baritoneWorkTimer.reset();
                break;
            }
            case 8: {
                long timeSinceLastDone;
                if (this.anarchyTimer.finished(40000.0)) {
                    this.currentState = State.SWITCHING;
                    this.anarchyTimer.reset();
                    break;
                }
                if (!this.doneBuildingReceived || this.lastDoneBuildingTime <= 0L || (timeSinceLastDone = System.currentTimeMillis() - this.lastDoneBuildingTime) <= 5000L) break;
                this.currentState = State.SWITCHING;
                this.doneBuildingReceived = false;
                this.baritoneWorking = false;
                this.lastDoneBuildingTime = 0L;
                break;
            }
            case 9: {
                if (!this.updateTimer.finished(1000.0)) break;
                this.updateTimer.reset();
                this.currentState = State.GETTING_ANARCHY;
                this.currentAnarchy = "";
            }
        }
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        class_7439 gameMessage;
        String message;
        if (e.getType() != PacketEvent.Type.RECEIVE) {
            return;
        }
        class_2596<?> class_25962 = e.getPacket();
        if (class_25962 instanceof class_7439 && (message = (gameMessage = (class_7439)class_25962).comp_763().getString()).contains("[Baritone]")) {
            if (message.contains("Filling now") && this.currentState == State.REPLACING) {
                this.baritoneWorking = true;
                this.baritoneWorkTimer.reset();
            }
            if (message.contains("Done building")) {
                long currentTime = System.currentTimeMillis();
                this.baritoneWorking = false;
                if (this.currentState == State.REPLACING) {
                    this.currentState = State.WAITING_DONE;
                    this.doneBuildingReceived = true;
                    this.lastDoneBuildingTime = currentTime;
                    this.doneBuildingTimer.reset();
                } else if (this.currentState == State.WAITING_DONE) {
                    this.lastDoneBuildingTime = currentTime;
                    this.doneBuildingTimer.reset();
                }
            }
        }
    }

    private boolean isNearbySel() {
        if (AutoMineBot.mc.field_1724 == null) {
            return false;
        }
        class_2338 playerPos = AutoMineBot.mc.field_1724.method_24515();
        class_243 playerVec = AutoMineBot.mc.field_1724.method_19538();
        double distToSel1 = playerVec.method_1022(class_243.method_24953((class_2382)SEL_1));
        double distToSel2 = playerVec.method_1022(class_243.method_24953((class_2382)SEL_2));
        boolean inSel1Area = distToSel1 <= 30.0;
        boolean inSel2Area = distToSel2 <= 30.0;
        int minX = Math.min(SEL_1.method_10263(), SEL_2.method_10263());
        int maxX = Math.max(SEL_1.method_10263(), SEL_2.method_10263());
        int minY = Math.min(SEL_1.method_10264(), SEL_2.method_10264());
        int maxY = Math.max(SEL_1.method_10264(), SEL_2.method_10264());
        int minZ = Math.min(SEL_1.method_10260(), SEL_2.method_10260());
        int maxZ = Math.max(SEL_1.method_10260(), SEL_2.method_10260());
        boolean inSelArea = (double)playerPos.method_10263() >= (double)minX - 30.0 && (double)playerPos.method_10263() <= (double)maxX + 30.0 && (double)playerPos.method_10264() >= (double)minY - 30.0 && (double)playerPos.method_10264() <= (double)maxY + 30.0 && (double)playerPos.method_10260() >= (double)minZ - 30.0 && (double)playerPos.method_10260() <= (double)maxZ + 30.0;
        return inSel1Area || inSel2Area || inSelArea;
    }

    private void fetchNearestAnarchy() {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL)).timeout(Duration.ofSeconds(5L)).GET().build();
            ((CompletableFuture)httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(response -> {
                if (response.statusCode() == 200) {
                    try {
                        String responseBody = (String)response.body();
                        JsonObject json = JsonParser.parseString((String)responseBody).getAsJsonObject();
                        boolean found = json.get("found").getAsBoolean();
                        if (found) {
                            String mineInfo = json.get("mine").getAsString();
                            Pattern pattern = Pattern.compile("/an(\\d+)");
                            Matcher matcher = pattern.matcher(mineInfo);
                            if (matcher.find()) {
                                String anarchyNum = matcher.group(1);
                                String timeToAutoMine = "";
                                Pattern timePattern = Pattern.compile("(\\d{1,2}:\\d{2})");
                                Matcher timeMatcher = timePattern.matcher(mineInfo);
                                if (timeMatcher.find()) {
                                    timeToAutoMine = timeMatcher.group(1);
                                }
                                String finalAnarchyNum = anarchyNum;
                                String finalTimeToAutoMine = timeToAutoMine;
                                mc.execute(() -> {
                                    if (!finalAnarchyNum.equals(this.currentAnarchy)) {
                                        this.currentAnarchy = finalAnarchyNum;
                                        this.currentState = State.TELEPORTING;
                                        this.sendCommand("/an" + finalAnarchyNum);
                                        this.commandDelay.reset();
                                        if (!finalTimeToAutoMine.isEmpty()) {
                                            ChatMessage.brandmessage("\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u0443\u044e\u0441\u044c \u043d\u0430 \u0430\u043d\u0430\u0440\u0445\u0438\u044e " + finalAnarchyNum + ", \u0434\u043e \u0430\u0432\u0442\u043e-\u0448\u0430\u0445\u0442\u044b " + finalTimeToAutoMine);
                                            Notifications.getInstance().addList("\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u0443\u044e\u0441\u044c \u043d\u0430 \u0430\u043d\u0430\u0440\u0445\u0438\u044e " + finalAnarchyNum + ", \u0434\u043e \u0430\u0432\u0442\u043e-\u0448\u0430\u0445\u0442\u044b " + finalTimeToAutoMine + " \u0441\u0435\u043a\u0443\u043d\u0434", 5000);
                                        } else {
                                            ChatMessage.brandmessage("\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u0443\u044e\u0441\u044c \u043d\u0430 \u0430\u043d\u0430\u0440\u0445\u0438\u044e " + finalAnarchyNum);
                                            Notifications.getInstance().addList("\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u0443\u044e\u0441\u044c \u043d\u0430 \u0430\u043d\u0430\u0440\u0445\u0438\u044e " + finalAnarchyNum, 3000);
                                        }
                                    }
                                });
                            }
                        }
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
            })).exceptionally(ex -> null);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void moveToGoal(class_243 goal) {
        boolean canJump;
        if (goal == null || AutoMineBot.mc.field_1724 == null || AutoMineBot.mc.field_1687 == null) {
            return;
        }
        Float moveYaw = this.getYawMovementToGoalVec(goal, 2);
        if (moveYaw == null) {
            return;
        }
        boolean canForward = MathAngle.computeAngleDifference(AutoMineBot.mc.field_1724.method_36454(), moveYaw.floatValue()) < 45.0f && AutoMineBot.mc.field_1724.method_5707(goal) > 0.5;
        boolean bl = canJump = canForward && AutoMineBot.mc.field_1724.method_6029() > 0.14f && AutoMineBot.mc.field_1724.field_5976 && AutoMineBot.mc.field_1724.method_6058() != class_1268.field_5808;
        if (canForward) {
            this.holdForward = true;
            float[] rot = this.rotationsToVec(goal);
            AutoMineBot.mc.field_1724.method_36456(class_3532.method_17821((float)0.35f, (float)AutoMineBot.mc.field_1724.method_36454(), (float)rot[0]));
            if (canJump) {
                this.holdJump = true;
            }
        }
    }

    private Float getYawMovementToGoalVec(class_243 goal, int scanRayRange) {
        if (goal == null) {
            return null;
        }
        float predictValue = 1.5f;
        class_243 scanAt = AutoMineBot.mc.field_1724.method_19538().method_1031(AutoMineBot.mc.field_1724.method_18798().field_1352 * (double)predictValue, 0.001, AutoMineBot.mc.field_1724.method_18798().field_1350 * (double)predictValue);
        ArrayList<class_243> freeRays = new ArrayList<class_243>();
        int stepDeg = 45;
        double offsetAABB = 0.5;
        for (float yaw = 0.0f; yaw < 360.0f; yaw += (float)stepDeg) {
            class_243 hitPos;
            double rad = Math.toRadians(yaw);
            class_243 end = scanAt.method_1031(-Math.sin(rad) * (double)scanRayRange, 0.0, Math.cos(rad) * (double)scanRayRange);
            class_3965 result = AutoMineBot.mc.field_1687.method_17742(new class_3959(scanAt, end, class_3959.class_3960.field_17558, class_3959.class_242.field_1347, (class_1297)AutoMineBot.mc.field_1724));
            if (result != null && result.method_17783() == class_239.class_240.field_1332) continue;
            class_243 class_2432 = hitPos = result != null ? result.method_17784() : end;
            if (hitPos.method_1027() < 1.0) continue;
            boolean blocked = false;
            for (class_2350 dir : HORIZONTAL_FACES) {
                class_243 off = new class_243((double)dir.method_10148(), 0.0, (double)dir.method_10165()).method_1021(offsetAABB);
                if (AutoMineBot.mc.field_1687.method_8320(class_2338.method_49638((class_2374)hitPos.method_1019(off))).method_26215()) continue;
                blocked = true;
                break;
            }
            if (blocked) continue;
            freeRays.add(hitPos);
        }
        if (freeRays.isEmpty()) {
            return null;
        }
        this.calculatedGoalPartH = (class_243)freeRays.get(0);
        freeRays.removeIf(n -> this.oldPositions.stream().anyMatch(old -> old.method_1025(n) < 0.0225));
        if (freeRays.size() > 1) {
            freeRays.sort(Comparator.comparingDouble(a -> a.method_1022(goal)));
        }
        if (!freeRays.isEmpty()) {
            this.calculatedGoalPartH = (class_243)freeRays.get(0);
        }
        if (this.calculatedGoalPartH == null) {
            return null;
        }
        if (this.oldPositions.size() > 40) {
            this.oldPositions.remove(0);
        }
        this.oldPositions.add(this.calculatedGoalPartH);
        return Float.valueOf(MathAngle.fromVec3d(this.calculatedGoalPartH.method_1020(scanAt)).getYaw());
    }

    private float[] rotationsToVec(class_243 target) {
        class_243 eye = AutoMineBot.mc.field_1724.method_33571();
        double dx = target.field_1352 - eye.field_1352;
        double dy = target.field_1351 - (eye.field_1351 + 0.5);
        double dz = target.field_1350 - eye.field_1350;
        double distXZ = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float)(Math.atan2(dz, dx) * 57.29577951308232) - 90.0f;
        float pitch = (float)(-(Math.atan2(dy, distXZ) * 57.29577951308232));
        return new float[]{class_3532.method_15393((float)yaw), class_3532.method_15363((float)pitch, (float)-90.0f, (float)90.0f)};
    }

    private void sendCommand(String command) {
        if (AutoMineBot.mc.field_1724 != null && AutoMineBot.mc.field_1724.field_3944 != null) {
            AutoMineBot.mc.field_1724.field_3944.method_45729(command);
        }
    }

    private static enum State {
        IDLE,
        SETTING_SEL,
        GETTING_ANARCHY,
        TELEPORTING,
        WAITING_WARP,
        WARPING,
        WAITING_REPLACE,
        REPLACING,
        WAITING_DONE,
        SWITCHING;

    }
}
