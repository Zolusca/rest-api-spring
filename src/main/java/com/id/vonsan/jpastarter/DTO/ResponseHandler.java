package com.id.vonsan.jpastarter.DTO;
import java.util.HashMap;
import java.util.LinkedHashMap;
//haslam
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseHandler {
    /**
     * this method to creating custom message, 
     * where add message and http status on json 
     * ex :
     * {
     *  "message":"success",
     *  "status":"200",
     *  "data":{
     *      Object
     *  }
     * }
     * @param message
     * @param httpStatus
     * @param object
     * @return responseEntity 
     */
    public static ResponseEntity<Object> customResponse
                                        (
                                            String message,
                                            HttpStatus httpStatus,
                                            Object object
                                        )
    {
        // menggunakan linkedhashmap untuk memastikan result tetap sama urutannya message baru object
        Map<String,Object> dataResponse = new LinkedHashMap<String,Object>();
        dataResponse.put("message",message);
        dataResponse.put("status", httpStatus.value());
        dataResponse.put("data", object);

        return new ResponseEntity<Object>(dataResponse,httpStatus);
    }
}
