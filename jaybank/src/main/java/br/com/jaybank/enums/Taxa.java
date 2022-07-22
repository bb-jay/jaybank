package br.com.jaybank.enums;

public enum Taxa {
	PJ(
			0.005,
			0.005,
			0.005,
			0.007),
	PF(
			0,
			0,
			0.003,
			0.010);

	private static final long serialVersionUID = 0400000L;
	private final double transferencia;
	private final double saque;
	private final double rendimento;
	private final double juros;

	Taxa(
			double transferencia,
			double saque,
			double rendimento,
			double juros) {
		this.transferencia = transferencia;
		this.saque = saque;
		this.rendimento = rendimento;
		this.juros = juros;
	}

	public double getJuros() {
		return this.juros;
	}

	public double getTransferencia() {
		return this.transferencia;
	}

	public double getSaque() {
		return this.saque;
	}

	public double getRendimento() {
		return this.rendimento;
	}

}
