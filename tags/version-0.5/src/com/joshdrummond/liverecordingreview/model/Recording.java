/**
 * 
 */
package com.joshdrummond.liverecordingreview.model;

/**
 * @author josh
 * 
 */
public class Recording
{
    private int id;
    private Category category;
    private char typeCode;
    private String description;
    private String source;
    private String info;
    private float avgPerformanceRating;
    private float avgRecordingRating;
    private int totalReviews;

    /**
     * @return the avgPerformanceRating
     */
    public float getAvgPerformanceRating()
    {
        return this.avgPerformanceRating;
    }

    /**
     * @param avgPerformanceRating
     *            the avgPerformanceRating to set
     */
    public void setAvgPerformanceRating(float avgPerformanceRating)
    {
        this.avgPerformanceRating = avgPerformanceRating;
    }

    /**
     * @return the avgRecordingRating
     */
    public float getAvgRecordingRating()
    {
        return this.avgRecordingRating;
    }

    /**
     * @param avgRecordingRating
     *            the avgRecordingRating to set
     */
    public void setAvgRecordingRating(float avgRecordingRating)
    {
        this.avgRecordingRating = avgRecordingRating;
    }

    /**
     * @return the category
     */
    public Category getCategory()
    {
        return this.category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(Category category)
    {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
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
     * @return the info
     */
    public String getInfo()
    {
        return this.info;
    }

    /**
     * @param info
     *            the info to set
     */
    public void setInfo(String info)
    {
        this.info = info;
    }

    /**
     * @return the source
     */
    public String getSource()
    {
        return this.source;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource(String source)
    {
        this.source = source;
    }

    /**
     * @return the totalReviews
     */
    public int getTotalReviews()
    {
        return this.totalReviews;
    }

    /**
     * @param totalReviews
     *            the totalReviews to set
     */
    public void setTotalReviews(int totalReviews)
    {
        this.totalReviews = totalReviews;
    }

    /**
     * 
     * @return
     */
    public char getTypeCode()
    {
        return this.typeCode;
    }

    /**
     * 
     * @param typeCode
     */
    public void setTypeCode(char typeCode)
    {
        this.typeCode = typeCode;
    }

    /**
     * @return the type
     */
    public String getType()
    {
        if (typeCode=='A')
            return "Audio";
        else if (typeCode=='V')
            return "Video";
        else
            return "";
    }

}
