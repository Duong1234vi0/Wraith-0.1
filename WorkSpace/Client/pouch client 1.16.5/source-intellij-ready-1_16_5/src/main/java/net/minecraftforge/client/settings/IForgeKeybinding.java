/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 */
package net.minecraftforge.client.settings;

import javax.annotation.Nonnull;
import lightning.product.D_590_W;
import lightning.product.Q_4113_P;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;

public interface IForgeKeybinding {
    default public D_590_W getKeyBinding() {
        return (D_590_W)this;
    }

    @Nonnull
    public Q_4113_P.n_1700_B getKey();

    default public boolean isActiveAndMatches(Q_4113_P.n_1700_B keyCode) {
        return keyCode != Q_4113_P.n_1700_B && keyCode.equals(this.getKey()) && this.getKeyConflictContext().isActive() && this.getKeyModifier().isActive(this.getKeyConflictContext());
    }

    default public void setToDefault() {
        this.setKeyModifierAndCode(this.getKeyModifierDefault(), this.getKeyBinding().w_1484_f());
    }

    public void setKeyConflictContext(IKeyConflictContext var1);

    public IKeyConflictContext getKeyConflictContext();

    public KeyModifier getKeyModifierDefault();

    public KeyModifier getKeyModifier();

    public void setKeyModifierAndCode(KeyModifier var1, Q_4113_P.n_1700_B var2);

    default public boolean isConflictContextAndModifierActive() {
        return this.getKeyConflictContext().isActive() && this.getKeyModifier().isActive(this.getKeyConflictContext());
    }

    default public boolean hasKeyCodeModifierConflict(D_590_W other) {
        return !(!this.getKeyConflictContext().conflicts(other.getKeyConflictContext()) && !other.getKeyConflictContext().conflicts(this.getKeyConflictContext()) || !this.getKeyModifier().matches(other.getKey()) && !other.getKeyModifier().matches(this.getKey()));
    }
}

