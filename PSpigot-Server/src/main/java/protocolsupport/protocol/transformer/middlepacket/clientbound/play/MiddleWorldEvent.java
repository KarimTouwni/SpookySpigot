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

public abstract class MiddleWorldEvent<T>
extends ClientBoundMiddlePacket<T> {
    protected int effectId;
    protected BlockPosition position;
    protected int data;
    protected boolean disableRelative;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.effectId = serializer.readInt();
        this.position = serializer.readPosition();
        this.data = serializer.readInt();
        this.disableRelative = serializer.readBoolean();
    }
}

