package shortestPath;

import java.util.ArrayList;

public class MyMatrix
{
	private static int[][] list;
	private static ArrayList<Path> finalRoute;
	private static String[] cityNames;
	
	public MyMatrix()
	{	
	}
	
	// 0-3-1-4-2-0
	//http://www.people.vcu.edu/~gasmerom/MAT131/cheaplink.html
	
	public static void CLalgo()
	{
		for (int m=0;m<list.length;m++)
		{
			Path newPath = findShortestPath();
			if (newPath == null)
			{
				System.out.println("null");
			}
			else
			{
				finalRoute.add(newPath);
			}
		}
	}
	
	private static Path findShortestPath()
	{
		Path returner = null;
		double shortestDistance = Double.MAX_VALUE;
		for (int i=0;i<cityNames.length;i++)
		{
			for (int j=i;j<cityNames.length;j++)
			{
				Path temp = new Path(i, j, list[i][j]);
				
				//System.out.println(pathNotUsed(temp));
				//System.out.println(pathLegal(temp));
				
				if (i!=j && temp.distance() < shortestDistance)
				{
					System.out.println(temp.distance() + " < " + shortestDistance);
					if (pathNotUsed(temp) && pathLegal(temp))
					{
						returner = temp;
						shortestDistance = temp.distance();
					}
				}
			}
		}
		return returner;
	}
	
	private static boolean pathNotUsed(Path temp)
	{
		for (int i=0;i<finalRoute.size();i++)
		{
			Path fin = finalRoute.get(i);
			if ((fin.start() == temp.start() && fin.end() == temp.end()) ||
				  fin.start() == temp.end() && fin.end() == temp.start())
			{
				return false;
			}
		}
		return true;
	}
	
	private static boolean pathLegal(Path temp)
	{
		//System.out.print(isThreePoint(temp) + " ");
		//System.out.println(isCloser(temp));
		return (!isThreePoint(temp) && !isCloser(temp));
	}
	
	private static boolean isThreePoint(Path temp)
	{
		int[] nodesUsed = new int[cityNames.length];
		for (int i=0;i<nodesUsed.length;i++) {nodesUsed[i] = 0;}
		
		for (int i=0;i<finalRoute.size();i++)
		{
			Path netflix = finalRoute.get(i);
			nodesUsed[netflix.start()]++;
			nodesUsed[netflix.end()]++;
		}
		
		if (nodesUsed[temp.start()] >= 2 || nodesUsed[temp.end()] >= 2)
		{
			return true;
		}
		
		return false;
	}
	
	private static boolean isCloser(Path temp)
	{
		int[] nodesUsed = new int[cityNames.length];
		for (int i=0;i<nodesUsed.length;i++) {nodesUsed[i] = 0;}
		
		for (int i=0;i<finalRoute.size();i++)
		{
			Path netflix = finalRoute.get(i);
			nodesUsed[netflix.start()]++;
			nodesUsed[netflix.end()]++;
		}
		
		//i now have a list of each node's degree
		//i need to check if there would be a closed path with the addition of the new link
		//if there is not a closed path, the start and end of the current path would be degree 1
		//so i need to check if the temp's start and end have degree 1. QED pls?
		
		if (nodesUsed[temp.start()] == 1 && nodesUsed[temp.end()] == 1)
		{
			return true;
		}
		
		return false;
		
	}
	
	private static void printArray(int[] list)
	{
		for (int i=0;i<list.length;i++) {System.out.print(list[i] + " ");}
	}
	
	public void printShortestCL()
	{
		String output = "Shortest Path: ";
		for (int i=0;i<finalRoute.size();i++)
		{
			output += "(" + finalRoute.get(i).toString() + ") ";
		}
		
		System.out.println(output);
	}
	
	// Initializes map
	public static void populate()
	{
		cityNames = new String[]{"A", "B", "C", "D", "E"};
		list = new int[cityNames.length][cityNames.length];
		finalRoute = new ArrayList<Path>();
		
		for (int i=0;i<list.length;i++)
		{
			for (int j=0;j<list.length;j++)
			{
				list[i][j] = -1;
			}
		}
		
		list[0][1] = 500;
		list[0][2] = 200;
		list[0][3] = 185;
		list[0][4] = 205;
		
		list[1][2] = 305;
		list[1][3] = 360;
		list[1][4] = 340;
		
		list[2][3] = 320;
		list[2][4] = 165;
		
		list[3][4] = 302;
		
		for (int i=0;i<list.length;i++)
		{
			for (int j=0;j<list.length;j++)
			{
				list[j][i] = list[i][j];
			}
		}
	}
}
