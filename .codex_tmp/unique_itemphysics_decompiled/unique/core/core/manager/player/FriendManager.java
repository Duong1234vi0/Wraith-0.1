package unique.core.core.manager.player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1657;
import net.minecraft.class_742;
import org.jetbrains.annotations.NotNull;
import unique.core.core.manager.IManager;

public class FriendManager implements IManager {
   public static List<String> friends = new ArrayList<>();

   public boolean isFriend(String name) {
      return friends.stream().anyMatch(friend -> friend.equalsIgnoreCase(name));
   }

   public boolean isFriend(@NotNull class_1657 player) {
      return this.isFriend(player.method_5477().getString());
   }

   public void removeFriend(String name) {
      friends.remove(name);
   }

   public void addFriend(String friend) {
      friends.add(friend);
   }

   public List<String> getFriends() {
      return friends;
   }

   public void clear() {
      friends.clear();
   }

   public List<class_742> getNearFriends() {
      return (List<class_742>)(mc.field_1687 == null
         ? new ArrayList<>()
         : mc.field_1687.method_18456().stream().filter(player -> friends.contains(player.method_5477().getString())).toList());
   }

   public void saveFriends() {
      File file = new File("ThunderHackCosmicDLC/misc/friends.txt");

      try {
         file.createNewFile();
      } catch (Exception var6) {
      }

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
         for (String friend : friends) {
            writer.write(friend + "\n");
         }
      } catch (Exception var8) {
      }
   }

   public void loadFriends() {
      try {
         File file = new File("ThunderHackCosmicDLC/misc/friends.txt");
         if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
               while (reader.ready()) {
                  friends.add(reader.readLine());
               }
            }
         }
      } catch (Exception var7) {
      }
   }
}
