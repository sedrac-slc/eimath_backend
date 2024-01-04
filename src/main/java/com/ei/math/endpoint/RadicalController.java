package com.ei.math.endpoint;

import com.ei.math.general.MathResponse;
import com.ei.math.general.MathResult;
import com.ei.math.service.RadicalService;
import com.ei.math.util.ExpressionParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/radical")
@CrossOrigin(origins = "*")
public class RadicalController {
    @Autowired
    RadicalService radicalService;
    
    @PostMapping
    public ResponseEntity<MathResponse> solve(@RequestBody ExpressionParams request){
       MathResult calculate = radicalService.calculate(request.getExpression());
       return ResponseEntity.ok(calculate.response());
    }
    
    
}
