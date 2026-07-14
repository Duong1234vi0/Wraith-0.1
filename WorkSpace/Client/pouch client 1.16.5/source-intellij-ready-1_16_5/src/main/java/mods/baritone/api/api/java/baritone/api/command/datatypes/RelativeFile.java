/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.datatypes;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;
import lightning.product.MinecraftClient;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeContext;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypePost;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.utils.Helper;

public enum RelativeFile implements IDatatypePost<File, File>
{
    INSTANCE;


    @Override
    public File apply(IDatatypeContext ctx, File original) throws CommandException {
        Path path;
        if (original == null) {
            original = new File("./");
        }
        try {
            path = FileSystems.getDefault().getPath(ctx.getConsumer().getString(), new String[0]);
        }
        catch (InvalidPathException e) {
            throw new IllegalArgumentException("invalid path");
        }
        return RelativeFile.getCanonicalFileUnchecked(original.toPath().resolve(path).toFile());
    }

    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) {
        return Stream.empty();
    }

    private static File getCanonicalFileUnchecked(File file) {
        try {
            return file.getCanonicalFile();
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static Stream<String> tabComplete(IArgConsumer consumer, File base0) throws CommandException {
        File base = RelativeFile.getCanonicalFileUnchecked(base0);
        String currentPathStringThing = consumer.getString();
        Path currentPath = FileSystems.getDefault().getPath(currentPathStringThing, new String[0]);
        Path basePath = currentPath.isAbsolute() ? currentPath.getRoot() : base.toPath();
        boolean useParent = !currentPathStringThing.isEmpty() && !currentPathStringThing.endsWith(File.separator);
        File currentFile = currentPath.isAbsolute() ? currentPath.toFile() : new File(base, currentPathStringThing);
        return Stream.of(Objects.requireNonNull(RelativeFile.getCanonicalFileUnchecked(useParent ? currentFile.getParentFile() : currentFile).listFiles())).map(f -> String.valueOf(currentPath.isAbsolute() ? f : basePath.relativize(f.toPath()).toString()) + (f.isDirectory() ? File.separator : "")).filter(s -> s.toLowerCase(Locale.US).startsWith(currentPathStringThing.toLowerCase(Locale.US))).filter(s -> !s.contains(" "));
    }

    @Deprecated
    public static File gameDir() {
        return RelativeFile.gameDir(Helper.mc);
    }

    public static File gameDir(MinecraftClient mc) {
        File gameDir = mc.M_182_A.getAbsoluteFile();
        if (gameDir.getName().equals(".")) {
            return gameDir.getParentFile();
        }
        return gameDir;
    }
}


