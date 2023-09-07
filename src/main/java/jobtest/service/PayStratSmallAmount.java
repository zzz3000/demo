package jobtest.service;

import java.math.BigDecimal;

import jobtest.Account;
import jobtest.service.PaymentService.NotEnoughMoneyException;

/**
 * стратегии оплаты : любая покупка дешевле 20 рублей
 * @author sanek
 */
public class PayStratSmallAmount implements PayStrat{

	/**
	 * процент комиссии банка
	 */
	public static BigDecimal BANK_COMMISSION = new BigDecimal(0.10);
	
	
	/**
	 * платеж + вычитаем дополнительную комиссию со счета
	 */
	@Override
	public void pay(Account account, BigDecimal amount) throws NotEnoughMoneyException {
		
		BigDecimal before = account.getNonCacheAmount();
		
		BigDecimal comission = amount.multiply(BANK_COMMISSION).setScale(2,BigDecimal.ROUND_DOWN);		
		
		synchronized (account) {
			if (before.subtract(amount).subtract(comission).doubleValue() >= 0) {
				account.setBalance(before.subtract(amount).subtract(comission));
			} else {
				throw new NotEnoughMoneyException();
			}
		}		
	}

}
