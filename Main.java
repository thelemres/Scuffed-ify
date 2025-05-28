
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("ily");
        System.out.println("ily2");

        SongLoader loader = new SongLoader("Songs");
        ArrayList<Song> allSongs = loader.loadSongs();

        // Lists all songs that were loaded into arraylist allSongs
        // for (Song song : allSongs) {
        //     System.out.println("Loaded song: " + song.getTitle());
        // }

        ArrayList<Album> allAlbums = AlbumLoader.loadAlbums(allSongs);
        // for ( Album album : allAlbums) {
        //     System.out.println("Loaded album: " + album.getTitle());
        //     album.printSongs();
        // }

        // To play a song do this and change index
        // allSongs.get(1).playAudio();
        Song[] a = Algorithms.sortSongs(allSongs);

        for (Song song : a) {
            System.out.println("Song: " + song.getTitle());
        }

        Album[] b = Algorithms.sortAlbums(allAlbums);

        for (Album album : b) {
            System.out.println("Album: " + album.getTitle());
        }

        //System.out.println(allSongs.get(0).getDuration());
    }
}
