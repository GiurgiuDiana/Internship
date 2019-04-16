package Task1.Evozon;

import org.junit.Test;

import junit.framework.TestCase;

public class ProgramTest extends TestCase {
    @Test
    public void testMultiplyingWithPositiveNumbers() {
          Program test= new Program();//The program to be tested
          int Z=25;
          int Y=3;
          int R= test.multiplying(Z,Y);
          assertEquals("25 *3 =75 ",75,R);
      }
    @Test
    public void testMultiplyingWithOneNegativeNumber() {
        Program test= new Program();
        int Z=25;
        int Y=-3;
        int R= test.multiplying(Z,Y);
        assertEquals("25 *(-3) =-75 ",-75,R);
    }
    @Test
    public void testMultiplyingWithZeroNumber() {
        Program test= new Program();
        int Z=0;
        int Y=-3;
        int R= test.multiplying(Z,Y);
        assertEquals("0 *(-3) =0",0,R);
    }
    @Test
    public void testMultiplyingWithNegativeNumbers() {
        Program test= new Program();
        int Z=-25;
        int Y=-3;
        int R= test.multiplying(Z,Y);
        assertEquals("-25 *-3 =75 ",75,R);
    }
    @Test
    public  void testDividingWithPositiveNumbers() {
    	  Program test= new Program();
          int Z=33;
          int Y=3;
          int R= test.dividing(Z,Y);
          assertEquals("33/3=11",11,R);
      }
    @Test
        public void testDividingToZeroNumbers() {
    	  Program test= new Program();
          int Z=0;
          int Y=-3;
          int R= test.dividing(Z,Y);
          assertEquals("0/3=0",0,R);
      }
    @Test
      public  void testDividingWithNegativeNumbers() {
    	  Program test= new Program();
          int Z=-33;
          int Y=-3;
          int R= test.dividing(Z,Y);
          assertEquals("(-33)/(-3)=11",11,R);
      }
    @Test
     public void testDividingWithOneNegativeNumber() {
    	  Program test= new Program();
          int Z=22;
          int Y=-2;
          int R= test.dividing(Z,Y);
          assertEquals("22/-2=-11",-11,R);
      }
}
