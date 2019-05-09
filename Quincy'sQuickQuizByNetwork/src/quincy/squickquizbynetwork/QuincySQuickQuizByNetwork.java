package quincy.squickquizbynetwork;

//<editor-fold defaultstate="collapsed" desc="Libraries">
//swing libraries
import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;
//</editor-fold>

/**
 * @author Franciscus Sluyter
 */

public class QuincySQuickQuizByNetwork extends JFrame implements ActionListener, MouseListener
{
    //<editor-fold defaultstate="collapsed" desc="Global Variables">
    private JLabel lblSortBy, lblCorrectAns, lblLinkedList, lblBinaryTree,
            lblPreOrder, lblInOrder, lblPostOrder, lblTitle,
            lblQuestionListTitle, lblCurrentQuestionTitle, lblTopic, lblQn,
            lblAnswerA, lblAnswerB, lblAnswerC, lblAnswerD, lblQuestionDetails;

    private JButton btnQuestionNumber, btnTopic, btnQuestionSort, btnExit,
            btnSendQuestion, btnPreOrderSave, btnPreOrderDisplay,
            btnInOrderSave, btnInOrderDisplay,
            btnPostOrderSave, btnPostOrderDisplay, btnBinaryTreeDisplay;

    public JTextArea txtLinkedList, txtBinaryTree, txtTopic, txtQuestion,
            txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD, txtCorrectAnswer;
    
    int maxEntries = 500;
    int numberOfEntries = 0;
    int currentEntry = 0;
    int questionNumber = 0;

    QuestionDataRecord[] QuestionInfo = new QuestionDataRecord[maxEntries];

    JTable table;

    MyModel questionModel;

    ArrayList<Object[]> dataValues;

    ArrayList<Object[]> questionValues;

    //BinaryNode root;
    
    DList dlist;
    
    String[] sortArray = new String[maxEntries];
    
    String dataFileName = "QuestionData.csv";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Runtime">
    public static void main(String[] args)
    {
        QuincySQuickQuizByNetwork QuizSystem = new QuincySQuickQuizByNetwork();
        QuizSystem.run();

    }
    
