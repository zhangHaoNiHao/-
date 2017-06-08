package IntegerDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

//整数中的各种方法
public class Typeshu 
{
	
	//求最大公约数
	public static int simple(int a, int b) 
	{
		int c = 0;
		if (a > b) 
		{
			while (a != b) 
			{
				c = a - b;
				if (b >= c) 
				{
					a = b;
					b = c;
				}
				if (c > b) 
				{
					a = c;
				}
			}		
			return a;
		} 
		else 
		{
			while (a != b) 
			{
				c = b - a;
				if (a >= c) 
				{
					b = a;
					a = c;
				}
				if (c > a) 
				{
					b = c;
				}

			}
			return a;
		}
	}

	//求最小公倍数
	public static int gongbei(int a, int b) {
		int t = simple(a, b);
		return a * b / t;
	}
		
	// 计算两个符号的优先级
	public static char youxian(String f, String s) {
		char a1[][] =
			{ 
				{ '>', '>', '<', '<', '<', '>', '>' },
				{ '>', '>', '<', '<', '<', '>', '>' },
				{ '>', '>', '>', '>', '<', '>', '>' },
				{ '>', '>', '>', '>', '<', '>', '>' },
				{ '<', '<', '<', '<', '<', '=', ' ' }, 
				{ '>', '>', '>', '>', ' ', '>', '>' },
				{ '<', '<', '<', '<', '<', ' ', '=' } 
			};
		String a = "+-*/()#";
		int a11 = a.indexOf(f); // 找出运算符在表格中的行坐标
		int a12 = a.indexOf(s); // 找出运算符在表格中的行坐标
		return a1[a11][a12];
	}

	// 随机产生括号
	public static int[] chansheng(int num) 
	{
		int[] b = new int[num];
		for (int i = 0; i < b.length; i++) 
		{
			b[i] = 0;
		}
		Random rd = new Random();
		for (int i = 2; i < num; i++) 
		{
			for (int j = 0; j < num - i + 1; j++) 
			{
				int t = rd.nextInt(2);
				if (t == 1) 
				{
					if (b[j] >= 0 && b[j + i - 1] <= 0) 
					{
						int c = 0;
						for (int k = j; k < j + i; k++) 
						{
							c += b[k];
						}
						if (c == 0) {
							b[j]++;
							b[j + i - 1]--;
						}
					}

				}
			}
		}
		return b;
	}

	// 产生带括号的整数表达式
	public static String chanshengbiaodashi(int num, int t1) 
	{
		int a1[] = new int[num];
		int a2[] = new int[num - 1];
		int a3[] = new int[num];
		String[] a5 = new String[num];
		String[] a4 = { "+", "-", "*", "/" };
		for (int i = 0; i < num; i++) {
			a1[i] = (int) (Math.random() * t1);
		}
		for (int i = 0; i < num - 1; i++) {
			a2[i] = (int) (Math.random() * 4);
		}
		a3 = chansheng(num);
		for (int i = 0; i < num; i++) 
		{
			a5[i] = "";
			if (a3[i] < 0) {
				int c = 0 - a3[i];
				for (int j = 0; j < c; j++) 
				{
					a5[i] += ")";
				}
			} 
			else {
				for (int j = 0; j < a3[i]; j++) 
				{
					a5[i] += "(";
				}
			}
		}
		String t = " ";
		for (int i = 0; i < num - 1; i++) 
		{
			if (a3[i] > 0) 
			{
				t += a5[i] + " " + a1[i] + " " + a4[a2[i]];
			} 
			else 
			{
				t += " " + a1[i] + " " + a5[i] + a4[a2[i]];
			}
		}
		if (a3[num - 1] > 0) 
		{
			t += a5[num - 1] + " " + a1[num - 1] + " ";
		} 
		else 
		{
			t += " " + a1[num - 1] + " " + a5[num - 1];
		}
		return t;
	}

