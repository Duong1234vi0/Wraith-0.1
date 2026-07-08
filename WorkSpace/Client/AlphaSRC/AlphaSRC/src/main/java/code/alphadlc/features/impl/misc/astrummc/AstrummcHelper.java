/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2653
 */
package code.alphadlc.features.impl.misc.astrummc;

import code.alphadlc.events.item.ClickSlotEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.HotBarUpdateEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.misc.astrummc.craft.LegendaryCraftDatabase;
import code.alphadlc.features.impl.misc.astrummc.craft.LegendaryCraftLogic;
import code.alphadlc.features.impl.misc.astrummc.craft.LegendaryCraftNotifier;
import code.alphadlc.features.impl.misc.astrummc.craft.LegendaryCraftRecipe;
import code.alphadlc.features.impl.misc.astrummc.craft.LegendaryCraftRecipes;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import java.util.List;
import java.util.Map;
import net.minecraft.class_2653;

public class AstrummcHelper
extends Module {
    private final LegendaryCraftNotifier notifier = new LegendaryCraftNotifier();
    private String lastInventoryFingerprint = "";

    public AstrummcHelper() {
        super("AstrummcHelper", "Astrummc Helper", ModuleCategory.MISC);
    }

    @Override
    public void activate() {
        super.activate();
        this.lastInventoryFingerprint = "";
        this.notifier.reset();
        this.checkInventory(true);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.lastInventoryFingerprint = "";
        this.notifier.reset();
    }

    @EventHandler
    public void onTick(TickEvent event) {
        this.checkInventory(false);
    }

    @EventHandler
    public void onClickSlot(ClickSlotEvent event) {
        this.checkInventory(false);
    }

    @EventHandler
    public void onHotBarUpdate(HotBarUpdateEvent event) {
        this.checkInventory(false);
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof class_2653) {
            this.checkInventory(false);
        }
    }

    private void checkInventory(boolean force) {
        if (AstrummcHelper.mc.field_1724 == null || AstrummcHelper.mc.field_1687 == null) {
            return;
        }
        Map<String, Integer> inventory = LegendaryCraftDatabase.collectInventoryCounts(InventoryTask::getInventoryCount);
        String fingerprint = LegendaryCraftDatabase.buildFingerprint(inventory);
        if (!force && fingerprint.equals(this.lastInventoryFingerprint)) {
            return;
        }
        this.lastInventoryFingerprint = fingerprint;
        for (LegendaryCraftRecipe recipe : LegendaryCraftRecipes.ALL) {
            LegendaryCraftLogic.RecipeStatus status = LegendaryCraftLogic.evaluate(recipe, inventory);
            List<String> messages = this.notifier.process(recipe, status);
            for (String message : messages) {
                ChatMessage.brandmessage(message);
            }
        }
    }
}
