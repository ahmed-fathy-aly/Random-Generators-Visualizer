package model;

/**
 * contains specs of a random generator gap size is the difference between each
 * 2 generated numbers period length is the number of different generated
 * numbers
 * 
 * @author ahmed
 *
 */
public class GeneratorSpecs
{
	long periodLength;
	double minGap, maxGap, averageGap;

	/* Constructors */
	public GeneratorSpecs()
	{
	}

	public GeneratorSpecs(long periodLength, double minGap, double maxGap, double averageGap)
	{
		this.periodLength = periodLength;
		this.minGap = minGap;
		this.maxGap = maxGap;
		this.averageGap = averageGap;
	}

	/* setters and getters */

	public long getPeriodLength()
	{
		return periodLength;
	}

	public void setPeriodLength(long periodLength)
	{
		this.periodLength = periodLength;
	}

	public double getMinGap()
	{
		return minGap;
	}

	public void setMinGap(double minGap)
	{
		this.minGap = minGap;
	}

	public double getMaxGap()
	{
		return maxGap;
	}

	public void setMaxGap(double maxGap)
	{
		this.maxGap = maxGap;
	}

	public double getAverageGap()
	{
		return averageGap;
	}

	public void setAverageGap(double averageGap)
	{
		this.averageGap = averageGap;
	}

	/* methods */
	@Override
	public String toString()
	{
		return "period length : " + periodLength + "\naverage gap : " + averageGap + "\nmin gap : " + minGap
				+ "\nmax gap : " + maxGap;
	}

}