	// 两个数的运算
	public static int[] tys(int a, int b, char c) 
	{
		int[] a1 = new int[2]; // a1[0]用来记录两数运算结果，a1[1]用来记录两数能否继续算下去
		a1[0] = a1[1] = 0;
		int d = 0;
		if (c == '+') 
		{
			d = a + b;
		}
		else if (c == '-') 
		{
			if (a < b) 
			{
				a1[1] = 1;
			}
            else 
            {
				d = a - b;
			}
		} 
		else if (c == '*')
		{
			d = a * b;
		}
		else 
		{
			if ((b == 0) || (a % b != 0) || (a < b)) 
			{
				a1[1] = 1;
			} else
				d = a / b;
		}
		a1[0] = d;
		return a1;
	}

	// 整数表达式的运算
	public static String jisuan(String a) 
	{
		Stack<String> num = new Stack<String>();
		Stack<String> fuhao = new Stack<String>();
		a += "#";
		fuhao.push("#");
		char ch;
		int i = 0;
		int s = 0;
		int y = 0;
		ch = a.charAt(i);
		while (!(ch + "").equals("#") || !fuhao.peek().equals("#"))
		{
			if (ch == ' ') // 如果遇到字符为空，说明遇到数字
			{
				String rn = ""; // 用来记录数据
				while (true) 
				{				
					ch = a.charAt(++i);
					if (ch == ' ') 
					{
						break;
					}
					rn += ch;
				}
				if ((i + 1) < a.length()) 
				{
					ch = a.charAt(++i);
				}
				num.push(rn);
			} 
			else// 遇到的是字符
			{
				char comp = youxian(fuhao.peek(), ch + "");    // 比较两个字符的优先级
				if (comp == '=')          // 说明遇到右括号
				{
					fuhao.pop();
					if ((i + 1) < a.length()) 
					{
						ch = a.charAt(++i);
					}
				} 
				else if (comp == '>') // 优先级高，弹出两个数和一个运算符，进行运算
				{
					String st1 = num.pop();
					String st2 = num.pop();
					int ai1 = Integer.parseInt(st1);
					int ai2 = Integer.parseInt(st2);
					String fuh1 = fuhao.pop();
					char fuh2 = fuh1.charAt(0); // 将String类型转为char类型
					int[] sz = new int[2];
					sz = tys(ai2, ai1, fuh2);
					if (sz[1] == 1) // 如果运算中有问题，就结束运算
					{
						return "error";
					} 
					else 
					{
						num.push(sz[0] + ""); // 将两数结果压入栈中
					}
				} 
				else // 优先级比较低，把运算符压入栈中
				{
					fuhao.push(ch + "");
					if ((i + 1) < a.length())
					{
						ch = a.charAt(++i);
					}
				}
			}
		}
		return num.pop();
	}
	
