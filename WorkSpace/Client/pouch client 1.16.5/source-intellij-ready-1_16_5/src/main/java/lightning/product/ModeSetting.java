/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Supplier;
import lightning.product.Setting;

public class ModeSetting
extends Setting<String> {
    public String[] G_564_y;
    public String P_1922_E;

    public ModeSetting(String name, String defaultVal, String ... strings) {
        super(name, defaultVal);
        this.P_1922_E = defaultVal;
        this.G_564_y = strings;
    }

    public ModeSetting(String name, String defaultVal, Supplier<Boolean> visible, String ... strings) {
        super(name, defaultVal);
        this.P_1922_E = defaultVal;
        this.G_564_y = strings;
        this.n_1700_B(visible);
    }

    public ModeSetting(String name, String defaultVal, String[] strings, Supplier<Boolean> visible) {
        super(name, defaultVal);
        this.P_1922_E = defaultVal;
        this.G_564_y = strings;
        this.n_1700_B(visible);
    }

    public int getIndex() {
        int index = 0;
        for (String val : this.G_564_y) {
            if (val.equalsIgnoreCase((String)this.getValue())) {
                return index;
            }
            ++index;
        }
        return 0;
    }

    public boolean isMode(String s) {
        return ((String)this.getValue()).equalsIgnoreCase(s);
    }

    @Override
    public void setOptions(String ... values) {
        this.G_564_y = values;
        boolean found = false;
        for (String val : values) {
            if (!val.equalsIgnoreCase((String)this.getValue())) continue;
            found = true;
            break;
        }
        if (!found && values.length > 0) {
            this.setValue(values[0]);
        }
    }
}

