package unique.core.features.modules.movement;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2743;
import net.minecraft.class_2793;
import net.minecraft.class_2797;
import net.minecraft.class_2799;
import net.minecraft.class_2827;
import net.minecraft.class_2828;
import net.minecraft.class_2859;
import net.minecraft.class_4587;
import net.minecraft.class_6374;
import net.minecraft.class_746;
import net.minecraft.class_2828.class_2831;
import unique.core.events.impl.EventTick;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.setting.Setting;
import unique.core.setting.impl.Bind;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.player.PlayerEntityCopy;
import unique.core.utility.render.Render3DEngine;

public class Blink extends Module {
   private final Setting<Boolean> pulse = new Setting<>("Pulse", false);
   private final Setting<Boolean> autoDisable = new Setting<>("AutoDisable", false);
   private final Setting<Boolean> disableOnVelocity = new Setting<>("DisableOnVelocity", false);
   private final Setting<Integer> disablePackets = new Setting<>("DisablePackets", 17, 1, 1000, v -> this.autoDisable.getValue());
   private final Setting<Integer> pulsePackets = new Setting<>("PulsePackets", 20, 1, 1000, v -> this.pulse.getValue());
   private final Setting<Boolean> render = new Setting<>("Render", true);
   private final Setting<Blink.RenderMode> renderMode = new Setting<>("Render Mode", Blink.RenderMode.Circle, value -> this.render.getValue());
   private final Setting<ColorSetting> circleColor = new Setting<>(
      "Color",
      new ColorSetting(-2464668),
      value -> this.render.getValue() && this.renderMode.getValue() == Blink.RenderMode.Circle || this.renderMode.getValue() == Blink.RenderMode.Both
   );
   private final Setting<Bind> cancel = new Setting<>("Cancel", new Bind(340, false, false));
   private PlayerEntityCopy blinkPlayer;
   public static class_243 lastPos = class_243.field_1353;
   private class_243 prevVelocity = class_243.field_1353;
   private float prevYaw = 0.0F;
   private boolean prevSprinting = false;
   private final Queue<class_2596<?>> storedPackets = new LinkedList<>();
   private final Queue<class_2596<?>> storedTransactions = new LinkedList<>();
   private final AtomicBoolean sending = new AtomicBoolean(false);

   public Blink() {
      super("Blink", Module.Category.MOVEMENT);
   }

   @Override
   public void onEnable() {
      if (mc.field_1724 != null && mc.field_1687 != null && !mc.method_1496() && mc.method_1562() != null) {
         this.storedTransactions.clear();
         lastPos = mc.field_1724.method_19538();
         this.prevVelocity = mc.field_1724.method_18798();
         this.prevYaw = mc.field_1724.method_36454();
         this.prevSprinting = mc.field_1724.method_5624();
         mc.field_1687
            .method_8649(
               new class_746(
                  mc,
                  mc.field_1687,
                  mc.method_1562(),
                  mc.field_1724.method_3143(),
                  mc.field_1724.method_3130(),
                  mc.field_1724.field_3919,
                  mc.field_1724.method_5715()
               )
            );
         this.sending.set(false);
         this.storedPackets.clear();
      } else {
         this.disable();
      }
   }

   @Override
   public void onDisable() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         while (!this.storedPackets.isEmpty()) {
            this.sendPacket(this.storedPackets.poll());
         }

         if (this.blinkPlayer != null) {
            this.blinkPlayer.deSpawn();
         }

