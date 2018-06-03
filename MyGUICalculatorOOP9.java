package myguicalculatoroop9;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/*

Functionality:
    
    The calculator is essentially a parser for a JLabel, that gets re-written constantly by the user.
    The screen which is called AnswerDisplay is the JLabel where values are input. 
    The user calls operations by hitting one of the functions/operational buttons. 
    Each one has its own dedicated class and listener that fires when the user clicks the button.
    The numbers get stored in an ArrayList. 
    The operations are each given and index value (Charted below). 
    Based on the value of the booleans and whether or not the operation is 
        single value input or two value input, the the correct processing method is called
    I have a boolean set true called firstRoundOperations, 
        (is this the first time an operation has been called since Equals() method was used).
    I have boolean for new number input - isNewNumber - (whether or not a new number is being used). 
    These two booleans do alot of the background heavy lifting.
    They make it so that the numbers stay on screen when calling an operation, 
    and new numbers are input the screen auto deletes.
    When enough numbers have been input as to trigger Equals() method automatically, 
        or the user manually hits the = button, the Equals() method calles the index operation 
        using a switch stement and runs through all possible cases. 
    After this everything is automatically cleared for clean slate for new calculations.    
    The Clear button also does this manually. 
    All Errors print an error message "Error: Press Clear" if excpetions or other errors are processed
        This prevents blowing up/ crashin at runtime.
*/
/* OPERATION INDEX:
    0. The defualt value, when no operation is selected. If called it is treated as an error.
    1.  Addition 
    2.  Subtraction
`   3.  Multiplication 
    4.  Division 
    5.  YPower (user says take x and raise it to the y power)
    6.  nCr (combinatorics formula for n chooses r)
    7.  nPr (combinatorics formula for n chooses r with perumatation so order matters)
    8.  LogP (user says take log base x of y) 
    9.  Square (Raises number to Second Power)
    10. Cube (Raises number to Third Power)   
    11. SquareRoot (Takes the square root of the number)
    12. NaturalLog (Takes the natural log of the number )
    13. Sin (Takes the sin of the number, if hyperbolic is on "hyp" takes the hyperbolic sin of the number)
    14. Cos (Takes the cosine of the number, if hyperbolic is on "hyp" takes the hyperbolic cos of the number)
    15. Tan (Takes the tan of the number, if hyperbolic is on "hyp" takes the hyperbolic tan of the number)
    16. SinInv (Takes the arcsin of the number)
    17. CosInv (Takes the arccos of the number)
    18. TanInv (Takes the arctan of the number)
    19. Factorial (Takes the factorial of the number) 
    20. LogB10 (Takes the Log base 10 of the number)
    21. Percent (Divides the number by 100)
    
    Each one gets called during the run of Equals() method. 
 */

 //-------------//-------------Skelaton Code Here V1 (Complicated)-------------//-------------//

class CoolCalc {
    
    private ArrayList <Double> NumberList = new ArrayList <>(); //Stores the numbers slected by user
    private int OperationChosen = 0; //Holds the value for the Operation index
    private double answer;       //Quickly Stores the "answer" for an operation can also be set as an input automatically 
    private String QuickOutput1; //used to store 1st input in line of inputs of values 
    private String QuickOutput2; //used to store 2nd input in line of inputs of values will    
    private double QuickNumber;          //Temorary value storring operational data called throughout 
    
    private boolean FirstRoundOperations = true; //These two booleans run background antics 
    private boolean isNewNumber = false;         //It keeps the operations more seemless    
    
    private boolean inDegrees = false;      // used for trig functions -> will change with selection
    private boolean isHyperbolic = false;   // makes base trig functions their hyperbolic versions -> will change with selection
        
        //----//----Declare Compononents------ 
  //level 1     
    private JFrame MainFrame; 
  //level 2
    private JPanel MainPanel;       //goes in MainFrame SOUTH - holds all numbers and operations 
    private JPanel TextOutputPanel; //goes in MainFrame NORTH - holds all user input text/printed values 
    private JPanel CenterColorPanel;//goes in MainFrame CENTER - just improves color aesthetic  
  //level 3
    private JPanel MainPanelBottom;
    private JPanel MainPanelTop;
   
  //level 4
    private JPanel NumberPanel;     //goes in MainPanelBottom WEST (the digits like 0,1,4,9, etc.)
    private JPanel OperationPanel;  //goes in MainPanelBottom EAST (+, -, X, Clear, =, etc.)
    private JPanel FunctionsPanel;  //goes in MainPanelTop (all things up like x^2, sin, !, nCr, etc.)

