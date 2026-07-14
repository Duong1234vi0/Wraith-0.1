/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.File;
import java.io.FileNotFoundException;

public class ResourcePackFileNotFoundException
extends FileNotFoundException {
    public ResourcePackFileNotFoundException(File resourcePack, String fileName) {
        super(String.format("'%s' in ResourcePack '%s'", fileName, resourcePack));
    }
}


