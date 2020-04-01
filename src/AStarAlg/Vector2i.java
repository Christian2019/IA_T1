package AStarAlg;

public class Vector2i {
public int x,y;

public Vector2i(int x, int y) {

	this.x = x;
	this.y = y;
}
public boolean equals(Object object) {
	Vector2i var= (Vector2i) object;
	if (var.x==this.x && var.y==this.y) {
		return true;
	}
	
	return false;
}

}
