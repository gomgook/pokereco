package com.stewhouse.pokereco.model;

/**
 * Created by Gomguk on 16. 7. 28..
 */
public class LevelRangeList {

    private LevelRange[] levelRange;

    public Integer[] getLevelsWithStardust(int searchStardust) {
        for (LevelRange range : levelRange) {
            if (range.getStardust() == searchStardust) {
                return range.getLevels();
            }
        }

        return null;
    }
}
