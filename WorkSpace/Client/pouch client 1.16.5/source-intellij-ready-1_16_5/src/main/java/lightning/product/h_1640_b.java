/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import lightning.product.C_332_W;
import lightning.product.S_1165_y;

public class h_1640_b {
    private final File n_1700_B;
    private final Map<String, S_1165_y> J_1907_R = new HashMap<String, S_1165_y>();

    public h_1640_b() {
        this.n_1700_B = new File(C_332_W.n_1700_B + "scripts\\");
        if (!this.n_1700_B.exists()) {
            this.n_1700_B.mkdirs();
        }
    }

    public File n_1700_B() {
        return this.n_1700_B;
    }

    public boolean n_1700_B(String scriptName) {
        try {
            File scriptFile = new File(this.n_1700_B, scriptName);
            if (!scriptFile.exists()) {
                return false;
            }
            String content = new String(Files.readAllBytes(scriptFile.toPath()));
            if (scriptName.toLowerCase().endsWith(".java") || scriptName.toLowerCase().endsWith(".pouch")) {
                S_1165_y script = new S_1165_y(scriptName, content);
                if (script.n_1700_B()) {
                    this.J_1907_R.put(scriptName, script);
                    return true;
                }
                return false;
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean J_1907_R(String scriptName) {
        return this.J_1907_R.containsKey(scriptName);
    }

    public boolean R_4764_Y(String scriptName) {
        if (!this.J_1907_R(scriptName)) {
            return false;
        }
        this.G_564_y(scriptName);
        return this.n_1700_B(scriptName);
    }

    public void G_564_y(String scriptName) {
        S_1165_y javaScript = this.J_1907_R.remove(scriptName);
        if (javaScript != null) {
            javaScript.J_1907_R();
        }
    }

    public String[] J_1907_R() {
        File[] files = this.n_1700_B.listFiles((dir, name) -> name.toLowerCase().endsWith(".java") || name.toLowerCase().endsWith(".pouch"));
        if (files == null) {
            return new String[0];
        }
        String[] scriptNames = new String[files.length];
        for (int i = 0; i < files.length; ++i) {
            scriptNames[i] = files[i].getName();
        }
        return scriptNames;
    }

    public void R_4764_Y() {
        for (S_1165_y script : this.J_1907_R.values()) {
            script.J_1907_R();
        }
        this.J_1907_R.clear();
    }
}

