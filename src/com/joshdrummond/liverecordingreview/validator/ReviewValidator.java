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

package com.joshdrummond.liverecordingreview.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.joshdrummond.liverecordingreview.model.Review;


/**
 * Spring Validator for Review object
 * 
 * @author Josh Drummond
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
