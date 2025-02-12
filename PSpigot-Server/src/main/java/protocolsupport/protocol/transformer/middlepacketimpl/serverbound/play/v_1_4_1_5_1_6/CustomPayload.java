/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.server.ItemStack
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.server.ItemStack;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.RecyclablePacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleCustomPayload;
import protocolsupport.utils.netty.ChannelUtils;

public class CustomPayload
extends MiddleCustomPayload {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        this.tag = serializer.readString(20);
        PacketDataSerializer olddata = new PacketDataSerializer(Unpooled.wrappedBuffer((byte[])serializer.readArray()), serializer.getVersion());
        RecyclablePacketDataSerializer newdata = RecyclablePacketDataSerializer.create(ProtocolVersion.getLatest());
        try {
            if (this.tag.equals("MC|ItemName")) {
                newdata.writeVarInt(olddata.readableBytes());
                newdata.writeBytes((ByteBuf)olddata);
            } else if (this.tag.equals("MC|BSign") || this.tag.equals("MC|BEdit")) {
                newdata.writeItemStack(olddata.readItemStack());
            } else if (this.tag.equals("MC|AdvCdm")) {
                newdata.writeByte(0);
                newdata.writeInt(olddata.readInt());
                newdata.writeInt(olddata.readInt());
                newdata.writeInt(olddata.readInt());
                newdata.writeString(olddata.readString(32767));
                newdata.writeBoolean(true);
            } else {
                newdata.writeBytes((ByteBuf)olddata);
            }
            this.data = ChannelUtils.toArray((ByteBuf)newdata);
        }
        finally {
            newdata.release();
        }
    }
}

