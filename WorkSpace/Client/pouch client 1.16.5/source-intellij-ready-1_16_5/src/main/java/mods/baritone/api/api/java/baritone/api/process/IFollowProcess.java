/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.process;

import java.util.List;
import java.util.function.Predicate;
import lightning.product.N_4263_v;
import mods.baritone.api.api.java.baritone.api.process.IBaritoneProcess;

public interface IFollowProcess
extends IBaritoneProcess {
    public void follow(Predicate<N_4263_v> var1);

    public List<N_4263_v> following();

    public Predicate<N_4263_v> currentFilter();

    default public void cancel() {
        this.onLostControl();
    }
}

