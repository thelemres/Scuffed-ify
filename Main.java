
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("ily");
        System.out.println("ily2");

        SongLoader loader = new SongLoader("Songs");
        ArrayList<Song> allSongs = loader.loadSongs();

        // Lists all songs that were loaded into arraylist allSongs
        for (Song song : allSongs) {
            System.out.println("Loaded song: " + song.getTitle());
        }

        ArrayList<Album> allAlbums = AlbumLoader.loadAlbums(allSongs);
        for ( Album album : allAlbums) {
            System.out.println("Loaded album: " + album.getTitle());
            album.printSongs();
        }

        Algorithms.sortSongs(allSongs);

        // To play a song do this and change index
        // allSongs.get(1).playAudio();

        for (Song song : Algorithms.sortSongs(allSongs)) {
            System.out.println("Song: " + song.getTitle());
        }

        System.out.println(allSongs.get(0).getDuration());
    }
}
