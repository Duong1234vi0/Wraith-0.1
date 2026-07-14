/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 */
package lightning.product;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.A_1557_z;
import lightning.product.ServerboundClientInformationPacket;
import lightning.product.ClientboundContainerSetContentPacket;
import lightning.product.B_3790_C;
import lightning.product.C_1375_J;
import lightning.product.D_1056_N;
import lightning.product.D_4338_T;
import lightning.product.ClientboundSetCameraPacket;
import lightning.product.ServerboundInteractPacket;
import lightning.product.ClientboundSetObjectivePacket;
import lightning.product.E_3520_U;
import lightning.product.ClientboundBlockBreakAckPacket;
import lightning.product.F_1464_b;
import lightning.product.F_551_J;
import lightning.product.ServerboundResourcePackPacket;
import lightning.product.G_4919_s;
import lightning.product.H_1420_X;
import lightning.product.H_2543_D;
import lightning.product.H_4075_o;
import lightning.product.ClientboundChangeDifficultyPacket;
import lightning.product.ClientIntentionPacket;
import lightning.product.I_4656_k;
import lightning.product.I_4838_g;
import lightning.product.ServerboundTeleportToEntityPacket;
import lightning.product.ClientboundHelloPacket;
import lightning.product.ServerboundSetJigsawBlockPacket;
import lightning.product.ClientboundLoginPacket;
import lightning.product.ClientboundGameEventPacket;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.ServerboundSeenAdvancementsPacket;
import lightning.product.ClientboundCustomSoundPacket;
import lightning.product.K_4053_T;
import lightning.product.ServerboundSetStructureBlockPacket;
import lightning.product.L_4122_s;
import lightning.product.ClientboundTeleportEntityPacket;
import lightning.product.ClientboundRespawnPacket;
import lightning.product.ClientboundCommandSuggestionsPacket;
import lightning.product.ClientboundLightUpdatePacket;
import lightning.product.N_3268_u;
import lightning.product.N_3369_p;
import lightning.product.N_4422_X;
import lightning.product.ServerboundRecipeBookChangeSettingsPacket;
import lightning.product.ClientboundGameProfilePacket;
import lightning.product.ClientboundPlayerAbilitiesPacket;
import lightning.product.ClientboundSetTitlesPacket;
import lightning.product.ClientboundUpdateAdvancementsPacket;
import lightning.product.ClientboundBlockUpdatePacket;
import lightning.product.O_3036_q;
import lightning.product.ServerboundStatusRequestPacket;
import lightning.product.ClientboundTagQueryPacket;
import lightning.product.P_1520_s;
import lightning.product.ServerboundContainerClickPacket;
import lightning.product.ClientboundAddPaintingPacket;
import lightning.product.P_4526_H;
import lightning.product.ClientboundSectionBlocksUpdatePacket;
import lightning.product.ClientboundAddMobPacket;
import lightning.product.ClientboundOpenScreenPacket;
import lightning.product.ClientboundStopSoundPacket;
import lightning.product.ClientboundUpdateAttributesPacket;
import lightning.product.R_831_p;
import lightning.product.ClientboundSetDefaultSpawnPositionPacket;
import lightning.product.ServerboundCustomQueryPacket;
import lightning.product.ClientboundPongResponsePacket;
import lightning.product.T_3558_p;
import lightning.product.T_3952_j;
import lightning.product.T_4830_s;
import lightning.product.U_157_Y;
import lightning.product.V_173_d;
import lightning.product.V_182_a;
import lightning.product.V_674_I;
import lightning.product.W_1158_a;
import lightning.product.ClientboundPlayerLookAtPacket;
import lightning.product.ClientboundSetHealthPacket;
import lightning.product.ClientboundAddPlayerPacket;
import lightning.product.W_4148_E;
import lightning.product.W_4328_U;
import lightning.product.ClientboundSoundPacket;
import lightning.product.X_508_u;
import lightning.product.X_776_r;
import lightning.product.X_821_u;
import lightning.product.ClientboundSetEquipmentPacket;
import lightning.product.Z_3504_M;
import lightning.product.Z_390_O;
import lightning.product.ClientboundRemoveMobEffectPacket;
import lightning.product.ClientboundSelectAdvancementsTabPacket;
import lightning.product.ServerboundPaddleBoatPacket;
import lightning.product.ClientboundSetExperiencePacket;
import lightning.product.ServerboundPlayerAbilitiesPacket;
import lightning.product.ClientboundMerchantOffersPacket;
import lightning.product.ClientboundBlockEventPacket;
import lightning.product.ServerboundSetCommandMinecartPacket;
import lightning.product.a_3942_s;
import lightning.product.a_4762_y;
import lightning.product.a_4764_N;
import lightning.product.a_9_q;
import lightning.product.ClientboundPlayerPositionPacket;
import lightning.product.ServerboundKeyPacket;
import lightning.product.ClientboundSetPassengersPacket;
import lightning.product.d_1428_k;
import lightning.product.ServerboundHelloPacket;
import lightning.product.d_338_B;
import lightning.product.ClientboundCustomQueryPacket;
import lightning.product.e_446_u;
import lightning.product.ClientboundCustomPayloadPacket;
import lightning.product.ClientboundLevelParticlesPacket;
import lightning.product.ClientboundExplodePacket;
import lightning.product.h_516_K;
import lightning.product.ClientboundAwardStatsPacket;
import lightning.product.i_2572_h;
import lightning.product.ClientboundSetPlayerTeamPacket;
import lightning.product.ClientboundAddExperienceOrbPacket;
import lightning.product.i_4972_c;
import lightning.product.j_3341_s;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.ClientboundChatPacket;
import lightning.product.ServerboundLockDifficultyPacket;
import lightning.product.ClientboundRecipePacket;
import lightning.product.m_1761_s;
import lightning.product.ClientboundSetEntityMotionPacket;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.ClientboundPlayerCombatPacket;
import lightning.product.n_2740_g;
import lightning.product.ClientboundRemoveEntitiesPacket;
import lightning.product.n_4563_y;
import lightning.product.p_1183_T;
import lightning.product.p_198_K;
import lightning.product.p_4692_E;
import lightning.product.ClientboundStatusResponsePacket;
import lightning.product.ClientboundSetTimePacket;
import lightning.product.q_3092_O;
import lightning.product.ClientboundUpdateMobEffectPacket;
import lightning.product.r_1873_a;
import lightning.product.r_586_S;
import lightning.product.ClientboundLevelEventPacket;
import lightning.product.ServerboundSignUpdatePacket;
import lightning.product.ClientboundSetDisplayObjectivePacket;
import lightning.product.Packet;
import lightning.product.t_3906_J;
import lightning.product.t_4503_H;
import lightning.product.v_4727_z;
import lightning.product.ClientboundSetScorePacket;
import lightning.product.w_3005_z;
import lightning.product.w_690_m;
import lightning.product.ServerboundSetCommandBlockPacket;
import lightning.product.x_2401_v;
import lightning.product.x_2680_y;
import lightning.product.ServerboundPlayerInputPacket;
import lightning.product.particlesParticleOptions;
import lightning.product.z_1181_o;
import lightning.product.z_1886_T;
import lightning.product.ClientboundSoundEntityPacket;
import org.apache.logging.log4j.LogManager;

