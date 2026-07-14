/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.reflect;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lightning.product.AxeItem;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.F_3620_e;
import lightning.product.G_3165_y;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.Button;
import lightning.product.BlockAndTintGetter;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.k_2603_m;
import lightning.product.k_596_g;
import lightning.product.q_1613_l;
import lightning.product.Items;
import net.optifine.Log;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorClass;
import net.optifine.reflect.ReflectorField;
import net.optifine.util.StrUtils;

public class ReflectorForge {
    public static Object EVENT_RESULT_ALLOW = Reflector.getFieldValue(Reflector.Event_Result_ALLOW);
    public static Object EVENT_RESULT_DENY = Reflector.getFieldValue(Reflector.Event_Result_DENY);
    public static Object EVENT_RESULT_DEFAULT = Reflector.getFieldValue(Reflector.Event_Result_DEFAULT);
    public static final boolean FORGE_BLOCKSTATE_HAS_TILE_ENTITY = Reflector.IForgeBlockState_hasTileEntity.exists();
    public static final boolean FORGE_ENTITY_CAN_UPDATE = Reflector.IForgeEntity_canUpdate.exists();

    public static void putLaunchBlackboard(String key, Object value) {
        Map map = (Map)Reflector.getFieldValue(Reflector.Launch_blackboard);
        if (map != null) {
            map.put(key, value);
        }
    }

    public static InputStream getOptiFineResourceStream(String path) {
        if (!Reflector.OptiFineResourceLocator.exists()) {
            return null;
        }
        path = StrUtils.removePrefix(path, "/");
        return (InputStream)Reflector.call(Reflector.OptiFineResourceLocator_getOptiFineResourceStream, path);
    }

    public static ReflectorClass getReflectorClassOptiFineResourceLocator() {
        String s = "optifine.OptiFineResourceLocator";
        Object object = System.getProperties().get(s + ".class");
        if (object instanceof Class) {
            Class oclass = (Class)object;
            return new ReflectorClass(oclass);
        }
        return new ReflectorClass(s);
    }

    public static boolean blockHasTileEntity(K_4074_S state) {
        return FORGE_BLOCKSTATE_HAS_TILE_ENTITY ? Reflector.callBoolean(state, Reflector.IForgeBlockState_hasTileEntity, new Object[0]) : state.J_1907_R().G_564_y();
    }

    public static boolean isItemDamaged(Z_1993_T stack) {
        return !Reflector.IForgeItem_showDurabilityBar.exists() ? stack.u_1723_Y() : Reflector.callBoolean(stack.J_1907_R(), Reflector.IForgeItem_showDurabilityBar, stack);
    }

    public static int getLightValue(K_4074_S stateIn, BlockAndTintGetter worldIn, c_1514_x posIn) {
        return Reflector.IForgeBlockState_getLightValue2.exists() ? Reflector.callInt(stateIn, Reflector.IForgeBlockState_getLightValue2, worldIn, posIn) : stateIn.u_1723_Y();
    }

    public static F_3620_e getMapData(Z_1993_T stack, b_4507_u world) {
        if (Reflector.ForgeHooksClient.exists()) {
            G_3165_y filledmapitem = (G_3165_y)stack.J_1907_R();
            return G_3165_y.J_1907_R(stack, world);
        }
        return G_3165_y.J_1907_R(stack, world);
    }

    public static String[] getForgeModIds() {
        if (!Reflector.Loader.exists()) {
            return new String[0];
        }
        Object object = Reflector.call(Reflector.Loader_instance, new Object[0]);
        List list = (List)Reflector.call(object, Reflector.Loader_getActiveModList, new Object[0]);
        if (list == null) {
            return new String[0];
        }
        ArrayList<String> list1 = new ArrayList<String>();
        for (Object object1 : list) {
            String s;
            if (!Reflector.ModContainer.isInstance(object1) || (s = Reflector.callString(object1, Reflector.ModContainer_getModId, new Object[0])) == null) continue;
            list1.add(s);
        }
        String[] astring = list1.toArray(new String[list1.size()]);
        return astring;
    }

    public static boolean isAir(K_4074_S blockState, BlockGetter world, c_1514_x pos) {
        return Reflector.IForgeBlockState_isAir2.exists() ? Reflector.callBoolean(blockState, Reflector.IForgeBlockState_isAir2, world, pos) : blockState.v_4262_N();
    }

    public static boolean canDisableShield(Z_1993_T itemstack, Z_1993_T itemstack1, a_3913_L entityplayer, Z_530_i entityLiving) {
        return Reflector.IForgeItemStack_canDisableShield.exists() ? Reflector.callBoolean(itemstack, Reflector.IForgeItemStack_canDisableShield, itemstack1, entityplayer, entityLiving) : itemstack.J_1907_R() instanceof AxeItem;
    }

