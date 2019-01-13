package com.sys.web.servlet;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

/**
 * Code copy from DefaultHandlerExceptionResolver
 *
 */
@SuppressWarnings("deprecation")
public class StatusResolver {

	public static int handException(Exception ex) {
		if (ex instanceof NoSuchRequestHandlingMethodException) {
			return handleNoSuchRequestHandlingMethod();
		} else if (ex instanceof HttpRequestMethodNotSupportedException) {
			return handleHttpRequestMethodNotSupported();
		} else if (ex instanceof HttpMediaTypeNotSupportedException) {
			return handleHttpMediaTypeNotSupported();
		} else if (ex instanceof HttpMediaTypeNotAcceptableException) {
			return handleHttpMediaTypeNotAcceptable();
		} else if (ex instanceof MissingServletRequestParameterException) {
			return handleMissingServletRequestParameter();
		} else if (ex instanceof ServletRequestBindingException) {
			return handleServletRequestBindingException();
		} else if (ex instanceof ConversionNotSupportedException) {
			return handleConversionNotSupported();
		} else if (ex instanceof TypeMismatchException) {
			return handleTypeMismatch();
		} else if (ex instanceof HttpMessageNotReadableException) {
			return handleHttpMessageNotReadable();
		} else if (ex instanceof HttpMessageNotWritableException) {
			return handleHttpMessageNotWritable();
		} else if (ex instanceof MethodArgumentNotValidException) {
			return handleMethodArgumentNotValidException();
		} else if (ex instanceof MissingServletRequestPartException) {
			return handleMissingServletRequestPartException();
		} else if (ex instanceof BindException) {
			return handleBindException();
		} else if (ex instanceof NoHandlerFoundException) {
			return handleNoHandlerFoundException();
		} else
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	}

	private static int handleNoSuchRequestHandlingMethod() {
		return HttpServletResponse.SC_NOT_FOUND;

	}

	/**
	 * Handle the case where no request handler method was found for the
	 * particular HTTP request method.
	 * <p>
	 * The default implementation logs a warning, sends an HTTP 405 error, sets
	 * the "Allow" header, and returns an empty {@code ModelAndView}.
	 * Alternatively, a fallback view could be chosen, or the
	 * HttpRequestMethodNotSupportedException could be rethrown as-is.
	 * 
	 * @param ex
	 *            the HttpRequestMethodNotSupportedException to be handled
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler, or {@code null} if none chosen at the
	 *            time of the exception (for example, if multipart resolution
	 *            failed)
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 */
	private static int handleHttpRequestMethodNotSupported() {
		return HttpServletResponse.SC_METHOD_NOT_ALLOWED;
	}

	/**
	 * Handle the case where no
	 * {@linkplain org.springframework.http.converter.HttpMessageConverter
	 * message converters} were found for the PUT or POSTed content.
	 * <p>
	 * The default implementation sends an HTTP 415 error, sets the "Accept"
	 * header, and returns an empty {@code ModelAndView}. Alternatively, a
	 * fallback view could be chosen, or the HttpMediaTypeNotSupportedException
	 * could be rethrown as-is.
	 * 
	 * @param ex
	 *            the HttpMediaTypeNotSupportedException to be handled
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 */
	private static int handleHttpMediaTypeNotSupported() {
		return HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
	}

	/**
	 * Handle the case where no
	 * {@linkplain org.springframework.http.converter.HttpMessageConverter
	 * message converters} were found that were acceptable;or the client
	 * (expressed via the {@code Accept} header.
	 * <p>
	 * The default implementation sends an HTTP 406 error and returns an empty
	 * {@code ModelAndView}. Alternatively, a fallback view could be chosen, or
	 * the HttpMediaTypeNotAcceptableException could be rethrown as-is.
	 * 
	 * @param ex
	 *            the HttpMediaTypeNotAcceptableException to be handled
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 */
	private static int handleHttpMediaTypeNotAcceptable() {

		return HttpServletResponse.SC_NOT_ACCEPTABLE;

	}

	/**
	 * Handle the case when a required parameter is missing.
	 * <p>
	 * The default implementation sends an HTTP 400 error, and returns an empty
	 * {@code ModelAndView}. Alternatively, a fallback view could be chosen, or
	 * the MissingServletRequestParameterException could be rethrown as-is.
	 * 
	 * @param ex
	 *            the MissingServletRequestParameterException to be handled
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 */
	private static int handleMissingServletRequestParameter() {
		return HttpServletResponse.SC_BAD_REQUEST;

	}

