package model;

public class ExponentialRandomGenerator extends UniformRandomGenerator
{

	// fields
	double lambda;

	// constructor
	public ExponentialRandomGenerator(double lambda)
	{
		super();
		this.lambda = lambda;
	}

	// overridden methods
	@Override
	public double nextRand()
	{
		// convert to inverse of exponential functio
		double r = super.nextRand();
		double x = - Math.log(1 - r) / lambda;
		return x;
	}

	public static void main(String[] args)
	{
		ExponentialRandomGenerator r = new ExponentialRandomGenerator(0.01);
		System.out.println(r.getHistogram(10000, 10));
	}
}
