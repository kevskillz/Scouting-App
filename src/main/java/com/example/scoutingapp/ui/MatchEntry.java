package com.example.scoutingapp.ui;

public class MatchEntry {
    public int highScoredAuton = 0;
    public int highMissedAuton = 0;
    public int lowScoredAuton = 0;
    public int lowMissedAuton = 0;
    public boolean taxi = false;
    public boolean interactsWithOtherTeamAuton = false;

    public int highScoredTeleop = 0;
    public int highMissedTeleop = 0;
    public int lowScoredTeleop = 0;
    public int lowMissedTeleop = 0;
    public double climbTime = 0;
    public int climbLevel = 0; // 0 (no climb) to 4 (traversal)

    private long climbStartMS = 0;

    public void startClimb() {
        climbStartMS = System.currentTimeMillis();
    }

    public void endClimb() {
        climbTime = (double)(System.currentTimeMillis() - climbStartMS) / 1000.0f;
    }

    public void clear() {
        highScoredAuton = 0;
        highMissedAuton = 0;
        lowScoredAuton = 0;
        lowMissedAuton = 0;
        taxi = false;
        interactsWithOtherTeamAuton = false;

        highScoredTeleop = 0;
        highMissedTeleop = 0;
        lowScoredTeleop = 0;
        lowMissedTeleop = 0;
        climbTime = 0;
        climbLevel = 0; // 0 (no climb) to 4 (traversal)
    }
}