    private JPanel DecorativePanel;
    private JPanel SettingsPanel;
        //---//---//
    private JLabel AnswerDisplay;   //goes in TextOutput
    private JLabel DegRadDisplay;   //goes in TextOutput  
    private JLabel HypDisplay;      //goes in TextOutput  
  //level 5  
    private JPanel NumberRow1;     //all go in NumberPanel 
    private JPanel NumberRow2;
    private JPanel NumberRow3;
    private JPanel NumberRow4;
    
    private JPanel OperationRow1;  //all go in OperationPanel 
    private JPanel OperationRow2;
    private JPanel OperationRow3; 
    private JPanel OperationRow4;  
    
    private JPanel FunctionsRow1;  //all go in FunctionsPanel 
    private JPanel FunctionsRow2;
    private JPanel FunctionsRow3;
    private JPanel FunctionsRow4;
    private JPanel FunctionsRow5;
  //level 6
        //No Number Input Functions
    
    private JButton ButtonForClear;
    private JButton ButtonForEqual;
    private JButton ButtonForPi;
    private JButton ButtonFore;
    private JButton ButtonForRand;
    private JButton ButtonForHyp;
    private JButton ButtonForDeg;
    
        //Two Number Input Functions
    
    private JButton ButtonForAdd;
    private JButton ButtonForSubtract;
    private JButton ButtonForMultiplication;
    private JButton ButtonForDivision;
    private JButton ButtonForYPower;
    private JButton ButtonFornCr;
    private JButton ButtonFornPr;
    private JButton ButtonForLogP;
    
        //One Number Input Functions
    
    private JButton ButtonForSquare;
    private JButton ButtonForCube;
    private JButton ButtonForSquareRoot;
    private JButton ButtonForNaturalLog;
    private JButton ButtonForSin;
    private JButton ButtonForCos;
    private JButton ButtonForTan;
    private JButton ButtonForSinInv;
    private JButton ButtonForCosInv;
    private JButton ButtonForTanInv;
    private JButton ButtonForFactorial;
    private JButton ButtonForLogB10;
    private JButton ButtonForPercent;
    
    private JButton ButtonForMinusSign;
    private JButton ButtonForDecimal;
    private JButton ButtonFor0;
    private JButton ButtonFor1;
    private JButton ButtonFor2;
    private JButton ButtonFor3;
    private JButton ButtonFor4;
    private JButton ButtonFor5;
    private JButton ButtonFor6;
    private JButton ButtonFor7;
    private JButton ButtonFor8;
    private JButton ButtonFor9;
    
