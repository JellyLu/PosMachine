package input;

import java.util.ArrayList;

public abstract class AbstractInput {
    protected ArrayList<String> inputList;

    public AbstractInput() {
       this.inputList = new ArrayList<String>();
    }

    public ArrayList<String> getInputList() throws Exception{
        return inputList;
    }
}
