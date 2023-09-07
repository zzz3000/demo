package jobtest.service;

import java.math.BigDecimal;

import jobtest.Account;
import jobtest.service.PaymentService.NotEnoughMoneyException;

/**
 * интрефейс для стратегии оплаты
 * @author sanek
 */
public interface PayStrat {

	/**
	 * оплата
	 * @param account - счет с которого происходит оплата
	 * @param amount - сумма покупки
	 */
	void pay(Account account, BigDecimal amount) throws NotEnoughMoneyException;
}
