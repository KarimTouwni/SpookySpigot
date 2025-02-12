/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleInventoryTransaction;

public class InventoryTransaction
extends MiddleInventoryTransaction {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        this.windowId = serializer.readUnsignedByte();
        this.actionNumber = serializer.readShort();
        this.accepted = serializer.readBoolean();
    }
}

