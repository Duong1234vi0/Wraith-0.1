/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.context.CommandContextBuilder
 *  com.mojang.brigadier.context.ParsedArgument
 *  com.mojang.brigadier.context.StringRange
 *  com.mojang.brigadier.context.SuggestionContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.suggestion.Suggestion
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  com.mojang.brigadier.tree.LiteralCommandNode
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.context.CommandContextBuilder;
import com.mojang.brigadier.context.ParsedArgument;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.context.SuggestionContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.I_1084_e;
import lightning.product.K_1289_S;
import lightning.product.O_694_j;
import lightning.product.P_3504_Q;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.Y_4083_F;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftClient;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.ClientBootstrap;
import lightning.product.Rect2i;
import lightning.product.u_530_F;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.y_4642_Y;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.event.events.TabCompleteEvent;

public class S_499_t {
    private static final Pattern n_1700_B = Pattern.compile("(\\s+)");
    private static final Z_1567_W J_1907_R = Z_1567_W.n_1700_B.n_1700_B(D_4024_W.P_4830_p);
    private static final Z_1567_W R_4764_Y = Z_1567_W.n_1700_B.n_1700_B(D_4024_W.w_1484_f);
    private static final List<Z_1567_W> G_564_y = (List)Stream.of(D_4024_W.M_588_G, D_4024_W.Q_4569_t, D_4024_W.u_2550_I, D_4024_W.h_1847_R, D_4024_W.v_4262_N).map(Z_1567_W.n_1700_B::n_1700_B).collect(ImmutableList.toImmutableList());
    private final MinecraftClient P_1922_E;
    private final k_2603_m u_1723_Y;
    private final O_694_j v_4262_N;
    private final Y_4083_F w_1484_f;
    private final boolean t_148_a;
    private final boolean s_956_w;
    private final int u_2550_I;
    private final int M_588_G;
    private final boolean P_4830_p;
    private final int h_1847_R;
    private final List<FormattedCharSequence> Q_4569_t = Lists.newArrayList();
    private int M_182_A;
    private int t_1786_h;
    private ParseResults<V_4217_p> multiplayerClientSuggestionProvider;
    private CompletableFuture<Suggestions> w_1457_N;
    private n_1700_B Y_601_j;
    private boolean Y_259_p;
    private boolean Q_2552_b;

    public S_499_t(MinecraftClient mc, k_2603_m screen, O_694_j inputField, Y_4083_F font, boolean commandsOnly, boolean hasCursor, int minAmountRendered, int maxAmountRendered, boolean isChat, int color) {
        this.P_1922_E = mc;
        this.u_1723_Y = screen;
        this.v_4262_N = inputField;
        this.w_1484_f = font;
        this.t_148_a = commandsOnly;
        this.s_956_w = hasCursor;
        this.u_2550_I = minAmountRendered;
        this.M_588_G = maxAmountRendered;
        this.P_4830_p = isChat;
        this.h_1847_R = color;
        inputField.setTextFormatter(this::n_1700_B);
    }

    public void n_1700_B(boolean autoSuggest) {
        this.Y_259_p = autoSuggest;
        if (!autoSuggest) {
            this.Y_601_j = null;
        }
    }

