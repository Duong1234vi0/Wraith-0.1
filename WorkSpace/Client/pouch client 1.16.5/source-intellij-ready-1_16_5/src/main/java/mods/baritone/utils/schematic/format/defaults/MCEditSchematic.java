/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.schematic.format.defaults;

import lightning.product.I_3420_V;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import mods.baritone.utils.schematic.StaticSchematic;

public final class MCEditSchematic
extends StaticSchematic {
    public MCEditSchematic(U_2912_j schematic) {
        String type = schematic.M_588_G("Materials");
        if (!type.equals("Alpha")) {
            throw new IllegalStateException("bad schematic " + type);
        }
        this.x = schematic.w_1484_f("Width");
        this.y = schematic.w_1484_f("Height");
        this.z = schematic.w_1484_f("Length");
        byte[] blocks = schematic.P_4830_p("Blocks");
        byte[] additional = null;
        if (schematic.P_1922_E("AddBlocks")) {
            byte[] addBlocks = schematic.P_4830_p("AddBlocks");
            additional = new byte[addBlocks.length * 2];
            for (int i = 0; i < addBlocks.length; ++i) {
                additional[i * 2 + 0] = (byte)(addBlocks[i] >> 4 & 0xF);
                additional[i * 2 + 1] = (byte)(addBlocks[i] >> 0 & 0xF);
            }
        }
        this.states = new K_4074_S[this.x][this.z][this.y];
        for (int y = 0; y < this.y; ++y) {
            for (int z = 0; z < this.z; ++z) {
                for (int x = 0; x < this.x; ++x) {
                    int blockInd = (y * this.z + z) * this.x + x;
                    int blockID = blocks[blockInd] & 0xFF;
                    if (additional != null) {
                        blockID |= additional[blockInd] << 8;
                    }
                    T_2915_h block = V_3137_a.q_4610_l.n_1700_B(g_2336_b.J_1907_R(I_3420_V.n_1700_B(blockID)));
                    this.states[x][z][y] = block.multiplayerClientSuggestionProvider();
                }
            }
        }
    }
}


