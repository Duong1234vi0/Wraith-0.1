package ua.babka.ui.drag;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import ua.babka.module.Module;
import ua.babka.ui.drag.Draggable;
import ua.babka.util.IMinecraft;

public class DragManager
implements IMinecraft {
    @Expose
    public static LinkedHashMap<String, Draggable> draggableElements = new LinkedHashMap();
    private static final File dataFile = new File(DragManager.mc.getInstance().runDirectory, "BabkaCleint/draggable.json");
    private static final Gson jsonParser = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    private static Draggable activeDragging = null;

    public static Draggable installDrag(Module module, String name, float x2, float y2) {
        draggableElements.put(name, new Draggable(module, name, x2, y2));
        return draggableElements.get(name);
    }

    public void load() {
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            return;
        }
        try (FileReader reader = new FileReader(dataFile);){
            Type type = new TypeToken<LinkedHashMap<String, Draggable>>(){}.getType();
            LinkedHashMap<String, Draggable> loaded = jsonParser.fromJson((Reader)reader, type);
            if (loaded != null) {
                for (Map.Entry<String, Draggable> entry : loaded.entrySet()) {
                    Draggable loadedDraggable = entry.getValue();
                    Draggable existing = draggableElements.get(entry.getKey());
                    if (existing != null) {
                        existing.setX(loadedDraggable.getX());
                        existing.setY(loadedDraggable.getY());
                        continue;
                    }
                    draggableElements.put(entry.getKey(), loadedDraggable);
                }
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void saveDraggables() {
        try {
            dataFile.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(dataFile);){
                jsonParser.toJson(draggableElements, (Appendable)writer);
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void onClickAll(int button) {
        if (button != 0) {
            return;
        }
        ArrayList<Draggable> elements = new ArrayList<Draggable>(draggableElements.values());
        Collections.reverse(elements);
        for (Draggable draggable : elements) {
            if (!draggable.isHovering() || draggable.getModule() == null || !draggable.getModule().isEnabled()) continue;
            activeDragging = draggable;
            draggable.onClick(button);
            break;
        }
    }

    public static void onDrawAll() {
        for (Draggable draggable : draggableElements.values()) {
            if (draggable.getModule() == null || !draggable.getModule().isEnabled()) continue;
            draggable.onDraw();
        }
    }

    public static void onReleaseAll(int button) {
        if (activeDragging != null) {
            activeDragging.onRelease(button);
            activeDragging = null;
        }
    }
}

