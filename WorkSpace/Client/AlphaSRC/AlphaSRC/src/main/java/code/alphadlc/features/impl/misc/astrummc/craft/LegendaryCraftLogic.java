/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.misc.astrummc.craft;

import code.alphadlc.features.impl.misc.astrummc.craft.CraftIngredient;
import code.alphadlc.features.impl.misc.astrummc.craft.LegendaryCraftRecipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class LegendaryCraftLogic {
    private LegendaryCraftLogic() {
    }

    public static RecipeStatus evaluate(LegendaryCraftRecipe recipe, Map<String, Integer> inventory) {
        ArrayList<IngredientStatus> statuses = new ArrayList<IngredientStatus>();
        int satisfiedCount = 0;
        for (CraftIngredient ingredient : recipe.ingredients()) {
            boolean satisfied;
            int available = inventory.getOrDefault(ingredient.id(), 0);
            boolean bl = satisfied = available >= ingredient.required();
            if (satisfied) {
                ++satisfiedCount;
            }
            statuses.add(new IngredientStatus(ingredient, available, satisfied));
        }
        int totalCount = recipe.ingredients().size();
        int halfThreshold = (int)Math.ceil((double)totalCount * 0.5);
        boolean allSatisfied = satisfiedCount == totalCount;
        boolean halfOrMore = satisfiedCount >= halfThreshold;
        return new RecipeStatus(recipe, statuses, satisfiedCount, totalCount, allSatisfied, halfOrMore);
    }

    public static String formatHalfMessage(RecipeStatus status) {
        String available = status.ingredients().stream().filter(IngredientStatus::satisfied).map(LegendaryCraftLogic::formatAvailable).collect(Collectors.joining(", "));
        String missing = status.ingredients().stream().filter(ingredient -> !ingredient.satisfied()).map(LegendaryCraftLogic::formatMissing).collect(Collectors.joining(", "));
        return "\u0423 \u0432\u0430\u0441 \u0435\u0441\u0442\u044c \u0440\u0435\u0441\u0443\u0440\u0441\u044b " + available + " \u0434\u043b\u044f \u043a\u0440\u0430\u0444\u0442\u0430 " + status.recipe().name() + ". \u041e\u0441\u0442\u0430\u043b\u043e\u0441\u044c \u0441\u043e\u0431\u0440\u0430\u0442\u044c: " + missing;
    }

    public static String formatCompleteMessage(RecipeStatus status) {
        String resources = status.recipe().ingredients().stream().map(ingredient -> LegendaryCraftLogic.formatRequired(ingredient.required(), ingredient.label())).collect(Collectors.joining(", "));
        return "\u0423 \u0432\u0430\u0441 \u0435\u0441\u0442\u044c \u0440\u0435\u0441\u0443\u0440\u0441\u044b " + resources + ", \u0432\u044b \u0433\u043e\u0442\u043e\u0432\u044b \u0441\u043a\u0440\u0430\u0444\u0442\u0438\u0442\u044c " + status.recipe().name();
    }

    private static String formatAvailable(IngredientStatus status) {
        return LegendaryCraftLogic.formatRequired(status.available(), status.ingredient().label());
    }

    private static String formatMissing(IngredientStatus status) {
        int remaining = status.ingredient().required() - status.available();
        return LegendaryCraftLogic.formatRequired(remaining, status.ingredient().label());
    }

    private static String formatRequired(int count, String label) {
        return count + " " + label;
    }

    public record IngredientStatus(CraftIngredient ingredient, int available, boolean satisfied) {
    }

    public record RecipeStatus(LegendaryCraftRecipe recipe, List<IngredientStatus> ingredients, int satisfiedCount, int totalCount, boolean allSatisfied, boolean halfOrMore) {
    }
}
