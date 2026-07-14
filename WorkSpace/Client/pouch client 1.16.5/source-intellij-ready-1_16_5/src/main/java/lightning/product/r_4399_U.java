/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  lombok.Generated
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.BooleanSupplier;
import javax.annotation.Nullable;
import lightning.product.ChunkSource;
import lightning.product.ChunkStatus;
import lightning.product.BlockGetter;
import lightning.product.H_1748_a;
import lightning.product.K_4719_o;
import lightning.product.N_4263_v;
import lightning.product.P_3550_Z;
import lightning.product.R_1900_x;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.b_2585_i;
import lightning.product.b_4507_u;
import lightning.product.c_1108_W;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_3005_b;
import lightning.product.SectionPos;
import lightning.product.ChunkAccess;
import lightning.product.EmptyLevelChunk;
import lightning.product.k_4690_i;
import lightning.product.u_530_F;
import lombok.Generated;
import mods.baritone.utils.accessor.IChunkArray;
import mods.baritone.utils.accessor.IClientChunkProvider;
import net.optifine.ChunkDataOF;
import net.optifine.ChunkOF;
import net.optifine.reflect.Reflector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class r_4399_U
extends ChunkSource
implements IClientChunkProvider {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final H_1748_a J_1907_R;
    private final R_1900_x R_4764_Y;
    private volatile n_1700_B G_564_y;
    private final b_4507_u P_1922_E;

    public r_4399_U(b_4507_u clientWorldIn, int viewDistance) {
        this.P_1922_E = clientWorldIn;
        this.J_1907_R = new EmptyLevelChunk(clientWorldIn, new Y_1387_d(0, 0));
        this.R_4764_Y = new R_1900_x(this, true, clientWorldIn.G_624_v().J_1907_R());
        this.G_564_y = new n_1700_B(r_4399_U.J_1907_R(viewDistance));
    }

    private static boolean n_1700_B(@Nullable H_1748_a chunkIn, int x, int z) {
        if (chunkIn == null) {
            return false;
        }
        Y_1387_d chunkpos = chunkIn.getPos();
        return chunkpos.J_1907_R == x && chunkpos.R_4764_Y == z;
    }

    public void n_1700_B(int x, int z) {
        int i;
        H_1748_a chunk;
        if (this.G_564_y.J_1907_R(x, z) && r_4399_U.n_1700_B(chunk = this.G_564_y.n_1700_B(i = this.G_564_y.n_1700_B(x, z)), x, z)) {
            if (Reflector.ChunkEvent_Unload_Constructor.exists()) {
                Reflector.postForgeBusEvent(Reflector.ChunkEvent_Unload_Constructor, chunk);
            }
            chunk.setLoaded(false);
            this.G_564_y.n_1700_B(i, chunk, null);
        }
    }

    @Nullable
    public H_1748_a n_1700_B(int chunkX, int chunkZ, ChunkStatus requiredStatus, boolean load) {
        H_1748_a chunk;
        if (this.G_564_y.J_1907_R(chunkX, chunkZ) && r_4399_U.n_1700_B(chunk = this.G_564_y.n_1700_B(this.G_564_y.n_1700_B(chunkX, chunkZ)), chunkX, chunkZ)) {
            return chunk;
        }
        return load ? this.J_1907_R : null;
    }

    @Override
    public BlockGetter n_1700_B() {
        return this.P_1922_E;
    }

    @Nullable
    public H_1748_a n_1700_B(int chunkX, int chunkZ, @Nullable c_1108_W biomeContainerIn, b_2585_i packetIn, U_2912_j nbtTagIn, int sizeIn, boolean p_228313_7_) {
        if (!this.G_564_y.J_1907_R(chunkX, chunkZ)) {
            n_1700_B.warn("Ignoring chunk since it's not in the view range: {}, {}", (Object)chunkX, (Object)chunkZ);
            return null;
        }
        int i = this.G_564_y.n_1700_B(chunkX, chunkZ);
        H_1748_a chunk = this.G_564_y.J_1907_R.get(i);
        if (!p_228313_7_ && r_4399_U.n_1700_B(chunk, chunkX, chunkZ)) {
            boolean flag = false;
            if (chunk instanceof ChunkOF) {
                ChunkOF chunkof = (ChunkOF)chunk;
                Object object = packetIn.J_1907_R("ChunkDataOF");
                if (object instanceof ChunkDataOF) {
                    ChunkDataOF chunkdataof = (ChunkDataOF)object;
                    chunkof.setChunkDataOF(chunkdataof);
                    P_3550_Z.n_1700_B.set(chunkdataof);
                    flag = true;
                }
            }
            chunk.read(biomeContainerIn, packetIn, nbtTagIn, sizeIn);
            if (flag) {
                P_3550_Z.n_1700_B.set(null);
            }
        } else {
            if (biomeContainerIn == null) {
                n_1700_B.warn("Ignoring chunk since we don't have complete data: {}, {}", (Object)chunkX, (Object)chunkZ);
                return null;
            }
            if (chunk != null) {
                chunk.setLoaded(false);
            }
            chunk = new ChunkOF(this.P_1922_E, new Y_1387_d(chunkX, chunkZ), biomeContainerIn);
            chunk.read(biomeContainerIn, packetIn, nbtTagIn, sizeIn);
            this.G_564_y.n_1700_B(i, chunk);
        }
        P_3550_Z[] achunksection = chunk.getSections();
        R_1900_x worldlightmanager = this.G_564_y();
        worldlightmanager.n_1700_B(new Y_1387_d(chunkX, chunkZ), true);
        for (int j = 0; j < achunksection.length; ++j) {
            P_3550_Z chunksection = achunksection[j];
            worldlightmanager.n_1700_B(SectionPos.n_1700_B(chunkX, j, chunkZ), P_3550_Z.n_1700_B(chunksection));
        }
        if (this.P_1922_E instanceof k_4690_i) {
            ((k_4690_i)this.P_1922_E).n_1700_B(chunkX, chunkZ);
        } else if (this.P_1922_E instanceof c_3005_b) {
            ((c_3005_b)this.P_1922_E).n_1700_B(chunkX, chunkZ);
        }
        if (Reflector.ChunkEvent_Load_Constructor.exists()) {
            Reflector.postForgeBusEvent(Reflector.ChunkEvent_Load_Constructor, chunk);
        }
        chunk.setLoaded(true);
        return chunk;
    }

    public void n_1700_B(BooleanSupplier hasTimeLeft) {
    }

    public void J_1907_R(int x, int z) {
        this.G_564_y.P_1922_E = x;
        this.G_564_y.u_1723_Y = z;
    }

    public void n_1700_B(int viewDistance) {
        int i = this.G_564_y.R_4764_Y;
        int j = r_4399_U.J_1907_R(viewDistance);
        if (i != j) {
            n_1700_B clientchunkprovider$chunkarray = new n_1700_B(j);
            clientchunkprovider$chunkarray.P_1922_E = this.G_564_y.P_1922_E;
            clientchunkprovider$chunkarray.u_1723_Y = this.G_564_y.u_1723_Y;
            for (int k = 0; k < this.G_564_y.J_1907_R.length(); ++k) {
                H_1748_a chunk = this.G_564_y.J_1907_R.get(k);
                if (chunk == null) continue;
                Y_1387_d chunkpos = chunk.getPos();
                if (!clientchunkprovider$chunkarray.J_1907_R(chunkpos.J_1907_R, chunkpos.R_4764_Y)) continue;
                clientchunkprovider$chunkarray.n_1700_B(clientchunkprovider$chunkarray.n_1700_B(chunkpos.J_1907_R, chunkpos.R_4764_Y), chunk);
            }
            this.G_564_y = clientchunkprovider$chunkarray;
        }
    }

    private static int J_1907_R(int p_217254_0_) {
        return Math.max(2, p_217254_0_) + 3;
    }

    @Override
    public String J_1907_R() {
        return "Client Chunk Cache: " + this.G_564_y.J_1907_R.length() + ", " + this.R_4764_Y();
    }

    public int R_4764_Y() {
        return this.G_564_y.v_4262_N;
    }

    @Override
    public void n_1700_B(K_4719_o type, SectionPos pos) {
        MinecraftClient.A_4115_X().u_1723_Y.J_1907_R(pos.n_1700_B(), pos.J_1907_R(), pos.R_4764_Y());
    }

    @Override
    public boolean n_1700_B(c_1514_x pos) {
        return this.P_1922_E(pos.getX() >> 4, pos.getZ() >> 4);
    }

    @Override
    public boolean n_1700_B(Y_1387_d pos) {
        return this.P_1922_E(pos.J_1907_R, pos.R_4764_Y);
    }

    @Override
    public boolean n_1700_B(N_4263_v entityIn) {
        return this.P_1922_E(u_530_F.R_4764_Y(entityIn.O_3598_v()) >> 4, u_530_F.R_4764_Y(entityIn.l_2647_k()) >> 4);
    }

    @Override
    public r_4399_U createThreadSafeCopy() {
        r_4399_U result;
        IChunkArray arr = this.extractReferenceArray();
        if (this.P_1922_E instanceof k_4690_i) {
            result = new r_4399_U((k_4690_i)this.P_1922_E, arr.viewDistance() - 3);
        } else if (this.P_1922_E instanceof c_3005_b) {
            result = new r_4399_U((c_3005_b)this.P_1922_E, arr.viewDistance() - 3);
        } else {
            throw new IllegalStateException("Unexpected world type: " + this.P_1922_E.getClass().getName());
        }
        IChunkArray copyArr = result.extractReferenceArray();
        copyArr.copyFrom(arr);
        if (copyArr.viewDistance() != arr.viewDistance()) {
            throw new IllegalStateException(copyArr.viewDistance() + " " + arr.viewDistance());
        }
        return result;
    }

    @Override
    public IChunkArray extractReferenceArray() {
        for (Field f : r_4399_U.class.getDeclaredFields()) {
            if (!IChunkArray.class.isAssignableFrom(f.getType())) continue;
            try {
                return (IChunkArray)f.get(this);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException(Arrays.toString(r_4399_U.class.getDeclaredFields()));
    }

    @Override
    @Generated
    public R_1900_x G_564_y() {
        return this.R_4764_Y;
    }

    @Override
    @Nullable
    public /* synthetic */ ChunkAccess J_1907_R(int n, int n2, ChunkStatus d_2560_i, boolean bl) {
        return this.n_1700_B(n, n2, d_2560_i, bl);
    }

    final class n_1700_B
    implements IChunkArray {
        private final AtomicReferenceArray<H_1748_a> J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;
        private volatile int P_1922_E;
        private volatile int u_1723_Y;
        private int v_4262_N;

        private n_1700_B(int viewDistanceIn) {
            this.R_4764_Y = viewDistanceIn;
            this.G_564_y = viewDistanceIn * 2 + 1;
            this.J_1907_R = new AtomicReferenceArray(this.G_564_y * this.G_564_y);
        }

        private int n_1700_B(int x, int z) {
            return Math.floorMod(z, this.G_564_y) * this.G_564_y + Math.floorMod(x, this.G_564_y);
        }

        protected void n_1700_B(int chunkIndex, @Nullable H_1748_a chunkIn) {
            H_1748_a chunk = this.J_1907_R.getAndSet(chunkIndex, chunkIn);
            if (chunk != null) {
                --this.v_4262_N;
                if (r_4399_U.this.P_1922_E instanceof k_4690_i) {
                    ((k_4690_i)r_4399_U.this.P_1922_E).n_1700_B(chunk);
                } else if (r_4399_U.this.P_1922_E instanceof c_3005_b) {
                    ((c_3005_b)r_4399_U.this.P_1922_E).n_1700_B(chunk);
                }
            }
            if (chunkIn != null) {
                ++this.v_4262_N;
            }
        }

        protected H_1748_a n_1700_B(int chunkIndex, H_1748_a chunkIn, @Nullable H_1748_a replaceWith) {
            if (this.J_1907_R.compareAndSet(chunkIndex, chunkIn, replaceWith) && replaceWith == null) {
                --this.v_4262_N;
            }
            if (r_4399_U.this.P_1922_E instanceof k_4690_i) {
                ((k_4690_i)r_4399_U.this.P_1922_E).n_1700_B(chunkIn);
            } else if (r_4399_U.this.P_1922_E instanceof c_3005_b) {
                ((c_3005_b)r_4399_U.this.P_1922_E).n_1700_B(chunkIn);
            }
            return chunkIn;
        }

        private boolean J_1907_R(int x, int z) {
            return Math.abs(x - this.P_1922_E) <= this.R_4764_Y && Math.abs(z - this.u_1723_Y) <= this.R_4764_Y;
        }

        @Nullable
        protected H_1748_a n_1700_B(int chunkIndex) {
            return this.J_1907_R.get(chunkIndex);
        }

        @Override
        public int centerX() {
            return this.P_1922_E;
        }

        @Override
        public int centerZ() {
            return this.u_1723_Y;
        }

        @Override
        public int viewDistance() {
            return this.R_4764_Y;
        }

        @Override
        public AtomicReferenceArray<H_1748_a> getChunks() {
            return this.J_1907_R;
        }

        @Override
        public void copyFrom(IChunkArray other) {
            this.P_1922_E = other.centerX();
            this.u_1723_Y = other.centerZ();
            AtomicReferenceArray<H_1748_a> copyingFrom = other.getChunks();
            for (int k = 0; k < copyingFrom.length(); ++k) {
                H_1748_a chunk = copyingFrom.get(k);
                if (chunk == null) continue;
                Y_1387_d chunkpos = chunk.getPos();
                if (!this.J_1907_R(chunkpos.J_1907_R, chunkpos.R_4764_Y)) continue;
                int index = this.n_1700_B(chunkpos.J_1907_R, chunkpos.R_4764_Y);
                if (this.J_1907_R.get(index) != null) {
                    throw new IllegalStateException("Doing this would mutate the client's REAL loaded chunks?!");
                }
                this.n_1700_B(index, chunk);
            }
        }
    }
}



