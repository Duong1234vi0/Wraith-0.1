/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap
 */
package mods.baritone.utils.schematic.format.defaults;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.v_3760_Q;
import mods.baritone.utils.schematic.StaticSchematic;
import mods.baritone.utils.type.VarInt;

public final class SpongeSchematic
extends StaticSchematic {
    public SpongeSchematic(U_2912_j nbt) {
        this.x = nbt.w_1484_f("Width");
        this.y = nbt.w_1484_f("Height");
        this.z = nbt.w_1484_f("Length");
        this.states = new K_4074_S[this.x][this.z][this.y];
        Int2ObjectArrayMap palette = new Int2ObjectArrayMap();
        U_2912_j paletteTag = nbt.M_182_A("Palette");
        for (String tag : paletteTag.G_564_y()) {
            int index = paletteTag.w_1484_f(tag);
            SerializedBlockState serializedState = SerializedBlockState.getFromString(tag);
            if (serializedState == null) {
                throw new IllegalArgumentException("Unable to parse palette tag");
            }
            K_4074_S state = serializedState.deserialize();
            if (state == null) {
                throw new IllegalArgumentException("Unable to deserialize palette tag");
            }
            palette.put(index, (Object)state);
        }
        byte[] rawBlockData = nbt.P_4830_p("BlockData");
        int[] blockData = new int[this.x * this.y * this.z];
        int offset = 0;
        for (int i = 0; i < blockData.length; ++i) {
            if (offset >= rawBlockData.length) {
                throw new IllegalArgumentException("No remaining bytes in BlockData for complete schematic");
            }
            VarInt varInt = VarInt.read(rawBlockData, offset);
            blockData[i] = varInt.getValue();
            offset += varInt.getSize();
        }
        for (int y = 0; y < this.y; ++y) {
            for (int z = 0; z < this.z; ++z) {
                for (int x = 0; x < this.x; ++x) {
                    int index = (y * this.z + z) * this.x + x;
                    K_4074_S state = (K_4074_S)palette.get(blockData[index]);
                    if (state == null) {
                        throw new IllegalArgumentException("Invalid Palette Index " + index);
                    }
                    this.states[x][z][y] = state;
                }
            }
        }
    }

    private static final class SerializedBlockState {
        private static final Pattern REGEX = Pattern.compile("(?<location>(\\w+:)?\\w+)(\\[(?<properties>(\\w+=\\w+,?)+)])?");
        private final g_2336_b resourceLocation;
        private final Map<String, String> properties;
        private K_4074_S blockState;

        private SerializedBlockState(g_2336_b resourceLocation, Map<String, String> properties) {
            this.resourceLocation = resourceLocation;
            this.properties = properties;
        }

        private K_4074_S deserialize() {
            if (this.blockState == null) {
                T_2915_h block = V_3137_a.q_4610_l.n_1700_B(this.resourceLocation);
                this.blockState = block.multiplayerClientSuggestionProvider();
                this.properties.keySet().stream().sorted(String::compareTo).forEachOrdered(key -> {
                    v_3760_Q<?> property = block.t_1786_h().n_1700_B((String)key);
                    if (property != null) {
                        this.blockState = SerializedBlockState.setPropertyValue(this.blockState, property, this.properties.get(key));
                    }
                });
            }
            return this.blockState;
        }

        private static SerializedBlockState getFromString(String s) {
            Matcher m = REGEX.matcher(s);
            if (!m.matches()) {
                return null;
            }
            try {
                String location = m.group("location");
                String properties = m.group("properties");
                g_2336_b resourceLocation = new g_2336_b(location);
                HashMap<String, String> propertiesMap = new HashMap<String, String>();
                if (properties != null) {
                    for (String property : properties.split(",")) {
                        String[] split = property.split("=");
                        propertiesMap.put(split[0], split[1]);
                    }
                }
                return new SerializedBlockState(resourceLocation, propertiesMap);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private static <T extends Comparable<T>> K_4074_S setPropertyValue(K_4074_S state, v_3760_Q<T> property, String value) {
            Optional<T> parsed = property.J_1907_R(value);
            if (parsed.isPresent()) {
                return (K_4074_S)state.n_1700_B(property, (Comparable)parsed.get());
            }
            throw new IllegalArgumentException("Invalid value for property " + String.valueOf(property));
        }
    }
}