	/**
	 * Handle the case when an unrecoverable binding exception occurs - e.g.
	 * required header, required cookie.
	 * <p>
	 * The default implementation sends an HTTP 400 error, and returns an empty
	 * {@code ModelAndView}. Alternatively, a fallback view could be chosen, or
	 * the exception could be rethrown as-is.
	 * 
	 * @param ex
	 *            the exception to be handled
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 */
	private static int handleServletRequestBindingException() {
		return HttpServletResponse.SC_BAD_REQUEST;
	}

	/**
	 * Handle the case when a {@link org.springframework.web.bind.WebDataBinder}
	 * conversion cannot occur.
	 * <p>
	 * The default implementation sends an HTTP 500 error, and returns an empty
	 * {@code ModelAndView}. Alternatively, a fallback view could be chosen, or
	 * the TypeMismatchException could be rethrown as-is.
	 * 
	 * @param ex
	 *            the ConversionNotSupportedException to be handled
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 */
	private static int handleConversionNotSupported() {
		return sendServerError();
	}

	/**
	 * Invoked to send a server error. Sets the status to 500 and also sets the
	 * request attribute "javax.servlet.error.exception" to the Exception.
	 */
	private static int sendServerError() {
		return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	}

	/**
	 * Handle the case when a {@link org.springframework.web.bind.WebDataBinder}
	 * conversion error occurs.
	 * <p>
	 * The default implementation sends an HTTP 400 error, and returns an empty
	 * {@code ModelAndView}. Alternatively, a fallback view could be chosen, or
	 * the TypeMismatchException could be rethrown as-is.
	 * 
	 * @param ex
	 *            the TypeMismatchException to be handled
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 */
	private static int handleTypeMismatch() {
		return HttpServletResponse.SC_BAD_REQUEST;
	}

	/**
	 * Handle the case where a
	 * {@linkplain org.springframework.http.converter.HttpMessageConverter
	 * message converter} cannot read from a HTTP request.
	 * <p>
	 * The default implementation sends an HTTP 400 error, and returns an empty
	 * {@code ModelAndView}. Alternatively, a fallback view could be chosen, or
	 * the HttpMediaTypeNotSupportedException could be rethrown as-is.
	 * 
	 * @param ex
	 *            the HttpMessageNotReadableException to be handled
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 */
	private static int handleHttpMessageNotReadable() {
		return HttpServletResponse.SC_BAD_REQUEST;

	}

	/**
	 * Handle the case where a
	 * {@linkplain org.springframework.http.converter.HttpMessageConverter
	 * message converter} cannot write to a HTTP request.
	 * <p>
	 * The default implementation sends an HTTP 500 error, and returns an empty
	 * {@code ModelAndView}. Alternatively, a fallback view could be chosen, or
	 * the HttpMediaTypeNotSupportedException could be rethrown as-is.
	 * 
	 * @param ex
	 *            the HttpMessageNotWritableException to be handled
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 */
	private static int handleHttpMessageNotWritable() {
		return sendServerError();
	}

	/**
	 * Handle the case where an argument annotated with {@code @Valid} such as
	 * an {@link RequestBody} or {@link RequestPart} argument fails validation.
	 * An HTTP 400 error is sent back to the client.
	 * 
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 */
	private static int handleMethodArgumentNotValidException() {
		return HttpServletResponse.SC_BAD_REQUEST;

	}

	/**
	 * Handle the case where an {@linkplain RequestPart @RequestPart}, a
	 * {@link MultipartFile}, or a {@code javax.servlet.http.Part} argument is
	 * required but is missing. An HTTP 400 error is sent back to the client.
	 * 
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 */
	private static int handleMissingServletRequestPartException() {
		return HttpServletResponse.SC_BAD_REQUEST;

	}

	/**
	 * Handle the case where an {@linkplain ModelAttribute @ModelAttribute}
	 * method argument has binding or validation errors and is not followed by
	 * another method argument of type {@link BindingResult}. By default an HTTP
	 * 400 error is sent back to the client.
	 * 
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 */
	private static int handleBindException() {
		return HttpServletResponse.SC_BAD_REQUEST;

	}

	/**
	 * Handle the case where no handler was found during the dispatch.
	 * <p>
	 * The default sends an HTTP 404 error, and returns an empty
	 * {@code ModelAndView}. Alternatively, a fallback view could be chosen, or
	 * the NoHandlerFoundException could be rethrown as-is.
	 * 
	 * @param ex
	 *            the NoHandlerFoundException to be handled
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            the executed handler, or {@code null} if none chosen at the
	 *            time of the exception (for example, if multipart resolution
	 *            failed)
	 * @an empty ModelAndView indicating the exception was handled @ potentially
	 *     thrown from return )
	 * @since 4.0
	 */
	private static int handleNoHandlerFoundException() {
		return HttpServletResponse.SC_NOT_FOUND;
	}
}
