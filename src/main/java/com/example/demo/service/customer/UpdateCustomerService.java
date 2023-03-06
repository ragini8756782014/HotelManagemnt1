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

	public void updateCustomer(List<Customer> list, List<RoomReturn> r) {
		getCustomerIdFromList(list, r);
	}

	private void getCustomerIdFromList(List<Customer> list, List<RoomReturn> r) {
		for (Customer c : list) {
			Optional<Customer> customer = customerRepository.findById(c.getCustomerid());
			if (customer.isPresent()) {

				if (list.size() == r.size()) {

					c.setRoomNo(getRoomNoForCustomer(r));
					customerRepository.save(c);
				} else {
					if (i == r.size()) {
						i = 0;
					}
					c.setRoomNo(getRoomNoForCustomer(r));
					customerRepository.save(c);
				}
			}
		}
	}

	private int getRoomNoForCustomer(List<RoomReturn> r) {
		int roomNo = 0;
		for (i = 0; i < r.size(); i++) {

			roomNo = r.get(i).getRoomNo();
		}
		return roomNo;
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
