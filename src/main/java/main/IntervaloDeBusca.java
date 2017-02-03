package main;

public enum IntervaloDeBusca {

	EM_QUALQUER_DATA,
	NA_ULTIMA_HORA,
	NAS_ULTIMAS_24_HORAS,
	NA_ULTIMA_SEMANA,
	NO_ULTIMO_MES,
	NO_ULTIMO_ANO;
//	INTERVALO_PERSONALIZADO;
	
	/*
 	De	//div/form/input[contains(@class, 'ktf mini cdr_mm cdr_min')]

	A	//div/form/input[contains(@class, 'ktf mini cdr_mm cdr_max')]

	Ir 	//div/form/input[contains(@class, 'ksb mini cdr_go')]
		OU
		//input[contains(@class, 'ksb mini cdr_go')][@value="Ir"]
	 */
	
	   public String getXPath() {
		   switch(this) {
		   	case EM_QUALQUER_DATA:   return "//div/ul/li[contains(@class, 'hdtbItm')][@id='qdr_']";
	   		case NA_ULTIMA_HORA: return "//div/ul/li[contains(@class, 'hdtbItm')][@id='qdr_h']";
	   		case NAS_ULTIMAS_24_HORAS:     return "//div/ul/li[contains(@class, 'hdtbItm')][@id='qdr_d']";
	   		case NA_ULTIMA_SEMANA:     return "//div/ul/li[contains(@class, 'hdtbItm')][@id='qdr_w']";
	   		case NO_ULTIMO_MES:      return "//div/ul/li[contains(@class, 'hdtbItm')][@id='qdr_m']";
	   		case NO_ULTIMO_ANO:     return "//div/ul/li[contains(@class, 'hdtbItm')][@id='qdr_y']";
	   		//case INTERVALO_PERSONALIZADO:     return "//div/ul/li[contains(@class, 'hdtbItm')][@id='cdr_opt']";
		   	default :       return "";
		   }
	   }
	   
}