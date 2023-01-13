import java.util.ArrayList;
public class DTO {
    private static DTO instance;
    private ArrayList<Integer> sequence = new ArrayList<Integer>();
    private ArrayList<String> record = new ArrayList<String>();
    private int count;

    private DTO(){}

    public static DTO getInstance(){
        if(instance == null){
            instance = new DTO();
        }
        return instance;
    }


    public ArrayList<Integer> getSequence() {
        return sequence;
    }

    public ArrayList<String> getRecord() {
        return record;
    }



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
