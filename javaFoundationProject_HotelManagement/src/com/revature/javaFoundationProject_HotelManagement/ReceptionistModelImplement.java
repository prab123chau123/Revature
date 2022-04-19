package com.revature.javaFoundationProject_HotelManagement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.revature.javaFoundationProject_HotelManagement.GuestServices;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.revature.javaFoundationProject_HotelManagement.Queries;
public class ReceptionistModelImplement implements ReceptionistModel 
{
	Connection conn=MyDataBaseConnectivity.getConnection();
	Statement statement=MyDataBaseConnectivity.getStatement();
	@Override
	public void seeAvailableRooms() throws Exception {
	PreparedStatement preparedStatement=conn.prepareStatement(Queries.SEE_AVAILABLE_ROOMS);
	preparedStatement.setString(1, "AVAILABLE");
	ResultSet rs=preparedStatement.executeQuery();
	System.out.println("This rooms are currently Available:");
	while(rs.next()) {
		int roomNo=rs.getInt("Room_Number");
		if(roomNo>=101 && roomNo<=105)
		System.out.println(roomNo+" - Normal");
		if(roomNo>=201 && roomNo<=205)
			System.out.println(roomNo+" - Deluxe");
		if(roomNo>=301 && roomNo<=305)
			System.out.println(roomNo+" - Super Deluxe");
		if(roomNo>=401 && roomNo<=405)
			System.out.println(roomNo+" - Executive Suite");
		
	}
	}

	@Override
	public void guestRegistration(String guestId,String guestName,
			long guestMobileNumber,String guestAddress,String password) throws Exception {
	PreparedStatement preparedStatement=conn.prepareStatement(Queries.GUEST_REGISTRATION);//register guest
	preparedStatement.setString(1, guestId);
	preparedStatement.setString(2, guestName);
	preparedStatement.setLong(3, guestMobileNumber);
	preparedStatement.setString(4, guestAddress);
	preparedStatement.setString(5, password);
	preparedStatement.executeUpdate();
	System.out.println("Hey! Guest Added Successfully!");

	}

	@Override
	public void bookRoom(String guestId,int roomNo,String idProof,String checkInDate,
			String checkOutDate,String reasonForStay) throws Exception {
		PreparedStatement preparedStatement=conn.prepareStatement(Queries.SELECT_GUESTID);
		preparedStatement.setString(1, guestId);
		ResultSet rs=preparedStatement.executeQuery();
		if(rs.getString("guest_id").equals(guestId)){ //if user registered
		System.out.println(rs.getString("guest_id"));
		rs=statement.executeQuery("select status from rooms where room_number="+roomNo);
		rs.next();
		if(rs.getString("Status").equals("Available")) { // if room available
		preparedStatement=conn.prepareStatement(Queries.UPDATE_ROOM_STATUS);//update room status to booked
		preparedStatement.setString(1, "Booked");
		preparedStatement.setInt(2, roomNo);
		preparedStatement.executeUpdate();
		preparedStatement=conn.prepareStatement(Queries.INSERT_BOOKING_INFO);//update booking_information
		preparedStatement.setString(1, guestId);
		preparedStatement.setInt(2, roomNo);
		preparedStatement.setString(3, idProof);
		preparedStatement.setString(4, checkInDate);
		preparedStatement.setString(5, checkOutDate);
		preparedStatement.setString(6, reasonForStay);
		preparedStatement.executeUpdate();
		preparedStatement=conn.prepareStatement(Queries.INSERT_BILL);
		preparedStatement.setString(1, guestId);
		preparedStatement.setInt(2, roomNo);
		preparedStatement.setString(3, "Room");
		preparedStatement.setDouble(4, checkPrice(roomNo));
		preparedStatement.executeUpdate();
		System.out.println("Hey! Room booked Successfully!");
		}
		else if(!rs.getString("Status").equals("Available")) { // if room not available
			System.out.println("Oh ho! The room you are looking for is not available right now...");
		}
		}
	}

	@Override
	public void guestServices() throws Exception {
		GuestServices gs=new GuestServices();
		gs.guestServices();
		
	}

	@Override
	public String guestBill(String guestId) throws Exception{
		PreparedStatement preparedStatement=conn.prepareStatement(Queries.GUEST_BILL);
		preparedStatement.setString(1, guestId);
		ResultSet rs=preparedStatement.executeQuery();
		rs.next();
		//System.out.println(rs.getString("SUM"));
		return rs.getString("SUM");
	}

	@Override
	public List<List<String>> bookingInfo() throws Exception {
		PreparedStatement preparedStatement=conn.prepareStatement(Queries.SELECT_BOOKING_INFO);
		ResultSet rs=statement.executeQuery(Queries.SELECT_BOOKING_INFO);
		//ResultSetMetaData rsmd = rs.getMetaData();
		//String firstColumnName = rsmd.getColumnName(1);
		List<List<String>> al=new ArrayList<>();
		while(rs.next()) {
			List<String> a=new ArrayList<>();
			a.add(rs.getString("booking_id"));
			a.add(rs.getString("guest_id"));
			a.add(rs.getString("room_number"));
			a.add(rs.getString("id_proof"));
			a.add(rs.getString("checkin_date"));
			a.add(rs.getString("checkout_date"));
			a.add(rs.getString("reason_for_stay"));
			al.add(a);
			
		}
		return al;
	}
	public double checkPrice(int roomNo) {
		double price=0;
		if(roomNo>=101 && roomNo<=105)
			price=1000;
		if(roomNo>=201 && roomNo<=205)
			price=1500;
		if(roomNo>=301 && roomNo<=305)
			price=2500;
		if(roomNo>=401 && roomNo<=405)
			price=3500;
		return price;
	}
	public int checkGuest(String guestId)throws Exception {
		PreparedStatement preparedStatement=conn.prepareStatement(Queries.SELECT_GUESTID);
		preparedStatement.setString(1, guestId);
		ResultSet rs=preparedStatement.executeQuery();
		if(rs.next()==false) {
			System.out.println("Oh no! user not registered..."); // check whether user exist or not
			return 0;
		}
		else
			return 1;
	}
}
