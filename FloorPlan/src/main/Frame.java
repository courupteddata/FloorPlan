package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import panels.Floor;
import placeables.Room;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	Floor floor;
	
	public Frame()
	{
		setTitle("Floor Plan");
		setSize(400,400);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		floor = new Floor();
		JButton addRoom = new JButton("Add Random Room");
		
		add(floor, BorderLayout.CENTER);
		add(addRoom, BorderLayout.PAGE_END);
		
		floor.addRoom(new Room(0,0,50,50));
		
		addRoom.addActionListener(new ActionListener()
		{

			
			public void actionPerformed(ActionEvent arg0) {
				floor.addRoom(new Room(Math.random() * 400, Math.random() * 400, 50, 50));
				floor.updatePanel();
				
			}
			
		});
		
		setVisible(true);
		
	}
	
	public void updateGraphicPanel()
	{
		floor.updatePanel();
	}

}
