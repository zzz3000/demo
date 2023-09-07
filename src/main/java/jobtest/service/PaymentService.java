package jobtest.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import jobtest.Account;

/**
 * сервис для проведения платежа 
 * и получения аккаунта
 * @author sanek
 *
 */
@org.springframework.stereotype.Service
public class PaymentService {
	
	/**
	 * тип платежа онлайн или магазин
	 * @author sanek
	 *
	 */
	public static enum PaymentType{
		ONLINE,
		SHOP
	}
	
	/**
	 * exception при недостатке денег на счете для оплаты
	 * @author sanek
	 *
	 */
	public static class NotEnoughMoneyException extends Exception{
		
	}

	/**
	 * информация о балансе и бонусах в банке
	 */
	@Autowired
	Account account; 
	
	
	/**
	 * общий метод для всех платежей
	 * @param paymentType - вид платежа
	 * @param amount - сумма покупки
	 * @throws NotEnoughMoneyException
	 */
	public void pay(PaymentType paymentType, int amount) throws NotEnoughMoneyException {
		
		PayStrat paymentStrategy = null;
		
		if (amount < 20) { 
			paymentStrategy = new PayStratSmallAmount();			
		}else if(amount > 300) {
			paymentStrategy = new PayStratBigAmount();			
		}else if(paymentType == PaymentType.ONLINE) {
			paymentStrategy = new PayStratOnline();			
		}else if(paymentType == PaymentType.SHOP) {
			paymentStrategy = new PayStratShop();			
		}
		
		paymentStrategy.pay(account, new BigDecimal(amount) );
	}
	
	
	public Account getAccount() {
		return account;
	}
	
	
}
