/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleAnimation;

public class Animation
extends MiddleAnimation {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        serializer.readInt();
        serializer.readByte();
    }
}

