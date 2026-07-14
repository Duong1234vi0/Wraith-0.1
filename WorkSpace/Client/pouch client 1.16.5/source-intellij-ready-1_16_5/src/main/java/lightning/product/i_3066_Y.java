/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import javax.annotation.Nullable;
import lightning.product.Y_1387_d;
import lightning.product.j_3341_s;
import lightning.product.RegionBitmap;
import lightning.product.RegionFileVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class i_3066_Y
implements AutoCloseable {
    private static final Logger J_1907_R = LogManager.getLogger();
    private static final ByteBuffer R_4764_Y = ByteBuffer.allocateDirect(1);
    private final FileChannel G_564_y;
    private final Path P_1922_E;
    private final RegionFileVersion u_1723_Y;
    private final ByteBuffer v_4262_N = ByteBuffer.allocateDirect(8192);
    private final IntBuffer w_1484_f;
    private final IntBuffer t_148_a;
    @VisibleForTesting
    protected final RegionBitmap n_1700_B = new RegionBitmap();

    public i_3066_Y(File p_i231893_1_, File p_i231893_2_, boolean p_i231893_3_) throws IOException {
        this(p_i231893_1_.toPath(), p_i231893_2_.toPath(), RegionFileVersion.J_1907_R, p_i231893_3_);
    }

    public i_3066_Y(Path p_i231894_1_, Path p_i231894_2_, RegionFileVersion p_i231894_3_, boolean p_i231894_4_) throws IOException {
        this.u_1723_Y = p_i231894_3_;
        if (!Files.isDirectory(p_i231894_2_, new LinkOption[0])) {
            throw new IllegalArgumentException("Expected directory, got " + String.valueOf(p_i231894_2_.toAbsolutePath()));
        }
        this.P_1922_E = p_i231894_2_;
        this.w_1484_f = this.v_4262_N.asIntBuffer();
        ((Buffer)this.w_1484_f).limit(1024);
        ((Buffer)this.v_4262_N).position(4096);
        this.t_148_a = this.v_4262_N.asIntBuffer();
        this.G_564_y = p_i231894_4_ ? FileChannel.open(p_i231894_1_, StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.DSYNC) : FileChannel.open(p_i231894_1_, StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE);
        this.n_1700_B.n_1700_B(0, 2);
        ((Buffer)this.v_4262_N).position(0);
        int i = this.G_564_y.read(this.v_4262_N, 0L);
        if (i != -1) {
            if (i != 8192) {
                J_1907_R.warn("Region file {} has truncated header: {}", (Object)p_i231894_1_, (Object)i);
            }
            long j = Files.size(p_i231894_1_);
            for (int k = 0; k < 1024; ++k) {
                int l = this.w_1484_f.get(k);
                if (l == 0) continue;
                int i1 = i_3066_Y.J_1907_R(l);
                int j1 = i_3066_Y.n_1700_B(l);
                if (i1 < 2) {
                    J_1907_R.warn("Region file {} has invalid sector at index: {}; sector {} overlaps with header", (Object)p_i231894_1_, (Object)k, (Object)i1);
                    this.w_1484_f.put(k, 0);
                    continue;
                }
                if (j1 == 0) {
                    J_1907_R.warn("Region file {} has an invalid sector at index: {}; size has to be > 0", (Object)p_i231894_1_, (Object)k);
                    this.w_1484_f.put(k, 0);
                    continue;
                }
                if ((long)i1 * 4096L > j) {
                    J_1907_R.warn("Region file {} has an invalid sector at index: {}; sector {} is out of bounds", (Object)p_i231894_1_, (Object)k, (Object)i1);
                    this.w_1484_f.put(k, 0);
                    continue;
                }
                this.n_1700_B.n_1700_B(i1, j1);
            }
        }
    }

    private Path P_1922_E(Y_1387_d p_227145_1_) {
        String s = "c." + p_227145_1_.J_1907_R + "." + p_227145_1_.R_4764_Y + ".mcc";
        return this.P_1922_E.resolve(s);
    }

    @Nullable
    public synchronized DataInputStream n_1700_B(Y_1387_d pos) throws IOException {
        int i = this.u_1723_Y(pos);
        if (i == 0) {
            return null;
        }
        int j = i_3066_Y.J_1907_R(i);
        int k = i_3066_Y.n_1700_B(i);
        int l = k * 4096;
        ByteBuffer bytebuffer = ByteBuffer.allocate(l);
        this.G_564_y.read(bytebuffer, j * 4096);
        ((Buffer)bytebuffer).flip();
        if (bytebuffer.remaining() < 5) {
            J_1907_R.error("Chunk {} header is truncated: expected {} but read {}", (Object)pos, (Object)l, (Object)bytebuffer.remaining());
            return null;
        }
        int i1 = bytebuffer.getInt();
        byte b0 = bytebuffer.get();
        if (i1 == 0) {
            J_1907_R.warn("Chunk {} is allocated, but stream is missing", (Object)pos);
            return null;
        }
        int j1 = i1 - 1;
        if (i_3066_Y.n_1700_B(b0)) {
            if (j1 != 0) {
                J_1907_R.warn("Chunk has both internal and external streams");
            }
            return this.n_1700_B(pos, i_3066_Y.J_1907_R(b0));
        }
        if (j1 > bytebuffer.remaining()) {
            J_1907_R.error("Chunk {} stream is truncated: expected {} but read {}", (Object)pos, (Object)j1, (Object)bytebuffer.remaining());
            return null;
        }
        if (j1 < 0) {
            J_1907_R.error("Declared size {} of chunk {} is negative", (Object)i1, (Object)pos);
            return null;
        }
        return this.n_1700_B(pos, b0, i_3066_Y.n_1700_B(bytebuffer, j1));
    }

    private static boolean n_1700_B(byte p_227130_0_) {
        return (p_227130_0_ & 0x80) != 0;
    }

    private static byte J_1907_R(byte p_227141_0_) {
        return (byte)(p_227141_0_ & 0xFFFFFF7F);
    }

    @Nullable
    private DataInputStream n_1700_B(Y_1387_d p_227134_1_, byte p_227134_2_, InputStream p_227134_3_) throws IOException {
        RegionFileVersion regionfileversion = RegionFileVersion.n_1700_B(p_227134_2_);
        if (regionfileversion == null) {
            J_1907_R.error("Chunk {} has invalid chunk stream version {}", (Object)p_227134_1_, (Object)p_227134_2_);
            return null;
        }
        return new DataInputStream(new BufferedInputStream(regionfileversion.n_1700_B(p_227134_3_)));
    }

    @Nullable
    private DataInputStream n_1700_B(Y_1387_d p_227133_1_, byte p_227133_2_) throws IOException {
        Path path = this.P_1922_E(p_227133_1_);
        if (!Files.isRegularFile(path, new LinkOption[0])) {
            J_1907_R.error("External chunk path {} is not file", (Object)path);
            return null;
        }
        return this.n_1700_B(p_227133_1_, p_227133_2_, Files.newInputStream(path, new OpenOption[0]));
    }

    private static ByteArrayInputStream n_1700_B(ByteBuffer p_227137_0_, int p_227137_1_) {
        return new ByteArrayInputStream(p_227137_0_.array(), p_227137_0_.position(), p_227137_1_);
    }

    private int n_1700_B(int p_227132_1_, int p_227132_2_) {
        return p_227132_1_ << 8 | p_227132_2_;
    }

    private static int n_1700_B(int p_227131_0_) {
        return p_227131_0_ & 0xFF;
    }

    private static int J_1907_R(int p_227142_0_) {
        return p_227142_0_ >> 8 & 0xFFFFFF;
    }

    private static int R_4764_Y(int p_227144_0_) {
        return (p_227144_0_ + 4096 - 1) / 4096;
    }

    public boolean J_1907_R(Y_1387_d p_222662_1_) {
        int i = this.u_1723_Y(p_222662_1_);
        if (i == 0) {
            return false;
        }
        int j = i_3066_Y.J_1907_R(i);
        int k = i_3066_Y.n_1700_B(i);
        ByteBuffer bytebuffer = ByteBuffer.allocate(5);
        try {
            this.G_564_y.read(bytebuffer, j * 4096);
            ((Buffer)bytebuffer).flip();
            if (bytebuffer.remaining() != 5) {
                return false;
            }
            int l = bytebuffer.getInt();
            byte b0 = bytebuffer.get();
            if (i_3066_Y.n_1700_B(b0)) {
                if (!RegionFileVersion.J_1907_R(i_3066_Y.J_1907_R(b0))) {
                    return false;
                }
                if (!Files.isRegularFile(this.P_1922_E(p_222662_1_), new LinkOption[0])) {
                    return false;
                }
            } else {
                if (!RegionFileVersion.J_1907_R(b0)) {
                    return false;
                }
                if (l == 0) {
                    return false;
                }
                int i1 = l - 1;
                if (i1 < 0 || i1 > 4096 * k) {
                    return false;
                }
            }
            return true;
        }
        catch (IOException ioexception) {
            return false;
        }
    }

    public DataOutputStream R_4764_Y(Y_1387_d p_222661_1_) throws IOException {
        return new DataOutputStream(new BufferedOutputStream(this.u_1723_Y.n_1700_B(new n_1700_B(p_222661_1_))));
    }

    public void n_1700_B() throws IOException {
        this.G_564_y.force(true);
    }

    protected synchronized void n_1700_B(Y_1387_d p_227135_1_, ByteBuffer p_227135_2_) throws IOException {
        J_1907_R regionfile$icompletecallback;
        int k1;
        int i = i_3066_Y.v_4262_N(p_227135_1_);
        int j = this.w_1484_f.get(i);
        int k = i_3066_Y.J_1907_R(j);
        int l = i_3066_Y.n_1700_B(j);
        int i1 = p_227135_2_.remaining();
        int j1 = i_3066_Y.R_4764_Y(i1);
        if (j1 >= 256) {
            Path path = this.P_1922_E(p_227135_1_);
            J_1907_R.warn("Saving oversized chunk {} ({} bytes} to external file {}", (Object)p_227135_1_, (Object)i1, (Object)path);
            j1 = 1;
            k1 = this.n_1700_B.n_1700_B(j1);
            regionfile$icompletecallback = this.n_1700_B(path, p_227135_2_);
            ByteBuffer bytebuffer = this.J_1907_R();
            this.G_564_y.write(bytebuffer, k1 * 4096);
        } else {
            k1 = this.n_1700_B.n_1700_B(j1);
            regionfile$icompletecallback = () -> Files.deleteIfExists(this.P_1922_E(p_227135_1_));
            this.G_564_y.write(p_227135_2_, k1 * 4096);
        }
        int l1 = (int)(j_3341_s.G_564_y() / 1000L);
        this.w_1484_f.put(i, this.n_1700_B(k1, j1));
        this.t_148_a.put(i, l1);
        this.R_4764_Y();
        regionfile$icompletecallback.run();
        if (k != 0) {
            this.n_1700_B.J_1907_R(k, l);
        }
    }

    private ByteBuffer J_1907_R() {
        ByteBuffer bytebuffer = ByteBuffer.allocate(5);
        bytebuffer.putInt(1);
        bytebuffer.put((byte)(this.u_1723_Y.n_1700_B() | 0x80));
        ((Buffer)bytebuffer).flip();
        return bytebuffer;
    }

    private J_1907_R n_1700_B(Path p_227138_1_, ByteBuffer p_227138_2_) throws IOException {
        Path path = Files.createTempFile(this.P_1922_E, "tmp", (String)null, new FileAttribute[0]);
        try (FileChannel filechannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);){
            ((Buffer)p_227138_2_).position(5);
            filechannel.write(p_227138_2_);
        }
        return () -> Files.move(path, p_227138_1_, StandardCopyOption.REPLACE_EXISTING);
    }

    private void R_4764_Y() throws IOException {
        ((Buffer)this.v_4262_N).position(0);
        this.G_564_y.write(this.v_4262_N, 0L);
    }

    private int u_1723_Y(Y_1387_d p_222660_1_) {
        return this.w_1484_f.get(i_3066_Y.v_4262_N(p_222660_1_));
    }

    public boolean G_564_y(Y_1387_d p_222667_1_) {
        return this.u_1723_Y(p_222667_1_) != 0;
    }

    private static int v_4262_N(Y_1387_d p_222668_0_) {
        return p_222668_0_.w_1484_f() + p_222668_0_.t_148_a() * 32;
    }

    @Override
    public void close() throws IOException {
        try {
            this.G_564_y();
        }
        finally {
            try {
                this.G_564_y.force(true);
            }
            finally {
                this.G_564_y.close();
            }
        }
    }

    private void G_564_y() throws IOException {
        int j;
        int i = (int)this.G_564_y.size();
        if (i != (j = i_3066_Y.R_4764_Y(i) * 4096)) {
            ByteBuffer bytebuffer = R_4764_Y.duplicate();
            ((Buffer)bytebuffer).position(0);
            this.G_564_y.write(bytebuffer, j - 1);
        }
    }

    class n_1700_B
    extends ByteArrayOutputStream {
        private final Y_1387_d J_1907_R;

        public n_1700_B(Y_1387_d p_i50620_2_) {
            super(8096);
            super.write(0);
            super.write(0);
            super.write(0);
            super.write(0);
            super.write(i_3066_Y.this.u_1723_Y.n_1700_B());
            this.J_1907_R = p_i50620_2_;
        }

        @Override
        public void close() throws IOException {
            ByteBuffer bytebuffer = ByteBuffer.wrap(this.buf, 0, this.count);
            bytebuffer.putInt(0, this.count - 5 + 1);
            i_3066_Y.this.n_1700_B(this.J_1907_R, bytebuffer);
        }
    }

    static interface J_1907_R {
        public void run() throws IOException;
    }
}


