/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.annotation.Nullable;
import lightning.product.U_2912_j;
import lightning.product.Y_3433_n;
import lightning.product.Tag;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.o_926_S;
import lightning.product.CrashReportCategory;
import lightning.product.EndTag;

public class r_1827_u {
    public static U_2912_j n_1700_B(File file) throws IOException {
        U_2912_j compoundnbt;
        try (FileInputStream inputstream = new FileInputStream(file);){
            compoundnbt = r_1827_u.n_1700_B(inputstream);
        }
        return compoundnbt;
    }

    public static U_2912_j n_1700_B(InputStream is) throws IOException {
        U_2912_j compoundnbt;
        try (DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(is)));){
            compoundnbt = r_1827_u.n_1700_B(datainputstream, o_926_S.n_1700_B);
        }
        return compoundnbt;
    }

    public static void n_1700_B(U_2912_j compound, File file) throws IOException {
        try (FileOutputStream outputstream = new FileOutputStream(file);){
            r_1827_u.n_1700_B(compound, outputstream);
        }
    }

    public static void n_1700_B(U_2912_j compound, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataoutputstream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(outputStream)));){
            r_1827_u.n_1700_B(compound, (DataOutput)dataoutputstream);
        }
    }

    public static void J_1907_R(U_2912_j compound, File fileIn) throws IOException {
        try (FileOutputStream fileoutputstream = new FileOutputStream(fileIn);
             DataOutputStream dataoutputstream = new DataOutputStream(fileoutputstream);){
            r_1827_u.n_1700_B(compound, (DataOutput)dataoutputstream);
        }
    }

    @Nullable
    public static U_2912_j J_1907_R(File fileIn) throws IOException {
        U_2912_j compoundnbt;
        if (!fileIn.exists()) {
            return null;
        }
        try (FileInputStream fileinputstream = new FileInputStream(fileIn);
             DataInputStream datainputstream = new DataInputStream(fileinputstream);){
            compoundnbt = r_1827_u.n_1700_B(datainputstream, o_926_S.n_1700_B);
        }
        return compoundnbt;
    }

    public static U_2912_j n_1700_B(DataInput inputStream) throws IOException {
        return r_1827_u.n_1700_B(inputStream, o_926_S.n_1700_B);
    }

    public static U_2912_j n_1700_B(DataInput input, o_926_S accounter) throws IOException {
        Tag inbt = r_1827_u.n_1700_B(input, 0, accounter);
        if (inbt instanceof U_2912_j) {
            return (U_2912_j)inbt;
        }
        throw new IOException("Root tag must be a named compound tag");
    }

    public static void n_1700_B(U_2912_j compound, DataOutput output) throws IOException {
        r_1827_u.n_1700_B((Tag)compound, output);
    }

    private static void n_1700_B(Tag tag, DataOutput output) throws IOException {
        output.writeByte(tag.n_1700_B());
        if (tag.n_1700_B() != 0) {
            output.writeUTF("");
            tag.n_1700_B(output);
        }
    }

    private static Tag n_1700_B(DataInput input, int depth, o_926_S accounter) throws IOException {
        byte b0 = input.readByte();
        if (b0 == 0) {
            return EndTag.J_1907_R;
        }
        input.readUTF();
        try {
            return Y_3433_n.n_1700_B(b0).J_1907_R(input, depth, accounter);
        }
        catch (IOException ioexception) {
            n_3236_c crashreport = n_3236_c.n_1700_B(ioexception, "Loading NBT data");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("NBT Tag");
            crashreportcategory.n_1700_B("Tag type", b0);
            throw new ReportedException(crashreport);
        }
    }
}


