package server.operations;
import java.util.Arrays;

import server.interfaces.Imatrix;

public class ServerOperations implements Imatrix {	
	public void multiply(float m[][], float v[], float r[]) {
		if (m[0].length == v.length) {
			for(int i=0; i<m.length; i++) 
				for(int j=0; j<m[0].length; j++)
					r[i] = r[i] + m[i][j] * v[j];
		}
		else {
			System.out.println("No se puede realizar la multiplicación, la cantidad de columnas de la matriz debe ser igual a la cantidad de filas del vector");
		}
	}
	
	public void multiply(float a[][], float b[][], float r[][]) {
		for(int i=0; i<a.length; i++) // matrix rows (vertical)
				for(int j=0; j<b[0].length; j++) // matrix columns (horizontal)
				for(int k=0; k<b.length; k++)
				r[i][j] = r[i][j] + a[i][k]* b[k][j];
	}		
	
	public void addition(float a[][], float b[][], float r[][]) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
		if(a.length != b.length || a[0].length != b[0].length) {
        	throw new IllegalArgumentException("Los tamaños de las matrices no son compatibles");
        }
		try {
			for(int i=0; i<a.length; i++) 
				for(int j=0; j<a[0].length; j++)
					r[i][j] = a[i][j] + b[i][j];
			//r[100][100] = 0; // test
		}

		catch (ArrayIndexOutOfBoundsException e) {
        	System.out.println("Arreglo fuera de limites");
        	e.printStackTrace();
			throw new ArrayIndexOutOfBoundsException("Arreglo fuera de limites");
        }
        assert(r.length == a.length && r[0].length == a[0].length);
	}
	
	public void addition (float v[], float v2[], float r[]) {
		for (int i = 0; i<v.length; i++)
			r[i] = v[i] + v2[i];
	}

	public boolean equal (float a[][], float b[][]) {
		return Arrays.deepEquals(a, b);
	}

	public boolean equal (float a[], float b[]) {
		return Arrays.equals(a, b);
	}
	
	public float greatest(float m[][]) {
		float greatest = m[0][0];
		try {			
			for (int i=0; i<m.length; i++) {
				for (int j=0; j<m[0].length; j++)
					if (m[i][j] > greatest)
						greatest = m[i][j];						
			}			
		}
		
		catch (ArrayIndexOutOfBoundsException e) {
	         System.out.println("Arreglo fuera de limites");
	         e.printStackTrace();
	    }
		return greatest;
	}

	public float greatest(float v[]) {
		float greatest = v[0];
		try {			
			for (int i=0; i<v.length; i++) {
				if (v[i] > greatest)
					greatest = v[i];						
			}			
		}
		
		catch (ArrayIndexOutOfBoundsException e) {
	         System.out.println("Arreglo fuera de limites");
	         e.printStackTrace();
	    }
		return greatest;
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
}
