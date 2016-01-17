package com.dici.chess.guifx;

import javafx.scene.control.Label;

public class Tile extends Label {
    public static Tile blackTile(double size) { return new Tile("black", size); }
    public static Tile whiteTile(double size) { return new Tile("white", size); }
    
    private boolean isPreSelected;
    private final String colorStyle;
    
    private Tile(String color, double size) {
        setPrefSize(size, size);
        this.colorStyle = "-fx-background-color: " + color;
        unselect();
        
        this.isPreSelected = false;
    }
    
    public void select   () { select  ("red"     ); }
    public void preselect() { select  ("green"   ); isPreSelected = true; }
    public void unselect () { setStyle(colorStyle); }
    
    private void select(String borderColor) { setStyle("-fx-border-width: 3; -fx-border-radius: 10; -fx-border-color: " + borderColor + ";" + colorStyle); }
    
    public boolean isPreSelected() { return isPreSelected; }
}
