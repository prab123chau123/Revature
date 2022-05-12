# Revature
HOTEL MANAGEMENT SYSTEM (Receptionist Model)

Receptionist Module which consists of overall 5 options.The receptionist can access the available Rooms , can check the guest/customer regiistration and details of customer and can generate the bills of the customer based on the stay and service used by the Customer.

The entire project is developed by the Core java Programming in which interface class is defined which was a DAO file in which all the abstact methods where defined and those methods were implemented in concrete classes.To connect and establish the connection from databse JDBC was used so its has connected the java code with sql for fetching details and storing the data.
First through connection interface we have established the connection to database and to store data preparedstatement was used and resultset interface was used to featch and display the data to the java console.
The below model was a menu driven which was displayed using do while loop and it gave a flexibilty for receptionist to access all the options.
♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦
------------------- Hotel OLDTOWN ----------------
♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦
                  Welcome Receptionist!

      Please Choose from below options-

      1. See Available Rooms
      2. Check customer registration
      3. Booking by Receipnist (10% off)
      4. Show Room Types (Normal, Deluxe, Super Deluxe, Executive Suite)
      5. See total bill for specific customer

      Press (n) to exit...
---------------------------------------------------

SQL(Model)

"ROOM"(First table which consists of 7 columns)
ROOM_NO 
TYPE (Normal, Deluxe, Super Deluxe, Executive Suite)
TELEVISION
AIR_CONDITIONER
WASHIN_MACHINE
KITECH
CHARGES

"BOOKING_INFOMATION"(Second table which consists of 11 columns with one primary key and one forgein key which is reference of room no)
BOOKING_ID --- PRIMARY KEY FO BOOKING_INFORMATION
GUEST_ID -- FK for USERID
ROOOM_ID --- FK fro ROOM_NO
CHECKIN_DATE  -- DEFAUL TIME is 12.00noon
CHECKOUT_DATE -- DEFAUL TIME is 12.00noon
ID_PROOF
VEHICLE_NO
MOBILE_NO
ADDRESS_COMING_FROM
REASON_FOR_STAY

"ROOM_SERVICES"(Third table consists of 4  columns and compromised of 4 columns with one foreign key reference of booking information column booking_id)
BOOKING_ID --- FK from BOOKING_INFORMATION
SERVICE_TYPE (Food, Laundry, Meal, Bevarages, Cab)
CHARGES
DATETIME_OF_SERVICES  

So in sql we have used tables such as room table which consists of column such as room no which is primary key followed by room type and the services in room provided such as(television,air conditioner,wshing machine ) and charges.Depend on room types the charges changes.
second table is booking information and third table is room services.
first guest have to register to have booking in the hotel and the username must start with G else it will through the error and user cannot register.

Required Tools to develop this project.
1.Eclipse IDE environment for writing the code.
2.MySQL for database.
3.JDBC for establising the connection between sql and java.
4.Core java 
