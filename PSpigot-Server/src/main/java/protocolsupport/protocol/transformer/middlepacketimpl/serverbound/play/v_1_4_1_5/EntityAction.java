/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleEntityAction;

public class EntityAction
extends MiddleEntityAction {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        this.entityId = serializer.readInt();
        this.actionId = serializer.readByte() - 1;
    }
}

