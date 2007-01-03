/**
 * 
 */
package com.joshdrummond.liverecordingreview.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.joshdrummond.liverecordingreview.model.Review;

/**
 * @author josh
 *
 */
public class ReviewValidator implements Validator
{

    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz)
    {
        return clazz.equals(Review.class);
    }

    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object target, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reviewer", "required", "Reviewer is required.");
        Review review = (Review)target;
        if (review.getPerformanceRating() > 10 ||
                review.getPerformanceRating() < 1)
        {
            errors.rejectValue("performanceRating", "numberRange", "Rating must be between 1 and 10.");
        }
        if (review.getRecordingRating() > 10 ||
                review.getRecordingRating() < 1)
        {
            errors.rejectValue("recordingRating", "numberRange", "Rating must be between 1 and 10.");
        }       
    }

}
