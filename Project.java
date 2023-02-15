import java.util.Scanner;

public class Project {
    static Scanner userInput = new Scanner(System.in);
    static String[][] gameBoard;
    static int size;
    static int yPos;
    static int xPos;
    static String[] numHorizontal = { " ", " 01", " 02", " 03", " 04", " 05", " 06", " 07", " 08", " 09", " 10",
            " 11", " 12", " 13", " 14" };
    static String[] numVertical = { " ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
            "14" };
    static int xCounter;
    static int oCounter;
    static int drawCounter;
    static int rules;
    static boolean winner;
    static boolean draw;

    public static void main(String[] args) throws InterruptedException {

        instructions();
        size = getSizeFromUser() + 1;
        gameBoard = new String[size][size];
        intializeBoard();
        printBoard();

        switch (size - 1) {
            case 3:
                rules = 3;
                break;
            case 4:
                rules = 3;
                break;
            case 5:
                rules = 3;
                break;
            case 6:
                rules = 3;
                break;
            case 7:
                rules = 4;
                break;
            case 8:
                rules = 4;
                break;
            case 9:
                rules = 4;
                break;
            case 10:
                rules = 4;
                break;
            case 11:
                rules = 5;
                break;
            case 12:
                rules = 5;
                break;
            case 13:
                rules = 5;
                break;
            case 14:
                rules = 5;
                break;
        }
        System.out.println("You need " + rules + " points to win and gameboard size is " + (size - 1));

        while (true) {
            playerPlaceMark();
            printBoard();
            winCheckHorizontal();
            winCheckVertical();
            winCheckDiagonalDownLeft();
            winCheckDiagonalUpLeft();
            if (winner) {
                break;
            }
            if ((size - 1) * (size - 1) == drawCounter) {
                draw = true;
                System.out.println("It's a draw!!! You couldn't even beat a computer!!! Loser!!!");
                break;
            }
            computerPlaceMark();
            printBoard();
            winCheckHorizontal();
            winCheckVertical();
            winCheckDiagonalDownLeft();
            winCheckDiagonalUpLeft();
            if (winner) {
                break;
            }
            if ((size - 1) * (size - 1) == drawCounter) {
                draw = true;
                System.out.println("It's a draw!!! You couldn't even beat a computer!!! Loser!!!");
                break;
            }
        }
    }

    public static int getSizeFromUser() { // Ask game size from user
        System.out.print("Give gameboard size between 3-14: ");
        int size;
        while (true) {
            try {
                size = Integer.parseInt(userInput.nextLine());
                if (size >= 3 && size <= 14) {
                    break;
                }
            } catch (Exception e) {
                System.out.print("Again! Gameboard size should be between 3 and 14: ");
            }
        }
        return size;
    }

