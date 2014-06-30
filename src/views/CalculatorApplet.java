package views;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

/*import jstuff.Calculator.CalcButton;
import jstuff.Calculator.ControlButton;
import jstuff.Calculator.DigitButton;
import jstuff.Calculator.FunctionButton;
import jstuff.Calculator.UnaryButton;*/

public class CalculatorApplet {
	
	
	 private Class ENGINE = null; 
	 private final CalculatorEngine engine = new CalculatorEngine();
	 final JTextField display = new JTextField();
	 JPanel cp = new JPanel();
	    
	    /**
	     *  Create a new calculator instance.
	     */ 
	    /*public void init(){        
	        //setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
	        setName( "Calculator" );   
	        display.setEditable(false);
	        display.setBackground(Color.yellow);   
	                
	        // set up a Class object used in actionPerformed()
	        // to invoke methods on the CalculatorEngine
	        ENGINE = engine.getClass();
	        
	        buildGUI();     
	        //pack();
	        //setResizable(false);
	        setLAF();
	        setSize(500,200);
	        //center( this );
	        setVisible(true); 
	    }*/

	    //Container cp = getContentPane();
	    public void buildGUI(){

	        ENGINE = engine.getClass();
	        cp.setLayout( new BoxLayout( cp, BoxLayout.Y_AXIS ) );
	        
	        display.setEditable(false); 
	        display.setBorder(new LineBorder(new Color(255, 0, 102), 5, true));
	        display.setForeground(Color.blue);
	        display.setFont(new Font("Tahoma", 1, 25));
	        
	        cp.add( display );
	        cp.add( buildControlPanel() );
	        cp.add( buildButtonPanels() );                                              
	    }
	    
	    private JPanel buildControlPanel(){
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	                        
	        panel.add( Box.createHorizontalGlue() );
	        panel.add( new ControlButton( "Backspace", "backspace" ) );
	        panel.add( Box.createRigidArea( new Dimension( 2, 0 ) ) );
	        
	        JPanel panel2 = new JPanel( new GridLayout( 1, 1, 2, 2 ) );
	        panel2.add( new ControlButton( "CE", "clearEntry" ) );
	        panel2.add( new ControlButton( "C", "clear" ) );
	        panel.add( panel2 );
	        
	        return panel;
	    }   
	    
	    private JPanel buildButtonPanels() {
	        JPanel buttons = new JPanel();
	        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
	        //buttons.setFont(new Font("Courier", 10, Font.BOLD));
	        buttons.setFont(new Font("Tahoma", 1, 14));
	                
	        buttons.add( buildUnaryPanel() );               
	        buttons.add( buildDigitPanel() );
	        buttons.add( buildFunctionPanel() );                

	        return buttons;
	    }
	        
	    private JPanel buildDigitPanel(){
	        JPanel panel = new JPanel();        
	        panel.setLayout( new GridLayout( 4, 3, 2, 2 ) );                
	        
	        panel.add( new DigitButton( "7" ) );
	        panel.add( new DigitButton( "8" ) );
	        panel.add( new DigitButton( "9" ) );    
	        
	        panel.add( new DigitButton( "4" ) );
	        panel.add( new DigitButton( "5" ) );
	        panel.add( new DigitButton( "6" ) );    
	        
	        panel.add( new DigitButton( "1" ) );
	        panel.add( new DigitButton( "2" ) );
	        panel.add( new DigitButton( "3" ) );    

	        panel.add( new DigitButton( "0" ) );
	        panel.add( new DigitButton( "." ) );
	        
	        // not a digit but added here to balance out the panel      
	        panel.add( new UnaryButton( " +/- ", "sign" ) );        
	        
	        return panel;       
	    }
	    

	    private JPanel buildFunctionPanel(){
	        JPanel buttons = new JPanel( new GridLayout( 4, 3, 2, 2 ) );
	        
	        buttons.add( new FunctionButton( "/", "divide" ) );             
	        buttons.add( new FunctionButton( "&", "and" ) );        
	        buttons.add( new FunctionButton( "<<", "leftShift" ) );
	                    
	        buttons.add( new FunctionButton( "*", "multiply" ) );                       
	        buttons.add( new FunctionButton( "|", "divide" ) );                     
	        buttons.add( new FunctionButton( ">>", "rightShift" ) );
	        
	        buttons.add( new FunctionButton( "-", "subtract" ) );
	        buttons.add( new FunctionButton( "^" , "xor" ) );
	        buttons.add( new FunctionButton( "pow" ) );

	        buttons.add( new FunctionButton( "+", "add" ) );
	        buttons.add( new FunctionButton( "=", "equals" ) );
	        buttons.add( new FunctionButton( "mod" ) );
	        
	        return buttons;             
	        
	    }
	    
