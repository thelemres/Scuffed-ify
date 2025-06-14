import java.util.ArrayList;

public class Album {
    private String title;
    private String artist;
    private ArrayList<Song> songs = new ArrayList<>();

    public Album(String title, String artist) {
        this.title = title;
        this.artist = artist;
    } 
    public Album(Album album) {
        this.title = album.title;
        this.artist = album.artist;
        this.songs = (ArrayList) songs.clone();
    }


    public Album copy() {
        return new Album(this);
    }

    //methods for the arraylist
    public void addSong(Song song) {
        songs.add(song);
    }
    public void removeSong(Song song) {
        songs.remove(song);
    }
    public void printSongs() {
        for (int i = 0; i < songs.size(); i++) {
            //Get by its index, then get it as a string, then print that string
            System.out.println(songs.get(i).toString());
        }
    }

    //getters
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public ArrayList<Song> getSongs() { return songs; }
}