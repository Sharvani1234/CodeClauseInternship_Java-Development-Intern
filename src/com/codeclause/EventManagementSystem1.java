package com.codeclause;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Event{
	private String name;
	private String date;
	private String location;
	private List<String>attendees;
	
	public Event(String name,String date,String location) {
		this.name=name;
		this.date=date;
		this.location=location;
		this.attendees=new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getLocation() {
		return location;
	}
	
	public List<String>getAttendees(){
		return attendees;
	}
	
	public void addAttendee(String attendee) {
		attendees.add(attendee);
	}
}

class EventManagementSystem extends JFrame implements ActionListener{
	private JTextField eventNameField,eventDateField,eventLocationField,attendeeNameField;
	private JTextArea eventListArea,attendeeListArea;
	private JButton createEventButton,addAttendeeButton;
	
	private List<Event>events;
	
	public EventManagementSystem() {
		events=new ArrayList<>();
		
		JLabel eventNameLabel=new JLabel("Event Name:");
		eventNameField=new JTextField(20);
		
		JLabel eventDateLabel=new JLabel("Event Date:");
		eventDateField=new JTextField(20);
		
		JLabel eventLocationLabel=new JLabel("Event Location:");
		eventLocationField=new JTextField(20);
		
		createEventButton=new JButton("Create Event");
		createEventButton.addActionListener(this);
		
		JLabel attendeeNameLabel=new JLabel("Attendee Name:");
		attendeeNameField=new JTextField(20);
		
		addAttendeeButton=new JButton("Add Attendee");
		addAttendeeButton.addActionListener(this);
		
		eventListArea=new JTextArea(10,40);
		eventListArea.setEditable(false);
		
		attendeeListArea=new JTextArea(10,40);
		attendeeListArea.setEditable(false);
		
		JPanel eventPanel=new JPanel();
		eventPanel.setLayout(new GridLayout(5,2));
		eventPanel.add(eventNameLabel);
		eventPanel.add(eventNameField);
		eventPanel.add(eventDateLabel);
		eventPanel.add(eventDateField);
		eventPanel.add(eventLocationLabel);
		eventPanel.add(eventLocationField);
		eventPanel.add(createEventButton);
		
		JPanel attendeePanel=new JPanel();
		attendeePanel.setLayout(new GridLayout(2,2));
		attendeePanel.add(attendeeNameLabel);
		attendeePanel.add(attendeeNameField);
		attendeePanel.add(addAttendeeButton);
		
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(eventPanel,BorderLayout.NORTH);
		mainPanel.add(new JScrollPane(eventListArea),BorderLayout.CENTER);
		mainPanel.add(attendeePanel,BorderLayout.SOUTH);
		mainPanel.add(new JScrollPane(attendeeListArea),BorderLayout.EAST);
		
		add(mainPanel);
		setTitle("Event Management System");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==createEventButton) {
			String eventName=eventNameField.getText();
			String eventDate=eventDateField.getText();
			String eventLocation=eventLocationField.getText();
			Event event=new Event(eventName,eventDate,eventLocation);
			events.add(event);
			displayEvents();
		}
		else if(e.getSource()==addAttendeeButton) {
			String attendeeName=attendeeNameField.getText();
			Event selectedEvent=events.get(0);
			selectedEvent.addAttendee(attendeeName);
			displayAttendees(selectedEvent);
		}
	}
	
	private void displayEvents() {
		eventListArea.setText("");
		for(Event event:events) {
			eventListArea.append("Name:"+event.getName()+"\n");
			eventListArea.append("Date:"+event.getDate()+"\n");
			eventListArea.append("Location:"+event.getLocation()+"\n");
		}
	}
	
	private void displayAttendees(Event event) {
		attendeeListArea.setText("");
		for(String attendee:event.getAttendees()) {
			attendeeListArea.append(attendee+"\n");
		}
	}
}

public class EventManagementSystem1{
	public static void main(String[] args) {
		EventManagementSystem app=new EventManagementSystem();
	}
}