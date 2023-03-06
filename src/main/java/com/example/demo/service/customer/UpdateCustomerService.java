package com.example.demo.service.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.classes.RoomReturn;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class UpdateCustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	int i;

	public void updateCustomer(List<Customer> list, List<RoomReturn> r,String CategoryType) {
		getCustomerIdFromList(list, r,CategoryType);
	}

	private void getCustomerIdFromList(List<Customer> list, List<RoomReturn> r,String CategoryType) {
		for(int i=0;i<list.size();i++)
		{
			int roomIndex= getRoomIndex(  i , CategoryType);
			Customer c=list.get(i);
			c.setRoomNo(r.get(roomIndex).getRoomNo());
			customerRepository.save(c);
		}	
	}

	private int  getRoomIndex( int i ,String CategoryType) {
		int roomIndex;
		switch (CategoryType) {
		case "Single Bed": 
			roomIndex=i;
		        break;		        
		case "Double Bed":
			roomIndex=i/2;
			break;			
		case "Triple Bed":
			roomIndex=i/3;
			break;
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + CategoryType);
		}
		return roomIndex;
	}

//	private void validate(List<Customer> list) {
//		for (Customer c : list) {
//			if (c.getAge() <= 0) {
//				throw new ValidationException("FV001", " age cannot be 0/smaller then 0 or empty");
//			} else if (c.getCustomerfullname() == null || c.getCustomerfullname().isBlank()
//					|| c.getCustomerfullname().isEmpty()) {
//				throw new ValidationException("FV002", " customer name cannot be null or empty");
//			} else if (c.getCustomeraddress() == null || c.getCustomeraddress().isBlank()
//					|| c.getCustomeraddress().isEmpty()) {
//				throw new ValidationException("FV003", " customer address cannot be null or empty");
//			} else if (c.getCustomerid() <= 0) {
//				throw new ValidationException("FV004", " customer id cannot be 0 or smaller then 0");
//			}
//		}
//	}
}
