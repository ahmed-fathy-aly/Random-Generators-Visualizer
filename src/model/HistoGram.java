package model;
import java.sql.NClob;

public class HistoGram
{
	int nPeriods;
	long nValues;
	double minValue, maxValue;
	long[] count;
	
	/* Constructors */
	public HistoGram()
	{
	}

	/* getters and setters */
	

	public long getnValues()
	{
		return nValues;
	}

	public void setnValues(long nValues)
	{
		this.nValues = nValues;
	}

	public double getMinValue()
	{
		return minValue;
	}

	public void setMinValue(double minValue)
	{
		this.minValue = minValue;
	}

	public double getMaxValue()
	{
		return maxValue;
	}

	public void setMaxValue(double maxValue)
	{
		this.maxValue = maxValue;
	}

	public long[] getCount()
	{
		return count;
	}

	public void setCount(long[] count)
	{
		this.count = count;
	}

	public int getnPeriods()
	{
		return nPeriods;
	}
	
	
	public void setnPeriods(int nPeriods)
	{
		this.nPeriods = nPeriods;
	}
	
	public double getPeriodSize()
	{
		return (maxValue - minValue) / nPeriods;
	}

	/* methods */
	@Override
	public String toString()
	{
		StringBuilder strb = new StringBuilder();
		strb.append(minValue + " -> " + maxValue + "\n");
		strb.append("nValues = " + nValues + "\n");
		for (int i = 0; i < count.length; i++) 
			strb.append(count[i] + " ");
		return strb.toString().trim();
			
	}
	
}
