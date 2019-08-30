package SUTClasses;

public class Prime implements Runnable {
    public static void main(String[] args) {
     
    }
    
    
    public void check (int num) {
    	   boolean flag = false;
           for(int i = 2; i <= num/2; ++i)
           {
               // condition for nonprime number
               if(num % i == 0)
               {
                   flag = true;
                   break;
               }
           }
           if (!flag)
               System.out.println(num + " is a prime number.");
           else
               System.out.println(num + " is not a prime number.");
    }


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
