package com.touchspin.td;

import java.util.ArrayList;
import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MoverBall extends MoverPhysics {

	private HashSet<NP> prev = new HashSet<NP>();
	public ArrayList<Balls> acted = new ArrayList<Balls>();
	private int frameCount = 30;
	private Balls ball;
	private Vector2 lineOfCenters= new Vector2();
	private Vector2 speed = new Vector2();

	public MoverBall(Balls ball) {
		super();
		this.ball = ball;
	}
	
	
	@Override
	public void move(GameThing gameThing) {
		this.gameThing = gameThing;

		// Save the previous position
		previousX = gameThing.getX();
		previousY = gameThing.getY();
		// Try to move
		physicsMove();
		// If movement is failed, set the position of the
		// actor to previous position

		gameThing.setX(gameThing.getX() + Gdx.graphics.getDeltaTime()
				* speedXPerSecond);
		if (!isXFree()) {
			gameThing.setX(previousX);
			speedXPerSecond = -0.5f * speedXPerSecond;
		}

		gameThing.setY(gameThing.getY() + Gdx.graphics.getDeltaTime()
				* speedYPerSecond);
		if (!isYFree()) {
			gameThing.setY(previousY);
			speedYPerSecond = -0.5f * speedYPerSecond;
		}
		
		ballCollisionDetection();
		if (triggeredNP.size() != 0) {
			for (NP object : triggeredNP) {
				if (!prev.contains(object) && object.active) {
					g.i().t.action(object.conditions, object.action);
				}
			}
			prev.clear();
			for (NP object : triggeredNP) {
				prev.add(object);
			}
			triggeredNP.clear();
		} else {
			prev.clear();
		}
		

	}
	
	private void ballCollisionDetection()
	{
		
		if( g.i().hero.getCenter().dst(ball.getCenter()) < (g.i().hero.radius+ball.radius))
		{	
				float collisionDepth = 1 + Math.abs(g.i().hero.radius+ball.radius - g.i().hero.getCenter().dst(ball.getCenter()));
				speed.set(g.i().hero.getXSpeed(),g.i().hero.getYSpeed()).nor();
				g.i().hero.setX(g.i().hero.getX()-speed.x*collisionDepth);
				g.i().hero.setY(g.i().hero.getY()-speed.y*collisionDepth);
			ballElsaticCollision(ball,g.i().hero);
			
		}
		for (Balls theOtherBall : g.i().balls)
		{
			if(!(acted.contains(theOtherBall)||theOtherBall.equals(ball)))	
			{
				if(theOtherBall.getCenter().dst(ball.getCenter()) < (theOtherBall.radius+ball.radius))
				{
					theOtherBall.ballMover.acted.add(theOtherBall);
					ballElsaticCollision(ball,theOtherBall);
				}
			}
		}
	}
	
//	private void ballElsaticCollision(Balls ball1, Balls ball2)
//	{
//		float collisionAngle = 0;
//		float v1x = 0;
//		float v1y = 0;
//		float v2x = 0;
//		float v2y = 0;
//		
//		float f1x = 0;
//		float f2x = 0;
//		
//		float f1x0 = 0;
//		float f1y0 = 0;
//		float f2x0 = 0;
//		float f2y0 = 0;
//		
//		collisionAngle = MathUtils.atan2(ball1.getCenter().y-ball2.getCenter().y,ball1.getCenter().x-ball2.getCenter().x);
//		
//		v1x = ball1.getXSpeed()*MathUtils.cos(collisionAngle) + ball1.getYSpeed()*MathUtils.sin(collisionAngle);
//		v1y = ball1.getYSpeed()*MathUtils.cos(collisionAngle) + ball1.getXSpeed()*MathUtils.sin(collisionAngle);
//		
//		v2x = ball2.getXSpeed()*MathUtils.cos(collisionAngle) + ball2.getYSpeed()*MathUtils.sin(collisionAngle);
//		v2y = ball2.getYSpeed()*MathUtils.cos(collisionAngle) + ball2.getXSpeed()*MathUtils.sin(collisionAngle);
//		
//		f1x = (v1x * (ball1.mass - ball2.mass) + 2*ball2.mass*v2x)/(ball1.mass + ball2.mass);
//		f2x = (v2x * (ball1.mass - ball2.mass) + 2*ball2.mass*v1x)/(ball1.mass + ball2.mass);
//		
//		f1x0 = f1x * MathUtils.cos(collisionAngle) + v1y *MathUtils.sin(collisionAngle);
//		f1y0 = f1x * MathUtils.sin(collisionAngle) + v1y *MathUtils.cos(collisionAngle);
//		f2x0 = f2x * MathUtils.cos(collisionAngle) + v2y *MathUtils.sin(collisionAngle);
//		f2y0 = f2x * MathUtils.sin(collisionAngle) + v2y *MathUtils.cos(collisionAngle);
//		
//		ball1.ballMover.speedXPerSecond = f1x0;
//		ball1.ballMover.speedYPerSecond = f1y0;
//		
//		ball2.ballMover.speedXPerSecond = f2x0;
//		ball2.ballMover.speedYPerSecond = f2y0;
//		
//		
//	}
	
	private void ballElsaticCollision(Balls ball1, Balls ball2)
	{
		Vector2 v = new Vector2(ball1.getXSpeed()-ball2.getXSpeed(),ball1.getYSpeed()-ball2.getYSpeed());
		Vector2 n = new Vector2(ball2.getCenter().x-ball1.getCenter().x,ball2.getCenter().y-ball1.getCenter().y);
		float mr = ball1.mass/ball2.mass;
		Vector2 dV2 = new Vector2(ball2.getXSpeed(),ball2.getYSpeed());
		Vector2 u = new Vector2(ball1.getXSpeed()-dV2.x,ball1.getYSpeed()-dV2.y);
		float imn = (float) (1/Math.pow((n.x*n.x+n.y*n.y),0.5));	
		Vector2 nn = new Vector2(n.x*imn,n.y*imn);
		float mv = (float) Math.pow((v.x*v.x+v.y*v.y),0.5); // magnitude of v
		float cun = (float) (mv*Math.cos(Math.atan2(n.y,n.x)-Math.atan2(u.y, u.x)));
		float sf = cun*imn;
		Vector2 un = new Vector2(n.x*sf,n.y*sf);
		Vector2 ut = new Vector2(u.x - un.x,u.y - un.y);
		float num = mr - 1;
		float invDen = 1/(mr + 1);
		Vector2 vn= new Vector2(un.x*num*invDen,un.y*num*invDen);
		Vector2 wn = new Vector2(un.x*2*mr*invDen,un.y*2*mr*invDen);
		Vector2 nuDV1 = new Vector2(ut.x+vn.x+dV2.x,ut.y+vn.y+dV2.y);
		Vector2 nuDV2 = new Vector2(wn.x+dV2.x,wn.y+dV2.y);
		ball1.ballMover.speedXPerSecond=nuDV1.x;	
		ball1.ballMover.speedYPerSecond=nuDV1.y;	
		ball2.ballMover.speedXPerSecond=nuDV2.x;
		ball2.ballMover.speedYPerSecond=nuDV2.y;
		
	}
	
//	private void ballElsaticCollision(Balls ball1, Hero ball2)
//	{
//		float collisionAngle = 0;
//		float v1x = 0;
//		float v1y = 0;
//		float v2x = 0;
//		float v2y = 0;
//		
//		float f1x = 0;
//		float f2x = 0;
//		
//		float f1x0 = 0;
//		float f1y0 = 0;
//		float f2x0 = 0;
//		float f2y0 = 0;
//		
//		collisionAngle = MathUtils.atan2(ball1.getCenter().y-ball2.getCenter().y,ball1.getCenter().x-ball2.getCenter().x);
//		
//		v1x = ball1.getXSpeed()*MathUtils.cos(collisionAngle) + ball1.getYSpeed()*MathUtils.sin(collisionAngle);
//		v1y = ball1.getYSpeed()*MathUtils.cos(collisionAngle) + ball1.getXSpeed()*MathUtils.sin(collisionAngle);
//		
//		v2x = ball2.getXSpeed()*MathUtils.cos(collisionAngle) + ball2.getYSpeed()*MathUtils.sin(collisionAngle);
//		v2y = ball2.getYSpeed()*MathUtils.cos(collisionAngle) + ball2.getXSpeed()*MathUtils.sin(collisionAngle);
//		
//		f1x = (v1x * (ball1.mass - ball2.mass) + 2*ball2.mass*v2x)/(ball1.mass + ball2.mass);
//		f2x = (v2x * (ball1.mass - ball2.mass) + 2*ball2.mass*v1x)/(ball1.mass + ball2.mass);
//		
//		f1x0 = f1x * MathUtils.cos(collisionAngle) + v1y *MathUtils.sin(collisionAngle);
//		f1y0 = f1x * MathUtils.sin(collisionAngle) + v1y *MathUtils.cos(collisionAngle);
//		f2x0 = f2x * MathUtils.cos(collisionAngle) + v2y *MathUtils.sin(collisionAngle);
//		f2y0 = f2x * MathUtils.sin(collisionAngle) + v2y *MathUtils.cos(collisionAngle);
//		
//		ball1.ballMover.speedXPerSecond = f1x0;
//		ball1.ballMover.speedYPerSecond = f1y0;
//		
//		ball2.heroMover.speedXPerSecond = f2x0;
//		ball2.heroMover.speedYPerSecond = f2y0;
//		
//		
//	}
	
	private void ballElsaticCollision(Balls ball1, Hero ball2)
	{
		Vector2 v = new Vector2(ball1.getXSpeed()-ball2.getXSpeed(),ball1.getYSpeed()-ball2.getYSpeed());
		Vector2 n = new Vector2(ball2.getCenter().x-ball1.getCenter().x,ball2.getCenter().y-ball1.getCenter().y);
		float mr = ball1.mass/ball2.mass;
		Vector2 dV2 = new Vector2(ball2.getXSpeed(),ball2.getYSpeed());
		Vector2 u = new Vector2(ball1.getXSpeed()-dV2.x,ball1.getYSpeed()-dV2.y);
		float imn = (float) (1/Math.pow((n.x*n.x+n.y*n.y),0.5));	
		Vector2 nn = new Vector2(n.x*imn,n.y*imn);
		float mv = (float) Math.pow((v.x*v.x+v.y*v.y),0.5); // magnitude of v
		float cun = (float) (mv*Math.cos(Math.atan2(n.y,n.x)-Math.atan2(u.y, u.x)));
		float sf = cun*imn;
		Vector2 un = new Vector2(n.x*sf,n.y*sf);
		Vector2 ut = new Vector2(u.x - un.x,u.y - un.y);
		float num = mr - 1;
		float invDen = 1/(mr + 1);
		Vector2 vn= new Vector2(un.x*num*invDen,un.y*num*invDen);
		Vector2 wn = new Vector2(un.x*2*mr*invDen,un.y*2*mr*invDen);
		Vector2 nuDV1 = new Vector2(ut.x+vn.x+dV2.x,ut.y+vn.y+dV2.y);
		Vector2 nuDV2 = new Vector2(wn.x+dV2.x,wn.y+dV2.y);
		ball1.ballMover.speedXPerSecond=nuDV1.x;	
		ball1.ballMover.speedYPerSecond=nuDV1.y;	
		ball2.heroMover.speedXPerSecond=nuDV2.x;
		ball2.heroMover.speedYPerSecond=nuDV2.y;
		
	}
	@Override
	protected void physicsMove() {

		isInWater();
		
		fractionFactor = ball.friction;
		if (isInWater) {
			speedXPerSecond *= (1 - fractionFactor * 2);
			speedYPerSecond *= (1 - fractionFactor * 2);
		} else {
			speedXPerSecond *= (1 - fractionFactor);
			speedYPerSecond *= (1 - fractionFactor);
		}
		
		speedXPerSecond += accelerationX;
		speedYPerSecond += accelerationY;

		if (g.i().gameMode == 'R') {
			if (isInWater) {
				speedYPerSecond += (maxDyInwater < ball.dyInWater)?maxDyInwater:ball.dyInWater;
			} else
				speedYPerSecond += gravityPerSecond;
		}
		radius = gameThing.getWidth() / 2;
		tileWidth = tileHeight = 32;
	}
	
	
	
}