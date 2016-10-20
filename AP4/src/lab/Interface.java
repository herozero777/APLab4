package lab;
import java.util.*;

public class Interface {
	
	public static void main(String[] args) {
		
		Schedule s = new Schedule();
		s.loadFile();
		s.loadPriorityQueue();
		s.setPriority();
		s.print();
		s.sheduler();
	}
	
}
