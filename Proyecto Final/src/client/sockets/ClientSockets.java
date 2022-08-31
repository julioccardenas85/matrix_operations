package client.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClientSockets {
	public static void sendMatrixSize (DataOutputStream os, int lines, int columns) throws IOException {
		os.writeInt(lines);
        os.writeInt(columns);
	}
	public static void sendMatrix (float m[][], DataOutputStream os) throws IOException {
		for (int i=0; i<m.length; i++) {
			for (int j=0; j<m[0].length; j++)
				os.writeFloat(m[i][j]);
		}
	}
	public static void sendVector (float v[], DataOutputStream os) throws IOException {
		for (int i = 0; i<v.length; i++)
			os.writeFloat(v[i]);
	}
	public static void getMatrix (float m[][], DataInputStream is) throws IOException {
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				m[i][j] = is.readFloat();
			}
		}
	}
	public static void getVector (float v[], DataInputStream is) throws IOException {
		for(int i=0; i<v.length; i++)
			v[i] = is.readFloat();
	}
}
