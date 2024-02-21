package Assignment6_000356049;

import java.util.ArrayList;
import java.util.List;
/**
 * This class represents an Orc, which is a type of Monster
 * Orcs can have a leadership point and may be considered a Warlord
 * They can command infantry Orcs and sound a battle cry to heal their infantry
 * Orc class contains constructors, methods to change the clan, add/remove infantry, heal infantry, etc
 *
 * @author TRUNG KIEN, BUI ~ 000356049
 * @author XUAN HUY PHAM ~ 000899551
 */
public class Orc extends Monster {
    /** declare if the orc is Warlord or not */
    private boolean isWarlord;

    /** Declare leadership point for the Warlord */
    private int leadership;

    /** a list of infantry Orcs commanded by this Orc (if it is a Warlord) */
    private List<Orc> infantry;

    /** the Warlord Orc commanding this Orc (if it is part of an infantry) */
    private Orc commandingWarlord;


    /**
     * method to sound the battle cry and heal the infantry Orcs (if applicable)
     * the healing amount is based on the leadership points
     */
    public void soundBattleCry() {
        if (isDead()) {
            System.out.println("Error: " + getFullName() + " is dead and cannot sound the battle cry.");
            return;
        }

        if (!isWarlord()) {
            System.out.println("Error: " + getFullName() + " is not a Warlord and cannot sound the battle cry.");
            return;
        }

        int healthBoost = leadership * 5;

        if (infantry.isEmpty()) {
            System.out.println("Error: " + getFullName() + " has no infantry to heal.");
            return;
        }
        System.out.println(getFullName() + " is Warlord sounded the battle cry, healing " + infantry.size()
                + " Infantry by " + healthBoost + " health points each.");
        healInfantry(healthBoost);

    }

