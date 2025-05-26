import java.io.File;
import java.nio.file.Path;
import javax.sound.sampled.*;

public class Song implements IPlayableAudio{
    private String title;
    private String artist;
    private String album;
    private String genre;
    private Path audioFilePath;

    public Song(String title, String artist, String album, String genre, File file) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.audioFilePath = file.toPath();
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    public Path getAudioFilePath() {
        return audioFilePath;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n"
             + "Artist: " + artist + "\n"
             + "Album: " + album + "\n"
             + "Genre: " + genre + "\n"; 
    }

    // Play audio on separate thread
    @Override
    public void playAudio() {
        new Thread(() -> {
            try {
                File file = audioFilePath.toFile();
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();

                while (!clip.isRunning()) {
                    Thread.sleep(10);
                }
                while (clip.isRunning()) {
                    Thread.sleep(10);
                }

                clip.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}