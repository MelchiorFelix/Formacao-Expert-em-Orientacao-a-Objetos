
public class TesteDeImpostos {
	
	public static void main(String[] args) {
		Imposto iss = new ISS();
		Imposto icms = new ICMS();
		Imposto iccc = new ICCC();
		
		Orcamento orcamento = new Orcamento(3001.0);
		
		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		
		//calculando o ISS
		calculador.realizaCalculo(orcamento, iss);
		
		//calculando o ICMS
		calculador.realizaCalculo(orcamento, icms);
		
		//calulando o ICC
		calculador.realizaCalculo(orcamento, iccc);
	}
}
