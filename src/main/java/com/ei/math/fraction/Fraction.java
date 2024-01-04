package com.ei.math.fraction;

import com.ei.math.fraction.exception.FractionDenominatorIsZeroException;
import com.ei.math.fraction.text.FractionHtml;
import com.ei.math.fraction.text.FractionText;
import com.ei.math.number.util.MDC;
import java.io.Serializable;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/**
 * The {@code Fraction} class represents the set of fractional numbers.
 * 
 * <p> {@code Fractional numbers} are represented by two whole numbers (fraction terms) 
 * separated by a horizontal dash (fraction dash). The top number (numerator) 
 * can be any integer and the bottom number (denominator) must be non-zero,
 * Examples of some fraction types: {@code 1/4, 3/4, 5/4}
 * </p>
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
@Getter
@Setter
@Builder
public final class Fraction extends Number implements Comparable< Fraction > , Serializable{
    protected Long numerator;
    protected Long denominator;

    public final static long MAX_VALUE_NUMERATOR = Long.MAX_VALUE;
    public final static long MIN_VALUE_NUMERATOR = Long.MIN_VALUE;
    
    public final static long MAX_VALUE_DENOMINATOR = Long.MAX_VALUE;
    public final static long MIN_VALUE_DENOMINATOR = Long.MIN_VALUE;

    protected Fraction(Long numerator) {
        this.numerator = numerator;
        this.denominator = 1L;
    }

    protected Fraction(Long numerator, Long denominator) throws FractionDenominatorIsZeroException{
        if(denominator == 0L) throw new FractionDenominatorIsZeroException();
        this.numerator = numerator;
        this.denominator = denominator;    
        changeSignal();
    }

    protected Fraction(Integer numerator, Integer denominator) throws FractionDenominatorIsZeroException{
        if(denominator == 0) throw new FractionDenominatorIsZeroException();
        this.numerator = numerator.longValue();
        this.denominator = denominator.longValue();  
        changeSignal();
    }
   
    private void changeSignal(){
        if(numerator > 0 && denominator < 0){
            numerator = numerator * (-1);
            denominator = Math.abs(denominator);
        }
        if(numerator < 0 && denominator < 0){
            numerator = Math.abs(numerator);
            denominator = Math.abs(denominator);
        }
    }
    /**
      Returns a fraction where numerator and denominator are equal to 1
     * @return new Fraction(1, 1);
     */
    public static Fraction of(){return new Fraction(1, 1);}
    /**
      Returns a fraction the denominator is equal to 1
     * @param numerator {@code Integer}.
     * @return new Fraction(numerator, 1);
     */    
    public static Fraction of(Integer numerator){return new Fraction(numerator, 1);}
    /**
      Returns a fraction the denominator is equal to 1
     * @param numerator.
     * @return new Fraction(numerator, 1L);
     */       
    public static Fraction of(Long numerator){return new Fraction(numerator, 1L);}
    /**
      Returns the fraction of a decimal number
     * @param number decimal number
     * @return FractionConverter.parse(number).simplify();
     */  
    public static Fraction of(Double number){
        return FractionConverter.parse(number).simplify();
    } 
    /**
      Returns the fraction with the numerator, and denominator values
     * @param numerator {@code Integer}
     * @param denominator {@code Integer}
     * @return new Fraction(numerator, denominator);
     */      
    public static Fraction of(Integer numerator,Integer denominator){
        if(numerator.equals(denominator)) return of();
        return new Fraction(numerator, denominator);
    }
    /**
      Returns the fraction with the numerator, and denominator values
     * @param numerator {@code Long}
     * @param denominator {@code Long}
     * @return new Fraction(numerator, denominator);
     */    
    public static Fraction of(Long numerator,Long denominator){return new Fraction(numerator, denominator);}
    /**
      Returns the fraction where the numerator and denominator are decimal numbers
     * @param numerator {@code double} to be converted to a Fraction 
     * @param denominator {@code double} to be converted to a Fraction
     * <pre>{@code
     *    if(numerator.equals(denominator)) return of();
     *    if(denominator.equals(0.0)) throw new FractionDenominatorIsZeroException();
     * }</pre> 
     * <em>the new fraction will be the division of the fractions</em>
     * @return FractionConverter.parse(numerator).div(FractionConverter.parse(denominator)).simplify();
     */       
    public static Fraction of(Double numerator,Double denominator){
        if(numerator.equals(denominator)) return of();
        if(denominator.equals(0.0)) throw new FractionDenominatorIsZeroException();
        return FractionConverter.parse(numerator).div(FractionConverter.parse(denominator)).simplify();
    }    
    /**
      Returns the fraction with the numerator, and denominator values
     * @param numerator {@code Integer} to be converted to a Long.
     * @param denominator {@code Long}
     * @return new Fraction(numerator.longValue(), denominator);
     */            
    public static Fraction of(Integer numerator,Long denominator){return new Fraction(numerator.longValue(), denominator);}
     /**
      Returns the fraction with the numerator, and denominator values
     * @param numerator {@code Integer}
     * @param denominator {@code Long} to be converted to a Long
     * @return new Fraction(numerator, denominator.longValue());
     */  
    public static Fraction of(Long numerator,Integer denominator){return new Fraction(numerator, denominator.longValue());}
     /**
      Returns the fraction with the numerator, and denominator values
     * @param numerator {@code Integer} to be converted to a Fraction
     * @param denominator {@code Double} to be converted to a Fraction
     * <pre>{@code
     *    if(denominator.equals(0.0)) throw new FractionDenominatorIsZeroException();
     * }</pre> 
     * <em>the new fraction will be the division of the fractions</em>
     * @return of(numerator).div(FractionConverter.parse(denominator)).simplify();
     */      
    public static Fraction of(Integer numerator,Double denominator){
        if(denominator.equals(0.0)) throw new FractionDenominatorIsZeroException();
        return of(numerator).div(FractionConverter.parse(denominator)).simplify();
    }
     /**
      Returns the fraction with the numerator, and denominator values
     * @param numerator {@code Double} to be converted to a Fraction
     * @param denominator {@code Integer} to be converted to a Fraction
     * <pre>{@code
     *    if(denominator.equals(0.0)) throw new FractionDenominatorIsZeroException();
     * }</pre> 
     * <em>the new fraction will be the division of the fractions</em>
     * @return FractionConverter.parse(numerator).div(of(denominator)).simplify();
     */     
    public static Fraction of(Double numerator,Integer denominator){
         if(denominator.equals(0)) throw new FractionDenominatorIsZeroException();
        return FractionConverter.parse(numerator).div(of(denominator)).simplify();
    }    
     /**
      Returns the fraction with the numerator, and denominator values
     * @param numerator {@code Long} to be converted to a Fraction
     * @param denominator {@code Double} to be converted to a Fraction
     * <pre>{@code
     *    if(denominator.equals(0.0)) throw new FractionDenominatorIsZeroException();
     * }</pre> 
     * <em>the new fraction will be the division of the fractions</em>
     * @return of(numerator).div(FractionConverter.parse(denominator)).simplify();
     */       
    public static Fraction of(Long numerator,Double denominator){
         if(denominator.equals(0.0)) throw new FractionDenominatorIsZeroException();
        return of(numerator).div(FractionConverter.parse(denominator)).simplify();
    }
     /**
      Returns the fraction with the numerator, and denominator values
     * @param numerator {@code Double} to be converted to a Fraction
     * @param denominator {@code Long} to be converted to a Fraction
     * <pre>{@code
     *    if(denominator.equals(0.0)) throw new FractionDenominatorIsZeroException();
     * }</pre> 
     * <em>the new fraction will be the division of the fractions</em>
     * @return FractionConverter.parse(numerator).div(of(denominator)).simplify();
     */       
    public static Fraction of(Double numerator,Long denominator){
        if(denominator.equals(0)) throw new FractionDenominatorIsZeroException();
        return FractionConverter.parse(numerator).div(of(denominator)).simplify();
    }         
    /**
     * compare if the denominator is equal to 1
     * <pre>{@code
     *     denominator == 1;
     * }</pre>
     * @return denominator == 1
     */
    public boolean isInteger(){
        return denominator == 1;
    }
    /**
     *<p>For a fraction to be positive, the denominator and numerator must have the same sign</p>
     *<strong>Examples</strong> {@code true: 1/4, -2/-7 | false: -2/3, 1/-8} 
     * @return (numerator &gt; 0 &amp;&amp; denominator &gt; 0) || (numerator &lt; 0 &amp;&amp; denominator &lt; 0);
     */    
    public boolean isPositive() {
       return (numerator > 0 && denominator > 0) || (numerator < 0 && denominator < 0);
    }
    /**
     *<p>For a fraction to be negative, the denominator and numerator must have a different sign</p>
     *<strong>Examples</strong> {@code false: 1/4, -2/-7 | true: -2/3, 1/-8} 
     * @return !isPositive();
     */  
    public boolean isNegative() {
        return !isPositive();
    }
    /**
     *<p>Proper Fraction: the numerator is less than the denominator</p>
     *<strong>Examples</strong> {@code true: 1/4, 2/ | false: 4/3, 9/8} 
     * @return numerator &lt; denominator;
     */      
    public boolean isProper(){
        return numerator < denominator;
    }
    /**
     *<p>Improper Fraction: the numerator is greater than the denominator</p>
     *<strong>Examples</strong> {@code false: 1/4, 2/5 | true: 4/3, 9/8} 
     * @return numerator &gt; denominator;
     */      
    public boolean isImproper(){
        return numerator > denominator;
    }    
   /**
     *<p>Apparent Fraction: the numerator is divisible denominator</p>
     *<strong>Examples</strong> {@code true: 8/4, 6/3 | false: 4/3, 9/8} 
     * @return numerator % denominator == 0;
     */    
    public boolean isApparent(){
        return numerator % denominator == 0;
    }
    /**
     *<p>Equivalent Fractions: are those that are apparently different, but have the same result.</p>
     *<strong>Examples</strong> {@code true: 1/2 and 2/4 | false: 1/3 and 9/8} 
     * <pre>
     *      a/b = c/d =&gt; a*d = b*c 
     *      1/2 = 2/4 =&gt; 1*4 = 2*2 =&gt; 4 = 4 (true) is equivalent
     *      1/3 = 9/8 =&gt; 1*8 = 3*9 =&gt; 8 = 27 (false) is not equivalent
     * </pre>
     * @param fraction
     * @return compareTo(fraction) == 0;
     */    
    public boolean  isEquivalent(Fraction fraction){
      return compareTo(fraction) == 0;
    }
   /**
     *<p>Irreducible Fraction: cannot be simplified</p>
     *<strong>Examples</strong> {@code true: 3/4, 2/3 | false: 4/8, 9/18} 
     * @return numerator % denominator == 0;
     */       
    public boolean isIrreducible(){
        long num = Math.abs(this.numerator);
        long den = Math.abs(this.denominator);
        
        if(den == 1 || num ==1) return true;
        if(num % den == 0 || den % num == 0) return false;
        return MDC.solve(num, den) == 1;
    }
   /**
     *<p>Returns Positive fraction, numerator and denominator have the same sign</p>
     * @return new Fraction(Math.abs(numerator), Math.abs(denominator));
     */         
    public Fraction positive(){
        return new Fraction(Math.abs(numerator), Math.abs(denominator));
    }
   /**
     *<p>Returns Positive fraction, numerator and denominator have different sign</p>
     * @return new Fraction(numerator*(-1),denominator);
     */      
    public Fraction negative(){
        return new Fraction(numerator*(-1),denominator);
    }
   /**
     Returns the sum of two fractions, using the cross-system method
     * <p>{@code cross-system method}</p>
     * <pre>
     *      <strong>formula:</strong> a/b+c/d = [(a*d)+(c*b)]/(b.d)
     *      1/2+3/5 = [(1*5)+(3*2)]/(2*5) = [5+6]/10 = 11/10
     * </pre>
     * <pre>{
        if(denominator.equals(fraction.getDenominator()))
            return new Fraction(numerator + fraction.getNumerator(), denominator).simplify();
        long num = numerator * fraction.getDenominator() + denominator * fraction.getNumerator();
        long den = denominator * fraction.getDenominator();
     *  }</pre>
     * @param fraction
     * @return  new Fraction(num, den).simplify();
     */      
    public Fraction sum(Fraction fraction){
        if(denominator.equals(fraction.getDenominator()))
            return new Fraction(numerator + fraction.getNumerator(), denominator).simplify();
        long num = numerator * fraction.getDenominator() + denominator * fraction.getNumerator();
        long den = denominator * fraction.getDenominator();
        return new Fraction(num, den).simplify();
    }
  /**
     Returns the sub of two fractions, using the cross-system method
     * <p>{@code cross-system method}</p>
     * <pre>
     *     <strong>formula:</strong> a/b-c/d = [(a*d)-(c*b)]/(b.d)
     *      1/2-3/5 = [(1*5)-(3*2)]/(2*5) = [5-6]/10 = -1/10
     * </pre>
      * <pre>{
        if(denominator.equals(fraction.getDenominator()))
            return new Fraction(numerator - fraction.getNumerator(), denominator).simplify();
        long num = numerator * fraction.getDenominator() - denominator * fraction.getNumerator();
        long den = denominator * fraction.getDenominator();
     *  }</pre>
     * @param fraction
     * @return new Fraction(num, den).simplify();
     */       
    public Fraction sub(Fraction fraction){
        if(denominator.equals(fraction.getDenominator()))
            return new Fraction(numerator - fraction.getNumerator(), denominator).simplify();        
        long num = numerator * fraction.getDenominator() - denominator * fraction.getNumerator();
        long den = denominator * fraction.getDenominator();
        return new Fraction(num, den).simplify();
    }    
 /**
     <p>Returns the div of two fractions</p>
     * <pre>
     *     <strong>formula:</strong> a/b:c/d = (a*d)/(c*d) | 1/3:4/5 = 1*5/4*3 = 5/12
     * </pre>
     * @param fraction
     * @return new Fraction(numerator * fraction.getNumerator(),denominator * fraction.getDenominator()).simplify();;
     */      
    public Fraction div(Fraction fraction) {
        return new Fraction(numerator * fraction.getDenominator(),denominator * fraction.getNumerator()).simplify();
    }
 /**
     <p>Returns the div of two fractions</p>
     * <pre>
     *     <strong>formula:</strong> a/b*c/d = (a*c)/(b*d) | 1/3*4/5 = 1*4/3*5 = 4/15
     * </pre>
     * @param fraction
     * @return new Fraction(numerator * fraction.getNumerator(),denominator * fraction.getDenominator()).simplify();;
     */  
    public Fraction mult(Fraction fraction) {
        return new Fraction(numerator * fraction.getNumerator(),denominator * fraction.getDenominator()).simplify();
    }    
 /**
     <p>Returns the irreducible fraction</p>
     * <strong>formula:</strong> calculate the greatest common divisor (GCD or MDC) between the numerator and denominator, then divide the numerator and denominator with (GCD or MDC)
     * <pre>{
     *  long mdc = MDC.solve(numerator, denominator);
     * }</pre> 
     * @return new Fraction(numerator / mdc, denominator / mdc);
     */     
    public Fraction simplify(){
        long mdc = MDC.solve(numerator, denominator);
        return new Fraction(numerator / mdc, denominator / mdc);
    }
 /**
     <p>Returns the  greatest common divisor (GCD or MDC) between the numerator and denominator,</p>
     * @return MDC.solve(Math.abs(numerator),Math.abs(denominator))
     */  
     public long mdc(){
         
         return MDC.solve(Math.abs(numerator),Math.abs(denominator));
     }
 /**
     Returns the power of the fraction
     * @param exponent
     * <pre>{
     * long mdc = MDC.solve(numerator, denominator);
     * }</pre> 
     * @return new Fraction(numerator / mdc, denominator / mdc);
     */      
    public Fraction pow(int exponent){
        return pow(Integer.valueOf(exponent).longValue());
    }
 /**
     Returns the power of the fraction
     * @param exponent
     *
     * <pre>{
     *  if (exponent &gt; 0) {
     *       long num = (long) Math.pow(numerator, exponent);
     *       long den = (long) Math.pow(denominator, exponent);
     *       return new Fraction(num, den).simplify();
     *   } else if (exponent &lt; 0) {
     *       exponent = Math.abs(exponent);
     *       long num = (long) Math.pow(numerator, exponent);
     *       long den = (long) Math.pow(denominator, exponent);
     *       return new Fraction(den, num).simplify();
     *   }
     * }</pre> 
     * @return new Fraction(1,1);
     */     
    public Fraction pow(long exponent) {
        if (exponent > 0) {
            long num = (long) Math.pow(numerator, exponent);
            long den = (long) Math.pow(denominator, exponent);
            return new Fraction(num, den).simplify();
        } else if (exponent < 0) {
            exponent = Math.abs(exponent);
            long num = (long) Math.pow(numerator, exponent);
            long den = (long) Math.pow(denominator, exponent);
            return new Fraction(den, num).simplify();
        }
        return new Fraction(1, 1);
    }   
 /**
     Returns the fraction in this format: 1/2, -1/4
     * @return FractionText.template(this);
     */     
    public String text(){
        return FractionText.template(this);
    }
 /**
     Returns the fraction in this format: +1/2, -1/4
     * @param signal {@code boolean} to not hide the plus sign (+)
     * @return FractionText.template(this, signal);
     */     
    public String text(boolean signal){
        return FractionText.template(this,signal);
    }
 /**
     Returns the fraction in this format: +1/2, -1/4
     * @param signal {@code boolean} verify sign
     * @param sig {@code boolean} to not hide the plus sign
     * @return FractionText.template(this, signal,sig);
     */     
    public String text(boolean signal,String sig){
        return FractionText.template(this,signal,sig);
    }    
  /**
     Returns the fraction in this format: 1/2(5), -1/4(7)
     * @param mmc {@code long} least common multiple
     * @return FractionText.template(this, mmc);
     */    
    public String text(long mmc){
        return FractionText.template(this,mmc);
    }
  /**
     Returns the fraction in this format: +1/2(5), -1/4(7)
    * @param signal {@code boolean} to not hide the plus sign (+)
     * @param mmc {@code long} least common multiple
     * @return FractionText.template(this, mmc, signal);
     */       
    public String text(long mmc, boolean signal){
        return FractionText.template(this,mmc, signal);
    }       
 /**
     Returns the fraction in this format with html code: 1/2, -1/4
     * @return FractionHtml.template(this);
     */     
    public String html(){
        return FractionHtml.template(this);
    }
 /**
     Returns the fraction in this format with html code: +1/2, -1/4
     * @param signal {@code boolean} to not hide the plus sign (+)
     * @return FractionHtml.template(this, signal);
     */       
    public String html(boolean signal){
        return FractionHtml.template(this,signal);
    }
 /**
     Returns the fraction in this format with html code: +1/2, -1/4
     * @param signal {@code boolean} verify sign (+)
     * @param sig  {@code boolean} to not hide the  sign 
     * @return FractionHtml.template(this, signal,sig);
     */       
    public String html(boolean signal,String sig){
        return FractionHtml.template(this,signal,sig);
    }    
  /**
     Returns the fraction in this format with html code: 1/2(5), -1/4(7)
     * @param mmc {@code long} least common multiple
     * @return FractionHtml.template(this, mmc);
     */     
    public String html(long mmc){
        return FractionHtml.template(this,mmc);
    }
  /**
    Returns the fraction in this format with html code: +1/2(5), -1/4(7)
    * @param signal {@code boolean} to not hide the plus sign (+)
     * @param mmc {@code long} least common multiple
     * @return FractionHtml.template(this, mmc, signal);
     */      
    public String html(long mmc, boolean signal){
        return FractionHtml.template(this,mmc, signal);
    }    
  /**
    Returns an inverse of the fraction {@code inverse(1/6) = 6/1}
    *<pre>{
    * if(Objects.equals(this.numerator, 0.0)) throw new FractionDenominatorIsZeroException();
    * }</pre>
    *@return new Fraction(denominator,numerator);
    */      
    public Fraction reverse() throws FractionDenominatorIsZeroException{
      if(Objects.equals(this.numerator, 0.0)) throw new FractionDenominatorIsZeroException();
      return new Fraction(denominator,numerator);
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.numerator);
        hash = 97 * hash + Objects.hashCode(this.denominator);
        return hash;
    }
  /**
    Checks if fractions are equals.
    * @param obj
    *<pre>{
    *   if(!(obj instanceof Fraction)) return false;
    *    Fraction fraction = (Fraction) obj;
    * }</pre>
    *@return numerator.equals(fraction.getNumerator()) &amp;&amp; denominator.equals(fraction.getDenominator());
    */  
    @Override
    public boolean equals(Object obj) {
      if(!(obj instanceof Fraction)) return false;
      Fraction fraction = (Fraction) obj;
      return numerator.equals(fraction.getNumerator()) && denominator.equals(fraction.getDenominator());
    }
  /**
    Checks if fractions are equivalent.
    * @param fraction 
    *<pre>{
    *   long num = numerator * fraction.getDenominator();
        long den = denominator * fraction.getNumerator();
    * }</pre>
    *@return Long.valueOf(num).compareTo(den);
    */      
    @Override
    public int compareTo(Fraction fraction) {
        long num = numerator * fraction.getDenominator();
        long den = denominator * fraction.getNumerator();
        return Long.valueOf(num).compareTo(den);
    }
 /**
     Returns the fraction in this format: 1/2, -1/4
     * @return text();
     */ 
    @Override
    public String toString() {
        return text();
    }
  /**
     Returns the result of the operation
     * @return numerator / denominator;
     */    
    @Override
    public long longValue() {return numerator / denominator;}
  /**
     Returns the result of the operation
     * @return numerator.intValue() / denominator.intValue();
     */        
    @Override
    public int intValue() {return numerator.intValue() / denominator.intValue();}
  /**
     Returns the result of the operation
     * @return numerator.floatValue() / denominator.floatValue();
     */        
    @Override
    public float floatValue() { return numerator.floatValue() / denominator.floatValue(); }
  /**
     Returns the result of the operation
     * @return numerator.doubleValue() / denominator.doubleValue();
     */        
    @Override
    public double doubleValue() { return numerator.doubleValue() / denominator.doubleValue();}   
    
}
