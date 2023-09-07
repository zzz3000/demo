package jobtest.service;

import java.math.BigDecimal;

import jobtest.Account;
import jobtest.service.PaymentService.NotEnoughMoneyException;

/**
 * стратегии оплаты : стандартная оплата офлайн от 20 до 300 р
 * бонус 10% в банк
 * @author sanek
 */
public class PayStratShop extends PayStratCommon {

	
	/**
	 * процент бонуса за покупку  (%/100)
	 */
	public static BigDecimal BONUS_PERCENT = new BigDecimal(0.10);
	
	/**
	 * платеж + начисляем бонус BONUS_PERCENT
	 */
	@Override
	public void pay(Account account, BigDecimal ammount)  throws NotEnoughMoneyException{
		BigDecimal before = account.getNonCacheAmount();
		
		synchronized (account) {
			if (before.compareTo(ammount) >= 0) {
				account.setBalance(before.subtract(ammount));			
				addBonus(account, ammount, BONUS_PERCENT);			
			}else {
				 throw new NotEnoughMoneyException();
			}
		}
	}

}
