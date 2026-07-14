/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelInboundHandlerAdapter
 */
package mods.viaversion.vialoadingbase.netty;

import com.viaversion.viaversion.api.connection.UserConnection;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import mods.viaversion.vialoadingbase.netty.event.CompressionReorderEvent;
import mods.viaversion.vialoadingbase.netty.handler.VLBViaDecodeHandler;
import mods.viaversion.vialoadingbase.netty.handler.VLBViaEncodeHandler;

public abstract class VLBPipeline
extends ChannelInboundHandlerAdapter {
    public static final String VIA_DECODER_HANDLER_NAME = "via-decoder";
    public static final String VIA_ENCODER_HANDLER_NAME = "via-encoder";
    private final UserConnection user;

    public VLBPipeline(UserConnection user) {
        this.user = user;
    }

    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        ctx.pipeline().addBefore(this.getDecoderHandlerName(), VIA_DECODER_HANDLER_NAME, (ChannelHandler)this.createVLBViaDecodeHandler());
        ctx.pipeline().addBefore(this.getEncoderHandlerName(), VIA_ENCODER_HANDLER_NAME, (ChannelHandler)this.createVLBViaEncodeHandler());
    }

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof CompressionReorderEvent) {
            int decoderIndex = ctx.pipeline().names().indexOf(this.getDecompressionHandlerName());
            if (decoderIndex == -1) {
                super.userEventTriggered(ctx, evt);
                return;
            }
            if (decoderIndex > ctx.pipeline().names().indexOf(VIA_DECODER_HANDLER_NAME)) {
                ChannelHandler decoder = ctx.pipeline().get(VIA_DECODER_HANDLER_NAME);
                ChannelHandler encoder = ctx.pipeline().get(VIA_ENCODER_HANDLER_NAME);
                ctx.pipeline().remove(decoder);
                ctx.pipeline().remove(encoder);
                ctx.pipeline().addAfter(this.getDecompressionHandlerName(), VIA_DECODER_HANDLER_NAME, decoder);
                ctx.pipeline().addAfter(this.getCompressionHandlerName(), VIA_ENCODER_HANDLER_NAME, encoder);
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    public VLBViaDecodeHandler createVLBViaDecodeHandler() {
        return new VLBViaDecodeHandler(this.user);
    }

    public VLBViaEncodeHandler createVLBViaEncodeHandler() {
        return new VLBViaEncodeHandler(this.user);
    }

    public abstract String getDecoderHandlerName();

    public abstract String getEncoderHandlerName();

    public abstract String getDecompressionHandlerName();

    public abstract String getCompressionHandlerName();

    public UserConnection getUser() {
        return this.user;
    }
}

