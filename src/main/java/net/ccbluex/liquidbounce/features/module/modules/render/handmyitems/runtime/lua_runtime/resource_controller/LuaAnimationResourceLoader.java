package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.resource_controller;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.LuaTestHMI;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.GlobalsStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.LuaScriptCache;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.ModelScriptCache;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.ScriptHolder;
import net.fabricmc.fabric.api.resource.v1.reloader.SimpleReloadListener;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LuaAnimationResourceLoader extends SimpleReloadListener<LuaAnimationResourceLoader.LoadResult> {
    public static final Identifier ID = Identifier.fromNamespaceAndPath("liquidbounce", "handmyitems_lua_animation_loader");
    private static final Pattern GLOBAL_PATTERN = Pattern.compile("global\\.(\\w+)\\s*=\\s*([^;]+)\\s*;");
    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\$\\{(\\w+)\\}");

    @Override
    protected LoadResult prepare(PreparableReloadListener.SharedState state) {
        if (!HandMyItemsRuntime.isEnabled() || HandMyItemsRuntime.state() == HandMyItemsRuntime.State.FAILED) {
            return LoadResult.skipped();
        }

        try {
            return load(state.resourceManager());
        } catch (Exception exception) {
            return LoadResult.failed(exception);
        }
    }

    @Override
    protected void apply(LoadResult result, PreparableReloadListener.SharedState state) {
        if (!HandMyItemsRuntime.isEnabled() || HandMyItemsRuntime.state() == HandMyItemsRuntime.State.FAILED || result.skipped) {
            return;
        }

        if (result.failure != null) {
            HandMyItemsRuntime.onReloadFailure(result.failure, result.failure.getMessage());
            return;
        }

        install(result);
    }

    public synchronized boolean reloadNow(ResourceManager manager) {
        if (!HandMyItemsRuntime.isEnabled() || HandMyItemsRuntime.state() == HandMyItemsRuntime.State.FAILED) {
            return false;
        }

        try {
            install(load(manager));
            return true;
        } catch (Exception exception) {
            HandMyItemsRuntime.onReloadFailure(exception, exception.getMessage());
            return false;
        }
    }

    public boolean hasPrimaryScripts(ResourceManager manager) {
        boolean found = true;
        for (String path : new String[] {
            "holdmyitems/hand_pose.lua",
            "holdmyitems/hand_relative_pose.lua",
            "holdmyitems/item_pose.lua",
            "holdmyitems/item_model.lua"
        }) {
            Identifier id = Identifier.fromNamespaceAndPath("minecraft", path);
            Resource resource = manager.getResource(id).orElse(null);
            if (resource == null) {
                found = false;
                HandMyItemsRuntime.diagnostic("primary resource missing id=" + id);
            } else {
                HandMyItemsRuntime.diagnostic("primary resource found id=" + id + " pack=" + resource.sourcePackId());
            }
        }
        return found;
    }

    private LoadResult load(ResourceManager manager) throws IOException {
        LuaScriptCache hand = compileScript(manager, "holdmyitems/hand_pose.lua");
        LuaScriptCache handRelative = compileScript(manager, "holdmyitems/hand_relative_pose.lua");
        LuaScriptCache item = compileScript(manager, "holdmyitems/item_pose.lua");
        ModelScriptCache itemModel = compileModelScript(manager, "holdmyitems/item_model.lua");
        ArrayList<LuaScriptCache> handAddons = loadMultiple(manager, "holdmyitems/hand_addon.lua");
        ArrayList<LuaScriptCache> handRelativeAddons = loadMultiple(manager, "holdmyitems/hand_relative_addon.lua");
        ArrayList<LuaScriptCache> itemAddons = loadMultiple(manager, "holdmyitems/item_addon.lua");
        ArrayList<ModelScriptCache> itemModelAddons = loadMultipleModel(manager, "holdmyitems/item_model_addon.lua");
        if (!hand.isOperational() || !handRelative.isOperational() || !item.isOperational() || !itemModel.isOperational()) {
            throw new IOException("One or more primary HandMyItems scripts did not produce an operational cache");
        }
        return new LoadResult(hand, handRelative, item, itemModel, handAddons, handRelativeAddons, itemAddons, itemModelAddons, null, false);
    }

    private void install(LoadResult result) {
        if (result.hand == null || result.handRelative == null || result.item == null || result.itemModel == null
            || !result.hand.isOperational() || !result.handRelative.isOperational()
            || !result.item.isOperational() || !result.itemModel.isOperational()) {
            HandMyItemsRuntime.onReloadFailure(new IllegalStateException("Refusing to install incomplete primary HandMyItems script state"));
            return;
        }
        // Commit as one unit so a failed preparation cannot combine old and new scripts.
        HandMyItemsRuntime.onScriptsReloaded();
        GlobalsStorage.renderAsBlock.clear();
        GlobalsStorage.translateItem.clear();
        GlobalsStorage.applyBlockRotation.clear();
        GlobalsStorage.itemSwingSpeed.clear();
        GlobalsStorage.useDuration.clear();
        GlobalsStorage.usingItem.clear();
        GlobalsStorage.registry.clear();
        ScriptHolder.install(
            result.hand,
            result.handRelative,
            result.item,
            result.itemModel,
            result.handAddons,
            result.handRelativeAddons,
            result.itemAddons,
            result.itemModelAddons
        );
        LuaTestHMI.LOGGER.info("Loaded embedded HandMyItems Lua scripts");
        HandMyItemsRuntime.onScriptsReady();
    }

    private String preprocessScript(String script) {
        if (script == null || script.isEmpty()) {
            return script;
        }

        HashSet<String> persistVars = new HashSet<>();
        StringBuilder processed = new StringBuilder();
        boolean respackoptsLoaded = FabricLoader.getInstance().isModLoaded("respackopts");
        for (String rawLine : script.split("\\r?\\n")) {
            String line = rawLine;
            if (!respackoptsLoaded) {
                Matcher placeholderMatcher = PLACEHOLDER_PATTERN.matcher(line);
                line = placeholderMatcher.replaceAll("0");
            }

            Matcher globalMatcher = GLOBAL_PATTERN.matcher(line);
            StringBuffer lineBuffer = new StringBuffer();
            boolean found = false;
            while (globalMatcher.find()) {
                String varName = globalMatcher.group(1);
                String value = globalMatcher.group(2).trim();
                persistVars.add(varName);
                globalMatcher.appendReplacement(
                    lineBuffer,
                    Matcher.quoteReplacement("local " + varName + " = registry:getOrDefault('" + varName + "', " + value + ")")
                );
                found = true;
            }
            globalMatcher.appendTail(lineBuffer);
            processed.append(found ? lineBuffer : line).append('\n');
        }

        if (!persistVars.isEmpty()) {
            processed.append("\n-- PERSIST VARIABLES\n");
            for (String varName : persistVars) {
                processed.append("registry:put('").append(varName).append("', ").append(varName).append(")\n");
            }
        }
        return processed.toString();
    }

    private String loadSingle(ResourceManager manager, String path) throws IOException {
        Identifier id = Identifier.fromNamespaceAndPath("minecraft", path);
        Resource resource = manager.getResource(id).orElse(null);
        if (resource == null) {
            throw new IOException("Required HandMyItems script is missing: " + id);
        }
        return read(resource, id);
    }

    private LuaScriptCache compileScript(ResourceManager manager, String path) throws IOException {
        Identifier id = Identifier.fromNamespaceAndPath("minecraft", path);
        String source = loadSingle(manager, path);
        try {
            LuaScriptCache cache = new LuaScriptCache(source, id.toString());
            HandMyItemsRuntime.diagnostic("primary script compiled id=" + id);
            return cache;
        } catch (Exception exception) {
            throw new IOException("Unable to compile HandMyItems script " + id, exception);
        }
    }

    private ModelScriptCache compileModelScript(ResourceManager manager, String path) throws IOException {
        Identifier id = Identifier.fromNamespaceAndPath("minecraft", path);
        String source = loadSingle(manager, path);
        try {
            ModelScriptCache cache = new ModelScriptCache(source, id.toString());
            HandMyItemsRuntime.diagnostic("primary script compiled id=" + id);
            return cache;
        } catch (Exception exception) {
            throw new IOException("Unable to compile HandMyItems script " + id, exception);
        }
    }

    private ArrayList<LuaScriptCache> loadMultiple(ResourceManager manager, String path) throws IOException {
        Identifier id = Identifier.fromNamespaceAndPath("minecraft", path);
        ArrayList<LuaScriptCache> caches = new ArrayList<>();
        for (Resource resource : manager.getResourceStack(id)) {
            caches.add(new LuaScriptCache(read(resource, id), id + " from " + resource.sourcePackId()));
        }
        return caches;
    }

    private ArrayList<ModelScriptCache> loadMultipleModel(ResourceManager manager, String path) throws IOException {
        Identifier id = Identifier.fromNamespaceAndPath("minecraft", path);
        ArrayList<ModelScriptCache> caches = new ArrayList<>();
        for (Resource resource : manager.getResourceStack(id)) {
            caches.add(new ModelScriptCache(read(resource, id), id + " from " + resource.sourcePackId()));
        }
        return caches;
    }

    private String read(Resource resource, Identifier id) throws IOException {
        try (InputStream stream = resource.open()) {
            byte[] bytes = stream.readAllBytes();
            HandMyItemsRuntime.diagnostic("primary resource found id=" + id + " bytes=" + bytes.length + " pack=" + resource.sourcePackId());
            return preprocessScript(new String(bytes, StandardCharsets.UTF_8));
        } catch (IOException exception) {
            throw new IOException("Failed to read HandMyItems script " + id + " from " + resource.sourcePackId(), exception);
        } catch (RuntimeException exception) {
            throw new IOException("Failed to parse HandMyItems script " + id + " from " + resource.sourcePackId(), exception);
        }
    }

    protected static final class LoadResult {
        private final LuaScriptCache hand;
        private final LuaScriptCache handRelative;
        private final LuaScriptCache item;
        private final ModelScriptCache itemModel;
        private final ArrayList<LuaScriptCache> handAddons;
        private final ArrayList<LuaScriptCache> handRelativeAddons;
        private final ArrayList<LuaScriptCache> itemAddons;
        private final ArrayList<ModelScriptCache> itemModelAddons;
        private final Exception failure;
        private final boolean skipped;

        private LoadResult(
            LuaScriptCache hand,
            LuaScriptCache handRelative,
            LuaScriptCache item,
            ModelScriptCache itemModel,
            ArrayList<LuaScriptCache> handAddons,
            ArrayList<LuaScriptCache> handRelativeAddons,
            ArrayList<LuaScriptCache> itemAddons,
            ArrayList<ModelScriptCache> itemModelAddons,
            Exception failure,
            boolean skipped
        ) {
            this.hand = hand;
            this.handRelative = handRelative;
            this.item = item;
            this.itemModel = itemModel;
            this.handAddons = handAddons;
            this.handRelativeAddons = handRelativeAddons;
            this.itemAddons = itemAddons;
            this.itemModelAddons = itemModelAddons;
            this.failure = failure;
            this.skipped = skipped;
        }

        private static LoadResult skipped() {
            return new LoadResult(null, null, null, null, null, null, null, null, null, true);
        }

        private static LoadResult failed(Exception exception) {
            return new LoadResult(null, null, null, null, null, null, null, null, exception, false);
        }
    }
}
