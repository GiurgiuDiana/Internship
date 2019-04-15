import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class InternshipEvozon {

    /*
    * Exercise 3:
Create a program that will do CRUD (Create, Read, Update, Delete) operations on a CSV (Coma Separated Values) file.
* This file will contain the products from an online store.
The program must:
- generate a CSV file when it first runs (if the CSV file exists, do not overwrite it)
- have a method to add a row
- have a method to read a row
- have a method to update a row
- have a method to delete a row

The CSV file will have three columns: <ProductName>,  <Price> and <Quantity>
The program does not need to have a GUI (command line interface or just the operations in a sequence is enough)
Can be implemented in any programming language.
    *Programming language chosen: Java
    * Env: IntelliJ IDEA
    * */


    public static void main(String[] args)
    {
        //this is the string that contains the name of the file.
        String fileName="list.csv";
        if(FileMethods.checkIfFileExists(fileName))
        {
            ArrayList<Products>productList= FileMethods.readFromFile(fileName);
            System.out.println("The file contains the following products");
            for(Products prod:productList)
            {
                System.out.println(prod);
            }
            FileMethods.addRow(fileName,"Tv LCD", 678,980);
            FileMethods.deleteRow(fileName,2);
            FileMethods.modifyRow(fileName,1,"Boxe Stereo",90,4);
            Products oneLine= FileMethods.readLine(fileName,1);
            if(oneLine==null)
            {System.out.println("null");}
            System.out.println("The read line"+oneLine);
        }
        else
        {
            System.out.println("File doesn't exist, create file");
            ArrayList<Products> newList= new ArrayList<>();
            Products newProduct= new Products("Name", 1,1);
            newList.add(newProduct);
            try {
                FileMethods.createFile(newList,"createFile.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
