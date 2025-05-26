import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Metadata {
    private String title;
    private String artist;
    private String album;
    private String genre;

    private Metadata() {}

    public static Metadata fromFile(File file) {
        Metadata metadata = new Metadata();

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] header = new byte[12];
            if (fis.read(header) != 12) return metadata;

            if (!"RIFF".equals(new String(header, 0, 4, "ASCII")) ||
                !"WAVE".equals(new String(header, 8, 4, "ASCII"))) {
                return metadata;
            }

            while (true) {
                byte[] chunkHeader = new byte[8];
                if (fis.read(chunkHeader) < 8) break;

                String chunkId = new String(chunkHeader, 0, 4, "ASCII");
                int chunkSize = littleEndianInt(chunkHeader, 4);

                if ("LIST".equals(chunkId)) {
                    byte[] listTypeBytes = new byte[4];
                    if (fis.read(listTypeBytes) < 4) break;
                    String listType = new String(listTypeBytes, "ASCII");
                    chunkSize -= 4;

                    if ("INFO".equals(listType)) {
                        int bytesRead = 0;
                        while (bytesRead < chunkSize) {
                            byte[] subchunkHeader = new byte[8];
                            if (fis.read(subchunkHeader) != 8) break;

                            String subchunkId = new String(subchunkHeader, 0, 4, "ASCII");
                            int subchunkSize = littleEndianInt(subchunkHeader, 4);

                            byte[] data = new byte[subchunkSize];
                            if (fis.read(data) != subchunkSize) break;

                            String value = new String(data, "ASCII").trim();

                            switch (subchunkId) {
                                case "INAM":
                                    metadata.title = value;
                                    break;
                                case "IART":
                                    metadata.artist = value;
                                    break;
                                case "IPRD":
                                    metadata.album = value;
                                    break;
                                case "IGNR":
                                    metadata.genre = value;
                                    break;
                            }

                            if (subchunkSize % 2 != 0) fis.read(); // pad byte
                            bytesRead += 8 + subchunkSize + (subchunkSize % 2);
                        }
                    } else {
                        fis.skip(chunkSize);
                    }
                } else {
                    fis.skip(chunkSize);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return metadata;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    private static int littleEndianInt(byte[] bytes, int offset) {
        return (bytes[offset] & 0xFF) |
               ((bytes[offset + 1] & 0xFF) << 8) |
               ((bytes[offset + 2] & 0xFF) << 16) |
               ((bytes[offset + 3] & 0xFF) << 24);
    }
}
