/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  org.luaj.vm2.LuaFunction
 *  org.luaj.vm2.lib.jse.CoerceJavaToLua
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.LuaTestHMI;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.Particle;
import java.util.function.Consumer;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class LuaConsumer
implements Consumer<Particle> {
    private final LuaFunction function;
    private boolean canRun = true;

    public LuaConsumer(LuaFunction function) {
        this.function = function;
    }

    @Override
    public void accept(Particle particle) {
        if (!this.canRun) {
            return;
        }
        try {
            this.function.call(CoerceJavaToLua.coerce((Object)particle));
        }
        catch (Exception e) {
            this.canRun = false;
            LuaTestHMI.LOGGER.error("HandMyItems particle callback failed and was disabled", e);
        }
    }
}
