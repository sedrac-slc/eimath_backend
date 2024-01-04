package com.ei.math.endpoint;

import com.ei.math.general.MathResponse;
import com.ei.math.general.MathResult;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.service.ArithmeticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arithmetic")
@CrossOrigin(origins = "*")
public class ArithmetcController {
    @Autowired
    ArithmeticService arithmeticService;
    
    @PostMapping
    public ResponseEntity<MathResponse> solve(@RequestBody ArithmeticParams request){
       System.out.println(request);
       MathResult calculate = arithmeticService.calculate(request);
      
       return ResponseEntity.ok(calculate.response());
    }
    
    
}
