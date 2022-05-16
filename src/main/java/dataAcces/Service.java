package dataAcces;

import model.User;

import java.io.*;

public class Service implements Serializable{
    public Service() {
        super();
    }

    public static boolean writeObjectInFile(Object obj, String file) {
        try{
            FileOutputStream outputFile = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);

            outputStream.writeObject(obj);
            outputStream.close();
            outputFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static Object readObjectFromFile(String file) {
        Object obj = null;
        try{
            File newFile = new File(file);
            FileInputStream inputFile;

            if(newFile.exists()) {
                inputFile = new FileInputStream(newFile);
                ObjectInputStream inputStream = new ObjectInputStream(inputFile);
                obj = inputStream.readObject(); //aici

                inputStream.close();
                inputFile.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
