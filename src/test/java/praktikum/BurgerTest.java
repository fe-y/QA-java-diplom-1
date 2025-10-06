package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock1;
    private Ingredient ingredientMock2;

    @Before
    public void setUp() {
        burger = new Burger();
        bunMock = mock(Bun.class);
        ingredientMock1 = mock(Ingredient.class);
        ingredientMock2 = mock(Ingredient.class);

        when(bunMock.getName()).thenReturn("Test Bun");
        when(bunMock.getPrice()).thenReturn(100f);

        when(ingredientMock1.getName()).thenReturn("Ingredient 1");
        when(ingredientMock1.getPrice()).thenReturn(50f);
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);

        when(ingredientMock2.getName()).thenReturn("Ingredient 2");
        when(ingredientMock2.getPrice()).thenReturn(75f);
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
    }
    @After
    public void tearDown() {
        System.out.println("Current Receipt:");
        System.out.println(burger.getReceipt());
    }
    @Test
    //("Тест установки булки")
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    //("Тест добавления ингредиента")
    public void testAddIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0));
    }

    @Test
    //("Тест удаления ингредиента")
    public void testRemoveIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(0));
    }

    @Test
    //("Тест перемещения ингредиента")
    public void testMoveIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientMock2, burger.ingredients.get(0));
        assertEquals(ingredientMock1, burger.ingredients.get(1));
    }

    @Test
    //("Тест расчета цены")
    public void testGetPrice() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        float expectedPrice = bunMock.getPrice() * 2 + ingredientMock1.getPrice() + ingredientMock2.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }

    @Test
    //("Тест получения чека")
    public void testGetReceipt() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        String expectedReceipt = String.format("(==== %s ====)%n= %s %s =%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n",
                bunMock.getName(), ingredientMock1.getType().toString().toLowerCase(), ingredientMock1.getName(),
                ingredientMock2.getType().toString().toLowerCase(), ingredientMock2.getName(),
                bunMock.getName(), burger.getPrice());
        System.out.println("Receipt:");
        System.out.println(burger.getReceipt());
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}