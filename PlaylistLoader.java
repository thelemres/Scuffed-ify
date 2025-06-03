import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.nio.file.FileSystemNotFoundException;
import java.util.Scanner; //Import the Scanner class to read the files

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

    private static boolean doesPlaylistExist(String title) {
        if (allPlaylists.isEmpty()) { return false; }

        for (Playlist playlist : allPlaylists) {
            if (playlist.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }

        return false;
    }
    
    private static Song findSongByTitle(String title, ArrayList<Song> songs) {
        if (songs.isEmpty()) { return null; }

        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }

        return null;
    }

    public static ArrayList<Playlist> loadPlaylists(ArrayList<Song> songs, String playListPath) {
        File playlistDirectory = new File(playListPath);
        File[] playlistsFiles = playlistDirectory.listFiles((dir, name) -> name.endsWith("txt"));

        for (File playlistFile : playlistsFiles) {
            Playlist playlist;
            String playlistName = playlistFile.getName();

            //check if playlist object already exists, if not make new one
            if (doesPlaylistExist(playlistName)) {
                playlist = findPlaylistByTitle(playlistName);
            } else {
                playlist = new Playlist(playlistName);
            }

                try (Scanner reader = new Scanner(playlistFile);) {
                    while (reader.hasNextLine()) {
                        Song song = findSongByTitle(reader.nextLine(), songs);
                        playlist.addSong(song);
                    }

                    reader.close();
                    allPlaylists.add(playlist);
                } catch (Exception e) {
                    
                }
        }
        return allPlaylists;
    }
}