package com.example.gestionhoteliere.models;

import java.util.Date;
import java.util.List;

public class Range{
    public Date startDate;
    public Date endDate;
    public Range(Date startDate,Date endDate){
        this.startDate=startDate;
        this.endDate=endDate;
    }
    // Check if the current Range overlaps with any Range in the list
    public boolean overlapsWith(List<Range> rangeList) {
        for (Range range : rangeList) {
            if (startDate.before(range.endDate) && endDate.after(range.startDate)) {
                // The current Range overlaps with the Range in the list
                return true;
            }
        }
        // No overlap found
        return false;
    }
}