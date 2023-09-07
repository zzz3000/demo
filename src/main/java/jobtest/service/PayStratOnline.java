package jobtest.service;

import java.math.BigDecimal;

import jobtest.Account;
import jobtest.service.PaymentService.NotEnoughMoneyException;

/**
 * стратегии оплаты: стандартная оплата онлай от 20 до 300 р
 * бонус 17% в банк
 * @author sanek
 *
 */
public class PayStratOnline extends PayStratCommon {

	/**
	 * процент бонуса за покупку  (%/100)
	 */
	public static BigDecimal BONUS_PERCENT = new BigDecimal(0.17);
	
	
	/**
	 * платеж + начисляем бонус BONUS_PERCENT
	 */
	@Override
	public void pay(Account account, BigDecimal amount) throws NotEnoughMoneyException {		
		BigDecimal before = account.getNonCacheAmount();		

		synchronized (account) {
			if (before.compareTo(amount) >= 0 ) {			
				account.setBalance(before.subtract(amount));			
				addBonus(account, amount, BONUS_PERCENT);			
			}else {
				 throw new NotEnoughMoneyException();
			}	
		}
	}
}
