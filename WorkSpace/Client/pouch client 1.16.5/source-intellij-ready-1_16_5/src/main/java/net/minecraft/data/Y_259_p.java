/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.hash.HashFunction
 *  com.google.common.hash.Hashing
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 */
package net.minecraft.data;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Objects;
import net.minecraft.data.M_182_A;

public interface Y_259_p {
    public static final HashFunction n_1700_B = Hashing.sha1();

    public void n_1700_B(M_182_A var1) throws IOException;

    public String n_1700_B();

    public static void n_1700_B(Gson gson, M_182_A cache, JsonElement jsonElement, Path pathIn) throws IOException {
        String s = gson.toJson(jsonElement);
        String s1 = n_1700_B.hashUnencodedChars((CharSequence)s).toString();
        if (!Objects.equals(cache.n_1700_B(pathIn), s1) || !Files.exists(pathIn, new LinkOption[0])) {
            Files.createDirectories(pathIn.getParent(), new FileAttribute[0]);
            try (BufferedWriter bufferedwriter = Files.newBufferedWriter(pathIn, new OpenOption[0]);){
                bufferedwriter.write(s);
            }
        }
        cache.n_1700_B(pathIn, s1);
    }
}

