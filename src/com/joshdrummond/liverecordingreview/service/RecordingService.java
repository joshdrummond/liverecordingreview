/**
 * 
 */
package com.joshdrummond.liverecordingreview.service;

import java.util.ArrayList;
import java.util.List;

import com.joshdrummond.liverecordingreview.BootlegBean;
import com.joshdrummond.liverecordingreview.model.Band;
import com.joshdrummond.liverecordingreview.model.Category;
import com.joshdrummond.liverecordingreview.model.Recording;
import com.joshdrummond.liverecordingreview.model.Review;

/**
 * @author josh
 *
 */
public class RecordingService
{
    /**
     * Get all Bands
     * @return
     */
    public List<Band> getBands()
    {
        List<Band> bands = new ArrayList<Band>();
        List<List<String>> results = BootlegBean.getBands();
        for (List<String> row : results)
        {
            Band band = new Band();
            band.setId(Integer.parseInt(row.get(0)));
            band.setDescription(row.get(1));
            bands.add(band);
        }
        return bands;
    }

    /**
     * Get specified Band detail
     * @param bandId
     * @return
     */
    public Band getBand(int bandId)
    {
        //get the band
        List<List<String>> results = BootlegBean.getBand(bandId);
        Band band = new Band();
        band.setId(bandId);
        band.setDescription(results.get(0).get(0));
        return band;
    }
    
    /**
     * Get all Categories of a specified Band
     * @param bandId
     * @return
     */
    public List<Category> getCategories(int bandId)
    {
        List<Category> categories = new ArrayList<Category>();

        //get the categories
        List<List<String>> results = BootlegBean.getCategories(bandId);
        for (List<String> row : results)
        {
            Category category = new Category();
            category.setId(Integer.parseInt(row.get(0)));
            category.setDescription(row.get(1));
            categories.add(category);
        }
        
        return categories;
    }
    
    /**
     * Get specified Category detail
     * @param categoryId
     * @return
     */
    public Category getCategory(int categoryId)
    {
        List<List<String>> results = BootlegBean.getCategory(categoryId);
        Category category = new Category();
        category.setId(categoryId);
        category.setDescription(results.get(0).get(0));
        Band band = new Band();
        band.setId(Integer.parseInt(results.get(0).get(1)));
        band.setDescription(results.get(0).get(2));
        category.setBand(band);
        return category;        
    }

    /**
     * Get all Recordings of a specified Category
     * @param categoryId
     * @return
     */
    public List<Recording> getRecordings(int categoryId)
    {
        List<Recording> recordings = new ArrayList<Recording>();

        //get the recordings
        List<List<String>> results = BootlegBean.getRecordings(categoryId);
        for (List<String> row : results)
        {
            Recording recording = new Recording();
            recording.setId(Integer.parseInt(row.get(0)));
            recording.setTypeCode((row.get(1)).charAt(0));
            recording.setDescription(row.get(2));
            recording.setSource(row.get(3));
            recording.setAvgPerformanceRating(Float.parseFloat(row.get(4)));
            recording.setAvgRecordingRating(Float.parseFloat(row.get(5)));
            recording.setTotalReviews(Integer.parseInt(row.get(6)));
            recordings.add(recording);
        }
        
        return recordings;
    }
    
    /**
     * Get specified Recording detail
     * @param recordingId
     * @return
     */
    public Recording getRecording(int recordingId)
    {
        List<List<String>> results = BootlegBean.getRecording(recordingId);
        Recording recording = new Recording();
        recording.setId(recordingId);
        recording.setDescription(results.get(0).get(6));
        recording.setTypeCode((results.get(0).get(5)).charAt(0));
        recording.setSource(results.get(0).get(7));
        recording.setInfo(results.get(0).get(8));
        recording.setAvgPerformanceRating(Float.parseFloat(results.get(0).get(9)));
        recording.setAvgRecordingRating(Float.parseFloat(results.get(0).get(10)));
        recording.setTotalReviews(Integer.parseInt(results.get(0).get(11)));
        Band band = new Band();
        band.setId(Integer.parseInt(results.get(0).get(3)));
        band.setDescription(results.get(0).get(4));
        Category category = new Category();
        category.setId(Integer.parseInt(results.get(0).get(1)));
        category.setDescription(results.get(0).get(2));
        category.setBand(band);
        recording.setCategory(category);
        return recording;
    }
    
    /**
     * Get all Reviews of a specified Recording
     * @param recordingId
     * @return
     */
    public List<Review> getReviews(int recordingId)
    {
        List<Review> reviews = new ArrayList<Review>();

        //get the recordings
        List<List<String>> results = BootlegBean.getReviews(recordingId);
        for (List<String> row : results)
        {
            Review review = new Review();
            review.setId(Integer.parseInt(row.get(0)));
            review.setReviewer(row.get(1));
            review.setPerformanceRating(Integer.parseInt(row.get(2)));
            review.setRecordingRating(Integer.parseInt(row.get(3)));
            review.setNotes(row.get(4));
            review.setDateCreated(row.get(5));
            reviews.add(review);
        }
        
        return reviews;
    }
    
    public boolean addReview(Review review)
    {
        return BootlegBean.addReview(review.getRecording().getId(), review.getReviewer(), review.getPerformanceRating(), review.getRecordingRating(), review.getNotes());
    }
}
