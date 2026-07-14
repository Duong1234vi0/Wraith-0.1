/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.ProfilerFiller;
import lightning.product.MinecraftClient;
import lightning.product.g_2336_b;
import lightning.product.SimplePreparableReloadListener;
import lightning.product.u_3100_Q;

public class W_1619_c
extends SimplePreparableReloadListener<List<String>> {
    private static final g_2336_b n_1700_B = new g_2336_b("texts/splashes.txt");
    private static final Random J_1907_R = new Random();
    private final List<String> R_4764_Y = Lists.newArrayList();
    private final u_3100_Q G_564_y;

    public W_1619_c(u_3100_Q gameSessionIn) {
        this.G_564_y = gameSessionIn;
    }

    /*
     * Enabled aggressive exception aggregation
     */
    protected List<String> n_1700_B(ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        try (Resource iresource = MinecraftClient.A_4115_X().T_2506_i().n_1700_B(n_1700_B);){
            List<String> list;
            try (BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(iresource.J_1907_R(), StandardCharsets.UTF_8));){
                list = bufferedreader.lines().map(String::trim).filter(p_215277_0_ -> p_215277_0_.hashCode() != 125780783).collect(Collectors.toList());
            }
            return list;
        }
        catch (IOException ioexception) {
            return Collections.emptyList();
        }
    }

    protected void n_1700_B(List<String> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        this.R_4764_Y.clear();
        this.R_4764_Y.addAll(objectIn);
    }

    @Nullable
    public String J_1907_R() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (calendar.get(2) + 1 == 12 && calendar.get(5) == 24) {
            return "Merry X-mas!";
        }
        if (calendar.get(2) + 1 == 1 && calendar.get(5) == 1) {
            return "Happy new year!";
        }
        if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31) {
            return "OOoooOOOoooo! Spooky!";
        }
        if (this.R_4764_Y.isEmpty()) {
            return null;
        }
        return this.G_564_y != null && J_1907_R.nextInt(this.R_4764_Y.size()) == 42 ? this.G_564_y.R_4764_Y().toUpperCase(Locale.ROOT) + " IS YOU" : this.R_4764_Y.get(J_1907_R.nextInt(this.R_4764_Y.size()));
    }

    @Override
    protected /* synthetic */ void apply(Object object, ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        this.n_1700_B((List)object, s_2107_a, x_2951_U);
    }

    @Override
    protected /* synthetic */ Object prepare(ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        return this.n_1700_B(s_2107_a, x_2951_U);
    }
}



