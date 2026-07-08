/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.misc.astrummc.craft;

import code.alphadlc.features.impl.misc.astrummc.craft.LegendaryCraftLogic;
import code.alphadlc.features.impl.misc.astrummc.craft.LegendaryCraftRecipe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LegendaryCraftNotifier {
    private final Map<String, NotificationState> states = new HashMap<String, NotificationState>();

    public List<String> process(LegendaryCraftRecipe recipe, LegendaryCraftLogic.RecipeStatus status) {
        NotificationState state = this.states.computeIfAbsent(recipe.name(), ignored -> new NotificationState());
        ArrayList<String> messages = new ArrayList<String>();
        if (status.allSatisfied()) {
            if (!state.notifiedComplete) {
                state.notifiedComplete = true;
                messages.add(LegendaryCraftLogic.formatCompleteMessage(status));
            }
            return messages;
        }
        state.notifiedComplete = false;
        if (status.halfOrMore()) {
            if (!state.notifiedHalf) {
                state.notifiedHalf = true;
                messages.add(LegendaryCraftLogic.formatHalfMessage(status));
            }
        } else {
            state.notifiedHalf = false;
        }
        return messages;
    }

    public void reset() {
        this.states.clear();
    }

    private static final class NotificationState {
        private boolean notifiedHalf;
        private boolean notifiedComplete;

        private NotificationState() {
        }
    }
}
