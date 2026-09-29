package validacao.corporativo;

import validacao.ValidadorCPF;

// A classe 'ValidadorCPFBancario' HERDA (extends) da classe 'ValidadorCPF'
public class ValidadorCPFBancario extends ValidadorCPF {

    // Método específico da regra de negócio bancária
    public boolean validarParaAberturaDeConta(String cpf, boolean possuiRestricaoSerasa) {
        // Usa o método público herdado
        boolean cpfValido = validar(cpf); 

        if (!cpfValido) {
            System.out.println("Reprovado: CPF inválido.");
            return false;
        }

        if (possuiRestricaoSerasa) {
            System.out.println("Reprovado: CPF com restrição financeira.");
            return false;
        }

        // Exemplo de acesso ao método PROTECTED da classe pai:
        // Como 'ValidadorCPFBancario' é filha de 'ValidadorCPF', 
        // ela pode chamar 'calcularDigitosVerificadores' diretamente se precisar!
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        boolean digitosOk = calcularDigitosVerificadores(cpfLimpo);

        return digitosOk;
    }
}
