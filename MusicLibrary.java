import java.util.ArrayList;

public class MusicLibrary {
    private ArrayList<Song> allSongs;
    private ArrayList<Album> allAlbums;

    public MusicLibrary() {
        SongLoader loader = new SongLoader("Songs");
        allSongs = loader.loadSongs();
        allAlbums = AlbumLoader.loadAlbums(allSongs);
    }

    //methods for finding things
    public Song findSongByTitle(String title) {
        for (Song song : allSongs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }
    public Album findAlbumByTitle(String title) {
        for (Album album : allAlbums) {
            if (album.getTitle().equalsIgnoreCase(title)) {
                return album;
            }
        }
        return null;
    }

    //misc methods
    public void addSongToAlbum(String title) {
        Song song = findSongByTitle(title);
        Album album = findAlbumByTitle(title);
        album.addSong(song);
    }
    //adds new songs that have been added to the "Songs" directory
    //also adds new songs to their appropriate albums and creates new albums if needed
    public void refreshSongs() {
        SongLoader loader = new SongLoader("Songs");
        allSongs = loader.loadSongs();
        allAlbums = AlbumLoader.loadAlbums(allSongs);
    }
    
    //methods used for sorting albums/songs
    public Song[] sortSongs() {
        return Algorithms.sortSongs(allSongs);
    }
    public Album[] sortAlbums() {
        return Algorithms.sortAlbums(allAlbums);
    }

}
