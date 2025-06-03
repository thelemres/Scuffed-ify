import java.util.ArrayList;

public class MusicLibrary {
    private ArrayList<Song> allSongs;    
    private ArrayList<Album> allAlbums;
    private ArrayList<Artist> allArtists;
    private ArrayList<Playlist> allPlaylists;

    public MusicLibrary() {
        SongLoader loader = new SongLoader("Songs");
        allSongs = loader.loadSongs();
        allAlbums = AlbumLoader.loadAlbums(allSongs);
        allArtists = ArtistLoader.loadArtists(allSongs, allAlbums);
        allPlaylists = PlaylistLoader.loadPlaylists(allSongs, "PlayLists");
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
    //also updates all relevant things
    public void refreshSongs() {
        SongLoader loader = new SongLoader("Songs");
        allSongs = loader.loadSongs();
        allAlbums = AlbumLoader.loadAlbums(allSongs);
        allArtists = ArtistLoader.loadArtists(allSongs, allAlbums);
        allPlaylists = PlaylistLoader.loadPlaylists(allSongs, "PlayLists");
    }
    
    //methods used for sorting albums/songs
    public Song[] sortSongs() {
        return Algorithms.sortSongs(allSongs);
    }
    public Album[] sortAlbums() {
        return Algorithms.sortAlbums(allAlbums);
    }
    public Song[] sortSongsReverse() {
        Song[] sortedSongs = Algorithms.sortSongs(allSongs);
        Song[] reverseSort = new Song[sortedSongs.length];

        for (int i = sortedSongs.length; i > 0; i--) {
            reverseSort[sortedSongs.length - i] = sortedSongs[i];
        }

        return reverseSort;
    }
    public Album[] sortAlbumsReverse() {
        Album[] sortedAlbums = Algorithms.sortAlbums(allAlbums);
        Album[] reverseSort = new Album[sortedAlbums.length];

        for (int i = sortedAlbums.length; i > 0; i--) {
            reverseSort[sortedAlbums.length - i] = sortedAlbums[i];
        }

        return reverseSort;
    }
 }
