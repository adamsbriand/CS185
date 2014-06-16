package com.touchspin.td;

import java.util.ArrayList;

/* ======================================================================================
 * File:			AnimationSet.java
 * Authors:			Brian Adams - b.adams5736@edmail.edcc.edu
 * 					Russell Brendel - russell.brendel.2925@edmail.edcc.edu
 * 					Damian Forrester - dforrester777@gmail.com
 * 					Wendi Tang - w.tang2404@myedmail.edcc.edu
 * 
 * Organization:	Edmonds Community College
 * Term:			Spring 2014
 * Class:			CS 185 - Game Project Developement
 * Instructor:		Tim Hunt - thunt@edcc.edu
 * 
 * Project:			Ollie
 *---------------------------------------------------------------------------------------
 * 
 * This class holds  information about a set of animations
 * ======================================================================================
 */
public class AnimationSet {

	private ArrayList<AnimationDescription> anims;
	public int iCurrAnim = 0;

	/**
	 * Default constructor
	 */
	public AnimationSet() {
		anims = new ArrayList<>();
	}

	/**
	 * Add a new animationDescription to the arraylist
	 * 
	 * @param ad
	 *            the animation description to be added in
	 */
	public void add(AnimationDescription ad) {
		anims.add(ad);
	}

	/**
	 * Find an animation description by name
	 * 
	 * @param name
	 *            the name of the animation description to look for
	 * @return the animation description if one if found, else return null
	 */
	public AnimationDescription find(String name) {
		for (int i = 0; i < anims.size(); i++) {
			if (anims.get(i).name.equals(name)) {
				return anims.get(i);
			}
		}
		return null;
	}

	/**
	 * Get the index of the anmaition with the given name
	 * 
	 * @param name
	 *            the name of the animation to look for
	 * @return the index of that animation
	 */
	public int getIndexOf(String name) {
		for (int i = 0; i < anims.size(); i++) {
			if (anims.get(i).name.equalsIgnoreCase(name))
				return i;
		}

		return -1;
	}

	/**
	 * find the start frame of the animation
	 * 
	 * @param animName
	 *            the name of the animation to look for
	 * @return the first frame of the animation if the animation is found, else
	 *         return -1
	 */
	public int start(String animName) {
		AnimationDescription anim = find(animName);
		if (anim != null)
			return anim.frameFirst;
		else
			return -1;
	}

	/**
	 * find the last frame of the animation with the given name
	 * 
	 * @param animName
	 *            - the name of the animation to look for
	 * @return the last frame of the animation if the animation is found, else
	 *         return -1
	 */
	public int stop(String animName) {
		AnimationDescription anim = find(animName);
		if (anim != null)
			return anim.frameLast;
		else
			return -1;
	}

	/**
	 * 
	 * @return the next animation after this animation finishes
	 */
	public AnimationDescription next() {
		iCurrAnim = getIndexOf(anims.get(iCurrAnim).nextAnim);
		return anims.get(iCurrAnim);
	}

	/**
	 * Get the size of the arraylist
	 * 
	 * @return the size of arraylist
	 */
	public int getSize() {

		return anims.size();
	}

	/**
	 * Get the animation description at given index
	 * 
	 * @param index
	 *            - the index
	 * @return the anmiation description at given index
	 */
	public AnimationDescription get(int index) {

		return anims.get(index);
	}

	/**
	 * 
	 * @return current animation description
	 */
	public AnimationDescription getCurrentAnimationDescription() {
		return anims.get(iCurrAnim);
	}

}
