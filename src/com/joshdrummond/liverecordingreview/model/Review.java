/**
 * 
 */
package com.joshdrummond.liverecordingreview.model;

/**
 * @author josh
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
    private String dateCreated;

    /**
     * @return the dateCreated
     */
    public String getDateCreated()
    {
        return this.dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(String dateCreated)
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
