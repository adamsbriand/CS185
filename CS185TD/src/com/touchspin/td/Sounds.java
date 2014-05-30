package com.touchspin.td;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	private Sound arrow1=null;
	private Sound arrow2=null;
	private Sound SlideWhistle=null;
	private Sound sndSwitch=null;
	private Music fire=null;
	private Music campFire=null;
	private Music windBlowing=null;
	private Music bgMusic=null;
	private Sound bounce1=null;
	private Sound bounce2=null;
	private Sound bounce3=null;
	
	public void BGMusic(String file){
		switch (file){
		case "dragon":
			g.i().sound.bgMusic = Gdx.audio.newMusic(Gdx.files.internal("snd/songScaryIntro.wav"));
			g.i().sound.bgMusic.setLooping(false);
			g.i().sound.bgMusic.setOnCompletionListener(
					new Music.OnCompletionListener(){
				public void onCompletion(Music music) {
					g.i().sound.bgMusic = Gdx.audio.newMusic(Gdx.files.internal("snd/songScaryLoopable.wav"));
					g.i().sound.bgMusic.setVolume(g.i().musicLevel);
					g.i().sound.bgMusic.play();
					g.i().sound.bgMusic.setLooping(true);
					g.i().sound.bgMusic.setOnCompletionListener(null);
				}});
			break;
		default:
			g.i().sound.bgMusic = Gdx.audio.newMusic(Gdx.files.internal("snd/song1Loopable.wav"));
			g.i().sound.bgMusic.setLooping(true);
		}
		if (g.i().music){
			g.i().sound.bgMusic.setVolume(g.i().musicLevel);
			g.i().sound.bgMusic.play();
		}
	}
	
	public void setBounce(){
		switch (g.i().currentBallType.toLowerCase()){
			case "pingpong":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal("snd/sndPingPongBounce1.wav"));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal("snd/sndPingPongBounce2.wav"));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal("snd/sndPingPongBounce3.wav"));
				break;
			case "soccerball":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal("snd/sndSoccerBallBounce1.wav"));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal("snd/sndSoccerBallBounce2.wav"));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal("snd/sndSoccerBallBounce3.wav"));
				break;
			case "tennisball":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal("snd/sndTennisBallBounce1.wav"));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal("snd/sndTennisBallBounce2.wav"));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal("snd/sndTennisBallBounce3.wav"));
				break;
			case "bowlingball":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal("snd/sndBowlingBallBounce1.wav"));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal("snd/sndBowlingBallBounce2.wav"));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal("snd/sndBowlingBallBounce3.wav"));
				break;
			case "basketball":
				bounce1 = Gdx.audio.newSound(Gdx.files.internal("snd/sndBasketBallBounce1.wav"));
				bounce2 = Gdx.audio.newSound(Gdx.files.internal("snd/sndBasketBallBounce2.wav"));
				bounce3 = Gdx.audio.newSound(Gdx.files.internal("snd/sndBasketBallBounce3.wav"));
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
			windBlowing=Gdx.audio.newMusic(Gdx.files.internal("snd/sndWindBlowing.wav"));
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
			fire=Gdx.audio.newMusic(Gdx.files.internal("snd/sndFire.wav"));
			fire.setLooping(true);
			fire.setVolume(g.i().sfxLevel);
			fire.play();
		} else {
			fire.stop();
			fire=null;
		}
	}
	
	public void campfire(Boolean on){
		if (on){
			campFire=Gdx.audio.newMusic(Gdx.files.internal("snd/sndCampFire.wav"));
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
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal("snd/sndSwitch.wav"));
				break;
			case "light":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal("snd/sndLightSwitch.wav"));
				break;
			case "ball":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal("snd/sndBallTransmorgification.wav"));
				break;
			case "break":
				Random random = new Random();
				int rand = random.nextInt(2) + 1;
				if (rand==1){
					sndSwitch=Gdx.audio.newSound(Gdx.files.internal("snd/sndGlassBreak1.wav"));
				} else {
					sndSwitch=Gdx.audio.newSound(Gdx.files.internal("snd/sndGlassBreak2.wav"));
				}
				break;
			case "brick":
				sndSwitch=Gdx.audio.newSound(Gdx.files.internal("snd/sndBrickCrash.wav"));
		}
		sndSwitch.play(g.i().sfxLevel);
	}
	
	public void SlideWhistle(String direction){
		switch (direction){
			case "up":
				SlideWhistle=Gdx.audio.newSound(Gdx.files.internal("snd/sndSlideWhistleUp.wav"));
				break;
			case "down":
				SlideWhistle=Gdx.audio.newSound(Gdx.files.internal("snd/sndSlideWhistleDown.wav"));
				break;
			case "tube":
				SlideWhistle=Gdx.audio.newSound(Gdx.files.internal("snd/sndDownTube.wav"));
				break;
		}
		SlideWhistle.play(g.i().sfxLevel);
	}
	
	public void arrow(){
		Random random = new Random();
		int rand = random.nextInt(2) + 1;
		if (arrow1!=null){
			arrow1=Gdx.audio.newSound(Gdx.files.internal("snd/sndArrow1.wav"));
			arrow2=Gdx.audio.newSound(Gdx.files.internal("snd/sndArrow2.wav"));
		}
		if (rand==1){
			arrow1.play(g.i().sfxLevel);
		}else{
			arrow2.play(g.i().sfxLevel);
		}
	}
}