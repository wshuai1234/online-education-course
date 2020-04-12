package net.xdclass.xdvideo.XdException;


import net.xdclass.xdvideo.domain.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * exception handler
 */
@ControllerAdvice
public class XdExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData Handler(Exception e){

        if(e instanceof XdException){
            XdException xdException =  (XdException)e;
            return JsonData.buildError(xdException.getMsg(),xdException.getCode());
        }else{
            return JsonData.buildError("gloabl exception, unknown error");
        }
    }


}