    public static final double Double_e  = 2.718281828459045;
    public static final double Double_Pi = 3.141592653589793;
    public static final String String_e  = "2.718281828459045";
    public static final String String_Pi = "3.141592653589793";

public void Run () {
        
        Random rand = new Random(); 
        Scanner input = new Scanner(System.in);    
        
        Font bigFont = new Font ("serif", Font.PLAIN,28);
        
        //Assign Objects to respctive components by level 
        
        //Level 1
        MainFrame = new JFrame();
        //Level 2
        MainPanel = new JPanel();       //goes SOUTH in Frame
        TextOutputPanel = new JPanel(); //goes NORTH in Frame
        
        MainPanelBottom = new JPanel(); //goes SOUTH in MainPanel
        MainPanelTop = new JPanel();    //goes NORTH in MainPanel
        
        OperationPanel = new JPanel();  //goes EAST in MainPanelBottom
        NumberPanel = new JPanel();     //goes WEST in MainPanelBottom
        
        FunctionsPanel = new JPanel();  //goes SOUTH in MainPanelTop
        DecorativePanel = new JPanel(); //goes NORTH in MainPanelTop

        AnswerDisplay = new JLabel(""); //goes EAST in TextOutputPanel
        SettingsPanel = new JPanel();   //goes WEST in TextOutputPanel
                
        DegRadDisplay = new JLabel("Rad"); //goes NORTH in SettingsPanel
        HypDisplay = new JLabel("");       //goes SOUTH in SettingsPanel  
        
        CenterColorPanel = new JPanel();
        
        NumberRow1 = new JPanel(); 
        NumberRow2 = new JPanel();
        NumberRow3 = new JPanel();
        NumberRow4 = new JPanel();
                
        OperationRow1 = new JPanel();
        OperationRow2 = new JPanel();
        OperationRow3 = new JPanel();       
        OperationRow4 = new JPanel();
        
        FunctionsRow1 = new JPanel();
        FunctionsRow2 = new JPanel();
        FunctionsRow3 = new JPanel();       
        FunctionsRow4 = new JPanel();
        FunctionsRow5 = new JPanel();
    
    //------------------Number Panel------------------//
        
        ButtonForMinusSign = new JButton("(-)");   
        ButtonForDecimal = new JButton(".");
        ButtonFor0 = new JButton("0"); 
        
        ButtonFor1 = new JButton("1");        
        ButtonFor2 = new JButton("2");       
        ButtonFor3 = new JButton("3");
        
        ButtonFor4 = new JButton("4");
        ButtonFor5 = new JButton("5");
        ButtonFor6 = new JButton("6");
        
        ButtonFor7 = new JButton("7");
        ButtonFor8 = new JButton("8");
        ButtonFor9 = new JButton("9");
        
    //------------------Operation Panel------------------//
        
        ButtonForAdd = new JButton("+"); 
        ButtonForClear = new JButton("Clear");
        
        ButtonForSubtract = new JButton("-");
        ButtonForEqual = new JButton("=");
        
        ButtonForMultiplication = new JButton("X");
        ButtonForDeg = new JButton("deg");
       
        ButtonForDivision = new JButton("÷");
        ButtonForPercent = new JButton("%");
    
    //------------------Functions Panel------------------//
        
        String XSquared = "<html><font color=black>x<sup>2</sup></font></html>";
        String XCubed = "<html><font color=black>x<sup>3</sup></font></html>";
        String XToTheY = "<html><font color=black>x<sup>y</sup></font></html>";
        String LogAOfB = "<html><font color=black>log<sub>x</sub>y</font></html>";
        String arcSin = "<html><font color=black>sin<sup>-1</sup></font></html>";
        String arcCos = "<html><font color=black>cos<sup>-1</sup></font></html>";      
        String arcTan = "<html><font color=black>tan<sup>-1</sup></font></html>";       
        
        //Dimension ButtonDimension = new Dimension (70,30);
        
        ButtonForSquare = new JButton(XSquared);
        ButtonForCube = new JButton(XCubed);
        ButtonForYPower = new JButton(XToTheY);
        ButtonForSquareRoot = new JButton("√");
        
        ButtonFornCr = new JButton("nCr");
        ButtonFornPr = new JButton("nPr");
        ButtonForFactorial = new JButton("!");
        ButtonForRand = new JButton("rand");
        
        ButtonForNaturalLog = new JButton("ln");
        ButtonForLogB10 = new JButton("log");
        ButtonForLogP = new JButton(LogAOfB);
        ButtonFore = new JButton("e");
        
        ButtonForSinInv = new JButton (arcSin);
        ButtonForCosInv = new JButton (arcCos);
        ButtonForTanInv = new JButton (arcTan);
        ButtonForPi = new JButton("π");
        
        ButtonForSin = new JButton ("sin");
        ButtonForCos = new JButton ("cos");
        ButtonForTan = new JButton ("tan");
        ButtonForHyp = new JButton("Hyp");
        
//LAYOUT MANAGER PART I (Selecting Layouts for Components)

        NumberPanel.setLayout(new BoxLayout(NumberPanel, BoxLayout.Y_AXIS));
        OperationPanel.setLayout(new BoxLayout(OperationPanel, BoxLayout.Y_AXIS));
        FunctionsPanel.setLayout(new BoxLayout(FunctionsPanel, BoxLayout.Y_AXIS));
        
        MainPanelTop.setLayout(new BorderLayout());
        MainPanelBottom.setLayout(new BorderLayout());
        
        SettingsPanel.setLayout(new BorderLayout());
 
        TextOutputPanel.setLayout(new BorderLayout());
       
        MainPanel.setLayout(new BorderLayout());
        
        //         ---Listeners for the Buttons---
        
        ButtonForClear.addActionListener(new ClearListener());
        ButtonForEqual.addActionListener(new EqualListener());
        
        ButtonForAdd.addActionListener(new AddListener());
        ButtonForSubtract.addActionListener(new SubListener());
        ButtonForMultiplication.addActionListener(new MultListener());
        ButtonForDivision.addActionListener(new DivListener());
        ButtonForYPower.addActionListener(new YPowerListener());
        ButtonFornCr.addActionListener(new nCrListener());        
        ButtonFornPr.addActionListener(new nPrListener()); 
        ButtonForLogP.addActionListener(new LogPListener());
        
        ButtonForSquare.addActionListener(new SquareListener());
        ButtonForCube.addActionListener(new CubeListener());
        ButtonForSquareRoot.addActionListener(new SquareRootListener());
        ButtonForNaturalLog.addActionListener(new NaturalLogListener());
        ButtonForSin.addActionListener(new SinListener());
        ButtonForCos.addActionListener(new CosListener());
        ButtonForTan.addActionListener(new TanListener());
        ButtonForSinInv.addActionListener(new SinInvListener());
        ButtonForCosInv.addActionListener(new CosInvListener());
        ButtonForTanInv.addActionListener(new TanInvListener());
        ButtonForFactorial.addActionListener(new FactorialListener());
        ButtonForLogB10.addActionListener(new LogB10Listener());
        ButtonForPercent.addActionListener(new PercentListener());        
        
        ButtonFore.addActionListener(new eListener());
        ButtonForPi.addActionListener(new PiListener());
        ButtonForMinusSign.addActionListener(new MinusSignListener());
        ButtonForDecimal.addActionListener(new DecimalListener());
        ButtonForRand.addActionListener(new RandListener());
        ButtonForDeg.addActionListener(new DegListener());
        ButtonForHyp.addActionListener(new HypListener());
        
        ButtonFor0.addActionListener(new NumberListener0());
        ButtonFor1.addActionListener(new NumberListener1());
        ButtonFor2.addActionListener(new NumberListener2());
        ButtonFor3.addActionListener(new NumberListener3());
        ButtonFor4.addActionListener(new NumberListener4());
        ButtonFor5.addActionListener(new NumberListener5());
        ButtonFor6.addActionListener(new NumberListener6());
        ButtonFor7.addActionListener(new NumberListener7());
        ButtonFor8.addActionListener(new NumberListener8());
        ButtonFor9.addActionListener(new NumberListener9());

//LAYOUT MANAGER PART II (Nesting Components in their respective places) 

        NumberRow1.add(ButtonForMinusSign);
        NumberRow1.add(ButtonForDecimal);
        NumberRow1.add(ButtonFor0);
        
        NumberRow2.add(ButtonFor1);
        NumberRow2.add(ButtonFor2);
        NumberRow2.add(ButtonFor3);
        
        NumberRow3.add(ButtonFor4);
        NumberRow3.add(ButtonFor5);
        NumberRow3.add(ButtonFor6);
         
        NumberRow4.add(ButtonFor7);
        NumberRow4.add(ButtonFor8);
        NumberRow4.add(ButtonFor9);
        
        OperationRow1.add(ButtonForAdd);
        OperationRow1.add(ButtonForClear);
    //---
        OperationRow2.add(ButtonForSubtract);
        OperationRow2.add(ButtonForEqual);
        
        OperationRow3.add(ButtonForMultiplication);
        OperationRow3.add(ButtonForDeg);
        
        OperationRow4.add(ButtonForDivision);
        OperationRow4.add(ButtonForPercent);
    //---
        FunctionsRow1.add(ButtonForSquare);
        FunctionsRow1.add(ButtonForCube);
        FunctionsRow1.add(ButtonForYPower);
        FunctionsRow1.add(ButtonForSquareRoot);
        
        FunctionsRow2.add(ButtonFornCr);
        FunctionsRow2.add(ButtonFornPr);
        FunctionsRow2.add(ButtonForFactorial);
        FunctionsRow2.add(ButtonForRand);
        
        FunctionsRow3.add(ButtonForNaturalLog);
        FunctionsRow3.add(ButtonForLogB10);
        FunctionsRow3.add(ButtonForLogP);
        FunctionsRow3.add(ButtonFore);
        
        FunctionsRow4.add(ButtonForSinInv);
        FunctionsRow4.add(ButtonForCosInv);
        FunctionsRow4.add(ButtonForTanInv);
        FunctionsRow4.add(ButtonForPi);
        
        FunctionsRow5.add(ButtonForSin);
        FunctionsRow5.add(ButtonForCos);
        FunctionsRow5.add(ButtonForTan);
        FunctionsRow5.add(ButtonForHyp);
    //---
        NumberPanel.add(NumberRow1);
        NumberPanel.add(NumberRow2);
        NumberPanel.add(NumberRow3);
        NumberPanel.add(NumberRow4);
        
        OperationPanel.add(OperationRow1);
        OperationPanel.add(OperationRow2);
        OperationPanel.add(OperationRow3);
        OperationPanel.add(OperationRow4);
        
        FunctionsPanel.add(FunctionsRow1);
        FunctionsPanel.add(FunctionsRow2);
        FunctionsPanel.add(FunctionsRow3);
        FunctionsPanel.add(FunctionsRow4);
        FunctionsPanel.add(FunctionsRow5);
        
        MainPanelTop.add(BorderLayout.SOUTH, FunctionsPanel);
        MainPanelTop.add(BorderLayout.NORTH, DecorativePanel);
        
        MainPanelBottom.add(BorderLayout.EAST,OperationPanel);
        MainPanelBottom.add(BorderLayout.WEST,NumberPanel);
        
        MainPanel.add(BorderLayout.SOUTH, MainPanelBottom);
        MainPanel.add(BorderLayout.NORTH, MainPanelTop);
        
        //----
        
        SettingsPanel.add(BorderLayout.SOUTH, HypDisplay);
        SettingsPanel.add(BorderLayout.NORTH, DegRadDisplay);
        
        TextOutputPanel.add(BorderLayout.EAST,AnswerDisplay);
        TextOutputPanel.add(BorderLayout.WEST, SettingsPanel);
        
        MainFrame.getContentPane().add(BorderLayout.CENTER, CenterColorPanel);
        MainFrame.getContentPane().add(BorderLayout.SOUTH, MainPanel);
        MainFrame.getContentPane().add(BorderLayout.NORTH, TextOutputPanel);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//LAYOUT MANAGER PART III (Setting preferential colors and sizes - go outer to inner this time)
        
        Dimension MinSizeFrame = new Dimension(413,512); 
        MainFrame.setMinimumSize(MinSizeFrame);
        MainFrame.setMaximumSize(MinSizeFrame);
        
        MainPanelBottom.setBackground(Color.BLACK);
        CenterColorPanel.setBackground(Color.BLACK);
        
        Color TextPanelColor = new Color (0,102,0,50);
        TextOutputPanel.setMaximumSize(new Dimension(413,70));
        TextOutputPanel.setBackground(TextPanelColor);

        FunctionsPanel.setPreferredSize(new Dimension(413,250));
        FunctionsPanel.setBackground(Color.BLACK);
        DecorativePanel.setPreferredSize(new Dimension(413,10));
        
        AnswerDisplay.setFont(bigFont); 
        AnswerDisplay.setBackground(TextPanelColor);
        AnswerDisplay.setHorizontalAlignment(AnswerDisplay.RIGHT);
        AnswerDisplay.setMaximumSize(new Dimension(375,70));

        Color SeeThroughLight = new Color (1,1,1,0);
        SettingsPanel.setBackground(SeeThroughLight);
        SettingsPanel.setPreferredSize(new Dimension(34,70) );
        
        MainFrame.setVisible(true);
        MainFrame.repaint();
        
    }
   
