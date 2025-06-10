import java.util.ArrayList;

public class Artist {
    final private String name;
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<Album> albums = new ArrayList<>();
    
    public Artist(String name) {
        this.name = name;
    }
    public Artist(Artist artist) {
        this.name = artist.name;
        this.songs = artist.songs;
        this.albums = artist.albums;
    }


    //Song methods
    public void addSong(Song song) {
        songs.add(song);
    }
    public void removeSong(Song song) {
        songs.remove(song);
    }
    public Song findSongByTitle(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        //if nothing found return null
        return null;
    }
    public Song[] sortSongs() {
        return Algorithms.sortSongs(songs);
    }
    public Song[] sortSongsReverse() {
        Song[] sortedSongs = Algorithms.sortSongs(songs);
        Song[] reverseSort = new Song[sortedSongs.length];

        for (int i = sortedSongs.length; i > 0; i--) {
            reverseSort[sortedSongs.length - i] = sortedSongs[i];
        }

        return reverseSort;
    }
    public ArrayList<Song> getSongs() { return songs; }

    //Album methods
    public void addAlbum(Album album) {
        albums.add(album);
    }
    public void removeAlbum(Album album) {
        albums.remove(album);
    }
    public Album findAlbumByTitle(String title) {
        for (Album album : albums) {
            if (album.getTitle().equalsIgnoreCase(title)) {
                return album;
            }
        }
        //if nothing found return null
        return null;
    }
    public Album[] sortAlbums() {
        return Algorithms.sortAlbums(albums);
    }
    public Album[] sortAlbumsReverse() {
        Album[] sortedAlbums = Algorithms.sortAlbums(albums);
        Album[] reverseSort = new Album[sortedAlbums.length];

        for (int i = sortedAlbums.length; i > 0; i--) {
            reverseSort[sortedAlbums.length - i] = sortedAlbums[i];
        }

        return reverseSort;
    }
    public ArrayList<Album> getAlbums() { return albums; }

    //getters
    public String getName() { return name; }

    //misc
    public Artist copy() {
        return new Artist(this);
    }
}