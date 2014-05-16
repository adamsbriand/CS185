package com.touchspin.td;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;

public class TiledMapWrapper {

	private TiledMap tiledMap; 
	private TiledMapRenderer backgroundTiledMapRenderer;
	private TiledMapRenderer foregroundTiledMapRenderer;
	private int mapPixelWidth;
	private int mapPixelHeight;
	int[] forgroundLayers = {1};
	int[] backgroundLayers = {0};
	float backgroundfactor = 0.3f;
	public TiledMapWrapper(String path)
	{
		tiledMap = new TmxMapLoader().load(path);
		backgroundTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		foregroundTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        MapProperties prop = tiledMap.getProperties();
        
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
	
	public void parallaxRender()
	{
		backgroundTiledMapRenderer.render(backgroundLayers);
		foregroundTiledMapRenderer.render(forgroundLayers);
	}
	
	public void setForegroundView(Matrix4 projectionMatrix,
	           float viewboundsX,
	           float viewboundsY,
	           float viewboundsWidth,
	           float viewboundsHeight)
	{
		foregroundTiledMapRenderer.setView(projectionMatrix, viewboundsX, viewboundsY, viewboundsWidth, viewboundsHeight);
		backgroundTiledMapRenderer.setView(projectionMatrix, backgroundfactor*viewboundsX-1, backgroundfactor*viewboundsY-1, viewboundsWidth, viewboundsHeight);
	}
	
}
