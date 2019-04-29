/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quincy.squickquizbynetwork;

/**
 *
 * @author Franciscus Sluyter
 */
public class QuestionDataRecord
{
    // Declarations of 3 Strings, used to store the PCName, PCID and IP_Address
    //      data in memory for EACH PCDataRecord.  That is: when one object is instantiated
	//      from this PCDataRecord class, it will record the detail for one PC.
    // These properties - PCName, PCID and IP_Address - are set to private so a calling
    //      class is not able to assign or access the respective values directly.
    //      Access to these properties would be best managed through the respective
    //      getters and setters - see below.
    private String QuestionNumber, Topic, Question, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer = new String();
    
    /** --------------------------------------------------------
    * Purpose: Constructor for the class: PCDataRecord.
    *          When a PCDataRecord is instantiated, and no default entries
    *          for the 3 properties - PC_Name/PC_ID/IP_Address - are provided,
    *          this method will set default values for each.
    * @param   None.
    * @returns Not applicable.
    * ----------------------------------------------------------
    */    
    public QuestionDataRecord()
    {    
        QuestionNumber = "Question_Number";
        Topic = "Topic";
        Question = "Question";
        AnswerA = "A";
        AnswerB = "B";
        AnswerC = "C";
        AnswerD = "D";
        CorrectAnswer = "Correct_Answer";
    }

    /** --------------------------------------------------------
    * Purpose: A second constructor for the class: PCDataRecord.
    *          When a PCDataRecord is instantiated and default entries
    *          for the 3 properties - PC_Name/PC_ID/IP_Address - are
	*          provided by the calling class, this constructor will run.
    * @param   PC_Name, PC_ID and IP_Address.
    * @returns Not applicable.
    * ----------------------------------------------------------
    */    
    public QuestionDataRecord(String questionNumber, String topic, String question,
            String answerA, String answerB, String answerC, String answerD, String correctAnswer)
    {    
        QuestionNumber = questionNumber;
        Topic = topic;
        Question = question;
        AnswerA = answerA;
        AnswerB = answerB;
        AnswerC = answerC;
        AnswerD = answerD;
        CorrectAnswer = correctAnswer;
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    
    /** --------------------------------------------------------
    * Purpose: A method that will allow the calling class to set the
    *          3 properties - Name/ID/IP_Address - all at the one time.
    * @param   PC_Name, PC_ID and IP_Address.
    * @returns nothing (void).
    * ----------------------------------------------------------
    */    
   public void setPersonInfo(String questionNumber, String topic, String question,
            String answerA, String answerB, String answerC, String answerD, String correctAnswer)
    {    
        QuestionNumber = questionNumber;
        Topic = topic;
        Question = question;
        AnswerA = answerA;
        AnswerB = answerB;
        AnswerC = answerC;
        AnswerD = answerD;
        CorrectAnswer = correctAnswer;
    }
   
    /** --------------------------------------------------------
    * Purpose: A method that will allow the calling class to set
    *          the PCName property.
    *          This method could include code to validate incoming
    *          PC_Name data.
    * @param  0
    * @returns nothing (void).
    * ----------------------------------------------------------
    */
    public void setQuestionNumber(String questionNumber)
    {    
        QuestionNumber = questionNumber;
    }
    
    public void setTopic(String topic)
    {    
        Topic = topic;
    }
    
    public void setQuestion(String question)
    {    
       Question = question;
    }
    
    public void setAnswerA(String answerA)
    {    
        AnswerA = answerA;
    }
    
    public void setAnswerB(String answerB)
    {    
        AnswerB = answerB;
    }
    
    public void setAnswerC(String answerC)
    {    
        AnswerC = answerC;
    }
    
    public void setAnswerD(String answerD)
    {    
        AnswerD = answerD;
    }
    
    public void setCorrectAnswer(String correctAnswer)
    {    
        CorrectAnswer = correctAnswer;
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    /** --------------------------------------------------------
    * Purpose: A method that will allow this PCDataRecord class
    *          to provide the calling class with the PC_Name data.
    *          This method allows this class to manage outgoing
    *          PC_Names.
    * @param   None.
    * @returns PCName (String).
    * ----------------------------------------------------------
    */
    public String getQuestionNumber()
    {    
        return QuestionNumber;
    }
    
    public String getTopic()
    {    
        return Topic;
    }
    
    public String getQuestion()
    {    
        return Question;
    }
    
       public String getAnswerA()
    {    
        return AnswerA;
    }
       
       public String getAnswerB()
    {    
        return AnswerB;
    }
       
        public String getAnswerC()
    {    
        return AnswerC;
    }
        
        public String getAnswerD()
    {    
        return AnswerD;
    }
        
        public String getCorrectAnswer()
    {    
        return CorrectAnswer;
    }
    //</editor-fold>
}
