/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.util.Attribute
 *  io.netty.util.AttributeKey
 *  net.minecraft.server.EnumProtocol
 *  net.minecraft.server.EnumProtocolDirection
 *  net.minecraft.server.NetworkManager
 *  net.minecraft.server.Packet
 *  net.minecraft.server.PacketDataSerializer
 *  net.minecraft.server.PacketListener
 *  org.bukkit.entity.Player
 *  org.spigotmc.SneakyThrow
 */
package protocolsupport.protocol.transformer.v_1_7;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.io.IOException;
import net.minecraft.server.EnumProtocol;
import net.minecraft.server.EnumProtocolDirection;
import net.minecraft.server.NetworkManager;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketListener;
import org.bukkit.entity.Player;
import org.spigotmc.SneakyThrow;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.core.IPacketEncoder;
import protocolsupport.protocol.storage.LocalStorage;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.login.v_1_4_1_5_1_6_1_7.EncryptionRequest;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.login.v_1_7.LoginDisconnect;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.login.v_1_7.LoginSuccess;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v1_5_1_6_1_7.InventoryOpen;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v1_5_1_6_1_7.ScoreboardObjective;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v1_5_1_6_1_7.ScoreboardScore;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v1_5_1_6_1_7.ScoreboardTeam;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v1_5_1_6_1_7.WorldParticle;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.BlockChangeMulti;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.BlockSignUpdate;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.BlockTileUpdate;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.ChunkMulti;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.ChunkSingle;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.CollectEffect;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.CustomPayload;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.Entity;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.EntityDestroy;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.EntityEffectAdd;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.EntityEffectRemove;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.EntityEquipment;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.EntityHeadRotation;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.EntityLook;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.EntityMetadata;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.EntityRelMove;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.EntityRelMoveLook;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.EntityStatus;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.EntityVelocity;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.InventoryClose;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.InventoryConfirmTransaction;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.InventoryData;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.InventorySetItems;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.InventorySetSlot;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.KeepAlive;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.PlayerInfo;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.ScoreboardDisplay;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.SetExperience;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.TimeUpdate;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7.WorldEvent;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_6_1_7.EntityAttach;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_6_1_7.EntitySetAttributes;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_6_1_7.PlayerAbilities;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_6_1_7.SetHealth;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.Animation;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.BlockAction;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.BlockBreakAnimation;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.BlockChangeSingle;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.BlockOpenSignEditor;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.Chat;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.EntityTeleport;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.Explosion;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.GameStateChange;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.HeldSlot;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.KickDisconnect;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.Login;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.Map;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.Position;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.ResourcePack;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.Respawn;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.SpawnExpOrb;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.SpawnGlobal;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.SpawnLiving;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.SpawnNamed;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.SpawnObject;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.SpawnPainting;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.SpawnPosition;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.Statistics;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.TabComplete;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.UseBed;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7.WorldSound;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.status.v_1_7.Pong;
import protocolsupport.protocol.transformer.middlepacketimpl.clientbound.status.v_1_7.ServerInfo;
import protocolsupport.protocol.transformer.utils.registry.MiddleTransformerRegistry;
import protocolsupport.utils.netty.Allocator;
import protocolsupport.utils.netty.ChannelUtils;
import protocolsupport.utils.recyclable.RecyclableCollection;

