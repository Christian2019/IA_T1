package graficos;

public class Ponto {
int x;
int y;
public Ponto(int x, int y) {
	this.x=x;
	this.y=y;
}
@Override
public String toString() {
	return "Ponto [x=" + x + ", y=" + y + "]\n";
}

}
