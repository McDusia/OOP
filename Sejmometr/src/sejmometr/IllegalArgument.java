
public class IllegalArgument extends Exception{

    public IllegalArgument(String ex){
        super(ex);
    };
    public String getMessage(){
        return super.getMessage();
    }
}
