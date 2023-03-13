# Hotel Management
The entitled project is basically a CRUD application which deals with 4 entities (Booking ,Category , Customer , Room). Where a customer choose category 
of room and automatically the room will be assigned on the basics of the customer requirements it teh room is available.

Mapping is made in such a way that one booking can contain multiples customer and multiple rooms of same category too.

Listing a short description of some methods of Booking Service Class.

createBookingDeatils --> it varifies all the input of bookingDetails and Customer 
                     --> it assign the room to customers basis of category and occupancy
                         it also add room number to customer table 
                         it return booked rooms info, total bill and booking id

deleteBookingDetails --> it delete the booking details with customers and also make room available to customers againe

getAllBookingDetails --> it get all the bookings 

updateBookingDetails --> in this we can update booking data but ( use it only when we have to change category of room) else it will assign a new room it will not update room 


updateBookingDeatilsUsingPatch ---> i use this method because using update i have to gave all information of booking but with this we can update any data.


createCustomer ---> this method just add room number to customer table 
