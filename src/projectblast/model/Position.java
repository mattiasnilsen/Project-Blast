package projectblast.model;

public class Position {
	private int x,y;
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof Position)){
			return false;
		}
		Position p = (Position)o;
		
		return (p.getX() == x && p.getY() == y);
	}
	
	@Override
	public int hashCode(){
		return 225583 * x + 22453 * y;
	}

}
