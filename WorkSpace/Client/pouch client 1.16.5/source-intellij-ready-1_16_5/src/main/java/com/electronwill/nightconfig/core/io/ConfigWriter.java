/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.io;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.io.CharsWrapper;
import com.electronwill.nightconfig.core.io.IoUtils;
import com.electronwill.nightconfig.core.io.WritingException;
import com.electronwill.nightconfig.core.io.WritingMode;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public interface ConfigWriter {
    public void write(UnmodifiableConfig var1, Writer var2);

    default public void write(UnmodifiableConfig config, OutputStream output, Charset charset) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, charset));){
            this.write(config, writer);
        }
        catch (IOException e) {
            throw new WritingException("An I/O error occured", e);
        }
    }

    default public void write(UnmodifiableConfig config, OutputStream output) {
        this.write(config, output, StandardCharsets.UTF_8);
    }

    default public void write(UnmodifiableConfig config, Path file, WritingMode writingMode) {
        this.write(config, file, writingMode, StandardCharsets.UTF_8);
    }

    default public void write(UnmodifiableConfig config, Path file, WritingMode writingMode, Charset charset) {
        if (writingMode == WritingMode.REPLACE_ATOMIC) {
            String tmpFileName = IoUtils.tempConfigFileName(file);
            Path tmp = file.resolveSibling(tmpFileName);
            try (OutputStream output = Files.newOutputStream(tmp, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);){
                this.write(config, output, charset);
            }
            catch (IOException e) {
                String msg = String.format("Failed to write (%s) the config to: %s", writingMode.toString(), file.toString());
                throw new WritingException(msg, e);
            }
            try {
                IoUtils.retryIfAccessDenied("move", () -> Files.move(tmp, file, StandardCopyOption.ATOMIC_MOVE));
            }
            catch (AtomicMoveNotSupportedException e) {
                String msg = String.format("Failed to atomically move the config from '%s' to '%s': WritingMode.REPLACE_ATOMIC is not supported for this path, use WritingMode.REPLACE instead.\n%s", tmp.toString(), file.toString(), "Note: you may see *.new.tmp files after this error, they contain the \"new version\" of your configurations and can be safely removed.If you want, you can manually copy their content into your regular configuration files (replacing the old config).");
                throw new WritingException(msg, e);
            }
            catch (IOException e) {
                String msg = String.format("Failed to atomically write (%s) the config to: %s", writingMode.toString(), file.toString());
                throw new WritingException(msg, e);
            }
        }
        StandardOpenOption lastOption = writingMode == WritingMode.APPEND ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING;
        try (OutputStream output = Files.newOutputStream(file, StandardOpenOption.WRITE, StandardOpenOption.CREATE, lastOption);){
            this.write(config, output, charset);
        }
        catch (IOException e) {
            String msg = String.format("Failed to write (%s) the config to: %s", writingMode.toString(), file.toString());
            throw new WritingException(msg, e);
        }
    }

    default public void write(UnmodifiableConfig config, File file, WritingMode writingMode) {
        this.write(config, file, writingMode, StandardCharsets.UTF_8);
    }

    default public void write(UnmodifiableConfig config, File file, WritingMode writingMode, Charset charset) {
        this.write(config, file.toPath(), writingMode, charset);
    }

    default public void write(UnmodifiableConfig config, URL url) {
        URLConnection connection;
        try {
            connection = url.openConnection();
        }
        catch (IOException e) {
            throw new WritingException("Unable to connect to the URL", e);
        }
        String encoding = connection.getContentEncoding();
        Charset charset = encoding == null ? StandardCharsets.UTF_8 : Charset.forName(encoding);
        try (OutputStream output = connection.getOutputStream();){
            this.write(config, output, charset);
        }
        catch (IOException e) {
            throw new WritingException("An I/O error occured", e);
        }
    }

    default public String writeToString(UnmodifiableConfig config) {
        CharsWrapper.Builder builder = new CharsWrapper.Builder(64);
        this.write(config, builder);
        return builder.toString();
    }
}

