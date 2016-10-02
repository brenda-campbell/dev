package com.mcreceiverdemo.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mcreceiverdemo.exceptions.CustomException;


import com.exacttarget.fuelsdk.ETSdkException;

//@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice extends ResponseEntityExceptionHandler{

	protected Logger logger;

	public GlobalExceptionHandlingControllerAdvice() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
	/* . . . . . . . . . . . . . EXCEPTION HANDLERS . . . . . . . . . . . . . . */
	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

	
	/**
	 * Demonstrates how to take total control - setup a model, add useful
	 * information and return the "support" view name. This method explicitly
	 * creates and returns
	 * 
	 * @param req
	 *            Current HTTP request.
	 * @param exception
	 *            The exception thrown - always {@link SupportInfoException}.
	 * @return The model and view used by the DispatcherServlet to generate
	 *         output.
	 * @throws Exception
	 */
	//@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "ET SDK threw exception")
	@ExceptionHandler({ ETSdkException.class, CustomException.class, UnsupportedOperationException.class, Exception.class})
	public ModelAndView handleError(HttpServletRequest req, Exception exception)
			throws Exception {
		
		// Rethrow annotated exceptions or they will be processed here instead.
		if (AnnotationUtils.findAnnotation(exception.getClass(),
				ResponseStatus.class) != null) {
			logger.error("throw-------------->: ####################### ");
			throw exception;
		}
			

		logger.error("Request: " + req.getRequestURI() + " raised " + exception);

		ModelAndView mav = new ModelAndView("support");
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.addObject("timestamp", new Date().toString());
		mav.addObject("status", 500);
		if(exception.getClass() == CustomException.class) {
			mav.addObject("error", exception.getMessage());
		}
		
		mav.setViewName("layouts/default");
		mav.addObject("view", "support");
		//mav.setViewName("support");
		return mav;
	}
}
