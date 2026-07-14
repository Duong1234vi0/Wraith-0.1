/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.cache;

import java.util.Set;
import mods.baritone.api.api.java.baritone.api.cache.IWaypoint;

public interface IWaypointCollection {
    public void addWaypoint(IWaypoint var1);

    public void removeWaypoint(IWaypoint var1);

    public IWaypoint getMostRecentByTag(IWaypoint.Tag var1);

    public Set<IWaypoint> getByTag(IWaypoint.Tag var1);

    public Set<IWaypoint> getAllWaypoints();
}

