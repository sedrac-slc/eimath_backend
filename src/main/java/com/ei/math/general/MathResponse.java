package com.ei.math.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MathResponse{
    @Builder.Default
    private List<StepMap> stepGroups = new ArrayList<>();
    @Builder.Default
    private List<Step> steps = List.of();
    
    private String expression;
    private String  result;
    @Builder.Default
    private String  method="default";
    @Builder.Default
    private String className = "undefined";
    @Builder.Default
    private String pack = "undefined";    
    @Builder.Default
    private String timeMilliseconds = "undefined";
    @Builder.Default
    private boolean status = false;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.expression);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MathResponse other = (MathResponse) obj;
        if (!Objects.equals(this.expression, other.expression)) {
            return false;
        }
        return true;
    }
    
}
