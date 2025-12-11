package amalgama_excercise;

public class Civilization {
    private CivilizationType type;
    private int numberOfPikemen;
    private int numberOfArchers;
    private int numberOfKnights;

    Civilization(CivilizationType type) {
        switch (type) {
            case CHINESE:
                this.numberOfPikemen = 2;
                this.numberOfArchers = 25;
                this.numberOfKnights = 2;
                break;
            case ENGLISH:
                this.numberOfPikemen = 10;
                this.numberOfArchers = 10;
                this.numberOfKnights = 10;
                break;
            case BYZANTINE:
                this.numberOfPikemen = 5;
                this.numberOfArchers = 8;
                this.numberOfKnights = 15;
                break;
        }
    }

    public CivilizationType getCivilizationType() {
        return type;
    }

    public int getnumberOfPikemen() {
        return numberOfPikemen;
    }

    public int getnumberOfArchers() {
        return numberOfArchers;
    }

    public int getnumberOfKnights() {
        return numberOfKnights;
    }
}

enum CivilizationType {
    CHINESE,
    ENGLISH,
    BYZANTINE
}





 