package com.touchspin.td;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/* ======================================================================================
 * File:			Sounds.java
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
 * --------------------------------------------------------------------------------------
 * 
 * This class holds and manages all the sounds used throughout the game. 
 * 
 * ======================================================================================
 */

public class Sounds {
	
	// Volume control
	private float loopsSoundFactor = 0.3f;
	
	// Bounce files
	private final FileHandle baseball1 = Gdx.files.internal("snd/bounce/BaseBall1.mp3");
	private final FileHandle baseball2 = Gdx.files.internal("snd/bounce/BaseBall2.mp3");
	private final FileHandle baseball3 = Gdx.files.internal("snd/bounce/BaseBall3.mp3");
	private final FileHandle basket1 = Gdx.files.internal("snd/bounce/BasketBall1.mp3");
	private final FileHandle basket2 = Gdx.files.internal("snd/bounce/BasketBall2.mp3");
	private final FileHandle basket3 = Gdx.files.internal("snd/bounce/BasketBall3.mp3");
	private final FileHandle bowling1 = Gdx.files.internal("snd/bounce/Bowling1.mp3");
	private final FileHandle bowling2 = Gdx.files.internal("snd/bounce/Bowling2.mp3");
	private final FileHandle bowling3 = Gdx.files.internal("snd/bounce/Bowling3.mp3");
	private final FileHandle pingPong1 = Gdx.files.internal("snd/bounce/PingPong1.mp3");
	private final FileHandle pingPong2 = Gdx.files.internal("snd/bounce/PingPong2.mp3");
	private final FileHandle pingPong3 = Gdx.files.internal("snd/bounce/PingPong3.mp3");
	private final FileHandle soccer1 = Gdx.files.internal("snd/bounce/SoccerBall1.mp3");
	private final FileHandle soccer2 = Gdx.files.internal("snd/bounce/SoccerBall2.mp3");
	private final FileHandle soccer3 = Gdx.files.internal("snd/bounce/SoccerBall3.mp3");
	private final FileHandle tennis1 = Gdx.files.internal("snd/bounce/TennisBall1.mp3");
	private final FileHandle tennis2 = Gdx.files.internal("snd/bounce/TennisBall2.mp3");
	private final FileHandle tennis3 = Gdx.files.internal("snd/bounce/TennisBall3.mp3");
	
	// Sound effects
	private final FileHandle brickCrash = Gdx.files.internal("snd/sfx/BrickCrash.mp3");
	private final FileHandle glassBreak1 = Gdx.files.internal("snd/sfx/GlassBreak1.mp3");
	private final FileHandle glassBreak2 = Gdx.files.internal("snd/sfx/GlassBreak2.mp3");
	private final FileHandle glassBreak3 = Gdx.files.internal("snd/sfx/GlassBreak3.mp3");
	private final FileHandle lightSwitch = Gdx.files.internal("snd/sfx/LightSwitch.mp3");
	private final FileHandle doorCreak = Gdx.files.internal("snd/sfx/DoorCreak.mp3");
	private final FileHandle airPuff = Gdx.files.internal("snd/sfx/AirPuff.mp3");
	private final FileHandle yummy = Gdx.files.internal("snd/sfx/Yummy.mp3");
	private final FileHandle switchFile = Gdx.files.internal("snd/sfx/Switch.mp3");
	private final FileHandle Transmorgification = 
			Gdx.files.internal("snd/sfx/BallTransmorgification.mp3");
	private final FileHandle tubeDown = Gdx.files.internal("snd/sfx/DownTube.mp3");
	private final FileHandle whistleDown = 
			Gdx.files.internal("snd/sfx/SlideWhistleDown.mp3");
	private final FileHandle whistleUp = 
			Gdx.files.internal("snd/sfx/SlideWhistleUp.mp3");
	private final FileHandle splash = Gdx.files.internal("snd/sfx/Splash.mp3");
	private final FileHandle Teleport = Gdx.files.internal("snd/sfx/Teleport.mp3");
	
