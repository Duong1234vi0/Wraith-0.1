/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.behavior;

import java.util.Optional;
import lightning.product.A_4115_X;
import lightning.product.F_1446_q;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.r_4790_y;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.Settings;
import mods.baritone.api.api.java.baritone.api.behavior.ILookBehavior;
import mods.baritone.api.api.java.baritone.api.behavior.look.IAimProcessor;
import mods.baritone.api.api.java.baritone.api.behavior.look.ITickableAimProcessor;
import mods.baritone.api.api.java.baritone.api.event.events.PacketEvent;
import mods.baritone.api.api.java.baritone.api.event.events.TickEvent;
import mods.baritone.api.api.java.baritone.api.event.events.WorldEvent;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;
import mods.baritone.behavior.Behavior;
import mods.baritone.behavior.look.ForkableRandom;

public final class LookBehavior
extends Behavior
implements ILookBehavior {
    private Target target;
    private Rotation prevRotation;
    private final AimProcessor processor;

    public LookBehavior(Baritone baritone) {
        super(baritone);
        this.processor = new AimProcessor(baritone.getPlayerContext());
        A_4115_X.n_1700_B(this);
    }

    @Override
    public void updateTarget(Rotation rotation, boolean blockInteract) {
        this.target = new Target(rotation, blockInteract);
    }

    @Override
    public IAimProcessor getAimProcessor() {
        return this.processor;
    }

    @Override
    public void onTick(TickEvent event) {
        if (event.getType() == TickEvent.Type.IN) {
            this.processor.tick();
        }
    }

    @Y_1740_V
    public void onPlayerUpdate(h_1015_G event) {
        if (this.target == null) {
            return;
        }
        Rotation actual = this.processor.peekRotation(this.target.rotation);
        float t = (float)this.ctx.player().RealmsWorldResetDto + this.ctx.minecraft().RealmsClientConfig();
        float smoothYaw = (float)(Math.sin(t * 0.5f) * 8.0 + Math.sin((double)(t * 0.04f) + 17.2) * 1.5);
        float smoothPitch = (float)(Math.sin(t * 0.65f) * 2.0 + Math.sin((double)(t * 0.03f) + 54.1) * 0.5);
        r_4790_y.n_1700_B(new F_1446_q(actual.getYaw(), actual.getPitch()), 180.0f, 1, 5);
        this.target = null;
    }

    @Override
    public void onSendPacket(PacketEvent event) {
    }

    @Override
    public void onWorldEvent(WorldEvent event) {
        this.target = null;
    }

    public void pig() {
        if (this.target != null) {
            Rotation desired = this.target.rotation;
            r_4790_y.n_1700_B(new F_1446_q(desired.getYaw(), this.ctx.player().f_4016_n), 180.0f, 2, 100);
        }
    }

    public Optional<Rotation> getEffectiveRotation() {
        return Optional.empty();
    }

    private static final class AimProcessor
    extends AbstractAimProcessor {
        public AimProcessor(IPlayerContext ctx) {
            super(ctx);
        }

        @Override
        protected Rotation getPrevRotation() {
            return this.ctx.playerRotations();
        }
    }

    private static class Target {
        public final Rotation rotation;
        public final Mode mode;

        public Target(Rotation rotation, boolean blockInteract) {
            this.rotation = rotation;
            this.mode = Mode.resolve(blockInteract);
        }

        static enum Mode {
            CLIENT,
            SERVER,
            NONE;


            static Mode resolve(boolean blockInteract) {
                Settings settings = Baritone.settings();
                boolean antiCheat = (Boolean)settings.antiCheatCompatibility.value;
                boolean blockFreeLook = (Boolean)settings.blockFreeLook.value;
                boolean freeLook = (Boolean)settings.freeLook.value;
                if (!freeLook) {
                    return CLIENT;
                }
                if (!blockFreeLook && blockInteract) {
                    return CLIENT;
                }
                if (antiCheat || blockInteract) {
                    return SERVER;
                }
                return NONE;
            }
        }
    }

    private static abstract class AbstractAimProcessor
    implements ITickableAimProcessor {
        protected final IPlayerContext ctx;
        private final ForkableRandom rand;
        private double randomYawOffset;
        private double randomPitchOffset;

        public AbstractAimProcessor(IPlayerContext ctx) {
            this.ctx = ctx;
            this.rand = new ForkableRandom();
        }

        private AbstractAimProcessor(AbstractAimProcessor source) {
            this.ctx = source.ctx;
            this.rand = source.rand.fork();
            this.randomYawOffset = source.randomYawOffset;
            this.randomPitchOffset = source.randomPitchOffset;
        }

        @Override
        public final Rotation peekRotation(Rotation rotation) {
            Rotation prev = this.getPrevRotation();
            float desiredYaw = rotation.getYaw();
            float desiredPitch = rotation.getPitch();
            if (desiredPitch == prev.getPitch()) {
                desiredPitch = this.nudgeToLevel(desiredPitch);
            }
            desiredYaw = (float)((double)desiredYaw + this.randomYawOffset);
            desiredPitch = (float)((double)desiredPitch + this.randomPitchOffset);
            return new Rotation(this.calculateMouseMove(prev.getYaw(), desiredYaw), this.calculateMouseMove(prev.getPitch(), desiredPitch)).clamp();
        }

        @Override
        public final void tick() {
            this.randomYawOffset = (this.rand.nextDouble() - 0.5) * (Double)Baritone.settings().randomLooking.value;
            this.randomPitchOffset = (this.rand.nextDouble() - 0.5) * (Double)Baritone.settings().randomLooking.value;
            double random = this.rand.nextDouble() - 0.5;
            if (Math.abs(random) < 0.1) {
                random *= 4.0;
            }
            this.randomYawOffset += random * (Double)Baritone.settings().randomLooking113.value;
        }

        @Override
        public final void advance(int ticks) {
            for (int i = 0; i < ticks; ++i) {
                this.tick();
            }
        }

        @Override
        public Rotation nextRotation(Rotation rotation) {
            Rotation actual = this.peekRotation(rotation);
            this.tick();
            return actual;
        }

        @Override
        public final ITickableAimProcessor fork() {
            return new AbstractAimProcessor(this){
                private Rotation prev;
                {
                    super(source);
                    this.prev = this.getPrevRotation();
                }

                @Override
                public Rotation nextRotation(Rotation rotation) {
                    this.prev = super.nextRotation(rotation);
                    return this.prev;
                }

                @Override
                protected Rotation getPrevRotation() {
                    return this.prev;
                }
            };
        }

        protected abstract Rotation getPrevRotation();

        private float nudgeToLevel(float pitch) {
            if (pitch < -20.0f) {
                return pitch + 1.0f;
            }
            if (pitch > 10.0f) {
                return pitch - 1.0f;
            }
            return pitch;
        }

        private float calculateMouseMove(float current, float target) {
            return target;
        }

        private double angleToMouse(double angleDelta) {
            return 0.0;
        }

        private double mouseToAngle(double mouseDelta) {
            return 0.0;
        }
    }
}


