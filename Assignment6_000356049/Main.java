
package Assignment6_000356049;

/**
 *  This assignment is about effectively using inheritance, association, overriding, and constructors
 *  This is Main class is used to display and test
 *
 * @author TRUNG KIEN, BUI ~ 000356049
 * @author XUAN HUY PHAM ~ 000899551
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create some instances of Monsters
        Manticore manticore1 = new Manticore("Clan A", 10, 10, 10, 20, 100);
        Manticore manticore2 = new Manticore("Clan B", 18, 7, 8, 20, 100);
        Goblin goblin1 = new Goblin("Clan A", 10, 10, 10, 50, 50, null);
        Goblin goblin2 = new Goblin("Clan B", 5, 5, 5, 30, 5, goblin1);
        goblin1.setSwornEnemy(goblin2);
        Orc orc1 = new Orc("Clan C", 20, 20, 20, 80, 100, true, 1);
        Orc orc2 = new Orc("Clan C", 14, 14, 14, 70, 50, false);
        Orc orc3 = new Orc("Clan C", 11, 11, 11, 50, 30, false);
        Orc orc4 = new Orc("Clan C", 12, 12, 12, 60, 40, false);
        Orc orc5 = new Orc("Clan C", 13, 13, 13, 55, 50, false);
        Orc orc6 = new Orc("Clan C", 15, 15, 15, 52, 57, false);
        orc1.addInfantry(orc2);
        orc1.addInfantry(orc3);
        orc1.addInfantry(orc4);
        orc1.addInfantry(orc5);
        orc1.addInfantry(orc6);
        // Print information about the Monsters
        System.out.println("=== Monster Information ===");
        System.out.println(manticore1);
        System.out.println(manticore2);
        System.out.println(goblin1);
        System.out.println(goblin2);
        System.out.println(orc1);
        System.out.println(orc2);
        System.out.println(orc3);
        System.out.println(orc4);
        System.out.println(orc5);
        System.out.println(orc6);
        // Test attacks
        System.out.println("\n=== Testing Attacks ===");
        manticore1.attack(orc2);
        orc1.attack(manticore1);
        // Print information about the Monsters
        System.out.println("=== Monster Information ===");
        System.out.println(manticore1);
        System.out.println(orc1);
        System.out.println(orc2);
        // Test attacks
        System.out.println("\n=== Testing Attacks ===");
        goblin1.attack(goblin2);
        goblin1.attack(orc2);
        System.out.println("=== Monster Information ===");
        System.out.println(goblin1);
        System.out.println(goblin2);
        System.out.println(orc2);
        // Test Warlord and Infantry
        System.out.println("\n=== Testing Warlord and Infantry ===");
        orc1.addInfantry(orc2);
        orc1.addInfantry(orc3);
        orc1.soundBattleCry();
        // Print information about the Monsters
        System.out.println("=== Monster Information ===");
        System.out.println(orc2);
        System.out.println(orc3);

        // Test Manticore changing clans
        System.out.println("\n=== Testing Manticore Changing Clans ===");
        manticore1.changeClan("Clan Z");
        System.out.println(manticore1);
    }

}
