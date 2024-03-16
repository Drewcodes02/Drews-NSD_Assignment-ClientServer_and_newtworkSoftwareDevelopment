package Assigment.utils;

public enum Config {

    CHAR_LIMIT(1000),
    LOGIN_WIDTH(256),
    LOGIN_HEIGHT(200),
    MAIN_WIDTH(1280),
    MAIN_HEIGHT(720),
    PICTURE_WIDTH(540);

    private final int temp;

    Config(final int temp) {
        this.temp = temp;
    }

    public int get() {
        return temp;
    }

}

