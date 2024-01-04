
package com.ei.math.fraction;

import com.ei.math.general.Step;
import com.ei.math.fraction.registory.FractionMessage;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 *{@code FractionResult} is a class that represents the response object for fraction operations.
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FractionResult {
    /**
     * Used to represent a fraction operation.
     * <p>
     * Example: 4/3+1/5, 4/7-1/5, 7/3*1/5, 2/3:1/7
     * </p>
     */
    private String expression;
    /**
     * Used to represent a result of fraction operation.
     */    
    private Fraction fraction;
    /**
     * Represents whether the operation between fractions was successful or not.
     */    
    @Builder.Default
    private boolean status = false;
    /**
     * The steps of solving the operation between fractions, to arrive at the result.
     */    
    @Builder.Default    
    private List<Step> steps = Arrays.asList();
    
    
    public static FractionResult of(Fraction fraction) {
        FractionMessage message = new FractionMessage();
        return (new FractionResult()).toBuilder().steps(
          List.of(new Step(1,fraction.text(),fraction.html(),message.getString("step.start")))
        ).fraction(fraction).status(true).build();
    }
    
}