    public boolean n_1700_B(int keyCode, int scanCode, int modifiers) {
        if (this.Y_601_j != null && this.Y_601_j.J_1907_R(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (this.u_1723_Y.getListener() == this.v_4262_N && keyCode == 258) {
            this.J_1907_R(true);
            return true;
        }
        return false;
    }

    public boolean n_1700_B(double delta) {
        return this.Y_601_j != null && this.Y_601_j.n_1700_B(u_530_F.n_1700_B(delta, -1.0, 1.0));
    }

    public boolean n_1700_B(double mouseX, double mouseY, int mouseButton) {
        return this.Y_601_j != null && this.Y_601_j.n_1700_B((int)mouseX, (int)mouseY, mouseButton);
    }

    public void J_1907_R(boolean narrateFirstSuggestion) {
        Suggestions suggestions;
        if (this.w_1457_N != null && this.w_1457_N.isDone() && !(suggestions = this.w_1457_N.join()).isEmpty()) {
            int i = 0;
            for (Suggestion suggestion : suggestions.getList()) {
                i = Math.max(i, this.w_1484_f.J_1907_R(suggestion.getText()));
            }
            int j = u_530_F.n_1700_B(this.v_4262_N.func_195611_j(suggestions.getRange().getStart()), 0, this.v_4262_N.func_195611_j(0) + this.v_4262_N.getAdjustedWidth() - i);
            int k = this.P_4830_p ? this.u_1723_Y.height - 12 : 72;
            this.Y_601_j = new n_1700_B(j, k, i, this.n_1700_B(suggestions), narrateFirstSuggestion);
        }
    }

    private List<Suggestion> n_1700_B(Suggestions suggestions) {
        String s = this.v_4262_N.getText().substring(0, this.v_4262_N.getCursorPosition());
        int i = S_499_t.n_1700_B(s);
        String s1 = s.substring(i).toLowerCase(Locale.ROOT);
        ArrayList list = Lists.newArrayList();
        ArrayList list1 = Lists.newArrayList();
        for (Suggestion suggestion : suggestions.getList()) {
            if (!suggestion.getText().startsWith(s1) && !suggestion.getText().startsWith("minecraft:" + s1)) {
                list1.add(suggestion);
                continue;
            }
            list.add(suggestion);
        }
        list.addAll(list1);
        return list;
    }

    public void n_1700_B() {
        boolean hasSlash;
        String s = this.v_4262_N.getText();
        if (this.multiplayerClientSuggestionProvider != null && !this.multiplayerClientSuggestionProvider.getReader().getString().equals(s)) {
            this.multiplayerClientSuggestionProvider = null;
        }
        if (!this.Q_2552_b) {
            this.v_4262_N.setSuggestion(null);
            this.Y_601_j = null;
        }
        this.Q_4569_t.clear();
        StringReader reader = new StringReader(s);
        boolean bl = hasSlash = reader.canRead() && reader.peek() == '/';
        if (!y_4642_Y.R_4764_Y() && reader.canRead(ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B().length()) && reader.getString().startsWith(ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B(), reader.getCursor())) {
            int cursor;
            reader.setCursor(reader.getCursor() + 1);
            if (this.multiplayerClientSuggestionProvider == null) {
                this.multiplayerClientSuggestionProvider = ClientBootstrap.Y_601_j().Q_4569_t().J_1907_R().parse(reader, (Object)ClientBootstrap.Y_601_j().Q_4569_t().G_564_y());
            }
            if (!((cursor = this.v_4262_N.getCursorPosition()) < 1 || this.Y_601_j != null && this.Q_2552_b)) {
                this.w_1457_N = ClientBootstrap.Y_601_j().Q_4569_t().J_1907_R().getCompletionSuggestions(this.multiplayerClientSuggestionProvider, cursor);
                this.w_1457_N.thenRun(() -> {
                    if (this.w_1457_N.isDone()) {
                        this.R_4764_Y();
                    }
                });
            }
        }
        if (hasSlash) {
            reader.skip();
        }
        boolean wantCommands = this.t_148_a || hasSlash;
        int cursor = this.v_4262_N.getCursorPosition();
        if (wantCommands) {
            int parseCursor;
            CommandDispatcher<V_4217_p> dispatcher = this.P_1922_E.Y_259_p.n_1700_B.t_148_a();
            if (this.multiplayerClientSuggestionProvider == null) {
                this.multiplayerClientSuggestionProvider = dispatcher.parse(reader, (Object)this.P_1922_E.Y_259_p.n_1700_B.n_1700_B());
            }
            int n = parseCursor = this.s_956_w ? reader.getCursor() : 1;
            if (!(cursor < parseCursor || this.Y_601_j != null && this.Q_2552_b)) {
                this.w_1457_N = dispatcher.getCompletionSuggestions(this.multiplayerClientSuggestionProvider, cursor);
                this.w_1457_N.thenRun(() -> {
                    if (this.w_1457_N.isDone()) {
                        this.R_4764_Y();
                    }
                });
            }
        } else {
            String before = s.substring(0, cursor);
            int ws = S_499_t.n_1700_B(before);
            Collection<String> names = this.P_1922_E.Y_259_p.n_1700_B.n_1700_B().n_1700_B();
            TabCompleteEvent evt = new TabCompleteEvent(before);
            BaritoneAPI.getProvider().getPrimaryBaritone().getGameEventHandler().onPreTabComplete(evt);
            if (evt.completions != null) {
                List list = Stream.of(evt.completions).map(c -> new Suggestion(StringRange.between((int)ws, (int)before.length()), c)).collect(Collectors.toList());
                this.w_1457_N = CompletableFuture.completedFuture(new Suggestions(StringRange.between((int)ws, (int)before.length()), list));
            } else {
                this.w_1457_N = V_4217_p.J_1907_R(names, new SuggestionsBuilder(before, ws));
            }
        }
    }

    private static int n_1700_B(String text) {
        if (Strings.isNullOrEmpty((String)text)) {
            return 0;
        }
        int i = 0;
        Matcher matcher = n_1700_B.matcher(text);
        while (matcher.find()) {
            i = matcher.end();
        }
        return i;
    }

    private static FormattedCharSequence n_1700_B(CommandSyntaxException exception) {
        x_282_a itextcomponent = ComponentUtils.n_1700_B(exception.getRawMessage());
        String s = exception.getContext();
        return s == null ? itextcomponent.u_1723_Y() : new F_2904_S("command.context.parse_error", itextcomponent, exception.getCursor(), s).u_1723_Y();
    }

    private void R_4764_Y() {
        if (this.multiplayerClientSuggestionProvider == null) {
            this.J_1907_R(false);
            return;
        }
        if (this.v_4262_N.getCursorPosition() == this.v_4262_N.getText().length()) {
            if (this.w_1457_N.join().isEmpty() && !this.multiplayerClientSuggestionProvider.getExceptions().isEmpty()) {
                int i = 0;
                for (Map.Entry entry : this.multiplayerClientSuggestionProvider.getExceptions().entrySet()) {
                    CommandSyntaxException ex = (CommandSyntaxException)((Object)entry.getValue());
                    if (ex.getType() == CommandSyntaxException.BUILT_IN_EXCEPTIONS.literalIncorrect()) {
                        ++i;
                        continue;
                    }
                    this.Q_4569_t.add(S_499_t.n_1700_B(ex));
                }
                if (i > 0) {
                    this.Q_4569_t.add(S_499_t.n_1700_B(CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownCommand().create()));
                }
            } else if (this.multiplayerClientSuggestionProvider.getReader().canRead()) {
                this.Q_4569_t.add(S_499_t.n_1700_B(Q_2241_p.n_1700_B(this.multiplayerClientSuggestionProvider)));
            }
        }
        this.M_182_A = 0;
        this.t_1786_h = this.u_1723_Y.width;
        if (this.Q_4569_t.isEmpty()) {
            this.n_1700_B(D_4024_W.w_1484_f);
        }
        this.Y_601_j = null;
        if (this.Y_259_p && this.P_1922_E.P_4830_p.v_4276_D) {
            this.J_1907_R(false);
        }
    }

    private void n_1700_B(D_4024_W formatting) {
        CommandContextBuilder commandcontextbuilder = this.multiplayerClientSuggestionProvider.getContext();
        SuggestionContext suggestioncontext = commandcontextbuilder.findSuggestionContext(this.v_4262_N.getCursorPosition());
        Map map = this.P_1922_E.Y_259_p.n_1700_B.t_148_a().getSmartUsage(suggestioncontext.parent, (Object)this.P_1922_E.Y_259_p.n_1700_B.n_1700_B());
        ArrayList list = Lists.newArrayList();
        int i = 0;
        Z_1567_W style = Z_1567_W.n_1700_B.n_1700_B(formatting);
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getKey() instanceof LiteralCommandNode) continue;
            list.add(FormattedCharSequence.n_1700_B((String)entry.getValue(), style));
            i = Math.max(i, this.w_1484_f.J_1907_R((String)entry.getValue()));
        }
        if (!list.isEmpty()) {
            this.Q_4569_t.addAll(list);
            this.M_182_A = u_530_F.n_1700_B(this.v_4262_N.func_195611_j(suggestioncontext.startPos), 0, this.v_4262_N.func_195611_j(0) + this.v_4262_N.getAdjustedWidth() - i);
            this.t_1786_h = i;
        }
    }

