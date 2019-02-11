/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleEntityEffectRemove;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.typeskipper.id.IdSkipper;
import protocolsupport.protocol.typeskipper.id.SkippingRegistry;
import protocolsupport.protocol.typeskipper.id.SkippingTable;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class EntityEffectRemove
extends MiddleEntityEffectRemove<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        if (IdSkipper.EFFECT.getTable(version).shouldSkip(this.effectId)) {
            return RecyclableEmptyList.get();
        }
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_ENTITY_EFFECT_REMOVE_ID, version);
        serializer.writeInt(this.entityId);
        serializer.writeByte(this.effectId);
        return RecyclableSingletonList.create(serializer);
    }
}

