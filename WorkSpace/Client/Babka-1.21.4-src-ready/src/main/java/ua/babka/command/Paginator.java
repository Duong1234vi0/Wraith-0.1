package ua.babka.command;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import ua.babka.command.CommandException;
import ua.babka.command.CommandInvalidTypeException;
import ua.babka.command.argument.IArgConsumer;
import ua.babka.util.QuickLogger;

public class Paginator<E>
implements QuickLogger {
    public final List<E> entries;
    public int pageSize = 8;
    public int page = 1;

    public Paginator(List<E> entries) {
        this.entries = entries;
    }

    @SafeVarargs
    public Paginator(E ... entries) {
        this.entries = Arrays.asList(entries);
    }

    public Paginator<E> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getMaxPage() {
        return (this.entries.size() - 1) / this.pageSize + 1;
    }

    public boolean validPage(int page) {
        return page > 0 && page <= this.getMaxPage();
    }

    public void skipPages(int pages) {
        this.page += pages;
    }

    public void display(Function<E, Text> transform, String commandPrefix) {
        int offset;
        for (int i2 = offset = (this.page - 1) * this.pageSize; i2 < offset + this.pageSize; ++i2) {
            if (i2 < this.entries.size()) {
                this.logDirect(transform.apply(this.entries.get(i2)));
                continue;
            }
            this.logDirect("--", Formatting.DARK_GRAY);
        }
        boolean hasPrevPage = commandPrefix != null && this.validPage(this.page - 1);
        boolean hasNextPage = commandPrefix != null && this.validPage(this.page + 1);
        MutableText prevPageComponent = Text.literal((String)"<<");
        if (hasPrevPage) {
            prevPageComponent.setStyle(prevPageComponent.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, commandPrefix.contains("help") ? commandPrefix + " " + (this.page - 1) : commandPrefix + " list " + (this.page - 1))).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)Text.literal((String)"Click to view previous page"))));
        } else {
            prevPageComponent.setStyle(prevPageComponent.getStyle().withColor(Formatting.DARK_GRAY));
        }
        MutableText nextPageComponent = Text.literal((String)">>");
        if (hasNextPage) {
            nextPageComponent.setStyle(nextPageComponent.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, commandPrefix.contains("help") ? commandPrefix + " " + (this.page + 1) : commandPrefix + " list " + (this.page + 1))).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)Text.literal((String)"Click to view next page"))));
        } else {
            nextPageComponent.setStyle(nextPageComponent.getStyle().withColor(Formatting.DARK_GRAY));
        }
        MutableText pagerComponent = Text.literal((String)"");
        pagerComponent.setStyle(pagerComponent.getStyle().withColor(Formatting.GRAY));
        pagerComponent.append((Text)prevPageComponent);
        pagerComponent.append(" | ");
        pagerComponent.append((Text)nextPageComponent);
        pagerComponent.append(String.format(" %d/%d", this.page, this.getMaxPage()));
        this.logDirect(new Text[]{pagerComponent});
    }

    public void display(Function<E, Text> transform) {
        this.display(transform, null);
    }

    public static <T> void paginate(IArgConsumer consumer, Paginator<T> pagi, Runnable pre, Function<T, Text> transform, String commandPrefix) throws CommandException {
        int page = 1;
        consumer.requireMax(1);
        if (consumer.hasAny() && !pagi.validPage(page = consumer.getAs(Integer.class).intValue())) {
            throw new CommandInvalidTypeException(consumer.consumed(), "Вы указали неверную страницу, пределы: 1-" + pagi.getMaxPage());
        }
        pagi.skipPages(page - pagi.page);
        if (pre != null) {
            pre.run();
        }
        pagi.display(transform, commandPrefix);
    }

    public static <T> void paginate(IArgConsumer consumer, List<T> elems, Runnable pre, Function<T, Text> transform, String commandPrefix) throws CommandException {
        Paginator.paginate(consumer, new Paginator<T>(elems), pre, transform, commandPrefix);
    }

    public static <T> void paginate(IArgConsumer consumer, T[] elems, Runnable pre, Function<T, Text> transform, String commandPrefix) throws CommandException {
        Paginator.paginate(consumer, Arrays.asList(elems), pre, transform, commandPrefix);
    }

    public static <T> void paginate(IArgConsumer consumer, Paginator<T> pagi, Function<T, Text> transform, String commandPrefix) throws CommandException {
        Paginator.paginate(consumer, pagi, null, transform, commandPrefix);
    }

    public static <T> void paginate(IArgConsumer consumer, List<T> elems, Function<T, Text> transform, String commandPrefix) throws CommandException {
        Paginator.paginate(consumer, new Paginator<T>(elems), null, transform, commandPrefix);
    }

    public static <T> void paginate(IArgConsumer consumer, T[] elems, Function<T, Text> transform, String commandPrefix) throws CommandException {
        Paginator.paginate(consumer, Arrays.asList(elems), null, transform, commandPrefix);
    }

    public static <T> void paginate(IArgConsumer consumer, Paginator<T> pagi, Runnable pre, Function<T, Text> transform) throws CommandException {
        Paginator.paginate(consumer, pagi, pre, transform, null);
    }

    public static <T> void paginate(IArgConsumer consumer, List<T> elems, Runnable pre, Function<T, Text> transform) throws CommandException {
        Paginator.paginate(consumer, new Paginator<T>(elems), pre, transform, null);
    }

    public static <T> void paginate(IArgConsumer consumer, T[] elems, Runnable pre, Function<T, Text> transform) throws CommandException {
        Paginator.paginate(consumer, Arrays.asList(elems), pre, transform, null);
    }

    public static <T> void paginate(IArgConsumer consumer, Paginator<T> pagi, Function<T, Text> transform) throws CommandException {
        Paginator.paginate(consumer, pagi, null, transform, null);
    }

    public static <T> void paginate(IArgConsumer consumer, List<T> elems, Function<T, Text> transform) throws CommandException {
        Paginator.paginate(consumer, new Paginator<T>(elems), null, transform, null);
    }

    public static <T> void paginate(IArgConsumer consumer, T[] elems, Function<T, Text> transform) throws CommandException {
        Paginator.paginate(consumer, Arrays.asList(elems), null, transform, null);
    }
}

