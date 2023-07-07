import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Calculator extends JFrame {

    public Calculator(){
        super("Calculator");

        // menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        menu.getAccessibleContext().setAccessibleDescription("This is a menu");
//        Dimension menuBarPreferredSize = menuBar.getPreferredSize();
//        menuBarPreferredSize.setSize(menuBarPreferredSize.getWidth(), 40);
//        menuBar.setPreferredSize(menuBarPreferredSize);
        // or
        menuBar.setPreferredSize(new Dimension(menuBar.getPreferredSize().width, 40));
        menuBar.add(menu);

        JMenuItem reset = new JMenuItem("Reset", KeyEvent.VK_R);
        JMenuItem quit = new JMenuItem("Quit", KeyEvent.VK_Q);

        menu.add(reset);
        menu.addSeparator();
        menu.add(quit);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Display
        JPanel panel = new JPanel();
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setText("0");
        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);
        add(panel);



        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
//        textField.setPreferredSize(new Dimension(panel.getWidth(), textField.getPreferredSize().height));

    }



}