               //-------METHODS TO BE CALLED THROUGHOUT PROGRAM--------//
    
    public void numberPrinter (String insNumString) {
        if (isNewNumber == false) {
            QuickOutput1 = AnswerDisplay.getText();
            QuickOutput2 = QuickOutput1+insNumString;
            AnswerDisplay.setText(QuickOutput2); 
        } else {
            AnswerDisplay.setText("");
            QuickOutput1 = AnswerDisplay.getText();
            QuickOutput2 = QuickOutput1+insNumString;
            AnswerDisplay.setText(QuickOutput2);
            isNewNumber = false;  
        }
        MainFrame.repaint();
    }
    public void twoNumberInputProcessing (int insertIndexForOperation) { //gets called if the funciton the
        try {                                                            //user picked needs two input values 
            
            if (FirstRoundOperations == true) {
                
                QuickNumber = Double.parseDouble(AnswerDisplay.getText());
                NumberList.add(QuickNumber); 
                OperationChosen = insertIndexForOperation; // add the coded term here 1 is for addition 
                FirstRoundOperations = false;
                isNewNumber = true;
        } 
            else { 
                
                Equals();
                isNewNumber = true;
                QuickNumber = Double.parseDouble(AnswerDisplay.getText());
                NumberList.add(QuickNumber); 
                OperationChosen = insertIndexForOperation; // add the coded term here 1 is for addition 
            }        
        } 
        catch (Exception ex) {
            AnswerDisplay.setText("Erorr: Press Clear");
        }
        MainFrame.repaint();
    }
    public void oneNumberInputProcessing (int insertIndexForOperation) { //gets called if the funciton the
        try {                                                            //user picked needs one input values
            NumberList.clear();
            //QuickNumber = Double.parseDouble(AnswerDisplay.getText());
            //NumberList.add(QuickNumber); 
            OperationChosen = insertIndexForOperation; // add the coded term here 1 is for addition 
            Equals();
            isNewNumber = true;                            
            }        
        catch (Exception ex) {
            AnswerDisplay.setText("Erorr: Press Clear");
        }
        MainFrame.repaint();
    }
    
    
//----------Two Value Input Methods----------// 
    
//They are in indexOfOperations order (e.g. addition is 1 multiplication is 3, etc).
    
