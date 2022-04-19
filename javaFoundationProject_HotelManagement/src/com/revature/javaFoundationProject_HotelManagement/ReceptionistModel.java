package com.revature.javaFoundationProject_HotelManagement;
import java.util.*;
import java.sql.ResultSet;
public interface ReceptionistModel 
{
	public void seeAvailableRooms() throws Exception;
	public void guestRegistration(String guestId,String guestName,
			long guestMobileNumber,String guestAddress,String password) throws Exception;
	public void bookRoom(String guestId,int roomNo,String idProof,
			String checkInDate,String checkOutDate,String reasonForSta) throws Exception;
	public void guestServices() throws Exception;
	public String guestBill(String guestId) throws Exception;
	public List<List<String>> bookingInfo() throws Exception;
}