    private FormattedCharSequence n_1700_B(String command, int maxLength) {
        return this.multiplayerClientSuggestionProvider != null ? S_499_t.n_1700_B(this.multiplayerClientSuggestionProvider, command, maxLength) : FormattedCharSequence.n_1700_B(command, Z_1567_W.n_1700_B);
    }

    @Nullable
    private static String n_1700_B(String inputText, String suggestionText) {
        return suggestionText.startsWith(inputText) ? suggestionText.substring(inputText.length()) : null;
    }

    private static FormattedCharSequence n_1700_B(ParseResults<V_4217_p> provider, String command, int maxLength) {
        int i1;
        ArrayList list = Lists.newArrayList();
        int i = 0;
        int j = -1;
        CommandContextBuilder commandcontextbuilder = provider.getContext().getLastChild();
        for (ParsedArgument parsedargument : commandcontextbuilder.getArguments().values()) {
            int k;
            if (++j >= G_564_y.size()) {
                j = 0;
            }
            if ((k = Math.max(parsedargument.getRange().getStart() - maxLength, 0)) >= command.length()) break;
            int l = Math.min(parsedargument.getRange().getEnd() - maxLength, command.length());
            if (l <= 0) continue;
            list.add(FormattedCharSequence.n_1700_B(command.substring(i, k), R_4764_Y));
            list.add(FormattedCharSequence.n_1700_B(command.substring(k, l), G_564_y.get(j)));
            i = l;
        }
        if (provider.getReader().canRead() && (i1 = Math.max(provider.getReader().getCursor() - maxLength, 0)) < command.length()) {
            int j1 = Math.min(i1 + provider.getReader().getRemainingLength(), command.length());
            list.add(FormattedCharSequence.n_1700_B(command.substring(i, i1), R_4764_Y));
            list.add(FormattedCharSequence.n_1700_B(command.substring(i1, j1), J_1907_R));
            i = j1;
        }
        list.add(FormattedCharSequence.n_1700_B(command.substring(i), R_4764_Y));
        return FormattedCharSequence.n_1700_B(list);
    }

