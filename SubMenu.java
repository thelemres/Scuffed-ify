public abstract class SubMenu {
    final protected  MusicLibrary library;
    protected SubMenu(MusicLibrary library) {
        this.library = library;
    }

    protected abstract void printSortAlphabet();
    protected abstract void printReverseSortAlphabet();
    public abstract void mainSubMenu(SortType type);
}
