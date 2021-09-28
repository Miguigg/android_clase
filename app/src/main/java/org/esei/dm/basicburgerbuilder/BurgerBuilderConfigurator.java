package org.esei.dm.basicburgerbuilder;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class BurgerBuilderConfigurator {

    public static String[] INGREDIENTS = new String[]{"onion", "salad", "tomato", "chesse"};
    public static double[] INGREDIENTS_COSTS = new double[]{0.25, 0.5, 0.75, 1};

    private boolean[] selectedIngredients;

    public BurgerBuilderConfigurator(){
        selectedIngredients = new boolean[INGREDIENTS.length];
    }

    public double calculatePrice(){
        double toret = 0;
        for (int i =0; i<selectedIngredients.length;i++){
            if(selectedIngredients[i]){
                toret+= INGREDIENTS_COSTS[i];
            }
        }
        return toret;
    }

    public boolean[] getSelectedIngredients() {
        return selectedIngredients;
    }

    public void setSelectedIngredients(boolean[] selectedIngredients) {
        this.selectedIngredients = selectedIngredients;
    }


}