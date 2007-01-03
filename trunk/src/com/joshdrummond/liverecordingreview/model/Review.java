/*
    Copyright 2006, 2007 Josh Drummond

    This file is part of LiveRecordingReview.

    LiveRecordingReview is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    LiveRecordingReview is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with LiveRecordingReview; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/

package com.joshdrummond.liverecordingreview.model;

import java.util.Date;

/**
 * POJO model for a review
 * 
 * @author Josh Drummond
 *
 */
public class Review
{
    private int id;
    private Recording recording;
    private String reviewer;
    private int performanceRating;
    private int recordingRating;
    private String notes;
    private Date dateCreated;

    /**
     * @return the dateCreated
     */
    public Date getDateCreated()
    {
        return this.dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the notes
     */
    public String getNotes()
    {
        return this.notes;
    }

    /**
     * @param notes
     *            the notes to set
     */
    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    /**
     * @return the performanceRating
     */
    public int getPerformanceRating()
    {
        return this.performanceRating;
    }

    /**
     * @param performanceRating
     *            the performanceRating to set
     */
    public void setPerformanceRating(int performanceRating)
    {
        this.performanceRating = performanceRating;
    }

    /**
     * @return the recording
     */
    public Recording getRecording()
    {
        return this.recording;
    }

    /**
     * @param recording
     *            the recording to set
     */
    public void setRecording(Recording recording)
    {
        this.recording = recording;
    }

    /**
     * @return the recordingRating
     */
    public int getRecordingRating()
    {
        return this.recordingRating;
    }

    /**
     * @param recordingRating
     *            the recordingRating to set
     */
    public void setRecordingRating(int recordingRating)
    {
        this.recordingRating = recordingRating;
    }

    /**
     * @return the reviewer
     */
    public String getReviewer()
    {
        return this.reviewer;
    }

    /**
     * @param reviewer
     *            the reviewer to set
     */
    public void setReviewer(String reviewer)
    {
        this.reviewer = reviewer;
    }

}
