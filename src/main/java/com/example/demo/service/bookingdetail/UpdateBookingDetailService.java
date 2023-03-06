package com.example.demo.service.bookingdetail;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.demo.classes.BookingDetailsReturn;
import com.example.demo.classes.RoomReturn;
import com.example.demo.entity.BookingDetails;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.BookingDetailsRepository;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.customer.UpdateCustomerService;
import com.example.demo.service.room.FindRoomNoService;

@Service
public class UpdateBookingDetailService {
	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;
	@Autowired
	private UpdateCustomerService updateCustomerService;
	@Autowired
	private FindRoomNoService findRoomNoService;

	public BookingDetailsReturn updateBookingDetails(BookingDetails bookingDetails) {
//		validate(bookingDetails);
		Optional<BookingDetails> dbBookingDetails = bookingDetailsRepository.findById(bookingDetails.getBookingid());

		List<RoomReturn> r = findRoomNoService.findRoomNo(bookingDetails.getCategoryType(),
				bookingDetails.getNoOfRooms());
		if (r.isEmpty()) {
			throw new ValidationException("FV001", "We do not have this category room empty");
		}
		updateCustomerService.updateCustomer(bookingDetails.getC(), r,bookingDetails.getCategoryType());
		if (dbBookingDetails.isPresent()) {
			bookingDetailsRepository.save(bookingDetails);
		}
		return createEntity1(
				findRoomNoService.findRoomNo(bookingDetails.getCategoryType(), bookingDetails.getNoOfRooms()),
				bookingDetails);
	}

	public BookingDetailsReturn createEntity1(List<RoomReturn> r, BookingDetails bd) {
		BookingDetailsReturn br = new BookingDetailsReturn();
		br.setBookingId(bd.getBookingid());
		br.setRoomReturn(r);
		br.setTotalPayment(getTotalPayment(r, bd.getNoOfRooms(), bd.getStartDate(), bd.getEnddate()));
		return br;
	}

	private int getTotalPayment(List<RoomReturn> r, int noOfRooms, String startDate, String endDate)  {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	     int  total = 0;
		try {
			Date d1 = sdf.parse(startDate);
			Date d2 = sdf.parse(endDate);
			int difference_In_Time = (int) (d2.getTime() - d1.getTime());
			int difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
			if(difference_In_Days==0)
			{
			  int difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
			   if(difference_In_Hours <24)
			   {
				   for (RoomReturn roomReturn : r) {
						total = roomReturn.getPricePerHour();
						break;
					} 
				   return  total*noOfRooms*difference_In_Hours;
				  
			   }
			   else {
				   for (RoomReturn roomReturn : r) {
						total = roomReturn.getBillAmount();
						break;
					} 
				   return total*noOfRooms*1;
				   
			   }
			}
			else
			{
				for (RoomReturn roomReturn : r) {
					total = roomReturn.getBillAmount();
					break;
				} 
			   return total*noOfRooms*difference_In_Days;
			}
					
		} catch (Exception e) {
			throw new ValidationException("FV001", " something went wrong in Date Time ");
		}
	}

	public BaseResponse updateBookingDetailsUsingPatch(Long id, Map<String, Object> fields) {
		Optional<BookingDetails> dbBookingDetails = bookingDetailsRepository.findById(id);
		BaseResponse baseresponse = new BaseResponse();
		if (dbBookingDetails.isPresent()) {
			fields.forEach((key, value) -> {

				Field field = ReflectionUtils.findField(BookingDetails.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, dbBookingDetails, value);

			});
			bookingDetailsRepository.save(dbBookingDetails.get());
			baseresponse.setStatus(HttpStatus.ACCEPTED.value());
			baseresponse.setMessage("Your bookingDetail is updated");
			return baseresponse;
		}

		baseresponse.setStatus(HttpStatus.BAD_REQUEST.value());
		baseresponse.setMessage("this id do not exist ");
		return baseresponse;
	}

//	private void validate(BookingDetails bookingDetails) {
//		if (bookingDetails.getCategoryType() == null || bookingDetails.getCategoryType().isBlank()
//				|| bookingDetails.getCategoryType().isEmpty()) {
//			throw new ValidationException("FV001", " category type cannot be null or empty");
//		} else if (bookingDetails.getStartDate() == null || bookingDetails.getStartDate().isBlank()
//				|| bookingDetails.getStartDate().isEmpty()) {
//			throw new ValidationException("FV002", " start date cannot be null or empty");
//		} else if (bookingDetails.getEnddate() == null || bookingDetails.getEnddate().isBlank()
//				|| bookingDetails.getEnddate().isEmpty()) {
//			throw new ValidationException("FV003", " end date cannot be null or empty");
//		} else if (bookingDetails.getModeofbooking() == null || bookingDetails.getModeofbooking().isBlank()
//				|| bookingDetails.getModeofbooking().isEmpty()) {
//			throw new ValidationException("FV004", " mode of booking cannot be null or empty");
//		} else if (bookingDetails.getModeofpayment() == null || bookingDetails.getModeofpayment().isBlank()
//				|| bookingDetails.getModeofpayment().isEmpty()) {
//			throw new ValidationException("FV005", " mode of payment cannot be null or empty");
//		} else if (bookingDetails.getNoOfRooms() <= 0) {
//			throw new ValidationException("FV006", " no of rooms cannot be 0 or empty");
//		} else if (bookingDetails.getBookingid() <= 0) {
//			throw new ValidationException("FV006", " bookingDetails id cannot be 0 or empty");
//		} else {
//
//		}
//	}

}
