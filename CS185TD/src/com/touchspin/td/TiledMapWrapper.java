package com.touchspin.td;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;

public class TiledMapWrapper {

	private TiledMap tiledMap; 
	private TiledMapRenderer backgroundTiledMapRenderer;
	private TiledMapRenderer playerTiledMapRenderer;
	private TiledMapRenderer foregroundTiledMapRenderer;
	private int mapPixelWidth;
	private int mapPixelHeight;
	int[] forgroundLayers = {6,7};
	int[] palyerLayers = {3,4,5,6};
	int[] backgroundLayers = {0,1,2};

	float backgroundfactor = 0.5f;
	float foregroundfactor = 1.5f;
	
	MapObjects collisionObjects;	
	MapObjects playerStartPoint;

	
	public TiledMapWrapper(String path)
	{
		tiledMap = new TmxMapLoader().load(path);
		if(g.i().gameMode == 'R')// only the runner needs a background
			backgroundTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		playerTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		foregroundTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
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
	
	public int getPixelWidth()
	{
		return mapPixelWidth;
	}
	
	public int getPixelHeight()
	{
		return mapPixelHeight;
	}
	
	public TiledMap getTiledMap()
	{
		return tiledMap;
	}
	
	public TiledMapRenderer getBackgroundTiledMapRenderer()
	{
		return backgroundTiledMapRenderer;
	}
	
	public TiledMapRenderer getForegroundTiledMapRenderer()
	{
		return foregroundTiledMapRenderer;
	}
	
	/**
	 * If it is in Runner game mode then parallax will be done and the maps
	 * will be rendered with different speeds. else it just renders the 
	 * entire map the same.
	 */
	public void renderMap()
	{
		
		// only if it is the runner do we need to render certain layers differently
		if(g.i().gameMode == 'R')
		{
			backgroundTiledMapRenderer.render(backgroundLayers);
			playerTiledMapRenderer.render(palyerLayers);
			foregroundTiledMapRenderer.render(forgroundLayers);
		}
		else
			foregroundTiledMapRenderer.render();
	}		
	
	/**
	 * Mainly called by Maze class
	 * @param camera
	 */
	public void setForegroundView(OrthographicCamera camera)
	{	foregroundTiledMapRenderer.setView(camera);	}
	
	/**
	 * Mailing called by Hero class
	 * @param projectionMatrix
	 * @param viewboundsX
	 * @param viewboundsY
	 * @param viewboundsWidth
	 * @param viewboundsHeight
	 */
	public void setForegroundView(Matrix4 projectionMatrix,
	           float viewboundsX,
	           float viewboundsY,
	           float viewboundsWidth,
	           float viewboundsHeight)
	{		
			foregroundTiledMapRenderer.setView(projectionMatrix,viewboundsX, 
					viewboundsY, viewboundsWidth, viewboundsHeight);
	}
	
	public void setBackGroundView(Matrix4 projectionMatrix,
	           float viewboundsX,
	           float viewboundsY,
	           float viewboundsWidth,
	           float viewboundsHeight)
	{
		backgroundTiledMapRenderer.setView(projectionMatrix, 
				viewboundsX, viewboundsY, 
				viewboundsWidth, viewboundsHeight);		
	}
	
	public void setPlayerLayerView(Matrix4 projectionMatrix,
	           float viewboundsX,
	           float viewboundsY,
	           float viewboundsWidth,
	           float viewboundsHeight)
	{
		playerTiledMapRenderer.setView(projectionMatrix, 
				viewboundsX, viewboundsY, 
				viewboundsWidth, viewboundsHeight);		
	}
	
	private void getObjects() 
	{
	    try
	    {
	    	//Get Collision objects
	    	if(tiledMap.getLayers().get("Collision") != null)
	    	collisionObjects = tiledMap.getLayers().get("Collision").getObjects();
	    	if(tiledMap.getLayers().get("objects") != null)
	    	collisionObjects = tiledMap.getLayers().get("objects").getObjects();

	    	// get player start point
	    	if(g.i().gameMode == 'M')				
	    		playerStartPoint = tiledMap.getLayers().get("Start").getObjects();
	    }
	    catch(NullPointerException e)
	    {}
	}    	
}
