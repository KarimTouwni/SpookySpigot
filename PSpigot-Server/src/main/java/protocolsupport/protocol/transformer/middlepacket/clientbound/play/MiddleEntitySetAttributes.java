/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import java.util.UUID;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleEntity;

public abstract class MiddleEntitySetAttributes<T>
extends MiddleEntity<T> {
    protected Attribute[] attributes;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        super.readFromServerData(serializer);
        this.attributes = new Attribute[serializer.readInt()];
        for (int i = 0; i < this.attributes.length; ++i) {
            Attribute attribute = new Attribute();
            attribute.key = serializer.readString(64);
            attribute.value = serializer.readDouble();
            attribute.modifiers = new Modifier[serializer.readVarInt()];
            for (int j = 0; j < attribute.modifiers.length; ++j) {
                Modifier modifier = new Modifier();
                modifier.uuid = serializer.readUUID();
                modifier.amount = serializer.readDouble();
                modifier.operation = serializer.readByte();
                attribute.modifiers[j] = modifier;
            }
            this.attributes[i] = attribute;
        }
    }

    protected static class Modifier {
        public UUID uuid;
        public double amount;
        public int operation;

        protected Modifier() {
        }
    }

    protected static class Attribute {
        public String key;
        public double value;
        public Modifier[] modifiers;

        protected Attribute() {
        }
    }

}

