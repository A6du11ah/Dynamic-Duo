package Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {


    public int current_state=0, max_state=4;

    public int worldX, worldY;

    public BufferedImage[] image=new BufferedImage[max_state];

    public int height, width;

    public Rectangle solidArea;

    public int increase_amount;

    public String hit_type;

    public int id;

    public String name;

    public String type;

}
