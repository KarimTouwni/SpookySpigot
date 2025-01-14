/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.IChatBaseComponent
 *  net.minecraft.server.IChatBaseComponent$ChatSerializer
 *  org.bukkit.entity.Player
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.IChatBaseComponent;
import org.bukkit.entity.Player;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleInventoryOpen;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.transformer.utils.LegacyUtils;
import protocolsupport.protocol.typeskipper.id.IdSkipper;
import protocolsupport.protocol.typeskipper.id.SkippingRegistry;
import protocolsupport.protocol.typeskipper.id.SkippingTable;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class InventoryOpen
extends MiddleInventoryOpen<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        byte id = LegacyUtils.getInventoryId(this.invname);
        if (IdSkipper.INVENTORY.getTable(version).shouldSkip(id)) {
            this.player.closeInventory();
            return RecyclableEmptyList.get();
        }
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_WINDOW_OPEN_ID, version);
        serializer.writeByte(this.windowId);
        serializer.writeByte((int)id);
        serializer.writeString(LegacyUtils.toText(IChatBaseComponent.ChatSerializer.a((String)this.titleJson)));
        serializer.writeByte(this.slots);
        return RecyclableSingletonList.create(serializer);
    }
}

