package com.pt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pt.vo.ResultInfo;

/**
 * 全局异常处理
 * @author gehb
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultInfo<String> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
    	ResultInfo<String> r = new ResultInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultInfo.ERROR);
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}
