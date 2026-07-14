/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.intercompatibility;

import java.net.SocketAddress;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import lightning.product.A_4115_X;
import lightning.product.D_590_W;
import lightning.product.PackRepository;
import lightning.product.I_2946_k;
import lightning.product.Q_4113_P;
import lightning.product.R_3197_Z;
import lightning.product.Y_1740_V;
import lightning.product.b_3528_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1633_k;
import mods.voicechat.eventforge.ClientPlayerNetworkEvent;
import mods.voicechat.eventforge.InputEvent;
import mods.voicechat.eventforge.RenderNameplateEvent;
import mods.voicechat.eventforge.TickEvent;
import mods.voicechat.eventforge.WorldEvent;
import mods.voicechat.events.ClientVoiceChatConnectedEvent;
import mods.voicechat.events.ClientVoiceChatDisconnectedEvent;
import mods.voicechat.intercompatibility.ClientCompatibilityManager;
import mods.voicechat.voice.client.ClientVoicechatConnection;

public class ForgeClientCompatibilityManager
extends ClientCompatibilityManager {
    private final MinecraftClient minecraft = MinecraftClient.A_4115_X();
    private final List<ClientCompatibilityManager.RenderNameplateEvent> renderNameplateEvents = new CopyOnWriteArrayList<ClientCompatibilityManager.RenderNameplateEvent>();
    private final List<ClientCompatibilityManager.RenderHUDEvent> renderHUDEvents = new CopyOnWriteArrayList<ClientCompatibilityManager.RenderHUDEvent>();
    private final List<ClientCompatibilityManager.KeyboardEvent> keyboardEvents = new CopyOnWriteArrayList<ClientCompatibilityManager.KeyboardEvent>();
    private final List<ClientCompatibilityManager.MouseEvent> mouseEvents = new CopyOnWriteArrayList<ClientCompatibilityManager.MouseEvent>();
    private final List<Runnable> clientTickEvents = new CopyOnWriteArrayList<Runnable>();
    private final List<Runnable> inputEvents = new CopyOnWriteArrayList<Runnable>();
    private final List<Runnable> disconnectEvents = new CopyOnWriteArrayList<Runnable>();
    private final List<Runnable> joinWorldEvents = new CopyOnWriteArrayList<Runnable>();
    private final List<Consumer<ClientVoicechatConnection>> voicechatConnectEvents = new CopyOnWriteArrayList<Consumer<ClientVoicechatConnection>>();
    private final List<Runnable> voicechatDisconnectEvents = new CopyOnWriteArrayList<Runnable>();
    private final List<Consumer<Integer>> publishServerEvents = new CopyOnWriteArrayList<Consumer<Integer>>();
    private boolean wasPublished;

    @Y_1740_V
    public void onRenderName(RenderNameplateEvent event) {
        this.renderNameplateEvents.forEach(renderNameplateEvent -> renderNameplateEvent.render(event.getEntity(), event.getContent(), event.getMatrixStack(), event.getRenderTypeBuffer(), event.getPackedLight()));
        if (this.minecraft.Y_259_p == null || event.getEntity().a_(this.minecraft.Y_259_p)) {
            return;
        }
        this.renderNameplateEvents.forEach(renderNameplateEvent -> renderNameplateEvent.render(event.getEntity(), event.getContent(), event.getMatrixStack(), event.getRenderTypeBuffer(), event.getPackedLight()));
    }

    @Y_1740_V
    public void onRenderOverlay(b_3528_u.R_4764_Y event) {
        this.renderHUDEvents.forEach(renderHUDEvent -> renderHUDEvent.render(event.J_1907_R(), event.R_4764_Y()));
    }

    @Y_1740_V
    public void onKey(InputEvent.KeyInputEvent event) {
        this.keyboardEvents.forEach(keyboardEvent -> keyboardEvent.onKeyboardEvent(this.minecraft.RealmsServerPing().t_148_a(), event.getKey(), event.getScanCode()));
    }

    @Y_1740_V
    public void onMouse(InputEvent.RawMouseEvent event) {
        this.mouseEvents.forEach(mouseEvent -> mouseEvent.onMouseEvent(this.minecraft.RealmsServerPing().t_148_a(), event.getButton(), event.getAction(), event.getMods()));
    }

    @Y_1740_V
    public void onKeyInput(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }
        this.clientTickEvents.forEach(Runnable::run);
    }

    @Y_1740_V
    public void onInput(TickEvent.ClientTickEvent event) {
        this.inputEvents.forEach(Runnable::run);
    }

    @Y_1740_V
    public void onDisconnect(WorldEvent.Unload event) {
        if (this.minecraft.w_1457_N == null) {
            this.disconnectEvents.forEach(Runnable::run);
        }
    }

    @Y_1740_V
    public void onJoinServer(ClientPlayerNetworkEvent.LoggedInEvent event) {
        if (event.getPlayer() != this.minecraft.Y_259_p) {
            return;
        }
        this.joinWorldEvents.forEach(Runnable::run);
    }

    @Y_1740_V
    public void onServer(TickEvent.ServerTickEvent event) {
        if (!event.phase.equals((Object)TickEvent.Phase.END)) {
            return;
        }
        R_3197_Z server = MinecraftClient.A_4115_X().n_3318_d();
        if (server == null) {
            return;
        }
        boolean published = server.RealmsClientConfig();
        if (published && !this.wasPublished) {
            this.publishServerEvents.forEach(portConsumer -> portConsumer.accept(server.d_2461_k()));
        }
        this.wasPublished = published;
    }

    @Override
    public void onRenderNamePlate(ClientCompatibilityManager.RenderNameplateEvent onRenderNamePlate) {
        this.renderNameplateEvents.add(onRenderNamePlate);
    }

    @Override
    public void onRenderHUD(ClientCompatibilityManager.RenderHUDEvent onRenderHUD) {
        this.renderHUDEvents.add(onRenderHUD);
    }

    @Override
    public void onKeyboardEvent(ClientCompatibilityManager.KeyboardEvent onKeyboardEvent) {
        this.keyboardEvents.add(onKeyboardEvent);
    }

    @Override
    public void onMouseEvent(ClientCompatibilityManager.MouseEvent onMouseEvent) {
        this.mouseEvents.add(onMouseEvent);
    }

    @Override
    public void onClientTick(Runnable onClientTick) {
        this.clientTickEvents.add(onClientTick);
    }

    @Override
    public Q_4113_P.n_1700_B getBoundKeyOf(D_590_W keyBinding) {
        return keyBinding.getKey();
    }

    @Override
    public void onHandleKeyBinds(Runnable onHandleKeyBinds) {
        this.inputEvents.add(onHandleKeyBinds);
    }

    @Override
    public D_590_W registerKeyBinding(D_590_W keyBinding) {
        return keyBinding;
    }

    @Override
    public void emitVoiceChatConnectedEvent(ClientVoicechatConnection client) {
        this.voicechatConnectEvents.forEach(consumer -> consumer.accept(client));
        A_4115_X.n_1700_B(new ClientVoiceChatConnectedEvent(client));
    }

    @Override
    public void emitVoiceChatDisconnectedEvent() {
        this.voicechatDisconnectEvents.forEach(Runnable::run);
        A_4115_X.n_1700_B(new ClientVoiceChatDisconnectedEvent());
    }

    @Override
    public void onVoiceChatConnected(Consumer<ClientVoicechatConnection> onVoiceChatConnected) {
        this.voicechatConnectEvents.add(onVoiceChatConnected);
    }

    @Override
    public void onVoiceChatDisconnected(Runnable onVoiceChatDisconnected) {
        this.voicechatDisconnectEvents.add(onVoiceChatDisconnected);
    }

    @Override
    public void onDisconnect(Runnable onDisconnect) {
        this.disconnectEvents.add(onDisconnect);
    }

    @Override
    public void onJoinWorld(Runnable onJoinWorld) {
        this.joinWorldEvents.add(onJoinWorld);
    }

    @Override
    public void onPublishServer(Consumer<Integer> onPublishServer) {
        this.publishServerEvents.add(onPublishServer);
    }

    @Override
    public SocketAddress getSocketAddress(c_1633_k connection) {
        return connection.h_1847_R().remoteAddress();
    }

    @Override
    public void addResourcePackSource(PackRepository packRepository, I_2946_k repositorySource) {
    }
}



