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
import com.joshdrummond.liverecordingreview.model.Category;
import com.joshdrummond.liverecordingreview.service.RecordingService;

/**
 * @author josh
 *
 */
public class ListRecordingsController extends AbstractCommandController
{
    public ListRecordingsController()
    {
        setCommandClass(GetListCommand.class);
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors)
        throws Exception
    {
        GetListCommand listCommand = (GetListCommand)command;
        ModelAndView mav = new ModelAndView("recordingList");
        mav.addObject("recordings", recordingService.getRecordings(listCommand.getId()));
        Category category = recordingService.getCategory(listCommand.getId());
        mav.addObject("category", category);
        return mav;
    }

    private RecordingService recordingService;
    public void setRecordingService(RecordingService recordingService)
    {
        this.recordingService = recordingService;
    }
}
