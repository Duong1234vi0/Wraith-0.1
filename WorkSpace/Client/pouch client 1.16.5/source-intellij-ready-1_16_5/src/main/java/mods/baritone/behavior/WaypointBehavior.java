/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.behavior;

import java.util.Set;
import lightning.product.J_2868_p;
import lightning.product.K_4074_S;
import lightning.product.h_2829_o;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.cache.IWaypoint;
import mods.baritone.api.api.java.baritone.api.cache.Waypoint;
import mods.baritone.api.api.java.baritone.api.event.events.BlockInteractEvent;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.behavior.Behavior;
import mods.baritone.utils.BlockStateInterface;

public class WaypointBehavior
extends Behavior {
    public WaypointBehavior(Baritone baritone) {
        super(baritone);
    }

    @Override
    public void onBlockInteract(BlockInteractEvent event) {
        BetterBlockPos pos;
        K_4074_S state;
        if (!((Boolean)Baritone.settings().doBedWaypoints.value).booleanValue()) {
            return;
        }
        if (event.getType() == BlockInteractEvent.Type.USE && (state = BlockStateInterface.get(this.ctx, pos = BetterBlockPos.from(event.getPos()))).J_1907_R() instanceof J_2868_p) {
            if (state.R_4764_Y(J_2868_p.P_4830_p) == h_2829_o.J_1907_R) {
                pos = pos.offset(state.R_4764_Y(J_2868_p.w_612_n));
            }
            Set<IWaypoint> waypoints = this.baritone.getWorldProvider().getCurrentWorld().getWaypoints().getByTag(IWaypoint.Tag.BED);
            boolean exists = waypoints.stream().map(IWaypoint::getLocation).filter(pos::equals).findFirst().isPresent();
            if (!exists) {
                this.baritone.getWorldProvider().getCurrentWorld().getWaypoints().addWaypoint(new Waypoint("bed", IWaypoint.Tag.BED, pos));
            }
        }
    }
}

