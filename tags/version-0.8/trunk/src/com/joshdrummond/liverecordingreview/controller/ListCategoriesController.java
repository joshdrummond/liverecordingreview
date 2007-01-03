/**
 * 
 */
package com.joshdrummond.liverecordingreview.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import com.joshdrummond.liverecordingreview.model.Artist;
import com.joshdrummond.liverecordingreview.service.RecordingService;

/**
 * @author josh
 *
 */
public class ListCategoriesController extends AbstractCommandController
{
    public ListCategoriesController()
    {
        setCommandClass(Artist.class);
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors)
        throws Exception
    {
        //GetListCommand listCommand = (GetListCommand)command;
        Artist artist = (Artist)command;
        ModelAndView mav = new ModelAndView("categoryList");
        mav.addObject("categories", recordingService.getCategories(artist));
        mav.addObject("artist", recordingService.getArtist(artist.getId()));
        return mav;
    }

    private RecordingService recordingService;
    public void setRecordingService(RecordingService recordingService)
    {
        this.recordingService = recordingService;
    }
}
