/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 */
package mods.baritone.process;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.BlockHitResult;
import lightning.product.HitResult;
import lightning.product.I_4817_s;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.Tuple;
import lightning.product.Q_4220_D;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.RotatedPillarBlock;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.AirBlock;
import lightning.product.IronBarsBlock;
import lightning.product.r_1827_u;
import lightning.product.s_1395_c;
import lightning.product.s_3834_w;
import lightning.product.v_1669_V;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;
import lightning.product.x_2838_H;
import lightning.product.z_2909_G;
import lightning.product.z_3539_x;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalComposite;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalGetToBlock;
import mods.baritone.api.api.java.baritone.api.process.IBuilderProcess;
import mods.baritone.api.api.java.baritone.api.process.PathingCommand;
import mods.baritone.api.api.java.baritone.api.process.PathingCommandType;
import mods.baritone.api.api.java.baritone.api.schematic.FillSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.ISchematic;
import mods.baritone.api.api.java.baritone.api.schematic.IStaticSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.SubstituteSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.format.ISchematicFormat;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.RayTraceUtils;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;
import mods.baritone.api.api.java.baritone.api.utils.RotationUtils;
import mods.baritone.api.api.java.baritone.api.utils.SettingsUtil;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;
import mods.baritone.pathing.movement.CalculationContext;
import mods.baritone.pathing.movement.Movement;
import mods.baritone.pathing.movement.MovementHelper;
import mods.baritone.utils.BaritoneProcessHelper;
import mods.baritone.utils.BlockStateInterface;
import mods.baritone.utils.PathingCommandContext;
import mods.baritone.utils.schematic.MapArtSchematic;
import mods.baritone.utils.schematic.SchematicSystem;
import mods.baritone.utils.schematic.SelectionSchematic;
import mods.baritone.utils.schematic.format.defaults.LitematicaSchematic;
import mods.baritone.utils.schematic.litematica.LitematicaHelper;
import mods.baritone.utils.schematic.schematica.SchematicaHelper;

