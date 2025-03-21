import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    /** MenuBar(GamePanel panel)
     * 1) Создать 2 выпадающее меню (Menu) file и help
     * 2) Создать элементы меню (new_game, exit, about)
     * 3) При нажатии на элемент new_game => создает новую игру panel.new_game()
     * 4) При нажатии на элемент exit => завершает программу
     * 5) При нажатии на элемент about => показывает информацию об авторе
     * 6) Добавим MenuItem в Menu
     *      file.add(new_game);
     *      file.add(exit);
     *      help.add(about);
     * 7) Добавим Menu в MenuBar
     *      JMenu.add(file);
     *      JMenu.add(help);
     * 8) Возвращает JMenuBer JMenu
     */
    public MenuBar(GamePanel panel) {
        JMenu file = new JMenu("File"); // Создать JMenu file и help
        JMenu help = new JMenu("Help");

        file.setMnemonic(KeyEvent.VK_F);
        help.setMnemonic(KeyEvent.VK_H);

        JMenuItem win = new JMenuItem("Win"); // Создать MenuItem new_game
        win.setMnemonic(KeyEvent.VK_W);
        win.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
        win.addActionListener(e -> panel.test_win());

        JMenuItem new_game = new JMenuItem("New"); // Создать MenuItem new_game
        new_game.setMnemonic(KeyEvent.VK_N);
        new_game.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        new_game.addActionListener(e -> panel.new_game());

        JMenuItem exit = new JMenuItem("Exit");  // Создать MenuItem exit
        exit.setMnemonic(KeyEvent.VK_X);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        exit.addActionListener(e -> System.exit(0));

        JMenuItem about = new JMenuItem("About");  // Создать MenuItem about
        about.setMnemonic(KeyEvent.VK_A);
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        about.addActionListener(e -> JOptionPane.showMessageDialog(null, "Хоанг Ван Куан, Р3166, 2023 года","Университет ИТМО ",JOptionPane.INFORMATION_MESSAGE));

        file.add(new_game); // Добавить в Menu
        file.add(exit);
        help.add(about);
        help.add(win);

        add(file); // Добавить в MenuBar
        add(help);
    }
}