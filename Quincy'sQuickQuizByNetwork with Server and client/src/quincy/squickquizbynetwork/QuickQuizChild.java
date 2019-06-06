
package quincy.squickquizbynetwork;

//<editor-fold defaultstate="collapsed" desc="Libraries">
//swing libraries
import javax.swing.*;
import java.net.*;
import java.io.*;


import java.awt.event.WindowAdapter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
//</editor-fold>

public class QuickQuizChild extends JFrame implements ActionListener
{
    //<editor-fold defaultstate="collapsed" desc="Global Variables">
    
    // All labels 
    private JLabel lblStudentAns, lblTitle,lblCurrentQuestionTitle, lblTopic, lblQn,
            lblAnswerA, lblAnswerB, lblAnswerC, lblAnswerD, lblStudentName;

    // All buttons
    private JButton btnSendQuestion;
    
    // All Textareas
    public JTextArea txtServerDialog, txtTopic, txtQuestion, txtStudentName,
            txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD, txtStudentAns;
    
    String handleString;
    
    String sendString;
    
    private Socket socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    private ChatClientThread2 client2 = null;
    private String serverName = "localhost";
    private int serverPort = 4444;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Runtime">
    public static void main(String[] args)
    {
        QuickQuizChild QuizSystem = new QuickQuizChild();
        QuizSystem.run();

    }
    
