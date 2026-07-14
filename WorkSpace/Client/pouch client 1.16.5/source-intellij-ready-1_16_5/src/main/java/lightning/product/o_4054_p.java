/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  it.unimi.dsi.fastutil.shorts.Short2ObjectMap
 *  it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.util.Supplier
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.shorts.Short2ObjectMap;
import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lightning.product.SharedConstants;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.j_3341_s;
import lightning.product.q_2232_A;
import lightning.product.y_2339_p;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

public class o_4054_p {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Short2ObjectMap<y_2339_p> J_1907_R = new Short2ObjectOpenHashMap();
    private final Map<q_2232_A, Set<y_2339_p>> R_4764_Y = Maps.newHashMap();
    private final Runnable G_564_y;
    private boolean P_1922_E;

    public static Codec<o_4054_p> n_1700_B(Runnable p_234158_0_) {
        return RecordCodecBuilder.create(p_234159_1_ -> p_234159_1_.group((App)RecordCodecBuilder.point((Object)p_234158_0_), (App)Codec.BOOL.optionalFieldOf("Valid", (Object)false).forGetter(data -> data.P_1922_E), (App)y_2339_p.n_1700_B(p_234158_0_).listOf().fieldOf("Records").forGetter(data -> ImmutableList.copyOf((Collection)data.J_1907_R.values()))).apply((Applicative)p_234159_1_, o_4054_p::new)).orElseGet(j_3341_s.n_1700_B("Failed to read POI section: ", arg_0 -> ((Logger)n_1700_B).error(arg_0)), () -> new o_4054_p(p_234158_0_, false, (List<y_2339_p>)ImmutableList.of()));
    }

    public o_4054_p(Runnable onChangeIn) {
        this(onChangeIn, true, (List<y_2339_p>)ImmutableList.of());
    }

    private o_4054_p(Runnable onChange, boolean valid, List<y_2339_p> interestPoints) {
        this.G_564_y = onChange;
        this.P_1922_E = valid;
        interestPoints.forEach(this::n_1700_B);
    }

    public Stream<y_2339_p> n_1700_B(Predicate<q_2232_A> typePredicate, b_4946_z.J_1907_R status) {
        return this.R_4764_Y.entrySet().stream().filter(typeToPointEntry -> typePredicate.test((q_2232_A)typeToPointEntry.getKey())).flatMap(p_234160_0_ -> ((Set)p_234160_0_.getValue()).stream()).filter(status.n_1700_B());
    }

    public void n_1700_B(c_1514_x pos, q_2232_A type) {
        if (this.n_1700_B(new y_2339_p(pos, type, this.G_564_y))) {
            n_1700_B.debug("Added POI of type {} @ {}", new Supplier[]{() -> type, () -> pos});
            this.G_564_y.run();
        }
    }

    private boolean n_1700_B(y_2339_p p_218254_1_) {
        c_1514_x blockpos = p_218254_1_.P_1922_E();
        q_2232_A pointofinteresttype = p_218254_1_.u_1723_Y();
        short short1 = SectionPos.J_1907_R(blockpos);
        y_2339_p pointofinterest = (y_2339_p)this.J_1907_R.get(short1);
        if (pointofinterest != null) {
            if (pointofinteresttype.equals(pointofinterest.u_1723_Y())) {
                return false;
            }
            String s = "POI data mismatch: already registered at " + String.valueOf(blockpos);
            if (SharedConstants.G_564_y) {
                throw j_3341_s.R_4764_Y(new IllegalStateException(s));
            }
            n_1700_B.error(s);
        }
        this.J_1907_R.put(short1, (Object)p_218254_1_);
        this.R_4764_Y.computeIfAbsent(pointofinteresttype, type -> Sets.newHashSet()).add(p_218254_1_);
        return true;
    }

    public void n_1700_B(c_1514_x pos) {
        y_2339_p pointofinterest = (y_2339_p)this.J_1907_R.remove(SectionPos.J_1907_R(pos));
        if (pointofinterest == null) {
            n_1700_B.error("POI data mismatch: never registered at " + String.valueOf(pos));
        } else {
            this.R_4764_Y.get(pointofinterest.u_1723_Y()).remove(pointofinterest);
            Supplier[] supplierArray = new Supplier[2];
            supplierArray[0] = pointofinterest::u_1723_Y;
            supplierArray[1] = pointofinterest::P_1922_E;
            n_1700_B.debug("Removed POI of type {} @ {}", supplierArray);
            this.G_564_y.run();
        }
    }

    public boolean J_1907_R(c_1514_x pos) {
        y_2339_p pointofinterest = (y_2339_p)this.J_1907_R.get(SectionPos.J_1907_R(pos));
        if (pointofinterest == null) {
            throw j_3341_s.R_4764_Y(new IllegalStateException("POI never registered at " + String.valueOf(pos)));
        }
        boolean flag = pointofinterest.J_1907_R();
        this.G_564_y.run();
        return flag;
    }

    public boolean n_1700_B(c_1514_x pos, Predicate<q_2232_A> typePredicate) {
        short short1 = SectionPos.J_1907_R(pos);
        y_2339_p pointofinterest = (y_2339_p)this.J_1907_R.get(short1);
        return pointofinterest != null && typePredicate.test(pointofinterest.u_1723_Y());
    }

    public Optional<q_2232_A> R_4764_Y(c_1514_x pos) {
        short short1 = SectionPos.J_1907_R(pos);
        y_2339_p pointofinterest = (y_2339_p)this.J_1907_R.get(short1);
        return pointofinterest != null ? Optional.of(pointofinterest.u_1723_Y()) : Optional.empty();
    }

    public void n_1700_B(Consumer<BiConsumer<c_1514_x, q_2232_A>> posToTypeConsumer) {
        if (!this.P_1922_E) {
            Short2ObjectOpenHashMap short2objectmap = new Short2ObjectOpenHashMap(this.J_1907_R);
            this.J_1907_R();
            posToTypeConsumer.accept((arg_0, arg_1) -> this.n_1700_B((Short2ObjectMap)short2objectmap, arg_0, arg_1));
            this.P_1922_E = true;
            this.G_564_y.run();
        }
    }

    private void J_1907_R() {
        this.J_1907_R.clear();
        this.R_4764_Y.clear();
    }

    boolean n_1700_B() {
        return this.P_1922_E;
    }

    private /* synthetic */ void n_1700_B(Short2ObjectMap short2objectmap, c_1514_x pos, q_2232_A type) {
        short short1 = SectionPos.J_1907_R(pos);
        y_2339_p pointofinterest = (y_2339_p)short2objectmap.computeIfAbsent(short1, p_234156_3_ -> new y_2339_p(pos, type, this.G_564_y));
        this.n_1700_B(pointofinterest);
    }
}


