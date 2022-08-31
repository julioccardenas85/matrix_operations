package server.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerSockets {
	public void getVector(float v[], DataInputStream in) {
	  	System.out.println("Recibiendo datos del vector...\n");
	    for (int i=0; i<v.length; i++) {
				try {
					v[i] = in.readFloat();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Valor [" + i + "]: " + v[i]);
	    }
	    System.out.println();
	}
	
	public void getMatrix(float m[][], DataInputStream in) throws IOException {
		System.out.println("Recibiendo datos de la matriz...");
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				m[i][j] = in.readFloat(); 
				System.out.println("Valor [" + i + "][" + j + "]: " + m[i][j]);
			}
		}
		System.out.println();
	}
	public void getMatrixSize (int lineas, int columnas, DataInputStream in) throws IOException {
		lineas = in.readInt();
		System.out.println("Líneas: " + lineas);
		columnas = in.readInt();
		System.out.println("Columnas: " + columnas + "\n");
	}
	public void sendMatrix (float m[][], DataOutputStream out) throws IOException {
		for (int i=0; i<m.length; i++) {
			for (int j=0; j<m[0].length; j++)
				out.writeFloat(m[i][j]);
				out.flush();
		}
	}
	public void sendVector (float v[], DataOutputStream out) throws IOException {
		for (int i=0; i<v.length; i++)
			out.writeFloat(v[i]);
			out.flush();
	}
}
