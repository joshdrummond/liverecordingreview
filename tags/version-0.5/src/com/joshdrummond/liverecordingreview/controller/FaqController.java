/**
 * 
 */
package com.joshdrummond.liverecordingreview.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author josh
 *
 */
public class FaqController implements Controller
{
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return new ModelAndView("faq");
    }
}
