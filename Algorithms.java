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
                if (firstCharAsInt == firstCharsAsInts[i]) {
                    sortedArray[i] = song.copy();
                }
            }
        }

        return sortedArray;
    }

    public static Album[] sortAlbums(ArrayList<Album> albums) {
        int[] firstCharsAsInts = new int[albums.size()];
        for (int i = 0; i < albums.size(); i++) {
            firstCharsAsInts[i] = (int) albums.get(i).getTitle().charAt(0);
        }

        mergeSort(firstCharsAsInts, firstCharsAsInts.length);

        Album[] sortedArray = new Album[albums.size()];

        for (int i = 0; i < albums.size(); i++) {
            for (Album album : albums) {
                int firstCharAsInt = (int) album.getTitle().charAt(0);

                //check if the first chars are the same
                if (firstCharAsInt == firstCharsAsInts[i]) {
                    sortedArray[i] = album.copy();
                }
            }
        }

        return sortedArray;
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