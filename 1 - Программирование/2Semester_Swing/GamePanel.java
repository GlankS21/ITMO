import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private final int SIZE = 4;  // размер
    private int emptyButton; // где пустая кнопка
    private final GameButton[][] button; // Массив кнопок

    /** GamePanel() - конструктор
     * 1) создаем массив кнопок
     * 2) Начинается game()
     */
    public GamePanel() {
        button = new GameButton[SIZE][SIZE]; // Массив кнопок
        Game();
    }

    /** Game() - это интерфейс игры
     * 1) Создаем новую панель игры с GridLayout SIZExSIZE
     * 2) Создаем random_button - массив случайно значений
     * 3) Добавляем кнопки на панель со случайными значениями
     *   3.1. создать действие при нажатии кнопки
     *   3.2. Скрываем кнопку с нулевым значением
     * 4) меняем пустую кнопку последней кнопкой
     * 5) Добавляем key listener
     */
    private void Game(){
        setLayout(new GridLayout(SIZE, SIZE)); // Панель игры с GridLayout SIZExSIZE

        int[] random_button = shuffle(); // Массив значений элементов
        // Добавляем кнопки на панель
        for (int i = 0; i < (SIZE * SIZE); i++) {
            int x = i / SIZE;          //строка
            int y = i % SIZE;          //столбец
            button[x][y] = new GameButton(random_button[i]);
            button[x][y].addActionListener(e -> change_button(x, y)); // создать действие при нажатии кнопки
            add(button[x][y]);

            if (random_button[i] == 0) { // Скрываем кнопку с нулевым значением
                emptyButton = i;
                button[x][y].setVisible(false);
            }
        }
        change(SIZE - 1, SIZE - 1);
        addKey();
        setFocusable(true);
    }

    /** int[] shuffle() - это метод, чтобы создать массив со случайными значеньями
     *  1) Создаем массив с SIZE * SIZE элементам
     *  2) for(i -> length) random_button[i] = i;
     *  3) меняем их местами
     */
    private int[] shuffle(){
        int[] random_button = new int[SIZE * SIZE];// создать массив с SIZE*SIZE элементами

        for(int i = 0; i < random_button.length; ++i) random_button[i] = i;

        for(int i = 0; i < random_button.length; ++i){ // shuffle
            int random = (int)(Math.random()*random_button.length);
            int temp = random_button[i];
            random_button[i] = random_button[random];
            random_button[random] = temp;
        }
        return random_button;
    }
    /** change_button(int i, int j) - это метод, чтобы проверить можно ли менять местами двух кнопки
     * 1) Найти расстояние между текущей кнопкой и пустей кнопкой
     * 2) Если текущая кнопка не совпадает с пустой или они не диагональные и находятся в панели => change(i,j)
     * 3) После этого, показываем диалоговое окно о победе, если игра выиграна
     */
    private void change_button(int i, int j) {
        int emptyButtonX = emptyButton / SIZE;
        int emptyButtonY = emptyButton % SIZE;
        int X = Math.abs(i - emptyButtonX); // расстояние между Х
        int Y = Math.abs(j - emptyButtonY); // расстояние между У
        boolean canSwap = ((i == emptyButtonX)||(j == emptyButtonY)) && (X == 1 || Y == 1) && (i >= 0 && i < SIZE) && (j >= 0 && j < SIZE);
        // Если текущая кнопка не совпадает с пустой или они не диагональные и находятся в панели => change(i,j)
        if (canSwap) change(i, j);
        if(check_win()) // Если победа
            JOptionPane.showMessageDialog(null, "You win","WIN", JOptionPane.INFORMATION_MESSAGE);
    }
    /**  change(int i, int j)- то метод, чтобы менять местами двух кнопки
     *  1) Поменять местами текущая кнопка с пустой кнопкой (Меняем их текстами)
     */
    private void change(int i, int j) {
        int x = emptyButton / SIZE; // Координаты пустой кнопки
        int y = emptyButton % SIZE;
        button[x][y].setText(button[i][j].getText()); // Меняем их текстами
        button[x][y].setVisible(true);  // set visible
        button[i][j].setText(String.valueOf(0));
        button[i][j].setVisible(false);
        emptyButton = (i * SIZE) + j; // Новая пустая кнопка
    }
    /** boolean check_win() - метод проверки победы
     * Проверка, если button[i / SIZE][i % SIZE] и i + 1 не получают одинаковые тексты => false
     * Иначе => true
     */
    private boolean check_win(){
        for(int i = 0; i < 15; ++i){
            String value = button[i / SIZE][i % SIZE].getText(); // текст на кнопке button[i / SIZE][i % SIZE]
            if(!value.equals(Integer.toString(i + 1))) return false;
        }
        return true;
    }

    /** void addKey() - Добавляем key listener
     *  1) Удалить старые key listener
     *  2) Если нажата клавиша вверх:
     *     if (press.getKeyCode () == KeyEvent.VK_UP) => change_button (x + 1, y);
     *     Аналогично для других клавиш: вниз, влево и вправо.
     */
    private void addKey(){
        for (int i = 0; i < getKeyListeners().length; i++) {  // Удалить старые key listener
            removeKeyListener(getKeyListeners()[i]);
        }
        addKeyListener(new KeyAdapter() {  // Добавляем key listener
            @Override
            public void keyPressed(KeyEvent press) {
                int x = emptyButton / SIZE;
                int y = emptyButton % SIZE;
                if (press.getKeyCode() == KeyEvent.VK_UP)
                    change_button(x + 1,y);
                if (press.getKeyCode() == KeyEvent.VK_DOWN)
                    change_button(x - 1,y);
                if (press.getKeyCode() == KeyEvent.VK_LEFT)
                    change_button(x,y + 1);
                if (press.getKeyCode() == KeyEvent.VK_RIGHT)
                    change_button(x,y - 1);
            }
        });
    }
    public void new_game(){
        int[] random_button = shuffle(); // Массив значений элементов
        for (int index = 0; index < (SIZE * SIZE); index++) {
            int x = index / SIZE;
            int y = index % SIZE;
            button[x][y].setText(String.valueOf(random_button[index]));
            if (random_button[index] == 0) { // Скрываем кнопку с нулевым значением
                emptyButton = index;
                button[x][y].setVisible(false);
            }else button[x][y].setVisible(true);
        }
        change(SIZE - 1, SIZE - 1);

        setFocusable(true);
    }
    public void test_win(){
        for (int index = 0; index < (SIZE * SIZE) - 1; index++) {
            int i = index / SIZE;
            int j = index % SIZE;
            button[i][j].setText(String.valueOf(index + 1));
            button[i][j].setVisible(true);
        }
        button[SIZE - 1][SIZE - 1].setText(String.valueOf(0)); // последняя кнопка
        button[SIZE - 1][SIZE - 1].setVisible(false);
        emptyButton = 15;

        setFocusable(true);
        JOptionPane.showMessageDialog(null, "You win","WIN", JOptionPane.INFORMATION_MESSAGE);
    }
}