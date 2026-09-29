package aplicacao;

import validacao.ValidadorCPF;

public class Main {
    public static void main(String[] args) {
        
        ValidadorCPF validador = new ValidadorCPF();

        // -------------------------------------------------------------------
        // 1. CHAMADA PERMITIDA (Método public)
        // -------------------------------------------------------------------
        boolean resultado1 = validador.validar("123.456.789-09");
        boolean resultado2 = validador.validar("11111111111");

        System.out.println("CPF 123.456.789-09 é válido? " + resultado1);
        System.out.println("CPF 111.111.111-11 é válido? " + resultado2);

        // -------------------------------------------------------------------
        // 2. ERROS DE COMPILAÇÃO (Tentando acessar private e protected)
        // -------------------------------------------------------------------
        
        // ❌ ERRO! 'limparCPF' é PRIVATE. Não pode ser visto de fora do ValidadorCPF.
        // String textoLimpo = validador.limparCPF("123.456.789-09"); 

        // ❌ ERRO! 'calcularDigitosVerificadores' é PROTECTED. 
        // Como 'Main' está em outro pacote ('aplicacao') e NÃO é uma subclasse, não tem acesso.
        // boolean digitosOk = validador.calcularDigitosVerificadores("12345678909"); 
    }
}
