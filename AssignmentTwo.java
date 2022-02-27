import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Scanner;

public class AssignmentTwo {

    public static void main(String[] args) throws FileNotFoundException {
        txtSelector txt = new txtSelector();
        System.out.println("Below is a list of txt files found. Please enter the corrisponding number to choise that file.");
        txt.printTxtNames();

        Scanner scan = new Scanner(System.in);
        int txtNumber = scan.nextInt();
        String txtName = txt.getTxtName(txtNumber);
        System.out.println(txtName);

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

        double correct = 0;
        double wrong = 0;
        long startTime = System.currentTimeMillis();

        for(int j=0; j<qCount;j++){
        int place = iterator.next();

 
        AnswerReader aReader = new AnswerReader(txtName, aLocator.getAnswerLocation(place));
        QuestionReader qReader = new QuestionReader(txtName, qLocator.getQuestionLocation(place));


        System.out.println("Question:");
        System.out.println(qReader.getQuestion());
        
        System.out.println("");
        System.out.println("Choices:");
        for(int i=1; i<=aReader.getNumberofChoices(); i++){
            System.out.println(i+") "+aReader.getChoice(i));
        }
        int choice = scan.nextInt();

        if(choice == aReader.getSolution()){
            System.out.println("Correct");
            correct++;
        }else{
            System.out.println("Wrong");
            wrong++;
        }
    }
    scan.close();
    long endTime = System.currentTimeMillis();
    double totalQuestions = wrong+correct;
    double percentCorrect = (correct/totalQuestions)*100;

    System.out.println("");
    System.out.println("*******************Quiz Statistics****************");
    System.out.println("play time: "+(endTime-startTime)/1000+" secounds");
    System.out.println("Questions Answerd: "+totalQuestions);
    System.out.println("Questions Answerd correctly: "+correct);
    System.out.println("Precent correct: "+percentCorrect+"%");
    System.out.println("**************************************************");
    }
    
}
