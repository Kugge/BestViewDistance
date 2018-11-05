package me.lxct.bestviewdistance.functions.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.google.common.base.Objects;
import org.bukkit.entity.Player;

public abstract class AbstractPacket {
    // The packet we will be modifying
    static PacketContainer handle;

    /**
     * Constructs a new strongly typed wrapper for the given packet.
     *
     * @param handle - handle to the raw packet data.
     * @param type - the packet type.
     */
    AbstractPacket(PacketContainer handle, PacketType type) {
        // Make sure we're given a valid packet
        if (handle == null)
            throw new IllegalArgumentException("Packet handle cannot be NULL.");
        if (!Objects.equal(handle.getType(), type))
            throw new IllegalArgumentException(handle.getHandle()
                    + " is not a packet of type " + type);

        AbstractPacket.handle = handle;
    }

    /**
     * Retrieve a handle to the raw packet data.
     *
     * @return Raw packet data.
     */
    public static PacketContainer getHandle() {
        return handle;
    }

    ///**
    // * Send the current packet to the given receiver.
    // *
    // * @param receiver - the receiver.
    // * @throws RuntimeException If the packet cannot be sent.
    // */
    // public static void sendPacket(Player receiver) {
    //    try {
    //        ProtocolLibrary.getProtocolManager().sendServerPacket(receiver,
    //                getHandle());
    //    } catch (InvocationTargetException e) {
    //        throw new RuntimeException("Cannot send packet.", e);
    //    }
    //}

    ///**
    // * Send the current packet to all online players.
    // */
    //public static void broadcastPacket() {
    //    ProtocolLibrary.getProtocolManager().broadcastServerPacket(getHandle());
    //}

    ///**
    // * Simulate receiving the current packet from the given sender.
    // *
    // * @param sender - the sender.
    // * @throws RuntimeException If the packet cannot be received.
    // * @deprecated Misspelled. recieve to receive
    // * @see #receivePacket(Player)
    // */
    //@Deprecated
    //public static void recievePacket(Player sender) {
    //    try {
    //        ProtocolLibrary.getProtocolManager().recieveClientPacket(sender,
    //                getHandle());
    //    } catch (Exception e) {
    //        throw new RuntimeException("Cannot recieve packet.", e);
    //    }
    //}

    /**
     * Simulate receiving the current packet from the given sender.
     *
     * @param sender - the sender.
     * @throws RuntimeException if the packet cannot be received.
     */
    public static void receivePacket(Player sender) {
        try {
            ProtocolLibrary.getProtocolManager().recieveClientPacket(sender,
                    getHandle());
        } catch (Exception e) {
            throw new RuntimeException("Cannot receive packet.", e);
        }
    }
}