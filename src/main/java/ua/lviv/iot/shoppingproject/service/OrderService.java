package ua.lviv.iot.shoppingproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import ua.lviv.iot.shoppingproject.exceptions.OrderNotFoundException;
import ua.lviv.iot.shoppingproject.dal.OrderRepository;
import ua.lviv.iot.shoppingproject.models.Order;

@Service
@ApplicationScope
public class OrderService {

	private AtomicInteger id = new AtomicInteger(0);
	
	private Map<Integer, Order> ordersMap = new HashMap<Integer, Order>();

	@Autowired
	private OrderRepository repository;
	
	public Order addOrder(Order order) {
		return repository.save(order);
	}
	
	public Order updateOrder(Order order) throws  OrderNotFoundException{
		if (repository.existsById(order.getId())) {
			return repository.save(order);
		}
		throw new OrderNotFoundException("Order with id:" + id + " not found in the system.");
	}
	
	public List<Order> getOrders() {
		return repository.findAll();
	}

	public Order getOrder(Integer id) {
		return repository.findById(id).orElseThrow();
	}
}
