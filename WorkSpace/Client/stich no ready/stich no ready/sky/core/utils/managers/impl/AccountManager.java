package sky.core.utils.managers.impl;

import com.adl.nativeprotect.Native;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import sky.core.utils.managers.BaseManager;

public class AccountManager extends BaseManager {
   private static final File LAST_ACCOUNT_FILE = new File("last_account.txt");

   public AccountManager() {
      super("temp\\accounts.file");
   }

   public void init() {
      this.createDirectoriesIfNeeded();
      this.initializeData();
      this.load();
      String selectedAccount = this.getSelectedAccount();
      if (selectedAccount != null && !selectedAccount.isEmpty()) {
      }

   }

   @Native
   protected void initializeData() {
      this.data = new AccountData();
   }

   public void readLastAlt() {
      try {
         BufferedReader reader = Files.newBufferedReader(LAST_ACCOUNT_FILE.toPath(), StandardCharsets.UTF_8);

         try {
            String last;
            String line;
            for(last = null; (line = reader.readLine()) != null; last = line.trim()) {
            }

            if (last != null) {
               Minecraft.getInstance().session = new Session(last, "", "", "mojang");
            }
         } catch (Throwable var5) {
            if (reader != null) {
               try {
                  reader.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }
            }

            throw var5;
         }

         if (reader != null) {
            reader.close();
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   protected JsonObject serializeData() {
      JsonObject config = new JsonObject();
      JsonArray accountsArray = new JsonArray();
      Iterator var3 = ((AccountData)this.data).getAccountNames().entrySet().iterator();

      while(var3.hasNext()) {
         Map.Entry entry = (Map.Entry)var3.next();
         String name = (String)entry.getKey();
         long timestamp = (Long)entry.getValue();
         boolean isFavorite = (Boolean)((AccountData)this.data).getFavoriteAccounts().getOrDefault(name, false);
         JsonObject accountObj = new JsonObject();
         accountObj.addProperty("name", name);
         accountObj.addProperty("timestamp", timestamp);
         accountObj.addProperty("isFavorite", isFavorite);
         accountsArray.add(accountObj);
      }

      config.add("accounts", accountsArray);
      if (((AccountData)this.data).getSelectedAccount() != null) {
         config.addProperty("selectedAccount", ((AccountData)this.data).getSelectedAccount());
      }

      return config;
   }

   protected void deserializeData(JsonObject jsonObject) {
      ((AccountData)this.data).getAccountNames().clear();
      ((AccountData)this.data).getFavoriteAccounts().clear();
      ((AccountData)this.data).setSelectedAccount((String)null);
      JsonArray accountsArray = jsonObject.getAsJsonArray("accounts");
      if (accountsArray != null) {
         Iterator var3 = accountsArray.iterator();

         while(var3.hasNext()) {
            JsonElement element = (JsonElement)var3.next();
            if (element.isJsonObject()) {
               JsonObject accountObj = element.getAsJsonObject();

               try {
                  this.loadProperty(accountObj, "name", (nameElement) -> {
                     this.loadProperty(accountObj, "timestamp", (timestampElement) -> {
                        this.loadProperty(accountObj, "isFavorite", (favoriteElement) -> {
                           String name = nameElement.getAsString();
                           long timestamp = timestampElement.getAsLong();
                           boolean isFavorite = favoriteElement.getAsBoolean();
                           ((AccountData)this.data).getAccountNames().put(name, timestamp);
                           ((AccountData)this.data).getFavoriteAccounts().put(name, isFavorite);
                        });
                     });
                  });
               } catch (Exception var7) {
                  System.err.println("Failed to parse account entry: " + var7.getMessage());
               }
            }
         }
      }

      this.loadProperty(jsonObject, "selectedAccount", (v) -> {
         ((AccountData)this.data).setSelectedAccount(v.getAsString());
      });
   }

   public void addAccount(String name) {
      if (name != null && !name.trim().isEmpty()) {
         String accountName = name.trim();
         ((AccountData)this.data).getAccountNames().put(accountName, System.currentTimeMillis());
         ((AccountData)this.data).getFavoriteAccounts().putIfAbsent(accountName, false);
         this.save();
      } else {
         System.err.println("Cannot add account: name is null or empty");
      }
   }

   public boolean removeAccount(String name) {
      if (name == null) {
         System.err.println("Cannot remove account: name is null");
         return false;
      } else {
         boolean removed = ((AccountData)this.data).getAccountNames().containsKey(name);
         if (removed) {
            ((AccountData)this.data).getAccountNames().remove(name);
            ((AccountData)this.data).getFavoriteAccounts().remove(name);
            if (name.equals(((AccountData)this.data).getSelectedAccount())) {
               ((AccountData)this.data).setSelectedAccount((String)null);
            }

            this.save();
         }

         return removed;
      }
   }

   public void setFavorite(String name, boolean favorite) {
      if (((AccountData)this.data).getAccountNames().containsKey(name)) {
         ((AccountData)this.data).getFavoriteAccounts().put(name, favorite);
         this.save();
      }

   }

   public boolean isFavorite(String name) {
      return (Boolean)((AccountData)this.data).getFavoriteAccounts().getOrDefault(name, false);
   }

   public List getAccountNamesList() {
      return new ArrayList(((AccountData)this.data).getAccountNames().keySet());
   }

   public void selectAccount(String name) {
      if (((AccountData)this.data).getAccountNames().containsKey(name)) {
         ((AccountData)this.data).setSelectedAccount(name);
         this.save();
      }

   }

   public void updateLastAccountFile() {
      try {
         Files.write(LAST_ACCOUNT_FILE.toPath(), Minecraft.getInstance().session.getUsername().getBytes(), new OpenOption[0]);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   @Native
   public Map getAccountNames() {
      return ((AccountData)this.data).getAccountNames();
   }

   @Native
   public Map getFavoriteAccounts() {
      return ((AccountData)this.data).getFavoriteAccounts();
   }

   public String getSelectedAccount() {
      return ((AccountData)this.data).getSelectedAccount();
   }

   @Native
   private void loadProperty(JsonObject object, String key, Consumer consumer) {
      JsonElement element = object.get(key);
      if (element != null && !element.isJsonNull()) {
         consumer.accept(element);
      }

   }

   public static class AccountData {
      private final Map accountNames = new LinkedHashMap();
      private final Map favoriteAccounts = new LinkedHashMap();
      private String selectedAccount;

      public Map getAccountNames() {
         return this.accountNames;
      }

      public Map getFavoriteAccounts() {
         return this.favoriteAccounts;
      }

      public String getSelectedAccount() {
         return this.selectedAccount;
      }

      public void setSelectedAccount(String selectedAccount) {
         this.selectedAccount = selectedAccount;
      }
   }
}
