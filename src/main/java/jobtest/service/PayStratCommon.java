package jobtest.service;

import java.math.BigDecimal;

import jobtest.Account;

public abstract class PayStratCommon implements PayStrat {
	
	/**
	 * начисляем бонус за платеж
	 * @param account 
	 * @param amount - сумма платежа
	 * @param percent - процент для расчета бонуса
	 */
	public void addBonus(Account account, BigDecimal amount, BigDecimal percent) {		
		BigDecimal bonus = amount.multiply(percent).setScale(2,BigDecimal.ROUND_HALF_DOWN) ;			
		account.setBonus(account.getBonus().add(bonus)); 
	}

}