	    private JPanel buildUnaryPanel(){
	        JPanel buttons = new JPanel( new GridLayout( 4, 3, 2, 2 ) );
	        
	        buttons.add( new UnaryButton( "sin" ) );
	        buttons.add( new UnaryButton( "cos" ) );
	        buttons.add( new UnaryButton( "tan" ) );
	        buttons.add( new UnaryButton( "asin" ) );
	        
	        buttons.add( new UnaryButton( "acos" ) );
	        buttons.add( new UnaryButton( "atan" ) );
	        buttons.add( new UnaryButton( "log" ) );
	        buttons.add( new UnaryButton( "deg", "degrees" ) );
	        
	        buttons.add( new UnaryButton( "rad", "radians" ) );             
	        buttons.add( new UnaryButton( "sqrt" ) );
	        
	        buttons.add( new UnaryButton( "%", "percent" ) );       
	        buttons.add( new UnaryButton( "1/x", "reciprocal" ) );
	        
	        return buttons;     
	        
	    }
	    
	    /*
	     *  Center a component on the screen.
	     * 
	     *  @param window the component to be centered.
	     */
	    private void center( Window window ) {

	        // Get the size of the screen
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	        // Determine the new location of the window
	        int w = window.getSize().width;
	        int h = window.getSize().height;
	        int x = (dim.width - w) / 2;
	        int y = (dim.height - h) / 2;

	        // Move the window
	        window.setLocation(x, y);
	    }

	    /*
	     *  Set the Look and Feel to the system look and feel.
	     */
	    private void setLAF() {
	        // Get the native look and feel class name
	        String nativeLF = UIManager.getSystemLookAndFeelClassName();

	        // Install the look and feel
	        try {
	            UIManager.setLookAndFeel(nativeLF);
	        } catch (InstantiationException e) {
	            System.out.println( e.getMessage() );
	        } catch (ClassNotFoundException e) {
	            System.out.println( e.getMessage() );
	        } catch (UnsupportedLookAndFeelException e) {
	            System.out.println( e.getMessage() );
	        } catch (IllegalAccessException e) {
	            System.out.println( e.getMessage() );
	        }
	    }
	    
	    
	    /*
	     *  Helper class to handle button formatting.
	     *  Each button acts as its own listener.
	     */
	    private class CalcButton extends JButton implements ActionListener{
	        
	        CalcButton( String s, String action ){
	            super( s );
	            setActionCommand( action );
	            setMargin( new Insets( 2, 2, 2, 2 ) );
	            setFont(new Font("Tahoma", 1, 14));
	            setForeground( Color.WHITE );
	            addActionListener( this );
	        }
	        
	        /*
	         *  Captures the button events and then uses 'reflection'
	         *  to invoke the right method in the calculator engine
	         * 
	         *  Digit buttons are handled slightly different as they
	         *  all use the digit( int ) method and their values must
	         *  be passed as arguments. 
	         * 
	         *  The digit button for the decimal has special handling; 
	         *  new Integer( "." ) throws a NumberFormatException, 
	         *  have to use new Integer( '.' ) which converts the ASCII
	         *  value of '.' to an integer.
	         * 
	         */ 
	        
	        public void actionPerformed(ActionEvent e) {

	            String methodName = e.getActionCommand();

	            Method method = null;
	            
	            try {
	                if ( e.getSource() instanceof DigitButton ) {
	                    method =
	                        ENGINE.getMethod("digit", new Class[] { int.class });

	                    if (methodName.equals(".")) {
	                        method.invoke(engine, new Object[] { new Integer( '.' )});
	                    } else {
	                        method.invoke(engine, new Object[] { 
	                                                new Integer( methodName )});
	                    }
	                } else {
	                    method = ENGINE.getMethod(methodName, null);
	                    method.invoke(engine, null);
	                }
	            } catch (NoSuchMethodException ex) {
	                System.out.println("No such method: " + methodName);
	            } catch (IllegalAccessException ea) {
	                System.out.println("Illegal access" + methodName);
	            } catch (InvocationTargetException et) {
	                System.out.println("Target exception: " + methodName);
	            }
	            
	            display.setText(engine.display());
	        }
	    }

	    private class DigitButton extends CalcButton {
	        DigitButton( String s ){
	            super( s, s );
	            setForeground( Color.BLUE );
	        }
	    }

	    private class FunctionButton extends CalcButton {
	        FunctionButton( String s ){
	            this( s, s );           
	        }
	        
	        FunctionButton( String s, String action ){          
	            super( s, action );
	            setBackground( Color.GRAY );
	        }
	    }
	    
	    private class ControlButton extends CalcButton{
	        ControlButton( String s ){
	            this( s, s );
	        }
	        
	        ControlButton( String s, String action ){
	            super( s, action );
	            setBackground( Color.RED );
	        }
	    }
	    
	    private class UnaryButton extends CalcButton {
	        UnaryButton( String s ){
	            this( s, s );
	        }
	        
	        UnaryButton( String s, String action ){
	            super( s, action );
	            setBackground( Color.BLUE );
	        }
	    }

}
