/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.context.StringRange
 *  com.mojang.brigadier.suggestion.Suggestion
 *  com.mojang.brigadier.suggestion.Suggestions
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;

public class ClientboundCommandSuggestionsPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private Suggestions J_1907_R;

    public ClientboundCommandSuggestionsPacket() {
    }

    public ClientboundCommandSuggestionsPacket(int p_i47941_1_, Suggestions p_i47941_2_) {
        this.n_1700_B = p_i47941_1_;
        this.J_1907_R = p_i47941_2_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        int i = buf.u_1723_Y();
        int j = buf.u_1723_Y();
        StringRange stringrange = StringRange.between((int)i, (int)(i + j));
        int k = buf.u_1723_Y();
        ArrayList list = Lists.newArrayListWithCapacity((int)k);
        for (int l = 0; l < k; ++l) {
            String s = buf.P_1922_E(Short.MAX_VALUE);
            x_282_a itextcomponent = buf.readBoolean() ? buf.P_1922_E() : null;
            list.add(new Suggestion(stringrange, s, (Message)itextcomponent));
        }
        this.J_1907_R = new Suggestions(stringrange, (List)list);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.G_564_y(this.J_1907_R.getRange().getStart());
        buf.G_564_y(this.J_1907_R.getRange().getLength());
        buf.G_564_y(this.J_1907_R.getList().size());
        for (Suggestion suggestion : this.J_1907_R.getList()) {
            buf.n_1700_B(suggestion.getText());
            buf.writeBoolean(suggestion.getTooltip() != null);
            if (suggestion.getTooltip() == null) continue;
            buf.n_1700_B(ComponentUtils.n_1700_B(suggestion.getTooltip()));
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public Suggestions R_4764_Y() {
        return this.J_1907_R;
    }
}


