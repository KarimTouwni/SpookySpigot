/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.BlockPosition
 *  net.minecraft.server.Packet
 */
package protocolsupport.protocol.transformer.middlepacket.serverbound.play;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.Packet;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddleTabComplete
extends ServerBoundMiddlePacket {
    protected String string;
    protected BlockPosition position;

    @Override
    public RecyclableCollection<? extends Packet<?>> toNative() throws Exception {
        PacketCreator creator = PacketCreator.create(ServerBoundPacket.PLAY_TAB_COMPLETE.get());
        creator.writeString(this.string);
        if (this.position != null) {
            creator.writeBoolean(true);
            creator.writePosition(this.position);
        } else {
            creator.writeBoolean(false);
        }
        creator.writeBoolean(false);
        return RecyclableSingletonList.create(creator.create());
    }
}

