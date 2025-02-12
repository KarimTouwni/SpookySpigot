/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.BlockPosition
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.BlockPosition;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleBlockChangeSingle;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.protocol.typeremapper.id.RemappingRegistry;
import protocolsupport.protocol.typeremapper.id.RemappingTable;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class BlockChangeSingle
extends MiddleBlockChangeSingle<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_BLOCK_CHANGE_SINGLE_ID, version);
        serializer.writeInt(this.position.getX());
        serializer.writeByte(this.position.getY());
        serializer.writeInt(this.position.getZ());
        serializer.writeShort(IdRemapper.BLOCK.getTable(version).getRemap(this.id >> 4));
        serializer.writeByte(this.id & 15);
        return RecyclableSingletonList.create(serializer);
    }
}

