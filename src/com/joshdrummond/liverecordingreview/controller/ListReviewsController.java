/**
 * 
 */
package com.joshdrummond.liverecordingreview.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import com.joshdrummond.liverecordingreview.command.GetListCommand;
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
        setCommandClass(GetListCommand.class);
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors)
        throws Exception
    {
        GetListCommand listCommand = (GetListCommand)command;
        ModelAndView mav = new ModelAndView("reviewList");
        mav.addObject("reviews", recordingService.getReviews(listCommand.getId()));
        Recording recording = recordingService.getRecording(listCommand.getId());
        mav.addObject("recording", recording);
        return mav;
    }

    private RecordingService recordingService;
    public void setRecordingService(RecordingService recordingService)
    {
        this.recordingService = recordingService;
    }
}
