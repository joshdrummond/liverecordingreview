/**
 * 
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
 * @author josh
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
  

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        Category category = new Category();
        Recording recording = new Recording();
        category.setId(Integer.parseInt(request.getParameter("id")));
        recording.setCategory(category);
        recording.setDescription("yyyy/mm/dd - Venue; City, State, Country");
        recording.setSource("aud/fm/tv/sbd mic/recorder/generation here");
        recording.setInfo("Put all other info here");
        return recording;
    }

    
    protected Map referenceData(HttpServletRequest request) throws Exception {
        
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
