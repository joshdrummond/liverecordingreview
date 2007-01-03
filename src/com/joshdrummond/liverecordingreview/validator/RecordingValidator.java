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
import com.joshdrummond.liverecordingreview.model.Recording;


/**
 * Spring Validator for Recording object
 * 
 * @author Josh Drummond
 *
 */
public class RecordingValidator implements Validator
{

    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz)
    {
        return clazz.equals(Recording.class);
    }

    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object target, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required", "Description is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "source", "required", "Source is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "info", "required", "Info is required.");
        Recording recording = (Recording)target;
        if ((recording.getTypeCode() != 'A') && (recording.getTypeCode() != 'V'))
        {
            errors.rejectValue("typeCode", "invalid", "Type is invalid.");
        }
    }

}
