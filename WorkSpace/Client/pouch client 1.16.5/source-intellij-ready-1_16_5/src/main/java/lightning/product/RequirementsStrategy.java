/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Collection;

public interface RequirementsStrategy {
    public static final RequirementsStrategy n_1700_B = requirementStrings -> {
        String[][] astring = new String[requirementStrings.size()][];
        int i = 0;
        for (String s : requirementStrings) {
            astring[i++] = new String[]{s};
        }
        return astring;
    };
    public static final RequirementsStrategy J_1907_R = requirementStrings -> new String[][]{requirementStrings.toArray(new String[0])};

    public String[][] createRequirements(Collection<String> var1);
}


