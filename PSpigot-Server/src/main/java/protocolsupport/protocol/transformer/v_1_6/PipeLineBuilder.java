/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelPipeline
 *  net.minecraft.server.NetworkManager
 *  net.minecraft.server.PacketListener
 */
package protocolsupport.protocol.transformer.v_1_6;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import net.minecraft.server.NetworkManager;
import net.minecraft.server.PacketListener;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.core.ChannelHandlers;
import protocolsupport.protocol.core.IPacketDecoder;
import protocolsupport.protocol.core.IPacketEncoder;
import protocolsupport.protocol.core.IPacketPrepender;
import protocolsupport.protocol.core.IPacketSplitter;
import protocolsupport.protocol.core.IPipeLineBuilder;
import protocolsupport.protocol.transformer.v_1_6.HandshakeListener;
import protocolsupport.protocol.transformer.v_1_6.PacketDecoder;
import protocolsupport.protocol.transformer.v_1_6.PacketEncoder;
import protocolsupport.protocol.transformer.v_1_6.PacketPrepender;
import protocolsupport.protocol.transformer.v_1_6.PacketSplitter;

public class PipeLineBuilder
implements IPipeLineBuilder {
    @Override
    public void buildPipeLine(Channel channel, ProtocolVersion version) {
        ChannelPipeline pipeline = channel.pipeline();
        NetworkManager networkmanager = (NetworkManager)pipeline.get("packet_handler");
        networkmanager.a((PacketListener)new HandshakeListener(networkmanager));
        ChannelHandlers.getSplitter(pipeline).setRealSplitter(new PacketSplitter());
        ChannelHandlers.getPrepender(pipeline).setRealPrepender(new PacketPrepender());
        ChannelHandlers.getDecoder(pipeline).setRealDecoder(new PacketDecoder(version));
        ChannelHandlers.getEncoder(pipeline).setRealEncoder(new PacketEncoder(version));
    }
}

