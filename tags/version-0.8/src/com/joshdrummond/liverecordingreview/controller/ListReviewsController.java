/**
 * 
 */
package com.joshdrummond.liverecordingreview.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import com.joshdrummond.liverecordingreview.model.Recording;
import com.joshdrummond.liverecordingreview.service.RecordingService;

/**
 * @author josh
 *
 */
public class ListReviewsController extends AbstractCommandController
{
    public ListReviewsController()
    {
        setCommandClass(Recording.class);
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors)
        throws Exception
    {
        Recording recording = (Recording)command;
        ModelAndView mav = new ModelAndView("reviewList");
        mav.addObject("reviews", recordingService.getReviews(recording));
        mav.addObject("recording", recordingService.getRecording(recording.getId()));
        return mav;
    }

    private RecordingService recordingService;
    public void setRecordingService(RecordingService recordingService)
    {
        this.recordingService = recordingService;
    }
}
