package unique.core.features.modules.base;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_3965;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import unique.core.events.impl.EventPostSync;
import unique.core.events.impl.EventSync;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.player.InteractionUtility;
import unique.core.utility.world.HoleUtility;

public abstract class TrapModule extends PlaceModule {
   protected final Setting<TrapModule.PlaceTiming> placeTiming = new Setting<>("Place Timing", TrapModule.PlaceTiming.Default);
   protected final Setting<Integer> blocksPerTick = new Setting<>("Block/Tick", 8, 1, 12, v -> this.placeTiming.getValue() == TrapModule.PlaceTiming.Default);
   protected final Setting<Integer> placeDelay = new Setting<>("Delay/Place", 3, 0, 10);
   protected final Setting<TrapModule.TrapMode> trapMode = new Setting<>("Trap Mode", TrapModule.TrapMode.Full);
   private int delay;
   protected class_1657 target;
   private final ArrayList<class_2338> sequentialBlocks = new ArrayList<>();

   public TrapModule(@NotNull String name, @NotNull Module.Category category) {
      super(name, category);
   }

   protected abstract boolean needNewTarget();

   @Nullable
   protected abstract class_1657 getTarget();

   @Override
   public void onDisable() {
      this.target = null;
      this.delay = 0;
      this.sequentialBlocks.clear();
   }

   @EventHandler
   private void onSync(EventSync event) {
      if (this.needNewTarget()) {
         this.target = this.getTarget();
      } else {
         if (this.placeTiming.getValue() == TrapModule.PlaceTiming.Vanilla && !this.rotate.is(InteractionUtility.Rotate.None)) {
            class_2338 targetBlock = this.getBlockToPlace();
            if (targetBlock != null && mc.field_1724 != null) {
               class_3965 result = InteractionUtility.getPlaceResult(targetBlock, this.interact.getValue(), false);
               if (result != null) {
                  float[] angle = InteractionUtility.calculateAngle(result.method_17784());
                  mc.field_1724.method_36456(angle[0]);
                  mc.field_1724.method_36457(angle[1]);
               }
            }
         }
      }
   }

   @EventHandler
   private void onPostSync(EventPostSync event) {
      if (this.delay > 0) {
         this.delay--;
      } else {
         InteractionUtility.Rotate rotateMod = this.placeTiming.is(TrapModule.PlaceTiming.Vanilla) && !this.rotate.is(InteractionUtility.Rotate.None)
            ? InteractionUtility.Rotate.None
            : this.rotate.getValue();
         if (this.placeTiming.getValue() == TrapModule.PlaceTiming.Default) {
            int placed = 0;

            while (placed < this.blocksPerTick.getValue()) {
               class_2338 targetBlock = this.getBlockToPlace();
               if (targetBlock == null || !this.placeBlock(targetBlock, rotateMod)) {
                  break;
               }

               placed++;
               this.delay = this.placeDelay.getValue();
               this.inactivityTimer.reset();
            }
         } else if (this.placeTiming.getValue() == TrapModule.PlaceTiming.Vanilla) {
            class_2338 targetBlock = this.getBlockToPlace();
            if (targetBlock != null && this.placeBlock(targetBlock, rotateMod)) {
               this.sequentialBlocks.add(targetBlock);
               this.delay = this.placeDelay.getValue();
               this.inactivityTimer.reset();
            }
         }
      }
   }

   @Nullable
   protected class_2338 getBlockToPlace() {
      return this.target != null && mc.field_1724 != null
         ? this.getBlocks(this.target)
            .stream()
            .filter(pos -> pos.method_19770(mc.field_1724.method_19538()) < this.range.getPow2Value())
            .filter(pos -> InteractionUtility.canPlaceBlock(pos, this.interact.getValue(), true))
            .max(Comparator.comparing(pos -> mc.field_1724.method_5707(pos.method_46558())))
            .orElse(null)
         : null;
   }

