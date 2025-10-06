package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class BunTest {

    // Метод для предоставления параметров
    private Object[] parametersForTestBunCreation() {
        return new Object[]{
                new Object[]{"black bun", 100},
                new Object[]{"white bun", 200},
                new Object[]{"red bun", 300},
        };
    }

    @Test
    @Parameters(method = "parametersForTestBunCreation")
    public void testBunCreation(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice(), 0.01);
    }
}