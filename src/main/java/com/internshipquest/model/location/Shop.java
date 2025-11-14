package com.internshipquest.model.location;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.utils.SoundManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Shop extends ALieuVisitable {

    private Texture ShopOwnerTexture;

    public Shop(InternshipQuestGame game) {
        super(game);
        this.openHour =9;
        this.closedHour =20;
       this.openOnWeekends = true;
        activities = ActivityFactory.getShopActivities(this);
        ShopOwnerTexture = new Texture("assets/StoreOwner.png");
    }

    @Override
    public void onEnter() {
        // nom, loop or not, volume %
        SoundManager.playMusic("shop", true, 0.3f);
    }

    @Override
    public void onExit() {
        SoundManager.stopMusic();
    }

    @Override
    public Texture getNpcTexture() {
        return ShopOwnerTexture;
    }

    @Override
    public String getNpcMessage() {
        return "Sir, are you here for work\n or to buy something?";
    }


    public void dispose() {
        if (ShopOwnerTexture != null)
            ShopOwnerTexture.dispose();
    }
}





