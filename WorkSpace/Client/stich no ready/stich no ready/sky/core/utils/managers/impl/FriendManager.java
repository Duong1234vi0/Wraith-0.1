package sky.core.utils.managers.impl;

import com.adl.nativeprotect.Native;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import sky.core.utils.managers.BaseManager;

public class FriendManager extends BaseManager {
   public FriendManager() {
      super("temp\\friends.file");
   }

   @Native
   protected void initializeData() {
      this.data = new ArrayList();
   }

   protected JsonObject serializeData() {
      JsonObject config = new JsonObject();
      JsonArray friendsArray = new JsonArray();
      Iterator var3 = ((List)this.data).iterator();

      while(var3.hasNext()) {
         FriendEntry friend = (FriendEntry)var3.next();
         JsonObject friendObj = new JsonObject();
         friendObj.addProperty("name", friend.getName());
         friendObj.addProperty("addedTime", friend.getAddedTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
         friendsArray.add(friendObj);
      }

      config.add("friends", friendsArray);
      return config;
   }

   @Native
   protected void deserializeData(JsonObject jsonObject) {
      ((List)this.data).clear();
      JsonArray friendsArray = jsonObject.getAsJsonArray("friends");
      if (friendsArray != null) {
         Iterator var3 = friendsArray.iterator();

         while(var3.hasNext()) {
            JsonElement element = (JsonElement)var3.next();
            if (element.isJsonObject()) {
               JsonObject friendObj = element.getAsJsonObject();
               this.loadProperty(friendObj, "name", (nameElement) -> {
                  this.loadProperty(friendObj, "addedTime", (timeElement) -> {
                     try {
                        String friendName = nameElement.getAsString();
                        String timeStr = timeElement.getAsString();
                        LocalDateTime addedTime = LocalDateTime.parse(timeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        ((List)this.data).add(new FriendEntry(friendName, addedTime));
                     } catch (Exception var6) {
                        System.err.println("Failed to parse friend entry: " + var6.getMessage());
                     }

                  });
               });
            }
         }
      }

   }

   protected void handleLoadError(Exception exception) {
      if (this.data == null) {
         this.initializeData();
      }

   }

   public void addFriend(String friendName) {
      if (friendName != null && !friendName.trim().isEmpty()) {
         String name = friendName.trim();
         if (!this.isFriend(name)) {
            ((List)this.data).add(new FriendEntry(name, LocalDateTime.now()));
            this.save();
         }

      } else {
         System.err.println("Cannot add friend: name is null or empty");
      }
   }

   public void removeFriend(String friendName) {
      if (friendName == null) {
         System.err.println("Cannot remove friend: name is null");
      } else {
         boolean removed = ((List)this.data).removeIf((entry) -> {
            return entry.getName().equalsIgnoreCase(friendName.trim());
         });
         if (removed) {
            this.save();
         }

      }
   }

   public void clearFriends() {
      if (!((List)this.data).isEmpty()) {
         ((List)this.data).clear();
         this.save();
      }

   }

   public boolean isFriend(String friendName) {
      return friendName == null ? false : ((List)this.data).stream().anyMatch((entry) -> {
         return entry.getName().equalsIgnoreCase(friendName.trim());
      });
   }

   public List getFriends() {
      return new ArrayList((Collection)this.data);
   }

   private void loadProperty(JsonObject object, String key, Consumer consumer) {
      JsonElement element = object.get(key);
      if (element != null && !element.isJsonNull()) {
         consumer.accept(element);
      }

   }

   public static class FriendEntry {
      private final String name;
      private final LocalDateTime addedTime;

      public FriendEntry(String name, LocalDateTime addedTime) {
         this.name = name;
         this.addedTime = addedTime;
      }

      public String toString() {
         String var10000 = this.name;
         return var10000 + " (" + this.addedTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ")";
      }

      public String getName() {
         return this.name;
      }

      public LocalDateTime getAddedTime() {
         return this.addedTime;
      }
   }
}
