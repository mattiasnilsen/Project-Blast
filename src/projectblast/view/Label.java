package projectblast.view;

import org.newdawn.slick.Color;

import projectblast.model.helper.Position;

public class Label {
	private Position position;
	private String text;
	private Color color;
	
	public Label(Position position, String text, Color color){
		this.text     = text;
		this.color    = color;
		this.position = position;
	}
	
	public Label(Position position, String text){
		this(position,text,Color.white);
	}
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public int getX() {
		return position.getX();
	}
	
	public int getY() {
		return position.getY();
	}
	
	public Position getPosition(){
		return position;
	}
	
	public void move(int x, int y){
		place(new Position(position.getX() + x, position.getY() + y));
	}
	
	public void place(Position p){
		this.position = p;
	}
	
	
}
