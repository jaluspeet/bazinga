package it.unibo.pss.model.entity;

import java.util.List;

import it.unibo.pss.model.world.World;

/** Represents an entity in the simulation. */
public abstract class Entity {

	protected int x;
	protected int y;
	protected final World grid;

	public Entity(World grid, int x, int y) {
		this.grid = grid;
		this.x = x;
		this.y = y;
		grid.getTile(x, y).addEntity(this);
	}

	/** Returns the tiles surrounding this entity within the given range. */
	public List<World.Tile> getSurroundingTiles(int range) {
		return grid.getTilesInRange(x, y, range);
	}

	/** Moves the entity to the given coordinates. */
	public void moveTo(int newX, int newY) {
		grid.getTile(x, y).removeEntity(this);
		this.x = newX;
		this.y = newY;
		grid.getTile(newX, newY).addEntity(this);
	}
}
