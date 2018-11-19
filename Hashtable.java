import java.util.*;
public class Hashtable {
    int numkeys = 0;
    ArrayList<bucket> hash;
    ArrayList<Integer> search = new ArrayList<Integer>();
    int numbuckets = 0;
    int bucketsize = 0;
    
    public Hashtable(int numkeys, ArrayList<Integer> input, int numbuckets, int bucketsize){
    	this.numkeys = numkeys;
    	this.search = input;
    	this.numbuckets = numbuckets;
    	this.bucketsize = bucketsize;
    	this.hash = new ArrayList<bucket>(5);
    	for(int i=0; i<numbuckets; i++){
    		this.hash.add(null);
    	}
    }
    public int hashfunction(int value){
    	int key = value*17%((int)Math.pow(2, 32));
    	key = Math.abs(key%(numbuckets));
    	//System.out.println("Key for " + value + " = " + key);
    	return key;
    }
    
    public int insert(int value){
    	int key = this.hashfunction(value);
    	if(hash.get(key)==null){
    		bucket newbucket = new bucket(bucketsize);
    		hash.set(key, newbucket);
    		newbucket.container.set(newbucket.index, value);
    		newbucket.index ++;
    	}
    	else{
    		bucket selected = hash.get(key);
    		bucket pointer = selected;
    		bucket prev = selected;
    		while(pointer != null){
    			if(pointer.container.contains(value)){
    		         return value;
    			}
    			prev = pointer;
    			pointer = pointer.next;
    		}
    	    // value not found
    			if(prev.index < bucketsize){
    			   prev.container.set(prev.index,value);
    			   prev.index ++;
    		   }
    		   else{
    			   prev.next = new bucket(bucketsize);
    			   prev.next.container.set(prev.next.index, value);
    			   prev.next.index ++;
    		   }
    		
    	}
  return value;  }
    
    public boolean contains(int value){
    	int key = this.hashfunction(value);
    	bucket selected = hash.get(key);
		bucket pointer = selected;
		while(pointer != null){
			if(pointer.container.contains(value)){
		         return true;
			}
			pointer = pointer.next;
		}
    	return false;
    }
    
    public void printhash(){
    	for(int i = 0; i<hash.size(); i ++){
    		bucket selected = hash.get(i);
    		bucket pointer = selected;
    		System.out.print("[ind:" + i + "]");
    		while(pointer != null){
    		
    		System.out.print(Arrays.toString(pointer.container.toArray()) + "-");
    		pointer = pointer.next;
    		}
    	System.out.println("");}
    }
    
	public static void main(String[] args) {
		ArrayList<Integer> inputs = new ArrayList<Integer>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter search keys: ");
		while (scan.hasNextInt()) {
		    inputs.add(scan.nextInt());
		}
		System.out.println("Enter number of buckets: ");
		Scanner scanner = new Scanner(System.in);
		int numbuckets = scanner.nextInt();
		System.out.println("Enter bucket size: ");
		int bucketsize = scanner.nextInt();
		Hashtable test = new Hashtable(5,inputs,numbuckets,bucketsize);
		for(int i = 0; i< inputs.size(); i++){
			test.insert(inputs.get(i));
		}
        test.printhash();
        //System.out.println(test.contains(16));
	}

}
