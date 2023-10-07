import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class flappybird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class flappybird extends Actor
{
    private double g = 1;
    private int y = 200;
    private boolean haspressed = false;
    private boolean isalive = true;
    private boolean isacross = false;
    private boolean hasaddscore = false;
    public flappybird(){
        GreenfootImage image = getImage();
        image.scale(50, 40);
    }
    public void act()
    {
        // Jikan tekan spasi,koordinat y akan berkurang dan terbang ke atas
        if(spacePressed()){
            g=-2;
        }
        g +=0.1; //Nilai g meningkat 0.1 setiap saat
        y += g; //Nilai y tidak berubah dengan kecepatan konstan
        setLocation(getX(), (int)(y));
        //Jika menabrak pipa maka flappybird mati
        if(isTouchpipe()){
            isalive = false;
        }
        //pemberian suara pada saat game over
        if(isTouchpipe()){
            isalive = false;
        }
        //setelah jatuh atau menabrak pipa maka flappybird hilang
        if(!isalive){
            getWorld().addObject(new gameover(), 300, 200);
            getWorld().removeObject(this);
        }
        //penambah skor setelah melewati pipa dan pemberian suara
        if(!hasaddscore && isacross && isalive){
            Greenfoot.playSound("score.mp3");
            Score.add(1);
        }
        hasaddscore = isacross;
    }
    public boolean spacePressed(){
        boolean pressed = false;
        if (Greenfoot.isKeyDown("Space")){
            if(!haspressed){//pemberian suara
                Greenfoot.playSound("flay.mp3");
                pressed = true;
        }
        haspressed = true;
    }else{
        haspressed = false;
    }
    return pressed;
}
//pemberian suara ketika menabrak pipa dan jatuh
    public boolean isTouchpipe(){
        isacross = false;
        for(pipe pipe : getWorld().getObjects(pipe.class)){
            if(Math.abs(pipe.getX() - getX()) < 69){
            if(Math.abs(pipe.getY() + 38 - getY()) > 37){
            Greenfoot.playSound("peng.mp3");
            isalive = false;
            }
            isacross = true;
        }
    }
    return !isalive;
}
}
