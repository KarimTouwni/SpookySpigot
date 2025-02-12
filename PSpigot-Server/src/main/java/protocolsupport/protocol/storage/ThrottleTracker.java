/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  gnu.trove.iterator.TObjectLongIterator
 *  gnu.trove.map.hash.TObjectLongHashMap
 *  net.minecraft.server.MinecraftServer
 *  org.bukkit.craftbukkit.CraftServer
 */
package protocolsupport.protocol.storage;

import gnu.trove.iterator.TObjectLongIterator;
import gnu.trove.map.hash.TObjectLongHashMap;
import java.net.InetAddress;
import net.minecraft.server.MinecraftServer;
import org.bukkit.craftbukkit.CraftServer;

public class ThrottleTracker {
    private static final TObjectLongHashMap<InetAddress> tracker = new TObjectLongHashMap();
    private static final long time = MinecraftServer.getServer().server.getConnectionThrottle();

    public static boolean isEnabled() {
        return time > 0L;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void track(InetAddress address, long time) {
        TObjectLongHashMap<InetAddress> tObjectLongHashMap = tracker;
        synchronized (tObjectLongHashMap) {
            tracker.put(address, time);
            if (tracker.size() > 100) {
                long currentTime = System.currentTimeMillis();
                TObjectLongIterator iterator = tracker.iterator();
                while (iterator.hasNext()) {
                    iterator.advance();
                    if (currentTime - iterator.value() >= time) continue;
                    iterator.remove();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean isThrottled(InetAddress address) {
        TObjectLongHashMap<InetAddress> tObjectLongHashMap = tracker;
        synchronized (tObjectLongHashMap) {
            return tracker.containsKey((Object)address) && System.currentTimeMillis() - tracker.get((Object)address) < time;
        }
    }
}

