import java.util.ArrayList;

public class Algorithms {
    private Algorithms() {}

    //takes the first character of the title of each song
    //then converts that character to an int
    //creates an array with all the characters (converted to ints)
    //runs merge sort
    //returns an array of sorted songs
    public static Song[] sortSongs(ArrayList<Song> songs) {
        int[] firstCharsAsInts = new int[songs.size()];
        for (int i = 0; i < songs.size(); i++) {
            firstCharsAsInts[i] = (int) songs.get(i).getTitle().charAt(0);
        }

        mergeSort(firstCharsAsInts, firstCharsAsInts.length);

        Song[] sortedArray = new Song[songs.size()];

        for (int i = 0; i < songs.size(); i++) {
            for (Song song : songs) {
                int firstCharAsInt = (int) song.getTitle().charAt(0);

                //check if the first chars are the same
                if (firstCharAsInt == firstCharsAsInts[i] && !isSongInArray(sortedArray, song)) {
                    sortedArray[i] = song.copy(); //copies since sortedArray[i] = song was causing a nullptr
                }
            }
        }

        return sortedArray;
    }

    //works the same way as sortSongs
    public static Album[] sortAlbums(ArrayList<Album> albums) {
        int[] firstCharsAsInts = new int[albums.size()];
        for (int i = 0; i < albums.size(); i++) {
            firstCharsAsInts[i] = (int) albums.get(i).getTitle().toLowerCase().charAt(0);
        }

        mergeSort(firstCharsAsInts, firstCharsAsInts.length);

        Album[] sortedArray = new Album[albums.size()];

        for (int i = 0; i < albums.size(); i++) {
            for (Album album : albums) {
                int firstCharAsInt = (int) album.getTitle().toLowerCase().charAt(0);

                //check if the first chars are the same
                if (firstCharAsInt == firstCharsAsInts[i] && !isAlbumInArray(sortedArray, album)) {
                    sortedArray[i] = album.copy();
                }
            }
        }

        return sortedArray;
    }

    //works the same way as sortSongs
    public static Playlist[] sortPlaylists(ArrayList<Playlist> playlists) {
        int[] firstCharsAsInts = new int[playlists.size()];
        for (int i = 0; i < playlists.size(); i++) {
            firstCharsAsInts[i] = (int) playlists.get(i).getTitle().toLowerCase().charAt(0);
        }

        mergeSort(firstCharsAsInts, firstCharsAsInts.length);

        Playlist[] sortedArray = new Playlist[playlists.size()];

        for (int i = 0; i < playlists.size(); i++) {
            for (Playlist playlist : playlists) {
                int firstCharAsInt = (int) playlist.getTitle().toLowerCase().charAt(0);

                //check if the first chars are the same
                if (firstCharAsInt == firstCharsAsInts[i] && !isPlaylistInArray(sortedArray, playlist)) {
                    sortedArray[i] = playlist.copy();
                }
            }
        }

        return sortedArray;
    }

    //works the same way as sortSongs
    public static Artist[] sortArtists(ArrayList<Artist> artists) {
        System.out.println("bruhhh");
        int[] firstCharsAsInts = new int[artists.size()];
        for (int i = 0; i < artists.size(); i++) {
            firstCharsAsInts[i] = (int) artists.get(i).getName().toLowerCase().charAt(0);
        }

        mergeSort(firstCharsAsInts, firstCharsAsInts.length);
        

        Artist[] sortedArray = new Artist[artists.size()];

        for (int i = 0; i < artists.size(); i++) {
            for (Artist artist : artists) {
                int firstCharAsInt = (int) artist.getName().toLowerCase().charAt(0);

                //check if the first chars are the same
                if (firstCharAsInt == firstCharsAsInts[i] && !isArtistInArray(sortedArray, artist)) {
                    sortedArray[i] = artist.copy();
                }
            }
        }
        return sortedArray;
    }
    
    //checkers, check if the object is already in the sorted array
    private static boolean isArtistInArray(Artist[] artists, Artist artist) {
        for (Artist art : artists) {
            if (art == null) {
                continue;
            }

            //check if the names of both of the artists are the same
            if (art.getName().equalsIgnoreCase(artist.getName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isAlbumInArray(Album[] albums, Album album) {
        for (Album alb : albums) {
            if (alb == null) {
                continue;
            }

            //check if the names of both of the albums are the same
            if (alb.getTitle().equalsIgnoreCase(album.getTitle())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSongInArray(Song[] songs, Song song) {
        for (Song s : songs) {
            if (s == null) {
                continue;
            }

            //check if the names of both of the songs are the same
            if (s.getTitle().equalsIgnoreCase(song.getTitle())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPlaylistInArray(Playlist[] playlists, Playlist playlist) {
        for (Playlist play : playlists) {
            if (playlist == null) {
                continue;
            }

            //check if the names of both of the playlists are the same
            if (playlist.getTitle().equalsIgnoreCase(playlist.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public static void mergeSort(int arr[], int length) {
        if (length < 2) {
            return;
        }
        
        int mid = length / 2;

        int[] leftArray = new int[mid];
        int[] rightArray = new int[length-mid];

        System.arraycopy(arr, 0, leftArray, 0, leftArray.length); //copy to left array
        System.arraycopy(arr, mid, rightArray, 0, rightArray.length); //copy to right array

        mergeSort(leftArray, leftArray.length); //merge sort on the left array
        mergeSort(rightArray, rightArray.length); // merge sort on the right array

        merge(arr, leftArray, rightArray, -1,-1, -1);
    }

    private static void merge(int[] arr,int[] leftArray, int[] rightArray, int leftIndex, int rightIndex, int arrayIndex) {

        if (leftIndex == -1 && rightIndex == -1 && arrayIndex == -1) { //check if leftIndex & rightIndex & arrayIndex actually exist
            leftIndex = 0;
            rightIndex = 0;
            arrayIndex = 0;
        }
        
        //base cases
        int amountOfRemainingNums;
        //copy remaining numbers from right array to the main array
        if (leftIndex >= leftArray.length) {
            amountOfRemainingNums = rightArray.length - rightIndex;
            System.arraycopy(rightArray, rightIndex, arr, arrayIndex, amountOfRemainingNums);
            return;
        }
        //copy remaining numbers from leftArray to the main array
        if (rightIndex >= rightArray.length) {
            amountOfRemainingNums = leftArray.length - leftIndex;
            System.arraycopy(leftArray, leftIndex, arr, arrayIndex, amountOfRemainingNums);
            return;
        }
        
        if (leftArray[leftIndex] < rightArray[rightIndex]) {
            arr[arrayIndex] = leftArray[leftIndex];
            merge(arr, leftArray, rightArray, leftIndex + 1, rightIndex, arrayIndex + 1);
        } else {
            arr[arrayIndex] = rightArray[rightIndex];
            merge(arr, leftArray, rightArray, leftIndex, rightIndex + 1, arrayIndex + 1);
        }
    }
}