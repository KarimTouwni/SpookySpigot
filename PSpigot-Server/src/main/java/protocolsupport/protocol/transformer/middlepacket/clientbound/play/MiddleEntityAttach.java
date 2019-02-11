/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleEntityAttach<T>
extends ClientBoundMiddlePacket<T> {
    protected int entityId;
    protected int vehicleId;
    protected boolean leash;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.entityId = serializer.readInt();
        this.vehicleId = serializer.readInt();
        this.leash = serializer.readBoolean();
    }
}

