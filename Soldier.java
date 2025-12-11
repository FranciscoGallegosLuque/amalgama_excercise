package amalgama_excercise;

public class Soldier {
    private int ID;
    private SoldierType type;
    private int age;
    private int points; 
    private int trainingCost;
    private int transformationCost;

    Soldier(int ID, SoldierType type, int age) {
        this.ID = ID;
        this.type = type;
        this.age = age;
        switch (type) {
            case PIKEMAN:
                points = 5;
                trainingCost = 10;
                transformationCost = 30;
                break;
            case ARCHER:
                points = 10;
                trainingCost = 20;
                transformationCost = 40;
                break;
            case KNIGHT:
                points = 20;
                trainingCost = 30;
                transformationCost = 0;
                break;
        }
    }

    public int getID() {
        return ID;
    }

    public SoldierType getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public int getPoints() {
        return points;
    }

    public int getTrainingCost() {
        return trainingCost;
    }

    public int getTransformationCost() {
        return transformationCost;
    }

    public void train() {
        switch (type) {
            case PIKEMAN:
                points += 3;
                break;
            case ARCHER:
                points += 7;
                break;
            case KNIGHT:
                points += 10;
                break;
        }
    }

    public void transform() {
        switch (type) {
            case PIKEMAN:
                type = SoldierType.ARCHER;
                points = 10;
                break;
            case ARCHER:
                type = SoldierType.KNIGHT;
                points = 20;
                break;
            // Knights cannot be transformed
            case KNIGHT:
                break;
        }
    }
}

enum SoldierType {
    PIKEMAN,
    ARCHER,
    KNIGHT,
}
