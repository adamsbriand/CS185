package com.touchspin.td;

import java.util.ArrayList;

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
    		if(anims.get(i).name == name)
    		{
    			return anims.get(i);
    		}
    	}
		return null;
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

        return find(anims.get(iCurrAnim).nextAnim);
    }

}
