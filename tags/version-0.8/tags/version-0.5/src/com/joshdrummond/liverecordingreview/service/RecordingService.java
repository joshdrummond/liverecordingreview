/**
 * 
 */
package com.joshdrummond.liverecordingreview.service;

import java.util.ArrayList;
import java.util.List;

import com.joshdrummond.liverecordingreview.BootlegBean;
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
    /**
     * Get all Artists
     * @return
     */
    public List<Artist> getArtists()
    {
        List<Artist> artists = new ArrayList<Artist>();
        List<List<String>> results = BootlegBean.getArtists();
        for (List<String> row : results)
        {
            Artist artist = new Artist();
            artist.setId(Integer.parseInt(row.get(0)));
            artist.setDescription(row.get(1));
            artists.add(artist);
        }
        return artists;
    }

    /**
     * Get specified Artist detail
     * @param artistId
     * @return
     */
    public Artist getArtist(int artistId)
    {
        //get the artist
        List<List<String>> results = BootlegBean.getArtist(artistId);
        Artist artist = new Artist();
        artist.setId(artistId);
        artist.setDescription(results.get(0).get(0));
        return artist;
    }
    
    /**
     * Get all Categories of a specified Artist
     * @param artistId
     * @return
     */
    public List<Category> getCategories(int artistId)
    {
        List<Category> categories = new ArrayList<Category>();

        //get the categories
        List<List<String>> results = BootlegBean.getCategories(artistId);
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
        Artist artist = new Artist();
        artist.setId(Integer.parseInt(results.get(0).get(1)));
        artist.setDescription(results.get(0).get(2));
        category.setArtist(artist);
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
        Artist artist = new Artist();
        artist.setId(Integer.parseInt(results.get(0).get(3)));
        artist.setDescription(results.get(0).get(4));
        Category category = new Category();
        category.setId(Integer.parseInt(results.get(0).get(1)));
        category.setDescription(results.get(0).get(2));
        category.setArtist(artist);
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
    
    public boolean addRecording(Recording recording)
    {
        return BootlegBean.addRecording(recording.getCategory().getId(), recording.getTypeCode(), recording.getDescription(), recording.getSource(), recording.getInfo());
    }
}
