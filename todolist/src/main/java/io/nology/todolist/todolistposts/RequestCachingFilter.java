package io.nology.todolist.todolistposts;

import java.io.IOException;
//import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

//import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
public class RequestCachingFilter extends OncePerRequestFilter {

	private final static Logger LOGGER = LoggerFactory.getLogger(RequestCachingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		StringBuilder requestDetails = new StringBuilder();
		requestDetails.append("Request Method: ").append(request.getMethod()).append("\n");
		requestDetails.append("Request URL: ").append(request.getRequestURL()).append("\n");
		requestDetails.append("Request Remote Address: ").append(request.getRemoteAddr()).append("\n");

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			requestDetails.append("Header: ").append(headerName).append("=").append(headerValue).append("\n");
		}

		LOGGER.info("REQUEST DATA:\n{}", requestDetails.toString());

		filterChain.doFilter(request, response);
	}
}
