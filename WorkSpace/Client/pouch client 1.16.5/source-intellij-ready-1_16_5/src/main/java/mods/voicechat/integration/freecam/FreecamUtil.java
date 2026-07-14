/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.integration.freecam;

import lightning.product.MinecraftClient;
import lightning.product.e_2866_D;
import mods.voicechat.VoicechatClient;
import mods.voicechat.integration.freecam.FreecamMode;
import mods.voicechat.voice.client.PositionalAudioUtils;

public class FreecamUtil {
    private static final MinecraftClient mc = MinecraftClient.A_4115_X();

    public static boolean isFreecamEnabled() {
        if (FreecamUtil.mc.Y_259_p == null) {
            return false;
        }
        return ((FreecamMode)((Object)VoicechatClient.CLIENT_CONFIG.freecamMode.get())).equals((Object)FreecamMode.PLAYER) && !FreecamUtil.mc.Y_259_p.d_2461_k() && !FreecamUtil.mc.Y_259_p.equals(mc.g_2268_R());
    }

    public static e_2866_D getReferencePoint() {
        if (FreecamUtil.mc.Y_259_p == null) {
            return e_2866_D.n_1700_B;
        }
        return FreecamUtil.isFreecamEnabled() ? FreecamUtil.mc.Y_259_p.u_2550_I(1.0f) : FreecamUtil.mc.s_956_w.M_588_G().J_1907_R();
    }

    public static double getDistanceTo(e_2866_D pos) {
        return FreecamUtil.getReferencePoint().u_1723_Y(pos);
    }

    public static float getDistanceVolume(float maxDistance, e_2866_D pos) {
        return PositionalAudioUtils.getDistanceVolume(maxDistance, FreecamUtil.getReferencePoint(), pos);
    }
}


