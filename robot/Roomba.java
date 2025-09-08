package robot;

import kareltherobot.*;

public class Roomba implements Directions {

	public static void main(String[] args) {
		
		String worldName = "robot/basicRoom.wld";
		World.setDelay(0);

		Roomba cleaner = new Roomba();
		int totalBeepers = cleaner.cleanRoom(worldName, 7, 6);
		System.out.println("Roomba cleaned up a total of " + totalBeepers + " beepers.");

	}

	private Robot roomba;

	public int cleanRoom(String worldName, int startX, int startY) {

		
		World.readWorld(worldName);
		World.setVisible(true);
		roomba = new Robot(startX, startY, East, 0);

		int totalBeepers = 0;
		Direction cleaningDirection = East;
		boolean doneCleaning = false;
		
		while (!doneCleaning) {

			totalBeepers = totalBeepers + cleanStreet();

			//Roomba face direction North
			faceDirection(North);

			if( roomba.frontIsClear() ) {
				// move up
				roomba.move();
				
				//Flip the horizontal cleaning direction
				if( cleaningDirection == East )
					cleaningDirection = West;
				else
					cleaningDirection = East;
				
				//Turn roomba to face cleaning direction
				faceDirection(cleaningDirection);			
			} 
			else {
				doneCleaning = true;
			}

		} 
			
		return totalBeepers;
		}
		
	
	
	
	//Cleans street roomba is on
	public int cleanStreet() {
		int beepers = 0;
		
		while (roomba.frontIsClear()) {

			while (roomba.nextToABeeper()) {
				roomba.pickBeeper();
				beepers++;
			}
			
			roomba.move();
		}
		//Cleans any beepers on the last avenue on that street
		 while (roomba.nextToABeeper()) {
			roomba.pickBeeper();
			beepers++;
			
		}
		
		
		return beepers;

		
	}

	public void faceDirection(Direction d)
	{
		if (d == East ) {
			while( ! roomba.facingEast())
				roomba.turnLeft();
		} else if ( d== West ) {
			while( ! roomba.facingWest())
				roomba.turnLeft();

		} else if ( d== North ) {
			while( ! roomba.facingNorth())
				roomba.turnLeft();

		} else if ( d== South ) {
			while( ! roomba.facingSouth())
				roomba.turnLeft();			
		}
	} 
	
	
}