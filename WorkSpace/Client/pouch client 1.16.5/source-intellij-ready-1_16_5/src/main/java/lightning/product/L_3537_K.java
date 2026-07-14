/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  lombok.Generated
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import lightning.product.I_1790_n;
import lightning.product.MinecraftAccess;
import lightning.product.w_2223_C;
import lombok.Generated;

public class L_3537_K
extends I_1790_n<n_1700_B> {
    public L_3537_K() {
        super("temp\\accounts.file");
    }

    @Override
    @w_2223_C
    public void n_1700_B() {
        this.J_1907_R();
        this.t_148_a();
        this.R_4764_Y();
        String selectedAccount = this.Q_4569_t();
        if (selectedAccount != null && !selectedAccount.isEmpty()) {
            try {
                MinecraftAccess.c_3005_b.w_1484_f.n_1700_B(selectedAccount);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    @Override
    protected void t_148_a() {
        this.P_1922_E = new n_1700_B();
    }

    @Override
    protected JsonObject s_956_w() {
        JsonObject config = new JsonObject();
        JsonArray accountsArray = new JsonArray();
        for (Map.Entry<String, Long> entry : ((n_1700_B)this.P_1922_E).n_1700_B().entrySet()) {
            String name = entry.getKey();
            long timestamp = entry.getValue();
            boolean isFavorite = ((n_1700_B)this.P_1922_E).J_1907_R().getOrDefault(name, false);
            JsonObject accountObj = new JsonObject();
            accountObj.addProperty("name", name);
            accountObj.addProperty("timestamp", (Number)timestamp);
            accountObj.addProperty("isFavorite", Boolean.valueOf(isFavorite));
            accountObj.addProperty("microsoft", Boolean.valueOf(((n_1700_B)this.P_1922_E).R_4764_Y().contains(name)));
            accountsArray.add((JsonElement)accountObj);
        }
        config.add("accounts", (JsonElement)accountsArray);
        if (((n_1700_B)this.P_1922_E).G_564_y() != null) {
            config.addProperty("selectedAccount", ((n_1700_B)this.P_1922_E).G_564_y());
        }
        return config;
    }

    @Override
    protected void n_1700_B(JsonObject jsonObject) {
        ((n_1700_B)this.P_1922_E).n_1700_B().clear();
        ((n_1700_B)this.P_1922_E).J_1907_R().clear();
        ((n_1700_B)this.P_1922_E).R_4764_Y().clear();
        ((n_1700_B)this.P_1922_E).n_1700_B(null);
        JsonArray accountsArray = jsonObject.getAsJsonArray("accounts");
        if (accountsArray != null) {
            for (JsonElement element : accountsArray) {
                if (!element.isJsonObject()) continue;
                JsonObject accountObj = element.getAsJsonObject();
                try {
                    this.n_1700_B(accountObj, "name", (JsonElement nameElement) -> this.n_1700_B(accountObj, "timestamp", (JsonElement timestampElement) -> this.n_1700_B(accountObj, "isFavorite", (JsonElement favoriteElement) -> {
                        String name = nameElement.getAsString();
                        long timestamp = timestampElement.getAsLong();
                        boolean isFavorite = favoriteElement.getAsBoolean();
                        ((n_1700_B)this.P_1922_E).n_1700_B().put(name, timestamp);
                        ((n_1700_B)this.P_1922_E).J_1907_R().put(name, isFavorite);
                        this.n_1700_B(accountObj, "microsoft", (JsonElement microsoftElement) -> {
                            if (microsoftElement.getAsBoolean()) {
                                ((n_1700_B)this.P_1922_E).R_4764_Y().add(name);
                            }
                        });
                    })));
                }
                catch (Exception e) {
                    System.err.println("Failed to parse account entry: " + e.getMessage());
                }
            }
        }
        this.n_1700_B(jsonObject, "selectedAccount", (JsonElement v) -> ((n_1700_B)this.P_1922_E).n_1700_B(v.getAsString()));
    }

    @Override
    public void n_1700_B(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Cannot add account: name is null or empty");
            return;
        }
        String accountName = name.trim();
        ((n_1700_B)this.P_1922_E).n_1700_B().put(accountName, System.currentTimeMillis());
        ((n_1700_B)this.P_1922_E).J_1907_R().putIfAbsent(accountName, false);
        this.G_564_y();
    }

    public void J_1907_R(String name) {
        if (name == null || name.trim().isEmpty()) {
            return;
        }
        String accountName = name.trim();
        ((n_1700_B)this.P_1922_E).n_1700_B().put(accountName, System.currentTimeMillis());
        ((n_1700_B)this.P_1922_E).J_1907_R().putIfAbsent(accountName, false);
        ((n_1700_B)this.P_1922_E).R_4764_Y().add(accountName);
        this.G_564_y();
    }

    public boolean R_4764_Y(String name) {
        return name != null && ((n_1700_B)this.P_1922_E).R_4764_Y().contains(name);
    }

    public boolean G_564_y(String name) {
        if (name == null) {
            System.err.println("Cannot remove account: name is null");
            return false;
        }
        boolean removed = ((n_1700_B)this.P_1922_E).n_1700_B().containsKey(name);
        if (removed) {
            ((n_1700_B)this.P_1922_E).n_1700_B().remove(name);
            ((n_1700_B)this.P_1922_E).J_1907_R().remove(name);
            ((n_1700_B)this.P_1922_E).R_4764_Y().remove(name);
            if (name.equals(((n_1700_B)this.P_1922_E).G_564_y())) {
                ((n_1700_B)this.P_1922_E).n_1700_B(null);
            }
            this.G_564_y();
        }
        return removed;
    }

    public void n_1700_B(String name, boolean favorite) {
        if (((n_1700_B)this.P_1922_E).n_1700_B().containsKey(name)) {
            ((n_1700_B)this.P_1922_E).J_1907_R().put(name, favorite);
            this.G_564_y();
        }
    }

    public boolean P_1922_E(String name) {
        return ((n_1700_B)this.P_1922_E).J_1907_R().getOrDefault(name, false);
    }

    public List<String> M_588_G() {
        return new ArrayList<String>(((n_1700_B)this.P_1922_E).n_1700_B().keySet());
    }

    public void u_1723_Y(String name) {
        if (((n_1700_B)this.P_1922_E).n_1700_B().containsKey(name)) {
            ((n_1700_B)this.P_1922_E).n_1700_B(name);
            this.G_564_y();
        }
    }

    public Map<String, Long> P_4830_p() {
        return ((n_1700_B)this.P_1922_E).n_1700_B();
    }

    public Map<String, Boolean> h_1847_R() {
        return ((n_1700_B)this.P_1922_E).J_1907_R();
    }

    public String Q_4569_t() {
        return ((n_1700_B)this.P_1922_E).G_564_y();
    }

    private void n_1700_B(JsonObject object, String key, Consumer<JsonElement> consumer) {
        JsonElement element = object.get(key);
        if (element != null && !element.isJsonNull()) {
            consumer.accept(element);
        }
    }

    public static class n_1700_B {
        private final Map<String, Long> n_1700_B = new LinkedHashMap<String, Long>();
        private final Map<String, Boolean> J_1907_R = new LinkedHashMap<String, Boolean>();
        private final Set<String> R_4764_Y = new HashSet<String>();
        private String G_564_y;

        @Generated
        public Map<String, Long> n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public Map<String, Boolean> J_1907_R() {
            return this.J_1907_R;
        }

        @Generated
        public Set<String> R_4764_Y() {
            return this.R_4764_Y;
        }

        @Generated
        public String G_564_y() {
            return this.G_564_y;
        }

        @Generated
        public void n_1700_B(String selectedAccount) {
            this.G_564_y = selectedAccount;
        }
    }
}


