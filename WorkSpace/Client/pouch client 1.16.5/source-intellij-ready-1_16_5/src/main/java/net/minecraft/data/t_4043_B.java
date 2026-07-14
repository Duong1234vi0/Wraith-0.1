/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import javax.annotation.Nullable;
import lightning.product.U_2912_j;
import lightning.product.r_1827_u;
import lightning.product.x_282_a;
import net.minecraft.data.M_182_A;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.Y_259_p;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class t_4043_B
implements Y_259_p {
    private static final Logger J_1907_R = LogManager.getLogger();
    private final Q_4569_t R_4764_Y;

    public t_4043_B(Q_4569_t generatorIn) {
        this.R_4764_Y = generatorIn;
    }

    @Override
    public void n_1700_B(M_182_A cache) throws IOException {
        Path path = this.R_4764_Y.J_1907_R();
        for (Path path1 : this.R_4764_Y.n_1700_B()) {
            Files.walk(path1, new FileVisitOption[0]).filter(path2 -> path2.toString().endsWith(".nbt")).forEach(nbtPath -> t_4043_B.n_1700_B(nbtPath, this.n_1700_B(path1, (Path)nbtPath), path));
        }
    }

    @Override
    public String n_1700_B() {
        return "NBT to SNBT";
    }

    private String n_1700_B(Path inputFolder, Path fileIn) {
        String s = inputFolder.relativize(fileIn).toString().replaceAll("\\\\", "/");
        return s.substring(0, s.length() - ".nbt".length());
    }

    @Nullable
    public static Path n_1700_B(Path snbtPath, String name, Path nbtPath) {
        try {
            U_2912_j compoundnbt = r_1827_u.n_1700_B(Files.newInputStream(snbtPath, new OpenOption[0]));
            x_282_a itextcomponent = compoundnbt.n_1700_B("    ", 0);
            String s = itextcomponent.getString() + "\n";
            Path path = nbtPath.resolve(name + ".snbt");
            Files.createDirectories(path.getParent(), new FileAttribute[0]);
            try (BufferedWriter bufferedwriter = Files.newBufferedWriter(path, new OpenOption[0]);){
                bufferedwriter.write(s);
            }
            J_1907_R.info("Converted {} from NBT to SNBT", (Object)name);
            return path;
        }
        catch (IOException ioexception) {
            J_1907_R.error("Couldn't convert {} from NBT to SNBT at {}", (Object)name, (Object)snbtPath, (Object)ioexception);
            return null;
        }
    }
}

