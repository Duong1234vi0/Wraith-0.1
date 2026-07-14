package ua.babka.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import ua.babka.server.Staff;

public class StaffManager {
    private static final Set<Staff> staffList = new HashSet<Staff>();
    private final File file = new File("BabkaCleint/staff.json");

    public static void addStaff(Staff staff) {
        staffList.add(staff);
    }

    public static void removeStaff(String staff) {
        staffList.removeIf(s2 -> s2.name.equalsIgnoreCase(staff));
    }

    public static boolean isStaff(String name) {
        for (Staff staff : staffList) {
            if (!staff.name.equalsIgnoreCase(name)) continue;
            return true;
        }
        return false;
    }

    public static void clearStaff() {
        staffList.clear();
    }

    public void save() {
        JsonArray array = new JsonArray();
        for (Staff staff : staffList) {
            array.add(staff.name);
        }
        try {
            if (!this.file.getParentFile().exists()) {
                this.file.getParentFile().mkdirs();
            }
            try (OutputStreamWriter writer = new OutputStreamWriter((OutputStream)new FileOutputStream(this.file), StandardCharsets.UTF_8);){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson((JsonElement)array, (Appendable)writer);
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void load() {
        if (!this.file.exists()) {
            return;
        }
        try (InputStreamReader reader = new InputStreamReader((InputStream)new FileInputStream(this.file), StandardCharsets.UTF_8);){
            JsonElement element = new JsonParser().parse((Reader)reader);
            if (!element.isJsonArray()) {
                return;
            }
            JsonArray array = element.getAsJsonArray();
            staffList.clear();
            for (JsonElement el : array) {
                String name = el.getAsString();
                staffList.add(new Staff(name));
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static Set<Staff> getStaffList() {
        return staffList;
    }
}

