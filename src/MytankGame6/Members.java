package MytankGame6;
import java.util.*;



class Bomb{
	int x;
	int y;
	//ը��������
	int life=9;
	boolean islive=true;
	public Bomb(int x,int y) {
		this.x=x;
		this.y=y;
	}
	//��������
	public void lifeDown() {
		if(life>0) {
			life--;
		}else {
			this.islive=false;
		}
	}
}






class Shot implements Runnable{
	int x;
	int y;
	int direct;
	int speed=1;
	boolean islive=true;
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
			System.out.println("shot x="+x+" y="+y);
			//�ӵ���������
			if(x<0||x>400||y<0||y>300) {
				this.islive=false;
				break;
			}
				
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
	
	boolean islive=true;
	
	
	
	//�ж�̹���Ƿ�Ӵ�����
	public boolean touchTanks(Tank ta,Tank tb) {
		//float distance=(tb.x-ta.x)*(tb.x-ta.x)+(tb.y-ta.y)*(tb.y-ta.y);
		double distance=Math.pow(tb.x-ta.x, 2)+Math.pow(tb.y-ta.y,2 );
		if(distance>30*1.7)
			return false;
		else
			return true;
	}
	
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
	//�����ӵ�����
	Vector <Shot> ss=new Vector<Shot>();
	public void fireEnemy() {
		switch(this.direct) {
		case 0:
			shot =new Shot(this.getX()+10,this.getY(),0);
			ss.add(shot);
			break;
		case 1:
			shot =new Shot(this.getX()+10,this.getY()+30,1);
			ss.add(shot);
			break;
		case 2:
			shot =new Shot(this.getX(),this.getY()+10,2);
			ss.add(shot);
			break;
		case 3:
			shot =new Shot(this.getX()+30,this.getY()+10,3);
			ss.add(shot);
			break;
		}
		Thread th=new Thread(shot);
		th.start();
	}
	public Hero(int x,int y) {
		super(x,y);
	}
	public void moveUp() {
		if(y>0) {
			this.y-=this.speed;
		}
	}
	public void moveDown() {
		if(y<270) {
			this.y+=this.speed;
		}
	}
	public void moveLeft() {
		if(x>0) {
			this.x-=this.speed;
		}
	}
	public void moveRight() {
		if(x<370) {
			this.x+=this.speed;
		}
	}
}


class EnemyTank extends Tank implements Runnable{
	
	Vector <Shot> ss=new Vector<Shot>();
	Shot s=null;
	//�з�̹�˵Ŀ�����
	public void fireEnemy() {
		switch(this.direct) {
		case 0:
			s =new Shot(this.getX()+10,this.getY(),0);
			ss.add(s);
			break;
		case 1:
			s =new Shot(this.getX()+10,this.getY()+30,1);
			ss.add(s);
			break;
		case 2:
			s =new Shot(this.getX(),this.getY()+10,2);
			ss.add(s);
			break;
		case 3:
			s =new Shot(this.getX()+30,this.getY()+10,3);
			ss.add(s);
			break;
		}
		Thread th=new Thread(s);
		th.start();
	}
	public EnemyTank(int x,int y) {
		super(x,y);
		//�з�̹��һ�������Ϳ����߳�
		Thread t=new Thread(this);
		t.start();
		
	}
	public void run() {
		
		while(true) {
			switch(this.direct) {
			case 0:
				for(int i=0;i<30;i++) {
					if(y>0) {
						this.y--;
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				break;
			case 1:
				for(int i=0;i<30;i++) {
					if(y<270) {
						this.y++;
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				break;
			case 2:
				for(int i=0;i<30;i++) {
					if(x>0) {
						this.x--;
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				break;
			case 3:
				for(int i=0;i<30;i++) {
					if(x<370) {
						this.x++;
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				break;
			}
			int rom=(int)(Math.random()*4);
			this.direct=rom;
			//System.out.println("������ܷ����ı�");
				this.fireEnemy() ;
			
			//�з�̹���������ж�����
			if(this.islive==false)
				break;
		}
	}
}