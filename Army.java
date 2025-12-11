package amalgama_excercise;

import java.util.ArrayList;
import java.util.Random;

public class Army {
    private int ID;
    private int coins;
    private int points;
    private ArrayList<Soldier> soldiers;
    private ArrayList<BattleDetails> battlesHistory;

    Army(int ID, Civilization c) {
        this.ID = ID;
        this.coins = 1000;
        this.points = 0;
        this.soldiers = assignSoldiers(c.getnumberOfPikemen(), c.getnumberOfArchers(), c.getnumberOfKnights());
        this.battlesHistory = new ArrayList<BattleDetails>();
    }

    // The upgradeSoldier method takes a soldier ID and a boolean to indicate whether the soldier is trained (true) or transformed (false)
    public void upgradeSoldier(int soldierID, boolean train) {
        // If the soldier ID is greater than the number of soldiers in the army, the method aborts.
        if (soldierID >= this.getSoldiers().size()) {
            return;
        }

        Soldier soldier = soldiers.get(soldierID);
        int upgradeCost;
        
        
        if (train) {
            upgradeCost = soldier.getTrainingCost();
        } else {
            upgradeCost = soldier.getTransformationCost();
        }

        // If the army hasn't got enough coins to upgrade the soldier, the method aborts.
        if (coins >= upgradeCost) {
            int soldierOldPoints = soldier.getPoints();
            if (train) {
                soldier.train();
            } else {
                soldier.transform();
            }
            int soldierNewPoints = soldier.getPoints();

            this.updateCoins(-upgradeCost);
            points += soldierNewPoints - soldierOldPoints;
        }

    }

    public void attack(Army enemy) {
        int ownID = this.getID();
        int enemyID = enemy.getID();

        if (ownID == enemyID) {
            return;
        }

        int ownPoints = this.getPoints();
        int enemyPoints = enemy.getPoints();
        int coinsWon = 100;

        boolean victory = ownPoints > enemyPoints;
        boolean defeat = ownPoints < enemyPoints;

        if (victory) {
            this.updateCoins(coinsWon);
            enemy.loseTwoBestSoldiers();
        } else if (defeat) {
            this.loseTwoBestSoldiers();
            enemy.updateCoins(coinsWon);
        } else {
            this.loseWorstSoldier();
            enemy.loseWorstSoldier();
        }

        // The battle is added to both army's Battle History
        BattleDetails ownBattleDetails = new BattleDetails(battlesHistory.size(), enemyID, enemyPoints, ownPoints);
        BattleDetails enemyBattleDetails = new BattleDetails(enemy.getBattleHistory().size(), ownID, ownPoints, enemyPoints);
        this.addBattleHistory(ownBattleDetails);
        enemy.addBattleHistory(enemyBattleDetails);
    }


    public void loseTwoBestSoldiers() {
        soldiers.sort((a, b) -> { return b.getPoints() - a.getPoints(); }); // Sorts the soldiers by descending points
        points -= soldiers.get(0).getPoints();
        soldiers.remove(0); // Removes the most powerful soldier
        points -= soldiers.get(0).getPoints();
        soldiers.remove(0); // Removes the second most powerful soldier
        soldiers.sort((a, b) -> { return a.getID() - b.getID(); }); // Sorts the soldiers by ascending ID 
    }

    public void loseWorstSoldier() {
        soldiers.sort((a, b) -> { return b.getPoints() - a.getPoints(); }); // Sorts the soldiers by descending points
        points -= soldiers.get(getSoldiers().size() - 1).getPoints();
        soldiers.remove(getSoldiers().size() - 1); // Removes the least powerful soldier
        soldiers.sort((a, b) -> { return a.getID() - b.getID(); }); // Sorts the soldiers by ascending ID 
    }

    public int getID() {
        return ID;
    }

    public int getCoins() {
        return coins;
    }

    public void updateCoins(int amount) {
        coins += amount;
    }

    public int getPoints() {
        return points;
    }

     public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    public ArrayList<BattleDetails> getBattleHistory() {
        return battlesHistory;
    }

    public void addBattleHistory(BattleDetails battleDetails) {
        battlesHistory.add(battleDetails);
    }

    // Creates an array of soldiers based on the number of pikemen, archers and knights of the civilization
    // Each soldier has an ID which initially corresponds with its position on the array
    private ArrayList<Soldier> assignSoldiers(int numberOfPikemen, int numberOfArchers, int numberOfKnights) {
        ArrayList<Soldier> soldiers = new ArrayList<Soldier>(numberOfPikemen + numberOfArchers + numberOfKnights);

        for (int ID = 0; ID < numberOfPikemen; ID++) {
            Soldier pikeman = new Soldier(ID, SoldierType.PIKEMAN, 20 + new Random().nextInt(30)); // The age of each soldier is a random number between 20 and 50
            soldiers.add(ID, pikeman);  
            points += pikeman.getPoints();
        }

        for (int ID = numberOfPikemen; ID < numberOfPikemen + numberOfArchers; ID++) {
            Soldier archer = new Soldier(ID, SoldierType.ARCHER, 20 + new Random().nextInt(30)); // The age of each soldier is a random number between 20 and 50
            soldiers.add(ID, archer);
            points += archer.getPoints();
        }

        for (int ID = numberOfPikemen + numberOfArchers; ID < numberOfPikemen + numberOfArchers + numberOfKnights; ID++) {
            Soldier knight = new Soldier(ID, SoldierType.KNIGHT, 20 + new Random().nextInt(30)); // The age of each soldier is a random number between 20 and 50
            soldiers.add(ID, knight);
            points += knight.getPoints();
        }

        return soldiers;
    }
}



