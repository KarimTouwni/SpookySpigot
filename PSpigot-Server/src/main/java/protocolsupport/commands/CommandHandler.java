package protocolsupport.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PropertyManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import protocolsupport.api.ProtocolSupportAPI;
import protocolsupport.api.ProtocolVersion;

public class CommandHandler implements CommandExecutor,
TabCompleter {
    private static final String DEBUG_PROPERTY = "debug";

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("protocolsupport.admin")) {
            sender.sendMessage((Object)ChatColor.RED + "You have no power here!");
            return true;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
            for (ProtocolVersion version : ProtocolVersion.values()) {
                if (version.getName() == null) continue;
                sender.sendMessage((Object)ChatColor.GOLD + "[" + version.getName() + "]: " + (Object)ChatColor.GREEN + this.getPlayersStringForProtocol(version));
            }
            return true;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase(DEBUG_PROPERTY)) {
            PropertyManager manager = MinecraftServer.getServer().getPropertyManager();
            if (!manager.getBoolean(DEBUG_PROPERTY, false)) {
                manager.setProperty(DEBUG_PROPERTY, (Object)Boolean.TRUE);
                sender.sendMessage((Object)ChatColor.GOLD + "Enabled debug");
            } else {
                manager.setProperty(DEBUG_PROPERTY, (Object)Boolean.FALSE);
                sender.sendMessage((Object)ChatColor.GOLD + "Disabled debug");
            }
            return true;
        }
        return false;
    }

    private String getPlayersStringForProtocol(ProtocolVersion version) {
        StringBuilder sb = new StringBuilder();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (ProtocolSupportAPI.getProtocolVersion(player) != version) continue;
            sb.append(player.getName());
            sb.append(", ");
        }
        if (sb.length() > 2) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args[0].isEmpty()) {
            return Arrays.asList("list", DEBUG_PROPERTY);
        }
        if ("list".startsWith(args[0])) {
            return Collections.singletonList("list");
        }
        if (DEBUG_PROPERTY.startsWith(args[0])) {
            return Collections.singletonList(DEBUG_PROPERTY);
        }
        return Collections.emptyList();
    }
}

