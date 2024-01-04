package com.ei.math.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseSimple {
    private Integer status;
    
    
    public static ResponseSimple ok(){
        return new ResponseSimple(200);
    }
    
}
