package Assignment6_000356049;

import java.util.Random;
/**
 * this abstract class represents a Monster, and it is the base class for various types of monsters
 * it contains attributes and methods common to all monsters,
 * such as clan affiliation, attributes, treasure, and health
 * The main purpose of this class is to define common behavior and characteristics shared by all monsters
 *  *
 *
 * @author TRUNG KIEN, BUI ~ 000356049
 * @author XUAN HUY PHAM ~ 000899551
 */
public abstract class Monster {
    /** represent the clan affiliation of the monster */
    private final String clanAffiliation;

    /** represent the ferocity attribute of the monster*/
    private int ferocity;

    /** represent the defense attribute of the monster */
    private int defense;

    /** represent the magic attribute of the monster */
    private int magic;

    /** represent the amount of treasure the monster possesses */
    private int treasure;

    /** represent the health points of the monster  */
    private int health;

    /**
     * Constructor for the Monster class.
     * @param clanAffiliation the clan affiliation of the monster
     * @param ferocity the ferocity attribute of the monster
     * @param defense The defense attribute of the monster
     * @param magic the magic attribute of the monster
     * @param treasure  amount of treasure the monster possesses
     * @param health The health points of the monster
     */
    public Monster(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health) {
        this.clanAffiliation = clanAffiliation;
        this.ferocity = validateAttribute(ferocity);
        this.defense = validateAttribute(defense);
        this.magic = validateAttribute(magic);
        // the treasure cannot be negative; if health is zero, set the treasure to zero
        this.treasure = (health > 0) ? treasure : 0;
        // health cannot be negative, if health is zero, set it to zero
        this.health = (health > 0) ? health : 0;
    }

    /**
     * it generates random values for attributes ferocity, defense, and magic
     * the default value for treasure is 0, and health is set to 100
     * @param clanAffiliation the clan affiliation of the monster
     */
    public Monster(String clanAffiliation) {
        this(clanAffiliation, getRandomAttribute(), getRandomAttribute(), getRandomAttribute(), 0, 100);
    }

    /**
     * to ensure it falls within the range [0, 20].
     * @param value the attribute value to be validated
     * @return the validated attribute value
     */
    private int validateAttribute(int value) {
        return Math.max(0, Math.min(20, value));
    }

    /**
     * generates a random attribute value in the range [0, 20].
     * @return a random attribute value.
     */
    private static int getRandomAttribute() {
        Random random = new Random();
        return random.nextInt(21);
    }

    /**
     * subclasses must implement this method to provide a battle score calculation mechanism specific to each monster type
     * @return  battle score of the monster.
     */
    public abstract double getBattleScore();
    /**
     * method to attack another monster.
     * it compares the battle scores of the monsters to determine the winner
     * also calculate the damage and treasure exchange
     * @param opponent the monster to be attacked.
     */

    public void attack(Monster opponent) {
        if (isDead() || opponent.isDead()) {// check if either monster is dead
            // if yes, they cannot attack or be attacked
            System.out.println("Error: Dead monsters cannot attack or be attacked.");
            return;
        }

        double myScore = getBattleScore();
        double opponentScore = opponent.getBattleScore();
        Random random = new Random();
        int opponentTreasure = opponent.getTreasure();

        // calculate the damage and reduce the opponent's health
        if (myScore > opponentScore) {
            double damage = myScore - opponentScore;
            opponent.takeDamage(damage);
            // randomly select the amount of treasure to be taken from the opponent and
            // also add it to the current monster.
            int takeTreasure = random.nextInt(opponentTreasure + 1);
            this.gainTreasure(takeTreasure);
            opponent.loseTreasure(takeTreasure);
        } else {
            // calculate the damage and reduce the current monster's health
            double damage = opponentScore - myScore;
            this.takeDamage(damage);
            // randomly select the amount of treasure to be taken from the current
            // monster and add it to the opponent
            int takeTreasure = random.nextInt(opponentTreasure + 1);
            this.loseTreasure(takeTreasure);
            opponent.gainTreasure(takeTreasure);
        }

    }

    /**
     * increase the specified attribute by 1
     * @param attributeName the of the attribute to be increased (ferocity, defense, or magic)
     */
    public void increaseAttribute(String attributeName) {
        switch (attributeName) {
            case "ferocity":
                setFerocity(getFerocity() + 1);
                break;
            case "defense":
                setDefense(getDefense() + 1);
                break;
            case "magic":
                setMagic(getMagic() + 1);
                break;
            default:
                System.out.println("Invalid attribute name.");
                break;
        }
    }
    /**
     * decrease the specified attribute by 1
     * @param attributeName The name of the attribute to be decreased (ferocity, defense, or magic).
     */
    public void decreaseAttribute(String attributeName) {
        switch (attributeName) {
            case "ferocity":
                setFerocity(getFerocity() - 1);
                break;
            case "defense":
                setDefense(getDefense() - 1);
                break;
            case "magic":
                setMagic(getMagic() - 1);
                break;
            default:
                System.out.println("Invalid attribute name.");
                break;
        }
    }

