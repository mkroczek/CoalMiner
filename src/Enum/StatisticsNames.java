package Enum;

public enum StatisticsNames {
    RESULT,
    GOAL,
    LEVEL,
    TIME,
    DYNAMITES;

    @Override
    public String toString() {
        switch(this){
            case GOAL -> {
                return "Goal";
            }
            case RESULT -> {
                return "Result";
            }
            case TIME -> {
                return "Time";
            }
            case LEVEL -> {
                return "Level";
            }
            case DYNAMITES -> {
                return "Dynamites";
            }
        }
        return null;
    }
}
