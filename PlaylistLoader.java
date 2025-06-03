import java.util.ArrayList;

public class PlaylistLoader {
    
    private PlaylistLoader() {}

    private static ArrayList<Playlist> allPlaylists = new ArrayList<>();

    private static Playlist findPlaylistByTitle(String title) {
        if (allPlaylists.isEmpty()) { return null; }

        for (Playlist playlist : allPlaylists) {
            if (playlist.getTitle().equalsIgnoreCase(title)) {
                return playlist;
            }
        }

        return null;
    }

    public static ArrayList<Playlist> loadPlaylists(ArrayList<Song> songs) {
        for (Song song : songs) {
            // ADD THIS TO USE TXT FILE FOR PLAYLISTS
        }
    }
}