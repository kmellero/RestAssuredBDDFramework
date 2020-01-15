package serializationDeserialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Test implements Serializable{
	
	int i=10,j=20;
}
public class SerializationDeserialization {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Test t1=new Test();
		
		//serialization; convert t1 to object file
		FileOutputStream fos = new FileOutputStream("test.txt"); //write file into memory
		ObjectOutputStream oos = new ObjectOutputStream(fos);	 //convert object into file format
		
		oos.writeObject(t1);  //now write object into file; test.txt contains binary data after writing
		
		//deserialization; convert file content (binary) back into object. Read the file content.
		FileInputStream fis = new FileInputStream("test.txt");
	    ObjectInputStream ois = new ObjectInputStream(fis);

	    Test t2 = (Test) ois.readObject();
	    System.out.println("i="+t2.i + ", j="+t2.j);
	}

}