    /**
     * @param clanAffiliation the clan affiliation of the Orc
     * @param ferocity the ferocity attribute of the Orc
     * @param defense the defense attribute of the Orc
     * @param magic the magic attribute of the Orc
     * @param treasure the amount of treasure the Orc possesses
     * @param health the health points of the Orc
     * @param isWarlord a flag indicating whether the Orc is a Warlord or not
     */
    public Orc(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health, boolean isWarlord) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health);
        this.isWarlord = isWarlord;
    }

    /**
     * @param clanAffiliation the clan affiliation of the Orc
     * @param ferocity the ferocity attribute of the Orc
     * @param defense the defense attribute of the Orc
     * @param magic the magic attribute of Orc
     * @param treasure the amount of treasure the Orc possesses
     * @param health the health points of the Orc
     * @param isWarlord a flag that indicates whether the Orc is a Warlord or not
     * @param leadership the leadership points for the Warlord Orc
     */
    public Orc(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health, boolean isWarlord, int leadership) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health);
        this.isWarlord = isWarlord;// set whether the Orc is a Warlord or not
        this.leadership = Math.max(1, Math.min(5, leadership));// ensure the leadership points are between 1 and 5 (inclusive)
        this.infantry = new ArrayList<>();// initialize the list to hold infantry Orcs
    }

    /**
     * Checks if the Orc is a Warlord.
     * @return True if the Orc is a Warlord, false otherwise.
     */
    public boolean isWarlord() {
        return isWarlord;
    }
    /**
     * gets the leadership points of the Warlord Orc
     * @return leadership points of the Warlord Orc
     */

    public int getLeadership() {
        return leadership;
    }

    /**
     * gets the list of infantry Orcs commanded by this Orc (if it is a Warlord)
     * @return the list of infantry Orcs
     */
    public List<Orc> getInfantry() {
        return infantry;
    }

    /**
     * gets the Warlord Orc commanding this Orc (if it is part of an infantry)
     * @return the commanding Warlord Orc
     */
    public Orc getCommandingWarlord() {
        return commandingWarlord;
    }


    /**
     * set the leadership points for the Warlord Orc
     * the value is constrained to be between 1 and 5 (inclusive)
     * @param leadership the leadership points to be set.
     */
    public void setLeadership(int leadership) {
        this.leadership = Math.max(1, Math.min(5, leadership));
    }

    /**
     * sets the Warlord Orc commanding this Orc
     * @param commandingWarlord the commanding Warlord Orc to be set
     */
    public void setCommandingWarlord(Orc commandingWarlord) {
        this.commandingWarlord = commandingWarlord;
    }

    /**
     * add an infantry Orc to the list of commanded infantry
     * if the Orc is dead, an error message is printed
     * if the Orc is already part of the infantry, a message is printed indicating so
     * if the Orc to be added is a Warlord, an error message is printed
     * @param orc the infantry Orc to be added
     */
    public void addInfantry(Orc orc) {
        if (isDead()) {
            System.out.println("Error: " + getFullName() + " is dead and cannot command Infantry.");
            return;
        }

        if (infantry.contains(orc)) {
            System.out.println(orc.getFullName() + " is already part of " + getFullName() + "'s Infantry.");
            return;
        }

        if (orc.isWarlord()) {
            System.out.println("Error: A Warlord cannot be part of another Warlord's Infantry.");
            return;
        }

        infantry.add(orc);
        orc.setCommandingWarlord(this);
    }

    /**
     * to remove an infantry Orc from the list of commanded infantry
     * if the Orc is dead, an error message is printed
     * if the Orc is not part of the infantry, a message is printed indicating so
     * @param orc the infantry Orc to be removed
     */
    public void removeInfantry(Orc orc) {
        if (isDead()) {
            System.out.println("Error: " + getFullName() + " is dead and cannot command Infantry.");
            return;
        }

        if (!infantry.contains(orc)) {
            System.out.println(orc.getFullName() + " is not part of " + getFullName() + "'s Infantry.");
            return;
        }

        infantry.remove(orc);
        orc.setCommandingWarlord(null);
    }

    /**
     * heal the infantry Orcs by the given health boost
     * if the Orc is dead, an error message is printed
     * @param healthBoost amount of health points by which the infantry Orcs are healed
     */
    public void healInfantry(int healthBoost) {
        if (isDead()) {
            System.out.println("Error: " + getFullName() + " is dead and cannot heal Infantry.");
            return;
        }

        for (Orc orc : infantry) {
            if (!orc.isDead()) {
                orc.takeHealing(healthBoost);
            }
        }
    }

    /**
     * if the Orc is dead, an error message is printed out
     * @param amount the amount of treasure to be gained
     */
    @Override
    public void gainTreasure(int amount) {
        if (isDead()) {
            System.out.println("Error: " + getFullName() + " is dead and cannot gain treasure.");
            return;
        }

        setTreasure(getTreasure() + amount);

        if (isWarlord) {// if the orc is a warlord, update the leadership with the treasure bonus
            int leadershipBoost = amount / 10;
            setLeadership(getLeadership() + leadershipBoost);
            System.out.println(getFullName() + " gained " + amount + " treasure and received a leadership boost of +" + leadershipBoost);
        } else {
            System.out.println(getFullName() + " gained " + amount + " treasure.");
        }
    }

    /**
     * if the Orc is a Warlord, the battle score is increased by 50% (average * 1.5),
     * otherwise, the average battle score is returned
     * @return  calculated battle score of the Orc
     */
    @Override
    public double getBattleScore() {
        double average = (getFerocity() + getDefense() + getMagic()) / 3.0;
        return isWarlord ? average * 1.5 : average;
    }

    /**
     * If the Orc is a Warlord, information about its leadership points and infantry count is included in the string
     * @return return information
     */
    @Override
    public String toString() {
        String warlordInfo = isWarlord ? (", Warlord, Leadership: " + leadership) : "";
        String infantryCount = isWarlord ? (", Infantry Count: " + infantry.size()) : "";
        return super.toString() + warlordInfo + infantryCount;
    }
}
