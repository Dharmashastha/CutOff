package cutoff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.ConnectionUtility;
import common.CustomException;

public class CutOffDbLayer implements Connected
{
	public String insertEngineeringCustOff(StudentInfo studentInfo) throws CustomException, ClassNotFoundException
	{
		final String insertQuery = "INSERT INTO Engineering VALUES(?,?,?,?,?,?);";
		
		String saved = null;
		
		try(PreparedStatement state = ConnectionUtility.getConnection().prepareStatement(insertQuery))
		{
			state.setString(1, studentInfo.getRollNumber());
			state.setString(2, studentInfo.getName());
			state.setInt(3, studentInfo.getMaths());
			state.setInt(4, studentInfo.getChemistry());
			state.setInt(5, studentInfo.getPhysics());
			state.setDouble(6, studentInfo.getCutOffMark());
			state.executeUpdate();
			
			saved = 1 + " Row Created.";
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			saved = "Data not Entered.";
		}
		return saved;
	}
	
	public double getCutOffData(String rollNumber) throws CustomException, ClassNotFoundException
	{
		
		double cutOff = 0.0;
		
		final String customerQuery = "SELECT CutOff FROM Engineering WHERE RollNumber = ?;";
		
		try(PreparedStatement state = ConnectionUtility.getConnection().prepareStatement(customerQuery);)
		{
			state.setString(1, rollNumber);
			
			try(ResultSet rs = state.executeQuery();)
			{
				while(rs.next())
				{
					cutOff = rs.getDouble(1);
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return cutOff;
	}
	
/*	public void insertAgricultureCustOff(StudentInfo studentInfo) throws SQLException, CustomException
	{
		final String insertQuery = "INSERT INTO Engineering VALUES(?,?,?,?,?,?);";
		
		try(PreparedStatement state = ConnectionUtility.getConnection().prepareStatement(insertQuery))
		{
			state.setString(1, studentInfo.getRollNumber());
			state.setString(2, studentInfo.getName());
			state.setInt(3, studentInfo.getMaths());
			state.setInt(4, studentInfo.getChemistry());
			state.setInt(5, studentInfo.getPhysics());
			state.setDouble(6, studentInfo.getCutOffMark());
		}
	}*/
	
	
}
