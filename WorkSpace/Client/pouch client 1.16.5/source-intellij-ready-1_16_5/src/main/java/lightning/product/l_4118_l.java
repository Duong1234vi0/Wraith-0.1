/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterators
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.PeekingIterator
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.MapLike
 *  com.mojang.serialization.RecordBuilder
 *  com.mojang.serialization.RecordBuilder$AbstractStringBuilder
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.PeekingIterator;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapLike;
import com.mojang.serialization.RecordBuilder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.D_908_R;
import lightning.product.StringTag;
import lightning.product.LongArrayTag;
import lightning.product.L_3985_e;
import lightning.product.T_2717_K;
import lightning.product.NumericTag;
import lightning.product.U_2912_j;
import lightning.product.IntArrayTag;
import lightning.product.Tag;
import lightning.product.a_969_m;
import lightning.product.ByteArrayTag;
import lightning.product.CollectionTag;
import lightning.product.q_2567_I;
import lightning.product.q_2896_o;
import lightning.product.IntTag;
import lightning.product.EndTag;

public class l_4118_l
implements DynamicOps<Tag> {
    public static final l_4118_l n_1700_B = new l_4118_l();

    protected l_4118_l() {
    }

    public Tag n_1700_B() {
        return EndTag.J_1907_R;
    }

    public <U> U n_1700_B(DynamicOps<U> p_convertTo_1_, Tag p_convertTo_2_) {
        switch (p_convertTo_2_.n_1700_B()) {
            case 0: {
                return (U)p_convertTo_1_.empty();
            }
            case 1: {
                return (U)p_convertTo_1_.createByte(((NumericTag)p_convertTo_2_).w_1484_f());
            }
            case 2: {
                return (U)p_convertTo_1_.createShort(((NumericTag)p_convertTo_2_).v_4262_N());
            }
            case 3: {
                return (U)p_convertTo_1_.createInt(((NumericTag)p_convertTo_2_).u_1723_Y());
            }
            case 4: {
                return (U)p_convertTo_1_.createLong(((NumericTag)p_convertTo_2_).P_1922_E());
            }
            case 5: {
                return (U)p_convertTo_1_.createFloat(((NumericTag)p_convertTo_2_).s_956_w());
            }
            case 6: {
                return (U)p_convertTo_1_.createDouble(((NumericTag)p_convertTo_2_).t_148_a());
            }
            case 7: {
                return (U)p_convertTo_1_.createByteList(ByteBuffer.wrap(((ByteArrayTag)p_convertTo_2_).G_564_y()));
            }
            case 8: {
                return (U)p_convertTo_1_.createString(p_convertTo_2_.M_588_G());
            }
            case 9: {
                return (U)this.convertList(p_convertTo_1_, p_convertTo_2_);
            }
            case 10: {
                return (U)this.convertMap(p_convertTo_1_, p_convertTo_2_);
            }
            case 11: {
                return (U)p_convertTo_1_.createIntList(Arrays.stream(((IntArrayTag)p_convertTo_2_).u_1723_Y()));
            }
            case 12: {
                return (U)p_convertTo_1_.createLongList(Arrays.stream(((LongArrayTag)p_convertTo_2_).u_1723_Y()));
            }
        }
        throw new IllegalStateException("Unknown tag type: " + String.valueOf(p_convertTo_2_));
    }

    public DataResult<Number> n_1700_B(Tag p_getNumberValue_1_) {
        return p_getNumberValue_1_ instanceof NumericTag ? DataResult.success((Object)((NumericTag)p_getNumberValue_1_).u_2550_I()) : DataResult.error((String)"Not a number");
    }

    public Tag n_1700_B(Number p_createNumeric_1_) {
        return D_908_R.n_1700_B(p_createNumeric_1_.doubleValue());
    }

    public Tag n_1700_B(byte p_createByte_1_) {
        return L_3985_e.n_1700_B(p_createByte_1_);
    }

    public Tag n_1700_B(short p_createShort_1_) {
        return a_969_m.n_1700_B(p_createShort_1_);
    }

    public Tag n_1700_B(int p_createInt_1_) {
        return IntTag.n_1700_B(p_createInt_1_);
    }

    public Tag n_1700_B(long p_createLong_1_) {
        return q_2567_I.n_1700_B(p_createLong_1_);
    }

    public Tag n_1700_B(float p_createFloat_1_) {
        return T_2717_K.n_1700_B(p_createFloat_1_);
    }

    public Tag n_1700_B(double p_createDouble_1_) {
        return D_908_R.n_1700_B(p_createDouble_1_);
    }

    public Tag n_1700_B(boolean p_createBoolean_1_) {
        return L_3985_e.n_1700_B(p_createBoolean_1_);
    }

    public DataResult<String> J_1907_R(Tag p_getStringValue_1_) {
        return p_getStringValue_1_ instanceof StringTag ? DataResult.success((Object)p_getStringValue_1_.M_588_G()) : DataResult.error((String)"Not a string");
    }

    public Tag n_1700_B(String p_createString_1_) {
        return StringTag.n_1700_B(p_createString_1_);
    }

    private static CollectionTag<?> n_1700_B(byte p_240602_0_, byte p_240602_1_) {
        if (l_4118_l.n_1700_B(p_240602_0_, p_240602_1_, (byte)4)) {
            return new LongArrayTag(new long[0]);
        }
        if (l_4118_l.n_1700_B(p_240602_0_, p_240602_1_, (byte)1)) {
            return new ByteArrayTag(new byte[0]);
        }
        return l_4118_l.n_1700_B(p_240602_0_, p_240602_1_, (byte)3) ? new IntArrayTag(new int[0]) : new q_2896_o();
    }

    private static boolean n_1700_B(byte p_240603_0_, byte p_240603_1_, byte p_240603_2_) {
        return p_240603_0_ == p_240603_2_ && (p_240603_1_ == p_240603_2_ || p_240603_1_ == 0);
    }

    private static <T extends Tag> void n_1700_B(CollectionTag<T> p_240609_0_, Tag p_240609_1_, Tag p_240609_2_) {
        if (p_240609_1_ instanceof CollectionTag) {
            CollectionTag collectionnbt = (CollectionTag)p_240609_1_;
            collectionnbt.forEach(p_240616_1_ -> p_240609_0_.add(p_240616_1_));
        }
        p_240609_0_.add(p_240609_2_);
    }

    private static <T extends Tag> void n_1700_B(CollectionTag<T> p_240608_0_, Tag p_240608_1_, List<Tag> p_240608_2_) {
        if (p_240608_1_ instanceof CollectionTag) {
            CollectionTag collectionnbt = (CollectionTag)p_240608_1_;
            collectionnbt.forEach(p_240614_1_ -> p_240608_0_.add(p_240614_1_));
        }
        p_240608_2_.forEach(p_240607_1_ -> p_240608_0_.add(p_240607_1_));
    }

    public DataResult<Tag> n_1700_B(Tag p_mergeToList_1_, Tag p_mergeToList_2_) {
        if (!(p_mergeToList_1_ instanceof CollectionTag) && !(p_mergeToList_1_ instanceof EndTag)) {
            return DataResult.error((String)("mergeToList called with not a list: " + String.valueOf(p_mergeToList_1_)), (Object)p_mergeToList_1_);
        }
        CollectionTag<?> collectionnbt = l_4118_l.n_1700_B(p_mergeToList_1_ instanceof CollectionTag ? ((CollectionTag)p_mergeToList_1_).P_1922_E() : (byte)0, p_mergeToList_2_.n_1700_B());
        l_4118_l.n_1700_B(collectionnbt, p_mergeToList_1_, p_mergeToList_2_);
        return DataResult.success(collectionnbt);
    }

    public DataResult<Tag> n_1700_B(Tag p_mergeToList_1_, List<Tag> p_mergeToList_2_) {
        if (!(p_mergeToList_1_ instanceof CollectionTag) && !(p_mergeToList_1_ instanceof EndTag)) {
            return DataResult.error((String)("mergeToList called with not a list: " + String.valueOf(p_mergeToList_1_)), (Object)p_mergeToList_1_);
        }
        CollectionTag<?> collectionnbt = l_4118_l.n_1700_B(p_mergeToList_1_ instanceof CollectionTag ? ((CollectionTag)p_mergeToList_1_).P_1922_E() : (byte)0, p_mergeToList_2_.stream().findFirst().map(Tag::n_1700_B).orElse((byte)0));
        l_4118_l.n_1700_B(collectionnbt, p_mergeToList_1_, p_mergeToList_2_);
        return DataResult.success(collectionnbt);
    }

    public DataResult<Tag> n_1700_B(Tag p_mergeToMap_1_, Tag p_mergeToMap_2_, Tag p_mergeToMap_3_) {
        if (!(p_mergeToMap_1_ instanceof U_2912_j) && !(p_mergeToMap_1_ instanceof EndTag)) {
            return DataResult.error((String)("mergeToMap called with not a map: " + String.valueOf(p_mergeToMap_1_)), (Object)p_mergeToMap_1_);
        }
        if (!(p_mergeToMap_2_ instanceof StringTag)) {
            return DataResult.error((String)("key is not a string: " + String.valueOf(p_mergeToMap_2_)), (Object)p_mergeToMap_1_);
        }
        U_2912_j compoundnbt = new U_2912_j();
        if (p_mergeToMap_1_ instanceof U_2912_j) {
            U_2912_j compoundnbt1 = (U_2912_j)p_mergeToMap_1_;
            compoundnbt1.G_564_y().forEach(p_240617_2_ -> compoundnbt.n_1700_B((String)p_240617_2_, compoundnbt1.R_4764_Y((String)p_240617_2_)));
        }
        compoundnbt.n_1700_B(p_mergeToMap_2_.M_588_G(), p_mergeToMap_3_);
        return DataResult.success((Object)compoundnbt);
    }

    public DataResult<Tag> n_1700_B(Tag p_mergeToMap_1_, MapLike<Tag> p_mergeToMap_2_) {
        if (!(p_mergeToMap_1_ instanceof U_2912_j) && !(p_mergeToMap_1_ instanceof EndTag)) {
            return DataResult.error((String)("mergeToMap called with not a map: " + String.valueOf(p_mergeToMap_1_)), (Object)p_mergeToMap_1_);
        }
        U_2912_j compoundnbt = new U_2912_j();
        if (p_mergeToMap_1_ instanceof U_2912_j) {
            U_2912_j compoundnbt1 = (U_2912_j)p_mergeToMap_1_;
            compoundnbt1.G_564_y().forEach(p_240615_2_ -> compoundnbt.n_1700_B((String)p_240615_2_, compoundnbt1.R_4764_Y((String)p_240615_2_)));
        }
        ArrayList list = Lists.newArrayList();
        p_mergeToMap_2_.entries().forEach(p_240605_2_ -> {
            Tag inbt = (Tag)p_240605_2_.getFirst();
            if (!(inbt instanceof StringTag)) {
                list.add(inbt);
            } else {
                compoundnbt.n_1700_B(inbt.M_588_G(), (Tag)p_240605_2_.getSecond());
            }
        });
        return !list.isEmpty() ? DataResult.error((String)("some keys are not strings: " + String.valueOf(list)), (Object)compoundnbt) : DataResult.success((Object)compoundnbt);
    }

    public DataResult<Stream<Pair<Tag, Tag>>> R_4764_Y(Tag p_getMapValues_1_) {
        if (!(p_getMapValues_1_ instanceof U_2912_j)) {
            return DataResult.error((String)("Not a map: " + String.valueOf(p_getMapValues_1_)));
        }
        U_2912_j compoundnbt = (U_2912_j)p_getMapValues_1_;
        return DataResult.success(compoundnbt.G_564_y().stream().map(p_240611_2_ -> Pair.of((Object)this.n_1700_B((String)p_240611_2_), (Object)compoundnbt.R_4764_Y((String)p_240611_2_))));
    }

    public DataResult<Consumer<BiConsumer<Tag, Tag>>> G_564_y(Tag p_getMapEntries_1_) {
        if (!(p_getMapEntries_1_ instanceof U_2912_j)) {
            return DataResult.error((String)("Not a map: " + String.valueOf(p_getMapEntries_1_)));
        }
        U_2912_j compoundnbt = (U_2912_j)p_getMapEntries_1_;
        return DataResult.success(p_240612_2_ -> compoundnbt.G_564_y().forEach(p_240606_3_ -> p_240612_2_.accept(this.n_1700_B((String)p_240606_3_), compoundnbt.R_4764_Y((String)p_240606_3_))));
    }

    public DataResult<MapLike<Tag>> P_1922_E(Tag p_getMap_1_) {
        if (!(p_getMap_1_ instanceof U_2912_j)) {
            return DataResult.error((String)("Not a map: " + String.valueOf(p_getMap_1_)));
        }
        final U_2912_j compoundnbt = (U_2912_j)p_getMap_1_;
        return DataResult.success((Object)new MapLike<Tag>(){

            @Nullable
            public Tag n_1700_B(Tag p_get_1_) {
                return compoundnbt.R_4764_Y(p_get_1_.M_588_G());
            }

            @Nullable
            public Tag n_1700_B(String p_get_1_) {
                return compoundnbt.R_4764_Y(p_get_1_);
            }

            public Stream<Pair<Tag, Tag>> entries() {
                return compoundnbt.G_564_y().stream().map(p_240624_2_ -> Pair.of((Object)l_4118_l.this.n_1700_B((String)p_240624_2_), (Object)compoundnbt.R_4764_Y((String)p_240624_2_)));
            }

            public String toString() {
                return "MapLike[" + String.valueOf(compoundnbt) + "]";
            }

            @Nullable
            public /* synthetic */ Object get(String string) {
                return this.n_1700_B(string);
            }

            @Nullable
            public /* synthetic */ Object get(Object object) {
                return this.n_1700_B((Tag)object);
            }
        });
    }

    public Tag n_1700_B(Stream<Pair<Tag, Tag>> p_createMap_1_) {
        U_2912_j compoundnbt = new U_2912_j();
        p_createMap_1_.forEach(p_240610_1_ -> compoundnbt.n_1700_B(((Tag)p_240610_1_.getFirst()).M_588_G(), (Tag)p_240610_1_.getSecond()));
        return compoundnbt;
    }

    public DataResult<Stream<Tag>> u_1723_Y(Tag p_getStream_1_) {
        return p_getStream_1_ instanceof CollectionTag ? DataResult.success(((CollectionTag)p_getStream_1_).stream().map(p_240621_0_ -> p_240621_0_)) : DataResult.error((String)"Not a list");
    }

    public DataResult<Consumer<Consumer<Tag>>> v_4262_N(Tag p_getList_1_) {
        if (p_getList_1_ instanceof CollectionTag) {
            CollectionTag collectionnbt = (CollectionTag)p_getList_1_;
            return DataResult.success(collectionnbt::forEach);
        }
        return DataResult.error((String)("Not a list: " + String.valueOf(p_getList_1_)));
    }

    public DataResult<ByteBuffer> w_1484_f(Tag p_getByteBuffer_1_) {
        return p_getByteBuffer_1_ instanceof ByteArrayTag ? DataResult.success((Object)ByteBuffer.wrap(((ByteArrayTag)p_getByteBuffer_1_).G_564_y())) : super.getByteBuffer((Object)p_getByteBuffer_1_);
    }

    public Tag n_1700_B(ByteBuffer p_createByteList_1_) {
        return new ByteArrayTag(DataFixUtils.toArray((ByteBuffer)p_createByteList_1_));
    }

    public DataResult<IntStream> t_148_a(Tag p_getIntStream_1_) {
        return p_getIntStream_1_ instanceof IntArrayTag ? DataResult.success((Object)Arrays.stream(((IntArrayTag)p_getIntStream_1_).u_1723_Y())) : super.getIntStream((Object)p_getIntStream_1_);
    }

    public Tag n_1700_B(IntStream p_createIntList_1_) {
        return new IntArrayTag(p_createIntList_1_.toArray());
    }

    public DataResult<LongStream> s_956_w(Tag p_getLongStream_1_) {
        return p_getLongStream_1_ instanceof LongArrayTag ? DataResult.success((Object)Arrays.stream(((LongArrayTag)p_getLongStream_1_).u_1723_Y())) : super.getLongStream((Object)p_getLongStream_1_);
    }

    public Tag n_1700_B(LongStream p_createLongList_1_) {
        return new LongArrayTag(p_createLongList_1_.toArray());
    }

    public Tag J_1907_R(Stream<Tag> p_createList_1_) {
        PeekingIterator peekingiterator = Iterators.peekingIterator(p_createList_1_.iterator());
        if (!peekingiterator.hasNext()) {
            return new q_2896_o();
        }
        Tag inbt = (Tag)peekingiterator.peek();
        if (inbt instanceof L_3985_e) {
            ArrayList list2 = Lists.newArrayList((Iterator)Iterators.transform((Iterator)peekingiterator, p_210815_0_ -> ((L_3985_e)p_210815_0_).w_1484_f()));
            return new ByteArrayTag(list2);
        }
        if (inbt instanceof IntTag) {
            ArrayList list1 = Lists.newArrayList((Iterator)Iterators.transform((Iterator)peekingiterator, p_210818_0_ -> ((IntTag)p_210818_0_).u_1723_Y()));
            return new IntArrayTag(list1);
        }
        if (inbt instanceof q_2567_I) {
            ArrayList list = Lists.newArrayList((Iterator)Iterators.transform((Iterator)peekingiterator, p_210816_0_ -> ((q_2567_I)p_210816_0_).P_1922_E()));
            return new LongArrayTag(list);
        }
        q_2896_o listnbt = new q_2896_o();
        while (peekingiterator.hasNext()) {
            Tag inbt1 = (Tag)peekingiterator.next();
            if (inbt1 instanceof EndTag) continue;
            listnbt.add(inbt1);
        }
        return listnbt;
    }

    public Tag n_1700_B(Tag p_remove_1_, String p_remove_2_) {
        if (p_remove_1_ instanceof U_2912_j) {
            U_2912_j compoundnbt = (U_2912_j)p_remove_1_;
            U_2912_j compoundnbt1 = new U_2912_j();
            compoundnbt.G_564_y().stream().filter(p_212019_1_ -> !Objects.equals(p_212019_1_, p_remove_2_)).forEach(p_212010_2_ -> compoundnbt1.n_1700_B((String)p_212010_2_, compoundnbt.R_4764_Y((String)p_212010_2_)));
            return compoundnbt1;
        }
        return p_remove_1_;
    }

    public String toString() {
        return "NBT";
    }

    public RecordBuilder<Tag> mapBuilder() {
        return new n_1700_B(this);
    }

    public /* synthetic */ Object remove(Object object, String string) {
        return this.n_1700_B((Tag)object, string);
    }

    public /* synthetic */ Object createLongList(LongStream longStream) {
        return this.n_1700_B(longStream);
    }

    public /* synthetic */ DataResult getLongStream(Object object) {
        return this.s_956_w((Tag)object);
    }

    public /* synthetic */ Object createIntList(IntStream intStream) {
        return this.n_1700_B(intStream);
    }

    public /* synthetic */ DataResult getIntStream(Object object) {
        return this.t_148_a((Tag)object);
    }

    public /* synthetic */ Object createByteList(ByteBuffer byteBuffer) {
        return this.n_1700_B(byteBuffer);
    }

    public /* synthetic */ DataResult getByteBuffer(Object object) {
        return this.w_1484_f((Tag)object);
    }

    public /* synthetic */ Object createList(Stream stream) {
        return this.J_1907_R(stream);
    }

    public /* synthetic */ DataResult getList(Object object) {
        return this.v_4262_N((Tag)object);
    }

    public /* synthetic */ DataResult getStream(Object object) {
        return this.u_1723_Y((Tag)object);
    }

    public /* synthetic */ DataResult getMap(Object object) {
        return this.P_1922_E((Tag)object);
    }

    public /* synthetic */ Object createMap(Stream stream) {
        return this.n_1700_B(stream);
    }

    public /* synthetic */ DataResult getMapEntries(Object object) {
        return this.G_564_y((Tag)object);
    }

    public /* synthetic */ DataResult getMapValues(Object object) {
        return this.R_4764_Y((Tag)object);
    }

    public /* synthetic */ DataResult mergeToMap(Object object, MapLike mapLike) {
        return this.n_1700_B((Tag)object, (MapLike<Tag>)mapLike);
    }

    public /* synthetic */ DataResult mergeToMap(Object object, Object object2, Object object3) {
        return this.n_1700_B((Tag)object, (Tag)object2, (Tag)object3);
    }

    public /* synthetic */ DataResult mergeToList(Object object, List list) {
        return this.n_1700_B((Tag)object, list);
    }

    public /* synthetic */ DataResult mergeToList(Object object, Object object2) {
        return this.n_1700_B((Tag)object, (Tag)object2);
    }

    public /* synthetic */ Object createString(String string) {
        return this.n_1700_B(string);
    }

    public /* synthetic */ DataResult getStringValue(Object object) {
        return this.J_1907_R((Tag)object);
    }

    public /* synthetic */ Object createBoolean(boolean bl) {
        return this.n_1700_B(bl);
    }

    public /* synthetic */ Object createDouble(double d) {
        return this.n_1700_B(d);
    }

    public /* synthetic */ Object createFloat(float f) {
        return this.n_1700_B(f);
    }

    public /* synthetic */ Object createLong(long l) {
        return this.n_1700_B(l);
    }

    public /* synthetic */ Object createInt(int n) {
        return this.n_1700_B(n);
    }

    public /* synthetic */ Object createShort(short s) {
        return this.n_1700_B(s);
    }

    public /* synthetic */ Object createByte(byte by) {
        return this.n_1700_B(by);
    }

    public /* synthetic */ Object createNumeric(Number number) {
        return this.n_1700_B(number);
    }

    public /* synthetic */ DataResult getNumberValue(Object object) {
        return this.n_1700_B((Tag)object);
    }

    public /* synthetic */ Object convertTo(DynamicOps dynamicOps, Object object) {
        return this.n_1700_B(dynamicOps, (Tag)object);
    }

    public /* synthetic */ Object empty() {
        return this.n_1700_B();
    }

    class n_1700_B
    extends RecordBuilder.AbstractStringBuilder<Tag, U_2912_j> {
        protected n_1700_B(l_4118_l this$0) {
            super((DynamicOps)this$0);
        }

        protected U_2912_j n_1700_B() {
            return new U_2912_j();
        }

        protected U_2912_j n_1700_B(String p_append_1_, Tag p_append_2_, U_2912_j p_append_3_) {
            p_append_3_.n_1700_B(p_append_1_, p_append_2_);
            return p_append_3_;
        }

        protected DataResult<Tag> n_1700_B(U_2912_j p_build_1_, Tag p_build_2_) {
            if (p_build_2_ != null && p_build_2_ != EndTag.J_1907_R) {
                if (!(p_build_2_ instanceof U_2912_j)) {
                    return DataResult.error((String)("mergeToMap called with not a map: " + String.valueOf(p_build_2_)), (Object)p_build_2_);
                }
                U_2912_j compoundnbt = new U_2912_j(Maps.newHashMap(((U_2912_j)p_build_2_).w_1484_f()));
                for (Map.Entry<String, Tag> entry : p_build_1_.w_1484_f().entrySet()) {
                    compoundnbt.n_1700_B(entry.getKey(), entry.getValue());
                }
                return DataResult.success((Object)compoundnbt);
            }
            return DataResult.success((Object)p_build_1_);
        }

        protected /* synthetic */ Object append(String string, Object object, Object object2) {
            return this.n_1700_B(string, (Tag)object, (U_2912_j)object2);
        }

        protected /* synthetic */ DataResult build(Object object, Object object2) {
            return this.n_1700_B((U_2912_j)object, (Tag)object2);
        }

        protected /* synthetic */ Object initBuilder() {
            return this.n_1700_B();
        }
    }
}


