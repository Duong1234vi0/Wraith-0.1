package ua.babka.module.impl.player;

import java.util.List;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.ModeSetting;

@ModuleInformation(moduleName="Item Replacer", moduleDesc="Замена предметов в инвентаре", moduleCategory=ModuleCategory.MISC)
public class ItemReplacer
extends Module {
    private static final String[] MODELS = new String[]{"Abominable Blade", "Abominable Great Saber", "Abominable Scythe", "Acidic Cleaver", "Amethyst Shuriken", "Ancient Royal Great Sword", "Aquatic Sacred Blade", "Arcanethyst", "Ashura's Blade", "Awakened Lichblade", "Blood Edge", "Bloody Death", "Bramblethorn", "Brimstone Claymore", "Carian Knight's Sword", "Chrono Blade", "Corrupted Mythic Blade", "Creation Splitter", "Crescent Rose", "Cyber Katana", "Cyber Mantis Blade", "Cyber Sword", "Cybernetic Chainsaw Blade", "Cybernetic Katana", "Cybernetic Knife", "Dainsleif", "Dark Blade", "Dark Cleaver", "Death Knight's Dagger", "Death Knight's Sword", "Demigod's Unholy Blade", "Demigod's Unholy Halberd", "Demon Lord's Great Axe", "Demon Lord's Sword", "Demonic Blade", "Demonic Cleaver", "Divine Axe Rhitta", "Divine Justice", "Divine Punisher", "Divine Reaper", "Dragon Slaying blade", "Edge Of The Astral Plane", "Emberblade", "Enigma", "Epic Sword", "Estoc", "Fallen God's Spear", "Fallen God's Sword", "Floral Longsword", "Floral Sabre", "Forest Guardian's Glaive", "Frost Axe", "Frost Blade", "Frost Scythe", "Hearthflame", "Hero Sword", "Holy Moonlight Sword", "Hornet's Needle", "Icewhisper", "Jade Halberd", "Katana", "Legendary Sword", "Longsword", "Magi Scythe", "Masamune", "Mjolnir", "Molten Blade", "Molten Sword", "Muramasa", "Mystical Spellblade", "Mythic Blade", "Ocean's Rage", "Partisan", "Pharaoh's Treasure", "Pheonix Grace", "Plague Longsword", "Power Fuse Hammer", "Power Fuse Sword", "Requiem of the Ninth Abyss", "Ribbon Cleaver", "Righteous Relic", "Rivers Of Blood", "Royal Chakram", "Royal Rapier", "Sabre", "Scissor Blade", "Sculk Cleaver", "Sculk Scythe", "Sculk Sword", "Sentinel's Will", "Silverine Blade", "Soul Claws", "Soul Collector", "Soul Devourer", "Soul Edge", "Soul Harvester", "Soul Stealer", "Soulrender", "Star's Edge", "Steel Sword", "Stop Sign", "Storm Bringer", "Storm's Edge", "Sunbreak", "Tengen's Blade", "Terra Blade", "Thousand Demon Daggers", "Thunder Bringer", "Thunderbrand", "True Excalibur", "Vampiric Needle", "Wakizashi", "Watcher Claymore", "Watching Warglaive", "Waxweaver", "Whisperwind", "Wickpiercer", "Wraith Scythe", "Yoru"};
    private final ModeSetting model = new ModeSetting("Model", "Katana", MODELS);

    public ItemStack getRenderStack(ItemStack stack) {
        if (!this.isEnabled() || stack.isEmpty() || !(stack.getItem() instanceof SwordItem)) {
            return stack;
        }
        ItemStack copy = stack.copy();
        copy.set(DataComponentTypes.CUSTOM_MODEL_DATA, (Object)new CustomModelDataComponent(List.of(), List.of(), List.of(this.model.getValue()), List.of()));
        return copy;
    }
}

