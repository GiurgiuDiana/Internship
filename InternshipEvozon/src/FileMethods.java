import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileMethods {
    //this class contains all the methods that handle the file input and output
    //file to be read list.csv it contains a list of tv's their price and the quantity
    //reading the file will be done with the class BufferedReader
    //reading will be done line by line and it will memorise all the products in an array list.
    public static ArrayList<Products> readFromFile(String fileName) {
        ArrayList<Products> productList = new ArrayList<Products>();
        Path filePath = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII)) {
            //we read the file one line by one
            String oneLine= reader.readLine();
            //chek if the we reach the end of file-> the line is null
            while(oneLine !=null)
            {
                String[] columns=oneLine.split(",");//the products' attributes are separated by comma
                Products prod= new Products(columns[0],Integer.parseInt(columns[1]),Integer.parseInt(columns[2]));//create new product object
                productList.add(prod);//add the product to the list
                oneLine= reader.readLine();//read next line
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return productList;
    }
    // for creating a file we use BufferedWriter class.
    //we iterate through all the products and add the line by line in the file.
    public static void createFile(ArrayList<Products>productList, String fileName) throws IOException
    {
        // File file= new File(fileName);
        FileWriter output= new FileWriter(fileName);
        BufferedWriter write= new BufferedWriter(output);
        for(Products prod: productList)
        {
            write.write(prod.getProductName());
            write.write(",");
            write.write(String.valueOf(prod.getPrice()));
            write.write(",");
            write.write(String.valueOf(prod.getQuantity()));
            write.write("\n");
        }
        write.flush();//we flush to make sure there is no data left in the buffer
        write.close();//we close the connexion between the program and the file.

    }

   //method to update an object in the array list
    public static ArrayList<Products> updateList(ArrayList<Products>listProduct, Products oldProduct, Products newProduct) throws IOException
    {
        for(Products prod:listProduct)
        {
            if(prod.equals(oldProduct))
            {
                oldProduct.setProductName(newProduct.getProductName());
                oldProduct.setPrice(newProduct.getPrice());
                oldProduct.setQuantity(newProduct.getQuantity());
            }
        }
        return listProduct;
    }
    //method to read one line from the file and to return it as a product
    public static Products readLine(String fileName, int lineNumber)
    {
        Products prod = null;
        Path filePath = Paths.get(fileName);
        int count=0;
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII)) {
            //we read the file one line by one
            String oneLine= reader.readLine();
            while(oneLine !=null)
            {
                //check to see if the index has reached the specified row
                if(count==lineNumber)
                {
                    //if yes, return the product
                    String[] columns=oneLine.split(",");//the products attributed are separated by comma
                   return prod = new Products(columns[0],Integer.parseInt(columns[1]),Integer.parseInt(columns[2]));//create new products
                }
                else{
                    //if no we goo froward
                    count++;
                    oneLine = reader.readLine();//read next line
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prod;

    }
    //method to see if a file exists
    public static boolean checkIfFileExists(String fileName)
    {
        Path filePath = Paths.get(fileName);
        File tempFile = new File(String.valueOf(filePath));
        boolean exists = tempFile.exists();
        return exists;
    }
    //method to add a row to the file, requires the file's name & the attributes of the new product
    //creates a new file with the added row
    public static void addRow(String fileName,String productName,int price, int quantity)
    {
        //creates  new product to add all the attributes to it
        Products prodToBeAdded= new Products(productName,price,quantity);
        //obtain the original product list
        ArrayList<Products> productList= readFromFile(fileName);
        //add the product
        productList.add(prodToBeAdded);
        //addProduct(productList,prodToBeAdded);
        try {
            //create new file
            createFile(productList,"addRow.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //method to modify a row, requires the file's name the number of the row we want to modify, and the new product attributes
    public static void modifyRow(String fileName,int numberRow,String productName,int price, int quantity)
    {
        Products newProduct= new Products(productName,price,quantity);
        ArrayList<Products> productList= readFromFile(fileName);
        Products oldProduct=productList.get(numberRow);//we obtain the product we wish to modify, the index in the array list is the same as the index in the file, we assume both start at 0
        ArrayList<Products> resultList = null;
        try {
            resultList=updateList(productList,oldProduct,newProduct);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            createFile(resultList,"updatesRow.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //method to delete a row requires the files'name and the index of the row we wish to delete
    public static void deleteRow(String fileName, int numberRow)
    {
        ArrayList<Products> productList= readFromFile(fileName);
        Products deleteProduct=productList.get(numberRow);
        productList.remove(numberRow);
       // ArrayList<Products> resultList=deleteProduct(productList,deleteProduct);
        try{
            createFile(productList,"deleteRow.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