	// 整数运算
	public static String[][] Integer(int t,int t1,int n) throws ClassNotFoundException, SQLException 
	{
		int numr = 0;
	    int numw = 0;
		String str[][] = new String[n][2];
		String str1[][] = new String[n][2];
		
		switch (t) 
		{
			case 1: 
			{			
				String a[] = { "+", "-", "*", "/" };
				int b[][] = new int[n][]; // 用二维表存储操作数
				for (int i = 0; i < n; i++) // 生成表达式并输出
				{
					str[i][0] = "第" + (i + 1) + "题： ";
					str1[i][0] = " ";
					
					int n1 = (int) (Math.random() * 3); // 随机生成操作数的个数
					b[i] = new int[n1 + 2];
					for (int j = 0; j < n1 + 2; j++)
						b[i][j] = (int) (Math.random() * t1);
					int d[] = new int[n1 + 1]; // 存储符号在a中的位置
					for (int j = 0; j < n1 + 1; j++) // 随机产生数映射到运算符
					{
						d[j] = (int) (Math.random() * 4);
					}
					for (int j = 0; j < n1 + 1; j++) // 将表达式存储到String类型的字符串中
					{			
						str1[i][0] += b[i][j] + " " + a[d[j]] + " ";
					}
					
					str1[i][0] += b[i][n1 + 1] + " "; // 不包含括号，方便堆栈过程的计算
					str[i][0]+=str1[i][0]+" =";
					boolean flag = true;
					for (int j = i - 1; j >= 0; j--) 
					{
						if (str1[i][0].equals(str1[j][0])) // 判断是否重复
						{
							flag = false;
							break;
						} 
						else if ((n1 == 0) && ((d[0] == 0) || (d[0] == 2)) && // 乘法或加法交换同样重复
								((b[i][0] == b[j][1]) && (b[i][1] == b[j][0]))) 
						{
							flag = false;
							break;
						}
					}
					for (int z = 0; z < n1 + 1; z++) // 判断有除法时，后边的操作数是否为0
					{
						if (d[z] == 3 && b[i][z + 1] == 0) 
						{
							flag = false;
							break;
						}
					}
					if (flag == false) 
					{
						i--;
					} 
					else 
					{
						
						str[i][1]= jisuan(str1[i][0]);
						if (str[i][1].equals("error")) 
						{
							i--;
						} 
					}
				}
				lianjiezhengshu(str); // 连接数据库，将表达式和结果导入数据库
				break;			
			}
			case 2: 
			{
				for (int i = 0; i < n; i++) 
				{
					str1[i][0] = " ";
					str[i][0] = "第" + (i + 1) + "题： ";
					
					int n1 = (int) (Math.random() * 3); // 随机生成操作数的个数
					str1[i][0] += chanshengbiaodashi(n1 + 2, t1);
					str[i][0] += str1[i][0] + " =";
					str[i][1] = jisuan(str1[i][0]);
					if (str[i][1].equals("error")) 
					{
						i--;
					}				
				}
				//lianjiezhengshu(str); // 连接数据库，将表达式和结果导入数据库
				break;				
			}
		}
		return str;
	}

	//两个真分数的计算	
	public static String liangfenshujisuan(String a, String b, char c) 
	{
		int t = 0;
		int t1 = 0, t2 = 0;
		int p1 = 0, p2 = 0;
		String s = "";
		String d[] = a.split("\\/");
		String d1[] = b.split("\\/");
		int da[] = new int[d.length];
		int db[] = new int[d1.length];
		for (int i = 0; i < d.length; i++) 
		{
			da[i] = Integer.parseInt(d[i]);
		}
		for (int i = 0; i < d1.length; i++) 
		{
			db[i] = Integer.parseInt(d1[i]);
		}

		if (c == '+')
		{
			t = gongbei(da[1], db[1]); // 两数分母同分
			t1 = t / da[1];
			da[0] *= t1; // 分子与分母相匹配
			t2 = t / db[1];
			db[0] *= t2;
			p1 = da[0] + db[0];
			s += p1 + "/" + t;
		}
		if (c == '-') 
		{
			t = gongbei(da[1], db[1]); // 两数分母同分
			t1 = t / da[1];
			da[0] *= t1; // 分子与分母相匹配
			t2 = t / db[1];
			db[0] *= t2;
			p1 = da[0] - db[0];
			if (p1 <= 0) 
			{
				return "error";
			} 
			else 
			{
				s += p1 + "/" + t;
			}
		}
		if (c == '*') 
		{
			p1 = da[0] * db[0];
			p2 = da[1] * db[1];
			s += p1 + "/" + p2;
		}
		if (c == '/') 
		{
			p1 = da[0] * db[1];
			p2 = da[1] * db[0];
			s += p1 + "/" + p2;
		}
		return s;
	}
	    
