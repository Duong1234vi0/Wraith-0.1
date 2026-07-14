/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.intercompatibility;

import java.net.SocketAddress;
import java.util.function.Consumer;
import lightning.product.D_590_W;
import lightning.product.PackRepository;
import lightning.product.I_2946_k;
import lightning.product.N_4263_v;
import lightning.product.Q_4113_P;
import lightning.product.c_1633_k;
import lightning.product.g_221_o;
import lightning.product.o_3091_w;
import lightning.product.x_282_a;
import mods.voicechat.api.VoicechatClientApi;
import mods.voicechat.plugins.impl.VoicechatClientApiImpl;
import mods.voicechat.service.Service;
import mods.voicechat.voice.client.ClientVoicechatConnection;

public abstract class ClientCompatibilityManager {
    public static ClientCompatibilityManager INSTANCE = Service.get(ClientCompatibilityManager.class);

    public abstract void onRenderNamePlate(RenderNameplateEvent var1);

    public abstract void onRenderHUD(RenderHUDEvent var1);

    public abstract void onKeyboardEvent(KeyboardEvent var1);

    public abstract void onMouseEvent(MouseEvent var1);

    public abstract void onClientTick(Runnable var1);

    public abstract Q_4113_P.n_1700_B getBoundKeyOf(D_590_W var1);

    public abstract void onHandleKeyBinds(Runnable var1);

    public abstract D_590_W registerKeyBinding(D_590_W var1);

    public abstract void emitVoiceChatConnectedEvent(ClientVoicechatConnection var1);

    public abstract void emitVoiceChatDisconnectedEvent();

    public abstract void onVoiceChatConnected(Consumer<ClientVoicechatConnection> var1);

    public abstract void onVoiceChatDisconnected(Runnable var1);

    public abstract void onDisconnect(Runnable var1);

    public abstract void onJoinWorld(Runnable var1);

    public abstract void onPublishServer(Consumer<Integer> var1);

    public abstract SocketAddress getSocketAddress(c_1633_k var1);

    public abstract void addResourcePackSource(PackRepository var1, I_2946_k var2);

    public VoicechatClientApi getClientApi() {
        return VoicechatClientApiImpl.INSTANCE;
    }

    public static interface MouseEvent {
        public void onMouseEvent(long var1, int var3, int var4, int var5);
    }

    public static interface KeyboardEvent {
        public void onKeyboardEvent(long var1, int var3, int var4);
    }

    public static interface RenderHUDEvent {
        public void render(g_221_o var1, float var2);
    }

    public static interface RenderNameplateEvent {
        public void render(N_4263_v var1, x_282_a var2, g_221_o var3, o_3091_w var4, int var5);
    }
}


