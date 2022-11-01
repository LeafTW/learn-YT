package day27ts;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Date;

public class filets {

    @Test
    public void file_method() {
        File file = new File("hellow.txt");
        File file1 = new File("//Users//zi-wei//IdeaProjects//JIDEA//src//day23ts//hellow.txt");
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(new Date(file.lastModified()));
        System.out.println();

        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(file1.lastModified());
    }

    @Test
    public void file_list() {
        File file = new File("/Volumes/Data/Java/java總整理");

        String[] list = file.list();
        for (String s : list) {
            System.out.println(s);
        }

        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1);
        }
    }

    @Test
    public void data_copy() {
        //內容複製
//        File file1=new File("//Users//zi-wei//IdeaProjects//JIDEA//src//day23ts//hellow.txt");
        File file2 = new File("/Volumes/Data/Java/ts.txt");
        File file3 = new File("/Volumes/Data/idea/ss.txt");
        System.out.println(file2.length());
        boolean b = file2.renameTo(file3);
        System.out.println(b);


    }

    @Test
    public void file_txt_method() {
        File file = new File("/Users/zi-wei/IdeaProjects/JIDEA/hellow java.txt");
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println(file.exists());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        System.out.println(file.isHidden());
        System.out.println();

        File file1 = new File("//Volumes//Data//Java");
        System.out.println(file.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());
    }

    @Test
    public void file_create() throws IOException {
        File file = new File("/Volumes/Data/Java/ts.txt");

        if (!file.exists()) {
            file.createNewFile();
            System.out.println("創建成功");
        } else {
            file.delete();
            System.out.println("刪除成功");
        }
    }

    @Test
    public void readtest1() {
        File file = new File("/Volumes/Data/Java/ts.txt");

        FileReader reader = null;
        try {
            reader = new FileReader(file);
            int data;
            while ((data = reader.read()) != -1) {
                System.out.println((char) data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Test
    public void readtest2() {
        //https://www.youtube.com/watch?v=s_nstY31DPM&list=PLmOn9nNkQxJH0qBIrtV6otI0Ep4o2q67A&index=587&ab_channel=%E5%B0%9A%E7%A1%85%E8%B0%B7IT%E5%9F%B9%E8%AE%AD%E5%AD%A6%E6%A0%A1
//        File file = new File("/Volumes/Data/Java/ts.txt");
        File file = new File("hellow java.txt");
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[5];//每次查5個
            int len;//read(char[] cbuf):返回每次读入cbuf数组中的字符的个数。如果达到文件末尾,返回1
            while ((len = reader.read(chars)) != -1) {  //讀完會傳-1
                String s = new String(chars, 0, len);
                System.out.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void writetest1() {
/*
    File对应的硬盘中的文件如果不存在,在输出的过程中,会自动创建此文件。

    File对应的硬盘中的文件如果存在:
        如果流使用的构造器是:Filewriter(file,false)/ Filewriter(file):对原有文件的覆盖
        如果流使用的构造器是:Filewriter(file,true:不会对原有文件覆益,而是在原有文件基础上追加内容
*/
        File file1 = new File("/Volumes/Data/Java/ts.txt");
        File file = new File("hellow java.txt");

        try (FileReader reader = new FileReader(file);
             FileWriter writer = new FileWriter(file1);) {
            char[] chars = new char[5];
            int end;
            while ((end = reader.read(chars)) != -1) {
                writer.write(chars, 0, end);
                String s = new String(chars, 0, end);
                System.out.print(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void fileINstream() {
        File file = new File("/Volumes/Data/UCTP/網路資料庫設計實務-簡報.pptx");
        File file1 = new File("/Volumes/Data/Java/網路資料庫簡報.pptx");
        long stimeMillis = System.currentTimeMillis();
        try (FileInputStream inputStream = new FileInputStream(file);
             FileOutputStream outputStream = new FileOutputStream(file1);) {
            byte[] bytes = new byte[50];
            int end;
            while ((end = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, end);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long etimeMillis = System.currentTimeMillis();
        System.out.println(etimeMillis - stimeMillis); //188

    }

    @Test
    public void bufferStream() {
        File file = new File("/Volumes/Data/UCTP/網路資料庫設計實務-簡報.pptx");
        File file1 = new File("/Volumes/Data/Java/網路資料庫簡報.pptx");

        long s = System.currentTimeMillis();
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            FileOutputStream outputStream = new FileOutputStream(file1);

            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);

            byte[] bytes = new byte[50];
            int end;
            while ((end = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, end);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    //關閉 BufferedStream /
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        long e = System.currentTimeMillis();
        System.out.println(e - s);
    }

    @Test
    public void buffer() {
        //沒有使用Buffer:23，使用Buffer:9
        File file = new File("/Volumes/Data/UCTP/java 考題.txt");
        File file1 = new File("/Volumes/Data/Java/java 考題.txt");
        long s = System.currentTimeMillis();
        try (FileReader reader = new FileReader(file1);
             FileWriter write = new FileWriter(file);

             BufferedReader bufferedReader = new BufferedReader(reader);
             BufferedWriter bufferedWriter = new BufferedWriter(write);) {
            /*
            //  方法1
            char[] chars = new char[10];
            int end;
            while ((end = bufferedReader.read(chars)) != -1) {
                bufferedWriter.write(chars, 0, end);
            }
            */
            //方法2
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long e = System.currentTimeMillis();
        System.out.println(e - s);
    }

    @Test
    public void InputStreamReader_OutputStreamWriter() {
        File file = new File("/Volumes/Data/UCTP/java 考題.txt");
        File file1 = new File("/Volumes/Data/Java/java 考題.txt");

        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            FileInputStream inputStream = new FileInputStream(file1);
            FileOutputStream outputStream = new FileOutputStream(file);

            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            outputStreamWriter = new OutputStreamWriter(outputStream, "gbk");
            char[] chars = new char[10];
            int end;
            while ((end = inputStreamReader.read(chars)) != -1) {
                String s = new String(chars, 0, end);
                System.out.print(s);
                outputStreamWriter.write(chars, 0, end);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(inputStreamReader != null){
                try {
                    inputStreamReader.close();
                    outputStreamWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }


}