    public void n_1700_B(g_221_o matrixStack, int mouseX, int mouseY) {
        if (this.Y_601_j != null) {
            this.Y_601_j.n_1700_B(matrixStack, mouseX, mouseY);
        } else {
            int i = 0;
            for (FormattedCharSequence ireorderingprocessor : this.Q_4569_t) {
                int j = this.P_4830_p ? this.u_1723_Y.height - 14 - 13 - 12 * i : 72 + 12 * i;
                C_2701_A.fill(matrixStack, this.M_182_A - 1, j, this.M_182_A + this.t_1786_h + 1, j + 12, this.h_1847_R);
                this.w_1484_f.n_1700_B(matrixStack, ireorderingprocessor, (float)this.M_182_A, (float)(j + 2), -1);
                ++i;
            }
        }
    }

    public String J_1907_R() {
        return this.Y_601_j != null ? "\n" + this.Y_601_j.R_4764_Y() : "";
    }

    public class n_1700_B {
        private final Rect2i J_1907_R;
        private final String R_4764_Y;
        private final List<Suggestion> G_564_y;
        private int P_1922_E;
        private int u_1723_Y;
        private P_3504_Q v_4262_N = P_3504_Q.n_1700_B;
        private boolean w_1484_f;
        private int t_148_a;

        private n_1700_B(int x, int y, int width, List<Suggestion> suggestions, boolean narrateFirstSuggestion) {
            int i = x - 1;
            int j = S_499_t.this.P_4830_p ? y - 3 - Math.min(suggestions.size(), S_499_t.this.M_588_G) * 12 : y;
            this.J_1907_R = new Rect2i(i, j, width + 1, Math.min(suggestions.size(), S_499_t.this.M_588_G) * 12);
            this.R_4764_Y = S_499_t.this.v_4262_N.getText();
            this.t_148_a = narrateFirstSuggestion ? -1 : 0;
            this.G_564_y = suggestions;
            this.J_1907_R(0);
        }

