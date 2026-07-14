/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.Validate
 */
package mods.baritone.utils.schematic.format.defaults;

import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.q_2896_o;
import lightning.product.v_3760_Q;
import lightning.product.z_3539_x;
import mods.baritone.utils.schematic.StaticSchematic;
import org.apache.commons.lang3.Validate;

public final class LitematicaSchematic
extends StaticSchematic {
    private final z_3539_x offsetMinCorner;
    private final U_2912_j nbt;

    public LitematicaSchematic(U_2912_j nbtTagCompound, boolean rotated) {
        this.nbt = nbtTagCompound;
        this.offsetMinCorner = new z_3539_x(this.getMinOfSchematic("x"), this.getMinOfSchematic("y"), this.getMinOfSchematic("z"));
        this.y = Math.abs(this.nbt.M_182_A("Metadata").M_182_A("EnclosingSize").w_1484_f("y"));
        if (rotated) {
            this.x = Math.abs(this.nbt.M_182_A("Metadata").M_182_A("EnclosingSize").w_1484_f("z"));
            this.z = Math.abs(this.nbt.M_182_A("Metadata").M_182_A("EnclosingSize").w_1484_f("x"));
        } else {
            this.x = Math.abs(this.nbt.M_182_A("Metadata").M_182_A("EnclosingSize").w_1484_f("x"));
            this.z = Math.abs(this.nbt.M_182_A("Metadata").M_182_A("EnclosingSize").w_1484_f("z"));
        }
        this.states = new K_4074_S[this.x][this.z][this.y];
        this.fillInSchematic();
    }

    private static String[] getRegions(U_2912_j nbt) {
        return nbt.M_182_A("Regions").G_564_y().toArray(new String[0]);
    }

    private static int getMinOfSubregion(U_2912_j nbt, String subReg, String s) {
        int a = nbt.M_182_A("Regions").M_182_A(subReg).M_182_A("Position").w_1484_f(s);
        int b = nbt.M_182_A("Regions").M_182_A(subReg).M_182_A("Size").w_1484_f(s);
        if (b < 0) {
            ++b;
        }
        return Math.min(a, a + b);
    }

    private static K_4074_S[] getBlockList(q_2896_o blockStatePalette) {
        K_4074_S[] blockList = new K_4074_S[blockStatePalette.size()];
        for (int i = 0; i < blockStatePalette.size(); ++i) {
            T_2915_h block = V_3137_a.q_4610_l.n_1700_B(new g_2336_b(((U_2912_j)blockStatePalette.s_956_w(i)).M_588_G("Name")));
            U_2912_j properties = ((U_2912_j)blockStatePalette.s_956_w(i)).M_182_A("Properties");
            blockList[i] = LitematicaSchematic.getBlockState(block, properties);
        }
        return blockList;
    }

    private static K_4074_S getBlockState(T_2915_h block, U_2912_j properties) {
        K_4074_S blockState = block.multiplayerClientSuggestionProvider();
        for (Object key : properties.G_564_y().toArray()) {
            v_3760_Q<?> property = block.t_1786_h().n_1700_B((String)key);
            String propertyValue = properties.M_588_G((String)key);
            if (property == null) continue;
            blockState = LitematicaSchematic.setPropertyValue(blockState, property, propertyValue);
        }
        return blockState;
    }

    private static <T extends Comparable<T>> K_4074_S setPropertyValue(K_4074_S state, v_3760_Q<T> property, String value) {
        Optional<T> parsed = property.J_1907_R(value);
        if (parsed.isPresent()) {
            return (K_4074_S)state.n_1700_B(property, (Comparable)parsed.get());
        }
        throw new IllegalArgumentException("Invalid value for property " + String.valueOf(property));
    }

    private static int getBitsPerBlock(int amountOfBlockTypes) {
        return (int)Math.max(2.0, Math.ceil(Math.log(amountOfBlockTypes) / Math.log(2.0)));
    }

    private static long getVolume(U_2912_j nbt, String subReg) {
        return Math.abs(nbt.M_182_A("Regions").M_182_A(subReg).M_182_A("Size").w_1484_f("x") * nbt.M_182_A("Regions").M_182_A(subReg).M_182_A("Size").w_1484_f("y") * nbt.M_182_A("Regions").M_182_A(subReg).M_182_A("Size").w_1484_f("z"));
    }

    private static long[] getBlockStates(U_2912_j nbt, String subReg) {
        return nbt.M_182_A("Regions").M_182_A(subReg).Q_4569_t("BlockStates");
    }

    private static boolean inSubregion(U_2912_j nbt, String subReg, int x, int y, int z) {
        return x >= 0 && y >= 0 && z >= 0 && x < Math.abs(nbt.M_182_A("Regions").M_182_A(subReg).M_182_A("Size").w_1484_f("x")) && y < Math.abs(nbt.M_182_A("Regions").M_182_A(subReg).M_182_A("Size").w_1484_f("y")) && z < Math.abs(nbt.M_182_A("Regions").M_182_A(subReg).M_182_A("Size").w_1484_f("z"));
    }

    private int getMinOfSchematic(String s) {
        int n = Integer.MAX_VALUE;
        for (String subReg : LitematicaSchematic.getRegions(this.nbt)) {
            n = Math.min(n, LitematicaSchematic.getMinOfSubregion(this.nbt, subReg, s));
        }
        return n;
    }

    private void fillInSchematic() {
        for (String subReg : LitematicaSchematic.getRegions(this.nbt)) {
            q_2896_o usedBlockTypes = this.nbt.M_182_A("Regions").M_182_A(subReg).G_564_y("BlockStatePalette", 10);
            K_4074_S[] blockList = LitematicaSchematic.getBlockList(usedBlockTypes);
            int bitsPerBlock = LitematicaSchematic.getBitsPerBlock(usedBlockTypes.size());
            long regionVolume = LitematicaSchematic.getVolume(this.nbt, subReg);
            long[] blockStateArray = LitematicaSchematic.getBlockStates(this.nbt, subReg);
            LitematicaBitArray bitArray = new LitematicaBitArray(bitsPerBlock, regionVolume, blockStateArray);
            this.writeSubregionIntoSchematic(this.nbt, subReg, blockList, bitArray);
        }
    }

    private void writeSubregionIntoSchematic(U_2912_j nbt, String subReg, K_4074_S[] blockList, LitematicaBitArray bitArray) {
        z_3539_x offsetSubregion = new z_3539_x(LitematicaSchematic.getMinOfSubregion(nbt, subReg, "x"), LitematicaSchematic.getMinOfSubregion(nbt, subReg, "y"), LitematicaSchematic.getMinOfSubregion(nbt, subReg, "z"));
        int index = 0;
        for (int y = 0; y < this.y; ++y) {
            for (int z = 0; z < this.z; ++z) {
                for (int x = 0; x < this.x; ++x) {
                    if (!LitematicaSchematic.inSubregion(nbt, subReg, x, y, z)) continue;
                    this.states[x - (this.offsetMinCorner.getX() - offsetSubregion.getX())][z - (this.offsetMinCorner.getZ() - offsetSubregion.getZ())][y - (this.offsetMinCorner.getY() - offsetSubregion.getY())] = blockList[bitArray.getAt(index)];
                    ++index;
                }
            }
        }
    }

    public z_3539_x getOffsetMinCorner() {
        return this.offsetMinCorner;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public void setDirect(int x, int y, int z, K_4074_S blockState) {
        this.states[x][z][y] = blockState;
    }

    public LitematicaSchematic getCopy(boolean rotated) {
        return new LitematicaSchematic(this.nbt, rotated);
    }

    private static class LitematicaBitArray {
        private final long[] longArray;
        private final int bitsPerEntry;
        private final long maxEntryValue;
        private final long arraySize;

        public LitematicaBitArray(int bitsPerEntryIn, long arraySizeIn, @Nullable long[] longArrayIn) {
            Validate.inclusiveBetween((long)1L, (long)32L, (long)bitsPerEntryIn);
            this.arraySize = arraySizeIn;
            this.bitsPerEntry = bitsPerEntryIn;
            this.maxEntryValue = (1L << bitsPerEntryIn) - 1L;
            this.longArray = longArrayIn != null ? longArrayIn : new long[(int)(LitematicaBitArray.roundUp(arraySizeIn * (long)bitsPerEntryIn, 64L) / 64L)];
        }

        public static long roundUp(long number, long interval) {
            long i;
            int sign = 1;
            if (interval == 0L) {
                return 0L;
            }
            if (number == 0L) {
                return interval;
            }
            if (number < 0L) {
                sign = -1;
            }
            return (i = number % (interval * (long)sign)) == 0L ? number : number + interval * (long)sign - i;
        }

        public int getAt(long index) {
            Validate.inclusiveBetween((long)0L, (long)(this.arraySize - 1L), (long)index);
            long startOffset = index * (long)this.bitsPerEntry;
            int startArrIndex = (int)(startOffset >> 6);
            int endArrIndex = (int)((index + 1L) * (long)this.bitsPerEntry - 1L >> 6);
            int startBitOffset = (int)(startOffset & 0x3FL);
            if (startArrIndex == endArrIndex) {
                return (int)(this.longArray[startArrIndex] >>> startBitOffset & this.maxEntryValue);
            }
            int endOffset = 64 - startBitOffset;
            return (int)((this.longArray[startArrIndex] >>> startBitOffset | this.longArray[endArrIndex] << endOffset) & this.maxEntryValue);
        }

        public long size() {
            return this.arraySize;
        }
    }
}