    public static void intializeBoard() { // game area initialization
        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard[y].length; x++) {
                gameBoard[y][x] = "[ ]";
                for (int i = 0; i < size; i++) {
                    if (y == 0) {
                        gameBoard[y][x] = numHorizontal[x];
                    }
                    if (x == 0) {
                        gameBoard[y][x] = numVertical[y];
                    }
                }
            }
        }
    }

    public static void printBoard() { // Print the game
        System.out.print("\033[H\033[2J");
        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard[y].length; x++) {
                System.out.print(gameBoard[y][x]);
            }
            System.out.println("");
        }
    }

    public static int getPositionFromPlayer(String text) { // Ask user coordinates to place X
        int number;
        while (true) {
            try {
                number = Integer.parseInt(userInput.nextLine());
                break;
            } catch (Exception e) {
                System.out.print("Wrong input. Give coordinate: ");
            }
        }
        return number;
    }

    public static void playerPlaceMark() { // Place X with user given coordinates
        System.out.println("Player turn");
        while (true) {
            do {
                System.out.print("Give coordinate-x(horizontal): ");
                xPos = getPositionFromPlayer("x-coordinate");
            } while (xPos < 0 || xPos >= size);
            do {
                System.out.print("Give coordinate-y(vertical): ");
                yPos = getPositionFromPlayer("y-coordinate");
            } while (yPos < 0 || yPos >= size);
            if (gameBoard[yPos][xPos] == "[ ]") {
                break;
            }
            System.out.println("Location is taken. Please, give new coordinates.");
        }
        drawCounter++;
        gameBoard[yPos][xPos] = "[X]";
    }

    public static void computerPlaceMark() { // Opponent (computer) place mark O, with random, in the game area
        System.out.println("Computer turn");
        while (true) {
            int compXPos = (int) (Math.random() * (gameBoard.length));
            int compYPos = (int) (Math.random() * (gameBoard.length));
            if (gameBoard[compYPos][compXPos] == "[ ]") {
                drawCounter++;
                gameBoard[compYPos][compXPos] = "[O]";
                break;
            }
        }
    }

    public static boolean winCheckHorizontal() { // Check if there is enough points for win horizontally
        for (int y = 1; y < size; y++) {
            xCounter = 0;
            oCounter = 0;
            for (int x = 1; x < size; x++) {
                if (gameBoard[y][x].equals("[X]")) {
                    xCounter++;
                    oCounter = 0;
                } else if (gameBoard[y][x].equals("[O]")) {
                    oCounter++;
                    xCounter = 0;
                } else if (gameBoard[y][x].equals("[ ]")) {
                    xCounter = 0;
                    oCounter = 0;
                }
                if (xCounter == rules) {
                    winner = true;
                    System.out.println(
                            "Player won! Congratulations!");
                    break;
                }
                if (oCounter == rules) {
                    winner = true;
                    System.out.println("Computer won!");
                    break;
                }
            }
        }
        return winner;
    }

    public static boolean winCheckVertical() { // Check if there is enough points for win vertically
        for (int x = 1; x < size; x++) {
            xCounter = 0;
            oCounter = 0;
            for (int y = 1; y < size; y++) {
                if (gameBoard[y][x].equals("[X]")) {
                    xCounter++;
                    oCounter = 0;
                } else if (gameBoard[y][x].equals("[O]")) {
                    oCounter++;
                    xCounter = 0;
                } else if (gameBoard[y][x].equals("[ ]")) {
                    xCounter = 0;
                    oCounter = 0;
                }
                if (xCounter == rules) {
                    winner = true;
                    System.out.println(
                            "Player won! Congratulations!");
                    break;
                }
                if (oCounter == rules) {
                    winner = true;
                    System.out.println("Computer won!");
                    break;
                }
            }
        }
        return winner;
    }

    public static boolean winCheckDiagonalDownLeft() { //Check if there is enough points for win from diagonal downleftToupright
        for (int y = 0; y < 2 * size - 3; y++) {
            xCounter = 0;
            oCounter = 0;
            for (int x = 1; x < size; x++) {
                if (y - x <= size - 1 && y - x >= 1) {
                    if (gameBoard[y - x][x].equals("[X]")) {
                        xCounter++;
                        oCounter = 0;
                    } else if (gameBoard[y - x][x].equals("[O]")) {
                        oCounter++;
                        xCounter = 0;
                    } else if (gameBoard[y - x][x].equals("[ ]")) {
                        xCounter = 0;
                        oCounter = 0;
                    }
                }
                if (xCounter == rules) {
                    winner = true;
                    System.out.println(
                            "Player won! Congratulations!");
                    break;
                }
                if (oCounter == rules) {
                    winner = true;
                    System.out.println("Computer won!");
                    break;
                }
            }
        }
        return winner;
    }

    public static boolean winCheckDiagonalUpLeft() { //Check if there is enough points for win from diagonal upleftTodownright
        for (int y = -14; y < 2 * size - 3; y++) {
            xCounter = 0;
            oCounter = 0;
            for (int x = 1; x < size; x++) {
                if (y + x <= size - 1 && y + x >= 1) {
                    if (gameBoard[y + x][x].equals("[X]")) {
                        xCounter++;
                        oCounter = 0;
                    } else if (gameBoard[y + x][x].equals("[O]")) {
                        oCounter++;
                        xCounter = 0;
                    } else if (gameBoard[y + x][x].equals("[ ]")) {
                        xCounter = 0;
                        oCounter = 0;
                    }
                }
                if (xCounter == rules) {
                    winner = true;
                    System.out.println(
                            "Player won! Congratulations!");
                    break;
                }
                if (oCounter == rules) {
                    winner = true;
                    System.out.println("Computer won!");
                    break;
                }
            }
        }
        return winner;
    }

    public static void instructions() { // Instructions for user before game
        System.out.println("Hello world!");
        System.out.println("");
        System.out.println("This game is tic-tac-toe!");
        System.out.println("");
        System.out.println("Please, read instructions below.");
        System.out.println("");
        System.out.println("Gameboard size between 3-6, you need 3 points to win.");
        System.out.println("Gameboard size between 7-10, you need 4 points to win.");
        System.out.println("Gameboard size between 11-14, you need 5 points to win.");
        System.out.println("");
        System.out.print("Press enter, when you have read instructions ");
        userInput.nextLine();
        System.out.println("");
    }
}
