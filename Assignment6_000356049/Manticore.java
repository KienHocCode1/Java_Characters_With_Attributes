package Assignment6_000356049;
/**
 * This class represents a Manticore, which is a type of Monster.
 * Manticores have a current clan affiliation and can change clans if they are not dead.
 * The Manticore class contains constructors, a method to change the clan, and overrides the getBattleScore and toString methods from the Monster class.
 *
 * @author TRUNG KIEN, BUI ~ 000356049
 * @author XUAN HUY PHAM ~ 000899551
 */
public class Manticore extends Monster {
    /** represents the current clan affiliation of the Manticore */
    private String currentClan;

    /**
     * @param clanAffiliation the initial clan affiliation of the Manticore
     * @param ferocity the ferocity attribute of the Manticore
     * @param defense the defense attribute of the Manticore
     * @param magic the magic attribute of the Manticore
     * @param treasure amount of treasure the Manticore gains/loses
     * @param health health points of the Manticore
     */
    public Manticore(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health);
        this.currentClan = clanAffiliation; // set the initial clan affiliation of the Manticore.
    }

    /**
     * constructor for the Manticore class with only the clan affiliation specified
     * @param clanAffiliation initial clan affiliation of the Manticore
     */
    public Manticore(String clanAffiliation) {
        super(clanAffiliation);
        this.currentClan = clanAffiliation; // set the initial clan affiliation of the Manticore.
    }

    /**
     * change the clan affiliation of the Manticore to the specified new clan
     * if the Manticore is dead, the clan cannot be changed, and an error message is printed
     * @param newClan the new clan affiliation to be set for the Manticore
     */
    public void changeClan(String newClan) { // check if the Manticore is dead. If yes, display an error message and return.
        if (isDead()) {
            System.out.println("Error: " + getFullName() + " is dead and cannot change clans.");
            return;
        }
        currentClan = newClan; // set the new clan affiliation for the Manticore.
    }

    /**
     * calculate the battle score of the Manticore
     * battle score is the average of ferocity, defense, and magic, multiplied by 1.5
     * @return the calculated score of the Manticore
     */
    @Override
    public double getBattleScore() {
        double average = (getFerocity() + getDefense() + getMagic()) / 3.0;
        return average * 1.5;
    }

    /**
     * @return  information about the Manticore
     * this includes its clan affiliation, ferocity, defense, magic, treasure, health, and current clan
     */
    @Override
    public String toString() {
        return super.toString() + ", Current Clan: " + currentClan; // additional information about the current clan affiliation
    }
}
