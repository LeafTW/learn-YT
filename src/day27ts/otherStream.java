package day27ts;

import org.junit.jupiter.api.Test;

import java.io.*;

public class otherStream {
    /*1.标准的输入、输出流
        1.1
        system.in:标准的输入流,默认从键盘输入
        System.out:标准的输出流,默认从控制台输出
        1.2
        System类的setIn(Inputstreamis)/ setout(printstreamps)方式重新指定输入和输出的流。
        1.3练习:
        从键盘输入字符串,要求将读取到的整行字符串转成大写输出。然后继续进行输入操作,
        直至当输入”e”或者“exit#时,退出程序。
        方法一:使用Scanner实现,调用next()返回一个字符串
        方法二:使用System.in实现。System.in---> 转换流->BufferedReader的readLine
     */
    public static void main(String[] args) {
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        try {
            while (true) {
                System.out.println("請輸入：");
                String st = bufferedReader.readLine();
                if ("e".equalsIgnoreCase(st) || "exit".equalsIgnoreCase(st)) {
                    break;
                }
                System.out.println(st.toUpperCase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    public void printStream() {
        //改輸出txt到資料夾
        PrintStream printStream = null;
        try {
            FileOutputStream outputStream = new FileOutputStream(new File("/Volumes/Data/Java/ts.txt"));

            printStream = new PrintStream(outputStream, true);
            if (printStream != null) {
                System.setOut(printStream);
            }
            for (int i = 0; i <= 255; i++) {
                System.out.print((char) i);
                if (i % 50 == 0) {
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            printStream.close();
        }
    }

    @Test
    public void DataOutputStream() {
        try (DataOutputStream outputStream = new DataOutputStream(
                new FileOutputStream("/Volumes/Data/Java/DataInputStream.txt"));) {
            outputStream.writeUTF("王小明");
            outputStream.writeInt(28);
            outputStream.writeBoolean(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void DataInputStream() {
        try (DataInputStream inputStream = new DataInputStream(
                new FileInputStream("/Volumes/Data/Java/DataInputStream.txt"));) {
            //一定要按照DataOutputStream順序來讀
            String name = inputStream.readUTF();
            int age = inputStream.readInt();
            boolean gender = inputStream.readBoolean();
            System.out.println("name: " + name + " age:" + age + " gender: " + gender);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void ObjectOutputStream() {
 /*     对象流的使用
        1.0bjectInputstream和Objectoutputstream
        2.作用:用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可以把java中的
        3.要想一个java对象是可序列化的,需要满足相应的要求。见Person.java
        4.序列化机制:
        对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流,从而允许把这种
        二进制流持久地保存在磁盘上,或通过网络将这种二进制流传输到另一个网络节点。
        当其它程序获取了这种二进制流,就可以恢复成原来的Java对象

        補充：ObjectOutputStream利ObjectInputStream不能序列化static和tansient饰的成员变量

  */
        //序列化
        //序列化類一定要implements Serializable 並 一定要有static final long serialVersionUID = 42L;(這一思是給序列化一個ID避免反序列化時找不到)
        //https://www.youtube.com/watch?v=dLolGw2lp1A&list=PLmOn9nNkQxJH0qBIrtV6otI0Ep4o2q67A&index=613&ab_channel=%E5%B0%9A%E7%A1%85%E8%B0%B7IT%E5%9F%B9%E8%AE%AD%E5%AD%A6%E6%A0%A1
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("/Volumes/Data/Java/ObjectInputStream.dat"));
            oos.writeObject(new Person("Leaf", 20, 'M'));
            oos.writeObject(new Person("tree", 22, 'G', new bankId(88888)));
            oos.writeUTF("hellow java");
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    public void ObjectInputStream() {
        //反序列化
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("/Volumes/Data/Java/ObjectInputStream.dat"));
            Object o = ois.readObject();
            Object o1 = ois.readObject();
            String s = ois.readUTF();
            System.out.println((Person) o);
            System.out.println((Person) o1);
            System.out.println(s);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    public void RandomAccessFile1() {
        /*
        r: 以只读方式打开
        rw:打开以便读取和写入
        rwd:打开以便读取和写入;同步文件内容的更新
        rws:打开以便读取和写入;同步文件内容和元数据的更新
         */
        RandomAccessFile raf = null;
        RandomAccessFile raf1 = null;
        try {
            raf = new RandomAccessFile(new File("/Volumes/Data/UCTP/廖韋喆.mp4"),"rw");
            raf1 = new RandomAccessFile(new File("/Volumes/Data/Java/廖韋喆1.mp4"),"rw");

            byte[] bytes = new byte[1024];
            int end;
            while ((end=raf.read(bytes)) != -1) {
                raf1.write(bytes,0,end);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(raf != null){
                try {
                    raf.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Test
    public void RandomAccessFile2() {
        /*
        RandomAccessFile的使用
        1.RandomAccessFile直接继承于java.Lang.0bject类,实现了DataInput和Data0utput接口
        2.RandomAccessFile既可以作为一个输入流,又可以作为一个输出流
        3.如果RandomAccessFile作为输出流时,写出到的文件如果不存在,则在执行过程中自动创建
            如果写出到的文件存在,则会对原有文件内容进行覆盖。(默认情况下,从头覆盏)
         */
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(new File("/Users/zi-wei/IdeaProjects/JIDEA/src/day27ts/hellow.txt"), "rw");
            //将文件记录指针定位到 pos 位置
            raf.seek(3);
//            raf.seek(raf.length()); //指針到最後
            raf.write("java11".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
        @Test
        public void RandomAccessFile3() {
            RandomAccessFile raf = null;
            try {
                raf = new RandomAccessFile(new File("/Users/zi-wei/IdeaProjects/JIDEA/src/day27ts/hellow.txt"),"rw");
                //将文件记录指针定位到 pos 位置
                raf.seek(6);
//            raf.seek(raf.length()); //指針到最後
                byte[] bytes = new byte[10];
                StringBuffer stringBuffer = new StringBuffer((int) new File("/Users/zi-wei/IdeaProjects/JIDEA/src/day27ts/hellow.txt").length());
                int end;
                while ((end=raf.read(bytes))!=-1){
                stringBuffer.append(new String(bytes,0,end));
                }
                raf.seek(6);
                raf.write("\njava".getBytes());
                raf.write(stringBuffer.toString().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (raf != null) {
                    try {
                        raf.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }


    }

}