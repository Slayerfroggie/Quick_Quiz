
package quincy.squickquizbynetwork;

/**
 *
 * @author Franciscus Sluyter
 * 
 * Purpose: to get and set all the data for the question data
 */
public class QuestionDataRecord
{
    // Declarations of 8 Strings, used to store the QuestionNumber, Topic, Question, AnswerA,
    // AnswerB, AnswerC, AnswerD, and Correct Answer
    //      data in memory for EACH QuestionDataRecord.  That is: when one object is instantiated
	//      from this QuestionDataRecord class, it will record the detail for one Question.
    //      These properties - QuestionNumber, Topic, Question, AnswerA, AnswerB, AnswerC,
    //      AnswerD, CorrectAnswer - are set to private so a calling
    //      class is not able to assign or access the respective values directly.
    //      Access to these properties would be best managed through the respective
    //      getters and setters - see below.
    private String QuestionNumber, Topic, Question, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer = new String();
    
    /** --------------------------------------------------------
    * Purpose: Constructor for the class: QuestionDataRecord.
    *          When a PCDataRecord is instantiated, and no default entries
    *          for the 8 properties - QuestionNumber/Topic/Question/AnswerA/
    *          AnswerB/AnswerC/AnswerD/CorrectAnswer - are provided,
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
    *          When a QuestionDataRecord is instantiated and default entries
    *          for the 8 properties - QuestionNumber/Topic/Question/AnswerA/
    *          AnswerB/AnswerC/AnswerD/CorrectAnswer - are provided 
    *          by the calling class, this constructor will run.
    * @param   QuestionNumber, Topic, Question, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer.
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
    *          8 properties - QuestionNumber/Topic/Question/AnswerA/
    *          AnswerB/AnswerC/AnswerD/CorrectAnswer - all at the one time.
    * @param   QuestionNumber, Topic, Question, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer.
    * @returns nothing (void).
    * ----------------------------------------------------------
    */    
   public void setQuestionInfo(String questionNumber, String topic, String question,
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
    *          the QuestionNumber property.
    *          This method could include code to validate incoming
    *          QuestionNumber data.
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
    * Purpose: A method that will allow this QuestionDataRecord class
    *          to provide the calling class with the QuestionNumber data.
    *          This method allows this class to manage outgoing
    *          QuestionNumber.
    * @param   None.
    * @returns QuestionNumber (String).
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
