/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.SystemUtils
 */
package mods.baritone.cache;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lightning.product.H_4757_Q;
import lightning.product.Tuple;
import lightning.product.ServerData;
import lightning.product.Z_3903_F;
import lightning.product.b_4507_u;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.cache.IWorldProvider;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;
import mods.baritone.cache.WorldData;
import org.apache.commons.lang3.SystemUtils;

public class WorldProvider
implements IWorldProvider {
    private static final Map<Path, WorldData> worldCache = new HashMap<Path, WorldData>();
    private final Baritone baritone;
    private final IPlayerContext ctx;
    private WorldData currentWorld;
    private b_4507_u mcWorld;

    public WorldProvider(Baritone baritone) {
        this.baritone = baritone;
        this.ctx = baritone.getPlayerContext();
    }

    @Override
    public final WorldData getCurrentWorld() {
        this.detectAndHandleBrokenLoading();
        return this.currentWorld;
    }

    public final void initWorld(b_4507_u world) {
        this.getSaveDirectories(world).ifPresent(dirs -> {
            Path worldDir = (Path)dirs.n_1700_B();
            Path readmeDir = (Path)dirs.J_1907_R();
            try {
                Files.createDirectories(readmeDir, new FileAttribute[0]);
                Files.write(readmeDir.resolve("readme.txt"), "https://github.com/cabaletta/baritone\n".getBytes(StandardCharsets.US_ASCII), new OpenOption[0]);
            }
            catch (IOException iOException) {
                // empty catch block
            }
            Path worldDataDir = this.getWorldDataDirectory(worldDir, world);
            try {
                Files.createDirectories(worldDataDir, new FileAttribute[0]);
            }
            catch (IOException iOException) {
                // empty catch block
            }
            System.out.println("Baritone world data dir: " + String.valueOf(worldDataDir));
            Map<Path, WorldData> map = worldCache;
            synchronized (map) {
                this.currentWorld = worldCache.computeIfAbsent(worldDataDir, d -> new WorldData((Path)d, world.g_2268_R()));
            }
            this.mcWorld = this.ctx.world();
        });
    }

    public final void closeWorld() {
        WorldData world = this.currentWorld;
        this.currentWorld = null;
        this.mcWorld = null;
        if (world == null) {
            return;
        }
        world.onClose();
    }

    private Path getWorldDataDirectory(Path parent, b_4507_u world) {
        return Z_3903_F.n_1700_B(world.g_2268_R(), parent.toFile()).toPath();
    }

    private Optional<Tuple<Path, Path>> getSaveDirectories(b_4507_u world) {
        Path readmeDir;
        Path worldDir;
        if (this.ctx.minecraft().e_4240_b()) {
            worldDir = this.ctx.minecraft().n_3318_d().n_1700_B(H_4757_Q.t_148_a);
            if (worldDir.relativize(this.ctx.minecraft().M_182_A.toPath()).getNameCount() != 2) {
                worldDir = worldDir.getParent();
            }
            readmeDir = worldDir = worldDir.resolve("baritone");
        } else {
            String folderName;
            ServerData serverData = this.ctx.minecraft().t_4043_B();
            if (serverData != null) {
                folderName = this.ctx.minecraft().Ping() ? "realms" : serverData.J_1907_R;
                String serverIP = serverData.J_1907_R.toLowerCase();
                if (serverIP.contains("cakeworld.pw") || serverIP.contains("lonygrief.me")) {
                    this.currentWorld = null;
                    this.mcWorld = this.ctx.world();
                    return Optional.empty();
                }
            } else {
                this.currentWorld = null;
                this.mcWorld = this.ctx.world();
                return Optional.empty();
            }
            if (SystemUtils.IS_OS_WINDOWS) {
                folderName = folderName.replace(":", "_");
            }
            worldDir = this.baritone.getDirectory().resolve(folderName);
            readmeDir = this.baritone.getDirectory();
        }
        return Optional.of(new Tuple<Path, Path>(worldDir, readmeDir));
    }

    private void detectAndHandleBrokenLoading() {
        if (this.mcWorld != this.ctx.world()) {
            if (this.currentWorld != null) {
                this.closeWorld();
            }
            if (this.ctx.world() != null) {
                this.initWorld(this.ctx.world());
            }
        } else if (this.currentWorld == null && this.ctx.world() != null && (this.ctx.minecraft().e_4240_b() || this.ctx.minecraft().t_4043_B() != null)) {
            this.initWorld(this.ctx.world());
        }
    }
}


