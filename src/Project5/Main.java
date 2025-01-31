package Project5;

public class Main {
    static boolean[][] seats = new boolean[12][30];

    public static void main(String[] args) {
        book('C',2);
        book('C',2);
        cancel('C',2);
        cancel('C',2);
        book('A',0);
        book('@',0);
        book('A',-1);
        cancel('L',29);
        cancel('M',29);
        cancel('L',30);
    }

    private static void book(char column, int row) {
        if (!validate(column, row)) return;
        if (seats[column - 65][row])
            System.out.println("Seat " + column + row + " is already booked");
        else {
            seats[column - 65][row] = true;
            System.out.println("Seat " + column + row + " is now booked :)");
        }
    }

    private static void cancel(char column, int row) {
        if (!validate(column, row)) return;
        if (!seats[column - 65][row])
            System.out.println("Seat " + column + row + " is already empty and can't be cancelled");
        else {
            seats[column - 65][row] = false;
            System.out.println("Seat " + column + row + " has been cancelled.");
        }
    }

    private static boolean validate(char c,int r) {
        if (c > 76 || c < 65) {
            System.out.println("Wrong column");
            return false;
        }
        if (r < 0 || r > 29) {
            System.out.println("Wrong row");
            return false;
        }
        return true;
    }
}
