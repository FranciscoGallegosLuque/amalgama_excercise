package amalgama_excercise;

public class BattleDetails {
    private int battleID;

    private int enemyArmyID;
    private int enemyPoints;

    private int ownPoints;

    BattleDetails(int battleID, int enemyArmyID, int enemyPoints, int ownPoints) {
        this.battleID = battleID;
        this.enemyArmyID = enemyArmyID;
        this.enemyPoints = enemyPoints;
        this.ownPoints = ownPoints;
    }

    public int getBattleID() {
        return battleID;
    }

    public int getEnemyArmyID() {
        return enemyArmyID;
    }

    public int getEnemyPoints() {
        return enemyPoints;
    }
    
    public int getOwnPoints() {
        return ownPoints;
    }
}
