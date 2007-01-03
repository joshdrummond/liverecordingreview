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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import com.joshdrummond.liverecordingreview.model.Artist;
import com.joshdrummond.liverecordingreview.service.RecordingService;


/**
 * Spring MVC Controller for displaying all categories for a given artist
 * 
 * @author Josh Drummond
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
