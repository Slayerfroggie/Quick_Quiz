/**
 * --------------------------------------------------------
 * Class: LibraryComponents
 *
 * @author Frank Sluyter
 * Developed: 2018
 *
 * Purpose: To contain a library of utility methods that can be accessed from other Java applications
 *
 * Currently:
 *  - LocateAJLabel - for positioning a JLabel using the layout manager: SpringLayout
 *  - LocateAJTextField - for positioning a JTextField using SpringLayout
 *  - LocateAJButton - for positioning a JButton using SpringLayout
 *  - LocateAJTextArea - for positioning a JTextArea using SpringLayout
 *
 * ----------------------------------------------------------
 */
package quincy.squickquizbynetwork;

//<editor-fold defaultstate="collapsed" desc="Libraries">
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
//</editor-fold>

public class LibraryComponents
{
    //<editor-fold defaultstate="collapsed" desc="JLabel">

    /**
     * -------------------------------------------------------- Purpose: Locate
     * a single JLabel within a JFrame.
     *
     * @param JFrame, Layout_manager, JLabel_Caption, Width, X_position,
     * Y_Position, Font_Size, Background_Red, Background_Green, Background_Blue,
     * Foreground_Red, Foreground_Green, Foreground_Blue
     * @returns The JLabel.
     * ----------------------------------------------------------
     */
    public static JLabel LocateAJLabel(JFrame myJFrame, SpringLayout myJLabelLayout, String JLabelCaption, int x, int y, int w, int h, int F, int rB, int gB, int bB, int rF, int gF, int bF, String Horizontal, String Vertical)
    {
        // Declare and Instantiate the JLabel
        JLabel myJLabel = new JLabel(JLabelCaption);
        // Add the JLabel to the screen
        myJFrame.add(myJLabel);
        // Set the position of the JLabel (From left hand side of the JFrame (West), and from top of JFrame (North))
        myJLabelLayout.putConstraint(SpringLayout.WEST, myJLabel, x, SpringLayout.WEST, myJFrame);
        myJLabelLayout.putConstraint(SpringLayout.NORTH, myJLabel, y, SpringLayout.NORTH, myJFrame);
        myJLabel.setPreferredSize(new Dimension(w, h));
        myJLabel.setFont(new Font("Helvetica", Font.PLAIN, F));
        myJLabel.setBackground(new Color(rB, gB, bB));
        myJLabel.setForeground(new Color(rF, gF, bF));
        if (Horizontal == "right")
        {
            myJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        } else if (Horizontal == "left")
        {
            myJLabel.setHorizontalAlignment(SwingConstants.LEFT);
        } else
        {
            myJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (Vertical == "top")
        {
            myJLabel.setVerticalAlignment(SwingConstants.TOP);
        } else if (Vertical == "bottom")
        {
            myJLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        } else
        {
            myJLabel.setVerticalAlignment(SwingConstants.CENTER);
        }
        myJLabel.setOpaque(true);
        // Return the label to the calling method
        return myJLabel;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="JTextField">
    /**
     * -------------------------------------------------------- Purpose: Locate
     * a single JTextField within a JFrame.
     *
     * @param JFrame, KeyListener, Layout_manager, Width, X_position, Y_Position
     * @returns The JTextField.
     * ----------------------------------------------------------
     */
    public static JTextField LocateAJTextField(JFrame myJFrame, KeyListener myKeyLstnr, SpringLayout myJTextFieldLayout, int width, int x, int y)
    {
        JTextField myJTextField = new JTextField(width);
        myJFrame.add(myJTextField);
        myJTextField.addKeyListener(myKeyLstnr);
        myJTextFieldLayout.putConstraint(SpringLayout.WEST, myJTextField, x, SpringLayout.WEST, myJFrame);
        myJTextFieldLayout.putConstraint(SpringLayout.NORTH, myJTextField, y, SpringLayout.NORTH, myJFrame);
        return myJTextField;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="JButton">
    /**
     * -------------------------------------------------------- Purpose: Locate
     * a single JButton within a JFrame.
     *
     * @param JFrame, ActionListener, Layout_manager, JButton_name,
     * JButton_caption, X_position, Y_Position, Width, Height
     * @returns The JButton.
     * ----------------------------------------------------------
     */
    public static JButton LocateAJButton(JFrame myJFrame, ActionListener myActnLstnr, SpringLayout myJButtonLayout, String JButtonCaption, int x, int y, int w, int h, int F)
    {
        JButton myJButton = new JButton(JButtonCaption);
        myJFrame.add(myJButton);
        myJButton.addActionListener(myActnLstnr);
        myJButtonLayout.putConstraint(SpringLayout.WEST, myJButton, x, SpringLayout.WEST, myJFrame);
        myJButtonLayout.putConstraint(SpringLayout.NORTH, myJButton, y, SpringLayout.NORTH, myJFrame);
        myJButton.setPreferredSize(new Dimension(w, h));
        myJButton.setFont(new Font("Helvetica", Font.PLAIN, F));
        return myJButton;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="JTextArea">
    /**
     * -------------------------------------------------------- Purpose: Locate
     * a single JTextArea within a JFrame.
     *
     * @param JFrame, Layout_manager, X_position, Y_Position, Width, Height
     * @returns The JTextArea.
     * ----------------------------------------------------------
     */
    public static JTextArea LocateAJTextArea(JFrame myJFrame, SpringLayout myTextAreaLayout, int x, int y, int w, int h)
    {
        JTextArea myJTextArea = new JTextArea(h, w);
        myJFrame.add(myJTextArea);
        myTextAreaLayout.putConstraint(SpringLayout.WEST, myJTextArea, x, SpringLayout.WEST, myJFrame);
        myTextAreaLayout.putConstraint(SpringLayout.NORTH, myJTextArea, y, SpringLayout.NORTH, myJFrame);
        myJTextArea.setPreferredSize(new Dimension(w, h));
        myJTextArea.setEditable(false);
        myJTextArea.setLineWrap(true);
        myJTextArea.setWrapStyleWord(true);
        return myJTextArea;

    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Jtable WIP">
//    public static JTable LocateAJTable(JFrame myJFrame, SpringLayout myTableLayout, int x, int y, int w, int h)
//    {
//        JTable myJtable = new JTable(w, h);
//        
//    }
    //</editor-fold>
}
