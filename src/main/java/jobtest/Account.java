package jobtest;

import java.math.BigDecimal;

/**
 * средства на счете + бонус в банке
 * @author sanek
 *
 */
public class Account {

	/**
	 * средства на счете (A)
	 */
	BigDecimal balance;
	
	/**
	 * бонусы в банке (D)
	 */
	BigDecimal bonus;

	public Account(BigDecimal nonCacheAmount, BigDecimal eMoney) {
		super();
		this.balance = nonCacheAmount;
		this.bonus = eMoney;
	}

	public BigDecimal getNonCacheAmount() {
		return balance;
	}

	public void setBalance(BigDecimal nonCacheAmount) {
		this.balance = nonCacheAmount;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal eMoney) {
		this.bonus = eMoney;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", bonus=" + bonus + "]";
	}
	
	
}
