import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Main {
    public static void main(String[] args) {
        MusicLibrary musicLibrary = new MusicLibrary();

        // Make overall frame
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Make card layout for screen-switching
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // Main Menu panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panel1 = new JPanel(new GridLayout(1, 1));
        JLabel title = new JLabel("Scuffed-ify", SwingConstants.CENTER);
        Font titleFont = title.getFont();
        title.setFont(new Font(titleFont.getName(), Font.BOLD, 36));
        panel1.add(title);


        JPanel panel2 = new JPanel(new GridLayout(2, 2));
        JButton songsButton = new JButton("Songs");
        songsButton.addActionListener(e -> cardLayout.show(cardPanel, "songsPanel"));
        panel2.add(songsButton);

        JButton albumsButton = new JButton("Albums");
        albumsButton.addActionListener(e -> cardLayout.show(cardPanel, "albumsPanel"));
        panel2.add(albumsButton);

        JButton artistsButton = new JButton("Artists");
        artistsButton.addActionListener(e -> cardLayout.show(cardPanel, "artistsPanel"));
        panel2.add(artistsButton);

        JButton playlistsButton = new JButton("Playlists");
        playlistsButton.addActionListener(e -> cardLayout.show(cardPanel, "playlistsPanel"));
        panel2.add(playlistsButton);

        mainPanel.add(panel1);
        mainPanel.add(panel2);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel currentlyPlayingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel currentlyPlayingLabel = new JLabel("No song playing");
        currentlyPlayingPanel.add(currentlyPlayingLabel);
        currentlyPlayingPanel.setPreferredSize(new Dimension(frame.getWidth(), 60));
        bottomPanel.add(currentlyPlayingPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(1,2));

        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> {
            ArrayList<Song> songs = musicLibrary.getSongs();
            for (Song song : songs) {
                if (!song.isPaused()) {
                    song.pauseAudio();
                }
            }
        });
        controlPanel.add(pauseButton);

        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> {
            ArrayList<Song> songs = musicLibrary.getSongs();
            for (Song song : songs) {
                if (song.isPlaying() && song.isPaused()) {
                    song.unpauseAudio();
                }
            }
        });
        controlPanel.add(playButton);

        bottomPanel.add(controlPanel, BorderLayout.EAST);

        // Songs Screen Panel
        JPanel songsPanel = new JPanel(new BorderLayout());
        JButton backButton1 = new JButton("Back");
        backButton1.addActionListener(e -> cardLayout.show(cardPanel, "mainPanel"));

        JPanel songsTopPanel = new JPanel(new BorderLayout());
        songsTopPanel.add(backButton1, BorderLayout.WEST);

        JLabel songsTitleLabel = new JLabel("All Songs", SwingConstants.CENTER);
        songsTopPanel.add(songsTitleLabel, BorderLayout.CENTER);
        songsPanel.add(songsTopPanel, BorderLayout.NORTH);
        
        ArrayList<Song> songs = musicLibrary.getSongs();

        String[] songStrings = new String[songs.size()];
        for (int i = 0; i < songs.size(); i++) {
            Song s = songs.get(i);
            songStrings[i] = s.getTitle() + " - " + s.getArtist();
        }

        JList<String> songList = new JList<>(songStrings);
        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(songList);
        songsPanel.add(scrollPane, BorderLayout.CENTER);

        songList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int index = songList.getSelectedIndex();
                    if (index >= 0) {
                        Song selectedSong = songs.get(index);

                        for (Song song : songs) {
                            song.stopAudio();
                        }

                        selectedSong.playAudio();

                        currentlyPlayingLabel.setText("Now Playing: " + selectedSong.getTitle() + " - " + selectedSong.getArtist());
                    }
                }
            }
        });

        // Albums Screen Panel
        JPanel albumsPanel = new JPanel(new BorderLayout());
        albumsPanel.add(new JLabel("Welcome to the Albums screen!", SwingConstants.CENTER), BorderLayout.CENTER);
        JButton backButton2 = new JButton("Back");
        backButton2.addActionListener(e -> cardLayout.show(cardPanel, "mainPanel"));
        albumsPanel.add(backButton2, BorderLayout.NORTH);

        // Artists Screen Panel
        JPanel artistsPanel = new JPanel(new BorderLayout());
        artistsPanel.add(new JLabel("Welcome to the Artists screen!", SwingConstants.CENTER), BorderLayout.CENTER);
        JButton backButton3 = new JButton("Back");
        backButton3.addActionListener(e -> cardLayout.show(cardPanel, "mainPanel"));
        artistsPanel.add(backButton3, BorderLayout.NORTH);

        // Playlists Screen Panel
        JPanel playlistsPanel = new JPanel(new BorderLayout());
        playlistsPanel.add(new JLabel("Welcome to the Playlists screen!", SwingConstants.CENTER), BorderLayout.CENTER);
        JButton backButton4 = new JButton("Back");
        backButton4.addActionListener(e -> cardLayout.show(cardPanel, "mainPanel"));
        playlistsPanel.add(backButton4, BorderLayout.NORTH);

        // Load all panels to cardPanel
        cardPanel.add(mainPanel, "mainPanel");
        cardPanel.add(songsPanel, "songsPanel");
        cardPanel.add(albumsPanel, "albumsPanel");
        cardPanel.add(artistsPanel, "artistsPanel");
        cardPanel.add(playlistsPanel, "playlistsPanel");

        frame.add(cardPanel, BorderLayout.CENTER);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}