        public void n_1700_B(g_221_o matrixStack, int mouseX, int mouseY) {
            Message message;
            boolean flag3;
            int i = Math.min(this.G_564_y.size(), S_499_t.this.M_588_G);
            int j = -5592406;
            boolean flag = this.P_1922_E > 0;
            boolean flag1 = this.G_564_y.size() > this.P_1922_E + i;
            boolean flag2 = flag || flag1;
            boolean bl = flag3 = this.v_4262_N.t_148_a != (float)mouseX || this.v_4262_N.s_956_w != (float)mouseY;
            if (flag3) {
                this.v_4262_N = new P_3504_Q(mouseX, mouseY);
            }
            if (flag2) {
                C_2701_A.fill(matrixStack, this.J_1907_R.n_1700_B(), this.J_1907_R.J_1907_R() - 1, this.J_1907_R.n_1700_B() + this.J_1907_R.R_4764_Y(), this.J_1907_R.J_1907_R(), S_499_t.this.h_1847_R);
                C_2701_A.fill(matrixStack, this.J_1907_R.n_1700_B(), this.J_1907_R.J_1907_R() + this.J_1907_R.G_564_y(), this.J_1907_R.n_1700_B() + this.J_1907_R.R_4764_Y(), this.J_1907_R.J_1907_R() + this.J_1907_R.G_564_y() + 1, S_499_t.this.h_1847_R);
                if (flag) {
                    for (int k = 0; k < this.J_1907_R.R_4764_Y(); ++k) {
                        if (k % 2 != 0) continue;
                        C_2701_A.fill(matrixStack, this.J_1907_R.n_1700_B() + k, this.J_1907_R.J_1907_R() - 1, this.J_1907_R.n_1700_B() + k + 1, this.J_1907_R.J_1907_R(), -1);
                    }
                }
                if (flag1) {
                    for (int i1 = 0; i1 < this.J_1907_R.R_4764_Y(); ++i1) {
                        if (i1 % 2 != 0) continue;
                        C_2701_A.fill(matrixStack, this.J_1907_R.n_1700_B() + i1, this.J_1907_R.J_1907_R() + this.J_1907_R.G_564_y(), this.J_1907_R.n_1700_B() + i1 + 1, this.J_1907_R.J_1907_R() + this.J_1907_R.G_564_y() + 1, -1);
                    }
                }
            }
            boolean flag4 = false;
            for (int l = 0; l < i; ++l) {
                Suggestion suggestion = this.G_564_y.get(l + this.P_1922_E);
                C_2701_A.fill(matrixStack, this.J_1907_R.n_1700_B(), this.J_1907_R.J_1907_R() + 12 * l, this.J_1907_R.n_1700_B() + this.J_1907_R.R_4764_Y(), this.J_1907_R.J_1907_R() + 12 * l + 12, S_499_t.this.h_1847_R);
                if (mouseX > this.J_1907_R.n_1700_B() && mouseX < this.J_1907_R.n_1700_B() + this.J_1907_R.R_4764_Y() && mouseY > this.J_1907_R.J_1907_R() + 12 * l && mouseY < this.J_1907_R.J_1907_R() + 12 * l + 12) {
                    if (flag3) {
                        this.J_1907_R(l + this.P_1922_E);
                    }
                    flag4 = true;
                }
                S_499_t.this.w_1484_f.n_1700_B(matrixStack, suggestion.getText(), (float)(this.J_1907_R.n_1700_B() + 1), (float)(this.J_1907_R.J_1907_R() + 2 + 12 * l), l + this.P_1922_E == this.u_1723_Y ? -256 : -5592406);
            }
            if (flag4 && (message = this.G_564_y.get(this.u_1723_Y).getTooltip()) != null) {
                S_499_t.this.u_1723_Y.renderTooltip(matrixStack, ComponentUtils.n_1700_B(message), mouseX, mouseY);
            }
        }

        public boolean n_1700_B(int mouseX, int mouseY, int mouseButton) {
            if (!this.J_1907_R.n_1700_B(mouseX, mouseY)) {
                return false;
            }
            int i = (mouseY - this.J_1907_R.J_1907_R()) / 12 + this.P_1922_E;
            if (i >= 0 && i < this.G_564_y.size()) {
                this.J_1907_R(i);
                this.n_1700_B();
            }
            return true;
        }

