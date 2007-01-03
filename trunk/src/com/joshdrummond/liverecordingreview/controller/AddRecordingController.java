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
    along with Foobar; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/

package com.joshdrummond.liverecordingreview.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.joshdrummond.liverecordingreview.model.Category;
import com.joshdrummond.liverecordingreview.model.Recording;
import com.joshdrummond.liverecordingreview.service.RecordingService;


/**
 * Spring MVC Form Controller for adding a new recording to a given artist/category
 * 
 * @author Josh Drummond
 *
 */
public class AddRecordingController extends SimpleFormController
{
   
    protected ModelAndView onSubmit(Object command, BindException errors)
        throws Exception
    {
        Recording recording = (Recording)command;
        recordingService.addRecording(recording);
        return new ModelAndView(getSuccessView(), "id", recording.getCategory().getId());
    }
    
    
    private RecordingService recordingService;
    public void setRecordingService(RecordingService recordingService)
    {
        this.recordingService = recordingService;
    }
  

    protected Object formBackingObject(HttpServletRequest request)
        throws Exception
    {
        Category category = new Category();
        Recording recording = new Recording();
        category.setId(Integer.parseInt(request.getParameter("id")));
        recording.setCategory(category);
        recording.setDescription("yyyy/mm/dd - Venue; City, State, Country");
        recording.setSource("aud/fm/tv/sbd mic/recorder/generation here");
        recording.setInfo("Put all other info here");
        return recording;
    }

    
    protected Map referenceData(HttpServletRequest request)
        throws Exception
    {
        Map<Object, Object> reference = new HashMap<Object, Object>();

        // generate typeCode & description list
        Map<String, String> typeCodes = new HashMap<String, String>();
        typeCodes.put("A", "Audio");
        typeCodes.put("V", "Video");
        reference.put("typeCodes", typeCodes);
        
        // get category info to display
        reference.put("category", recordingService.getCategory(Integer.parseInt(request.getParameter("id"))));

        return reference;
    }
    
}
