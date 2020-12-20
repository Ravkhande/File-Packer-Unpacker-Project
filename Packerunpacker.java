
import java.lang.*;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class Packerunpacker {
    public static void main(String[] args) {
        Scanner sobj = new Scanner(System.in);

        System.out.println("Packer-Unpacker");
        while (true) {
            System.out.println("__________________________________");
            System.out.println("1 : Packing");
            System.out.println("2 : Unpacking");
            System.out.println("3 : Exit");
            System.out.println("__________________________________");

            System.out.println("Enter your choice");
            int choice = sobj.nextInt();

            switch (choice) {
                case 1:
                    try {

                        System.out.println("Enter the folder name:");
                        String Dir = sobj.next();

                        System.out.println("Enter the Destination file name:");
                        String Filename = sobj.next();

                        Pack pobj = new Pack(Dir, Filename);
                    } catch (Exception obj) {
                        System.out.println(obj);
                    }
                    break;

                case 2:
                    System.out.println("Enter the file name for unpack");
                    String FileName = sobj.next();
                    Unpack uobj = new Unpack(FileName);
                    break;

                case 3:
                    System.out.println("____________________________________________");
                    System.out.println("Thank you for using Packer-Unpacker");
                    System.out.println("____________________________________________");
                    System.exit(0);
                    break;
            }

        }

    }
}

class Pack {
    int counter = 0;
    public FileOutputStream outstream = null;

    public Pack(String Foldername, String Filename) throws Exception {

        System.out.println("Inside Packer");

        File fobj = new File(Filename);
        outstream = new FileOutputStream(Filename);

        TravelDirectory(Foldername);
    }

    public void TravelDirectory(String FolderName) throws Exception {
        File Directory = new File(FolderName);

        File arr[] = Directory.listFiles();

        for (File FilePath : arr) {
            if (FilePath.getName().endsWith(".txt")) {
                counter++;
                System.out.println("_______________________________________");
                System.out.println("File packed is :" + FilePath.getName());
                System.out.println("_______________________________________");
                Packing(FilePath.getAbsolutePath());
            }
        }

        System.out.println("__________________________________________");
        System.out.println("Number of packed files are :" + counter);
        System.out.println("__________________________________________");
    }

    public void Packing(String FileName) throws Exception {
        int length = 0;
        byte Header[] = new byte[100];
        byte Data[] = new byte[1024];

        File fobj = new File(FileName);
        String temp = FileName + " " + fobj.length();

        for (int i = temp.length(); i < 100; i++) {
            temp = temp + " ";
        }

        Header = temp.getBytes();

        FileInputStream instream = new FileInputStream(FileName);
        outstream.write(Header, 0, Header.length);

        while ((length = instream.read(Data)) > 0) {
            outstream.write(Data, 0, length);
        }

        instream.close();
    }

}

class Unpack {
    public FileOutputStream outstream = null;
    int counter = 0;

    public Unpack(String src) {

        System.out.println("Inside Unpacker");
        System.out.println();
        Unpacking(src);
    }

    public void Unpacking(String Filename) {
        try {

            FileInputStream instream = new FileInputStream(Filename);
            byte Header[] = new byte[100];
            int length = 0;

            while ((length = instream.read(Header, 0, 100)) > 0) {

                String str = new String(Header);

                String ext = str.substring(str.lastIndexOf("\\"));

                ext = ext.substring(1);

                String arr[] = ext.split("\\s");
                String name = arr[0];

                int size = Integer.parseInt(arr[1]);

                byte arr1[] = new byte[size];

                instream.read(arr1, 0, size);

                outstream = new FileOutputStream(name);

                outstream.write(arr1, 0, size);
                counter++;

            }

            System.out.println("____________________________________________");
            System.out.println("Sucessfully Unpacked file is : " + counter);
            System.out.println("____________________________________________");

        } catch (Exception obj) {

        }

    }
}