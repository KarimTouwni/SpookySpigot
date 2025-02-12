/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleLogin;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class Login
extends MiddleLogin<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_LOGIN_ID, version);
        serializer.writeInt(this.playerEntityId);
        serializer.writeString(this.leveltype);
        serializer.writeByte(this.gamemode);
        serializer.writeByte(this.dimension);
        serializer.writeByte(this.difficulty);
        serializer.writeByte(0);
        serializer.writeByte(Math.min(this.maxplayers, 60));
        return RecyclableSingletonList.create(serializer);
    }
}