    public double Addition (double a, double b) {
        return a + b;
    }
    public double Subtraction (double a, double b) {
        return a - b;
    }
    public double Multiplication (double a, double b) {
        return a * b;
    }
    public double Division (double a, double b) {
        return a/b;
    }
    public double YPower (double a, double b) {
        return Math.pow(a, b);
    }
    public double nCr (double a, double b) {
        //  Formula: a!/(b!(a-b!))
        double aFactorial = Factorial(a);
        double bFactorial = Factorial(b);
        double nCr_innerBottom = Factorial(a-b);
        double nCr_outerBottom = bFactorial * nCr_innerBottom;
        double ReturnThis = aFactorial/nCr_outerBottom;        
        return ReturnThis;
    }
    public double nPr (double a, double b) {
        //  Formula:  a!/(a-b!)
        double aFactorial = Factorial(a);
        double nPr_Bottom = Factorial(a-b);
        double ReturnThis = aFactorial/nPr_Bottom;        
        return ReturnThis;
    }
    
    public double LogP (double a, double b) {
        //logb(n) = loge(n) / loge(b) -> where b is base and n is what u want log of
        return Math.log(a)/Math.log(b);
    }
//----------One Value Input Methods----------//
    
    public double Square (double a) {
        return a*a;
    }
    public double Cube (double a) {
        return a*a*a;
    }
    public double SquareRoot (double a) {
        return Math.sqrt(a);
    }
    public double NaturalLog (double a) {
        return Math.log(a);
    }
    public double Sin (double a) {
        if (isHyperbolic == false) {    
            if (inDegrees == false) {
                return Math.sin(a); //radians is defualt
            } else {
                double DegToRad; 
                DegToRad = Math.toRadians(a);
                return Math.sin(DegToRad);
            }
        } 
        else { return Math.sinh(a);}  
    }
    public double Cos (double a) {
        if (isHyperbolic == false){    
            if (inDegrees == false) {
                return Math.cos(a); //radians is defualt
            } else {
                double DegToRad; 
                DegToRad = Math.toRadians(a);
                return Math.cos(DegToRad);
            }
        } else { return Math.cosh(a);} 
    }
    public double Tan (double a) {
        if (isHyperbolic == false) {    
            if (inDegrees == false) {
                return Math.tan(a); //radians is defualt
            } else {
                double DegToRad; 
                DegToRad = Math.toRadians(a);
                return Math.tan(DegToRad);
            }
        } else {return Math.tanh(a); }    
    }
    public double SinInv (double a) {
        if (inDegrees == false) {
            return Math.asin(a); //radians is defualt
        } else {
            double Ans;
            double AnsToDeg;
            Ans = Math.asin(a);
            AnsToDeg = Math.toDegrees(Ans);
            return AnsToDeg;
        }  
    }
    public double CosInv (double a) {
        if (inDegrees == false) {
            return Math.acos(a); //radians is defualt
        } else {
            double Ans;
            double AnsToDeg;
            Ans = Math.acos(a);
            AnsToDeg = Math.toDegrees(Ans);
            return AnsToDeg;
        }
    }
    public double TanInv (double a) {
        if (inDegrees == false) {
            return Math.atan(a); //radians is defualt
        } else {
            double Ans;
            double AnsToDeg;
            Ans = Math.atan(a);
            AnsToDeg = Math.toDegrees(Ans);
            return AnsToDeg;
        }
    }
    public double Factorial (double a) {
        if (a == 0) return 1;
        if (a == 1) return 1; 
        return a * Factorial(a-1); // must make factorial method 
    }
    public double LogB10 (double a) { 
        return Math.log10(a);
    }
    public double Percent (double a) { 
        return a/100;
    }
    public void Clear() {
        answer = 0;
        OperationChosen = 0;
        AnswerDisplay.setText("");
        NumberList.clear();
        FirstRoundOperations = true;
        MainFrame.repaint();
    }
    
