
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("ily");
        System.out.println("ily2");

        SongLoader loader = new SongLoader("Songs");
        ArrayList<Song> allSongs = loader.loadSongs();

        // Lists all songs that were loaded into arraylist allSongs
        for (Song song : allSongs) {
            System.out.println("Loaded song: " + song);
        }

        // To play a song do this and change index
        allSongs.get(1).playAudio();
    }
}
