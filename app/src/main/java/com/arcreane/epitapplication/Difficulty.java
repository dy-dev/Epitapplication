package com.arcreane.epitapplication;

public enum Difficulty {
    EASY(10),
    MEDIUM(50),
    HARD(100);

    private final int m_iValue;

    Difficulty(int p_ivalue) {
        m_iValue = p_ivalue;
    }


    public int getValue() {
        return m_iValue;
    }
}