   protected List<class_2338> getBlocks(@NotNull class_1657 player) {
      class_243 playerPos = player.method_19538();
      List<class_2338> offsets = new ArrayList<>();
      List<class_2338> holePoses = HoleUtility.getHolePoses(playerPos);
      List<class_2338> surroundPoses = HoleUtility.getSurroundPoses(playerPos);
      if (mc.field_1724 != null && mc.field_1687 != null) {
         switch ((TrapModule.TrapMode)this.trapMode.getValue()) {
            case Full:
               offsets.addAll(holePoses.stream().map(class_2338::method_10074).toList());
               if (this.interact.getValue() != InteractionUtility.Interact.AirPlace) {
                  offsets.addAll(this.addHelpOffsets(surroundPoses));
               }

               offsets.addAll(surroundPoses);
               offsets.addAll(surroundPoses.stream().map(class_2338::method_10084).toList());
               if (this.interact.getValue() != InteractionUtility.Interact.AirPlace) {
                  surroundPoses.stream()
                     .map(pos -> pos.method_10086(2))
                     .filter(pos -> pos.method_19770(mc.field_1724.method_19538()) < this.range.getPow2Value())
                     .max(Comparator.comparing(pos -> mc.field_1724.method_5707(pos.method_46558())))
                     .ifPresent(pos -> {
                        offsets.add(pos);
                        offsets.add(pos.method_10074());
                     });
               }

               offsets.addAll(holePoses.stream().map(pos -> pos.method_10086(2)).toList());
               break;
            case Legs:
               offsets.addAll(holePoses.stream().map(class_2338::method_10074).toList());
               if (this.interact.getValue() != InteractionUtility.Interact.AirPlace) {
                  surroundPoses.stream()
                     .filter(pos -> pos.method_19770(mc.field_1724.method_19538()) < this.range.getPow2Value())
                     .max(Comparator.comparing(pos -> player.method_5707(pos.method_46558())))
                     .ifPresent(pos -> {
                        offsets.add(pos);
                        offsets.add(pos.method_10084());
                        offsets.add(pos.method_10086(2));
                     });
                  offsets.addAll(this.addHelpOffsets(surroundPoses));
               }

               offsets.addAll(surroundPoses);
               offsets.addAll(holePoses.stream().map(pos -> pos.method_10086(2)).toList());
               break;
            case Head:
               offsets.addAll(holePoses.stream().map(class_2338::method_10074).toList());
               if (this.interact.getValue() != InteractionUtility.Interact.AirPlace) {
                  surroundPoses.stream()
                     .<class_2338>map(class_2338::method_10074)
                     .filter(pos -> pos.method_19770(mc.field_1724.method_19538()) < this.range.getPow2Value())
                     .max(Comparator.comparing(pos -> player.method_5707(pos.method_46558())))
                     .ifPresent(pos -> {
                        offsets.add(pos);
                        offsets.add(pos.method_10084());
                        offsets.add(pos.method_10086(3));
                     });
               }

               offsets.addAll(surroundPoses.stream().map(class_2338::method_10084).toList());
               offsets.addAll(holePoses.stream().map(pos -> pos.method_10086(2)).toList());
         }

         return offsets;
      } else {
         return offsets;
      }
   }

   @NotNull
   private List<class_2338> addHelpOffsets(@NotNull List<class_2338> surroundPoses) {
      List<class_2338> helpOffsets = new ArrayList<>();
      if (mc.field_1687 != null && mc.field_1724 != null) {
         surroundPoses.stream()
            .<class_2338>map(class_2338::method_10074)
            .filter(pos -> pos.method_19770(mc.field_1724.method_19538()) < this.range.getPow2Value())
            .filter(pos -> {
               for (class_2350 dir : class_2350.values()) {
                  if (!mc.field_1687.method_8320(pos.method_10081(dir.method_10163().method_30931())).method_45474()) {
                     return false;
                  }
               }

               return true;
            })
            .forEach(helpOffsets::add);
         return helpOffsets;
      } else {
         return helpOffsets;
      }
   }

   protected static enum PlaceTiming {
      Default,
      Vanilla;
   }

   protected static enum TrapMode {
      Full,
      Legs,
      Head;
   }
}
