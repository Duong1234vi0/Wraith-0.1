/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api;

import mods.baritone.api.api.java.baritone.api.behavior.ILookBehavior;
import mods.baritone.api.api.java.baritone.api.behavior.IPathingBehavior;
import mods.baritone.api.api.java.baritone.api.cache.IWorldProvider;
import mods.baritone.api.api.java.baritone.api.command.manager.ICommandManager;
import mods.baritone.api.api.java.baritone.api.event.listener.IEventBus;
import mods.baritone.api.api.java.baritone.api.pathing.calc.IPathingControlManager;
import mods.baritone.api.api.java.baritone.api.process.IBuilderProcess;
import mods.baritone.api.api.java.baritone.api.process.ICustomGoalProcess;
import mods.baritone.api.api.java.baritone.api.process.IExploreProcess;
import mods.baritone.api.api.java.baritone.api.process.IFarmProcess;
import mods.baritone.api.api.java.baritone.api.process.IFollowProcess;
import mods.baritone.api.api.java.baritone.api.process.IGetToBlockProcess;
import mods.baritone.api.api.java.baritone.api.process.IMineProcess;
import mods.baritone.api.api.java.baritone.api.selection.ISelectionManager;
import mods.baritone.api.api.java.baritone.api.utils.IInputOverrideHandler;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;

public interface IBaritone {
    public IPathingBehavior getPathingBehavior();

    public ILookBehavior getLookBehavior();

    public IFollowProcess getFollowProcess();

    public IMineProcess getMineProcess();

    public IBuilderProcess getBuilderProcess();

    public IExploreProcess getExploreProcess();

    public IFarmProcess getFarmProcess();

    public ICustomGoalProcess getCustomGoalProcess();

    public IGetToBlockProcess getGetToBlockProcess();

    public IWorldProvider getWorldProvider();

    public IPathingControlManager getPathingControlManager();

    public IInputOverrideHandler getInputOverrideHandler();

    public IPlayerContext getPlayerContext();

    public IEventBus getGameEventHandler();

    public ISelectionManager getSelectionManager();

    public ICommandManager getCommandManager();

    public void openClick();
}

