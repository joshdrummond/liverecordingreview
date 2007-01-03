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

package com.joshdrummond.liverecordingreview.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.joshdrummond.liverecordingreview.model.Recording;
import com.joshdrummond.liverecordingreview.model.Review;
import com.joshdrummond.liverecordingreview.service.RecordingService;


/**
 * Spring MVC Form Controller for adding a new review to a given recording
 * 
 * @author Josh Drummond
 *
 */
public class AddReviewController extends SimpleFormController
{
    public AddReviewController()
    {
    }
    
    
    protected ModelAndView onSubmit(Object command, BindException errors)
        throws Exception
    {
        Review review = (Review)command;
        recordingService.addReview(review);
        return new ModelAndView(getSuccessView(), "id", review.getRecording().getId());
    }
    
    
    private RecordingService recordingService;
    public void setRecordingService(RecordingService recordingService)
    {
        this.recordingService = recordingService;
    }
    
    
    protected Object formBackingObject(HttpServletRequest request)
        throws Exception
    {
        Review review = new Review();
        Recording recording = new Recording();
        recording.setId(Integer.parseInt(request.getParameter("id")));
        review.setRecording(recording);
        return review;
    }

    
    protected Map referenceData(HttpServletRequest request)
        throws Exception
    {
        Map<Object, Object> reference = new HashMap<Object, Object>();

        // generate list of ratings 1-10
        List<Integer> ratings = new ArrayList<Integer>();
        for (int i=10; i > 0; i--)
        {
            ratings.add(new Integer(i));
        }
        reference.put("ratings", ratings);
        
        // get recording info to display
        reference.put("recording", recordingService.getRecording(Integer.parseInt(request.getParameter("id"))));

        return reference;
    }
    
}
