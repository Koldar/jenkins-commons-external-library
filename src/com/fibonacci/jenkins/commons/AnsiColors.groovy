class AnsiColors {
    static final DEFAULT = "\033[39m";
    static final RED = "\033[31m";
    static final BLACK = "\033[30m";
    static final GREEN = "\033[32m";
    static final YELLOW = "\033[33m";
    static final BLUE = "\033[34m";
    static final MAGENTA = "\033[35m";
    static final CYAN = "\033[36m";
    static final LIGHTGRAY = "\033[37m";
    static final DARKGRAY = "\033[90m";
    static final LIGHTRED = "\033[91m";
    static final LIGHTGREEN = "\033[92m";
    static final LIGHTYELLOW = "\033[93m";
    static final LIGHTBLUE = "\033[94m";
    static final LIGHTMAGENTA = "\033[95m";
    static final LIGHTCYAN = "\033[96m";
    static final WHITE = "\033[97m";


    static useColorEcho(String message, String color = "default") {
        def xtermColor = "";
        switch (color) {
            "black": xtermColor = AnsiContants.BLACK;
            "red": xtermColor = AnsiContants.RED;
            "yellow": xtermColor = AnsiContants.YELLOW;
            "green": xtermColor = AnsiContants.GREEN;
            "blue": xtermColor = AnsiContants.BLUE;
            "white": xtermColor = AnsiContants.WHITE;
            "cyan": xtermColor = AnsiContants.CYAN;
            "magenta": xtermColor = AnsiContants.MAGENTA;
            default: throw new IllegalArgumentException("Invalid color ${color}")
        }

        echo "${xtermColor}${message}${AnsiConstants.DEFAULT}"
    }
}