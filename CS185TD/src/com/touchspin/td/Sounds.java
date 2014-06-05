package com.touchspin.td;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	
	// Bounce files
	private static final String bounce1BasketBall = "snd/sndBasketBallBounce1.wav";
	private static final String bounce2BasketBall = "snd/sndBasketBallBounce2.wav";
	private static final String bounce3BasketBall = "snd/sndBasketBallBounce3.wav";
	private static final String bounce1BowlingBall = "snd/sndBowlingBallBounce1.wav";
	private static final String bounce2BowlingBall = "snd/sndBowlingBallBounce2.wav";
	private static final String bounce3BowlingBall = "snd/sndBowlingBallBounce3.wav";
	private static final String bounce1PingPong = "snd/sndPingPongBounce1.wav";
	private static final String bounce2PingPong = "snd/sndPingPongBounce2.wav";
	private static final String bounce3PingPong = "snd/sndPingPongBounce3.wav";
	private static final String bounce1SoccerBall = "snd/sndSoccerBallBounce1.wav";
	private static final String bounce2SoccerBall = "snd/sndSoccerBallBounce2.wav";
	private static final String bounce3SoccerBall = "snd/sndSoccerBallBounce3.wav";
	private static final String bounce1TennisBall = "snd/sndTennisBallBounce1.wav";
	private static final String bounce2TennisBall = "snd/sndTennisBallBounce2.wav";
	private static final String bounce3TennisBall = "snd/sndTennisBallBounce3.wav";
	private static final String splash = "snd/sndSplash.wav";
	private static final String Teleport = "snd/Teleport.wav";
	
	// Sound effects
	private static final String arrowfile1 = "snd/sndArrow1.wav";
	private static final String arrowfile2 = "snd/sndArrow2.wav";
	private static final String brickCrash = "snd/sndBrickCrash.wav";
	private static final String glassBreak1 = "snd/sndGlassBreak1.wav";
	private static final String glassBreak2 = "snd/sndGlassBreak2.wav";
	private static final String lightSwitch = "snd/sndLightSwitch.wav";
	private static final String switchFile = "snd/sndSwitch.wav";
	private static final String Transmorgification = "snd/sndBallTransmorgification.wav";
	private static final String tubeDown = "snd/sndDownTube.wav";
	private static final String whistleDown = "snd/sndSlideWhistleDown.wav";
	private static final String whistleUp = "snd/sndSlideWhistleUp.wav";

	// Music files
	private static final String scaryIntro = "snd/songScaryIntro.wav";
	private static final String outro = "snd/outro.mp3";
	private static final String scary = "snd/songScaryLoopable.wav";
	private static final String song1 = "snd/song1Loopable.wav";
	private static final String wind = "snd/sndWindBlowing.wav";
	private static final String fireburn = "snd/sndFire.wav";
	private static final String campfire = "snd/sndCampFire.wav";
	private static final String song3 = "snd/song3.wav";
	private static final String song3a = "snd/song3A.wav";
	private static final String song3b = "snd/song3B.wav";
	private static final String song3c = "snd/song3C.wav";
	
	private Sound arrow1=null;
	private Sound arrow2=null;
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
			case "outro2":
				bgMusic = Gdx.audio.newMusic(Gdx.files.internal(song3));
				bgMusic.setLooping(true);
				bgMusic.setOnCompletionListener(null);
				break;
			case "outro":
				bgMusic = Gdx.audio.newMusic(Gdx.files.internal(song3));
				bgMusic.setLooping(true);
				bgMusic.setOnCompletionListener(null);		
				break;
			case "mute":
				break;
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
			case "tennisball":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal(bounce1TennisBall));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal(bounce2TennisBall));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal(bounce3TennisBall));
				break;
			case "bowling":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal(bounce1BowlingBall));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal(bounce2BowlingBall));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal(bounce3BowlingBall));
				break;
			case "basketball":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal(bounce1BasketBall));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal(bounce2BasketBall));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal(bounce3BasketBall));
				break;
			case "baseball":
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
			windBlowing=Gdx.audio.newMusic(Gdx.files.internal(wind));
			windBlowing.setLooping(true);
			windBlowing.setVolume(g.i().sfxLevel);
			windBlowing.play();
		} else {
			windBlowing.stop();
			windBlowing=null;
		}
	}
	
	public void fire(Boolean on){
		if (on){
			if(fire == null)
			{
			fire=Gdx.audio.newMusic(Gdx.files.internal(fireburn));
			fire.setLooping(true);
			fire.setVolume(g.i().sfxLevel);
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
		}
		sndSwitch.play(g.i().sfxLevel);
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
	
	public void arrow(){
		Random random = new Random();
		int rand = random.nextInt(2) + 1;
		if (arrow1!=null){
			arrow1=Gdx.audio.newSound(Gdx.files.internal(arrowfile1));
			arrow2=Gdx.audio.newSound(Gdx.files.internal(arrowfile2));
		}
		if (rand==1){
			arrow1.play(g.i().sfxLevel);
		}else{
			arrow2.play(g.i().sfxLevel);
		}
	}
	
	public void buttonClick(){
		
	}
}