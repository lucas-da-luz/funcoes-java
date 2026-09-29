package validacao;

public class ValidadorCPF {

    // =========================================================================
    // 1. MÉTODO PÚBLICO (public)
    // Acessível por QUALQUER classe do sistema. Serve como interface principal.
    // =========================================================================
    public boolean validar(String cpf) {
        // Passo A: Limpa o CPF usando o método privado de suporte
        String cpfLimpo = limparCPF(cpf);

        // Passo B: Aplica regras básicas de formato
        if (cpfLimpo == null || cpfLimpo.length() != 11 || eSequenciaInvalida(cpfLimpo)) {
            return false;
        }

        // Passo C: Chama o método protegido de cálculo dos dígitos
        return calcularDigitosVerificadores(cpfLimpo);
    }

    // =========================================================================
    // 2. MÉTODOS PRIVADOS (private)
    // Acessíveis APENAS dentro desta classe. Protegem a lógica interna.
    // =========================================================================
    
    // Remove caracteres especiais (pontos, traços, espaços)
    private String limparCPF(String cpf) {
        if (cpf == null) return null;
        return cpf.replaceAll("[^0-9]", ""); // Mantém apenas dígitos
    }

    // Verifica se é uma sequência padrão inválida como "111.111.111-11"
    private boolean eSequenciaInvalida(String cpf) {
        return cpf.matches("(\\d)\\1{10}");
    }

    // =========================================================================
    // 3. MÉTODO PROTEGIDO (protected)
    // Acessível na MESMA pasta/pacote E por SUBCLASSES (herança) em outros pacotes.
    // Permite que classes filhas aproveitem ou sobrescrevam o algoritmo de cálculo.
    // =========================================================================
    protected boolean calcularDigitosVerificadores(String cpf) {
        int digito1 = calcularDigito(cpf.substring(0, 9), 10);
        int digito2 = calcularDigito(cpf.substring(0, 9) + digito1, 11);

        // Verifica se os dígitos calculados batem com os dois últimos do CPF
        return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
    }

    // Método privado auxiliar para a fórmula matemática dos dígitos do CPF
    private int calcularDigito(String str, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;

        for (int i = 0; i < str.length(); i++) {
            soma += Character.getNumericValue(str.charAt(i)) * peso--;
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }
}
