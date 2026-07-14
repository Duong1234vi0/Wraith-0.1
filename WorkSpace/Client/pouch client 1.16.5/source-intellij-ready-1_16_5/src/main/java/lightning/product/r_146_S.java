/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.SharedConstants;

public class r_146_S {
    private static final Pattern n_1700_B = Pattern.compile("(<name>.*) \\((<count>\\d*)\\)", 66);
    private static final Pattern J_1907_R = Pattern.compile(".*\\.|(?:COM|CLOCK\\$|CON|PRN|AUX|NUL|COM[1-9]|LPT[1-9])(?:\\..*)?", 2);

    public static String n_1700_B(Path dirPath, String fileName, String fileFormat) throws IOException {
        for (char c0 : SharedConstants.P_1922_E) {
            fileName = ((String)fileName).replace(c0, '_');
        }
        if (J_1907_R.matcher((CharSequence)(fileName = ((String)fileName).replaceAll("[./\"]", "_"))).matches()) {
            fileName = "_" + (String)fileName + "_";
        }
        Matcher matcher = n_1700_B.matcher((CharSequence)fileName);
        int j = 0;
        if (matcher.matches()) {
            fileName = matcher.group("name");
            j = Integer.parseInt(matcher.group("count"));
        }
        if (((String)fileName).length() > 255 - fileFormat.length()) {
            fileName = ((String)fileName).substring(0, 255 - fileFormat.length());
        }
        while (true) {
            Object s = fileName;
            if (j != 0) {
                String s1 = " (" + j + ")";
                int i = 255 - s1.length();
                if (((String)fileName).length() > i) {
                    s = ((String)fileName).substring(0, i);
                }
                s = (String)s + s1;
            }
            s = (String)s + fileFormat;
            Path path = dirPath.resolve((String)s);
            try {
                Path path1 = Files.createDirectory(path, new FileAttribute[0]);
                Files.deleteIfExists(path1);
                return dirPath.relativize(path1).toString();
            }
            catch (FileAlreadyExistsException filealreadyexistsexception) {
                ++j;
                continue;
            }
            break;
        }
    }

    public static boolean n_1700_B(Path pathIn) {
        Path path = pathIn.normalize();
        return path.equals(pathIn);
    }

    public static boolean J_1907_R(Path pathIn) {
        for (Path path : pathIn) {
            if (!J_1907_R.matcher(path.toString()).matches()) continue;
            return false;
        }
        return true;
    }

    public static Path J_1907_R(Path dirPath, String locationPath, String fileFormat) {
        String s = locationPath + fileFormat;
        Path path = Paths.get(s, new String[0]);
        if (path.endsWith(fileFormat)) {
            throw new InvalidPathException(s, "empty resource name");
        }
        return dirPath.resolve(path);
    }
}


