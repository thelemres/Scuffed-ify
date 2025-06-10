import java.util.ArrayList;

public class ArtistSubMenu extends SubMenu {
    private ArrayList<Artist> artists;
    final private String MENU = """

                                To view more about a specifc artist type their name
                                To sort the list of artists alphabetically type "sort"
                                To sort the list of artists to sort reverse alphabetically type "reverse"
                                """;

    public ArtistSubMenu(MusicLibrary library) {
        super(library);
        artists = library.getArtists();
    }


    @Override
    protected void printSortAlphabet() {
        Artist[] sortedArtistsArray = library.sortArtists();
        for (Artist artist : sortedArtistsArray) {
            System.out.println(artist.getName());
        }
    }
    @Override
    protected void printReverseSortAlphabet() {
        Artist[] sortedArtistsArray = library.sortArtistsReverse();
        for (Artist artist : sortedArtistsArray) {
            System.out.println(artist.getName());
        }
    }

    @Override
    public void mainSubMenu(SortType type) {
        library.clearConsole();
        System.out.println("***ALL ARTISTS***");

        //print all artists depending on sort method
        switch (type) {
            case NONE -> {
                Artist[] sortedArtistsArray = Algorithms.sortArtists(artists);
                for (Artist artist : sortedArtistsArray) {
                    System.out.println(artist.getName());
                }
            }
            case ALPHABET -> {
                printSortAlphabet();
            }
            case REVERSE_ALPHABET -> {
                printReverseSortAlphabet();
            }
        }
            
        String userAction = Input.getUserString(MENU);
        userAction = userAction.toLowerCase(); //lowercase string to make case-insensitive switch statement

        //handle user input
        switch (userAction) {
            case "sort" -> {
                mainSubMenu(SortType.ALPHABET);
            }
            case "reverse" -> {
                mainSubMenu(SortType.REVERSE_ALPHABET);
            }
            default -> {
                //if sort and reverse are not the picked option
                //then the option must be the name of the artist
                //or a misspelling
                Artist artist = library.findArtistByName(userAction);

                // if artist is not found, run the submenu again with the same argument
                if (artist == null) {
                    mainSubMenu(type);
                }
            }
        }
    }
    

    public void artistMenu (Artist artist) {
        
    }
}