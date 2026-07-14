package ua.babka.event;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import ua.babka.event.Event;

public class EventHUD
extends Event {
    private final DrawContext drawContext;
    private final RenderTickCounter renderTickCounter;

    public DrawContext getDrawContext() {
        return this.drawContext;
    }

    public RenderTickCounter getRenderTickCounter() {
        return this.renderTickCounter;
    }

    public EventHUD(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        this.drawContext = drawContext;
        this.renderTickCounter = renderTickCounter;
    }
}

