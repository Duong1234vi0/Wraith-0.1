/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.resources.Identifier
 *  org.luaj.vm2.LuaFunction
 *  org.luaj.vm2.LuaValue
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.annotation.Safe;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.LuaConsumer;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.Particle;
import java.util.ArrayList;
import java.util.List;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.Identifier;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;

public class ParticleManager {
    @Safe
    public void addParticle(ArrayList<Particle> particles, boolean gravity, double x, double y, double z, double dx, double dy, double dz, double rx, double ry, double rz, double drx, double dry, double drz, double maxScale, Identifier texture, String space, InteractionHand hand, String lifecycleType, String particleRenderType, double particleLifetime, double alpha, LuaValue func) {
        particleRenderType = particleRenderType.intern();
        lifecycleType = lifecycleType.intern();
        space = space.intern();
        LuaConsumer ticker = null;
        if (func.isfunction()) {
            ticker = new LuaConsumer((LuaFunction)func);
        }
        particles.add(new Particle(gravity, x, y, z, dx, dy, dz, rx, ry, rz, drx, dry, drz, maxScale, texture, space, hand, lifecycleType, particleRenderType, particleLifetime, alpha, ticker));
    }

    @Safe
    public void addParticle(ArrayList<Particle> particles, boolean gravity, double x, double y, double z, double dx, double dy, double dz, double rx, double ry, double rz, double drx, double dry, double drz, double maxScale, Identifier texture, String space, InteractionHand hand, String lifecycleType, String particleRenderType, double particleLifetime, double alpha) {
        particleRenderType = particleRenderType.intern();
        lifecycleType = lifecycleType.intern();
        space = space.intern();
        particles.add(new Particle(gravity, x, y, z, dx, dy, dz, rx, ry, rz, drx, dry, drz, maxScale, texture, space, hand, lifecycleType, particleRenderType, particleLifetime, alpha));
    }

    @Safe
    public void addParticle(ArrayList<Particle> particles, boolean gravity, double x, double y, double z, double dx, double dy, double dz, double rx, double ry, double rz, double drx, double dry, double drz, double maxScale, Identifier texture, List<Identifier> keyframes, String space, InteractionHand hand, String lifecycleType, String particleRenderType, double particleLifetime, double alpha, LuaValue func) {
        particleRenderType = particleRenderType.intern();
        lifecycleType = lifecycleType.intern();
        space = space.intern();
        LuaConsumer ticker = null;
        if (func.isfunction()) {
            ticker = new LuaConsumer((LuaFunction)func);
        }
        if (particles.stream().noneMatch(particle -> particle.lifecycleType == "KEYFRAME" && particle.hand == hand)) {
            particles.add(new Particle(gravity, x, y, z, dx, dy, dz, rx, ry, rz, drx, dry, drz, maxScale, texture, keyframes, space, hand, lifecycleType, particleRenderType, particleLifetime, alpha, ticker));
        }
    }

    @Safe
    public void addParticle(ArrayList<Particle> particles, boolean gravity, double x, double y, double z, double dx, double dy, double dz, double rx, double ry, double rz, double drx, double dry, double drz, double maxScale, Identifier texture, String space, InteractionHand hand, String lifecycleType, String particleRenderType, double particleLifetime, double alpha, LuaValue func, PoseStack p_matrices) {
        particleRenderType = particleRenderType.intern();
        lifecycleType = lifecycleType.intern();
        space = space.intern();
        LuaConsumer ticker = null;
        if (func.isfunction()) {
            ticker = new LuaConsumer((LuaFunction)func);
        }
        particles.add(new Particle(gravity, x, y, z, dx, dy, dz, rx, ry, rz, drx, dry, drz, maxScale, texture, space, hand, lifecycleType, particleRenderType, particleLifetime, alpha, ticker, p_matrices));
    }
}