    private void run()
    {
        //sets up all the window based functions bounds, title, listeners, etc.
        setBounds(100, 200, 1080, 654);
        setTitle("Quiz By Network");
        getContentPane().setBackground(new Color(255, 254, 220));
        dlist = new DList("r", 0, 0);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowOpened(WindowEvent we)
            {
                readFile(dataFileName);
            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        
        //dlist = new DList("r", 0, 0);

        displayGUI();

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
        displayTables(springLayout);
        //displaySelectedQuestion();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Component Properties">
    private void displayLabels(SpringLayout layout)
    {
        lblTitle = LibraryComponents.LocateAJLabel(this, layout, "Quincy's Quick Quizzes", 5, 5, 1065, 40, 23, 0, 102, 0, 255, 255, 255, "", "");
        lblSortBy = LibraryComponents.LocateAJLabel(this, layout, "Sort By:", 10, 325, 600, 22, 16, 230, 237, 215, 0, 102, 0, "left", "");
        lblCorrectAns = LibraryComponents.LocateAJLabel(this, layout, "Correct Ans:", 630, 325, 435, 22, 16, 230, 237, 215, 0, 102, 0, "left", "");
        lblLinkedList = LibraryComponents.LocateAJLabel(this, layout, "Linked List:", 10, 352, 1055, 94, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblBinaryTree = LibraryComponents.LocateAJLabel(this, layout, "Binary Tree:", 10, 452, 1055, 94, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblPreOrder = LibraryComponents.LocateAJLabel(this, layout, "Pre-Order", 10, 565, 150, 50, 16, 0, 102, 0, 255, 255, 255, "", "top");
        lblInOrder = LibraryComponents.LocateAJLabel(this, layout, "In-Order", 458, 565, 150, 50, 16, 0, 102, 0, 255, 255, 255, "", "top");
        lblPostOrder = LibraryComponents.LocateAJLabel(this, layout, "Post-Order", 915, 565, 150, 50, 16, 0, 102, 0, 255, 255, 255, "", "top");
        lblQuestionListTitle = LibraryComponents.LocateAJLabel(this, layout, "Questions", 10, 50, 600, 22, 16, 230, 237, 215, 0, 102, 0, "", "");
        lblQuestionDetails = LibraryComponents.LocateAJLabel(this, layout, "Selected Question", 630, 50, 435, 22, 16, 230, 237, 215, 0, 102, 0, "", "");
        lblTopic = LibraryComponents.LocateAJLabel(this, layout, "Topic:", 630, 77, 435, 24, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblQn = LibraryComponents.LocateAJLabel(this, layout, "Qn:", 630, 101, 435, 57, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblAnswerA = LibraryComponents.LocateAJLabel(this, layout, "A:", 630, 158, 435, 40, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblAnswerB = LibraryComponents.LocateAJLabel(this, layout, "B:", 630, 198, 435, 40, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblAnswerC = LibraryComponents.LocateAJLabel(this, layout, "C:", 630, 238, 435, 40, 16, 230, 237, 215, 0, 102, 0, "left", "top");
        lblAnswerD = LibraryComponents.LocateAJLabel(this, layout, "D:", 630, 278, 435, 40, 16, 230, 237, 215, 0, 102, 0, "left", "top");
    }

    public void displayTextAreas(SpringLayout layout)
    {
        txtLinkedList = LibraryComponents.LocateAJTextArea(this, layout, 15, 377, 95, 4);
        txtBinaryTree = LibraryComponents.LocateAJTextArea(this, layout, 15, 477, 95, 4);
        txtTopic = LibraryComponents.LocateAJTextArea(this, layout, 676, 81, 35, 1);
        txtQuestion = LibraryComponents.LocateAJTextArea(this, layout, 676, 105, 35, 3);
        txtAnswerA = LibraryComponents.LocateAJTextArea(this, layout, 676, 162, 35, 2);
        txtAnswerB = LibraryComponents.LocateAJTextArea(this, layout, 676, 202, 35, 2);
        txtAnswerC = LibraryComponents.LocateAJTextArea(this, layout, 676, 242, 35, 2);
        txtAnswerD = LibraryComponents.LocateAJTextArea(this, layout, 676, 282, 35, 2);
        txtCorrectAnswer = LibraryComponents.LocateAJTextArea(this, layout, 720, 329, 3, 1);
    }

    private void displayButtons(SpringLayout layout)
    {
        btnQuestionNumber = LibraryComponents.LocateAJButton(this, this, layout, "Question #", 70, 325, 179, 22, 15);
        btnTopic = LibraryComponents.LocateAJButton(this, this, layout, "Topic", 249, 325, 179, 22, 15);
        btnQuestionSort = LibraryComponents.LocateAJButton(this, this, layout, "Question", 428, 325, 181, 22, 15);
        //btnExit = LibraryComponents.LocateAJButton(this, this, layout, " Exit ", 295, 325, 91, 22, 15);
        btnSendQuestion = LibraryComponents.LocateAJButton(this, this, layout, "Send", 760, 325, 305, 22, 15);
        btnPreOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 10, 593, 75, 22, 12);
        btnPreOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 85, 593, 75, 22, 12);
        btnInOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 458, 593, 75, 22, 12);
        btnInOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 533, 593, 75, 22, 12);
        btnPostOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 915, 593, 75, 22, 12);
        btnPostOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 990, 593, 75, 22, 12);
        btnBinaryTreeDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display Binary Tree", 890, 452, 170, 22, 15);
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="JTable">
    private void displayTables(SpringLayout layout)
    {
        // Create a panel to hold all other components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        add(topPanel);

        // Create column names
        String columnNames[] =
        {
            "Qn#", "Topic", "Question"
        };

        dataValues = new ArrayList();

//      dataValues.add(new Object[]
//      {
//            1", "Software", "OOP is an Acronym for:"
//      });
        // constructor of JTable model
        questionModel = new MyModel(dataValues, columnNames);

        // Create a new table instance
        table = new JTable(questionModel);

        // Configure some of JTable's paramters
        table.isForegroundSet();
        table.setShowHorizontalLines(false);
        add(table);

        // Change the text and background colours
        table.setSelectionForeground(new Color(255, 255, 255));
        table.setSelectionBackground(new Color(0, 102, 0));

        // Add the table to a scrolling pane, size and locate
        JScrollPane scrollPane = table.createScrollPaneForTable(table);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        topPanel.setPreferredSize(new Dimension(600, 241));
        layout.putConstraint(SpringLayout.WEST, topPanel, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, topPanel, 77, SpringLayout.NORTH, this);
        setJTableColumnsWidth(table, 600, 40, 120, 440);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);
        table.addMouseListener(this);
    }

    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages)
    {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++)
        {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++)
        {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
        }
    }

