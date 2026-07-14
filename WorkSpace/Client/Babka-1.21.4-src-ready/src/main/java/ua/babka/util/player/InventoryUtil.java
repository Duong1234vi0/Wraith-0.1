package ua.babka.util.player;

import com.google.common.collect.Lists;
import com.mojang.text2speech.Narrator;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.consume.UseAction;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.collection.DefaultedList;
import ua.babka.Babka;
import ua.babka.module.impl.movement.Sprint;
import ua.babka.util.IMinecraft;
import ua.babka.util.Instance;
import ua.babka.util.animation.SlownessManager;
import ua.babka.util.network.NetworkUtils;

public class InventoryUtil
implements IMinecraft {
    public static final InventoryUtil instance = new InventoryUtil();

    public static int searchItem(Item item) {
        for (int i2 = 0; i2 < InventoryUtil.mc.player.getInventory().getChangeCount(); ++i2) {
            if (!InventoryUtil.mc.player.getInventory().getStack(i2).getItem().equals(item)) continue;
            return i2;
        }
        return -1;
    }

    public static int searchItem(Item item, int start, int end) {
        for (int i2 = start; i2 < end; ++i2) {
            if (!InventoryUtil.mc.player.getInventory().getStack(i2).getItem().equals(item)) continue;
            return i2;
        }
        return -1;
    }

    public static int searchItem(List<Item> items) {
        for (int i2 = 0; i2 < InventoryUtil.mc.player.getInventory().getChangeCount(); ++i2) {
            for (Item item : items) {
                if (!InventoryUtil.mc.player.getInventory().getStack(i2).getItem().equals(item)) continue;
                return i2;
            }
        }
        return -1;
    }

    public static int searchItemHotbar(Item item) {
        for (int i2 = 0; i2 < 9; ++i2) {
            if (!InventoryUtil.mc.player.getInventory().getStack(i2).getItem().equals(item)) continue;
            return i2;
        }
        return -1;
    }

    public static int searchItemHotbar(List<Item> items) {
        for (int i2 = 0; i2 < 9; ++i2) {
            for (Item item : items) {
                if (!InventoryUtil.mc.player.getInventory().getStack(i2).getItem().equals(item)) continue;
                return i2;
            }
        }
        return -1;
    }

    public static int searchItemStack(Predicate<ItemStack> predicate) {
        for (int i2 = 0; i2 < 45; ++i2) {
            ItemStack stack = InventoryUtil.mc.player.getInventory().getStack(i2);
            if (stack.isEmpty() || !predicate.test(stack)) continue;
            return i2;
        }
        return -1;
    }

    public static int searchHotbarStack(Predicate<ItemStack> predicate) {
        for (int i2 = 0; i2 < 9; ++i2) {
            ItemStack stack = InventoryUtil.mc.player.getInventory().getStack(i2);
            if (stack.isEmpty() || !predicate.test(stack)) continue;
            return i2;
        }
        return -1;
    }

    public static void swapWithBypassGrim(Runnable runnable) {
        if (InventoryUtil.mc.player == null || mc.getNetworkHandler() == null) {
            return;
        }
        try {
            mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(new PlayerInput(false, false, false, false, false, false, false)));
            if (Babka.getInstance().getServerManager().isServerSprinting()) {
                InventoryUtil.mc.player.setSprinting(false);
                mc.getNetworkHandler().sendPacket((Packet)new ClientCommandC2SPacket((Entity)InventoryUtil.mc.player, ClientCommandC2SPacket.Mode.STOP_SPRINTING));
                if (!Instance.get(Sprint.class).isEnabled()) {
                    InventoryUtil.mc.options.sprintKey.setPressed(false);
                }
            }
            runnable.run();
            NetworkUtils.sendSilentPacket(new CloseHandledScreenC2SPacket(0));
            mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(InventoryUtil.mc.player.input.playerInput));
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void swapWithBypassPolar(Runnable runnable) {
        InventoryUtil.swapWithBypassPolar(runnable, 100L);
    }

    public static void swapWithBypassPolar(Runnable runnable, long duration) {
        if (InventoryUtil.mc.player == null || mc.getNetworkHandler() == null) {
            return;
        }
        try {
            SlownessManager.addTask(new SlownessManager.SlowTask(duration, () -> {
                if (InventoryUtil.mc.player.isUsingItem()) {
                    InventoryUtil.mc.interactionManager.stopUsingItem((PlayerEntity)InventoryUtil.mc.player);
                }
                runnable.run();
                NetworkUtils.sendSilentPacket(new CloseHandledScreenC2SPacket(0));
            }));
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void swapAndUseHvH(Item item) {
        if (InventoryUtil.mc.player.getItemCooldownManager().isCoolingDown(new ItemStack((ItemConvertible)item))) {
            return;
        }
        int slot = InventoryUtil.searchItem(item, 9, 45);
        int slotHotbar = InventoryUtil.searchItem(item, 0, 9);
        int previousSlot = InventoryUtil.mc.player.getInventory().selectedSlot;
        if (InventoryUtil.mc.player.getMainHandStack().getItem() == item) {
            InventoryUtil.mc.interactionManager.interactItem((PlayerEntity)InventoryUtil.mc.player, Hand.MAIN_HAND);
            return;
        }
        if (InventoryUtil.mc.player.getOffHandStack().getItem() == item) {
            InventoryUtil.mc.interactionManager.interactItem((PlayerEntity)InventoryUtil.mc.player, Hand.OFF_HAND);
            return;
        }
        if (slotHotbar != -1) {
            mc.getNetworkHandler().sendPacket((Packet)new UpdateSelectedSlotC2SPacket(slotHotbar));
            InventoryUtil.mc.interactionManager.interactItem((PlayerEntity)InventoryUtil.mc.player, Hand.MAIN_HAND);
            mc.getNetworkHandler().sendPacket((Packet)new UpdateSelectedSlotC2SPacket(previousSlot));
        }
        if (slotHotbar == -1 && slot != -1) {
            int slotCorrectable = -1;
            for (int slotNone = 0; slotNone < 8; ++slotNone) {
                UseAction action;
                ItemStack stack = InventoryUtil.mc.player.getInventory().getStack(slotNone);
                if (stack.isEmpty()) {
                    slotCorrectable = slotNone;
                }
                if ((action = stack.getUseAction()) != UseAction.NONE) continue;
                slotCorrectable = slotNone;
            }
            if (slotCorrectable == -1) {
                mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(new PlayerInput(false, false, false, false, false, false, false)));
                if (Babka.getInstance().getServerManager().isServerSprinting()) {
                    InventoryUtil.mc.player.setSprinting(false);
                    mc.getNetworkHandler().sendPacket((Packet)new ClientCommandC2SPacket((Entity)InventoryUtil.mc.player, ClientCommandC2SPacket.Mode.STOP_SPRINTING));
                    if (!Instance.get(Sprint.class).isEnabled()) {
                        InventoryUtil.mc.options.sprintKey.setPressed(false);
                    }
                }
                InventoryUtil.mc.interactionManager.clickSlot(0, slot, 8, SlotActionType.SWAP, (PlayerEntity)InventoryUtil.mc.player);
                mc.getNetworkHandler().sendPacket((Packet)new CloseHandledScreenC2SPacket(0));
                mc.getNetworkHandler().sendPacket((Packet)new UpdateSelectedSlotC2SPacket(8));
                InventoryUtil.mc.interactionManager.interactItem((PlayerEntity)InventoryUtil.mc.player, Hand.MAIN_HAND);
                mc.getNetworkHandler().sendPacket((Packet)new UpdateSelectedSlotC2SPacket(previousSlot));
                mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(InventoryUtil.mc.player.input.playerInput));
            } else {
                mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(new PlayerInput(false, false, false, false, false, false, false)));
                if (Babka.getInstance().getServerManager().isServerSprinting()) {
                    InventoryUtil.mc.player.setSprinting(false);
                    mc.getNetworkHandler().sendPacket((Packet)new ClientCommandC2SPacket((Entity)InventoryUtil.mc.player, ClientCommandC2SPacket.Mode.STOP_SPRINTING));
                    if (!Instance.get(Sprint.class).isEnabled()) {
                        InventoryUtil.mc.options.sprintKey.setPressed(false);
                    }
                }
                InventoryUtil.mc.interactionManager.clickSlot(0, slot, slotCorrectable, SlotActionType.SWAP, (PlayerEntity)InventoryUtil.mc.player);
                mc.getNetworkHandler().sendPacket((Packet)new CloseHandledScreenC2SPacket(0));
                mc.getNetworkHandler().sendPacket((Packet)new UpdateSelectedSlotC2SPacket(slotCorrectable));
                InventoryUtil.mc.interactionManager.interactItem((PlayerEntity)InventoryUtil.mc.player, Hand.MAIN_HAND);
                mc.getNetworkHandler().sendPacket((Packet)new UpdateSelectedSlotC2SPacket(previousSlot));
                InventoryUtil.mc.interactionManager.clickSlot(0, slot, slotCorrectable, SlotActionType.SWAP, (PlayerEntity)InventoryUtil.mc.player);
                mc.getNetworkHandler().sendPacket((Packet)new CloseHandledScreenC2SPacket(0));
                mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(InventoryUtil.mc.player.input.playerInput));
            }
        }
    }

    public static void swapAndUseLegit(Item item) {
        if (InventoryUtil.mc.player.getItemCooldownManager().isCoolingDown(new ItemStack((ItemConvertible)item))) {
            return;
        }
        int slot = InventoryUtil.searchItem(item, 9, 45);
        int slotHotbar = InventoryUtil.searchItem(item, 0, 9);
        int previousSlot = InventoryUtil.mc.player.getInventory().selectedSlot;
        if (InventoryUtil.mc.player.getMainHandStack().getItem() == item) {
            InventoryUtil.mc.interactionManager.interactItem((PlayerEntity)InventoryUtil.mc.player, Hand.MAIN_HAND);
            return;
        }
        if (InventoryUtil.mc.player.getOffHandStack().getItem() == item) {
            InventoryUtil.mc.interactionManager.interactItem((PlayerEntity)InventoryUtil.mc.player, Hand.OFF_HAND);
            return;
        }
        if (slotHotbar != -1) {
            InventoryUtil.mc.player.getInventory().selectedSlot = slotHotbar;
            InventoryUtil.mc.interactionManager.syncSelectedSlot();
            InventoryUtil.mc.interactionManager.sendSequencedPacket(InventoryUtil.mc.world, sequence -> new PlayerInteractItemC2SPacket(Hand.MAIN_HAND, sequence, InventoryUtil.mc.player.getYaw(), InventoryUtil.mc.player.getPitch()));
            InventoryUtil.mc.player.getInventory().selectedSlot = previousSlot;
        }
        if (slotHotbar == -1 && slot != -1) {
            int slotCorrectable = -1;
            for (int slotNone = 0; slotNone < 8; ++slotNone) {
                UseAction action;
                ItemStack stack = InventoryUtil.mc.player.getInventory().getStack(slotNone);
                if (stack.isEmpty()) {
                    slotCorrectable = slotNone;
                }
                if ((action = stack.getUseAction()) != UseAction.NONE) continue;
                slotCorrectable = slotNone;
            }
            if (slotCorrectable == -1) {
                mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(new PlayerInput(false, false, false, false, false, false, false)));
                InventoryUtil.mc.interactionManager.clickSlot(0, slot, 8, SlotActionType.SWAP, (PlayerEntity)InventoryUtil.mc.player);
                mc.getNetworkHandler().sendPacket((Packet)new CloseHandledScreenC2SPacket(0));
                InventoryUtil.mc.player.getInventory().selectedSlot = slotCorrectable;
                InventoryUtil.mc.interactionManager.syncSelectedSlot();
                InventoryUtil.mc.interactionManager.sendSequencedPacket(InventoryUtil.mc.world, sequence -> new PlayerInteractItemC2SPacket(Hand.MAIN_HAND, sequence, InventoryUtil.mc.player.getYaw(), InventoryUtil.mc.player.getPitch()));
                InventoryUtil.mc.player.getInventory().selectedSlot = previousSlot;
                mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(InventoryUtil.mc.player.input.playerInput));
            } else {
                mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(new PlayerInput(false, false, false, false, false, false, false)));
                InventoryUtil.mc.interactionManager.clickSlot(0, slot, slotCorrectable, SlotActionType.SWAP, (PlayerEntity)InventoryUtil.mc.player);
                mc.getNetworkHandler().sendPacket((Packet)new CloseHandledScreenC2SPacket(0));
                InventoryUtil.mc.player.getInventory().selectedSlot = slotCorrectable;
                InventoryUtil.mc.interactionManager.syncSelectedSlot();
                InventoryUtil.mc.interactionManager.sendSequencedPacket(InventoryUtil.mc.world, sequence -> new PlayerInteractItemC2SPacket(Hand.MAIN_HAND, sequence, InventoryUtil.mc.player.getYaw(), InventoryUtil.mc.player.getPitch()));
                InventoryUtil.mc.player.getInventory().selectedSlot = previousSlot;
                InventoryUtil.mc.interactionManager.clickSlot(0, slot, slotCorrectable, SlotActionType.SWAP, (PlayerEntity)InventoryUtil.mc.player);
                mc.getNetworkHandler().sendPacket((Packet)new CloseHandledScreenC2SPacket(0));
                mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(InventoryUtil.mc.player.input.playerInput));
            }
        }
    }

    public static void clickSlotNoSync(int syncId, int slotId, int button, SlotActionType actionType, PlayerEntity player) {
        ScreenHandler screenHandler = player.currentScreenHandler;
        if (syncId != screenHandler.syncId) {
            Narrator.LOGGER.warn("Ignoring click in mismatching container. Click in {}, player has {}.", (Object)syncId, (Object)screenHandler.syncId);
        } else {
            DefaultedList defaultedList = screenHandler.slots;
            int i2 = defaultedList.size();
            ArrayList list = Lists.newArrayListWithCapacity((int)i2);
            for (Slot slot : defaultedList) {
                list.add(slot.getStack().copy());
            }
            screenHandler.onSlotClick(slotId, button, actionType, player);
            Int2ObjectOpenHashMap int2ObjectMap = new Int2ObjectOpenHashMap();
            for (int j2 = 0; j2 < i2; ++j2) {
                ItemStack itemStack2;
                ItemStack itemStack = (ItemStack)list.get(j2);
                if (ItemStack.areEqual((ItemStack)itemStack, (ItemStack)(itemStack2 = ((Slot)defaultedList.get(j2)).getStack()))) continue;
                int2ObjectMap.put(j2, (Object)itemStack2.copy());
            }
            NetworkUtils.sendSilentPacket(new ClickSlotC2SPacket(syncId, screenHandler.getRevision(), slotId, button, actionType, screenHandler.getCursorStack().copy(), (Int2ObjectMap)int2ObjectMap));
        }
    }

    public static int findBestElytraSlot() {
        int bestSlot = -1;
        double bestScore = -1.0;
        RegistryEntry protection = (RegistryEntry)((Registry)MinecraftClient.getInstance().world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get()).getEntry(Enchantments.PROTECTION.getValue()).orElseThrow();
        RegistryEntry unbreaking = (RegistryEntry)((Registry)MinecraftClient.getInstance().world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get()).getEntry(Enchantments.UNBREAKING.getValue()).orElseThrow();
        RegistryEntry mending = (RegistryEntry)((Registry)MinecraftClient.getInstance().world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get()).getEntry(Enchantments.MENDING.getValue()).orElseThrow();
        for (int slot = 0; slot < 36; ++slot) {
            int currentDamage;
            int maxDurability;
            double durabilityRatio;
            int mendLevel;
            int unbLevel;
            int protLevel;
            double score;
            ItemStack stack = InventoryUtil.mc.player.getInventory().getStack(slot);
            if (!stack.isOf(Items.ELYTRA) || !((score = (double)((protLevel = EnchantmentHelper.getLevel((RegistryEntry)protection, (ItemStack)stack)) * 100 + (unbLevel = EnchantmentHelper.getLevel((RegistryEntry)unbreaking, (ItemStack)stack)) * 10 + ((mendLevel = EnchantmentHelper.getLevel((RegistryEntry)mending, (ItemStack)stack)) > 0 ? 1 : 0)) + (durabilityRatio = (double)((maxDurability = stack.getMaxDamage()) - (currentDamage = stack.getDamage())) / (double)maxDurability) * 10.0) > bestScore)) continue;
            bestScore = score;
            bestSlot = slot;
        }
        return bestSlot;
    }

    public static int findBestChestplateSlot() {
        int bestSlot = -1;
        double bestScore = -1.0;
        RegistryEntry.Reference protection = (RegistryEntry.Reference)((Registry)InventoryUtil.mc.world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get()).getEntry(Enchantments.PROTECTION.getValue()).orElseThrow();
        RegistryEntry.Reference unbreaking = (RegistryEntry.Reference)((Registry)InventoryUtil.mc.world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get()).getEntry(Enchantments.UNBREAKING.getValue()).orElseThrow();
        RegistryEntry.Reference mending = (RegistryEntry.Reference)((Registry)InventoryUtil.mc.world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get()).getEntry(Enchantments.MENDING.getValue()).orElseThrow();
        for (int slot = 0; slot < 36; ++slot) {
            int currentDamage;
            int maxDurability;
            double durabilityRatio;
            ItemStack stack = InventoryUtil.mc.player.getInventory().getStack(slot);
            Item item = stack.getItem();
            if (!(item instanceof ArmorItem)) continue;
            ArmorItem armor = (ArmorItem)item;
            int protLevel = EnchantmentHelper.getLevel((RegistryEntry)protection, (ItemStack)stack);
            int unbLevel = EnchantmentHelper.getLevel((RegistryEntry)unbreaking, (ItemStack)stack);
            int mendLevel = EnchantmentHelper.getLevel((RegistryEntry)mending, (ItemStack)stack);
            int armorTypePriority = InventoryUtil.getChestplatePriority((Item)armor);
            double score = (double)(armorTypePriority * 10000 + protLevel * 100 + unbLevel * 10 + (mendLevel > 0 ? 1 : 0)) + (durabilityRatio = (double)((maxDurability = stack.getMaxDamage()) - (currentDamage = stack.getDamage())) / (double)maxDurability) * 10.0;
            if (!(score > bestScore)) continue;
            bestScore = score;
            bestSlot = slot;
        }
        return bestSlot;
    }

    private static int getArmorPriority(Item item) {
        if (item == Items.NETHERITE_HELMET || item == Items.NETHERITE_CHESTPLATE || item == Items.NETHERITE_LEGGINGS || item == Items.NETHERITE_BOOTS) {
            return 6;
        }
        if (item == Items.DIAMOND_HELMET || item == Items.DIAMOND_CHESTPLATE || item == Items.DIAMOND_LEGGINGS || item == Items.DIAMOND_BOOTS) {
            return 5;
        }
        if (item == Items.IRON_HELMET || item == Items.IRON_CHESTPLATE || item == Items.IRON_LEGGINGS || item == Items.IRON_BOOTS) {
            return 4;
        }
        if (item == Items.CHAINMAIL_HELMET || item == Items.CHAINMAIL_CHESTPLATE || item == Items.CHAINMAIL_LEGGINGS || item == Items.CHAINMAIL_BOOTS) {
            return 3;
        }
        if (item == Items.GOLDEN_HELMET || item == Items.GOLDEN_CHESTPLATE || item == Items.GOLDEN_LEGGINGS || item == Items.GOLDEN_BOOTS) {
            return 2;
        }
        if (item == Items.LEATHER_HELMET || item == Items.LEATHER_CHESTPLATE || item == Items.LEATHER_LEGGINGS || item == Items.LEATHER_BOOTS) {
            return 1;
        }
        return 0;
    }

    private static int getChestplatePriority(Item item) {
        if (item == Items.NETHERITE_CHESTPLATE) {
            return 6;
        }
        if (item == Items.DIAMOND_CHESTPLATE) {
            return 5;
        }
        if (item == Items.IRON_CHESTPLATE) {
            return 4;
        }
        if (item == Items.CHAINMAIL_CHESTPLATE) {
            return 3;
        }
        if (item == Items.GOLDEN_CHESTPLATE) {
            return 2;
        }
        if (item == Items.LEATHER_CHESTPLATE) {
            return 1;
        }
        return 0;
    }

    public static int getBestArmorSlot(EquipmentSlot slot) {
        int bestSlot = -1;
        double bestScore = -1.0;
        for (int i2 = 0; i2 < 36; ++i2) {
            double score;
            ItemStack stack = InventoryUtil.mc.player.getInventory().getStack(i2);
            if (!(stack.getItem() instanceof ArmorItem) || InventoryUtil.getNeededArmorSlot(stack) != slot || !((score = InventoryUtil.getArmorScore(stack)) > bestScore)) continue;
            bestScore = score;
            bestSlot = i2;
        }
        return bestSlot;
    }

    private static EquipmentSlot getNeededArmorSlot(ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof ArmorItem) {
            if (item == Items.NETHERITE_HELMET || item == Items.DIAMOND_HELMET || item == Items.IRON_HELMET || item == Items.GOLDEN_HELMET || item == Items.CHAINMAIL_HELMET || item == Items.LEATHER_HELMET) {
                return EquipmentSlot.HEAD;
            }
            if (item == Items.NETHERITE_CHESTPLATE || item == Items.DIAMOND_CHESTPLATE || item == Items.IRON_CHESTPLATE || item == Items.GOLDEN_CHESTPLATE || item == Items.CHAINMAIL_CHESTPLATE || item == Items.LEATHER_CHESTPLATE) {
                return EquipmentSlot.CHEST;
            }
            if (item == Items.NETHERITE_LEGGINGS || item == Items.DIAMOND_LEGGINGS || item == Items.IRON_LEGGINGS || item == Items.GOLDEN_LEGGINGS || item == Items.CHAINMAIL_LEGGINGS || item == Items.LEATHER_LEGGINGS) {
                return EquipmentSlot.LEGS;
            }
            if (item == Items.NETHERITE_BOOTS || item == Items.DIAMOND_BOOTS || item == Items.IRON_BOOTS || item == Items.GOLDEN_BOOTS || item == Items.CHAINMAIL_BOOTS || item == Items.LEATHER_BOOTS) {
                return EquipmentSlot.FEET;
            }
        }
        return null;
    }

    public static void sendDiscordWebhook(String url, String content) {
        if (url == null || url.isEmpty() || content == null) {
            return;
        }
        new Thread(() -> {
            try {
                String json = "{\"content\":\"" + content.replace("\"", "\\\"") + "\"}";
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(json)).build();
                client.send(request, HttpResponse.BodyHandlers.ofString());
            }
            catch (Exception exception) {
                // empty catch block
            }
        }).start();
    }

    public static double getArmorScore(ItemStack stack) {
        double score = 0.0;
        score += (double)InventoryUtil.getArmorPriority(stack.getItem());
        RegistryEntry.Reference protection = (RegistryEntry.Reference)((Registry)InventoryUtil.mc.world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get()).getEntry(Enchantments.PROTECTION.getValue()).orElseThrow();
        RegistryEntry.Reference unbreaking = (RegistryEntry.Reference)((Registry)InventoryUtil.mc.world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get()).getEntry(Enchantments.UNBREAKING.getValue()).orElseThrow();
        RegistryEntry.Reference mending = (RegistryEntry.Reference)((Registry)InventoryUtil.mc.world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get()).getEntry(Enchantments.MENDING.getValue()).orElseThrow();
        score += (double)EnchantmentHelper.getLevel((RegistryEntry)protection, (ItemStack)stack) * 0.75;
        score += (double)EnchantmentHelper.getLevel((RegistryEntry)unbreaking, (ItemStack)stack) * 0.3;
        score += (double)EnchantmentHelper.getLevel((RegistryEntry)mending, (ItemStack)stack) * 0.1;
        double durabilityFactor = 1.0;
        if (stack.isDamageable()) {
            int max = stack.getMaxDamage();
            int left = max - stack.getDamage();
            durabilityFactor = Math.max(0.05, (double)left / (double)max);
        }
        return score * durabilityFactor;
    }

    public static InventoryUtil getInstance() {
        return instance;
    }
}

