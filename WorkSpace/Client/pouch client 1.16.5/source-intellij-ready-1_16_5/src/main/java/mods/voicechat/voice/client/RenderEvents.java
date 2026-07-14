/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.client;

import java.util.UUID;
import lightning.product.D_4792_h;
import lightning.product.FormattedText;
import lightning.product.N_4263_v;
import lightning.product.Z_3224_L;
import lightning.product.a_3913_L;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.x_282_a;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.onboarding.OnboardingManager;
import mods.voicechat.intercompatibility.ClientCompatibilityManager;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientPlayerStateManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.client.GroupChatManager;
import mods.voicechat.voice.client.MicrophoneActivationType;

public class RenderEvents {
    private static final g_2336_b MICROPHONE_ICON = new g_2336_b("voicechat/textures/icons/microphone.png");
    private static final g_2336_b WHISPER_MICROPHONE_ICON = new g_2336_b("voicechat/textures/icons/microphone_whisper.png");
    private static final g_2336_b MICROPHONE_OFF_ICON = new g_2336_b("voicechat/textures/icons/microphone_off.png");
    private static final g_2336_b SPEAKER_ICON = new g_2336_b("voicechat/textures/icons/speaker.png");
    private static final g_2336_b WHISPER_SPEAKER_ICON = new g_2336_b("voicechat/textures/icons/speaker_whisper.png");
    private static final g_2336_b SPEAKER_OFF_ICON = new g_2336_b("voicechat/textures/icons/speaker_off.png");
    private static final g_2336_b DISCONNECT_ICON = new g_2336_b("voicechat/textures/icons/disconnected.png");
    private static final g_2336_b GROUP_ICON = new g_2336_b("voicechat/textures/icons/group.png");
    private final MinecraftClient minecraft = MinecraftClient.A_4115_X();

    public RenderEvents() {
        ClientCompatibilityManager.INSTANCE.onRenderNamePlate(this::onRenderName);
        ClientCompatibilityManager.INSTANCE.onRenderHUD(this::onRenderHUD);
    }

    private void onRenderHUD(g_221_o stack, float tickDelta) {
        if (!this.shouldShowIcons()) {
            return;
        }
        if (((Boolean)VoicechatClient.CLIENT_CONFIG.hideIcons.get()).booleanValue()) {
            return;
        }
        ClientPlayerStateManager manager = ClientManager.getPlayerStateManager();
        ClientVoicechat client = ClientManager.getClient();
        if (manager.isDisconnected() && this.isStartup()) {
            return;
        }
        if (manager.isDisconnected()) {
            this.renderIcon(stack, DISCONNECT_ICON);
        } else if (manager.isDisabled()) {
            this.renderIcon(stack, SPEAKER_OFF_ICON);
        } else if (manager.isMuted() && ((MicrophoneActivationType)((Object)VoicechatClient.CLIENT_CONFIG.microphoneActivationType.get())).equals((Object)MicrophoneActivationType.VOICE)) {
            this.renderIcon(stack, MICROPHONE_OFF_ICON);
        } else if (client != null && client.getMicThread() != null) {
            if (client.getMicThread().isWhispering()) {
                this.renderIcon(stack, WHISPER_MICROPHONE_ICON);
            } else if (client.getMicThread().isTalking()) {
                this.renderIcon(stack, MICROPHONE_ICON);
            }
        }
        if (manager.getGroupID() != null && ((Boolean)VoicechatClient.CLIENT_CONFIG.showGroupHud.get()).booleanValue()) {
            GroupChatManager.renderIcons(stack);
        }
    }

    private boolean isStartup() {
        ClientVoicechat client = ClientManager.getClient();
        return client != null && System.currentTimeMillis() - client.getStartTime() < 5000L;
    }

    private void renderIcon(g_221_o matrixStack, g_2336_b texture) {
        matrixStack.n_1700_B();
        this.minecraft.G_624_v().n_1700_B(texture);
        int posX = (Integer)VoicechatClient.CLIENT_CONFIG.hudIconPosX.get();
        int posY = (Integer)VoicechatClient.CLIENT_CONFIG.hudIconPosY.get();
        if (posX < 0) {
            matrixStack.n_1700_B((double)this.minecraft.RealmsServerPing().Q_4569_t(), 0.0, 0.0);
        }
        if (posY < 0) {
            matrixStack.n_1700_B(0.0, (double)this.minecraft.RealmsServerPing().M_182_A(), 0.0);
        }
        matrixStack.n_1700_B((double)posX, (double)posY, 0.0);
        float scale = ((Double)VoicechatClient.CLIENT_CONFIG.hudIconScale.get()).floatValue();
        matrixStack.n_1700_B(scale, scale, 1.0f);
        k_2603_m.blit(matrixStack, posX < 0 ? -16 : 0, posY < 0 ? -16 : 0, 0.0f, 0.0f, 16, 16, 16, 16);
        matrixStack.J_1907_R();
    }

