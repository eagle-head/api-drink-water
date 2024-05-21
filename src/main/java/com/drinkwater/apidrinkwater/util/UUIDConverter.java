package com.drinkwater.apidrinkwater.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public final class UUIDConverter {

    private static final int UUID_BYTE_ARRAY_LENGTH = 16;

    private UUIDConverter() {
    }

    public static byte[] toBytes(UUID uuid) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[UUID_BYTE_ARRAY_LENGTH]);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());

        return byteBuffer.array();
    }

    public static UUID toUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long mostSignificantBits = byteBuffer.getLong();
        long leastSignificantBits = byteBuffer.getLong();

        return new UUID(mostSignificantBits, leastSignificantBits);
    }
}
