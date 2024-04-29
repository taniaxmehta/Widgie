package cp317project.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;



public class Calculator extends JPanel implements ActionListener {
    private DecimalFormat df = new DecimalFormat("#,###.00"); //will format large numbers
    
    //define all the labels that the calculator will have
    private String[] symbols = {
        "AC", "+/-", "%", "/",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "^", "="

    };
    private int operator = 0; //will hold the operator that is pressed
    private JPanel panel = new JPanel(); //panel to hold buttons
    private JPanel btnPanel = new JPanel(new GridLayout(5, 3)); //button panel to hold indiv buttons
    private JButton[] btns = new JButton[20]; // this will contain 20 buttons of the defined symbols
    private JTextArea screen = new JTextArea(2, 40); //result will appear here
    private double firstNum = 0, secondNum = 0; //holds the numbers after being pressed
    private JTextField calculatingTf = new JTextField(40); //show the calculation as being done


    public Calculator() {

        init();


    }

    public void init() {
       
        Color DeepSpaceSparkle = new Color(51, 92, 103);
        Color MediumChampagne = new Color(255, 243, 176);
        Color Rosewood = new Color(84, 11, 14);
        Color TeaGreen = new Color(205, 234, 192);
        Color Apricot = new Color(254, 195, 166);
        panel.setBounds(10, 11, 325, 378);
        panel.setBackground(TeaGreen);

        for (int i = 0; i < btns.length; i++){
            btns[i] = new JButton(symbols[i]);
            btns[i].setFont(new Font("Arial Nova", Font.PLAIN, 16));
            btns[i].setOpaque(true);
            btns[i].setBorderPainted(false);
            btns[i].setBackground(Apricot);
            btns[i].setForeground(Color.BLACK);
            btns[i].addActionListener(this);
            btnPanel.add(btns[i]);
        }
        setLayout(null);

        add(panel);
        panel.setLayout(null);
        btnPanel.setBounds(10, 85, 305, 248);
        panel.add(btnPanel);
        btnPanel.setBackground(TeaGreen);
                screen.setBounds(10, 11, 305, 66);
                panel.add(screen);
                screen.setEditable(false);
                screen.setBorder(BorderFactory.createLineBorder(Color.gray));
                screen.setFont(new Font("Arial Nova", Font.PLAIN, 30));
                screen.setBackground(TeaGreen);
                screen.setForeground(Color.BLACK);
                calculatingTf.setEditable(false);
                calculatingTf.setBorder(BorderFactory.createLineBorder(Color.gray));
                calculatingTf.setBounds(10, 339, 305, 28);
                panel.add(calculatingTf);
                calculatingTf.setFont(new Font("Arial Nova", Font.PLAIN, 16));
                
                        
                        calculatingTf.setForeground(Color.BLACK); //choose diff colour, maybe grey
                        calculatingTf.setBackground(Apricot);
        setSize(345, 400);
      
        setVisible(true);
    }

    public static void main(String[] args){
        new Calculator();
    }


    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand().toString();

        switch (cmd) {
            case ".":
                if (!screen.getText().contains(".")){
                    screen.setText(screen.getText() + ".");
                }
                break;
            case "0":
                screen.setText(screen.getText() + "0");
                break;
            case "1":
                screen.setText(screen.getText() + "1");
                break;
            case "2":
                screen.setText(screen.getText() + "2");
                break;
            case "3":
                screen.setText(screen.getText() + "3");
                break;
            case "4":
                screen.setText(screen.getText() + "4");
                break;
            case "5":
                screen.setText(screen.getText() + "5");
                break;
            case "6":
                screen.setText(screen.getText() + "6");
                break;
            case "7":
                screen.setText(screen.getText() + "7");
                break;
            case "8":
                screen.setText(screen.getText() + "8");
                break;
            case "9":
                screen.setText(screen.getText() + "9");
                break;
            //operation handling
            //need to store the previous number somewhere and then use the operation on it
            case "+":
                if (!screen.getText().isEmpty()){
                    firstNum = Double.parseDouble(screen.getText().toString());
                    operator = 1;
                    screen.setText("");
                }
                break;
            
            case "-":
                if (!screen.getText().isEmpty()){
                    firstNum = Double.parseDouble(screen.getText().toString());
                    operator = 2;
                    screen.setText("");
                }
                break;
            
            case "x":
                if (!screen.getText().isEmpty()){
                    firstNum = Double.parseDouble(screen.getText().toString());
                    operator = 3;
                    screen.setText("");
                }
                break;
            
            case "/":
                if (!screen.getText().isEmpty()){
                    firstNum = Double.parseDouble(screen.getText().toString());
                    operator = 4;
                    screen.setText("");
                }
                break;

            case "^":
                if (!screen.getText().isEmpty()){
                    firstNum = Double.parseDouble(screen.getText().toString());
                    operator = 5;
                    screen.setText("");
                }
                break;
            
            case "%": 
                double num = Double.parseDouble(screen.getText().toString());
                screen.setText(String.valueOf(num / 100.0));
                break;
            
            case "+/-":
                double neg = Double.parseDouble(screen.getText().toString());
                neg *= -1;
                screen.setText(String.valueOf(neg));
                break;
                
            
            case "AC":
                screen.setText("");
                break;
            default:
        }


        if (cmd.equalsIgnoreCase("=")){
            if (!screen.getText().isEmpty()){      //check if there is a second number on the screen to calc with

                secondNum = Double.parseDouble(screen.getText().toString()); //obtain second number

                switch (operator) { //use operators
                    case 1: //addition
                        screen.setText(String.valueOf(df.format(firstNum + secondNum)));
                        calculatingTf.setText(String.valueOf(firstNum + " + " + secondNum + " = " + 
                        (df.format(firstNum + secondNum))));
                        break;
                    case 2: //subtraction
                        screen.setText(String.valueOf(df.format(firstNum - secondNum)));
                        calculatingTf.setText(String.valueOf(firstNum + " - " + secondNum + " = " + 
                        (df.format(firstNum - secondNum))));
                        break;
                    case 3: //multiplication
                        screen.setText(String.valueOf(df.format(firstNum * secondNum)));
                        calculatingTf.setText(String.valueOf(firstNum + " x " + secondNum + " = " + 
                        (df.format(firstNum * secondNum))));
                        break;
                    case 4: //division
                        screen.setText(String.valueOf(df.format(firstNum / secondNum)));
                        calculatingTf.setText(String.valueOf(firstNum + " / " + secondNum + " = " + 
                            (df.format(firstNum / secondNum))));
                        break;
                    case 5: //power
                        screen.setText(String.valueOf(df.format(Math.pow(firstNum,secondNum))));
                        calculatingTf.setText(String.valueOf(firstNum + " ^ " + secondNum + " = " + 
                            (df.format(Math.pow(firstNum,secondNum)))));
                        break;
                
                    default:
                        break;
                }
            }
        }
        
    }
}
