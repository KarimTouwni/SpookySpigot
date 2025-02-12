/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleTimeUpdate<T>
extends ClientBoundMiddlePacket<T> {
    protected long worldAge;
    protected long timeOfDay;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.worldAge = serializer.readLong();
        this.timeOfDay = serializer.readLong();
    }

    @Override
    public void handle() {
    }
}

