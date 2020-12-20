
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.io.*;

class Front 
{
    public static void main(String args[]) 
    {
        NewWindow nw = new NewWindow();

    }

}

class Window // implements ActionListener
{
    public Window() {
        JFrame f = new JFrame("Marvellous Packer-Unpacker");

        // create content panel, iT is nothing but container object c
        Container c = f.getContentPane();

        // set Background color
        c.setBackground(Color.green);
        

        JLabel l1 = new JLabel("Packing Portal");
        l1.setBounds(1,1,100,60);

        JButton bobj = new JButton("Submit");
        bobj.setBounds(80, 190, 140, 40); // setBounds(int x-coordinate, int y-coordinate, int width, int Height

        JButton bobj1 = new JButton("Previous");
        bobj1.setBounds(250, 190, 140, 40); // setBounds(int x-coordinate, int y-coordinate, int width, int Height

        JLabel lobj1 = new JLabel("Enter Folder Name");
        lobj1.setBounds(10, 20, 100, 100);
        JTextField tf1 = new JTextField();
        tf1.setBounds(100, 50, 130, 30);

        JLabel lobj2 = new JLabel("Enter file name");
        lobj2.setBounds(10, 100, 100, 80);

        JTextField tf2 = new JTextField();
        tf2.setBounds(100, 120, 130, 30); // kuthe thevayche Textfield

        f.add(lobj1);
        f.add(bobj);
        f.add(tf1);
        f.add(lobj2);
        f.add(tf2);
        f.add(bobj1);
        f.add(l1);

        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // here because of this when our window is closed our
                                                          // applicatin also terminate

        bobj.addActionListener(new ActionListener() // submit
        {
            public void actionPerformed(ActionEvent eobj) {
                Packer pobj = new Packer(tf1.getText(), tf2.getText());
                f.setVisible(false);
                NewWindow o = new NewWindow();

            }
        });

        bobj1.addActionListener(new ActionListener() // previous
        {
            public void actionPerformed(ActionEvent eobj) {

                NewWindow o1 = new NewWindow();
                f.setVisible(false);

            }
        });

    }
}

class Window1 // implements ActionListener
{
    public Window1() {
        JFrame f1 = new JFrame("Marvellous Packer-Unpacker");

        // create content panel, iT is nothing but container object c
        Container c1 = f1.getContentPane();

        // set Background color
        c1.setBackground(Color.green);

        JLabel l2 = new JLabel("UnPacking Portal");
        l2.setBounds(3,3,100,100);

        JButton bobj = new JButton("Extract Here");
        bobj.setBounds(80, 190, 140, 40); // setBounds(int x-coordinate, int y-coordinate, int width, int Height

        JButton bobj1 = new JButton("Previous");
        bobj1.setBounds(230, 190, 140, 40); // setBounds(int x-coordinate, int y-coordinate, int width, int Height

        JLabel lobj2 = new JLabel("Enter file name");
        lobj2.setBounds(10, 100, 100, 80);

        JTextField tf2 = new JTextField();
        tf2.setBounds(100, 120, 130, 30); // kuthe thevayche Textfield

        f1.add(bobj);
        f1.add(lobj2);
        f1.add(tf2);
        f1.add(bobj1);
        f1.add(l2);

        f1.setSize(500, 500);
        f1.setLayout(null);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // here because of this when our window is closed our
                                                           // applicatin also terminate

        bobj.addActionListener(new ActionListener() // for extract here
        {
            public void actionPerformed(ActionEvent eobj) 
            {
                Unpacker pobj = new Unpacker(tf2.getText());
                f1.setVisible(false);
                NewWindow o = new NewWindow();

            }
        });

        bobj1.addActionListener(new ActionListener() // for previous
        {
            public void actionPerformed(ActionEvent eobj) 
            {
                NewWindow o1 = new NewWindow();
                f1.setVisible(false);

            }
        });

    }
}

