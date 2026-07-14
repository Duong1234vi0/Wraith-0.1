/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.properties.Property
 *  com.mojang.datafixers.DataFixer
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.H_1468_N;
import lightning.product.SerializableUUID;
import lightning.product.SharedConstants;
import lightning.product.K_4074_S;
import lightning.product.P_1008_U;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.IntArrayTag;
import lightning.product.V_3137_a;
import lightning.product.Y_1835_y;
import lightning.product.Tag;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;
import lightning.product.l_4118_l;
import lightning.product.o_1967_f;
import lightning.product.q_2896_o;
import lightning.product.v_3760_Q;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class n_3832_I {
    private static final Logger n_1700_B = LogManager.getLogger();

    @Nullable
    public static GameProfile n_1700_B(U_2912_j compound) {
        String s = null;
        UUID uuid = null;
        if (compound.R_4764_Y("Name", 8)) {
            s = compound.M_588_G("Name");
        }
        if (compound.J_1907_R("Id")) {
            uuid = compound.n_1700_B("Id");
        }
        try {
            GameProfile gameprofile = new GameProfile(uuid, s);
            if (compound.R_4764_Y("Properties", 10)) {
                U_2912_j compoundnbt = compound.M_182_A("Properties");
                for (String s1 : compoundnbt.G_564_y()) {
                    q_2896_o listnbt = compoundnbt.G_564_y(s1, 10);
                    for (int i = 0; i < listnbt.size(); ++i) {
                        U_2912_j compoundnbt1 = listnbt.n_1700_B(i);
                        String s2 = compoundnbt1.M_588_G("Value");
                        if (compoundnbt1.R_4764_Y("Signature", 8)) {
                            gameprofile.getProperties().put((Object)s1, (Object)new Property(s1, s2, compoundnbt1.M_588_G("Signature")));
                            continue;
                        }
                        gameprofile.getProperties().put((Object)s1, (Object)new Property(s1, s2));
                    }
                }
            }
            return gameprofile;
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    public static U_2912_j n_1700_B(U_2912_j tagCompound, GameProfile profile) {
        if (!H_1468_N.J_1907_R(profile.getName())) {
            tagCompound.n_1700_B("Name", profile.getName());
        }
        if (profile.getId() != null) {
            tagCompound.n_1700_B("Id", profile.getId());
        }
        if (!profile.getProperties().isEmpty()) {
            U_2912_j compoundnbt = new U_2912_j();
            for (String s : profile.getProperties().keySet()) {
                q_2896_o listnbt = new q_2896_o();
                for (Property property : profile.getProperties().get((Object)s)) {
                    U_2912_j compoundnbt1 = new U_2912_j();
                    compoundnbt1.n_1700_B("Value", property.getValue());
                    if (property.hasSignature()) {
                        compoundnbt1.n_1700_B("Signature", property.getSignature());
                    }
                    listnbt.add(compoundnbt1);
                }
                compoundnbt.n_1700_B(s, listnbt);
            }
            tagCompound.n_1700_B("Properties", compoundnbt);
        }
        return tagCompound;
    }

    @VisibleForTesting
    public static boolean n_1700_B(@Nullable Tag nbt1, @Nullable Tag nbt2, boolean compareTagList) {
        if (nbt1 == nbt2) {
            return true;
        }
        if (nbt1 == null) {
            return true;
        }
        if (nbt2 == null) {
            return false;
        }
        if (!nbt1.getClass().equals(nbt2.getClass())) {
            return false;
        }
        if (nbt1 instanceof U_2912_j) {
            U_2912_j compoundnbt = (U_2912_j)nbt1;
            U_2912_j compoundnbt1 = (U_2912_j)nbt2;
            for (String s : compoundnbt.G_564_y()) {
                Tag inbt1 = compoundnbt.R_4764_Y(s);
                if (n_3832_I.n_1700_B(inbt1, compoundnbt1.R_4764_Y(s), compareTagList)) continue;
                return false;
            }
            return true;
        }
        if (nbt1 instanceof q_2896_o && compareTagList) {
            q_2896_o listnbt = (q_2896_o)nbt1;
            q_2896_o listnbt1 = (q_2896_o)nbt2;
            if (listnbt.isEmpty()) {
                return listnbt1.isEmpty();
            }
            for (int i = 0; i < listnbt.size(); ++i) {
                Tag inbt = listnbt.s_956_w(i);
                boolean flag = false;
                for (int j = 0; j < listnbt1.size(); ++j) {
                    if (!n_3832_I.n_1700_B(inbt, listnbt1.s_956_w(j), compareTagList)) continue;
                    flag = true;
                    break;
                }
                if (flag) continue;
                return false;
            }
            return true;
        }
        return nbt1.equals(nbt2);
    }

    public static IntArrayTag n_1700_B(UUID p_240626_0_) {
        return new IntArrayTag(SerializableUUID.n_1700_B(p_240626_0_));
    }

    public static UUID n_1700_B(Tag tag) {
        if (tag.J_1907_R() != IntArrayTag.n_1700_B) {
            throw new IllegalArgumentException("Expected UUID-Tag to be of type " + IntArrayTag.n_1700_B.n_1700_B() + ", but found " + tag.J_1907_R().n_1700_B() + ".");
        }
        int[] aint = ((IntArrayTag)tag).u_1723_Y();
        if (aint.length != 4) {
            throw new IllegalArgumentException("Expected UUID-Array to be of length 4, but found " + aint.length + ".");
        }
        return SerializableUUID.n_1700_B(aint);
    }

    public static c_1514_x J_1907_R(U_2912_j tag) {
        return new c_1514_x(tag.w_1484_f("X"), tag.w_1484_f("Y"), tag.w_1484_f("Z"));
    }

    public static U_2912_j n_1700_B(c_1514_x pos) {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.J_1907_R("X", pos.getX());
        compoundnbt.J_1907_R("Y", pos.getY());
        compoundnbt.J_1907_R("Z", pos.getZ());
        return compoundnbt;
    }

    public static K_4074_S R_4764_Y(U_2912_j tag) {
        if (!tag.R_4764_Y("Name", 8)) {
            return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        T_2915_h block = V_3137_a.q_4610_l.n_1700_B(new g_2336_b(tag.M_588_G("Name")));
        K_4074_S blockstate = block.multiplayerClientSuggestionProvider();
        if (tag.R_4764_Y("Properties", 10)) {
            U_2912_j compoundnbt = tag.M_182_A("Properties");
            Y_1835_y<T_2915_h, K_4074_S> statecontainer = block.t_1786_h();
            for (String s : compoundnbt.G_564_y()) {
                v_3760_Q<?> property = statecontainer.n_1700_B(s);
                if (property == null) continue;
                blockstate = n_3832_I.n_1700_B(blockstate, property, s, compoundnbt, tag);
            }
        }
        return blockstate;
    }

    private static <S extends P_1008_U<?, S>, T extends Comparable<T>> S n_1700_B(S p_193590_0_, v_3760_Q<T> p_193590_1_, String p_193590_2_, U_2912_j p_193590_3_, U_2912_j p_193590_4_) {
        Optional<T> optional = p_193590_1_.J_1907_R(p_193590_3_.M_588_G(p_193590_2_));
        if (optional.isPresent()) {
            return (S)((P_1008_U)p_193590_0_.n_1700_B(p_193590_1_, (Comparable)((Comparable)optional.get())));
        }
        n_1700_B.warn("Unable to read property: {} with value: {} for blockstate: {}", (Object)p_193590_2_, (Object)p_193590_3_.M_588_G(p_193590_2_), (Object)p_193590_4_.toString());
        return p_193590_0_;
    }

    public static U_2912_j n_1700_B(K_4074_S tag) {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("Name", V_3137_a.q_4610_l.J_1907_R(tag.J_1907_R()).toString());
        ImmutableMap<v_3760_Q<?>, Comparable<?>> immutablemap = tag.q_2307_F();
        if (!immutablemap.isEmpty()) {
            U_2912_j compoundnbt1 = new U_2912_j();
            for (Map.Entry entry : immutablemap.entrySet()) {
                v_3760_Q property = (v_3760_Q)entry.getKey();
                compoundnbt1.n_1700_B(property.P_1922_E(), n_3832_I.n_1700_B(property, (Comparable)entry.getValue()));
            }
            compoundnbt.n_1700_B("Properties", compoundnbt1);
        }
        return compoundnbt;
    }

    private static <T extends Comparable<T>> String n_1700_B(v_3760_Q<T> p_190010_0_, Comparable<?> p_190010_1_) {
        return p_190010_0_.n_1700_B(p_190010_1_);
    }

    public static U_2912_j n_1700_B(DataFixer dataFixer, o_1967_f type, U_2912_j nbt, int version) {
        return n_3832_I.n_1700_B(dataFixer, type, nbt, version, SharedConstants.n_1700_B().getWorldVersion());
    }

    public static U_2912_j n_1700_B(DataFixer dataFixer, o_1967_f type, U_2912_j nbt, int version, int newVersion) {
        return (U_2912_j)dataFixer.update(type.n_1700_B(), new Dynamic((DynamicOps)l_4118_l.n_1700_B, (Object)nbt), version, newVersion).getValue();
    }
}


