package cutoff;

public class CalculateCutOff 
{
	
	Connected connect;
	
	public CalculateCutOff()
	{
		try {
			
			Class<?> connected = Class.forName("cutoff.CutOffDbLayer");
			
			Object saved = connected.getDeclaredConstructor().newInstance();
			
			connect = (Connected) saved;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connected connect()
	{
		return connect;
	}
	
	public double engineeringCutOff(int maths,int chemistry,int physics)
	{
		return maths + (physics + chemistry)/2;
	}
	
	/*
	 * public double artsAndScienceCutOff(int subject1,int subject2,int subject3,int
	 * subject4) { return (subject1+subject2+subject3+subject4)/4; }
	 */
	
	public double agricultureCustOff(int mathsOrBotany,int chemistry,int physics,int biologyOrCsOrZoology)
	{
		return (mathsOrBotany+chemistry+physics+biologyOrCsOrZoology)/2;
	}
	
	public double paraMedicalCutOff(int biology,int physics,int chemistry)
	{
		return biology + (physics + chemistry)/2;
	}
}
