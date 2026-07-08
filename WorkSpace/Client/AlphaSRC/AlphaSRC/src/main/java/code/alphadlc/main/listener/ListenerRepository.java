/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.main.listener;

import code.alphadlc.AlphaDLC;
import code.alphadlc.emotions.EmotionWheelManager;
import code.alphadlc.features.impl.render.ItemReplacerSyncManager;
import code.alphadlc.features.impl.render.customcape.CapeSyncManager;
import code.alphadlc.features.impl.render.custommodels.CustomModelSyncManager;
import code.alphadlc.irc.IrcManager;
import code.alphadlc.main.listener.Listener;
import code.alphadlc.main.listener.impl.EventListener;
import code.alphadlc.utils.client.managers.event.EventManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListenerRepository {
    private final List<Listener> listeners = new ArrayList<Listener>();

    public void setup() {
        this.registerListeners(new EventListener(), EmotionWheelManager.create(), CustomModelSyncManager.create(), ItemReplacerSyncManager.create(), CapeSyncManager.create(), IrcManager.create());
    }

    public void registerListeners(Listener ... listeners) {
        this.listeners.addAll(List.of(listeners));
        Arrays.stream(listeners).forEach(listener -> {
            AlphaDLC.getInstance().getEventManager();
            EventManager.register(listener);
        });
    }

    public List<Listener> getListeners() {
        return this.listeners;
    }
}
