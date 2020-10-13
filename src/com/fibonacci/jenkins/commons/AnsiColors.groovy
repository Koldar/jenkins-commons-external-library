class AnsiColors {
    static final DEFAULT = '\033[39m';
    static final RED = '\033[31m';
    static final BLACK = '\033[30m';
    static final GREEN = '\033[32m';
    static final YELLOW = '\033[33m';
    static final BLUE = '\033[34m';
    static final MAGENTA = '\033[35m';
    static final CYAN = '\033[36m';
    static final LIGHTGRAY = '\033[37m';
    static final DARKGRAY = '\033[90m';
    static final LIGHTRED = '\033[91m';
    static final LIGHTGREEN = '\033[92m';
    static final LIGHTYELLOW = '\033[93m';
    static final LIGHTBLUE = '\033[94m';
    static final LIGHTMAGENTA = '\033[95m';
    static final LIGHTCYAN = '\033[96m';
    static final WHITE = '\033[97m';


    static useColorEcho(String message, String color = "default") {
        def xtermColor = "";
        switch (color) {
            case "black": 
                xtermColor = AnsiColors.BLACK;
                break
            case "red": 
                xtermColor = AnsiColors.RED;
                break
            case "yellow": 
                xtermColor = AnsiColors.YELLOW;
                break
            case "green": 
                xtermColor = AnsiColors.GREEN;
                break
            case "blue":   
                xtermColor = AnsiColors.BLUE;
                break
            case "white": 
                xtermColor = AnsiColors.WHITE;
                break
            case "cyan": 
                xtermColor = AnsiColors.CYAN;
                break
            case "magenta": 
                xtermColor = AnsiColors.MAGENTA;
                break
            default: 
                throw new IllegalArgumentException("Invalid color ${color}")
        }

        echo xtermColor + message + AnsiColors.DEFAULT
        //echo message
    }
}