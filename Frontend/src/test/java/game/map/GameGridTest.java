/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

package game.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import game.view.components.GameGrid;
import game.models.map.TileType;
import org.junit.Test;

import game.models.map.factory.ITile;
import game.models.map.factory.EmptyTile;
import javafx.scene.layout.VBox;

/**
 *
 * @author tontt
 */
public class GameGridTest {

    public GameGridTest() {
    }

    /**
     * Test of renderGrid method, of class Grid.
     */
    // @Test
    // public void testRenderGrid() {
    //     System.out.println("renderGrid");
    //     Grid instance = new GridImpl();
    //     instance.renderGrid();
    //     // TODO review the generated test code and remove the default call to fail.
    //     fail("The test case is a prototype.");
    // }

    /**
     * Test of createTile method, of class Grid.
     */
    @Test
    public void testCreateTile() {
        System.out.println("createTile");
        GameGrid instance = new GameGridImpl();
        ITile result = instance.createTile();

        assertNotNull(result);
        assertTrue(result instanceof EmptyTile);
    }

    @Test
    public void testGetTile() {
        System.out.println("getTile");
        GameGrid instance = new GameGridImpl();
        ITile tile = instance.createTile();

        assertNotNull(tile.getTile());
        assertTrue(tile.getTile() instanceof VBox);
    }

    @Test
    public void testGetTileType() {
        System.out.println("getTileType");
        GameGrid instance = new GameGridImpl();
        ITile tile = instance.createTile();

        TileType type = tile.getTileType();
        assertNotNull(type);
        assertEquals(type, TileType.EMPTY);
    }

    public class GameGridImpl extends GameGrid {

        @Override
        public ITile createTile() {
            return new EmptyTile();
        }
    }

}