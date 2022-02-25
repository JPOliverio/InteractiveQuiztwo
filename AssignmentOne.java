import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class AssignmentOne {
    public static void main(String[] args) throws FileNotFoundException {
        
        //******** Gets the file name ***************/
        txtSelectorinterface texts = new txtSelectorinterface();
        String txtName=null;
        while(txtName==null){
            txtName=texts.getTxtName();
        }
        //**************************************** */

        AnswerLocator aLocator = new AnswerLocator(txtName);
        QuestionLocator qLocator = new QuestionLocator(txtName);
        int qCount = qLocator.getQuestionCount();

        //************Question place lis*******************/
        // creates list with values 1 to number of questions then shuffles it.
        // This is used to pull random questions.
        ArrayList<Integer> list = new ArrayList<Integer>(qCount);
        for(int i = 1; i <= qCount; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        ListIterator<Integer> iterator = list.listIterator();
        //*************************************************/


        //****** Opens a new Interactive Quiz Interface******** */
        Interface intFace = new Interface();
        //**************************************************** */

        //*********************Interactive Quiz Logic********* */
        int place;
        AnswerReader aReader;
        QuestionReader qReader;
        long startTime = System.currentTimeMillis();
        long currentTime;
        long time;
        Boolean nextQuestion = true;

        int j = 0;
        while(qCount>=j){
            currentTime = System.currentTimeMillis();
            time = (currentTime-startTime)/1000;

            if(nextQuestion){
                j++;
                if(iterator.hasNext()){
                    currentTime = System.currentTimeMillis();
                    time = (currentTime-startTime)/1000;
                    place = iterator.next();
                    aReader = new AnswerReader(txtName, aLocator.getAnswerLocation(place));
                    qReader = new QuestionReader(txtName, qLocator.getQuestionLocation(place));
                    intFace.updateInterface(qReader.getQuestion(), aReader.getChoices(), aReader.getSolution(), aReader.getNumberofChoices());
                }
                intFace.statsUpdate(time);
                nextQuestion = false;
            }else{ 
                intFace.statsUpdate(time);
            }
            nextQuestion = intFace.nextQuestion();
        }

        currentTime = System.currentTimeMillis();
        time = (currentTime-startTime)/1000;
        intFace.endGame(time);
        //************************************************************** */


    }
    
}