    public void Equals() {
        
        QuickNumber = Double.parseDouble(AnswerDisplay.getText());
        NumberList.add(QuickNumber);
        
        boolean ErrorDetected = false;
        
        switch (OperationChosen) {
                        //------Double Number Operations------//
            case 0:
                AnswerDisplay.setText("No Operation Input Press Clear");
                NumberList.clear();
                OperationChosen = 0;
                ErrorDetected = false;    
            break;
            
            case 1:
                answer = Addition(NumberList.get(0),NumberList.get(1));
            break;
            
            case 2:
                answer = Subtraction(NumberList.get(0),NumberList.get(1));
            break;
            
            case 3:
                answer = Multiplication(NumberList.get(0),NumberList.get(1));    
            break;
            
            case 4:
                answer = Division(NumberList.get(0),NumberList.get(1));    
            break; 
            
            case 5:
                answer = YPower(NumberList.get(0),NumberList.get(1));    
            break;
            
            case 6:
                if (NumberList.get(0) > 0 && NumberList.get(1) >= 0         //means n>0 and r >=0
                    && NumberList.get(0) >= NumberList.get(1)               //means n >= r
                    && NumberList.get(0) == Math.rint(NumberList.get(0))    //means n is an integer
                    && NumberList.get(1) == Math.rint(NumberList.get(1))) { //means r is an integer
                        answer = nCr(NumberList.get(0),NumberList.get(1));
                } else {ErrorDetected = true;}    
            break;
            
            case 7:
                if (NumberList.get(0) > 0 && NumberList.get(1) >= 0         //means n>0 and r >=0
                    && NumberList.get(0) >= NumberList.get(1)               //means n >= r
                    && NumberList.get(0) == Math.rint(NumberList.get(0))    //means n is an integer
                    && NumberList.get(1) == Math.rint(NumberList.get(1))) { //means r is an integer
                        answer = nPr(NumberList.get(0),NumberList.get(1));
                } else {ErrorDetected = true;}   
            break;
            
            case 8:
                answer = LogP(NumberList.get(0),NumberList.get(1));    
            break;
                        //------Single Number Operations------// 
            case 9:
                answer = Square(NumberList.get(0));    
            break;
            
            case 10:
                answer = Cube(NumberList.get(0));    
            break;
            
            case 11:
                if (NumberList.get(0) >= 0) {
                    answer = SquareRoot(NumberList.get(0));
                } else {ErrorDetected = true;}      
            break;
            
            case 12:
                if (NumberList.get(0) > 0) {
                    answer = NaturalLog(NumberList.get(0));
                } else {ErrorDetected = true;}      
            break;
            
            case 13:
                answer = Sin(NumberList.get(0));    
            break;
            
            case 14:
                answer = Cos(NumberList.get(0));    
            break;
            
            case 15:
                answer = Tan(NumberList.get(0));    
            break;
            
            case 16:
                answer = SinInv(NumberList.get(0));    
            break;
            
            case 17:
                answer = CosInv(NumberList.get(0));    
            break;
            
            case 18:
                answer = TanInv(NumberList.get(0));    
            break;
            
            case 19:
                if (NumberList.get(0) >= 0 && NumberList.get(0) == Math.rint(NumberList.get(0))
                    && NumberList.get(0) <= 25000) {  
                    answer = Factorial(NumberList.get(0));
                } else {ErrorDetected = true;}            
            break;
            
            case 20:                
                if (NumberList.get(0) > 0) {
                    answer = LogB10(NumberList.get(0));
                } else {ErrorDetected = true;} 
            break;
            
            case 21:
                answer = Percent(NumberList.get(0));    
            break;
        } 
        if (ErrorDetected == false) {
            String answerAsString = Double.toString(answer);
            AnswerDisplay.setText(answerAsString);
            double quickie = answer; // idk yet
            answer = Double.parseDouble(AnswerDisplay.getText());
            NumberList.clear();
            OperationChosen = 0; 
        } 
        else { // runs if there was an error detected (e.g. SQRT(-2), 2.45!, etc.)
            AnswerDisplay.setText("Erorr: Press Clear");
            NumberList.clear();
            OperationChosen = 0;
            ErrorDetected = false;
        }
        MainFrame.repaint();
    } 
    
