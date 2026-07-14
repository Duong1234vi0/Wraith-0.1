package ua.babka.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.HopperScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.mixin.ScreenAccessor;

@Mixin(value={HandledScreen.class})
public abstract class HandledScreenMixin {
    @Shadow
    protected int x;
    @Shadow
    protected int y;
    @Shadow
    protected int backgroundWidth;
    @Shadow
    protected int backgroundHeight;
    @Shadow
    protected ScreenHandler handler;
    @Unique
    private ButtonWidget takeAllButton;
    @Unique
    private ButtonWidget putAllButton;
    @Unique
    private ButtonWidget throwAllButton;

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void onInit(CallbackInfo ci) {
        if (!this.isStorageContainer()) {
            return;
        }
        int buttonWidth = 80;
        int startX = this.x + this.backgroundWidth + 4;
        int startY = this.y;
        if (this.takeAllButton == null) {
            this.takeAllButton = ButtonWidget.builder((Text)Text.literal((String)"Взять"), btn -> this.takeAll()).dimensions(startX, startY, buttonWidth, 20).build();
            this.putAllButton = ButtonWidget.builder((Text)Text.literal((String)"Сложить"), btn -> this.putAll()).dimensions(startX, startY + 22, buttonWidth, 20).build();
            this.throwAllButton = ButtonWidget.builder((Text)Text.literal((String)"Выкинуть"), btn -> this.throwAll()).dimensions(startX, startY + 44, buttonWidth, 20).build();
        } else {
            this.takeAllButton.setX(startX);
            this.takeAllButton.setY(startY);
            this.putAllButton.setX(startX);
            this.putAllButton.setY(startY + 22);
            this.throwAllButton.setX(startX);
            this.throwAllButton.setY(startY + 44);
        }
        ScreenAccessor accessor = (ScreenAccessor)((Object)this);
        accessor.invokeAddDrawableChild(this.takeAllButton);
        accessor.invokeAddDrawableChild(this.putAllButton);
        accessor.invokeAddDrawableChild(this.throwAllButton);
    }

    @Inject(method={"render"}, at={@At(value="HEAD")})
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this.takeAllButton == null) {
            return;
        }
        this.takeAllButton.active = this.hasItemsInContainer();
        this.putAllButton.active = this.hasItemsInPlayerInventory();
        this.throwAllButton.active = this.hasItemsInContainer();
    }

    @Unique
    private boolean isStorageContainer() {
        return this.handler instanceof GenericContainerScreenHandler || this.handler instanceof ShulkerBoxScreenHandler || this.handler instanceof HopperScreenHandler;
    }

    @Unique
    private int getContainerSlotCount() {
        ScreenHandler screenHandler = this.handler;
        if (screenHandler instanceof GenericContainerScreenHandler) {
            GenericContainerScreenHandler c2 = (GenericContainerScreenHandler)screenHandler;
            return c2.getRows() * 9;
        }
        if (this.handler instanceof ShulkerBoxScreenHandler) {
            return 27;
        }
        if (this.handler instanceof HopperScreenHandler) {
            return 5;
        }
        return 0;
    }

    @Unique
    private boolean hasItemsInContainer() {
        int count = this.getContainerSlotCount();
        for (int i2 = 0; i2 < count; ++i2) {
            Slot slot = this.handler.getSlot(i2);
            if (!slot.hasStack()) continue;
            return true;
        }
        return false;
    }

    @Unique
    private boolean hasItemsInPlayerInventory() {
        int count;
        for (int i2 = count = this.getContainerSlotCount(); i2 < this.handler.slots.size(); ++i2) {
            Slot slot = this.handler.getSlot(i2);
            if (!slot.hasStack()) continue;
            return true;
        }
        return false;
    }

    @Unique
    private void takeAll() {
        if (!this.isStorageContainer()) {
            return;
        }
        int count = this.getContainerSlotCount();
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.interactionManager == null || client.player == null) {
            return;
        }
        for (int i2 = count - 1; i2 >= 0; --i2) {
            Slot slot = this.handler.getSlot(i2);
            if (!slot.hasStack()) continue;
            client.interactionManager.clickSlot(this.handler.syncId, i2, 0, SlotActionType.QUICK_MOVE, (PlayerEntity)client.player);
        }
    }

    @Unique
    private void putAll() {
        if (!this.isStorageContainer()) {
            return;
        }
        int count = this.getContainerSlotCount();
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.interactionManager == null || client.player == null) {
            return;
        }
        for (int i2 = count; i2 < this.handler.slots.size(); ++i2) {
            Slot slot = this.handler.getSlot(i2);
            if (!slot.hasStack()) continue;
            client.interactionManager.clickSlot(this.handler.syncId, i2, 0, SlotActionType.QUICK_MOVE, (PlayerEntity)client.player);
        }
    }

    @Unique
    private void throwAll() {
        if (!this.isStorageContainer()) {
            return;
        }
        int count = this.getContainerSlotCount();
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.interactionManager == null || client.player == null) {
            return;
        }
        for (int i2 = count - 1; i2 >= 0; --i2) {
            Slot slot = this.handler.getSlot(i2);
            if (!slot.hasStack()) continue;
            client.interactionManager.clickSlot(this.handler.syncId, i2, 1, SlotActionType.THROW, (PlayerEntity)client.player);
        }
    }
}