         this.blinkPlayer = null;
      }
   }

   @Override
   public String getDisplayInfo() {
      return Integer.toString(this.storedPackets.size());
   }

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive event) {
      if (event.getPacket() instanceof class_2743 vel && vel.method_11818() == mc.field_1724.method_5628() && this.disableOnVelocity.getValue()) {
         this.disable(ClientSettings.isRu() ? "Выключенно из-за велосити!" : "Disabled due to velocity!");
      }
   }

   @EventHandler
   public void onPacketSend(PacketEvent.Send event) {
      if (!fullNullCheck()) {
         class_2596<?> packet = event.getPacket();
         if (!this.sending.get()) {
            if (packet instanceof class_6374) {
               this.storedTransactions.add(packet);
            }

            if (this.pulse.getValue()) {
               if (packet instanceof class_2828) {
                  event.cancel();
                  this.storedPackets.add(packet);
               }
            } else if (!(packet instanceof class_2797)
               && !(packet instanceof class_2793)
               && !(packet instanceof class_2827)
               && !(packet instanceof class_2859)
               && !(packet instanceof class_2799)) {
               event.cancel();
               this.storedPackets.add(packet);
            }
         }
      }
   }

   @EventHandler
   public void onUpdate(EventTick event) {
      if (!fullNullCheck()) {
         if (!this.isKeyPressed(this.cancel)) {
            if (this.pulse.getValue() && this.storedPackets.size() >= this.pulsePackets.getValue()) {
               this.sendPackets();
            }

            if (this.autoDisable.getValue() && this.storedPackets.size() >= this.disablePackets.getValue()) {
               this.disable();
            }
         } else {
            this.storedPackets.clear();
            mc.field_1724.method_23327(lastPos.method_10216(), lastPos.method_10214(), lastPos.method_10215());
            mc.field_1724.method_18799(this.prevVelocity);
            mc.field_1724.method_36456(this.prevYaw);
            mc.field_1724.method_5728(this.prevSprinting);
            mc.field_1724.method_5660(false);
            mc.field_1690.field_1832.method_23481(false);
            this.sending.set(true);

            while (!this.storedTransactions.isEmpty()) {
               this.sendPacket(this.storedTransactions.poll());
            }

            this.sending.set(false);
            this.disable(ClientSettings.isRu() ? "Отменяю.." : "Canceling..");
         }
      }
   }

   private void sendPackets() {
      if (mc.field_1724 != null) {
         this.sending.set(true);

         while (!this.storedPackets.isEmpty()) {
            class_2596<?> packet = this.storedPackets.poll();
            this.sendPacket(packet);
            if (packet instanceof class_2828 && !(packet instanceof class_2831)) {
               lastPos = new class_243(
                  ((class_2828)packet).method_12269(mc.field_1724.method_23317()),
                  ((class_2828)packet).method_12268(mc.field_1724.method_23318()),
                  ((class_2828)packet).method_12274(mc.field_1724.method_23321())
               );
               if (this.renderMode.getValue() == Blink.RenderMode.Model || this.renderMode.getValue() == Blink.RenderMode.Both) {
                  this.blinkPlayer.deSpawn();
                  this.blinkPlayer = new PlayerEntityCopy();
                  this.blinkPlayer.spawn();
               }
            }
         }

         this.sending.set(false);
         this.storedPackets.clear();
      }
   }

   @Override
   public void onRender3D(class_4587 stack) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (this.render.getValue() && lastPos != null) {
            if (this.renderMode.getValue() == Blink.RenderMode.Circle || this.renderMode.getValue() == Blink.RenderMode.Both) {
               float[] hsb = Color.RGBtoHSB(
                  this.circleColor.getValue().getRed(), this.circleColor.getValue().getGreen(), this.circleColor.getValue().getBlue(), null
               );
               float hue = (float)(System.currentTimeMillis() % 7200L) / 7200.0F;
               int rgb = Color.getHSBColor(hue, hsb[1], hsb[2]).getRGB();
               ArrayList<class_243> vecs = new ArrayList<>();
               double x = lastPos.field_1352;
               double y = lastPos.field_1351;
               double z = lastPos.field_1350;

               for (int i = 0; i <= 360; i++) {
                  class_243 vec = new class_243(x + Math.sin(i * Math.PI / 180.0) * 0.5, y + 0.01, z + Math.cos(i * Math.PI / 180.0) * 0.5);
                  vecs.add(vec);
               }

               for (int j = 0; j < vecs.size() - 1; j++) {
                  Render3DEngine.drawLine(vecs.get(j), vecs.get(j + 1), new Color(rgb));
                  hue += 0.0027777778F;
                  rgb = Color.getHSBColor(hue, hsb[1], hsb[2]).getRGB();
               }
            }

            if ((this.renderMode.getValue() == Blink.RenderMode.Model || this.renderMode.getValue() == Blink.RenderMode.Both) && this.blinkPlayer == null) {
               this.blinkPlayer = new PlayerEntityCopy();
               this.blinkPlayer.spawn();
            }
         }
      }
   }

   private static enum RenderMode {
      Circle,
      Model,
      Both;
   }
}
