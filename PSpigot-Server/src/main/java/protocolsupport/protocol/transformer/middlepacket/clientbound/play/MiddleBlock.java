/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.BlockPosition
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import net.minecraft.server.BlockPosition;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleBlock<T>
extends ClientBoundMiddlePacket<T> {
    protected BlockPosition position;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.position = serializer.readPosition();
    }
}

