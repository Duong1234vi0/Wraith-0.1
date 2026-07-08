/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.Instance;

public class NoInteract
extends Module {
    public static NoInteract getInstance() {
        return Instance.get(NoInteract.class);
    }

    public NoInteract() {
        super("NoInteract", "NoInteract", ModuleCategory.COMBAT);
    }
}