class NewWindow {
    public NewWindow() {

        JFrame fobj = new JFrame("Marvellous Packer-Unpacker");

        Container c1 = fobj.getContentPane();

        // set Background color
        c1.setBackground(Color.green);

        JLabel jl = new JLabel("Welcome-Marvellous Admin Page");
        jl.setBounds(5,5,100,100);

        JButton jb1 = new JButton("Pack");
        jb1.setBounds(40, 80, 80, 40);

        JButton jb2 = new JButton("Unpack");
        jb2.setBounds(150, 80, 80, 40);

        JButton jb3=new JButton("Exit");
        jb3.setBounds(290,80,80,40);

        fobj.add(jl);
        fobj.add(jb1);
        fobj.add(jb2);
        fobj.add(jb3);

        fobj.setSize(500, 500);
        fobj.setLayout(null);
        fobj.setVisible(true);
        fobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eobj) 
            {
                Window w = new Window();
                fobj.setVisible(false);

            }
        });

        jb2.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent eobj) 
            {

                Window1 w = new Window1();
                fobj.setVisible(false);

            }
        });

        jb3.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent eobj) 
            {

               System.exit(0);
            }
        });



    }
}

class Packer {
    // object reference for file writing
    public FileOutputStream outstream = null; // chracteristic which is accessible in whole class

    public Packer(String FolderName, String FileName) {
        try {
            System.out.println("Inside Packer constructor");
            // Creat new file for packing
            File outfile = new File(FileName);
            outstream = new FileOutputStream(FileName); // file creat jhali by using Fileoutputstream

            // set the current working directory for folder traversal
            System.setProperty("user.dir", FolderName); // set the Demo folder for serching purpose

            TravelDirectory(FolderName); // this function is used to travel the directory

        } catch (Exception obj) {
            System.out.println(obj);
        }

    }

    public void TravelDirectory(String path) {
        File directoryPath = new File(path);

        // get all file names from directory
        File arr[] = directoryPath.listFiles(); // it list out all files

        for (File filename : arr) {
            // System.out.println(filename.getName()); // it gives names of all files
            // System.out.println(filename.getAbsolutePath()); // it gives path of all files
            if (filename.getName().endsWith(".txt")) // it is same like ==".txt"
            {
                PackFile(filename.getAbsolutePath());
            }

        }

    }

    public void PackFile(String FilePath) {
        System.out.println("File name reaceived " + FilePath);
        byte Header[] = new byte[100]; // it creates byte array
        byte Buffer[] = new byte[1024];
        int length = 0;

        FileInputStream istream = null;

        File fobj = null;
        fobj = new File(FilePath);
        // name // length
        String temp = FilePath + " " + fobj.length(); // length method gives the length of the file

        // create header of 100 bytes
        for (int i = temp.length(); i < 100; i++) // i=82
        {
            temp = temp + " "; // here we concate or add spaces to make our file 100 bytes
        }

        Header = temp.getBytes(); // to convert a header into byte array from string
        try {
            // open the file for reading
            istream = new FileInputStream(FilePath);
            // byte[] , offset or position , no of bytes
            outstream.write(Header, 0, Header.length); // this write method writes Heder.length bytes from byte array to
                                                       // the fileoutputstream
            while ((length = istream.read(Buffer)) > 0) {
                outstream.write(Buffer, 0, length);
            }

            istream.close();
        } catch (Exception obj) {
        }
        // System.out.println("Header : "+temp.length());

    }

}

class Unpacker {
    public FileOutputStream outstream = null;
    int counter = 0;

    public Unpacker(String src) {
        System.out.println("Inside unpacker");
        unpackFile(src);
    }

    public void unpackFile(String FilePath) {
        try {
            FileInputStream instream = new FileInputStream(FilePath);
            byte Header[] = new byte[100];
            int length = 0;
            int counter = 0;

            while ((length = instream.read(Header, 0, 100)) > 0) // to read 100 bytes
            {
                String str = new String(Header); // to convert int int byte data into string format
                // C:\asdas\asdasd\asdas\demo.txt 45
                String ext = str.substring(str.lastIndexOf("\\")); // we get upto this // C:\asdas\asdasd\asdas\\

                ext = ext.substring(1);

                String words[] = ext.split("\\s"); // \\s is deliminator make a tokens of string when white space is get
                String name = words[0]; // demo.txt // name of file

                int size = Integer.parseInt(words[1]); // 45 // length of file

                byte arr[] = new byte[size];
                instream.read(arr, 0, size);
                // New file gets created
                FileOutputStream fout = new FileOutputStream(name);
                // write the data into newly created file
                fout.write(arr, 0, size);
                counter++;

            }

            System.out.println("__________________________________________________");
            System.out.println("Sucessfully unpacked files : " + counter);
            System.out.println("__________________________________________________");
        } catch (Exception obj) {

        }
    }

}