	//分数表达式计算
	public static String fenshujisuan(String a)                       
		{
			Stack <String>num=new Stack <String>();
			Stack <String>fuhao=new Stack<String>();
			a+="#";
			fuhao.push("#");
			char ch;
			int i=0;
			int s=0;
			int y=0;
			ch=a.charAt(i);
			while(!(ch+"").equals("#")||(!(fuhao.peek().equals("#"))))
			{
				if(ch==' ')          //如果ch=" ",则说明接下来是数字
				{
					String rn="";
					while(true)
					{
						ch=a.charAt(++i);
						if(ch==' ')
						{
							break;
						}
						rn+=ch;
					}
					if((i+1)<a.length())
					{
						ch=a.charAt(++i);
					}
					num.push(rn);
				}
				else           //遇到的是字符
				{
					char comp=youxian(fuhao.peek(),ch+"");  //比较两个字符的优先级
					if(comp=='=')                          //说明遇到右括号
					{                 
						fuhao.pop();
						if((i+1)<a.length())
						{
							ch=a.charAt(++i);
						}
					}
					else if(comp=='>')                   //优先级高，弹出两个数和一个运算符，进行运算
					{
						String st1=num.pop();
						String st2=num.pop();
						String fuh1=fuhao.pop();
						char fuh2=fuh1.charAt(0);        //将String类型转为char类型
						String sz=""; 
						sz=liangfenshujisuan(st2,st1,fuh2);
						if(sz.equals("error"))                     //如果运算中有问题，就结束运算
						{
							return "error";
						}
						else
						{
							num.push(sz+"");             //将两数结果压入栈中
						}
					}
					else                                  //优先级比较低，把运算符压入栈中
					{
						fuhao.push(ch+"");
						if((i+1)<a.length())
						{
							ch=a.charAt(++i);
						}
					}
				}
			}
			return num.pop();
		}
		
	//生成真分数的字符串表达式  n1=2,3,4
	public static String fenshuchansheng(int n1,int t1)           
		{
			
			String s=" ";
			String a[]={"+","-","*","/"};
			int a1[]=new int [n1-1];
			int b[]=new int [2*n1];                  //生成的随机数是操作数的2倍
			for(int i=0;i<2*n1;i=i+2)
			{
				b[i]=(int)(Math.random()*t1);         //随机产生第一个操作数的分子分母
				b[i+1]=(int)(Math.random()*t1);
				if((b[i]>=b[i+1])||(b[i+1]==0)||(b[i]==0))           //如果分子大于分母或分子、分母为0，重新赋值
				{
					i=i-2;
				}			
			}		
			for(int j=0;j<n1-1;j++)
			{
				a1[j]=(int)(Math.random()*4);          //随机产生运算符的位置
			}
			int i=0;
			for(int j=0;j<2*n1;j+=2)
			{
				int t=simple(b[j],b[j+1]);       //第1,2个数是第一个操作数的分子分母，以此类推
				b[j] /=t;                       //化简分子分母
				b[j+1] /=t;
			}
			for(int j=0;j<2*(n1-1);j+=2)
			{	
				s+=b[j]+"/"+b[j+1]+" "+a[a1[i]]+" ";
				i++;
			}
			s+=b[2*(n1-1)]+"/"+b[2*(n1-1)+1]+" ";
			return s;	
		}
		
