/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

package game.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import game.map.factory.ITile;
import game.map.factory.MovementTile;
import javafx.scene.layout.VBox;

/**
 *
 * @author tontt
 */
public class GridTest {

    public GridTest() {
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
        Grid instance = new GridImpl();
        ITile result = instance.createTile();

        assertNotNull(result);
        assertTrue(result instanceof MovementTile);
    }

    @Test
    public void testGetTile() {
        System.out.println("getTile");
        Grid instance = new GridImpl();
        ITile tile = instance.createTile();

        assertNotNull(tile.getTile());
        assertTrue(tile.getTile() instanceof VBox);
    }

    @Test
    public void testGetTileType() {
        System.out.println("getTileType");
        Grid instance = new GridImpl();
        ITile tile = instance.createTile();

        MapType type = tile.getTileType();
        assertNotNull(type);
        assertEquals(type, MapType.MOVEMENT);
    }

    public class GridImpl extends Grid {

        @Override
        public ITile createTile() {
            return new MovementTile();
        }
    }

}