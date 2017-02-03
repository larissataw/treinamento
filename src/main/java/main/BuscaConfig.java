package main;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuscaConfig {

	private String termo;
	private int pagina;
	private IntervaloDeBusca intervalo;
	
	public BuscaConfig(String termo){
		
		this.termo = termo;
		this.pagina = 2;
		this.intervalo = IntervaloDeBusca.NO_ULTIMO_MES;
		
	}

}