    private void onRenderName(N_4263_v entity, x_282_a component, g_221_o stack, o_3091_w vertexConsumers, int light) {
        if (component == null) {
            return;
        }
        if (!this.shouldShowIcons()) {
            return;
        }
        if (((Boolean)VoicechatClient.CLIENT_CONFIG.hideIcons.get()).booleanValue()) {
            return;
        }
        if (!(entity instanceof a_3913_L)) {
            return;
        }
        a_3913_L player = (a_3913_L)entity;
        if (entity == this.minecraft.Y_259_p) {
            return;
        }
        if (!this.minecraft.P_4830_p.RetryCallException) {
            ClientPlayerStateManager manager = ClientManager.getPlayerStateManager();
            ClientVoicechat client = ClientManager.getClient();
            UUID groupId = manager.getGroup(player);
            if (client != null && client.getTalkCache().isWhispering(player)) {
                this.renderPlayerIcon(player, component, WHISPER_SPEAKER_ICON, stack, vertexConsumers, light);
            } else if (client != null && client.getTalkCache().isTalking(player)) {
                this.renderPlayerIcon(player, component, SPEAKER_ICON, stack, vertexConsumers, light);
            } else if (manager.isPlayerDisconnected(player)) {
                this.renderPlayerIcon(player, component, DISCONNECT_ICON, stack, vertexConsumers, light);
            } else if (groupId != null && !groupId.equals(manager.getGroupID())) {
                this.renderPlayerIcon(player, component, GROUP_ICON, stack, vertexConsumers, light);
            } else if (manager.isPlayerDisabled(player)) {
                this.renderPlayerIcon(player, component, SPEAKER_OFF_ICON, stack, vertexConsumers, light);
            }
        }
    }

    private void renderPlayerIcon(a_3913_L player, x_282_a component, g_2336_b texture, g_221_o matrixStackIn, o_3091_w buffer, int light) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(0.0, (double)player.v_165_F() + 0.5, 0.0);
        matrixStackIn.n_1700_B(this.minecraft.O_508_d().R_4764_Y());
        matrixStackIn.n_1700_B(-0.025f, -0.025f, 0.025f);
        matrixStackIn.n_1700_B(0.0, -1.0, 0.0);
        float offset = this.minecraft.t_148_a.n_1700_B((FormattedText)component) / 2 + 2;
        D_4792_h builder = buffer.getBuffer(o_2576_A.M_182_A(texture));
        int alpha = 32;
        if (player.U_1341_G()) {
            RenderEvents.vertex(builder, matrixStackIn, offset, 10.0f, 0.0f, 0.0f, 1.0f, alpha, light);
            RenderEvents.vertex(builder, matrixStackIn, offset + 10.0f, 10.0f, 0.0f, 1.0f, 1.0f, alpha, light);
            RenderEvents.vertex(builder, matrixStackIn, offset + 10.0f, 0.0f, 0.0f, 1.0f, 0.0f, alpha, light);
            RenderEvents.vertex(builder, matrixStackIn, offset, 0.0f, 0.0f, 0.0f, 0.0f, alpha, light);
        } else {
            RenderEvents.vertex(builder, matrixStackIn, offset, 10.0f, 0.0f, 0.0f, 1.0f, light);
            RenderEvents.vertex(builder, matrixStackIn, offset + 10.0f, 10.0f, 0.0f, 1.0f, 1.0f, light);
            RenderEvents.vertex(builder, matrixStackIn, offset + 10.0f, 0.0f, 0.0f, 1.0f, 0.0f, light);
            RenderEvents.vertex(builder, matrixStackIn, offset, 0.0f, 0.0f, 0.0f, 0.0f, light);
            D_4792_h builderSeeThrough = buffer.getBuffer(o_2576_A.t_1786_h(texture));
            RenderEvents.vertex(builderSeeThrough, matrixStackIn, offset, 10.0f, 0.0f, 0.0f, 1.0f, alpha, light);
            RenderEvents.vertex(builderSeeThrough, matrixStackIn, offset + 10.0f, 10.0f, 0.0f, 1.0f, 1.0f, alpha, light);
            RenderEvents.vertex(builderSeeThrough, matrixStackIn, offset + 10.0f, 0.0f, 0.0f, 1.0f, 0.0f, alpha, light);
            RenderEvents.vertex(builderSeeThrough, matrixStackIn, offset, 0.0f, 0.0f, 0.0f, 0.0f, alpha, light);
        }
        matrixStackIn.J_1907_R();
    }

    private boolean shouldShowIcons() {
        if (OnboardingManager.isOnboarding()) {
            return false;
        }
        if (ClientManager.getClient() != null && ClientManager.getClient().getConnection() != null && ClientManager.getClient().getConnection().isInitialized()) {
            return true;
        }
        return this.minecraft.n_3318_d() == null || this.minecraft.n_3318_d().RealmsClientConfig();
    }

    private static void vertex(D_4792_h builder, g_221_o matrixStack, float x, float y, float z, float u, float v, int light) {
        RenderEvents.vertex(builder, matrixStack, x, y, z, u, v, 255, light);
    }

    private static void vertex(D_4792_h builder, g_221_o matrixStack, float x, float y, float z, float u, float v, int alpha, int light) {
        g_221_o.n_1700_B entry = matrixStack.R_4764_Y();
        builder.n_1700_B(entry.n_1700_B(), x, y, z).color(255, 255, 255, alpha).tex(u, v).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(light).n_1700_B(entry.J_1907_R(), 0.0f, 0.0f, -1.0f).endVertex();
    }
}



