/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.BlockPosition
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.BlockPosition;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleBlockBreakAnimation;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class BlockBreakAnimation
extends MiddleBlockBreakAnimation<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_BLOCK_BREAK_ANIMATION_ID, version);
        serializer.writeVarInt(this.entityId);
        serializer.writeInt(this.position.getX());
        serializer.writeInt(this.position.getY());
        serializer.writeInt(this.position.getZ());
        serializer.writeByte(this.stage);
        return RecyclableSingletonList.create(serializer);
    }
}

