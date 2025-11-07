package com.internshipquest.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

abstract public class AHero {

    // attributes
    protected int endurance;
    protected int social;
    protected int luck;
    protected int skills;
    protected int motivation;
    protected int money;
    protected int energy;

    private Location currentLocation;
    private Location targetLocation;
    private boolean isMoving;
    private float moveSpeed;
    private List<Vector2> pathWaypoints;
    private int currentWaypointIndex;

    // position
    protected float x;
    protected float y;
    //texture
    protected Texture texture  = new Texture("assets/Hero.png");

    // getters
    public int getEndurance() {return endurance;}
    public int getSocial() {return social;}
    public int getLuck() {return luck;}
    public int getSkills() {return skills;}
    public int getMotivation() {return motivation;}
    public int getMoney() {return money;}
    public int getEnergy() {return energy;}


    public float getX() {return x;}
    public float getY() {return y;}

    public Texture getTexture() {return texture;}

    // setters
    public void setEndurance(int endurance) {this.endurance = endurance;}
    public void setEnergy(int energy) {this.energy = energy;}
    public void setSocial(int social) {this.social = social;}
    public void setLuck(int luck) {this.luck = luck;}
    public void setSkills(int skills) {this.skills = skills;}
    public void setMotivation(int motivation) {this.motivation = motivation;}
    public void setMoney(int money) {this.money = money;}

    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}

    // constructor
    public AHero(int endurance,  int social, int luck, int skills, int motivation, int money, int energy) {

        this.endurance = endurance;
        this.social = social;
        this.luck = luck;
        this.skills = skills;
        this.motivation = motivation;
        this.money = money;
        this.energy = energy;
        this.isMoving = false;
        this.moveSpeed = 200.0f;
        this.currentLocation = null;
        this.targetLocation = null;
        this.pathWaypoints = new ArrayList<>();
        this.currentWaypointIndex = 0;

        this.x = 100;
        this.y = 100;

        this.texture = texture;
    }

    public void update(float delta) {
        if (!isMoving || pathWaypoints.isEmpty()) {
            return;
        }

        Vector2 currentTarget = pathWaypoints.get(currentWaypointIndex);
        float endX = currentTarget.x;
        float endY = currentTarget.y;

        float deltaX = endX - this.x;
        float deltaY = endY - this.y;
        float totalDistance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (totalDistance < 5.0f) {
            currentWaypointIndex++;

            if (currentWaypointIndex >= pathWaypoints.size()) {
                this.x = targetLocation.getCenterX();
                this.y = targetLocation.getCenterY();
                this.currentLocation = targetLocation;
                this.isMoving = false;
                this.targetLocation = null;
                this.pathWaypoints.clear();
                this.currentWaypointIndex = 0;
                System.out.println("[HERO] Arrivé à " + currentLocation.getName() + " !");
                return;
            }
            return;
        }

        float distanceThisFrame = moveSpeed * delta;

        if (distanceThisFrame >= totalDistance) {
            this.x = endX;
            this.y = endY;
        } else {
            float ratio = distanceThisFrame / totalDistance;
            this.x += deltaX * ratio;
            this.y += deltaY * ratio;
        }
    }

    private List<Vector2> createPath(Location from, Location to) {
        List<Vector2> path = new ArrayList<>();

        if (from == null || to == null) {
            path.add(new Vector2(to.getCenterX(), to.getCenterY()));
            return path;
        }

        String fromName = from.getName();
        String toName = to.getName();

        if (fromName.equals("Maison") && toName.equals("FitnessClub")) {
            path.add(new Vector2(48, 230));
            path.add(new Vector2(48, 640));
            path.add(new Vector2(780, 640));
            path.add(new Vector2(to.getCenterX(), to.getY() - 40));
        }
        else if (fromName.equals("FitnessClub") && toName.equals("Maison")) {
            path.add(new Vector2(780, 640));
            path.add(new Vector2(48, 640));
            path.add(new Vector2(48, 230));
            path.add(new Vector2(to.getCenterX(), to.getY() - 40));
        }
        else {
            path.add(new Vector2(to.getCenterX(), to.getY() - 40));
        }

        return path;
    }

    public void setInitialLocation(Location location) {
        this.currentLocation = location;
        if (location.getName().equals("Maison")) {
            this.x = location.getCenterX();
            this.y = location.getY() - 40;
        } else if (location.getName().equals("FitnessClub")) {
            this.x = location.getCenterX();
            this.y = location.getY() - 40;
        } else {
            this.x = location.getCenterX();
            this.y = location.getCenterY();
        }
    }


    public void moveTo(Location destination) {
        if (isMoving) {
            System.out.println("[HERO] Déjà en mouvement, impossible de bouger !");
            return;
        }

        if (destination == currentLocation) {
            System.out.println("[HERO] Déjà à cet endroit !");
            return;
        }

        System.out.println("[HERO] Déplacement vers " + destination.getName());

        this.pathWaypoints = createPath(currentLocation, destination);
        this.currentWaypointIndex = 0;

        if (!pathWaypoints.isEmpty()) {
            this.targetLocation = destination;
            this.isMoving = true;
        }
    }

    //methods
    public void render(SpriteBatch batch){
        batch.draw(texture,x,y,40,40);
    }

    // public abstract int energyLeft(int endurance);
    // public abstract boolean hasEnoughTime(Activity activity, Day day);
    // public abstract int fatigue();
    // public abstract Object postuler(Entreprise entreprise);
    // public abstract void allerVers(Lieu lieu);
    public Location getCurrentLocation() {return currentLocation;}
    public boolean isMoving() {return isMoving;}


    public void dispose() {
        if (texture != null) texture.dispose();
    }

}