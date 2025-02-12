package protocolsupport.api.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.IChatBaseComponent;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketPlayOutChat;
import net.minecraft.server.PlayerConnection;
import org.apache.commons.lang3.Validate;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import protocolsupport.api.chat.components.BaseComponent;
import protocolsupport.api.chat.modifiers.ClickAction;
import protocolsupport.api.chat.modifiers.HoverAction;
import protocolsupport.api.chat.modifiers.Modifier;
import protocolsupport.utils.chat.ClickActionSerializer;
import protocolsupport.utils.chat.ComponentSerializer;
import protocolsupport.utils.chat.HoverActionSerializer;
import protocolsupport.utils.chat.ModifierSerializer;

public class ChatAPI {
    private static final Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(BaseComponent.class, (Object)new ComponentSerializer()).registerTypeHierarchyAdapter(Modifier.class, (Object)new ModifierSerializer()).registerTypeHierarchyAdapter(ClickAction.class, (Object)new ClickActionSerializer()).registerTypeHierarchyAdapter(HoverAction.class, (Object)new HoverActionSerializer()).create();

    public static BaseComponent fromJSON(String json) {
        return json != null ? (BaseComponent)gson.fromJson(json, BaseComponent.class) : null;
    }

    public static String toJSON(BaseComponent component) {
        return component != null ? gson.toJson((Object)component) : null;
    }

    public static void sendMessage(Player player, BaseComponent message) {
        ChatAPI.sendMessage(player, message, MessagePosition.CHAT);
    }

    public static void sendMessage(Player player, String messageJson) {
        ChatAPI.sendMessage(player, messageJson, MessagePosition.CHAT);
    }

    public static void sendMessage(Player player, BaseComponent message, MessagePosition position) {
        ChatAPI.sendMessage(player, ChatAPI.toJSON(message), position);
    }

    public static void sendMessage(Player player, String messageJson, MessagePosition position) {
        Validate.notNull((Object)player, (String)"Player can't be null", (Object[])new Object[0]);
        Validate.notNull((Object)messageJson, (String)"Message can't be null", (Object[])new Object[0]);
        Validate.notNull((Object)((Object)position), (String)"Message position can't be null", (Object[])new Object[0]);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a((String)messageJson), (byte)position.ordinal()));
    }

    public static enum MessagePosition {
        CHAT,
        SYSMESSAGE,
        HOTBAR;
        

        private MessagePosition() {
        }
    }

}

