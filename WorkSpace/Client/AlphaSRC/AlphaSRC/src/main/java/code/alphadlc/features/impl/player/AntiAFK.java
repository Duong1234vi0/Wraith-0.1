/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;

public class AntiAFK
extends Module {
    private final SliderSettings moveDelay = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043f\u0435\u0440\u0435\u0434 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0435\u043c", "\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0432 \u0441\u0435\u043a\u0443\u043d\u0434\u0430\u0445").setValue(59.0f).range(1.0f, 60.0f).step(0.5f);
    private long lastSpecialMoveTime = 0L;
    private boolean performingSpecialMove = false;
    private long specialMoveStartTime = 0L;
    private int specialMovePhase = 0;

    public AntiAFK() {
        super("AntiAFK", ModuleCategory.PLAYER);
        this.setup(this.moveDelay);
    }

    @Override
    public void activate() {
        this.lastSpecialMoveTime = System.currentTimeMillis();
        this.performingSpecialMove = false;
        this.specialMovePhase = 0;
        super.activate();
    }

    @Override
    public void deactivate() {
        this.resetAllKeys();
        super.deactivate();
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (AntiAFK.mc.field_1724 == null || AntiAFK.mc.field_1687 == null) {
            return;
        }
        this.handleMovementMode(System.currentTimeMillis());
    }

    private void handleMovementMode(long currentTime) {
        if (this.performingSpecialMove) {
            long specialMoveElapsed = currentTime - this.specialMoveStartTime;
            switch (this.specialMovePhase) {
                case 0: {
                    this.resetAllKeys();
                    AntiAFK.mc.field_1690.field_1894.method_23481(true);
                    if (specialMoveElapsed < 280L) break;
                    this.specialMovePhase = 1;
                    this.specialMoveStartTime = currentTime;
                    this.resetAllKeys();
                    break;
                }
                case 1: {
                    this.resetAllKeys();
                    AntiAFK.mc.field_1690.field_1881.method_23481(true);
                    if (specialMoveElapsed < 350L) break;
                    this.specialMovePhase = 2;
                    this.performingSpecialMove = false;
                    this.resetAllKeys();
                }
            }
            return;
        }
        long delayMs = (long)(this.moveDelay.getValue() * 1000.0f);
        if (currentTime - this.lastSpecialMoveTime >= delayMs) {
            this.performingSpecialMove = true;
            this.specialMoveStartTime = currentTime;
            this.specialMovePhase = 0;
            this.lastSpecialMoveTime = currentTime;
            this.resetAllKeys();
        }
    }

    private void resetAllKeys() {
        AntiAFK.mc.field_1690.field_1894.method_23481(false);
        AntiAFK.mc.field_1690.field_1881.method_23481(false);
        AntiAFK.mc.field_1690.field_1913.method_23481(false);
        AntiAFK.mc.field_1690.field_1849.method_23481(false);
    }
}
