package ua.babka.command.impl;

import java.util.List;
import java.util.stream.Stream;
import net.minecraft.util.Formatting;
import ua.babka.Babka;
import ua.babka.combat.NeuroAuraSystem;
import ua.babka.command.CommandException;
import ua.babka.command.argument.IArgConsumer;
import ua.babka.command.impl.Command;
import ua.babka.module.impl.combat.AttackAura;
import ua.babka.util.ChatUtil;

public class NeuroCommand
extends Command {
    public NeuroCommand() {
        super("neuro");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String action;
        AttackAura aura = Babka.getInstance().getModuleStorage().get(AttackAura.class);
        if (aura == null) {
            ChatUtil.send((Object)(String.valueOf(Formatting.RED) + "[Neuro] AttackAura не найден!"));
            return;
        }
        NeuroAuraSystem neuro = aura.getNeuroSystem();
        if (!args.hasAny()) {
            ChatUtil.send((Object)(String.valueOf(Formatting.AQUA) + "[Neuro] §fИспользование:"));
            ChatUtil.send((Object)" §f> neuro record §7- начать запись");
            ChatUtil.send((Object)" §f> neuro stop <name> §7- остановить и сохранить");
            ChatUtil.send((Object)" §f> neuro play <name> §7- загрузить паттерн");
            ChatUtil.send((Object)" §f> neuro clear §7- очистить паттерны");
            ChatUtil.send((Object)" §f> neuro status §7- статус системы");
            return;
        }
        switch (action = args.getString().toLowerCase()) {
            case "record": {
                neuro.startRecording();
                ChatUtil.send((Object)(String.valueOf(Formatting.GREEN) + "[Neuro] §fЗапись начата"));
                break;
            }
            case "stop": {
                if (neuro.getPatternCount() == 0) {
                    ChatUtil.send((Object)(String.valueOf(Formatting.RED) + "[Neuro] §fНет записанных паттернов"));
                    return;
                }
                Object name = args.hasAny() ? args.getString() : "neuro_" + System.currentTimeMillis();
                neuro.savePatterns((String)name);
                neuro.stopRecording();
                ChatUtil.send((Object)(String.valueOf(Formatting.GREEN) + "[Neuro] §fЗапись сохранена как §e" + (String)name));
                break;
            }
            case "play": {
                if (!args.hasAny()) {
                    ChatUtil.send((Object)(String.valueOf(Formatting.RED) + "[Neuro] §fУкажи имя паттерна!"));
                    return;
                }
                String name = args.getString();
                if (!aura.isEnabled()) {
                    ChatUtil.send((Object)(String.valueOf(Formatting.RED) + "[Neuro] §fВключи AttackAura для использования паттернов!"));
                    return;
                }
                neuro.loadPatterns(name);
                neuro.setRecording(false);
                neuro.setUsingNeuro(true);
                ChatUtil.send((Object)(String.valueOf(Formatting.GREEN) + "[Neuro] §fЗагружен паттерн §e" + name + " §f(§e" + neuro.getPatternCount() + "§f паттернов)"));
                ChatUtil.send((Object)(String.valueOf(Formatting.GRAY) + "Выбери режим ротации 'Neuro' в настройках AttackAura"));
                break;
            }
            case "clear": {
                neuro.clearPatterns();
                ChatUtil.send((Object)(String.valueOf(Formatting.YELLOW) + "[Neuro] §fВсе паттерны очищены"));
                break;
            }
            case "status": {
                String status = neuro.getStatusString();
                ChatUtil.send((Object)(String.valueOf(Formatting.AQUA) + "[Neuro] §f" + status.replace("§8[§bNeuro§8] §f", "")));
                if (neuro.getPatternCount() <= 0) break;
                ChatUtil.send((Object)(String.valueOf(Formatting.GRAY) + "Новых в сессии: §f" + neuro.getRecordedThisSession()));
                break;
            }
            default: {
                ChatUtil.send((Object)(String.valueOf(Formatting.RED) + "[Neuro] §fНеизвестное действие: " + action));
            }
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (!args.hasAny()) {
            return Stream.of("record", "stop", "play", "clear", "status");
        }
        if (args.getConsumed().size() == 1) {
            String partial = args.getString().toLowerCase();
            return Stream.of("record", "stop", "play", "clear", "status").filter(s2 -> s2.startsWith(partial));
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Управление нейро-системой ауры";
    }

    @Override
    public List<String> getLongDesc() {
        return List.of("Нейро-система запоминает твои повороты и воспроизводит их.", "", "> neuro record — начать запись", "> neuro stop <name> — остановить и сохранить", "> neuro play <name> — загрузить паттерн", "> neuro clear — очистить", "> neuro status — статус");
    }
}

