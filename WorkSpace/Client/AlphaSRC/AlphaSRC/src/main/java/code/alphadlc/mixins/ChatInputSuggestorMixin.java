/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.context.StringRange
 *  com.mojang.brigadier.suggestion.Suggestion
 *  com.mojang.brigadier.suggestion.Suggestions
 *  net.minecraft.class_342
 *  net.minecraft.class_4717
 *  net.minecraft.class_4717$class_464
 *  net.minecraft.class_5481
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.events.chat.TabCompleteEvent;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.class_342;
import net.minecraft.class_4717;
import net.minecraft.class_5481;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_4717.class})
public class ChatInputSuggestorMixin {
    @Shadow
    @Final
    class_342 field_21599;
    @Shadow
    @Final
    private List<class_5481> field_21607;
    @Shadow
    private ParseResults<?> field_21610;
    @Shadow
    private CompletableFuture<Suggestions> field_21611;
    @Shadow
    private class_4717.class_464 field_21612;
    @Shadow
    boolean field_21614;

    @Inject(method={"refresh"}, at={@At(value="HEAD")}, cancellable=true)
    private void preUpdateSuggestion(CallbackInfo ci) {
        String prefix = this.field_21599.method_1882().substring(0, Math.min(this.field_21599.method_1882().length(), this.field_21599.method_1881()));
        TabCompleteEvent event = SafeEvents.dispatch(() -> new TabCompleteEvent(prefix));
        if (event != null && event.isCancelled()) {
            ci.cancel();
            return;
        }
        if (event != null && event.completions != null) {
            ci.cancel();
            this.field_21610 = null;
            if (this.field_21614) {
                return;
            }
            this.field_21599.method_1887(null);
            this.field_21612 = null;
            this.field_21607.clear();
            if (event.completions.length == 0) {
                this.field_21611 = Suggestions.empty();
            } else {
                StringRange range = StringRange.between((int)(prefix.lastIndexOf(" ") + 1), (int)prefix.length());
                List suggestionList = Stream.of(event.completions).map(s -> new Suggestion(range, s)).collect(Collectors.toList());
                Suggestions suggestions = new Suggestions(range, suggestionList);
                this.field_21611 = new CompletableFuture();
                this.field_21611.complete(suggestions);
            }
            ((class_4717)this).method_23920(true);
        }
    }
}
