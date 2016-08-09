package model;
public class WeibullRandomGenerator extends UniformRandomGenerator
{

	// fields 
	double alpha, beta;

	// constructor 
	public WeibullRandomGenerator(double alpha, double beta)
	{
		super();
		this.alpha = alpha;
		this.beta = beta;
	}

	// overridden methods
	@Override
	public double nextRand()
	{
		// get inverse of weibull function
		double r = super.nextRand();
		double x = alpha * Math.pow(- Math.log(1 - r), 1.0 / beta);
		return x;
	}
	
	public static void main(String[] args)
	{
		WeibullRandomGenerator r = new WeibullRandomGenerator(50, 3);
		System.out.println(r.getHistogram(1000, 10));
	}
}

