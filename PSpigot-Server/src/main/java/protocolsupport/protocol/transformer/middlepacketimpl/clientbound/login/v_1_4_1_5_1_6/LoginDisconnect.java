/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.IChatBaseComponent
 *  net.minecraft.server.IChatBaseComponent$ChatSerializer
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.login.v_1_4_1_5_1_6;

import java.io.IOException;
import net.minecraft.server.IChatBaseComponent;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.login.MiddleLoginDisconnect;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.transformer.utils.LegacyUtils;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class LoginDisconnect
extends MiddleLoginDisconnect<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        PacketData serializer = PacketData.create(ClientBoundPacket.LOGIN_DISCONNECT_ID, version);
        serializer.writeString(LegacyUtils.toText(IChatBaseComponent.ChatSerializer.a((String)this.messageJson)));
        return RecyclableSingletonList.create(serializer);
    }
}

