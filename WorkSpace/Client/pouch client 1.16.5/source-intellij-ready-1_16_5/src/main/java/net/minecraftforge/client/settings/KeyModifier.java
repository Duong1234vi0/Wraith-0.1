/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package net.minecraftforge.client.settings;

import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.Q_4113_P;
import lightning.product.MinecraftClient;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import net.minecraftforge.client.settings.IKeyConflictContext;

public enum KeyModifier {
    CONTROL{

        @Override
        public boolean matches(Q_4113_P.n_1700_B key) {
            int keyCode = key.J_1907_R();
            if (MinecraftClient.n_1700_B) {
                return keyCode == 342 || keyCode == 346;
            }
            return keyCode == 341 || keyCode == 345;
        }

        @Override
        public boolean isActive(@Nullable IKeyConflictContext conflictContext) {
            return k_2603_m.hasControlDown();
        }

        @Override
        public x_282_a getCombinedName(Q_4113_P.n_1700_B key, Supplier<x_282_a> defaultLogic) {
            String localizationFormatKey = MinecraftClient.n_1700_B ? "forge.controlsgui.control.mac" : "forge.controlsgui.control";
            return new F_2904_S(localizationFormatKey, defaultLogic.get());
        }
    }
    ,
    SHIFT{

        @Override
        public boolean matches(Q_4113_P.n_1700_B key) {
            return key.J_1907_R() == 340 || key.J_1907_R() == 344;
        }

        @Override
        public boolean isActive(@Nullable IKeyConflictContext conflictContext) {
            return k_2603_m.hasShiftDown();
        }

        @Override
        public x_282_a getCombinedName(Q_4113_P.n_1700_B key, Supplier<x_282_a> defaultLogic) {
            return new F_2904_S("forge.controlsgui.shift", defaultLogic.get());
        }
    }
    ,
    ALT{

        @Override
        public boolean matches(Q_4113_P.n_1700_B key) {
            return key.J_1907_R() == 342 || key.J_1907_R() == 346;
        }

        @Override
        public boolean isActive(@Nullable IKeyConflictContext conflictContext) {
            return k_2603_m.hasAltDown();
        }

        @Override
        public x_282_a getCombinedName(Q_4113_P.n_1700_B keyCode, Supplier<x_282_a> defaultLogic) {
            return new F_2904_S("forge.controlsgui.alt", defaultLogic.get());
        }
    }
    ,
    NONE{

        @Override
        public boolean matches(Q_4113_P.n_1700_B key) {
            return false;
        }

        @Override
        public boolean isActive(@Nullable IKeyConflictContext conflictContext) {
            if (conflictContext != null && !conflictContext.conflicts(KeyConflictContext.IN_GAME)) {
                for (KeyModifier keyModifier : MODIFIER_VALUES) {
                    if (!keyModifier.isActive(conflictContext)) continue;
                    return false;
                }
            }
            return true;
        }

        @Override
        public x_282_a getCombinedName(Q_4113_P.n_1700_B key, Supplier<x_282_a> defaultLogic) {
            return defaultLogic.get();
        }
    };

    public static final KeyModifier[] MODIFIER_VALUES;

    public static KeyModifier getActiveModifier() {
        for (KeyModifier keyModifier : MODIFIER_VALUES) {
            if (!keyModifier.isActive(null)) continue;
            return keyModifier;
        }
        return NONE;
    }

    public static boolean isKeyCodeModifier(Q_4113_P.n_1700_B key) {
        for (KeyModifier keyModifier : MODIFIER_VALUES) {
            if (!keyModifier.matches(key)) continue;
            return true;
        }
        return false;
    }

    public static KeyModifier valueFromString(String stringValue) {
        try {
            return KeyModifier.valueOf(stringValue);
        }
        catch (IllegalArgumentException | NullPointerException ignored) {
            return NONE;
        }
    }

    public abstract boolean matches(Q_4113_P.n_1700_B var1);

    public abstract boolean isActive(@Nullable IKeyConflictContext var1);

    public abstract x_282_a getCombinedName(Q_4113_P.n_1700_B var1, Supplier<x_282_a> var2);

    static {
        MODIFIER_VALUES = new KeyModifier[]{SHIFT, CONTROL, ALT};
    }

    public static enum KeyConflictContext implements IKeyConflictContext
    {
        UNIVERSAL{

            @Override
            public boolean isActive() {
                return true;
            }

            @Override
            public boolean conflicts(IKeyConflictContext other) {
                return true;
            }
        }
        ,
        GUI{

            @Override
            public boolean isActive() {
                return MinecraftClient.A_4115_X().Y_1740_V != null;
            }

            @Override
            public boolean conflicts(IKeyConflictContext other) {
                return this == other;
            }
        }
        ,
        IN_GAME{

            @Override
            public boolean isActive() {
                return !GUI.isActive();
            }

            @Override
            public boolean conflicts(IKeyConflictContext other) {
                return this == other;
            }
        };

    }
}


