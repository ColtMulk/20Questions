import java.io.PrintStream;
import java.util.*;


public class QuestionTree {
    private QuestionNode top;
    Scanner console = new Scanner(System.in);
    QuestionTree(){
        top = new QuestionNode("a:", "computer");

    }
    void read(Scanner input){
        if(input.hasNextLine()) {
            String type = input.nextLine();
            String text = input.nextLine();
            top = new QuestionNode(type, text);
            initialize(top, input);
            System.out.println(top.yes.text + " " + top.no.text);
        }
        else
            throw new IllegalArgumentException();

    }
    private void initialize(QuestionNode a, Scanner input){
        if(input.hasNextLine()){
            String type = input.nextLine().trim().toLowerCase();
            String text = input.nextLine().trim().toLowerCase();
            //System.out.println(text);
            a.yes = new QuestionNode(type, text);
            if(type.equalsIgnoreCase("q:")){
                initialize(a.yes, input);

            }
            type = input.nextLine().trim().toLowerCase();
            text = input.nextLine().trim().toLowerCase();
            a.no = new QuestionNode(type,text);
            if(type.equalsIgnoreCase("q:")){
                initialize(a.no, input);
            }


        }

    }
    void write(PrintStream output){
        print(top, output);

    }
    private void print(QuestionNode a, PrintStream output){
        output.println(a.type);
        output.println(a.text);
        if(a.yes != null){
            QuestionNode b = a.yes;
            print(b,output);

        }
        if(a.no != null){
            QuestionNode b = a.no;
            print(a.no,output);
        }
    }

    void askQuestions(){
        String answer;
        String path = "";
        QuestionNode previous = top;
        QuestionNode current = top;
        while(current.type.equalsIgnoreCase("q:")){
            System.out.print(current.text + " (y/n)? ");
            answer = console.nextLine().trim().toLowerCase();
            previous = current;
            if(answer.equals("y")) {
                path = "y";
                current = current.yes;
            }
            else {
                path = "n";
                current = current.no;
            }
        }
        System.out.print("Would your object happen to be " + current.text + " (y/n)? ");
        answer = console.nextLine().trim().toLowerCase();
        if(answer.equals("y"))
            System.out.println("I got it right!!!");
        else{
            System.out.print("What is the name of your object? ");
            String object = console.nextLine();
            System.out.print("Please give me a yes/no question that \ndistinguishes between your object\nand mine--> ");
            String question = console.nextLine();
            System.out.print("And what is the answer for your object? (y/n)? ");
            String yesorno = console.nextLine().trim().toLowerCase();
            QuestionNode user = new QuestionNode("a:", object);

            if(top.type.equalsIgnoreCase("a:")){

                if(yesorno.equalsIgnoreCase("y")) {
                    top = new QuestionNode("q:", question, user, top);
                }
                else
                    top = new QuestionNode("q:", question, top, user);
            }
            else{
                if(path.equals("y")) {
                    if(yesorno.equals("y")) {
                        previous.yes = new QuestionNode("q:", question, user, current);
                    }
                    else
                        previous.yes = new QuestionNode("q:", question, current, user);
                }
                else{
                    if(yesorno.equals("y")) {
                        previous.no = new QuestionNode("q:", question, user, current);
                    }
                    else
                        previous.no = new QuestionNode("q:", question, current, user);
                }

            }

        }

    }
    // post: asks the user a question, forcing an answer of "y " or "n";

//       returns true if the answer was yes, returns false otherwise

    public boolean yesTo(String prompt) {

        System.out.print(prompt + " (y/n)? ");

        String response = console.nextLine().trim().toLowerCase();

        while (!response.equals("y") && !response.equals("n")) {

            System.out.println("Please answer y or n.");

            System.out.print(prompt + " (y/n)? ");

            response = console.nextLine().trim().toLowerCase();

        }

        return response.equals("y");

    }

}
