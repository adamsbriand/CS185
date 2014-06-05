package com.touchspin.td;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class TiledMapWrapper {

	private TiledMap tiledMap;
	private TiledMapRenderer backgroundTiledMapRenderer;
	private TiledMapRenderer playerTiledMapRenderer;
	private TiledMapRenderer foregroundTiledMapRenderer;
	private int mapPixelWidth;
	private int mapPixelHeight;
	public String name;

	int[] forgroundLayers;
	int[] palyerLayers;
	int[] backgroundLayers;

	int[] m1 = { 0, 1, 2 };
	int[] m2 = { 0};

	float backgroundfactor = 0.5f;
	float foregroundfactor = 1.5f;

	MapObjects collisionObjects;
	MapObjects npObjects;
	MapObjects playerStartPoint;

	public TiledMapWrapper(String path) {
		ArrayList<Integer> foregroundLayer = new ArrayList<Integer>();
		ArrayList<Integer> playerLayer = new ArrayList<Integer>();
		ArrayList<Integer> backgroundLayer = new ArrayList<Integer>();
		tiledMap = new TmxMapLoader().load(path);
		int start = path.indexOf('/') + 1;
		int stop = path.indexOf('.');
		name = path.substring(start, stop);
		int i = 0;
		for (MapLayer layer : tiledMap.getLayers()) {
			if (layer.getName().contains("fg"))
				foregroundLayer.add(i);
			else if (layer.getName().contains("bg"))
				backgroundLayer.add(i);
			else
				playerLayer.add(i);
			i++;
		}
		forgroundLayers = convertIntegers(foregroundLayer);
		palyerLayers = convertIntegers(playerLayer);
		backgroundLayers = convertIntegers(backgroundLayer);

		if (g.i().gameMode == 'R')// only the runner needs a background
		{
			backgroundTiledMapRenderer = new OrthogonalTiledMapRenderer(
					tiledMap);
			foregroundTiledMapRenderer = new OrthogonalTiledMapRenderer(
					tiledMap);
		}

		playerTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		collisionObjects = new MapObjects();
		playerStartPoint = new MapObjects();

		MapProperties prop = tiledMap.getProperties();

		getObjects();

		int mapWidth = prop.get("width", Integer.class);
		int mapHeight = prop.get("height", Integer.class);
		int tilePixelWidth = prop.get("tilewidth", Integer.class);
		int tilePixelHeight = prop.get("tileheight", Integer.class);

		mapPixelWidth = mapWidth * tilePixelWidth;
		mapPixelHeight = mapHeight * tilePixelHeight;

	}

	public int getPixelWidth() {
		return mapPixelWidth;
	}

	public int getPixelHeight() {
		return mapPixelHeight;
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}

	public TiledMapRenderer getBackgroundTiledMapRenderer() {
		return backgroundTiledMapRenderer;
	}

	public TiledMapRenderer getForegroundTiledMapRenderer() {
		return foregroundTiledMapRenderer;
	}

	/**
	 * If it is in Runner game mode then parallax will be done and the maps will
	 * be rendered with different speeds. else it just renders the entire map
	 * the same.
	 */
	public void renderMap() {
		playerTiledMapRenderer.render();
	}
	
	public void renderBackground() {
		backgroundTiledMapRenderer.render(backgroundLayers);
	}

	public void renderPlayerlayer() {
		playerTiledMapRenderer.render(palyerLayers);
	}

	public void renderForeground() {
		foregroundTiledMapRenderer.render(forgroundLayers);
	}

	/**
	 * Mailing called by Hero class
	 * 
	 * @param projectionMatrix
	 * @param viewboundsX
	 * @param viewboundsY
	 * @param viewboundsWidth
	 * @param viewboundsHeight
	 */
	public void setForegroundView(Matrix4 projectionMatrix, float viewboundsX,
			float viewboundsY, float viewboundsWidth, float viewboundsHeight) {
		foregroundTiledMapRenderer.setView(projectionMatrix, viewboundsX,
				viewboundsY, viewboundsWidth, viewboundsHeight);
	}

	public void setBackGroundView(Matrix4 projectionMatrix, float viewboundsX,
			float viewboundsY, float viewboundsWidth, float viewboundsHeight) {
		backgroundTiledMapRenderer.setView(projectionMatrix, viewboundsX,
				viewboundsY, viewboundsWidth, viewboundsHeight);
	}

	public void setPlayerLayerView(Matrix4 projectionMatrix, float viewboundsX,
			float viewboundsY, float viewboundsWidth, float viewboundsHeight) {
		playerTiledMapRenderer.setView(projectionMatrix, viewboundsX,
				viewboundsY, viewboundsWidth, viewboundsHeight);
	}

	private void getObjects() {
		TiledMapTileLayer temp;
		RectangleMapObject tempObj = new RectangleMapObject();
		collisionObjects = new MapObjects();
		float tileWidth;
		float tileHeight;

		if (tiledMap.getLayers().get("Collision") != null)
			collisionObjects = tiledMap.getLayers().get("Collision")
					.getObjects();
		
//		if (tiledMap.getLayers().get("blocks") != null) {
<<<<<<< HEAD
//		temp = (TiledMapTileLayer) tiledMap.getLayers().get("blocks");
//		tileWidth = temp.getTileWidth();
//		tileHeight = temp.getTileHeight();
//		for (int i = 0; i < temp.getWidth(); i++)
//			for (int j = 0; j < temp.getHeight(); j++) {
//				if (temp.getCell(i, j) != null) {
//					tempObj = new RectangleMapObject(i * tileWidth, j
//							* tileHeight, tileWidth, tileHeight);
//					collisionObjects.add(tempObj);
//				}
//			}
//	}
=======
//			temp = (TiledMapTileLayer) tiledMap.getLayers().get("blocks");
//			tileWidth = temp.getTileWidth();
//			tileHeight = temp.getTileHeight();
//			for (int i = 0; i < temp.getWidth(); i++)
//				for (int j = 0; j < temp.getHeight(); j++) {
//					if (temp.getCell(i, j) != null) {
//						tempObj = new RectangleMapObject(i * tileWidth, j
//								* tileHeight, tileWidth, tileHeight);
//						collisionObjects.add(tempObj);
//					}
//				}
//		}
>>>>>>> refs/remotes/origin/master

		if (tiledMap.getLayers().get("collisions") != null) {
			temp = (TiledMapTileLayer) tiledMap.getLayers().get("collisions");
			tileWidth = temp.getTileWidth();
			tileHeight = temp.getTileHeight();
			for (int i = 0; i < temp.getWidth(); i++)
				for (int j = 0; j < temp.getHeight(); j++) {
					if (temp.getCell(i, j) != null) {
						tempObj = new RectangleMapObject(i * tileWidth, j
								* tileHeight, tileWidth, tileHeight);
						collisionObjects.add(tempObj);
					}
				}
		}

		if (tiledMap.getLayers().get("objects") != null) {
			npObjects = tiledMap.getLayers().get("objects").getObjects();
		}
		if(tiledMap.getLayers().get("objectsForeground") != null)
		{
			for(MapObject object:tiledMap.getLayers().get("objectsForeground").getObjects())
			npObjects.add(object);
		}
	}

	private int[] convertIntegers(ArrayList<Integer> integers) {

		if (integers.size() != 0) {
			int[] ret = new int[integers.size()];
			for (int i = 0; i < ret.length; i++) {
				ret[i] = integers.get(i).intValue();
			}
			return ret;
		} else {
			int[] ret = { 0 };
			return ret;
		}
	}
	
	public void dispose()
	{

		tiledMap.dispose();
	}

}
