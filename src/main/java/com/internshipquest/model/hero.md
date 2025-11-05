Render method draws hero sprite using its texture and position
`public void render(SpriteBatch batch){
batch.draw(texture,x,y);
}`

In WorldMapScreen.java, it is shown using :
`heroBatch.begin();
hero.render(heroBatch);
heroBatch.end();`

You have to declare a heroBatch that contains heroTexture
before rendering it.