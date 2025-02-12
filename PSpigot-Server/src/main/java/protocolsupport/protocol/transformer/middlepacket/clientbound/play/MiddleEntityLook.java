/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleEntity;

public abstract class MiddleEntityLook<T>
extends MiddleEntity<T> {
    protected int yaw;
    protected int pitch;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        super.readFromServerData(serializer);
        this.yaw = serializer.readByte();
        this.pitch = serializer.readByte();
    }
}

