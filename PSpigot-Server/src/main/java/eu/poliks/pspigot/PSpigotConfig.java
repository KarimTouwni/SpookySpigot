package eu.poliks.pspigot;

import eu.poliks.pspigot.command.gameplay.KnockbackCommand;
import eu.poliks.pspigot.command.dev.DevCommand;
import eu.poliks.pspigot.command.dev.DevLogCommand;
import eu.poliks.pspigot.command.personal.DimensionCommand;
import eu.poliks.pspigot.command.personal.PingCommand;
import eu.poliks.pspigot.command.server.BroadcastCommand;
import eu.poliks.pspigot.command.server.ClearChatCommand;
import eu.poliks.pspigot.command.server.SetSlotCommand;
import eu.poliks.pspigot.command.server.WhoisCommand;
import eu.poliks.pspigot.packet.PacketsManager;
import eu.poliks.pspigot.player.CustomPlayerMovements;
import eu.poliks.pspigot.command.gameplay.PotionsCommand;
import net.minecraft.server.MinecraftServer;
import eu.poliks.pspigot.command.PSpigotCommand;
import org.bukkit.command.Command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum PSpigotConfig {

    getInstance;

    public Set<PacketsManager> packetsManagers = new HashSet<>();
    public  Set<CustomPlayerMovements> customPlayerMovements = new HashSet<>();
    static Map<String, Command> commands;

    public Set<PacketsManager> getPacketsManagers() {
        return packetsManagers;
    }

    public Set<CustomPlayerMovements> getCustomPlayerMovements() {
        return customPlayerMovements;
    }

    public void addPacket(PacketsManager manager) {
        this.packetsManagers.add(manager);
    }

    public void addCustomPlayerMovement(CustomPlayerMovements movement) {
        this.customPlayerMovements.add(movement);
    }


    public void registerCommands() {
        Map<String, Command> commands = new HashMap<>();
        commands.put("knockback", new KnockbackCommand());
        commands.put("spookyspigot", new PSpigotCommand());
        commands.put("potions", new PotionsCommand());
        commands.put("setslots", new SetSlotCommand());
        commands.put("ping", new PingCommand());
        commands.put("clearchat", new ClearChatCommand());
        commands.put("whois", new WhoisCommand());
        commands.put("dimension", new DimensionCommand());
        commands.put("devlog", new DevLogCommand());
        commands.put("dev", new DevCommand());
        commands.put("broadcast", new BroadcastCommand());





        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            MinecraftServer.getServer().server.getCommandMap().register(entry.getKey(), "spookyspigot", entry.getValue());
        }
    }
}