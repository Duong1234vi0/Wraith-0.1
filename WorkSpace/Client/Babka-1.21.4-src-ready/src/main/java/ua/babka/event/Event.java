package ua.babka.event;

import ua.babka.Babka;

public class Event {
    private boolean cancelled;

    public void post() {
        Babka.getInstance().getEventBus().post((Object)this);
    }

    public void cancelEvent() {
        this.setCancelled(true);
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (!(o2 instanceof Event)) {
            return false;
        }
        Event other = (Event)o2;
        if (!other.canEqual(this)) {
            return false;
        }
        return this.isCancelled() == other.isCancelled();
    }

    protected boolean canEqual(Object other) {
        return other instanceof Event;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.isCancelled() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "Event(cancelled=" + this.isCancelled() + ")";
    }
}

