/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import lightning.product.D_4024_W;
import lightning.product.U_2871_b;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.x_282_a;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidTypeException;
import mods.baritone.api.api.java.baritone.api.utils.Helper;

public class Paginator<E>
implements Helper {
    public final List<E> entries;
    public int pageSize = 8;
    public int page = 1;

    public Paginator(List<E> entries) {
        this.entries = entries;
    }

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

    public Paginator<E> skipPages(int pages) {
        this.page += pages;
        return this;
    }

    public void display(Function<E, x_282_a> transform, String commandPrefix) {
        int offset;
        for (int i = offset = (this.page - 1) * this.pageSize; i < offset + this.pageSize; ++i) {
            if (i < this.entries.size()) {
                this.logDirect(transform.apply(this.entries.get(i)));
                continue;
            }
            this.logDirect("--", D_4024_W.t_148_a);
        }
        boolean hasPrevPage = commandPrefix != null && this.validPage(this.page - 1);
        boolean hasNextPage = commandPrefix != null && this.validPage(this.page + 1);
        U_2871_b prevPageComponent = new U_2871_b("<<");
        if (hasPrevPage) {
            prevPageComponent.n_1700_B(prevPageComponent.n_1700_B().n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, String.format("%s %d", commandPrefix, this.page - 1))).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("Click to view previous page"))));
        } else {
            prevPageComponent.n_1700_B(prevPageComponent.n_1700_B().n_1700_B(D_4024_W.t_148_a));
        }
        U_2871_b nextPageComponent = new U_2871_b(">>");
        if (hasNextPage) {
            nextPageComponent.n_1700_B(nextPageComponent.n_1700_B().n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, String.format("%s %d", commandPrefix, this.page + 1))).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("Click to view next page"))));
        } else {
            nextPageComponent.n_1700_B(nextPageComponent.n_1700_B().n_1700_B(D_4024_W.t_148_a));
        }
        U_2871_b pagerComponent = new U_2871_b("");
        pagerComponent.n_1700_B(pagerComponent.n_1700_B().n_1700_B(D_4024_W.w_1484_f));
        pagerComponent.n_1700_B(prevPageComponent);
        pagerComponent.n_1700_B(" | ");
        pagerComponent.n_1700_B(nextPageComponent);
        pagerComponent.n_1700_B(String.format(" %d/%d", this.page, this.getMaxPage()));
        this.logDirect(pagerComponent);
    }

    public void display(Function<E, x_282_a> transform) {
        this.display(transform, null);
    }

    public static <T> void paginate(IArgConsumer consumer, Paginator<T> pagi, Runnable pre, Function<T, x_282_a> transform, String commandPrefix) throws CommandException {
        int page = 1;
        consumer.requireMax(1);
        if (consumer.hasAny() && !pagi.validPage(page = consumer.getAs(Integer.class).intValue())) {
            throw new CommandInvalidTypeException(consumer.consumed(), String.format("a valid page (1-%d)", pagi.getMaxPage()), consumer.consumed().getValue());
        }
        pagi.skipPages(page - pagi.page);
        if (pre != null) {
            pre.run();
        }
        pagi.display(transform, commandPrefix);
    }

    public static <T> void paginate(IArgConsumer consumer, List<T> elems, Runnable pre, Function<T, x_282_a> transform, String commandPrefix) throws CommandException {
        Paginator.paginate(consumer, new Paginator<T>(elems), pre, transform, commandPrefix);
    }

    public static <T> void paginate(IArgConsumer consumer, T[] elems, Runnable pre, Function<T, x_282_a> transform, String commandPrefix) throws CommandException {
        Paginator.paginate(consumer, Arrays.asList(elems), pre, transform, commandPrefix);
    }

    public static <T> void paginate(IArgConsumer consumer, Paginator<T> pagi, Function<T, x_282_a> transform, String commandPrefix) throws CommandException {
        Paginator.paginate(consumer, pagi, null, transform, commandPrefix);
    }

    public static <T> void paginate(IArgConsumer consumer, List<T> elems, Function<T, x_282_a> transform, String commandPrefix) throws CommandException {
        Paginator.paginate(consumer, new Paginator<T>(elems), null, transform, commandPrefix);
    }

    public static <T> void paginate(IArgConsumer consumer, T[] elems, Function<T, x_282_a> transform, String commandPrefix) throws CommandException {
        Paginator.paginate(consumer, Arrays.asList(elems), null, transform, commandPrefix);
    }

    public static <T> void paginate(IArgConsumer consumer, Paginator<T> pagi, Runnable pre, Function<T, x_282_a> transform) throws CommandException {
        Paginator.paginate(consumer, pagi, pre, transform, null);
    }

    public static <T> void paginate(IArgConsumer consumer, List<T> elems, Runnable pre, Function<T, x_282_a> transform) throws CommandException {
        Paginator.paginate(consumer, new Paginator<T>(elems), pre, transform, null);
    }

    public static <T> void paginate(IArgConsumer consumer, T[] elems, Runnable pre, Function<T, x_282_a> transform) throws CommandException {
        Paginator.paginate(consumer, Arrays.asList(elems), pre, transform, null);
    }

    public static <T> void paginate(IArgConsumer consumer, Paginator<T> pagi, Function<T, x_282_a> transform) throws CommandException {
        Paginator.paginate(consumer, pagi, null, transform, null);
    }

    public static <T> void paginate(IArgConsumer consumer, List<T> elems, Function<T, x_282_a> transform) throws CommandException {
        Paginator.paginate(consumer, new Paginator<T>(elems), null, transform, null);
    }

    public static <T> void paginate(IArgConsumer consumer, T[] elems, Function<T, x_282_a> transform) throws CommandException {
        Paginator.paginate(consumer, Arrays.asList(elems), null, transform, null);
    }
}