               //-------NUMERICAL BUTTONS TO BE PRESSED BY USER--------//
   
    class DecimalListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            if (isNewNumber == false) {
                QuickOutput1 = AnswerDisplay.getText();
                QuickOutput2 = QuickOutput1+".";
                AnswerDisplay.setText(QuickOutput2);
            }
            else {
                AnswerDisplay.setText("");
                QuickOutput1 = AnswerDisplay.getText();
                QuickOutput2 = QuickOutput1+".";
                AnswerDisplay.setText(QuickOutput2);
                isNewNumber = false; 
            }
            MainFrame.repaint(); 
        }  
    }
    class MinusSignListener implements ActionListener {        
        public void actionPerformed (ActionEvent event) {
            if (isNewNumber == false) {
                if (AnswerDisplay.getText() == "") {
                    AnswerDisplay.setText("-");
                }
                else if (AnswerDisplay.getText() == "-") {
                    AnswerDisplay.setText("");
                }
                else {
                    double QuickNegativeDouble; 
                    String QuickNegativeString;
                    QuickNegativeDouble = Double.parseDouble(AnswerDisplay.getText());
                    QuickNegativeDouble = QuickNegativeDouble * -1;
                    QuickNegativeString = Double.toString(QuickNegativeDouble);
                    AnswerDisplay.setText(QuickNegativeString);
                }
            } else {
                AnswerDisplay.setText("");
                if (AnswerDisplay.getText() == "") {
                    AnswerDisplay.setText("-");
                }
                else if (AnswerDisplay.getText() == "-") {
                    AnswerDisplay.setText("");
                }
                else {
                    double QuickNegativeDouble; 
                    String QuickNegativeString;
                    QuickNegativeDouble = Double.parseDouble(AnswerDisplay.getText());
                    QuickNegativeDouble = QuickNegativeDouble * -1;
                    QuickNegativeString = Double.toString(QuickNegativeDouble);
                    AnswerDisplay.setText(QuickNegativeString);
                }
                isNewNumber = false;
            } 
            MainFrame.repaint();
        }  
    }
    
    class NumberListener0 implements ActionListener {  
        public void actionPerformed (ActionEvent event) {
            numberPrinter("0");
        }  
    }
    class NumberListener1 implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            numberPrinter("1");
        }  
    }    
    class NumberListener2 implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            numberPrinter("2");
        }
    }
    class NumberListener3 implements ActionListener { 
        public void actionPerformed (ActionEvent event) { //make like others after testing
            numberPrinter("3");    
        }
    }
    class NumberListener4 implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            numberPrinter("4");   
        }
    }
    class NumberListener5 implements ActionListener { 
        public void actionPerformed (ActionEvent event) {
            numberPrinter("5");
        }
    }
    class NumberListener6 implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            numberPrinter("6"); 
        }
    }
    class NumberListener7 implements ActionListener {        
        public void actionPerformed (ActionEvent event) {
            numberPrinter("7"); 
        }
    }  
    class NumberListener8 implements ActionListener {        
        public void actionPerformed (ActionEvent event) {
            numberPrinter("8"); 
        }
    }  
    class NumberListener9 implements ActionListener {        
        public void actionPerformed (ActionEvent event) {
            numberPrinter("9"); 
        }
    }
    
            //-------------------FUNCTION BUTTONS TO BE PRESSED BY USER---------------------//
           
    //-------Multi-Number Input Functions--------//
    
    class AddListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            twoNumberInputProcessing(1);    
        }
    }
  
    class SubListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            twoNumberInputProcessing(2);    
        }
    }
    
    class MultListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            twoNumberInputProcessing(3);    
        }
    }
    class DivListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            twoNumberInputProcessing(4);   
        }
    }
    class YPowerListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            twoNumberInputProcessing(5);   
        }
    }
    class nCrListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            twoNumberInputProcessing(6);   
        }
    }
    class nPrListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            twoNumberInputProcessing(7);   
        }
    }
    class LogPListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            twoNumberInputProcessing(8);   
        }
    }
    //-------Single-Number Input Functions--------//
    
    class SquareListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(9);   
        }
    }
    class CubeListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(10);   
        }
    }
    class SquareRootListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(11);   
        }
    }
    class NaturalLogListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(12);   
        }
    }
    class SinListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(13);   
        }
    }
    class CosListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(14);   
        }
    }
    class TanListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(15);   
        }
    }    
    class SinInvListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(16);   
        }
    }
    class CosInvListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(17);   
        }
    }
    class TanInvListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(18);   
        } 
    }    
    class FactorialListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(19);   
        }
    }
    class LogB10Listener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(20);   
        }
    }
    class PercentListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            oneNumberInputProcessing(21);   
        }
    } 
    //^^^^Should add hyperbolics as conditional^^^^^     
    
    //----------Other Functions Utlized-----------//
    
    class EqualListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            try {
                Equals();
                FirstRoundOperations = true;
                isNewNumber = true; 
                
            } 
            catch (Exception ex) {
                AnswerDisplay.setText("Erorr: Press Clear");
                MainFrame.repaint();
            }
        }
    }

    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            Clear(); 
       }
    }
    class PiListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            AnswerDisplay.setText(String_Pi);
            MainFrame.repaint();
        }  
    }
    class eListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            AnswerDisplay.setText(String_e);
            MainFrame.repaint();
        }  
    }
    class RandListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            Random rand = new Random();            
            double QuickRandomNum = rand.nextDouble();
            String QuickRandomString;
            QuickRandomString = Double.toString(QuickRandomNum);        
            AnswerDisplay.setText(QuickRandomString);
            MainFrame.repaint();
        }  
    }
    class HypListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            if (isHyperbolic == false) {
                isHyperbolic = true;
                HypDisplay.setText("Hyp");
            }
            else {
                isHyperbolic = false;
                HypDisplay.setText("");
            }
            MainFrame.repaint();
        }  
    }
    class DegListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            if (inDegrees == false) {
                inDegrees = true;
                DegRadDisplay.setText("Deg");
            }
            else {
                inDegrees = false;
                DegRadDisplay.setText("Rad");
            }
            MainFrame.repaint();
        }  
    }
}    

public class MyGUICalculatorOOP9 {

    public static void main(String[] args) {
       
        CoolCalc MyCalculator = new CoolCalc(); 
        MyCalculator.Run();    
    }   
}