	//生成带括号的真分数的字符串表达式  n1=2,3,4
		public static String fenshuchanshengkuohao(int n1,int t1)     
		{	
			String s="";
			String a[]={"+","-","*","/"};
			int a3[]=new int [n1];                   //记录括号产生的位置
			String a5[]=new String [2*n1];
			
			int a1[]=new int [n1-1];
			int b[]=new int [2*n1];                  //生成的随机数是操作数的2倍
			
			for(int i=0;i<2*n1;i=i+2)
			{
				b[i]=(int)(Math.random()*t1);         //随机产生第一个操作数的分子分母
				b[i+1]=(int)(Math.random()*t1);
				if((b[i]==0)||(b[i]>=b[i+1])||(b[i+1]==0))           //如果分子大于分母或分母为0，重新赋值
				{
					i=i-2;
				}			
			}		
			for(int j=0;j<n1-1;j++)
			{
				a1[j]=(int)(Math.random()*4);          //随机产生运算符的位置
			}
			
			for(int j=0;j<2*n1;j+=2)            //化简真分数
			{
				int t=simple(b[j],b[j+1]);       //第1,2个数是第一个操作数的分子分母，以此类推
				b[j] /=t;                       //化简分子分母
				b[j+1] /=t;
			}
			
			a3=chansheng(n1);
			int j=0;
			int r=0;
			for(int i=0;i<2*(n1-1);i+=2)
			{
				if(a3[r]>0)
				{
					for(int z=0;z<a3[r];z++)
						s+="(";
					s+=" " + b[i]+"/"+b[i+1]+" "+a[a1[j]];
				}
				else if(a3[r]==0)
				{
					s+=" "+b[i]+"/"+b[i+1]+" "+a[a1[j]];				
				}
				else if(a3[r]<0)
				{
					s+=" " + b[i]+"/"+b[i+1]+" ";
					for(int z=0;z<(-1*a3[r]);z++)
					{
						s+=")";
					}	
					s+=a[a1[j]];
				}
				r++;
				j++;
			}
			
			if(a3[n1-1]<0)
			{
				s+=" " + b[2*(n1-1)]+"/"+b[2*(n1-1)+1]+" ";
				for(int z=0;z<(-1*a3[r]);z++)
				   s+=")";
			}
			else
			{
				s+=" " + b[2*(n1-1)]+"/"+b[2*(n1-1)+1]+" ";
			}
				
			return s;	
		
		}

		
	//分数运算
	public static String[][] fenshu(int t2,int n,int t1) throws ClassNotFoundException, SQLException 
	{		
		String str[][] = new String[n][2];
		String str1[][] = new String[n][2];
			
		switch (t2) 
		{
			case 1: // 无括号表达式
			{
				for (int i = 0; i < n; i++) 
				{
					str[i][0]="";
					int n1 = (int) (Math.random() * 3);
					str1[i][0] = "第" + (i + 1) + "道题目:";           //用于输出
					str[i][0] += fenshuchansheng(n1 + 2, t1);           //用于计算
					str1[i][0] += str[i][0] + " =";
					str[i][1] = fenshujisuan(str[i][0]);
					
					int z = 1;
					for (int j = i - 1; j >= 0; j--) 
					{
						if (str[i][0].equals(str[j][0])) // 查重，暂时没完成要求
						{
							z = 0;
							break;
						}
					}
	
					if ((z == 0) || (str[i][1].equals("error"))) 
					{
						i--;
					} 
					else 
					{
						String b1[] = new String[2]; // 对运算结果进行化简
						int b[] = new int[2];
						b1 = str[i][1].split("\\/");
						b[0] = Integer.parseInt(b1[0]);
						b[1] = Integer.parseInt(b1[1]);
						int t = simple(b[0], b[1]);
						b[0] /= t;
						b[1] /= t;
						int a = (int) (b[0] / b[1]);
						int c = b[0] % b[1];
	
						if (b[0] > b[1]) // 判断结果格式
						{
							if (b[0] % b[1] == 0) 
							{
								str1[i][1] =""+ a;
							} 
							else 
							{
								str1[i][1] = a + "'" + c + "/" + b[1];
							}
						}
						if (b[1] > b[0]) 
						{
							str1[i][1] =b[0] + "/" + b[1];
						}
						else if (b[0] == b[1]) 
						{
							str1[i][1] = "1";
						}
	
					}
	
				}
				//lianjiefenshu(str1); // 连接数据库，将表达式和结果导入数据库
				break;
			}
			case 2: // 含有括号的表达式
			{
				for (int i = 0; i < n; i++) 
				{
					str[i][0]="";
					int n1 = (int) (Math.random() * 3);
					str1[i][0] = "第" + (i + 1) + "道题目:";           //用于输出
					str[i][0] += fenshuchanshengkuohao(n1 + 2, t1);           //用于计算
					str1[i][0] += str[i][0] + " =";
					str[i][1] = fenshujisuan(str[i][0]);
					
					int z = 1;
					for (int j = i - 1; j >= 0; j--) 
					{
						if (str[i][0].equals(str[j][0])) // 查重，暂时没完成要求
						{
							z = 0;
							break;
						}
					}
	
					if ((z == 0) || (str[i][1].equals("error"))) 
					{
						i--;
					} 
					else 
					{
						String b1[] = new String[2]; // 对运算结果进行化简
						int b[] = new int[2];
						b1 = str[i][1].split("\\/");
						b[0] = Integer.parseInt(b1[0]);
						b[1] = Integer.parseInt(b1[1]);
						int t = simple(b[0], b[1]);
						b[0] /= t;
						b[1] /= t;
						int a = (int) (b[0] / b[1]);
						int c = b[0] % b[1];
	
						if (b[0] > b[1]) // 判断结果格式
						{
							if (b[0] % b[1] == 0) 
							{
								str1[i][1] =""+ a;
							} 
							else 
							{
								str1[i][1] = a + "'" + c + "/" + b[1];
							}
						}
						if (b[1] > b[0]) 
						{
							str1[i][1] =b[0] + "/" + b[1];
						}
						else if (b[0] == b[1]) 
						{
							str1[i][1] = "1";
						}
	
					}
	
				}
				break;
			}
	
		}
			return str1;
	}
	
	
	//分数连接数据库
	public static void lianjiefenshu(String a[][]) throws ClassNotFoundException, SQLException
    {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String driverName = "com.mysql.jdbc.Driver";
		String userName = "root";
		String userPwd = "123456789";
		String ur11 = "jdbc:mysql://localhost:3306/size03";
		String ur12 = "?user=" + userName + "&password=" + userPwd;
		String ur13 = "&useUnicode=true&characterEncoding=UTF-8";
		String ur1 = ur11 + ur12 + ur13;
		Class.forName(driverName);
		conn = DriverManager.getConnection(ur1);
		String sql = "insert into text03fenshu(表达式,结果) values(?,?)";
		pstmt = conn.prepareStatement(sql);
		for(int i=0;i<a.length;i++)
		{
			pstmt.setString(1,a[i][0] );
		    pstmt.setString(2,a[i][1]);
		    pstmt.executeUpdate();
		}
		if(pstmt !=null)
		{
		 pstmt.close();
		}
		if(conn!=null)
		{
		 conn.close();
		}
		   
    }
	
