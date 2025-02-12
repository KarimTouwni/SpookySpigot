/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  net.minecraft.server.IChatBaseComponent
 *  net.minecraft.server.ServerPing
 *  net.minecraft.server.ServerPing$ServerData
 *  net.minecraft.server.ServerPing$ServerPingPlayerSample
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.status.v_1_5_1_6;

import com.google.gson.Gson;
import java.io.IOException;
import net.minecraft.server.IChatBaseComponent;
import net.minecraft.server.ServerPing;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.status.MiddleServerInfo;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.transformer.utils.LegacyUtils;
import protocolsupport.protocol.transformer.utils.ServerPingSerializers;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class ServerInfo
extends MiddleServerInfo<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        ServerPing serverPing;
        int versionId;
        PacketData serializer = PacketData.create(ClientBoundPacket.STATUS_SERVER_INFO_ID, version);
        String response = "\u00a71\u0000" + ((versionId = (serverPing = ServerPingSerializers.PING_GSON.fromJson(this.pingJson, ServerPing.class)).c().b()) == ProtocolVersion.getLatest().getId() ? serializer.getVersion().getId() : versionId) + "\u0000" + serverPing.c().a() + "\u0000" + LegacyUtils.toText(serverPing.a()) + "\u0000" + serverPing.b().b() + "\u0000" + serverPing.b().a();
        serializer.writeString(response);
        return RecyclableSingletonList.create(serializer);
    }
}