    public static boolean isShield(Z_1993_T itemstack, a_3913_L entityplayer) {
        if (Reflector.IForgeItemStack_isShield.exists()) {
            return Reflector.callBoolean(itemstack, Reflector.IForgeItemStack_isShield, entityplayer);
        }
        return itemstack.J_1907_R() == Items.NoteBlock;
    }

    public static Button makeButtonMods(k_596_g guiMainMenu, int yIn, int rowHeightIn) {
        return !Reflector.ModListScreen_Constructor.exists() ? null : new Button(guiMainMenu.width / 2 - 100, yIn + rowHeightIn * 2, 98, 20, new F_2904_S("fml.menu.mods"), button -> {
            k_2603_m screen = (k_2603_m)Reflector.ModListScreen_Constructor.newInstance(guiMainMenu);
            MinecraftClient.A_4115_X().n_1700_B(screen);
        });
    }

    public static void setForgeLightPipelineEnabled(boolean value) {
        if (Reflector.ForgeConfig_Client_forgeLightPipelineEnabled.exists()) {
            ReflectorForge.setConfigClientBoolean(Reflector.ForgeConfig_Client_forgeLightPipelineEnabled, value);
        }
    }

    public static boolean getForgeUseCombinedDepthStencilAttachment() {
        return Reflector.ForgeConfig_Client_useCombinedDepthStencilAttachment.exists() ? ReflectorForge.getConfigClientBoolean(Reflector.ForgeConfig_Client_useCombinedDepthStencilAttachment, false) : false;
    }

    public static boolean getConfigClientBoolean(ReflectorField configField, boolean def) {
        if (!configField.exists()) {
            return def;
        }
        Object object = Reflector.ForgeConfig_CLIENT.getValue();
        if (object == null) {
            return def;
        }
        Object object1 = Reflector.getFieldValue(object, configField);
        return object1 == null ? def : Reflector.callBoolean(object1, Reflector.ForgeConfigSpec_ConfigValue_get, new Object[0]);
    }

    private static void setConfigClientBoolean(ReflectorField clientField, final boolean value) {
        Object object1;
        Object object;
        if (clientField.exists() && (object = Reflector.ForgeConfig_CLIENT.getValue()) != null && (object1 = Reflector.getFieldValue(object, clientField)) != null) {
            Supplier<Boolean> supplier = new Supplier<Boolean>(){

                @Override
                public Boolean get() {
                    return value;
                }
            };
            Reflector.setFieldValue(object1, Reflector.ForgeConfigSpec_ConfigValue_defaultSupplier, supplier);
            Object object2 = Reflector.getFieldValue(object1, Reflector.ForgeConfigSpec_ConfigValue_spec);
            if (object2 != null) {
                Reflector.setFieldValue(object2, Reflector.ForgeConfigSpec_childConfig, null);
            }
            Log.dbg("Set ForgeConfig.CLIENT." + clientField.getTargetField().getName() + "=" + value);
        }
    }

    public static boolean canUpdate(N_4263_v entity) {
        return FORGE_ENTITY_CAN_UPDATE ? Reflector.callBoolean(entity, Reflector.IForgeEntity_canUpdate, new Object[0]) : true;
    }

    public static boolean isDamageable(q_1613_l item, Z_1993_T stack) {
        return Reflector.IForgeItem_isDamageable1.exists() ? Reflector.callBoolean(item, Reflector.IForgeItem_isDamageable1, stack) : item.P_4830_p();
    }

    public static void fillNormal(int[] faceData, b_257_Y facing) {
        M_1336_P vector3f = ReflectorForge.getVertexPos(faceData, 3);
        M_1336_P vector3f1 = ReflectorForge.getVertexPos(faceData, 1);
        M_1336_P vector3f2 = ReflectorForge.getVertexPos(faceData, 2);
        M_1336_P vector3f3 = ReflectorForge.getVertexPos(faceData, 0);
        vector3f.J_1907_R(vector3f1);
        vector3f2.J_1907_R(vector3f3);
        vector3f2.G_564_y(vector3f);
        vector3f2.G_564_y();
        int i = (byte)Math.round(vector3f2.n_1700_B() * 127.0f) & 0xFF;
        int j = (byte)Math.round(vector3f2.J_1907_R() * 127.0f) & 0xFF;
        int k = (byte)Math.round(vector3f2.R_4764_Y() * 127.0f) & 0xFF;
        int l = i | j << 8 | k << 16;
        int i1 = faceData.length / 4;
        for (int j1 = 0; j1 < 4; ++j1) {
            faceData[j1 * i1 + 7] = l;
        }
    }

    private static M_1336_P getVertexPos(int[] data, int vertex) {
        int i = data.length / 4;
        int j = vertex * i;
        float f = Float.intBitsToFloat(data[j]);
        float f1 = Float.intBitsToFloat(data[j + 1]);
        float f2 = Float.intBitsToFloat(data[j + 2]);
        return new M_1336_P(f, f1, f2);
    }
}



