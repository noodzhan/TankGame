package MytankGame3;




class Shot implements Runnable{
	int x;
	int y;
	int direct;
	int speed=1;
	public Shot(int x,int y,int direct) {
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	
	public void run() {
		while(true) {
			
			try{
				Thread.sleep(50);
			}catch(Exception e) {
				e.toString();
			}
			switch(direct) {
			case 0:
				this.y-=speed;
				break;
			case 1:
				this.y+=speed;
				break;
			case 2:
				this.x-=speed;
				break;
			case 3:
				this.x+=speed;
				break;
			}
			System.out.println("�ӵ���x="+x+" �ӵ���y="+y);
			if(x<0||x>400||y<0||y>300)
				break;
		}
	}


}






//̹����
class Tank{
	//��ʾ̹�˵ĺ�����
	int x=0;
	//������
	int y=0;
	//��ʾ̹�˵ķ��� 0 1 2 3 ��Ӧ  �� �� �� ��
	int direct=0;
	//̹�˵ķ���
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
//�ҵ�̹��
class Hero extends Tank{
	
	int speed=5;
	Shot shot=null;
	public void fireEnemy() {
		switch(this.direct) {
		case 0:
			shot =new Shot(this.getX()+10,this.getY(),0);
			
			break;
		case 1:
			shot =new Shot(this.getX()+10,this.getY()+30,1);
			
			break;
		case 2:
			shot =new Shot(this.getX(),this.getY()+10,2);
			
			break;
		case 3:
			shot =new Shot(this.getX()+30,this.getY()+10,3);
			
			break;
		}
		Thread th=new Thread(shot);
		th.start();
	}
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