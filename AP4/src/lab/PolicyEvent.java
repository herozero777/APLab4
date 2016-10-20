package lab;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PolicyEvent {
	private Date dStart;
	private Date dEnd;
	private String activity;
	private String user;
	private int priority;
	private int timeSlot;
	private int waitDays;
	private int eventDays;
	PolicyEvent() {
		
	}
	
	PolicyEvent(Date dStart, Date dEnd, String activity) {
		this.dStart = dStart;
		this.dEnd = dEnd;
		this.activity = activity;
	}
	// copy constructor hack
	PolicyEvent(String a,String u,int p,int t,int w,int days) {
		activity = a;
		user = u;
		priority = p;
		timeSlot = t;
		waitDays = w;
		eventDays = days;
	}
	// Getters and Setters
	public Date getdStart() {
		return dStart;
	}
	public void setdStart(Date dStart) {
		this.dStart = dStart;
	}
	public Date getdEnd() {
		return dEnd;
	}
	public void setdEnd(Date dEnd) {
		this.dEnd = dEnd;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}
	public int getWaitDays() {
		return waitDays;
	}
	public void setWaitDays(int waitDays) {
		this.waitDays = waitDays;
	}
	public int getEventDays() {
		return eventDays;
	}
	public void setEventDays(int eventDays) {
		this.eventDays = eventDays;
	}
	//
	public void print() {
		//DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
		System.out.print("Start: " + dStart + " End: " + dEnd + " Activity: " + activity);
		System.out.println(" user: " + user + " ptr: " + priority + " TS: " + timeSlot);
	}

}
