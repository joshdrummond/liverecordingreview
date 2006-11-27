/**
 * 
 */
package com.joshdrummond.liverecordingreview.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joshdrummond.liverecordingreview.service.RecordingService;

/**
 * @author josh
 *
 */
public class ListArtistsController implements Controller
{
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return new ModelAndView("artistList", "artists", recordingService.getArtists());
    }

    private RecordingService recordingService;
    public void setRecordingService(RecordingService recordingService)
    {
        this.recordingService = recordingService;
    }
}