public class PacketEncoder
implements IPacketEncoder {
    private static final EnumProtocolDirection direction = EnumProtocolDirection.CLIENTBOUND;
    private static final AttributeKey<EnumProtocol> currentStateAttrKey = NetworkManager.c;
    private final MiddleTransformerRegistry<ClientBoundMiddlePacket<RecyclableCollection<PacketData>>> registry = new MiddleTransformerRegistry();
    private final ProtocolVersion version;
    private final LocalStorage storage;
    private final PacketDataSerializer serverdata;

    public PacketEncoder(ProtocolVersion version) {
        try {
            this.registry.register(EnumProtocol.LOGIN, ClientBoundPacket.LOGIN_SUCCESS_ID, LoginSuccess.class);
            this.registry.register(EnumProtocol.LOGIN, ClientBoundPacket.LOGIN_ENCRYPTION_BEGIN_ID, EncryptionRequest.class);
            this.registry.register(EnumProtocol.LOGIN, ClientBoundPacket.LOGIN_DISCONNECT_ID, LoginDisconnect.class);
            this.registry.register(EnumProtocol.STATUS, ClientBoundPacket.STATUS_SERVER_INFO_ID, ServerInfo.class);
            this.registry.register(EnumProtocol.STATUS, ClientBoundPacket.STATUS_PONG_ID, Pong.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_KEEP_ALIVE_ID, KeepAlive.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_LOGIN_ID, Login.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_CHAT_ID, Chat.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_UPDATE_TIME_ID, TimeUpdate.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_EQUIPMENT_ID, EntityEquipment.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_SPAWN_POSITION_ID, SpawnPosition.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_UPDATE_HEALTH_ID, SetHealth.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_RESPAWN_ID, Respawn.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_POSITION_ID, Position.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_HELD_SLOT_ID, HeldSlot.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_BED_ID, UseBed.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ANIMATION_ID, Animation.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_SPAWN_NAMED_ID, SpawnNamed.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_COLLECT_EFFECT_ID, CollectEffect.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_SPAWN_OBJECT_ID, SpawnObject.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_SPAWN_LIVING_ID, SpawnLiving.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_SPAWN_PAINTING_ID, SpawnPainting.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_SPAWN_EXP_ORB_ID, SpawnExpOrb.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_VELOCITY_ID, EntityVelocity.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_DESTROY_ID, EntityDestroy.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_ID, Entity.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_REL_MOVE_ID, EntityRelMove.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_LOOK_ID, EntityLook.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_REL_MOVE_LOOK_ID, EntityRelMoveLook.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_TELEPORT_ID, EntityTeleport.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_HEAD_ROTATION_ID, EntityHeadRotation.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_STATUS_ID, EntityStatus.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_ATTACH_ID, EntityAttach.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_METADATA_ID, EntityMetadata.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_EFFECT_ADD_ID, EntityEffectAdd.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_EFFECT_REMOVE_ID, EntityEffectRemove.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_EXPERIENCE_ID, SetExperience.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ENTITY_ATTRIBUTES_ID, EntitySetAttributes.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_CHUNK_SINGLE_ID, ChunkSingle.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_BLOCK_CHANGE_MULTI_ID, BlockChangeMulti.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_BLOCK_CHANGE_SINGLE_ID, BlockChangeSingle.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_BLOCK_ACTION_ID, BlockAction.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_BLOCK_BREAK_ANIMATION_ID, BlockBreakAnimation.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_CHUNK_MULTI_ID, ChunkMulti.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_EXPLOSION_ID, Explosion.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_WORLD_EVENT_ID, WorldEvent.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_WORLD_SOUND_ID, WorldSound.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_WORLD_PARTICLES_ID, WorldParticle.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_GAME_STATE_CHANGE_ID, GameStateChange.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_SPAWN_WEATHER_ID, SpawnGlobal.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_WINDOW_OPEN_ID, InventoryOpen.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_WINDOW_CLOSE_ID, InventoryClose.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_WINDOW_SET_SLOT_ID, InventorySetSlot.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_WINDOW_SET_ITEMS_ID, InventorySetItems.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_WINDOW_DATA_ID, InventoryData.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_WINDOW_TRANSACTION_ID, InventoryConfirmTransaction.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_UPDATE_SIGN_ID, BlockSignUpdate.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_MAP_ID, Map.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_UPDATE_TILE_ID, BlockTileUpdate.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_SIGN_EDITOR_ID, BlockOpenSignEditor.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_STATISTICS, Statistics.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_PLAYER_INFO_ID, PlayerInfo.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_ABILITIES_ID, PlayerAbilities.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_TAB_COMPLETE_ID, TabComplete.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_SCOREBOARD_OBJECTIVE_ID, ScoreboardObjective.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_SCOREBOARD_SCORE_ID, ScoreboardScore.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_SCOREBOARD_DISPLAY_SLOT_ID, ScoreboardDisplay.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_SCOREBOARD_TEAM_ID, ScoreboardTeam.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_CUSTOM_PAYLOAD_ID, CustomPayload.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_RESOURCE_PACK_ID, ResourcePack.class);
            this.registry.register(EnumProtocol.PLAY, ClientBoundPacket.PLAY_KICK_DISCONNECT_ID, KickDisconnect.class);
        }
        catch (Throwable t) {
            SneakyThrow.sneaky((Throwable)t);
        }
        this.storage = new LocalStorage();
        this.serverdata = new PacketDataSerializer(Unpooled.buffer(), ProtocolVersion.getLatest());
        this.version = version;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void encode(ChannelHandlerContext ctx, Packet<PacketListener> packet, ByteBuf output) throws Exception {
        Channel channel = ctx.channel();
        EnumProtocol currentProtocol = (EnumProtocol)channel.attr(currentStateAttrKey).get();
        Integer packetId = currentProtocol.a(direction, packet);
        if (packetId == null) {
            throw new IOException("Can't serialize unregistered packet");
        }
        ClientBoundMiddlePacket<RecyclableCollection<PacketData>> packetTransformer = this.registry.getTransformer(currentProtocol, packetId);
        if (packetTransformer != null) {
            this.serverdata.clear();
            packet.b((net.minecraft.server.PacketDataSerializer)this.serverdata);
            if (packetTransformer.needsPlayer()) {
                packetTransformer.setPlayer(ChannelUtils.getBukkitPlayer(channel));
            }
            packetTransformer.readFromServerData(this.serverdata);
            packetTransformer.setLocalStorage(this.storage);
            packetTransformer.handle();
            RecyclableCollection<PacketData> data = packetTransformer.toData(this.version);
            try {
                for (PacketData packetdata : data) {
                    ByteBuf senddata = Allocator.allocateBuffer();
                    ChannelUtils.writeVarInt(senddata, packetdata.getPacketId());
                    senddata.writeBytes((ByteBuf)packetdata);
                    ctx.write((Object)senddata);
                }
                ctx.flush();
            }
            finally {
                for (PacketData packetdata : data) {
                    packetdata.recycle();
                }
                data.recycle();
            }
        }
    }
}

