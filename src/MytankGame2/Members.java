package MytankGame2;


//坦克类
class Tank{
	//表示坦克的横坐标
	int x=0;
	//纵坐标
	int y=0;
	//表示坦克的方向 0 1 2 3 对应  上 下 左 右
	int direct=0;
	//坦克的方向
	int color;
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public  Tank(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	
}
//我的坦克
class Hero extends Tank{
	
	int speed=5;
	public Hero(int x,int y) {
		super(x,y);
	}
	public void moveUp() {
		this.y-=this.speed;
	}
	public void moveDown() {
		this.y+=this.speed;
	}
	public void moveLeft() {
		this.x-=this.speed;
	}
	public void moveRight() {
		this.x+=this.speed;
	}
}


class EnemyTank extends Tank{
	
	public EnemyTank(int x,int y) {
		super(x,y);
	}
}