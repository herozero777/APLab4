package lab;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Schedule {
	// variable containing our cvs policy file
	private List<PolicyEvent> eventList = new ArrayList<PolicyEvent>();
	// variable containing our priority entries for each unique activity
	private List<String[]> priorityEntries = new ArrayList<String[]>();
	
	private List<PolicyEvent> eventHolder = new ArrayList<PolicyEvent>(); 
	
	//Scanner UsrRsp = new Scanner(System.in);
	public void loadFile() {
		// For Reading File
		String filePath = "Academic _Schedule.csv";
		String line = "";
		String cvsSplitBy = ",";
	    BufferedReader br = null;
	    String[] dataStr = {""};
	    
	    DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
	    
	    try {
	    	br = new BufferedReader(new FileReader(filePath));
	    	line = br.readLine();
	    	while ((line = br.readLine()) != null) {
	    		dataStr = line.split(cvsSplitBy);	    			
	    		eventList.add( new PolicyEvent(df.parse(dataStr[0]), df.parse(dataStr[1]), dataStr[2]) );	    		
	    	}
	    	// testing
	    	//List<String> tskName = new ArrayList<String>();
	    	//boolean fnd = true;
	    	// if( ! ) also compares to also the other values which are not equal so it will obviously add it
	    	//for (int i = 0; i < eventList.size(); i++) {
	    		//fnd = false;
	    		//for (int j = 0; j < tskName.size(); j++) {
	    			//if ( eventList.get(i).getActivity().equals(tskName.get(j)) ) {
	    				//System.out.println("hello print this");
	    				
	    				//fnd = true;
	    			//}
	    		//}
	    		//if (fnd == false) {
	    			//eventList.get(i).print();
	    			//tskName.add( eventList.get(i).getActivity() );
	    		//}
	    	//}
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			System.out.println("Nope .... you got an error");
		} finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
	}
	
    
	public void loadPriorityQueue() {
		// For Reading File
		String filePath = "Priority_Queue.csv";
		String line = "";
		String cvsSplitBy = ",";
	    BufferedReader br = null;
	    String[] dataStr = {""};
	    
	    try { 
	    	br = new BufferedReader(new FileReader(filePath));
	    	while ((line = br.readLine()) != null) {	    		
	    		dataStr = line.split(cvsSplitBy);	    			
	    		//eventList.add( new PolicyEvent(df.parse(dataStr[0]), df.parse(dataStr[1]), dataStr[2]) );
	    		priorityEntries.add(dataStr);
	    	}
	    	
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			System.out.println("Nope .... you got an error");
		} finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
	}
	
	// Setting priority queue's entries into eventList's priority field
	public void setPriority() {
		// Hard coding the beginning date // for wait days
		Date beginDate = eventList.get(0).getdStart();
		
		for (PolicyEvent event : eventList) {
			for (String[] priority : priorityEntries) {
				// is event's activity == to priorityEnt's entry
				if ( event.getActivity().equals(priority[0]) ) {
					//System.out.println("prt " + event.getActivity() + "val " + priority[1]);
					event.setUser( priority[1] );
					event.setPriority(Integer.parseInt( priority[2] ));
					event.setTimeSlot(Integer.parseInt( priority[3] ));
				}
			}
			// setting Waitdays and EventDays
			long diff = Math.abs( event.getdEnd().getTime() - event.getdStart().getTime() );
			long diffDays = 1 + (diff / (24 * 60 * 60 * 1000));
			event.setEventDays( (int) diffDays);
			// wait days
			long wdif = ( event.getdStart().getTime() - beginDate.getTime() );
			long wdifDays = (wdif / (24 * 60 * 60 * 1000));
			event.setWaitDays( (int) wdifDays);
		}
	}
	
	public void print() {
		int count = 0;
		for (int i = 0; i < eventList.size(); i++) {
			
			//long diff = Math.abs( eventList.get(i).getdEnd().getTime() - eventList.get(i).getdStart().getTime() );
			//long diffDays = diff / (24 * 60 * 60 * 1000);
			//System.out.println("time diff: " + diffDays);
			System.out.print("activity: " + eventList.get(i).getActivity());
			System.out.print(" wait days: " + eventList.get(i).getWaitDays());
			System.out.println(" event days: " + eventList.get(i).getEventDays());
			count++;
		}
		System.out.println("Count: " + count);
	}

// Allotting bandwidth to the users
	public void sheduler() {
		int eventRow = 0;
		
		int waitDays = 0;
		while (waitDays != 740)
		//for (int i = 0; i < eventList.size(); i++) {
		for (PolicyEvent event : eventList) {
			if (event.getWaitDays() == waitDays) {
				
				eventHolder.add(new PolicyEvent(event.getActivity(),event.getUser(),
						event.getPriority(),event.getTimeSlot(),event.getWaitDays(),
						event.getEventDays()));
			}
		}
		
		// performing arithmatic on eventHolder
		int stud = 0;
		int fac = 0;
		int man = 0;
		int studPriority = 0;
		int facPriority = 0;
		int manPriority = 0;
		// timeslot
		
		// %
		double studPercent = 0;
		double facPercent = 0;
		for (PolicyEvent cur : eventHolder) {
			
			if (cur.getUser().equals("s")) {
				stud++;
				studPriority += cur.getPriority();
			} else if (cur.getUser().equals("f")) {
				fac++;
				facPriority += cur.getPriority();
			} else if (cur.getUser().equals("m")) {
				man++;
				manPriority += cur.getPriority();
			}
			
			// Timeslots will be catered later
			
			
			cur.setEventDays(cur.getEventDays() - 1);
		}
		
		// assigning percentages to students and facalty
		double sp = stud * (studPriority);
		double fp = fac * facPriority;
		double tot = 0;
		for (int z = 0; z < 4 ; z++) {
			// student time slot
			if (z == 1) {
				tot = sp*(0.6) + fp*(0.4);
				facPercent = ( sp*(0.6)/tot ) * 90;
				studPercent = ( fp*(0.4)/tot ) * 90;
				// pump this data into the logfile here
			} else if ((z==1) || (z==2)) {
				// faculty timeslot
				tot = sp*(0.4) + fp*(0.6);
				facPercent = ( sp*(0.4)/tot ) * 90;
				studPercent = ( fp*(0.6)/tot ) * 90;
				// pump this data into the logfile here
			} else {
				tot = sp*(0.6) + fp*(0.4);
				facPercent = ( sp*(0.6)/tot ) * 90;
				studPercent = ( fp*(0.4)/tot ) * 90;
				// pump this data into the logfile here
			}
		}
		
		waitDays++;
		// for printing // testing
		for (int i = 0; i < eventHolder.size(); i++) {			
			System.out.print("EH a: " + eventHolder.get(i).getActivity());
			System.out.print(" EH wDays: " + eventHolder.get(i).getWaitDays());
			System.out.println(" EH eDays: " + eventHolder.get(i).getEventDays());
		}
	}
}




