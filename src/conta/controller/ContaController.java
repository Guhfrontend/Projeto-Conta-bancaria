package conta.controller;

import conta.model.Conta;
import conta.repository.ContaRepository;
import java.util.ArrayList;

public class ContaController implements ContaRepository {

    private int numero;
    private ArrayList<Conta> listaContas = new ArrayList<>();

    @Override
    public void procurarPorNumero(int numero) {
            var conta = buscarNaCollection(numero);
            if (conta != null) {
                conta.visualizar();
            }else
                System.out.println("\nA conta número: " + numero + " não foi encontrada!");

    }

    @Override
    public void listarTodas() {
            for (var conta : listaContas){
                conta.visualizar();
            }
    }

    @Override
    public void cadastrar(Conta conta) {
        listaContas.add(conta);
        System.out.println("\nA conta de número: " + conta.getNumero() + " foi criada com sucesso!");
    }

    @Override
    public void atualizar(Conta conta) {
        var buscarConta = buscarNaCollection(conta.getNumero());

        if (buscarConta != null){
            listaContas.set(listaContas.indexOf(buscarConta), conta);
            System.out.println("\nA conta numero: " + conta.getNumero() + " foi atualizada com sucesso!");
        }else
            System.out.println("\nA conta numero: " + conta.getNumero() + " não foi encontrada!");

    }

    @Override
    public void deletar(int numero) {
        var conta = buscarNaCollection(numero);

        if (conta != null){
            if (listaContas.remove(conta) == true) {
                System.out.println("\nA conta numero: " + numero + " foi deletada com sucesso!");
            }
        }else {
            System.out.println("\nA conta numero: " + numero + "não foi encontrada!");
        }
    }

    @Override
    public void sacar(int numero, float valor) {
        var conta = buscarNaCollection(numero);

        if (conta != null){
            if (conta.sacar(valor) == true){
                System.out.println("\nO saque na conta numero: " + numero + " foi efetuado com sucesso!");
            }
        }else {
            System.out.println("\nA conta numero: " + numero + "n]ao foi encontrada!");
        }

    }

    @Override
    public void depositar(int numero, float valor) {
        var conta = buscarNaCollection(numero);

        if (conta != null){
            conta.deposito(valor);
            System.out.println("\nO saque na conta numero: " + numero + " foi efetuado com sucesso!");

        }else {
            System.out.println("\nA conta numero: " + numero + "n]ao foi encontrada!");
        }

    }

    @Override
    public void transferir(int numeroOrigem, int numeroDestino, float valor) {
        var contaOrigem = buscarNaCollection(numeroOrigem);
        var contaDestino = buscarNaCollection(numeroDestino);

        if (contaOrigem != null && contaDestino != null){
            if (contaOrigem.sacar(valor) == true){
                contaDestino.deposito(valor);
                System.out.println("\nA transferencia foi efetuada com sucesso!");
            }else {
                System.out.println("\nA conta de origem e/ou destino não forma encontradas!");
            }
        }

    }
    public int gerarNumero(){
        return ++numero;
    }

    public Conta buscarNaCollection(int numero){
        for (var conta : listaContas){
            if (conta.getNumero() == numero){
                return conta;
            }
        }
        return null;
    }

}
