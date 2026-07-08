/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.common.repository.macro;

import code.alphadlc.common.repository.macro.Macro;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.features.impl.misc.Panic;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.managers.event.EventManager;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.interfaces.QuickLogger;
import java.util.ArrayList;
import java.util.List;

public class MacroRepository
implements QuickImports,
QuickLogger {
    public List<Macro> macroList = new ArrayList<Macro>();

    public MacroRepository(EventManager eventManager) {
        EventManager.register(this);
    }

    public void addMacro(String name, String message, int key) {
        this.macroList.add(new Macro(name, message, key));
    }

    public boolean hasMacro(String text) {
        return this.macroList.stream().anyMatch(macro -> macro.name().equalsIgnoreCase(text));
    }

    public void deleteMacro(String text) {
        this.macroList.removeIf(macro -> macro.name().equalsIgnoreCase(text));
    }

    public void clearList() {
        this.macroList.clear();
    }

    @EventHandler
    public void onKey(KeyEvent e) {
        if (Panic.isPanicMode()) {
            return;
        }
        if (MacroRepository.mc.field_1724 != null && e.action() == 0 && MacroRepository.mc.field_1755 == null) {
            this.macroList.stream().filter(macro -> macro.key() == e.key()).findFirst().ifPresent(macro -> MacroRepository.mc.field_1724.field_3944.method_45729(macro.message()));
        }
    }
}