    class MyModel extends AbstractTableModel
    {

        ArrayList<Object[]> al;

        // the headers
        String[] header;

        // constructor 
        MyModel(ArrayList<Object[]> obj, String[] header)
        {
            // save the header
            this.header = header;
            // and the data
            al = obj;
        }

        // method that needs to be overload. The row count is the size of the ArrayList
        public int getRowCount()
        {
            return al.size();
        }

        // method that needs to be overload. The column count is the size of our header
        public int getColumnCount()
        {
            return header.length;
        }

        // method that needs to be overload. The object is in the arrayList at rowIndex
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            return al.get(rowIndex)[columnIndex];
        }

        // a method to return the column name 
        public String getColumnName(int index)
        {
            return header[index];
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btnQuestionNumber)
        {
            bubbleSort(dataValues);
            table.repaint();
        }

        if (e.getSource() == btnTopic)
        {
            insertionSort(dataValues);
            table.repaint();
        }

        if (e.getSource() == btnQuestionSort)
        {
            selectionSort(dataValues);
            table.repaint();
        }

        if (e.getSource() == btnSendQuestion)
        {
            System.out.println("Before Print");
            dlist.print();
            print();
        }

        if (e.getSource() == btnBinaryTreeDisplay)
        {

        }

        if (e.getSource() == btnPreOrderSave)
        {

        }

        if (e.getSource() == btnPreOrderDisplay)
        {

        }

        if (e.getSource() == btnInOrderSave)
        {

        }

        if (e.getSource() == btnInOrderDisplay)
        {

        }

        if (e.getSource() == btnPostOrderSave)
        {

        }

