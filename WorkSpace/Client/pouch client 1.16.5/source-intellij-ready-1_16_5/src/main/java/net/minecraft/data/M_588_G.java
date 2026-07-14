/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.mojang.brigadier.CommandDispatcher
 */
package net.minecraft.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.brigadier.CommandDispatcher;
import java.io.IOException;
import java.nio.file.Path;
import lightning.product.A_958_X;
import lightning.product.Q_2241_p;
import lightning.product.y_2498_m;
import net.minecraft.data.M_182_A;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.Y_259_p;

public class M_588_G
implements Y_259_p {
    private static final Gson J_1907_R = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final Q_4569_t R_4764_Y;

    public M_588_G(Q_4569_t generatorIn) {
        this.R_4764_Y = generatorIn;
    }

    @Override
    public void n_1700_B(M_182_A cache) throws IOException {
        Path path = this.R_4764_Y.J_1907_R().resolve("reports/commands.json");
        CommandDispatcher<y_2498_m> commanddispatcher = new Q_2241_p(Q_2241_p.n_1700_B.n_1700_B).n_1700_B();
        Y_259_p.n_1700_B(J_1907_R, cache, (JsonElement)A_958_X.n_1700_B(commanddispatcher, commanddispatcher.getRoot()), path);
    }

    @Override
    public String n_1700_B() {
        return "Command Syntax";
    }
}

