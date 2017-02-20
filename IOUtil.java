//我的github:https://github.com/ygj0930
//我的博客：http://www.cnblogs.com/ygj0930/

import java.io.*;
import java.util.Vector;
import java.util.*;

public class IOUtil
{
	public static void main(String[] args)
	{
		
		try{
			fileDelete("adc");
		
		}catch(IOException ex)
		{
			System.out.println(ex);
		}
		
	}
	
	
	//文件复制需要用两个流：FileInputStream，FileOutputStream
	public static void fileCopy(String srcFile,String tarFile)
	{
		File src=new File(srcFile);
		File tar=new File(tarFile);
	try
		{	
		FileInputStream fis=new FileInputStream(src);
		FileOutputStream fos=new FileOutputStream(tar);
		
		byte[] temp=new byte[(int)src.length()];
		while((fis.read(temp))!=-1)
		{
			fos.write(temp);
		}
		
			if((fis!=null)||(fos!=null))
			{
				fis.close();
				fos.close();
			}
		}catch(IOException ex)
		{
			System.out.println(ex);
		}
	}
	
	//文件加密需要用到一个类：RandomAccessFile
	public static void fileEncrypt(String srcFile)
	{
		try{
			File f=new File(srcFile);
			RandomAccessFile rf=new RandomAccessFile(f,"rw");
			byte[] b=new byte[(int)f.length()];
			
			rf.read(b);
			
		for(int i=0;i<b.length;i++)
		{
			b[i]=(byte)(b[i]+12);
		}
		
		rf.seek(0);//加密的实质是：把内容全部读出来，按一定规则修改，然后从头写入文档覆盖原来内容
		
		rf.write(b);
		rf.close();
		
		}catch(IOException ex)
		{
			System.out.println(ex);
		}
	}
	
	public static void fileDecrypt(String srcFile)
	{
		try{
			File f=new File(srcFile);
			RandomAccessFile rf=new RandomAccessFile(f,"rw");
			byte[] b=new byte[(int)f.length()];
			
			rf.read(b);
			
		for(int i=0;i<b.length;i++)
		{
			b[i]=(byte)(b[i]-12);
		}
		
		rf.seek(0);//解密的实质是：把内容全部读出来，按一定规则修改，然后从头写入文档覆盖原来内容
		
		rf.write(b);
		rf.close();
		
		}catch(IOException ex)
		{
			System.out.println(ex);
		}
	}
	
	//读取多行文本主要用到BufferedReader类，以及它的readLine（）方法,每readLine一次得到一个字符串，多行就循环读取，放进字符串数组中
	public static String[] fileReadMultiLine(String srcFile) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File(srcFile)));
		Vector<String> arr=new Vector<String>();
		String temp;
		while((temp=br.readLine())!=null)
		{
			arr.add(temp);
		}
		String[] arrs=new String[arr.size()];
		arr.toArray(arrs);
		return arrs;
		
	}
	
	//写入多行文本需要用到BufferedWriter类以及write方法。每write一次写入一行，用newLine换行。多出循环即实现多行写入
	public static void fileWriteMultiLine(String[] textArray,String tarFile) throws IOException
	{
		File tar=new File(tarFile);
		BufferedWriter bw=new BufferedWriter(new FileWriter(tar));
		
		for(int i=0;i<textArray.length;++i)
		{
			bw.write(textArray[i]);
			bw.newLine();
			
		}
		bw.close();
		
	}
	//追加内容要用：RandomAccessFile以及seek，write方法
	public static void fileContentAppend(String text,String srcFile) throws IOException
	{
		RandomAccessFile rf=new RandomAccessFile(srcFile,"rw");
		rf.seek(rf.length());
		byte[] arr=text.getBytes();
		rf.write(arr);//rf只能写int或byte[]类型
		rf.close();
	}
	public static void fileContentPrepend(String text,String srcFile) throws IOException
	{
		//先把文件内容读出来放进一个足够大的byte数组中
		File f=new File(srcFile);
		FileInputStream fis=new FileInputStream(f);
		byte[] arr=new byte[(int)f.length()];
		fis.read(arr);
		//写入开头，再把原来的重新写入
		RandomAccessFile rf=new RandomAccessFile(srcFile,"rw");
		rf.seek(0);
		rf.write(text.getBytes());
		rf.seek(rf.length());
		rf.write(arr);
		
	}
	
	public static void fileContentCopy(String srcFile) throws IOException
	{
		//先把文件内容读出来放进一个足够大的byte数组中
		File f=new File(srcFile);
		FileInputStream fis=new FileInputStream(f);
		byte[] arr=new byte[(int)f.length()];
		fis.read(arr);
		
		RandomAccessFile rf=new RandomAccessFile(srcFile,"rw");
		rf.seek(rf.length());
		rf.write(arr);
	}
	//替换需要用到FileReader对象，用字符流来处理，因为需要判断哪一处的字符
	public static void fileContentReplace(String srcFile,String oldContent,String newContent) throws IOException
	{
		    FileReader fis = new FileReader(srcFile);// 创建文件输入流
            char[] data = new char[1024];// 创建缓冲字符数组
            int rn = 0;
            StringBuilder sb = new StringBuilder();// 创建字符串构建器
            while ((rn = fis.read(data)) > 0) {// 读取文件内容到字符串构建器
                String str = String.valueOf(data, 0, rn);
                sb.append(str);
            }
            fis.close();// 关闭输入流
            // 从构建器中生成字符串，并替换搜索文本
            String str = sb.toString().replace(oldContent, newContent);
            FileWriter fout = new FileWriter(srcFile);// 创建文件输出流
            fout.write(str.toCharArray());// 把替换完成的字符串写入文件内
            fout.close();
		
		
		
	}
	
	public static void fileDelete(String srcFile) throws IOException
	{
		File file=new File(srcFile);
		if(file.exists()){ 
            if(file.isFile()){ 
               file.delete(); 
          }else if(file.isDirectory()){ 
            File[] files = file.listFiles(); 
            for(int i=0;i<files.length;i++){ 
             fileDelete(files[i].getName()); 
         } 
        } 
   }
   System.out.println("删除成功！");
	}
		
	
}