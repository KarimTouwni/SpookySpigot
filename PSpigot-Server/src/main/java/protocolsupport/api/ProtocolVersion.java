package protocolsupport.api;

public enum ProtocolVersion {
    MINECRAFT_1_10(210, 15, "1.10"),
    MINECRAFT_1_9_3(110, 14, "1.9.3"),
    MINECRAFT_1_9_2(109, 13, "1.9.2"),
    MINECRAFT_1_9_1(108, 12, "1.9.1"),
    MINECRAFT_1_9(107, 11, "1.9"),
    MINECRAFT_FUTURE(-1, 10),
    MINECRAFT_1_8(47, 9, "1.8"),
    MINECRAFT_1_7_10(5, 8, "1.7.10"),
    MINECRAFT_1_7_5(4, 7, "1.7.5"),
    MINECRAFT_1_6_4(78, 6, "1.6.4"),
    MINECRAFT_1_6_2(74, 5, "1.6.2"),
    MINECRAFT_1_6_1(73, 4, "1.6.1"),
    MINECRAFT_1_5_2(61, 3, "1.5.2"),
    MINECRAFT_1_5_1(60, 2, "1.5.1"),
    MINECRAFT_1_4_7(51, 1, "1.4.7"),
    MINECRAFT_LEGACY(-1, 0),
    UNKNOWN(-1);
    
    private final int id;
    private final int orderId;
    private final String name;
    private static final ProtocolVersion[] byOrderId;

    private ProtocolVersion(int id) {
        this(id, -1);
    }

    private ProtocolVersion(int id, int orderId) {
        this(id, orderId, null);
    }

    private ProtocolVersion(int id, int orderId, String name) {
        this.id = id;
        this.orderId = orderId;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isSupported() {
        return this.name != null;
    }

    public boolean isAfter(ProtocolVersion another) {
        return this.orderId > another.orderId;
    }

    public boolean isAfterOrEq(ProtocolVersion another) {
        return this.orderId >= another.orderId;
    }

    public boolean isBefore(ProtocolVersion another) {
        return this.orderId < another.orderId;
    }

    public boolean isBeforeOrEq(ProtocolVersion another) {
        return this.orderId <= another.orderId;
    }

    public boolean isBetween(ProtocolVersion start, ProtocolVersion end) {
        int startId = Math.min(start.orderId, end.orderId);
        int endId = Math.max(start.orderId, end.orderId);
        return this.orderId >= startId && this.orderId <= endId;
    }

    @Deprecated
    public static ProtocolVersion fromId(int id) {
        switch (id) {
            case 210: {
                return MINECRAFT_1_10;
            }
            case 110: {
                return MINECRAFT_1_9_3;
            }
            case 109: {
                return MINECRAFT_1_9_2;
            }
            case 108: {
                return MINECRAFT_1_9_1;
            }
            case 107: {
                return MINECRAFT_1_9;
            }
            case 47: {
                return MINECRAFT_1_8;
            }
            case 5: {
                return MINECRAFT_1_7_10;
            }
            case 4: {
                return MINECRAFT_1_7_5;
            }
            case 78: {
                return MINECRAFT_1_6_4;
            }
            case 74: {
                return MINECRAFT_1_6_2;
            }
            case 73: {
                return MINECRAFT_1_6_1;
            }
            case 61: {
                return MINECRAFT_1_5_2;
            }
            case 60: {
                return MINECRAFT_1_5_1;
            }
            case 51: {
                return MINECRAFT_1_4_7;
            }
        }
        return UNKNOWN;
    }

    public static ProtocolVersion[] getAllBetween(ProtocolVersion start, ProtocolVersion end) {
        int startId = Math.min(start.orderId, end.orderId);
        int endId = Math.max(start.orderId, end.orderId);
        ProtocolVersion[] between = new ProtocolVersion[endId - startId + 1];
        for (int i = startId; i <= endId; ++i) {
            between[i - startId] = byOrderId[i];
        }
        return between;
    }

    public static ProtocolVersion[] getAllBefore(ProtocolVersion version) {
        return ProtocolVersion.getAllBetween(ProtocolVersion.getOldest(), version);
    }

    public static ProtocolVersion[] getAllAfter(ProtocolVersion version) {
        return ProtocolVersion.getAllBetween(version, ProtocolVersion.getLatest());
    }

    public static ProtocolVersion getLatest() {
        return MINECRAFT_1_8;
    }

    public static ProtocolVersion getOldest() {
        return MINECRAFT_1_4_7;
    }

    static {
        byOrderId = new ProtocolVersion[ProtocolVersion.values().length - 1];
        for (ProtocolVersion version : ProtocolVersion.values()) {
            if (version.orderId == -1) continue;
            ProtocolVersion.byOrderId[version.orderId] = version;
        }
    }
}

