import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Calculator extends JFrame  {
    private String[] keyValues ={"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "c", "0", "=", "/"} ;
    private String operators = "+-*/=c";
    private String curOperator;
    private int result = 0;
    private int tempNum= 0;
    private boolean isFirstTyping = true;
    private boolean isEmptyField = false;

    private boolean isAfterEqualOpration= false;
    private boolean isFirstMinus = true;

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
                System.out.println("entry: "+ buttonText);
                if(!operators.contains(buttonText)){

                    if(isFirstTyping | isEmptyField){
                        System.out.println("entry result: " + result);
                        textField.setText("");
                        isEmptyField=false;
                        isFirstTyping=false;
                    }

                    textField.setText(textField.getText() + buttonText);
                    tempNum= Integer.parseInt(textField.getText());
                    System.out.println("startstart: result"+result +"tempNum: "+ tempNum);
                    isEmptyField=false;
                }else{
                    switch (buttonText){
                        case "+":
                            result+= tempNum;
                            isEmptyField=true;
                            System.out.println("result in +: "+result);
                            // text field will update with the result after 1st round of operation
                            if(result!=Integer.parseInt(textField.getText())){
                                textField.setText(Integer.toString(result));
                            }
                            curOperator="+";
                            break;
                        case "-":
                            if(isFirstMinus | isAfterEqualOpration){
                                result=Math.abs(tempNum-result);
                                isAfterEqualOpration=false;
                                isFirstMinus=false;
                            }else{
                                result-= tempNum;
                            }

                            isEmptyField=true;
                            System.out.println("result in -: "+result);
                            if(result!=Integer.parseInt(textField.getText())){
                                textField.setText(Integer.toString(result));
                            }
                            curOperator="-";
                            break;
                        case "*":
                            System.out.println("*result: "+result + " tempNum: "+tempNum);
                            if(result ==0){
                                result=1;
                            }
                            result*= tempNum;
                            isEmptyField=true;
                            System.out.println("result in *: "+result);
                            if(result!=Integer.parseInt(textField.getText())){
                                textField.setText(Integer.toString(result));
                            }
                            curOperator="*";
                            break;
                        case "/":
                            System.out.println("/result: "+result + " tempNum: "+tempNum);
                            if(result ==0){
                                result = tempNum;
                            }else{
                                result/= tempNum;
                            }

                            isEmptyField=true;
                            System.out.println("result in /: "+result);
                            if(result!=Integer.parseInt(textField.getText())){
                                textField.setText(Integer.toString(result));
                            }
                            curOperator="/";
                            break;
                        case "=":
                            result = calculateResult(result, tempNum, curOperator);

                            isEmptyField=true;
                            isAfterEqualOpration=true;
                            textField.setText(Integer.toString(result));
                            if(curOperator =="*" | curOperator =="/"){
                                tempNum=1;
                            }else{
                                tempNum=0;
                            }
                            curOperator="";

                            System.out.println("in = result: "+result + " tempNum: "+ tempNum);

                            break;
                        case "c":
                            result=0;
                            tempNum=0;
                            isFirstTyping=true;
                            isEmptyField=false;
                            textField.setText("0");
                            curOperator="";
                            System.out.println("------------------");
                            System.out.println("reset1->result: "+result + " tempNum: "+tempNum);
                            System.out.println("reset2->isFirstTyping: "+isFirstTyping + " isEmptyField: "+isEmptyField);
                            System.out.println("Current opearator: " + curOperator);
                            System.out.println("------------------");
                    }

                }

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

    public static int calculateResult(int result, int tempNum, String operator){
        switch (operator) {
            case "+":
                result+=tempNum;
                return result;
            case "-":
                result-=tempNum;
                return result;
            case "*":
                result *= tempNum;
                return result;
            case "/":
                result /= tempNum;
                return result;
        }
        return 0;
    }


}
