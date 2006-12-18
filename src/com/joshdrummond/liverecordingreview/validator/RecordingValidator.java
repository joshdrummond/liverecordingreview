/**
 * 
 */
package com.joshdrummond.liverecordingreview.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.joshdrummond.liverecordingreview.model.Recording;

/**
 * @author josh
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
