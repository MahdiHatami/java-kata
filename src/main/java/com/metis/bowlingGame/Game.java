package com.metis.bowlingGame;

public class Game
{
    private int roll = 0;
    // 21 is the most row that we can have in the game
    private int[] rolls = new int[21];

    public void roll(int... rolls)
    {
        for (int pinsDown : rolls) {
            roll(pinsDown);
        }
    }

    private void roll(int pinsDown)
    {
        rolls[roll++] = pinsDown;
    }

    public int score()
    {
        int score = 0;
        int cursor = 0;
        // there is always 10 frames
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(cursor)) {
                score += 10 + (rolls[cursor + 1] + rolls[cursor + 2]);
                cursor ++;
            } else if (isSpare(cursor)) {
                score += 10 + rolls[cursor + 2];
                cursor += 2;
            } else {
                score += rolls[cursor] + rolls[cursor + 1];
                cursor += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int cursor)
    {
        return rolls[cursor] == 10;
    }

    private boolean isSpare(int cursor)
    {
        return rolls[cursor] + rolls[cursor + 1] == 10;
    }
}