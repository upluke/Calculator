import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Calculator extends JFrame  {
    private String[] keyValues ={"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "c", "0", "=", "/"} ;
    public Calculator(){
        super("Calculator");

        // menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        menu.getAccessibleContext().setAccessibleDescription("This is a menu");
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
        JPanel textPanel = new JPanel();
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setText("0");
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textField, BorderLayout.CENTER);


        // Number Keys
        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new GridLayout(4, 4));
        for(int i = 0; i<keyValues.length;i++){
            JButton button = new JButton(keyValues[i]);
//          button.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String buttonText = ((JButton) e.getSource()).getText();
//                    System.out.println(buttonText);
//                }
//            });
            // or with lambda
            button.addActionListener(e -> {
                String buttonText = ((JButton) e.getSource()).getText();
                System.out.println(buttonText);
            });


            keyPanel.add((button));
        }


        // Add textPanel and keyPanel to the frame
        setLayout(new BorderLayout());
        add(textPanel, BorderLayout.NORTH);
        add(keyPanel, BorderLayout.CENTER);


        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }



}
