/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DataResult$PartialResult
 *  com.mojang.serialization.DynamicOps
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufAllocator
 *  io.netty.buffer.ByteBufInputStream
 *  io.netty.buffer.ByteBufOutputStream
 *  io.netty.handler.codec.DecoderException
 *  io.netty.handler.codec.EncoderException
 *  io.netty.util.ByteProcessor
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import io.netty.util.ByteProcessor;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.BlockHitResult;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.l_4118_l;
import lightning.product.o_926_S;
import lightning.product.q_1613_l;
import lightning.product.r_1827_u;
import lightning.product.x_282_a;
import net.minecraftforge.common.extensions.IForgePacketBuffer;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;

public class b_2585_i
extends ByteBuf
implements IForgePacketBuffer {
    private final ByteBuf n_1700_B;
    private Map<String, Object> J_1907_R;

    public b_2585_i(ByteBuf wrapped) {
        this.n_1700_B = wrapped;
    }

    public b_2585_i(ByteBuf p_i242108_1_, Map<String, Object> p_i242108_2_) {
        this.n_1700_B = p_i242108_1_;
        this.J_1907_R = p_i242108_2_;
    }

    public static int n_1700_B(int input) {
        for (int i = 1; i < 5; ++i) {
            if ((input & -1 << i * 7) != 0) continue;
            return i;
        }
        return 5;
    }

    public <T> T n_1700_B(Codec<T> p_240628_1_) throws IOException {
        U_2912_j compoundnbt = this.s_956_w();
        DataResult dataresult = p_240628_1_.parse((DynamicOps)l_4118_l.n_1700_B, (Object)compoundnbt);
        if (dataresult.error().isPresent()) {
            throw new IOException("Failed to decode: " + ((DataResult.PartialResult)dataresult.error().get()).message() + " " + String.valueOf(compoundnbt));
        }
        return dataresult.result().get();
    }

    public <T> void n_1700_B(Codec<T> p_240629_1_, T p_240629_2_) throws IOException {
        DataResult dataresult = p_240629_1_.encodeStart((DynamicOps)l_4118_l.n_1700_B, p_240629_2_);
        if (dataresult.error().isPresent()) {
            throw new IOException("Failed to encode: " + ((DataResult.PartialResult)dataresult.error().get()).message() + " " + String.valueOf(p_240629_2_));
        }
        this.n_1700_B((U_2912_j)dataresult.result().get());
    }

    public b_2585_i n_1700_B(byte[] array) {
        this.G_564_y(array.length);
        this.writeBytes(array);
        return this;
    }

    public byte[] n_1700_B() {
        return this.J_1907_R(this.readableBytes());
    }

    public byte[] J_1907_R(int maxLength) {
        int i = this.u_1723_Y();
        if (i > maxLength) {
            throw new DecoderException("ByteArray with size " + i + " is bigger than allowed " + maxLength);
        }
        byte[] abyte = new byte[i];
        this.readBytes(abyte);
        return abyte;
    }

    public b_2585_i n_1700_B(int[] array) {
        this.G_564_y(array.length);
        for (int i : array) {
            this.G_564_y(i);
        }
        return this;
    }

    public int[] J_1907_R() {
        return this.R_4764_Y(this.readableBytes());
    }

    public int[] R_4764_Y(int maxLength) {
        int i = this.u_1723_Y();
        if (i > maxLength) {
            throw new DecoderException("VarIntArray with size " + i + " is bigger than allowed " + maxLength);
        }
        int[] aint = new int[i];
        for (int j = 0; j < aint.length; ++j) {
            aint[j] = this.u_1723_Y();
        }
        return aint;
    }

    public b_2585_i n_1700_B(long[] array) {
        this.G_564_y(array.length);
        for (long i : array) {
            this.writeLong(i);
        }
        return this;
    }

    public long[] J_1907_R(@Nullable long[] array) {
        return this.n_1700_B(array, this.readableBytes() / 8);
    }

    public long[] n_1700_B(@Nullable long[] array, int maxLength) {
        int i = this.u_1723_Y();
        if (array == null || array.length != i) {
            if (i > maxLength) {
                throw new DecoderException("LongArray with size " + i + " is bigger than allowed " + maxLength);
            }
            array = new long[i];
        }
        for (int j = 0; j < array.length; ++j) {
            array[j] = this.readLong();
        }
        return array;
    }

    public c_1514_x R_4764_Y() {
        return c_1514_x.fromLong(this.readLong());
    }

    public b_2585_i n_1700_B(c_1514_x pos) {
        this.writeLong(pos.toLong());
        return this;
    }

    public SectionPos G_564_y() {
        return SectionPos.n_1700_B(this.readLong());
    }

    public x_282_a P_1922_E() {
        return x_282_a.n_1700_B.n_1700_B(this.P_1922_E(262144));
    }

    public b_2585_i n_1700_B(x_282_a component) {
        return this.n_1700_B(x_282_a.n_1700_B.n_1700_B(component), 262144);
    }

    public <T extends Enum<T>> T n_1700_B(Class<T> enumClass) {
        return (T)((Enum[])enumClass.getEnumConstants())[this.u_1723_Y()];
    }

    public b_2585_i n_1700_B(Enum<?> value) {
        return this.G_564_y(value.ordinal());
    }

    public int u_1723_Y() {
        byte b0;
        int i = 0;
        int j = 0;
        do {
            b0 = this.readByte();
            i |= (b0 & 0x7F) << j++ * 7;
            if (j <= 5) continue;
            throw new RuntimeException("VarInt too big");
        } while ((b0 & 0x80) == 128);
        return i;
    }

    public long v_4262_N() {
        byte b0;
        long i = 0L;
        int j = 0;
        do {
            b0 = this.readByte();
            i |= (long)(b0 & 0x7F) << j++ * 7;
            if (j <= 10) continue;
            throw new RuntimeException("VarLong too big");
        } while ((b0 & 0x80) == 128);
        return i;
    }

    public b_2585_i n_1700_B(UUID uuid) {
        this.writeLong(uuid.getMostSignificantBits());
        this.writeLong(uuid.getLeastSignificantBits());
        return this;
    }

    public UUID w_1484_f() {
        return new UUID(this.readLong(), this.readLong());
    }

    public b_2585_i G_564_y(int input) {
        while ((input & 0xFFFFFF80) != 0) {
            this.writeByte(input & 0x7F | 0x80);
            input >>>= 7;
        }
        this.writeByte(input);
        return this;
    }

    public b_2585_i n_1700_B(long value) {
        while ((value & 0xFFFFFFFFFFFFFF80L) != 0L) {
            this.writeByte((int)(value & 0x7FL) | 0x80);
            value >>>= 7;
        }
        this.writeByte((int)value);
        return this;
    }

    public b_2585_i n_1700_B(@Nullable U_2912_j nbt) {
        if (nbt == null) {
            this.writeByte(0);
        } else {
            try {
                r_1827_u.n_1700_B(nbt, (DataOutput)new ByteBufOutputStream((ByteBuf)this));
            }
            catch (IOException ioexception) {
                throw new EncoderException((Throwable)ioexception);
            }
        }
        return this;
    }

    @Nullable
    public U_2912_j t_148_a() {
        return this.n_1700_B(new o_926_S(0x200000L));
    }

    @Nullable
    public U_2912_j s_956_w() {
        return this.n_1700_B(o_926_S.n_1700_B);
    }

    @Nullable
    public U_2912_j n_1700_B(o_926_S p_244272_1_) {
        int i = this.readerIndex();
        byte b0 = this.readByte();
        if (b0 == 0) {
            return null;
        }
        this.readerIndex(i);
        try {
            return r_1827_u.n_1700_B((DataInput)new ByteBufInputStream((ByteBuf)this), p_244272_1_);
        }
        catch (IOException ioexception) {
            throw new EncoderException((Throwable)ioexception);
        }
    }

    public b_2585_i n_1700_B(Z_1993_T stack) {
        return this.n_1700_B(stack, true);
    }

    public b_2585_i n_1700_B(Z_1993_T p_writeItemStack_1_, boolean p_writeItemStack_2_) {
        if (p_writeItemStack_1_.n_1700_B()) {
            this.writeBoolean(false);
        } else {
            this.writeBoolean(true);
            q_1613_l item = p_writeItemStack_1_.J_1907_R();
            this.G_564_y(q_1613_l.n_1700_B(item));
            this.writeByte(p_writeItemStack_1_.t_4043_B());
            U_2912_j compoundnbt = null;
            if (ReflectorForge.isDamageable(item, p_writeItemStack_1_) || item.M_182_A()) {
                compoundnbt = p_writeItemStack_2_ && Reflector.IForgeItemStack_getShareTag.exists() ? (U_2912_j)Reflector.call(p_writeItemStack_1_, Reflector.IForgeItemStack_getShareTag, new Object[0]) : p_writeItemStack_1_.Q_4569_t();
            }
            this.n_1700_B(compoundnbt);
        }
        return this;
    }

    public Z_1993_T u_2550_I() {
        if (!this.readBoolean()) {
            return Z_1993_T.J_1907_R;
        }
        int i = this.u_1723_Y();
        byte j = this.readByte();
        Z_1993_T itemstack = new Z_1993_T(q_1613_l.J_1907_R(i), j);
        if (Reflector.IForgeItemStack_readShareTag.exists()) {
            Reflector.call(itemstack, Reflector.IForgeItemStack_readShareTag, this.t_148_a());
        } else {
            itemstack.R_4764_Y(this.t_148_a());
        }
        return itemstack;
    }

    public String M_588_G() {
        return this.P_1922_E(Short.MAX_VALUE);
    }

    public String P_1922_E(int maxLength) {
        int i = this.u_1723_Y();
        if (i > maxLength * 4) {
            throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + i + " > " + maxLength * 4 + ")");
        }
        if (i < 0) {
            throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
        }
        String s = this.toString(this.readerIndex(), i, StandardCharsets.UTF_8);
        this.readerIndex(this.readerIndex() + i);
        if (s.length() > maxLength) {
            throw new DecoderException("The received string length is longer than maximum allowed (" + i + " > " + maxLength + ")");
        }
        return s;
    }

    public b_2585_i n_1700_B(String string) {
        return this.n_1700_B(string, Short.MAX_VALUE);
    }

    public b_2585_i n_1700_B(String string, int maxLength) {
        byte[] abyte = string.getBytes(StandardCharsets.UTF_8);
        if (abyte.length > maxLength) {
            throw new EncoderException("String too big (was " + abyte.length + " bytes encoded, max " + maxLength + ")");
        }
        this.G_564_y(abyte.length);
        this.writeBytes(abyte);
        return this;
    }

    public g_2336_b P_4830_p() {
        return new g_2336_b(this.P_1922_E(Short.MAX_VALUE));
    }

    public b_2585_i n_1700_B(g_2336_b resourceLocationIn) {
        this.n_1700_B(resourceLocationIn.toString());
        return this;
    }

    public Date h_1847_R() {
        return new Date(this.readLong());
    }

    public b_2585_i n_1700_B(Date time) {
        this.writeLong(time.getTime());
        return this;
    }

    public BlockHitResult Q_4569_t() {
        c_1514_x blockpos = this.R_4764_Y();
        b_257_Y direction = this.n_1700_B(b_257_Y.class);
        float f = this.readFloat();
        float f1 = this.readFloat();
        float f2 = this.readFloat();
        boolean flag = this.readBoolean();
        return new BlockHitResult(new e_2866_D((double)blockpos.getX() + (double)f, (double)blockpos.getY() + (double)f1, (double)blockpos.getZ() + (double)f2), direction, blockpos, flag);
    }

    public void n_1700_B(BlockHitResult resultIn) {
        c_1514_x blockpos = resultIn.n_1700_B();
        this.n_1700_B(blockpos);
        this.n_1700_B(resultIn.J_1907_R());
        e_2866_D vector3d = resultIn.P_1922_E();
        this.writeFloat((float)(vector3d.J_1907_R - (double)blockpos.getX()));
        this.writeFloat((float)(vector3d.R_4764_Y - (double)blockpos.getY()));
        this.writeFloat((float)(vector3d.G_564_y - (double)blockpos.getZ()));
        this.writeBoolean(resultIn.G_564_y());
    }

    public int capacity() {
        return this.n_1700_B.capacity();
    }

    public ByteBuf capacity(int p_capacity_1_) {
        return this.n_1700_B.capacity(p_capacity_1_);
    }

    public int maxCapacity() {
        return this.n_1700_B.maxCapacity();
    }

    public ByteBufAllocator alloc() {
        return this.n_1700_B.alloc();
    }

    public ByteOrder order() {
        return this.n_1700_B.order();
    }

    public ByteBuf order(ByteOrder p_order_1_) {
        return this.n_1700_B.order(p_order_1_);
    }

    public ByteBuf unwrap() {
        return this.n_1700_B.unwrap();
    }

    public boolean isDirect() {
        return this.n_1700_B.isDirect();
    }

    public boolean isReadOnly() {
        return this.n_1700_B.isReadOnly();
    }

    public ByteBuf asReadOnly() {
        return this.n_1700_B.asReadOnly();
    }

    public int readerIndex() {
        return this.n_1700_B.readerIndex();
    }

    public ByteBuf readerIndex(int p_readerIndex_1_) {
        return this.n_1700_B.readerIndex(p_readerIndex_1_);
    }

    public int writerIndex() {
        return this.n_1700_B.writerIndex();
    }

    public ByteBuf writerIndex(int p_writerIndex_1_) {
        return this.n_1700_B.writerIndex(p_writerIndex_1_);
    }

    public ByteBuf setIndex(int p_setIndex_1_, int p_setIndex_2_) {
        return this.n_1700_B.setIndex(p_setIndex_1_, p_setIndex_2_);
    }

    public int readableBytes() {
        return this.n_1700_B.readableBytes();
    }

    public int writableBytes() {
        return this.n_1700_B.writableBytes();
    }

    public int maxWritableBytes() {
        return this.n_1700_B.maxWritableBytes();
    }

    public boolean isReadable() {
        return this.n_1700_B.isReadable();
    }

    public boolean isReadable(int p_isReadable_1_) {
        return this.n_1700_B.isReadable(p_isReadable_1_);
    }

    public boolean isWritable() {
        return this.n_1700_B.isWritable();
    }

    public boolean isWritable(int p_isWritable_1_) {
        return this.n_1700_B.isWritable(p_isWritable_1_);
    }

    public ByteBuf clear() {
        return this.n_1700_B.clear();
    }

    public ByteBuf markReaderIndex() {
        return this.n_1700_B.markReaderIndex();
    }

    public ByteBuf resetReaderIndex() {
        return this.n_1700_B.resetReaderIndex();
    }

    public ByteBuf markWriterIndex() {
        return this.n_1700_B.markWriterIndex();
    }

    public ByteBuf resetWriterIndex() {
        return this.n_1700_B.resetWriterIndex();
    }

    public ByteBuf discardReadBytes() {
        return this.n_1700_B.discardReadBytes();
    }

    public ByteBuf discardSomeReadBytes() {
        return this.n_1700_B.discardSomeReadBytes();
    }

    public ByteBuf ensureWritable(int p_ensureWritable_1_) {
        return this.n_1700_B.ensureWritable(p_ensureWritable_1_);
    }

    public int ensureWritable(int p_ensureWritable_1_, boolean p_ensureWritable_2_) {
        return this.n_1700_B.ensureWritable(p_ensureWritable_1_, p_ensureWritable_2_);
    }

    public boolean getBoolean(int p_getBoolean_1_) {
        return this.n_1700_B.getBoolean(p_getBoolean_1_);
    }

    public byte getByte(int p_getByte_1_) {
        return this.n_1700_B.getByte(p_getByte_1_);
    }

    public short getUnsignedByte(int p_getUnsignedByte_1_) {
        return this.n_1700_B.getUnsignedByte(p_getUnsignedByte_1_);
    }

    public short getShort(int p_getShort_1_) {
        return this.n_1700_B.getShort(p_getShort_1_);
    }

    public short getShortLE(int p_getShortLE_1_) {
        return this.n_1700_B.getShortLE(p_getShortLE_1_);
    }

    public int getUnsignedShort(int p_getUnsignedShort_1_) {
        return this.n_1700_B.getUnsignedShort(p_getUnsignedShort_1_);
    }

    public int getUnsignedShortLE(int p_getUnsignedShortLE_1_) {
        return this.n_1700_B.getUnsignedShortLE(p_getUnsignedShortLE_1_);
    }

    public int getMedium(int p_getMedium_1_) {
        return this.n_1700_B.getMedium(p_getMedium_1_);
    }

    public int getMediumLE(int p_getMediumLE_1_) {
        return this.n_1700_B.getMediumLE(p_getMediumLE_1_);
    }

    public int getUnsignedMedium(int p_getUnsignedMedium_1_) {
        return this.n_1700_B.getUnsignedMedium(p_getUnsignedMedium_1_);
    }

    public int getUnsignedMediumLE(int p_getUnsignedMediumLE_1_) {
        return this.n_1700_B.getUnsignedMediumLE(p_getUnsignedMediumLE_1_);
    }

    public int getInt(int p_getInt_1_) {
        return this.n_1700_B.getInt(p_getInt_1_);
    }

    public int getIntLE(int p_getIntLE_1_) {
        return this.n_1700_B.getIntLE(p_getIntLE_1_);
    }

    public long getUnsignedInt(int p_getUnsignedInt_1_) {
        return this.n_1700_B.getUnsignedInt(p_getUnsignedInt_1_);
    }

    public long getUnsignedIntLE(int p_getUnsignedIntLE_1_) {
        return this.n_1700_B.getUnsignedIntLE(p_getUnsignedIntLE_1_);
    }

    public long getLong(int p_getLong_1_) {
        return this.n_1700_B.getLong(p_getLong_1_);
    }

    public long getLongLE(int p_getLongLE_1_) {
        return this.n_1700_B.getLongLE(p_getLongLE_1_);
    }

    public char getChar(int p_getChar_1_) {
        return this.n_1700_B.getChar(p_getChar_1_);
    }

    public float getFloat(int p_getFloat_1_) {
        return this.n_1700_B.getFloat(p_getFloat_1_);
    }

    public double getDouble(int p_getDouble_1_) {
        return this.n_1700_B.getDouble(p_getDouble_1_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, ByteBuf p_getBytes_2_) {
        return this.n_1700_B.getBytes(p_getBytes_1_, p_getBytes_2_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, ByteBuf p_getBytes_2_, int p_getBytes_3_) {
        return this.n_1700_B.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, ByteBuf p_getBytes_2_, int p_getBytes_3_, int p_getBytes_4_) {
        return this.n_1700_B.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_, p_getBytes_4_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, byte[] p_getBytes_2_) {
        return this.n_1700_B.getBytes(p_getBytes_1_, p_getBytes_2_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, byte[] p_getBytes_2_, int p_getBytes_3_, int p_getBytes_4_) {
        return this.n_1700_B.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_, p_getBytes_4_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, ByteBuffer p_getBytes_2_) {
        return this.n_1700_B.getBytes(p_getBytes_1_, p_getBytes_2_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, OutputStream p_getBytes_2_, int p_getBytes_3_) throws IOException {
        return this.n_1700_B.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_);
    }

    public int getBytes(int p_getBytes_1_, GatheringByteChannel p_getBytes_2_, int p_getBytes_3_) throws IOException {
        return this.n_1700_B.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_);
    }

    public int getBytes(int p_getBytes_1_, FileChannel p_getBytes_2_, long p_getBytes_3_, int p_getBytes_5_) throws IOException {
        return this.n_1700_B.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_, p_getBytes_5_);
    }

    public CharSequence getCharSequence(int p_getCharSequence_1_, int p_getCharSequence_2_, Charset p_getCharSequence_3_) {
        return this.n_1700_B.getCharSequence(p_getCharSequence_1_, p_getCharSequence_2_, p_getCharSequence_3_);
    }

    public ByteBuf setBoolean(int p_setBoolean_1_, boolean p_setBoolean_2_) {
        return this.n_1700_B.setBoolean(p_setBoolean_1_, p_setBoolean_2_);
    }

    public ByteBuf setByte(int p_setByte_1_, int p_setByte_2_) {
        return this.n_1700_B.setByte(p_setByte_1_, p_setByte_2_);
    }

    public ByteBuf setShort(int p_setShort_1_, int p_setShort_2_) {
        return this.n_1700_B.setShort(p_setShort_1_, p_setShort_2_);
    }

    public ByteBuf setShortLE(int p_setShortLE_1_, int p_setShortLE_2_) {
        return this.n_1700_B.setShortLE(p_setShortLE_1_, p_setShortLE_2_);
    }

    public ByteBuf setMedium(int p_setMedium_1_, int p_setMedium_2_) {
        return this.n_1700_B.setMedium(p_setMedium_1_, p_setMedium_2_);
    }

    public ByteBuf setMediumLE(int p_setMediumLE_1_, int p_setMediumLE_2_) {
        return this.n_1700_B.setMediumLE(p_setMediumLE_1_, p_setMediumLE_2_);
    }

    public ByteBuf setInt(int p_setInt_1_, int p_setInt_2_) {
        return this.n_1700_B.setInt(p_setInt_1_, p_setInt_2_);
    }

    public ByteBuf setIntLE(int p_setIntLE_1_, int p_setIntLE_2_) {
        return this.n_1700_B.setIntLE(p_setIntLE_1_, p_setIntLE_2_);
    }

    public ByteBuf setLong(int p_setLong_1_, long p_setLong_2_) {
        return this.n_1700_B.setLong(p_setLong_1_, p_setLong_2_);
    }

    public ByteBuf setLongLE(int p_setLongLE_1_, long p_setLongLE_2_) {
        return this.n_1700_B.setLongLE(p_setLongLE_1_, p_setLongLE_2_);
    }

    public ByteBuf setChar(int p_setChar_1_, int p_setChar_2_) {
        return this.n_1700_B.setChar(p_setChar_1_, p_setChar_2_);
    }

    public ByteBuf setFloat(int p_setFloat_1_, float p_setFloat_2_) {
        return this.n_1700_B.setFloat(p_setFloat_1_, p_setFloat_2_);
    }

    public ByteBuf setDouble(int p_setDouble_1_, double p_setDouble_2_) {
        return this.n_1700_B.setDouble(p_setDouble_1_, p_setDouble_2_);
    }

    public ByteBuf setBytes(int p_setBytes_1_, ByteBuf p_setBytes_2_) {
        return this.n_1700_B.setBytes(p_setBytes_1_, p_setBytes_2_);
    }

    public ByteBuf setBytes(int p_setBytes_1_, ByteBuf p_setBytes_2_, int p_setBytes_3_) {
        return this.n_1700_B.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_);
    }

    public ByteBuf setBytes(int p_setBytes_1_, ByteBuf p_setBytes_2_, int p_setBytes_3_, int p_setBytes_4_) {
        return this.n_1700_B.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_, p_setBytes_4_);
    }

    public ByteBuf setBytes(int p_setBytes_1_, byte[] p_setBytes_2_) {
        return this.n_1700_B.setBytes(p_setBytes_1_, p_setBytes_2_);
    }

    public ByteBuf setBytes(int p_setBytes_1_, byte[] p_setBytes_2_, int p_setBytes_3_, int p_setBytes_4_) {
        return this.n_1700_B.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_, p_setBytes_4_);
    }

    public ByteBuf setBytes(int p_setBytes_1_, ByteBuffer p_setBytes_2_) {
        return this.n_1700_B.setBytes(p_setBytes_1_, p_setBytes_2_);
    }

    public int setBytes(int p_setBytes_1_, InputStream p_setBytes_2_, int p_setBytes_3_) throws IOException {
        return this.n_1700_B.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_);
    }

    public int setBytes(int p_setBytes_1_, ScatteringByteChannel p_setBytes_2_, int p_setBytes_3_) throws IOException {
        return this.n_1700_B.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_);
    }

    public int setBytes(int p_setBytes_1_, FileChannel p_setBytes_2_, long p_setBytes_3_, int p_setBytes_5_) throws IOException {
        return this.n_1700_B.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_, p_setBytes_5_);
    }

    public ByteBuf setZero(int p_setZero_1_, int p_setZero_2_) {
        return this.n_1700_B.setZero(p_setZero_1_, p_setZero_2_);
    }

    public int setCharSequence(int p_setCharSequence_1_, CharSequence p_setCharSequence_2_, Charset p_setCharSequence_3_) {
        return this.n_1700_B.setCharSequence(p_setCharSequence_1_, p_setCharSequence_2_, p_setCharSequence_3_);
    }

    public boolean readBoolean() {
        return this.n_1700_B.readBoolean();
    }

    public byte readByte() {
        return this.n_1700_B.readByte();
    }

    public short readUnsignedByte() {
        return this.n_1700_B.readUnsignedByte();
    }

    public short readShort() {
        return this.n_1700_B.readShort();
    }

    public short readShortLE() {
        return this.n_1700_B.readShortLE();
    }

    public int readUnsignedShort() {
        return this.n_1700_B.readUnsignedShort();
    }

    public int readUnsignedShortLE() {
        return this.n_1700_B.readUnsignedShortLE();
    }

    public int readMedium() {
        return this.n_1700_B.readMedium();
    }

    public int readMediumLE() {
        return this.n_1700_B.readMediumLE();
    }

    public int readUnsignedMedium() {
        return this.n_1700_B.readUnsignedMedium();
    }

    public int readUnsignedMediumLE() {
        return this.n_1700_B.readUnsignedMediumLE();
    }

    public int readInt() {
        return this.n_1700_B.readInt();
    }

    public int readIntLE() {
        return this.n_1700_B.readIntLE();
    }

    public long readUnsignedInt() {
        return this.n_1700_B.readUnsignedInt();
    }

    public long readUnsignedIntLE() {
        return this.n_1700_B.readUnsignedIntLE();
    }

    public long readLong() {
        return this.n_1700_B.readLong();
    }

    public long readLongLE() {
        return this.n_1700_B.readLongLE();
    }

    public char readChar() {
        return this.n_1700_B.readChar();
    }

    public float readFloat() {
        return this.n_1700_B.readFloat();
    }

    public double readDouble() {
        return this.n_1700_B.readDouble();
    }

    public ByteBuf readBytes(int p_readBytes_1_) {
        return this.n_1700_B.readBytes(p_readBytes_1_);
    }

    public ByteBuf readSlice(int p_readSlice_1_) {
        return this.n_1700_B.readSlice(p_readSlice_1_);
    }

    public ByteBuf readRetainedSlice(int p_readRetainedSlice_1_) {
        return this.n_1700_B.readRetainedSlice(p_readRetainedSlice_1_);
    }

    public ByteBuf readBytes(ByteBuf p_readBytes_1_) {
        return this.n_1700_B.readBytes(p_readBytes_1_);
    }

    public ByteBuf readBytes(ByteBuf p_readBytes_1_, int p_readBytes_2_) {
        return this.n_1700_B.readBytes(p_readBytes_1_, p_readBytes_2_);
    }

    public ByteBuf readBytes(ByteBuf p_readBytes_1_, int p_readBytes_2_, int p_readBytes_3_) {
        return this.n_1700_B.readBytes(p_readBytes_1_, p_readBytes_2_, p_readBytes_3_);
    }

    public ByteBuf readBytes(byte[] p_readBytes_1_) {
        return this.n_1700_B.readBytes(p_readBytes_1_);
    }

    public ByteBuf readBytes(byte[] p_readBytes_1_, int p_readBytes_2_, int p_readBytes_3_) {
        return this.n_1700_B.readBytes(p_readBytes_1_, p_readBytes_2_, p_readBytes_3_);
    }

    public ByteBuf readBytes(ByteBuffer p_readBytes_1_) {
        return this.n_1700_B.readBytes(p_readBytes_1_);
    }

    public ByteBuf readBytes(OutputStream p_readBytes_1_, int p_readBytes_2_) throws IOException {
        return this.n_1700_B.readBytes(p_readBytes_1_, p_readBytes_2_);
    }

    public int readBytes(GatheringByteChannel p_readBytes_1_, int p_readBytes_2_) throws IOException {
        return this.n_1700_B.readBytes(p_readBytes_1_, p_readBytes_2_);
    }

    public CharSequence readCharSequence(int p_readCharSequence_1_, Charset p_readCharSequence_2_) {
        return this.n_1700_B.readCharSequence(p_readCharSequence_1_, p_readCharSequence_2_);
    }

    public int readBytes(FileChannel p_readBytes_1_, long p_readBytes_2_, int p_readBytes_4_) throws IOException {
        return this.n_1700_B.readBytes(p_readBytes_1_, p_readBytes_2_, p_readBytes_4_);
    }

    public ByteBuf skipBytes(int p_skipBytes_1_) {
        return this.n_1700_B.skipBytes(p_skipBytes_1_);
    }

    public ByteBuf writeBoolean(boolean p_writeBoolean_1_) {
        return this.n_1700_B.writeBoolean(p_writeBoolean_1_);
    }

    public ByteBuf writeByte(int p_writeByte_1_) {
        return this.n_1700_B.writeByte(p_writeByte_1_);
    }

    public ByteBuf writeShort(int p_writeShort_1_) {
        return this.n_1700_B.writeShort(p_writeShort_1_);
    }

    public ByteBuf writeShortLE(int p_writeShortLE_1_) {
        return this.n_1700_B.writeShortLE(p_writeShortLE_1_);
    }

    public ByteBuf writeMedium(int p_writeMedium_1_) {
        return this.n_1700_B.writeMedium(p_writeMedium_1_);
    }

    public ByteBuf writeMediumLE(int p_writeMediumLE_1_) {
        return this.n_1700_B.writeMediumLE(p_writeMediumLE_1_);
    }

    public ByteBuf writeInt(int p_writeInt_1_) {
        return this.n_1700_B.writeInt(p_writeInt_1_);
    }

    public ByteBuf writeIntLE(int p_writeIntLE_1_) {
        return this.n_1700_B.writeIntLE(p_writeIntLE_1_);
    }

    public ByteBuf writeLong(long p_writeLong_1_) {
        return this.n_1700_B.writeLong(p_writeLong_1_);
    }

    public ByteBuf writeLongLE(long p_writeLongLE_1_) {
        return this.n_1700_B.writeLongLE(p_writeLongLE_1_);
    }

    public ByteBuf writeChar(int p_writeChar_1_) {
        return this.n_1700_B.writeChar(p_writeChar_1_);
    }

    public ByteBuf writeFloat(float p_writeFloat_1_) {
        return this.n_1700_B.writeFloat(p_writeFloat_1_);
    }

    public ByteBuf writeDouble(double p_writeDouble_1_) {
        return this.n_1700_B.writeDouble(p_writeDouble_1_);
    }

    public ByteBuf writeBytes(ByteBuf p_writeBytes_1_) {
        return this.n_1700_B.writeBytes(p_writeBytes_1_);
    }

    public ByteBuf writeBytes(ByteBuf p_writeBytes_1_, int p_writeBytes_2_) {
        return this.n_1700_B.writeBytes(p_writeBytes_1_, p_writeBytes_2_);
    }

    public ByteBuf writeBytes(ByteBuf p_writeBytes_1_, int p_writeBytes_2_, int p_writeBytes_3_) {
        return this.n_1700_B.writeBytes(p_writeBytes_1_, p_writeBytes_2_, p_writeBytes_3_);
    }

    public ByteBuf writeBytes(byte[] p_writeBytes_1_) {
        return this.n_1700_B.writeBytes(p_writeBytes_1_);
    }

    public ByteBuf writeBytes(byte[] p_writeBytes_1_, int p_writeBytes_2_, int p_writeBytes_3_) {
        return this.n_1700_B.writeBytes(p_writeBytes_1_, p_writeBytes_2_, p_writeBytes_3_);
    }

    public ByteBuf writeBytes(ByteBuffer p_writeBytes_1_) {
        return this.n_1700_B.writeBytes(p_writeBytes_1_);
    }

    public int writeBytes(InputStream p_writeBytes_1_, int p_writeBytes_2_) throws IOException {
        return this.n_1700_B.writeBytes(p_writeBytes_1_, p_writeBytes_2_);
    }

    public int writeBytes(ScatteringByteChannel p_writeBytes_1_, int p_writeBytes_2_) throws IOException {
        return this.n_1700_B.writeBytes(p_writeBytes_1_, p_writeBytes_2_);
    }

    public int writeBytes(FileChannel p_writeBytes_1_, long p_writeBytes_2_, int p_writeBytes_4_) throws IOException {
        return this.n_1700_B.writeBytes(p_writeBytes_1_, p_writeBytes_2_, p_writeBytes_4_);
    }

    public ByteBuf writeZero(int p_writeZero_1_) {
        return this.n_1700_B.writeZero(p_writeZero_1_);
    }

    public int writeCharSequence(CharSequence p_writeCharSequence_1_, Charset p_writeCharSequence_2_) {
        return this.n_1700_B.writeCharSequence(p_writeCharSequence_1_, p_writeCharSequence_2_);
    }

    public int indexOf(int p_indexOf_1_, int p_indexOf_2_, byte p_indexOf_3_) {
        return this.n_1700_B.indexOf(p_indexOf_1_, p_indexOf_2_, p_indexOf_3_);
    }

    public int bytesBefore(byte p_bytesBefore_1_) {
        return this.n_1700_B.bytesBefore(p_bytesBefore_1_);
    }

    public int bytesBefore(int p_bytesBefore_1_, byte p_bytesBefore_2_) {
        return this.n_1700_B.bytesBefore(p_bytesBefore_1_, p_bytesBefore_2_);
    }

    public int bytesBefore(int p_bytesBefore_1_, int p_bytesBefore_2_, byte p_bytesBefore_3_) {
        return this.n_1700_B.bytesBefore(p_bytesBefore_1_, p_bytesBefore_2_, p_bytesBefore_3_);
    }

    public int forEachByte(ByteProcessor p_forEachByte_1_) {
        return this.n_1700_B.forEachByte(p_forEachByte_1_);
    }

    public int forEachByte(int p_forEachByte_1_, int p_forEachByte_2_, ByteProcessor p_forEachByte_3_) {
        return this.n_1700_B.forEachByte(p_forEachByte_1_, p_forEachByte_2_, p_forEachByte_3_);
    }

    public int forEachByteDesc(ByteProcessor p_forEachByteDesc_1_) {
        return this.n_1700_B.forEachByteDesc(p_forEachByteDesc_1_);
    }

    public int forEachByteDesc(int p_forEachByteDesc_1_, int p_forEachByteDesc_2_, ByteProcessor p_forEachByteDesc_3_) {
        return this.n_1700_B.forEachByteDesc(p_forEachByteDesc_1_, p_forEachByteDesc_2_, p_forEachByteDesc_3_);
    }

    public ByteBuf copy() {
        return this.n_1700_B.copy();
    }

    public ByteBuf copy(int p_copy_1_, int p_copy_2_) {
        return this.n_1700_B.copy(p_copy_1_, p_copy_2_);
    }

    public ByteBuf slice() {
        return this.n_1700_B.slice();
    }

    public ByteBuf retainedSlice() {
        return this.n_1700_B.retainedSlice();
    }

    public ByteBuf slice(int p_slice_1_, int p_slice_2_) {
        return this.n_1700_B.slice(p_slice_1_, p_slice_2_);
    }

    public ByteBuf retainedSlice(int p_retainedSlice_1_, int p_retainedSlice_2_) {
        return this.n_1700_B.retainedSlice(p_retainedSlice_1_, p_retainedSlice_2_);
    }

    public ByteBuf duplicate() {
        return this.n_1700_B.duplicate();
    }

    public ByteBuf retainedDuplicate() {
        return this.n_1700_B.retainedDuplicate();
    }

    public int nioBufferCount() {
        return this.n_1700_B.nioBufferCount();
    }

    public ByteBuffer nioBuffer() {
        return this.n_1700_B.nioBuffer();
    }

    public ByteBuffer nioBuffer(int p_nioBuffer_1_, int p_nioBuffer_2_) {
        return this.n_1700_B.nioBuffer(p_nioBuffer_1_, p_nioBuffer_2_);
    }

    public ByteBuffer internalNioBuffer(int p_internalNioBuffer_1_, int p_internalNioBuffer_2_) {
        return this.n_1700_B.internalNioBuffer(p_internalNioBuffer_1_, p_internalNioBuffer_2_);
    }

    public ByteBuffer[] nioBuffers() {
        return this.n_1700_B.nioBuffers();
    }

    public ByteBuffer[] nioBuffers(int p_nioBuffers_1_, int p_nioBuffers_2_) {
        return this.n_1700_B.nioBuffers(p_nioBuffers_1_, p_nioBuffers_2_);
    }

    public boolean hasArray() {
        return this.n_1700_B.hasArray();
    }

    public byte[] array() {
        return this.n_1700_B.array();
    }

    public int arrayOffset() {
        return this.n_1700_B.arrayOffset();
    }

    public boolean hasMemoryAddress() {
        return this.n_1700_B.hasMemoryAddress();
    }

    public long memoryAddress() {
        return this.n_1700_B.memoryAddress();
    }

    public String toString(Charset p_toString_1_) {
        return this.n_1700_B.toString(p_toString_1_);
    }

    public String toString(int p_toString_1_, int p_toString_2_, Charset p_toString_3_) {
        return this.n_1700_B.toString(p_toString_1_, p_toString_2_, p_toString_3_);
    }

    public int hashCode() {
        return this.n_1700_B.hashCode();
    }

    public boolean equals(Object p_equals_1_) {
        return this.n_1700_B.equals(p_equals_1_);
    }

    public int compareTo(ByteBuf p_compareTo_1_) {
        return this.n_1700_B.compareTo(p_compareTo_1_);
    }

    public String toString() {
        return this.n_1700_B.toString();
    }

    public ByteBuf retain(int p_retain_1_) {
        return this.n_1700_B.retain(p_retain_1_);
    }

    public ByteBuf retain() {
        return this.n_1700_B.retain();
    }

    public ByteBuf touch() {
        return this.n_1700_B.touch();
    }

    public ByteBuf touch(Object p_touch_1_) {
        return this.n_1700_B.touch(p_touch_1_);
    }

    public int refCnt() {
        return this.n_1700_B.refCnt();
    }

    public boolean release() {
        return this.n_1700_B.release();
    }

    public boolean release(int p_release_1_) {
        return this.n_1700_B.release(p_release_1_);
    }

    public Map<String, Object> M_182_A() {
        return this.J_1907_R;
    }

    public Object J_1907_R(String p_getCustomData_1_) {
        return this.J_1907_R == null ? null : this.J_1907_R.get(p_getCustomData_1_);
    }

    public void n_1700_B(Map<String, Object> p_setCustomData_1_) {
        this.J_1907_R = p_setCustomData_1_;
    }
}


