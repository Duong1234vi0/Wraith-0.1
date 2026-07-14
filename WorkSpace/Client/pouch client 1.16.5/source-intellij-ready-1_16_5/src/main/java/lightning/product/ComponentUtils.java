/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.authlib.GameProfile
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.ContextAwareComponent;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.N_4263_v;
import lightning.product.U_2871_b;
import lightning.product.Z_1567_W;
import lightning.product.c_973_a;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class ComponentUtils {
    public static MutableComponent n_1700_B(MutableComponent p_240648_0_, Z_1567_W p_240648_1_) {
        if (p_240648_1_.v_4262_N()) {
            return p_240648_0_;
        }
        Z_1567_W style = p_240648_0_.n_1700_B();
        if (style.v_4262_N()) {
            return p_240648_0_.n_1700_B(p_240648_1_);
        }
        return style.equals(p_240648_1_) ? p_240648_0_ : p_240648_0_.n_1700_B(style.n_1700_B(p_240648_1_));
    }

    public static MutableComponent n_1700_B(@Nullable y_2498_m p_240645_0_, x_282_a p_240645_1_, @Nullable N_4263_v p_240645_2_, int p_240645_3_) throws CommandSyntaxException {
        if (p_240645_3_ > 100) {
            return p_240645_1_.P_1922_E();
        }
        MutableComponent iformattabletextcomponent = p_240645_1_ instanceof ContextAwareComponent ? ((ContextAwareComponent)((Object)p_240645_1_)).n_1700_B(p_240645_0_, p_240645_2_, p_240645_3_ + 1) : p_240645_1_.G_564_y();
        for (x_282_a itextcomponent : p_240645_1_.R_4764_Y()) {
            iformattabletextcomponent.n_1700_B(ComponentUtils.n_1700_B(p_240645_0_, itextcomponent, p_240645_2_, p_240645_3_ + 1));
        }
        return iformattabletextcomponent.J_1907_R(ComponentUtils.n_1700_B(p_240645_0_, p_240645_1_.n_1700_B(), p_240645_2_, p_240645_3_));
    }

    private static Z_1567_W n_1700_B(@Nullable y_2498_m p_240646_0_, Z_1567_W p_240646_1_, @Nullable N_4263_v p_240646_2_, int p_240646_3_) throws CommandSyntaxException {
        x_282_a itextcomponent;
        c_973_a hoverevent = p_240646_1_.t_148_a();
        if (hoverevent != null && (itextcomponent = hoverevent.n_1700_B(c_973_a.n_1700_B.n_1700_B)) != null) {
            c_973_a hoverevent1 = new c_973_a(c_973_a.n_1700_B.n_1700_B, ComponentUtils.n_1700_B(p_240646_0_, itextcomponent, p_240646_2_, p_240646_3_ + 1));
            return p_240646_1_.n_1700_B(hoverevent1);
        }
        return p_240646_1_;
    }

    public static x_282_a n_1700_B(GameProfile profile) {
        if (profile.getName() != null) {
            return new U_2871_b(profile.getName());
        }
        return profile.getId() != null ? new U_2871_b(profile.getId().toString()) : new U_2871_b("(unknown)");
    }

    public static x_282_a n_1700_B(Collection<String> collection) {
        return ComponentUtils.n_1700_B(collection, (T p_197681_0_) -> new U_2871_b((String)p_197681_0_).n_1700_B(D_4024_W.u_2550_I));
    }

    public static <T extends Comparable<T>> x_282_a n_1700_B(Collection<T> collection, Function<T, x_282_a> toTextComponent) {
        if (collection.isEmpty()) {
            return U_2871_b.R_4764_Y;
        }
        if (collection.size() == 1) {
            return toTextComponent.apply((Comparable)collection.iterator().next());
        }
        ArrayList list = Lists.newArrayList(collection);
        list.sort(Comparable::compareTo);
        return ComponentUtils.J_1907_R(list, toTextComponent);
    }

    public static <T> MutableComponent J_1907_R(Collection<T> p_240649_0_, Function<T, x_282_a> p_240649_1_) {
        if (p_240649_0_.isEmpty()) {
            return new U_2871_b("");
        }
        if (p_240649_0_.size() == 1) {
            return p_240649_1_.apply(p_240649_0_.iterator().next()).P_1922_E();
        }
        U_2871_b iformattabletextcomponent = new U_2871_b("");
        boolean flag = true;
        for (T t : p_240649_0_) {
            if (!flag) {
                iformattabletextcomponent.n_1700_B(new U_2871_b(", ").n_1700_B(D_4024_W.w_1484_f));
            }
            iformattabletextcomponent.n_1700_B(p_240649_1_.apply(t));
            flag = false;
        }
        return iformattabletextcomponent;
    }

    public static MutableComponent n_1700_B(x_282_a toWrap) {
        return new F_2904_S("chat.square_brackets", toWrap);
    }

    public static x_282_a n_1700_B(Message message) {
        return message instanceof x_282_a ? (x_282_a)message : new U_2871_b(message.getString());
    }
}


