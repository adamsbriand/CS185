package com.touchspin.td;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	
	// Volume control
	private float loopsSoundFactor = 0.3f;
	
	// Bounce files
	private static final String bounce1BasketBall = "snd/bounce/sndBasketBallBounce1.mp3";
	private static final String bounce2BasketBall = "snd/bounce/sndBasketBallBounce2.mp3";
	private static final String bounce3BasketBall = "snd/bounce/sndBasketBallBounce3.mp3";
	private static final String bounce1BowlingBall = "snd/bounce/sndBowlingBallBounce1.mp3";
	private static final String bounce2BowlingBall = "snd/bounce/sndBowlingBallBounce2.mp3";
	private static final String bounce3BowlingBall = "snd/bounce/sndBowlingBallBounce3.mp3";
	private static final String bounce1PingPong = "snd/bounce/sndPingPongBounce1.mp3";
	private static final String bounce2PingPong = "snd/bounce/sndPingPongBounce2.mp3";
	private static final String bounce3PingPong = "snd/bounce/sndPingPongBounce3.mp3";
	private static final String bounce1SoccerBall = "snd/bounce/sndSoccerBallBounce1.mp3";
	private static final String bounce2SoccerBall = "snd/bounce/sndSoccerBallBounce2.mp3";
	private static final String bounce3SoccerBall = "snd/bounce/sndSoccerBallBounce3.mp3";
	private static final String bounce1TennisBall = "snd/bounce/sndTennisBallBounce1.mp3";
	private static final String bounce2TennisBall = "snd/bounce/sndTennisBallBounce2.mp3";
	private static final String bounce3TennisBall = "snd/bounce/sndTennisBallBounce3.mp3";
	
	// Sound effects
	private static final String brickCrash  = "snd/sfx/sndBrickCrash.mp3";
	private static final String glassBreak1 = "snd/sfx/sndGlassBreak1.mp3";
	private static final String glassBreak2 = "snd/sfx/sndGlassBreak2.mp3";
	private static final String lightSwitch = "snd/sfx/sndLightSwitch.mp3";
	private static final String doorCreak   = "snd/sfx/sndDoorCreak.mp3";
	private static final String airPuff     = "snd/sfx/sndAirPuff.mp3";
	private static final String yummy       = "snd/sfx/sndYummy.mp3";
	private static final String switchFile = "snd/sfx/sndSwitch.mp3";
	private static final String Transmorgification = "snd/sfx/sndBallTransmorgification.mp3";
	private static final String tubeDown = "snd/sfx/sndDownTube.mp3";
	private static final String whistleDown = "snd/sfx/sndSlideWhistleDown.mp3";
	private static final String whistleUp = "snd/sfx/sndSlideWhistleUp.mp3";
	private static final String splash = "snd/sfx/sndSplash.mp3";
	private static final String Teleport = "snd/sfx/Teleport.mp3";

	// Music files
	private static final String scaryIntro = "snd/bgmusic/songScaryIntro.mp3";
	private static final String scary = "snd/bgmusic/songScaryLoopable.mp3";
	private static final String song1 = "snd/bgmusic/song1Loopable.mp3";
	private static final String wind = "snd/longsfx/sndWindBlowing.mp3";
	private static final String fireburn = "snd/longsfx/sndFire.mp3";
	private static final String campfire = "snd/longsfx/sndCampFire.mp3";
	private static final String song3 = "snd/bgmusic/song3.mp3";
	private static final String song4 = "snd/bgmusic/song4.mp3";
	private static final String iSeemToBeABall = "snd/bgmusic/songIseemToBeABall.mp3";
	
	private Sound whistle=null;
	public Sound sndSwitch=null;
	private Music fire=null;
	private Music campFire=null;
	private Music windBlowing=null;
	private Music bgMusic=null;
	private Sound bounce1=null;
	private Sound bounce2=null;
	private Sound bounce3=null;
	
	public void BGMusic(String file){
		
		if(bgMusic != null){
			bgMusic.stop();
		}
		switch (file){
			case "scaryIntro":
			case "dragon":
				bgMusic = Gdx.audio.newMusic(Gdx.files.internal(scaryIntro));
				bgMusic.setLooping(false);
				bgMusic.setOnCompletionListener(
						new Music.OnCompletionListener(){
					public void onCompletion(Music music) {
						bgMusic = Gdx.audio.newMusic(Gdx.files.internal(scary));
						bgMusic.setVolume(g.i().musicLevel);
						bgMusic.play();
						bgMusic.setLooping(true);
						bgMusic.setOnCompletionListener(null);
					}});
				break;
			case "song3":
			case "outro2":
				bgMusic = Gdx.audio.newMusic(Gdx.files.internal(song3));
				bgMusic.setLooping(true);
				bgMusic.setOnCompletionListener(null);
				break;		
			case "song4":
			case "menu":
				bgMusic = Gdx.audio.newMusic(Gdx.files.internal(song4));
				bgMusic.setLooping(false);
				bgMusic.setOnCompletionListener(
						new Music.OnCompletionListener(){
					public void onCompletion(Music music) {
						BGMusic("mainmenu");	
					}});
				break;
			case "mute":
				break;
			case "iamaball":
				bgMusic = Gdx.audio.newMusic(Gdx.files.internal(iSeemToBeABall));
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
				bgMusic = Gdx.audio.newMusic(Gdx.files.internal(song1));
				bgMusic.setLooping(true);
				bgMusic.setOnCompletionListener(null);
		}
		if (g.i().music && bgMusic != null){
			bgMusic.setVolume(g.i().musicLevel);
			bgMusic.play();
		}
	}
	
	public void setBounce(){
		switch (g.i().currentBallType.toLowerCase()){
			case "pingpong":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal(bounce1PingPong));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal(bounce2PingPong));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal(bounce3PingPong));
				break;
			case "soccerball":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal(bounce1SoccerBall));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal(bounce2SoccerBall));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal(bounce3SoccerBall));
				break;
			case "tennis":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal(bounce1TennisBall));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal(bounce2TennisBall));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal(bounce3TennisBall));
				break;
			case "bowling":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal(bounce1BowlingBall));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal(bounce2BowlingBall));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal(bounce3BowlingBall));
				break;
			case "basket":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal(bounce1BasketBall));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal(bounce2BasketBall));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal(bounce3BasketBall));
				break;
			case "base":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal(bounce1TennisBall));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal(bounce2TennisBall));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal(bounce3TennisBall));
				break;
			default:
				bounce1 = null;
				bounce2 = null;
				bounce3 = null;
		}
	}
	
	public void Bounce(){
		if (bounce1!=null){
			Random random = new Random();
			int rand = random.nextInt(3) + 1;
			switch (rand){
				case 1:
					bounce1.play();
					break;
				case 2:
					bounce2.play();
					break;
				case 3:
					bounce3.play();
					break;
			}
		}
	}
	
	public void wind(Boolean on){
		if (on){
			if(windBlowing == null)
			{
			windBlowing=Gdx.audio.newMusic(Gdx.files.internal(wind));
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
	
	public void fire(Boolean on){
		if (on){
			if(fire == null)
			{
			fire=Gdx.audio.newMusic(Gdx.files.internal(fireburn));
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
	
	public void campfire(Boolean on){
		if (on){
			campFire=Gdx.audio.newMusic(Gdx.files.internal(campfire));
			campFire.setLooping(true);
			campFire.setVolume(g.i().sfxLevel);
			campFire.play();
		} else {
			campFire.stop();
			campFire=null;
		}
	}
	
	public void sndSwitch(String type){
		switch (type){
			case "switch":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal(switchFile));
				break;
			case "light":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal(lightSwitch));
				break;
			case "ball":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal(Transmorgification));
				break;
			case "break":
				Random random = new Random();
				int rand = random.nextInt(2) + 1;
				if (rand==1){
					sndSwitch=Gdx.audio.newSound(Gdx.files.internal(glassBreak1));
				} else {
					sndSwitch=Gdx.audio.newSound(Gdx.files.internal(glassBreak2));
				}
				break;
			case "brick":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal(brickCrash));
				break;
			case "splash":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal(splash));
				break;
			case "Teleport":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal(Teleport));
				break;
			case "transmorgify":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal(Transmorgification));
				break;
			case "doorCreak":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal(doorCreak));
				break;
			case "airPuff":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal(airPuff));
				break;
			case "yummy":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal(yummy));
				break;
		}
	}
	
	public void playSwitch(){
		sndSwitch.play(g.i().sfxLevel);
	}
	
	public void SlideWhistle(String direction){
		switch (direction){
			case "up":
				whistle=Gdx.audio.newSound(Gdx.files.internal(whistleUp));
				break;
			case "down":
				whistle=Gdx.audio.newSound(Gdx.files.internal(whistleDown));
				break;
			case "tube":
				whistle=Gdx.audio.newSound(Gdx.files.internal(tubeDown));
				break;
		}
		whistle.play(g.i().sfxLevel);
	}
	
	
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
		
	}
}
