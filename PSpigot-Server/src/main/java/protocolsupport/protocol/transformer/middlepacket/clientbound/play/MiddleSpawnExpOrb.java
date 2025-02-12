/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleSpawnExpOrb<T>
extends ClientBoundMiddlePacket<T> {
    protected int entityId;
    protected int x;
    protected int y;
    protected int z;
    protected int count;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.entityId = serializer.readVarInt();
        this.x = serializer.readInt();
        this.y = serializer.readInt();
        this.z = serializer.readInt();
        this.count = serializer.readShort();
    }
}

