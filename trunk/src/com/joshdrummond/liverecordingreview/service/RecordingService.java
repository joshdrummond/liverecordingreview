/**
 * 
 */
package com.joshdrummond.liverecordingreview.service;


import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.joshdrummond.liverecordingreview.model.Artist;
import com.joshdrummond.liverecordingreview.model.Category;
import com.joshdrummond.liverecordingreview.model.Recording;
import com.joshdrummond.liverecordingreview.model.Review;

/**
 * @author josh
 *
 */
public class RecordingService
{
    private HibernateTemplate hibernateTemplate;
    private SessionFactory sessionFactory;
    
    /**
     * Get all Artists
     * @return
     */
    public List<Artist> getArtists()
    {
        return hibernateTemplate.find("from Artist order by description");
    }

    /**
     * Get specified Artist detail
     * @param artistId
     * @return
     */
    public Artist getArtist(int artistId)
    {
        return (Artist)hibernateTemplate.get(Artist.class, artistId);
    }
    
    /**
     * Get all Categories of a specified Artist
     * @param artist
     * @return
     */
    public List<Category> getCategories(Artist artist)
    {
        return hibernateTemplate.find("from Category where artist=? order by description", artist);
    }
    
    /**
     * Get specified Category detail
     * @param categoryId
     * @return
     */
    public Category getCategory(int categoryId)
    {
        return (Category)hibernateTemplate.find("from Category category left join fetch category.artist where category.id=?", categoryId).get(0);
    }

    /**
     * Get all Recordings of a specified Category
     * @param category
     * @return
     */
    public List<Recording> getRecordings(Category category)
    {
        return hibernateTemplate.find("from Recording where category=? order by description, typeCode, source", category);
    }
    
    /**
     * Get specified Recording detail
     * @param recordingId
     * @return
     */
    public Recording getRecording(int recordingId)
    {
        return (Recording)hibernateTemplate.find("from Recording recording left join fetch recording.category left join fetch recording.category.artist where recording.id=?", recordingId).get(0);
    }
    
    /**
     * Get all Reviews of a specified Recording
     * @param recording
     * @return
     */
    public List<Review> getReviews(Recording recording)
    {
        return hibernateTemplate.find("from Review where recording=? order by dateCreated", recording);
    }
    
    /**
     * Add the specified Recording
     * @param recording
     */
    public void addRecording(Recording recording)
    {
        recording.setDateCreated(new Date());
        hibernateTemplate.save(recording);
    }
    
    /**
     * Add the specified Review and recalculate it's Recording's various scores
     * @param review
     */
    public void addReview(Review review)
    {
        review.setDateCreated(new Date());
        hibernateTemplate.save(review);
        recalculateRecordingScore(review.getRecording());
    }
    
    /**
     * Recalculate total reviews, average performance and recording scores of specified Recording
     * @param recording
     */
    public void recalculateRecordingScore(Recording recording)
    {
        recording = (Recording)hibernateTemplate.get(Recording.class, recording.getId());
        List results = hibernateTemplate.find("select count(*), sum(performanceRating), sum(recordingRating) from Review where recording=?", recording);
        if (!results.isEmpty())
        {
            Object[] res = (Object[])results.get(0);
            float reviewCount = (Long)res[0];
            float reviewSumPerformanceRating = (Long)res[1];
            float reviewSumRecordingRating = (Long)res[2];
            float reviewAvgPerformanceRating = 0;
            float reviewAvgRecordingRating = 0;
            if (reviewCount > 0)
            {
                reviewAvgPerformanceRating = reviewSumPerformanceRating / reviewCount;
                reviewAvgRecordingRating = reviewSumRecordingRating / reviewCount;
            }
            recording.setAvgPerformanceRating(reviewAvgPerformanceRating);
            recording.setAvgRecordingRating(reviewAvgRecordingRating);
            recording.setTotalReviews((int)reviewCount);
            hibernateTemplate.saveOrUpdate(recording);
        }
    }

    /**
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate()
    {
        return this.hibernateTemplate;
    }

    /**
     * @param hibernateTemplate the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
    {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory()
    {
        return this.sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
}
