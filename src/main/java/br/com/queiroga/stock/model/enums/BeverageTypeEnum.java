package br.com.queiroga.stock.model.enums;

public enum BeverageTypeEnum {
	
	ALCOHOLIC(500),NON_ALCOHOLIC(400);

	private Integer maxVolume;
	
	BeverageTypeEnum(Integer maxVolume) {
		this.maxVolume = maxVolume;
	}

	public Integer getMaxVolume() {
		return maxVolume;
	}
	
}
