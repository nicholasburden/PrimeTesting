package primeTesting;

import java.math.BigInteger;
import java.util.Random;

public class PrimeTest {
    Random rand = new Random();
    BigInteger two = new BigInteger("2");

    public boolean testPrime(BigInteger p, int accuracy){
        if(p.mod(two).compareTo(BigInteger.ZERO) == 0 && p.compareTo(two) != 0){
            return false;
        }
        
        for(int i = 0; i < accuracy; i++){
            BigInteger a = randomBigInteger(p);
            if(modPow(a, p, p).compareTo(a) != 0){
                return false;
            }
        }
        return true;
    }

    public String nextPrimeFrom(long base, int exponent){
        BigInteger start = BigInteger.valueOf(base).pow(exponent);
        if(start.mod(two).compareTo(BigInteger.ZERO) == 0){
            start = start.add(BigInteger.ONE);
        }
        while(!testPrime(start, 3)){
            start.add(two);
        }
        return start.toString();
    }

    private BigInteger randomBigInteger(BigInteger n){
        int maxBitLength = n.bitLength();
        BigInteger randomInteger = new BigInteger(maxBitLength, rand);
        while(n.compareTo(randomInteger) <= 0 || randomInteger.compareTo(BigInteger.ZERO) == 0){
            randomInteger = new BigInteger(maxBitLength, rand);
        }
        return randomInteger;
    }

    private BigInteger modPow(BigInteger base, BigInteger exponent, final BigInteger m)
    {
        BigInteger result = BigInteger.ONE;
        while(exponent.compareTo(BigInteger.ZERO) > 0){
            if(exponent.testBit(0)){ //odd exponent
                result = (result.multiply(base)).mod(m);
            }
            exponent = exponent.shiftRight(1);
            base = (base.multiply(base)).mod(m);
        }
        return result.mod(m);
    }

    public static void main(String[] args){
        BigInteger a = new BigInteger("74898936789457689542978195748489367894555555555555555548359764589747482746748989367894576895429781957484893678945555555555555555483597645897474827467478293456879435678465894367895645893789125784931758137548457689431675897575756325443157894139857349815789431578943175893413215555555555555555555555666666666666666666666666666666666666666665749385784937658934276589431657894345974782934568794356784658943678956458937891257849317581375484576894316758975757563254431578941398573498157894315789431758934132155555555555555555555556666666666666666666666666666666666666666657493857849376589342765894316578943016");
        if(!a.testBit(0)){
            a=a.add(BigInteger.ONE);
        }
        PrimeTest tester = new PrimeTest();
        while(!tester.testPrime(a, 50)){
            a=a.add(tester.two);
        }
        System.out.print(a.toString());
    }
}
