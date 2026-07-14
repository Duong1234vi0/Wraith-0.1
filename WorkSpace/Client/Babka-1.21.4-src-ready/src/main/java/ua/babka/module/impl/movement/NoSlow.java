package ua.babka.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import ua.babka.Babka;
import ua.babka.event.EventNoSlow;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.combat.AttackAura;
import ua.babka.module.setting.ModeSetting;
import ua.babka.util.player.SimulatedPlayer;

@ModuleInformation(moduleName="No Slow", moduleDesc="Убирает замедление при использовании", moduleCategory=ModuleCategory.MOVEMENT)
public class NoSlow
extends Module {
    private final ModeSetting mode = new ModeSetting("Мод", "Vanilla", "Vanilla", "AresMine", "ReallyWorld");
    private static int ticks = 0;
    private static int cycleCounter = 0;
    private int vanillaTicks = 0;

    @Subscribe
    private void onNoSlow(EventNoSlow e2) {
        switch (this.mode.getValue()) {
            case "Vanilla": {
                if (Babka.getInstance().getModuleStorage().get(AttackAura.class).getTarget() == null || !(SimulatedPlayer.simulateLocalPlayer((int)1).fallDistance > 0.0f)) {
                    this.mc.player.setSprinting(true);
                }
                e2.cancelEvent();
                break;
            }
            case "AresMine": {
                boolean shouldSprint = this.mc.player.getItemUseTime() > 4 && (Babka.getInstance().getModuleStorage().get(AttackAura.class).getTarget() == null || !(SimulatedPlayer.simulateLocalPlayer((int)1).fallDistance > 0.0f)) && Babka.getInstance().getServerManager().getSprintingChangeTicks() > 0.0f;
                this.mc.player.setSprinting(shouldSprint);
                if (this.mc.player.getItemUseTime() % 2 != 0) break;
                e2.cancelEvent();
                break;
            }
            case "ReallyWorld": {
                this.handleReallyWorld(e2);
            }
        }
    }

    private void handleReallyWorld(EventNoSlow e2) {
        int[] thresholds;
        int threshold;
        if (this.mc.player.isUsingItem() && ticks >= (threshold = (thresholds = new int[]{2, 3, 3})[cycleCounter % 3])) {
            e2.cancelEvent();
            ticks = 0;
            ++cycleCounter;
        }
    }

    @Subscribe
    public void onUpdate(EventTick e2) {
        if (!this.mode.getValue().equals("ReallyWorld")) {
            return;
        }
        if (this.mc.player != null && this.mc.player.isUsingItem()) {
            ++ticks;
        } else {
            ticks = 0;
            cycleCounter = 0;
        }
    }
}

