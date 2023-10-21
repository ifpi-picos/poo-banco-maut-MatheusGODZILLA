public class ContaPoupanca extends Conta {
    private double rendimento;

    public ContaPoupanca(int numeroAgencia, int numeroConta, double saldo, double rendimento, Cliente cliente) {
        super(numeroAgencia, numeroConta, saldo, cliente);
        this.rendimento = rendimento;
    }

    @Override
    public double sacar(double valor) {
        if (valor <= saldo) {
            double taxa = valor * 0.05; // Taxa de 5% para saques
            super.sacar(valor + taxa); 
            return valor;
        } else {
            System.out.println("Saldo insuficiente");
            return 0;
        }
    }

    @Override
    public boolean depositar(double valor) {
        double rendimentoValor = valor * rendimento;
        double valorComRendimento = valor + rendimentoValor;
        transacao.adicionarTransacao("Depósito com Rendimento", valorComRendimento, obterDataHoraFormatada());
        return super.depositar(valorComRendimento);
    }

    @Override
    public void transferir(Conta destino, double valor) {
        if (valor <= saldo) {
            double taxa = valor * 0.10; // Taxa de 10% para transferências
            super.sacar(taxa);
            destino.depositar(valor - taxa);
        } else {
            System.out.println("Saldo insuficiente");
        }
    }
}