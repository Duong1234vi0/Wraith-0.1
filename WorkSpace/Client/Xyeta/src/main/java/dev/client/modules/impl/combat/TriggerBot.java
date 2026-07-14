package dev.client.modules.impl.combat;

import dev.client.WildClient;
import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.IEnableable;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.settings.Setting;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.util.aura.AuraUtil;
import dev.client.util.math.TimerUtil;
import java.util.concurrent.ThreadLocalRandom;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import ru.metaculture.sdk.Compile;

@Environment(EnvType.CLIENT)
public class TriggerBot extends Module implements ITickable, IEnableable {
   private final BooleanSetting onlyCrits = ((BooleanSetting)(new BooleanSetting()).name("Only Criticals")).value(false);
   private final BooleanSetting smartCrits = ((BooleanSetting)(new BooleanSetting() {
      public boolean isVisible() {
         return TriggerBot.this.onlyCrits.getValue();
      }
   }).name("Smart Criticals")).value(false);
   private final BooleanSetting attackInvisibles = ((BooleanSetting)(new BooleanSetting()).name("AttackInvisibles")).value(false);
   private final TimerUtil timerUtil;
   private LivingEntity target;
   private SyncTps syncTps;
   private final TimerUtil attackRand = new TimerUtil();
   private boolean targetted = false;
   private float randValue;

   public TriggerBot() {
      super(new ModuleInfo("TriggerBot", Category.COMBAT, "Бьет противника при наведении"));
      this.addSetting(new Setting[]{this.onlyCrits, this.smartCrits, this.attackInvisibles});
      this.timerUtil = new TimerUtil();
   }

   @Compile
   public void onTick(TickEvent event) {
      this.target = null;
      Entity entity = MinecraftClient.getInstance().targetedEntity;
      if (entity != null && MinecraftClient.getInstance().player != null && entity instanceof LivingEntity living) {
         if (living.isAlive()) {
            if (!this.targetted) {
               this.attackRand.reset();
               this.randValue = (float)ThreadLocalRandom.current().nextInt(25);
               this.targetted = true;
            }

            if (living instanceof PlayerEntity) {
               PlayerEntity player = (PlayerEntity)living;
               if (!WildClient.INSTANCE.getFriendManager().isFriend(player.getNameForScoreboard())) {
                  this.target = player;
                  this.attack(player);
                  return;
               }
            }

            if (!(living instanceof PlayerEntity)) {
               this.target = living;
               this.attack(entity);
            }

            return;
         }
      }

      this.targetted = false;
   }

   private boolean invisibleCheck(Entity entity) {
      return this.attackInvisibles.getValue() ? true : !entity.isInvisible();
   }

   @Compile
   private void attack(Entity entity) {
      if (AuraUtil.canAttack(this.smartCrits.getValue(), this.syncTps) && this.attackRand.isReached((long)this.randValue) && this.timerUtil.isReached(550L) && entity.isAlive() && this.invisibleCheck(entity)) {
         MinecraftClient.getInstance().interactionManager.attackEntity(MinecraftClient.getInstance().player, entity);
         MinecraftClient.getInstance().player.swingHand(Hand.MAIN_HAND);
         this.timerUtil.reset();
      }

   }

   public LivingEntity getTarget() {
      return this.target;
   }

   public void onEnable() {
      this.syncTps = (SyncTps)WildClient.INSTANCE.getModuleManager().getByClass(SyncTps.class);
   }
}
