package springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 10326 on 2020/1/17.
 * Feign的全局异常处理器
 */
@RestController
public class ErrorHandlerController implements ErrorController {// 实现该接口

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {// 异常后走的请求路径
        return "/error";
    }

    @RequestMapping("/error")
    public @ResponseBody Map error(HttpServletRequest request){
        WebRequest webRequest = new ServletWebRequest(request);
        Map errorMap = errorAttributes.getErrorAttributes(webRequest, true);
        errorMap.remove("trace");
        return errorMap;
    }
}
