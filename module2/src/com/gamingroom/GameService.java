package com.gamingroom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A singleton service for the game engine
 * 
 * @author coce@snhu.edu
 */
public class GameService {

	/**
	 * A list of the active games
	 */
	private static List<Game> games = new ArrayList<Game>();

	/*
	 * Holds the next game identifier
	 */
	private static long nextGameId = 1;

	// Static volatile initialized to null
	private static volatile GameService service = null;
	
	// private constructor
	private GameService() {
		
	}
	
	// Singleton Creator & Check 
	public static GameService getInstance() {
		if (service == null) {
			synchronized(GameService.class) {
				if (service == null) {
					service = new GameService();
				}
			}
		}
		return service;
	}

	/**
	 * Construct a new game instance
	 * 
	 * @param name the unique name of the game
	 * @return the game instance (new or existing)
	 */
	public Game addGame(String name) {

		// a local game instance
		Game game = null;

		// Use iterator to look for existing game with same name
		// if found, simply return the existing instance
		Iterator<Game> gameCheck = games.iterator();
		while(gameCheck.hasNext()) {
			Game gameInstance = gameCheck.next();
			
			if (gameInstance.getName().equalsIgnoreCase(name)) {
				return gameInstance;
			}
		}
		

		// if not found, make a new game instance and add to list of games
		if (game == null) {
			game = new Game(nextGameId++, name);
			games.add(game);
		}

		// return the new/existing game instance to the caller
		return game;
	}

	/**
	 * Returns the game instance at the specified index.
	 * <p>
	 * Scope is package/local for testing purposes.
	 * </p>
	 * @param index index position in the list to return
	 * @return requested game instance
	 */
	Game getGame(int index) {
		return games.get(index);
	}
	
	/**
	 * Returns the game instance with the specified id.
	 * 
	 * @param id unique identifier of game to search for
	 * @return requested game instance
	 */
	public Game getGame(long id) {

		// a local game instance
		Game game = null;

		// Use iterator to look for existing game with same id
		// if found, simply assign that instance to the local variable
		// Iterator for game instance
		Iterator<Game> gameCheck = games.iterator();
		//iterates through list to check for id
		while(gameCheck.hasNext()) {
			Game gameInstance = gameCheck.next();
			//checks if game has same id
			if (gameInstance.getId() == id) {
				return gameInstance;
			}
		}
		

		return game;
	}

	/**
	 * Returns the game instance with the specified name.
	 * 
	 * @param name unique name of game to search for
	 * @return requested game instance
	 */
	public Game getGame(String name) {

		// a local game instance
		Game game = null;

		// Use iterator to look for existing game with same name
		// if found, simply assign that instance to the local variable
		Iterator<Game> gameCheck = games.iterator();
		
		while(gameCheck.hasNext()) {
			Game gameInstance = gameCheck.next();
			//Checks if game exists with name
			if (gameInstance.getName().equalsIgnoreCase(name)) {
				game =  gameInstance; // if game exists returns instance with name
			}
		}

		return game;
	}

	/**
	 * Returns the number of games currently active
	 * 
	 * @return the number of games currently active
	 */
	public int getGameCount() {
		return games.size();
	}
}
