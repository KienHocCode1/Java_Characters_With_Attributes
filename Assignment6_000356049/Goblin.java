package Assignment6_000356049;
/**
 *  This is Goblin class, which represents a Goblin, a type of Monster, and extends the Monster class
 *  Goblins have a sworn enemy, and their score calculation is affected by the presence of the sworn enemy
 *  This class contains a constructor, getter, setter methods
 *  Goblin overrides the getBattleScore and toString methods from Monsters class
 *
 * @author TRUNG KIEN, BUI ~ 000356049
 * @author XUAN HUY PHAM ~ 000899551
 */

public class Goblin extends Monster {
    /** represents the sworn enemy of goblins */
    private Goblin swornEnemy;

    /**
     *
     * @param clanAffiliation the clan affiliation of the goblin
     * @param ferocity the ferocity attribute of the goblin
     * @param defense the defense attribute of the goblin
     * @param magic the magic attribute of the goblin
     * @param treasure number of treasure that goblin gains/loses
     * @param health health score of goblin
     * @param swornEnemy the sworn enemy of goblin
     */
    public Goblin(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health, Goblin swornEnemy) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health);
        this.swornEnemy = swornEnemy;//set the sworn enemy of goblin
    }

    /**
     * Constructor for the goblin class with only the clan affiliation specified
     * @param clanAffiliation the clan affiliation of the goblin.
     */
    public Goblin(String clanAffiliation) {
        super(clanAffiliation);//call the constructor of Monster class with the provided clan affiliation
        this.swornEnemy = null;//set the sworn enemy to null as a default value
    }

    /**
     * sets the sworn enemy of goblin
     * @param swornEnemy the object presenting the sworn enemy
     */
    public void setSwornEnemy(Goblin swornEnemy) {
        this.swornEnemy = swornEnemy;
    }

    /**
     * gets the sworn enemy of goblin
     * @return the goblin object representing the sworn enemy
     */
    public Goblin getSwornEnemy() {
        return swornEnemy;
    }

    /**
     * calculates the battle score of the goblin
     * if the sworn enemy is alive, the battle score is the average
     * of ferocity, defense and magic
     * if the sworn enemy is dead, the battle score is increased by 50%
     * @return the calculated battle scare of goblin
     */
    @Override
    public double getBattleScore() {
        double average = (getFerocity() + getDefense() + getMagic()) / 3.0;
        return (isSwornEnemyAlive()) ? average : average * 1.5;
    }

    /**
     * check if the sworn enemy is alive/dead
     * @return true if the enemy is otherwise,it's false
     */
    private boolean isSwornEnemyAlive() {
        return !swornEnemy.isDead();
    }

    /**
     * overrides to toString method to provide a custom string representation of goblin
     * @return information about the goblin, including affiliation, ferocity, defense,
     * magic, treasure, health left,amd the sworn enemy
     */
    @Override
    public String toString() {
        return super.toString() + ", Sworn Enemy: " + swornEnemy.getFullName();
    }
}
