/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 */
package mods.baritone.process;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.cache.ICachedWorld;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalComposite;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalXZ;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalYLevel;
import mods.baritone.api.api.java.baritone.api.process.IExploreProcess;
import mods.baritone.api.api.java.baritone.api.process.PathingCommand;
import mods.baritone.api.api.java.baritone.api.process.PathingCommandType;
import mods.baritone.api.api.java.baritone.api.utils.MyChunkPos;
import mods.baritone.cache.CachedWorld;
import mods.baritone.utils.BaritoneProcessHelper;

public final class ExploreProcess
extends BaritoneProcessHelper
implements IExploreProcess {
    private c_1514_x explorationOrigin;
    private IChunkFilter filter;
    private int distanceCompleted;

    public ExploreProcess(Baritone baritone) {
        super(baritone);
    }

    @Override
    public boolean isActive() {
        return this.explorationOrigin != null;
    }

    @Override
    public void explore(int centerX, int centerZ) {
        this.explorationOrigin = new c_1514_x(centerX, 0, centerZ);
        this.distanceCompleted = 0;
    }

    @Override
    public void applyJsonFilter(Path path, boolean invert) throws Exception {
        this.filter = new JsonChunkFilter(path, invert);
    }

    public IChunkFilter calcFilter() {
        IChunkFilter filter = this.filter != null ? new EitherChunk(this, this.filter, new BaritoneChunkCache()) : new BaritoneChunkCache();
        return filter;
    }

    @Override
    public PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel) {
        if (calcFailed) {
            this.logDirect("Failed");
            if (((Boolean)Baritone.settings().notificationOnExploreFinished.value).booleanValue()) {
                this.logNotification("Exploration failed", true);
            }
            this.onLostControl();
            return null;
        }
        IChunkFilter filter = this.calcFilter();
        if (!((Boolean)Baritone.settings().disableCompletionCheck.value).booleanValue() && filter.countRemain() == 0) {
            this.logDirect("Explored all chunks");
            if (((Boolean)Baritone.settings().notificationOnExploreFinished.value).booleanValue()) {
                this.logNotification("Explored all chunks", false);
            }
            this.onLostControl();
            return null;
        }
        Goal[] closestUncached = this.closestUncachedChunks(this.explorationOrigin, filter);
        if (closestUncached == null) {
            this.logDebug("awaiting region load from disk");
            return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
        }
        return new PathingCommand(new GoalComposite(closestUncached), PathingCommandType.FORCE_REVALIDATE_GOAL_AND_PATH);
    }

    /*
     * Enabled aggressive block sorting
     */
    private Goal[] closestUncachedChunks(c_1514_x center, IChunkFilter filter) {
        int chunkX = center.getX() >> 4;
        int chunkZ = center.getZ() >> 4;
        int count = Math.min(filter.countRemain(), (Integer)Baritone.settings().exploreChunkSetMinimumSize.value);
        ArrayList<c_1514_x> centers = new ArrayList<c_1514_x>();
        int renderDistance = (Integer)Baritone.settings().worldExploringChunkOffset.value;
        int dist = this.distanceCompleted;
        block5: while (true) {
            int dx = -dist;
            while (true) {
                int zval;
                if (dx <= dist) {
                    zval = dist - Math.abs(dx);
                } else {
                    if (dist % 10 == 0) {
                        count = Math.min(filter.countRemain(), (Integer)Baritone.settings().exploreChunkSetMinimumSize.value);
                    }
                    if (centers.size() >= count) {
                        return (Goal[])centers.stream().map(pos -> ExploreProcess.createGoal(pos.getX(), pos.getZ())).toArray(Goal[]::new);
                    }
                    if (centers.isEmpty()) {
                        this.distanceCompleted = dist + 1;
                    }
                    ++dist;
                    continue block5;
                }
                block7: for (int mult = 0; mult < 2; ++mult) {
                    int dz = (mult * 2 - 1) * zval;
                    int trueDist = Math.abs(dx) + Math.abs(dz);
                    if (trueDist != dist) {
                        throw new IllegalStateException();
                    }
                    switch (filter.isAlreadyExplored(chunkX + dx, chunkZ + dz).ordinal()) {
                        case 2: {
                            return null;
                        }
                        case 1: {
                            break;
                        }
                        case 0: {
                            continue block7;
                        }
                    }
                    int centerX = (chunkX + dx << 4) + 8;
                    int centerZ = (chunkZ + dz << 4) + 8;
                    int offset = renderDistance << 4;
                    centerX = dx < 0 ? (centerX -= offset) : (centerX += offset);
                    centerZ = dz < 0 ? (centerZ -= offset) : (centerZ += offset);
                    centers.add(new c_1514_x(centerX, 0, centerZ));
                }
                ++dx;
            }
            break;
        }
    }

    private static Goal createGoal(int x, int z) {
        if ((Integer)Baritone.settings().exploreMaintainY.value == -1) {
            return new GoalXZ(x, z);
        }
        return new GoalXZ(x, z){

            @Override
            public double heuristic(int x, int y, int z) {
                return super.heuristic(x, y, z) + GoalYLevel.calculate((Integer)Baritone.settings().exploreMaintainY.value, y);
            }
        };
    }

    @Override
    public void onLostControl() {
        this.explorationOrigin = null;
    }

    @Override
    public String displayName0() {
        return "Exploring around " + String.valueOf(this.explorationOrigin) + ", distance completed " + this.distanceCompleted + ", currently going to " + String.valueOf(new GoalComposite(this.closestUncachedChunks(this.explorationOrigin, this.calcFilter())));
    }

    private class JsonChunkFilter
    implements IChunkFilter {
        private final boolean invert;
        private final LongOpenHashSet inFilter;
        private final MyChunkPos[] positions;

        private JsonChunkFilter(Path path, boolean invert) throws Exception {
            this.invert = invert;
            Gson gson = new GsonBuilder().create();
            this.positions = (MyChunkPos[])gson.fromJson((Reader)new InputStreamReader(Files.newInputStream(path, new OpenOption[0])), MyChunkPos[].class);
            ExploreProcess.this.logDirect("Loaded " + this.positions.length + " positions");
            this.inFilter = new LongOpenHashSet();
            for (MyChunkPos mcp : this.positions) {
                this.inFilter.add(Y_1387_d.n_1700_B(mcp.x, mcp.z));
            }
        }

        @Override
        public Status isAlreadyExplored(int chunkX, int chunkZ) {
            if (this.inFilter.contains(Y_1387_d.n_1700_B(chunkX, chunkZ)) ^ this.invert) {
                return Status.EXPLORED;
            }
            return Status.UNKNOWN;
        }

        @Override
        public int countRemain() {
            if (!this.invert) {
                return Integer.MAX_VALUE;
            }
            int countRemain = 0;
            BaritoneChunkCache bcc = new BaritoneChunkCache();
            for (MyChunkPos pos : this.positions) {
                if (bcc.isAlreadyExplored(pos.x, pos.z) == Status.EXPLORED || ++countRemain < (Integer)Baritone.settings().exploreChunkSetMinimumSize.value) continue;
                return countRemain;
            }
            return countRemain;
        }
    }

    private static interface IChunkFilter {
        public Status isAlreadyExplored(int var1, int var2);

        public int countRemain();
    }

    private class EitherChunk
    implements IChunkFilter {
        private final IChunkFilter a;
        private final IChunkFilter b;

        private EitherChunk(ExploreProcess exploreProcess, IChunkFilter a, IChunkFilter b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Status isAlreadyExplored(int chunkX, int chunkZ) {
            if (this.a.isAlreadyExplored(chunkX, chunkZ) == Status.EXPLORED) {
                return Status.EXPLORED;
            }
            return this.b.isAlreadyExplored(chunkX, chunkZ);
        }

        @Override
        public int countRemain() {
            return Math.min(this.a.countRemain(), this.b.countRemain());
        }
    }

    private class BaritoneChunkCache
    implements IChunkFilter {
        private final ICachedWorld cache;

        private BaritoneChunkCache() {
            this.cache = ExploreProcess.this.baritone.getWorldProvider().getCurrentWorld().getCachedWorld();
        }

        @Override
        public Status isAlreadyExplored(int chunkX, int chunkZ) {
            int centerX = chunkX << 4;
            int centerZ = chunkZ << 4;
            if (this.cache.isCached(centerX, centerZ)) {
                return Status.EXPLORED;
            }
            if (!((CachedWorld)this.cache).regionLoaded(centerX, centerZ)) {
                Baritone.getExecutor().execute(() -> ((CachedWorld)this.cache).tryLoadFromDisk(centerX >> 9, centerZ >> 9));
                return Status.UNKNOWN;
            }
            return Status.NOT_EXPLORED;
        }

        @Override
        public int countRemain() {
            return Integer.MAX_VALUE;
        }
    }

    private static enum Status {
        EXPLORED,
        NOT_EXPLORED,
        UNKNOWN;

    }
}

