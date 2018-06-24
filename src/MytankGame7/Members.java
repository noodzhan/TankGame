package MytankGame7;
import java.util.*;


class Info{
	static private int allscore=20;
	static private int myherolife=5;
	static private int enemynumber=5;
	
	public static void downmyherolife() {
		myherolife--;
	}
	public static void downenemynumber() {
		enemynumber--;
	}
	
	public static int getMyherolife() {
		return myherolife;
	}

	public static void setMyherolife(int myherolife) {
		Info.myherolife = myherolife;
	}

	public static int getEnemynumber() {
		return enemynumber;
	}

	public static void setEnemynumber(int enemynumber) {
		Info.enemynumber = enemynumber;
	}

	
	
	public static int getAllscore() {
		return allscore;
	}

	public static void setAllscore(int allscore) {
		Info.allscore = allscore;
	}
	
	
	
	
	
	
}








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
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

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
				this.y-=this.speed;
				break;
			case 1:
				this.y+=this.speed;
				break;
			case 2:
				this.x-=this.speed;
				break;
			case 3:
				this.x+=this.speed;
				break;
			}
			//System.out.println("shot x="+x+" y="+y);
			//�ӵ���������
			if(x<0||x>600||y<0||y>400) {
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
	
	int speed=1;
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	boolean islive=true;
	

	
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
	
	boolean stopmode=true;
	Shot shot=null;
	//�����ӵ�����
	Vector <Shot> ss=new Vector<Shot>();
	public void fireEnemy() {
		if(stopmode) {
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
		if(y<370) {
			this.y+=this.speed;
		}
	}
	public void moveLeft() {
		if(x>0) {
			this.x-=this.speed;
		}
	}
	public void moveRight() {
		if(x<570) {
			this.x+=this.speed;
		}
	}
}


class EnemyTank extends Tank implements Runnable{
	
	boolean stopmode=true;
	
	Vector <Shot> ss=new Vector<Shot>();
	Shot s=null;
	Vector <EnemyTank> vv=null;
	boolean flag=false;//��¼�Ƿ�Ӵ�
	public void getMyPanelEnemyset(Vector <EnemyTank> etc) {
		this.vv=etc;
	}
	
	//�з�̹�˵Ŀ�����
	public void fireEnemy() {
		if(stopmode) {
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
	}
	
	//�ж���̹���Ƿ�Ӵ�����
	public boolean touchTanks(EnemyTank ta,EnemyTank tb) {
		
		boolean b=false;
		//float distance=(tb.x-ta.x)*(tb.x-ta.x)+(tb.y-ta.y)*(tb.y-ta.y);
		double distance=Math.pow(tb.x-ta.x+5, 2)+Math.pow(tb.y-ta.y-5,2 );
		if(distance<1800)
			b=true;
		return b;
		
		}
	
	
	//�ж�̹����̹�˼��������̹���Ƿ�Ӵ�
	public boolean isTouchTank() {
		boolean b=false;
		EnemyTank temp=null;
		switch(this.direct) {
		
		case 0:
			for(int i=0;i<vv.size();i++) {
				temp=vv.get(i);
				if(temp!=this) {
					if(temp.direct==0||temp.direct==1) {
						if(this.x>temp.x&&this.x<temp.x+20&&this.y>temp.y&&this.y<temp.y+30) 
							b=true;
						if(this.x+20>temp.x&&this.x+20<temp.x+20&&this.y>temp.y&&this.y<temp.y+30)
							b=true;
						
					}
					if(temp.direct==2||temp.direct==3) {
						if(this.x>temp.x&&this.x<temp.x+30&&this.y>temp.y&&this.y<temp.y+20)
							b=true;
						if(this.x+20>temp.x&&this.x+20<temp.x+30&&this.y>temp.y&&this.y<temp.y+20)
							b=true;
					}
				}
			}
			
			break;
		case 1:
			for(int i=0;i<vv.size();i++) {
				temp=vv.get(i);
				if(temp!=this) {
					if(temp.direct==0||temp.direct==1) {
						if(this.x>temp.x&&this.x<temp.x+20&&this.y+30>temp.y&&this.y<temp.y+30) 
							b=true;
						if(this.x+20>temp.x&&this.x+20<temp.x+20&&this.y+30>temp.y&&this.y<temp.y+30)
							b=true;
						
					}
					if(temp.direct==2||temp.direct==3) {
						if(this.x>temp.x&&this.x<temp.x+30&&this.y+30>temp.y&&this.y+30<temp.y+20)
							b=true;
						if(this.x+20>temp.x&&this.x+20<temp.x+30&&this.y+30>temp.y&&this.y+30<temp.y+20)
							b=true;
					}
				}
			}
			
			break;
		case 2:
			for(int i=0;i<vv.size();i++) {
				temp=vv.get(i);
				if(temp!=this) {
					if(temp.direct==0||temp.direct==1) {
						if(this.x>temp.x&&this.x<temp.x+20&&this.y>temp.y&&this.y<temp.y+30) 
							b=true;
						if(this.x>temp.x&&this.x<temp.x+20&&this.y+20>temp.y&&this.y+20<temp.y+30)
							b=true;
						
					}
					if(temp.direct==2||temp.direct==3) {
						if(this.x>temp.x&&this.x<temp.x+30&&this.y>temp.y&&this.y<temp.y+20)
							b=true;
						if(this.x>temp.x&&this.x<temp.x+30&&this.y+20>temp.y&&this.y+20<temp.y+20)
							b=true;
					}
				}
			}
			
			break;
		case 3:
			for(int i=0;i<vv.size();i++) {
				temp=vv.get(i);
				if(temp!=this) {
					if(temp.direct==0||temp.direct==1) {
						if(this.x+30>temp.x&&this.x+30<temp.x+20&&this.y>temp.y&&this.y<temp.y+30) 
							b=true;
						if(this.x+30>temp.x&&this.x+30<temp.x+20&&this.y+20>temp.y&&this.y+20<temp.y+30)
							b=true;
						
					}
					if(temp.direct==2||temp.direct==3) {
						if(this.x+30>temp.x&&this.x+30<temp.x+30&&this.y>temp.y&&this.y<temp.y+20)
							b=true;
						if(this.x+30>temp.x&&this.x+30<temp.x+30&&this.y+20>temp.y&&this.y+20<temp.y+20)
							b=true;
					}
				}
			}
			
			break;
		
		
		}
		return b;
	}
	
	
	
	public EnemyTank(int x,int y) {
		super(x,y);
		//�з�̹��һ�������Ϳ����߳�
		Thread t=new Thread(this);
		t.start();
		
	}
	
	
	
	public void run() {

		while(true) {
			if(stopmode) {
				switch(this.direct) {
				case 0:
					for(int i=0;i<30;i++) {
						if(y>0&&!this.isTouchTank()) {
							this.y-=this.speed;
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
						if(y<370&&!this.isTouchTank()) {
							this.y+=this.speed;
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
						if(x>0&&!this.isTouchTank()) {
							this.x-=this.speed;
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
						if(x<570&&!this.isTouchTank()) {
							this.x+=this.speed;
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
}