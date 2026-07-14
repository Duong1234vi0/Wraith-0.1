/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 */
package net.minecraft.server;

import com.google.common.base.Charsets;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;

public class v_4262_N
implements AutoCloseable {
    private final FileChannel n_1700_B;
    private final FileLock J_1907_R;
    private static final ByteBuffer R_4764_Y;

    public static v_4262_N n_1700_B(Path p_232998_0_) throws IOException {
        Path path = p_232998_0_.resolve("session.lock");
        if (!Files.isDirectory(p_232998_0_, new LinkOption[0])) {
            Files.createDirectories(p_232998_0_, new FileAttribute[0]);
        }
        FileChannel filechannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        try {
            filechannel.write(R_4764_Y.duplicate());
            filechannel.force(true);
            FileLock filelock = filechannel.tryLock();
            if (filelock == null) {
                throw net.minecraft.server.v_4262_N$n_1700_B.n_1700_B(path);
            }
            return new v_4262_N(filechannel, filelock);
        }
        catch (IOException ioexception1) {
            try {
                filechannel.close();
            }
            catch (IOException ioexception) {
                ioexception1.addSuppressed(ioexception);
            }
            throw ioexception1;
        }
    }

    private v_4262_N(FileChannel p_i231437_1_, FileLock p_i231437_2_) {
        this.n_1700_B = p_i231437_1_;
        this.J_1907_R = p_i231437_2_;
    }

    @Override
    public void close() throws IOException {
        try {
            if (this.J_1907_R.isValid()) {
                this.J_1907_R.release();
            }
        }
        finally {
            if (this.n_1700_B.isOpen()) {
                this.n_1700_B.close();
            }
        }
    }

    public boolean n_1700_B() {
        return this.J_1907_R.isValid();
    }

    /*
     * Enabled aggressive exception aggregation
     */
    public static boolean J_1907_R(Path p_232999_0_) throws IOException {
        Path path = p_232999_0_.resolve("session.lock");
        try (FileChannel filechannel = FileChannel.open(path, StandardOpenOption.WRITE);){
            boolean bl;
            block15: {
                FileLock filelock = filechannel.tryLock();
                try {
                    boolean bl2 = bl = filelock == null;
                    if (filelock == null) break block15;
                }
                catch (Throwable throwable) {
                    if (filelock != null) {
                        try {
                            filelock.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                filelock.close();
            }
            return bl;
        }
        catch (AccessDeniedException accessdeniedexception) {
            return true;
        }
        catch (NoSuchFileException nosuchfileexception) {
            return false;
        }
    }

    static {
        byte[] abyte = "\u2603".getBytes(Charsets.UTF_8);
        R_4764_Y = ByteBuffer.allocateDirect(abyte.length);
        R_4764_Y.put(abyte);
        ((Buffer)R_4764_Y).flip();
    }

    public static class n_1700_B
    extends IOException {
        private n_1700_B(Path p_i231438_1_, String p_i231438_2_) {
            super(String.valueOf(p_i231438_1_.toAbsolutePath()) + ": " + p_i231438_2_);
        }

        public static n_1700_B n_1700_B(Path p_233000_0_) {
            return new n_1700_B(p_233000_0_, "already locked (possibly by other Minecraft instance?)");
        }
    }
}

