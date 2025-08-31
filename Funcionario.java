import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//2 - Classe Funcionário que estenda a classe Pessoa, com os atributos: salário (BigDecimal) e função (String).
// Classe Funcionario que herda de Pessoa
public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    // Construtor
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    // Getters e Setters
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
    //3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
    // - informação de data deve ser exibido no formato dd/mm/aaaa;
    // -informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
    // Método para exibir os dados formatados
    public void exibir() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        String dataFormatada = getDataNascimento().format(formatter);
        String salarioFormatado = nf.format(salario);

        System.out.println("Nome: " + getNome());
        System.out.println("Data de Nascimento: " + dataFormatada);
        System.out.println("Salário: " + salarioFormatado);
        System.out.println("Função: " + funcao);
        System.out.println("-----------------------------");
    }
}