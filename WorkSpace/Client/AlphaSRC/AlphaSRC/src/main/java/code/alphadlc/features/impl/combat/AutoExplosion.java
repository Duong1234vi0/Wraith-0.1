/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1511
 *  net.minecraft.class_1542
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_2244
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2885
 *  net.minecraft.class_3532
 *  net.minecraft.class_3965
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.events.block.BlockBreakingEvent;
import code.alphadlc.events.block.BlockUpdateEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.RotationStorage;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.rotate.Rotation;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryToolkit;
import java.util.Iterator;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2244;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2885;
import net.minecraft.class_3532;
import net.minecraft.class_3965;

public class AutoExplosion
extends Module {
    private static final double EXPAND = 0.0;
    private final BooleanSetting protectSelf = new BooleanSetting("\u041d\u0435 \u0432\u0437\u0440\u044b\u0432\u0430\u0442\u044c \u0441\u0435\u0431\u044f", "\u041d\u0435 \u0432\u0437\u0440\u044b\u0432\u0430\u0435\u0442 \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b \u043d\u0430 \u043e\u0434\u043d\u043e\u0439 \u0432\u044b\u0441\u043e\u0442\u0435 \u0441 \u0432\u0430\u043c\u0438").setValue(true);
    private final BooleanSetting protectItems = new BooleanSetting("\u041d\u0435 \u0432\u0437\u0440\u044b\u0432\u0430\u0442\u044c, \u0435\u0441\u043b\u0438 \u0440\u044f\u0434\u043e\u043c \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", "\u041f\u0440\u043e\u043f\u0443\u0441\u043a\u0430\u0435\u0442 \u0432\u0437\u0440\u044b\u0432, \u0435\u0441\u043b\u0438 \u0440\u044f\u0434\u043e\u043c \u043b\u0435\u0436\u0430\u0442 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b").setValue(false);
    private final BooleanSetting keepCrystalHand = new BooleanSetting("\u041e\u0441\u0442\u0430\u0432\u043b\u044f\u0442\u044c \u043a\u0440\u0438\u0441\u0442\u0430\u043b \u0432 \u0440\u0443\u043a\u0435", "\u041d\u0435 \u0432\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0435\u0442 \u0441\u043b\u043e\u0442 \u043f\u043e\u0441\u043b\u0435 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043a\u0438 \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430").setValue(false);
    private class_2338 placePos;
    private int crystalSlot = -1;
    private boolean waitPlaceTick;
    private boolean restoreSlot;
    private int previousSlot;
    private boolean bedPlaceFlow;
    private class_238 attackBox;

    public AutoExplosion() {
        super("AutoExplosion", "Auto Explosion", ModuleCategory.COMBAT);
        this.setup(this.protectSelf, this.protectItems, this.keepCrystalHand);
    }

    public static AutoExplosion getInstance() {
        return Instance.get(AutoExplosion.class);
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        if (!event.isSend() || PlayerInteractionHelper.nullCheck()) {
            return;
        }
        class_2596<?> class_25962 = event.getPacket();
        if (!(class_25962 instanceof class_2885)) {
            return;
        }
        class_2885 packet = (class_2885)class_25962;
        if (!AutoExplosion.mc.field_1724.method_5998(packet.method_12546()).method_31574(class_1802.field_8281)) {
            return;
        }
        class_2338 targetPos = packet.method_12543().method_17777().method_10093(packet.method_12543().method_17780());
        if (AutoExplosion.mc.field_1724.method_7357().method_7904(class_1802.field_8301.method_7854())) {
            return;
        }
        if (this.protectItems.isValue() && this.hasNearbyItems(targetPos)) {
            return;
        }
        int slot = this.findItemSlot(class_1802.field_8301, true);
        if (slot == -1) {
            return;
        }
        this.placePos = targetPos;
        this.crystalSlot = slot;
        this.bedPlaceFlow = false;
        this.waitPlaceTick = true;
    }

    @EventHandler
    public void onBlockUpdate(BlockUpdateEvent event) {
        if (event.type() != BlockUpdateEvent.Type.UPDATE) {
            return;
        }
        if (this.placePos != null || this.waitPlaceTick) {
            return;
        }
        if (AutoExplosion.mc.field_1724 == null) {
            return;
        }
        class_2338 pos = event.pos();
        if (event.state().method_27852(class_2246.field_10540)) {
            if (!AutoExplosion.mc.field_1724.method_6047().method_31574(class_1802.field_8281) && !AutoExplosion.mc.field_1724.method_6079().method_31574(class_1802.field_8281)) {
                return;
            }
            if (AutoExplosion.mc.field_1724.method_24515().method_19455((class_2382)pos) > 6) {
                return;
            }
            if (AutoExplosion.mc.field_1724.method_7357().method_7904(class_1802.field_8301.method_7854())) {
                return;
            }
            if (this.protectItems.isValue() && this.hasNearbyItems(pos)) {
                return;
            }
            int slot = this.findItemSlot(class_1802.field_8301, true);
            if (slot == -1) {
                return;
            }
            this.placePos = pos;
            this.crystalSlot = slot;
            this.bedPlaceFlow = false;
            this.waitPlaceTick = true;
        } else if (event.state().method_26204() instanceof class_2244) {
            int slot = this.findItemSlot(class_1802.field_8301, true);
            if (slot == -1) {
                return;
            }
            this.placePos = pos;
            this.crystalSlot = slot;
            this.bedPlaceFlow = true;
            this.waitPlaceTick = true;
        }
    }

    @EventHandler
    public void onBlockBreaking(BlockBreakingEvent event) {
        if (PlayerInteractionHelper.nullCheck()) {
            return;
        }
        if (AutoExplosion.mc.field_1724.method_7357().method_7904(class_1802.field_8301.method_7854())) {
            return;
        }
        class_2248 block = AutoExplosion.mc.field_1687.method_8320(event.blockPos()).method_26204();
        if (block != class_2246.field_10540 && block != class_2246.field_22423) {
            return;
        }
        this.attackBox = new class_238(event.blockPos().method_10084()).method_1014(0.0);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (PlayerInteractionHelper.nullCheck()) {
            return;
        }
        this.handleSlotRestore();
        this.handleCrystalPlace();
        this.handleCrystalAttack();
    }

    private void handleSlotRestore() {
        if (!this.restoreSlot) {
            return;
        }
        this.restoreSlot = false;
        AutoExplosion.mc.field_1724.method_31548().field_7545 = this.previousSlot;
        InventoryToolkit.switchToSilent(this.previousSlot);
    }

    private void handleCrystalPlace() {
        if (this.placePos == null) {
            return;
        }
        if (AutoExplosion.mc.field_1687.method_8320(this.placePos).method_26215()) {
            this.placePos = null;
            return;
        }
        if (this.waitPlaceTick) {
            this.waitPlaceTick = false;
            return;
        }
        class_243 eyes = AutoExplosion.mc.field_1724.method_33571();
        class_243 hitPos = this.getClosestPoint(eyes, new class_238(this.placePos));
        class_243 delta = hitPos.method_1020(eyes);
        float yaw = (float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(delta.field_1350, delta.field_1352)) - 90.0));
        float pitch = (float)(-Math.toDegrees(Math.atan2(delta.field_1351, Math.hypot(delta.field_1352, delta.field_1350))));
        RotationStorage.update(new Rotation(yaw, pitch), 180.0f, 180.0f, 25.0f, 20.0f, 1, 6, false);
        this.previousSlot = AutoExplosion.mc.field_1724.method_31548().field_7545;
        AutoExplosion.mc.field_1724.method_31548().field_7545 = this.crystalSlot;
        InventoryToolkit.switchToSilent(this.crystalSlot);
        class_243 direction = delta.method_1029();
        class_3965 hit = new class_3965(hitPos, class_2350.method_10142((double)direction.field_1352, (double)direction.field_1351, (double)direction.field_1350), this.placePos, false);
        AutoExplosion.mc.field_1761.method_2896(AutoExplosion.mc.field_1724, class_1268.field_5808, hit);
        AutoExplosion.mc.field_1724.method_6104(class_1268.field_5808);
        this.restoreSlot = true;
        if (this.bedPlaceFlow) {
            this.crystalSlot = this.previousSlot;
            this.bedPlaceFlow = false;
        } else {
            this.placePos = null;
            if (this.keepCrystalHand.isValue()) {
                this.restoreSlot = false;
            }
        }
    }

    private void handleCrystalAttack() {
        if (this.attackBox == null) {
            return;
        }
        for (class_1297 entity : AutoExplosion.mc.field_1687.method_18112()) {
            class_1511 crystal;
            if (!(entity instanceof class_1511) || !this.attackBox.method_994((crystal = (class_1511)entity).method_5829())) continue;
            if (this.protectSelf.isValue() && Math.abs(AutoExplosion.mc.field_1724.method_23318() - crystal.method_23318()) < 1.0) {
                this.attackBox = null;
                return;
            }
            class_2338 below = class_2338.method_49638((class_2374)this.attackBox.method_1005()).method_10074();
            if (this.protectItems.isValue() && this.hasNearbyItems(below)) {
                this.attackBox = null;
                return;
            }
            if (!crystal.method_5829().method_994(AutoExplosion.mc.field_1724.method_5829())) {
                class_243 point = MathAngle.getClosestVec((class_1297)crystal);
                class_243 delta = point.method_1020(AutoExplosion.mc.field_1724.method_33571());
                float yaw = (float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(delta.field_1350, delta.field_1352)) - 90.0));
                float pitch = (float)(-Math.toDegrees(Math.atan2(delta.field_1351, Math.hypot(delta.field_1352, delta.field_1350))));
                RotationStorage.update(new Rotation(yaw, pitch), 180.0f, 180.0f, 25.0f, 20.0f, 1, 6, false);
            }
            AutoExplosion.mc.field_1761.method_2918((class_1657)AutoExplosion.mc.field_1724, (class_1297)crystal);
            AutoExplosion.mc.field_1724.method_6104(class_1268.field_5808);
            this.attackBox = null;
            return;
        }
    }

    private boolean hasNearbyItems(class_2338 pos) {
        class_238 box = new class_238(pos).method_1014(1.0);
        Iterator iterator = AutoExplosion.mc.field_1687.method_8390(class_1542.class, box, e -> true).iterator();
        if (iterator.hasNext()) {
            class_1297 entity = (class_1297)iterator.next();
            return true;
        }
        return false;
    }

    private int findItemSlot(class_1792 item, boolean hotbarOnly) {
        int limit = hotbarOnly ? 9 : 36;
        int found = -1;
        for (int i = 0; i < limit; ++i) {
            if (!AutoExplosion.mc.field_1724.method_31548().method_5438(i).method_31574(item)) continue;
            found = i;
        }
        return found;
    }

    private class_243 getClosestPoint(class_243 point, class_238 box) {
        double x = class_3532.method_15350((double)point.field_1352, (double)box.field_1323, (double)box.field_1320);
        double y = class_3532.method_15350((double)point.field_1351, (double)box.field_1322, (double)box.field_1325);
        double z = class_3532.method_15350((double)point.field_1350, (double)box.field_1321, (double)box.field_1324);
        return new class_243(x, y, z);
    }

    @Override
    public void deactivate() {
        this.placePos = null;
        this.crystalSlot = -1;
        this.waitPlaceTick = false;
        this.restoreSlot = false;
        this.bedPlaceFlow = false;
        this.attackBox = null;
        super.deactivate();
    }
}
