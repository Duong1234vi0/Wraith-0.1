/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_1557_z;
import lightning.product.ClientboundContainerSetContentPacket;
import lightning.product.B_3790_C;
import lightning.product.C_1375_J;
import lightning.product.D_1056_N;
import lightning.product.ClientboundSetCameraPacket;
import lightning.product.ClientboundSetObjectivePacket;
import lightning.product.E_3520_U;
import lightning.product.ClientboundBlockBreakAckPacket;
import lightning.product.F_551_J;
import lightning.product.G_4919_s;
import lightning.product.ClientboundChangeDifficultyPacket;
import lightning.product.I_4656_k;
import lightning.product.ClientboundLoginPacket;
import lightning.product.ClientboundGameEventPacket;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.ClientboundCustomSoundPacket;
import lightning.product.ClientboundTeleportEntityPacket;
import lightning.product.ClientboundRespawnPacket;
import lightning.product.ClientboundCommandSuggestionsPacket;
import lightning.product.ClientboundLightUpdatePacket;
import lightning.product.N_3369_p;
import lightning.product.N_4422_X;
import lightning.product.ClientboundPlayerAbilitiesPacket;
import lightning.product.ClientboundSetTitlesPacket;
import lightning.product.ClientboundUpdateAdvancementsPacket;
import lightning.product.ClientboundBlockUpdatePacket;
import lightning.product.ClientboundTagQueryPacket;
import lightning.product.ClientboundAddPaintingPacket;
import lightning.product.ClientboundSectionBlocksUpdatePacket;
import lightning.product.ClientboundAddMobPacket;
import lightning.product.ClientboundOpenScreenPacket;
import lightning.product.ClientboundStopSoundPacket;
import lightning.product.ClientboundUpdateAttributesPacket;
import lightning.product.R_831_p;
import lightning.product.ClientboundSetDefaultSpawnPositionPacket;
import lightning.product.U_157_Y;
import lightning.product.W_1158_a;
import lightning.product.ClientboundPlayerLookAtPacket;
import lightning.product.ClientboundSetHealthPacket;
import lightning.product.ClientboundAddPlayerPacket;
import lightning.product.W_4148_E;
import lightning.product.ClientboundSoundPacket;
import lightning.product.X_508_u;
import lightning.product.X_776_r;
import lightning.product.X_821_u;
import lightning.product.ClientboundSetEquipmentPacket;
import lightning.product.Z_390_O;
import lightning.product.ClientboundRemoveMobEffectPacket;
import lightning.product.ClientboundSelectAdvancementsTabPacket;
import lightning.product.ClientboundSetExperiencePacket;
import lightning.product.ClientboundMerchantOffersPacket;
import lightning.product.ClientboundBlockEventPacket;
import lightning.product.a_4762_y;
import lightning.product.a_4764_N;
import lightning.product.ClientboundPlayerPositionPacket;
import lightning.product.ClientboundSetPassengersPacket;
import lightning.product.d_338_B;
import lightning.product.e_446_u;
import lightning.product.ClientboundCustomPayloadPacket;
import lightning.product.ClientboundLevelParticlesPacket;
import lightning.product.ClientboundExplodePacket;
import lightning.product.ClientboundAwardStatsPacket;
import lightning.product.ClientboundSetPlayerTeamPacket;
import lightning.product.ClientboundAddExperienceOrbPacket;
import lightning.product.ClientboundChatPacket;
import lightning.product.ClientboundRecipePacket;
import lightning.product.m_1761_s;
import lightning.product.ClientboundSetEntityMotionPacket;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.ClientboundPlayerCombatPacket;
import lightning.product.n_2740_g;
import lightning.product.ClientboundRemoveEntitiesPacket;
import lightning.product.n_4563_y;
import lightning.product.p_198_K;
import lightning.product.ClientboundSetTimePacket;
import lightning.product.q_3092_O;
import lightning.product.ClientboundUpdateMobEffectPacket;
import lightning.product.r_1873_a;
import lightning.product.ClientboundLevelEventPacket;
import lightning.product.ClientboundSetDisplayObjectivePacket;
import lightning.product.t_3906_J;
import lightning.product.t_4503_H;
import lightning.product.v_4727_z;
import lightning.product.ClientboundSetScorePacket;
import lightning.product.w_3005_z;
import lightning.product.w_690_m;
import lightning.product.x_2680_y;
import lightning.product.particlesParticleOptions;
import lightning.product.ClientboundSoundEntityPacket;