	// Long sounds
	private final FileHandle wind = Gdx.files.internal("snd/longsfx/Wind.mp3");
	private final FileHandle fireburn = Gdx.files.internal("snd/longsfx/Fire.mp3");
	private final FileHandle campfire = Gdx.files.internal("snd/longsfx/CampFire.mp3");

	// Music files
	private final FileHandle scaryIntro = Gdx.files.internal("snd/bg/ScaryIntro.mp3");
	private final FileHandle scary = Gdx.files.internal("snd/bg/Scary.mp3");
	private final FileHandle song1 = Gdx.files.internal("snd/bg/song1.mp3");
	private final FileHandle song3 = Gdx.files.internal("snd/bg/song3.mp3");
	private final FileHandle song4 = Gdx.files.internal("snd/bg/song4.mp3");
	private final FileHandle ABall = Gdx.files.internal("snd/bg/ABall.mp3");
	
	// Sound variables
	private Sound whistle=null;
	public Sound sndSwitch=null;
	private Sound bounce1=null;
	private Sound bounce2=null;
	private Sound bounce3=null;
	private Music fire=null;
	private Music campFire=null;
	private Music windBlowing=null;
	private Music bgMusic=null;
	
	/* ----------------------------------------------------------------------------------
	 * Maintains the background music. Processes requests to play new background music. 
	 * 
	 * Input:
	 * 		BGMusicFile - The name of the file to play. 
	 * ----------------------------------------------------------------------------------
	 */
	public void BGMusic(String BGMusicFile){
		if(bgMusic != null){
			bgMusic.stop();
		}
		switch (BGMusicFile){
			case "scaryIntro":
			case "dragon":
				bgMusic = Gdx.audio.newMusic(scaryIntro);
				bgMusic.setLooping(false);
				bgMusic.setOnCompletionListener(
						new Music.OnCompletionListener(){
					public void onCompletion(Music music) {
						bgMusic = Gdx.audio.newMusic(scary);
						bgMusic.setVolume(g.i().musicLevel);
						bgMusic.play();
						bgMusic.setLooping(true);
						bgMusic.setOnCompletionListener(null);
					}});
				break;
			case "song3":
			case "outro2":
				bgMusic = Gdx.audio.newMusic(song3);
				bgMusic.setLooping(true);
				bgMusic.setOnCompletionListener(null);
				break;
			case "menu":
			case "song4":
				bgMusic = Gdx.audio.newMusic(song4);
				bgMusic.setLooping(true);
				bgMusic.setOnCompletionListener(null);
				break;
			case "mute":
				break;
			case "iamaball":
				bgMusic = Gdx.audio.newMusic(ABall);
				bgMusic.setLooping(false);
				bgMusic.setOnCompletionListener(
						new Music.OnCompletionListener(){
					public void onCompletion(Music music) {
						BGMusic("mainmenu");
					}});
				break;
			case "outro":
			case "mainmenu":
			default:
				bgMusic = Gdx.audio.newMusic(song1);
				bgMusic.setLooping(true);
				bgMusic.setOnCompletionListener(null);
				break;
		}
		if (g.i().music && bgMusic != null){
			bgMusic.setVolume(g.i().musicLevel);
			bgMusic.play();
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Sets up the bounce sounds based on the ball type.
	 * ----------------------------------------------------------------------------------
	 */
	public void setBounce(){
		switch (g.i().currentBallType.toLowerCase()){
			case "pingpong":
				bounce1 = Gdx.audio.newSound(pingPong1);
				bounce2 = Gdx.audio.newSound(pingPong2);
				bounce3 = Gdx.audio.newSound(pingPong3);
				break;
			case "soccerball":
				bounce1 = Gdx.audio.newSound(soccer1);
				bounce2 = Gdx.audio.newSound(soccer2);
				bounce3 = Gdx.audio.newSound(soccer3);
				break;
			case "tennis":
				bounce1 = Gdx.audio.newSound(tennis1);
				bounce2 = Gdx.audio.newSound(tennis2);
				bounce3 = Gdx.audio.newSound(tennis3);
				break;
			case "bowling":
				bounce1 = Gdx.audio.newSound(bowling1);
				bounce2 = Gdx.audio.newSound(bowling2);
				bounce3 = Gdx.audio.newSound(bowling3);
				break;
			case "basket":
				bounce1 = Gdx.audio.newSound(basket1);
				bounce2 = Gdx.audio.newSound(basket2);
				bounce3 = Gdx.audio.newSound(basket3);
				break;
			case "base":
				bounce1 = Gdx.audio.newSound(baseball1);
				bounce2 = Gdx.audio.newSound(baseball2);
				bounce3 = Gdx.audio.newSound(baseball3);
				break;
			default:
				bounce1 = null;
				bounce2 = null;
				bounce3 = null;
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Chooses a random bounce sound from the set and plays it.  
	 * ----------------------------------------------------------------------------------
	 */
	public void Bounce(){
		if (bounce1!=null){
			Random random = new Random();
			int rand = random.nextInt(3) + 1;
			switch (rand){
				case 1:
					bounce1.play(g.i().sfxLevel);
					break;
				case 2:
					bounce2.play(g.i().sfxLevel);
					break;
				case 3:
					bounce3.play(g.i().sfxLevel);
					break;
			}
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Toggles the wind blowing sound
	 * ----------------------------------------------------------------------------------
	 */
	public void wind(){
		if (windBlowing==null){
			windBlowing=Gdx.audio.newMusic(wind);
			windBlowing.setLooping(true);
			windBlowing.setVolume(g.i().sfxLevel * loopsSoundFactor);
			windBlowing.play();
		} else {
			windBlowing.stop();
			windBlowing=null;
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Turns the wind sound on or off 
	 * 
	 * Input:
	 * 		on - Boolean value to turn the sound on or off
	 * 			 True = on
	 * 			 False = off
	 * ----------------------------------------------------------------------------------
	 */
	public void wind(Boolean on){
		if (on){
			if(windBlowing == null)
			{
			windBlowing=Gdx.audio.newMusic(wind);
			windBlowing.setLooping(true);
			windBlowing.setVolume(g.i().sfxLevel * loopsSoundFactor);
			windBlowing.play();
			}
		} else {
			if(windBlowing != null)
			{
			windBlowing.stop();
			windBlowing=null;
			}
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Toggles the fire sound. 
	 * ----------------------------------------------------------------------------------
	 */
	public void fire(){
		if (fire==null){
			fire=Gdx.audio.newMusic(fireburn);
			fire.setLooping(true);
			fire.setVolume(g.i().sfxLevel * loopsSoundFactor);
			fire.play();
		} else {
			fire.stop();
			fire=null;
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Turn the fire sound on or off. 
	 * 
	 * Input:
	 * 		on - Boolean value to turn the sound on or off
	 * 			 True = on
	 * 			 False = off
	 * ----------------------------------------------------------------------------------
	 */
	public void fire(Boolean on){
		if (on){
			if(fire == null)
			{
			fire=Gdx.audio.newMusic(fireburn);
			fire.setLooping(true);
			fire.setVolume(g.i().sfxLevel * loopsSoundFactor);
			fire.play();
			}
		} else {
			if(fire != null)
			{
				fire.stop();
				fire=null;
			}
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Toggles the campfire sound on or off. 
	 * ----------------------------------------------------------------------------------
	 */
	public void campfire(){
		if (campFire==null){
			campFire=Gdx.audio.newMusic(campfire);
			campFire.setLooping(true);
			campFire.setVolume(g.i().sfxLevel);
			campFire.play();
		} else {
			campFire.stop();
			campFire=null;
		}
			
	}
	
	/* ----------------------------------------------------------------------------------
	 * Turn the campfire sound on or off. 
	 * 
	 * Input:
	 * 		on - Boolean value to turn the sound on or off
	 * 			 True = on
	 * 			 False = off
	 * ----------------------------------------------------------------------------------
	 */
	public void campfire(Boolean on){
		if (on){
			campFire=Gdx.audio.newMusic(campfire);
			campFire.setLooping(true);
			campFire.setVolume(g.i().sfxLevel);
			campFire.play();
		} else {
			campFire.stop();
			campFire=null;
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Plays the requested sound effect
	 * 
	 * Input:
	 * 		type - The name of the sound effect to play
	 * ----------------------------------------------------------------------------------
	 */
	public void sndSwitch(String type){
		switch (type){
			case "switch":
				sndSwitch=Gdx.audio.newSound(switchFile);
				break;
			case "light":
				sndSwitch=Gdx.audio.newSound(lightSwitch);
				break;
			case "ball":
				sndSwitch=Gdx.audio.newSound(Transmorgification);
				break;
			case "break":
				Random random = new Random();
				int rand = random.nextInt(3) + 1;
				switch (rand) {
				case 1:
					sndSwitch=Gdx.audio.newSound(glassBreak1);
					break;
				case 2:
					sndSwitch=Gdx.audio.newSound(glassBreak2);
					break;
				case 3:
					sndSwitch=Gdx.audio.newSound(glassBreak3);
					break;
				}
				break;
			case "brick":
				sndSwitch=Gdx.audio.newSound(brickCrash);
				break;
			case "splash":
				sndSwitch=Gdx.audio.newSound(splash);
				break;
			case "Teleport":
				sndSwitch=Gdx.audio.newSound(Teleport);
				break;
			case "transmorgify":
				sndSwitch=Gdx.audio.newSound(Transmorgification);
				break;
			case "doorCreak":
				sndSwitch=Gdx.audio.newSound(doorCreak);
				break;
			case "airPuff":
				sndSwitch=Gdx.audio.newSound(airPuff);
				break;
			case "yummy":
				sndSwitch=Gdx.audio.newSound(yummy);
				break;
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Plays the requested whistle sound effect.
	 * 
	 * Input:
	 * 		direction - Sound type and direction of sound to be played. 
	 * ----------------------------------------------------------------------------------
	 */
	public void SlideWhistle(String direction){
		switch (direction){
			case "up":
				whistle=Gdx.audio.newSound(whistleUp);
				break;
			case "down":
				whistle=Gdx.audio.newSound(whistleDown);
				break;
			case "tube":
				whistle=Gdx.audio.newSound(tubeDown);
				break;
		}
		whistle.play(g.i().sfxLevel);
	}
	
	/* ----------------------------------------------------------------------------------
	 * Mutes all sound effects. 
	 * 
	 * Input:
	 * 		tempMute - Boolean value if this is to mute or unmute the sounds. 
	 * ----------------------------------------------------------------------------------
	 */
	public void sfxMute(boolean tempMute){
		if (!g.i().sfx || tempMute){
			if (whistle!=null) whistle.stop();
			if (sndSwitch!=null) sndSwitch.stop();
			if (fire!=null) fire.stop();
			if (campFire!=null) campFire.stop();
			if (windBlowing!=null) windBlowing.stop();
			if (bounce1!=null) bounce1.stop();
			if (bounce2!=null) bounce2.stop();
			if (bounce3!=null) bounce3.stop();
		} else {
			if (g.i().fire){
				fire(true);
			}
			for (int i = 0; i < g.i().npMap.size(); i++){
				if (g.i().npMap.get(i).name == "fan1") {
					if (g.i().npMap.get(i).getAnimation() == "on"){
						wind(true);
					}
				}
			}
		}
	}
	
	public void buttonClick(){
		// TODO
	}
}
