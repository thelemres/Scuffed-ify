public class Main {
    public static void main(String[] args) {
        MusicLibrary library = new MusicLibrary();
        ArtistSubMenu artistSubMenu = new ArtistSubMenu(library);
        final String MENU = """
                            Welcome to Scuffed-ify
                            1. View Artists
                            2. View Albums
                            3. View Song
                            4. View Playlists
                            5. Refresh songs (add new songs from the songs folder)
                            6. Exit
                            """;
        while (true) {
            int selectedOption = Input.getUserInt(MENU);
            switch (selectedOption) {
                case 1 -> {
                    artistSubMenu.mainSubMenu(SortType.NONE);
                }
            }
        }
    }
}
