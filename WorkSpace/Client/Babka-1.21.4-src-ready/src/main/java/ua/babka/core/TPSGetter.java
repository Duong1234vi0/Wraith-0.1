package ua.babka.core;

import com.google.common.eventbus.Subscribe;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.math.MathHelper;
import ua.babka.Babka;
import ua.babka.event.EventPacket;

public class TPSGetter {
    private final Queue<Float> tpsHistory = new LinkedList<Float>();
    private float TPS = 20.0f;
    private float averageTPS = 20.0f;
    private float adjustTicks = 0.0f;
    private long timestamp;

    public TPSGetter() {
        Babka.getInstance().getEventBus().register((Object)this);
    }

    @Subscribe
    private void onPacket(EventPacket e2) {
        if (e2.getPacket() != null && e2.getPacket() instanceof WorldTimeUpdateS2CPacket) {
            this.updateTPS();
        }
    }

    private void updateTPS() {
        long delay = System.nanoTime() - this.timestamp;
        float maxTPS = 20.0f;
        float rawTPS = maxTPS * (1.0E9f / (float)delay);
        float boundedTPS = MathHelper.clamp((float)rawTPS, (float)0.0f, (float)maxTPS);
        this.TPS = (float)this.round(boundedTPS);
        this.adjustTicks = boundedTPS - maxTPS;
        this.timestamp = System.nanoTime();
        this.updateAverageTPS();
    }

    private void updateAverageTPS() {
        if (this.tpsHistory.size() >= 10) {
            this.tpsHistory.poll();
        }
        this.tpsHistory.add(Float.valueOf(this.TPS));
        float sum = 0.0f;
        Iterator iterator = this.tpsHistory.iterator();
        while (iterator.hasNext()) {
            float tps = ((Float)iterator.next()).floatValue();
            sum += tps;
        }
        this.averageTPS = (float)this.round(sum / (float)this.tpsHistory.size());
    }

    public double round(double input) {
        return (double)Math.round(input * 100.0) / 100.0;
    }

    public Queue<Float> getTpsHistory() {
        return this.tpsHistory;
    }

    public float getTPS() {
        return this.TPS;
    }

    public float getAverageTPS() {
        return this.averageTPS;
    }

    public float getAdjustTicks() {
        return this.adjustTicks;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}

