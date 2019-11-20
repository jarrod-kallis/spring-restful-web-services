package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

// This won't be called as long as ExceptionResponseHandler is annotated with @RestControllerAdvice
// Not sure why though
// This is still called for unhandled 404 errors
@Component
public class ResponseErrorAttributes extends DefaultErrorAttributes {
//	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		// Let Spring handle the error first, we will modify the attribute afterwards
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

		// format & update timestamp
		Object timestamp = errorAttributes.get("timestamp");
		if (timestamp == null) {
//			errorAttributes.put("timestamp", dateFormat.format(new Date()));
			errorAttributes.put("timestamp", new Date());
		}
//		else {
//			errorAttributes.put("timestamp", dateFormat.format((Date) timestamp));
//		}

		errorAttributes.remove("trace");

		errorAttributes.put("details", errorAttributes.get("path"));
//		errorAttributes.put("author", "Jarrod Kallis");

//		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
//				webRequest.getAttribute("javax.servlet.error.message", RequestAttributes.SCOPE_REQUEST).toString(),
//				webRequest.getDescription(false));
//
//		Map<String, Object> errorAttributes = exceptionResponse.createErrorAttributes();

		return errorAttributes;
	}
}
