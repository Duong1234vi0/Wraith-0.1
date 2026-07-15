/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.ModelPartAnimator;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.Particle;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.custom_api.DebugTextRenderer;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class GlobalsStorage {
    public static HashMap<String, Boolean> renderAsBlock = new HashMap();
    public static HashMap<String, Boolean> translateItem = new HashMap();
    public static HashMap<String, Boolean> applyBlockRotation = new HashMap();
    public static HashMap<String, Integer> itemSwingSpeed = new HashMap();
    public static HashMap<String, Object> useDuration = new HashMap();
    public static HashMap<String, Boolean> usingItem = new HashMap();
    public static final HashMap<String, Object> registry = new HashMap();
    public static final ArrayList<Particle> particles = new ArrayList<>();
    public static final ModelPartAnimator modelPartAnimator = new ModelPartAnimator();
    public static final DebugTextRenderer debugTextRenderer = new DebugTextRenderer();
    public static ItemStack mainHandItem = Items.AIR.getDefaultInstance();
    public static ItemStack offHandItem = Items.AIR.getDefaultInstance();
    public static ItemStack renderedStack = Items.AIR.getDefaultInstance();
}
