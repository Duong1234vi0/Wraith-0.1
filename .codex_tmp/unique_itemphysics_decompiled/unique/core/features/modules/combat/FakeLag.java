package unique.core.features.modules.combat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1802;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2664;
import net.minecraft.class_2708;
import net.minecraft.class_2813;
import net.minecraft.class_2824;
import net.minecraft.class_2828;
import net.minecraft.class_2879;
import net.minecraft.class_2885;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.Timer;
import unique.core.utility.render.Render3DEngine;

public class FakeLag extends Module {
   private final Setting<FakeLag.Mode> mode = new Setting<>("Mode", FakeLag.Mode.NEW);
   private final Setting<Integer> pulseLength = new Setting<>("PulseLength", 2, 1, 7, v -> this.mode.getValue() == FakeLag.Mode.OLD);
   private final Setting<Integer> pulseDelay = new Setting<>("PulseDelay", 2, 1, 5, v -> this.mode.getValue() == FakeLag.Mode.OLD);
   private final Setting<Boolean> render = new Setting<>("Render", true);
   private final Setting<FakeLag.RenderMode> renderMode = new Setting<>("RenderMode", FakeLag.RenderMode.Box, v -> this.render.getValue());
   private final Setting<ColorSetting> color = new Setting<>("Color", new ColorSetting(new Color(16766720)), v -> this.render.getValue());
   private final Setting<Boolean> themeSync = new Setting<>("ThemeSync", false, v -> this.render.getValue());
   private final Setting<Integer> lagDelayMin = new Setting<>("LagDelayMin", 100, 0, 1500, v -> this.mode.getValue() == FakeLag.Mode.NEW);
   private final Setting<Integer> lagDelayMax = new Setting<>("LagDelayMax", 300, 0, 1500, v -> this.mode.getValue() == FakeLag.Mode.NEW);
   private final Setting<Boolean> cancelOnElytra = new Setting<>("CancelOnElytra", false, v -> this.mode.getValue() == FakeLag.Mode.NEW);
   private final Timer pulseTimer = new Timer();
   private final Queue<class_2596<?>> storedPackets = new ConcurrentLinkedQueue<>();
   private final AtomicBoolean sending = new AtomicBoolean(false);
   private boolean isLagging;
   private class_243 fakePos = class_243.field_1353;
   private class_243 animFrom = class_243.field_1353;
   private class_243 animTo = class_243.field_1353;
   private long animStartMs;
   private float animDurationMs = 300.0F;
   private int currentDelay;
   private long displayedLagMs;

   public FakeLag() {
      super("FakeLag", Module.Category.COMBAT);
   }

   private boolean isAnimating() {
      return (float)(System.currentTimeMillis() - this.animStartMs) < this.animDurationMs;
   }

   @Override
   public void onEnable() {
      this.storedPackets.clear();
      this.pulseTimer.reset();
      if (mc.field_1724 != null) {
         this.fakePos = mc.field_1724.method_19538();
      }

      this.isLagging = true;
      if (this.mode.getValue() == FakeLag.Mode.NEW) {
         this.generateNewDelay();
      }
   }

   @Override
   public void onDisable() {
      this.flushPackets();
      this.isLagging = false;
      this.displayedLagMs = 0L;
   }