        if (e.getSource() == btnPostOrderDisplay)
        {

        }
    }

    public void mouseClicked(MouseEvent e)
    {
        displaySelectedQuestion();
    }

    //<editor-fold defaultstate="collapsed" desc="Unused Events">
    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {

    }

    public void mouseReleased(MouseEvent e)
    {

    }
    //</editor-fold>

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Read Data File">
    public void readFile(String fileName)
    {
        // Try to read in the data and if an exception occurs go to the Catch section 
        try
        {
            // Set up vaious streams for reading in the content of the data file.
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int i = 0;   // i is used as the line counter
            String line; // line is used to temporarily store the line read in from the data file

            // Read a line from the data file into the buffer and then check whether
            //      it is null.  The while loop continues until a line read in is null.
            while ((line = br.readLine()) != null)
            {
                // Split the line of data (from the text file) and put each entry into the
                //                                             temporary array - temp[]
                String[] temp = line.split(",");

                QuestionInfo[i] = new QuestionDataRecord(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);
                
                
                dlist.head.append(new LinkedListNode(temp[1], Integer.parseInt(temp[0]), Integer.parseInt(temp[0])));
//                txtTopic.setText(temp[1]);
//                txtQuestion.setText(temp[2]);
//                txtAnswerA.setText(temp[3]);
//                txtAnswerB.setText(temp[4]);
//                txtAnswerC.setText(temp[5]);
//                txtAnswerD.setText(temp[6]);
//                txtCorrectAnswer.setText(temp[7]);

                dataValues.add(new Object[]
                {
                    temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]
                });

                questionModel.fireTableDataChanged();

                questionNumber = Integer.valueOf(temp[0]);

                i++;  // Increment i so we can keep a count of how many entries have been read in.
            }

            numberOfEntries = i;   // Set numberOfEntries equal to i, so as to remember how many entries are now in the arrays 

            br.close();            // Close the BufferedReader
            in.close();            // Close the DataInputStream
            fstream.close();       // Close the FileInputStream

            System.out.println("Data file has been read");
        } catch (Exception e)
        {
            // If an exception occurs, print an error message on the console.
            System.err.print("Error Reading File: " + e.getMessage());
        }
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Sorts">
    public static void bubbleSort(ArrayList<Object[]> arr)
    {

        for (int j = 0; j < arr.size(); j++)
        {
            for (int i = j + 1; i < arr.size(); i++)
            {
                if ((arr.get(i)[0]).toString().compareToIgnoreCase(arr.get(j)[0].toString()) < 0)
                {
                    Object[] words = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, words);
                }
            }
        }
    }

    public static void insertionSort(ArrayList<Object[]> arr)
    {
        int j; // the number of items sorted so far
        Object[] key; // the item to be inserted
        int i;

        for (j = 1; j < arr.size(); j++) // Start with 1 (not 0)
        {
            key = arr.get(j);
            //for (i = j - 1; (i >= 0) && (arr.get(i) < key); i--)
            for (i = j - 1; (i >= 0) && ((arr.get(i)[1]).toString().compareToIgnoreCase(key[1].toString()) > 0); i--) // Smaller values are moving up
            {
                arr.set((i + 1), arr.get(i));
            }
            arr.set((i + 1), key); // Put the key in its proper location
        }
    }

    public static void selectionSort(ArrayList<Object[]> arr)
    {
        int i, j, first;
        Object[] temp;
        for (i = (arr.size() - 1); i > 0; i--)
        {
            first = 0; //initialize to subscript of first element
            for (j = 1; j <= i; j++) //locate smallest element between positions 1 and i.
            {
                if ((arr.get(j)[2]).toString().compareToIgnoreCase(arr.get(first)[2].toString()) > 0)
                {
                    first = j;
                }
            }
            temp = arr.get(first); //swap smallest found with element in position i.
            arr.set((first), arr.get(i));
            arr.set(i, temp);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Display Selected Question">
    public void displaySelectedQuestion()
    {
        currentEntry = table.getSelectedRow();
        txtTopic.setText(dataValues.get(currentEntry)[1].toString());
        txtQuestion.setText(dataValues.get(currentEntry)[2].toString());
        txtAnswerA.setText(dataValues.get(currentEntry)[3].toString());
        txtAnswerB.setText(dataValues.get(currentEntry)[4].toString());
        txtAnswerC.setText(dataValues.get(currentEntry)[5].toString());
        txtAnswerD.setText(dataValues.get(currentEntry)[6].toString());
        txtCorrectAnswer.setText(dataValues.get(currentEntry)[7].toString());
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Print Linked List">
    public void print()
    {                  // print content of list
        if (dlist.head.next == dlist.head)
        {             // list is empty, only header Node
            txtLinkedList.setText("list empty");
            return;
        }
        txtLinkedList.setText("list content = ");
        for (LinkedListNode current = dlist.head.next; current != dlist.head.prev; current = current.next)
        {
            txtLinkedList.append(current.questionQN + " - " + current.questionTopic + ", ");
        }
        txtLinkedList.append("");
    }
    //</editor-fold>
}
