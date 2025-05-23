public class Song {
    private String title;
    private String artist;
    private String audioFilePath;

    public Song(String title, String artist, String audioFilePath) {
        this.title = title;
        this.artist = artist;
        this.audioFilePath = audioFilePath;
    }

    @Override
    public String toString() {
        return title + " by " + artist;
    }
}