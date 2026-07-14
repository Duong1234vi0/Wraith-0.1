package ua.babka.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import ua.babka.event.EventCloseInv;
import ua.babka.event.EventPacket;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.util.Instance;
import ua.babka.util.animation.SlownessManager;
import ua.babka.util.network.NetworkUtils;
import ua.babka.util.player.MoveUtil;

@ModuleInformation(moduleName="Gui Move", moduleDesc="Движение с открытым инвентарём", moduleCategory=ModuleCategory.MOVEMENT)
public class GuiMove
extends Module {
    private final ModeSetting mode = new ModeSetting("Обход", "Обычный", "Обычный", "Grim");
    private final ModeSetting grimVersion = new ModeSetting("Версия свапа", "1.21.4", "1.21.4", "1.16.5");
    private final BooleanSetting universal = new BooleanSetting("Универсальный", false);
    private final SliderSetting slownessDuration = new SliderSetting("Длительность замедления", 50.0, 1.0, 400.0, 1.0);
    private final List<Packet<?>> packets = new ArrayList();
    private final List<ClickSlotC2SPacket> pendingPackets = new ArrayList<ClickSlotC2SPacket>();
    private CloseHandledScreenC2SPacket pendingClosePacket = null;
    private boolean sprintPaused = false;
    private boolean waitingToClose = false;
    private int delayedFlushTicks = -1;
    private boolean flushingPackets = false;
    private int tick = 0;
    private boolean wasSprinting = false;

    public GuiMove() {
        this.grimVersion.setVisible(() -> this.mode.is("Grim"));
        this.slownessDuration.setVisible(this.universal::getValue);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.resetAll();
    }

    private void resetAll() {
        this.packets.clear();
        this.pendingPackets.clear();
        this.pendingClosePacket = null;
        this.flushingPackets = false;
        this.waitingToClose = false;
        this.delayedFlushTicks = -1;
        this.tick = 0;
        this.sprintPaused = false;
        this.wasSprinting = false;
    }

    @Subscribe
    public void onUpdate(EventPlayerUpdate e2) {
        if (this.mc.player == null) {
            return;
        }
        KeyBinding[] pressedKeys = new KeyBinding[]{this.mc.options.forwardKey, this.mc.options.backKey, this.mc.options.leftKey, this.mc.options.rightKey, this.mc.options.jumpKey, this.mc.options.sprintKey};
        if (this.mode.is("Grim")) {
            if (this.grimVersion.is("1.21.4") && this.waitingToClose && !MoveUtil.hasPlayerMovement()) {
                this.flushQueuedPackets(true);
                this.waitingToClose = false;
                this.tick = 3;
            }
            if (this.grimVersion.is("1.16.5") && this.delayedFlushTicks >= 0) {
                if (this.delayedFlushTicks == 0) {
                    this.flushQueuedPackets(true);
                    this.delayedFlushTicks = -1;
                    this.tick = 1;
                } else {
                    --this.delayedFlushTicks;
                }
            }
            if (this.tick == 0 && !this.pendingPackets.isEmpty() && this.mc.currentScreen == null && !this.waitingToClose) {
                this.sendPendingPackets();
            }
            if (this.tick != 0) {
                for (KeyBinding kb : pressedKeys) {
                    kb.setPressed(false);
                }
                --this.tick;
                if (this.tick == 0 && this.sprintPaused) {
                    this.sprintPaused = false;
                }
                return;
            }
            if (this.mc.currentScreen instanceof ChatScreen || this.mc.currentScreen instanceof SignEditScreen) {
                return;
            }
            if (this.mc.currentScreen instanceof HandledScreen && !(this.mc.currentScreen instanceof InventoryScreen)) {
                return;
            }
            if (this.waitingToClose) {
                for (KeyBinding kb : pressedKeys) {
                    kb.setPressed(false);
                }
                return;
            }
        }
        for (KeyBinding key : new KeyBinding[]{this.mc.options.forwardKey, this.mc.options.backKey, this.mc.options.leftKey, this.mc.options.rightKey, this.mc.options.jumpKey}) {
            key.setPressed(InputUtil.isKeyPressed((long)this.mc.getWindow().getHandle(), (int)InputUtil.fromTranslationKey((String)key.getBoundKeyTranslationKey()).getCode()));
        }
    }

    @Subscribe
    public void onPacket(EventPacket e2) {
        if (e2.isCancelled() || this.flushingPackets) {
            return;
        }
        Packet<?> packet = e2.getPacket();
        if (this.mode.is("Grim") && MoveUtil.hasPlayerMovement() && this.mc.currentScreen instanceof InventoryScreen) {
            if (packet instanceof ClickSlotC2SPacket) {
                ClickSlotC2SPacket click = (ClickSlotC2SPacket)packet;
                this.pendingPackets.add(click);
                e2.cancelEvent();
                return;
            }
            if (packet instanceof CloseHandledScreenC2SPacket) {
                CloseHandledScreenC2SPacket close;
                this.pendingClosePacket = close = (CloseHandledScreenC2SPacket)packet;
                if (this.grimVersion.is("1.16.5")) {
                    this.delayedFlushTicks = 1;
                } else {
                    this.waitingToClose = true;
                }
                this.pauseSprint();
                e2.cancelEvent();
                return;
            }
        }
        if (this.universal.getValue() && this.mc.currentScreen != null && !(this.mc.currentScreen instanceof ChatScreen)) {
            if (packet instanceof ClickSlotC2SPacket && MoveUtil.hasPlayerMovement() && this.mc.currentScreen instanceof InventoryScreen) {
                this.packets.add(packet);
                e2.cancelEvent();
            } else if (packet instanceof CloseHandledScreenC2SPacket && MoveUtil.hasPlayerMovement() && this.mc.player.isSprinting()) {
                this.wasSprinting = true;
                this.packets.add(packet);
                e2.cancelEvent();
            }
        }
    }

    @Subscribe
    public void onCloseInv(EventCloseInv e2) {
        if (this.grimVersion.is("1.16.5") && MoveUtil.hasPlayerMovement() && this.mc.currentScreen instanceof InventoryScreen) {
            this.pendingClosePacket = new CloseHandledScreenC2SPacket(this.mc.player.currentScreenHandler.syncId);
            this.delayedFlushTicks = 1;
            this.pauseSprint();
            this.tick = 1;
            e2.cancelEvent();
            return;
        }
        if (this.universal.getValue() && !this.packets.isEmpty()) {
            e2.cancelEvent();
            if (this.wasSprinting) {
                this.mc.player.setSprinting(false);
            }
            SlownessManager.addTask(new SlownessManager.SlowTask((long)this.slownessDuration.getIntValue(), 0L, () -> {
                this.packets.forEach(NetworkUtils::sendSilentPacket);
                this.packets.clear();
                NetworkUtils.sendSilentPacket(new CloseHandledScreenC2SPacket(this.mc.player.currentScreenHandler.syncId));
                if (this.wasSprinting) {
                    this.wasSprinting = false;
                }
            }));
        }
    }

    private void pauseSprint() {
        if (this.sprintPaused) {
            return;
        }
        this.sprintPaused = true;
    }

    private void sendPendingPackets() {
        if (this.mc.player == null || this.mc.getNetworkHandler() == null) {
            this.pendingPackets.clear();
            return;
        }
        this.flushingPackets = true;
        try {
            for (ClickSlotC2SPacket p2 : this.pendingPackets) {
                this.mc.getNetworkHandler().sendPacket((Packet)p2);
            }
        }
        finally {
            this.flushingPackets = false;
        }
        this.pendingPackets.clear();
    }

    private void flushQueuedPackets(boolean includeClose) {
        if (this.mc.player == null || this.mc.getNetworkHandler() == null) {
            this.pendingPackets.clear();
            this.pendingClosePacket = null;
            return;
        }
        this.sendPendingPackets();
        if (includeClose && this.pendingClosePacket != null) {
            this.flushingPackets = true;
            try {
                this.mc.getNetworkHandler().sendPacket((Packet)this.pendingClosePacket);
            }
            finally {
                this.flushingPackets = false;
            }
            this.pendingClosePacket = null;
        }
    }

    public static void stopTick(int ticks) {
        GuiMove gm = Instance.get(GuiMove.class);
        if (gm != null && gm.isEnabled()) {
            gm.tick = Math.max(gm.tick, ticks);
        }
    }
}

