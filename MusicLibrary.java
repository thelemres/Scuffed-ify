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
    public Playlist findPlaylistByTitle(String title) {
        for (Playlist playlist : allPlaylists) {
            if (playlist.getTitle().equalsIgnoreCase(title)) {
                return playlist;
            }
        }
        return null;
    }
    public Artist findArtistByName(String name) {
        for (Artist artist : allArtists) {
            if (artist.getName().equalsIgnoreCase(name)) {
                return artist;
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
        allPlaylists = PlaylistLoader.loadPlaylists(allSongs, "Playlists");
    }
    public void clearConsole() {
        System.out.print("\033[H\033[2J"); // clears out the console
    }

    //methods used for sorting
    public Song[] sortSongs() {
        return Algorithms.sortSongs(allSongs);
    }
    public Album[] sortAlbums() {
        return Algorithms.sortAlbums(allAlbums);
    }
    public Artist[] sortArtists() {
        return Algorithms.sortArtists(allArtists);
    }
    public Playlist[] sortPlaylists() {
        return Algorithms.sortPlaylists(allPlaylists);
    }
    public Song[] sortSongsReverse() {
        Song[] sortedSongs = Algorithms.sortSongs(allSongs);
        Song[] reverseSort = new Song[sortedSongs.length];

        for (int i = sortedSongs.length - 1; i > 0; i--) {
            reverseSort[sortedSongs.length - i] = sortedSongs[i];
        }

        return reverseSort;
    }
    public Album[] sortAlbumsReverse() {
        Album[] sortedAlbums = Algorithms.sortAlbums(allAlbums);
        Album[] reverseSort = new Album[sortedAlbums.length];

        for (int i = sortedAlbums.length - 1; i > 0; i--) {
            reverseSort[sortedAlbums.length - i] = sortedAlbums[i];
        }

        return reverseSort;
    }
    public Playlist[] sortPlaylistsReverse() {
        Playlist[] sortedPlaylists = Algorithms.sortPlaylists(allPlaylists);
        Playlist[] reverseSort = new Playlist[sortedPlaylists.length];

        for (int i = sortedPlaylists.length - 1; i > 0; i--) {
            reverseSort[sortedPlaylists.length - i] = sortedPlaylists[i];
        }

        return reverseSort;
    }
    public Artist[] sortArtistsReverse() {
        Artist[] sortedArtists = Algorithms.sortArtists(allArtists);
        Artist[] reverseSort = new Artist[sortedArtists.length];

        for (int i = sortedArtists.length - 1; i > 0; i--) {
            reverseSort[sortedArtists.length - i] = sortedArtists[i];
        }

        return reverseSort;
    }
    
    //getters
    public ArrayList<Artist> getArtists() { return allArtists; }
    public ArrayList<Playlist> getPlaylists() { return allPlaylists; }
    public ArrayList<Album> getAlbums() { return allAlbums; }
    public ArrayList<Song> getSongs() { return allSongs; }
}