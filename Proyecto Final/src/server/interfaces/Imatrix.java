package server.interfaces;

public interface Imatrix {
	public void multiply(float m[][], float v[], float r[]);

	public void multiply(float a[][], float b[][], float r[][]);

	public void addition(float a[][], float b[][], float r[][]);

	public boolean equal(float a[][], float b[][]);

	public float greatest(float m[][]);

	public float greatest(float v[]);
}