    private void run()
    {
        //sets up all the window based functions bounds, title, listeners, etc.
        setBounds(100, 100, 450, 493);
        setTitle("Quiz By Network");
        getContentPane().setBackground(new Color(255, 254, 220));
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowOpened(WindowEvent we)
            {
            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                sendForCountdown();
                System.exit(0);
            }
        });
        
        //dlist = new DList("r", 0, 0);
        getParameters();
        
        connect(serverName, serverPort);
        
        sendForCount();
        
        displayGUI();
        
        setUndecorated(true);

        setResizable(false);

        setVisible(true);
    }

    private void displayGUI()
    {
        //intializes all the frame's components
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        displayTextAreas(springLayout);
        displayButtons(springLayout);
        displayLabels(springLayout);
        //displayTables(springLayout);
        //displaySelectedQuestion();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Component Properties">
    private void displayLabels(SpringLayout layout)
    {
        lblTitle = LibraryComponents.LocateAJLabel(this, layout, "Quincy's Quick Quizzes", 5, 5, 435, 60, 23, 0, 102, 0, 255, 255, 255, "", "");
        lblStudentName = LibraryComponents.LocateAJLabel(this, layout, "Your Name:", 5, 77, 435, 24, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblTopic = LibraryComponents.LocateAJLabel(this, layout, "Topic:", 5, 117, 435, 24, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblQn = LibraryComponents.LocateAJLabel(this, layout, "Qn:", 5, 141, 435, 57, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblAnswerA = LibraryComponents.LocateAJLabel(this, layout, "A:", 5, 198, 435, 40, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblAnswerB = LibraryComponents.LocateAJLabel(this, layout, "B:", 5, 238, 435, 40, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblAnswerC = LibraryComponents.LocateAJLabel(this, layout, "C:", 5, 278, 435, 40, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblAnswerD = LibraryComponents.LocateAJLabel(this, layout, "D:", 5, 318, 435, 40, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblStudentAns = LibraryComponents.LocateAJLabel(this, layout, "Your Answer:", 5, 365, 435, 22, 16, 230, 237, 215, 0, 102, 0, "left", "");
    }

    public void displayTextAreas(SpringLayout layout)
    {
        txtStudentName = LibraryComponents.LocateAJTextArea(this, layout, 91, 81, 31, 1);
        txtTopic = LibraryComponents.LocateAJTextArea(this, layout, 51, 121, 35, 1);
        txtQuestion = LibraryComponents.LocateAJTextArea(this, layout, 51, 145, 35, 3);
        txtAnswerA = LibraryComponents.LocateAJTextArea(this, layout, 51, 202, 35, 2);
        txtAnswerB = LibraryComponents.LocateAJTextArea(this, layout, 51, 242, 35, 2);
        txtAnswerC = LibraryComponents.LocateAJTextArea(this, layout, 51, 282, 35, 2);
        txtAnswerD = LibraryComponents.LocateAJTextArea(this, layout, 51, 322, 35, 2);
        txtStudentAns = LibraryComponents.LocateAJTextArea(this, layout, 98, 369, 1, 1);
        txtServerDialog = LibraryComponents.LocateAJTextArea(this, layout, 6, 395, 39, 4);
        
        txtStudentAns.setEditable(true);
        txtStudentName.setEditable(true);
    }

    private void displayButtons(SpringLayout layout)
    {
        btnSendQuestion = LibraryComponents.LocateAJButton(this, this, layout, "Submit Answer", 135, 365, 304, 22, 15);
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btnSendQuestion)
        {
            if (txtStudentName.getText().equals("") || txtStudentAns.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Enter Answer and/or Name");
            }
            else
            {
                send();
            }
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Server Methods">
    public void connect(String serverName, int serverPort)
    {
        System.out.println("Establishing connection. Please wait ...");
        try
        {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            open();
        }
        catch (UnknownHostException uhe)
        {
            System.out.println("Host unknown: " + uhe.getMessage());
        }
        catch (IOException ioe)
        {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
    }
    
    private void sendForCount()
    {
        try
        {
            streamOut.writeUTF("Count");
            
            streamOut.flush();
        }
        catch (IOException ioe)
        {
            System.out.println("Count sending error: " + ioe.getMessage());
            close();
        }
    }
    
    private void sendForCountdown()
    {
        try
        {
            streamOut.writeUTF("Countdown");
            
            streamOut.flush();
        }
        catch (IOException ioe)
        {
            System.out.println("Countdown sending error: " + ioe.getMessage());
            close();
        }
    }

    private void send()
    {
        try
        {
            streamOut.writeUTF("Child" + "," + sendString + "," + txtStudentAns.getText().toUpperCase());
            
            streamOut.flush();
            
            System.exit(0);
        }
        catch (IOException ioe)
        {
            System.out.println("Sending error: " + ioe.getMessage());
            close();
        }
    }

    public void handle(String msg)
    {
        if (msg.equals(".bye"))
        {
            System.out.println("Good bye. Press EXIT button to exit ...");
            close();
        }
        else
        {
            System.out.println("Handle:" + msg);
            System.out.println(msg);
            handleString = msg;
            DisplayHandleData();
        }
    }

    public void open()
    {
        try
        {
            streamOut = new DataOutputStream(socket.getOutputStream());
            client2 = new ChatClientThread2(this, socket);
        }
        catch (IOException ioe)
        {
            System.out.println("Error opening output stream: " + ioe);
        }
    }

    public void close()
    {
        try
        {
            if (streamOut != null)
            {
                streamOut.close();
            }
            if (socket != null)
            {
                socket.close();
            }
        }
        catch (IOException ioe)
        {
            System.out.println("Error closing ...");
        }
        client2.close();
        client2.stop();
    }

    public void getParameters()
    {
//        serverName = getParameter("host");
//        serverPort = Integer.parseInt(getParameter("port"));
        
        serverName = "localhost";
        serverPort = 4444;        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Question Functions">
    
    public void DisplayHandleData()
    {
        // Split the line of data (from the text file) and put each entry into the
        //                                             temporary array - temp[]
        String[] temp1 = handleString.split(":");
        String[] temp2 = temp1[1].split(",");
        
        if (temp2[0].equals("Ins"))
        {
            sendString = temp2[1] + "," + temp2[2] + "," + temp2[8];
            
            txtTopic.setText(temp2[2]);
            txtQuestion.setText(temp2[3]);
            txtAnswerA.setText(temp2[4]);
            txtAnswerB.setText(temp2[5]);
            txtAnswerC.setText(temp2[6]);
            txtAnswerD.setText(temp2[7]);
        }
    }   
    //</editor-fold>
}