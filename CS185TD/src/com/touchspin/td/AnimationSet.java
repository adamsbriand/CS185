package com.touchspin.td;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationSet {

	private ArrayList<AnimationDescription> anims;
    private int iCurrAnim=0;
    
    public AnimationSet( ) {
        anims = new ArrayList<>();
    }
    public void add(AnimationDescription ad) {
        anims.add(ad);
    }
    
    public AnimationDescription find(String name)
    {
    	for(int i = 0; i < anims.size();i++)
    	{
    		if(anims.get(i).name.equals(name))
    		{
    			return anims.get(i);
    		}
    	}
		return null;
    }
    
    public int getIndexOf(String name)
    {
    	for(int i = 0 ; i < anims.size() ; i ++)
    	{
    		if(anims.get(i).name.equalsIgnoreCase(name))
    			return i;
    	}
    	
    	return -1;
    }
    
    public int start(String animName)
    {
        AnimationDescription anim = find(animName);
        if (anim != null) return anim.frameFirst;
        else return -1;
    }
    
    public int stop(String animName)
    {
        AnimationDescription anim = find(animName);
        if (anim != null) return anim.frameLast;
        else return -1;
    }
    
    public AnimationDescription next()
    {
    	iCurrAnim = getIndexOf(anims.get(iCurrAnim).nextAnim);
        return anims.get(iCurrAnim);
    }
    
    public int getSize()
    {

        return anims.size();
    }
    
    public  AnimationDescription get(int index)
    {

        return anims.get(index);
    }
    
    public AnimationDescription getCurrentAnimationDescription()
    {
    	return anims.get(iCurrAnim);
    }

}