public interface ClientGamePacketListener
extends particlesParticleOptions {
    public void n_1700_B(ClientboundAddEntityPacket var1);

    public void n_1700_B(ClientboundAddExperienceOrbPacket var1);

    public void n_1700_B(ClientboundAddMobPacket var1);

    public void n_1700_B(ClientboundSetObjectivePacket var1);

    public void n_1700_B(ClientboundAddPaintingPacket var1);

    public void n_1700_B(ClientboundAddPlayerPacket var1);

    public void n_1700_B(q_3092_O var1);

    public void n_1700_B(ClientboundAwardStatsPacket var1);

    public void n_1700_B(ClientboundRecipePacket var1);

    public void n_1700_B(t_4503_H var1);

    public void n_1700_B(w_3005_z var1);

    public void n_1700_B(ClientboundBlockEntityDataPacket var1);

    public void n_1700_B(ClientboundBlockEventPacket var1);

    public void n_1700_B(ClientboundBlockUpdatePacket var1);

    public void n_1700_B(ClientboundChatPacket var1);

    public void n_1700_B(ClientboundSectionBlocksUpdatePacket var1);

    public void n_1700_B(N_3369_p var1);

    public void n_1700_B(n_2740_g var1);

    public void n_1700_B(v_4727_z var1);

    public void n_1700_B(ClientboundContainerSetContentPacket var1);

    public void n_1700_B(t_3906_J var1);

    public void n_1700_B(X_821_u var1);

    public void n_1700_B(a_4764_N var1);

    public void n_1700_B(ClientboundCustomPayloadPacket var1);

    public void n_1700_B(w_690_m var1);

    public void n_1700_B(C_1375_J var1);

    public void n_1700_B(e_446_u var1);

    public void n_1700_B(ClientboundSetPassengersPacket var1);

    public void n_1700_B(ClientboundExplodePacket var1);

    public void n_1700_B(ClientboundGameEventPacket var1);

    public void n_1700_B(r_1873_a var1);

    public void n_1700_B(U_157_Y var1);

    public void n_1700_B(W_4148_E var1);

    public void n_1700_B(ClientboundLevelEventPacket var1);

    public void n_1700_B(ClientboundLoginPacket var1);

    public void n_1700_B(N_4422_X var1);

    public void n_1700_B(ClientboundPlayerPositionPacket var1);

    public void n_1700_B(ClientboundLevelParticlesPacket var1);

    public void n_1700_B(ClientboundPlayerAbilitiesPacket var1);

    public void n_1700_B(d_338_B var1);

    public void n_1700_B(ClientboundRemoveEntitiesPacket var1);

    public void n_1700_B(ClientboundRemoveMobEffectPacket var1);

    public void n_1700_B(ClientboundRespawnPacket var1);

    public void n_1700_B(X_776_r var1);

    public void n_1700_B(Z_390_O var1);

    public void n_1700_B(ClientboundSetDisplayObjectivePacket var1);

    public void n_1700_B(a_4762_y var1);

    public void n_1700_B(ClientboundSetEntityMotionPacket var1);

    public void n_1700_B(ClientboundSetEquipmentPacket var1);

    public void n_1700_B(ClientboundSetExperiencePacket var1);

    public void n_1700_B(ClientboundSetHealthPacket var1);

    public void n_1700_B(ClientboundSetPlayerTeamPacket var1);

    public void n_1700_B(ClientboundSetScorePacket var1);

    public void n_1700_B(ClientboundSetDefaultSpawnPositionPacket var1);

    public void n_1700_B(ClientboundSetTimePacket var1);

    public void n_1700_B(ClientboundSoundPacket var1);

    public void n_1700_B(ClientboundSoundEntityPacket var1);

    public void n_1700_B(ClientboundCustomSoundPacket var1);

    public void n_1700_B(X_508_u var1);

    public void n_1700_B(ClientboundTeleportEntityPacket var1);

    public void n_1700_B(ClientboundUpdateAttributesPacket var1);

    public void n_1700_B(ClientboundUpdateMobEffectPacket var1);

    public void n_1700_B(I_4656_k var1);

    public void n_1700_B(ClientboundPlayerCombatPacket var1);

    public void n_1700_B(ClientboundChangeDifficultyPacket var1);

    public void n_1700_B(ClientboundSetCameraPacket var1);

    public void n_1700_B(R_831_p var1);

    public void n_1700_B(ClientboundSetTitlesPacket var1);

    public void n_1700_B(D_1056_N var1);

    public void n_1700_B(E_3520_U var1);

    public void n_1700_B(m_1761_s var1);

    public void n_1700_B(G_4919_s var1);

    public void n_1700_B(F_551_J var1);

    public void n_1700_B(ClientboundUpdateAdvancementsPacket var1);

    public void n_1700_B(ClientboundSelectAdvancementsTabPacket var1);

    public void n_1700_B(W_1158_a var1);

    public void n_1700_B(B_3790_C var1);

    public void n_1700_B(ClientboundStopSoundPacket var1);

    public void n_1700_B(ClientboundCommandSuggestionsPacket var1);

    public void n_1700_B(A_1557_z var1);

    public void n_1700_B(ClientboundPlayerLookAtPacket var1);

    public void n_1700_B(ClientboundTagQueryPacket var1);

    public void n_1700_B(ClientboundLightUpdatePacket var1);

    public void n_1700_B(x_2680_y var1);

    public void n_1700_B(ClientboundOpenScreenPacket var1);

    public void n_1700_B(ClientboundMerchantOffersPacket var1);

    public void n_1700_B(n_4563_y var1);

    public void n_1700_B(p_198_K var1);

    public void n_1700_B(ClientboundBlockBreakAckPacket var1);
}


