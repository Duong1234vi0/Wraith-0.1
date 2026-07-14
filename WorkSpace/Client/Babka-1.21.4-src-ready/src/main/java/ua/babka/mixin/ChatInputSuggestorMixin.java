package ua.babka.mixin;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.command.CommandSource;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.event.TabCompleteEvent;

@Mixin(value={ChatInputSuggestor.class})
public class ChatInputSuggestorMixin {
    @Shadow
    @Final
    TextFieldWidget textField;
    @Shadow
    @Final
    private List<OrderedText> messages;
    @Shadow
    private ParseResults<CommandSource> parse;
    @Shadow
    private CompletableFuture<Suggestions> pendingSuggestions;
    @Shadow
    private ChatInputSuggestor.SuggestionWindow window;
    @Shadow
    boolean completingSuggestions;

    @Inject(method={"refresh"}, at={@At(value="HEAD")}, cancellable=true)
    private void preUpdateSuggestion(CallbackInfo ci) {
        String prefix = this.textField.getText().substring(0, Math.min(this.textField.getText().length(), this.textField.getCursor()));
        TabCompleteEvent event = new TabCompleteEvent(prefix);
        event.post();
        if (event.isCancelled()) {
            ci.cancel();
            return;
        }
        if (event.completions != null) {
            ci.cancel();
            this.parse = null;
            if (this.completingSuggestions) {
                return;
            }
            this.textField.setSuggestion(null);
            this.window = null;
            this.messages.clear();
            if (event.completions.length == 0) {
                this.pendingSuggestions = Suggestions.empty();
            } else {
                StringRange range = StringRange.between((int)(prefix.lastIndexOf(" ") + 1), (int)prefix.length());
                List suggestionList = Stream.of(event.completions).map(s2 -> new Suggestion(range, s2)).collect(Collectors.toList());
                Suggestions suggestions = new Suggestions(range, suggestionList);
                this.pendingSuggestions = new CompletableFuture();
                this.pendingSuggestions.complete(suggestions);
            }
            ((ChatInputSuggestor)this).show(true);
        }
    }
}

