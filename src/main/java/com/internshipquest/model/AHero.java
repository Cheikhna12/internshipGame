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
    protected int codingSkills;
    protected int motivation;
    protected int money;
    protected int energy;
    protected String name;
    protected String heroDescription;

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
    protected Texture texture;

    //roads
    final float ROUTE_NORD = 668f;
    final float ROUTE_CENTRALE = 288f;
    final float ROUTE_SUD = 64f;
    final float AVENUE_OUEST = 32f;
    final float AVENUE_EST = 1216f;



    // getters
    public int getEndurance() {return endurance;}
    public int getSocial() {return social;}
    public int getLuck() {return luck;}
    public int getCodingSkills() {return codingSkills;}
    public int getMotivation() {return motivation;}
    public int getMoney() {return money;}
    public int getEnergy() {return energy;}
    public String getName() {return name;}
    public String getHeroDescription() {return heroDescription;}

    public float getX() {return x;}
    public float getY() {return y;}

    public Texture getTexture() {return texture;}

    // setters
    public void setEndurance(int endurance) {this.endurance = endurance;}
    public void setEnergy(int energy) {this.energy = energy;}
    public void setSocial(int social) {this.social = social;}
    public void setLuck(int luck) {this.luck = luck;}
    public void setSkills(int skills) {this.codingSkills = skills;}
    public void setMotivation(int motivation) {this.motivation = motivation;}
    public void setMoney(int money) {this.money = money;}

    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}

    // constructor
    public AHero(String name, int endurance,  int social, int luck, int skills, int motivation, int money, int energy, Texture texture) {

        this.endurance = endurance;
        this.social = social;
        this.luck = luck;
        this.codingSkills = skills;
        this.motivation = motivation;
        this.money = money;
        this.energy = energy;
        this.isMoving = false;
        this.moveSpeed = 400.0f;
        this.currentLocation = null;
        this.targetLocation = null;
        this.pathWaypoints = new ArrayList<>();
        this.currentWaypointIndex = 0;
        this.name = name;
        this.texture = texture;
    }

    public void newEnergy(int endurance){
        this.setEnergy(Math.round(endurance * 1.5f));
    };


    // public abstract Object postuler(Entreprise entreprise);


    // fonction deplacement
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

        if (totalDistance < 3.0f) {
            this.x = endX;
            this.y = endY;
            currentWaypointIndex++;

            if (currentWaypointIndex >= pathWaypoints.size()) {
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
            path.add(new Vector2(to.getCenterX(), to.getCenterY() - 40));
            return path;
        }



        float fromX = from.getCenterX();
        float fromY = from.getY() - 20;
        float toX = to.getCenterX();
        float toY = to.getY() - 20;

        float fromRouteY = getClosestRoute(fromY);
        float toRouteY = getClosestRoute(toY);

        if (Math.abs(fromRouteY - toRouteY) < 50) {
            path.add(new Vector2(fromX, fromRouteY));
            path.add(new Vector2(toX, toRouteY));
            path.add(new Vector2(toX, toY));
        } else {
            path.add(new Vector2(fromX, fromRouteY));

            float avenue = (fromX > 640 || toX > 640) ? AVENUE_EST : AVENUE_OUEST;

            if (fromRouteY >= ROUTE_NORD - 50) {
                path.add(new Vector2(avenue, fromRouteY));
                if (toRouteY >= ROUTE_CENTRALE - 50 && toRouteY < ROUTE_NORD - 50) {
                    path.add(new Vector2(avenue, ROUTE_CENTRALE));
                    path.add(new Vector2(toX, ROUTE_CENTRALE));
                } else if (toRouteY < ROUTE_SUD + 50) {
                    path.add(new Vector2(avenue, ROUTE_SUD));
                    path.add(new Vector2(toX, ROUTE_SUD));
                }
            } else if (fromRouteY >= ROUTE_CENTRALE - 50 && fromRouteY < ROUTE_NORD - 50) {
                path.add(new Vector2(avenue, fromRouteY));
                if (toRouteY >= ROUTE_NORD - 50) {
                    path.add(new Vector2(avenue, ROUTE_NORD));
                    path.add(new Vector2(toX, ROUTE_NORD));
                } else if (toRouteY < ROUTE_SUD + 50) {
                    path.add(new Vector2(avenue, ROUTE_SUD));
                    path.add(new Vector2(toX, ROUTE_SUD));
                } else {
                    path.add(new Vector2(toX, ROUTE_CENTRALE));
                }
            } else if (fromRouteY < ROUTE_SUD + 50) {
                path.add(new Vector2(avenue, fromRouteY));
                if (toRouteY >= ROUTE_NORD - 50) {
                    path.add(new Vector2(avenue, ROUTE_NORD));
                    path.add(new Vector2(toX, ROUTE_NORD));
                } else if (toRouteY >= ROUTE_CENTRALE - 50) {
                    path.add(new Vector2(avenue, ROUTE_CENTRALE));
                    path.add(new Vector2(toX, ROUTE_CENTRALE));
                }
            }
            path.add(new Vector2(toX, toY));
        }

        return path;
    }

    private float getClosestRoute(float y) {


        float distNord = Math.abs(y - ROUTE_NORD);
        float distCentrale = Math.abs(y - ROUTE_CENTRALE);
        float distSud = Math.abs(y - ROUTE_SUD);

        if (distNord <= distCentrale && distNord <= distSud) {
            return ROUTE_NORD;
        } else if (distCentrale <= distSud) {
            return ROUTE_CENTRALE;
        } else {
            return ROUTE_SUD;
        }
    }


    public void setInitialLocation(Location location) {
        this.currentLocation = location;
        this.x = location.getCenterX();
        this.y = location.getY() - 5;
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



    public Location getCurrentLocation() {return currentLocation;}
    public boolean isMoving() {return isMoving;}


    public void dispose() {
        if (texture != null) texture.dispose();
    }

}