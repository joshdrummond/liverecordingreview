/**
 * 
 */
package com.joshdrummond.liverecordingreview.model;

/**
 * @author josh
 * 
 */
public class Category
{

    private int id;
    private Artist artist;
    private String description;

    /**
     * @return the artist
     */
    public Artist getArtist()
    {
        return this.artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(Artist artist)
    {
        this.artist = artist;
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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + this.id;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Category other = (Category) obj;
        if (this.id != other.id)
            return false;
        return true;
    }

}