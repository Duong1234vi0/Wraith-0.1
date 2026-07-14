/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 */
package net.optifine.shaders;

import com.google.common.collect.ImmutableList;
import lightning.product.A_1726_L;
import lightning.product.b_1213_w;

public class SVertexFormat {
    public static final int vertexSizeBlock = 18;
    public static final int offsetMidBlock = 8;
    public static final int offsetMidTexCoord = 9;
    public static final int offsetTangent = 11;
    public static final int offsetEntity = 13;
    public static final int offsetVelocity = 15;
    public static final A_1726_L SHADERS_MIDBLOCK_3B = SVertexFormat.makeElement("SHADERS_MIDOFFSET_3B", 0, A_1726_L.n_1700_B.R_4764_Y, A_1726_L.J_1907_R.P_1922_E, 3);
    public static final A_1726_L PADDING_1B = SVertexFormat.makeElement("PADDING_1B", 0, A_1726_L.n_1700_B.R_4764_Y, A_1726_L.J_1907_R.P_1922_E, 1);
    public static final A_1726_L SHADERS_MIDTEXCOORD_2F = SVertexFormat.makeElement("SHADERS_MIDTEXCOORD_2F", 0, A_1726_L.n_1700_B.n_1700_B, A_1726_L.J_1907_R.P_1922_E, 2);
    public static final A_1726_L SHADERS_TANGENT_4S = SVertexFormat.makeElement("SHADERS_TANGENT_4S", 0, A_1726_L.n_1700_B.P_1922_E, A_1726_L.J_1907_R.P_1922_E, 4);
    public static final A_1726_L SHADERS_MC_ENTITY_4S = SVertexFormat.makeElement("SHADERS_MC_ENTITY_4S", 0, A_1726_L.n_1700_B.P_1922_E, A_1726_L.J_1907_R.P_1922_E, 4);
    public static final A_1726_L SHADERS_VELOCITY_3F = SVertexFormat.makeElement("SHADERS_VELOCITY_3F", 0, A_1726_L.n_1700_B.n_1700_B, A_1726_L.J_1907_R.P_1922_E, 3);

    public static b_1213_w makeExtendedFormatBlock(b_1213_w blockVanilla) {
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.addAll(blockVanilla.R_4764_Y());
        builder.add((Object)SHADERS_MIDBLOCK_3B);
        builder.add((Object)PADDING_1B);
        builder.add((Object)SHADERS_MIDTEXCOORD_2F);
        builder.add((Object)SHADERS_TANGENT_4S);
        builder.add((Object)SHADERS_MC_ENTITY_4S);
        builder.add((Object)SHADERS_VELOCITY_3F);
        return new b_1213_w((ImmutableList<A_1726_L>)builder.build());
    }

    public static b_1213_w makeExtendedFormatEntity(b_1213_w entityVanilla) {
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.addAll(entityVanilla.R_4764_Y());
        builder.add((Object)SHADERS_MIDTEXCOORD_2F);
        builder.add((Object)SHADERS_TANGENT_4S);
        builder.add((Object)SHADERS_MC_ENTITY_4S);
        builder.add((Object)SHADERS_VELOCITY_3F);
        return new b_1213_w((ImmutableList<A_1726_L>)builder.build());
    }

    private static A_1726_L makeElement(String name, int indexIn, A_1726_L.n_1700_B typeIn, A_1726_L.J_1907_R usageIn, int count) {
        A_1726_L vertexformatelement = new A_1726_L(indexIn, typeIn, usageIn, count);
        vertexformatelement.n_1700_B(name);
        return vertexformatelement;
    }
}

