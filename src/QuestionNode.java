public class QuestionNode {
    String type, text;
    QuestionNode yes;
    QuestionNode no;

    public QuestionNode(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public QuestionNode(String type, String text, QuestionNode y, QuestionNode n){
        this.type = type;
        this.text = text;
        yes = y;
        no = n;

    }
}
