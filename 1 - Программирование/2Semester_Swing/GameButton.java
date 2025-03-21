import javax.swing.*;
import java.awt.*;

public class GameButton extends JButton {
    /**
     * 1) super(String.valueOf(number))
     * 2) set size
     * 3) set font
     */
    public GameButton(int number){
        super(String.valueOf(number));
        setFocusable(false);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int Buttonsize = size.height;
        // шрифт
        Font font = new Font("Arial", Font.BOLD, Buttonsize*4/56); // = buttonsize * 1/2 * 1/5 * 4/7
        setFont(font);
    }
}