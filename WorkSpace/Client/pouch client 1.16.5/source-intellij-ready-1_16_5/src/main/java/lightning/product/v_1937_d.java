/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import lightning.product.H_3272_P;
import lightning.product.ClientboundLoginPacket;
import lightning.product.ClientboundRespawnPacket;
import lightning.product.ClientboundPlayerPositionPacket;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.RunningOnDifferentThreadException;
import lightning.product.Packet;
import lightning.product.particlesParticleOptions;
import net.optifine.util.PacketRunnable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class v_1937_d {
    private static final Logger J_1907_R = LogManager.getLogger();
    public static f_2392_k<b_4507_u> n_1700_B = null;

    public static <T extends particlesParticleOptions> void n_1700_B(Packet<T> packetIn, T processor, e_3591_l worldIn) throws RunningOnDifferentThreadException {
        v_1937_d.n_1700_B(packetIn, processor, worldIn.T_2506_i());
    }

    public static <T extends particlesParticleOptions> void n_1700_B(Packet<T> packetIn, T processor, H_3272_P<?> executor) throws RunningOnDifferentThreadException {
        if (!executor.RealmsLongConfirmationScreen()) {
            executor.execute(new PacketRunnable(packetIn, () -> {
                v_1937_d.n_1700_B(packetIn);
                if (processor.getNetworkManager() != null && processor.getNetworkManager().u_1723_Y()) {
                    packetIn.n_1700_B(processor);
                }
                if (processor.getBotNetwork() != null && processor.getBotNetwork().v_4262_N()) {
                    packetIn.n_1700_B(processor);
                }
            }));
            throw RunningOnDifferentThreadException.n_1700_B;
        }
        v_1937_d.n_1700_B(packetIn);
    }

    protected static void n_1700_B(Packet p_clientPreProcessPacket_0_) {
        if (p_clientPreProcessPacket_0_ instanceof ClientboundPlayerPositionPacket) {
            MinecraftClient.A_4115_X().u_1723_Y.Q_2552_b();
        }
        if (p_clientPreProcessPacket_0_ instanceof ClientboundRespawnPacket) {
            ClientboundRespawnPacket srespawnpacket = (ClientboundRespawnPacket)p_clientPreProcessPacket_0_;
            n_1700_B = srespawnpacket.R_4764_Y();
        } else if (p_clientPreProcessPacket_0_ instanceof ClientboundLoginPacket) {
            ClientboundLoginPacket sjoingamepacket = (ClientboundLoginPacket)p_clientPreProcessPacket_0_;
            n_1700_B = sjoingamepacket.s_956_w();
        } else {
            n_1700_B = null;
        }
    }
}



