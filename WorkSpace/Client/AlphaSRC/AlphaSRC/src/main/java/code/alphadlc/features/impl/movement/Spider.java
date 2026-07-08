/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1922
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_265
 *  net.minecraft.class_2868
 *  net.minecraft.class_2879
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.PostTickEvent;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.rotations.constructor.LinearConstructor;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConfig;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.simulate.PlayerSimulation;
import code.alphadlc.utils.math.script.Script;
import code.alphadlc.utils.math.task.TaskPriority;
import code.alphadlc.utils.math.time.StopWatch;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_265;
import net.minecraft.class_2868;
import net.minecraft.class_2879;
import net.minecraft.class_3959;
import net.minecraft.class_3965;

public class Spider
extends Module {
    private static final String FT_HAY = "\u0421\u0435\u043d\u043e";
    private static final String FT_KELP = "\u041b\u0430\u043c\u0438\u043d\u0430\u0440\u0438\u044f";
    private static final String FT_LILY_PAD = "\u041a\u0443\u0432\u0448\u0438\u043d\u043a\u0430";
    private static final String FT_RAILS = "\u0420\u0435\u043b\u044c\u0441\u044b";
    private static final String FT_PRESSURE_PLATES = "\u041d\u0430\u0436\u0438\u043c\u043d\u044b\u0435 \u043f\u043b\u0438\u0442\u044b";
    private static final String FT_SEA_PICKLE = "\u041c\u043e\u0440\u0441\u043a\u043e\u0439 \u043e\u0433\u0443\u0440\u0435\u0446";
    private static final String FT_CANDLES = "\u0421\u0432\u0435\u0447\u0438";
    private static final String FT_TURTLE_EGG = "\u0427\u0435\u0440\u0435\u043f\u0430\u0448\u044c\u0435 \u044f\u0439\u0446\u043e";
    private static final String FT_MUSHROOMS = "\u0413\u0440\u0438\u0431\u044b";
    private static final String FT_FLOWERS = "\u0426\u0432\u0435\u0442\u044b";
    private static final String FT_SIGNS = "\u0422\u0430\u0431\u043b\u0438\u0447\u043a\u0438";
    private Script script = new Script();
    private StopWatch stopWatch = new StopWatch();
    private SelectSetting mode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0438\u0440\u0430\u0435\u0442 \u0440\u0435\u0436\u0438\u043c").value("\u0411\u043b\u043e\u043a\u0438", "\u0421\u043f\u0443\u043a\u0438\u0422\u0430\u0439\u043c", "\u0424\u0430\u043d\u0422\u0430\u0439\u043c", "SpookyNEW").selected("\u0411\u043b\u043e\u043a\u0438");
    private MultiSelectSetting funTimeBlocks = new MultiSelectSetting("\u0412\u044b\u0431\u043e\u0440 \u0431\u043b\u043e\u043a\u043e\u0432", "\u041a\u0430\u043a\u0438\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0432 \u0440\u0435\u0436\u0438\u043c\u0435 \u0424\u0430\u043d\u0422\u0430\u0439\u043c").value("\u0421\u0435\u043d\u043e", "\u041b\u0430\u043c\u0438\u043d\u0430\u0440\u0438\u044f", "\u041a\u0443\u0432\u0448\u0438\u043d\u043a\u0430", "\u0420\u0435\u043b\u044c\u0441\u044b", "\u041d\u0430\u0436\u0438\u043c\u043d\u044b\u0435 \u043f\u043b\u0438\u0442\u044b", "\u041c\u043e\u0440\u0441\u043a\u043e\u0439 \u043e\u0433\u0443\u0440\u0435\u0446", "\u0421\u0432\u0435\u0447\u0438", "\u0427\u0435\u0440\u0435\u043f\u0430\u0448\u044c\u0435 \u044f\u0439\u0446\u043e", "\u0413\u0440\u0438\u0431\u044b", "\u0426\u0432\u0435\u0442\u044b", "\u0422\u0430\u0431\u043b\u0438\u0447\u043a\u0438").selected("\u0421\u0435\u043d\u043e", "\u041b\u0430\u043c\u0438\u043d\u0430\u0440\u0438\u044f", "\u041a\u0443\u0432\u0448\u0438\u043d\u043a\u0430").visible(() -> this.mode.isSelected("\u0424\u0430\u043d\u0422\u0430\u0439\u043c"));
    private SliderSettings delay = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", "\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u044f \u0432\u0435\u0434\u0440\u0430").range(0.1f, 1.0f).step(0.001f).setValue(0.4f).visible(() -> this.mode.isSelected("WaterBucket"));
    private SliderSettings funTimeDelay = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0424\u0430\u043d\u0422\u0430\u0439\u043c", "\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043c\u0435\u0436\u0434\u0443 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043a\u043e\u0439 \u0431\u043b\u043e\u043a\u043e\u0432 (\u043c\u0441)").range(50.0f, 500.0f).step(10.0f).setValue(130.0f).visible(() -> this.mode.isSelected("\u0424\u0430\u043d\u0422\u0430\u0439\u043c"));
    private SliderSettings funTimePitch = new SliderSettings("\u0423\u0433\u043e\u043b \u0424\u0430\u043d\u0422\u0430\u0439\u043c", "\u0423\u0433\u043e\u043b \u043d\u0430\u043a\u043b\u043e\u043d\u0430 \u043f\u0440\u0438 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043a\u0435 \u0431\u043b\u043e\u043a\u043e\u0432").range(60.0f, 90.0f).step(1.0f).setValue(75.0f).visible(() -> this.mode.isSelected("\u0424\u0430\u043d\u0422\u0430\u0439\u043c"));
    private BooleanSetting holdShift = new BooleanSetting("\u0417\u0430\u0436\u0438\u043c\u0430\u0442\u044c \u0448\u0438\u0444\u0442", "\u0417\u0430\u0436\u0438\u043c\u0430\u0435\u0442 \u0448\u0438\u0444\u0442 \u043f\u0440\u0438 \u043b\u0430\u0437\u0430\u043d\u0438\u0438").setValue(true).visible(() -> this.mode.isSelected("WaterBucket"));
    private BooleanSetting silentUse = new BooleanSetting("\u0411\u0440\u0430\u0442\u044c \u0432\u0435\u0434\u0440\u043e \u043d\u0435\u0432\u0438\u0434\u0438\u043c\u043e", "\u041c\u0435\u043d\u044f\u0435\u0442 \u0441\u043b\u043e\u0442 \u043d\u0435\u0432\u0438\u0434\u0438\u043c\u043e").setValue(true).visible(() -> this.mode.isSelected("WaterBucket"));
    private BooleanSetting holdSpace = new BooleanSetting("\u041f\u0440\u044b\u0433\u0430\u0442\u044c \u0432 \u043d\u0430\u0447\u0430\u043b\u0435", "\u041f\u0440\u044b\u0433\u0430\u0435\u0442 \u0432 \u043d\u0430\u0447\u0430\u043b\u0435 \u043b\u0430\u0437\u0430\u043d\u0438\u044f").setValue(false).visible(() -> this.mode.isSelected("WaterBucket"));
    private Timer spookyTimer = new Timer();
    private boolean canUseSpooky = true;
    private long lastWallJumpMs = 0L;
    static final long WALL_JUMP_COOLDOWN_MS = 250L;
    private final Random random = new Random();
    private boolean canUseWaterBucket = true;
    private StopWatch useCooldownWaterBucket = new StopWatch();
    private boolean canUseWaterBucket2 = true;
    private StopWatch useCooldownWaterBucket2 = new StopWatch();
    private int prevSlotSpookyNEW = -1;
    private int useItemSequence = 0;
    private double lastWaterY = 0.0;
    private long lastWaterPlaceTime = 0L;

    public Spider() {
        super("Spider", ModuleCategory.MOVEMENT);
        this.setup(this.mode, this.funTimeBlocks, this.delay, this.funTimeDelay, this.funTimePitch, this.holdShift, this.silentUse, this.holdSpace);
    }

    @Override
    public void deactivate() {
        if (Spider.mc.field_1690 != null) {
            Spider.mc.field_1690.field_1832.method_23481(false);
            Spider.mc.field_1690.field_1903.method_23481(false);
        }
        if (this.mode.isSelected("\u0412\u0435\u0434\u0440\u043e \u043b\u0430\u0432\u044b")) {
            this.spookyTimer.cancel();
            this.spookyTimer = new Timer();
            this.canUseSpooky = true;
        }
        if (this.mode.isSelected("WaterBucket")) {
            this.canUseWaterBucket = true;
            this.useCooldownWaterBucket.reset();
        }
        if (this.mode.isSelected("\u0421\u043f\u0443\u043a\u0438\u0422\u0430\u0439\u043c")) {
            this.canUseWaterBucket2 = true;
            this.useCooldownWaterBucket2.reset();
        }
        if (this.mode.isSelected("SpookyNEW")) {
            if (Spider.mc.field_1724 != null && this.prevSlotSpookyNEW != -1 && this.prevSlotSpookyNEW != Spider.mc.field_1724.method_31548().field_7545) {
                Spider.mc.field_1724.method_31548().field_7545 = this.prevSlotSpookyNEW;
            }
            this.prevSlotSpookyNEW = -1;
        }
    }

    @EventHandler
    public void onPostTick(PostTickEvent e) {
        if (Spider.mc.field_1724 == null || Spider.mc.field_1687 == null) {
            return;
        }
        if (this.mode.isSelected("\u0424\u0430\u043d\u0422\u0430\u0439\u043c")) {
            if (Spider.mc.field_1724.field_5976) {
                Spider.mc.field_1690.field_1903.method_23481(true);
                Spider.mc.field_1724.method_36457(this.funTimePitch.getValue());
                if (this.stopWatch.finished(this.funTimeDelay.getValue())) {
                    int blockSlot = this.findFunTimeBlockSlot();
                    if (blockSlot == -1) {
                        ChatMessage.brandmessage("\u041d\u0443\u0436\u0435\u043d \u0432\u044b\u0431\u0440\u0430\u043d\u043d\u044b\u0439 \u0431\u043b\u043e\u043a \u0424\u0430\u043d\u0422\u0430\u0439\u043c \u0432 \u0445\u043e\u0442\u0431\u0430\u0440\u0435");
                        this.setState(false);
                        return;
                    }
                    Spider.mc.field_1724.method_24830(true);
                    Spider.mc.field_1724.method_6043();
                    this.placeFunTimeBlockAtFeet(blockSlot);
                    Spider.mc.field_1724.field_6017 = 0.0f;
                    this.stopWatch.reset();
                }
            } else {
                Spider.mc.field_1690.field_1903.method_23481(false);
            }
        }
        if (this.mode.isSelected("FixedPitch")) {
            class_2338 placeablePos;
            boolean hasWaterBucketInMain;
            if (Spider.mc.field_1724.field_5976) {
                Spider.mc.field_1690.field_1894.method_23481(false);
            }
            int waterBucketSlot = InventoryTask.getHotbarSlotId(i -> Spider.mc.field_1724.method_31548().method_5438(i).method_7909() == class_1802.field_8705);
            boolean bl = hasWaterBucketInMain = Spider.mc.field_1724.method_6047().method_7909() == class_1802.field_8705;
            if (waterBucketSlot >= 0 && waterBucketSlot <= 8) {
                Spider.mc.field_1690.field_1903.method_23481(true);
                Spider.mc.field_1690.field_1832.method_23481(true);
                Spider.mc.field_1724.method_31548().method_61496(waterBucketSlot);
            } else {
                ChatMessage.brandmessage("\u041d\u0443\u0436\u043d\u043e \u0432\u0435\u0434\u0440\u043e \u0432\u043e\u0434\u044b \u0432 \u0445\u043e\u0442\u0431\u0430\u0440\u0435");
            }
            if (hasWaterBucketInMain && (placeablePos = this.getPlaceableWaterBlock()) != null && this.stopWatch.finished(340.0)) {
                Spider.mc.field_1724.method_36457(75.0f);
                class_243 vec = placeablePos.method_46558();
                class_2350 direction = class_2350.method_10142((double)(vec.field_1352 - Spider.mc.field_1724.method_23317()), (double)(vec.field_1351 - Spider.mc.field_1724.method_23318()), (double)(vec.field_1350 - Spider.mc.field_1724.method_23321()));
                PlayerInteractionHelper.interactItem(class_1268.field_5808);
                Spider.mc.field_1724.field_3944.method_52787((class_2596)new class_2879(class_1268.field_5808));
                this.stopWatch.reset();
                Spider.mc.field_1724.method_18800(Spider.mc.field_1724.method_18798().field_1352, 0.35, Spider.mc.field_1724.method_18798().field_1350);
            }
        }
        if (this.mode.isSelected("\u0412\u0435\u0434\u0440\u043e \u043b\u0430\u0432\u044b")) {
            this.handleLavaBucketMode();
        }
        if (this.mode.isSelected("WaterBucket")) {
            this.handleWaterBucketMode();
        }
        if (this.mode.isSelected("\u0421\u043f\u0443\u043a\u0438\u0422\u0430\u0439\u043c")) {
            this.handleWaterBucketMode2();
        }
        if (this.mode.isSelected("SpookyNEW")) {
            this.handleSpookyNEWMode();
        }
    }

    private void handleLavaBucketMode() {
        long now;
        if (Spider.mc.field_1724 == null || Spider.mc.field_1724.method_5799()) {
            return;
        }
        Spider.mc.field_1724.method_36457(68.0f);
        if (!Spider.mc.field_1724.field_5976) {
            Spider.mc.field_1690.field_1832.method_23481(false);
            Spider.mc.field_1690.field_1903.method_23481(false);
            return;
        }
        int lavaSlot = -1;
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = Spider.mc.field_1724.method_31548().method_5438(i);
            if (stack == null || stack.method_7909() != class_1802.field_8187) continue;
            lavaSlot = i;
            break;
        }
        if (lavaSlot == -1) {
            return;
        }
        if (Spider.mc.field_1724.method_24828() && (now = System.currentTimeMillis()) - this.lastWallJumpMs > 250L) {
            Spider.mc.field_1724.method_6043();
            this.lastWallJumpMs = now;
        }
        Spider.mc.field_1690.field_1903.method_23481(true);
        if (this.canUseSpooky) {
            int currentSlot = Spider.mc.field_1724.method_31548().field_7545;
            if (lavaSlot != currentSlot) {
                Spider.mc.field_1724.field_3944.method_52787((class_2596)new class_2868(lavaSlot));
            }
            Spider.mc.field_1761.method_2919((class_1657)Spider.mc.field_1724, class_1268.field_5808);
            if (lavaSlot != currentSlot) {
                Spider.mc.field_1724.field_3944.method_52787((class_2596)new class_2868(currentSlot));
            }
            double yBoost = 0.4 + this.random.nextDouble() * 0.03;
            Spider.mc.field_1724.method_18800(Spider.mc.field_1724.method_18798().field_1352, yBoost, Spider.mc.field_1724.method_18798().field_1350);
            this.canUseSpooky = false;
            int delayMs = 450 + this.random.nextInt(60) - 30;
            this.spookyTimer.schedule(new TimerTask(){

                @Override
                public void run() {
                    Spider.this.canUseSpooky = true;
                }
            }, Math.max(150, delayMs));
        }
        Spider.mc.field_1690.field_1832.method_23481(true);
    }

    @EventHandler
    public void onRotationUpdate(RotationUpdateEvent e) {
        if (e.getType() == 0 && this.mode.isSelected("\u0411\u043b\u043e\u043a\u0438")) {
            boolean offHand = Spider.mc.field_1724.method_6079().method_7909() instanceof class_1747;
            int slotId = InventoryTask.getHotbarSlotId(i -> Spider.mc.field_1724.method_31548().method_5438(i).method_7909() instanceof class_1747);
            class_2338 blockPos = this.findPos();
            if (this.script.isFinished() && (offHand || slotId != -1) && !blockPos.equals((Object)class_2338.field_10980)) {
                class_1799 stack = offHand ? Spider.mc.field_1724.method_6079() : Spider.mc.field_1724.method_31548().method_5438(slotId);
                class_1268 hand = offHand ? class_1268.field_5810 : class_1268.field_5808;
                class_243 vec = blockPos.method_46558();
                class_2350 direction = class_2350.method_10142((double)(vec.field_1352 - Spider.mc.field_1724.method_23317()), (double)(vec.field_1351 - Spider.mc.field_1724.method_23318()), (double)(vec.field_1350 - Spider.mc.field_1724.method_23321()));
                Turns angle = MathAngle.calculateAngle(vec.method_1020(new class_243(direction.method_62675()).method_1021((double)0.1f)));
                Turns.VecRotation vecRotation = new Turns.VecRotation(angle, angle.toVector());
                TurnsConnection.INSTANCE.rotateTo(vecRotation, (class_1309)Spider.mc.field_1724, 1, new TurnsConfig(new LinearConstructor(), true, true), TaskPriority.HIGH_IMPORTANCE_1, this);
                if (this.canPlace(stack)) {
                    int prev = Spider.mc.field_1724.field_7514.field_7545;
                    if (!offHand) {
                        Spider.mc.field_1724.field_7514.field_7545 = slotId;
                    }
                    Spider.mc.field_1761.method_2896(Spider.mc.field_1724, hand, new class_3965(vec, direction.method_10153(), blockPos, false));
                    Spider.mc.field_1724.field_3944.method_52787((class_2596)new class_2879(hand));
                    if (!offHand) {
                        Spider.mc.field_1724.field_7514.field_7545 = prev;
                    }
                }
            }
        }
    }

    private boolean canPlace(class_1799 stack) {
        class_2338 blockPos = this.getBlockPos();
        if (blockPos.method_10264() >= Spider.mc.field_1724.method_31478()) {
            return false;
        }
        class_1747 blockItem = (class_1747)stack.method_7909();
        class_265 shape = blockItem.method_7711().method_9564().method_26220((class_1922)Spider.mc.field_1687, blockPos);
        if (shape.method_1110()) {
            return false;
        }
        class_238 box = shape.method_1107().method_996(blockPos);
        return !box.method_994(Spider.mc.field_1724.method_5829()) && box.method_994(PlayerSimulation.simulateLocalPlayer((int)4).boundingBox);
    }

    private class_2338 findPos() {
        class_2338 blockPos = this.getBlockPos();
        if (Spider.mc.field_1687.method_8320(blockPos).method_51367()) {
            return class_2338.field_10980;
        }
        return Stream.of(blockPos.method_10067(), blockPos.method_10078(), blockPos.method_10072(), blockPos.method_10095()).filter(pos -> Spider.mc.field_1687.method_8320(pos).method_51367()).findFirst().orElse(class_2338.field_10980);
    }

    private class_2338 getPlaceableWaterBlock() {
        class_2338 below = class_2338.method_49638((class_2374)Spider.mc.field_1724.method_19538().method_1031(0.0, -1.3, 0.0));
        if (Spider.mc.field_1687.method_8320(below).method_26212((class_1922)Spider.mc.field_1687, below)) {
            return below;
        }
        for (class_2350 dir : class_2350.values()) {
            class_2338 side = below.method_10093(dir);
            if (!Spider.mc.field_1687.method_8320(side).method_26212((class_1922)Spider.mc.field_1687, side)) continue;
            return side;
        }
        return null;
    }

    private class_2338 getBlockPos() {
        return class_2338.method_49638((class_2374)PlayerSimulation.simulateLocalPlayer((int)1).pos.method_1031(0.0, -0.001, 0.0));
    }

    private int findFunTimeBlockSlot() {
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = Spider.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960() || !this.isAllowedFunTimeItem(stack.method_7909())) continue;
            return i;
        }
        return -1;
    }

    private boolean isAllowedFunTimeItem(class_1792 item) {
        return this.funTimeBlocks.isSelected(FT_HAY) && item == class_1802.field_8861 || this.funTimeBlocks.isSelected(FT_KELP) && item == class_1802.field_17532 || this.funTimeBlocks.isSelected(FT_LILY_PAD) && item == class_1802.field_17524 || this.funTimeBlocks.isSelected(FT_RAILS) && this.isRail(item) || this.funTimeBlocks.isSelected(FT_PRESSURE_PLATES) && this.isPressurePlate(item) || this.funTimeBlocks.isSelected(FT_SEA_PICKLE) && item == class_1802.field_17498 || this.funTimeBlocks.isSelected(FT_CANDLES) && this.isCandle(item) || this.funTimeBlocks.isSelected(FT_TURTLE_EGG) && item == class_1802.field_8618 || this.funTimeBlocks.isSelected(FT_MUSHROOMS) && this.isMushroom(item) || this.funTimeBlocks.isSelected(FT_FLOWERS) && this.isFlower(item) || this.funTimeBlocks.isSelected(FT_SIGNS) && this.isSign(item);
    }

    private boolean isRail(class_1792 item) {
        return item == class_1802.field_8129 || item == class_1802.field_8848 || item == class_1802.field_8211 || item == class_1802.field_8655;
    }

    private boolean isPressurePlate(class_1792 item) {
        return item == class_1802.field_8667 || item == class_1802.field_8391 || item == class_1802.field_8707 || item == class_1802.field_8779 || item == class_1802.field_8047 || item == class_1802.field_8173 || item == class_1802.field_8886 || item == class_1802.field_37527 || item == class_1802.field_42704 || item == class_1802.field_40221 || item == class_1802.field_21993 || item == class_1802.field_21994 || item == class_1802.field_23835 || item == class_1802.field_8721 || item == class_1802.field_8592;
    }

    private boolean isCandle(class_1792 item) {
        return item == class_1802.field_27024 || item == class_1802.field_27025 || item == class_1802.field_27026 || item == class_1802.field_27027 || item == class_1802.field_27028 || item == class_1802.field_27029 || item == class_1802.field_27052 || item == class_1802.field_27053 || item == class_1802.field_27054 || item == class_1802.field_27055 || item == class_1802.field_27056 || item == class_1802.field_27057 || item == class_1802.field_27058 || item == class_1802.field_27059 || item == class_1802.field_27060 || item == class_1802.field_27061 || item == class_1802.field_27062;
    }

    private boolean isMushroom(class_1792 item) {
        return item == class_1802.field_17516 || item == class_1802.field_17517;
    }

    private boolean isFlower(class_1792 item) {
        return item == class_1802.field_8491 || item == class_1802.field_8880 || item == class_1802.field_17499 || item == class_1802.field_17500 || item == class_1802.field_17501 || item == class_1802.field_17502 || item == class_1802.field_17509 || item == class_1802.field_17510 || item == class_1802.field_17511 || item == class_1802.field_17512 || item == class_1802.field_17513 || item == class_1802.field_17514 || item == class_1802.field_17515 || item == class_1802.field_17525 || item == class_1802.field_17526 || item == class_1802.field_17527 || item == class_1802.field_17529 || item == class_1802.field_42695 || item == class_1802.field_43192 || item == class_1802.field_28652 || item == class_1802.field_28651 || item == class_1802.field_28649;
    }

    private boolean isSign(class_1792 item) {
        return item == class_1802.field_8788 || item == class_1802.field_8111 || item == class_1802.field_8422 || item == class_1802.field_8867 || item == class_1802.field_8203 || item == class_1802.field_8496 || item == class_1802.field_37534 || item == class_1802.field_42709 || item == class_1802.field_40228 || item == class_1802.field_22011 || item == class_1802.field_22012;
    }

    private void placeFunTimeBlockAtFeet(int slot) {
        int previousSlot = Spider.mc.field_1724.method_31548().field_7545;
        float previousPitch = Spider.mc.field_1724.method_36455();
        Spider.mc.field_1724.method_31548().field_7545 = slot;
        Spider.mc.field_1724.method_36457(this.funTimePitch.getValue());
        class_3965 hitResult = Spider.mc.field_1687.method_17742(new class_3959(Spider.mc.field_1724.method_33571(), Spider.mc.field_1724.method_33571().method_1019(class_243.method_1030((float)this.funTimePitch.getValue(), (float)Spider.mc.field_1724.method_36454()).method_1029().method_1021(4.5)), class_3959.class_3960.field_17559, class_3959.class_242.field_1348, (class_1297)Spider.mc.field_1724));
        Spider.mc.field_1761.method_2896(Spider.mc.field_1724, class_1268.field_5808, hitResult);
        Spider.mc.field_1724.method_36457(previousPitch);
        Spider.mc.field_1724.method_31548().field_7545 = previousSlot;
    }

    private void handleWaterBucketMode() {
        if (Spider.mc.field_1724 == null) {
            return;
        }
        if (Spider.mc.field_1724.method_5799() || Spider.mc.field_1724.method_5869()) {
            Spider.mc.field_1724.method_18800(Spider.mc.field_1724.method_18798().field_1352, 0.45, Spider.mc.field_1724.method_18798().field_1350);
            return;
        }
        if (!this.canUseWaterBucket && this.useCooldownWaterBucket.finished(this.getAppliedDelayMs())) {
            this.canUseWaterBucket = true;
        }
        if (this.silentUse.isValue()) {
            this.handleSilentWaterBucket();
        } else {
            this.handleVisibleWaterBucket();
        }
        if (!Spider.mc.field_1724.field_5976) {
            if (Spider.mc.field_1690.field_1832.method_1434() && this.holdShift.isValue()) {
                Spider.mc.field_1690.field_1832.method_23481(false);
            }
            if (Spider.mc.field_1690.field_1903.method_1434() && this.holdSpace.isValue()) {
                Spider.mc.field_1690.field_1903.method_23481(false);
            }
        }
    }

    private void handleSilentWaterBucket() {
        int waterSlot = -1;
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = Spider.mc.field_1724.method_31548().method_5438(i);
            if (stack == null || stack.method_7909() != class_1802.field_8705) continue;
            waterSlot = i;
            break;
        }
        if (waterSlot != -1 && Spider.mc.field_1724.field_5976) {
            this.handleWallClimb(waterSlot);
        }
    }

    private void handleVisibleWaterBucket() {
        if (Spider.mc.field_1724.method_6047().method_7909() == class_1802.field_8705 && Spider.mc.field_1724.field_5976) {
            this.handleWallClimb(Spider.mc.field_1724.method_31548().field_7545);
        }
    }

    private void handleWallClimb(int waterSlot) {
        if (this.holdSpace.isValue()) {
            long now;
            if (Spider.mc.field_1724.method_24828() && (now = System.currentTimeMillis()) - this.lastWallJumpMs > 250L) {
                Spider.mc.field_1724.method_6043();
                this.lastWallJumpMs = now;
            }
            Spider.mc.field_1690.field_1903.method_23481(true);
        }
        if (this.canUseWaterBucket) {
            int clientSlot = Spider.mc.field_1724.method_31548().field_7545;
            Spider.mc.field_1724.method_36457(75.0f);
            if (this.silentUse.isValue() && waterSlot != clientSlot) {
                Spider.mc.field_1724.field_3944.method_52787((class_2596)new class_2868(waterSlot));
                Spider.mc.field_1724.method_31548().field_7545 = waterSlot;
            }
            PlayerInteractionHelper.interactItem(class_1268.field_5808);
            Spider.mc.field_1724.method_18800(Spider.mc.field_1724.method_18798().field_1352, 0.43, Spider.mc.field_1724.method_18798().field_1350);
            if (this.silentUse.isValue() && waterSlot != clientSlot) {
                Spider.mc.field_1724.field_3944.method_52787((class_2596)new class_2868(clientSlot));
                Spider.mc.field_1724.method_31548().field_7545 = clientSlot;
            }
            this.canUseWaterBucket = false;
            this.useCooldownWaterBucket.reset();
        }
        if (this.holdShift.isValue()) {
            Spider.mc.field_1690.field_1832.method_23481(true);
        }
    }

    private int getAppliedDelayMs() {
        float base = this.delay.getValue();
        return (int)(base * 1000.0f);
    }

    private void handleWaterBucketMode2() {
        if (Spider.mc.field_1724 == null) {
            return;
        }
        if (Spider.mc.field_1724.method_5799() || Spider.mc.field_1724.method_5869()) {
            Spider.mc.field_1724.field_18276.field_1351 = 0.46;
            return;
        }
        if (!this.canUseWaterBucket2 && this.useCooldownWaterBucket2.finished(this.getAppliedDelayMs2())) {
            this.canUseWaterBucket2 = true;
        }
        this.handleSilentWaterBucket2();
        if (!Spider.mc.field_1724.field_5976) {
            if (Spider.mc.field_1690.field_1832.method_1434()) {
                Spider.mc.field_1690.field_1832.method_23481(false);
            }
            if (Spider.mc.field_1690.field_1903.method_1434()) {
                Spider.mc.field_1690.field_1903.method_23481(false);
            }
        }
    }

    private void handleSilentWaterBucket2() {
        int waterSlot = -1;
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = Spider.mc.field_1724.method_31548().method_5438(i);
            if (stack == null || stack.method_7909() != class_1802.field_8705) continue;
            waterSlot = i;
            break;
        }
        if (waterSlot != -1 && Spider.mc.field_1724.field_5976) {
            this.handleWallClimb2(waterSlot);
        }
    }

    private void handleWallClimb2(int waterSlot) {
        long now;
        if (Spider.mc.field_1724.method_24828() && (now = System.currentTimeMillis()) - this.lastWallJumpMs > 250L) {
            Spider.mc.field_1724.method_6043();
            this.lastWallJumpMs = now;
        }
        Spider.mc.field_1690.field_1903.method_23481(true);
        if (this.canUseWaterBucket2) {
            int clientSlot = Spider.mc.field_1724.method_31548().field_7545;
            Spider.mc.field_1724.method_36457(77.0f);
            if (waterSlot != clientSlot) {
                Spider.mc.field_1724.field_3944.method_52787((class_2596)new class_2868(waterSlot));
            }
            PlayerInteractionHelper.interactItem(class_1268.field_5808);
            Spider.mc.field_1724.field_18276.field_1351 = 0.46;
            if (waterSlot != clientSlot) {
                Spider.mc.field_1724.field_3944.method_52787((class_2596)new class_2868(clientSlot));
            }
            this.canUseWaterBucket2 = false;
            this.useCooldownWaterBucket2.reset();
        }
        Spider.mc.field_1690.field_1832.method_23481(true);
    }

    private int getAppliedDelayMs2() {
        float base = 0.405f;
        return (int)(base * 1000.0f);
    }

    private void handleSpookyNEWMode() {
        if (Spider.mc.field_1724 == null || Spider.mc.field_1687 == null) {
            return;
        }
        int bucketSlot = this.findWaterBucketSlotSpookyNEW();
        if (bucketSlot == -1) {
            return;
        }
        if (Spider.mc.field_1724.field_5976) {
            this.prevSlotSpookyNEW = Spider.mc.field_1724.method_31548().field_7545;
            Spider.mc.field_1724.field_3944.method_52787((class_2596)new class_2868(bucketSlot));
            Spider.mc.field_1724.method_31548().field_7545 = bucketSlot;
            if (Spider.mc.field_1761 != null) {
                Spider.mc.field_1761.method_2919((class_1657)Spider.mc.field_1724, class_1268.field_5808);
            }
            Spider.mc.field_1724.method_18800(Spider.mc.field_1724.method_18798().field_1352, 0.3, Spider.mc.field_1724.method_18798().field_1350);
            if (this.prevSlotSpookyNEW != bucketSlot) {
                Spider.mc.field_1724.field_3944.method_52787((class_2596)new class_2868(this.prevSlotSpookyNEW));
                Spider.mc.field_1724.method_31548().field_7545 = this.prevSlotSpookyNEW;
            }
        }
    }

    private int findWaterBucketSlotSpookyNEW() {
        if (Spider.mc.field_1724 == null) {
            return -1;
        }
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = Spider.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960() || stack.method_7909() != class_1802.field_8705) continue;
            return i;
        }
        return -1;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public void setStopWatch(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    public void setMode(SelectSetting mode) {
        this.mode = mode;
    }

    public void setFunTimeBlocks(MultiSelectSetting funTimeBlocks) {
        this.funTimeBlocks = funTimeBlocks;
    }

    public void setDelay(SliderSettings delay) {
        this.delay = delay;
    }

    public void setFunTimeDelay(SliderSettings funTimeDelay) {
        this.funTimeDelay = funTimeDelay;
    }

    public void setFunTimePitch(SliderSettings funTimePitch) {
        this.funTimePitch = funTimePitch;
    }

    public void setHoldShift(BooleanSetting holdShift) {
        this.holdShift = holdShift;
    }

    public void setSilentUse(BooleanSetting silentUse) {
        this.silentUse = silentUse;
    }

    public void setHoldSpace(BooleanSetting holdSpace) {
        this.holdSpace = holdSpace;
    }

    public void setSpookyTimer(Timer spookyTimer) {
        this.spookyTimer = spookyTimer;
    }

    public void setCanUseSpooky(boolean canUseSpooky) {
        this.canUseSpooky = canUseSpooky;
    }

    public void setLastWallJumpMs(long lastWallJumpMs) {
        this.lastWallJumpMs = lastWallJumpMs;
    }

    public void setCanUseWaterBucket(boolean canUseWaterBucket) {
        this.canUseWaterBucket = canUseWaterBucket;
    }

    public void setUseCooldownWaterBucket(StopWatch useCooldownWaterBucket) {
        this.useCooldownWaterBucket = useCooldownWaterBucket;
    }

    public void setCanUseWaterBucket2(boolean canUseWaterBucket2) {
        this.canUseWaterBucket2 = canUseWaterBucket2;
    }

    public void setUseCooldownWaterBucket2(StopWatch useCooldownWaterBucket2) {
        this.useCooldownWaterBucket2 = useCooldownWaterBucket2;
    }

    public void setPrevSlotSpookyNEW(int prevSlotSpookyNEW) {
        this.prevSlotSpookyNEW = prevSlotSpookyNEW;
    }

    public void setUseItemSequence(int useItemSequence) {
        this.useItemSequence = useItemSequence;
    }

    public void setLastWaterY(double lastWaterY) {
        this.lastWaterY = lastWaterY;
    }

    public void setLastWaterPlaceTime(long lastWaterPlaceTime) {
        this.lastWaterPlaceTime = lastWaterPlaceTime;
    }

    public Script getScript() {
        return this.script;
    }

    public StopWatch getStopWatch() {
        return this.stopWatch;
    }

    public SelectSetting getMode() {
        return this.mode;
    }

    public MultiSelectSetting getFunTimeBlocks() {
        return this.funTimeBlocks;
    }

    public SliderSettings getDelay() {
        return this.delay;
    }

    public SliderSettings getFunTimeDelay() {
        return this.funTimeDelay;
    }

    public SliderSettings getFunTimePitch() {
        return this.funTimePitch;
    }

    public BooleanSetting getHoldShift() {
        return this.holdShift;
    }

    public BooleanSetting getSilentUse() {
        return this.silentUse;
    }

    public BooleanSetting getHoldSpace() {
        return this.holdSpace;
    }

    public Timer getSpookyTimer() {
        return this.spookyTimer;
    }

    public boolean isCanUseSpooky() {
        return this.canUseSpooky;
    }

    public long getLastWallJumpMs() {
        return this.lastWallJumpMs;
    }

    public Random getRandom() {
        return this.random;
    }

    public boolean isCanUseWaterBucket() {
        return this.canUseWaterBucket;
    }

    public StopWatch getUseCooldownWaterBucket() {
        return this.useCooldownWaterBucket;
    }

    public boolean isCanUseWaterBucket2() {
        return this.canUseWaterBucket2;
    }

    public StopWatch getUseCooldownWaterBucket2() {
        return this.useCooldownWaterBucket2;
    }

    public int getPrevSlotSpookyNEW() {
        return this.prevSlotSpookyNEW;
    }

    public int getUseItemSequence() {
        return this.useItemSequence;
    }

    public double getLastWaterY() {
        return this.lastWaterY;
    }

    public long getLastWaterPlaceTime() {
        return this.lastWaterPlaceTime;
    }
}
