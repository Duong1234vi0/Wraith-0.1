package ua.babka.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;

@ModuleInformation(moduleName="AntiBot", moduleDesc="Не дает бить ботов на ReallyWorld", moduleCategory=ModuleCategory.COMBAT)
public class AntiBot
extends Module {
    public static AntiBot INSTANCE;
    private final List<PlayerEntity> botsMap = new ArrayList<PlayerEntity>();

    public AntiBot() {
        INSTANCE = this;
    }

    @Subscribe
    private void onUpdate(EventTick e2) {
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        for (PlayerEntity player : this.mc.world.getPlayers()) {
            boolean isBot;
            if (this.mc.player == player) continue;
            DefaultedList armor = player.getInventory().armor;
            boolean bl = isBot = ((ItemStack)armor.get(0)).getItem() != Items.AIR && ((ItemStack)armor.get(1)).getItem() != Items.AIR && ((ItemStack)armor.get(2)).getItem() != Items.AIR && ((ItemStack)armor.get(3)).getItem() != Items.AIR && ((ItemStack)armor.get(0)).isEnchantable() && ((ItemStack)armor.get(1)).isEnchantable() && ((ItemStack)armor.get(2)).isEnchantable() && ((ItemStack)armor.get(3)).isEnchantable() && player.getOffHandStack().getItem() == Items.AIR && (((ItemStack)armor.get(0)).getItem() == Items.LEATHER_BOOTS || ((ItemStack)armor.get(1)).getItem() == Items.LEATHER_LEGGINGS || ((ItemStack)armor.get(2)).getItem() == Items.LEATHER_CHESTPLATE || ((ItemStack)armor.get(3)).getItem() == Items.LEATHER_HELMET || ((ItemStack)armor.get(0)).getItem() == Items.IRON_BOOTS || ((ItemStack)armor.get(1)).getItem() == Items.IRON_LEGGINGS || ((ItemStack)armor.get(2)).getItem() == Items.IRON_CHESTPLATE || ((ItemStack)armor.get(3)).getItem() == Items.IRON_HELMET) && player.getMainHandStack().getItem() != Items.AIR && !((ItemStack)armor.get(0)).isDamaged() && !((ItemStack)armor.get(1)).isDamaged() && !((ItemStack)armor.get(2)).isDamaged() && !((ItemStack)armor.get(3)).isDamaged() && player.getHungerManager().getFoodLevel() == 20;
            if (isBot) {
                if (this.botsMap.contains(player)) continue;
                this.botsMap.add(player);
                continue;
            }
            this.botsMap.remove(player);
        }
    }

    public boolean isBot(PlayerEntity player) {
        return this.botsMap.contains(player);
    }

    public static boolean checkBot(LivingEntity entity) {
        PlayerEntity p2;
        return INSTANCE != null && entity instanceof PlayerEntity && INSTANCE.isBot(p2 = (PlayerEntity)entity);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.botsMap.clear();
    }
}

