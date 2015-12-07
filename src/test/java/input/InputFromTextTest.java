package input;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InputFromTextTest {

    private InputFromText inputFromText;

    @Before
    public void setUp() throws Exception{
        inputFromText = new InputFromText();
    }

    @Test
    public void return_list_length_0_when_text_is_empty() throws Exception{
        inputFromText.setTextName(  "emptyFile.txt" );

        assertThat( inputFromText.getInputList().size(), is(0) );
    }

    @Test
    public void return_list_length_1_when_text_is_empty() throws Exception{
        inputFromText.setTextName(  "lengthOneFile.txt" );

        assertThat( inputFromText.getInputList().size(), is(1) );
    }

    @Test
    public void return_list_length_4_when_text_is_empty() throws Exception{
        inputFromText.setTextName( "lengthFourFile.txt" );

        assertThat( inputFromText.getInputList().size(), is(4) );
    }
}
