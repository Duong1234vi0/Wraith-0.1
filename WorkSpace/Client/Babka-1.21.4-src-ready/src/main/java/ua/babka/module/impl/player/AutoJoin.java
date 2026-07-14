package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import ua.babka.event.EventPacket;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.SliderSetting;
import ua.babka.util.ChatUtil;
import ua.babka.util.math.StopWatch;

@ModuleInformation(moduleName="AutoJoin", moduleDesc="Автоматически заходит на выбранный гриф", moduleCategory=ModuleCategory.MISC)
public class AutoJoin
extends Module {
    private static final long CLICK_DELAY_MS = 30L;
    private static final int NEXT_PAGE_SLOT = 44;
    private static final int MAX_PAGE_SWITCHES = 5;
    private final SliderSetting grief = new SliderSetting("Гриф", 5.0, 1.0, 64.0, 1.0);
    private final StopWatch clickTimer = new StopWatch();
    private final StopWatch compassTimer = new StopWatch();
    private boolean joining;
    private int pageSwitches;
    private int targetGrief;

    public void startJoinTo(int griefId) {
        this.grief.setValue(griefId);
        if (!this.isEnabled()) {
            this.toggle();
            return;
        }
        this.startJoin();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.startJoin();
    }

    @Override
    public void onDisable() {
        this.joining = false;
        this.pageSwitches = 0;
        super.onDisable();
    }

    @Subscribe
    public void onUpdate(EventPlayerUpdate event) {
        if (!this.joining || this.mc.player == null || this.mc.world == null || this.mc.interactionManager == null) {
            return;
        }
        if (!(this.mc.currentScreen instanceof GenericContainerScreen)) {
            if (this.findCompassSlot() == -1) {
                this.autoDisable();
                return;
            }
            this.openServerSelector(false);
            return;
        }
        this.handleServerMenu();
    }

    @Subscribe
    public void onPacket(EventPacket event) {
        if (!this.joining || this.mc.player == null || this.mc.world == null || event.getType() != EventPacket.Type.RECEIVE) {
            return;
        }
        if (event.getPacket() instanceof GameJoinS2CPacket) {
            ChatUtil.send((Object)("Вход на гриф #" + this.targetGrief + ": успешно"));
            this.autoDisable();
            return;
        }
        Packet<?> packet = event.getPacket();
        if (!(packet instanceof GameMessageS2CPacket)) {
            return;
        }
        GameMessageS2CPacket packet2 = (GameMessageS2CPacket)packet;
        String message = packet2.comp_763().getString();
        if (message.contains("Подождите несколько секунд перед повторным подключением")) {
            event.cancelEvent();
            return;
        }
        if (message.contains("К сожалению сервер переполнен")) {
            event.cancelEvent();
            ChatUtil.send((Object)("Вход на гриф #" + this.targetGrief + ": неудачно"));
            return;
        }
        this.openServerSelector(false);
    }

    private void startJoin() {
        this.joining = true;
        this.pageSwitches = 0;
        this.targetGrief = (int)Math.round(this.grief.getValue());
        this.clickTimer.reset();
        this.compassTimer.reset();
        if (this.mc.player != null && this.mc.world != null) {
            this.openServerSelector(true);
        }
    }

    private void openServerSelector(boolean force) {
        if (!force && !this.compassTimer.isReached(30L)) {
            return;
        }
        if (this.mc.player == null || this.mc.interactionManager == null || this.mc.getNetworkHandler() == null) {
            return;
        }
        int previousSlot = this.mc.player.getInventory().selectedSlot;
        int slot = this.findCompassSlot();
        if (slot == -1) {
            return;
        }
        this.pageSwitches = 0;
        this.mc.player.getInventory().selectedSlot = slot;
        this.mc.getNetworkHandler().sendPacket((Packet)new UpdateSelectedSlotC2SPacket(slot));
        this.mc.interactionManager.interactItem((PlayerEntity)this.mc.player, Hand.MAIN_HAND);
        this.mc.player.getInventory().selectedSlot = previousSlot;
        this.mc.getNetworkHandler().sendPacket((Packet)new UpdateSelectedSlotC2SPacket(previousSlot));
        this.compassTimer.reset();
    }

    private int findCompassSlot() {
        if (this.mc.player == null) {
            return -1;
        }
        for (int i2 = 0; i2 < 9; ++i2) {
            if (this.mc.player.getInventory().getStack(i2).getItem() != Items.COMPASS) continue;
            return i2;
        }
        return -1;
    }

    private void handleServerMenu() {
        Slot nextPageSlot;
        Screen screen = this.mc.currentScreen;
        if (!(screen instanceof GenericContainerScreen)) {
            return;
        }
        GenericContainerScreen screen2 = (GenericContainerScreen)screen;
        if (!this.clickTimer.isReached(30L)) {
            return;
        }
        String title = screen2.getTitle().getString();
        ScreenHandler handler = screen2.getScreenHandler();
        if (title.contains("Выбор сервера")) {
            this.clickSlot(handler, 21);
            this.pageSwitches = 0;
            this.clickTimer.reset();
            return;
        }
        if (this.clickTargetGriefIfVisible(handler)) {
            return;
        }
        if (this.targetGrief > 36 && this.pageSwitches < 5 && (nextPageSlot = this.getSlot(handler, 44)) != null && nextPageSlot.hasStack()) {
            this.clickSlot(handler, 44);
            ++this.pageSwitches;
            this.clickTimer.reset();
        }
    }

    private boolean clickTargetGriefIfVisible(ScreenHandler handler) {
        String targetName = "ГРИФ #" + this.targetGrief + " (1.16.5+)";
        String targetPrefix = "ГРИФ #" + this.targetGrief;
        for (int slot = 0; slot < handler.slots.size(); ++slot) {
            String itemName;
            Slot containerSlot = handler.getSlot(slot);
            if (containerSlot == null || !containerSlot.hasStack() || !(itemName = containerSlot.getStack().getName().getString()).equalsIgnoreCase(targetName) && !itemName.toUpperCase().contains(targetPrefix)) continue;
            this.clickSlot(handler, slot);
            this.pageSwitches = 0;
            this.clickTimer.reset();
            return true;
        }
        return false;
    }

    private void autoDisable() {
        this.joining = false;
        this.pageSwitches = 0;
        if (this.isEnabled()) {
            this.toggle();
        }
    }

    private void clickSlot(ScreenHandler handler, int slot) {
        if (this.mc.player == null || this.mc.interactionManager == null) {
            return;
        }
        if (slot < 0 || slot >= handler.slots.size()) {
            return;
        }
        this.mc.interactionManager.clickSlot(handler.syncId, slot, 0, SlotActionType.PICKUP, (PlayerEntity)this.mc.player);
    }

    private Slot getSlot(ScreenHandler handler, int slot) {
        if (slot < 0 || slot >= handler.slots.size()) {
            return null;
        }
        return handler.getSlot(slot);
    }
}

