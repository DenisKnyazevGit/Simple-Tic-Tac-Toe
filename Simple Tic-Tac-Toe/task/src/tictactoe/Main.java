package tictactoe;

import java.util.*;

public class Main {
    /**
     * Константа для хранения символа, которым ходит первый игрок
     */
    public static final char FIRST_PLAYER_CHAR = 'X';
    /**
     * Константа для хранения символа, которым ходит второй игрок
     */
    public static final char SECOND_PLAYER_CHAR = 'O';
    /**
     * Константа для хранения пустого символа, показывающего свободную клетку на поле
     */
    public static final char EMPTY_CHAR = ' ';

    /**
     * Функция принимает двумерный массив символьных элементов, где каждый элемент соответствует
     * сделанному ходу игрока - занятой клетке, либо пустой клетке на поле. Функция запускается после каждого хода
     * и проверяет результат игры: определен победитель, игра продолжается или заняты все клетки на поле,
     * но победителя нет - ничья
     *
     * @param elements двумерный массив символов, соответствующих клеткам на поле
     * @return Возвращает строку с результатом проверки
     */
    public static String check(char[][] elements) {
        if (elements[0][0] != EMPTY_CHAR && elements[0][0] == elements[0][1] && elements[0][0] == elements[0][2]) {
            return elements[0][0] + " wins";
        }
        if (elements[1][0] != EMPTY_CHAR && elements[1][0] == elements[1][1] && elements[1][0] == elements[1][2]) {
            return elements[1][0] + " wins";
        }
        if (elements[2][0] != EMPTY_CHAR && elements[2][0] == elements[2][1] && elements[2][0] == elements[2][2]) {
            return elements[2][0] + " wins";
        }
        if (elements[0][0] != EMPTY_CHAR && elements[0][0] == elements[1][0] && elements[0][0] == elements[2][0]) {
            return elements[0][0] + " wins";
        }
        if (elements[0][1] != EMPTY_CHAR && elements[0][1] == elements[1][1] && elements[0][1] == elements[2][1]) {
            return elements[0][1] + " wins";
        }
        if (elements[0][2] != EMPTY_CHAR && elements[0][2] == elements[1][2] && elements[0][2] == elements[2][2]) {
            return elements[0][2] + " wins";
        }
        if (elements[0][0] != EMPTY_CHAR && elements[0][0] == elements[1][1] && elements[0][0] == elements[2][2]) {
            return elements[0][0] + " wins";
        }
        if (elements[0][2] != EMPTY_CHAR && elements[0][2] == elements[1][1] && elements[0][2] == elements[2][0]) {
            return elements[0][2] + " wins";
        }

        boolean hasEmptyElement = false;

        for (char[] row : elements) {
            for (char elem : row) {
                if (elem == EMPTY_CHAR) {
                    hasEmptyElement = true;
                    break;
                }
            }

            if (hasEmptyElement) {
                return "";
            }
        }

        return "Draw";
    }

    /**
     * Функция рисует поле в виде сетки
     *
     * @param elements двумерный массив символьных элементов
     */
    public static void drawGrid(char[][] elements) {
        System.out.println("---------");

        for (char[] row : elements) {
            System.out.print("|");
            for (char elem : row) {
                System.out.print(" " + elem);
            }
            ;
            System.out.println(" |");
        }

        System.out.println("---------");
    }

    /**
     * Функция формирует двумерный массив клеток поля для новой игры
     *
     * @return возвращает двумерный массив пустых элементов - клеток поля
     */
    public static char[][] getNewGameEmptyArray() {
        char[][] elements = new char[3][3];
        for (char[] row : elements) {
            Arrays.fill(row, EMPTY_CHAR);
        }
        return elements;
    }

    /**
     * Функция запускает игру
     *
     * @param args входные параметры запуска из командной строки
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] elements = getNewGameEmptyArray();
        drawGrid(elements);

        int coord1;
        int coord2;

        char currentPlayerChar = FIRST_PLAYER_CHAR;
        String res = "";

        while (res.equals("")) {
            System.out.println("Enter the coordinates:");

            try {
                coord1 = scanner.nextInt();
                coord2 = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Coordinates should be integer number!");
                scanner.nextLine();
                continue;
            }


            if (coord1 < 1 || coord1 > 3 || coord2 < 1 || coord2 > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (elements[coord1 - 1][coord2 - 1] != EMPTY_CHAR) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                elements[coord1 - 1][coord2 - 1] = currentPlayerChar;
                currentPlayerChar = currentPlayerChar == FIRST_PLAYER_CHAR ? SECOND_PLAYER_CHAR : FIRST_PLAYER_CHAR;
                drawGrid(elements);
                res = check(elements);
            }
        }

        System.out.print(res);
    }
}
