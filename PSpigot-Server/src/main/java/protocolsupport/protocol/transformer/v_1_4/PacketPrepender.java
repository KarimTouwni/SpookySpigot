/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 */
package protocolsupport.protocol.transformer.v_1_4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import protocolsupport.protocol.core.IPacketPrepender;

public class PacketPrepender
implements IPacketPrepender {
    @Override
    public void prepend(ChannelHandlerContext ctx, ByteBuf input, ByteBuf output) throws Exception {
        int readableBytes = input.readableBytes();
        output.ensureWritable(readableBytes);
        output.writeBytes(input, input.readerIndex(), readableBytes);
    }
}

