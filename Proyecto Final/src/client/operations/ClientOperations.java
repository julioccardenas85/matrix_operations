package client.operations;
import java.util.InputMismatchException;
import java.util.Scanner;

import client.interfaces.IprintMatrix;

public class ClientOperations implements IprintMatrix {

	public static int [] matrixSize (int lines, int columns) {
	    Scanner stdIn = new Scanner( System.in );
	    boolean repeat = false;
		do {
			try {
			    System.out.print("Ingrese el número de líneas de la matriz: ");
				lines = stdIn.nextInt();
				System.out.println();
				System.out.print("Ingrese el número de columnas de la matriz: ");
				columns = stdIn.nextInt();
				System.out.println();
				repeat = false;
			} catch (InputMismatchException e) {
				System.err.println("Valor incorrecto. Introduzca un número entero.\n");
				stdIn = new Scanner( System.in );
				repeat = true;
			}
		} while (repeat == true);
		int size [] = new int [2];
		size[0] = lines;
		size[1] = columns;
		return size;
	}
	
	public static int vectorSize (int vector) {
		Scanner stdIn = new Scanner(System.in);
		boolean repeat = false;
		do {
			try {
				System.out.print("Ingrese el número de valores del vector: ");
				vector = stdIn.nextInt();
				System.out.println();
				repeat = false;
			} catch (InputMismatchException e) {
				System.err.println("Valor incorrecto. Introduzca un número entero.\n");
				stdIn = new Scanner( System.in );
				repeat = true;
			}
		} while (repeat == true);
		return vector;
	}
	
	public int chooseType (int type) {
		Scanner stdIn = new Scanner(System.in);
		do {
			try {
				System.out.print("Elija tipo de arreglo:\n"
						+ "1. Matriz\n"
						+ "2. Vector\n"
						+ "Opción: ");
				type = stdIn.nextInt();
				System.out.println();
				if (type != 1 && type != 2) {
					System.out.println("Opción incorrecta. Elija opción 1 o 2.\n");
				}
			} catch (InputMismatchException e) {
				System.err.println("Valor incorrecto. Introduzca un número entero (1 o 2).\n");
				stdIn = new Scanner( System.in );
			}
		} while (type != 1 && type != 2);
		
		return type;
	}
	
	public void print(float v[]) {
		for (int i=0; i<v.length; i++)
			System.out.println(v[i]);
		System.out.println();
	}
		
	public void print(float m[][]) {
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++)
				System.out.print(m[i][j] + " " ); 
				System.out.println();
		}
		System.out.println();
	}
	public static void matrixData (float m[][]) {
		System.out.println("Ingrese los valores de la matriz:");
		Scanner stdIn = new Scanner(System.in);
		boolean repeat = false;
		for (int i=0; i<m.length; i++) {
			for (int j=0; j<m[0].length; j++) {
				do {
					try {
						System.out.print("Valor [" + i + "][" + j + "]: ");
						m[i][j] = stdIn.nextFloat();
						System.out.println();
						repeat = false;
					} catch (InputMismatchException e) {
						System.err.println("Valor incorrecto. Introduzca un número entero o decimal.\n");
						stdIn = new Scanner( System.in );
						repeat = true;
					}
				} while (repeat == true);
			}
		}
	}
	
	public static void vectorData (float v[]) {
		System.out.println("Ingrese los valores del vector:");
		Scanner stdIn = new Scanner(System.in);
		boolean repeat = false;
		for (int i=0; i<v.length; i++) {
			do {
				try {
					System.out.print("Valor [" + i + "]: ");
					v[i] = stdIn.nextFloat();
					System.out.println();
					repeat = false;
				} catch (InputMismatchException e) {
					System.err.println("Valor incorrecto. Introduzca un número entero o decimal.\n");
					stdIn = new Scanner( System.in );
					repeat = true;
				}
			} while (repeat == true);
		}
	}
}
