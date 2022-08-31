package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

import client.operations.ClientOperations;
import client.sockets.ClientSockets;

public class Client {
	public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        DataOutputStream os = null;
        DataInputStream is = null;
        //DataInputStream stdIn = new DataInputStream(System.in);
        Scanner stdIn = new Scanner( System.in );
        int option = 0;
        
        try {
            echoSocket = new Socket("localhost", 2004);
            os = new DataOutputStream(echoSocket.getOutputStream());
            is = new DataInputStream(echoSocket.getInputStream());
        } 
        catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost");
        } 
        catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to server");
        }

        if (echoSocket != null && os != null && is != null) {
        	do {
                System.out.println("Menú de operaciones con matrices:\n"
                		+ "1. Multiplicación\n"
                		+ "2. Suma\n"
                		+ "3. Encuentra el número mayor\n"
                		+ "4. Comparación\n"
                		+ "5. Salir\n");		
                boolean repeat = false;
    			
                do {
	    			try {
	    				System.out.print("Seleccione la operación:");
	    				option = stdIn.nextInt();
	    				System.out.println();
	    				repeat = false;
	    			}
	    			catch (InputMismatchException e) {
	    				System.err.println("Valor incorrecto. Introduzca un número entero.\n");
	    				option = 0;
	    				stdIn = new Scanner( System.in );
	    				repeat = true;
	    			}
                } while (repeat == true);

    			os.writeInt(option);
                
                try {
                    int type = 0;
                    int lines = 0;
                    int lines2 = 0;
                    int columns = 0;
                    int columns2 = 0;
                    int vector = 0;
                    int matrixSize [];
                    boolean isEqual;
                    ClientOperations myOperations = new ClientOperations();
                   // while ((userInput = stdIn.readLine()) != null) {
                    switch (option) {
                    	case 1:
                    		repeat = false;
	                        do {
	                        	System.out.println("Ingrese los valores de la primera matriz:\n");
	                        	matrixSize = ClientOperations.matrixSize(lines, columns);
	                        	lines = matrixSize[0];
	                			columns = matrixSize[1];
	                			type = myOperations.chooseType(type);
	                			if (type == 1) {
	                				System.out.println("Ingrese los valores de la segunda matriz:");
		                        	matrixSize = ClientOperations.matrixSize(lines2, columns2);
		                        	lines2 = matrixSize[0];
		                			columns2 = matrixSize[1];
		                			if (columns == lines2) {
		                				float m1 [][] = new float [lines][columns];
		                				float m2 [][] = new float [lines2][columns2];
		                				os.writeInt(type);
		                				ClientSockets.sendMatrixSize(os, lines, columns);
		                				ClientSockets.sendMatrixSize(os, lines2, columns2);
		                				System.out.println("Primera matriz:");
		                				ClientOperations.matrixData(m1);
		                    			System.out.println("La primera matriz ingresada es:");
		                    			myOperations.print(m1);
		                    			ClientSockets.sendMatrix(m1, os);
		                    			System.out.println("Segunda matriz:");
		                    			ClientOperations.matrixData(m2);
		                    			System.out.println("La segunda matriz ingresada es:");
		                    			myOperations.print(m2);
		                    			ClientSockets.sendMatrix(m2, os);
		                    			System.out.println("Enviando matrices al servidor. Esperando resultado...\n");
		                    			float r1 [][] = new float [lines][columns2];
		                    			ClientSockets.getMatrix(r1, is);
		                    			System.out.println("El resultado es:");
		                    			myOperations.print(r1);
		                    			break;
		                			}
		                			else {
		                				System.out.println("Las columnas de la primera matriz deben ser del mismo tamaño que las líneas de la segunda matriz.\n");
		                				repeat = true;
		                			}
	                			}
	                			if (type == 2) {
	                				vector = ClientOperations.vectorSize(vector);
	                				if (columns == vector) {
	                					float m3 [][] = new float [lines][columns];
	                					float v [] = new float [vector];
	                					os.writeInt(type);
	                					ClientSockets.sendMatrixSize(os, lines, columns);
		                				System.out.println("Primera matriz:");
		                				ClientOperations.matrixData(m3);
		                    			System.out.println("La matriz ingresada es:");
		                    			myOperations.print(m3);
		                    			ClientSockets.sendMatrix(m3, os);
		                    			ClientOperations.vectorData(v);
	                					System.out.println("El vector ingresado es:");
	                					myOperations.print(v);
	                					ClientSockets.sendVector(v, os);
	                					System.out.println("Enviando al servidor. Esperando resultado...");
	                					float r [] = new float [vector];
	                					System.out.println("El resultado es:");
	                					ClientSockets.getVector(r, is);
	                					myOperations.print(r);
	                					break;
	                				}
	                				else {
	                					System.out.println("El número de columnas de la matriz debe ser igual al número de valores del vector.\n");
	                					repeat = true;
	                				}
	                			}
	                        }
	                        while (repeat = true);
	                        break;
                    	case 2:
                    		type = myOperations.chooseType(type);
                    		do {
	                    		if (type == 1) {
	                    			os.writeInt(type);
		                    		System.out.println("Ingrese el tamaño de las matrices: \n");
		                    		matrixSize = ClientOperations.matrixSize(lines, columns);
		                    		lines = matrixSize[0];
		                			columns = matrixSize[1];
		                			ClientSockets.sendMatrixSize(os, lines, columns);
		                			float m3 [][] = new float [lines][columns];
		                			System.out.println("Primera matriz:");
		                			ClientOperations.matrixData(m3);
		                			System.out.println("La primera matriz ingresada es:");
		                			myOperations.print(m3);
		                			ClientSockets.sendMatrix(m3, os);
		                			float m4 [][] = new float [lines][columns];
		                			System.out.println("Segunda matriz:");
		                			ClientOperations.matrixData(m4);
		                			System.out.println("La segunda matriz ingresada es:");
		                			myOperations.print(m4);
		                			ClientSockets.sendMatrix(m4, os);
		                			System.out.println("Enviando a servidor. Esperando resultado...\n");
		                			float r2 [][] = new float [lines][columns];
		                			ClientSockets.getMatrix(r2, is);
		                			System.out.println("El resultado es:");
		                			myOperations.print(r2);
		                			break;
	                    		}
	                    		if (type == 2) {
	                    			os.writeInt(type);
	                    			System.out.println("Tamaño de los vectores");
	                    			vector = ClientOperations.vectorSize(vector);
	                    			os.writeInt(vector);
	                    			float v2[] = new float [vector];
	                    			System.out.println("Primer vector");
	                    			ClientOperations.vectorData(v2);
	                    			ClientSockets.sendVector(v2, os);
	                    			float v3[] = new float [vector];
	                    			System.out.println("Segundo vector");
	                    			ClientOperations.vectorData(v3);
	                    			ClientSockets.sendVector(v3, os);
	                    			System.out.println("Enviando a servidor, esperando resultado...\n");
	                    			float r2 [] = new float [vector];
	                    			ClientSockets.getVector(r2, is);
	                    			System.out.println("El resultado es:");
	                    			myOperations.print(r2);
	                    			break;
	                    		} else {
	                    			System.out.println("Opción incorrecta. Elija 1 o 2.");
	                    		}
                    		} while (type != 1 && type != 2);
                    		break;
                    	case 3:
                    		type = myOperations.chooseType(type);
                    		os.writeInt(type);
                    		if (type == 1) {
                    			matrixSize = ClientOperations.matrixSize(lines, columns);
                    			lines = matrixSize[0];
                    			columns = matrixSize[1];
                    			ClientSockets.sendMatrixSize(os, lines, columns);
                    			float m5 [][] = new float [lines][columns];                    			
                    			ClientOperations.matrixData(m5);
                    			System.out.println("La matriz ingresada es:");
                    			myOperations.print(m5);
                    			ClientSockets.sendMatrix(m5, os);
                    			System.out.println("El mayor valor de la matriz es: " + is.readFloat() + "\n");
                    		}
                    		if (type == 2) {
                    			vector = ClientOperations.vectorSize(vector);
                    			float v4 [] = new float [vector];
                    			os.writeInt(vector);
                    			ClientOperations.vectorData(v4);
                    			System.out.println("El vector ingresado es:");
                    			myOperations.print(v4);
                    			ClientSockets.sendVector(v4, os);
                    			System.out.println("El mayor valor del vector es: " + is.readFloat() + "\n");
                    		}
                    		break;
                    	case 4:
                    		type = myOperations.chooseType(type);
                    		do {
	                    		if (type == 1) {
	                    			os.writeInt(type);
		                    		System.out.println("Ingrese el tamaño de las matrices: \n");
		                    		matrixSize = ClientOperations.matrixSize(lines, columns);
		                    		lines = matrixSize[0];
		                    		columns = matrixSize[1];
		                    		ClientSockets.sendMatrixSize(os, lines, columns);
		                    		float m6 [][] = new float [lines][columns];
		                    		System.out.println("Primera matriz");
		                    		ClientOperations.matrixData(m6);
		                    		System.out.println("La primera matriz ingresada es:");
		                    		myOperations.print(m6);
		                    		ClientSockets.sendMatrix(m6, os);
		                    		float m7 [][] = new float [lines][columns];
		                    		System.out.println("Segunda matriz");
		                    		ClientOperations.matrixData(m7);
		                    		System.out.println("La segunda matriz ingresada es:");
		                    		myOperations.print(m7);
		                    		ClientSockets.sendMatrix(m7, os);
		                    		System.out.println("Enviando a servidor...\n");
		                    		isEqual = is.readBoolean();
		                    		if (isEqual) {
		                    			System.out.println("Las matrices son IGUALES.\n");
		                    		}
		                    		else {
		                    			System.out.println("Las matrices son DIFERENTES.\n");
		                    		}
		                    		break;
	                    		}
	                    		if (type == 2) {
	                    			os.writeInt(type);
		                    		System.out.println("Ingrese el tamaño de los vectores: \n");
		                    		vector = ClientOperations.vectorSize(vector);
		                    		os.writeInt(vector);
		                    		float v5 [] = new float [vector];
		                    		System.out.println("Primer vector");
		                    		ClientOperations.vectorData(v5);
		                    		ClientSockets.sendVector(v5, os);
		                    		float v6 [] = new float [vector];
		                    		System.out.println("Segundo vector");
		                    		ClientOperations.vectorData(v6);
		                    		ClientSockets.sendVector(v6, os);
		                    		System.out.println("Enviando a servidor...\n");
		                    		isEqual = is.readBoolean();
		                    		if (isEqual) {
		                    			System.out.println("Los vectores son IGUALES.\n");
		                    		}
		                    		else {
		                    			System.out.println("Los vectores son DIFERENTES.\n");
		                    		}
		                    		break;
	                    		} else {
	                    			System.out.println("Opción incorrecta. Elija 1 o 2.");
	                    		}
                    		} while (type != 1 && type != 2);
                    		break;
                    	case 5:
                    		System.out.println("Salir del programa. ¡Hasta luego!");
                            os.close();
                            is.close();
                            echoSocket.close();
                            stdIn.close();
                    		break;
                    	default:
                    		System.out.println("Opción incorrecta.\n");
                    }
                }
                catch (IOException e) {
                    System.err.println("I/O failed on the connection to: localhost");
                }
    			catch (Exception e) {
    				e.printStackTrace();
    			}
        	}
        	while (option != 5);
        }
	}
}
