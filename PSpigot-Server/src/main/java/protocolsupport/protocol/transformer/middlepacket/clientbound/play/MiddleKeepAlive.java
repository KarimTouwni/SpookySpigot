/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleKeepAlive<T>
extends ClientBoundMiddlePacket<T> {
    protected int keepAliveId;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.keepAliveId = serializer.readVarInt();
    }
}

