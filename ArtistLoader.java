import java.util.ArrayList;

public class ArtistLoader {

    private ArtistLoader() {}

    private static ArrayList<Artist> allArtists = new ArrayList<>();

    private static Artist findArtist(String title) {
        //return null if allArtists is empty
        if (allArtists.isEmpty()) { return null; }
        
        //iterate through artists until something something matches
        for (Artist artist : allArtists) {
            if (artist.getName().equalsIgnoreCase(title)) {
                return artist;
            }
        }

        //return null if nothing is found
        return null;
    }

    public static ArrayList<Artist> loadArtists(ArrayList<Song> songs, ArrayList<Album> albums) {
        for (Song song : songs) {
            String artistName = song.getArtist();
            String albumTitle = song.getAlbum();

            //add artist if it does not exist
            if (findArtist(artistName) == null) {
                Artist artist = new Artist(artistName);
                allArtists.add(artist);
                artist.addSong(song);
                continue;
            } 
            Artist artist = findArtist(artistName);

            //adds album if not exists
            if (artist.findAlbumByTitle(albumTitle) == null) {
                for (Album album : albums) {
                    if (album.getTitle().equalsIgnoreCase(albumTitle)) {
                        artist.addAlbum(album);
                        break;
                    }
                }
            }
            artist.addSong(song);
        } 

        return allArtists;
    }
}
