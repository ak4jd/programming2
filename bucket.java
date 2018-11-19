import java.util.ArrayList;
import java.util.Collections;

public class bucket {
	 ArrayList<Integer> container;
	 bucket next;
	 int index = 0;
	 public bucket(int size){
		 container = new ArrayList<Integer>(Collections.nCopies(size,null));
	 }
}
