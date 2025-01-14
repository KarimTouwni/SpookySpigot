/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7;

import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleTabComplete;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class TabComplete
extends MiddleTabComplete<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_TAB_COMPLETE_ID, version);
        serializer.writeVarInt(this.matches.length);
        for (String match : this.matches) {
            serializer.writeString(match);
        }
        return RecyclableSingletonList.create(serializer);
    }
}

