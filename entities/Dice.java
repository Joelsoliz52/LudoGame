package entities;

/**
 * Entity Dice
 * Logic of the dice.
 * - Can contains other values.
 * @param <T> Dice values type.
 *
 * @author JoelS
 * @version 1
 */
public class Dice<T> {
    // Fields of the class.
    private final String type;
    private T[] values;
    public T content;

    /**
     * Dice constructor
     * @param type Dice type.
     */
    public Dice(String type) {
        this.type = type;
        setValues();
    }

    /**
     * Set type of dice values.
     */
    @SuppressWarnings("unchecked")
	private void setValues() {
        if (type.equals("int")) {
            values = (T[]) DiceValues.intDice;
        } else {
            values = null;
        }
    }

    /**
     * Roll dice random and set the dice content.
     */
    public void throwDice() {
        try {
            int value = (int) (Math.random() * values.length);
            content = values[value];
        } catch (Exception exception) {
            content = null;
        }
    }
    
    /** 
     * Begin a player pay in array of aliance through its turn.
       */
    public int throwDiceRun(){
        return (int)(Math.random()*4)+1;
    }
    
    /**
     * Dice values according to dice type.
     */
    private static class DiceValues {
        public static Integer[] intDice = { 1, 2, 3, 4, 5, 6 };
    }
}