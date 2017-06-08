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

//�����еĸ��ַ���
public class Typeshu 
{
	
	//�����Լ��
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

	//����С������
	public static int gongbei(int a, int b) {
		int t = simple(a, b);
		return a * b / t;
	}
		
	// �����������ŵ����ȼ�
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
		int a11 = a.indexOf(f); // �ҳ�������ڱ���е�������
		int a12 = a.indexOf(s); // �ҳ�������ڱ���е�������
		return a1[a11][a12];
	}

	// �����������
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

	// ���������ŵ��������ʽ
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

	// ������������
	public static int[] tys(int a, int b, char c) 
	{
		int[] a1 = new int[2]; // a1[0]������¼������������a1[1]������¼�����ܷ��������ȥ
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

	// �������ʽ������
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
			if (ch == ' ') // ��������ַ�Ϊ�գ�˵����������
			{
				String rn = ""; // ������¼����
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
			else// ���������ַ�
			{
				char comp = youxian(fuhao.peek(), ch + "");    // �Ƚ������ַ������ȼ�
				if (comp == '=')          // ˵������������
				{
					fuhao.pop();
					if ((i + 1) < a.length()) 
					{
						ch = a.charAt(++i);
					}
				} 
				else if (comp == '>') // ���ȼ��ߣ�������������һ�����������������
				{
					String st1 = num.pop();
					String st2 = num.pop();
					int ai1 = Integer.parseInt(st1);
					int ai2 = Integer.parseInt(st2);
					String fuh1 = fuhao.pop();
					char fuh2 = fuh1.charAt(0); // ��String����תΪchar����
					int[] sz = new int[2];
					sz = tys(ai2, ai1, fuh2);
					if (sz[1] == 1) // ��������������⣬�ͽ�������
					{
						return "error";
					} 
					else 
					{
						num.push(sz[0] + ""); // ���������ѹ��ջ��
					}
				} 
				else // ���ȼ��Ƚϵͣ��������ѹ��ջ��
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
	
	// ��������
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
				int b[][] = new int[n][]; // �ö�ά��洢������
				for (int i = 0; i < n; i++) // ���ɱ��ʽ�����
				{
					str[i][0] = "��" + (i + 1) + "�⣺ ";
					str1[i][0] = " ";
					
					int n1 = (int) (Math.random() * 3); // ������ɲ������ĸ���
					b[i] = new int[n1 + 2];
					for (int j = 0; j < n1 + 2; j++)
						b[i][j] = (int) (Math.random() * t1);
					int d[] = new int[n1 + 1]; // �洢������a�е�λ��
					for (int j = 0; j < n1 + 1; j++) // ���������ӳ�䵽�����
					{
						d[j] = (int) (Math.random() * 4);
					}
					for (int j = 0; j < n1 + 1; j++) // �����ʽ�洢��String���͵��ַ�����
					{			
						str1[i][0] += b[i][j] + " " + a[d[j]] + " ";
					}
					
					str1[i][0] += b[i][n1 + 1] + " "; // ���������ţ������ջ���̵ļ���
					str[i][0]+=str1[i][0]+" =";
					boolean flag = true;
					for (int j = i - 1; j >= 0; j--) 
					{
						if (str1[i][0].equals(str1[j][0])) // �ж��Ƿ��ظ�
						{
							flag = false;
							break;
						} 
						else if ((n1 == 0) && ((d[0] == 0) || (d[0] == 2)) && // �˷���ӷ�����ͬ���ظ�
								((b[i][0] == b[j][1]) && (b[i][1] == b[j][0]))) 
						{
							flag = false;
							break;
						}
					}
					for (int z = 0; z < n1 + 1; z++) // �ж��г���ʱ����ߵĲ������Ƿ�Ϊ0
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
				lianjiezhengshu(str); // �������ݿ⣬�����ʽ�ͽ���������ݿ�
				break;			
			}
			case 2: 
			{
				for (int i = 0; i < n; i++) 
				{
					str1[i][0] = " ";
					str[i][0] = "��" + (i + 1) + "�⣺ ";
					
					int n1 = (int) (Math.random() * 3); // ������ɲ������ĸ���
					str1[i][0] += chanshengbiaodashi(n1 + 2, t1);
					str[i][0] += str1[i][0] + " =";
					str[i][1] = jisuan(str1[i][0]);
					if (str[i][1].equals("error")) 
					{
						i--;
					}				
				}
				//lianjiezhengshu(str); // �������ݿ⣬�����ʽ�ͽ���������ݿ�
				break;				
			}
		}
		return str;
	}

	//����������ļ���	
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
			t = gongbei(da[1], db[1]); // ������ĸͬ��
			t1 = t / da[1];
			da[0] *= t1; // �������ĸ��ƥ��
			t2 = t / db[1];
			db[0] *= t2;
			p1 = da[0] + db[0];
			s += p1 + "/" + t;
		}
		if (c == '-') 
		{
			t = gongbei(da[1], db[1]); // ������ĸͬ��
			t1 = t / da[1];
			da[0] *= t1; // �������ĸ��ƥ��
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
	    
	//�������ʽ����
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
				if(ch==' ')          //���ch=" ",��˵��������������
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
				else           //���������ַ�
				{
					char comp=youxian(fuhao.peek(),ch+"");  //�Ƚ������ַ������ȼ�
					if(comp=='=')                          //˵������������
					{                 
						fuhao.pop();
						if((i+1)<a.length())
						{
							ch=a.charAt(++i);
						}
					}
					else if(comp=='>')                   //���ȼ��ߣ�������������һ�����������������
					{
						String st1=num.pop();
						String st2=num.pop();
						String fuh1=fuhao.pop();
						char fuh2=fuh1.charAt(0);        //��String����תΪchar����
						String sz=""; 
						sz=liangfenshujisuan(st2,st1,fuh2);
						if(sz.equals("error"))                     //��������������⣬�ͽ�������
						{
							return "error";
						}
						else
						{
							num.push(sz+"");             //���������ѹ��ջ��
						}
					}
					else                                  //���ȼ��Ƚϵͣ��������ѹ��ջ��
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
		
	//������������ַ������ʽ  n1=2,3,4
	public static String fenshuchansheng(int n1,int t1)           
		{
			
			String s=" ";
			String a[]={"+","-","*","/"};
			int a1[]=new int [n1-1];
			int b[]=new int [2*n1];                  //���ɵ�������ǲ�������2��
			for(int i=0;i<2*n1;i=i+2)
			{
				b[i]=(int)(Math.random()*t1);         //���������һ���������ķ��ӷ�ĸ
				b[i+1]=(int)(Math.random()*t1);
				if((b[i]>=b[i+1])||(b[i+1]==0)||(b[i]==0))           //������Ӵ��ڷ�ĸ����ӡ���ĸΪ0�����¸�ֵ
				{
					i=i-2;
				}			
			}		
			for(int j=0;j<n1-1;j++)
			{
				a1[j]=(int)(Math.random()*4);          //��������������λ��
			}
			int i=0;
			for(int j=0;j<2*n1;j+=2)
			{
				int t=simple(b[j],b[j+1]);       //��1,2�����ǵ�һ���������ķ��ӷ�ĸ���Դ�����
				b[j] /=t;                       //������ӷ�ĸ
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
		
	//���ɴ����ŵ���������ַ������ʽ  n1=2,3,4
		public static String fenshuchanshengkuohao(int n1,int t1)     
		{	
			String s="";
			String a[]={"+","-","*","/"};
			int a3[]=new int [n1];                   //��¼���Ų�����λ��
			String a5[]=new String [2*n1];
			
			int a1[]=new int [n1-1];
			int b[]=new int [2*n1];                  //���ɵ�������ǲ�������2��
			
			for(int i=0;i<2*n1;i=i+2)
			{
				b[i]=(int)(Math.random()*t1);         //���������һ���������ķ��ӷ�ĸ
				b[i+1]=(int)(Math.random()*t1);
				if((b[i]==0)||(b[i]>=b[i+1])||(b[i+1]==0))           //������Ӵ��ڷ�ĸ���ĸΪ0�����¸�ֵ
				{
					i=i-2;
				}			
			}		
			for(int j=0;j<n1-1;j++)
			{
				a1[j]=(int)(Math.random()*4);          //��������������λ��
			}
			
			for(int j=0;j<2*n1;j+=2)            //���������
			{
				int t=simple(b[j],b[j+1]);       //��1,2�����ǵ�һ���������ķ��ӷ�ĸ���Դ�����
				b[j] /=t;                       //������ӷ�ĸ
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

		
	//��������
	public static String[][] fenshu(int t2,int n,int t1) throws ClassNotFoundException, SQLException 
	{		
		String str[][] = new String[n][2];
		String str1[][] = new String[n][2];
			
		switch (t2) 
		{
			case 1: // �����ű��ʽ
			{
				for (int i = 0; i < n; i++) 
				{
					str[i][0]="";
					int n1 = (int) (Math.random() * 3);
					str1[i][0] = "��" + (i + 1) + "����Ŀ:";           //�������
					str[i][0] += fenshuchansheng(n1 + 2, t1);           //���ڼ���
					str1[i][0] += str[i][0] + " =";
					str[i][1] = fenshujisuan(str[i][0]);
					
					int z = 1;
					for (int j = i - 1; j >= 0; j--) 
					{
						if (str[i][0].equals(str[j][0])) // ���أ���ʱû���Ҫ��
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
						String b1[] = new String[2]; // �����������л���
						int b[] = new int[2];
						b1 = str[i][1].split("\\/");
						b[0] = Integer.parseInt(b1[0]);
						b[1] = Integer.parseInt(b1[1]);
						int t = simple(b[0], b[1]);
						b[0] /= t;
						b[1] /= t;
						int a = (int) (b[0] / b[1]);
						int c = b[0] % b[1];
	
						if (b[0] > b[1]) // �жϽ����ʽ
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
				//lianjiefenshu(str1); // �������ݿ⣬�����ʽ�ͽ���������ݿ�
				break;
			}
			case 2: // �������ŵı��ʽ
			{
				for (int i = 0; i < n; i++) 
				{
					str[i][0]="";
					int n1 = (int) (Math.random() * 3);
					str1[i][0] = "��" + (i + 1) + "����Ŀ:";           //�������
					str[i][0] += fenshuchanshengkuohao(n1 + 2, t1);           //���ڼ���
					str1[i][0] += str[i][0] + " =";
					str[i][1] = fenshujisuan(str[i][0]);
					
					int z = 1;
					for (int j = i - 1; j >= 0; j--) 
					{
						if (str[i][0].equals(str[j][0])) // ���أ���ʱû���Ҫ��
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
						String b1[] = new String[2]; // �����������л���
						int b[] = new int[2];
						b1 = str[i][1].split("\\/");
						b[0] = Integer.parseInt(b1[0]);
						b[1] = Integer.parseInt(b1[1]);
						int t = simple(b[0], b[1]);
						b[0] /= t;
						b[1] /= t;
						int a = (int) (b[0] / b[1]);
						int c = b[0] % b[1];
	
						if (b[0] > b[1]) // �жϽ����ʽ
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
	
	
	//�����������ݿ�
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
		String sql = "insert into text03fenshu(���ʽ,���) values(?,?)";
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
	
	//�����������ݿ�
	public static void lianjiezhengshu(String a[][]) throws ClassNotFoundException, SQLException
    {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String driverName = "com.mysql.jdbc.Driver";
		String userName = "root";
		String userPwd = "123456789";     //���ݿ�����
		String ur11 = "jdbc:mysql://localhost:3306/size03";     //���ݿ���
		String ur12 = "?user=" + userName + "&password=" + userPwd;
		String ur13 = "&useUnicode=true&characterEncoding=UTF-8";
		String ur1 = ur11 + ur12 + ur13;
		Class.forName(driverName);
		conn = DriverManager.getConnection(ur1);
		String sql = "insert into text03zhengshu(���ʽ,���) values(?,?)";        //����
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
	
	//�Ƚ�����Ľ���Ƿ���ȷ
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
		String sql = "select * from text03fenshu where ���ʽ=? and ���=?";
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
		System.out.println("��ȷ����"+numr+"��    �������"+numw+"��");
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
