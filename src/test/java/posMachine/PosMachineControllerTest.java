package posMachine;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PosMachineControllerTest {

    @Test
    public void should_consume_return_308() throws Exception{
        PosMachineController  posMachineController = new PosMachineController( "/src/main/resources/" );

        assertThat( posMachineController.consume(), is( 258d ) );
    }

}
