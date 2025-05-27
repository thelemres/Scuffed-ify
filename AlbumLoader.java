import java.util.ArrayList;

public class AlbumLoader {
    private static ArrayList<Album> allAlbums = new ArrayList<>();

    private static Album findAlbumByTitle(String title) {
        //return null if allAlbums is empty
        if (allAlbums.isEmpty()) { return null; }
        
        //iterate through albums until something something matches
        for (Album album : allAlbums) {
            if (album.getTitle().equalsIgnoreCase(title)) {
                return album;
            }
        }

        //return null if nothing is found
        return null;
    }

    public static ArrayList<Album> loadAlbums(ArrayList<Song> songs) {
        for (Song song : songs) {
            String title = song.getAlbum();
            String artist = song.getArtist();

            //add album if it does not exist
            if (findAlbumByTitle(title) == null) {
                Album album = new Album(title, artist);
                allAlbums.add(album);
                album.addSong(song);
                continue;
            }

            Album album = findAlbumByTitle(title);
            album.addSong(song);
        } 

        return allAlbums;
    }
}
