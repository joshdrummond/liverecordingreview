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
import com.joshdrummond.liverecordingreview.service.RecordingService;

/**
 * @author josh
 *
 */
public class ListCategoriesController extends AbstractCommandController
{
    public ListCategoriesController()
    {
        setCommandClass(GetListCommand.class);
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors)
        throws Exception
    {
        GetListCommand listCommand = (GetListCommand)command;
        ModelAndView mav = new ModelAndView("categoryList");
        mav.addObject("categories", recordingService.getCategories(listCommand.getId()));
        mav.addObject("band", recordingService.getBand(listCommand.getId()));
        return mav;
    }

    private RecordingService recordingService;
    public void setRecordingService(RecordingService recordingService)
    {
        this.recordingService = recordingService;
    }
}