   @EventHandler
   public void onPacketSend(PacketEvent.Send event) {
      if (mc.field_1724 != null && mc.field_1687 != null && !this.sending.get()) {
         if (this.mode.getValue() == FakeLag.Mode.NEW) {
            if (event.getPacket() instanceof class_2824
               || event.getPacket() instanceof class_2879
               || event.getPacket() instanceof class_2885
               || event.getPacket() instanceof class_2813) {
               this.resetLag();
               return;
            }

            if (this.cancelOnElytra.getValue() && mc.field_1724.method_31548().method_7372(2).method_7909() == class_1802.field_8833) {
               this.resetLag();
               return;
            }

            if (this.isLagging) {
               event.cancel();
               this.storedPackets.add(event.getPacket());
            }
         }

         if (this.mode.getValue() == FakeLag.Mode.OLD && this.isLagging && event.getPacket() instanceof class_2828) {
            event.cancel();
            this.storedPackets.add(event.getPacket());
         }
      }
   }

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive event) {
      if (mc.field_1724 != null && this.mode.getValue() == FakeLag.Mode.NEW) {
         if (event.getPacket() instanceof class_2664 || event.getPacket() instanceof class_2708) {
            this.resetLag();
         }
      }
   }

   @Override
   public void onUpdate() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (this.mode.getValue() == FakeLag.Mode.NEW) {
            if (this.isLagging && this.pulseTimer.passedMs(this.currentDelay)) {
               this.resetLag();
            }

            if (!this.isLagging && !this.isAnimating()) {
               this.isLagging = true;
               this.pulseTimer.reset();
               this.generateNewDelay();
            }
         }
      } else {
         this.disable();
      }
   }

   private void generateNewDelay() {
      int min = this.lagDelayMin.getValue();
      int max = this.lagDelayMax.getValue();
      this.currentDelay = min + (int)(Math.random() * (max - min));
      this.displayedLagMs = this.currentDelay;
      this.pulseTimer.reset();
      this.fakePos = mc.field_1724.method_19538();
   }

   private void resetLag() {
      this.flushPackets();
      class_243 realPos = mc.field_1724.method_19538();
      this.animFrom = this.fakePos;
      this.animTo = realPos;
      this.animDurationMs = Math.max(80.0F, this.currentDelay * 0.6F);
      this.animStartMs = System.currentTimeMillis();
      this.fakePos = realPos;
      this.isLagging = false;
   }

   private void flushPackets() {
      if (!this.sending.get()) {
         this.sending.set(true);

         while (!this.storedPackets.isEmpty()) {
            mc.method_1562().method_52787(this.storedPackets.poll());
         }

         this.sending.set(false);
      }
   }

   @Override
   public void onRender3D(class_4587 stack) {
      if (this.render.getValue() && mc.field_1724 != null) {
         class_243 renderPos;
         if (this.isLagging) {
            renderPos = this.fakePos;
         } else {
            if (!this.isAnimating()) {
               return;
            }

            float t = Math.min(1.0F, (float)(System.currentTimeMillis() - this.animStartMs) / this.animDurationMs);
            float tt = t * t * (3.0F - 2.0F * t);
            renderPos = new class_243(
               class_3532.method_16436(tt, this.animFrom.field_1352, this.animTo.field_1352),
               class_3532.method_16436(tt, this.animFrom.field_1351, this.animTo.field_1351),
               class_3532.method_16436(tt, this.animFrom.field_1350, this.animTo.field_1350)
            );
         }

         Color renderColor = this.themeSync.getValue() ? HudEditor.getColor(0) : this.color.getValue().getColorObject();
         if (this.renderMode.getValue() == FakeLag.RenderMode.Circle || this.renderMode.getValue() == FakeLag.RenderMode.All) {
            this.drawCircle(renderPos, renderColor);
         }

         if (this.renderMode.getValue() == FakeLag.RenderMode.Box || this.renderMode.getValue() == FakeLag.RenderMode.All) {
            this.drawBox(stack, renderPos, renderColor);
         }
      }
   }

   private void drawCircle(class_243 pos, Color color) {
      ArrayList<class_243> points = new ArrayList<>();

      for (int i = 0; i <= 360; i += 6) {
         double rad = Math.toRadians(i);
         points.add(new class_243(pos.field_1352 + Math.sin(rad) * 0.5, pos.field_1351 + 0.01, pos.field_1350 + Math.cos(rad) * 0.5));
      }

      for (int i = 0; i < points.size() - 1; i++) {
         Render3DEngine.drawLine(points.get(i), points.get(i + 1), color);
      }
   }

   private void drawBox(class_4587 stack, class_243 pos, Color color) {
      class_238 bb = mc.field_1724.method_5829();
      double hw = (bb.field_1320 - bb.field_1323) / 2.0;
      double hd = (bb.field_1324 - bb.field_1321) / 2.0;
      double h = bb.field_1325 - bb.field_1322;
      class_238 box = new class_238(pos.field_1352 - hw, pos.field_1351, pos.field_1350 - hd, pos.field_1352 + hw, pos.field_1351 + h, pos.field_1350 + hd);
      Color fill = new Color(color.getRed(), color.getGreen(), color.getBlue(), 90);
      Render3DEngine.drawFilledBox(stack, box, fill);
      Render3DEngine.drawBoxOutline(box, color, 2.0F);
   }

   public long getCurrentLagMs() {
      return this.displayedLagMs;
   }

   public boolean isLaggingNow() {
      return this.isLagging;
   }

   public static enum Mode {
      OLD,
      NEW;
   }

   public static enum RenderMode {
      Circle,
      Box,
      All;
   }
}
