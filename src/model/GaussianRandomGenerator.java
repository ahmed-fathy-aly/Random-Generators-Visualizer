package model;
public class GaussianRandomGenerator extends UniformRandomGenerator
{

	// fields 
	double u, alpha;

	// constructor 
	public GaussianRandomGenerator(double u, double alpha)
	{
		super();
		this.u = u;
		this.alpha = alpha;
	}

	// overridden methods
	@Override
	public double nextRand()
	{
		// get normal with u = 0, alpha = 0 using Schmeiser equation
		double r = super.nextRand();
		double x = (Math.pow(r, 0.135) - Math.pow(1 -r, 0.135)) / 0.1975;

		// get normal with required u and alpha
		return x*alpha + u;
	}
	
	public static void main(String[] args)
	{
		GaussianRandomGenerator r = new GaussianRandomGenerator(0, 0);
		System.out.println(r.getHistogram(1000, 10));
	}
}

