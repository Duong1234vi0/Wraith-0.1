/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1713
 *  net.minecraft.class_1723
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_364
 *  net.minecraft.class_4185
 *  net.minecraft.class_465
 *  net.minecraft.class_490
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.display.widgets.ClearButtonWidget;
import code.alphadlc.features.impl.movement.GuiMove;
import code.alphadlc.mixins.IScreen;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1723;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_465;
import net.minecraft.class_490;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_490.class})
public abstract class InventoryScreenMixin
extends class_465<class_1723> {
    @Unique
    private ClearButtonWidget deleteButton;
    @Unique
    private static volatile boolean deleting = false;
    @Unique
    private static final ScheduledExecutorService clearExecutor = Executors.newSingleThreadScheduledExecutor();

    @Unique
    public ClearButtonWidget getDeleteButton() {
        return this.deleteButton;
    }

    public InventoryScreenMixin(class_1723 handler, class_1661 inventory, class_2561 title) {
        super((class_1703)handler, inventory, title);
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void onInit(CallbackInfo ci) {
        if (!GuiMove.mode.isSelected("SpookyTime")) {
            return;
        }
        class_490 screen = (class_490)this;
        this.deleteButton = new ClearButtonWidget(0, 0, 12, 12, () -> this.deleteAllItems());
        IScreen iScreen = (IScreen)screen;
        iScreen.getDrawables().add(this.deleteButton);
        iScreen.getSelectables().add(this.deleteButton);
        iScreen.getChildren().add(this.deleteButton);
    }

    @Inject(method={"render"}, at={@At(value="TAIL")})
    private void onRender(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this.deleteButton != null && GuiMove.mode.isSelected("SpookyTime")) {
            class_490 screen;
            class_490 screenBase = screen = (class_490)this;
            int guiLeft = (screenBase.field_22789 - this.field_2792) / 2;
            int guiTop = (screenBase.field_22790 - 166) / 2;
            int buttonX = guiLeft + this.field_2792 - 12 - 5;
            int buttonY = guiTop + 5;
            this.deleteButton.setPosition(buttonX, buttonY);
        } else if (this.deleteButton != null && !GuiMove.mode.isSelected("SpookyTime")) {
            class_490 screen = (class_490)this;
            IScreen iScreen = (IScreen)screen;
            iScreen.getDrawables().remove(this.deleteButton);
            iScreen.getSelectables().remove(this.deleteButton);
            iScreen.getChildren().remove(this.deleteButton);
            this.deleteButton = null;
        }
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void addDropAllButton(CallbackInfo ci) {
        class_310 mc = class_310.method_1551();
        class_490 screen = (class_490)this;
        int x = screen.field_22789 / 2 - 40;
        int y = screen.field_22790 / 2 - 110;
        class_4185 dropAllButton = class_4185.method_46430((class_2561)class_2561.method_30163((String)"\u0412\u044b\u043a\u0438\u043d\u0443\u0442\u044c \u0432\u0441\u0435"), button -> this.dropAllItems(mc)).method_46433(x, y).method_46437(80, 20).method_46431();
        screen.method_37063((class_364)dropAllButton);
    }

    private void dropAllItems(class_310 mc) {
        class_1799 stack;
        int i;
        class_746 player = mc.field_1724;
        if (player == null || player.field_7512 == null) {
            return;
        }
        for (i = 5; i <= 8; ++i) {
            mc.field_1761.method_2906(player.field_7512.field_7763, i, 1, class_1713.field_7795, (class_1657)player);
        }
        mc.field_1761.method_2906(player.field_7512.field_7763, 45, 1, class_1713.field_7795, (class_1657)player);
        for (i = 9; i < 36; ++i) {
            stack = player.method_31548().method_5438(i);
            if (stack.method_7960()) continue;
            mc.field_1761.method_2906(player.field_7512.field_7763, i, 1, class_1713.field_7795, (class_1657)player);
        }
        for (i = 0; i < 9; ++i) {
            stack = player.method_31548().method_5438(i);
            if (stack.method_7960()) continue;
            mc.field_1761.method_2906(player.field_7512.field_7763, i + 36, 1, class_1713.field_7795, (class_1657)player);
        }
    }

    private void deleteAllItems() {
        if (deleting) {
            return;
        }
        deleting = true;
        for (int i = 0; i < 36; ++i) {
            int slot = i;
            clearExecutor.schedule(() -> {
                class_1799 stack;
                if (QuickImports.mc.field_1724 != null && QuickImports.mc.field_1761 != null && QuickImports.mc.field_1724.field_7512 != null && (stack = QuickImports.mc.field_1724.method_31548().method_5438(slot)).method_7909() != class_1802.field_8162) {
                    int containerSlot = slot < 9 ? slot + 36 : slot;
                    QuickImports.mc.field_1761.method_2906(QuickImports.mc.field_1724.field_7512.field_7763, containerSlot, 45, class_1713.field_7791, (class_1657)QuickImports.mc.field_1724);
                }
            }, (long)i * 80L, TimeUnit.MILLISECONDS);
        }
        clearExecutor.schedule(() -> {
            class_1799 stack;
            if (QuickImports.mc.field_1724 != null && QuickImports.mc.field_1761 != null && QuickImports.mc.field_1724.field_7512 != null && (stack = QuickImports.mc.field_1724.method_31548().method_5438(40)).method_7909() != class_1802.field_8162) {
                QuickImports.mc.field_1761.method_2906(QuickImports.mc.field_1724.field_7512.field_7763, 45, 45, class_1713.field_7791, (class_1657)QuickImports.mc.field_1724);
            }
        }, 2880L, TimeUnit.MILLISECONDS);
        clearExecutor.schedule(() -> {
            class_1799 stack;
            if (QuickImports.mc.field_1724 != null && QuickImports.mc.field_1761 != null && QuickImports.mc.field_1724.field_7512 != null && (stack = QuickImports.mc.field_1724.method_31548().method_5438(39)).method_7909() != class_1802.field_8162) {
                QuickImports.mc.field_1761.method_2906(QuickImports.mc.field_1724.field_7512.field_7763, 5, 45, class_1713.field_7791, (class_1657)QuickImports.mc.field_1724);
            }
        }, 2960L, TimeUnit.MILLISECONDS);
        clearExecutor.schedule(() -> {
            class_1799 stack;
            if (QuickImports.mc.field_1724 != null && QuickImports.mc.field_1761 != null && QuickImports.mc.field_1724.field_7512 != null && (stack = QuickImports.mc.field_1724.method_31548().method_5438(38)).method_7909() != class_1802.field_8162) {
                QuickImports.mc.field_1761.method_2906(QuickImports.mc.field_1724.field_7512.field_7763, 6, 45, class_1713.field_7791, (class_1657)QuickImports.mc.field_1724);
            }
        }, 3040L, TimeUnit.MILLISECONDS);
        clearExecutor.schedule(() -> {
            class_1799 stack;
            if (QuickImports.mc.field_1724 != null && QuickImports.mc.field_1761 != null && QuickImports.mc.field_1724.field_7512 != null && (stack = QuickImports.mc.field_1724.method_31548().method_5438(37)).method_7909() != class_1802.field_8162) {
                QuickImports.mc.field_1761.method_2906(QuickImports.mc.field_1724.field_7512.field_7763, 7, 45, class_1713.field_7791, (class_1657)QuickImports.mc.field_1724);
            }
        }, 3120L, TimeUnit.MILLISECONDS);
        clearExecutor.schedule(() -> {
            class_1799 stack;
            if (QuickImports.mc.field_1724 != null && QuickImports.mc.field_1761 != null && QuickImports.mc.field_1724.field_7512 != null && (stack = QuickImports.mc.field_1724.method_31548().method_5438(36)).method_7909() != class_1802.field_8162) {
                QuickImports.mc.field_1761.method_2906(QuickImports.mc.field_1724.field_7512.field_7763, 8, 45, class_1713.field_7791, (class_1657)QuickImports.mc.field_1724);
            }
            deleting = false;
        }, 3200L, TimeUnit.MILLISECONDS);
    }
}
