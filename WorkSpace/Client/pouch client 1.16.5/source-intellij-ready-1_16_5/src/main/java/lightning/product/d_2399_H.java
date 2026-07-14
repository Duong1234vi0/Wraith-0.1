/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import java.util.Collection;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.A_2226_Q;
import lightning.product.TagContainer;
import lightning.product.V_4217_p;
import lightning.product.ClientAdvancements;
import lightning.product.c_1633_k;
import lightning.product.DebugQueryHandler;
import lightning.product.Packet;

public interface d_2399_H {
    public void n_1700_B(Packet<?> var1);

    public void J_1907_R(Packet<?> var1);

    public c_1633_k n_1700_B();

    public ClientAdvancements J_1907_R();

    public Collection<A_2226_Q> R_4764_Y();

    @Nullable
    public A_2226_Q n_1700_B(UUID var1);

    @Nullable
    public A_2226_Q n_1700_B(String var1);

    public CommandDispatcher<V_4217_p> G_564_y();

    public V_4217_p P_1922_E();

    public TagContainer u_1723_Y();

    @Nullable
    public DebugQueryHandler v_4262_N();
}


