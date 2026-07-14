package ua.babka.friend;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.PlayerEntity;
import ua.babka.friend.Friend;
import ua.babka.util.QuickLogger;

public class FriendRepository
implements QuickLogger {
    private static final File file = new File("BabkaCleint/friends.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final List<Friend> friends = new ArrayList<Friend>();

    public static void addFriend(String name) {
        friends.add(new Friend(name));
    }

    public static void removeFriend(String name) {
        friends.removeIf(friend -> friend.name().equalsIgnoreCase(name));
    }

    public static boolean shouldAttack(PlayerEntity player) {
        return !FriendRepository.isFriend(player.getNameForScoreboard());
    }

    public static boolean isFriend(String friend) {
        return friends.stream().anyMatch(f2 -> f2.name().equalsIgnoreCase(friend));
    }

    public static Friend getFriend(String name) {
        return friends.stream().filter(f2 -> f2.name().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static void clear() {
        friends.clear();
    }

    public static void save() {
        try {
            file.getParentFile().mkdirs();
            try (OutputStreamWriter writer = new OutputStreamWriter((OutputStream)new FileOutputStream(file), StandardCharsets.UTF_8);){
                gson.toJson(friends, (Appendable)writer);
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public static void load() {
        if (!file.exists()) {
            return;
        }
        try (InputStreamReader reader = new InputStreamReader((InputStream)new FileInputStream(file), StandardCharsets.UTF_8);){
            Type listType = new TypeToken<ArrayList<Friend>>(){}.getType();
            List loaded = (List)gson.fromJson((Reader)reader, listType);
            if (loaded != null) {
                friends.clear();
                friends.addAll(loaded);
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public static List<Friend> getFriends() {
        return friends;
    }
}

