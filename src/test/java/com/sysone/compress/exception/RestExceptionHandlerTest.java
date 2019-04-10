package com.sysone.compress.exception;

import com.sysone.compress.dto.ErrorMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class RestExceptionHandlerTest {

    @Mock
    private MethodParameter parameter;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private WebRequest request;

    @Test
    public void handleMethodArgumentNotValidTest() {
        ObjectError objectError = new ObjectError("Error","Invalid input");
        List<ObjectError> errorList = new ArrayList<>();
        errorList.add(objectError);

        when(bindingResult.getAllErrors()).thenReturn(errorList);

        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        MethodArgumentNotValidException methodArgumentNotValidException =
                new MethodArgumentNotValidException(parameter, bindingResult);

        ResponseEntity<Object> responseEntity = restExceptionHandler.handleMethodArgumentNotValid(
                methodArgumentNotValidException, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertTrue(((ErrorMessage) responseEntity.getBody()).getError().contains("Invalid input"));
    }
}