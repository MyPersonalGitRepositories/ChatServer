package packet;

import java.util.HashMap;
import java.util.Map;

public class PacketManager {

    public static final Map<Short, Class<? extends OPacket>> packets = new HashMap<>();

    static {
        packets.put((short) 1, PacketAuthorize.class);
    }

    public static OPacket getPacket(short id) {
        try {
            return packets.get(id).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
