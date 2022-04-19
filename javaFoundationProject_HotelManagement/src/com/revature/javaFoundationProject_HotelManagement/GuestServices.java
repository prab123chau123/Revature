package com.revature.javaFoundationProject_HotelManagement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class GuestServices 
{
	Connection conn=MyDataBaseConnectivity .getConnection();
	public void guestServices() throws Exception {
		System.out.println("Choose from below options:");
		Scanner sc=new Scanner(System.in);
		int flag=1;
		int flag2=1;
		do {
		System.out.println("1. Food");
		System.out.println("2. Beverges");
		System.out.println("3. Laundry");
		System.out.println("4. Cab");
		System.out.println("5. Exit");
		int choice=sc.nextInt();
		if(choice==5) {
			break;
		}
		System.out.print("\nEnter GuestID: ");
		String guestId=sc.next();
		System.out.println("\nEnter RoomNo: ");
		int roomNo=sc.nextInt();
		System.out.println("\nFor How many days: ");
		int days=sc.nextInt();
		PreparedStatement preparedStatement=conn.prepareStatement(Queries.SELECT_GUESTID);
		preparedStatement.setString(1, guestId);
		ResultSet rs=preparedStatement.executeQuery();
		if(rs.next()==false) {
			System.out.println("Oh no! user not registered..."); // check whether user exist or not
		}
		else {
		switch(choice) {
		case 1:
			preparedStatement=conn.prepareStatement(Queries.INSERT_BILL);
			preparedStatement.setString(1, guestId);
			preparedStatement.setInt(2, roomNo);
			preparedStatement.setString(3, "Food");
			preparedStatement.setInt(4, 500*days);
			preparedStatement.executeUpdate();
			System.out.println("Hey! Service Added Successfully");
			break;
		case 2:
			preparedStatement=conn.prepareStatement(Queries.INSERT_BILL);
			preparedStatement.setString(1, guestId);
			preparedStatement.setInt(2, roomNo);
			preparedStatement.setString(3, "Beverges");
			preparedStatement.setInt(4, 250*days);
			preparedStatement.executeUpdate();
			System.out.println("Hey! Service Added Successfully");
			break;
		case 3:
			preparedStatement=conn.prepareStatement(Queries.INSERT_BILL);
			preparedStatement.setString(1, guestId);
			preparedStatement.setInt(2, roomNo);
			preparedStatement.setString(3, "Beverges");
			preparedStatement.setInt(4, 300*days);
			preparedStatement.executeUpdate();
			System.out.println("Hey! Service Added Successfully");
			break;
		case 4:
			preparedStatement=conn.prepareStatement(Queries.INSERT_BILL);
			preparedStatement.setString(1, guestId);
			preparedStatement.setInt(2, roomNo);
			preparedStatement.setString(3, "Cab");
			preparedStatement.setInt(4, 1000*days);
			preparedStatement.executeUpdate();
			System.out.println("Hey! Service Added Successfully");
			break;
		case 5:
			flag=0;
		default:
			System.out.println("Sorry! I didn't get you...");
		}
		}
		}while(flag==1);
	}

}
