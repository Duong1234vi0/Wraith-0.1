package ua.babka.macro;

import com.google.common.eventbus.Subscribe;
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
import net.minecraft.util.Formatting;
import ua.babka.Babka;
import ua.babka.event.EventKeyInput;
import ua.babka.macro.Macro;
import ua.babka.util.IMinecraft;
import ua.babka.util.KeyStorage;
import ua.babka.util.QuickLogger;

public class MacroRepository
implements IMinecraft,
QuickLogger {
    private final File file = new File("BabkaCleint]/macros.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final List<Macro> macroList = new ArrayList<Macro>();

    public MacroRepository() {
        Babka.getInstance().getEventBus().register((Object)this);
    }

    public boolean isEmpty() {
        return this.macroList.isEmpty();
    }

    public void addMacro(String message, int key) {
        this.macroList.add(new Macro(message, key));
    }

    public boolean hasMacro(String macroName) {
        for (Macro macro : this.macroList) {
            if (!KeyStorage.getKey(macro.key()).equalsIgnoreCase(macroName)) continue;
            return true;
        }
        return false;
    }

    public void deleteMacro(String name) {
        this.macroList.removeIf(macro -> KeyStorage.getKey(macro.key()).equalsIgnoreCase(name));
    }

    public void clearList() {
        this.macroList.clear();
    }

    public void save() {
        try {
            this.file.getParentFile().mkdirs();
            try (OutputStreamWriter writer = new OutputStreamWriter((OutputStream)new FileOutputStream(this.file), StandardCharsets.UTF_8);){
                this.gson.toJson(this.macroList, (Appendable)writer);
            }
        }
        catch (IOException e2) {
            this.logDirect("Ошибка при сохранении макросов: " + e2.getMessage(), Formatting.RED);
        }
    }

    public boolean removeByKey(int key) {
        return this.macroList.removeIf(m2 -> m2.key() == key);
    }

    public void load() {
        if (!this.file.exists()) {
            return;
        }
        try (InputStreamReader reader = new InputStreamReader((InputStream)new FileInputStream(this.file), StandardCharsets.UTF_8);){
            Type listType = new TypeToken<ArrayList<Macro>>(){}.getType();
            List loaded = (List)this.gson.fromJson((Reader)reader, listType);
            if (loaded != null) {
                this.macroList.clear();
                this.macroList.addAll(loaded);
            }
        }
        catch (IOException e2) {
            this.logDirect("Ошибка при загрузке макросов: " + e2.getMessage(), Formatting.RED);
        }
    }

    @Subscribe
    public void onKey(EventKeyInput event) {
        int key = event.getKey();
        if (key == -1) {
            return;
        }
        if (MacroRepository.mc.player == null || event.getAction() == 0 || MacroRepository.mc.currentScreen != null) {
            return;
        }
        for (Macro macro : this.macroList) {
            if (macro.key() != key) continue;
            String msg = macro.message();
            if (msg.startsWith(".")) {
                msg = msg.substring(1);
                Babka.getInstance().getCommandDispatcher().runCommand(msg);
                continue;
            }
            if (msg.startsWith("/")) {
                mc.getNetworkHandler().sendChatCommand(msg.replaceFirst("/", ""));
                continue;
            }
            mc.getNetworkHandler().sendChatMessage(msg);
        }
    }

    public File getFile() {
        return this.file;
    }

    public Gson getGson() {
        return this.gson;
    }

    public List<Macro> getMacroList() {
        return this.macroList;
    }
}

