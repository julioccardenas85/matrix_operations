package server;
import java.io.*;
import java.net.*;
import java.net.Socket;
import server.operations.ServerOperations;
import server.sockets.ServerSockets;

public class Server {

	   ServerSocket server;
	   Socket ns;
	   DataOutputStream out;
	   DataInputStream in;
	   int operation;
	   int type;
	   int lines;
	   int columns;
	   int lines2;
	   int columns2;
	   float greatest;
	   int vector;
	   boolean isEqual;
	   ServerOperations myOperations = new ServerOperations ();

	  public void listenSocket(){
	    try{
	      server = new ServerSocket(2004, 10); 
	      ns = server.accept();
	      in = new DataInputStream(ns.getInputStream());
	      out = new DataOutputStream(ns.getOutputStream());
	      ServerSockets mySockets = new ServerSockets();

	      do {
		      System.out.println("Leyendo operación a realizar del cliente...\n");
		      operation = in.readInt();
		      switch (operation) {
		      	case 1:
		      		System.out.println("El cliente ha elegido la opción de multiplicación. Leyendo el tipo de arreglos...\n");
		      		type = in.readInt();
		      		if (type == 1) {
		      			System.out.println("El cliente ha elegido multiplicación de matrices. Leyendo tamaño de matrices...\n");
		      			lines = in.readInt();
		      			System.out.println("Líneas de la primera matriz: " + lines);
		      			columns = in.readInt();
		      			System.out.println("Columnas de la primera matriz: " + columns + "\n");
		      			float m1 [][] = new float [lines][columns];
		      			lines2 = in.readInt();
		      			System.out.println("Líneas de la segunda matriz: " + lines2);
		      			columns2 = in.readInt();
		      			System.out.println("Columnas de la segunda matriz: " + columns2 + "\n");
		      			float m2 [][] = new float [lines2][columns2];
		      			System.out.println("Primera matriz");
		      			mySockets.getMatrix(m1, in);
		      			System.out.println("Segunda matriz");
		      			mySockets.getMatrix(m2, in);
		      			float r[][] = new float [lines][columns2];
		      			myOperations.multiply(m1, m2, r);
		      			System.out.println("El resultado es:");
		      			myOperations.print(r);
		      			mySockets.sendMatrix(r, out);
		      		}
		      		if (type == 2) {
		      			System.out.println("El cliente ha elegido multiplicación de matríz por vector. Leyendo tamaño de los arreglos...\n");
		      			lines = in.readInt();
		      			System.out.println("Líneas de la matriz: " + lines);
		      			columns = in.readInt();
		      			System.out.println("Columnas de la matriz: " + columns + "\n");
		      			float m3 [][] = new float [lines][columns];
		      			vector = columns;
		      			System.out.println("Tamaño del vector: " + vector + "\n");
		      			float v [] = new float [vector];
		      			mySockets.getMatrix(m3, in);
		      			mySockets.getVector(v, in);
		      			float r [] = new float [vector];
		      			myOperations.multiply(m3, v, r);
		      			System.out.println("El resultado es:");
		      			myOperations.print(r);
		      			mySockets.sendVector(r, out);
		      			System.out.println("Enviando resultado a cliente...\n");
		      		}
		      		break;
		      	case 2:
		      		System.out.println("El cliente ha elegido la opción de suma. Leyendo tipo de arreglo...\n");
		      		type = in.readInt();
		      		if (type == 1) {
		      			System.out.println("El cliente ha elegido la opción de suma de matrices. Leyendo tamaño de matrices...\n");
			      		lines = in.readInt();
						System.out.println("Líneas: " + lines);
						columns = in.readInt();
						System.out.println("Columnas: " + columns + "\n");
						float m3[][] = new float [lines][columns];
						System.out.println("Primera matriz");
						mySockets.getMatrix(m3, in);
						float m4[][] = new float [lines][columns];
						System.out.println("Segunda matriz");
						mySockets.getMatrix(m4, in);
						float r2[][] = new float [lines][columns];
						myOperations.addition(m3, m4, r2);
						System.out.println("El resultado es:");
						myOperations.print(r2);
						mySockets.sendMatrix(r2, out);
						System.out.println("Enviando resultado al cliente...\n");
						break;
		      		}
		      		if (type == 2) {
		      			System.out.println("El cliente ha elegido suma de vectores. Leyendo tamaño de vectores...\n");
		      			vector = in.readInt();
		      			System.out.println("El tamaño de los vectores es: " + vector + ".\n\nPrimer vector");
		      			float v2 [] = new float [vector];
		      			mySockets.getVector(v2, in);
		      			System.out.println("Segundo vector");
		      			float v3 [] = new float [vector];
		      			mySockets.getVector(v3, in);
		      			float r2 [] = new float [vector];
		      			myOperations.addition(v2, v3, r2);
		      			System.out.println("El resultado de la suma es:");
		      			myOperations.print(r2);
		      			mySockets.sendVector(r2, out);
		      			System.out.println("Enviando resultado a cliente.\n");
		      			break;
		      		}
		      	case 3:
		      		System.out.println("El cliente ha elegido la opción de encontrar el mayor valor. Leyendo el tipo de arreglo que se evaluará...\n");
		      		type = in.readInt();
		      		if (type == 1) {
		      			System.out.println("El cliente ha elegido encontrar el número mayor de una matriz. Leyendo el tamaño de la matriz...\n");
		      			lines = in.readInt();
		      			System.out.println("Líneas: " + lines);
		      			columns = in.readInt();
		      			System.out.println("Columnas: " + columns + "\n");
		      			float m5 [][] = new float [lines][columns];	
		      			mySockets.getMatrix(m5, in);
		      			greatest = myOperations.greatest(m5);
		      			System.out.println("El número mayor de la matriz es: " + greatest + "\n");
		      			System.out.println("Enviando a socket...\n");
		      			out.flush();
		      			out.writeFloat(greatest);
		      	      	out.flush();
		      		}
		      		if (type == 2) {
		      			System.out.println("El cliente ha elegido la opción de encontrar el mayor valor de un vector. Leyendo el tamaño del vector...\n");
		      			vector = in.readInt();
		      			float v3 [] = new float [vector];
		      			mySockets.getVector(v3, in);
		      			greatest = myOperations.greatest(v3);
		      			System.out.println("El número mayor del vector es: " + greatest + "\n");
		      			System.out.println("Enviando a socket...\n");
		      			out.flush();
		      			out.writeFloat(greatest);
		      	      	out.flush();
		      		}
		      		break;
		      	case 4:
		      		System.out.println("El cliente ha elegido comparar arreglos. Leyendo tipo de arreglos...\n");
		      		type = in.readInt();
		      		if (type == 1) {
			      		System.out.println("El cliente ha elegido comparar matrices. Leyendo el tamaño de las matrices...\n");
			      		lines = in.readInt();
						System.out.println("Líneas: " + lines);
						columns = in.readInt();
						System.out.println("Columnas: " + columns + "\n");
			      		float m6 [][] = new float [lines][columns];
			      		System.out.println("Primera matriz");
			      		mySockets.getMatrix(m6, in);
			      		float m7 [][] = new float [lines][columns];
			      		System.out.println("Segunda matriz");
			      		mySockets.getMatrix(m7, in);
			      		isEqual = myOperations.equal(m6, m7);
			      		if (isEqual) {
	            			System.out.println("Las matrices son iguales.\n");
	            		}
	            		else {
	            			System.out.println("Las matrices son diferentes.\n");
	            		}
			      		out.flush();
			      		out.writeBoolean(isEqual);
			      		out.flush();
			      		System.out.println("Enviando resultado a cliente...\n");
		      		}
		      		if (type == 2) {
		      			System.out.println("El cliente ha elegido comparar vectores. Leyendo el tamaño de los vectores...\n");
			      		vector = in.readInt();
						System.out.println("Tamaño de los vectores: " + vector + "\n");
			      		float v4 [] = new float [vector];
			      		System.out.println("Primer vector");
			      		mySockets.getVector(v4, in);
			      		float v5 [] = new float [vector];
			      		System.out.println("Segundo vector");
			      		mySockets.getVector(v5, in);
			      		isEqual = myOperations.equal(v4, v5);
			      		if (isEqual) {
	            			System.out.println("Los vectores son iguales.\n");
	            		}
	            		else {
	            			System.out.println("Los vectores son diferentes.\n");
	            		}
			      		out.flush();
			      		out.writeBoolean(isEqual);
			      		out.flush();
			      		System.out.println("Enviando resultado a cliente...\n");
		      		}
		      		break;
		      	case 5:
		      		System.out.println("El cliente ha terminado la conexión.");
		      		break;
		      	default:
		      		System.out.println("El cliente ha elegido una opción incorrecta.\n");
		      		break;
		      }
		    }
	      	while (operation != 5);
	      }

		    catch (IOException e) {
		          System.out.println("Accept failed: 4444");
		          System.exit(-1);
		    }
		  
	  }
	  protected void finalize(){
		     try{
		        in.close();
		        out.close();
		        server.close();
		    } catch (IOException e) {
		        System.out.println("Could not close.");
		        System.exit(-1);
		    }
		  }

	  public static void main(String[] args){
		      System.out.println("Servidor Operaciones con Matrices\n");
		      Server s = new Server();

		      s.listenSocket();
		      s.finalize();
		  }
}
