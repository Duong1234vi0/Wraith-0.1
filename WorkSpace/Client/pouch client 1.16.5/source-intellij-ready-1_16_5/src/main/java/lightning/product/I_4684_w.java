/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import lightning.product.Resource;
import lightning.product.O_728_b;
import lightning.product.AudioStream;
import lightning.product.ResourceManager;
import lightning.product.b_815_d;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.m_4536_S;
import lightning.product.v_4977_T;

public class I_4684_w {
    private final ResourceManager n_1700_B;
    private final Map<g_2336_b, CompletableFuture<m_4536_S>> J_1907_R = Maps.newHashMap();

    public I_4684_w(ResourceManager resourceManagerIn) {
        this.n_1700_B = resourceManagerIn;
    }

    public CompletableFuture<m_4536_S> n_1700_B(g_2336_b soundIDIn) {
        return this.J_1907_R.computeIfAbsent(soundIDIn, soundID -> CompletableFuture.supplyAsync(() -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.getAnalysis(Method.java:520)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteDynamicExpression(LambdaRewriter.java:351)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteDynamicExpression(LambdaRewriter.java:167)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteExpression(LambdaRewriter.java:105)
             *     at org.benf.cfr.reader.bytecode.analysis.parse.rewriters.ExpressionRewriterHelper.applyForwards(ExpressionRewriterHelper.java:12)
             *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.StaticFunctionInvokation.applyExpressionRewriterToArgs(StaticFunctionInvokation.java:103)
             *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.StaticFunctionInvokation.applyExpressionRewriter(StaticFunctionInvokation.java:90)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteExpression(LambdaRewriter.java:103)
             *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredReturn.rewriteExpressions(StructuredReturn.java:99)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewrite(LambdaRewriter.java:88)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.rewriteLambdas(Op04StructuredStatement.java:1137)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:912)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1050)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
             *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             */
            throw new IllegalStateException("Decompilation failed");
        }, j_3341_s.u_1723_Y()));
    }

    public CompletableFuture<AudioStream> n_1700_B(g_2336_b resourceLocation, boolean isWrapper) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Resource iresource = this.n_1700_B.n_1700_B(resourceLocation);
                InputStream inputstream = iresource.J_1907_R();
                return isWrapper ? new b_815_d(v_4977_T::new, inputstream) : new v_4977_T(inputstream);
            }
            catch (IOException ioexception) {
                throw new CompletionException(ioexception);
            }
        }, j_3341_s.u_1723_Y());
    }

    public void n_1700_B() {
        this.J_1907_R.values().forEach(audioStreamBuffer -> audioStreamBuffer.thenAccept(m_4536_S::J_1907_R));
        this.J_1907_R.clear();
    }

    public CompletableFuture<?> n_1700_B(Collection<O_728_b> sounds) {
        return CompletableFuture.allOf((CompletableFuture[])sounds.stream().map(sound -> this.n_1700_B(sound.G_564_y())).toArray(CompletableFuture[]::new));
    }
}


