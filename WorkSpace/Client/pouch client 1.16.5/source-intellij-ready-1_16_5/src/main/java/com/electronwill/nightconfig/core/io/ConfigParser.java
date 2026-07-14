/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.io;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.file.FileNotFoundAction;
import com.electronwill.nightconfig.core.io.ParsingMode;
import com.electronwill.nightconfig.core.io.WritingException;
import com.electronwill.nightconfig.core.utils.FastStringReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public interface ConfigParser<C extends Config> {
    public ConfigFormat<C> getFormat();

    public C parse(Reader var1);

    public void parse(Reader var1, Config var2, ParsingMode var3);

    default public C parse(String input) {
        return this.parse(new FastStringReader(input));
    }

    default public void parse(String input, Config destination, ParsingMode parsingMode) {
        this.parse(new StringReader(input), destination, parsingMode);
    }

    default public C parse(InputStream input) {
        return this.parse(input, StandardCharsets.UTF_8);
    }

    default public C parse(InputStream input, Charset charset) {
        CharsetDecoder decoder = charset.newDecoder();
        decoder.onMalformedInput(CodingErrorAction.REPORT);
        decoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        return this.parse(new BufferedReader(new InputStreamReader(input, decoder)));
    }

    default public void parse(InputStream input, Config destination, ParsingMode parsingMode) {
        this.parse(input, destination, parsingMode, StandardCharsets.UTF_8);
    }

    default public void parse(InputStream input, Config destination, ParsingMode parsingMode, Charset charset) {
        CharsetDecoder decoder = charset.newDecoder();
        decoder.onMalformedInput(CodingErrorAction.REPORT);
        decoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, decoder));
        this.parse(reader, destination, parsingMode);
    }

    default public C parse(File file, FileNotFoundAction notFoundAction) {
        return this.parse(file, notFoundAction, StandardCharsets.UTF_8);
    }

    default public C parse(File file, FileNotFoundAction notFoundAction, Charset charset) {
        return this.parse(file.toPath(), notFoundAction, charset);
    }

    default public void parse(File file, Config destination, ParsingMode parsingMode, FileNotFoundAction notFoundAction) {
        this.parse(file, destination, parsingMode, notFoundAction, StandardCharsets.UTF_8);
    }

    default public void parse(File file, Config destination, ParsingMode parsingMode, FileNotFoundAction notFoundAction, Charset charset) {
        this.parse(file.toPath(), destination, parsingMode, notFoundAction, charset);
    }

    default public C parse(Path file, FileNotFoundAction notFoundAction) {
        return this.parse(file, notFoundAction, StandardCharsets.UTF_8);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    default public C parse(Path file, FileNotFoundAction notFoundAction, Charset charset) {
        try {
            if (Files.notExists(file, new LinkOption[0]) && !notFoundAction.run(file, this.getFormat())) {
                return this.getFormat().createConfig();
            }
            try (InputStream input = Files.newInputStream(file, new OpenOption[0]);){
                C c = this.parse(input, charset);
                return c;
            }
        }
        catch (IOException e) {
            throw new WritingException("An I/O error occured", e);
        }
    }

    default public void parse(Path file, Config destination, ParsingMode parsingMode, FileNotFoundAction notFoundAction) {
        this.parse(file, destination, parsingMode, notFoundAction, StandardCharsets.UTF_8);
    }

    default public void parse(Path file, Config destination, ParsingMode parsingMode, FileNotFoundAction notFoundAction, Charset charset) {
        try {
            if (Files.notExists(file, new LinkOption[0]) && !notFoundAction.run(file, this.getFormat())) {
                return;
            }
            try (InputStream input = Files.newInputStream(file, new OpenOption[0]);){
                this.parse(input, destination, parsingMode, charset);
            }
        }
        catch (IOException e) {
            throw new WritingException("An I/O error occured", e);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    default public C parse(URL url) {
        URLConnection connection;
        try {
            connection = url.openConnection();
        }
        catch (IOException e) {
            throw new WritingException("Unable to connect to the URL", e);
        }
        String encoding = connection.getContentEncoding();
        Charset charset = encoding == null ? StandardCharsets.UTF_8 : Charset.forName(encoding);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), charset));){
            C c = this.parse(reader);
            return c;
        }
        catch (IOException e) {
            throw new WritingException("An I/O error occured", e);
        }
    }

    default public void parse(URL url, Config destination, ParsingMode parsingMode) {
        URLConnection connection;
        try {
            connection = url.openConnection();
        }
        catch (IOException e) {
            throw new WritingException("Unable to connect to the URL", e);
        }
        String encoding = connection.getContentEncoding();
        Charset charset = encoding == null ? StandardCharsets.UTF_8 : Charset.forName(encoding);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), charset));){
            this.parse(reader, destination, parsingMode);
        }
        catch (IOException e) {
            throw new WritingException("An I/O error occured", e);
        }
    }
}

