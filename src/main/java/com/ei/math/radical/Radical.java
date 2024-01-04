package com.ei.math.radical;

import com.ei.math.fraction.Fraction;
import com.ei.math.radical.text.RadicalHtml;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Radical extends RadicalRoot{
    @Getter
    @Setter
    @Builder.Default
    private Fraction coefficient = Fraction.of();

    public Radical(Fraction coefficient, Fraction base, Fraction expoent) {
        super(base, expoent);
        this.coefficient = coefficient;
    }
    
    public static Radical of(long base) {
        return Radical.builder().base(Fraction.of(base)).build();
    }
    
    public static Radical of(long coefficient, long base) {
        return Radical.builder()
                      .coefficient(Fraction.of(coefficient))
                      .base(Fraction.of(base))
            .build();
    }
    
    public static Radical of(long coefficient, long base, Fraction expoent) {
        return Radical.builder()
                      .coefficient(Fraction.of(coefficient))
                      .base(Fraction.of(base))
                      .expoent(expoent)
            .build();
    }    
    
    public static Radical of(long base, double expoent) {
        return Radical.builder()
                      .base(Fraction.of(base))
                      .expoent(Fraction.of(expoent))
            .build();
    }    
    
    public static Radical of(long coefficient, long base, double expoent) {
        return Radical.builder()
                      .coefficient(Fraction.of(coefficient))
                      .base(Fraction.of(base))
                      .expoent(Fraction.of(expoent))
            .build();
    }    
    
    public static Radical of(Fraction base) {
        return Radical.builder().base(base).build();
    }
    
    public static Radical of(Fraction coefficient, Fraction base) {
        return Radical.builder().coefficient(coefficient).base(base).build();
    }
    
    public static Radical of(Fraction coefficient, Fraction base, Fraction expoent) {
        return Radical.builder().coefficient(coefficient).base(base).expoent(expoent).build();
    }    
    
    public boolean equals(Radical radical){
        if(!expoent.equals(radical.getExpoent())) return false;
        return base.equals(radical.getBase()) && coefficient.equals(radical.getCoefficient());
    }
    
    public boolean equalsRadicalRoot(Radical radical){
        if(!expoent.equals(radical.getExpoent())) return false;
        return base.equals(radical.getBase());
    }    
    
    public RadicalRoot root() {
        return new RadicalRoot(base, expoent);
    }
    
    public String simplyText(){
        return String.format("%s^%s", base,expoent.text());
    }
    
    public String text(boolean signal){
        String sig = signal ? (coefficient.isPositive() ? "+" : "-") : "";
        return sig+String.format("%s(%s^%s)", coefficient.text(), base.text(),expoent.text());
    }
    
   @Override
   public String text(){ return text(false); }    
    
    public String html(boolean signal){
        return RadicalHtml.html(this, signal);
    }  
    
    @Override
    public String html(){ return html(false); }    

    @Override
    public String toString() {
        return "Radical{" + "coefficient=" + coefficient + ", base="+ base +", expoent="+ expoent +"}";
    }


 
    
    
}
