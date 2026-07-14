/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import lightning.product.Tuple;
import mods.voicechat.api.events.Event;

public class EventBuilder {
    private final Map<Class<? extends Event>, List<Tuple<Integer, Consumer<? extends Event>>>> events = new HashMap<Class<? extends Event>, List<Tuple<Integer, Consumer<? extends Event>>>>();

    private EventBuilder() {
    }

    public <T extends Event> EventBuilder addEvent(Class<T> eventClass, Consumer<T> event, int priority) {
        List eventList = this.events.getOrDefault(eventClass, new ArrayList());
        eventList.add(new Tuple<Integer, Consumer<T>>(priority, event));
        this.events.put(eventClass, eventList);
        return this;
    }

    public Map<Class<? extends Event>, List<Consumer<? extends Event>>> build() {
        HashMap<Class<? extends Event>, List<Consumer<? extends Event>>> result = new HashMap<Class<? extends Event>, List<Consumer<? extends Event>>>();
        for (Map.Entry<Class<? extends Event>, List<Tuple<Integer, Consumer<? extends Event>>>> entry : this.events.entrySet()) {
            result.put(entry.getKey(), entry.getValue().stream().sorted((o1, o2) -> Integer.compare((Integer)o2.n_1700_B(), (Integer)o1.n_1700_B())).map(Tuple::J_1907_R).collect(Collectors.toList()));
        }
        return result;
    }

    public static EventBuilder create() {
        return new EventBuilder();
    }
}


