/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.Packet
 */
package protocolsupport.protocol.transformer.middlepacket.serverbound.status;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.Packet;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddlePing
extends ServerBoundMiddlePacket {
    protected long pingId;

    @Override
    public RecyclableCollection<? extends Packet<?>> toNative() throws Exception {
        PacketCreator creator = PacketCreator.create(ServerBoundPacket.STATUS_PING.get());
        creator.writeLong(this.pingId);
        return RecyclableSingletonList.create(creator.create());
    }
}

