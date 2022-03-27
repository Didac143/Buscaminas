public enum Difficulty {
    EASY(10),
    MEDIUM(15),
    HARD(20);

    private int value;

    Difficulty(int i) {
        this.value = i;
    }

    public int get() {
        return value;
    }
}
