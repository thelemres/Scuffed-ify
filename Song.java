import java.io.File;
import java.nio.file.Path;
import javax.sound.sampled.*;

public class Song implements IPlayableAudio {
    private String title;
    private String artist;
    private String album;
    private String genre;
    private Path audioFilePath;

    // Buffer variables
    private File file;
    private Clip clip;

    public Song(String title, String artist, String album, String genre, File file) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.file = file;
        this.audioFilePath = file.toPath();
    }

    public Song(Song song) {
        this.title = song.title;
        this.artist =song.artist;
        this.album = song.album;
        this.genre = song.genre;
        this.file = song.file;
        this.audioFilePath = song.audioFilePath;
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

    // @Override
    // public Object clone() throws CloneNotSupportedException {
    //     return new Song(this);
    // }

    public Song copy() {
        return new Song(this);
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n"
             + "Artist: " + artist + "\n"
             + "Album: " + album + "\n"
             + "Genre: " + genre + "\n"; 
    }

    public double getDuration() {
        AudioInputStream stream = null;

        try {
            stream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = stream.getFormat();
            return file.length() / format.getSampleRate() / (format.getSampleSizeInBits() / 8.0) / format.getChannels();
        } catch (Exception e) {
            return -1;
        } finally {
            try {
                stream.close();
            } catch (Exception ex) {
            }
        }
    }

    // Play audio on separate thread
    @Override
    public synchronized void playAudio() {
        // Stop currently playing clip if exists
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }

        try {
            File file = audioFilePath.toFile();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            // Optionally, start a thread to close clip after playing finishes
            new Thread(() -> {
                try {
                    while (clip.isRunning()) {
                        Thread.sleep(10);
                    }
                    clip.close();
                } catch (InterruptedException ignored) {
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Optionally, add a stop method:
    public synchronized void stopAudio() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}