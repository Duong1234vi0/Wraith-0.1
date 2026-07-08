/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_1309
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1921
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_4587
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_811
 *  net.minecraft.class_918
 *  org.joml.Math
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 *  org.joml.Quaternionf
 *  org.joml.Vector3f
 *  org.joml.Vector4f
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.Instance;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_811;
import net.minecraft.class_918;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class ItemReplacer
extends Module {
    private static volatile SwordVariant[] swordVariants;
    private static volatile Map<String, SwordVariant> variantsByLabel;
    private static ItemReplacer instance;
    private static final ThreadLocal<class_1799> PENDING_STACK;
    private static final ThreadLocal<class_811> PENDING_MODE;
    private static final ThreadLocal<String> PENDING_VARIANT;
    private final MultiSelectSetting swordTypes;
    private final SelectSetting diamondSwordModel;
    private final SelectSetting netheriteSwordModel;
    private final SelectSetting diamondAxeModel;
    private final SelectSetting netheriteAxeModel;
    private String cachedSelectedLabel;
    private SwordVariant cachedSelectedVariant;

    private static void clearPending() {
        PENDING_STACK.remove();
        PENDING_MODE.remove();
        PENDING_VARIANT.remove();
    }

    public static ItemReplacer getInstance() {
        return Instance.get(ItemReplacer.class);
    }

    public ItemReplacer() {
        super("ItemReplacer", "\u0417\u0430\u043c\u0435\u043d\u043a\u0438 \u043d\u0430 \u041c\u0435\u0447", ModuleCategory.RENDER);
        SwordVariant[] variants = ItemReplacer.swordVariants();
        this.cachedSelectedVariant = variants[0];
        this.swordTypes = new MultiSelectSetting("\u0417\u0430\u043c\u0435\u043d\u044f\u0442\u044c", "\u041a\u0430\u043a\u0438\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0437\u0430\u043c\u0435\u043d\u044f\u0442\u044c").value("\u0410\u043b\u043c\u0430\u0437\u043d\u044b\u0439 \u043c\u0435\u0447", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u043c\u0435\u0447", "\u0410\u043b\u043c\u0430\u0437\u043d\u044b\u0439 \u0442\u043e\u043f\u043e\u0440", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u0442\u043e\u043f\u043e\u0440").selected("\u0410\u043b\u043c\u0430\u0437\u043d\u044b\u0439 \u043c\u0435\u0447", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u043c\u0435\u0447");
        this.diamondSwordModel = this.createModelSetting("\u041c\u043e\u0434\u0435\u043b\u044c \u0430\u043b\u043c. \u043c\u0435\u0447\u0430", "\u0410\u043b\u043c\u0430\u0437\u043d\u044b\u0439 \u043c\u0435\u0447", variants);
        this.netheriteSwordModel = this.createModelSetting("\u041c\u043e\u0434\u0435\u043b\u044c \u043d\u0435\u0437. \u043c\u0435\u0447\u0430", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u043c\u0435\u0447", variants);
        this.diamondAxeModel = this.createModelSetting("\u041c\u043e\u0434\u0435\u043b\u044c \u0430\u043b\u043c. \u0442\u043e\u043f\u043e\u0440\u0430", "\u0410\u043b\u043c\u0430\u0437\u043d\u044b\u0439 \u0442\u043e\u043f\u043e\u0440", variants);
        this.netheriteAxeModel = this.createModelSetting("\u041c\u043e\u0434\u0435\u043b\u044c \u043d\u0435\u0437. \u0442\u043e\u043f\u043e\u0440\u0430", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u0442\u043e\u043f\u043e\u0440", variants);
        this.setup(this.swordTypes, this.diamondSwordModel, this.netheriteSwordModel, this.diamondAxeModel, this.netheriteAxeModel);
        instance = this;
    }

    private SelectSetting createModelSetting(String name, String typeKey, SwordVariant[] variants) {
        return new SelectSetting(name, "\u041c\u043e\u0434\u0435\u043b\u044c \u0434\u043b\u044f " + typeKey.toLowerCase()).values(Arrays.asList(ItemReplacer.getVariantLabels(variants))).selected(variants[0].label).visible(() -> this.swordTypes.isSelected(typeKey));
    }

    public static boolean captureForRender(class_1799 stack, class_811 transformType, class_1309 entity) {
        ItemReplacer.clearPending();
        if (stack == null || stack.method_7960() || entity == null) {
            return false;
        }
        ItemReplacer replacer = instance;
        if (replacer == null || !replacer.isState()) {
            return false;
        }
        String variantOverride = replacer.resolveVariantLabel(entity, stack);
        if (variantOverride == null) {
            return false;
        }
        PENDING_STACK.set(stack);
        PENDING_MODE.set(transformType);
        PENDING_VARIANT.set(variantOverride);
        return true;
    }

    public static boolean renderPending(class_4587 matrixStack, class_4597 buffer, int combinedLight, int combinedOverlay) {
        class_1799 stack = PENDING_STACK.get();
        class_811 transformType = PENDING_MODE.get();
        String variantLabel = PENDING_VARIANT.get();
        ItemReplacer.clearPending();
        if (stack == null || transformType == null || variantLabel == null) {
            return false;
        }
        ItemReplacer replacer = instance;
        if (replacer == null || !replacer.isState()) {
            return false;
        }
        return replacer.resolveVariant(variantLabel).getModel().render(stack, transformType, matrixStack, buffer, combinedLight, combinedOverlay);
    }

    @Nullable
    private String resolveVariantLabel(class_1309 entity, class_1799 stack) {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null || entity != client.field_1724 || !this.shouldReplace(stack)) {
            return null;
        }
        return this.getModelLabelFor(stack.method_7909());
    }

    @Nullable
    private String getModelLabelFor(class_1792 item) {
        if (item == class_1802.field_8802 && this.swordTypes.isSelected("\u0410\u043b\u043c\u0430\u0437\u043d\u044b\u0439 \u043c\u0435\u0447")) {
            return this.diamondSwordModel.getSelected();
        }
        if (item == class_1802.field_22022 && this.swordTypes.isSelected("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u043c\u0435\u0447")) {
            return this.netheriteSwordModel.getSelected();
        }
        if (item == class_1802.field_8556 && this.swordTypes.isSelected("\u0410\u043b\u043c\u0430\u0437\u043d\u044b\u0439 \u0442\u043e\u043f\u043e\u0440")) {
            return this.diamondAxeModel.getSelected();
        }
        if (item == class_1802.field_22025 && this.swordTypes.isSelected("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u0442\u043e\u043f\u043e\u0440")) {
            return this.netheriteAxeModel.getSelected();
        }
        return null;
    }

    private SwordVariant resolveVariant(@Nullable String labelOverride) {
        if (labelOverride == null || labelOverride.isBlank()) {
            return this.getSelectedVariant();
        }
        SwordVariant variant = ItemReplacer.variantsByLabel().get(labelOverride.toLowerCase(Locale.ROOT));
        return variant != null ? variant : this.getSelectedVariant();
    }

    private boolean shouldReplace(class_1799 stack) {
        return this.getModelLabelFor(stack.method_7909()) != null;
    }

    public SwordVariant getSelectedVariant() {
        return this.getSelectedVariant(this.diamondSwordModel.getSelected());
    }

    public SwordVariant getSelectedVariant(@Nullable String label) {
        if (label == null || label.isBlank()) {
            return ItemReplacer.swordVariants()[0];
        }
        if (label.equalsIgnoreCase(this.cachedSelectedLabel)) {
            return this.cachedSelectedVariant;
        }
        SwordVariant variant = ItemReplacer.variantsByLabel().get(label.toLowerCase(Locale.ROOT));
        if (variant == null) {
            variant = ItemReplacer.swordVariants()[0];
        }
        this.cachedSelectedLabel = label;
        this.cachedSelectedVariant = variant;
        return variant;
    }

    @Override
    public void deactivate() {
        this.clearLoadedModels();
        ItemReplacer.clearPending();
        super.deactivate();
    }

    public void clearLoadedModels() {
        for (SwordVariant variant : ItemReplacer.swordVariants()) {
            variant.clear();
        }
    }

    public static String[] getVariantLabels() {
        return ItemReplacer.getVariantLabels(ItemReplacer.swordVariants());
    }

    private static String[] getVariantLabels(SwordVariant[] variants) {
        String[] labels = new String[variants.length];
        for (int i = 0; i < variants.length; ++i) {
            labels[i] = variants[i].label;
        }
        return labels;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static SwordVariant[] swordVariants() {
        SwordVariant[] cached = swordVariants;
        if (cached != null) {
            return cached;
        }
        Class<ItemReplacer> clazz = ItemReplacer.class;
        synchronized (ItemReplacer.class) {
            cached = swordVariants;
            if (cached == null) {
                cached = ItemReplacer.createSwordVariants();
                swordVariants = cached;
            }
            // ** MonitorExit[var1_1] (shouldn't be in output)
            return cached;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Map<String, SwordVariant> variantsByLabel() {
        Map<String, SwordVariant> cached = variantsByLabel;
        if (cached != null) {
            return cached;
        }
        Class<ItemReplacer> clazz = ItemReplacer.class;
        synchronized (ItemReplacer.class) {
            cached = variantsByLabel;
            if (cached == null) {
                variantsByLabel = cached = ItemReplacer.createVariantMap(ItemReplacer.swordVariants());
            }
            // ** MonitorExit[var1_1] (shouldn't be in output)
            return cached;
        }
    }

    private static Map<String, SwordVariant> createVariantMap(SwordVariant[] variants) {
        HashMap<String, SwordVariant> map = new HashMap<String, SwordVariant>(variants.length * 2);
        for (SwordVariant variant : variants) {
            map.put(variant.label.toLowerCase(Locale.ROOT), variant);
        }
        return map;
    }

    private static SwordVariant[] createSwordVariants() {
        String[][] data = new String[][]{{"Abominable Blade", "abominableblade"}, {"Abominable Greatsaber", "abominablegreatsaber"}, {"Abominable Scythe", "abominablescythe"}, {"Acid Demon", "aciddemon"}, {"Amethyst Shuriken", "amethyst_shuriken"}, {"Ancient Royal Greatsword", "ancient_royal_great_sword"}, {"Aquantic Sacred Blade", "aquantic_sacred_blade"}, {"Aquantic Trident", "aquantictrident"}, {"Arcanethyst", "arcanethyst"}, {"Ashura Blade", "ashura_blade"}, {"Awakened Lichblade", "awakened_lichblade"}, {"Blood Edge", "bloodedge"}, {"Bloody Death", "bloodydeath"}, {"Bramblethorn", "bramblethorn"}, {"Brimstone Claymore", "brimstone_claymore"}, {"Carian Sword", "cariansword"}, {"Chrono Blade", "chrono_blade"}, {"Corrupted Mythic Blade", "corruptedmythicblade"}, {"Creation Splitter", "creationsplitter"}, {"Crescent Rose", "crescentrose"}, {"Cyber Katana", "cyberkatana"}, {"Cyber Mantis Blade", "cybermantisblade"}, {"Cybernetic Katana", "cybernetickatana"}, {"Cybernetic Knife", "cyberneticknife"}, {"Cybernetic Sawblade", "cyberneticsawblade"}, {"Cyber Sword", "cybersword"}, {"Dainsleif", "dainsleif"}, {"Dark Blade", "dark_blade"}, {"Dark Cleaver", "dark_cleaver"}, {"Death Knight Dagger", "death_knight_dagger"}, {"Death Knight Sword", "death_knight_sword"}, {"Demigod's Unholy Blade", "demigodsunholyblade"}, {"Demigod's Unholy Halberd", "demigodsunholyhalberd"}, {"Demonic Blade", "demonicblade"}, {"Demonic Cleaver", "demoniccleaver"}, {"Demon Lord's Great Axe", "demonlordsgreataxe"}, {"Demon Lord's Sword", "demonlordsword"}, {"Divine Justice", "divine_justice"}, {"Divine Reaper", "divine_reaper"}, {"Divine Axe Rhitta", "divineaxerhitta"}, {"Divine Punisher", "divinepunisher"}, {"Dragon Slaying Blade", "dragonslayingblade"}, {"Edge of the Astral Plane", "edgeoftheastralplane"}, {"Emberblade", "emberblade"}, {"Enigma", "enigma"}, {"Epic Sword", "epicsword"}, {"Estoc", "estoc"}, {"Excalibur", "excalibur"}, {"Fallen God Spear", "fallengodspear"}, {"Fallen God Sword", "fallengodsword"}, {"Floral Longsword", "floral_longsword"}, {"Floral Sabre", "floral_sabre"}, {"Forest Guardian Glaive", "forest_guardian_glaive"}, {"Frost Axe", "frostaxe"}, {"Frostblade", "frostblade"}, {"Frost Scythe", "frostscythe"}, {"Green Scythe", "greenscythe"}, {"Hearthflame", "hearthflame"}, {"Hero Sword", "herosword"}, {"Holy Moonlight Sword", "holymoonlightsword"}, {"Hornet's Needle", "hornetsneedle"}, {"Ice Whisper", "icewhisper"}, {"Jade Halberd", "jadehalberd"}, {"Katana", "katana"}, {"Legendary Sword", "legendarysword"}, {"Longsword", "longsword"}, {"Magi Scythe", "magiscythe"}, {"Masamune", "masamune"}, {"Mjolnir", "mjolnir"}, {"Molten Blade", "moltenblade"}, {"Molten Sword", "moltensword"}, {"Muramasa", "muramasa"}, {"Mystical Spellblade", "mysticalspellblade"}, {"Mythic Blade", "mythicblade"}, {"Partisan", "partisan"}, {"Pharaoh's Treasure", "pharaohs_treasure"}, {"Phoenix Grace", "pheonixgrace"}, {"Powerfuse Hammer", "powerfusehammer"}, {"Powerfuse Sword", "powerfusesword"}, {"Requiem of Hell", "requiem_of_hell"}, {"Ribbon Cleaver", "ribboncleaver"}, {"Righteous Relic", "righteous_relic"}, {"Rivers of Blood", "riversofblood"}, {"Royal Chakram", "royalchakram"}, {"Royal Rapier", "royalrapier"}, {"Sabre", "sabre"}, {"Scissor Blade", "scissorblade"}, {"Sculk Cleaver", "sculkcleaver"}, {"Sculk Scythe", "sculkscythe"}, {"Sculk Sword", "sculksword"}, {"Sentinel's Will", "sentinels_will"}, {"Silverine Blade", "silverine_blade"}, {"Soul Claws", "soulclaws"}, {"Soul Edge", "souledge"}, {"Soul Harvester", "soulharvester"}, {"Soul Render", "soulrender"}, {"Soul Stealer", "soulstealer"}, {"Soul Collector", "soul_collector"}, {"Soul Devourer", "soul_devourer"}, {"Star's Edge", "stars_edge"}, {"Steel Sword", "steelsword"}, {"Stop Sign", "stop_sign"}, {"Stormbringer", "stormbringer"}, {"Storm's Edge", "storms_edge"}, {"Sunbreak", "sunbreak"}, {"Tengen's Blade", "tengensblade"}, {"Terrablade", "terrablade"}, {"Thousand Demon Daggers", "thousanddemondaggers"}, {"Thunderbrand", "thunderbrand"}, {"Thunderbringer", "thunderbringer"}, {"Toxic Longsword", "toxic_longsword"}, {"Vampiric Needle", "vampiricneedle"}, {"Wakizashi", "wakizashi"}, {"Watcher Claymore", "watcher_claymore"}, {"Watching Warglaive", "watching_warglaive"}, {"Waxweaver", "waxweaver"}, {"Whisperwind", "whisperwind"}, {"Wickpiercer", "wickpiercer"}, {"Yoru", "yoru"}};
        SwordVariant[] variants = new SwordVariant[data.length];
        for (int i = 0; i < data.length; ++i) {
            variants[i] = new SwordVariant(data[i][0], data[i][1]);
        }
        return variants;
    }

    static {
        PENDING_STACK = new ThreadLocal();
        PENDING_MODE = new ThreadLocal();
        PENDING_VARIANT = new ThreadLocal();
    }

    public static final class SwordVariant {
        public final String label;
        public final String modelName;
        private RuntimeSwordModel model;

        private SwordVariant(String label, String modelName) {
            this.label = label;
            this.modelName = modelName;
        }

        RuntimeSwordModel getModel() {
            if (this.model == null) {
                this.model = new RuntimeSwordModel(this.modelName);
            }
            return this.model;
        }

        void clear() {
            if (this.model != null) {
                this.model.clear();
            }
        }
    }

    private static final class RuntimeSwordModel {
        private static final String MODEL_ROOT = "item_replacer/sword/models/";
        private static final String TEXTURE_ROOT = "item_replacer/sword/textures/";
        private static final String NAMESPACE = "mre";
        private static final float PIXEL = 0.0625f;
        private static final float MODEL_CENTER = 0.5f;
        private static final int VERTEX_STRIDE = 8;
        private final String modelPath;
        private final ArrayList<ModelCube> cubes = new ArrayList();
        private final DisplayTransform[] displayTransforms = new DisplayTransform[8];
        private class_2960 textureId;
        private class_1921 renderLayer;
        private float[] bakedMesh = new float[0];
        private int vertexCount;
        private boolean loaded;

        private RuntimeSwordModel(String modelName) {
            this.modelPath = MODEL_ROOT + modelName + ".json";
        }

        private void clear() {
            this.loaded = false;
            this.textureId = null;
            this.renderLayer = null;
            this.bakedMesh = new float[0];
            this.vertexCount = 0;
            this.cubes.clear();
            for (int i = 0; i < this.displayTransforms.length; ++i) {
                this.displayTransforms[i] = null;
            }
        }

        private boolean render(class_1799 stack, class_811 transformType, class_4587 matrixStack, class_4597 buffer, int combinedLight, int combinedOverlay) {
            this.loadIfNeeded();
            if (this.vertexCount == 0 || this.renderLayer == null) {
                return false;
            }
            class_4588 consumer = stack.method_7958() ? class_918.method_23181((class_4597)buffer, (class_1921)this.renderLayer, (boolean)true, (boolean)true) : buffer.getBuffer(this.renderLayer);
            matrixStack.method_22903();
            this.applyDisplayTransform(matrixStack, transformType);
            class_4587.class_4665 entry = matrixStack.method_23760();
            this.drawMesh(consumer, entry.method_23761(), entry.method_23762(), combinedLight, combinedOverlay);
            matrixStack.method_22909();
            return true;
        }

        private class_310 mc() {
            return class_310.method_1551();
        }

        private void loadIfNeeded() {
            if (this.loaded) {
                return;
            }
            this.loaded = true;
            try (InputStream input = this.mc().method_1478().open(class_2960.method_60655((String)NAMESPACE, (String)this.modelPath));
                 InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);){
                JsonObject root = JsonParser.parseReader((Reader)reader).getAsJsonObject();
                this.readModel(root);
            }
            catch (Exception e) {
                System.err.println("[ItemReplacer] Failed to load model " + this.modelPath + ": " + e.getMessage());
            }
        }

        private void readModel(JsonObject root) {
            JsonObject texturesObj = root.getAsJsonObject("textures");
            if (texturesObj == null) {
                return;
            }
            HashMap<String, String> textureMap = new HashMap<String, String>();
            for (Map.Entry e : texturesObj.entrySet()) {
                textureMap.put((String)e.getKey(), ((JsonElement)e.getValue()).getAsString());
            }
            String primaryKey = this.resolvePrimaryTextureKey(textureMap, root);
            String primaryPath = (String)textureMap.get(primaryKey);
            if (primaryPath == null) {
                return;
            }
            String texName = primaryPath.contains("/") ? primaryPath.substring(primaryPath.lastIndexOf(47) + 1) : primaryPath;
            this.textureId = class_2960.method_60655((String)NAMESPACE, (String)(TEXTURE_ROOT + texName + ".png"));
            this.renderLayer = this.hasPartialAlpha(this.textureId) ? class_1921.method_29379((class_2960)this.textureId) : class_1921.method_23576((class_2960)this.textureId);
            JsonArray elements = root.getAsJsonArray("elements");
            if (elements == null) {
                return;
            }
            String primaryRef = "#" + primaryKey;
            for (JsonElement el : elements) {
                JsonObject obj = el.getAsJsonObject();
                JsonArray from = obj.getAsJsonArray("from");
                JsonArray to = obj.getAsJsonArray("to");
                if (from == null || to == null) continue;
                ModelCube cube = new ModelCube(from.get(0).getAsFloat() * 0.0625f, from.get(1).getAsFloat() * 0.0625f, from.get(2).getAsFloat() * 0.0625f, to.get(0).getAsFloat() * 0.0625f, to.get(1).getAsFloat() * 0.0625f, to.get(2).getAsFloat() * 0.0625f);
                JsonObject faces = obj.getAsJsonObject("faces");
                if (faces == null || !this.readFaces(cube, faces, primaryRef)) continue;
                if (obj.has("rotation")) {
                    this.readRotation(cube, obj.getAsJsonObject("rotation"));
                }
                this.cubes.add(cube);
            }
            this.bakeMesh();
            this.readDisplayTransforms(root);
        }

        private boolean readFaces(ModelCube cube, JsonObject faces, String primaryRef) {
            boolean has = false;
            if (faces.has("north")) {
                cube.north = this.readFace(faces.getAsJsonObject("north"), primaryRef);
                has |= cube.north != null;
            }
            if (faces.has("south")) {
                cube.south = this.readFace(faces.getAsJsonObject("south"), primaryRef);
                has |= cube.south != null;
            }
            if (faces.has("east")) {
                cube.east = this.readFace(faces.getAsJsonObject("east"), primaryRef);
                has |= cube.east != null;
            }
            if (faces.has("west")) {
                cube.west = this.readFace(faces.getAsJsonObject("west"), primaryRef);
                has |= cube.west != null;
            }
            if (faces.has("up")) {
                cube.up = this.readFace(faces.getAsJsonObject("up"), primaryRef);
                has |= cube.up != null;
            }
            if (faces.has("down")) {
                cube.down = this.readFace(faces.getAsJsonObject("down"), primaryRef);
                has |= cube.down != null;
            }
            return has;
        }

        private FaceUv readFace(JsonObject face, String primaryRef) {
            if (face.has("texture") && !face.get("texture").getAsString().equals(primaryRef)) {
                return null;
            }
            JsonArray uv = face.getAsJsonArray("uv");
            if (uv == null || uv.size() < 4) {
                return null;
            }
            return new FaceUv(uv.get(0).getAsFloat() * 0.0625f, uv.get(1).getAsFloat() * 0.0625f, uv.get(2).getAsFloat() * 0.0625f, uv.get(3).getAsFloat() * 0.0625f);
        }

        private void readRotation(ModelCube cube, JsonObject rotation) {
            if (!(rotation != null && rotation.has("origin") && rotation.has("axis") && rotation.has("angle"))) {
                return;
            }
            JsonArray origin = rotation.getAsJsonArray("origin");
            cube.rotationOriginX = origin.get(0).getAsFloat() * 0.0625f;
            cube.rotationOriginY = origin.get(1).getAsFloat() * 0.0625f;
            cube.rotationOriginZ = origin.get(2).getAsFloat() * 0.0625f;
            cube.setRotation(rotation.get("axis").getAsString(), rotation.get("angle").getAsFloat());
        }

        private String resolvePrimaryTextureKey(Map<String, String> textureMap, JsonObject root) {
            if (textureMap.containsKey("0")) {
                return "0";
            }
            HashMap<String, Integer> useCount = new HashMap<String, Integer>();
            JsonArray elements = root.getAsJsonArray("elements");
            if (elements != null) {
                for (JsonElement el : elements) {
                    JsonObject faces = el.getAsJsonObject().getAsJsonObject("faces");
                    if (faces == null) continue;
                    for (Map.Entry fe : faces.entrySet()) {
                        JsonObject f = ((JsonElement)fe.getValue()).getAsJsonObject();
                        if (!f.has("texture")) continue;
                        String ref = f.get("texture").getAsString().replace("#", "");
                        useCount.merge(ref, 1, Integer::sum);
                    }
                }
            }
            String best = null;
            int bestCount = -1;
            for (Map.Entry e : useCount.entrySet()) {
                if (!textureMap.containsKey(e.getKey()) || "particle".equals(e.getKey()) || (Integer)e.getValue() <= bestCount) continue;
                best = (String)e.getKey();
                bestCount = (Integer)e.getValue();
            }
            if (best != null) {
                return best;
            }
            for (String key : textureMap.keySet()) {
                if ("particle".equals(key)) continue;
                return key;
            }
            return textureMap.keySet().iterator().next();
        }

        /*
         * Exception decompiling
         */
        private boolean hasPartialAlpha(class_2960 id) {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 16[FORLOOP]
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:923)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doClass(Driver.java:84)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:78)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             */
            throw new IllegalStateException("Decompilation failed");
        }

        private void bakeMesh() {
            int faceCount = 0;
            for (ModelCube c : this.cubes) {
                faceCount += c.countFaces();
            }
            float[] baked = new float[faceCount * 4 * 8];
            int offset = 0;
            for (ModelCube c : this.cubes) {
                offset = this.bakeCube(baked, offset, c);
            }
            this.bakedMesh = baked;
            this.vertexCount = offset / 8;
            this.cubes.clear();
            this.cubes.trimToSize();
        }

        private int bakeCube(float[] out, int offset, ModelCube cube) {
            FaceUv f;
            float x1 = cube.x1;
            float y1 = cube.y1;
            float z1 = cube.z1;
            float x2 = cube.x2;
            float y2 = cube.y2;
            float z2 = cube.z2;
            if (cube.north != null) {
                f = cube.north;
                offset = this.writeBakedVertex(out, offset, cube, x2, y1, z1, f.u1, f.v2, 0.0f, 0.0f, -1.0f);
                offset = this.writeBakedVertex(out, offset, cube, x1, y1, z1, f.u2, f.v2, 0.0f, 0.0f, -1.0f);
                offset = this.writeBakedVertex(out, offset, cube, x1, y2, z1, f.u2, f.v1, 0.0f, 0.0f, -1.0f);
                offset = this.writeBakedVertex(out, offset, cube, x2, y2, z1, f.u1, f.v1, 0.0f, 0.0f, -1.0f);
            }
            if (cube.south != null) {
                f = cube.south;
                offset = this.writeBakedVertex(out, offset, cube, x1, y1, z2, f.u1, f.v2, 0.0f, 0.0f, 1.0f);
                offset = this.writeBakedVertex(out, offset, cube, x2, y1, z2, f.u2, f.v2, 0.0f, 0.0f, 1.0f);
                offset = this.writeBakedVertex(out, offset, cube, x2, y2, z2, f.u2, f.v1, 0.0f, 0.0f, 1.0f);
                offset = this.writeBakedVertex(out, offset, cube, x1, y2, z2, f.u1, f.v1, 0.0f, 0.0f, 1.0f);
            }
            if (cube.east != null) {
                f = cube.east;
                offset = this.writeBakedVertex(out, offset, cube, x2, y1, z2, f.u1, f.v2, 1.0f, 0.0f, 0.0f);
                offset = this.writeBakedVertex(out, offset, cube, x2, y1, z1, f.u2, f.v2, 1.0f, 0.0f, 0.0f);
                offset = this.writeBakedVertex(out, offset, cube, x2, y2, z1, f.u2, f.v1, 1.0f, 0.0f, 0.0f);
                offset = this.writeBakedVertex(out, offset, cube, x2, y2, z2, f.u1, f.v1, 1.0f, 0.0f, 0.0f);
            }
            if (cube.west != null) {
                f = cube.west;
                offset = this.writeBakedVertex(out, offset, cube, x1, y1, z1, f.u1, f.v2, -1.0f, 0.0f, 0.0f);
                offset = this.writeBakedVertex(out, offset, cube, x1, y1, z2, f.u2, f.v2, -1.0f, 0.0f, 0.0f);
                offset = this.writeBakedVertex(out, offset, cube, x1, y2, z2, f.u2, f.v1, -1.0f, 0.0f, 0.0f);
                offset = this.writeBakedVertex(out, offset, cube, x1, y2, z1, f.u1, f.v1, -1.0f, 0.0f, 0.0f);
            }
            if (cube.up != null) {
                f = cube.up;
                offset = this.writeBakedVertex(out, offset, cube, x1, y2, z1, f.u1, f.v1, 0.0f, 1.0f, 0.0f);
                offset = this.writeBakedVertex(out, offset, cube, x1, y2, z2, f.u1, f.v2, 0.0f, 1.0f, 0.0f);
                offset = this.writeBakedVertex(out, offset, cube, x2, y2, z2, f.u2, f.v2, 0.0f, 1.0f, 0.0f);
                offset = this.writeBakedVertex(out, offset, cube, x2, y2, z1, f.u2, f.v1, 0.0f, 1.0f, 0.0f);
            }
            if (cube.down != null) {
                f = cube.down;
                offset = this.writeBakedVertex(out, offset, cube, x1, y1, z2, f.u1, f.v1, 0.0f, -1.0f, 0.0f);
                offset = this.writeBakedVertex(out, offset, cube, x1, y1, z1, f.u1, f.v2, 0.0f, -1.0f, 0.0f);
                offset = this.writeBakedVertex(out, offset, cube, x2, y1, z1, f.u2, f.v2, 0.0f, -1.0f, 0.0f);
                offset = this.writeBakedVertex(out, offset, cube, x2, y1, z2, f.u2, f.v1, 0.0f, -1.0f, 0.0f);
            }
            return offset;
        }

        private int writeBakedVertex(float[] out, int offset, ModelCube cube, float x, float y, float z, float u, float v, float nx, float ny, float nz) {
            if (cube.rotationAxis != 0) {
                float dx = x - cube.rotationOriginX;
                float dy = y - cube.rotationOriginY;
                float dz = z - cube.rotationOriginZ;
                float sin = cube.rotationSin;
                float cos = cube.rotationCos;
                switch (cube.rotationAxis) {
                    case 1: {
                        y = cube.rotationOriginY + dy * cos - dz * sin;
                        z = cube.rotationOriginZ + dy * sin + dz * cos;
                        float rny = ny * cos - nz * sin;
                        nz = ny * sin + nz * cos;
                        ny = rny;
                        break;
                    }
                    case 2: {
                        x = cube.rotationOriginX + dx * cos + dz * sin;
                        z = cube.rotationOriginZ - dx * sin + dz * cos;
                        float rnx = nx * cos + nz * sin;
                        nz = -nx * sin + nz * cos;
                        nx = rnx;
                        break;
                    }
                    case 3: {
                        x = cube.rotationOriginX + dx * cos - dy * sin;
                        y = cube.rotationOriginY + dx * sin + dy * cos;
                        float rnx = nx * cos - ny * sin;
                        ny = nx * sin + ny * cos;
                        nx = rnx;
                        break;
                    }
                }
            }
            out[offset++] = x - 0.5f;
            out[offset++] = y - 0.5f;
            out[offset++] = z - 0.5f;
            out[offset++] = u;
            out[offset++] = v;
            out[offset++] = nx;
            out[offset++] = ny;
            out[offset++] = nz;
            return offset;
        }

        private void readDisplayTransforms(JsonObject root) {
            if (!root.has("display")) {
                return;
            }
            JsonObject d = root.getAsJsonObject("display");
            this.displayTransforms[0] = this.readDT(d, "thirdperson_righthand");
            this.displayTransforms[1] = this.readDT(d, "thirdperson_lefthand");
            this.displayTransforms[2] = this.readDT(d, "firstperson_righthand");
            this.displayTransforms[3] = this.readDT(d, "firstperson_lefthand");
            this.displayTransforms[4] = this.readDT(d, "ground");
            this.displayTransforms[5] = this.readDT(d, "gui");
            this.displayTransforms[6] = this.readDT(d, "head");
            this.displayTransforms[7] = this.readDT(d, "fixed");
        }

        private DisplayTransform readDT(JsonObject display, String key) {
            if (!display.has(key)) {
                return null;
            }
            JsonObject obj = display.getAsJsonObject(key);
            float[] rot = new float[]{0.0f, 0.0f, 0.0f};
            float[] trans = new float[]{0.0f, 0.0f, 0.0f};
            float[] scale = new float[]{1.0f, 1.0f, 1.0f};
            if (obj.has("rotation")) {
                this.readFA(obj.getAsJsonArray("rotation"), rot);
            }
            if (obj.has("translation")) {
                this.readFA(obj.getAsJsonArray("translation"), trans);
            }
            if (obj.has("scale")) {
                this.readFA(obj.getAsJsonArray("scale"), scale);
            }
            return new DisplayTransform(rot, trans, scale);
        }

        private void readFA(JsonArray arr, float[] target) {
            for (int i = 0; i < target.length && i < arr.size(); ++i) {
                target[i] = arr.get(i).getAsFloat();
            }
        }

        private void applyDisplayTransform(class_4587 ms, class_811 type) {
            DisplayTransform dt = this.getDisplayTransform(type);
            if (dt == null) {
                return;
            }
            boolean left = type == class_811.field_4323 || type == class_811.field_4321;
            dt.apply(ms, left);
        }

        private DisplayTransform getDisplayTransform(class_811 type) {
            return switch (type) {
                case class_811.field_4320 -> this.displayTransforms[0];
                case class_811.field_4323 -> this.displayTransforms[1];
                case class_811.field_4322 -> this.displayTransforms[2];
                case class_811.field_4321 -> this.displayTransforms[3];
                case class_811.field_4318 -> this.displayTransforms[4];
                case class_811.field_4317 -> this.displayTransforms[5];
                case class_811.field_4316 -> this.displayTransforms[6];
                case class_811.field_4319 -> this.displayTransforms[7];
                default -> null;
            };
        }

        private void drawMesh(class_4588 vc, Matrix4f matrix, Matrix3f normalMatrix, int light, int overlay) {
            int limit = this.vertexCount * 8;
            Vector4f pos = new Vector4f();
            Vector3f norm = new Vector3f();
            for (int i = 0; i < limit; i += 8) {
                pos.set(this.bakedMesh[i], this.bakedMesh[i + 1], this.bakedMesh[i + 2], 1.0f);
                matrix.transform(pos);
                norm.set(this.bakedMesh[i + 5], this.bakedMesh[i + 6], this.bakedMesh[i + 7]);
                normalMatrix.transform(norm);
                vc.method_22912(pos.x, pos.y, pos.z).method_1336(255, 255, 255, 255).method_22913(this.bakedMesh[i + 3], this.bakedMesh[i + 4]).method_22922(overlay).method_60803(light).method_22914(norm.x, norm.y, norm.z);
            }
        }
    }

    private static final class FaceUv {
        final float u1;
        final float v1;
        final float u2;
        final float v2;

        FaceUv(float u1, float v1, float u2, float v2) {
            this.u1 = u1;
            this.v1 = v1;
            this.u2 = u2;
            this.v2 = v2;
        }
    }

    private static final class ModelCube {
        static final int NO_ROTATION = 0;
        static final int ROTATE_X = 1;
        static final int ROTATE_Y = 2;
        static final int ROTATE_Z = 3;
        final float x1;
        final float y1;
        final float z1;
        final float x2;
        final float y2;
        final float z2;
        FaceUv north;
        FaceUv south;
        FaceUv east;
        FaceUv west;
        FaceUv up;
        FaceUv down;
        float rotationOriginX;
        float rotationOriginY;
        float rotationOriginZ;
        int rotationAxis = 0;
        float rotationSin;
        float rotationCos = 1.0f;

        ModelCube(float x1, float y1, float z1, float x2, float y2, float z2) {
            this.x1 = x1;
            this.y1 = y1;
            this.z1 = z1;
            this.x2 = x2;
            this.y2 = y2;
            this.z2 = z2;
        }

        int countFaces() {
            int n = 0;
            if (this.north != null) {
                ++n;
            }
            if (this.south != null) {
                ++n;
            }
            if (this.east != null) {
                ++n;
            }
            if (this.west != null) {
                ++n;
            }
            if (this.up != null) {
                ++n;
            }
            if (this.down != null) {
                ++n;
            }
            return n;
        }

        void setRotation(String axis, float angle) {
            switch (axis) {
                case "x": {
                    this.rotationAxis = 1;
                    break;
                }
                case "y": {
                    this.rotationAxis = 2;
                    break;
                }
                case "z": {
                    this.rotationAxis = 3;
                    break;
                }
                default: {
                    this.rotationAxis = 0;
                    return;
                }
            }
            float rad = (float)Math.toRadians(angle);
            this.rotationSin = (float)Math.sin(rad);
            this.rotationCos = (float)Math.cos(rad);
        }
    }

    private static final class DisplayTransform {
        private final float txN;
        private final float txM;
        private final float ty;
        private final float tz;
        private final float rx;
        private final float ry;
        private final float rz;
        private final float sx;
        private final float sy;
        private final float sz;
        private final boolean hasTrans;
        private final boolean hasRot;
        private final boolean hasScale;

        private DisplayTransform(float[] rotation, float[] translation, float[] scale) {
            this.txN = translation[0] / 16.0f;
            this.txM = -this.txN;
            this.ty = translation[1] / 16.0f;
            this.tz = translation[2] / 16.0f;
            this.rx = rotation[0];
            this.ry = rotation[1];
            this.rz = rotation[2];
            this.sx = scale[0];
            this.sy = scale[1];
            this.sz = scale[2];
            this.hasTrans = translation[0] != 0.0f || translation[1] != 0.0f || translation[2] != 0.0f;
            this.hasRot = rotation[0] != 0.0f || rotation[1] != 0.0f || rotation[2] != 0.0f;
            this.hasScale = scale[0] != 1.0f || scale[1] != 1.0f || scale[2] != 1.0f;
        }

        private void apply(class_4587 ms, boolean leftHand) {
            if (this.hasTrans) {
                ms.method_46416(leftHand ? this.txM : this.txN, this.ty, this.tz);
            }
            if (this.hasRot) {
                float yaw = leftHand ? -this.ry : this.ry;
                float pitch = leftHand ? -this.rx : this.rx;
                float roll = this.rz;
                if (yaw != 0.0f) {
                    ms.method_22907(new Quaternionf().rotationY(org.joml.Math.toRadians((float)yaw)));
                }
                if (pitch != 0.0f) {
                    ms.method_22907(new Quaternionf().rotationX(org.joml.Math.toRadians((float)pitch)));
                }
                if (roll != 0.0f) {
                    ms.method_22907(new Quaternionf().rotationZ(org.joml.Math.toRadians((float)roll)));
                }
            }
            if (this.hasScale) {
                ms.method_22905(this.sx, this.sy, this.sz);
            }
        }
    }
}
