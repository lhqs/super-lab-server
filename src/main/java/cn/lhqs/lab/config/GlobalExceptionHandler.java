package cn.lhqs.lab.config;

import cn.lhqs.lab.entity.ReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-05-30 20:45
 * description : GlobalExceptionHandler.class
 * version : 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ReturnResult defaultException(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        logger.error("异常捕获:"+exception);
        return ReturnResult.fail;
    }

    /**
     * 处理意想不到的运行时异常
     * @param request
     * @param ex
     */
    @ExceptionHandler({RuntimeException.class})
    public ReturnResult handleRuntimeException(HttpServletRequest request, RuntimeException ex) {
        ex.printStackTrace();
        logger.error("未知运行异常:" + ex.getMessage(), ex);
        return ReturnResult.fail;
    }

    @ExceptionHandler(MultipartException.class)
    public String handleError(MultipartException es) {
        es.printStackTrace();
        return "test";
    }
}