public final class BuilderProcess
extends BaritoneProcessHelper
implements IBuilderProcess {
    private HashSet<BetterBlockPos> incorrectPositions;
    private LongOpenHashSet observedCompleted;
    private String name;
    private ISchematic realSchematic;
    private ISchematic schematic;
    private z_3539_x origin;
    private int ticks;
    private boolean paused;
    private int layer;
    private int numRepeats;
    private List<K_4074_S> approxPlaceable;
    public int stopAtHeight = 0;
    public static final Set<v_3760_Q<?>> orientationProps = ImmutableSet.of(RotatedPillarBlock.t_1786_h, (Object)HorizontalDirectionalBlock.w_612_n, (Object)z_2909_G.P_4830_p, z_2909_G.h_1847_R, z_2909_G.Q_4569_t, (Object)IronBarsBlock.P_4830_p, (Object[])new v_3760_Q[]{IronBarsBlock.h_1847_R, IronBarsBlock.Q_4569_t, IronBarsBlock.M_182_A, Q_4220_D.P_4830_p, x_2838_H.P_4830_p, x_2838_H.h_1847_R});

    public BuilderProcess(Baritone baritone) {
        super(baritone);
    }

    @Override
    public void build(String name, ISchematic schematic, z_3539_x origin) {
        this.name = name;
        this.schematic = schematic;
        this.realSchematic = null;
        boolean buildingSelectionSchematic = schematic instanceof SelectionSchematic;
        if (!((Map)Baritone.settings().buildSubstitutes.value).isEmpty()) {
            this.schematic = new SubstituteSchematic(this.schematic, (Map)Baritone.settings().buildSubstitutes.value);
        }
        int x = origin.getX();
        int y = origin.getY();
        int z = origin.getZ();
        if (((Boolean)Baritone.settings().schematicOrientationX.value).booleanValue()) {
            x += schematic.widthX();
        }
        if (((Boolean)Baritone.settings().schematicOrientationY.value).booleanValue()) {
            y += schematic.heightY();
        }
        if (((Boolean)Baritone.settings().schematicOrientationZ.value).booleanValue()) {
            z += schematic.lengthZ();
        }
        this.origin = new z_3539_x(x, y, z);
        this.paused = false;
        this.layer = (Integer)Baritone.settings().startAtLayer.value;
        this.stopAtHeight = schematic.heightY();
        if (((Boolean)Baritone.settings().buildOnlySelection.value).booleanValue() && buildingSelectionSchematic) {
            if (this.baritone.getSelectionManager().getSelections().length == 0) {
                this.logDirect("Poor little kitten forgot to set a selection while BuildOnlySelection is true");
                this.stopAtHeight = 0;
            } else if (((Boolean)Baritone.settings().buildInLayers.value).booleanValue()) {
                OptionalInt minim = Stream.of(this.baritone.getSelectionManager().getSelections()).mapToInt(sel -> sel.min().y).min();
                OptionalInt maxim = Stream.of(this.baritone.getSelectionManager().getSelections()).mapToInt(sel -> sel.max().y).max();
                if (minim.isPresent() && maxim.isPresent()) {
                    int startAtHeight = (Boolean)Baritone.settings().layerOrder.value != false ? y + schematic.heightY() - maxim.getAsInt() : minim.getAsInt() - y;
                    this.stopAtHeight = ((Boolean)Baritone.settings().layerOrder.value != false ? y + schematic.heightY() - minim.getAsInt() : maxim.getAsInt() - y) + 1;
                    this.layer = Math.max(this.layer, startAtHeight / (Integer)Baritone.settings().layerHeight.value);
                    this.logDebug(String.format("Schematic starts at y=%s with height %s", y, schematic.heightY()));
                    this.logDebug(String.format("Selection starts at y=%s and ends at y=%s", minim.getAsInt(), maxim.getAsInt()));
                    this.logDebug(String.format("Considering relevant height %s - %s", startAtHeight, this.stopAtHeight));
                }
            }
        }
        this.numRepeats = 0;
        this.observedCompleted = new LongOpenHashSet();
        this.incorrectPositions = null;
    }

    @Override
    public void resume() {
        this.paused = false;
    }

    @Override
    public void pause() {
        this.paused = true;
    }

    @Override
    public boolean isPaused() {
        return this.paused;
    }

    @Override
    public boolean build(String name, File schematic, z_3539_x origin) {
        ISchematic parsed;
        Optional<ISchematicFormat> format = SchematicSystem.INSTANCE.getByFile(schematic);
        if (!format.isPresent()) {
            return false;
        }
        try {
            parsed = format.get().parse(new FileInputStream(schematic));
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        parsed = this.applyMapArtAndSelection(origin, (IStaticSchematic)parsed);
        this.build(name, parsed, origin);
        return true;
    }

    private ISchematic applyMapArtAndSelection(z_3539_x origin, IStaticSchematic parsed) {
        ISchematic schematic = parsed;
        if (((Boolean)Baritone.settings().mapArtMode.value).booleanValue()) {
            schematic = new MapArtSchematic(parsed);
        }
        if (((Boolean)Baritone.settings().buildOnlySelection.value).booleanValue()) {
            schematic = new SelectionSchematic(schematic, origin, this.baritone.getSelectionManager().getSelections());
        }
        return schematic;
    }

    @Override
    public void buildOpenSchematic() {
        if (SchematicaHelper.isSchematicaPresent()) {
            Optional<Tuple<IStaticSchematic, c_1514_x>> schematic = SchematicaHelper.getOpenSchematic();
            if (schematic.isPresent()) {
                ISchematic schem;
                IStaticSchematic s = schematic.get().n_1700_B();
                c_1514_x origin = schematic.get().J_1907_R();
                ISchematic iSchematic = schem = (Boolean)Baritone.settings().mapArtMode.value != false ? new MapArtSchematic(s) : s;
                if (((Boolean)Baritone.settings().buildOnlySelection.value).booleanValue()) {
                    schem = new SelectionSchematic(schem, origin, this.baritone.getSelectionManager().getSelections());
                }
                this.build(schematic.get().n_1700_B().toString(), schem, (z_3539_x)origin);
            } else {
                this.logDirect("No schematic currently open");
            }
        } else {
            this.logDirect("Schematica is not present");
        }
    }

    @Override
    public void buildOpenLitematic(int i) {
        if (LitematicaHelper.isLitematicaPresent()) {
            if (LitematicaHelper.hasLoadedSchematic()) {
                String name = LitematicaHelper.getName(i);
                try {
                    LitematicaSchematic schematic1 = new LitematicaSchematic(r_1827_u.n_1700_B(Files.newInputStream(LitematicaHelper.getSchematicFile(i).toPath(), new OpenOption[0])), false);
                    z_3539_x correctedOrigin = LitematicaHelper.getCorrectedOrigin(schematic1, i);
                    ISchematic schematic2 = LitematicaHelper.blackMagicFuckery(schematic1, i);
                    schematic2 = this.applyMapArtAndSelection(this.origin, (IStaticSchematic)schematic2);
                    this.build(name, schematic2, correctedOrigin);
                }
                catch (Exception e) {
                    this.logDirect("Schematic File could not be loaded.");
                }
            } else {
                this.logDirect("No schematic currently loaded");
            }
        } else {
            this.logDirect("Litematica is not present");
        }
    }

    @Override
    public void clearArea(c_1514_x corner1, c_1514_x corner2) {
        c_1514_x origin = new c_1514_x(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()), Math.min(corner1.getZ(), corner2.getZ()));
        int widthX = Math.abs(corner1.getX() - corner2.getX()) + 1;
        int heightY = Math.abs(corner1.getY() - corner2.getY()) + 1;
        int lengthZ = Math.abs(corner1.getZ() - corner2.getZ()) + 1;
        this.build("clear area", new FillSchematic(widthX, heightY, lengthZ, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider()), (z_3539_x)origin);
    }

    @Override
    public List<K_4074_S> getApproxPlaceable() {
        return new ArrayList<K_4074_S>(this.approxPlaceable);
    }

    @Override
    public boolean isActive() {
        return this.schematic != null;
    }

    public K_4074_S placeAt(int x, int y, int z, K_4074_S current) {
        if (!this.isActive()) {
            return null;
        }
        if (!this.schematic.inSchematic(x - this.origin.getX(), y - this.origin.getY(), z - this.origin.getZ(), current)) {
            return null;
        }
        K_4074_S state = this.schematic.desiredState(x - this.origin.getX(), y - this.origin.getY(), z - this.origin.getZ(), current, this.approxPlaceable);
        if (state.J_1907_R() instanceof AirBlock) {
            return null;
        }
        return state;
    }

    private Optional<Tuple<BetterBlockPos, Rotation>> toBreakNearPlayer(BuilderCalculationContext bcc) {
        BetterBlockPos center = this.ctx.playerFeet();
        BetterBlockPos pathStart = this.baritone.getPathingBehavior().pathStart();
        for (int dx = -5; dx <= 5; ++dx) {
            int dy;
            int n = dy = (Boolean)Baritone.settings().breakFromAbove.value != false ? -1 : 0;
            while (dy <= 5) {
                for (int dz = -5; dz <= 5; ++dz) {
                    BetterBlockPos pos;
                    Optional<Rotation> rot;
                    K_4074_S curr;
                    K_4074_S desired;
                    int x = center.x + dx;
                    int y = center.y + dy;
                    int z = center.z + dz;
                    if (dy == -1 && x == pathStart.x && z == pathStart.z || (desired = bcc.getSchematic(x, y, z, bcc.bsi.get0(x, y, z))) == null || (curr = bcc.bsi.get0(x, y, z)).J_1907_R() instanceof AirBlock || curr.J_1907_R() == a_3742_W.c_3005_b || curr.J_1907_R() == a_3742_W.H_2857_Y || this.valid(curr, desired, false) || !(rot = RotationUtils.reachable(this.ctx, (c_1514_x)(pos = new BetterBlockPos(x, y, z)), this.ctx.playerController().getBlockReachDistance())).isPresent()) continue;
                    return Optional.of(new Tuple<BetterBlockPos, Rotation>(pos, rot.get()));
                }
                ++dy;
            }
        }
        return Optional.empty();
    }

    private Optional<Placement> searchForPlacables(BuilderCalculationContext bcc, List<K_4074_S> desirableOnHotbar) {
        BetterBlockPos center = this.ctx.playerFeet();
        for (int dx = -5; dx <= 5; ++dx) {
            for (int dy = -5; dy <= 1; ++dy) {
                for (int dz = -5; dz <= 5; ++dz) {
                    K_4074_S curr;
                    int x = center.x + dx;
                    int y = center.y + dy;
                    int z = center.z + dz;
                    K_4074_S desired = bcc.getSchematic(x, y, z, bcc.bsi.get0(x, y, z));
                    if (desired == null || !MovementHelper.isReplaceable(x, y, z, curr = bcc.bsi.get0(x, y, z), bcc.bsi) || this.valid(curr, desired, false) || dy == 1 && bcc.bsi.get0(x, y + 1, z).J_1907_R() instanceof AirBlock) continue;
                    desirableOnHotbar.add(desired);
                    Optional<Placement> opt = this.possibleToPlace(desired, x, y, z, bcc.bsi);
                    if (!opt.isPresent()) continue;
                    return opt;
                }
            }
        }
        return Optional.empty();
    }

    public boolean placementPlausible(c_1514_x pos, K_4074_S state) {
        s_1395_c voxelshape = state.u_2550_I(this.ctx.world(), pos);
        return voxelshape.J_1907_R() || this.ctx.world().n_1700_B(null, voxelshape.n_1700_B(pos.getX(), (double)pos.getY(), (double)pos.getZ()));
    }

    private Optional<Placement> possibleToPlace(K_4074_S toPlace, int x, int y, int z, BlockStateInterface bsi) {
        for (b_257_Y against : b_257_Y.values()) {
            BetterBlockPos placeAgainstPos = new BetterBlockPos(x, y, z).offset(against);
            K_4074_S placeAgainstState = bsi.get0(placeAgainstPos);
            if (MovementHelper.isReplaceable(placeAgainstPos.x, placeAgainstPos.y, placeAgainstPos.z, placeAgainstState, bsi) || !toPlace.n_1700_B((T_1316_M)this.ctx.world(), (c_1514_x)new BetterBlockPos(x, y, z)) || !this.placementPlausible(new BetterBlockPos(x, y, z), toPlace)) continue;
            I_4817_s aabb = placeAgainstState.s_956_w(this.ctx.world(), placeAgainstPos).n_1700_B();
            for (e_2866_D placementMultiplier : BuilderProcess.aabbSideMultipliers(against)) {
                OptionalInt hotbar;
                double placeX = (double)placeAgainstPos.x + aabb.minX * placementMultiplier.J_1907_R + aabb.maxX * (1.0 - placementMultiplier.J_1907_R);
                double placeY = (double)placeAgainstPos.y + aabb.minY * placementMultiplier.R_4764_Y + aabb.maxY * (1.0 - placementMultiplier.R_4764_Y);
                double placeZ = (double)placeAgainstPos.z + aabb.minZ * placementMultiplier.G_564_y + aabb.maxZ * (1.0 - placementMultiplier.G_564_y);
                Rotation rot = RotationUtils.calcRotationFromVec3d(RayTraceUtils.inferSneakingEyePosition(this.ctx.player()), new e_2866_D(placeX, placeY, placeZ), this.ctx.playerRotations());
                Rotation actualRot = this.baritone.getLookBehavior().getAimProcessor().peekRotation(rot);
                HitResult result = RayTraceUtils.rayTraceTowards(this.ctx.player(), actualRot, this.ctx.playerController().getBlockReachDistance(), true);
                if (result == null || result.R_4764_Y() != HitResult.n_1700_B.J_1907_R || !((BlockHitResult)result).n_1700_B().equals(placeAgainstPos) || ((BlockHitResult)result).J_1907_R() != against.u_1723_Y() || !(hotbar = this.hasAnyItemThatWouldPlace(toPlace, result, actualRot)).isPresent()) continue;
                return Optional.of(new Placement(hotbar.getAsInt(), placeAgainstPos, against.u_1723_Y(), rot));
            }
        }
        return Optional.empty();
    }

    private OptionalInt hasAnyItemThatWouldPlace(K_4074_S desired, HitResult result, Rotation rot) {
        for (int i = 0; i < 9; ++i) {
            Z_1993_T stack = this.ctx.player().l_1268_F.n_1700_B.get(i);
            if (stack.n_1700_B() || !(stack.J_1907_R() instanceof v_1669_V)) continue;
            float originalYaw = this.ctx.player().p_178_J;
            float originalPitch = this.ctx.player().f_4016_n;
            this.ctx.player().p_178_J = rot.getYaw();
            this.ctx.player().f_4016_n = rot.getPitch();
            BlockPlaceContext meme = new BlockPlaceContext(new UseOnContext(this, this.ctx.world(), this.ctx.player(), x_1688_C.n_1700_B, stack, (BlockHitResult)result){});
            K_4074_S wouldBePlaced = ((v_1669_V)stack.J_1907_R()).v_4262_N().n_1700_B(meme);
            this.ctx.player().p_178_J = originalYaw;
            this.ctx.player().f_4016_n = originalPitch;
            if (wouldBePlaced == null || !meme.n_1700_B() || !this.valid(wouldBePlaced, desired, true)) continue;
            return OptionalInt.of(i);
        }
        return OptionalInt.empty();
    }

    private static e_2866_D[] aabbSideMultipliers(b_257_Y side) {
        switch (side) {
            case J_1907_R: {
                return new e_2866_D[]{new e_2866_D(0.5, 1.0, 0.5), new e_2866_D(0.1, 1.0, 0.5), new e_2866_D(0.9, 1.0, 0.5), new e_2866_D(0.5, 1.0, 0.1), new e_2866_D(0.5, 1.0, 0.9)};
            }
            case n_1700_B: {
                return new e_2866_D[]{new e_2866_D(0.5, 0.0, 0.5), new e_2866_D(0.1, 0.0, 0.5), new e_2866_D(0.9, 0.0, 0.5), new e_2866_D(0.5, 0.0, 0.1), new e_2866_D(0.5, 0.0, 0.9)};
            }
            case R_4764_Y: 
            case G_564_y: 
            case u_1723_Y: 
            case P_1922_E: {
                double x = side.t_148_a() == 0 ? 0.5 : (double)(1 + side.t_148_a()) / 2.0;
                double z = side.u_2550_I() == 0 ? 0.5 : (double)(1 + side.u_2550_I()) / 2.0;
                return new e_2866_D[]{new e_2866_D(x, 0.25, z), new e_2866_D(x, 0.75, z)};
            }
        }
        throw new IllegalStateException();
    }

    @Override
    public PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel) {
        return this.onTick(calcFailed, isSafeToCancel, 0);
    }

    public PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel, int recursions) {
        Goal goal;
        Optional<Tuple<BetterBlockPos, Rotation>> toBreak;
        BuilderCalculationContext bcc;
        if (recursions > 1000) {
            return new PathingCommand(null, PathingCommandType.SET_GOAL_AND_PATH);
        }
        this.approxPlaceable = this.approxPlaceable(36);
        this.ticks = this.baritone.getInputOverrideHandler().isInputForcedDown(Input.CLICK_LEFT) ? 5 : --this.ticks;
        this.baritone.getInputOverrideHandler().clearAllKeys();
        if (this.paused) {
            return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
        }
        if (((Boolean)Baritone.settings().buildInLayers.value).booleanValue()) {
            int minYInclusive;
            int maxYInclusive;
            if (this.realSchematic == null) {
                this.realSchematic = this.schematic;
            }
            final ISchematic realSchematic = this.realSchematic;
            if (((Boolean)Baritone.settings().layerOrder.value).booleanValue()) {
                maxYInclusive = realSchematic.heightY() - 1;
                minYInclusive = realSchematic.heightY() - this.layer * (Integer)Baritone.settings().layerHeight.value;
            } else {
                maxYInclusive = this.layer * (Integer)Baritone.settings().layerHeight.value - 1;
                minYInclusive = 0;
            }
            this.schematic = new ISchematic(){

                @Override
                public K_4074_S desiredState(int x, int y, int z, K_4074_S current, List<K_4074_S> approxPlaceable) {
                    return realSchematic.desiredState(x, y, z, current, BuilderProcess.this.approxPlaceable);
                }

                @Override
                public boolean inSchematic(int x, int y, int z, K_4074_S currentState) {
                    return ISchematic.super.inSchematic(x, y, z, currentState) && y >= minYInclusive && y <= maxYInclusive && realSchematic.inSchematic(x, y, z, currentState);
                }

                @Override
                public void reset() {
                    realSchematic.reset();
                }

                @Override
                public int widthX() {
                    return realSchematic.widthX();
                }

                @Override
                public int heightY() {
                    return realSchematic.heightY();
                }

                @Override
                public int lengthZ() {
                    return realSchematic.lengthZ();
                }
            };
        }
        if (!this.recalc(bcc = new BuilderCalculationContext())) {
            if (((Boolean)Baritone.settings().buildInLayers.value).booleanValue() && this.layer * (Integer)Baritone.settings().layerHeight.value < this.stopAtHeight) {
                this.logDirect("Starting layer " + this.layer);
                ++this.layer;
                return this.onTick(calcFailed, isSafeToCancel, recursions + 1);
            }
            z_3539_x repeat = (z_3539_x)Baritone.settings().buildRepeat.value;
            int max = (Integer)Baritone.settings().buildRepeatCount.value;
            ++this.numRepeats;
            if (repeat.equals(new z_3539_x(0, 0, 0)) || max != -1 && this.numRepeats >= max) {
                this.logDirect("Done building");
                if (((Boolean)Baritone.settings().notificationOnBuildFinished.value).booleanValue()) {
                    this.logNotification("Done building", false);
                }
                this.onLostControl();
                return null;
            }
            this.layer = 0;
            this.origin = new c_1514_x(this.origin).add(repeat);
            if (!((Boolean)Baritone.settings().buildRepeatSneaky.value).booleanValue()) {
                this.schematic.reset();
            }
            this.logDirect("Repeating build in vector " + String.valueOf(repeat) + ", new origin is " + String.valueOf(this.origin));
            return this.onTick(calcFailed, isSafeToCancel, recursions + 1);
        }
        if (((Boolean)Baritone.settings().distanceTrim.value).booleanValue()) {
            this.trim();
        }
        if ((toBreak = this.toBreakNearPlayer(bcc)).isPresent() && isSafeToCancel && this.ctx.player().M_1641_O()) {
            Rotation rot = toBreak.get().J_1907_R();
            BetterBlockPos pos = toBreak.get().n_1700_B();
            this.baritone.getLookBehavior().updateTarget(rot, true);
            MovementHelper.switchToBestToolFor(this.ctx, bcc.get(pos));
            if (this.ctx.player().Z_875_P()) {
                this.baritone.getInputOverrideHandler().setInputForceState(Input.SNEAK, true);
            }
            if (this.ctx.isLookingAt(pos) || this.ctx.playerRotations().isReallyCloseTo(rot)) {
                this.baritone.getInputOverrideHandler().setInputForceState(Input.CLICK_LEFT, true);
            }
            return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
        }
        ArrayList<K_4074_S> desirableOnHotbar = new ArrayList<K_4074_S>();
        Optional<Placement> toPlace = this.searchForPlacables(bcc, desirableOnHotbar);
        if (toPlace.isPresent() && isSafeToCancel && this.ctx.player().M_1641_O() && this.ticks <= 0) {
            Rotation rot = toPlace.get().rot;
            this.baritone.getLookBehavior().updateTarget(rot, true);
            this.ctx.player().l_1268_F.G_564_y = toPlace.get().hotbarSelection;
            this.baritone.getInputOverrideHandler().setInputForceState(Input.SNEAK, true);
            if (this.ctx.isLookingAt(toPlace.get().placeAgainst) && ((BlockHitResult)this.ctx.objectMouseOver()).J_1907_R().equals(toPlace.get().side) || this.ctx.playerRotations().isReallyCloseTo(rot)) {
                this.baritone.getInputOverrideHandler().setInputForceState(Input.CLICK_RIGHT, true);
            }
            return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
        }
        if (((Boolean)Baritone.settings().allowInventory.value).booleanValue()) {
            ArrayList<Integer> usefulSlots = new ArrayList<Integer>();
            ArrayList<K_4074_S> noValidHotbarOption = new ArrayList<K_4074_S>();
            block0: for (K_4074_S desired : desirableOnHotbar) {
                for (int i = 0; i < 9; ++i) {
                    if (!this.valid(this.approxPlaceable.get(i), desired, true)) continue;
                    usefulSlots.add(i);
                    continue block0;
                }
                noValidHotbarOption.add(desired);
            }
            block2: for (int i = 9; i < 36; ++i) {
                for (K_4074_S desired : noValidHotbarOption) {
                    if (!this.valid(this.approxPlaceable.get(i), desired, true)) continue;
                    if (this.baritone.getInventoryBehavior().attemptToPutOnHotbar(i, usefulSlots::contains)) break block2;
                    return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
                }
            }
        }
        if ((goal = this.assemble(bcc, this.approxPlaceable.subList(0, 9))) == null && (goal = this.assemble(bcc, this.approxPlaceable, true)) == null) {
            if (((Boolean)Baritone.settings().skipFailedLayers.value).booleanValue() && ((Boolean)Baritone.settings().buildInLayers.value).booleanValue() && this.layer * (Integer)Baritone.settings().layerHeight.value < this.realSchematic.heightY()) {
                this.logDirect("Skipping layer that I cannot construct! Layer #" + this.layer);
                ++this.layer;
                return this.onTick(calcFailed, isSafeToCancel, recursions + 1);
            }
            this.logDirect("Unable to do it. Pausing. resume to resume, cancel to cancel");
            this.paused = true;
            return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
        }
        return new PathingCommandContext(goal, PathingCommandType.FORCE_REVALIDATE_GOAL_AND_PATH, bcc);
    }

    private boolean recalc(BuilderCalculationContext bcc) {
        if (this.incorrectPositions == null) {
            this.incorrectPositions = new HashSet();
            this.fullRecalc(bcc);
            if (this.incorrectPositions.isEmpty()) {
                return false;
            }
        }
        this.recalcNearby(bcc);
        if (this.incorrectPositions.isEmpty()) {
            this.fullRecalc(bcc);
        }
        return !this.incorrectPositions.isEmpty();
    }

    private void trim() {
        HashSet<BetterBlockPos> copy = new HashSet<BetterBlockPos>(this.incorrectPositions);
        copy.removeIf(pos -> pos.distanceSq(this.ctx.player().b_2312_j()) > 200.0);
        if (!copy.isEmpty()) {
            this.incorrectPositions = copy;
        }
    }

    private void recalcNearby(BuilderCalculationContext bcc) {
        BetterBlockPos center = this.ctx.playerFeet();
        int radius = (Integer)Baritone.settings().builderTickScanRadius.value;
        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dy = -radius; dy <= radius; ++dy) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    int x = center.x + dx;
                    int y = center.y + dy;
                    int z = center.z + dz;
                    K_4074_S desired = bcc.getSchematic(x, y, z, bcc.bsi.get0(x, y, z));
                    if (desired == null) continue;
                    BetterBlockPos pos = new BetterBlockPos(x, y, z);
                    if (this.valid(bcc.bsi.get0(x, y, z), desired, false)) {
                        this.incorrectPositions.remove(pos);
                        this.observedCompleted.add(BetterBlockPos.longHash(pos));
                        continue;
                    }
                    this.incorrectPositions.add(pos);
                    this.observedCompleted.remove(BetterBlockPos.longHash(pos));
                }
            }
        }
    }

    private void fullRecalc(BuilderCalculationContext bcc) {
        this.incorrectPositions = new HashSet();
        for (int y = 0; y < this.schematic.heightY(); ++y) {
            for (int z = 0; z < this.schematic.lengthZ(); ++z) {
                for (int x = 0; x < this.schematic.widthX(); ++x) {
                    int blockZ;
                    int blockY;
                    int blockX = x + this.origin.getX();
                    K_4074_S current = bcc.bsi.get0(blockX, blockY = y + this.origin.getY(), blockZ = z + this.origin.getZ());
                    if (!this.schematic.inSchematic(x, y, z, current)) continue;
                    if (bcc.bsi.worldContainsLoadedChunk(blockX, blockZ)) {
                        if (this.valid(bcc.bsi.get0(blockX, blockY, blockZ), this.schematic.desiredState(x, y, z, current, this.approxPlaceable), false)) {
                            this.observedCompleted.add(BetterBlockPos.longHash(blockX, blockY, blockZ));
                            continue;
                        }
                        this.incorrectPositions.add(new BetterBlockPos(blockX, blockY, blockZ));
                        this.observedCompleted.remove(BetterBlockPos.longHash(blockX, blockY, blockZ));
                        if (this.incorrectPositions.size() <= (Integer)Baritone.settings().incorrectSize.value) continue;
                        return;
                    }
                    if (this.observedCompleted.contains(BetterBlockPos.longHash(blockX, blockY, blockZ)) || ((List)Baritone.settings().buildSkipBlocks.value).contains(this.schematic.desiredState(x, y, z, current, this.approxPlaceable).J_1907_R())) continue;
                    this.incorrectPositions.add(new BetterBlockPos(blockX, blockY, blockZ));
                    if (this.incorrectPositions.size() <= (Integer)Baritone.settings().incorrectSize.value) continue;
                    return;
                }
            }
        }
    }

    private Goal assemble(BuilderCalculationContext bcc, List<K_4074_S> approxPlaceable) {
        return this.assemble(bcc, approxPlaceable, false);
    }

    private Goal assemble(BuilderCalculationContext bcc, List<K_4074_S> approxPlaceable, boolean logMissing) {
        ArrayList placeable = new ArrayList();
        ArrayList breakable = new ArrayList();
        ArrayList sourceLiquids = new ArrayList();
        ArrayList flowingLiquids = new ArrayList();
        HashMap missing = new HashMap();
        this.incorrectPositions.forEach(pos -> {
            K_4074_S state = bcc.bsi.get0((c_1514_x)pos);
            if (state.J_1907_R() instanceof AirBlock) {
                if (this.containsBlockState(approxPlaceable, bcc.getSchematic(pos.x, pos.y, pos.z, state))) {
                    placeable.add(pos);
                } else {
                    K_4074_S desired = bcc.getSchematic(pos.x, pos.y, pos.z, state);
                    missing.put(desired, 1 + missing.getOrDefault(desired, 0));
                }
            } else if (state.J_1907_R() instanceof s_3834_w) {
                if (!MovementHelper.possiblyFlowing(state)) {
                    sourceLiquids.add(pos);
                } else {
                    flowingLiquids.add(pos);
                }
            } else {
                breakable.add(pos);
            }
        });
        ArrayList toBreak = new ArrayList();
        breakable.forEach(pos -> toBreak.add(this.breakGoal((c_1514_x)pos, bcc)));
        ArrayList toPlace = new ArrayList();
        placeable.forEach(pos -> {
            if (!placeable.contains(pos.down()) && !placeable.contains(pos.down(2))) {
                toPlace.add(this.placementGoal((c_1514_x)pos, bcc));
            }
        });
        sourceLiquids.forEach(pos -> toPlace.add(new GoalBlock(pos.up())));
        if (!toPlace.isEmpty()) {
            return new JankyGoalComposite(new GoalComposite(toPlace.toArray(new Goal[0])), new GoalComposite(toBreak.toArray(new Goal[0])));
        }
        if (toBreak.isEmpty()) {
            if (logMissing && !missing.isEmpty()) {
                this.logDirect("Missing materials for at least:");
                this.logDirect(missing.entrySet().stream().map(e -> String.format("%sx %s", e.getValue(), e.getKey())).collect(Collectors.joining("\n")));
            }
            if (logMissing && !flowingLiquids.isEmpty()) {
                this.logDirect("Unreplaceable liquids at at least:");
                this.logDirect(flowingLiquids.stream().map(p -> String.format("%s %s %s", p.x, p.y, p.z)).collect(Collectors.joining("\n")));
            }
            return null;
        }
        return new GoalComposite(toBreak.toArray(new Goal[0]));
    }

    private Goal placementGoal(c_1514_x pos, BuilderCalculationContext bcc) {
        if (!(this.ctx.world().getBlockState(pos).J_1907_R() instanceof AirBlock)) {
            return new GoalPlace(pos);
        }
        boolean allowSameLevel = !(this.ctx.world().getBlockState(pos.up()).J_1907_R() instanceof AirBlock);
        K_4074_S current = this.ctx.world().getBlockState(pos);
        for (b_257_Y facing : Movement.HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP) {
            if (!MovementHelper.canPlaceAgainst(this.ctx, pos.offset(facing)) || !this.placementPlausible(pos, bcc.getSchematic(pos.getX(), pos.getY(), pos.getZ(), current))) continue;
            return new GoalAdjacent(pos, pos.offset(facing), allowSameLevel);
        }
        return new GoalPlace(pos);
    }

    private Goal breakGoal(c_1514_x pos, BuilderCalculationContext bcc) {
        if (((Boolean)Baritone.settings().goalBreakFromAbove.value).booleanValue() && bcc.bsi.get0(pos.up()).J_1907_R() instanceof AirBlock && bcc.bsi.get0(pos.up(2)).J_1907_R() instanceof AirBlock) {
            return new JankyGoalComposite(new GoalBreak(pos), new GoalGetToBlock(this, pos.up()){

                @Override
                public boolean isInGoal(int x, int y, int z) {
                    if (y > this.y || x == this.x && y == this.y && z == this.z) {
                        return false;
                    }
                    return super.isInGoal(x, y, z);
                }
            });
        }
        return new GoalBreak(pos);
    }

    @Override
    public void onLostControl() {
        this.incorrectPositions = null;
        this.name = null;
        this.schematic = null;
        this.realSchematic = null;
        this.layer = (Integer)Baritone.settings().startAtLayer.value;
        this.numRepeats = 0;
        this.paused = false;
        this.observedCompleted = null;
    }

    @Override
    public String displayName0() {
        return this.paused ? "Builder Paused" : "Building " + this.name;
    }

    private List<K_4074_S> approxPlaceable(int size) {
        ArrayList<K_4074_S> result = new ArrayList<K_4074_S>();
        for (int i = 0; i < size; ++i) {
            Z_1993_T stack = this.ctx.player().l_1268_F.n_1700_B.get(i);
            if (stack.n_1700_B() || !(stack.J_1907_R() instanceof v_1669_V)) {
                result.add(a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
                continue;
            }
            K_4074_S itemState = ((v_1669_V)stack.J_1907_R()).v_4262_N().n_1700_B(new BlockPlaceContext(new UseOnContext(this, this.ctx.world(), this.ctx.player(), x_1688_C.n_1700_B, stack, new BlockHitResult(new e_2866_D(this.ctx.player().s_4990_V().J_1907_R, this.ctx.player().s_4990_V().R_4764_Y, this.ctx.player().s_4990_V().G_564_y), b_257_Y.J_1907_R, this.ctx.playerFeet(), false)){}));
            if (itemState != null) {
                result.add(itemState);
                continue;
            }
            result.add(a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
        }
        return result;
    }

    private boolean sameBlockstate(K_4074_S first, K_4074_S second) {
        if (first.J_1907_R() != second.J_1907_R()) {
            return false;
        }
        boolean ignoreDirection = (Boolean)Baritone.settings().buildIgnoreDirection.value;
        List ignoredProps = (List)Baritone.settings().buildIgnoreProperties.value;
        if (!ignoreDirection && ignoredProps.isEmpty()) {
            return first.equals(second);
        }
        ImmutableMap<v_3760_Q<?>, Comparable<?>> map1 = first.q_2307_F();
        ImmutableMap<v_3760_Q<?>, Comparable<?>> map2 = second.q_2307_F();
        for (v_3760_Q prop : map1.keySet()) {
            if (map1.get((Object)prop) == map2.get((Object)prop) || ignoreDirection && orientationProps.contains(prop) || ignoredProps.contains(prop.P_1922_E())) continue;
            return false;
        }
        return true;
    }

    private boolean containsBlockState(Collection<K_4074_S> states, K_4074_S state) {
        for (K_4074_S testee : states) {
            if (!this.sameBlockstate(testee, state)) continue;
            return true;
        }
        return false;
    }

    private boolean valid(K_4074_S current, K_4074_S desired, boolean itemVerify) {
        if (desired == null) {
            return true;
        }
        if (current.J_1907_R() instanceof s_3834_w && ((Boolean)Baritone.settings().okIfWater.value).booleanValue()) {
            return true;
        }
        if (current.J_1907_R() instanceof AirBlock && desired.J_1907_R() instanceof AirBlock) {
            return true;
        }
        if (current.J_1907_R() instanceof AirBlock && ((List)Baritone.settings().okIfAir.value).contains(desired.J_1907_R())) {
            return true;
        }
        if (desired.J_1907_R() instanceof AirBlock && ((List)Baritone.settings().buildIgnoreBlocks.value).contains(current.J_1907_R())) {
            return true;
        }
        if (!(current.J_1907_R() instanceof AirBlock) && ((Boolean)Baritone.settings().buildIgnoreExisting.value).booleanValue() && !itemVerify) {
            return true;
        }
        if (((List)Baritone.settings().buildSkipBlocks.value).contains(desired.J_1907_R()) && !itemVerify) {
            return true;
        }
        if (((Map)Baritone.settings().buildValidSubstitutes.value).getOrDefault(desired.J_1907_R(), Collections.emptyList()).contains(current.J_1907_R()) && !itemVerify) {
            return true;
        }
        if (current.equals(desired)) {
            return true;
        }
        return this.sameBlockstate(current, desired);
    }

    public class BuilderCalculationContext
    extends CalculationContext {
        private final List<K_4074_S> placeable;
        private final ISchematic schematic;
        private final int originX;
        private final int originY;
        private final int originZ;

        public BuilderCalculationContext() {
            super(BuilderProcess.this.baritone, true);
            this.placeable = BuilderProcess.this.approxPlaceable(9);
            this.schematic = BuilderProcess.this.schematic;
            this.originX = BuilderProcess.this.origin.getX();
            this.originY = BuilderProcess.this.origin.getY();
            this.originZ = BuilderProcess.this.origin.getZ();
            this.jumpPenalty += 10.0;
            this.backtrackCostFavoringCoefficient = 1.0;
        }

        private K_4074_S getSchematic(int x, int y, int z, K_4074_S current) {
            if (this.schematic.inSchematic(x - this.originX, y - this.originY, z - this.originZ, current)) {
                return this.schematic.desiredState(x - this.originX, y - this.originY, z - this.originZ, current, BuilderProcess.this.approxPlaceable);
            }
            return null;
        }

        @Override
        public double costOfPlacingAt(int x, int y, int z, K_4074_S current) {
            if (this.isPossiblyProtected(x, y, z) || !this.worldBorder.canPlaceAt(x, z)) {
                return 1000000.0;
            }
            K_4074_S sch = this.getSchematic(x, y, z, current);
            if (sch != null && !((List)Baritone.settings().buildSkipBlocks.value).contains(sch.J_1907_R())) {
                if (sch.J_1907_R() instanceof AirBlock) {
                    return this.placeBlockCost * 2.0;
                }
                if (this.placeable.contains(sch)) {
                    return 0.0;
                }
                if (!this.hasThrowaway) {
                    return 1000000.0;
                }
                return this.placeBlockCost * 3.0;
            }
            if (this.hasThrowaway) {
                return this.placeBlockCost;
            }
            return 1000000.0;
        }

        @Override
        public double breakCostMultiplierAt(int x, int y, int z, K_4074_S current) {
            if (!this.allowBreak && !this.allowBreakAnyway.contains(current.J_1907_R()) || this.isPossiblyProtected(x, y, z)) {
                return 1000000.0;
            }
            K_4074_S sch = this.getSchematic(x, y, z, current);
            if (sch != null && !((List)Baritone.settings().buildSkipBlocks.value).contains(sch.J_1907_R())) {
                if (sch.J_1907_R() instanceof AirBlock) {
                    return 1.0;
                }
                if (BuilderProcess.this.valid(this.bsi.get0(x, y, z), sch, false)) {
                    return (Double)Baritone.settings().breakCorrectBlockPenaltyMultiplier.value;
                }
                return 1.0;
            }
            return 1.0;
        }
    }

    public static class Placement {
        private final int hotbarSelection;
        private final c_1514_x placeAgainst;
        private final b_257_Y side;
        private final Rotation rot;

        public Placement(int hotbarSelection, c_1514_x placeAgainst, b_257_Y side, Rotation rot) {
            this.hotbarSelection = hotbarSelection;
            this.placeAgainst = placeAgainst;
            this.side = side;
            this.rot = rot;
        }
    }

    public static class JankyGoalComposite
    implements Goal {
        private final Goal primary;
        private final Goal fallback;

        public JankyGoalComposite(Goal primary, Goal fallback) {
            this.primary = primary;
            this.fallback = fallback;
        }

        @Override
        public boolean isInGoal(int x, int y, int z) {
            return this.primary.isInGoal(x, y, z) || this.fallback.isInGoal(x, y, z);
        }

        @Override
        public double heuristic(int x, int y, int z) {
            return this.primary.heuristic(x, y, z);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            JankyGoalComposite goal = (JankyGoalComposite)o;
            return Objects.equals(this.primary, goal.primary) && Objects.equals(this.fallback, goal.fallback);
        }

        public int hashCode() {
            int hash = -1701079641;
            hash = hash * 1196141026 + this.primary.hashCode();
            hash = hash * -80327868 + this.fallback.hashCode();
            return hash;
        }

        public String toString() {
            return "JankyComposite Primary: " + String.valueOf(this.primary) + " Fallback: " + String.valueOf(this.fallback);
        }
    }

    public static class GoalPlace
    extends GoalBlock {
        public GoalPlace(c_1514_x placeAt) {
            super(placeAt.up());
        }

        @Override
        public double heuristic(int x, int y, int z) {
            return (double)(this.y * 100) + super.heuristic(x, y, z);
        }

        @Override
        public int hashCode() {
            return super.hashCode() * 1910811835;
        }

        @Override
        public String toString() {
            return String.format("GoalPlace{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z));
        }
    }

    public static class GoalAdjacent
    extends GoalGetToBlock {
        private boolean allowSameLevel;
        private c_1514_x no;

        public GoalAdjacent(c_1514_x pos, c_1514_x no, boolean allowSameLevel) {
            super(pos);
            this.no = no;
            this.allowSameLevel = allowSameLevel;
        }

        @Override
        public boolean isInGoal(int x, int y, int z) {
            if (x == this.x && y == this.y && z == this.z) {
                return false;
            }
            if (x == this.no.getX() && y == this.no.getY() && z == this.no.getZ()) {
                return false;
            }
            if (!this.allowSameLevel && y == this.y - 1) {
                return false;
            }
            if (y < this.y - 1) {
                return false;
            }
            return super.isInGoal(x, y, z);
        }

        @Override
        public double heuristic(int x, int y, int z) {
            return (double)(this.y * 100) + super.heuristic(x, y, z);
        }

        @Override
        public boolean equals(Object o) {
            if (!super.equals(o)) {
                return false;
            }
            GoalAdjacent goal = (GoalAdjacent)o;
            return this.allowSameLevel == goal.allowSameLevel && Objects.equals(this.no, goal.no);
        }

        @Override
        public int hashCode() {
            int hash = 806368046;
            hash = hash * 1412661222 + super.hashCode();
            hash = hash * 1730799370 + (int)BetterBlockPos.longHash(this.no.getX(), this.no.getY(), this.no.getZ());
            hash = hash * 260592149 + (this.allowSameLevel ? -1314802005 : 1565710265);
            return hash;
        }

        @Override
        public String toString() {
            return String.format("GoalAdjacent{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z));
        }
    }

    public static class GoalBreak
    extends GoalGetToBlock {
        public GoalBreak(c_1514_x pos) {
            super(pos);
        }

        @Override
        public boolean isInGoal(int x, int y, int z) {
            if (y > this.y) {
                return false;
            }
            return super.isInGoal(x, y, z);
        }

        @Override
        public String toString() {
            return String.format("GoalBreak{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z));
        }

        @Override
        public int hashCode() {
            return super.hashCode() * 1636324008;
        }
    }
}


