import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    /**
     * 1) Устанавливаем размер окна игры в половину экрана
     * 2) Размещаем окно по центру экрана
     * 3) Запрещаем изменять размер окна
     * 4) Создаем панель меню
     * 5) Создаем JMenuBar
     * 6) Добавляем панель в frame
     */
    public Game() {
        super("Пятнашки");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Получаем размеры экрана
        int screenHeight = screenSize.height;
        Dimension frameSize = new Dimension(screenHeight/2,screenHeight/2); // устанавливаем размер окна игры в половину экрана

        setSize(frameSize);
        setLocationRelativeTo(null); // Размещаем окно по центру экрана
        setResizable(false); // Запрещаем изменять размер окна

        GamePanel game = new GamePanel();
        setJMenuBar(new MenuBar(game));
        add(game);
    }
}