/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Properties;
import lightning.product.SharedConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class P_1922_E {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Path J_1907_R;
    private final boolean R_4764_Y;

    public P_1922_E(Path file) {
        this.J_1907_R = file;
        this.R_4764_Y = SharedConstants.G_564_y || this.J_1907_R();
    }

    private boolean J_1907_R() {
        boolean bl;
        block8: {
            InputStream inputstream = Files.newInputStream(this.J_1907_R, new OpenOption[0]);
            try {
                Properties properties = new Properties();
                properties.load(inputstream);
                bl = Boolean.parseBoolean(properties.getProperty("eula", "false"));
                if (inputstream == null) break block8;
            }
            catch (Throwable throwable) {
                try {
                    if (inputstream != null) {
                        try {
                            inputstream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (Exception exception) {
                    n_1700_B.warn("Failed to load {}", (Object)this.J_1907_R);
                    this.R_4764_Y();
                    return false;
                }
            }
            inputstream.close();
        }
        return bl;
    }

    public boolean n_1700_B() {
        return this.R_4764_Y;
    }

    private void R_4764_Y() {
        if (!SharedConstants.G_564_y) {
            try (OutputStream outputstream = Files.newOutputStream(this.J_1907_R, new OpenOption[0]);){
                Properties properties = new Properties();
                properties.setProperty("eula", "false");
                properties.store(outputstream, "By changing the setting below to TRUE you are indicating your agreement to our EULA (https://account.mojang.com/documents/minecraft_eula).");
            }
            catch (Exception exception) {
                n_1700_B.warn("Failed to save {}", (Object)this.J_1907_R, (Object)exception);
            }
        }
    }
}


