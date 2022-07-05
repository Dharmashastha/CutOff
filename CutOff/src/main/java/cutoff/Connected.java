package cutoff;


import common.CustomException;

public interface Connected 
{
	public String insertEngineeringCustOff(StudentInfo studentInfo) throws CustomException, ClassNotFoundException;
	
	public double getCutOffData(String rollNumber) throws CustomException, ClassNotFoundException;
}
