import java.io.File;
import java.util.ArrayList;

public class SongLoader {
    private final File songsDirectory;

    public SongLoader(String directoryPath) {
        this.songsDirectory = new File(directoryPath);
    }

    public ArrayList<Song> loadSongs() {
        ArrayList<Song> songs = new ArrayList<>();

        File[] files = songsDirectory.listFiles((dir, name) -> name.endsWith(".wav"));

        for (File file : files) {
            Metadata meta = Metadata.fromFile(file);

            String title = meta.getTitle();
            String artist = meta.getArtist();

            if (title != null && artist != null) {
                songs.add(new Song(title, artist, file));
            } else {
                System.out.println("Skipping file (missing metadata): " + file.getName());
            }
        }

        return songs;
    }
}
