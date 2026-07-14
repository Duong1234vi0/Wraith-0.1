/*
 * Decompiled with CFR 0.152.
 */
package mods.cape;

import mods.cape.CapeMovement;
import mods.cape.CapeStyle;
import mods.cape.WindMode;

public class Config {
    public WindMode windMode = WindMode.NONE;
    public CapeStyle capeStyle = CapeStyle.SMOOTH;
    public CapeMovement capeMovement = CapeMovement.BASIC_SIMULATION;
    public int gravity = 25;
    public int heightMultiplier = 6;
    public int straveMultiplier = 2;
}

