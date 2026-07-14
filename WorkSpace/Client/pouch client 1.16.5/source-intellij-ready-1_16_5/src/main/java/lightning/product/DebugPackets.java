/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import io.netty.buffer.Unpooled;
import java.util.Collection;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.E_194_H;
import lightning.product.F_997_G;
import lightning.product.WorldGenLevel;
import lightning.product.Y_1387_d;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_1722_e;
import lightning.product.b_1913_J;
import lightning.product.b_2585_i;
import lightning.product.b_3129_s;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.StructureStart;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.ClientboundCustomPayloadPacket;
import lightning.product.r_4811_B;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DebugPackets {
    private static final Logger n_1700_B = LogManager.getLogger();

    public static void n_1700_B(e_3591_l p_229752_0_, c_1514_x p_229752_1_, String p_229752_2_, int p_229752_3_, int p_229752_4_) {
        b_2585_i packetbuffer = new b_2585_i(Unpooled.buffer());
        packetbuffer.n_1700_B(p_229752_1_);
        packetbuffer.writeInt(p_229752_3_);
        packetbuffer.n_1700_B(p_229752_2_);
        packetbuffer.writeInt(p_229752_4_);
        DebugPackets.n_1700_B(p_229752_0_, packetbuffer, ClientboundCustomPayloadPacket.Q_4569_t);
    }

    public static void n_1700_B(e_3591_l p_229751_0_) {
        b_2585_i packetbuffer = new b_2585_i(Unpooled.buffer());
        DebugPackets.n_1700_B(p_229751_0_, packetbuffer, ClientboundCustomPayloadPacket.M_182_A);
    }

    public static void n_1700_B(e_3591_l worldIn, Y_1387_d p_218802_1_) {
    }

    public static void n_1700_B(e_3591_l worldIn, c_1514_x p_218799_1_) {
        DebugPackets.G_564_y(worldIn, p_218799_1_);
    }

    public static void J_1907_R(e_3591_l worldIn, c_1514_x p_218805_1_) {
        DebugPackets.G_564_y(worldIn, p_218805_1_);
    }

    public static void R_4764_Y(e_3591_l worldIn, c_1514_x p_218801_1_) {
        DebugPackets.G_564_y(worldIn, p_218801_1_);
    }

    private static void G_564_y(e_3591_l p_240840_0_, c_1514_x p_240840_1_) {
    }

    public static void n_1700_B(b_4507_u worldIn, Z_530_i p_218803_1_, @Nullable b_1722_e p_218803_2_, float p_218803_3_) {
    }

    public static void n_1700_B(b_4507_u worldIn, c_1514_x p_218806_1_) {
    }

    public static void n_1700_B(WorldGenLevel worldIn, StructureStart<?> p_218804_1_) {
    }

    public static void n_1700_B(b_4507_u worldIn, Z_530_i p_218800_1_, E_194_H p_218800_2_) {
        if (worldIn instanceof e_3591_l) {
            // empty if block
        }
    }

    public static void n_1700_B(e_3591_l worldIn, Collection<b_3129_s> p_222946_1_) {
    }

    public static void n_1700_B(r_4811_B p_218798_0_) {
    }

    public static void n_1700_B(b_1913_J p_229749_0_) {
    }

    public static void n_1700_B(F_997_G p_229750_0_) {
    }

    private static void n_1700_B(e_3591_l p_229753_0_, b_2585_i p_229753_1_, g_2336_b p_229753_2_) {
        ClientboundCustomPayloadPacket ipacket = new ClientboundCustomPayloadPacket(p_229753_2_, p_229753_1_);
        for (a_3913_L a_3913_L2 : p_229753_0_.J_1907_R().multiplayerClientSuggestionProvider()) {
            ((B_4088_l)a_3913_L2).n_1700_B.n_1700_B(ipacket);
        }
    }
}


