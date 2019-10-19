package com.scep.genetics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import org.mini2Dx.core.engine.Positionable;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.miniscript.core.GameFuture;

import java.util.List;

public abstract class Fighter {
    protected double mov_speed;
    protected double secondPerAttack;
    protected int damage;
    protected int health, initial_health;
    protected int armor;
    protected int crit_chance;
    protected int crit_damage;
    protected int dodge;
    protected Sprite sprite;
    protected CollisionPoint position;
    protected CollisionBox collisionBox;
    protected Fighter opponent;
    protected long timeSinceLastAttack;

    public Fighter(){
        position = new CollisionPoint();
        timeSinceLastAttack = 0;
    }

    public Fighter(String spritePath) {
        this();
        sprite = new Sprite(new Texture(Gdx.files.internal(spritePath)));
        Rectangle spriteBounds = sprite.getBoundingRectangle();
        collisionBox = new CollisionBox(spriteBounds.x, spriteBounds.y, spriteBounds.width, spriteBounds.height);
    }

    public abstract void setCarac(List<Integer> pts);

    public void update(long delta){
        timeSinceLastAttack += delta;
        position.preUpdate();

        if(timeSinceLastAttack >= secondPerAttack) {
            timeSinceLastAttack = 0;
            float distanceToOpponent = position.getDistanceTo((Positionable) opponent.getPosition());
            attack(distanceToOpponent);
        }
    }

    public void interpolate(float alpha){
        position.interpolate(null, alpha);
    }

    public void render(Graphics g){
        g.drawSprite(sprite, position.getRenderX(), position.getRenderY());
    }

    public GameFuture moveByScript(float angle){
        return new MoveFuture(this, angle);
    }

    public void moveBy(float x, float y){
        position.add(x, y);
    }

    protected abstract void attack(float distanceToOpponent);

    public CollisionPoint getPosition() {
        return position;
    }

    public void setOpponent(Fighter opponent) {
        this.opponent = opponent;
    }

    public void setMov_speed(double mov_speed) {
        this.mov_speed = mov_speed;
    }

    public void setSecondPerAttack(double secondPerAttack) {
        this.secondPerAttack = secondPerAttack;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setCrit_chance(int crit_chance) {
        this.crit_chance = crit_chance;
    }

    public void setCrit_damage(int crit_damage) {
        this.crit_damage = crit_damage;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }
}
