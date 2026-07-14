package sky.core.modules.impl.miscellaneous;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CUseEntityPacket;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.events.EventAttack;
import sky.core.events.EventGameUpdate;
import sky.core.events.EventMotion;
import sky.core.events.EventPacket;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.StopWatch;
import sky.core.utils.math.TimeUtil;

public class FakeLag extends Module {
   private final BooleanSetting пульс = new BooleanSetting("Пульс", false);
   private final BooleanSetting рендер = new BooleanSetting("Рендер позиции", false);
   private final SliderSetting лаг;
   private final BooleanSetting атака;
   TimeUtil asd;
   public final StopWatch часы;
   private final CopyOnWriteArrayList пакеты;
   boolean стоп;
   boolean цвет;
   private long начался;
   final StopWatch стопер;
   Vector3d прошлаяПозиция;
   private IPacket отложенныйПакет;
   private boolean ждётАтаку;
   private final ScheduledExecutorService планировщик;

   public FakeLag() {
      super("Fake Lag", "Создаёт фейковый пинг на сервере", Category.Combat);
      BooleanSetting var10008 = this.пульс;
      Objects.requireNonNull(var10008);
      this.лаг = new SliderSetting("Пинг", 100.0F, 100.0F, 2000.0F, 25.0F, var10008::get);
      this.атака = new BooleanSetting("Обновлять при атаке", false);
      this.asd = new TimeUtil();
      this.часы = new StopWatch();
      this.пакеты = new CopyOnWriteArrayList();
      this.стоп = false;
      this.цвет = false;
      this.стопер = new StopWatch();
      this.прошлаяПозиция = new Vector3d(0.0, 0.0, 0.0);
      this.отложенныйПакет = null;
      this.ждётАтаку = false;
      this.планировщик = Executors.newSingleThreadScheduledExecutor();
      this.addSettings(new Setting[]{this.пульс, this.лаг, this.атака});
   }

   @EventTarget
   @Native
   public void приОбновленииИгры(EventGameUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player == null || mc.world == null && this.isEnabled()) {
         this.toggle();
      }

   }

   @EventTarget
   @Native
   public void приПакете(EventPacket e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null && !mc.isSingleplayer()) {
         var10000 = mc;
         if (!Minecraft.player.getShouldBeDead()) {
            if (this.ждётАтаку && e.getPacket() instanceof CUseEntityPacket) {
               e.setCancelled(true);
               return;
            }

            boolean возврат = !this.цвет && e.getPacket() instanceof CUseEntityPacket && e.isSend();
            if (e.isSend()) {
               if (!возврат || !(Boolean)this.атака.get()) {
                  this.пакеты.add(e.getPacket());
               }

               e.setCancelled(true);
            }

            return;
         }
      }

      this.toggle();
   }

   @EventTarget
   @Native
   public void приОбновлении(EventMotion e) {
      if (System.currentTimeMillis() - this.начался >= 29900L) {
         this.toggle();
      }

      if (this.часы.finished((double)(Float)this.лаг.get()) && (Boolean)this.пульс.get() || this.стоп && (Boolean)this.атака.get() && this.стопер.finished(20.0)) {
         Minecraft var10001 = mc;
         this.прошлаяПозиция = Minecraft.player.getPositionVec();
         Iterator var2 = this.пакеты.iterator();

         while(var2.hasNext()) {
            IPacket пакет = (IPacket)var2.next();
            Minecraft var10000 = mc;
            Minecraft.player.connection.getNetworkManager().sendPacketWithoutEvent(пакет);
         }

         this.стоп = false;
         this.пакеты.clear();
         this.начался = System.currentTimeMillis();
         this.часы.reset();
      }

   }

   @EventTarget
   @Native
   public void приАтаке(EventAttack e) {
      if ((Boolean)this.атака.get()) {
         Entity var10000 = e.getTarget();
         Minecraft var10001 = mc;
         double dist = (double)var10000.getDistance(Minecraft.player);
         if (dist < 4.5) {
            this.стопер.reset();
            this.стоп = true;
            this.цвет = true;
         } else {
            e.setCancelled(true);
            this.цвет = false;
            this.ждётАтаку = true;
            Entity var10003 = e.getTarget();
            Minecraft var10004 = mc;
            this.отложенныйПакет = new CUseEntityPacket(var10003, Minecraft.player.isSneaking());
            boolean былоВключено = this.isEnabled();
            if (былоВключено) {
               this.toggle();
            }

            this.планировщик.schedule(() -> {
               if (this.отложенныйПакет != null) {
                  Minecraft var10000 = mc;
                  Minecraft.player.connection.getNetworkManager().sendPacketWithoutEvent(this.отложенныйПакет);
               }

               this.отложенныйПакет = null;
               this.ждётАтаку = false;
               if (!this.isEnabled() && былоВключено) {
                  this.toggle();
               }

            }, 1L, TimeUnit.MILLISECONDS);
         }
      }
   }

   @EventTarget
   public void приРендере(EventRender3D e) {
   }

   @Native
   public void onEnable() {
      super.onEnable();
      this.начался = System.currentTimeMillis();
      Minecraft var10001 = mc;
      this.прошлаяПозиция = Minecraft.player.getPositionVec();
      this.часы.reset();
      this.стоп = false;
      this.стопер.reset();
   }

   @Native
   public void onDisable() {
      super.onDisable();
      Iterator var1 = this.пакеты.iterator();

      while(var1.hasNext()) {
         IPacket пакет = (IPacket)var1.next();
         Minecraft var10000 = mc;
         Minecraft.player.connection.getNetworkManager().sendPacketWithoutEvent(пакет);
      }

      this.часы.reset();
      this.пакеты.clear();
      this.стоп = false;
      this.стопер.reset();
   }
}
