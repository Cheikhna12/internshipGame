package com.internshipquest.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Hero extends AbstractHero{


    //attribute
    private final String name;
    private final Texture texture = new Texture("assets/Hero.png");
    
    private Location currentLocation;
    private Location targetLocation;
    private boolean isMoving;
    private float moveSpeed;
    
    private List<Vector2> pathWaypoints;
    private int currentWaypointIndex;


    // constructor
    // template : public Hero(int endurance,  int social, int luck,
    // int skills, int motivation, int money, int energy, Texture texture)
    public Hero(){
      
        super(100, 20, 5, 0, 50, 500, 100, new Texture("assets/Hero.png"));
        this.name = "Zeldo";
        
        this.isMoving = false;
        this.moveSpeed = 200.0f;
        this.currentLocation = null;
        this.targetLocation = null;
        this.pathWaypoints = new ArrayList<>();
        this.currentWaypointIndex = 0;
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

    //methods
    public void render(SpriteBatch batch){
        batch.draw(texture,x,y,40,40);
    }

    //getter
    public String getName() {return name;}
    public Location getCurrentLocation() {return currentLocation;}
    public boolean isMoving() {return isMoving;}
}