public final class d_4952_K
extends Enum<d_4952_K> {
    public static final /* enum */ d_4952_K n_1700_B = new d_4952_K(-1, d_4952_K.J_1907_R().n_1700_B(a_3942_s.n_1700_B, new n_1700_B().n_1700_B(ClientIntentionPacket.class, ClientIntentionPacket::new)));
    public static final /* enum */ d_4952_K J_1907_R = new d_4952_K(0, d_4952_K.J_1907_R().n_1700_B(a_3942_s.J_1907_R, new n_1700_B().n_1700_B(ClientboundAddEntityPacket.class, ClientboundAddEntityPacket::new).n_1700_B(ClientboundAddExperienceOrbPacket.class, ClientboundAddExperienceOrbPacket::new).n_1700_B(ClientboundAddMobPacket.class, ClientboundAddMobPacket::new).n_1700_B(ClientboundAddPaintingPacket.class, ClientboundAddPaintingPacket::new).n_1700_B(ClientboundAddPlayerPacket.class, ClientboundAddPlayerPacket::new).n_1700_B(q_3092_O.class, q_3092_O::new).n_1700_B(ClientboundAwardStatsPacket.class, ClientboundAwardStatsPacket::new).n_1700_B(ClientboundBlockBreakAckPacket.class, ClientboundBlockBreakAckPacket::new).n_1700_B(t_4503_H.class, t_4503_H::new).n_1700_B(ClientboundBlockEntityDataPacket.class, ClientboundBlockEntityDataPacket::new).n_1700_B(ClientboundBlockEventPacket.class, ClientboundBlockEventPacket::new).n_1700_B(ClientboundBlockUpdatePacket.class, ClientboundBlockUpdatePacket::new).n_1700_B(m_1761_s.class, m_1761_s::new).n_1700_B(ClientboundChangeDifficultyPacket.class, ClientboundChangeDifficultyPacket::new).n_1700_B(ClientboundChatPacket.class, ClientboundChatPacket::new).n_1700_B(ClientboundCommandSuggestionsPacket.class, ClientboundCommandSuggestionsPacket::new).n_1700_B(B_3790_C.class, B_3790_C::new).n_1700_B(n_2740_g.class, n_2740_g::new).n_1700_B(v_4727_z.class, v_4727_z::new).n_1700_B(ClientboundContainerSetContentPacket.class, ClientboundContainerSetContentPacket::new).n_1700_B(X_821_u.class, X_821_u::new).n_1700_B(a_4764_N.class, a_4764_N::new).n_1700_B(G_4919_s.class, G_4919_s::new).n_1700_B(ClientboundCustomPayloadPacket.class, ClientboundCustomPayloadPacket::new).n_1700_B(ClientboundCustomSoundPacket.class, ClientboundCustomSoundPacket::new).n_1700_B(w_690_m.class, w_690_m::new).n_1700_B(C_1375_J.class, C_1375_J::new).n_1700_B(ClientboundExplodePacket.class, ClientboundExplodePacket::new).n_1700_B(W_4148_E.class, W_4148_E::new).n_1700_B(ClientboundGameEventPacket.class, ClientboundGameEventPacket::new).n_1700_B(t_3906_J.class, t_3906_J::new).n_1700_B(r_1873_a.class, r_1873_a::new).n_1700_B(U_157_Y.class, U_157_Y::new).n_1700_B(ClientboundLevelEventPacket.class, ClientboundLevelEventPacket::new).n_1700_B(ClientboundLevelParticlesPacket.class, ClientboundLevelParticlesPacket::new).n_1700_B(ClientboundLightUpdatePacket.class, ClientboundLightUpdatePacket::new).n_1700_B(ClientboundLoginPacket.class, ClientboundLoginPacket::new).n_1700_B(N_3369_p.class, N_3369_p::new).n_1700_B(ClientboundMerchantOffersPacket.class, ClientboundMerchantOffersPacket::new).n_1700_B(N_4422_X.R_4764_Y.class, N_4422_X.R_4764_Y::new).n_1700_B(N_4422_X.J_1907_R.class, N_4422_X.J_1907_R::new).n_1700_B(N_4422_X.n_1700_B.class, N_4422_X.n_1700_B::new).n_1700_B(N_4422_X.class, N_4422_X::new).n_1700_B(F_551_J.class, F_551_J::new).n_1700_B(x_2680_y.class, x_2680_y::new).n_1700_B(ClientboundOpenScreenPacket.class, ClientboundOpenScreenPacket::new).n_1700_B(w_3005_z.class, w_3005_z::new).n_1700_B(W_1158_a.class, W_1158_a::new).n_1700_B(ClientboundPlayerAbilitiesPacket.class, ClientboundPlayerAbilitiesPacket::new).n_1700_B(ClientboundPlayerCombatPacket.class, ClientboundPlayerCombatPacket::new).n_1700_B(d_338_B.class, d_338_B::new).n_1700_B(ClientboundPlayerLookAtPacket.class, ClientboundPlayerLookAtPacket::new).n_1700_B(ClientboundPlayerPositionPacket.class, ClientboundPlayerPositionPacket::new).n_1700_B(ClientboundRecipePacket.class, ClientboundRecipePacket::new).n_1700_B(ClientboundRemoveEntitiesPacket.class, ClientboundRemoveEntitiesPacket::new).n_1700_B(ClientboundRemoveMobEffectPacket.class, ClientboundRemoveMobEffectPacket::new).n_1700_B(E_3520_U.class, E_3520_U::new).n_1700_B(ClientboundRespawnPacket.class, ClientboundRespawnPacket::new).n_1700_B(X_776_r.class, X_776_r::new).n_1700_B(ClientboundSectionBlocksUpdatePacket.class, ClientboundSectionBlocksUpdatePacket::new).n_1700_B(ClientboundSelectAdvancementsTabPacket.class, ClientboundSelectAdvancementsTabPacket::new).n_1700_B(R_831_p.class, R_831_p::new).n_1700_B(ClientboundSetCameraPacket.class, ClientboundSetCameraPacket::new).n_1700_B(Z_390_O.class, Z_390_O::new).n_1700_B(p_198_K.class, p_198_K::new).n_1700_B(n_4563_y.class, n_4563_y::new).n_1700_B(ClientboundSetDefaultSpawnPositionPacket.class, ClientboundSetDefaultSpawnPositionPacket::new).n_1700_B(ClientboundSetDisplayObjectivePacket.class, ClientboundSetDisplayObjectivePacket::new).n_1700_B(a_4762_y.class, a_4762_y::new).n_1700_B(e_446_u.class, e_446_u::new).n_1700_B(ClientboundSetEntityMotionPacket.class, ClientboundSetEntityMotionPacket::new).n_1700_B(ClientboundSetEquipmentPacket.class, ClientboundSetEquipmentPacket::new).n_1700_B(ClientboundSetExperiencePacket.class, ClientboundSetExperiencePacket::new).n_1700_B(ClientboundSetHealthPacket.class, ClientboundSetHealthPacket::new).n_1700_B(ClientboundSetObjectivePacket.class, ClientboundSetObjectivePacket::new).n_1700_B(ClientboundSetPassengersPacket.class, ClientboundSetPassengersPacket::new).n_1700_B(ClientboundSetPlayerTeamPacket.class, ClientboundSetPlayerTeamPacket::new).n_1700_B(ClientboundSetScorePacket.class, ClientboundSetScorePacket::new).n_1700_B(ClientboundSetTimePacket.class, ClientboundSetTimePacket::new).n_1700_B(ClientboundSetTitlesPacket.class, ClientboundSetTitlesPacket::new).n_1700_B(ClientboundSoundEntityPacket.class, ClientboundSoundEntityPacket::new).n_1700_B(ClientboundSoundPacket.class, ClientboundSoundPacket::new).n_1700_B(ClientboundStopSoundPacket.class, ClientboundStopSoundPacket::new).n_1700_B(D_1056_N.class, D_1056_N::new).n_1700_B(ClientboundTagQueryPacket.class, ClientboundTagQueryPacket::new).n_1700_B(X_508_u.class, X_508_u::new).n_1700_B(ClientboundTeleportEntityPacket.class, ClientboundTeleportEntityPacket::new).n_1700_B(ClientboundUpdateAdvancementsPacket.class, ClientboundUpdateAdvancementsPacket::new).n_1700_B(ClientboundUpdateAttributesPacket.class, ClientboundUpdateAttributesPacket::new).n_1700_B(ClientboundUpdateMobEffectPacket.class, ClientboundUpdateMobEffectPacket::new).n_1700_B(A_1557_z.class, A_1557_z::new).n_1700_B(I_4656_k.class, I_4656_k::new)).n_1700_B(a_3942_s.n_1700_B, new n_1700_B().n_1700_B(x_2401_v.class, x_2401_v::new).n_1700_B(H_1420_X.class, H_1420_X::new).n_1700_B(z_1886_T.class, z_1886_T::new).n_1700_B(W_4328_U.class, W_4328_U::new).n_1700_B(H_2543_D.class, H_2543_D::new).n_1700_B(ServerboundClientInformationPacket.class, ServerboundClientInformationPacket::new).n_1700_B(I_4838_g.class, I_4838_g::new).n_1700_B(V_674_I.class, V_674_I::new).n_1700_B(h_516_K.class, h_516_K::new).n_1700_B(ServerboundContainerClickPacket.class, ServerboundContainerClickPacket::new).n_1700_B(P_4526_H.class, P_4526_H::new).n_1700_B(O_3036_q.class, O_3036_q::new).n_1700_B(D_4338_T.class, D_4338_T::new).n_1700_B(T_4830_s.class, T_4830_s::new).n_1700_B(ServerboundInteractPacket.class, ServerboundInteractPacket::new).n_1700_B(V_182_a.class, V_182_a::new).n_1700_B(p_4692_E.class, p_4692_E::new).n_1700_B(ServerboundLockDifficultyPacket.class, ServerboundLockDifficultyPacket::new).n_1700_B(N_3268_u.n_1700_B.class, N_3268_u.n_1700_B::new).n_1700_B(N_3268_u.J_1907_R.class, N_3268_u.J_1907_R::new).n_1700_B(N_3268_u.R_4764_Y.class, N_3268_u.R_4764_Y::new).n_1700_B(N_3268_u.class, N_3268_u::new).n_1700_B(L_4122_s.class, L_4122_s::new).n_1700_B(ServerboundPaddleBoatPacket.class, ServerboundPaddleBoatPacket::new).n_1700_B(i_2572_h.class, i_2572_h::new).n_1700_B(H_4075_o.class, H_4075_o::new).n_1700_B(ServerboundPlayerAbilitiesPacket.class, ServerboundPlayerAbilitiesPacket::new).n_1700_B(ServerboundPlayerActionPacket.class, ServerboundPlayerActionPacket::new).n_1700_B(T_3952_j.class, T_3952_j::new).n_1700_B(ServerboundPlayerInputPacket.class, ServerboundPlayerInputPacket::new).n_1700_B(ServerboundRecipeBookChangeSettingsPacket.class, ServerboundRecipeBookChangeSettingsPacket::new).n_1700_B(z_1181_o.class, z_1181_o::new).n_1700_B(P_1520_s.class, P_1520_s::new).n_1700_B(ServerboundResourcePackPacket.class, ServerboundResourcePackPacket::new).n_1700_B(ServerboundSeenAdvancementsPacket.class, ServerboundSeenAdvancementsPacket::new).n_1700_B(a_9_q.class, a_9_q::new).n_1700_B(d_1428_k.class, d_1428_k::new).n_1700_B(p_1183_T.class, p_1183_T::new).n_1700_B(ServerboundSetCommandBlockPacket.class, ServerboundSetCommandBlockPacket::new).n_1700_B(ServerboundSetCommandMinecartPacket.class, ServerboundSetCommandMinecartPacket::new).n_1700_B(r_586_S.class, r_586_S::new).n_1700_B(ServerboundSetJigsawBlockPacket.class, ServerboundSetJigsawBlockPacket::new).n_1700_B(ServerboundSetStructureBlockPacket.class, ServerboundSetStructureBlockPacket::new).n_1700_B(ServerboundSignUpdatePacket.class, ServerboundSignUpdatePacket::new).n_1700_B(T_3558_p.class, T_3558_p::new).n_1700_B(ServerboundTeleportToEntityPacket.class, ServerboundTeleportToEntityPacket::new).n_1700_B(F_1464_b.class, F_1464_b::new).n_1700_B(Z_3504_M.class, Z_3504_M::new)));
    public static final /* enum */ d_4952_K R_4764_Y = new d_4952_K(1, d_4952_K.J_1907_R().n_1700_B(a_3942_s.n_1700_B, new n_1700_B().n_1700_B(ServerboundStatusRequestPacket.class, ServerboundStatusRequestPacket::new).n_1700_B(K_4053_T.class, K_4053_T::new)).n_1700_B(a_3942_s.J_1907_R, new n_1700_B().n_1700_B(ClientboundStatusResponsePacket.class, ClientboundStatusResponsePacket::new).n_1700_B(ClientboundPongResponsePacket.class, ClientboundPongResponsePacket::new)));
    public static final /* enum */ d_4952_K G_564_y = new d_4952_K(2, d_4952_K.J_1907_R().n_1700_B(a_3942_s.J_1907_R, new n_1700_B().n_1700_B(V_173_d.class, V_173_d::new).n_1700_B(ClientboundHelloPacket.class, ClientboundHelloPacket::new).n_1700_B(ClientboundGameProfilePacket.class, ClientboundGameProfilePacket::new).n_1700_B(i_4972_c.class, i_4972_c::new).n_1700_B(ClientboundCustomQueryPacket.class, ClientboundCustomQueryPacket::new)).n_1700_B(a_3942_s.n_1700_B, new n_1700_B().n_1700_B(ServerboundHelloPacket.class, ServerboundHelloPacket::new).n_1700_B(ServerboundKeyPacket.class, ServerboundKeyPacket::new).n_1700_B(ServerboundCustomQueryPacket.class, ServerboundCustomQueryPacket::new)));
    private static final d_4952_K[] P_1922_E;
    private static final Map<Class<? extends Packet<?>>, d_4952_K> u_1723_Y;
    private final int v_4262_N;
    private final Map<a_3942_s, ? extends n_1700_B<?>> w_1484_f;
    private static final /* synthetic */ d_4952_K[] t_148_a;

    public static d_4952_K[] values() {
        return (d_4952_K[])t_148_a.clone();
    }

    public static d_4952_K valueOf(String name) {
        return Enum.valueOf(d_4952_K.class, name);
    }

    private static J_1907_R J_1907_R() {
        return new J_1907_R();
    }

    private d_4952_K(int p_i226083_3_, J_1907_R p_i226083_4_) {
        this.v_4262_N = p_i226083_3_;
        this.w_1484_f = p_i226083_4_.n_1700_B;
    }

    @Nullable
    public Integer n_1700_B(a_3942_s direction, Packet<?> packetIn) {
        return this.w_1484_f.get((Object)direction).n_1700_B(packetIn.getClass());
    }

    @Nullable
    public Packet<?> n_1700_B(a_3942_s direction, int packetId) {
        return this.w_1484_f.get((Object)direction).n_1700_B(packetId);
    }

    public int n_1700_B() {
        return this.v_4262_N;
    }

    @Nullable
    public static d_4952_K n_1700_B(int stateId) {
        return stateId >= -1 && stateId <= 2 ? P_1922_E[stateId - -1] : null;
    }

    public static d_4952_K n_1700_B(Packet<?> packetIn) {
        return u_1723_Y.get(packetIn.getClass());
    }

    private static /* synthetic */ d_4952_K[] R_4764_Y() {
        return new d_4952_K[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
    }

    static {
        t_148_a = d_4952_K.R_4764_Y();
        P_1922_E = new d_4952_K[4];
        u_1723_Y = Maps.newHashMap();
        for (d_4952_K protocoltype : d_4952_K.values()) {
            int i = protocoltype.n_1700_B();
            if (i < -1 || i > 2) {
                throw new Error("Invalid protocol ID " + Integer.toString(i));
            }
            d_4952_K.P_1922_E[i - -1] = protocoltype;
            protocoltype.w_1484_f.forEach((p_229713_1_, p_229713_2_) -> p_229713_2_.n_1700_B().forEach(p_229712_1_ -> {
                if (u_1723_Y.containsKey(p_229712_1_) && u_1723_Y.get(p_229712_1_) != protocoltype) {
                    throw new IllegalStateException("Packet " + String.valueOf(p_229712_1_) + " is already assigned to protocol " + String.valueOf((Object)u_1723_Y.get(p_229712_1_)) + " - can't reassign to " + String.valueOf((Object)protocoltype));
                }
                u_1723_Y.put((Class<Packet<?>>)p_229712_1_, protocoltype);
            }));
        }
    }

    static class J_1907_R {
        private final Map<a_3942_s, n_1700_B<?>> n_1700_B = Maps.newEnumMap(a_3942_s.class);

        private J_1907_R() {
        }

        public <T extends particlesParticleOptions> J_1907_R n_1700_B(a_3942_s p_229724_1_, n_1700_B<T> p_229724_2_) {
            this.n_1700_B.put(p_229724_1_, p_229724_2_);
            return this;
        }
    }

    static class n_1700_B<T extends particlesParticleOptions> {
        private final Object2IntMap<Class<? extends Packet<T>>> n_1700_B = (Object2IntMap)j_3341_s.n_1700_B(new Object2IntOpenHashMap(), p_229719_0_ -> p_229719_0_.defaultReturnValue(-1));
        private final List<Supplier<? extends Packet<T>>> J_1907_R = Lists.newArrayList();

        private n_1700_B() {
        }

        public <P extends Packet<T>> n_1700_B<T> n_1700_B(Class<P> p_229721_1_, Supplier<P> p_229721_2_) {
            int i = this.J_1907_R.size();
            int j = this.n_1700_B.put(p_229721_1_, i);
            if (j != -1) {
                String s = "Packet " + String.valueOf(p_229721_1_) + " is already registered to ID " + j;
                LogManager.getLogger().fatal(s);
                throw new IllegalArgumentException(s);
            }
            this.J_1907_R.add(p_229721_2_);
            return this;
        }

        @Nullable
        public Integer n_1700_B(Class<?> p_229720_1_) {
            int i = this.n_1700_B.getInt(p_229720_1_);
            return i == -1 ? null : Integer.valueOf(i);
        }

        @Nullable
        public Packet<?> n_1700_B(int p_229718_1_) {
            Supplier<Packet<T>> supplier = this.J_1907_R.get(p_229718_1_);
            return supplier != null ? supplier.get() : null;
        }

        public Iterable<Class<? extends Packet<?>>> n_1700_B() {
            return Iterables.unmodifiableIterable((Iterable)this.n_1700_B.keySet());
        }
    }
}


