package io.villim.blog.rest.service;

import io.villim.blog.domain.exception.BlogValidationException;
import io.villim.blog.rest.schema.PostRequest;
import io.villim.blog.rest.schema.UserRequest;
import org.apache.commons.lang3.StringUtils;

public class RequestValidator {

    private RequestValidator() {

        throw new RuntimeException("Cant new RequestValidator");

    }

    public static boolean validateUserRequest(UserRequest request) {

        if (StringUtils.isBlank(request.getName()) || StringUtils.isBlank(request.getPassword())) {
            throw new BlogValidationException("Name and Password cant be empty.");
        }

        return true;
    }

    public static boolean validatePostRequest(PostRequest request) {

        if (StringUtils.isBlank(request.getTitle()) || StringUtils.isBlank(request.getBody()) || request.getUserId() == 0) {
            throw new BlogValidationException("UserId, Title and Body cant be empty.");
        }

        return true;
    }

}
