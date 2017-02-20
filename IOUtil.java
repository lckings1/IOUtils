//�ҵ�github:https://github.com/ygj0930
//�ҵĲ��ͣ�http://www.cnblogs.com/ygj0930/

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
	
	
	//�ļ�������Ҫ����������FileInputStream��FileOutputStream
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
	
	//�ļ�������Ҫ�õ�һ���ࣺRandomAccessFile
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
		
		rf.seek(0);//���ܵ�ʵ���ǣ�������ȫ������������һ�������޸ģ�Ȼ���ͷд���ĵ�����ԭ������
		
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
		
		rf.seek(0);//���ܵ�ʵ���ǣ�������ȫ������������һ�������޸ģ�Ȼ���ͷд���ĵ�����ԭ������
		
		rf.write(b);
		rf.close();
		
		}catch(IOException ex)
		{
			System.out.println(ex);
		}
	}
	
	//��ȡ�����ı���Ҫ�õ�BufferedReader�࣬�Լ�����readLine��������,ÿreadLineһ�εõ�һ���ַ��������о�ѭ����ȡ���Ž��ַ���������
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
	
	//д������ı���Ҫ�õ�BufferedWriter���Լ�write������ÿwriteһ��д��һ�У���newLine���С����ѭ����ʵ�ֶ���д��
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
	//׷������Ҫ�ã�RandomAccessFile�Լ�seek��write����
	public static void fileContentAppend(String text,String srcFile) throws IOException
	{
		RandomAccessFile rf=new RandomAccessFile(srcFile,"rw");
		rf.seek(rf.length());
		byte[] arr=text.getBytes();
		rf.write(arr);//rfֻ��дint��byte[]����
		rf.close();
	}
	public static void fileContentPrepend(String text,String srcFile) throws IOException
	{
		//�Ȱ��ļ����ݶ������Ž�һ���㹻���byte������
		File f=new File(srcFile);
		FileInputStream fis=new FileInputStream(f);
		byte[] arr=new byte[(int)f.length()];
		fis.read(arr);
		//д�뿪ͷ���ٰ�ԭ��������д��
		RandomAccessFile rf=new RandomAccessFile(srcFile,"rw");
		rf.seek(0);
		rf.write(text.getBytes());
		rf.seek(rf.length());
		rf.write(arr);
		
	}
	
	public static void fileContentCopy(String srcFile) throws IOException
	{
		//�Ȱ��ļ����ݶ������Ž�һ���㹻���byte������
		File f=new File(srcFile);
		FileInputStream fis=new FileInputStream(f);
		byte[] arr=new byte[(int)f.length()];
		fis.read(arr);
		
		RandomAccessFile rf=new RandomAccessFile(srcFile,"rw");
		rf.seek(rf.length());
		rf.write(arr);
	}
	//�滻��Ҫ�õ�FileReader�������ַ�����������Ϊ��Ҫ�ж���һ�����ַ�
	public static void fileContentReplace(String srcFile,String oldContent,String newContent) throws IOException
	{
		    FileReader fis = new FileReader(srcFile);// �����ļ�������
            char[] data = new char[1024];// ���������ַ�����
            int rn = 0;
            StringBuilder sb = new StringBuilder();// �����ַ���������
            while ((rn = fis.read(data)) > 0) {// ��ȡ�ļ����ݵ��ַ���������
                String str = String.valueOf(data, 0, rn);
                sb.append(str);
            }
            fis.close();// �ر�������
            // �ӹ������������ַ��������滻�����ı�
            String str = sb.toString().replace(oldContent, newContent);
            FileWriter fout = new FileWriter(srcFile);// �����ļ������
            fout.write(str.toCharArray());// ���滻��ɵ��ַ���д���ļ���
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
   System.out.println("ɾ���ɹ���");
	}
		
	
}