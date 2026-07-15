/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.client.player.AbstractClientPlayer
 *  net.minecraft.client.renderer.SubmitNodeCollector
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.world.InteractionHand
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.Particle;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.InteractionHand;

public class ParticleRenderManager {
    public static void draw(ArrayList<Particle> particles, PoseStack matrices, SubmitNodeCollector queue, String space, InteractionHand hand, int light, AbstractClientPlayer player, float tickProgress) {
        Iterator<Particle> iterator = particles.iterator();
        while (iterator.hasNext()) {
            Particle particle = iterator.next();
            if (particle.dead) {
                iterator.remove();
                continue;
            }
            if (particle.space != space || particle.hand != hand) continue;
            if (particle.p_matrices != null) {
                particle.render(particle.p_matrices, queue, light, player, tickProgress);
            } else {
                particle.render(matrices, queue, light, player, tickProgress);
            }
            particle.tick();
        }
    }
}
