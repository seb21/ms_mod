package mod.mod;

public class Canbus {
    public static void writeObd(byte[] data) {
        if (data != null && data.length > 2) {
            int checksumIndex = data.length - 1;
            byte checksum = 0;
            for (int i = 1; i <= checksumIndex; i++) {
                checksum = (byte) (data[i] + checksum);
            }
            data[checksumIndex] = (byte) (((byte) (checksum & 255)) ^ 255);
            MsWriteCanbus.WriteCanbusDirect(data);
        }
    }
}
