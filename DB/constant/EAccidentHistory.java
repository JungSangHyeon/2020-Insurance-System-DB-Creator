package constant;

public enum EAccidentHistory {
	TotalLoss, /*침수차량으로  가치가 없어져 보험회사에 보험가입 전액을 청구할수 있게되는 상태로, 이력이 있는지*/
	Immersion, /*침수차량이지만 고장나진 않은 상태*/
	Theft, /*도난차량*/
	DamageToMyCar,
	DamageForOhterCar;
}