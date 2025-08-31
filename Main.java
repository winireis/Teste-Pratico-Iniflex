import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //3.1 - Inserir todos os funcionários, na mesma ordem e informações da tabela
        List<Funcionario> funcionarios = new ArrayList<>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Gerente"));

         //3.2 – Remover o funcionário “João”
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));
        
        //3.3 - Imprimir todos os funcionários com todas suas informações
        System.out.println("=== Imprimir todos os funcionários com todas suas informações ===");
        for (Funcionario f : funcionarios) {
          f.exibir();
        }
        
        //3.4 – Aumento de 10%
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

        //3.5 – Agrupar funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao =
                funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        //3.6 – Imprimir agrupados por função
        System.out.println("=== Funcionários agrupados por função ===");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("==== Função: " + funcao + " ====");
            lista.forEach(Funcionario::exibir);
        });

        //3.8 – Funcionários com aniversário em outubro (10) e dezembro (12)
        System.out.println("=== Funcionários aniversariantes em Outubro e Dezembro ===");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonth() == Month.OCTOBER ||
                             f.getDataNascimento().getMonth() == Month.DECEMBER)
                .forEach(Funcionario::exibir);

        //3.9 – Funcionário com maior idade
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);

        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("=== Funcionário mais velho ===");
            System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + idade);
            System.out.println("-----------------------------");
        }

        //3.10 – Lista em ordem alfabética
        System.out.println("=== Funcionários em ordem alfabética ===");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(Funcionario::exibir);

        //3.11 – Total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("=== Total dos salários ===");
        System.out.println("R$ " + totalSalarios);
        System.out.println("-----------------------------");

        //3.12 – Quantos salários mínimos cada funcionário ganha
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("=== Quantos salários mínimos cada funcionário ganha ===");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + " - " + qtd + " salários mínimos");
            System.out.println("-----------------------------");
        });
    }
}