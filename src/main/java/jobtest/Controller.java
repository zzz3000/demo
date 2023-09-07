package jobtest;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jobtest.service.PaymentService;
import jobtest.service.PaymentService.NotEnoughMoneyException;
import jobtest.service.PaymentService.PaymentType;

@RestController
@RequestMapping("/api")
public class Controller {
	

	@Autowired
	private PaymentService paymentService;
	
	/** 
	 * проведение платежа в магазине
	 * @param amount
	 * @return
	 * @throws NotEnoughMoneyException
	 */
	@GetMapping("/payment/shop/{amount}")
	ResponseEntity paymentShop(@PathVariable Integer amount) throws NotEnoughMoneyException {		
		try {
			paymentService.pay(PaymentType.SHOP, amount);	
		}catch (NotEnoughMoneyException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	/**
	 * проведение платежа онлайн
	 * @param amount
	 * @return
	 * @throws NotEnoughMoneyException
	 */
	@GetMapping("/payment/online/{amount}")
	ResponseEntity paymentOnline(@PathVariable Integer amount)  throws NotEnoughMoneyException {	
		try {
			paymentService.pay(PaymentType.ONLINE, amount);
		}catch (NotEnoughMoneyException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	/**
	 * получение бонусов на счете в банке
	 * @return
	 */
	@GetMapping("/bankAccountOfEMoney")
	ResponseEntity<BigDecimal>  getAccountBonus() {
		return new ResponseEntity<BigDecimal>(paymentService.getAccount().getBonus(),HttpStatus.OK);
	}
	
	/**
	 * получение баланса на аккаунте
	 * @return
	 */
	@GetMapping("/money")
	ResponseEntity<BigDecimal> getAccountMoney() {
		return new ResponseEntity<BigDecimal>(paymentService.getAccount().getNonCacheAmount(),HttpStatus.OK);
	}
	
	@GetMapping("/account")
	ResponseEntity<Account> getAccount() {
		return new ResponseEntity<Account>(paymentService.getAccount(),HttpStatus.OK);
	}

}
