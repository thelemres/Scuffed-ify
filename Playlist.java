import java.util.ArrayList;

public class Playlist {
    private String title;
    private ArrayList<Song> songs = new ArrayList<>();

    public Playlist(String title) {
        this.title = title;
    }

    public Playlist(Playlist playlist) {
        this.title = playlist.title;
        this.songs = (ArrayList) songs.clone();
    }

    public Playlist copy() {
        return new Playlist(this);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public void printSongs() {
        for (int i = 0; i < songs.size(); i++) {
            System.out.println(songs.get(i).toString());
        }
    }

    public String getTitle() { return title; }
}