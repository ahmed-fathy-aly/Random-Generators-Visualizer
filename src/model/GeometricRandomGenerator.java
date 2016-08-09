package model;

public class GeometricRandomGenerator extends UniformRandomGenerator
{

	// fields
	double p;

	// constructor
	public GeometricRandomGenerator(double p)
	{
		super();
		this.p = p;
	}

	// overridden methods
	@Override
	public double nextRand()
	{
		// convert to inverse of exponential functio
		double r = super.nextRand();
		double x = Math.ceil((Math.log(1 - r) / Math.log(1 - p)) - 1);
		return x;
	}

	public static void main(String[] args)
	{
		GeometricRandomGenerator r = new GeometricRandomGenerator(0.5);
		System.out.println(r.getHistogram(100000, 10));
	}
}