        public boolean n_1700_B(double delta) {
            int j;
            int i = (int)(S_499_t.this.P_1922_E.h_1847_R.G_564_y() * (double)S_499_t.this.P_1922_E.RealmsServerPing().Q_4569_t() / (double)S_499_t.this.P_1922_E.RealmsServerPing().P_4830_p());
            if (this.J_1907_R.n_1700_B(i, j = (int)(S_499_t.this.P_1922_E.h_1847_R.P_1922_E() * (double)S_499_t.this.P_1922_E.RealmsServerPing().M_182_A() / (double)S_499_t.this.P_1922_E.RealmsServerPing().h_1847_R()))) {
                this.P_1922_E = u_530_F.n_1700_B((int)((double)this.P_1922_E - delta), 0, Math.max(this.G_564_y.size() - S_499_t.this.M_588_G, 0));
                return true;
            }
            return false;
        }

        public boolean J_1907_R(int keyCode, int scanCode, int modifiers) {
            if (keyCode == 265) {
                this.n_1700_B(-1);
                this.w_1484_f = false;
                return true;
            }
            if (keyCode == 264) {
                this.n_1700_B(1);
                this.w_1484_f = false;
                return true;
            }
            if (keyCode == 258) {
                if (this.w_1484_f) {
                    this.n_1700_B(k_2603_m.hasShiftDown() ? -1 : 1);
                }
                this.n_1700_B();
                return true;
            }
            if (keyCode == 256) {
                this.J_1907_R();
                return true;
            }
            return false;
        }

        public void n_1700_B(int change) {
            this.J_1907_R(this.u_1723_Y + change);
            int i = this.P_1922_E;
            int j = this.P_1922_E + S_499_t.this.M_588_G - 1;
            if (this.u_1723_Y < i) {
                this.P_1922_E = u_530_F.n_1700_B(this.u_1723_Y, 0, Math.max(this.G_564_y.size() - S_499_t.this.M_588_G, 0));
            } else if (this.u_1723_Y > j) {
                this.P_1922_E = u_530_F.n_1700_B(this.u_1723_Y + S_499_t.this.u_2550_I - S_499_t.this.M_588_G, 0, Math.max(this.G_564_y.size() - S_499_t.this.M_588_G, 0));
            }
        }

        public void J_1907_R(int index) {
            this.u_1723_Y = index;
            if (this.u_1723_Y < 0) {
                this.u_1723_Y += this.G_564_y.size();
            }
            if (this.u_1723_Y >= this.G_564_y.size()) {
                this.u_1723_Y -= this.G_564_y.size();
            }
            Suggestion suggestion = this.G_564_y.get(this.u_1723_Y);
            S_499_t.this.v_4262_N.setSuggestion(S_499_t.n_1700_B(S_499_t.this.v_4262_N.getText(), suggestion.apply(this.R_4764_Y)));
            if (I_1084_e.J_1907_R.n_1700_B() && this.t_148_a != this.u_1723_Y) {
                I_1084_e.J_1907_R.n_1700_B(this.R_4764_Y());
            }
        }

        public void n_1700_B() {
            Suggestion suggestion = this.G_564_y.get(this.u_1723_Y);
            S_499_t.this.Q_2552_b = true;
            S_499_t.this.v_4262_N.setText(suggestion.apply(this.R_4764_Y));
            int i = suggestion.getRange().getStart() + suggestion.getText().length();
            S_499_t.this.v_4262_N.clampCursorPosition(i);
            S_499_t.this.v_4262_N.setSelectionPos(i);
            this.J_1907_R(this.u_1723_Y);
            S_499_t.this.Q_2552_b = false;
            this.w_1484_f = true;
        }

        private String R_4764_Y() {
            this.t_148_a = this.u_1723_Y;
            Suggestion suggestion = this.G_564_y.get(this.u_1723_Y);
            Message message = suggestion.getTooltip();
            return message != null ? K_1289_S.n_1700_B("narration.suggestion.tooltip", this.u_1723_Y + 1, this.G_564_y.size(), suggestion.getText(), message.getString()) : K_1289_S.n_1700_B("narration.suggestion", this.u_1723_Y + 1, this.G_564_y.size(), suggestion.getText());
        }

        public void J_1907_R() {
            S_499_t.this.Y_601_j = null;
        }
    }
}



