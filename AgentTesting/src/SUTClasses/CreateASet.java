package SUTClasses;

import java.util.Arrays;
import java.awt.Rectangle;

/**
 * A sample of a ploymorphic method.
 * @author scottm
 *
 */
public class CreateASet implements Runnable {
    
    public static void main(String[] args){

    }
    
    /**
     * An example of polymorphism in action. The method relies
     * on Java's inheritance requirement and polymorhphism to call
     * the correct equals method.
     * @param data != null, no elements of data are null
     * @return a Set (no duplicates) of the elements in data.
     */
    public static Object[] makeSet(Object[] data){
        assert data != null : "Failed precondition makeSet. parameter cannot be null";
        assert noNulls(data) : "Failed precondition makeSet. no elements of parameter can be null";
        Object[] result = new Object[data.length];
        int numUnique = 0;
        boolean found;
        int indexInResult;
        for(int i = 0; i < data.length; i++){
            // maybe should break this out into another method
            indexInResult = 0;
            found = false;
            while(!found && indexInResult < numUnique){
                found = data[i].equals(result[indexInResult]);
                indexInResult++;
            }
            if( ! found ){
                result[numUnique] = data[i];
                numUnique++;
            }
        }
        Object[] result2 = new Object[numUnique];
        System.arraycopy(result, 0, result2, 0, numUnique);
        return result2;
    }
    
    // pre: data != null
    // return true if all elements of data are non null,
    // false otherwise
    public static boolean noNulls(Object[] data){
        assert data != null : "Failed precondition makeSet. parameter cannot be null";
        boolean good = true;
        int i = 0;
        while( good && i < data.length ){
            good = data[i] != null;
            i++;
        }
        return good;        
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
    
}