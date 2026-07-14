/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import lightning.product.B_3091_S;
import lightning.product.HopperScreen;
import lightning.product.ContainerScreen;
import lightning.product.CraftingScreen;
import lightning.product.FurnaceScreen;
import lightning.product.N_4263_v;
import lightning.product.Q_1939_l;
import lightning.product.BrewingStandScreen;
import lightning.product.T_1316_M;
import lightning.product.DispenserScreen;
import lightning.product.PackResources;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.ShulkerBoxScreen;
import lightning.product.g_2336_b;
import lightning.product.g_904_S;
import lightning.product.i_4221_J;
import lightning.product.k_2603_m;
import lightning.product.k_4690_i;
import lightning.product.HorseInventoryScreen;
import lightning.product.AnvilScreen;
import lightning.product.t_1279_j;
import lightning.product.v_1406_g;
import lightning.product.z_3427_G;
import net.optifine.Config;
import net.optifine.CustomGuiProperties;
import net.optifine.override.PlayerControllerOF;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;

public class CustomGuis {
    private static MinecraftClient mc = Config.getMinecraft();
    private static PlayerControllerOF playerControllerOF = null;
    private static CustomGuiProperties[][] guiProperties = null;
    public static boolean isChristmas = CustomGuis.isChristmas();

    public static g_2336_b getTextureLocation(g_2336_b loc) {
        if (guiProperties == null) {
            return loc;
        }
        k_2603_m screen = CustomGuis.mc.Y_1740_V;
        if (!(screen instanceof z_3427_G)) {
            return loc;
        }
        if (loc.R_4764_Y().equals("minecraft") && loc.J_1907_R().startsWith("textures/gui/")) {
            N_4263_v entity;
            if (playerControllerOF == null) {
                return loc;
            }
            k_4690_i iworldreader = CustomGuis.mc.Y_601_j;
            if (iworldreader == null) {
                return loc;
            }
            if (screen instanceof B_3091_S) {
                return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.CREATIVE, CustomGuis.mc.Y_259_p.b_2312_j(), iworldreader, loc, screen);
            }
            if (screen instanceof Q_1939_l) {
                return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.INVENTORY, CustomGuis.mc.Y_259_p.b_2312_j(), iworldreader, loc, screen);
            }
            c_1514_x blockpos = playerControllerOF.getLastClickBlockPos();
            if (blockpos != null) {
                if (screen instanceof AnvilScreen) {
                    return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.ANVIL, blockpos, iworldreader, loc, screen);
                }
                if (screen instanceof v_1406_g) {
                    return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.BEACON, blockpos, iworldreader, loc, screen);
                }
                if (screen instanceof BrewingStandScreen) {
                    return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.BREWING_STAND, blockpos, iworldreader, loc, screen);
                }
                if (screen instanceof ContainerScreen) {
                    return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.CHEST, blockpos, iworldreader, loc, screen);
                }
                if (screen instanceof CraftingScreen) {
                    return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.CRAFTING, blockpos, iworldreader, loc, screen);
                }
                if (screen instanceof DispenserScreen) {
                    return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.DISPENSER, blockpos, iworldreader, loc, screen);
                }
                if (screen instanceof t_1279_j) {
                    return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.ENCHANTMENT, blockpos, iworldreader, loc, screen);
                }
                if (screen instanceof FurnaceScreen) {
                    return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.FURNACE, blockpos, iworldreader, loc, screen);
                }
                if (screen instanceof HopperScreen) {
                    return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.HOPPER, blockpos, iworldreader, loc, screen);
                }
                if (screen instanceof ShulkerBoxScreen) {
                    return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.SHULKER_BOX, blockpos, iworldreader, loc, screen);
                }
            }
            if ((entity = playerControllerOF.getLastClickEntity()) != null) {
                if (screen instanceof HorseInventoryScreen) {
                    return CustomGuis.getTextureEntity(CustomGuiProperties.EnumContainer.HORSE, entity, iworldreader, loc);
                }
                if (screen instanceof g_904_S) {
                    return CustomGuis.getTextureEntity(CustomGuiProperties.EnumContainer.VILLAGER, entity, iworldreader, loc);
                }
            }
            return loc;
        }
        return loc;
    }

    private static g_2336_b getTexturePos(CustomGuiProperties.EnumContainer container, c_1514_x pos, T_1316_M blockAccess, g_2336_b loc, k_2603_m screen) {
        CustomGuiProperties[] acustomguiproperties = guiProperties[container.ordinal()];
        if (acustomguiproperties == null) {
            return loc;
        }
        for (int i = 0; i < acustomguiproperties.length; ++i) {
            CustomGuiProperties customguiproperties = acustomguiproperties[i];
            if (!customguiproperties.matchesPos(container, pos, blockAccess, screen)) continue;
            return customguiproperties.getTextureLocation(loc);
        }
        return loc;
    }

    private static g_2336_b getTextureEntity(CustomGuiProperties.EnumContainer container, N_4263_v entity, T_1316_M blockAccess, g_2336_b loc) {
        CustomGuiProperties[] acustomguiproperties = guiProperties[container.ordinal()];
        if (acustomguiproperties == null) {
            return loc;
        }
        for (int i = 0; i < acustomguiproperties.length; ++i) {
            CustomGuiProperties customguiproperties = acustomguiproperties[i];
            if (!customguiproperties.matchesEntity(container, entity, blockAccess)) continue;
            return customguiproperties.getTextureLocation(loc);
        }
        return loc;
    }

    public static void update() {
        guiProperties = null;
        if (Config.isCustomGuis()) {
            ArrayList<List<CustomGuiProperties>> list = new ArrayList<List<CustomGuiProperties>>();
            PackResources[] airesourcepack = Config.getResourcePacks();
            for (int i = airesourcepack.length - 1; i >= 0; --i) {
                PackResources iresourcepack = airesourcepack[i];
                CustomGuis.update(iresourcepack, list);
            }
            guiProperties = CustomGuis.propertyListToArray(list);
        }
    }

    private static CustomGuiProperties[][] propertyListToArray(List<List<CustomGuiProperties>> listProps) {
        if (listProps.isEmpty()) {
            return null;
        }
        CustomGuiProperties[][] acustomguiproperties = new CustomGuiProperties[CustomGuiProperties.EnumContainer.values().length][];
        for (int i = 0; i < acustomguiproperties.length; ++i) {
            List<CustomGuiProperties> list;
            if (listProps.size() <= i || (list = listProps.get(i)) == null) continue;
            CustomGuiProperties[] acustomguiproperties1 = list.toArray(new CustomGuiProperties[list.size()]);
            acustomguiproperties[i] = acustomguiproperties1;
        }
        return acustomguiproperties;
    }

    private static void update(PackResources rp, List<List<CustomGuiProperties>> listProps) {
        String[] astring = ResUtils.collectFiles(rp, "optifine/gui/container/", ".properties", (String[])null);
        Arrays.sort(astring);
        for (int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            Config.dbg("CustomGuis: " + s);
            try {
                g_2336_b resourcelocation = new g_2336_b(s);
                InputStream inputstream = rp.getResourceStream(i_4221_J.n_1700_B, resourcelocation);
                if (inputstream == null) {
                    Config.warn("CustomGuis file not found: " + s);
                    continue;
                }
                PropertiesOrdered properties = new PropertiesOrdered();
                properties.load(inputstream);
                inputstream.close();
                CustomGuiProperties customguiproperties = new CustomGuiProperties(properties, s);
                if (!customguiproperties.isValid(s)) continue;
                CustomGuis.addToList(customguiproperties, listProps);
                continue;
            }
            catch (FileNotFoundException filenotfoundexception) {
                Config.warn("CustomGuis file not found: " + s);
                continue;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private static void addToList(CustomGuiProperties cgp, List<List<CustomGuiProperties>> listProps) {
        if (cgp.getContainer() == null) {
            CustomGuis.warn("Invalid container: " + String.valueOf((Object)cgp.getContainer()));
        } else {
            int i = cgp.getContainer().ordinal();
            while (listProps.size() <= i) {
                listProps.add(null);
            }
            List<CustomGuiProperties> list = listProps.get(i);
            if (list == null) {
                list = new ArrayList<CustomGuiProperties>();
                listProps.set(i, list);
            }
            list.add(cgp);
        }
    }

    public static PlayerControllerOF getPlayerControllerOF() {
        return playerControllerOF;
    }

    public static void setPlayerControllerOF(PlayerControllerOF playerControllerOF) {
        CustomGuis.playerControllerOF = playerControllerOF;
    }

    private static boolean isChristmas() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26;
    }

    private static void warn(String str) {
        Config.warn("[CustomGuis] " + str);
    }
}



