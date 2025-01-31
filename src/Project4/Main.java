package Project4;

import java.util.Scanner;

public class Main {
    public static final Scanner sc = new Scanner(System.in);
    public static char[] positions = new char[9];
    public static PlayersTurn playersTurn;

    public static void main(String[] args) {
        resetBoard();
        while(true) {
            showMenu();
            String choice = getInput();
            if (choice.equalsIgnoreCase("q")) {
                System.out.println("Goodbye!");
                break;
            }
            if (choice.equalsIgnoreCase("n")) {
                System.out.println("Preparing new game...");
                resetBoard();
                continue;
            }
            char ch = choice.charAt(0);
            if (positions[ch - 49] == 'X' || positions[ch - 49] == 'O') {
                System.out.println("Position already occupied");
                continue;
            }
            positions[ch - 49] = (playersTurn == PlayersTurn.PLAYER_1) ? 'X' : 'O';
             if (checkTriliza()) {
                 System.out.printf("Player %s has won!\n", playersTurn);
                 System.out.println("Preparing new game...");
                 resetBoard();
                 continue;
             }
             if (checkDraw()) {
                 System.out.println("Its a draw!");
                 System.out.println("Preparing new game...");
                 resetBoard();
                 continue;
             }
            playersTurn = playersTurn == PlayersTurn.PLAYER_1 ? PlayersTurn.PLAYER_2 : PlayersTurn.PLAYER_1;
        }
    }

    private static boolean checkDraw() {
        for (int i = 0; i < 9; i++) {
            if (positions[i] != 'X' && positions[i] != 'O')
                return false;
        }
        return true;
    }

    private static boolean checkTriliza() {
        // Check Horizontal
        for (int i = 0; i < 9; i += 3) {
            if (positions[i] == positions[i + 1] && positions[i + 1] == positions[i + 2])
                return true;
        }
        // Check Vertical
        for (int i = 0; i < 3; i++) {
            if (positions[i] == positions[i + 3] && positions[i + 3] == positions[i + 6])
                return true;
        }
        // Check Diagonal
        if (positions[0] == positions[4] && positions[4] == positions[8] ||
            positions[2] == positions[4] && positions[4] == positions[6])
            return true;

        return false;
    }

    private static void showMenu() {
        System.out.println("Press Q/q to exit or N/n for new game");
        if (playersTurn == PlayersTurn.PLAYER_1)
            System.out.println("Player 1, choose a position to place X");
        else
            System.out.println("Player 2, choose a position to place O");
        printBoard();
    }

    private static void printBoard() {
        System.out.printf("[ %c %c %c ]\n[ %c %c %c ]\n[ %c %c %c ]\n", positions[0], positions[1],positions[2],
                positions[3], positions[4], positions[5], positions[6], positions[7], positions[8]);
    }

    private static String getInput() {
        String s;
        while (true) {
            s = sc.nextLine();
            if (s.length() != 1) {
                System.out.println("Invalid input. Try again");
                continue;
            }
            try {
                if (s.equalsIgnoreCase("q") || s.equalsIgnoreCase("n") ||
                        (Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 9)) {
                    return s;
                }
                System.out.println("Invalid input. Try again");
            } catch (NumberFormatException ignored) {
                System.out.println("Invalid input. Try again");
            }
        }
    }

    private static void resetBoard() {
        playersTurn = PlayersTurn.PLAYER_1;
        for (int i = 0; i < 9; i++) {
            positions[i] = (char) (i + 49);
        }
    }

    public enum PlayersTurn {
        PLAYER_1,
        PLAYER_2;
    }
}
