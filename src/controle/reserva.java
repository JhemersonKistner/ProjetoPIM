/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

/**
 *
 * @author Jhemerson
 */
public class reserva {

    private int idreserva;
    private String nome_cliente;
    private int numquarto;
    private int valor_diaria;
    private int num_diarias;
    private String statuss;
    private int valor_total = valor_diaria * num_diarias ; 

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public int getNumquarto() {
        return numquarto;
    }

    public void setNumquarto(int numquarto) {
        this.numquarto = numquarto;
    }

    public int getValor_diaria() {
        return valor_diaria;
    }

    public void setValor_diaria(int valor_diaria) {
        this.valor_diaria = valor_diaria;
    }

    public int getNum_diarias() {
        return num_diarias;
    }

    public void setNum_diarias(int num_diarias) {
        this.num_diarias = num_diarias;
    }

    public String getStatuss() {
        return statuss;
    }

    public void setStatuss(String statuss) {
        this.statuss = statuss;
    }

    public int getValor_total() {
        return valor_total;
    }

    public void setValor_total(int valor_total) {
        this.valor_total = valor_total;
    }
}
