/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 */
package mods.voicechat.eventforge;

import com.mojang.brigadier.CommandDispatcher;
import lightning.product.Q_2241_p;
import lightning.product.x_607_J;
import lightning.product.y_2498_m;

public class RegisterCommandsEvent
implements x_607_J {
    private final CommandDispatcher<y_2498_m> dispatcher;
    private final Q_2241_p.n_1700_B environment;

    public RegisterCommandsEvent(CommandDispatcher<y_2498_m> dispatcher, Q_2241_p.n_1700_B environment) {
        this.dispatcher = dispatcher;
        this.environment = environment;
    }

    public CommandDispatcher<y_2498_m> getDispatcher() {
        return this.dispatcher;
    }

    public Q_2241_p.n_1700_B getEnvironment() {
        return this.environment;
    }
}