	//整数连接数据库
	public static void lianjiezhengshu(String a[][]) throws ClassNotFoundException, SQLException
    {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String driverName = "com.mysql.jdbc.Driver";
		String userName = "root";
		String userPwd = "123456789";     //数据库密码
		String ur11 = "jdbc:mysql://localhost:3306/size03";     //数据库名
		String ur12 = "?user=" + userName + "&password=" + userPwd;
		String ur13 = "&useUnicode=true&characterEncoding=UTF-8";
		String ur1 = ur11 + ur12 + ur13;
		Class.forName(driverName);
		conn = DriverManager.getConnection(ur1);
		String sql = "insert into text03zhengshu(表达式,结果) values(?,?)";        //表名
		pstmt = conn.prepareStatement(sql);
		for(int i=0;i<a.length;i++)
		{
			pstmt.setString(1,a[i][0]);
		    pstmt.setString(2,a[i][1]);
		    pstmt.executeUpdate();
		}
		if(pstmt !=null)
		{
		 pstmt.close();
		}
		if(conn!=null)
		{
		 conn.close();
		}
		   
    }
	
	//比较输入的结果是否正确
	public static void judge(String a[],String b[]) throws ClassNotFoundException, SQLException
	{
		int numr=0;
		int numw=0;
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		String driverName = "com.mysql.jdbc.Driver";
   		String userName = "root";
   		String userPwd = "123456789";
   		String dbName = "size03";
   				String url1 = "jdbc:mysql://localhost:3306/" + dbName;
		String url2 = "?user=" + userName + "&password=" + userPwd;
		String url3 = "&useUnicode=true&characterEncoding=UTF-8";
		String url = url1 + url2 + url3;
		Class.forName(driverName);
		conn = DriverManager.getConnection(url);
		String sql = "select * from text03fenshu where 表达式=? and 结果=?";
		pstmt = conn.prepareStatement(sql);
		
		for(int i=0;i<a.length;i++)
		{	
			pstmt.setString(1, a[i]);
			pstmt.setString(2, b[i]);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				numr++;
			}
			else
				numw++;
			
		}
		System.out.println("正确的有"+numr+"道    错误的有"+numw+"道");
		rs = pstmt.executeQuery();
		if(rs != null) {
			rs.close();
		}
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
	}

	
	
	
	

}
