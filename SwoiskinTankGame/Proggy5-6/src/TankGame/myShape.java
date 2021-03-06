package TankGame;

// this class is mostly used for hitboxes and could be modified to work for any convex polygon (if the corner locations are known)

public class myShape
{
	private Vector2D myul, myur, mydr, mydl;
	private Vector2D[] vectors;
	private double x, y;
	
	//shape[]s should be 0: ul, 1:ur, 2: dr, 3: dl
	
	public myShape()
	{
		myur = myul = mydr = mydl = null;
	}
	
	public myShape(Vector2D ul, Vector2D ur, Vector2D dr, Vector2D dl)
	{
		myul = ul;
		myur = ur;
		mydr = dr;
		mydl = dl;
		vectors = new Vector2D[]{myul, myur, mydr, mydl};
	}
	
	public myShape(myShape s)
	{
		myul = s.UL();
		myur = s.UR();
		mydr = s.DR();
		mydl = s.DL();
		vectors = new Vector2D[]{myul, myur, mydr, mydl};
	}
	
	public void setX(double gx)
	{
		x = gx;
	}
	
	public void setY(double gy)
	{
		y = gy;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getWidth()
	{
		//assuming rectangle
		return myur.getX() - x;
	}
	
	public double getHeight()
	{
		//assuming rectangle
		return mydr.getY() - y;
	}
	
	public Vector2D UL()
	{
		return myul;
	}
	
	public Vector2D UR()
	{
		return myur;
	}
	
	public Vector2D DR()
	{
		return mydr;
	}
	
	public Vector2D DL()
	{
		return mydl;
	}
	
	public Vector2D getVector2D(int i)
	{
		return vectors[i];
	}
	
	public void translateX(int d)
	{
		for (Vector2D p : vectors)
			p.setX(p.getX() + d);
	}
	
	public void translateY(int d)
	{
		for (Vector2D p : vectors)
			p.setY(p.getY() + d);
	}
	
	public boolean collidesWith(myShape other)
	{
		 for (int x=0; x<2; x++)
	    {
	        myShape polygon = (x==0) ? this : other;
	       
	        for (int i1=0; i1<4; i1++)
	        {
	            int   i2 = (i1 + 1) % 4;
	            Vector2D p1 = polygon.getVector2D(i1);
	            Vector2D p2 = polygon.getVector2D(i2);
	           
	            Vector2D normal = new Vector2D(p2.getY() - p1.getY(), p1.getX() - p2.getX());
	           
	            double minA = Double.MAX_VALUE;
	            double maxA = Double.MIN_VALUE;
	           
	            for (int j = 0; j < 4; j++)
	            {
	            	Vector2D point = this.getVector2D(j);
	               double projected = normal.getX() * point.getX() + normal.getY() * point.getY();
	               
	               if (projected < minA)
	                   minA = projected;
	               if (projected > maxA)
	                   maxA = projected;
	            }
	           
	            double minB = Double.MAX_VALUE;
	            double maxB = Double.MIN_VALUE;
	           
	            for (int j = 0; j < 4; j++)
	            {
	            	Vector2D point = other.getVector2D(j);
	                double projected = normal.getX() * point.getX() + normal.getY() * point.getY();
	               
	                if (projected < minB)
	                    minB = projected;
	                if (projected > maxB)
	                    maxB = projected;
	            }
	           
	            if (maxA < minB || maxB < minA)
	                return false;
	        }
	    }
	   
	    return true;
	}
}