    /**
     * for the monster to gain a specified amount of treasure.
     * if the monster is dead, an error message is printed,
     * and it cannot gain treasure
     * @param amount amount of treasure to be gained
     */
    public void gainTreasure(int amount) {
        if (isDead()) {
            System.out.println("Error: " + getFullName() + " is dead and cannot gain treasure.");
            return;
        }

        treasure += amount;
        System.out.println(getFullName() + " gained " + amount + " treasure.");
    }

    /**
     * if the monster is dead, an error message is printed, and it cannot lose treasure.
     * the treasure cannot be negative
     * if the specified amount results in negative treasure, it is set to zero
     * @param amount amount of treasure to be lost
     */
    public void loseTreasure(int amount) {
        if (isDead()) {
            System.out.println("Error: " + getFullName() + " is dead and cannot lose treasure.");
            return;
        }

        int remainingTreasure = Math.max(0, treasure - amount);
        int lostAmount = treasure - remainingTreasure;
        treasure = remainingTreasure;
        System.out.println(getFullName() + " lost " + lostAmount + " treasure.");
    }

    /**
     * this method to take damage by reducing the health points
     * if the monster is dead, an error message is printed, and it also can't take more damage
     * the health cannot be negative
     * if the damage exceeds the current health, it is set to zero (indicating the monster is dead)
     * @param damage  amount of damage to be taken
     */
    public void takeDamage(double damage) {
        if (isDead()) {
            System.out.println("Error: " + getFullName() + " is already dead and cannot take more damage.");
            return;
        }

        int newHealth = Math.max(0, health - (int) damage);
        if (newHealth == 0) {
            System.out.println(getFullName() + " has been defeated.");
        }
        health = newHealth;
    }

    /**
     * check if the monster is dead
     * @return True if the monster's health is zero (dead), false otherwise
     */
    public boolean isDead() {
        return 0 == health;
    }

    /** getter method to retrieve the amount of treasure the monster possesses.
     * @return amount of treasure the monster possesses
     * */
    public int getTreasure() {
        return treasure;
    }

    /**
     * retrieve the full name of the monster
     * The full name is composed of the class name (simple name) and the clan affiliation of the monster.
     * @return full name of the monster
     */
    public String getFullName() {
        return this.getClass().getSimpleName() + " from clan " + clanAffiliation;
    }

    /**
     * setter method to set the ferocity attribute of the monster

     * @param ferocity the new ferocity value to be set
     */
    public void setFerocity(int ferocity) {
        this.ferocity = validateAttribute(ferocity);
    }

    /**
     * setter method to set the defense attribute of the monster
     * @param defense The new defense value to be set.
     */
    public void setDefense(int defense) {
        this.defense = validateAttribute(defense);
    }

    /**
     * setter method to set the magic attribute of the monster
     * @param magic the new magic value to be set
     */
    public void setMagic(int magic) {
        this.magic = validateAttribute(magic);
    }

    /**
     * retrieve the ferocity attribute of the monster
     * @return ferocity attribute of the monster
     */
    public int getFerocity() {
        return ferocity;
    }


    /**
     * retrieve the defense attribute of the monster
     * @return The defense attribute of the monster
     */
    public int getDefense() {
        return defense;
    }

    /**
     * retrieve the magic attribute of the monster
     * @return magic attribute of the monster
     */
    public int getMagic() {
        return magic;
    }

    /**
     * @return The health points of the monster.
     */
    public int getHealth() {
        return health;
    }
    /**
     * set the health points of the monster
     * @param health the new health points value to be set
     */
    public void setHealth(int health) {
        this.health = health;
    }
    /**
     * @param treasure The new amount of treasure to be set.
     */
    public void setTreasure(int treasure) {
        this.treasure = treasure;
    }

    /**
     * this method receives healing and increases its health points by the specified amount
     * if the monster is dead, an error message is printed, and it can't be healed.
     * @param healthBoost amount of health points to be added as healing
     */
    public void takeHealing(int healthBoost) {
        if (isDead()) {
            System.out.println("Error: " + getFullName() + " is dead and cannot be healed.");
            return;
        }

        int currentHealth = getHealth();
        int newHealth = currentHealth + healthBoost;
        int healedAmount = newHealth - currentHealth; // Calculate the actual amount of healing
        setHealth(newHealth); // Update health after healing
        System.out.println(getFullName() + " was healed by " + healedAmount + " health points.");
    }

    /**
     * @return formatted string representation of the Monster object
     */
    @Override
    public String toString() {
        String status = isDead() ? "Dead" : "Alive";
        return getFullName() + ", Status: " + status + ", Ratings: Ferocity-" + ferocity + ", Defense-" + defense + ", Magic-" + magic + ", Treasure-" + treasure + ", Health-" + health;
    }

}
