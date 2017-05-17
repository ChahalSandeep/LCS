import java.io.*;
class LCS{
	public static void main(String[] args)throws IOException{
	      
// Timer starts
	      long startTime = System.currentTimeMillis();
//Taking Input from a file 
		FileInputStream fis =new FileInputStream("input.txt");
		BufferedInputStream bis =new BufferedInputStream(fis);
		int c;
		String x = "";
		String y = "";
//reads the file till #,stores in x our first string
		while ((c=bis.read())!= '#'){
			x+=Character.toString((char)c );
		}
		x="!"+x;
//reads after #, stores everything in y as our second string
		if(c=='#'){
				while ((c=bis.read())!=-1){
					y += Character.toString((char)c);
				}	
			}	
		y="~"+y;
		bis.close();

//creating object, calling display an storing results in result string
		LCS rim=new LCS();
		String result =rim.display(x,y);
		System.out.println("LCS is: " + result);
		
//writing result to output file		
		BufferedWriter writer = new BufferedWriter( new FileWriter("output.txt"));
		writer.write(result);
		writer.close();
//timer ends
		long endTime   = System.currentTimeMillis();
		String TimeTaken = Long.toString(endTime - startTime);
		System.out.println("runtime = " + (TimeTaken = TimeTaken + "milliseconds"));
		
//writes to file
		BufferedWriter show = new BufferedWriter( new FileWriter("runtime.txt"));
		show.write(TimeTaken);
		show.close();


	}
		
String display(String x, String y)
{
			int[][] mat = new int[x.length()][y.length()];
			int i=0, j=0;
			
			// base case
			if(i== x.length()||j==y.length())
			{	
				
				return "0";
			}

			else
			{
				for(i=1;i<x.length();i++)
				{
					for(j=1;j<y.length();j++)
					{
						if(x.charAt(i)==y.charAt(j))
						{
							mat[i][j]= 1+mat[i-1][j-1];
						}
						else if(mat[i-1][j]>=mat[i][j-1])
						{
							mat[i][j]=mat[i-1][j];
						}
						else
						{
							mat[i][j] = mat[i][j-1];
						}
					}
				}
				int v = mat[i-1][j-1];
				System.out.println("LCS length is:" + v);
//Backtrack		
				String s1="";
				i=x.length()-1;j=y.length()-1;
				while(i!=0&&j!=0){
					if(mat[i-1][j]==mat[i][j-1] && mat[i-1][j-1]<mat[i][j])
					{						
						s1= s1+x.charAt(i);
						i=i-1;
						j=j-1;					
							continue;
					}
					else if(mat[i-1][j]>mat[i][j-1])
					{
						i=i-1;
					}
					else if(mat[i-1][j]<=mat[i][j-1])
					{
						j=j-1;
					}
				}
				String s2= new StringBuilder(s1).reverse().toString();
				return s2;
			}		
			
		}


}